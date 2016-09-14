package com.lxc.autopage.excel;

import jxl.Cell;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by chenlx
 * on 2016/9/12.
 */
public class CellBooleanDefined extends CellBaseDefined implements ICellDefined<Boolean> {

    // 日期格式化
    private boolean isConvert = false;
    // 值转换map
    private Map<Object, Object> convertMap;

    public CellBooleanDefined(){}
    public CellBooleanDefined(int columnIndex, String headName, String propName, boolean isRequired){
        super(columnIndex, headName, propName, isRequired);
    }
    public CellBooleanDefined(int columnIndex, String headName, String propName, boolean isRequired, boolean isConvert, Map convertMap){
        super(columnIndex, headName, propName, isRequired);
        this.isConvert = isConvert;
        this.convertMap = convertMap;
    }

    /**
     * 获取日期类型的单元格数据
     * @param cell
     * @return
     */
    @Override
    public Boolean getCellValue(Cell cell) throws CellException{
        String result = StringUtils.trim(cell.getContents());
        Boolean rtnResult = null;
        if (StringUtils.isNotEmpty(result)){
            // 数值转换
            if (isConvert()){
                rtnResult = MapUtils.getBoolean(getConvertMap(), result);
            } else {
                try {
                    Boolean.valueOf(result);
                } catch (Exception e){
                }
                // 如果转换后的结果没有值，但是获取到单元格有值
                if (rtnResult == null && StringUtils.isNotEmpty(result)){
                    throw new CellException(getHeadName(), CellException.ErrorType.NOT_REG);
                }
            }
        } else if(isRequired()) {   // 必填校验
            throw new CellException(getHeadName(), CellException.ErrorType.NOT_NULL);
        }
        // 判断是否是限定值
        if (rtnResult == null && isRequired()){
            throw new CellException(getHeadName(), CellException.ErrorType.NOT_CONVERT);
        }
        return rtnResult;
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
}
