package com.lxc.autopage.excel;

import jxl.Cell;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by chenlx
 * on 2016/9/12.
 */
public class CellIntegerDefined extends CellBaseDefined implements ICellDefined<Integer> {

    public CellIntegerDefined(){}
    public CellIntegerDefined(int columnIndex, String headName, String propName, boolean isRequired){
        super(columnIndex, headName, propName, isRequired);
    }

    /**
     * 获取日期类型的单元格数据
     * @param cell
     * @return
     */
    @Override
    public Integer getCellValue(Cell cell) throws CellException{
        String result = StringUtils.trim(cell.getContents());
        Integer rtnResult = null;
        if (StringUtils.isNotEmpty(result)){
            // 值转换，字符串转Integer
            try {
                rtnResult = Integer.valueOf(result);
            } catch (Exception e) {
            }
            // 如果转换后的结果没有值，但是获取到单元格有值
            if (rtnResult == null && StringUtils.isNotEmpty(result)){
                throw new CellException(getHeadName(), CellException.ErrorType.NOT_REG);
            }
            if (rtnResult == null && isRequired()){
                throw new CellException(getHeadName(), CellException.ErrorType.NOT_CONVERT);
            }
        } else if(isRequired()) {   // 必填校验
            throw new CellException(getHeadName(), CellException.ErrorType.NOT_NULL);
        }
        return rtnResult;
    }

}
