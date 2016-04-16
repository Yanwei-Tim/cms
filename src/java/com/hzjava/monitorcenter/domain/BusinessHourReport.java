package com.hzjava.monitorcenter.domain;

import java.util.Date;

/**
 * 业务小时报表
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="business_hour_report"
 */
public class BusinessHourReport {
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
	 * 统计时间点
	 * 
	 * @hibernate.property column="report_hour" type="java.lang.Integer"
	 */
	int reportHour;

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
	
	/**
	 * 状态
	 * 
	 * @hibernate.property column="state" type="int"
	 */
	int state = 0;

	public BusinessHourReport() {

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

	public int getReportHour() {
		return reportHour;
	}

	public void setReportHour(int reportHour) {
		this.reportHour = reportHour;
	}

	public double getExtDataFlow() {
		return extDataFlow;
	}

	public void setExtDataFlow(Double extDataFlow) {
		this.extDataFlow = extDataFlow;
	}

	public double getIntDataFlow() {
		return intDataFlow;
	}

	public void setIntDataFlow(Double intDataFlow) {
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setExtDataFlow(double extDataFlow) {
		this.extDataFlow = extDataFlow;
	}

	public void setIntDataFlow(double intDataFlow) {
		this.intDataFlow = intDataFlow;
	}

}
