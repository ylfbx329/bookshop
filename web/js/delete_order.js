$(function () {
    $(".delete").click(function () {
        let tr = this.parentElement.parentElement
        $.get(
            "/bookshop_war_exploded/DeleteOrderServlet", {
                order_id: this.value,
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
})