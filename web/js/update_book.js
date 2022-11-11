$(function () {
    let storage = window.sessionStorage;

    $(".update").click(function () {
        let book = {}
        // tr 为 jquery 选择的元素，不是原生的变量，便于选择
        let tr = $(this).parent().parent()
        book.isbn = $(tr).children('.isbn')[0].innerHTML.trim()
        book.book_name = $(tr).children('.book_name')[0].innerHTML.trim()
        book.author = $(tr).children('.author')[0].innerHTML.trim()
        book.press = $(tr).children('.press')[0].innerHTML.trim()
        book.pub_date = $(tr).children('.pub_date')[0].innerHTML.trim()
        book.store_mount = $(tr).children('.store_mount')[0].innerHTML.trim()
        book.price = $(tr).children('.price')[0].innerHTML.trim()
        book.img = $(tr).children('.img')[0].innerHTML.trim()
        storage.removeItem("book")
        let str_book = JSON.stringify(book)
        let url_book = window.encodeURIComponent(str_book)
        location.href = "update_book.jsp?book=" + url_book
    })

    // 返回索引，-1 表示未找到
    if (location.pathname.indexOf("update_book.jsp") >= 0) {
        let book = JSON.parse(storage.getItem("book"));
        if (book == null) {
            let str_book = decodeURIComponent(location.search.slice(1).split("=")[1]);
            book = JSON.parse(str_book)
            storage.setItem("book", str_book)
        }
        $("#isbn").val(book.isbn)
        $("#book_name").val(book.book_name)
        $("#author").val(book.author)
        $("#press").val(book.press)
        $("#pub_date").val(book.pub_date)
        $("#store_mount").val(book.store_mount)
        $("#price").val(book.price)
    }
})