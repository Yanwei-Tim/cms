package com.hzjava.monitorcenter.domain;

/**
 * 平台运行状态代码表
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="platform_running_code"
 */
public class PlatformRunningCode {

	/**
	 * 代码
	 * 
	 * @hibernate.id column="code" generator-class="assigned"
	 *               type="java.lang.String"
	 */
	String code;

	/**
	 * 平台运行状态
	 * 
	 * @hibernate.property column="code_desc" type="java.lang.String"
	 */
	String codeDesc;

	public PlatformRunningCode() {

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
}
