package com.lxc.autopage.base.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/8/22.
 */
public class ElementHtmlGroupVo {

    private Integer id;
    private List<ElementHtmlVo> elementHtmlVoList = new ArrayList<>();

    public ElementHtmlGroupVo(){

    }

    public ElementHtmlGroupVo(Integer id){
        this.id = id;
    }

    public void addElementHtmlVo(ElementHtmlVo vo){
        elementHtmlVoList.add(vo);
    }

    public void addElementHtmlVoList(List<ElementHtmlVo> voList){
        elementHtmlVoList.addAll(voList);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ElementHtmlVo> getElementHtmlVoList() {
        return elementHtmlVoList;
    }

    public void setElementHtmlVoList(List<ElementHtmlVo> elementHtmlVoList) {
        this.elementHtmlVoList = elementHtmlVoList;
    }
}
