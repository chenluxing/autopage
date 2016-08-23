<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>auto page test</title>

    <style type="text/css">
        *{
            font-size: 12px;
            color: #7d7268;
            margin: 0px;
        }
        .div_group{
            background:#f1f1f1;
            display:inline-block;
            width: 100%;
            min-height: 100px;
            min-width: 1000px;
        }
        .div_title{
            height: 25px;
            line-height: 25px;
            background: #fff;
            border-bottom: 1px solid #f3deca;
            font-size: 14px;
            padding: 10px 0 0 26px;
            font-weight: bold;
            width: 98%;
        }
        .div_element{
            width: 50%;
            *width:49%
        }
        .div_fl{
            float: left;
            display: inline;
        }
        .div_fr{
            float: right;
            display: inline;
        }
        .fontOran{
            color: #f60;
            margin-right: 3px;
        }
        .policy_lable{
            float: left;
            display: inline;
            text-align: right;
            width: 30%;
            padding-right: 10px;
        }
        .lable_sufixx{
            padding-left: 3px;
        }
        .div_inputText{
            float: left;
            display: inline;
            width: 65%;
            padding-top: 5px;
            padding-bottom: 5px;
            line-height:30px;
        }
        .policy_text{
            width: 260px;
            height: 25px;
            line-height: 25px;
            padding-left: 3px;
            border:1px solid #f4d3b3;
            margin-bottom: 2px;
        }
        .policy_select{
            width: 266px;
            height: 25px;
            line-height: 25px;
            padding-left: 3px;
            border:1px solid #f4d3b3;
            margin-bottom: 2px;
        }
        .policy_checkbox{
            height: 25px;
            line-height: 25px;
            padding-left: 3px;
            border:1px solid #f4d3b3;
            vertical-align: middle;
            margin-bottom: 2px;
        }
        input.policy_date{
            width: 260px;
            height: 25px;
            line-height: 25px;
            padding-left: 3px;
            border:1px solid #f4d3b3;
            margin-bottom: 2px;
        }
        .policy_textarea{
            padding: 10px;
            width: 400px;
            height: 140px;
            margin-top: 10px;
        }
        .policy_select hover,.policy_text hover, .policy_checkbox hover{
            border: 1px solid #ff5307;
        }
    </style>

    <script type="text/javascript" src="${base}/plugin/datePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${base}/plugin/cxselect/jquery.cxselect.min.js"></script>
    <script type="text/javascript" src="${base}/js/jquery-1.11.3.min.js"></script>

    <script type="text/javascript">
        // 省市级联
        function initProvinceCity(div, province, city){
            $(div).cxSelect({
                selects: [province, city],
                url: '${base}/common/provinces.json',
                jsonName: 'name',
                jsonValue: 'code',
                jsonSub: 'children'
            });
        }
    </script>
</head>
<body>
    <div style="float: left; width: 100%;">
        <#list groups as group>
        <div class="div_group">
            <div class="div_title" >${group.name}</div>
            <#if (group.elementVos)??>
                <#list group.elementVos as element>
                    <#if element_index%2==0>
                    <div class="div_element div_fl">
                    <#else>
                    <div class="div_element div_fr">
                    </#if>
                <ul style="list-style-type:none; padding:10px 0;">
                <li>
                    <#if element.elementHtmlGroupVoList?size == 0>
                        <label for="" class="policy_lable" style="line-height: 40px; "><span class="fontOran">*</span>${element.name}</label>
                    <#else>
                        <label for="" class="policy_lable" style="line-height: ${40*element.elementHtmlGroupVoList?size}px;"><span class="fontOran">*</span>${element.name}</label>
                    </#if>

                    <#list element.elementHtmlGroupVoList as ehgVo>
                            <div class="div_inputText" >
                                <#list ehgVo.elementHtmlVoList as ehVo>
                                    <#if (ehVo.htmlType)?? && ehVo.htmlType == 'INPUT'>
                                        <input type="text" class="policy_text" name="${group.code}.${ehVo.code}" /><label class="lable_sufixx">${(ehVo.ehSuffix)!""}</label>

                                    <#elseif (ehVo.htmlType)?? && ehVo.htmlType == 'SELECT'>
                                        <select class="policy_select" name="${group.code}.${ehVo.code}" >
                                            <option value="">--请选择--</option>
                                            <#if (ehVo.subList)??>
                                                <#list ehVo.subList as subEhVo>
                                                    <option value="${subEhVo.ehValue}">${subEhVo.ehSuffix}</option>
                                                </#list>
                                            </#if>
                                        </select><label class="lable_sufixx">${(ehVo.ehSuffix)!""}</label>

                                    <#elseif (ehVo.htmlType)?? && ehVo.htmlType == 'CHECKBOX'>
                                        <input type="checkbox" class="policy_checkbox" value="${(ehVo.ehValue)!""}" name="${group.code}.${ehVo.code}" /><label class="lable_sufixx">${(ehVo.ehSuffix)!""}</label>

                                    <#elseif (ehVo.htmlType)?? && ehVo.htmlType == 'DATE'>
                                        <input type="text" class="policy_date Wdate" name="${group.code}.${ehVo.code}"
                                               onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'});" /><label class="lable_sufixx">${(ehVo.ehSuffix)!""}</label>

                                    <#elseif (ehVo.htmlType)?? && ehVo.htmlType == 'DATETIME'>
                                        <input type="text" class="Wdate policy_date" name="${group.code}.${ehVo.code}"
                                               onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'});" /><label class="lable_sufixx">${(ehVo.ehSuffix)!""}</label>

                                    <#elseif (ehVo.htmlType)?? && ehVo.htmlType == 'LABEL'>
                                        <label class="lable_sufixx" >${(ehVo.ehValue)!""}</label><label class="lable_sufixx">${(ehVo.ehSuffix)!""}</label>

                                    <#elseif (ehVo.htmlType)?? && ehVo.htmlType == 'TEXTAREA'>
                                        <textarea name="${group.code}.${ehVo.code}" class="policy_textarea"></textarea><label class="lable_sufixx">${(ehVo.ehSuffix)!""}</label>

                                    <#elseif (ehVo.htmlType)?? && ehVo.htmlType == 'FILE'>
                                        <input type="file" class="" value="${(ehVo.ehValue)!""}" name="${group.code}.${ehVo.code}" ><label class="lable_sufixx">${(ehVo.ehSuffix)!""}</label>

                                    <#elseif (ehVo.htmlType)?? && ehVo.htmlType == 'HIDDEN'>
                                        <input type="hidden" class="" value="${(ehVo.ehValue)!""}" name="${group.code}.${ehVo.code}" ><label class="lable_sufixx">${(ehVo.ehSuffix)!""}</label>

                                    <#elseif (ehVo.htmlType)?? && ehVo.htmlType == 'AREA'>
                                        <div id="${group.code}_area">
                                            省：
                                            <select name="${group.code}.provinceCode" class="province" style="width: 120px"></select>
                                            市：
                                            <select name="${group.code}.cityCode" class="city" style="width: 120px"></select>
                                            <label class="lable_sufixx">${(ehVo.ehSuffix)!""}</label>
                                        </div>
                                        <script type="text/javascript">
                                            initProvinceCity("#${group.code}_area", "province", "city");
                                        </script>
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