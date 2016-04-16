package com.hzjava.monitorcenter.domain;

import java.util.Date;

/**
 * 终端设备【硬件】
 * 
 * @author xiangqi.java@gmail.com
 * @hibernate.class table="systerminalinfo"
 */
public class SysTerminalInfo {
	/**
	 * @hibernate.id column="Id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;

	/**
	 * 系统标识
	 * 
	 * @hibernate.property column="Idsystem" type="java.lang.Long"
	 */
//	Long idSystem;

	/**
	 * 接入终端标识
	 * 
	 * @hibernate.property column="Idterminal" type="java.lang.String"
	 */
//	String idTerminal;

	/**
	 * 接入终端名称
	 * 
	 * @hibernate.property column="TerminalName" type="java.lang.String"
	 */
	String terminalName;

	/**
	 * 接入终端类型
	 * 
	 * @hibernate.property column="Terminaltype" type="java.lang.String"
	 */
	String terminalType;

	/**
	 * 接入终端外部链路
	 * 
	 * @hibernate.property column="TermianlOutlink" type="java.lang.String"
	 */
	String termianlOutlink;

	/**
	 * 接入终端操作系统
	 * 
	 * @hibernate.property column="Termianlos" type="java.lang.String"
	 */
	String termianlOS;

	/**
	 * 接入终端品牌
	 * 
	 * @hibernate.property column="Termianlband" type="java.lang.String"
	 */
	String termianlBand;

	/**
	 * 安全卡类型
	 * 
	 * @hibernate.property column="cardtype" type="java.lang.String"
	 */
	String cardType;

	/**
	 * 终端安全卡型号
	 * 
	 * @hibernate.property column="Cardname" type="java.lang.String"
	 */
	String cardName;

	/**
	 * 终端安全卡版本
	 * 
	 * @hibernate.property column="card_version" type="java.lang.String"
	 */
	String cardVersion;

	/**
	 * 用户身份证号码
	 * 
	 * @hibernate.property column="userid" type="java.lang.String"
	 */
	String userId;

	/**
	 * 用户姓名
	 * 
	 * @hibernate.property column="username" type="java.lang.String"
	 */
	String userName;

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
	 * 初次接入时间
	 * 
	 * @hibernate.property column="Regtime" type="java.util.Date"
	 */
	Date regTime;

	/**
	 * 终端是否已停用 0表示终端正常使用；1表示该终端已注销，不再被使用 注：新增记录时候标识为0
	 * 
	 * @hibernate.property column="Ifcancel" type="java.lang.String" length="2"
	 */
	String ifCancel;

	/**
	 * 操作标记 1表示新增、2表示修改、0表示正常
	 * 
	 * @hibernate.property column="flag" type="java.lang.String" length="1"
	 */
	String flag;
	
	public SysTerminalInfo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
//
//	public Long getIdSystem() {
//		return idSystem;
//	}
//
//	public void setIdSystem(Long idSystem) {
//		this.idSystem = idSystem;
//	}
//
//	public String getIdTerminal() {
//		return idTerminal;
//	}
//
//	public void setIdTerminal(String idTerminal) {
//		this.idTerminal = idTerminal;
//	}

	public String getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}

	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	public String getTermianlOutlink() {
		return termianlOutlink;
	}

	public void setTermianlOutlink(String termianlOutlink) {
		this.termianlOutlink = termianlOutlink;
	}

	public String getTermianlOS() {
		return termianlOS;
	}

	public void setTermianlOS(String termianlOS) {
		this.termianlOS = termianlOS;
	}

	public String getTermianlBand() {
		return termianlBand;
	}

	public void setTermianlBand(String termianlBand) {
		this.termianlBand = termianlBand;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardVersion() {
		return cardVersion;
	}

	public void setCardVersion(String cardVersion) {
		this.cardVersion = cardVersion;
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

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getIfCancel() {
		return ifCancel;
	}

	public void setIfCancel(String ifCancel) {
		this.ifCancel = ifCancel;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
