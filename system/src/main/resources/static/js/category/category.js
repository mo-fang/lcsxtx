/**
 * make by YANSHUCHUN 2018年9月20日10:16:16
 */
layui.use(['table', 'form'], function () {
    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
    var table = layui.table,form = layui.form;
    // 页面的渲染↓
    table.render({
        elem: '#table_obj'
        , url: 'categoryList.html' //数据接口
        , page: true //开启分页
        , method: 'post'
        // ,height:'full'
        , limits: [10,20]
        , cols: [[ //表头
            {checkbox: true, field: 'id', title: 'ID', width: '10%', fixed: 'left'}
            , {field: 'name', title: '名称', width: '20%'}
            , {field: 'parentid', title: '所属类别', width: '20%',templet:"#parentid"}
            , {field: 'createtime', title: '创建时间', width: '20%'}
            , {field: 'updatetime', title: '更新时间', width: '20%'}
            , {fixed: 'right', title: '操作',width: '10%', toolbar: '#bar'}
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
            var parentid = $('#parentid-search').val();
            //执行重载
            table.reload('table_obj', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                     name:name
                    ,parentid:parentid
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
                    url:"doDelCategory.html",
                    type:"post",
                    data:{id:objId},
                    dataType:"json",
                    success:function (data) {
                        if (data.retCode==200){
                                    active.reload();
                                // location.reload();
                                layer.msg('操作成功', {icon: 1,time:1e3});
                            }else{
                                layer.msg('操作失败', {icon: 1,time:1e3});
                            }
                    }
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
                area: ['600px', '300px'],
                content: objId+'/toEidtCategory.html'
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
            area: ['600px', '300px'],
            title: ['新增', 'background:#f2f2f2;color:#333;'],
            shade: .5,
            content: 'toAddCategory.html'
        })

    });
    //监听提交
    form.on('submit(formsub)', function (data) {
        //restful 惹的祸  期待有人能解决。 暂时先用这个笨方法解决吧  ┭┮﹏┭┮
        var url = data.field.id==null?"doAddCategory.html":"../doAddCategory.html";
        // layer.msg(JSON.stringify(data.field));
        $.ajax({
            type: 'post',
            url: url,
            data: $(".layui-form").serialize(),
            dataType: "json",
            success: function (data) {
                if (data != 'succ') {
                    layer.confirm('操作成功,是否继续操作?', {icon: 3, title: '询问'}, function (index) {
                        layer.close(index);
                        return false;
                    }, function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        parent.location.reload();
                    })
                } else {
                    layer.msg('操作失败', {icon: 1, time: 1e3});
                }
            }
        });
        return false;
    });
    // 新增↑


    // 业务相关 ↓
    form.on('radio(type)',function (data) {
        var typeVal = data.value;
        if (typeVal==0){
            $(".layui-form-label-parentid").hide();
            $(".layui-select-parentid").removeAttr('lay-verify');
            $(".layui-select-parentid").removeAttr('name');
        }else {
            $(".layui-form-label-parentid").show();
            $(".layui-select-parentid").attr('lay-verify','required');
            $(".layui-select-parentid").attr("name","parentid");
        }
    })

});
