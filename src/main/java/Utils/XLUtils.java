package Utils;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

import static Base.Setup.testSuiteName;
import static Utils.Constants.NegativeExcelpath;
import static Utils.Constants.RegressionExcelpath;

public class XLUtils {

    public FileInputStream fi;
    public FileOutputStream fo;
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    private static File file;

    public  static List<Map<String, String>> excelData = new ArrayList<>();


    public static List<Map<String, String>> readWriteExcel(String sheetname) throws EncryptedDocumentException, InvalidFormatException, IOException {

        if (testSuiteName.contains("Regression")) {
            file = new File(RegressionExcelpath);
        } else if (testSuiteName.contains("Negative")) {
            file = new File(NegativeExcelpath);
        } else if (testSuiteName.contains("Smoke")) {
            file = new File(RegressionExcelpath);
        }

        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet(sheetname);
        int rowCount = sheet.getLastRowNum();
        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(0);
            Map<String, String> rowData = new HashMap<>();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                String key = row.getCell(j).getStringCellValue().toLowerCase();
                String value = sheet.getRow(i).getCell(j).getStringCellValue();
                rowData.put(key, value);
            }
            excelData.add(rowData);
        }
        return excelData;
    }


}
