$(function () {
    $(".pay").click(function () {
        if ($("tr").length > 1) {
            $.get(
                "/bookshop_war_exploded/PayOrderServlet", {
                    user_id: $("#user_id")[0].innerHTML,
                })
        } else {
            alert("目前尚无订单，请前去选购")
            location.href = "user_index.jsp"
        }
    })
})