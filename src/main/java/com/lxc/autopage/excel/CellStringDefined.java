package com.lxc.autopage.excel;

import jxl.Cell;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by chenlx
 * on 2016/9/12.
 */
public class CellStringDefined extends CellBaseDefined implements ICellDefined<String> {

    // excel中的默认值（如果为必填信息，则excel取值不允许等于默认值）
    private String defaultValue;
    // 最大长度
    private int maxLength;
    // 是否需要值转换
    private boolean isConvert = false;
    // 值转换map
    private Map<Object, Object> convertMap;
    // 正则表达校验
    private String reg;

    public CellStringDefined(){}
    public CellStringDefined(int columnIndex, String headName, String propName, boolean isRequired){
        super(columnIndex, headName, propName, isRequired);
    }
    public CellStringDefined(int columnIndex, String headName, String propName, boolean isRequired, String defaultValue, int maxLength, boolean isConvert, Map convertMap, String reg){
        super(columnIndex, headName, propName, isRequired);
        this.defaultValue = defaultValue;
        this.maxLength = maxLength;
        this.isConvert = isConvert;
        this.convertMap = convertMap;
        this.reg = reg;
    }

    /**
     * 获取字符串类型的单元格值
     * @param cell
     * @return
     */
    @Override
    public String getCellValue(Cell cell) throws CellException{
        // 获取去除前后空格的值
        String result = StringUtils.trim(cell.getContents());
        if (StringUtils.isNotEmpty(result)){
            // 如果数据为必填项，则不允许为默认值
            if (isRequired() && StringUtils.isNotEmpty(getDefaultValue()) && getDefaultValue().equals(result)){
                throw new CellException(getHeadName(), CellException.ErrorType.NOT_REG);
            }
            // 进行长度校验，如果设置了最大长度，则需要进行长度校验
            if (getMaxLength() > 0 && result.getBytes().length > getMaxLength()){
                StringBuilder message = new StringBuilder("超出指定长度大小");
                message.append(getMaxLength());
                message.append("位字符（汉字占2位字符）");
                throw new CellException(getHeadName(), message.toString());
            }
            // 正则验证
            if (StringUtils.isNotEmpty(getReg()) && result.matches(getReg())){
                throw new CellException(getHeadName(), CellException.ErrorType.NOT_REG);
            }
            // 数值转换
            if (isConvert()){
                result = MapUtils.getString(getConvertMap(), result);
                // 判断是否是限定值
                if (result == null){
                    throw new CellException(getHeadName(), CellException.ErrorType.NOT_CONVERT);
                }
            }
        } else if(isRequired()) {   // 必填校验
            throw new CellException(getHeadName(), CellException.ErrorType.NOT_NULL);
        }
        return result;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public boolean isConvert() {
        return isConvert;
    }

    public void setIsConvert(boolean isConvert) {
        this.isConvert = isConvert;
    }

    public Map<Object, Object> getConvertMap() {
        return convertMap;
    }

    public void setConvertMap(Map<Object, Object> convertMap) {
        this.convertMap = convertMap;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }
}
