package com.hzjava.monitorcenter.domain;

/**
 * 单位类型表
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="use_depart_type"
 */
public class UseDepartType {

	/**
	 * 单位类型代码
	 * 
	 * @hibernate.id column="depart_code" generator-class="assigned"
	 *               type="java.lang.String"
	 */
	String departCode;

	/**
	 * 单位类型
	 * 
	 * @hibernate.property column="depart_tpye_desc" type="java.lang.String"
	 */
	String departTypeDesc;

	public UseDepartType() {

	}

	public String getDepartCode() {
		return departCode;
	}

	public void setDepartCode(String departCode) {
		this.departCode = departCode;
	}

	public String getDepartTypeDesc() {
		return departTypeDesc;
	}

	public void setDepartTypeDesc(String departTypeDesc) {
		this.departTypeDesc = departTypeDesc;
	}

}
