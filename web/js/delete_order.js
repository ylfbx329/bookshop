$(function () {
    $(".delete").click(function () {
        let tr = $(this).parent().parent()[0]
        $.get(
            "/bookshop_war_exploded/DeleteOrderServlet", {
                order_id: this.value,
            },
            function (data) {
                if (data === "1") {
                    tr.remove()
                    if ($("tr").length <= 1) {
                        location.reload()
                    }
                } else {
                    alert("服务器错误")
                }
            })
    })
})