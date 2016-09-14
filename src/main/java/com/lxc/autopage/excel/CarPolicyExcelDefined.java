package com.lxc.autopage.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车险保单excel定义
 * Created by chenlx
 * on 2016/9/13.
 */
public class CarPolicyExcelDefined {

    public List<ICellDefined> getCellDefinedList(){
        List<ICellDefined> list = new ArrayList<>();
        Map<String, Object> surMap = new HashMap<>();
        surMap.put("是", true);
        surMap.put("否", false);
        // 保单基本信息定义
        CellBooleanDefined isSur = new CellBooleanDefined(0, "批单否", "isSur", true, true, surMap);
        CellStringDefined billNo = new CellStringDefined(1, "保单号/批单号", "billNo", true);
        CellDateDefined startDate = new CellDateDefined(2, "起保时间/退保时间", "startDate", true);
        CellDateDefined outDate = new CellDateDefined(3, "出单日期", "outDate", true);
        CellBigDecimalDefined billAmount = new CellBigDecimalDefined(4, "保费", "billAmount", true);
        CellBigDecimalDefined agentPercent = new CellBigDecimalDefined(5, "佣金比例", "agentPercent", true);
        CellStringDefined insuranceType = new CellStringDefined(7, "险种类型", "insuranceType", true);
        CellStringDefined insRole = new CellStringDefined(8, "业务员姓名", "insRole", true);
        list.add(isSur);
        list.add(billNo);
        list.add(startDate);
        list.add(outDate);
        list.add(billAmount);
        list.add(agentPercent);
        list.add(insuranceType);
        list.add(insRole);

        // 被保人信息定义
        CellStringDefined insuredName = new CellStringDefined(9, "被保人姓名", "insuredName", true);
        CellStringDefined insuredmobilePhone = new CellStringDefined(10, "被保人手机号", "insuredmobilePhone", true);
        CellStringDefined insuredidentifyName = new CellStringDefined(11, "被保人证件类型", "insuredidentifyName", true);
        CellStringDefined insuredidentifyNumber = new CellStringDefined(12, "被保人证件号码", "insuredidentifyNumber", true);
        list.add(insuredName);
        list.add(insuredmobilePhone);
        list.add(insuredidentifyName);
        list.add(insuredidentifyNumber);
        // 车主信息定义

        // 车辆信息定义

        // 基本险种信息定义

        // 附加信息定义

        // 备注

        return list;
    }

}
