package com.hzjava.monitorcenter.domain;

public class TerminalAccessUrl {
	Long id;
	Long idTerminal;
	String busName;
	String accessUrl;
	String resourceRegx;
	String action;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
		
	public Long getIdTerminal() {
		return idTerminal;
	}
	public void setIdTerminal(Long idTerminal) {
		this.idTerminal = idTerminal;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getAccessUrl() {
		return accessUrl;
	}
	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}
	public String getResourceRegx() {
		return resourceRegx;
	}
	public void setResourceRegx(String resourceRegx) {
		this.resourceRegx = resourceRegx;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
