package com.hzjava.monitorcenter.domain;

/**
 * 协议代码表
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="protocol_type"
 */
public class ProtocolType {
	/**
	 * 协议编号
	 * 
	 * @hibernate.id column="protocol_code" generator-class="assigned"
	 *               type="java.lang.String"
	 */
	String protocolCode;

	/**
	 * 协议名称
	 * 
	 * @hibernate.property column="protocol_name" type="java.lang.String"
	 */
	String protocolName;

	public ProtocolType() {

	}

	public String getProtocolCode() {
		return protocolCode;
	}

	public void setProtocolCode(String protocolCode) {
		this.protocolCode = protocolCode;
	}

	public String getProtocolName() {
		return protocolName;
	}

	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}

}
