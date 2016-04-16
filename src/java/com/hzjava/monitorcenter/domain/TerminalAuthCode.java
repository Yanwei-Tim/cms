package com.hzjava.monitorcenter.domain;

/**
 * 终端认证方式代码表
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="terminal_auth_code"
 */
public class TerminalAuthCode {

	/**
	 * 代码
	 * 
	 * @hibernate.id column="code" generator-class="assigned"
	 *               type="java.lang.String"
	 */
	String code;

	/**
	 * 认证方式
	 * 
	 * @hibernate.property column="code_desc" type="java.lang.String"
	 */
	String codeDesc;

	public TerminalAuthCode() {

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
