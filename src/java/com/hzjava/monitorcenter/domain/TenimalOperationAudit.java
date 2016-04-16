package com.hzjava.monitorcenter.domain;

import java.util.Date;


/**
 * 用户操作记录
 * @author xiangqi.java@gmail.com
 * @hibernate.class table="tenimal_operation_audit"
 */
public class TenimalOperationAudit {
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
	 * 
	 * @hibernate.property column="cert_id" type="java.lang.String"
	 */
	String certId;
	
	/**
	 * 操作类型
	 * 截屏'A'，阻断 'B'、吊销证书'C' 、恢复接入'D'
	 * @hibernate.property column="operate_tpye" type="java.lang.String" length="1"
	 */
	String operateTpye;
	
	
	/**
	 * 操作时间
	 * @hibernate.property column="operate_time" type="java.util.Date"
	 */
	Date operateTime;
	
	/**
	 * 操作员
	 * 
	 * @hibernate.property column="operater" type="java.lang.String"
	 */
	String operater;
	


	public TenimalOperationAudit() {
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



	public String getOperateTpye() {
		return operateTpye;
	}



	public void setOperateTpye(String operateTpye) {
		this.operateTpye = operateTpye;
	}



	public Date getOperateTime() {
		return operateTime;
	}



	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}



	public String getOperater() {
		return operater;
	}



	public void setOperater(String operater) {
		this.operater = operater;
	}


}
