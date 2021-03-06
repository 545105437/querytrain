<!DOCTYPE html>
<html lang="en">
    <#include "../common/head.ftl">
<body>
<div class="container">
    <#-- 导航栏 -->
    <#include "../common/nav.ftl">

    <#-- 主要内容区 -->
        <div class="col-md-12 column" id="detail">
            <h2>公司详情</h2><br>
            <table class="table">
                <tbody>
                <tr>
                    <td width="15%" class="warning"><b>公司名称</b></td>
                    <td width="35%" class="info"><a target="_blank" href="https://www.tianyancha.com/search?key=${companyDTO.companyName}">${companyDTO.companyName}</a></td>
                    <td width="15%" class="warning"><b>公司简称</b></td>
                    <td width="35%" class="info">${companyDTO.companyShortName}</td>
                </tr>
                <tr >
                    <td width="15%" class="warning"><b>公司性质</b></td>
                    <td width="35%" class="info">${companyDTO.getCompanyTypeEnum().message}</td>
                    <td width="15%" class="warning"><b>公司地址</b></td>
                    <td width="35%" class="info">${companyDTO.companyAddress}</td>
                </tr>
                <tr>
                    <td width="15%" class="warning"><b>访问次数</b></td>
                    <td width="35%" class="info">${companyDTO.number}</td>
                    <td width="15%" class="warning"><b>录入时间</b></td>
                    <td width="35%" class="info">${companyDTO.inputTime}</td>
                </tr>
                <tr>
                    <td width="15%" class="warning"><b>经营范围</b></td>
                    <td width="85%" colspan="3" class="info">${companyDTO.businessScope}</td>
                </tr>
                <tr>
                    <td width="15%" class="warning"><b>详细描述</b></td>
                    <td width="85%" colspan="3" class="info">${companyDTO.detailsDescription}</td>
                </tr>
                <tr>
                    <td colspan="4"  class="warning"><a href="#" onClick="javascript :history.go(-1);return false;" class="btn btn-primary"><i class="glyphicon glyphicon-arrow-left"></i>&nbsp;返回</td>
                </tr>
                <#--<tr>
                    <td colspan="4"  class="warning"><a href="http://192.168.2.25:8090/tongji" class="btn btn-primary"><i class="glyphicon glyphicon-arrow-left"></i>&nbsp;点击</td>
                </tr>-->
                </tbody>
            </table>
        </div>
        <div id="bottom"></div>

    <!-- Copyright -->
    <#include "../common/footer.ftl">
</div>
</body>
    <script src="/querytrain/js/main.js"></script>
</html>