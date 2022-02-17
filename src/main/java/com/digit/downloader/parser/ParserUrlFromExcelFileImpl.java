package com.digit.downloader.parser;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class ParserUrlFromExcelFileImpl implements ParserUrlFromFileImpl {

    @Override
    public List<String> parse(String source) throws IOException {

        List<String> urlList = new ArrayList<>();

        // Get excel file
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(source));
        // total list count
        int sheetCount = myExcelBook.getNumberOfSheets();
        // find url in all lists
        for (int k = 0; k < sheetCount; k++) {
            XSSFSheet myExcelSheet = myExcelBook.getSheetAt(k);
            for (int i = 0; i <= myExcelSheet.getLastRowNum(); i++) {
                XSSFRow currentRow = myExcelSheet.getRow(i);
                System.out.println(currentRow);
                if (currentRow == null) {
                    continue;
                }
                for (int j = 0; j <= currentRow.getLastCellNum(); j++) {
                    if (currentRow.getCell(j) == null) {
                        continue;
                    }
                    if (currentRow.getCell(j).getCellType() == CellType.STRING) {
                        String cellValue = currentRow.getCell(j).getStringCellValue();
                        if (cellValue.contains("https://youtu") || cellValue.contains("https://youtube")
                        || cellValue.contains("https://www.youtu")) {
                            int startIndex = currentRow.getCell(j).getStringCellValue().indexOf("https");
                            String url = currentRow.getCell(j).getStringCellValue().substring(startIndex).trim().replace("\u00a0", "");
                            urlList.add(url);
                        }
                    }
                }
            }
        }
        myExcelBook.close();
        return urlList;
    }
}
