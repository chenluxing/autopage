package com.lxc.autopage.base.vo;

import com.lxc.autopage.base.module.ElementPo;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * Created by admin on 2016/8/1.
 */
public class ElementVo implements Serializable {

    private Integer id;
    private String code;
    private String name;
    private String desc;
    private Integer elementType;
    private Integer groupId;
    private int showSeq;
    private ElementHtmlVo elementHtml;

    public ElementVo copyPoValue(ElementPo po){
        this.id = po.getId();
        this.code = po.getCode();
        this.name = po.getName();
        this.desc = po.getDesc();
        this.elementType = po.getElementType();
        this.groupId = po.getGroupId();
        this.showSeq = po.getShowSeq();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getElementType() {
        return elementType;
    }

    public void setElementType(Integer elementType) {
        this.elementType = elementType;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public int getShowSeq() {
        return showSeq;
    }

    public void setShowSeq(int showSeq) {
        this.showSeq = showSeq;
    }

    public ElementHtmlVo getElementHtml() {
        return elementHtml;
    }

    public void setElementHtml(ElementHtmlVo elementHtml) {
        this.elementHtml = elementHtml;
    }
}
