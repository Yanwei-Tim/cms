package com.hzjava.monitorcenter.domain;

import java.util.Date;

/**
 * 平台异常事件报警
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="security_event_alert"
 */
public class SecurityEventAlert {
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
	 * @hibernate.property column="equ_name" type="java.lang.String"
	 */
	String equName;

	/**
	 * 报警时间
	 * 
	 * @hibernate.property column="alert_time" type="java.util.Date"
	 */
	Date alertTime;

	/**
	 * 报警类型
	 * 
	 * @hibernate.property column="event_code" type="java.lang.String"
	 */
	String eventCode;
	/**
	 * @hibernate.many-to-one class="com.hzjava.monitorcenter.domain.SecurityEventCode"
	 *                        column="event_code" insert="false" update="false"
	 */
	SecurityEventCode eventCodeObject;

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

	public SecurityEventAlert() {

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

	public Date getAlertTime() {
		return alertTime;
	}

	public void setAlertTime(Date alertTime) {
		this.alertTime = alertTime;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public SecurityEventCode getEventCodeObject() {
		return eventCodeObject;
	}

	public void setEventCodeObject(SecurityEventCode eventCodeObject) {
		this.eventCodeObject = eventCodeObject;
	}

	public String getAlertInfo() {
		return alertInfo;
	}

	public void setAlertInfo(String alertInfo) {
		this.alertInfo = alertInfo;
	}

}
