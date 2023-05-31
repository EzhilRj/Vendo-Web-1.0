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

    public static FileInputStream fi;
    public static XSSFWorkbook workbook;
    private static File file;
    public static XSSFSheet sheet;
    public static XSSFRow row;
    public XSSFCell cell;


    public static void LoadExcel() throws IOException {

        System.out.println("Loading Excel data...");
        if (testSuiteName.contains("Regression")) {
            file = new File(RegressionExcelpath);
        } else if (testSuiteName.contains("Negative")) {
            file = new File(NegativeExcelpath);
        } else if (testSuiteName.contains("Smoke")) {
            file = new File(RegressionExcelpath);
        }
        fi = new FileInputStream(file);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet("Login");
        fi.close();

    }

    public static Map<String, Map<String,String>>getDatamap() throws Exception {
        if (sheet == null) {
            LoadExcel();
        }

        Map<String, Map<String,String>> supermap = new HashMap<String, Map<String,String>>();
        int lastRowNum = sheet.getLastRowNum();
        int lastColNum = sheet.getRow(0).getLastCellNum();
        Map<String,String>Mymap = new HashMap<String, String>();
        for(int i = 0; i <lastRowNum ; i++) {
            row = sheet.getRow(i);
            String Keycell  = row.getCell(0).getStringCellValue();

            for (int j = 0; j < lastColNum; j++) {
                String value = row.getCell(j).getStringCellValue();
                Mymap.put(value, sheet.getRow(i + 1).getCell(j).getStringCellValue());
            }

            supermap.put(Keycell, new HashMap<>(Mymap));
            Mymap.clear();
        }
        return supermap;
    }

    public static String Getdata(String key) throws Exception {
        Map<String, Map<String, String>> dataMap = getDatamap();

        for (Map.Entry<String, Map<String, String>> entry : dataMap.entrySet()) {
            Map<String, String> myval = entry.getValue();
            String retvalue = myval.get(key);
            if (retvalue != null) {
                return retvalue;
            }
        }

        return null; // or handle the case when the key is not found
    }
}