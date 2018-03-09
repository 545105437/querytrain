<!DOCTYPE html>
<html lang="en">
    <#include "../common/head.ftl">
<body>
<div class="container">
    <#-- 导航栏 -->
    <#include "../common/nav.ftl">

    <#-- 主要内容区 -->
        <div class="col-md-12 column" id="submitData">
            <form role="form" method="post" action="">
                <div class="form-group">
                    <label for="exampleInputEmail1">公司名称：</label><input type="text" class="form-control" id="companyName" />
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">公司简称：</label><input type="text" class="form-control" id="companyShortName" />
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">公司性质：</label>
                    <select name="companyType" class="form-control">
                    <#--<#list categoryList as category>-->
                        <#--<option value="${category.categoryType}" <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>selected</#if> >-->
                        <#--${category.categoryName}-->
                        <#--</option>-->
                    <#--</#list>-->
                        <option value="0">培训机构</option>
                        <option value="1">招聘转培训</option>
                        <option value="2">培训机构和招聘转培训</option>
                        <option value="3">校企合作</option>
                        <option value="4">校企合作和招聘转培训</option>
                        <option value="5">疑似传销</option>
                        <option value="6">皮包公司外包培训</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">信息来源：</label><input type="text" class="form-control" id="infoSource" />
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">公司地址：</label><input type="text" class="form-control" id="companyAddress" />
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">经营范围：</label><input type="text" class="form-control" id="businessScope" />
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">详细描述：</label><input type="text" class="form-control" id="detailsDescription" />
                </div>
                <input hidden type="text" name="companyId" value="${(productInfo.productId)!''}">
                <button type="submit" class="btn btn-default">提交</button>
            </form>
        </div>

    <!-- Copyright -->
    <#include "../common/footer.ftl">
</div>
</body>
</html>