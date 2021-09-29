package com.neu.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.util.List;
import java.util.Map;

public class ExcelUtil {


    public static Workbook writer(String [] head, List<Map<String,String>> data,String sheetName){
        Workbook workbook = createDocument();
/*
        Sheet sheetxx =  workbook.getSheetAt(0);

        //总行
        sheetxx.getLastRowNum();


      Row rxx =   sheetxx.getRow(0);

        rxx.getLastCellNum();

        Cell celxx = rxx.getCell(0);

        celxx.getStringCellValue();*/





        Sheet sheet = createSheet(workbook,sheetName);

        Row row = sheet.createRow(0);

        Cell nameCell = row.createCell(0);

        //插入数据
        CellStyle style = workbook.createCellStyle();
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setVerticalAlignment(style.getVerticalAlignment().CENTER);

        nameCell.setCellStyle(style);

        nameCell.setCellValue(head[0]);

        Cell loginNameCell = row.createCell(1);
        loginNameCell.setCellValue(head[1]);



        for(int i=0;i<data.size();i++){

            row = sheet.createRow(i+1);

            nameCell = row.createCell(0);
            nameCell.setCellValue(data.get(i).get("name"));

            loginNameCell = row.createCell(1);
            loginNameCell.setCellValue(data.get(i).get("loginName"));
        }


        return workbook;
    }

    /**
     * 创建Excel文档对象
     * @return
     * @throws Exception
     */
    public static Workbook createDocument()  {
       return new HSSFWorkbook();
    }

    /**
     * 创建工作簿
     * @param workbook
     * @param name
     */
    private static Sheet createSheet(Workbook workbook,String name){
        return workbook.createSheet(name);
    }

}
