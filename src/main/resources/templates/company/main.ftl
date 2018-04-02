<!DOCTYPE html>
<html lang="en">
    <#include "../common/head.ftl">
<body>
<div class="container">
    <#-- 导航栏 -->
    <#include "../common/nav.ftl">

    <#-- 主要内容区 -->
        <div class="col-md-8 column" id="search">
            <form id="myForm" role="form" method="get" action="/querytrain/company/search">
                <div class="input-group">
                    <input type="text" class="form-control input-lg" name="companyName" id="companyName" placeholder="检索如：北大青鸟" ><span class="input-group-addon btn btn-primary" onclick="submit()">搜索</span>
                </div>
            </form>
        </div>

    <!-- Copyright -->
    <#include "../common/footer.ftl">
</div>
</body>
    <script src="/querytrain/js/main.js"></script>
</html>