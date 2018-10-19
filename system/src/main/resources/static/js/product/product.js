/**
 * make by YANSHUCHUN 2018年9月20日10:16:16
 */
layui.use(['table', 'form','upload'], function () {
    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
    var table = layui.table,form = layui.form;
    // 页面的渲染↓
    table.render({
        elem: '#table_obj'
        , url: 'productList.html' //数据接口
        , page: true //开启分页
        , method: 'post'
        , cols: [[ //表头
            {checkbox: true, field: 'id', title: 'ID', width: "5%", fixed: 'left'}
            , {field: 'name', title: '设备名称', width: "45%"}
            , {field: 'code', title: '设备编码', width: "35%"}
            , {fixed: 'right', title: '操作',width: "10%", toolbar: '#bar'}
        ]]
    });
    // 页面的渲染↑

    //执行重新加载的方法--页面的搜索方法。 search ↓
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    var active = {
        reload: function(){
            var code = $('#code-search').val();
            var name = $('#name-search').val();
            //执行重载
            table.reload('table_obj', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                      name:name
                    ,code:code
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
                title: ['商品详情', 'background:#f2f2f2;color:#333;'],
                shade: .5,
                area: ['800px', '700px'],
                content: objId+'/toProductDetail.html'
            })
        } else if (layEvent === 'del') {
            layer.confirm('真的删除行么', function (index) {
                obj.del(index); //删除对应行（tr）的DOM结构
                layer.close(index);
                $.ajax({
                    url:"doDelProduct.html",
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
        } else if (layEvent === 'er') {
            layer.open({
                type: 2,
                anim: 1,
                shadeClose: false,
                maxmin: true,
                title: ['条码展示', 'background:#f2f2f2;color:#333;'],
                shade: .5,
                offset: 'auto',
                area: ['350px', '350px'],
                content: objId + '/er/toProductCode.html'
            });
        }else if (layEvent === 'tiao') {
            layer.open({
                type: 2,
                anim: 1,
                shadeClose: false,
                maxmin: true,
                offset: 'auto',
                title: ['条码展示', 'background:#f2f2f2;color:#333;'],
                shade: .5,
                area: ['350px', '120px'],
                content: objId+'/tiao/toProductCode.html'
            })
        }
    });
    //监听工具条↑


    // 新增↓
    // 新增点击事件的监听 ↓
    $(".addNews_btn").on('click', function() {
        var index = layer.open({
            type: 2,
            anim: 2,
            shadeClose: false,
            area: ['100%', '100%'],
            title: ['新增', 'background:#f2f2f2;color:#333;'],
            shade: .5,
            content: 'toAddProduct.html',
            success:function () {
                setTimeout(function(){
                    layer.tips('点击此处返回产品列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        });
        layer.full(index);
    });
    //监听提交
    form.on('submit(formsub)', function (data) {
        //restful 惹的祸  期待有人能解决。 暂时先用这个笨方法解决吧  ┭┮﹏┭┮
        var url = data.field.id==null?"doAddProduct.html":"../doAddProduct.html";
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
