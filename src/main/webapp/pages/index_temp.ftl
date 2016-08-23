<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>auto page test</title>

    <link href="${base}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/css/header.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/css/left.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/css/main.css" rel="stylesheet" type="text/css"/>
    <link href="${base}/css/insdetails.css" rel="stylesheet" type="text/css"/>

    <style type="text/css">
        *{
            font-size: 12px;
            color: #7d7268;
        }
    </style>
    <script type="text/javascript">

    </script>
</head>
<body>
    <div class="detmain">
        <#list groups as group>
        <div class="bgf1 dettop">
            <div class="detltit">${group.name}</div>
            <#if (group.elementVos)??>
                <#list group.elementVos as element>
                    <#if element_index%2==0>
                    <div class="detl fl">
                    <#else>
                    <div class="detr fr">
                    </#if>
                <ul>
                <li>
                    <#if element.elementHtmlGroupVoList?size == 0>
                        <label for="" style="line-height: 25px;"><span class="fontOran">*</span>${element.name}</label>
                    <#else>
                        <label for="" style="line-height: ${25*element.elementHtmlGroupVoList?size}px;"><span class="fontOran">*</span>${element.name}</label>
                    </#if>

                    <#list element.elementHtmlGroupVoList as ehgVo>
                            <div class="inputText">
                                <#list ehgVo.elementHtmlVoList as ehVo>
                                    <#if (ehVo.htmlType)?? && ehVo.htmlType == 2>
                                        <input type="text" class="text" name="${group.code}.${ehVo.code}">
                                    <#elseif (ehVo.htmlType)?? && ehVo.htmlType == 3>
                                        <select class="text" name="${group.code}.${ehVo.code}">
                                        </select>
                                    </#if>
                                </#list>
                            </div>


                    </#list>
                </li>
                </ul>
                </div>
                </#list>
            </#if>
        </div>
        </#list>
    </div>
</body>
</html>