package com.apose.cells.android.demo;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.app.Activity;

public class MainActivity extends Activity {
	
	private static final String REMARK_STRTING = "Files save in path: " + Utils.SAVE_PATH;
	private static final String SOURCE_PARENT_PATH = "DemoSource/";
	private String sourcePath = null;
	
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);	
				
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() 
			{
				prepareData();
				mHandler.post(new Runnable() {
					
					@Override
					public void run() 
					{
						show();
					}
				});
			}
		});
		
		thread.start();
	}
	
	void prepareData()
	{
		try 
		{
			//Cells
			System.out.println("Cells-----------");
			sourcePath = SOURCE_PARENT_PATH + "Cells/";
			CellsOperate.adjustingRowsAndColumns();
			
			CellsOperate.groupingRowsAndColumns(getAssets().open(sourcePath + "GroupingRowsAndColumns.xls"));
			CellsOperate.unGroupingRowsAndColumns(getAssets().open(sourcePath + "UnGroupingRowsAndColumns.xls"));
			
			CellsOperate.hidingRowsAndColumns();
			CellsOperate.displayingRowsAndColumns();
			
			CellsOperate.insertRowsAndColumns();
			CellsOperate.deleteRowsAndColumns();
			
			
			//Chart
			System.out.println("Chart-----------");
			sourcePath = SOURCE_PARENT_PATH +"Chart/";
			ChartOperate.areaChart(getAssets().open(sourcePath + "AreaTemplate.xls"));
			
			
			//Data
			System.out.println("Data-----------");
			sourcePath = SOURCE_PARENT_PATH +"Data/";
			DataOperate.addingHyperlinks();
			
			DataOperate.calculateFormula(getAssets().open(sourcePath + "CalculateFormula.xls"));
			
			DataOperate.dataFilter();
			
			DataOperate.dataValidateion();
			
			DataOperate.helloWorld(getAssets().open(sourcePath + "HelloWorld.xls"));
			
			DataOperate.importingData();
			
			DataOperate.namedRanges();
			
			
			DataOperate.setFormula(getAssets().open(sourcePath + "Formula.xls"));
			
			//Formatting
			System.out.println("Formatting-----------");
			sourcePath = SOURCE_PARENT_PATH +"Formatting/";
			FormattingOperate.alignmentSetting(getAssets().open(sourcePath + "AlignmentSetting.xls"));
			
			FormattingOperate.borderSetting();
			
			FormattingOperate.fontSetting();
			
			FormattingOperate.formattingRange();
			
			FormattingOperate.numberFormatting(getAssets().open(sourcePath + "NumberFormatting.xls"));
			
			FormattingOperate.patternSetting();
			
			//PageSetup
			System.out.println("PageSetup-----------");
			sourcePath = SOURCE_PARENT_PATH +"Pagesetup/";
			PageSetupOperate.headersAndFooters(getAssets().open(sourcePath + "book1.xls"));
			
			PageSetupOperate.pageBreaks(getAssets().open(sourcePath + "book1.xls"));
			
			PageSetupOperate.settingMargins(getAssets().open(sourcePath + "book1.xls"));
			
			PageSetupOperate.settingPageOption(getAssets().open(sourcePath + "book1.xls"));
			
			PageSetupOperate.settingPrintOptions(getAssets().open(sourcePath + "book1.xls"));
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
	}
	
	void show()
	{
		setContentView(R.layout.activity_main);
		TextView textView = (TextView)findViewById(R.id.textview);
		textView.setText(REMARK_STRTING);
	}

}
