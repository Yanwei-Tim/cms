package com.hzjava.monitorcenter.domain;

/**
 * 业务代码
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="business_code"
 */
public class BusinessCode {

	/**
	 * 业务代码
	 * 
	 * @hibernate.id column="code" generator-class="assigned"
	 *               type="java.lang.String"
	 */
	String code;

	/**
	 * 业务描述
	 * 
	 * @hibernate.property column="code_desc" type="string"
	 */
	String description;

	public BusinessCode() {

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
