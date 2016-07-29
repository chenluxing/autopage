package com.lxc.autopage.base.module;

import org.apache.ibatis.type.Alias;

import java.security.Timestamp;

/**
 * Created by admin on 2016/7/29.
 */
@Alias("com.lxc.autopage.base.module.Attachment")
public class Attachment {

    private Integer id;
    private String fileName;
    private String filePath;
    private Integer attType;
    private Integer sourceId;
    private Integer showSeq;
    private Integer createMemberId;
    private int delFlag;
    private Timestamp gmtCreated;
    private Timestamp gmtModify;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getAttType() {
        return attType;
    }

    public void setAttType(Integer attType) {
        this.attType = attType;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getShowSeq() {
        return showSeq;
    }

    public void setShowSeq(Integer showSeq) {
        this.showSeq = showSeq;
    }

    public Integer getCreateMemberId() {
        return createMemberId;
    }

    public void setCreateMemberId(Integer createMemberId) {
        this.createMemberId = createMemberId;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public Timestamp getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Timestamp gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Timestamp getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Timestamp gmtModify) {
        this.gmtModify = gmtModify;
    }
}
