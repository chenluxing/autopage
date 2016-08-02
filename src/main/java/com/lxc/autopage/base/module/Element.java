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
@Alias("com.lxc.autopage.base.module.Element")
public class Element implements Serializable {

    private Integer id;
    private String code;
    private String name;
    private String desc;
    private Integer elementType;
    private Integer groupId;
    private int showSeq;
    private ElementHtml elementHtml;

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

    public ElementHtml getElementHtml() {
        return elementHtml;
    }

    public void setElementHtml(ElementHtml elementHtml) {
        this.elementHtml = elementHtml;
    }
}
