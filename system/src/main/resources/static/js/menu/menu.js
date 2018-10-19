/**
 * make by YANSHUCHUN 2018年9月20日10:16:16
 */
layui.use(['table', 'form'], function () {
    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
    var table = layui.table,form = layui.form;
    // 页面的渲染↓
    table.render({
        elem: '#table_obj'
        , url: 'menuList.html' //数据接口
        , page: true //开启分页
        , method: 'post'
        // ,height:'full'
        , limits: [10,20]
        , cols: [[ //表头
             {checkbox: true, field: 'id', title: 'ID', width: "5%", fixed: 'left'}
            , {field: 'menuUrl', title: 'URL', width:  "20%"}
            , {field: 'menuCode', title: '编码', width:  "10%"}
            , {field: 'menuName', title: '名称', width:  "20%"}
            , {field: 'parentMenucode', title: '父类编码', width:  "20%",templet:"#parentMenucode"}
            , {field: 'menuType', title: '类型', width:  "10%" ,templet:"#menuType"}
            , {fixed: 'right', title: '操作',width: '15%', toolbar: '#bar'}
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
            var menuName = $('#menuName-search').val();
            var parentMenucode = $('#parentMenucode-search').val();
            //执行重载
            table.reload('table_obj', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                     menuName:menuName
                    ,parentMenucode:parentMenucode
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
                    url:"doDelMenu.html",
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
                content: objId+'/toEidtMenu.html'
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
            area: ['600px', '500px'],
            title: ['新增', 'background:#f2f2f2;color:#333;'],
            shade: .5,
            content: 'toAddMenu.html'
        })

    });
    //监听提交
    form.on('submit(formsub)', function (data) {
        //restful 惹的祸  期待有人能解决。 暂时先用这个笨方法解决吧  ┭┮﹏┭┮
        var url = data.field.id==null?"doAddMenu.html":"../doAddMenu.html";
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

});
