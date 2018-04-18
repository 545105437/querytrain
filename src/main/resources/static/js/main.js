/** 知心天气网页插件代码 */
(function(T,h,i,n,k,P,a,g,e){g=function(){P=h.createElement(i);a=h.getElementsByTagName(i)[0];P.src=k;P.charset="utf-8";P.async=1;a.parentNode.insertBefore(P,a)};T["ThinkPageWeatherWidgetObject"]=n;T[n]||(T[n]=function(){(T[n].q=T[n].q||[]).push(arguments)});T[n].l=+new Date();if(T.attachEvent){T.attachEvent("onload",g)}else{T.addEventListener("load",g,false)}}(window,document,"script","tpwidget","//widget.seniverse.com/widget/chameleon.js"))

tpwidget("init", {
    "flavor": "slim",
    "location": "WX4FBXXFKE4F",
    "geolocation": "enabled",
    "language": "auto",
    "unit": "c",
    "theme": "chameleon",
    "container": "tp-weather-widget",
    "bubble": "enabled",
    "alarmType": "badge",
    "color": "#C6C6C6",
    "uid": "UBBB8DBE07",
    "hash": "6dda6603c46080d47f2c51d061c86bba"
});
tpwidget("show");

/** 按下回车键，点击按钮 */
$(function(){
    $("body").keydown(function () {
        var companyName = $("#companyName").val();
        if (event.keyCode == "13") {//keyCode=13是回车键
            if(companyName == ""){
                alert("请填写公司名称或简称");
            }else{
                $("#search").click();
            }
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