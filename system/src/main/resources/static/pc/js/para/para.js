/**
 * make by YANSHUCHUN 2018年9月20日10:16:16
 */
layui.use(['table', 'form','upload'], function () {
    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
    var table = layui.table,form = layui.form,upload = layui.upload;;
    // 页面的渲染↓
    table.render({
        elem: '#table_obj'
        , url: 'paraList.html' //数据接口
        , page: true //开启分页
        , method: 'post'
        , cols: [[ //表头
            {checkbox: true, field: 'id', title: 'ID', width: "5%", fixed: 'left'}
            , {field: 'name', title: '参数名称', width: "15%"}
            , {field: 'value', title: '参数值', width: "15%"}
            , {field: 'mark', title: '备注', width: "15%"}
            , {field: 'enable', title: '状态', width: "10%",templet:"#enable"}
            , {field: 'updatetime', title: '操作时间', width: "15%"}
            , {field: 'insertuser', title: '操作人', width: "5%"}
            , {fixed: 'right', title: '操作',width: "10%", toolbar: '#bar'}
        ]]
    });
    // 页面的渲染↑

    //执行重新加载的方法--页面的搜索方法。 search ↓
    $('.layui-search-body .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    var active = {
        reload: function(){
            var name = $('#name-search').val();
            var mark = $('#mark-search').val();
            //执行重载
            table.reload('table_obj', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    name:name
                    ,mark:mark
                }
            });
        }
    };
//执行重新加载的方法--页面的搜索方法。 search ↑
    //监听工具条↓
    table.on('tool(obj)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            , layEvent = obj.event //获得 lay-event 对应的值
            , objId = data.id; //获取操作的数据的id
        if (layEvent === 'detail') {
            layer.open({
                type: 2,
                anim: 1,
                shadeClose: false,
                maxmin: true,
                title: ['产品详情', 'background:#f2f2f2;color:#333;'],
                shade: .5,
                area: ['600px', '600px'],
                content: '#.html'
            })
        } else if (layEvent === 'del') {
            layer.confirm('真的删除行么', function (index) {
                obj.del(index); //删除对应行（tr）的DOM结构
                layer.close(index);
                $.ajax({
                    url:"doDelPara.html",
                    type:"post",
                    data:{id:objId},
                    dataType:"json",
                    success:function (data) {
                        console.info(data);
                        if (data.retCode==200){
                            active.reload();
                            layer.msg('操作成功', {icon: 1,time:1e3});
                        }else{
                            layer.msg(data.message, {icon: 1,time:1e3});
                        }
                    },
                })
            });
        } else if (layEvent === 'edit') {
            layer.open({
                type: 2,
                anim: 1,
                shadeClose: false,
                maxmin: true,
                title: ['修改', 'background:#f2f2f2;color:#333;'],
                shade: .5,
                area: ['600px', '500px'],
                content: objId+'/toEidtPara.html'
            })
        } else if (layEvent === 'pic') {
            layer.open({
                type: 2,
                anim: 1,
                shadeClose: false,
                maxmin: true,
                title: ['上传图片', 'background:#f2f2f2;color:#333;'],
                shade: .5,
                area: ['600px', '500px'],
                content: objId+'/toUploadPic.html'
            })
        }
    });
    //监听工具条↑


    // 新增↓
    // 新增点击事件的监听 ↓
    $(".tool-bar .btn-add").on('click', function() {
        layer.open({
            type: 2,
            anim: 2,
            shadeClose: false,
            area: ['700px', '600px'],
            title: ['新增', 'background:#f2f2f2;color:#333;'],
            shade: .5,
            content: 'toAddPara.html'
        })

    });
    //监听提交
    form.on('submit(formsub)', function (data) {
        //restful 惹的祸  期待有人能解决。 暂时先用这个笨方法解决吧  ┭┮﹏┭┮
        var url = data.field.id==null?"doSaveOrUpdatePara.html":"../doSaveOrUpdatePara.html";
        // layer.msg($(".layui-form").serialize());
        // return false
        $.ajax({
            type: 'post',
            url: url,
            data: $(".layui-form").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.retCode == 200) {
                    layer.confirm('操作成功,是否继续操作?', {icon: 3, title: '询问'}, function (index) {
                        layer.close(index);
                        return false;
                    }, function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        parent.location.reload();
                    })
                } else {
                    layer.msg(data.message, {icon: 1, time: 1e3});
                }
            }
        });
        return false;
    });
    // 新增↑


    // 业务相关 ↓
    form.on('radio(menuType)',function (data) {
        var typeVal = data.value;
        if (typeVal==0){
            $(".layui-form-label-parentMenucode").hide();
            $(".layui-select-parentMenucode").removeAttr('lay-verify');
            $(".layui-select-parentMenucode").removeAttr('name');
        }else {
            $(".layui-form-label-parentMenucode").show();
            $(".layui-select-parentMenucode").attr('lay-verify','required');
            $(".layui-select-parentMenucode").attr("name","parentMenucode");
        }
    })

    //多文件列表示例
    var demoListView = $('#demoList')
        ,uploadListIns = upload.render({
        elem: '#testList'
        ,url: '../doUploadPic.html'
        // ,accept: 'file'
        ,multiple: true
        ,auto: false
        ,bindAction: '#testListAction'
        ,choose: function(obj){
            var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            //读取本地文件
            obj.preview(function(index, file, result){
                var tr = $(['<tr id="upload-'+ index +'">'
                    ,'<td>'+ file.name +'</td>'
                    ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                    ,'<td>等待上传</td>'
                    ,'<td>'
                    ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                    ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                    ,'</td>'
                    ,'</tr>'].join(''));

                //单个重传
                tr.find('.demo-reload').on('click', function(){
                    obj.upload(index, file);
                });

                //删除
                tr.find('.demo-delete').on('click', function(){
                    delete files[index]; //删除对应的文件
                    tr.remove();
                    uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                });

                demoListView.append(tr);
            });
        }
        ,done: function(res, index, upload){
            if(res.retCode == 200){ //上传成功
                var tr = demoListView.find('tr#upload-'+ index)
                    ,tds = tr.children();
                tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(3).html(''); //清空操作
                return delete this.files[index]; //删除文件队列已经上传成功的文件
            }
            this.error(index, upload);
        }
        ,error: function(index, upload){
            var tr = demoListView.find('tr#upload-'+ index)
                ,tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
            tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
        }
    });


});
