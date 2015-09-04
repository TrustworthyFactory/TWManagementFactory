package com.thalesgroup.optet.twmanagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Evidence {

	private String evidenceName;
	private String evidenceID;
	
	public String getEvidenceName() {
		return evidenceName;
	}
	
	public String getEvidenceID(){
		return evidenceID;
	}

	private Map<String, Metric> metrics;

	public Evidence(String evidenceName, String evidenceID) {
		super();
		this.evidenceName = evidenceName;
		this.evidenceID = evidenceID;
		metrics = new HashMap<String, Metric>();
	} 
	
	public void addMetric(String name, Metric metric){
		metrics.put(name, metric);
	}
	
	public void removeMetric(String name){
		metrics.remove(name);
	}
	
	public Map<String, Metric> getMetrics(){
		return metrics;
	}
}
