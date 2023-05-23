package Utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static Base.Setup.testSuiteName;
import static Utils.Constants.NegativeExcelpath;
import static Utils.Constants.RegressionExcelpath;

public class XLUtils {

    public static void main(String[] args) {
        String excelFilePath = NegativeExcelpath;
        String sheetName = "Login"; // Replace with the actual sheet name

        try (FileInputStream fileInputStream = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet != null) {
                List<List<String>> dataList = readExcelData(sheet);
                System.out.println(dataList.get(1));
            } else {
                System.out.println("Sheet not found: " + sheetName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<List<String>> readExcelData(Sheet sheet) {
        List<List<String>> dataList = new ArrayList<>();

        for (Row row : sheet) {
            List<String> rowData = new ArrayList<>();

            for (Cell cell : row) {
                rowData.add(getStringCellValue(cell));
            }

            dataList.add(rowData);
        }

        return dataList;
    }

    private static String getStringCellValue(Cell cell) {
        String cellValue = "";

        if (cell.getCellType() == CellType.STRING) {
            cellValue = cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            cellValue = String.valueOf(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            cellValue = String.valueOf(cell.getBooleanCellValue());
        } // Handle other cell types if required

        return cellValue;
    }

}
