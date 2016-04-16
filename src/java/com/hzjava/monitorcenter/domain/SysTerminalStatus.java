package com.hzjava.monitorcenter.domain;

import java.util.Date;

public class SysTerminalStatus {
	Long id;
	String idSystem;
	Long idTerminal;
	String userId;
	String isOnline;
	String tPrintScreen;
	Date tScreenTime;
	String accessUrl;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdSystem() {
		return idSystem;
	}
	public void setIdSystem(String idSystem) {
		this.idSystem = idSystem;
	}
	public Long getIdTerminal() {
		return idTerminal;
	}
	public void setIdTerminal(Long idTerminal) {
		this.idTerminal = idTerminal;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	public String gettPrintScreen() {
		return tPrintScreen;
	}
	public void settPrintScreen(String tPrintScreen) {
		this.tPrintScreen = tPrintScreen;
	}
	public Date gettScreenTime() {
		return tScreenTime;
	}
	public void settScreenTime(Date tScreenTime) {
		this.tScreenTime = tScreenTime;
	}
	public String getAccessUrl() {
		return accessUrl;
	}
	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}
	
}
