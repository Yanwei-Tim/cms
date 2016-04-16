package com.hzjava.monitorcenter.domain;

import java.util.Date;

/**
 * 本级系统违规情况
 * @author xiangqi.java@gmail.com
 * @hibernate.class table="lower_sysabnormal"
 */
public class LowerSysabnormal {
	/**
	 * @hibernate.id column="Id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;
	
	/**
	 * 系统标识
	 * 
	 * @hibernate.property column="Idsystem" type="java.lang.String"
	 */
	String idSystem;

	/**
	 * 违规事件标识
	 * @hibernate.property column="Idalertmatter" type="java.lang.Long"
	 */
	Long idAlertMatter;

	/**
	 * 违规事件类型
	 * @hibernate.property column="Abnormaltypecode" type="java.lang.String"
	 */
	String abNormalTypeCode;

	/**
	 * 违规对象
	 * 
	 * @hibernate.property column="Connectobjectcode" type="java.lang.String"
	 */
	String connectObjectCode;
	
	/**
	 * 违规事件描述
	 * 
	 * @hibernate.property column="Exceptiondesc" type="java.lang.String"
	 */
	String exceptionDesc;
	
	/**
	 * 违规事件发生时间
	 * 
	 * @hibernate.property column="Happentime" type="java.util.Date"
	 */
	Date happenTime;
	
	/**
	 * 违规事件处理时间
	 * 
	 * @hibernate.property column="Treattime" type="java.util.Date"
	 */
	Date treatTime;
	
	/**
	 * 处理结果
	 * 
	 * @hibernate.property column="Treadresult" type="java.lang.String"
	 */
	String treadResult;

	/**
	 * 记录时间
	 * 
	 * @hibernate.property column="writetime" type="java.util.Date"
	 */
	Date writeTime;
	
	SysabnormalEventCode sysabnormalEventCode;
	
	SysabnormalObjectType sysabnormalObjectType;
	
	SysabnormalResult sysabnormalResult;

	public LowerSysabnormal() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getIdSystem() {
		return idSystem;
	}


	public void setIdSystem(String idSystem) {
		this.idSystem = idSystem;
	}


	public Long getIdAlertMatter() {
		return idAlertMatter;
	}


	public void setIdAlertMatter(Long idAlertMatter) {
		this.idAlertMatter = idAlertMatter;
	}


	public String getAbNormalTypeCode() {
		return abNormalTypeCode;
	}


	public void setAbNormalTypeCode(String abNormalTypeCode) {
		this.abNormalTypeCode = abNormalTypeCode;
	}


	public String getConnectObjectCode() {
		return connectObjectCode;
	}


	public void setConnectObjectCode(String connectObjectCode) {
		this.connectObjectCode = connectObjectCode;
	}


	public String getExceptionDesc() {
		return exceptionDesc;
	}


	public void setExceptionDesc(String exceptionDesc) {
		this.exceptionDesc = exceptionDesc;
	}


	public Date getHappenTime() {
		return happenTime;
	}


	public void setHappenTime(Date happenTime) {
		this.happenTime = happenTime;
	}


	public Date getTreatTime() {
		return treatTime;
	}


	public void setTreatTime(Date treatTime) {
		this.treatTime = treatTime;
	}


	public String getTreadResult() {
		return treadResult;
	}


	public void setTreadResult(String treadResult) {
		this.treadResult = treadResult;
	}


	public Date getWriteTime() {
		return writeTime;
	}


	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}


	public SysabnormalEventCode getSysabnormalEventCode() {
		return sysabnormalEventCode;
	}


	public void setSysabnormalEventCode(SysabnormalEventCode sysabnormalEventCode) {
		this.sysabnormalEventCode = sysabnormalEventCode;
	}


	public SysabnormalObjectType getSysabnormalObjectType() {
		return sysabnormalObjectType;
	}


	public void setSysabnormalObjectType(SysabnormalObjectType sysabnormalObjectType) {
		this.sysabnormalObjectType = sysabnormalObjectType;
	}


	public SysabnormalResult getSysabnormalResult() {
		return sysabnormalResult;
	}


	public void setSysabnormalResult(SysabnormalResult sysabnormalResult) {
		this.sysabnormalResult = sysabnormalResult;
	}



}
