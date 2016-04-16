package com.hzjava.monitorcenter.domain;

import java.util.Date;

/**
 * 下级系统状态
 * @author xiangqi.java@gmail.com
 * @hibernate.class table="lower_sysruntime"
 */
public class LowerSysruntime {
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
	 * 运行状态
	 * 正常；中断；故障；维护；升级
	 * @hibernate.property column="Runstatecode" type="java.lang.String"
	 */
	String runStateCode;

	/**
	 * 状态说明
	 * 如果状态为非正常，描述其原因和具体情况
	 * @hibernate.property column="Desc" type="java.lang.String"
	 */
	String desc;

	/**
	 * 状态开始时间
	 * 
	 * @hibernate.property column="Starttime" type="java.util.Date"
	 */
	Date startTime;
	
	/**
	 * 状态结束时间
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


	public LowerSysruntime() {
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


	public String getRunStateCode() {
		return runStateCode;
	}


	public void setRunStateCode(String runStateCode) {
		this.runStateCode = runStateCode;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
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
