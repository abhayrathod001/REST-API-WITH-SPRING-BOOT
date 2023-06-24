package util;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.FormulaEvaluator;  
import org.apache.poi.ss.usermodel.Row; 
import java.util.*; 
import com.springrest.springrest.entities.*;






public class ExcelOperation {
	
	
	public static int predictMaximumRun( String city , HashMap<String,ArrayList<Score>> scoreMap  ) {
		
		ArrayList<Score> scoreList = scoreMap.get(city);
		
		if( scoreList == null || scoreList.size() == 0 ) return -1;
		else {
			int ans = -1;
			
			for(Score s : scoreList ) {
//				System.out.println("value = " + s.getFI() + " and " + s.getSI());
				if(s.getFI() > ans) {
					ans = s.getFI();
				}
			}
			
			return ans;
			
		}
		
	}
	
	
public static int predictAverageFirstScore( String city , HashMap<String,ArrayList<Score>> scoreMap  ) {
		
		ArrayList<Score> scoreList = scoreMap.get(city);
		
		if( scoreList == null || scoreList.size() == 0 ) return -1;
		else {
			int ans = 0;
			
			for(Score s : scoreList ) {
//				System.out.println("value = " + s.getFI() + " and " + s.getSI());
				
					ans = ans +  s.getFI();
				
			}
			
			return ans/(scoreList.size());
			
		}
		
	}
	
	
 
	public static void main(String args[]) throws IOException  
	{ 
		ExcelOperation excelOperation = new ExcelOperation();
		HashMap<String,ArrayList<Score>> scoreMap = excelOperation.readExcel();
//		predict("Delhi",scoreMap);
		System.out.println( "the averagre score can be " + predictAverageFirstScore("Mumbai",scoreMap) );
	}
	
	public HashMap<String,ArrayList<Score>> readExcel() {
		HashMap<String,ArrayList<Score>> scoreMap = new HashMap<String,ArrayList<Score>>() ;
		try {
		
	//obtaining input bytes from a file  
	FileInputStream fis=new FileInputStream(new File("D:\\Book2.xls"));  
	//creating workbook instance that refers to .xls file  
	HSSFWorkbook wb=new HSSFWorkbook(fis);   
	//creating a Sheet object to retrieve the object  
	HSSFSheet sheet=wb.getSheetAt(0);  
	//evaluating cell type   
	FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();
	
	
	
	
//	Map<String,ArrayList<Score>> scoreMap;
	
	for(Row row: sheet)     //iteration over row using for each loop  
	{  
//	for(Cell cell: row)    //iteration over cell using for each loop  
//	{  
//	switch(formulaEvaluator.evaluateInCell(cell).getCellType())  
//	{  
//	case Cell.CELL_TYPE_NUMERIC:   //field that represents numeric cell type  
//	//getting the value of the cell as a number  
//	System.out.print(cell.getNumericCellValue()+ "\t\t");   
//	break;  
//	case Cell.CELL_TYPE_STRING:    //field that represents string cell type  
//	//getting the value of the cell as a string  
//	System.out.print(cell.getStringCellValue()+ "\t\t");  
//	break;  
//	}  
//	}  
//	System.out.println(); 
		
		
		
//		System.out.println( row.getCell(0) );
//		
		String ground = row.getCell(0).toString();
		int firstScore = (int) row.getCell(1).getNumericCellValue();
		int secondScore = (int) row.getCell(2).getNumericCellValue();
		
		
//		
		ArrayList<Score> scoreList = scoreMap.get(ground);
		
		Score Match = new Score();
		
		if(scoreList ==null || scoreList.size() == 0) {
			
			scoreList = new ArrayList<Score>();
			
			Match.set(firstScore ,secondScore );
			
			scoreList.add(Match);
			
			scoreMap.put(ground, scoreList);
		
		}
		else {
							
				Match.set(firstScore ,secondScore );
							
			    scoreList.add(Match);
							
			   scoreMap.put(ground, scoreList);
			
		}
		
		
//		System.out.println( ground + " " +  (firstScore+secondScore)  );
	}
	
//	for (Entry<String, ArrayList<Score>> entry : scoreMap.entrySet()) {
//        System.out.println("Key = " + entry.getKey());
//	
//	for(Score s : entry.getValue()) {
//		System.out.println("value = " + s.getFI() + " and " + s.getSI());
//	}
//	}
	
	}catch(Exception e) {
		e.printStackTrace();
	}
		
		return scoreMap;
	}

}
