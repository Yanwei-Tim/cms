package com.hzjava.monitorcenter.domain;

/**
 * 探针表
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="monitor_agent"
 */
public class MonitorAgent {
	/**
	 * @hibernate.id column="Id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;

	/**
	 * 探针名
	 * 
	 * @hibernate.property column="agent_name" type="java.lang.String"
	 */
	String agentName;

	/**
	 * 探针IP
	 * 
	 * @hibernate.property column="agent_ip" type="java.lang.String"
	 */
	String agentIp;

	/**
	 * 探针控制命令端口
	 * 
	 * @hibernate.property column="agent_port" type="int"
	 */
	int agentPort;

	/**
	 * 是否加密传输
	 * 
	 * @hibernate.property column="is_encrypted" type="java.lang.String"
	 */
	String isEncrypted;

	/**
	 * 链路
	 * 
	 * @hibernate.property column="link_name" type="java.lang.String"
	 */
	String linkName;

	/**
	 * 探针位置 说明：i为内网，e为外网
	 * 
	 * @hibernate.property column="agent_station" type="java.lang.String"
	 */
	String agentStation;

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
	 * 备注
	 * 
	 * @hibernate.property column="memo" type="java.lang.String"
	 */
	String memo;

	public MonitorAgent() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentIp() {
		return agentIp;
	}

	public void setAgentIp(String agentIp) {
		this.agentIp = agentIp;
	}

	public int getAgentPort() {
		return agentPort;
	}

	public void setAgentPort(int agentPort) {
		this.agentPort = agentPort;
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

	public String getAgentStation() {
		return agentStation;
	}

	public void setAgentStation(String agentStation) {
		this.agentStation = agentStation;
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
