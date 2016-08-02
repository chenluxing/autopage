package com.lxc.autopage.base.module;

import com.lxc.autopage.base.module.enums.ElementType;
import com.lxc.autopage.base.module.enums.RelationType;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/1.
 */
@Alias("com.lxc.autopage.base.module.ElementHtml")
public class ElementHtml implements Serializable{

    private Integer id;
    private String ehValue;
    private String ehSuffix;
    private Integer elementId;
    private Integer htmlType;
    private Integer relationType;  // 主从类型、主子
    private Integer ehParentId;
    private String desc;
    private List<Element> subElements = new ArrayList<>();
    private List<Element> slaveElements = new ArrayList<>();  // 从属节点

    public void addSubElement(Element element){
        subElements.add(element);
    }

    public void addSubElements(List<Element> elements){
        subElements.addAll(elements);
    }

    public void addSlaveElement(Element element){
        slaveElements.add(element);
    }

    public void addSlaveElements(List<Element> elements){
        slaveElements.addAll(elements);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Element> getSubElements() {
        return subElements;
    }

    public void setSubElements(List<Element> subElements) {
        this.subElements = subElements;
    }

    public List<Element> getSlaveElements() {
        return slaveElements;
    }

    public void setSlaveElements(List<Element> slaveElements) {
        this.slaveElements = slaveElements;
    }
}
