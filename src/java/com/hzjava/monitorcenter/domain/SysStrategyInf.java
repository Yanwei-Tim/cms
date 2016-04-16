package com.hzjava.monitorcenter.domain;

import java.util.Date;

public class SysStrategyInf {
	private Long id;
	private String strategyDesc;
	private String strategyProtocalType;
	private String srcIp;
	private String descIp;
	private String srcPort;
	private String descPort;
	private Date collectTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStrategyDesc() {
		return strategyDesc;
	}
	public void setStrategyDesc(String strategyDesc) {
		this.strategyDesc = strategyDesc;
	}
	public String getStrategyProtocalType() {
		return strategyProtocalType;
	}
	public void setStrategyProtocalType(String strategyProtocalType) {
		this.strategyProtocalType = strategyProtocalType;
	}
	public String getSrcIp() {
		return srcIp;
	}
	public void setSrcIp(String srcIp) {
		this.srcIp = srcIp;
	}
	public String getDescIp() {
		return descIp;
	}
	public void setDescIp(String descIp) {
		this.descIp = descIp;
	}
	public String getSrcPort() {
		return srcPort;
	}
	public void setSrcPort(String srcPort) {
		this.srcPort = srcPort;
	}
	public String getDescPort() {
		return descPort;
	}
	public void setDescPort(String descPort) {
		this.descPort = descPort;
	}
	public Date getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}
	
	

}
