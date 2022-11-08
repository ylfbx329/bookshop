function check() {
    let formData = $("input[type=text],input[type=password]");
    let submit = true;
    formData.each(function (idx, ele) {
        if (ele.value === '') {
            ele.focus();
            alert("不可为空");
            submit = false;
            return false;
        }
        if (submit && ele.value.length > 20) {
            ele.focus();
            alert("长度超过限制");
            submit = false;
            return false;
        }
    });
    return submit
}

function form_submit(isuser) {
    if (check()) {
        $("input[name=isuser]").val(isuser);
        $("#form").submit();
    }
}