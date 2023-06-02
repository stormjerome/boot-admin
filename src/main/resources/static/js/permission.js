/**
 * 权限校验
 */
function checkPermissoin() {
    // 所有权限
    var permissions = jQuery.parseJSON(localStorage.getItem("permissions"));
    // 遍历带有permission属性的元素
    $("[permission]").each(function () {
       var per = $(this).attr("permission");
       // 判断是否存在该权限
       if ($.inArray(per, permissions) < 0) {
           $(this).hide();
       }
    });
}