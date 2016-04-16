package com.hzjava.monitorcenter.domain;

public class SysAbnormalInf {
	private Long id;
	private String abnormalTypeCode;
	private String connectObjectType;
	private String exceptionIf;
	private String exceptionDesc;
	private String action;
	private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAbnormalTypeCode() {
		return abnormalTypeCode;
	}
	public void setAbnormalTypeCode(String abnormalTypeCode) {
		this.abnormalTypeCode = abnormalTypeCode;
	}
	public String getConnectObjectType() {
		return connectObjectType;
	}
	public void setConnectObjectType(String connectObjectType) {
		this.connectObjectType = connectObjectType;
	}
	public String getExceptionIf() {
		return exceptionIf;
	}
	public void setExceptionIf(String exceptionIf) {
		this.exceptionIf = exceptionIf;
	}
	public String getExceptionDesc() {
		return exceptionDesc;
	}
	public void setExceptionDesc(String exceptionDesc) {
		this.exceptionDesc = exceptionDesc;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
		

}
