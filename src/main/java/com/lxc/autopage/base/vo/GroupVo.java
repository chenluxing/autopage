package com.lxc.autopage.base.vo;

import com.lxc.autopage.base.module.ElementPo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/8/8.
 */
public class GroupVo implements Serializable {

    private Integer id;
    private String code;
    private String name;
    private String desc;
    private Integer elementType;
    private int showSeq;
    private List<ElementVo> elementVos;

    public GroupVo copyPoValue(ElementPo po){
        this.id = po.getId();
        this.code = po.getCode();
        this.name = po.getName();
        this.desc = po.getDesc();
        this.elementType = po.getElementType();
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

    public int getShowSeq() {
        return showSeq;
    }

    public void setShowSeq(int showSeq) {
        this.showSeq = showSeq;
    }

    public List<ElementVo> getElementVos() {
        return elementVos;
    }

    public void setElementVos(List<ElementVo> elementVos) {
        this.elementVos = elementVos;
    }
}
