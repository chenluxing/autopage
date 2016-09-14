package com.lxc.autopage.excel;

/**
 * Created by chenlx
 * on 2016/9/13.
 */
public class CellBaseDefined {

    // 列序号
    protected int columnIndex;
    // 表头名
    protected String headName;
    // 属性名
    protected String propName;
    // 是否必填
    protected boolean isRequired = false;

    public CellBaseDefined(){}
    public CellBaseDefined(int columnIndex, String headName, String propName, boolean isRequired){
        this.columnIndex = columnIndex;
        this.headName = headName;
        this.propName = propName;
        this.isRequired = isRequired;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setIsRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }
}
