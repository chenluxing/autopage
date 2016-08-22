package com.lxc.autopage.base.vo;

import com.lxc.autopage.base.module.ElementHtmlPo;

import java.io.Serializable;

/**
 * Created by admin on 2016/8/1.
 */
public class ElementHtmlVo implements Serializable{

    private Integer id;
    private String code;
    private String ehValue;
    private String ehSuffix;
    private Integer ehGroupId;
    private Integer htmlType;
    private String desc;

    public ElementHtmlVo copyPoValue(ElementHtmlPo po){
        this.id = po.getId();
        this.code = po.getEhCode();
        this.ehValue = po.getEhValue();
        this.ehSuffix = po.getEhSuffix();
        this.ehGroupId = po.getEhGroupId();
        this.htmlType = po.getHtmlType();
        this.desc = po.getEhDesc();
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public void setEhGroupId(Integer ehGroupId) {
        this.ehGroupId = ehGroupId;
    }

    public Integer getHtmlType() {
        return htmlType;
    }

    public void setHtmlType(Integer htmlType) {
        this.htmlType = htmlType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
