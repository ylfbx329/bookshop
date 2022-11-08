$(function () {
    // 先设置奇偶
    $("tr:odd").css("background-color", "#D9E1F2");
    $("tr:even").css("background-color", "#FFFFFF");
    // 再选择首行
    $("tr:first-child").css({"background-color": "#4472C4", "color": "#FFFFEC"});
    // 记录原来颜色
    let color;
    // 选择第 0 行之后的所有行，不包含第 0 行
    $("tr:gt(0)").hover(
        function () {
            color = $(this).css("background-color");
            $(this).css("background-color", "rgba(255,105,0,0.7)");
        }, function () {
            $(this).css("background-color", color);
        }
    )
});