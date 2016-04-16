package com.hzjava.monitorcenter.domain;

import java.util.Date;

public class SysTerminalStatusRunReport {
	Long id;
	Date auditDate;
	String accessUrl;
	String busName;
	String cardType;
	Long count;
	Long flux;
	Long idTerminal;
	String policeNumber;
	String userDepart;
	String userId;
	String userZone;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	public String getAccessUrl() {
		return accessUrl;
	}
	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getFlux() {
		return flux;
	}
	public void setFlux(Long flux) {
		this.flux = flux;
	}
	public Long getIdTerminal() {
		return idTerminal;
	}
	public void setIdTerminal(Long idTerminal) {
		this.idTerminal = idTerminal;
	}
	public String getPoliceNumber() {
		return policeNumber;
	}
	public void setPoliceNumber(String policeNumber) {
		this.policeNumber = policeNumber;
	}
	public String getUserDepart() {
		return userDepart;
	}
	public void setUserDepart(String userDepart) {
		this.userDepart = userDepart;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserZone() {
		return userZone;
	}
	public void setUserZone(String userZone) {
		this.userZone = userZone;
	}
	
}
