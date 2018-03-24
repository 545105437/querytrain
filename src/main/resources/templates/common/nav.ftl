<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/querytrain/main">培训机构查询</a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="active"><a href="/querytrain/main">首页</a></li>
            <li><a href="/querytrain/aboutUs">关于</a></li>
            <li><a href="/querytrain/submitData">提交数据</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">捐赠<strong class="caret"></strong></a>
                <ul class="dropdown-menu">
                    <li><a href="#wxModal" data-toggle="modal">微信</a></li>
                    <li><a href="#aliModal" data-toggle="modal">支付宝</a></li>
                </ul>
            </li>
            <li>
              <#--  <B style="color: red">本站暂时还未挂上服务器，每日工作时间可以访问（9:00~17:30）,请大家见谅</B>-->
                  <a href="#"><B style="color: red">本站收录数据还有待提高，希望大家能一起完善，谢谢！！！</B></a>
            </li>
        </ul>
        <form class="navbar-form navbar-left" role="search" style="display:none;">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="请输入企业名或简称"/>
            </div> <button type="submit" class="btn btn-default">Submit</button>
        </form>

    </div>
</nav>

<div class="modal fade" id="wxModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 280px;height:250px; ">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
               <img src="/querytrain/images/WX.png" width="250px" height="250px" >
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="aliModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 280px;height:250px; ">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <img src="/querytrain/images/ZFB.jpg" width="250px" height="250px" >
            </div>
        </div>
    </div>
</div>

