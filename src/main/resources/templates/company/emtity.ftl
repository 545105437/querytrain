<!DOCTYPE html>
<html lang="en">
    <#include "../common/head.ftl">
<body>
<div class="container">
    <#-- 导航栏 -->
    <#include "../common/nav.ftl">

    <#-- 主要内容区 -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column" id="list">
                        <div class="alert alert-dismissable alert-info">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                            <h4>
                                注意!
                            </h4> <strong>${msg!""}</strong> <a href="${url}" class="alert-link">3秒后自动跳转</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <!-- Copyright -->
    <#include "../common/footer.ftl">
</div>
</body>
    <script>
        setTimeout('location.href="${url}"',3000)
    </script>
</html>