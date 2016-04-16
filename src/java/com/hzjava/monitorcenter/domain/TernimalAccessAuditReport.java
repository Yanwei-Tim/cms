package com.hzjava.monitorcenter.domain;

import java.util.Date;


/**
 * 终端用户访问统计表
 * @author qxp
 * @hibernate.class table="ternimal_access_audit_report"
 */
public class TernimalAccessAuditReport {
	/**
	 * @hibernate.id column="Id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;
	
	/**
	 * 安全卡类型
	 * 
	 * @hibernate.property column="cardType" type="java.lang.String"
	 */
	String cardType;
	
	/**
	 * 身份证号
	 * 
	 * @hibernate.property column="userId" type="java.lang.String"
	 */
	String userId;
	
	/**
	 * 所属单位
	 * 
	 * @hibernate.property column="userDepart" type="java.lang.String"
	 */
	String userDepart;
	
	/**
	 * 
	 * 
	 * @hibernate.property column="reportDate" type="java.util.Date"
	 */
	Date reportDate;
	
	/**
	 * 流量
	 * 
	 * @hibernate.property column="flux" type="java.lang.Integer"
	 */
	int flux;
	
	/**
	 * 业务名称
	 * 
	 * @hibernate.property column="busName" type="java.lang.String"
	 */
	String busName;
	
	/**
	 * 请求
	 * 
	 * @hibernate.property column="count" type="java.lang.Integer"
	 */
	int count;

	public TernimalAccessAuditReport() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserDepart() {
		return userDepart;
	}

	public void setUserDepart(String userDepart) {
		this.userDepart = userDepart;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public int getFlux() {
		return flux;
	}

	public void setFlux(int flux) {
		this.flux = flux;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
