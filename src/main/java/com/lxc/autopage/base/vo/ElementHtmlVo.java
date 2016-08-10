package com.lxc.autopage.base.vo;

import com.lxc.autopage.base.module.ElementHtmlPo;
import com.lxc.autopage.base.module.enums.RelationType;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 2016/8/1.
 */
public class ElementHtmlVo implements Serializable{

    private Integer id;
    private String code;
    private String ehValue;
    private String ehSuffix;
    private Integer elementId;
    private Integer htmlType;
    private RelationType relationType;  // 主从类型、主子
    private Integer ehParentId;
    private String desc;
    private List<ElementHtmlVo> subElements = new ArrayList<>();
    private List<ElementHtmlVo> slaveElements = new ArrayList<>();  // 从属节点

    public ElementHtmlVo copyPoValue(ElementHtmlPo po){
        this.id = po.getId();
        this.code = po.getEhCode();
        this.ehValue = po.getEhValue();
        this.ehSuffix = po.getEhSuffix();
        this.elementId = po.getElementId();
        this.htmlType = po.getHtmlType();
        this.relationType = RelationType.get(po.getRelationType());
        this.ehParentId = po.getEhParentId();
        this.desc = po.getEhDesc();
        return this;
    }

    public void addSubElement(ElementHtmlVo element){
        subElements.add(element);
    }

    public void addSubElements(List<ElementHtmlVo> elements){
        subElements.addAll(elements);
    }

    public void addSlaveElement(ElementHtmlVo element){
        slaveElements.add(element);
    }

    public void addSlaveElements(Collection<ElementHtmlVo> elements){
        slaveElements.addAll(elements);
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

    public RelationType getRelationType() {
        return relationType;
    }

    public void setRelationType(RelationType relationType) {
        this.relationType = relationType;
    }

    public Integer getEhParentId() {
        return ehParentId;
    }

    public void setEhParentId(Integer ehParentId) {
        this.ehParentId = ehParentId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<ElementHtmlVo> getSubElements() {
        return subElements;
    }

    public void setSubElements(List<ElementHtmlVo> subElements) {
        this.subElements = subElements;
    }

    public List<ElementHtmlVo> getSlaveElements() {
        return slaveElements;
    }

    public void setSlaveElements(List<ElementHtmlVo> slaveElements) {
        this.slaveElements = slaveElements;
    }
}
