package com.hzjava.monitorcenter.domain;

/**
 * 外部链路类型
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="ext_link_type"
 */
public class ExtLinkType {

	/**
	 * 链路类型代码
	 * 
	 * @hibernate.id column="link_type_code" generator-class="assigned"
	 *               type="java.lang.String"
	 */
	String linkTypeCode;

	/**
	 * 链路类型
	 * 
	 * @hibernate.property column="link_type_name" type="java.lang.String"
	 */
	String linkTypeName;

	public ExtLinkType() {

	}

	public String getLinkTypeCode() {
		return linkTypeCode;
	}

	public void setLinkTypeCode(String linkTypeCode) {
		this.linkTypeCode = linkTypeCode;
	}

	public String getLinkTypeName() {
		return linkTypeName;
	}

	public void setLinkTypeName(String linkTypeName) {
		this.linkTypeName = linkTypeName;
	}

}
