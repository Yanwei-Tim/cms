package com.hzjava.monitorcenter.domain;

/**
 * @author collin.code@gmail.com
 * @hibernate.class table="conf"
 */
public class Conf {
	/**
	 * 唯一ID
	 * 
	 * @hibernate.id column="Id" generator-class="identity" type="long"
	 */
	Long id;

	/**
	 * code
	 * 
	 * @hibernate.property column="code" type="string"
	 */
	String code;

	/**
	 * 配置内容，以json格式保存
	 * 
	 * @hibernate.property column="content" type="text"
	 */
	String content;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Conf() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
