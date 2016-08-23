package com.lxc.autopage.base.vo;

import com.lxc.autopage.base.module.ElementHtmlPo;
import com.lxc.autopage.base.module.enums.HtmlType;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/1.
 */
public class ElementHtmlVo implements Serializable{

    private Integer id;
    private String code;
    private String ehValue;
    private String ehSuffix;
    private Integer ehGroupId;
    private HtmlType htmlType;
    private String desc;
    private List<ElementHtmlVo> subList;

    public ElementHtmlVo copyPoValue(ElementHtmlPo po){
        this.id = po.getId();
        this.code = po.getEhCode();
        this.ehValue = po.getEhValue();
        this.ehSuffix = po.getEhSuffix();
        this.ehGroupId = po.getEhGroupId();
        this.htmlType = HtmlType.getByValue(po.getHtmlType());
        this.desc = po.getEhDesc();
        subList = new ArrayList<>();
        return this;
    }

    public void addSubElementVo(ElementHtmlVo vo){
        if (CollectionUtils.isEmpty(subList)){
            subList = new ArrayList<>();
        }
        subList.add(vo);
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

    public HtmlType getHtmlType() {
        return htmlType;
    }

    public void setHtmlType(HtmlType htmlType) {
        this.htmlType = htmlType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<ElementHtmlVo> getSubList() {
        return subList;
    }

    public void setSubList(List<ElementHtmlVo> subList) {
        this.subList = subList;
    }
}
