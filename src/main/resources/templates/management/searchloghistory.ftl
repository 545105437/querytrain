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
                <div class="col-md-12 column" id="search">
                    <form id="myForm" role="form" method="get" action="/querytrain/management/searchLogHistoryList">
                        <div class="input-group">
                            时间范围：<input type="text" class="input-sm" name="startTime" id="startTime" value="${(startTime)!''}"> --
                            <input type="text" class="input-sm" name="endTime" id="endTime" value="${(endTime)!''}">
                            <span class="input-group-addon btn btn-primary" id="managementsearch" onclick="submit()">检索</span>
                        </div>
                    </form>
                    <table class="table table-condensed table-bordered">
                        <thead>
                        <colgroup>
                            <col width='4%'>
                            <col width='16%'>
                            <col width='16%'>
                            <col width='16%'>
                            <col width='16%'>
                            <col width='16%'>
                            <col width='16%'>

                        </colgroup>
                        <tr>
                            <th class="text-center">序号</th>
                            <th class="text-center">访问地址</th>
                            <th class="text-center">查询参数</th>
                            <th class="text-center">访问者IP</th>
                            <th class="text-center">访问类型</th>
                            <th class="text-center">访问行为</th>
                            <th class="text-center">访问时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list searchLogHistoryDTOPage.content as searchLogHistoryDTO>
                        <tr>
                            <td>${searchLogHistoryDTO.logId}</td>
                            <td>${searchLogHistoryDTO.url}</td>
                            <td>${searchLogHistoryDTO.args}</td>
                            <td>${searchLogHistoryDTO.ip}</td>
                            <td>${searchLogHistoryDTO.actionType}</td>
                            <td>${searchLogHistoryDTO.action}</td>
                            <td>${searchLogHistoryDTO.visitTime}</td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                    <#if (searchLogHistoryDTOPage.getTotalPages() = 0)>
                        <div align="center">
                            无相关日志信息，请更改检索条件，重新查询
                        </div>
                    </#if>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/querytrain/management/searchLogHistoryList?startTime=${startTime}&endTime=${endTime}&page=${currentPage - 1}&size=${size}">上一页</a></li>
                    </#if>
                    <#-- <#list 1..orderDTOPage.getTotalPages() as index >
                         <#if currentPage == index>
                             <li class="disabled"><a href="#">${index}</a></li>
                         <#else>
                             <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a></li>
                         </#if>
                     </#list>-->


                    <#-- 以下为带省略号分页 -->
                    <#--第一页-->
                    <#if (searchLogHistoryDTOPage.getTotalPages() > 0)>
                        <li <#if currentPage == 1>class="disabled"</#if>><a href="/querytrain/management/searchLogHistoryList?startTime=${startTime}&endTime=${endTime}&page=${currentPage - 1}&size=${size}" >1</a></li>
                    </#if>

                    <#--如果不只有一页-->
                    <#if (searchLogHistoryDTOPage.getTotalPages() > 1)>
                    <#--如果当前页往前查3页不是第2页-->
                        <#if ((currentPage - 3) > 2)>
                            <li><span class="text">…</span></li>
                        </#if>

                    <#--当前页的前3页和后3页-->
                        <#list (currentPage - 3)..(currentPage + 3) as index>
                        <#--如果位于第一页和最后一页之间-->
                            <#if (index > 1) && (index < searchLogHistoryDTOPage.getTotalPages())>
                                <li <#if currentPage == index>class="disabled"</#if>><a href="/querytrain/management/searchLogHistoryList?startTime=${startTime}&endTime=${endTime}&page=${currentPage - 1}&size=${size}" >${index}</a></li>
                            </#if>
                        </#list>

                    <#--如果当前页往后查3页不是倒数第2页-->
                        <#if (currentPage + 3) < (searchLogHistoryDTOPage.getTotalPages() - 1)>
                            <li><span class="text">…</span></li>
                        </#if>

                    <#--最后页-->
                        <li <#if currentPage == searchLogHistoryDTOPage.getTotalPages()>class="disabled"</#if>><a href="/querytrain/management/searchLogHistoryList?startTime=${startTime}&endTime=${endTime}&page=${currentPage - 1}&size=${size}" >${searchLogHistoryDTOPage.getTotalPages()}</a></li>
                    </#if>


                    <#if currentPage gte searchLogHistoryDTOPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/querytrain/management/searchLogHistoryList?startTime=${startTime}&endTime=${endTime}&page=${currentPage - 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
