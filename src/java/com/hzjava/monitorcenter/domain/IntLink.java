package com.hzjava.monitorcenter.domain;

/**
 * 内部链路表
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="int_link"
 */
public class IntLink {
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
	 * 接入对象
	 * 
	 * @hibernate.property column="jrdx" type="java.lang.String"
	 */
	String jrdx;

	/**
	 * 业务交换方式
	 * 
	 * @hibernate.property column="exchange_mode" type="java.lang.String"
	 */
	String exchangeMode;

	/**
	 * 链路带宽（M）
	 * 
	 * @hibernate.property column="link_bandwidth" type="java.lang.Integer"
	 */
	int linkBandWidth;

	/**
	 * 是否使用防火墙
	 * 
	 * @hibernate.property column="FW_used" type="java.lang.String"
	 */
	String fwUsed;

	/**
	 * 是否使用安全网关
	 * 
	 * @hibernate.property column="sec_gateway_used" type="java.lang.String"
	 */
	String secGatewayUsed;

	/**
	 * 是否使用网闸
	 * 
	 * @hibernate.property column="gap_used" type="java.lang.String"
	 */
	String gapUsed;

	/**
	 * 是否使用VPN
	 * 
	 * @hibernate.property column="VPN_used" type="java.lang.String"
	 */
	String vpnUsed;

	/**
	 * 其他安全设施
	 * 
	 * @hibernate.property column="other_security" type="java.lang.String"
	 */
	String otherSecurity;

	public IntLink() {

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

	public String getJrdx() {
		return jrdx;
	}

	public void setJrdx(String jrdx) {
		this.jrdx = jrdx;
	}

	public String getExchangeMode() {
		return exchangeMode;
	}

	public void setExchangeMode(String exchangeMode) {
		this.exchangeMode = exchangeMode;
	}

	public int getLinkBandWidth() {
		return linkBandWidth;
	}

	public void setLinkBandWidth(int linkBandWidth) {
		this.linkBandWidth = linkBandWidth;
	}

	public String getFwUsed() {
		return fwUsed;
	}

	public void setFwUsed(String fwUsed) {
		this.fwUsed = fwUsed;
	}

	public String getSecGatewayUsed() {
		return secGatewayUsed;
	}

	public void setSecGatewayUsed(String secGatewayUsed) {
		this.secGatewayUsed = secGatewayUsed;
	}

	public String getGapUsed() {
		return gapUsed;
	}

	public void setGapUsed(String gapUsed) {
		this.gapUsed = gapUsed;
	}

	public String getVpnUsed() {
		return vpnUsed;
	}

	public void setVpnUsed(String vpnUsed) {
		this.vpnUsed = vpnUsed;
	}

	public String getOtherSecurity() {
		return otherSecurity;
	}

	public void setOtherSecurity(String otherSecurity) {
		this.otherSecurity = otherSecurity;
	}

}
