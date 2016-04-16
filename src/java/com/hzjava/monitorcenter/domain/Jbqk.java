package com.hzjava.monitorcenter.domain;

import java.sql.Blob;
import java.util.Date;

/**
 * 基本情况表
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="jbqk"
 */
public class Jbqk {
	/**
	 * @hibernate.id column="Id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;

	/**
	 * 平台名称
	 * 
	 * @hibernate.property column="platform_name" type="java.lang.String"
	 */
	String platformName;

	/**
	 * 平台标识（机构代码）
	 * 
	 * @hibernate.property column="platform_id" type="java.lang.String"
	 */
	String platformId;
	/**
	 * 系统类型
	 * @hibernate.property column="system_class" type="java.lang.String"
	 */
	String systemClass;

	/**
	 * 物理地址
	 * 
	 * @hibernate.property column="address" type="java.lang.String"
	 */
	String address;

	/**
	 * 负责人姓名
	 * 
	 * @hibernate.property column="fzr_name" type="java.lang.String"
	 */
	String fzrName;

	/**
	 * 负责人电话
	 * 
	 * @hibernate.property column="fzr_phone" type="java.lang.String"
	 */
	String fzrPhone;

	/**
	 * 负责人邮件
	 * 
	 * @hibernate.property column="fzr_email" type="java.lang.String"
	 */
	String fzrEmail;

	/**
	 * 负责人其他联系方式
	 * 
	 * @hibernate.property column="fzr_other_phone" type="java.lang.String"
	 */
	String fzrOhterPhone;

	/**
	 * 本监控系统IP
	 * 
	 * @hibernate.property column="jksys_ip" type="java.lang.String"
	 */
	String jksysIp;

	/**
	 * 本监控系统MAC
	 * 
	 * @hibernate.property column="jksys_mac" type="java.lang.String"
	 */
	String jksysMac;

	/**
	 * 建设单位
	 * 
	 * @hibernate.property column="jsdw" type="java.lang.Long"
	 */
	Long jsdw;

	/**
	 * 建设单位上级，仅显示用
	 */
	Long parentJsdw;
	/**
	 * 审批单位上级
	 */
	Long parentSpdw;
	/**
	 * 运维单位上级
	 */
	Long parentMainComp;

	/**
	 * 主要承建部门
	 * 
	 * @hibernate.property column="zycjdw" type="java.lang.String"
	 */
	String zycjdw;

	/**
	 * 建设日期
	 * 
	 * @hibernate.property column="js_day" type="java.util.Date"
	 */
	Date jsDay;

	/**
	 * 是否签署保密协议
	 * 
	 * @hibernate.property column="bmxy" type="java.lang.String"
	 */
	String bmxy;

	/**
	 * 审批单位（机构代码）
	 * 
	 * @hibernate.property column="spdw" type="java.lang.Long"
	 */
	Long spdw;

	/**
	 * 审批日期
	 * 
	 * @hibernate.property column="sp_day" type="java.util.Date"
	 */
	Date spDay;

	/**
	 * 审批批号
	 * 
	 * @hibernate.property column="spph" type="java.lang.String"
	 */
	String spph;

	/**
	 * 审批材料
	 * 
	 * @hibernate.property column="spcl" type="java.sql.Blob"
	 */
	Blob spcl;

	/**
	 * @hibernate.property column="spcl_file_name" type="string"
	 */
	String spclFileName;

	/**
	 * 技术方案
	 * 
	 * @hibernate.property column="Technology_solution" type="java.sql.Blob"
	 */
	Blob technologySolution;

	/**
	 * @hibernate.property column="technology_solution_file_name" type="string"
	 */
	String technologySolutionFileName;

	/**
	 * 保密协议
	 * 
	 * @hibernate.property column="Confidential_Agreement" type="java.sql.Blob"
	 */
	Blob confidentialAgreement;

	/**
	 * @hibernate.property column="confidential_agreement_file_name"
	 *                     type="string"
	 */
	String confidentialAgreementFileName;

	/**
	 * 运维单位名称（代码）
	 * 
	 * @hibernate.property column="main_comp" type="java.lang.Long"
	 */
	Long mainComp;

	/**
	 * 管理员姓名
	 * 
	 * @hibernate.property column="maintainer_name" type="java.lang.String"
	 */
	String maintainerName;

	/**
	 * 管理员电话
	 * 
	 * @hibernate.property column="maintainer_phone" type="java.lang.String"
	 */
	String maintainerPhone;

	/**
	 * 管理员邮箱
	 * 
	 * @hibernate.property column="maintainer_email" type="java.lang.String"
	 */
	String maintainerEmail;

	/**
	 * 管理员其他联系方式
	 * 
	 * @hibernate.property column="maintainer_other_phone"
	 *                     type="java.lang.String"
	 */
	String maintainerOhterPhone;

	/**
	 * 平台拓扑图
	 * 
	 * @hibernate.property column="platform_tp" type="java.sql.Blob"
	 */
	Blob platformTp;

	/**
	 * 平台拓扑图文件名称
	 * 
	 * @hibernate.property column="platform_tp_file_name" type="string"
	 */
	String platformTpFileName;

	/**
	 * @hibernate.many-to-one class="com.hzjava.monitorcenter.domain.Orgcode"
	 *                        lazy="false" column="jsdw" insert="false"
	 *                        update="false"
	 */
	Orgcode districtJsdw;

	/**
	 * @hibernate.many-to-one class="com.hzjava.monitorcenter.domain.Orgcode"
	 *                        lazy="false" column="spdw" insert="false"
	 *                        update="false"
	 */
    Orgcode districtSpdw;

	/**
	 * @hibernate.many-to-one class="com.hzjava.monitorcenter.domain.Orgcode"
	 *                        lazy="false" column="main_comp" insert="false"
	 *                        update="false"
	 */
    Orgcode districtMainComp;
	
	/**
	 * @hibernate.many-to-one class="com.hzjava.monitorcenter.domain.SysClass"
	 *                        lazy="false" column="system_class" insert="false"
	 *                        update="false"
	 */
	SysClass sysClass;
	

	public Jbqk() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFzrName() {
		return fzrName;
	}

	public void setFzrName(String fzrName) {
		this.fzrName = fzrName;
	}

	public String getFzrPhone() {
		return fzrPhone;
	}

	public void setFzrPhone(String fzrPhone) {
		this.fzrPhone = fzrPhone;
	}

	public String getFzrEmail() {
		return fzrEmail;
	}

	public void setFzrEmail(String fzrEmail) {
		this.fzrEmail = fzrEmail;
	}

	public String getFzrOhterPhone() {
		return fzrOhterPhone;
	}

	public void setFzrOhterPhone(String fzrOhterPhone) {
		this.fzrOhterPhone = fzrOhterPhone;
	}

	public String getJksysIp() {
		return jksysIp;
	}

	public void setJksysIp(String jksysIp) {
		this.jksysIp = jksysIp;
	}

	public String getZycjdw() {
		return zycjdw;
	}

	public void setZycjdw(String zycjdw) {
		this.zycjdw = zycjdw;
	}

	public Date getJsDay() {
		return jsDay;
	}

	public void setJsDay(Date jsDay) {
		this.jsDay = jsDay;
	}

	public String getBmxy() {
		return bmxy;
	}

	public void setBmxy(String bmxy) {
		this.bmxy = bmxy;
	}

	public Date getSpDay() {
		return spDay;
	}

	public void setSpDay(Date spDay) {
		this.spDay = spDay;
	}

	public String getSpph() {
		return spph;
	}

	public void setSpph(String spph) {
		this.spph = spph;
	}

	public String getMaintainerName() {
		return maintainerName;
	}

	public void setMaintainerName(String maintainerName) {
		this.maintainerName = maintainerName;
	}

	public String getMaintainerPhone() {
		return maintainerPhone;
	}

	public void setMaintainerPhone(String maintainerPhone) {
		this.maintainerPhone = maintainerPhone;
	}

	public String getMaintainerEmail() {
		return maintainerEmail;
	}

	public void setMaintainerEmail(String maintainerEmail) {
		this.maintainerEmail = maintainerEmail;
	}

	public String getMaintainerOhterPhone() {
		return maintainerOhterPhone;
	}

	public void setMaintainerOhterPhone(String maintainerOhterPhone) {
		this.maintainerOhterPhone = maintainerOhterPhone;
	}

	public Blob getSpcl() {
		return spcl;
	}

	public void setSpcl(Blob spcl) {
		this.spcl = spcl;
	}

	public Blob getTechnologySolution() {
		return technologySolution;
	}

	public void setTechnologySolution(Blob technologySolution) {
		this.technologySolution = technologySolution;
	}

	public Blob getConfidentialAgreement() {
		return confidentialAgreement;
	}

	public void setConfidentialAgreement(Blob confidentialAgreement) {
		this.confidentialAgreement = confidentialAgreement;
	}

	public Blob getPlatformTp() {
		return platformTp;
	}

	public void setPlatformTp(Blob platformTp) {
		this.platformTp = platformTp;
	}

	public String getSpclFileName() {
		return spclFileName;
	}

	public void setSpclFileName(String spclFileName) {
		this.spclFileName = spclFileName;
	}

	public String getTechnologySolutionFileName() {
		return technologySolutionFileName;
	}

	public void setTechnologySolutionFileName(String technologySolutionFileName) {
		this.technologySolutionFileName = technologySolutionFileName;
	}

	public String getConfidentialAgreementFileName() {
		return confidentialAgreementFileName;
	}

	public void setConfidentialAgreementFileName(
			String confidentialAgreementFileName) {
		this.confidentialAgreementFileName = confidentialAgreementFileName;
	}

	public String getPlatformTpFileName() {
		return platformTpFileName;
	}

	public void setPlatformTpFileName(String platformTpFileName) {
		this.platformTpFileName = platformTpFileName;
	}

	public String getJksysMac() {
		return jksysMac;
	}

	public void setJksysMac(String jksysMac) {
		this.jksysMac = jksysMac;
	}

	public Long getParentJsdw() {
		return Long.valueOf(this.getJsdw() / 10000) * 10000;
	}

	public void setParentJsdw(Long parentJsdw) {
		this.parentJsdw = parentJsdw;
	}

	public Long getParentSpdw() {
		return Long.valueOf(this.getSpdw() / 10000) * 10000;
	}

	public void setParentSpdw(Long parentSpdw) {
		this.parentSpdw = parentSpdw;
	}

	public Long getParentMainComp() {
		return Long.valueOf(this.getMainComp() / 10000) * 10000;
	}

	public void setParentMainComp(Long parentMainComp) {
		this.parentMainComp = parentMainComp;
	}

    public Orgcode getDistrictJsdw() {
        return districtJsdw;
    }

    public void setDistrictJsdw(Orgcode districtJsdw) {
        this.districtJsdw = districtJsdw;
    }

    public Orgcode getDistrictSpdw() {
        return districtSpdw;
    }

    public void setDistrictSpdw(Orgcode districtSpdw) {
        this.districtSpdw = districtSpdw;
    }

    public Orgcode getDistrictMainComp() {
        return districtMainComp;
    }

    public void setDistrictMainComp(Orgcode districtMainComp) {
        this.districtMainComp = districtMainComp;
    }

    public Long getJsdw() {
		return jsdw;
	}

	public void setJsdw(Long jsdw) {
		this.jsdw = jsdw;
	}

	public Long getMainComp() {
		return mainComp;
	}

	public void setMainComp(Long mainComp) {
		this.mainComp = mainComp;
	}

	public Long getSpdw() {
		return spdw;
	}

	public void setSpdw(Long spdw) {
		this.spdw = spdw;
	}

	public String getSystemClass() {
		return systemClass;
	}

	public void setSystemClass(String systemClass) {
		this.systemClass = systemClass;
	}

	public SysClass getSysClass() {
		return sysClass;
	}

	public void setSysClass(SysClass sysClass) {
		this.sysClass = sysClass;
	}
	
	

	// public static void main(String[] args){
	// System.out.println(Long.valueOf(110101/10000)*10000);
	// }

}
