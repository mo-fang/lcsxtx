/**
 * make by YANSHUCHUN 2018年9月20日10:16:16
 */
layui.use(['table', 'form'], function () {
    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
    var table = layui.table,form = layui.form;
    // 页面的渲染↓
    table.render({
        elem: '#table_obj'
        , url: 'roleList.html' //数据接口
        , page: true //开启分页
        , method: 'post'
        // ,height:'full'
        , limits: [10,20]
        , cols: [[ //表头
             {checkbox: true, field: 'id', title: 'ID', width: "5%", fixed: 'left'}
            , {field: 'code', title: '编码', width:  "30%"}
            , {field: 'name', title: '名称', width:  "30%"}
            , {field: 'inserttime', title: '新增时间', width:  "20%",templet:"#formartdate"}
            // , {field: 'menuType', title: '类型', width:  "10%" ,templet:"#menuType"}
            , {fixed: 'right', title: '操作',width: '15%', toolbar: '#bar'}
        ]]
    });
    // 页面的渲染↑

    //执行重新加载的方法--页面的搜索方法。 search ↓
    $('.search_btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    var active = {
        reload: function(){
            var name = $('#name-search').val();
            var code = $('#code-search').val();
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
                title: ['产品详情', 'background:#f2f2f2;color:#333;'],
                shade: .5,
                area: ['600px', '600px'],
                content: '#.html'
            })
        } else if (layEvent === 'del') {
            layer.confirm('删除操作将删除该角色的全部信息，包括该角色下权限以及具有改角色的人员的权限信息，都将被清空，请慎重操作', function (index) {
                obj.del(index); //删除对应行（tr）的DOM结构
                layer.close(index);
                $.ajax({
                    url:"doDelRole.html",
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
                area: ['600px', '500px'],
                content: objId+'/toEidtRole.html'
            })
        }else if(layEvent === 'auth'){
            layer.open({
                type: 2,
                anim: 1,
                shadeClose: false,
                maxmin: true,
                title: ['授权', 'background:#f2f2f2;color:#333;'],
                shade: .5,
                area: ['600px', '500px'],
                content: objId+'/toAuthRole.html'
            })

        }
    });
    //监听工具条↑


    // 新增↓
    // 新增点击事件的监听 ↓
    $(".addNews_btn").on('click', function() {
        layer.open({
            type: 2,
            anim: 2,
            shadeClose: false,
            area: ['600px', '300px'],
            title: ['新增', 'background:#f2f2f2;color:#333;'],
            shade: .5,
            content: 'toAddRole.html',
            success:function () {
                setTimeout(function(){
                    layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })

    });
    //监听提交
    form.on('submit(formsub)', function (data) {
        //restful 惹的祸  期待有人能解决。 暂时先用这个笨方法解决吧  ┭┮﹏┭┮ 记得加上id
        var url = data.field.id==null?"doAddRole.html":"../doAddRole.html";
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
    var id = $("#id").val();
    var xtree = new layuiXtree({
        elem: 'xtree'                  //必填三兄弟之老大
        , form: form                    //必填三兄弟之这才是真老大
        , data: "../"+id+"/doGetAuth.html" //必填三兄弟之这也算是老大
        , isopen: false  //加载完毕后的展开状态，默认值：true
        , ckall: true    //启用全选功能，默认值：false
        , ckallback: function () { } //全选框状态改变后执行的回调函数
        , icon: {        //三种图标样式，更改几个都可以，用的是layui的图标
            open: "&#xe7a0;"       //节点打开的图标
            , close: "&#xe622;"    //节点关闭的图标
            , end: "&#xe621;"      //末尾节点的图标
        }
        , color: {       //三种图标颜色，独立配色，更改几个都可以
            open: "#EE9A00"        //节点图标打开的颜色
            , close: "#EEC591"     //节点图标关闭的颜色
            , end: "#828282"       //末级节点图标的颜色
        }
        // , click: function (data) {  //节点选中状态改变事件监听，全选框有自己的监听事件
        // console.info(data);
        // console.log(data.elem); //得到checkbox原始DOM对象
        // console.log(data.elem.checked); //开关是否开启，true或者false
        // console.log(data.value); //开关value值，也可以通过data.elem.value得到
        // console.log(data.othis); //得到美化后的DOM对象
        // }
    });
//获取全部[选中的][末级节点]原checkbox DOM对象，返回Array
    $("#authsub").on('click',function () {
        var oCks = xtree.GetChecked();         //这是方法
        var mbid = new Array();
        for(var i in oCks){
            mbid.push(oCks[i].value);
        }
        $.ajax({
            url:"../doAuthRole.html",
            traditional: true,
            type:"post",
            data:{"id":id,"mbid":mbid},
            dataType:"json",
            success: function (data) {
                console.info(data);
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
            },
            error:function (data) {
                console.info(data);

            }
        })
    })

});
