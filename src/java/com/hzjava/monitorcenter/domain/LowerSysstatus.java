package com.hzjava.monitorcenter.domain;

import java.util.Date;

/**
 * 下级平台系统运行情况
 * @author xiangqi.java@gmail.com
 * @hibernate.class table="lower_sysstatus"
 */
public class LowerSysstatus {
	/**
	 * @hibernate.id column="Id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;
	
	/**
	 * 系统标识
	 * 
	 * @hibernate.property column="Idsystem" type="java.lang.String"
	 */
	String idSystem;

	/**
	 * 登入用户数
	 * @hibernate.property column="Access" type="java.lang.Long"
	 */
	Long access;
	
	/**
	 * 接入终端数目
	 * @hibernate.property column="Terminalnum" type="java.lang.Long"
	 */
	Long terminalNum;
	
	/**
	 * 流入总流量
	 * @hibernate.property column="Influx" type="java.lang.Long"
	 */
	Long inFlux;
	
	/**
	 * 流出总流量
	 * @hibernate.property column="Outflux" type="java.lang.Long"
	 */
	Long outFlux;
	
	/**
	 * 登入用户人次
	 * @hibernate.property column="Accesssum" type="java.lang.Long"
	 */
	Long accessSum;
	
	/**
	 * 接入应用数目
	 * @hibernate.property column="Bizsum" type="java.lang.Long"
	 */
	Long bizSum;
	
	/**
	 * 开始时间
	 * 
	 * @hibernate.property column="Starttime" type="java.util.Date"
	 */
	Date startTime;
	
	/**
	 * 结束时间
	 * 
	 * @hibernate.property column="Endtime" type="java.util.Date"
	 */
	Date endTime;

	/**
	 * 记录时间
	 * 
	 * @hibernate.property column="writetime" type="java.util.Date"
	 */
	Date writeTime;


	public LowerSysstatus() {
	}


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


	public Long getAccess() {
		return access;
	}


	public void setAccess(Long access) {
		this.access = access;
	}


	public Long getTerminalNum() {
		return terminalNum;
	}


	public void setTerminalNum(Long terminalNum) {
		this.terminalNum = terminalNum;
	}


	public Long getInFlux() {
		return inFlux;
	}


	public void setInFlux(Long inFlux) {
		this.inFlux = inFlux;
	}


	public Long getOutFlux() {
		return outFlux;
	}


	public void setOutFlux(Long outFlux) {
		this.outFlux = outFlux;
	}


	public Long getAccessSum() {
		return accessSum;
	}


	public void setAccessSum(Long accessSum) {
		this.accessSum = accessSum;
	}


	public Long getBizSum() {
		return bizSum;
	}


	public void setBizSum(Long bizSum) {
		this.bizSum = bizSum;
	}


	public Date getStartTime() {
		return startTime;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	public Date getWriteTime() {
		return writeTime;
	}


	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}

}
