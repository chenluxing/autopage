package com.lxc.autopage.excel;

import jxl.Cell;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * Created by chenlx
 * on 2016/9/12.
 */
public class CellBigDecimalDefined extends CellBaseDefined implements ICellDefined<BigDecimal> {

    // 保留小数位数
    private int sacle = 2;
    // 四舍五入
    private int roundingMode = BigDecimal.ROUND_HALF_UP;

    public CellBigDecimalDefined(){}
    public CellBigDecimalDefined(int columnIndex, String headName, String propName, boolean isRequired){
        super(columnIndex, headName, propName, isRequired);
    }
    public CellBigDecimalDefined(int columnIndex, String headName, String propName, boolean isRequired, int sacle, int roundingMode){
        super(columnIndex, headName, propName, isRequired);
        this.sacle = sacle;
        this.roundingMode = roundingMode;
    }

    /**
     * 获取日期类型的单元格数据
     * @param cell
     * @return
     */
    @Override
    public BigDecimal getCellValue(Cell cell) throws CellException{
        String result = StringUtils.trim(cell.getContents());
        BigDecimal rtnResult = BigDecimal.ZERO;
        Double tempResult = null;
        if (StringUtils.isNotEmpty(result)){
            // 值转换，字符串转Integer
            try {
                tempResult = Double.valueOf(result);
            } catch (Exception e) {
            }
            // 如果转换后的结果没有值，但是获取到单元格有值
            if (rtnResult == null && StringUtils.isNotEmpty(result)){
                throw new CellException(getHeadName(), CellException.ErrorType.NOT_REG);
            }
            // 判断是否必填值
            if (tempResult == null && isRequired()){
                throw new CellException(getHeadName(), CellException.ErrorType.NOT_CONVERT);
            }
            // 值转换
            if (tempResult != null){
                rtnResult = BigDecimal.valueOf(tempResult);
            }
        } else if(isRequired()) {   // 必填校验
            throw new CellException(getHeadName(), CellException.ErrorType.NOT_NULL);
        }
        return rtnResult.setScale(sacle, roundingMode);
    }

}
