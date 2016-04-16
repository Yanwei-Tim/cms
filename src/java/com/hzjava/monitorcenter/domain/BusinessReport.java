package com.hzjava.monitorcenter.domain;

import java.util.Date;

/**
 * 业务统计表 说明：与BusinessRegister一一对应，每天凌晨自动生成前一天的数据，数据为合计数据，当天数据加上历史数据之和
 * 
 * 已过期，不用
 * @author collin.code@gmail.com
 * @hibernate.class table="business_report"
 */
public class BusinessReport {
	/**
	 * @hibernate.id column="Id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;

	/**
	 * 统计日期
	 * 
	 * @hibernate.property column="report_date" type="java.util.Date"
	 */
	Date reportDate;

	/**
	 * 业务名
	 * 
	 * @hibernate.property column="business_name" type="java.lang.String"
	 */
	String businessName;

	/**
	 * 记录/请求数
	 * 
	 * @hibernate.property column="record_count" type="java.lang.Long"
	 */
	long recordCount;

	/**
	 * 总流量
	 * 
	 * @hibernate.property column="datavolume" type="java.lang.Float"
	 */
	float dataVolume;

	/**
	 * 报警数
	 * 
	 * @hibernate.property column="alert_count" type="java.lang.Long"
	 */
	long alertCount;

	public BusinessReport() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}

	public float getDataVolume() {
		return dataVolume;
	}

	public void setDataVolume(float dataVolume) {
		this.dataVolume = dataVolume;
	}

	public long getAlertCount() {
		return alertCount;
	}

	public void setAlertCount(long alertCount) {
		this.alertCount = alertCount;
	}
}
