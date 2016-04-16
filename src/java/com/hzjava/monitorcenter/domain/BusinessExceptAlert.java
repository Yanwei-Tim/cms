package com.hzjava.monitorcenter.domain;

import java.util.Date;

/**
 * 平台异常事件报警
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="business_except_alert"
 */
public class BusinessExceptAlert {
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
	 * 报警时间
	 * 
	 * @hibernate.property column="alert_time" type="java.util.Date"
	 */
	Date alertTime;

	/**
	 * 报警类型
	 * 
	 * @hibernate.property column="except_code" type="java.lang.String"
	 */
	String exceptCode;
	/**
	 * @hibernate.many-to-one class="com.hzjava.monitorcenter.domain.BusinessExceptCode"
	 *                        column="except_code" insert="false" update="false"
	 */
	BusinessExceptCode exceptCodeObject;

	/**
	 * 报警内容
	 * 
	 * @hibernate.property column="alert_info" type="java.lang.String"
	 */
	String alertInfo;

	/**
	 * 已读标记
	 * 
	 * @hibernate.property column="isread" type="java.lang.String" length="1"
	 */
	String isRead;

	/**
	 * IP地址
	 * 
	 * @hibernate.property column="ip" type="java.lang.String" length="100"
	 */
	String ip;

	/**
	 * 用户名
	 * 
	 * @hibernate.property column="user_name" type="java.lang.String"
	 *                     length="100"
	 */
	String userName;

	public BusinessExceptAlert() {

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

	public Date getAlertTime() {
		return alertTime;
	}

	public void setAlertTime(Date alertTime) {
		this.alertTime = alertTime;
	}

	public String getExceptCode() {
		return exceptCode;
	}

	public void setExceptCode(String exceptCode) {
		this.exceptCode = exceptCode;
	}

	public String getAlertInfo() {
		return alertInfo;
	}

	public void setAlertInfo(String alertInfo) {
		this.alertInfo = alertInfo;
	}

	public BusinessExceptCode getExceptCodeObject() {
		return exceptCodeObject;
	}

	public void setExceptCodeObject(BusinessExceptCode exceptCodeObject) {
		this.exceptCodeObject = exceptCodeObject;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
