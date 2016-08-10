<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>auto page test</title>
    <style type="text/css">
        *{
            font-size: 12px;
            color: #7d7268;
        }
        td{
            height: 37px;
            vertical-align: middle;
        }
        .input_text{
            width: 200px;
            border: 1px solid #f4d3b3;
            line-height: 25px;
            height: 25px;
            padding-left: 2%;
            margin-top: 5px;
        }
        .input_checkbox{
            vertical-align: middle;
        }
        .select{
            border: 1px solid #f4d3b3;
            min-width: 100px;
            line-height: 25px;
            height: 25px;
            width: 210px;
        }
        .td_group{
            color: #7d7268;
            font-weight: bold;
            border: 1px solid #f3deca; 
            background:white;
            padding-left: 20px;
        }
        .td_head{
            text-align: right;
            padding-right: 5px; width: 15%;
        }
        .td_body{
            width: 34%;
        }
    </style>
    <script type="text/javascript">

    </script>
</head>
<body>
    <table style="width: 100%; background-color: #f1f1f1">
        <#list groups as group>
        <tr>
            <td colspan="4" class="td_group">${group.name}</td>
        </tr>
            <#if (group.elementVos)??>
            <#list group.elementVos as element>
                <#if element_index%2==0>
                <tr>
                </#if>
                <td class="td_head">${element.name}</td>
                <td class="td_body">
                    <#if (element.elementHtml)??>
                        <#if (element.elementHtml.htmlType)?? && element.elementHtml.htmlType == 2>
                            <input name="${group.code}.${element.elementHtml.code}" type="text" class="input_text" /> ${(element.elementHtml.ehSuffix)!""}
                        <#elseif (element.elementHtml.htmlType)?? && element.elementHtml.htmlType == 3>
                            <select name="${group.code}.${element.elementHtml.code}" class="select input_text">

                            </select> ${(element.elementHtml.ehSuffix)!""}
                        <#elseif (element.elementHtml.htmlType)?? && element.elementHtml.htmlType == 4>
                            <input name="${group.code}.${element.elementHtml.code}" type="checkbox"/> ${(element.elementHtml.ehSuffix)!""}
                        </#if>

                        <!-- 兄弟节点 -->
                        <#if (element.elementHtml.slaveElements)??>
                            <#list element.elementHtml.slaveElements as slaveElement>
                                <#if (slaveElement.htmlType)?? && slaveElement.htmlType == 2>
                                    <input name="${group.code}.${slaveElement.code}" type="text" class="input_text" /> ${(slaveElement.ehSuffix)!""}
                                <#elseif (slaveElement.htmlType)?? && slaveElement.htmlType == 3>
                                    <select name="${group.code}.${slaveElement.code}" class="select input_text">
                                    </select> ${(slaveElement.ehSuffix)!""}
                                <#elseif (slaveElement.htmlType)?? && slaveElement.htmlType == 4>
                                    <input name="${group.code}.${slaveElement.code}" type="checkbox"/> ${(slaveElement.ehSuffix)!""}
                                </#if>
                            </#list>
                        </#if>

                    </#if>
                </td>
                <#if element_index%2==1 || element_index==group.elementVos?size>
                </tr>
                </#if>
            </#list>
            </#if>
        </#list>
    </table>

</body>
</html>