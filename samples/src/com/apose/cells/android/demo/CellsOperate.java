package com.apose.cells.android.demo;

import java.io.InputStream;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;


public class CellsOperate 
{
	public static void adjustingRowsAndColumns() 
	{
		int fileFormatType = FileFormatType.EXCEL_97_TO_2003;
	    Workbook workbook = new Workbook(fileFormatType);
	    WorksheetCollection worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.get(0);
	
	    Cells cells = worksheet.getCells();
	
	    //Set the height of all row in the worksheet
	    cells.setStandardHeight(20);
	    //Set the width of all columns in the worksheet
	    cells.setStandardWidth(20);
	
	    //Set the width of the first column
	    cells.setColumnWidth(0, 12);
	    cells.setColumnWidth(1, 40);
	    //Setting the height of row
	    cells.setRowHeight(1, 8);
	    
	    try 
	    {
	    	workbook.save(Utils.SAVE_PATH + "Cells_AdjustingRowsAndColumns.xls");
		} 
	    catch (Exception e) 
	    {
			e.printStackTrace(System.out);
		}
	}
	
	public static void groupingRowsAndColumns(InputStream stream) 
	{
		try 
		{
	        Workbook wb = new Workbook(stream);
	        Worksheet worksheet = wb.getWorksheets().get(0);
	        
	        Cells cells = worksheet.getCells();
	        cells.groupRows(0, 9, false);
	        cells.groupColumns(0, 1, false);
	
	        //Set SummaryRowBelow property
	        worksheet.getOutline().SummaryRowBelow = true;
	        //Set SummaryColumnRight property
	        worksheet.getOutline().SummaryColumnRight = true;
	        
	        wb.save(Utils.SAVE_PATH + "Cells_GroupingRowsAndColumns.xls");
		}			
		catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void unGroupingRowsAndColumns(InputStream stream) 
	{
		try 
		{
			Workbook wb = new Workbook(stream);
		    Worksheet worksheet = wb.getWorksheets().get(0);
		    
	        Cells cells = worksheet.getCells();
	        cells.ungroupRows(0, 9);
	        cells.ungroupColumns(0, 1);
	        
	        wb.save(Utils.SAVE_PATH + "Cells_UnGroupingRowsAndColumns.xls");
		}
		catch(Exception e)
		{
			e.printStackTrace(System.out);
		}
	}
	
	public static void hidingRowsAndColumns()
    {
        int fileFormatType = FileFormatType.EXCEL_97_TO_2003;
        Workbook wb = new Workbook(fileFormatType);
        Worksheet worksheet = wb.getWorksheets().get(0);
        
        //Hide the 3rd row of the worksheet
        worksheet.getCells().hideRow(2);

        //Hide the 2nd column of the worksheet
        worksheet.getCells().hideColumn(1);
        
        try {
        	wb.save(Utils.SAVE_PATH + "Cells_HideRowsAndColumns.xls");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
        
    }
	
	public static void displayingRowsAndColumns()
    {
        int fileFormatType = FileFormatType.EXCEL_97_TO_2003;
        Workbook wb = new Workbook(fileFormatType);
        Worksheet worksheet = wb.getWorksheets().get(0);
        
        //Hide the 3rd row of the worksheet
        worksheet.getCells().unhideRow(2, 13.5);
        //Hide the 2nd column of the worksheet
        worksheet.getCells().unhideColumn(1, 15);
        
        try {
        	wb.save(Utils.SAVE_PATH + "Cells_DisplayRowsAndColumns.xls");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
        
    }

    public static void insertRowsAndColumns()
    {
        int fileFormatType = FileFormatType.EXCEL_97_TO_2003;

        Workbook wb = new Workbook(fileFormatType);
        Cells cells = wb.getWorksheets().get(0).getCells();
        
        createStaticReport(cells);

        //Insert a row or column into the worksheet
        cells.insertRows(2, 10);
        cells.insertColumns(1, 1);
        
        try {
        	wb.save(Utils.SAVE_PATH + "Cells_InsertRowsAndColumns.xls");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
        
    }

    public static void deleteRowsAndColumns()
    {
    	 int fileFormatType = FileFormatType.EXCEL_97_TO_2003;

         Workbook wb = new Workbook(fileFormatType);
         Cells cells = wb.getWorksheets().get(0).getCells();
         
        createStaticReport(cells);

        //Delete a row or column into the worksheet
        cells.deleteRows(2, 2, false);
        cells.deleteColumns(1, 1, false);
        
        try {
        	 wb.save(Utils.SAVE_PATH + "Cells_DeleteRowsAndColumns.xls");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
       
    }

    private static void createStaticReport(Cells cells)
    {
        Cell cell;

        //Put a value into a cell
        cell = cells.get("A1");
        cell.setValue("Aspose");
        cell = cells.get("A2");
        cell.setValue(123);
        cell = cells.get("A3");
        cell.setValue("Hello World");
        cell = cells.get("B1");
        cell.setValue(120);
    }
	
}
