document.write("<script type=\"text/javascript\" src=\"../../public/jquery-3.3.1/jquery-3.3.1.js\" ></script>");
document.write("<script type=\"text/javascript\" src=\"../../public/echarts/echarts.min.js\" ></script>");
document.write("<script type=\"text/javascript\" src=\"../../public/layui/layui.js\" ></script>");
document.write("<link  rel=\"stylesheet\"  type=\"text/css\" href=\"../../public/layui/css/layui.css\" media=\"all\"></link >");
document.write("<link  rel=\"stylesheet\"  type=\"text/css\" href=\"../../public/layui/css/public.css\" media=\"all\"></link >");

// onkeydown="if(event.keyCode==13)return false";

/**
 * js 模拟post 提交表单
 * @param URL
 * @param PARAMS
 * @returns {HTMLFormElement}
 */
function post(URL, PARAMS) {
    var temp = document.createElement("form");
    temp.action = URL;
    temp.method = "post";
    temp.style.display = "none";
    for (var x in PARAMS) {
        var opt = document.createElement("textarea");
        opt.name = x;
        opt.value = PARAMS[x];
        temp.appendChild(opt);
    }
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}

function get(URL, PARAMS) {
    var temp = document.createElement("form");
    temp.action = URL;
    temp.method = "get";
    temp.style.display = "none";
    for (var x in PARAMS) {
        var opt = document.createElement("textarea");
        opt.name = x;
        opt.value = PARAMS[x];
        temp.appendChild(opt);
    }
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}
function encodeUnicode(str) {
    var res = [];
    for ( var i=0; i<str.length; i++ ) {
        res[i] = ( "00" + str.charCodeAt(i).toString(16) ).slice(-4);
    }
    var a = "\\u" + res.join("\\u");
    alert(a)
    return a;
}
// 解码
function decodeUnicode(str) {
    str = str.replace(/u/g, "%u");
    // str = str.replace(/\\/g, "%");
    return  unescape(str);
}

