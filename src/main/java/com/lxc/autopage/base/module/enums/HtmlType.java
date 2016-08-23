package com.lxc.autopage.base.module.enums;

/**
 * Created by admin on 2016/8/1.
 */
public enum HtmlType {

    INPUT(1, "INPUT"),
    SELECT(2, "下拉选"){
        public boolean hasSubElement(){
            return true;
        }
    },
    CHECKBOX(3, "复选框"),
    DATE(4, "时间控件"),
    DATETIME(5, "时间控件"),
    LABEL(6, "文本"),
    TEXTAREA(7, "文本域"),
    FILE(8, "文件"),
    HIDDEN(9, "隐藏值"),
    AREA(10, "省市级联控件");

    private Integer value;
    private String name;

    HtmlType(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 判断是否包含子节点
    public boolean hasSubElement(){
        return false;
    }

    public static HtmlType getByValue(Integer value){
        if (value != null){
            for (HtmlType elementType : values()){
                if (elementType.getValue().equals(value)){
                    return elementType;
                }
            }
        }
        return null;
    }
}
