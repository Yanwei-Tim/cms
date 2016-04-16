package com.hzjava.monitorcenter.domain;

import java.util.Date;

/**
 * 业务日报表
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="business_day_report"
 */
public class BusinessDayReport {
	/**
	 * 唯一ID
	 * 
	 * @hibernate.id column="Id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;

	/**
	 * 业务名
	 * 
	 * @hibernate.property column="business_name" type="java.lang.String"
	 */
	String businessName;

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
	 * 外网流量
	 * 
	 * @hibernate.property column="ext_dataflow" type="java.lang.Double"
	 */
	double extDataFlow;

	/**
	 * 内网流量
	 * 
	 * @hibernate.property column="int_dataflow" type="java.lang.Double"
	 */
	double intDataFlow;

	/**
	 * 外网记录/请求数
	 * 
	 * @hibernate.property column="ext_record_count" type="java.lang.Long"
	 */
	long extRecordCount;

	/**
	 * 内网记录/请求数
	 * 
	 * @hibernate.property column="int_record_count" type="java.lang.Long"
	 */
	long intRecordCount;

	/**
	 * 报警数
	 * 
	 * @hibernate.property column="alert_count" type="java.lang.Long"
	 */
	long alertCount;

	public BusinessDayReport() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
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

	public double getExtDataFlow() {
		return extDataFlow;
	}

	public void setExtDataFlow(double extDataFlow) {
		this.extDataFlow = extDataFlow;
	}

	public double getIntDataFlow() {
		return intDataFlow;
	}

	public void setIntDataFlow(double intDataFlow) {
		this.intDataFlow = intDataFlow;
	}

	public long getExtRecordCount() {
		return extRecordCount;
	}

	public void setExtRecordCount(long extRecordCount) {
		this.extRecordCount = extRecordCount;
	}

	public long getIntRecordCount() {
		return intRecordCount;
	}

	public void setIntRecordCount(long intRecordCount) {
		this.intRecordCount = intRecordCount;
	}

	public long getAlertCount() {
		return alertCount;
	}

	public void setAlertCount(long alertCount) {
		this.alertCount = alertCount;
	}

}
