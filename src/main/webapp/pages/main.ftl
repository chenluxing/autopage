[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>首页</title>
        <link href="${base}/resources/css/main.css" rel="stylesheet" type="text/css">
        <script type="text/javascript">
            $().ready(function () {
            });
            if (self != top) {
                top.location = self.location;
            }
        </script>
    </head>
    <body>
    asdasdfd
        <!-- 头部 start-->
        <header class="header">
            <div class="wrap clearfix bgf5">
                <div class="logoText fl">机构名称</div>
                <div class="backlTit fr clearfix">
                    <div class="backother fl">
                        <i class="tIcon"></i>
                        <a class="fl fontBrown tText a83" href="" id="text">欢迎您,[@shiro.principal/]</a>
                        <div class="adsj"></div>
                    </div>
                    <div class="adconbg">
                        <div class="adCon">
                            </p>
                            <div class="adText">
                                <ul>
                                    <a href="${base}/member/toPassword.html" target="iframe">
                                        <li>[修改密码]</li>
                                    </a>
                                </ul>
                            </div>
                            <a href="${base}/logout.html" target="_top">
                                <div class="adQuit clearfix"><i class="qIcon"></i>[退出]</div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="wrap headmenu bgOran">
                <!-- 顶部主菜单 start -->
                <ul class="menu clearfix">
                    <li class="active" id="workbench">
                        <a href="#product">工作台</a>
                    </li>
                    <li>
                        <a href="#product">保险产品</a>
                    </li>
                </ul>
                <!-- 顶部主菜单 end -->
            </div>
            <div class="userswitch">
                <div class="usertit">
                    角色切换
                    <div class="uclose"></div>
                </div>
                <div class="usercon">
                </div>
            </div>
            <div class="userswitchbg">
            </div>
        </header>
        <!-- 头部end -->
        <!-- 内容部分 start -->
        <div class="wrap clearfix" id="backmain">
        <!-- 左栏 start -->
            <div id="backleft" class="fl">

                <div class="bleftcon" style="display:block;">
                    <div>
                        <div class="vtitle tsy">
                            <i></i><em class="bleft bsty"></em>保险公司
                        </div>
                        <div class="vcon">
                            <ul class="vconlist clearfix">
                                    <li class="select">
                                        <a href="${base}/insurer/queryList.html" class="current" target="iframe"><i></i>保险公司查询</a>
                                    </li>
                                    <li>
                                        <a href="${base}/insurer/list.html" class="current" target="iframe"><i></i>保险公司维护</a>
                                    </li>
                            </ul>
                        </div>
                    </div>
                    <div>
                        <div class="vtitle"><i></i><em class="bleft bsty"></em>协议管理</div>
                        <div class="vcon">
                            <ul class="vconlist clearfix">
                                    <li>
                                        <a href="${base}/protocal/queryList.html" class="current" target="iframe"><i></i>协议查询</a>
                                    </li>
                                    <li>
                                        <a href="${base}/protocal/list.html" class="current" target="iframe"><i></i>协议维护</a>
                                    </li>
                            </ul>
                        </div>
                    </div>
                    <div>
                        <div class="vtitle"><i></i><em class="bleft bsty"></em>产品管理</div>
                        <div class="vcon">
                            <ul class="vconlist clearfix">
                                    <li><a href="${base}/product/queryList.html" target="iframe"><i></i>产品查询</a></li>
                                    <li><a href="${base}/product/list.html" target="iframe"><i></i>产品维护</a></li>
                                    <li><a href="${base}/category/list.html" target="iframe"><i></i>产品分类维护</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 左栏 end -->

            <!-- 右栏 start -->
            <div id="backcon" style="margin-left: 0px;">
                <iframe id="iframe" name="iframe" src="" frameborder="0">
                </iframe>
            </div>
            <!-- 右栏 end -->
        </div>
        <!-- 内容部分 end -->
    </body>
</html>