// 设置全局ajax处理逻辑
$.ajaxSetup({
    // 设置ajax请求结束后的执行动作
    complete: function (xhr) {
        if ("REDIRECT" === xhr.getResponseHeader("REDIRECT")) {
            // 若HEADER中含有REDIRECT说明后端想重定向，
            let win = window;
            while (win !== win.top) {
                win = win.top;
            }
            // 将后端重定向的地址取出来,使用win.location.href去实现重定向的要求
            win.location.href = xhr.getResponseHeader("CONTENTPATH");
        }
        if ("ERROR" === xhr.getResponseHeader("ERROR")) {
            alert(xhr.getResponseHeader("ERRORMESSAGE"))
        }
    }
});