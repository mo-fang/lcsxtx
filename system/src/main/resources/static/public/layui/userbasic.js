var message;
layui.config({
    base: '../../build/js/'
}).use(['app', 'message'], function() {
    var app = layui.app,
        $ = layui.jquery,
        layer = layui.layer;
    //将message设置为全局以便子页面调用
    message = layui.message;
    //     //主入口
    app.set({
        type: 'iframe'
    }).init();
});
$(function(){
    $("#basic_info").on('click', function() {
        var userId = $(this).attr("value");
        layui.use('layer', function() {
            var  index = parent.layer.open({
                type: 2,
                anim: 1,
                shadeClose:false,
                title: ['基本资料', 'background:#f2f2f2;color:#333;'],
                shade: .5,
                area: ['440px', '450px'],
                content: 'userBasic.html',
                data:[{userId:userId}]
                // end:function () {   组件销毁之后的方法
                //     location.reload();
                // }
            })
        })
    });
});