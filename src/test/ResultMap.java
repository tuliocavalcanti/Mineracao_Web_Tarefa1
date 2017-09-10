package test;

import java.util.HashMap;
import java.util.Map;

public class ResultMap {

	private Map<String,Boolean> mapQuery1 = new HashMap<String, Boolean>();
	private Map<String,Boolean> mapQuery2 = new HashMap<String, Boolean>();
	
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
