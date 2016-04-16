package com.hzjava.monitorcenter.domain;


/**
 * 移动终端操作系统代码表
 * @author xiangqi.java@gmail.com
 * @hibernate.class table="termianlos"
 */
public class TermianlOS {
	/**
	 * @hibernate.id column="Id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;
	
	/**
	 * 名称
	 * 
	 * @hibernate.property column="OS_name" type="java.lang.String"
	 */
	String name;

	/**
	 * 代码
	 * 
	 * @hibernate.property column="OS_id" type="java.lang.String"
	 */
	String code;
	


	public TermianlOS() {
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}
}
