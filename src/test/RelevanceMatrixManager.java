package test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class RelevanceMatrixManager{
	
	//"data/matrix.xlsx"
    public static ResultMap getRelevanceMatrix(String matrixPath) throws IOException {
    	
    	Map<String,Boolean> mapQuery1 = new HashMap<String, Boolean>();
    	Map<String,Boolean> mapQuery2 = new HashMap<String, Boolean>();

        FileInputStream inputStream = new FileInputStream(new File(matrixPath));
        
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        
        Row docNamesRow = CellUtil.getRow(0, firstSheet);
        Row query1Row = CellUtil.getRow(1, firstSheet);
        Row query2Row = CellUtil.getRow(2, firstSheet);
        
        Iterator<Cell> docsNamesIterator = docNamesRow.cellIterator();
        Iterator<Cell> query1Iterator = query1Row.cellIterator();
        Iterator<Cell> query2Iterator = query2Row.cellIterator();
        
        //skips the first column
        docsNamesIterator.next();
        query1Iterator.next();
        query2Iterator.next(); 
        
        String key;
        Boolean val1;
        Boolean val2;
        
        while(docsNamesIterator.hasNext()) {
        	key = docsNamesIterator.next().getStringCellValue();
        	val1 = query1Iterator.next().getNumericCellValue() == 1 ? true : false;
        	val2 = query2Iterator.next().getNumericCellValue() == 1 ? true : false;
        	
        	mapQuery1.put(key, val1);
        	mapQuery2.put(key, val2);
        }
         
        workbook.close();
        inputStream.close();
        
        return new ResultMap(mapQuery1, mapQuery2);
    }
}