package com.apose.cells.android.demo;

import java.io.InputStream;

import com.aspose.cells.BackgroundType;
import com.aspose.cells.Border;
import com.aspose.cells.BorderCollection;
import com.aspose.cells.BorderType;
import com.aspose.cells.Cell;
import com.aspose.cells.CellBorderType;
import com.aspose.cells.CellValueType;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.Column;
import com.aspose.cells.Font;
import com.aspose.cells.Row;
import com.aspose.cells.Style;
import com.aspose.cells.StyleFlag;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.TextDirectionType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;

public class FormattingOperate 
{
	
	public static void alignmentSetting(InputStream inputStream) 
	{
		try {

			Workbook workbook = new Workbook(inputStream);
			
			WorksheetCollection worksheets = workbook.getWorksheets();
	        Worksheet worksheet = worksheets.get(0);
	
	        Cells cells = worksheet.getCells();
	        Cell cell;
	        Style style;
	
	        //Set style to A1
	        cell = cells.get("A1");
	        style = cell.getStyle();
	        style.setHorizontalAlignment(TextAlignmentType.CENTER);
	        style.setVerticalAlignment(TextAlignmentType.CENTER);
	        cell.setStyle(style);
	
	        //Set style to A2
	        cell = cells.get("A2");
	        style = cell.getStyle();
	        style.setRotationAngle(45);
	        cell.setStyle(style);
	
	        //Set style to C3
	        cell = cells.get("C3");
	        style = cell.getStyle();
	        style.setShrinkToFit(true);
	        cell.setStyle(style);
	
	        //Set style to A4
	        cell = cells.get("A4");
	        style = cell.getStyle();
	        style.setIndentLevel(5);
	        cell.setStyle(style);
	
	        //Set style to A5
	        cell = cells.get("A5");
	        style = cell.getStyle();
	        style.setTextDirection(TextDirectionType.CONTEXT);
	        cell.setStyle(style);
	
	        //Set style to A6
	        cell = cells.get("A6");
	        style = cell.getStyle();
	        style.setTextWrapped(true);
	        cell.setStyle(style);
	        
	        workbook.save(Utils.SAVE_PATH + "Formatting_AlignmentSetting.xls");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void borderSetting() 
	{
		Workbook workbook = new Workbook();
		
		WorksheetCollection worksheets = workbook.getWorksheets();
        Worksheet worksheet = worksheets.get(0);

        Cells cells = worksheet.getCells();
        Cell cell;
        Style style;

        cell = cells.get("B2");
        style = cell.getStyle();
        BorderCollection borders = style.getBorders();
        borders.setStyle(CellBorderType.DASHED);
        borders.setColor(Color.getBlue());
        cell.setStyle(style);
        
        try {
        	workbook.save(Utils.SAVE_PATH + "Formatting_BorderSetting.xls");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void fontSetting() 
	{
		Workbook workbook = new Workbook();
		WorksheetCollection worksheets = workbook.getWorksheets();
        Worksheet worksheet = worksheets.get(0);

        Cells cells = worksheet.getCells();
        Cell cell;
        Style style;

        //Set font to A2
        cell = cells.get("A2");
        cell.setValue("Aspose");
        style = cell.getStyle();
        Font font1 = style.getFont();
        font1.setColor(Color.getRed());
        cell.setStyle(style);

        //Set font to B2
        cell = cells.get("B2");
        cell.setValue("Aspose");
        style = cell.getStyle();
        Font font2 = style.getFont();
        font2.setBold(true);
        cell.setStyle(style);

        //Set font to C2
        cell = cells.get("C2");
        cell.setValue("Aspose");
        style = cell.getStyle();
        Font font3 = style.getFont();
        font3.setItalic(true);
        cell.setStyle(style);
        
        try {
        	workbook.save(Utils.SAVE_PATH + "Formatting_FontSetting.xls");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void formattingRange() 
	{
		Workbook workbook = new Workbook();
        WorksheetCollection worksheets = workbook.getWorksheets();
        Worksheet worksheet = worksheets.get(0);

        Cells cells = worksheet.getCells();
        Cell cell;
        Style style = workbook.createStyle();
        style.setVerticalAlignment(TextAlignmentType.CENTER);
        style.setHorizontalAlignment(TextAlignmentType.CENTER);
        Font font = style.getFont();
        font.setColor(Color.getGreen());
        style.setShrinkToFit(true);

        BorderCollection borders = style.getBorders();
        Border border = borders.getByBorderType(BorderType.BOTTOM_BORDER);
        border.setColor(Color.getRed());
        border.setLineStyle(CellBorderType.DOTTED);

        //Access a row from the Rows collection
        Row row = cells.getRows().get(0);
        StyleFlag flag = new StyleFlag();
        flag.setAll(true);
        row.applyStyle(style, flag);

        //Access a column from the Columns collection
        Column column = cells.getColumns().get(0);
        column.applyStyle(style, flag);
        
        try {
        	workbook.save(Utils.SAVE_PATH + "Formatting_FormattingRange.xls");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void numberFormatting(InputStream inputStream) 
	{
		try {
			Workbook workbook = new Workbook(inputStream);
			WorksheetCollection worksheets = workbook.getWorksheets();
	        Worksheet worksheet = worksheets.get(0);

	        Cell cell;
	        Cells cells = worksheet.getCells();
	        Style style;
	        int number = 0;
	        String temp = "";
	        Double d;

	        for(int i = 1; i <= 36; i++)
	        {
	            cell = cells.get(i, 0);
	            temp = cell.getStringValue();

	            switch (cell.getType())
	            {
	                case CellValueType.IS_NUMERIC:
	                    number = cell.getIntValue();
	                    break;
	            }

	            //Set the display format of numbers and dates
	            cell = cells.get(i, 1);
	            cell.setValue(123.5);
	            style = cell.getStyle();
	            style.setNumber(number);
	            cell.setStyle(style);
	        }
	        
	        workbook.save(Utils.SAVE_PATH + "Formatting_NumberFormatting.xls");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void patternSetting() 
	{
		Workbook workbook = new Workbook();
		
		WorksheetCollection worksheets = workbook.getWorksheets();
        Worksheet worksheet = worksheets.get(0);

        Cells cells = worksheet.getCells();
        Cell cell;
        Style style;

        cell = cells.get("B2");
        style = cell.getStyle();
        style.setPattern(BackgroundType.DIAGONAL_CROSSHATCH);
        style.setBackgroundColor(Color.getBlue());
        cell.setStyle(style);
        
        try{
            workbook.save(Utils.SAVE_PATH + "Formatting_PatternSetting.xls");
			
    		} catch (Exception e) {
    			e.printStackTrace(System.out);
    		}
	}

}
