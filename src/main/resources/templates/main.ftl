<!DOCTYPE html>
<html lang="en">
    <#include "common/head.ftl">
<body>
<div class="container">
    <#-- 导航栏 -->
    <#include "common/nav.ftl">

    <#-- 主要内容区 -->
        <div class="col-md-8 column" id="search">
            <form role="form">
                <div class="input-group">
                    <input type="text" class="form-control input-lg"><span class="input-group-addon btn btn-primary">搜索</span>
                </div>
            </form>
        </div>

    <!-- Copyright -->
    <#include "common/footer.ftl">
</div>
</body>
</html>