package com.lxc.autopage.base.module;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by admin on 2016/8/1.
 */
@Alias("com.lxc.autopage.base.module.ElementHtmlPo")
public class ElementHtmlPo implements Serializable{

    private Integer id;
    private String ehCode;
    private String ehValue;
    private String ehSuffix;
    private Integer ehGroupId;  // 分组标记
    private Integer ehParentId; // 父节点ID
    private Integer htmlType;
    private String ehDesc;
    private int showSeq;

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

    public Integer getEhGroupId() {
        return ehGroupId;
    }

    public Integer getEhParentId() {
        return ehParentId;
    }

    public void setEhParentId(Integer ehParentId) {
        this.ehParentId = ehParentId;
    }

    public void setEhGroupId(Integer ehGroupId) {
        this.ehGroupId = ehGroupId;
    }

    public Integer getHtmlType() {
        return htmlType;
    }

    public void setHtmlType(Integer htmlType) {
        this.htmlType = htmlType;
    }

    public String getEhDesc() {
        return ehDesc;
    }

    public void setEhDesc(String ehDesc) {
        this.ehDesc = ehDesc;
    }

    public int getShowSeq() {
        return showSeq;
    }

    public void setShowSeq(int showSeq) {
        this.showSeq = showSeq;
    }
}
