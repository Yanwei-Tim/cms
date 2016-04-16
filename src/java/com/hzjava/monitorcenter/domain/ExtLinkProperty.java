package com.hzjava.monitorcenter.domain;

/**
 * 外部链路性质表
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="ext_link_property"
 */
public class ExtLinkProperty {

	/**
	 * 外部链路性质代码
	 * 
	 * @hibernate.id column="code" generator-class="assigned"
	 *               type="java.lang.String"
	 */
	String code;

	/**
	 * 链路性质描述
	 * 
	 * @hibernate.property column="code_desc" type="java.lang.String"
	 */
	String codeDesc;

	public ExtLinkProperty() {

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
