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
            width: 65%;
            border: 1px solid #f4d3b3;
            line-height: 25px;
            height: 25px;
            padding-left: 2%;
        }
        .input_checkbox{
            vertical-align: middle;
        }
        .select{
            border: 1px solid #f4d3b3;
            min-width: 100px;
            line-height: 25px;
            height: 25px;
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
            <td colspan="4" class="td_group">${group.displayName}</td>
        </tr>
            <#list group.subElements as element>
            <#if element_index%2==0>
                <tr>
            </#if>
                <td class="td_head">${element.displayName}</td>
                <td class="td_body">
                    <#if element.elementType == 'INPUT'>
                        <input name="${group.name}.${element.name}" type="text" class="input_text"></input>
                    <#elseif element.elementType == 'SELECT'>
                        <select name="${group.name}.${element.name}" class="select">
                        <!-- 读取子节点 -->
                        <#list element.subElements as subElement>
                            <#if subElement.elementType == 'OPTION'>
                                <option value="${subElement.value}">${subElement.displayName}</option>
                            </#if>
                        </#list>
                        </select>
                    <#elseif element.elementType == 'CHECKBOX'>
                        <input name="${group.name}.${element.name}" type="checkbox" class="input_checkbox">${element.displayName}</input>
                    </#if>

                    <!-- 读取从节点 -->
                    <#list element.slaveElements as slaveElement>
                        <#if slaveElement.elementType == 'INPUT'>
                            <input name="${group.name}.${slaveElement.name}" type="text"></input>
                        <#elseif slaveElement.elementType == 'SELECT'>
                            <select name="${group.name}.${slaveElement.name}" class="select">

                            </select>
                        <#elseif slaveElement.elementType == 'CHECKBOX'>
                            <input name="${group.name}.${slaveElement.name}" type="checkbox" class="input_checkbox">${slaveElement.displayName}</input>
                        </#if>
                    </#list>
                </td>
            <#if element_index%2==1 || element_index==element?size>
            </tr>
            </#if>
            </#list>
        </#list>
    </table>

</body>
</html>