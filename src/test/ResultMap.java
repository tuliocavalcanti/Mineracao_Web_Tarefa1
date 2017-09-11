package test;

import java.util.HashMap;
import java.util.Map;

public class ResultMap {

	private Map<String,Boolean> mapQuery1 = new HashMap<String, Boolean>();
	private Map<String,Boolean> mapQuery2 = new HashMap<String, Boolean>();
	
	private int numberOfRelevantDocsQuery1;
	private int numberOfRelevantDocsQuery2;
	
	public ResultMap(Map<String, Boolean> mapQuery1, Map<String, Boolean> mapQuery2, int numberOfRelevantDocsQuery1,
			int numberOfRelevantDocsQuery2) {
		this.mapQuery1 = mapQuery1;
		this.mapQuery2 = mapQuery2;
		this.numberOfRelevantDocsQuery1 = numberOfRelevantDocsQuery1;
		this.numberOfRelevantDocsQuery2 = numberOfRelevantDocsQuery2;
	}

	public int getNumberOfRelevantDocsQuery1() {
		return numberOfRelevantDocsQuery1;
	}

	public void setNumberOfRelevantDocsQuery1(int numberOfRelevantDocsQuery1) {
		this.numberOfRelevantDocsQuery1 = numberOfRelevantDocsQuery1;
	}

	public int getNumberOfRelevantDocsQuery2() {
		return numberOfRelevantDocsQuery2;
	}

	public void setNumberOfRelevantDocsQuery2(int numberOfRelevantDocsQuery2) {
		this.numberOfRelevantDocsQuery2 = numberOfRelevantDocsQuery2;
	}

	public ResultMap(Map<String, Boolean> mapQuery1, Map<String, Boolean> mapQuery2) {
		this.mapQuery1 = mapQuery1;
		this.mapQuery2 = mapQuery2;
	}
	
	public Map<String, Boolean> getMapQuery1() {
		return mapQuery1;
	}
	public void setMapQuery1(Map<String, Boolean> mapQuery1) {
		this.mapQuery1 = mapQuery1;
	}
	public Map<String, Boolean> getMapQuery2() {
		return mapQuery2;
	}
	public void setMapQuery2(Map<String, Boolean> mapQuery2) {
		this.mapQuery2 = mapQuery2;
	}	
}
