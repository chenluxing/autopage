package com.lxc.autopage.base.module;

import com.lxc.autopage.base.module.enums.ElementType;
import com.lxc.autopage.base.module.enums.RelationType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/1.
 */
public class Element implements Serializable {

    private Integer id;
    private String name;
    private String value;
    private String displayName;
    private ElementType elementType;
    private Integer parentId;
    private int showSeq;
    private List<Element> subElements = new ArrayList<>();

    public void addSubElement(Element element){
        subElements.add(element);
    }

    public void addSubElements(List<Element> elements){
        subElements.addAll(elements);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public ElementType getElementType() {
        return elementType;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public int getShowSeq() {
        return showSeq;
    }

    public void setShowSeq(int showSeq) {
        this.showSeq = showSeq;
    }

    public List<Element> getSubElements() {
        return subElements;
    }

    public void setSubElements(List<Element> subElements) {
        this.subElements = subElements;
    }
}
