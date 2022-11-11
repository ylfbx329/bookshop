$(function () {
    $(".delete").click(function () {
        let tr = $(this).parent().parent()[0]
        let book_id = $(tr).children(".book_id")[0].innerHTML.trim()
        $.get(
            "/bookshop_war_exploded/DeleteBookServlet", {
                book_id: book_id,
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