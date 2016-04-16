package com.hzjava.monitorcenter.domain;

/**
 * 功能模块表
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="platform_function_module"
 */
public class PlatformFunctionModule {
	/**
	 * @hibernate.id column="Id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;

	/**
	 * 模块编号
	 * 
	 * @hibernate.property column="code" type="java.lang.String"
	 */
	String code;

	/**
	 * 模块名
	 * 
	 * @hibernate.property column="code_desc" type="java.lang.String"
	 */
	String codeDesc;

	public PlatformFunctionModule() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
