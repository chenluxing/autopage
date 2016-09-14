package com.lxc.autopage.base.utils;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.*;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/9/8.
 */
public class ExcelUtils {


    public static void main(String[] args){
        ExcelUtils.load();
    }

    public static void load(){
        try {
            File file_src = new File("/usr/local/policy/template/tmp_policy_normal.xls");

            Workbook book_src = Workbook.getWorkbook(file_src);
            Sheet sheet_src = book_src.getSheet(0);

            File file_tag = new File("/usr/local/policy/template/tmp_policy_normal_tag.xls");
            WritableWorkbook book_tag = Workbook.createWorkbook(file_tag, book_src);
            WritableSheet sheet_tag = book_tag.getSheet(0);// 得到一个工作对象
            System.out.println(sheet_tag.getCell(9, 4).getContents());
            List<String> nameList = new ArrayList<>();
            nameList.add("张三");
            nameList.add("李四");
            nameList.add("王武");
            Label label = null;
            WritableCellFeatures ws = null;
            WritableCellFormat cellFormat = new WritableCellFormat();
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
            for (int i = 5; i <= 505; i++){
                ws = new WritableCellFeatures();
                ws.setDataValidationList(nameList);
                label = new Label(8, i, "请选择");
                label.setCellFormat(cellFormat);
                label.setCellFeatures(ws);
                sheet_tag.addCell(label);
            }

            book_tag.write();
            book_tag.close();
            book_src.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void explainExcel(){
        try {

            File file = new File("/usr/local/policy/template/tmp_policy_normal.xls");
            Workbook book = Workbook.getWorkbook(file);
            Sheet sheet = book.getSheet(0);

            for (int i = 0; i < sheet.getRows(); i++){
                Cell[] cells = sheet.getRow(i);
                for (Cell cell : cells){
                    CellType cellType = cell.getType();


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
