package com.hzjava.monitorcenter.domain;

/**
 * 交换平台配置
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="exchange_platform"
 */
public class ExchangePlatform {
	/**
	 * @hibernate.id column="Id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;

	/**
	 * 交换平台名称
	 * 
	 * @hibernate.property column="platform_name" type="java.lang.String"
	 */
	String platformName;

	/**
	 * 平台IP
	 * 
	 * @hibernate.property column="platform_ip" type="java.lang.String"
	 */
	String platformIp;

	/**
	 * 平台监控端口
	 * 
	 * @hibernate.property column="platform_port" type="java.lang.Integer"
	 */
	int platformPort;

	/**
	 * 是否加密 说明：N为未加密，Y加密
	 * 
	 * @hibernate.property column="is_encrypted" type="java.lang.String"
	 */
	String isEncrypted;

	/**
	 * 链路名
	 * 
	 * @hibernate.property column="link_name" type="java.lang.String"
	 */
	String linkName;

	/**
	 * 证书路径
	 * 
	 * @hibernate.property column="certificate_path" type="java.lang.String"
	 */
	String certificatePath;

	/**
	 * 证书密码
	 * 
	 * @hibernate.property column="certificate_pwd" type="java.lang.String"
	 */
	String certificatePwd;

	/**
	 * 描述
	 * 
	 * @hibernate.property column="memo" type="java.lang.String"
	 */
	String memo;

	public ExchangePlatform() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getPlatformIp() {
		return platformIp;
	}

	public void setPlatformIp(String platformIp) {
		this.platformIp = platformIp;
	}

	public int getPlatformPort() {
		return platformPort;
	}

	public void setPlatformPort(int platformPort) {
		this.platformPort = platformPort;
	}

	public String getIsEncrypted() {
		return isEncrypted;
	}

	public void setIsEncrypted(String isEncrypted) {
		this.isEncrypted = isEncrypted;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getCertificatePath() {
		return certificatePath;
	}

	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}

	public String getCertificatePwd() {
		return certificatePwd;
	}

	public void setCertificatePwd(String certificatePwd) {
		this.certificatePwd = certificatePwd;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
