package com.hzjava.monitorcenter.domain;

/**
 * 数据交换方向
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="exchange_direction"
 */
public class ExchangeDirection {

	/**
	 * 代码
	 * 
	 * @hibernate.id column="code" generator-class="assigned"
	 *               type="java.lang.String"
	 */
	String code;

	/**
	 * 交换方向
	 * 
	 * @hibernate.property column="code_desc" type="java.lang.String"
	 */
	String codeDesc;

	public ExchangeDirection() {

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
