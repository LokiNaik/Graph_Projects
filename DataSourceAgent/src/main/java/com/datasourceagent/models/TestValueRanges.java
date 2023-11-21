package com.datasourceagent.models;

import java.util.LinkedHashMap;

public class TestValueRanges {
	private String waferId;
	private double minVal;
	private double maxVal;
	private int tn;
	private String tm;
	private LinkedHashMap<String, FrequencyDistribution> range = new LinkedHashMap<>();

	public String getWaferId() {
		return waferId;
	}

	public void setWaferId(String waferId) {
		this.waferId = waferId;
	}

	public int getTn() {
		return tn;
	}

	public void setTn(int tn) {
		this.tn = tn;
	}

	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	public LinkedHashMap<String, FrequencyDistribution> getRange() {
		return range;
	}

	public double getMinVal() {
		return minVal;
	}

	public void setMinVal(double minVal) {
		this.minVal = minVal;
	}

	public double getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(double maxVal) {
		this.maxVal = maxVal;
	}

	public void setRange(LinkedHashMap<String, FrequencyDistribution> range) {
		this.range = range;
	}
}
