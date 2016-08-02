package com.lxc.autopage.base.module.enums;

/**
 * Created by admin on 2016/8/1.
 */
public enum ElementType {

    GROUP(1, "分组"),
    INPUT(2, "分组"),
    SELECT(3, "分组"){
        public ElementType getChildType(){
            return null;
        }
    },
    OPTION(1, "分组"),
    CHECKBOX(4, "分组");

    private int value;
    private String name;

    ElementType(int value, String name){
        this.value = value;
        this.name = name;
    }

    public ElementType getChildType(){
        return null;
    }
}
