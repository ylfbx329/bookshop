$(function () {
    let storage = window.sessionStorage;

    $(".update").click(function () {
        let book = {}
        // tr 为 jquery 选择的元素，不是原生的变量，便于选择
        let tr = $(this).parent().parent()
        // console.log($(td).children('.isbn'))
        book.isbn = $(tr).children('.isbn')[0].innerHTML.trim()
        // console.log($(tr).children('.book_name'))
        book.book_name = $(tr).children('.book_name')[0].innerHTML.trim()
        book.author = $(tr).children('.author')[0].innerHTML.trim()
        book.press = $(tr).children('.press')[0].innerHTML.trim()
        book.pub_date = $(tr).children('.pub_date')[0].innerHTML.trim()
        book.store_mount = $(tr).children('.store_mount')[0].innerHTML.trim()
        book.price = $(tr).children('.price')[0].innerHTML.trim()
        book.img = $(tr).children('.img')[0].innerHTML.trim()
        console.log(book)
        console.log(JSON.stringify(book))
        let str_book = JSON.stringify(book)
        console.log(window.encodeURIComponent(str_book))
        console.log("update_book.jsp?book=" + window.encodeURIComponent(JSON.stringify(book)))
        console.log("update_book.jsp?book=" + window.encodeURIComponent(str_book))
        location.href = "update_book.jsp?book=" + window.encodeURIComponent(JSON.stringify(book))
        // let span = this.nextElementSibling;
        // let book_id_td = span.parentElement.parentElement.firstElementChild;
        // if (parseInt(span.innerHTML) - 1 >= 0) {
        //     span.innerHTML = parseInt(span.innerHTML) - 1
        //     let book_id = book_id_td.innerHTML.replace(/^\s+|\s+$/g, '');
        //     storage.setItem(book_id, span.innerHTML)
        // }
    })

    // 返回索引，-1 表示未找到
    // console.log(location.pathname.indexOf("update_book.jsp"))
    if (location.pathname.indexOf("update_book.jsp") >= 0) {
        let book = JSON.parse(decodeURIComponent(location.search.slice(1).split("=")[1]))
        $("#isbn").val(book.isbn)
        $("#book_name").val(book.book_name)
        $("#author").val(book.author)
        $("#press").val(book.press)
        $("#pub_date").val(book.pub_date)
        $("#store_mount").val(book.store_mount)
        $("#price").val(book.price)
        $("#img").val(book.img)
        console.log(book.isbn)
        console.log(book)
    }

    // // console.log(location.pathname)
    // console.log(window.location.pathname)
    // console.log(window.location.href)
    // console.log(window.location.port)
    // console.log(window.location.protocol)
    // console.log(window.location.hash)
    // console.log(window.location.host)
    // console.log(window.location.hostname)
    // console.log(window.location.search)
    // console.log(window.location)
    // console.log(book)
})