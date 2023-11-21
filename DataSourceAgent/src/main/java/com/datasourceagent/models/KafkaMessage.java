package com.datasourceagent.models;

import java.util.Date;

public class KafkaMessage {

	private int count;
	private String productId;
	private String productName;
	private String testBoardId;
	private String lotId;
	private String orginalLotId;
	private String lotNumber;
	private String deviceId;
	private String processCode;
	private String probeCardId;
	private String careerId;
	private String programName;
	private long lotStartTime;
	private long lotEndTime;
	private int chipArea;
	private String deviceDesignGroup;
	private String deviceGroup;

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

	public int getChipArea() {
		return chipArea;
	}

	public void setChipArea(int chipArea) {
		this.chipArea = chipArea;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTestBoardId() {
		return testBoardId;
	}

	public void setTestBoardId(String testBoardId) {
		this.testBoardId = testBoardId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	// public String getMapId() {
	// 	return mapId;
	// }

	// public void setMapId(String mapId) {
	// 	this.mapId = mapId;
	// }

	public String getLotId() {
		return lotId;
	}

	public void setLotId(String lotId) {
		this.lotId = lotId;
	}

	public String getOrginalLotId() {
		return orginalLotId;
	}

	public void setOrginalLotId(String orginalLotId) {
		this.orginalLotId = orginalLotId;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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

	public String getCareerId() {
		return careerId;
	}

	public void setCareerId(String careerId) {
		this.careerId = careerId;
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

}
