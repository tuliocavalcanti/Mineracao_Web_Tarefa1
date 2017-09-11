package gui;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.queryparser.classic.ParseException;

import facade.Facade;
import test.RelevanceMatrixManager;
import test.ResultMap;

public class RunQueries {

	private static final String query1 = "q1";
	private static final String query2 = "q2";
	
	public static void main(String[] args) throws IOException, ParseException{
		Facade fachada = new Facade();
		
		ResultMap resultMap = RelevanceMatrixManager.getRelevanceMatrix("PATH");

		double q1TotalRelevance = resultMap.getNumberOfRelevantDocsQuery1();
		double q2TotalRelevance = resultMap.getNumberOfRelevantDocsQuery2();
		
		ArrayList<String> q1ff = fachada.searchFiles(query1, false, false);
		ArrayList<String> q1ft = fachada.searchFiles(query1, false, true);
		ArrayList<String> q1tf = fachada.searchFiles(query1, true, false);
		ArrayList<String> q1tt = fachada.searchFiles(query1, true, true);
		
		ArrayList<String> q2ff = fachada.searchFiles(query2, false, false);
		ArrayList<String> q2ft = fachada.searchFiles(query2, false, true);
		ArrayList<String> q2tf = fachada.searchFiles(query2, true, false);
		ArrayList<String> q2tt = fachada.searchFiles(query2, true, true);
		
		double q1ffCovered = 0;
		double q1ftCovered = 0;
		double q1tfCovered = 0;
		double q1ttCovered = 0;
		
		double q2ffCovered = 0;
		double q2ftCovered = 0;
		double q2tfCovered = 0;
		double q2ttCovered = 0;
		
		//QUERY 1
		for(int i = 0; i < q1ff.size(); i++){
			if(resultMap.getMapQuery1().get(q1ff.get(i))){
				q1ffCovered++;
			}
		}

		for(int i = 0; i < q1ft.size(); i++){
			if(resultMap.getMapQuery1().get(q1ft.get(i))){
				q1ftCovered++;
			}
		}
		
		for(int i = 0; i < q1tf.size(); i++){
			if(resultMap.getMapQuery1().get(q1tf.get(i))){
				q1tfCovered++;
			}
		}

		for(int i = 0; i < q1tt.size(); i++){
			if(resultMap.getMapQuery1().get(q1tt.get(i))){
				q1ttCovered++;
			}
		}
		//QUERY 2
		for(int i = 0; i < q2ff.size(); i++){
			if(resultMap.getMapQuery1().get(q2ff.get(i))){
				q2ffCovered++;
			}
		}

		for(int i = 0; i < q2ft.size(); i++){
			if(resultMap.getMapQuery1().get(q2ft.get(i))){
				q2ftCovered++;
			}
		}
		
		for(int i = 0; i < q2tf.size(); i++){
			if(resultMap.getMapQuery1().get(q2tf.get(i))){
				q2tfCovered++;
			}
		}
		for(int i = 0; i < q2tt.size(); i++){
			if(resultMap.getMapQuery1().get(q2tt.get(i))){
				q2ttCovered++;
			}
		}
		//PRECISIONS
		double precisionq1ff = q1ffCovered/q1TotalRelevance;
		double precisionq1ft = q1ftCovered/q1TotalRelevance;
		double precisionq1tf = q1tfCovered/q1TotalRelevance;
		double precisionq1tt = q1ttCovered/q1TotalRelevance;

		double precisionq2ff = q2ffCovered/q2TotalRelevance;
		double precisionq2ft = q2ftCovered/q2TotalRelevance;
		double precisionq2tf = q2tfCovered/q2TotalRelevance;
		double precisionq2tt = q2ttCovered/q2TotalRelevance;

		double precisionResff = (precisionq1ff + precisionq2ff)/2;
		double precisionResft = (precisionq1ft + precisionq2ft)/2;
		double precisionRestf = (precisionq1tf + precisionq2tf)/2;
		double precisionRestt = (precisionq1tt + precisionq2tt)/2;
		
		//COVERAGE
		double coverageq1ff = q1ffCovered/q1ff.size();
		double coverageq1ft = q1ftCovered/q1ft.size();
		double coverageq1tf = q1tfCovered/q1tf.size();
		double coverageq1tt = q1ttCovered/q1tt.size();

		double coverageq2ff = q2ffCovered/q2ff.size();
		double coverageq2ft = q2ftCovered/q2ft.size();
		double coverageq2tf = q2tfCovered/q2tf.size();
		double coverageq2tt = q2ttCovered/q2tt.size();

		double coverageResff = (coverageq1ff + coverageq2ff)/2;
		double coverageResft = (coverageq1ft + coverageq2ft)/2;
		double coverageRestf = (coverageq1tf + coverageq2tf)/2;
		double coverageRestt = (coverageq1tt + coverageq2tt)/2;

		//F-MEASURE
		double fmeasureq1ff = 2*q1ffCovered*precisionq1ff/(q1ffCovered+precisionq1ff);
		double fmeasureq1ft = 2*q1ftCovered*precisionq1ft/(q1ftCovered+precisionq1ft);
		double fmeasureq1tf = 2*q1tfCovered*precisionq1tf/(q1tfCovered+precisionq1tf);
		double fmeasureq1tt = 2*q1ttCovered*precisionq1tt/(q1ttCovered+precisionq1tt);

		double fmeasureq2ff = 2*q2ffCovered*precisionq2ff/(q2ffCovered+precisionq2ff);
		double fmeasureq2ft = 2*q2ftCovered*precisionq2ft/(q2ftCovered+precisionq2ft);
		double fmeasureq2tf = 2*q2tfCovered*precisionq2tf/(q2tfCovered+precisionq2tf);
		double fmeasureq2tt = 2*q2ttCovered*precisionq2tt/(q2ttCovered+precisionq2tt);

		double fmeasureResff = (fmeasureq1ff + fmeasureq2ff)/2;
		double fmeasureResft = (fmeasureq1ft + fmeasureq2ft)/2;
		double fmeasureRestf = (fmeasureq1tf + fmeasureq2tf)/2;
		double fmeasureRestt = (fmeasureq1tt + fmeasureq2tt)/2;
		
		System.out.println("QUERY 1");
		System.out.println("BASE 1: PRES:"+ precisionq1ff + " COV:" + coverageq1ff + "F-MEASURE" + fmeasureq1ff);
		System.out.println("BASE 2: PRES:"+ precisionq1ft + " COV:" + coverageq1ft + "F-MEASURE" + fmeasureq1ft);
		System.out.println("BASE 3: PRES:"+ precisionq1tf + " COV:" + coverageq1tf + "F-MEASURE" + fmeasureq1tf);
		System.out.println("BASE 4: PRES:"+ precisionq1tt + " COV:" + coverageq1tt + "F-MEASURE" + fmeasureq1tt);
		
		System.out.println("QUERY 2");
		System.out.println("BASE 1: PRES:"+ precisionq2ff + " COV:" + coverageq2ff + "F-MEASURE" + fmeasureq2ff);
		System.out.println("BASE 2: PRES:"+ precisionq2ft + " COV:" + coverageq2ft + "F-MEASURE" + fmeasureq2ft);
		System.out.println("BASE 3: PRES:"+ precisionq2tf + " COV:" + coverageq2tf + "F-MEASURE" + fmeasureq2tf);
		System.out.println("BASE 4: PRES:"+ precisionq2tt + " COV:" + coverageq2tt + "F-MEASURE" + fmeasureq2tt);
		
		
		System.out.println("RESULTADO");
		System.out.println("BASE 1: PRES:"+ precisionResff + " COV:" + coverageResff + "F-MEASURE" + fmeasureResff);
		System.out.println("BASE 2: PRES:"+ precisionResft + " COV:" + coverageResft + "F-MEASURE" + fmeasureResft);
		System.out.println("BASE 3: PRES:"+ precisionRestf + " COV:" + coverageRestf + "F-MEASURE" + fmeasureRestf);
		System.out.println("BASE 4: PRES:"+ precisionRestt + " COV:" + coverageRestt + "F-MEASURE" + fmeasureRestt);
		
	}
}
