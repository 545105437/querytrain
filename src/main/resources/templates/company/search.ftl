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
                        <h2>相关公司列表</h2><br>
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th>公司名称</th>
                                <th>公司地址</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list companyDTOPage.content as companyDTO>
                            <tr>
                                <td>${companyDTO.companyName}</td>
                                <td>${companyDTO.companyAddress}</td>
                                <td><a href="/querytrain/company/detail?companyId=${companyDTO.companyId}">详情</a></td>
                            </tr>
                            </#list>
                            <tr>
                                <td colspan="3"><a href="/querytrain/main"  class="btn btn-primary"><i class="glyphicon glyphicon-arrow-left"></i>&nbsp;返回</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                <#--分页-->
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/querytrain/company/search?companyName=${companyName}&page=${currentPage - 1}&size=${size}">上一页</a></li>
                        </#if>
                        <#-- <#list 1..orderDTOPage.getTotalPages() as index >
                             <#if currentPage == index>
                                 <li class="disabled"><a href="#">${index}</a></li>
                             <#else>
                                 <li><a href="/querytrain/company/search?companyName=${companyName}&page=${index}&size=${size}">${index}</a></li>
                             </#if>
                         </#list>-->


                        <#-- 以下为带省略号分页 -->
                        <#--第一页-->
                        <#if (companyDTOPage.getTotalPages() > 0)>
                            <li <#if currentPage == 1>class="disabled"</#if>><a href="/querytrain/company/search?companyName=${companyName}&page=1&size=${size}" >1</a></li>
                        </#if>

                        <#--如果不只有一页-->
                        <#if (companyDTOPage.getTotalPages() > 1)>
                        <#--如果当前页往前查3页不是第2页-->
                            <#if ((currentPage - 3) > 2)>
                                <li><span class="text">…</span></li>
                            </#if>

                        <#--当前页的前3页和后3页-->
                            <#list (currentPage - 3)..(currentPage + 3) as index>
                            <#--如果位于第一页和最后一页之间-->
                                <#if (index > 1) && (index < companyDTOPage.getTotalPages())>
                                    <li <#if currentPage == index>class="disabled"</#if>><a href="/querytrain/company/search?companyName=${companyName}&page=${index}&size=${size}" >${index}</a></li>
                                </#if>
                            </#list>

                        <#--如果当前页往后查3页不是倒数第2页-->
                            <#if (currentPage + 3) < (companyDTOPage.getTotalPages() - 1)>
                                <li><span class="text">…</span></li>
                            </#if>

                        <#--最后页-->
                            <li <#if currentPage == companyDTOPage.getTotalPages()>class="disabled"</#if>><a href="/querytrain/company/search?companyName=${companyName}&page=${companyDTOPage.getTotalPages()}&size=${size}" >${companyDTOPage.getTotalPages()}</a></li>
                        </#if>

                        <#if currentPage gte companyDTOPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/querytrain/company/search?companyName=${companyName}&page=${currentPage + 1}&size=${size}">下一页</a></li>
                        </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div id="bottom"></div>


    <!-- Copyright -->
    <#include "../common/footer.ftl">
</div>
</body>
</html>