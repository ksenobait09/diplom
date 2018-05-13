package com.apose.cells.android.demo;

import java.io.InputStream;

import com.aspose.cells.PageOrientationType;
import com.aspose.cells.PageSetup;
import com.aspose.cells.PaperSizeType;
import com.aspose.cells.PrintCommentsType;
import com.aspose.cells.PrintOrderType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.WorksheetCollection;

public class PageSetupOperate
{
	
	public static void headersAndFooters(InputStream inputStream) 
	{
		try {
			Workbook wb = new Workbook(inputStream);
	        WorksheetCollection worksheets = wb.getWorksheets();
	        Worksheet worksheet = worksheets.get(0);

	        PageSetup pageSetup = worksheet.getPageSetup();
	        pageSetup.setFooter(0, "&P");
	        pageSetup.setHeader(1, "&D");
	        
	        wb.save(Utils.SAVE_PATH + "Pagesetup_HeadersAndFooters.xls");
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void pageBreaks(InputStream inputStream) 
	{
		try {
			
			Workbook workbook = new Workbook(inputStream);
	        WorksheetCollection worksheets = workbook.getWorksheets();
	        Worksheet worksheet = worksheets.get(0);
	        //Add page break
	        worksheet.addPageBreaks("B2");

	        //or
	        worksheet.getHorizontalPageBreaks().add("B12");
	        worksheet.getVerticalPageBreaks().add("B12");
	        
	        workbook.save(Utils.SAVE_PATH + "Pagesetup_PageBreaks.xls");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void settingMargins(InputStream inputStream) 
	{
		try {
			
			Workbook workbook = new Workbook(inputStream);
			WorksheetCollection worksheets = workbook.getWorksheets();
		    Worksheet worksheet = worksheets.get(0);
		
		    PageSetup pageSetup = worksheet.getPageSetup();
		    pageSetup.setBottomMargin(5);
		    pageSetup.setFooterMargin(5);
		    pageSetup.setHeaderMargin(5);
		    pageSetup.setLeftMargin(5);
		    pageSetup.setRightMargin(5);
		    pageSetup.setTopMargin(5);
	        workbook.save(Utils.SAVE_PATH + "Pagesetup_SettingMargins.xls");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void settingPageOption(InputStream inputStream) 
	{
		try {
			
			Workbook workbook = new Workbook(inputStream);
			WorksheetCollection worksheets = workbook.getWorksheets();
	        Worksheet worksheet = worksheets.get(0);

	        PageSetup pageSetup = worksheet.getPageSetup();
	        //Set the orientation
	        pageSetup.setOrientation(PageOrientationType.LANDSCAPE);
	        //You can either choose FitToPages or Zoom property but not both at the same time
	        pageSetup.setZoom(80);
	        //Set the paper size
	        pageSetup.setPaperSize(PaperSizeType.PAPER_A_4);
	        //Set the print quality of the worksheet
	        pageSetup.setPrintQuality(200);
	        //Set the first page number of the worksheet pages
	        pageSetup.setFirstPageNumber(1);
	        
	        workbook.save(Utils.SAVE_PATH + "Pagesetup_SettingPageOption.xls");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	public static void settingPrintOptions(InputStream inputStream) 
	{
		try {
			
			Workbook workbook = new Workbook(inputStream);
			WorksheetCollection worksheets = workbook.getWorksheets();
	        Worksheet worksheet = worksheets.get(0);

	        PageSetup pageSetup = worksheet.getPageSetup();
	        pageSetup.setPrintArea("A1:G5");
	        pageSetup.setPrintTitleColumns("$A:$B");
	        pageSetup.setPrintTitleRows("$1:$2");
	        //Allow to print gridlines
	        pageSetup.setPrintGridlines(true);
	        //Allow to print row/column headings
	        pageSetup.setPrintHeadings(true);
	        //Allow to print worksheet in black & white mode
	        pageSetup.setBlackAndWhite(true);
	        //Allow to print comments as displayed on worksheet
	        pageSetup.setPrintComments(PrintCommentsType.PRINT_IN_PLACE);
	        //Allow to print worksheet with draft quality
	        pageSetup.setPrintDraft(true);
	        //Allow to print cell errors
	        pageSetup.setOrder(PrintOrderType.DOWN_THEN_OVER);
	        
	        workbook.save(Utils.SAVE_PATH + "Pagesetup_SettingPrintOptions.xls");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}
	
	
        
}
