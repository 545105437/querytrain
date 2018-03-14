<!DOCTYPE html>
<html lang="en">
    <#include "../common/head.ftl">
    <script src="/querytrain/js/bootstrapValidator.min.js"></script>
    <link href="/querytrain/css/bootstrapValidator.min.css" rel="stylesheet" />
<body>
<div class="container">
    <#-- 导航栏 -->
    <#include "../common/nav.ftl">

    <#-- 主要内容区 -->
        <div class="col-md-12 column" id="submitData">
            <form role="form" method="post" action="/querytrain/company/save">
                <div class="form-group">
                    <label for="companyName">公司名称：</label><input type="text" class="form-control" name="companyName" />
                </div>
                <div class="form-group">
                    <label for="companyShortName">公司简称：</label><input type="text" class="form-control" name="companyShortName" />
                </div>
                <div class="form-group">
                    <label for="companyType">公司性质：</label>
                    <select name="companyType" class="form-control">
                    <#--<#list categoryList as category>-->
                        <#--<option value="${category.categoryType}" <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>selected</#if> >-->
                        <#--${category.categoryName}-->
                        <#--</option>-->
                    <#--</#list>-->
                        <option value="0">培训机构</option>
                        <option value="1">招聘转培训</option>
                        <option value="2">校企合作</option>
                        <option value="3">疑似传销</option>
                        <option value="4">皮包公司外包培训</option>
                        <option value="5">黑名单</option>
                    </select>
                </div>
                <#--<div class="form-group">-->
                    <#--<label for="infoSource">信息来源：</label><input type="text" class="form-control" id="infoSource" />-->
                <#--</div>-->
                <div class="form-group">
                    <label for="companyAddress">公司地址：</label><input type="text" class="form-control" name="companyAddress" />
                </div>
                <div class="form-group">
                    <label for="businessScope">经营范围：</label><input type="text" class="form-control" name="businessScope" />
                </div>
                <div class="form-group">
                    <label for="detailsDescription">详细描述：</label><input type="text" class="form-control" name="detailsDescription" />
                </div>
                <#--<input hidden type="text" name="companyId" value="${(company.companyId)!''}">-->
                <button type="submit" class="btn btn-primary">提交</button>
            </form>
        </div>

    <!-- Copyright -->
    <#include "../common/footer.ftl">
</div>
</body>
    <script>
        $(function () {
            $('form').bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    companyName: {
                        message: '公司名称验证失败',
                        validators: {
                            notEmpty: {
                                message: '公司名称不能为空'
                            }
                        }
                    },
                    companyAddress: {
                        validators: {
                            notEmpty: {
                                message: '公司地址不能为空'
                            }
                        }
                    }
                }
            });
        });
    </script>
</html>