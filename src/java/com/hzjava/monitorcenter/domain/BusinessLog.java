package com.hzjava.monitorcenter.domain;

import java.util.Date;

/**
 * 业务审计表
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="business_log"
 */
public class BusinessLog {
	/**
	 * @hibernate.id column="Id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;

	/**
	 * 日志级别
	 * 
	 * @hibernate.property column="level" type="java.lang.String"
	 */
	String level;

	/**
	 * 日志时间
	 * 
	 * @hibernate.property column="log_time" type="java.util.Date"
	 */
	Date logTime;

	/**
	 * 业务名
	 * 
	 * @hibernate.property column="business_name" type="java.lang.String"
	 */
	String businessName;

	/**
	 * 平台名
	 * 
	 * @hibernate.property column="platform_name" type="java.lang.String"
	 */
	String platformName;

	/**
	 * 审计内容
	 * 
	 * @hibernate.property column="audit_info" type="java.lang.String"
	 */
	String auditInfo;

	/**
	 * 源IP
	 * 
	 * @hibernate.property column="source_ip" type="java.lang.String"
	 */
	String sourceIp;

	/**
	 * 源端口
	 * 
	 * @hibernate.property column="source_dest" type="java.lang.String"
	 */
	String sourcePort;

	/**
	 * 目标IP
	 * 
	 * @hibernate.property column="dest_ip" type="java.lang.String" length="30"
	 */
	String destIp;

	/**
	 * 目标端口
	 * 
	 * @hibernate.property column="dest_port" type="java.lang.String"
	 *                     length="10"
	 */
	String destPort;

	/**
	 * 用户名
	 * 
	 * @hibernate.property column="user_name" type="java.lang.String"
	 *                     length="50"
	 */
	String userName;

	/**
	 * 操作行为
	 * 
	 * @hibernate.property column="operation" type="java.lang.String"
	 *                     length="100"
	 */
	String operation;

	public BusinessLog() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(String auditInfo) {
		this.auditInfo = auditInfo;
	}

	public String getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(String sourcePort) {
		this.sourcePort = sourcePort;
	}

	public String getDestIp() {
		return destIp;
	}

	public void setDestIp(String destIp) {
		this.destIp = destIp;
	}

	public String getDestPort() {
		return destPort;
	}

	public void setDestPort(String destPort) {
		this.destPort = destPort;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getSourceIp() {
		return sourceIp;
	}

	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

}
