package com.hzjava.monitorcenter.domain;


/**
 * 上级交换平台配置
 * 
 * @author xiangqi.java@gmail.com
 * @hibernate.class table="parent_exchange_platform"
 */
public class ParentExchangePlatform {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    String type;      //服务类型（Webservice。Ftp）
    String name;      //用户名（如果服务类型选择Ftp，需要输入用户名密码）
    String pass;      //密码
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
	 * 交换平台端口
	 * 
	 * @hibernate.property column="platform_port" type="java.lang.Integer"
	 */
	int platformPort;
	
	/**
	 * 平台MAC
	 * 
	 * @hibernate.property column="platform_mac" type="java.lang.String"
	 */
	String platformMac;

	/**
	 * 是否加密 说明：N为未加密，Y加密
	 * 
	 * @hibernate.property column="is_encrypted" type="java.lang.String"
	 *                     length="1"
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
	
	/**
	 * 服务地址
	 * 
	 * @hibernate.property column="address" type="java.lang.String"
	 */
	String address;
	
	/**
	 * 上报时间间隔
	 * 
	 * @hibernate.property column="timeType" type="java.lang.String"
	 */
	String timeType;

	/**
	 * 
	 * @hibernate.property column="enablereport" type="java.lang.Boolean"
	 */
	boolean enablereport = false;
	
	/**
	 * 
	 * @hibernate.property column="hour" type="java.lang.String"
	 */
	String hour;
	
	/**
	 * 
	 * @hibernate.property column="minute" type="java.lang.String"
	 */
	String minute;
	
	/**
	 * 
	 * @hibernate.property column="second" type="java.lang.String"
	 */
	String second;

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public ParentExchangePlatform() {
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

	public boolean isEnablereport() {
		return enablereport;
	}

	public void setEnablereport(boolean enablereport) {
		this.enablereport = enablereport;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPlatformMac() {
		return platformMac;
	}

	public void setPlatformMac(String platformMac) {
		this.platformMac = platformMac;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	

}
