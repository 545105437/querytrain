
/** 按下回车键，点击按钮 */
$(function(){
    $("body").keydown(function () {
        if (event.keyCode == "13") {//keyCode=13是回车键
            $("#search").click();
        }
    });
});

/** 首页检索 */
function submit(){
    var companyName = $("#companyName").val();
    var actionUrl = $("#myForm").attr("action");
    if(companyName == ""){
        alert("请填写公司名称或简称");
    }else{
        $("#myForm").attr("action",actionUrl+"?companyName="+companyName);
        $("#myForm").submit();
    }
}