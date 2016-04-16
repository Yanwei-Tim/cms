package com.hzjava.monitorcenter.domain;

import java.util.Date;

/**
 * 全部终端列表
 * 
 * @author xiangqi.java@gmail.com
 * @hibernate.class table="tenimal_list"
 */
public class TenimalList {
	/**
	 * @hibernate.id column="Id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;

	/**
	 * 安全卡编号
	 * 
	 * @hibernate.property column="card_id" type="java.lang.String"
	 */
	String cardId;

	/**
	 * 安全卡类型
	 * 
	 * @hibernate.property column="cardtype" type="java.lang.String"
	 */
	String cardType;

	/**
	 * 
	 * @hibernate.property column="card_model" type="java.lang.String"
	 */
	String cardModel;

	/**
	 * 
	 * @hibernate.property column="card_version" type="java.lang.String"
	 */
	String cardVersion;

	/**
	 * 
	 * @hibernate.property column="card_certid_date" type="java.util.Date"
	 */
	Date cardCertidDate;

	/**
	 * 部门
	 * 
	 * @hibernate.property column="userdepart" type="java.lang.String"
	 */
	String userDepart;

	/**
	 * 地区
	 * 
	 * @hibernate.property column="userzone" type="java.lang.String"
	 */
	String userZone;

	/**
	 * 警号
	 * 
	 * @hibernate.property column="policenumber" type="java.lang.String"
	 */
	String policeId;

	/**
	 * 姓名
	 * 
	 * @hibernate.property column="police_name" type="java.lang.String"
	 */
	String policeName;

	/**
	 * 证书编号
	 * 
	 * @hibernate.property column="cert_id" type="java.lang.String"
	 */
	String certId;

	/**
	 * 制证日期
	 * 
	 * @hibernate.property column="certcreate_date" type="java.util.Date"
	 */
	Date certcreateDate;

	/**
	 * 最近登陆时间
	 * 
	 * @hibernate.property column="login_time" type="java.util.Date"
	 */
	Date loginTime;

	/**
	 * 状态 无阻断 A 、临时阻断 B、长久阻断 C
	 * 
	 * @hibernate.property column="block_type" type="java.lang.String"
	 *                     length="1"
	 */
	String blockType;
	/**
	 * 当前状态 1 在线，0 离线
	 * 
	 * @hibernate.property column="status" type="java.lang.String" length="1"
	 */
	String status;
	/**
	 * 临时阻断的小时数
	 * 
	 * @hibernate.property column="hour" type="java.lang.String" 
	 */
	String hour;

	public TenimalList() {
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardModel() {
		return cardModel;
	}

	public void setCardModel(String cardModel) {
		this.cardModel = cardModel;
	}

	public String getCardVersion() {
		return cardVersion;
	}

	public void setCardVersion(String cardVersion) {
		this.cardVersion = cardVersion;
	}

	public Date getCardCertidDate() {
		return cardCertidDate;
	}

	public void setCardCertidDate(Date cardCertidDate) {
		this.cardCertidDate = cardCertidDate;
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

	public String getCertId() {
		return certId;
	}

	public void setCertId(String certId) {
		this.certId = certId;
	}

	public Date getCertcreateDate() {
		return certcreateDate;
	}

	public void setCertcreateDate(Date certcreateDate) {
		this.certcreateDate = certcreateDate;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getBlockType() {
		return blockType;
	}

	public void setBlockType(String blockType) {
		this.blockType = blockType;
	}

}
