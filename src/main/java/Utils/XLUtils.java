package Utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class XLUtils {

    public static XSSFSheet sheet;
    public static XSSFCell cell;
    public static int totalrows ;
    public static void ReadExcel(String path, String sheetname) throws IOException, IOException, IOException {

        FileInputStream fi =  new FileInputStream(path);
        XSSFWorkbook wb = new XSSFWorkbook(fi);
        sheet = wb.getSheet(sheetname);
        totalrows = sheet.getLastRowNum();
    }

    public static void Getdatas(){


    }
}
