package com.lxc.autopage.base.module.enums;

/**
 * Created by admin on 2016/8/1.
 */
public enum RelationType {

    MASTER(0 , "主节点"),
    SLAVE(1 , "从节点"),
    SUB(2 , "子节点");

    private Integer value;
    private String name;

    private RelationType(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    public static RelationType getRelationType(Integer value){

        return null;
    }
}
