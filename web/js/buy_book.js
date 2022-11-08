$(function () {
    let storage = window.sessionStorage;

    $(".sub").click(function () {
        let span = this.nextElementSibling;
        let book_id_td = span.parentElement.parentElement.firstElementChild;
        if (parseInt(span.innerHTML) - 1 >= 0) {
            span.innerHTML = parseInt(span.innerHTML) - 1
            let book_id = book_id_td.innerHTML.replace(/^\s+|\s+$/g, '');
            storage.setItem(book_id, span.innerHTML)
        }
    })

    $(".add").click(function () {
        let span = this.previousElementSibling;
        span.innerHTML = parseInt(span.innerHTML) + 1;
        let book_id_td = span.parentElement.parentElement.firstElementChild;
        let book_id = book_id_td.innerHTML.replace(/^\s+|\s+$/g, '');
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

    $.ajax(
        "/bookshop_war_exploded/BuyBookServlet",
        {
            type: "POST",
            data: {
                user_id: $("#user_id")[0].innerHTML,
                order: JSON.stringify(orders),
            },
            success: function (data) {
                console.log("yes")
                console.log(data)
            },
            error: function (data) {
                console.log("no")
                console.log(data)
            },
        });

    $.get(
        "/bookshop_war_exploded/BuyBookServlet", {
            user_id: $("#user_id")[0].innerHTML,
            order: JSON.stringify(orders),
        })
    storage.clear()
}
