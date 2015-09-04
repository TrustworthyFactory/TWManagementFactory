/*
 *		OPTET Factory
 *
 *	Class Metric 1.0 25 oct. 2013
 *
 *	Copyright (c) 2013 Thales Communications & Security SAS
 *	4, Avenue des Louvresses - 92230 Gennevilliers 
 *	All rights reserved
 *
 */

package com.thalesgroup.optet.twmanagement;

/**
 * @author F. Motte
 *
 */
public class Metric {
	private String metricName;
	private String metricID;
	private String metricExpectedValue;
	public String getMetricName() {
		return metricName;
	}
	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}
	public String getMetricID() {
		return metricID;
	}
	public void setMetricID(String metricID) {
		this.metricID = metricID;
	}
	public String getMetricExpectedValue() {
		return metricExpectedValue;
	}
	public void setMetricExpectedValue(String metricExpectedValue) {
		this.metricExpectedValue = metricExpectedValue;
	}
	
}
