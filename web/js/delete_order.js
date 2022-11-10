$(function () {
    $(".delete").click(function () {
        let tr = this.parentElement.parentElement
        $.get(
            "/bookshop_war_exploded/DeleteOrderServlet", {
                id: this.value,
                pay_flag: 0
            },
            function (data) {
                console.log(data)
                if (data === "1") {
                    tr.remove()
                } else {
                    alert("服务器错误")
                }
            })
    })
    $(".pay").click(function () {
        if ($("tr").length > 1) {
            $.get(
                "/bookshop_war_exploded/DeleteOrderServlet", {
                    id: $("#user_id")[0].innerHTML,
                    pay_flag: 1
                },
                function (data) {
                    console.log(data)
                })
        } else {
            alert("目前尚无订单，请前去选购")
            location.href = "user_index.jsp"
        }
    })
})