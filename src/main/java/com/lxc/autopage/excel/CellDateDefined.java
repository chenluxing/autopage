package com.lxc.autopage.excel;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by chenlx
 * on 2016/9/12.
 */
public class CellDateDefined extends CellBaseDefined implements ICellDefined<Date> {

    // 日期格式化
    private String pattern = DateUtil.DATE_FMT_3;

    public CellDateDefined(){}
    public CellDateDefined(int columnIndex, String headName, String propName, boolean isRequired){
        super(columnIndex, headName, propName, isRequired);
    }
    public CellDateDefined(int columnIndex, String headName, String propName, boolean isRequired, String pattern){
        super(columnIndex, headName, propName, isRequired);
        if (StringUtils.isNotEmpty(pattern)){
            this.pattern = pattern;
        }
    }

    /**
     * 获取日期类型的单元格数据
     * @param cell
     * @return
     */
    @Override
    public Date getCellValue(Cell cell) throws CellException{
        Date date = null;
        CellType cellType = cell.getType();
        // 单元格格式为日期格式
        if (CellType.DATE.equals(cellType)){
            DateCell dateCell = (DateCell)cell;
            date = dateCell.getDate();
        }else{
            // 单元格格式不是日期格式，但是取值为日期
            String content = cell.getContents();
            date = DateUtil.getDateFromString(content, pattern);
            // 输入值不为空，取值为空时，提示输入值错误
            if (StringUtils.isNotEmpty(content) && date == null){
                throw new CellException(getHeadName(), CellException.ErrorType.NOT_REG);
            }
        }
        // 校验是否必填值
        if (date == null && isRequired){
            throw new CellException(getHeadName(), CellException.ErrorType.NOT_NULL);
        }
        return date;
    }

}
