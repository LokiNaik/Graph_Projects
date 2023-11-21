package com.datasourceagent.models;

import java.util.Date;

public class TestResults {

	private String waferId;
//	private String mapId;
	private String productId;
	private String waferNumber;
	private Date waferStartTime;
	private Date waferEndTime;
	private String lotId;
	private String productName;
	private String careerId;
	private String deviceId;
	private String originalLotId;
	private String lotNumber;
	private String programName;
	private long lotStartTime;
	private long lotEndTime;
	private String processCode;
	private String probeCardId;
	private String testBoardId;
	private int slotNumber;
	private int testTime;
	private int goodChipCount;
	private double lastYield;
	private double yield;
	private int testFailCount;
	private int testPassCount;
	private int testChipCount;
	private double testChipRatio;
	private double failRatio;
	private String deviceDesignGroup;
	private String deviceGroup;
	private long chipArea;
	private int testNumber;
	private int dutNumber;
	private int binNumber;
	private String testName;
	private boolean testPass;
	private double measurementValue;
	private String measurementUnit;;

//	public String getMapId() {
//		return mapId;
//	}
//
//	public void setMapId(String mapId) {
//		this.mapId = mapId;
//	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getWaferNumber() {
		return waferNumber;
	}

	public void setWaferNumber(String waferNumber) {
		this.waferNumber = waferNumber;
	}

	public Date getWaferStartTime() {
		return waferStartTime;
	}

	public void setWaferStartTime(Date waferStartTime) {
		this.waferStartTime = waferStartTime;
	}

	public Date getWaferEndTime() {
		return waferEndTime;
	}

	public void setWaferEndTime(Date waferEndTime) {
		this.waferEndTime = waferEndTime;
	}

	public String getLotId() {
		return lotId;
	}

	public void setLotId(String lotId) {
		this.lotId = lotId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCareerId() {
		return careerId;
	}

	public void setCareerId(String careerId) {
		this.careerId = careerId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getOriginalLotId() {
		return originalLotId;
	}

	public void setOriginalLotId(String originalLotId) {
		this.originalLotId = originalLotId;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}



	public long getLotStartTime() {
		return lotStartTime;
	}

	public void setLotStartTime(long lotStartTime) {
		this.lotStartTime = lotStartTime;
	}

	public long getLotEndTime() {
		return lotEndTime;
	}

	public void setLotEndTime(long lotEndTime) {
		this.lotEndTime = lotEndTime;
	}

	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	public String getProbeCardId() {
		return probeCardId;
	}

	public void setProbeCardId(String probeCardId) {
		this.probeCardId = probeCardId;
	}

	public String getTestBoardId() {
		return testBoardId;
	}

	public void setTestBoardId(String testBoardId) {
		this.testBoardId = testBoardId;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	public int getTestTime() {
		return testTime;
	}

	public void setTestTime(int testTime) {
		this.testTime = testTime;
	}

	public int getGoodChipCount() {
		return goodChipCount;
	}

	public void setGoodChipCount(int goodChipCount) {
		this.goodChipCount = goodChipCount;
	}

	public double getLastYield() {
		return lastYield;
	}

	public void setLastYield(double lastYield) {
		this.lastYield = lastYield;
	}

	public double getYield() {
		return yield;
	}

	public void setYield(double yield) {
		this.yield = yield;
	}

	public int getTestFailCount() {
		return testFailCount;
	}

	public void setTestFailCount(int testFailCount) {
		this.testFailCount = testFailCount;
	}

	public int getTestPassCount() {
		return testPassCount;
	}

	public void setTestPassCount(int testPassCount) {
		this.testPassCount = testPassCount;
	}

	public int getTestChipCount() {
		return testChipCount;
	}

	public void setTestChipCount(int testChipCount) {
		this.testChipCount = testChipCount;
	}

	public double getTestChipRatio() {
		return testChipRatio;
	}

	public void setTestChipRatio(double testChipRatio) {
		this.testChipRatio = testChipRatio;
	}

	public double getFailRatio() {
		return failRatio;
	}

	public void setFailRatio(double failRatio) {
		this.failRatio = failRatio;
	}

	public String getDeviceDesignGroup() {
		return deviceDesignGroup;
	}

	public void setDeviceDesignGroup(String deviceDesignGroup) {
		this.deviceDesignGroup = deviceDesignGroup;
	}

	public String getDeviceGroup() {
		return deviceGroup;
	}

	public void setDeviceGroup(String deviceGroup) {
		this.deviceGroup = deviceGroup;
	}

	public long getChipArea() {
		return chipArea;
	}

	public void setChipArea(long chipArea) {
		this.chipArea = chipArea;
	}

	public int getTestNumber() {
		return testNumber;
	}

	public void setTestNumber(int testNumber) {
		this.testNumber = testNumber;
	}

	public int getDutNumber() {
		return dutNumber;
	}

	public void setDutNumber(int dutNumber) {
		this.dutNumber = dutNumber;
	}

	public int getBinNumber() {
		return binNumber;
	}

	public void setBinNumber(int binNumber) {
		this.binNumber = binNumber;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public boolean isTestPass() {
		return testPass;
	}

	public void setTestPass(boolean testPass) {
		this.testPass = testPass;
	}

	public double getMeasurementValue() {
		return measurementValue;
	}

	public void setMeasurementValue(double measurementValue) {
		this.measurementValue = measurementValue;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public String getWaferId() {
		return waferId;
	}

	public void setWaferId(String waferId) {
		this.waferId = waferId;
	}

}
