<#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>首页</title>
        <link href="${base}/resources/css/main.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="${base}/resources/js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript">
            $().ready(function () {
            });
            if (self != top) {
                top.location = self.location;
            }
        </script>
        <style type="text/css">
            .logoText {
                height: 60px;
                line-height: 60px;
                overflow: hidden;
                padding-left: 30px;
                font-family: "微软雅黑", "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif, "Arial Narrow", HELVETICA;
                font-size: 20px;
                font-weight: bold;
                color: #ff5307;
            }
            .fl{
                float: left;
                display: inline;
            }
            .fr{
                float: right;
                display: inline-block;
            }
            .backlTit {
                height: 45px;
                cursor: pointer;
                color: #7d5930;
                font-weight: bold;
            }
            .wrap {
                min-width: 1200px;
            }
            .backother {
                height: 53px;
                margin: 7px 68px 0px 0px;
                position: relative;
            }
            .clearfix {
                zoom: 1;
                display: block;
            }
            .menu {
                margin-left: 30px;
            }
            ul {
                list-style-type: none;
            }
            .menu li {
                float: left;
                margin-right: 20px;
                height: 45px;
            }
            .menu li a{display: block; height: 14px; line-height: 12px; *line-height: 14px; margin-top: 8px; padding: 7px; color:#fff; font-size: 14px; text-decoration: none;}
            .menu li.active a,.menu li a:hover{color: #ff5307; font-weight: bold; border-radius: 4px; background-color: #fff;}
            .menu li.active a{color: #ff5307;}
            .bgOran {
                background-color: #ff5307;
            }
            #backleft, #backleft:before {
                width: 180px;
                height: 100%;
                background-color: #fff9f3;
                border-right: 1px solid #decbbf;
                margin-top: 8px;
            }
            .vtitle {
                height: 36px;
                line-height: 36px;
                color: #776c62;
                cursor: pointer;
                background-color: #fef4e9;
            }
            .tsy {
                background-color: #754723;
                color: #dfd4cb;
                font-size: 14px;
                font-weight: bold;
            }
            .vcon {
                border-bottom: 1px solid #decbbf;
                padding: 1px;
            }
            .bleftcon {
                height: 100%;
            }
            .vconlist li.select, .vconlist li:hover {
                background-color: #ffe8d2;
                border-color: #f4d3b3;
            }

            .vconlist li {
                overflow: hidden;
                cursor: pointer;
                border: 1px solid #fff9f3;
                border-left: 0px;
                border-right: 0px;
            }
            .vconlist li a {
                height: 34px;
                width: 100%;
                line-height: 34px;
                float: left;
                display: block;
                color: #776c62;
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <!-- 头部 start-->
        <header>
            <div class="wrap clearfix">
                <div class="logoText fl">机构名称</div>
                <div class="backlTit fr clearfix" style="font-weight: normal;">
                    <div class="backother fl">
                        <i class="tIcon"></i>
                        <a class="fl fontBrown tText a83" href="" id="text">欢迎您,张三丰<@shiro.principal/></a>
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
                                    <a href="${base}/index.html" class="current" target="iframe"><i></i>保险公司查询</a>
                                </li>
                                <li>
                                    <a href="${base}/index.html" class="current" target="iframe"><i></i>保险公司维护</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div>
                        <div class="vtitle"><i></i><em class="bleft bsty"></em>协议管理</div>
                        <div class="vcon">
                            <ul class="vconlist clearfix">
                                    <li>
                                        <a href="${base}/index.html" class="current" target="iframe"><i></i>协议查询</a>
                                    </li>
                                    <li>
                                        <a href="${base}/index.html" class="current" target="iframe"><i></i>协议维护</a>
                                    </li>
                            </ul>
                        </div>
                    </div>
                    <div>
                        <div class="vtitle"><i></i><em class="bleft bsty"></em>产品管理</div>
                        <div class="vcon">
                            <ul class="vconlist clearfix">
                                    <li><a href="${base}/index.html" target="iframe"><i></i>产品查询</a></li>
                                    <li><a href="${base}/index.html" target="iframe"><i></i>产品维护</a></li>
                                    <li><a href="${base}/index.html" target="iframe"><i></i>产品分类维护</a></li>
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