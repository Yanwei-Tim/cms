package com.hzjava.monitorcenter.domain;

import java.util.Date;

/**
 * 上报配置表
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="sbpz"
 */
public class Sbpz {

	/**
	 * @hibernate.id column="id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;

	/**
	 * @hibernate.property column="level" type="java.lang.String"
	 */
	String level;

	/**
	 * @hibernate.property column="ip" type="java.lang.String"
	 */
	String ip;

	/**
	 * @hibernate.property column="port" type="java.lang.String"
	 */
	String port;

	/**
	 * @hibernate.property column="username" type="java.lang.String"
	 */
	String username;

	/**
	 * @hibernate.property column="password" type="java.lang.String"
	 */
	String password;

	/**
	 * @hibernate.property column="hour" type="int"
	 */
	int hour;

	/**
	 * @hibernate.property column="minute" type="int"
	 */
	int minute;

	/**
	 * @hibernate.property column="second" type="int"
	 */
	int second;

	/**
	 * @hibernate.property column="created_time" type="java.util.Date"
	 */
	Date createdTime;

	/**
	 * @hibernate.property column="modified_time" type="java.util.Date"
	 */
	Date modifiedTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Sbpz() {

	}
}
