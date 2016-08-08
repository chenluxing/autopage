package com.lxc.autopage.base.module;

import com.lxc.autopage.base.module.enums.RelationType;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/1.
 */
@Alias("com.lxc.autopage.base.module.ElementHtmlPo")
public class ElementHtmlPo implements Serializable{

    private Integer id;
    private String ehCode;
    private String ehValue;
    private String ehSuffix;
    private Integer elementId;
    private Integer htmlType;
    private Integer relationType;  // 主从类型、主子
    private Integer ehParentId;
    private String ehDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEhCode() {
        return ehCode;
    }

    public void setEhCode(String ehCode) {
        this.ehCode = ehCode;
    }

    public String getEhValue() {
        return ehValue;
    }

    public void setEhValue(String ehValue) {
        this.ehValue = ehValue;
    }

    public String getEhSuffix() {
        return ehSuffix;
    }

    public void setEhSuffix(String ehSuffix) {
        this.ehSuffix = ehSuffix;
    }

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    public Integer getHtmlType() {
        return htmlType;
    }

    public void setHtmlType(Integer htmlType) {
        this.htmlType = htmlType;
    }

    public Integer getRelationType() {
        return relationType;
    }

    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    public Integer getEhParentId() {
        return ehParentId;
    }

    public void setEhParentId(Integer ehParentId) {
        this.ehParentId = ehParentId;
    }

    public String getEhDesc() {
        return ehDesc;
    }

    public void setEhDesc(String ehDesc) {
        this.ehDesc = ehDesc;
    }
}
