package com.hzjava.monitorcenter.domain;

/**
 * 安全事件代码表
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="security_event_code"
 */
public class SecurityEventCode {
	/**
	 * 代码
	 * 
	 * @hibernate.id column="code" generator-class="assigned"
	 *               type="java.lang.String"
	 */
	String code;

	/**
	 * 安全事件类型
	 * 
	 * @hibernate.property column="code_desc" type="java.lang.String"
	 */
	String codeDesc;

	public SecurityEventCode() {
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
