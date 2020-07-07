package com.arcsoft.facetest.util;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <p>
 * Excel工具类
 * </p>
 *
 * @author goinhn
 * @version 0.0.1
 * @date 2020-07-02
 * @since 0.0.1
 */
public class ExcelUtil {

    /**
     * 写文件到excel中
     *
     * @param tablePath
     * @param sheetName
     * @param result
     * @throws IOException
     */
    public static void writeExcel(String tablePath, String sheetName, Map<String, List<String>> result) throws IOException {
        File file = new File(tablePath);

        Workbook workbook = null;
        if (!file.exists()) {
            workbook = new XSSFWorkbook();
        } else {
            FileInputStream fin = new FileInputStream(file);
            // 输入之前的excel表
            workbook = new XSSFWorkbook(fin);
        }

        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }

        Set<String> setRowName = result.keySet();
        int rowNum = 0;
        for (String rowName : setRowName) {
            Row row = sheet.createRow(rowNum++);
            // 设置头的名字
            Cell cell = row.createCell(0);
            cell.setCellValue(rowName);

            // 填充进数据
            List<String> contentRow = result.get(rowName);
            int size = contentRow.size();
            for (int i = 0; i < size; i++) {
                cell = row.createCell(i + 1);
                String value = contentRow.get(i);

                // 判断是数字转换为数字输入
                Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
                Matcher isNum = pattern.matcher(value);
                if (isNum.matches()) {
                    Double number = Double.parseDouble(value);
                    cell.setCellValue(number);
                } else {
                    cell.setCellValue(value);
                }
            }
        }

        FileOutputStream fos = new FileOutputStream(tablePath);
        workbook.write(fos);
    }
}