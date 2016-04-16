package com.hzjava.monitorcenter.domain;

import java.util.Date;


/**
 * 终端访问信息
 * @author xiangqi.java@gmail.com
 * @hibernate.class table="ternimal_access_audit"
 */
public class TernimalAccessAudit {
	/**
	 * @hibernate.id column="Id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;
	
	/**
	 * 安全卡类型
	 * 
	 * @hibernate.property column="card_type" type="java.lang.String"
	 */
	String cardType;

	/**
	 * 警号
	 * 
	 * @hibernate.property column="police_id" type="java.lang.String"
	 */
	String policeId;
	
	/**
	 * 姓名
	 * 
	 * @hibernate.property column="police_name" type="java.lang.String"
	 */
	String policeName;
	
	/**
	 * 操作时间
	 * @hibernate.property column="access_time" type="java.util.Date"
	 */
	Date accessTime;
	
	/**
	 * 消息级别
	 * 
	 * @hibernate.property column="message_level" type="java.lang.String"
	 */
	String messageLevel;
	
	/**
	 * 
	 * @hibernate.property column="desc" type="java.lang.String"
	 */
	String desc;
	
	String userId;
	
	String userDepart;
	
	String userZone;
	
	int flux;
	
	String busName;
	
	/**
	 * 请求
	 * 
	 * @hibernate.property column="count" type="java.lang.Integer"
	 */
	int count;

	public TernimalAccessAudit() {
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

	public String getPoliceId() {
		return policeId;
	}

	public void setPoliceId(String policeId) {
		this.policeId = policeId;
	}

	public String getPoliceName() {
		return policeName;
	}

	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public String getMessageLevel() {
		return messageLevel;
	}

	public void setMessageLevel(String messageLevel) {
		this.messageLevel = messageLevel;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public String getUserZone() {
		return userZone;
	}

	public void setUserZone(String userZone) {
		this.userZone = userZone;
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
