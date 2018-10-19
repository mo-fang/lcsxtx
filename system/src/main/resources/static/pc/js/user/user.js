/**
 * make by YANSHUCHUN 2018年9月20日10:16:16
 */
layui.use(['table', 'form'], function () {
    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
    var table = layui.table,form = layui.form;
    // 页面的渲染↓
    table.render({
        elem: '#table_obj'
        , url: 'userList.html' //数据接口
        , page: true //开启分页
        , method: 'post'
        // ,height:'full'
        , limits: [10,20]
        , cols: [[ //表头
            {checkbox: true, field: 'id', title: 'ID',  fixed: 'left'}
            , {field: 'username', title: '登录账号', width: '100'}
            , {field: 'name', title: '姓名',align:'center',width: '100'}
            , {field: 'phonum', title: '电话',align:'center',width: '100'}
            , {field: 'sexcode', title: '性别',templet:"#sexcode",width: '100'}
            , {field: 'role', title: '岗位',templet:"#role",width: '100'}
            // , {field: 'departmentid', title: '所属部门',width: '100',templet:"#departmentid"}
            // , {field: 'postid', title: '岗位职能',width: '100',templet:"#postid"}
            , {field: 'sfzh', title: '身份证号码',width: '200'}
            // , {field: 'hiredate', title: '入职时间',width: '200'}
            , {field: 'birthday', title: '出生日期',width: '200'}
            , {field: 'qualificationid', title: '学历',width: '100',templet:"#qualificationid"}
            // , {field: 'havewife', title: '婚否',width: '100',templet:"#havewife" }
            , {field: 'addr', title: '家庭住址',width: '300'}
            // , {field: 'sosname', title: '紧急联系人',width: '100'}
            // , {field: 'sosphonnum', title: '联系人电话',width: '100'}
            // , {field: 'updatetime', title: '创建时间',width: '200'}
            ,{fixed: 'right',title:"操作", toolbar: '#bar',width: '200'}
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
            var username = $('#username-search').val();
            var name = $('#name-search').val();
            var departmentid = $('#department-search').val();
            //执行重载
            table.reload('table_obj', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    name:name
                    ,username:username
                    ,departmentid:departmentid
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
            var index = layer.open({
                type: 2,
                anim: 1,
                shadeClose: false,
                maxmin: true,
                title: ['修改', 'background:#f2f2f2;color:#333;'],
                shade: .5,
                area: ['100%', '100%'],
                content: objId+'/toEidtUser.html',
                success:function () {
                    setTimeout(function(){
                        layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    },500)
                }
            });
            layer.full(index);
        }else if (layEvent === 'role') {
            layer.open({
                type: 2,
                anim: 1,
                shadeClose: false,
                maxmin: true,
                title: ['角色', 'background:#f2f2f2;color:#333;'],
                shade: .5,
                area: ['600px', '500px'],
                content: objId+'/toChangeRole.html'
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
            area: ['600px', '600px'],
            title: ['新增', 'background:#f2f2f2;color:#333;'],
            shade: .5,
            content: 'toAddUser.html',
            success:function () {
                setTimeout(function(){
                    layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        });
        // layer.full(index);
    });
    //监听提交
    form.on('submit(formsub)', function (data) {
        //restful 惹的祸  期待有人能解决。 暂时先用这个笨方法解决吧  ┭┮﹏┭┮
        var url = data.field.id==null?"saveOrUpdateUser.html":"../saveOrUpdateUser.html";
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
    //监听提交    修改密码
    form.on('submit(changePwd)', function (data) {
        //restful 惹的祸  期待有人能解决。 暂时先用这个笨方法解决吧  ┭┮﹏┭┮
        var url = "doChangePwd.html";
        var pwdold = $("#pwdold").val();
        var pwdn = $("#pwdn").val();
        var pwdn1 = $("#pwdn1").val();

        if(pwdn==pwdold){
            layer.msg('新密码和旧密码不能一致', {icon: 1, time: 1e3});
            return false;
        }
        if(pwdn!=pwdn1){
            layer.msg('两次输入的新密码不一致', {icon: 1, time: 1e3});
            return false;
        }
        // layer.msg(JSON.stringify(data.field));
        $.ajax({
            type: 'post',
            url: url,
            data: $(".layui-form-changepwd").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.retCode==200){
                    layer.msg('操作成功', {icon: 1, time: 1e3});
                } else {
                    layer.msg(data.message, {icon: 1, time: 1e3});
                }
            }
        });
        return false;
    });
    // 修改密码
    //监听提交    修改密码
    form.on('submit(formsubrole)', function (data) {
        $.ajax({
            type: 'post',
            url: "../doChangRole.html",
            data: $(".layui-form").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.retCode==200){
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
    // 修改密码


    // 业务相关 ↓
    form.on('radio(type)',function (data) {
        var typeVal = data.value;
        if (typeVal==0){
            $(".layui-form-label-parentid").hide();
            $(".layui-select-parentid").removeAttr('lay-verify');
            $(".layui-select-parentid").removeAttr('name');
        }else {
            $(".layui-form-label-parentid").show();
            $(".layui-select-parentid").attr("name","parentid");
        }
    })

});


