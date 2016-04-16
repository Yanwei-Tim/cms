package com.hzjava.monitorcenter.domain;

/**
 * 
 * 
 * @author www
 * @hibernate.class table="sys_class"
 */
public class SysClass {
	
	/**
	 * @hibernate.id column="system_class_code" generator-class="increment" type="long"
	 *               length="5"
	 */
	String id;
	
	/**
	 * 部门名
	 * 
	 * @hibernate.property column="system_class_name" type="string" length="20"
	 */
	String sysClassName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysClassName() {
		return sysClassName;
	}

	public void setSysClassName(String sysClassName) {
		this.sysClassName = sysClassName;
	}
	

}
