package com.hzjava.monitorcenter.domain;

import java.util.Date;

/**
 * 设备日报表：每日生成
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="equipment_day_report"
 */
public class EquipmentDayReport {
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
	 * 统计时间点/天数
	 * 
	 * @hibernate.property column="report_day" type="java.lang.Integer"
	 */
	int reportDay;

	/**
	 * 统计时间点/月份
	 * 
	 * @hibernate.property column="report_month" type="java.lang.Integer"
	 */
	int reportMonth;

	/**
	 * 统计时间点/年份
	 * 
	 * @hibernate.property column="report_year" type="java.lang.Integer"
	 */
	int reportYear;

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

	public int getReportDay() {
		return reportDay;
	}

	public void setReportDay(int reportDay) {
		this.reportDay = reportDay;
	}

	public int getReportMonth() {
		return reportMonth;
	}

	public void setReportMonth(int reportMonth) {
		this.reportMonth = reportMonth;
	}

	public int getReportYear() {
		return reportYear;
	}

	public void setReportYear(int reportYear) {
		this.reportYear = reportYear;
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
