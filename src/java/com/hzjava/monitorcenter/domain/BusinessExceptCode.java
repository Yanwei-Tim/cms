package com.hzjava.monitorcenter.domain;

/**
 * 异常事件代码表
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="business_except_code"
 */
public class BusinessExceptCode {

	/**
	 * 异常事件代码
	 * 
	 * @hibernate.id column="code" generator-class="assigned"
	 *               type="java.lang.String"
	 */
	String code;

	/**
	 * 异常事件类型
	 * 
	 * @hibernate.property column="code_desc" type="java.lang.String"
	 */
	String description;

	public BusinessExceptCode() {

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
