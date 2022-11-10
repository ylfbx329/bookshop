$(function () {
    let storage = window.sessionStorage;

    $(".sub").click(function () {
        let span = $(this).next()[0];
        let order_mount = parseInt(span.innerHTML) - 1
        if (order_mount >= 0) {
            let book_id = $(this).parent().parent().children(".book_id")[0].innerHTML.trim()
            if (order_mount === 0) {
                storage.removeItem(book_id)
            } else {
                storage.setItem(book_id, order_mount.toString())
            }
            span.innerHTML = order_mount
        }
    })

    $(".add").click(function () {
        let span = $(this).prev()[0];
        span.innerHTML = parseInt(span.innerHTML) + 1;
        let book_id = $(this).parent().parent().children(".book_id")[0].innerHTML.trim()
        storage.setItem(book_id, span.innerHTML)
    })
})

function buy_book() {
    let storage = window.sessionStorage;
    let orders = [];

    for (let i = 0, len = storage.length; i < len; i++) {
        let order = {};
        let book_id = storage.key(i);
        let order_mount = storage.getItem(book_id)
        order.book_id = book_id
        order.order_mount = order_mount
        orders.push(order)
    }

    if (orders.length === 0) {
        alert("请至少选购一本图书")
        return;
    }

    // $.ajax(
    //     "/bookshop_war_exploded/BuyBookServlet",
    //     {
    //         type: "POST",
    //         data: {
    //             user_id: $("#user_id")[0].innerHTML,
    //             order: JSON.stringify(orders),
    //         },
    //         success: function (data) {
    //             console.log("yes")
    //             console.log(data)
    //         },
    //         error: function (data) {
    //             console.log("no")
    //             console.log(data)
    //         },
    //     });

    $.get(
        "/bookshop_war_exploded/BuyBookServlet", {
            user_id: $("#user_id")[0].innerHTML,
            order: JSON.stringify(orders),
        })
    storage.clear()
}
