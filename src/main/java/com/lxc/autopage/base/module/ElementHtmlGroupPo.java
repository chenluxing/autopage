package com.lxc.autopage.base.module;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Created by admin on 2016/8/22.
 */
@Alias("com.lxc.autopage.base.module.ElementHtmlGroupPo")
public class ElementHtmlGroupPo {

    private Integer id;
    private Integer elementId;
    private Integer showSeq;
    private String ehgDesc;
    private Date gmtCreated;
    private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    public Integer getShowSeq() {
        return showSeq;
    }

    public void setShowSeq(Integer showSeq) {
        this.showSeq = showSeq;
    }

    public String getEhgDesc() {
        return ehgDesc;
    }

    public void setEhgDesc(String ehgDesc) {
        this.ehgDesc = ehgDesc;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
