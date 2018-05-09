<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
                后台管理系统
            </a>
        </li>
        <li>
            <a href="/querytrain/management/companylist?state=W"><i class="fa fa-fw fa-list-alt"></i> 待审核培训机构</a>
        </li>
        <li>
            <a href="/querytrain/management/companylist?state=F"><i class="fa fa-fw fa-list-alt"></i> 未通过审核培训机构</a>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i class="fa fa-fw fa-plus"></i> 已审核培训机构 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/querytrain/management/companylist">列表</a></li>
                <li><a href="/querytrain/management/neworedit">新增</a></li>
            </ul>
        </li>
        <li>
            <a href="/querytrain/management/logout"><i class="fa fa-fw fa-list-alt"></i> 登出</a>
        </li>
    </ul>
</nav>