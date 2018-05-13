package com.apose.cells.android.demo;

import java.io.InputStream;
import java.util.Calendar;

import com.aspose.cells.Cell;
import com.aspose.cells.CellArea;
import com.aspose.cells.CellValueType;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.DateTime;
import com.aspose.cells.Font;
import com.aspose.cells.FontUnderlineType;
import com.aspose.cells.OperatorType;
import com.aspose.cells.Range;
import com.aspose.cells.Style;
import com.aspose.cells.Validation;
import com.aspose.cells.ValidationAlertType;
import com.aspose.cells.ValidationCollection;
import com.aspose.cells.ValidationType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;

public class DataOperate 
{
	public static void addingHyperlinks() 
	{
		Workbook workbook = new Workbook();
		
		WorksheetCollection worksheets = workbook.getWorksheets();
        Worksheet worksheet = worksheets.get(0);

        Cells cells = worksheet.getCells();
        Cell cell;
        /* Adding link to a URL */
        //Put a value into a cell
        cell = cells.get("A1");
        cell.setValue("Visit Aspose");
        Style style = cell.getStyle();
        Font font = style.getFont();
        font.setColor(Color.getBlue());
        font.setUnderline(FontUnderlineType.SINGLE);
        cell.setStyle(style);

        //Add a hyperlink to a URL at "A1" cell
        worksheet.getHyperlinks().add("A1", "B1", "http://www.aspose.com", "Hello Aspose", "1");
		
        try {
        	workbook.save(Utils.SAVE_PATH + "Data_AddingHyperlinks.xls");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
        
	}

	public static void calculateFormula(InputStream inputStream) 
	{
		try {
			  Workbook workbook = new Workbook(inputStream);
			
			  WorksheetCollection worksheets = workbook.getWorksheets();
			  Worksheet worksheet = worksheets.get(0);
			  Cells cells = worksheet.getCells();
			  Cell cell;
			  String strFormula;
			
			  for(int i = 7; i <= 131; i++)
			  {
			      cell = cells.get(i, 2);
			      strFormula = cell.getStringValue();
			      cell = cells.get(i, 3);
			      cell.setFormula(strFormula);
			  }
			
			  workbook.calculateFormula();
			  for(int j = 7; j <= 131; j++)
			  {
			      cell = cells.get(j, 3);
			      cells.get(j, 4).setValue(cell.getValue());
			  }
			  cells.get("F8").setSharedFormula("=E8=D8", 125, 1);
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		  
	}
	
	public static void dataFilter() 
	{
		Workbook workbook = new Workbook();
		WorksheetCollection worksheets = workbook.getWorksheets();
        Worksheet worksheet = worksheets.get(0);

        Cells cells = worksheet.getCells();
        Cell cell;
        //Put a value into a cell
        cell = cells.get("A1");
        cell.setValue("Fruit");
        cell = cells.get("B1");
        cell.setValue("Total");
        cell = cells.get("A2");
        cell.setValue("Apple");
        cell = cells.get("B2");
        cell.setValue(1000);
        cell = cells.get("A3");
        cell.setValue("Orange");
        cell = cells.get("B3");
        cell.setValue(2500);
        cell = cells.get("A4");
        cell.setValue("Bananas");
        cell = cells.get("B4");
        cell.setValue(2500);
        cell = cells.get("A5");
        cell.setValue("Pear");
        cell = cells.get("B5");
        cell.setValue(1000);
        cell = cells.get("A6");
        cell.setValue("Grape");
        cell = cells.get("B6");
        cell.setValue(2000);

        cell = cells.get("D1");
        cell.setValue("Count:");
        cell = cells.get("E1");
        cell.setFormula("=SUBTOTAL(2, B1:B6)");

        worksheet.getAutoFilter().setRange("A1:B6");
        
        try {
        	workbook.save(Utils.SAVE_PATH + "Data_DataFilter.xls");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
        
	}
	
	public static void dataValidateion() 
	{
		Workbook wb = new Workbook();
	  	WorksheetCollection worksheets = wb.getWorksheets();
        Worksheet worksheet = worksheets.get(0);
        Cells cells = worksheet.getCells();
        ValidationCollection validations = worksheet.getValidations();

        Cell cell;
        cell = cells.get("A1");
        cell.setValue("Enter Number:");
        cell = cells.get("B1");
        cell.setValue(100);
        //Create a validation object
        int index = validations.add();
        Validation validation = validations.get(index);
        //Set the validation type to whole number
        validation.setType(ValidationType.WHOLE_NUMBER);
        //Set the operator for validation to between
        validation.setOperator(OperatorType.BETWEEN);
        //Set the minimum value for the validation
        validation.setFormula1("0");
        //Set the maximum value for the validation
        validation.setFormula2("10");
        validation.setShowError(true);
        validation./* setAlertType */setAlertStyle(ValidationAlertType.INFORMATION);
        validation.setErrorTitle("Error");
        validation.setErrorMessage("Enter value between 0 to 10");
        validation.setInputMessage("Data Validation using Condition for Numbers");
        validation.setIgnoreBlank(true);
        validation.setShowInput(true);
        validation.setShowError(true);

        //Apply the validation to a range of cells from B1 to B1 using the CellArea structure
        CellArea cellArea = CellArea.createCellArea(0, 1, 0, 1);

        //Add the cell area to Validation
        validation.addArea(cellArea);
        
        try {
        	wb.save(Utils.SAVE_PATH + "Data_DataValidation.xls");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void helloWorld(InputStream inputStream) 
	{
		try {
			Workbook workbook = new Workbook(inputStream);
			WorksheetCollection worksheets = workbook.getWorksheets();
	        Worksheet worksheet = worksheets.get(0);
	        Cells cells = worksheet.getCells();
	        Cell cell;

	        //Put a string value into the cell using its name
	        cell = cells.get("A1");
	        cell.setValue("Cell Value");

	        //put a string value into the cell using its name
	        cell = cells.get("A2");
	        cell.setValue("Hello World");

	        //Put an boolean value into the cell using its name
	        cell = cells.get("A3");
	        cell.setValue(true);

	        //Put an int value into the cell using its name
	        cell = cells.get("A4");
	        cell.setValue(100);

	        //Put an double value into the cell using its name
	        cell = cells.get("A5");
	        cell.setValue(2856.5);

	        //Put an object value into the cell using its name
	        Object obj = "Aspose";
	        cell = cells.get("A6");
	        cell.setValue(obj);

	        //Put an datetime value into the cell
	        Calendar calendar = Calendar.getInstance();
	        cell = cells.get("A7");
	        cell.setValue(new DateTime(calendar));
	        Style style = cell.getStyle();
	        style.setNumber(14);
	        cell.setStyle(style);

	        //Put a string value into the cell using its row and column
	        cell = cells.get(0, 1);
	        cell.setValue("Cell Value Type");
	        int temp;
	        for(int i = 1; i < 7; i++)
	        {
	            temp = cells.get(i, 0)./* getValueType */getType();
	            cell = cells.get(i, 1);
	            switch (temp)
	            {
	                case CellValueType.IS_NULL:
	                    cell.setValue("Null");
	                    break;
	                case CellValueType.IS_BOOL:
	                    cell.setValue("Boolean");
	                    break;
	                case CellValueType.IS_DATE_TIME:
	                    cell.setValue("Calendar");
	                    break;
	                case CellValueType.IS_STRING:
	                    cell.setValue("String");
	                    break;
	                case CellValueType.IS_NUMERIC:
	                    cell.setValue("Double");
	                    break;
	                default:
	                    cell.setValue("String");
	                    break;
	            }
	        }
	        
	        workbook.save(Utils.SAVE_PATH + "Data_HelloWorld.xls");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
	}
	
	
	public static void importingData()
	{
		Workbook workbook = new Workbook();
        WorksheetCollection worksheets = workbook.getWorksheets();
        Worksheet worksheet = worksheets.get(0);
        Cells cells = worksheet.getCells();


        String[] names = new String[]
        {
                "Tom", "John", "kelly"
        };
        cells.importArray(names, 0, 0, true);
        
        try {
			workbook.save(Utils.SAVE_PATH + "Data_ImportingData.xls");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
    }

	public static void namedRanges() 
	{
		Workbook workbook = new Workbook();
		WorksheetCollection worksheets = workbook.getWorksheets();
        Worksheet worksheet = worksheets.get(0);

        Cells cells = worksheet.getCells();
        Cell cell;

        //Create a named range and Set the name of the named range
        Range nameRange = cells.createRange("B1", "E5");
        nameRange.setName("TestRange");
        //Accessing a specific Named Range

        Range mynameRange = worksheets.getRangeByName("TestRange");
        cell = mynameRange.get(0, 0);
        cell.setValue("First");

        //Accessing All Named Ranges in a Worksheet
        Range[] nameRangeList = worksheets.getNamedRanges();
        Range testNameRange;
        for(int i = 0; i < nameRangeList.length; i++)
        {
            testNameRange = nameRangeList[i];
            cell = testNameRange.get(1, 0);
            cell.setValue("Second");
        }
        //Use cells.CreateRange method to extend it
        nameRange = cells.createRange(0, 0, 6, 6);
        //access the address (row and column) of the range
        mynameRange = worksheets.getRangeByName("TestRange");
		
        try {
			workbook.save(Utils.SAVE_PATH + "Data_NamedRanges.xls");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void setFormula(InputStream inputStream) 
	{
		try {
			Workbook workbook = new Workbook(inputStream);
			WorksheetCollection worksheets = workbook.getWorksheets();
		    Worksheet worksheet = worksheets.get(0);

		    Cells cells = worksheet.getCells();
		    Cell cell;

		    String strFormula = "";
		    for(int i = 18; i <= 133; i++)
		    {
		        strFormula = cells.get(i, 2).getStringValue();
		        cell = cells.get(i, 3);
		        //Set a formula of the Cell
		        cell.setFormula(strFormula);
		    }
		    
		    workbook.save(Utils.SAVE_PATH + "Data_SetFormula.xls");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
		
	}

   
}
