package com.lxc.autopage.excel;

import jxl.Cell;

/**
 * Created by chenlx
 * on 2016/9/13.
 *
 */
public interface ICellDefined<T> {

    public T getCellValue(Cell cell) throws CellException;

}
