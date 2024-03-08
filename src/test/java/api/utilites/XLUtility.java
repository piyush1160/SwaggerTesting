package api.utilites;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class XLUtility {

    public FileInputStream fi ;
    public FileInputStream fo ;
    public XSSFWorkbook workbook;

    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;


    String path;
    public XLUtility(String path){
        this.path = path ;
    }

    public int getRowCount(String sheetName) throws IOException{

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowCount;
    }

    public int getCellCount(String sheetName , int rownum) throws IOException{

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellCount;
    }

    public String getCellData(String sheetName ,int collnum,  int rownum) throws IOException{

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
       cell = row.getCell(collnum);

        DataFormatter formatter = new DataFormatter();
        String data;

        try{
     // return the formatted value of a cell as a String
            data = formatter.formatCellValue(cell);
        }catch(Exception e){
            data ="";
        }


        workbook.close();
        fi.close();
        return data;
    }

}
