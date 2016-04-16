package com.hzjava.monitorcenter.domain;

/**
 * 业务交换方式
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="business_exch_model"
 */
public class BusinessExchModel {

	/**
	 * 代码
	 * 
	 * @hibernate.id column="code" generator-class="assigned"
	 *               type="java.lang.String"
	 */
	String code;

	/**
	 * 说明
	 * 
	 * @hibernate.property column="code_desc" type="java.lang.String"
	 */
	String description;

	public BusinessExchModel() {

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
