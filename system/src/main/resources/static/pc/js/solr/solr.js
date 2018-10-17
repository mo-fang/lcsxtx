/**
 * make by YANSHUCHUN 2018年9月20日10:16:16
 */
layui.use(['table', 'form'], function () {
    var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
    var table = layui.table, form = layui.form;

    $("#solrAdd").on("click", function () {
        layer.confirm('确定进行索引的全部增加操作吗', function (index) {
            layer.close(index);
            $.ajax({
                url: "solrProductAdd.html",
                type: "post",
                dataType: "json",
                success: function (data) {
                    console.info(data);
                    if (data.retCode == 200) {
                        layer.msg('操作成功', {icon: 1, time: 1e3});
                    } else {
                        layer.msg(data.message, {icon: 1, time: 1e3});
                    }
                },
            })
        });

    })
    $("#solrDel").on("click", function () {
        layer.confirm('确定进行索引的全部删除行么', function (index) {
            layer.close(index);
            $.ajax({
                url: "solrProductDel.html",
                type: "post",
                dataType: "json",
                success: function (data) {
                    console.info(data);
                    if (data.retCode == 200) {
                        layer.msg('操作成功', {icon: 1, time: 1e3});
                    } else {
                        layer.msg(data.message, {icon: 1, time: 1e3});
                    }
                },
            })
        });

    });

    $("#solrQuery").on("click", function () {
        var queryparam = $("#queryparam").val();
        $.ajax({
            url: "queryProduct.html",
            type: "post",
            data: {"queryparam": queryparam},
            dataType: "json",
            success: function (data) {
                console.info(data);
                if(data.length==0)
                    $("#results").html("");
                else
                    $("#results").html(data[0].name)
            },
        })
    });
})

