package com.hzjava.monitorcenter.domain;

import java.util.Date;

/**
 * 设备每小时报表
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="equipment_hour_report"
 */
public class EquipmentHourReport {
	/**
	 * @hibernate.id column="Id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;

	/**
	 * 设备名
	 * 
	 * @hibernate.property column="equ_name" type="java.lang.String"
	 */
	String equName;

	/**
	 * 统计日期
	 * 
	 * @hibernate.property column="report_date" type="java.util.Date"
	 */
	Date reportDate;

	/**
	 * 统计时间点
	 * 
	 * @hibernate.property column="report_time" type="java.lang.Long"
	 */
	long reportTime;

	/**
	 * 故障次数
	 * 
	 * @hibernate.property column="breakdown_count" type="java.lang.Long"
	 */
	long breakdownCount;

	/**
	 * 报警次数
	 * 
	 * @hibernate.property column="alert_count" type="java.lang.Long"
	 */
	long alertCount;
	
	/**
	 * 状态
	 * 
	 * @hibernate.property column="state" type="int"
	 */
	int state = 0;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public EquipmentHourReport() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEquName() {
		return equName;
	}

	public void setEquName(String equName) {
		this.equName = equName;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public long getReportTime() {
		return reportTime;
	}

	public void setReportTime(long reportTime) {
		this.reportTime = reportTime;
	}

	public long getBreakdownCount() {
		return breakdownCount;
	}

	public void setBreakdownCount(long breakdownCount) {
		this.breakdownCount = breakdownCount;
	}

	public long getAlertCount() {
		return alertCount;
	}

	public void setAlertCount(long alertCount) {
		this.alertCount = alertCount;
	}
}
