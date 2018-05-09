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
                    <form id="myForm" role="form" method="get" action="/querytrain/management/companylist">
                        <div class="input-group">
                            <input type="text" class="form-control input-lg" name="companyName" id="companyName" placeholder="请输入公司名称" value="${(companyName)!''}"><span class="input-group-addon btn btn-primary" id="managementsearch" onclick="submit()">检索</span>
                        </div>
                        <input type="text" name="state" id="state" value="${(state)!''}" hidden="hidden">
                    </form>
                    <table class="table table-condensed table-bordered">
                        <thead>
                        <colgroup>
                            <col width='15%'>
                            <col width='8%'>
                            <col width='8%'>
                            <col width='8%'>
                            <col width='20%'>
                            <col width='25%'>
                            <col width='6%'>
                            <col width='3%'>
                            <col width='3%'>
                            <col width='6%'>
                        </colgroup>
                        <tr>
                            <th class="text-center">公司名称</th>
                            <th class="text-center">公司简称</th>
                            <th class="text-center">公司性质</th>
                            <th class="text-center">信息来源</th>
                            <th class="text-center">公司地址</th>
                            <th class="text-center">详细描述</th>
                            <th class="text-center">状态</th>
                            <th colspan="2" class="text-center">审核</th>
                            <th class="text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list companyDTOPage.content as companyDTO>
                        <tr>
                            <td>${companyDTO.companyName}</td>
                            <td>${companyDTO.companyShortName}</td>
                            <td>${companyDTO.getCompanyTypeEnum().message}</td>
                            <td>${companyDTO.infoSource}</td>
                            <td>${companyDTO.companyAddress}</td>
                            <td>${companyDTO.detailsDescription}</td>
                            <td>${companyDTO.getStateEnum().message}</td>
                            <td>
                                <#if companyDTO.getStateEnum().message == "待审批">
                                    <a href="/querytrain/management/pass?companyId=${companyDTO.companyId}" ><span class="glyphicon glyphicon-ok"></span></a>
                                </#if>
                            </td>
                            <td>
                                <#if companyDTO.getStateEnum().message == "待审批">
                                    <a href="/querytrain/management/reject?companyId=${companyDTO.companyId}" id="a_remove" ><span class="glyphicon glyphicon-remove"></span></a>
                                </#if>
                            </td>
                            <td>
                                <a href="/querytrain/management/neworedit?companyId=${companyDTO.companyId}"><span class="glyphicon glyphicon-pencil"></span> Edit</a>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                    <#if (companyDTOPage.getTotalPages() = 0)>
                        <div align="center">
                            无相关培训机构信息，请更改检索条件，重新查询
                        </div>
                    </#if>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/querytrain/management/companylist?companyName=${companyName}&state=${state}&page=${currentPage - 1}&size=${size}">上一页</a></li>
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
                    <#if (companyDTOPage.getTotalPages() > 0)>
                        <li <#if currentPage == 1>class="disabled"</#if>><a href="/querytrain/management/companylist?companyName=${companyName}&state=${state}&page=1&size=${size}" >1</a></li>
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
                                <li <#if currentPage == index>class="disabled"</#if>><a href="/querytrain/management/companylist?companyName=${companyName}&state=${state}&page=${index}&size=${size}" >${index}</a></li>
                            </#if>
                        </#list>

                    <#--如果当前页往后查3页不是倒数第2页-->
                        <#if (currentPage + 3) < (companyDTOPage.getTotalPages() - 1)>
                            <li><span class="text">…</span></li>
                        </#if>

                    <#--最后页-->
                        <li <#if currentPage == companyDTOPage.getTotalPages()>class="disabled"</#if>><a href="/querytrain/management/companylist?companyName=${companyName}&state=${state}&page=${companyDTOPage.getTotalPages()}&size=${size}" >${companyDTOPage.getTotalPages()}</a></li>
                    </#if>


                    <#if currentPage gte companyDTOPage.getTotalPages()>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/querytrain/management/companylist?companyName=${companyName}&state=${state}&page=${currentPage + 1}&size=${size}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<#--弹窗-->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                你有新的培训机构录入
            </div>
            <div class="modal-footer">
                <button onclick="javascript:document.getElementById('notice').pause();" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的培训机构</button>
            </div>
        </div>
    </div>
</div>
<#--播放音乐-->
<audio id="notice" loop="loop">
    <source src="/querytrain/mp3/song.mp3" type="audio/mpeg">
</audio>
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    var websocket = null;
    if('WebSocket' in window) {
        websocket = new WebSocket("ws://query.natapp1.cc/querytrain/webSocket");
    }else {
        alert('该浏览器不支持websocket!')
    }

    websocket.onopen = function (event) {
        console.log('建立连接');
    }

    websocket.onclose = function (event) {
        console.log('连接关闭');
    }

    websocket.onmessage = function (event) {
        console.log('收到消息：' + event.data );
        //弹窗提醒，播放音乐
        $('#myModal').modal('show');

        document.getElementById('notice').play();
    }

    websocket.onerror = function () {
        alert('webscoket通信发生错误！');
    }

    window.onbeforeunload = function () {
        websocket.close();
    }

</script>

</body>
</html>

<#--



-->
