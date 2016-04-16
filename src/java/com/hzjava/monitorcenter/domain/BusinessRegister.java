package com.hzjava.monitorcenter.domain;

import java.sql.Blob;
import java.util.Date;

/**
 * 业务注册表
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="business_register"
 */
public class BusinessRegister {
	/**
	 * @hibernate.id column="Id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;

	/**
	 * 业务系统名称
	 * 
	 * @hibernate.property column="business_name" type="java.lang.String"
	 */
	String businessName;

	/**
	 * 业务系统描述
	 * 
	 * @hibernate.property column="business_desc" type="java.lang.String"
	 *                     length="60"
	 */
	String businessDesc;

	/**
	 * 业务管理部门
	 * 
	 * @hibernate.property column="business_depart" type="java.lang.Long"
	 */
	Long businessDepart;
	
	/**
	 * @hibernate.many-to-one class="com.hzjava.monitorcenter.domain.District"
	 *                        column="business_depart" insert="false"
	 *                        update="false" lazy="false"
	 */
    Orgcode businessDepartDistrict;
	Long businessDepartParent;

	/**
	 * 业务类型
	 * 
	 * @hibernate.property column="business_code" type="java.lang.String"
	 */
	String businessCodeId;

	/**
	 * @hibernate.many-to-one class="com.hzjava.monitorcenter.domain.BusinessCode"
	 *                        column="business_code" insert="false"
	 *                        update="false" lazy="false"
	 */
    BusinessCode businessCode;

	/**
	 * 链路名
	 * 
	 * @hibernate.property column="link_name" type="java.lang.String"
	 */
	String linkName;

	/**
	 * 业务交换方式
	 * 
	 * @hibernate.property column="business_exch_model" type="java.lang.String"
	 */
	String businessExchModelId;
	/**
	 * @hibernate.many-to-one class="com.hzjava.monitorcenter.domain.BusinessExchModel"
	 *                        column="business_exch_model" insert="false"
	 *                        update="false" lazy="false"
	 */
	BusinessExchModel businessExchModel;

	/**
	 * 业务主管人
	 * 
	 * @hibernate.property column="business_admin" type="java.lang.String"
	 */
	String businessAdmin;

	/**
	 * 业务主管人电话
	 * 
	 * @hibernate.property column="admin_phone" type="java.lang.String"
	 */
	String adminPhone;

	/**
	 * 主管人邮件
	 * 
	 * @hibernate.property column="admin_email" type="java.lang.String"
	 */
	String adminEmail;

	/**
	 * 主管人其他联系方式
	 * 
	 * @hibernate.property column="admin_other_phone" type="java.lang.String"
	 */
	String adminOtherPhone;

	/**
	 * 审批部门
	 * 
	 * @hibernate.property column="auth_depart" type="java.lang.Long"
	 */
	Long authDepart;
	/**
	 * @hibernate.many-to-one class="com.hzjava.monitorcenter.domain.District"
	 *                        column="auth_depart" insert="false"
	 *                        update="false" lazy="false"
	 */
    Orgcode authDepartDistrict;
	Long authDepartParent;

	/**
	 * 审批时间
	 * 
	 * @hibernate.property column="auth_date" type="java.util.Date"
	 */
	Date authDate;

	/**
	 * 审批编号
	 * 
	 * @hibernate.property column="auth_serial" type="java.lang.String"
	 */
	String authSerial;

	/**
	 * 审批材料
	 * 
	 * @hibernate.property column="auth_material" type="java.sql.Blob"
	 */
	Blob authMaterial;

	/**
	 * @hibernate.property column="auth_material_file_name" type="string"
	 */
	String authMaterialFileName;

	/**
	 * 注册时间
	 * 
	 * @hibernate.property column="register_date" type="java.util.Date"
	 */
	Date registerDate;

	/**
	 * 交换方向
	 * 
	 * @hibernate.property column="exchange_direction" type="java.lang.String"
	 */
	String exchangeDirectionCode;
	/**
	 * @hibernate.many-to-one class="com.hzjava.monitorcenter.domain.ExchangeDirection"
	 *                        column="exchange_direction" insert="false"
	 *                        update="false" lazy="false"
	 */
	ExchangeDirection exchangeDirection;

	/**
	 * 业务交换协议
	 * 
	 * @hibernate.property column="exch_protocol" type="java.lang.String"
	 */
	String exchProtocolCode;

	/**
	 * @hibernate.many-to-one class="com.hzjava.monitorcenter.domain.ProtocolType"
	 *                        column="exch_protocol" insert="false"
	 *                        update="false" lazy="false"
	 */
	ProtocolType exchProtocol;

	/**
	 * 是否有实时性要求
	 * 
	 * @hibernate.property column="is_realtime" type="java.lang.Boolean"
	 */
	boolean isRealtime;

	/**
	 * 日数据交换量（M）
	 * 
	 * @hibernate.property column="day_datavolume" type="java.lang.Long"
	 */
	long dayDataVolume;

	/**
	 * 是否备案
	 * 
	 * @hibernate.property column="is_approved" type="java.lang.Boolean"
	 */
	boolean isApproved;

	/**
	 * 备案单位名称
	 * 
	 * @hibernate.property column="approved_depart" type="java.lang.Long"
	 */
	Long approvedDepart;
	/**
	 * @hibernate.many-to-one class="com.hzjava.monitorcenter.domain.District"
	 *                        column="approved_depart" insert="false"
	 *                        update="false" lazy="false"
	 */
    Orgcode approvedDepartDistrict;
	Long approvedDepartParent;

	/**
	 * 拓扑图
	 * 
	 * @hibernate.property column="tp_graph" type="java.sql.Blob"
	 */
	Blob tpGraph;

	/**
	 * @hibernate.property column="tp_graph_file_name" type="string"
	 */
	String tpGraphFileName;

	/**
	 * 业务协议名
	 * 
	 * @hibernate.property column="business_protocol" type="java.lang.String"
	 */
	String businessProtocol;

	/**
	 * 协议描述
	 * 
	 * @hibernate.property column="protocol_desc" type="java.lang.String"
	 */
	String protocolDesc;

	/**
	 * 源IP地址范围
	 * 
	 * @hibernate.property column="s_ip_range" type="java.lang.String"
	 */
	String sourceIpRange;

	/**
	 * 源端口范围
	 * 
	 * @hibernate.property column="s_port_range" type="java.lang.String"
	 */
	String sourcePortRange;

	/**
	 * 目标IP地址范围
	 * 
	 * @hibernate.property column="d_ip_range" type="java.lang.String"
	 */
	String destIpRange;

	/**
	 * 目标端口范围
	 * 
	 * @hibernate.property column="d_port_range" type="java.lang.String"
	 */
	String destPortRange;

	/**
	 * 使用单位
	 * 
	 * @hibernate.property column="use_depart" type="java.lang.Long"
	 */
	Long useDepart;
	
	/**
	 * @hibernate.many-to-one class="com.hzjava.monitorcenter.domain.District"
	 *                        column="use_depart" insert="false"
	 *                        update="false" lazy="false"
	 */
    Orgcode useDepartDistrict;
	Long useDepartParent;

	/**
	 * 使用单位类型
	 * 
	 * @hibernate.property column="use_depart_tpye" type="java.lang.String"
	 */
	String useDepartTypeId;
	/**
	 * @hibernate.many-to-one class="com.hzjava.monitorcenter.domain.UseDepartType"
	 *                        column="use_depart_tpye" insert="false"
	 *                        update="false" lazy="false"
	 */
	UseDepartType useDepartType;

	/**
	 * 使用单位物理地址
	 * 
	 * @hibernate.property column="use_depart_address" type="java.lang.String"
	 */
	String useDepartAddress;

	/**
	 * 使用单位主管人姓名
	 * 
	 * @hibernate.property column="use_depart_admin_name"
	 *                     type="java.lang.String"
	 */
	String useDepartAdminName;

	/**
	 * 使用单位主管人电话
	 * 
	 * @hibernate.property column="use_depart_admin_phone"
	 *                     type="java.lang.String"
	 */
	String useDepartAdminPhone;

	/**
	 * 使用单位主管人邮件
	 * 
	 * @hibernate.property column="use_depart_admin_email"
	 *                     type="java.lang.String"
	 */
	String useDepartAdminEmail;

	/**
	 * 其他联系方式
	 * 
	 * @hibernate.property column="use_depart_admin_other_phone"
	 *                     type="java.lang.String"
	 */
	String useDepartAdminOhterPhone;

	/**
	 * 终端数量
	 * 
	 * @hibernate.property column="terminal_amount" type="java.lang.Long"
	 */
	long terminalAmount;

	/**
	 * 用户数量
	 * 
	 * @hibernate.property column="user_amount" type="java.lang.Long"
	 */
	long userAmount;

	/**
	 * 允许上报 0：不允许上报 1：允许上报 默认0
	 * 
	 * @hibernate.property column="enablereport" type="java.lang.Boolean"
	 */
	boolean enablereport = false;

	public BusinessRegister() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getBusinessCodeId() {
		return businessCodeId;
	}

	public void setBusinessCodeId(String businessCodeId) {
		this.businessCodeId = businessCodeId;
	}

	public BusinessCode getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(BusinessCode businessCode) {
		this.businessCode = businessCode;
	}

	public String getBusinessExchModelId() {
		return businessExchModelId;
	}

	public void setBusinessExchModelId(String businessExchModelId) {
		this.businessExchModelId = businessExchModelId;
	}

	public BusinessExchModel getBusinessExchModel() {
		return businessExchModel;
	}

	public void setBusinessExchModel(BusinessExchModel businessExchModel) {
		this.businessExchModel = businessExchModel;
	}

	public String getBusinessAdmin() {
		return businessAdmin;
	}

	public void setBusinessAdmin(String businessAdmin) {
		this.businessAdmin = businessAdmin;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminOtherPhone() {
		return adminOtherPhone;
	}

	public void setAdminOtherPhone(String adminOtherPhone) {
		this.adminOtherPhone = adminOtherPhone;
	}

	public Date getAuthDate() {
		return authDate;
	}

	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}

	public String getAuthSerial() {
		return authSerial;
	}

	public void setAuthSerial(String authSerial) {
		this.authSerial = authSerial;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getExchangeDirectionCode() {
		return exchangeDirectionCode;
	}

	public void setExchangeDirectionCode(String exchangeDirectionCode) {
		this.exchangeDirectionCode = exchangeDirectionCode;
	}

	public ExchangeDirection getExchangeDirection() {
		return exchangeDirection;
	}

	public void setExchangeDirection(ExchangeDirection exchangeDirection) {
		this.exchangeDirection = exchangeDirection;
	}

	public String getExchProtocolCode() {
		return exchProtocolCode;
	}

	public void setExchProtocolCode(String exchProtocolCode) {
		this.exchProtocolCode = exchProtocolCode;
	}

	public ProtocolType getExchProtocol() {
		return exchProtocol;
	}

	public void setExchProtocol(ProtocolType exchProtocol) {
		this.exchProtocol = exchProtocol;
	}

	public boolean getIsRealtime() {
		return isRealtime;
	}

	public void setIsRealtime(boolean isRealtime) {
		this.isRealtime = isRealtime;
	}

	public long getDayDataVolume() {
		return dayDataVolume;
	}

	public void setDayDataVolume(long dayDataVolume) {
		this.dayDataVolume = dayDataVolume;
	}

	public boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Blob getAuthMaterial() {
		return authMaterial;
	}

	public void setAuthMaterial(Blob authMaterial) {
		this.authMaterial = authMaterial;
	}

	public Blob getTpGraph() {
		return tpGraph;
	}

	public String getAuthMaterialFileName() {
		return authMaterialFileName;
	}

	public void setAuthMaterialFileName(String authMaterialFileName) {
		this.authMaterialFileName = authMaterialFileName;
	}

	public String getTpGraphFileName() {
		return tpGraphFileName;
	}

	public void setTpGraphFileName(String tpGraphFileName) {
		this.tpGraphFileName = tpGraphFileName;
	}

	public void setTpGraph(Blob tpGraph) {
		this.tpGraph = tpGraph;
	}

	public String getBusinessProtocol() {
		return businessProtocol;
	}

	public void setBusinessProtocol(String businessProtocol) {
		this.businessProtocol = businessProtocol;
	}

	public String getProtocolDesc() {
		return protocolDesc;
	}

	public void setProtocolDesc(String protocolDesc) {
		this.protocolDesc = protocolDesc;
	}

	public String getSourceIpRange() {
		return sourceIpRange;
	}

	public void setSourceIpRange(String sourceIpRange) {
		this.sourceIpRange = sourceIpRange;
	}

	public String getSourcePortRange() {
		return sourcePortRange;
	}

	public void setSourcePortRange(String sourcePortRange) {
		this.sourcePortRange = sourcePortRange;
	}

	public String getDestIpRange() {
		return destIpRange;
	}

	public void setDestIpRange(String destIpRange) {
		this.destIpRange = destIpRange;
	}

	public String getDestPortRange() {
		return destPortRange;
	}

	public void setDestPortRange(String destPortRange) {
		this.destPortRange = destPortRange;
	}

	public String getUseDepartTypeId() {
		return useDepartTypeId;
	}

	public void setUseDepartTypeId(String useDepartTypeId) {
		this.useDepartTypeId = useDepartTypeId;
	}

	public UseDepartType getUseDepartType() {
		return useDepartType;
	}

	public void setUseDepartType(UseDepartType useDepartType) {
		this.useDepartType = useDepartType;
	}

	public String getUseDepartAddress() {
		return useDepartAddress;
	}

	public void setUseDepartAddress(String useDepartAddress) {
		this.useDepartAddress = useDepartAddress;
	}

	public String getUseDepartAdminName() {
		return useDepartAdminName;
	}

	public void setUseDepartAdminName(String useDepartAdminName) {
		this.useDepartAdminName = useDepartAdminName;
	}

	public String getUseDepartAdminPhone() {
		return useDepartAdminPhone;
	}

	public void setUseDepartAdminPhone(String useDepartAdminPhone) {
		this.useDepartAdminPhone = useDepartAdminPhone;
	}

	public String getUseDepartAdminEmail() {
		return useDepartAdminEmail;
	}

	public void setUseDepartAdminEmail(String useDepartAdminEmail) {
		this.useDepartAdminEmail = useDepartAdminEmail;
	}

	public String getUseDepartAdminOhterPhone() {
		return useDepartAdminOhterPhone;
	}

	public void setUseDepartAdminOhterPhone(String useDepartAdminOhterPhone) {
		this.useDepartAdminOhterPhone = useDepartAdminOhterPhone;
	}

	public long getTerminalAmount() {
		return terminalAmount;
	}

	public void setTerminalAmount(long terminalAmount) {
		this.terminalAmount = terminalAmount;
	}

	public long getUserAmount() {
		return userAmount;
	}

	public void setUserAmount(long userAmount) {
		this.userAmount = userAmount;
	}

	public Long getBusinessDepart() {
		return businessDepart;
	}

	public void setBusinessDepart(Long businessDepart) {
		this.businessDepart = businessDepart;
	}

	public Long getAuthDepart() {
		return authDepart;
	}

	public void setAuthDepart(Long authDepart) {
		this.authDepart = authDepart;
	}

	public Long getApprovedDepart() {
		return approvedDepart;
	}

	public void setApprovedDepart(Long approvedDepart) {
		this.approvedDepart = approvedDepart;
	}

	public Long getUseDepart() {
		return useDepart;
	}

	public void setUseDepart(Long useDepart) {
		this.useDepart = useDepart;
	}

	public String getBusinessDesc() {
		return businessDesc;
	}

	public void setBusinessDesc(String businessDesc) {
		this.businessDesc = businessDesc;
	}

	public boolean isEnablereport() {
		return enablereport;
	}

	public void setEnablereport(boolean enablereport) {
		this.enablereport = enablereport;
	}

	public void setRealtime(boolean isRealtime) {
		this.isRealtime = isRealtime;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Long getBusinessDepartParent() {
		Long l = Long.valueOf(this.getBusinessDepart() / 10000) * 10000;
		return l;
	}

	public void setBusinessDepartParent(Long businessDepartParent) {
		this.businessDepartParent = businessDepartParent;
	}

	public Long getAuthDepartParent() {
		return Long.valueOf(this.getAuthDepart() / 10000) * 10000;
	}

	public void setAuthDepartParent(Long authDepartParent) {
		this.authDepartParent = authDepartParent;
	}

	public Long getApprovedDepartParent() {
		return Long.valueOf(this.getApprovedDepart() / 10000) * 10000;
	}

	public void setApprovedDepartParent(Long approvedDepartParent) {
		this.approvedDepartParent = approvedDepartParent;
	}

	public Long getUseDepartParent() {
		return Long.valueOf(this.getUseDepart() / 10000) * 10000;
	}

	public void setUseDepartParent(Long useDepartParent) {
		this.useDepartParent = useDepartParent;
	}

	/*public District getBusinessDepartDistrict() {
		return businessDepartDistrict;
	}

	public void setBusinessDepartDistrict(District businessDepartDistrict) {
		this.businessDepartDistrict = businessDepartDistrict;
	}

	public District getAuthDepartDistrict() {
		return authDepartDistrict;
	}

	public void setAuthDepartDistrict(District authDepartDistrict) {
		this.authDepartDistrict = authDepartDistrict;
	}

	public District getApprovedDepartDistrict() {
		return approvedDepartDistrict;
	}

	public void setApprovedDepartDistrict(District approvedDepartDistrict) {
		this.approvedDepartDistrict = approvedDepartDistrict;
	}

	public District getUseDepartDistrict() {
		return useDepartDistrict;
	}

	public void setUseDepartDistrict(District useDepartDistrict) {
		this.useDepartDistrict = useDepartDistrict;
	}*/

    public Orgcode getBusinessDepartDistrict() {
        return businessDepartDistrict;
    }

    public void setBusinessDepartDistrict(Orgcode businessDepartDistrict) {
        this.businessDepartDistrict = businessDepartDistrict;
    }

    public Orgcode getAuthDepartDistrict() {
        return authDepartDistrict;
    }

    public void setAuthDepartDistrict(Orgcode authDepartDistrict) {
        this.authDepartDistrict = authDepartDistrict;
    }

    public Orgcode getApprovedDepartDistrict() {
        return approvedDepartDistrict;
    }

    public void setApprovedDepartDistrict(Orgcode approvedDepartDistrict) {
        this.approvedDepartDistrict = approvedDepartDistrict;
    }

    public Orgcode getUseDepartDistrict() {
        return useDepartDistrict;
    }

    public void setUseDepartDistrict(Orgcode useDepartDistrict) {
        this.useDepartDistrict = useDepartDistrict;
    }
}
