<html>
<#include "../common/manageheader.ftl">
    <body>
        <div id="wrapper" class="toggled">
            <#--边栏 sidebar-->
            <#include "../common/managenav.ftl">

            <#--主要内容区域-->
            <div id="page-content-wrapper">
                <div class="container-fluid">
                    <div class="row clearfix">
                    <#-- 订单详情表数据 -->
                        <div class="col-md-12 column" id="submitData">
                            <form role="form" method="post" action="">
                                <table class="table">
                                    <tbody>
                                    <tr>
                                        <td width="15%" class="warning"><b>公司名称</b></td>
                                        <td width="35%" class="info"><input type="text" class="form-control" value="${(companyDTO.companyName)!''}"></td>
                                        <td width="15%" class="warning"><b>公司简称</b></td>
                                        <td width="35%" class="info"><input type="text" class="form-control" value="${(companyDTO.companyShortName)!''}"></td>
                                    </tr>
                                    <tr >
                                        <td width="15%" class="warning"><b>公司地址</b></td>
                                        <td width="35%" class="info"><input type="text" class="form-control" value="${(companyDTO.companyAddress)!''}"></td>
                                        <td width="15%" class="warning"><b>访问次数</b></td>
                                        <td width="35%" class="info"><input type="text" class="form-control" value="${(companyDTO.number)!''}"></td>
                                    </tr>
                                    <tr>
                                        <td width="15%" class="warning"><b>公司性质</b></td>
                                        <td width="35%" class="info"><#--${companyDTO.getCompanyTypeEnum().message}-->
                                            <select name="companyType" class="form-control">
                                                <#list companyTypeEnum as companyType>
                                                    <option value="${companyType.code}" <#if companyDTO.getCompanyTypeEnum().message == companyType.message>selected</#if>>${companyType.message}</option>
                                                </#list>
                                            </select>
                                        </td>
                                        <td width="15%" class="warning"><b>信息来源</b></td>
                                        <td width="35%" class="info">
                                            <input type="text" class="form-control" value="${(companyDTO.infoSource)!''}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="15%" class="warning"><b>经营范围</b></td>
                                        <td width="85%" colspan="3" class="info"><textarea class="form-control" rows="5">${(companyDTO.businessScope)!''}</textarea></td>
                                    </tr>
                                    <tr>
                                        <td width="15%" class="warning"><b>详细描述</b></td>
                                        <td width="85%" colspan="3" class="info"><textarea class="form-control" rows="5">${(companyDTO.detailsDescription)!''}</textarea></td>
                                    </tr>
                                    </tbody>
                                </table>
                                <input hidden type="text" name="companyId" value="${(companyDTO.companyId)!''}">
                                <button type="submit" class="btn btn-primary">提交</button>
                            </form>
                        </div>

                    <#--操作-->
                        <#--<div class="col-md-12 column">
                        <#if orderDTO.getOrderStatusEnum().message == "新订单">
                            <a href="/sell/seller/order/finish?orderId=${companyDTO.companyId}" type="button" class="btn btn-default btn-primary">完结订单</a>
                            <a href="/sell/seller/order/cancel?orderId=${companyDTO.companyId}" type="button" class="btn btn-default btn-danger">取消订单</a>
                        </#if>
                        </div>-->
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>