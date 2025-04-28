package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class ExcelFileUtil {
	Workbook wb;
	//create constructor for reading excel file path
	public ExcelFileUtil(String FilePath)throws Throwable
	{
		FileInputStream fi = new FileInputStream(FilePath);
		wb = WorkbookFactory.create(fi);
		}
	//method for row count
	public int rowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}
		//method for getting CellData
		public String setCellDaString(String sheetname,int row,int column)
		{
			String data="";
if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==CellType.NUMERIC)
{
	int celldata= (int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
	data = String.valueOf(celldata);
}
else
{
	data = wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
	}
	return data;
	}
	//method for writing cell data
	public void setCellData(String sheetname,int row,int column,String status,String outPutFile)throws Throwable	
	{
		//get sheet from Wb
		Sheet ws= wb.getSheet(sheetname);
		//get row from sheet
		Row rowNum = ws.getRow(row);
		//create cell in row
		Cell cell= rowNum.createCell(column);
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass"))
		{
			CellStyle Style= wb.createCellStyle();
			Style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			Style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			ws.getRow(row).getCell(column).setCellStyle(Style);
			}
		else if(status.equalsIgnoreCase("fail"))
		{
			CellStyle Style= wb.createCellStyle();
			Style.setFillForegroundColor(IndexedColors.RED.getIndex());
			Style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			ws.getRow(row).getCell(column).setCellStyle(Style);	
		}
		FileOutputStream fo = new FileOutputStream(outPutFile);
		wb.write(fo);
	}
	public String getCellData(String tCSheet, int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	}
	
		
	


