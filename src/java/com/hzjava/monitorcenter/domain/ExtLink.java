package com.hzjava.monitorcenter.domain;

/**
 * 链路
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="ext_link"
 */
public class ExtLink {
	/**
	 * @hibernate.id column="Id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;

	/**
	 * 链路名称
	 * 
	 * @hibernate.property column="link_name" type="java.lang.String"
	 */
	String linkName;

	/**
	 * 链路性质
	 * 
	 * @hibernate.property column="link_property" type="java.lang.String"
	 */
	String linkPropertyCode;

	/**
	 * @hibernate.many-to-one class="com.hzjava.monitorcenter.domain.ExtLinkProperty"
	 *                        column="link_property" insert="false"
	 *                        update="false" lazy="false"
	 */
	ExtLinkProperty linkProperty;

	/**
	 * 链路类型
	 * 
	 * @hibernate.property column="link_type" type="java.lang.String"
	 */
	String linkTypeCode;
	/**
	 * @hibernate.many-to-one class="com.hzjava.monitorcenter.domain.ExtLinkType"
	 *                        column="link_type" insert="false" update="false"
	 *                        lazy="false"
	 */
	ExtLinkType linkType;

	/**
	 * 链路运营商
	 * 
	 * @hibernate.property column="link_Corp" type="java.lang.String"
	 */
	String linkCorp;

	/**
	 * 链路安全机制
	 * 
	 * @hibernate.property column="link_security" type="java.lang.String"
	 */
	String linkSecurity;

	/**
	 * 链路数量
	 * 
	 * @hibernate.property column="link_amount" type="java.lang.Long"
	 */
	long linkAmount;

	/**
	 * 链路带宽（M）
	 * 
	 * @hibernate.property column="link_bandwidth" type="java.lang.Long"
	 */
	long linkBandWidth;

	/**
	 * 其他安全设施
	 * 
	 * @hibernate.property column="other_security" type="java.lang.String"
	 */
	String otherSecurity;
	
	/**
	 * 接入网络地址
	 * 
	 * @hibernate.property column="ip" type="java.lang.String"
	 */
	String ip;
	
	/**
	 * 接入网络子网掩码
	 * 
	 * @hibernate.property column="mask" type="java.lang.String"
	 */
	String mask;
	
	/**
	 * 接入网关地址
	 * 
	 * @hibernate.property column="gateway" type="java.lang.String"
	 */
	String gateway;

	public ExtLink() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkPropertyCode() {
		return linkPropertyCode;
	}

	public void setLinkPropertyCode(String linkPropertyCode) {
		this.linkPropertyCode = linkPropertyCode;
	}

	public ExtLinkProperty getLinkProperty() {
		return linkProperty;
	}

	public void setLinkProperty(ExtLinkProperty linkProperty) {
		this.linkProperty = linkProperty;
	}

	public String getLinkTypeCode() {
		return linkTypeCode;
	}

	public void setLinkTypeCode(String linkTypeCode) {
		this.linkTypeCode = linkTypeCode;
	}

	public ExtLinkType getLinkType() {
		return linkType;
	}

	public void setLinkType(ExtLinkType linkType) {
		this.linkType = linkType;
	}

	public String getLinkCorp() {
		return linkCorp;
	}

	public void setLinkCorp(String linkCorp) {
		this.linkCorp = linkCorp;
	}

	public String getLinkSecurity() {
		return linkSecurity;
	}

	public void setLinkSecurity(String linkSecurity) {
		this.linkSecurity = linkSecurity;
	}

	public long getLinkAmount() {
		return linkAmount;
	}

	public void setLinkAmount(long linkAmount) {
		this.linkAmount = linkAmount;
	}

	public long getLinkBandWidth() {
		return linkBandWidth;
	}

	public void setLinkBandWidth(long linkBandWidth) {
		this.linkBandWidth = linkBandWidth;
	}

	public String getOtherSecurity() {
		return otherSecurity;
	}

	public void setOtherSecurity(String otherSecurity) {
		this.otherSecurity = otherSecurity;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	
}
