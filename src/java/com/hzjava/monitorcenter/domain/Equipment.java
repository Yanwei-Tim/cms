package com.hzjava.monitorcenter.domain;

import java.util.Date;

/**
 * 设备表
 * 
 * @author collin.code@gmail.com
 * @hibernate.class table="equipment"
 */
public class Equipment {
	/**
	 * @hibernate.id column="Id" generator-class="identity"
	 *               type="java.lang.Long"
	 */
	Long id;

	/**
	 * 设备名称
	 * 
	 * @hibernate.property column="equ_name" type="java.lang.String"
	 */
	String equName;

	/**
	 * 设备类型
	 * 
	 * @hibernate.property column="equ_type" type="java.lang.String"
	 */
	String equTypeCode;

	/**
	 * @hibernate.many-to-one class="com.hzjava.monitorcenter.domain.EquipmentType"
	 *                        column="equ_type" insert="false" update="false"
	 *                        lazy="false"
	 */
	EquipmentType equType;

	/**
	 * 设备图标
	 * 
	 * @hibernate.property column="equ_Icon_code" type="java.lang.String"
	 */
	String equIconCode;

	/**
	 * 网络位置 说明：e为外网，i为内网
	 * 
	 * @hibernate.property column="net_station" type="java.lang.String"
	 */
	String netStation;

	/**
	 * 是否开启监控 说明：N未开启，Y开启
	 * 
	 * @hibernate.property column="monitor_used" type="java.lang.String"
	 */
	String monitorUsed;
	/**
	 * 是否核心设备 说明：1是，0否
	 * 
	 * @hibernate.property column="is_key_device" type="java.lang.String"
	 */
	String isKeyDevice;

	/**
	 * IP地址
	 * 
	 * @hibernate.property column="ip" type="java.lang.String"
	 */
	String ip;

	/**
	 * 次选IP
	 * 
	 * @hibernate.property column="other_ip" type="java.lang.String"
	 */
	String otherIp;

	/**
	 * MAC地址
	 * 
	 * @hibernate.property column="MAC" type="java.lang.String"
	 */
	String mac;

	/**
	 * 子网掩码
	 * 
	 * @hibernate.property column="subnet_mask" type="java.lang.String"
	 */
	String subnetMask;

	/**
	 * 链路位置 说明：外部链路e,内部链路i
	 * 
	 * @hibernate.property column="int_or_ext" type="java.lang.String"
	 */
	String inrOrExt;

	/**
	 * 链路 说明：对应ext_link或者int_link表link_name字段,根据int_or_ext判断
	 * 
	 * @hibernate.property column="link_name" type="java.lang.String"
	 */
	String linkName;

	/**
	 * 设备位置
	 * 
	 * @hibernate.property column="equ_station" type="java.lang.String"
	 */
	String equStation;

	/**
	 * 设备硬件配置
	 * 
	 * @hibernate.property column="equ_info" type="java.lang.String"
	 */
	String equInfo;

	/**
	 * 生产厂家
	 * 
	 * @hibernate.property column="manufacturer" type="java.lang.String"
	 */
	String manufacturer;

	/**
	 * 产品型号
	 * 
	 * @hibernate.property column="model" type="java.lang.String"
	 */
	String model;

	/**
	 * 供货商
	 * 
	 * @hibernate.property column="provider" type="java.lang.String"
	 */
	String provider;

	/**
	 * 联系电话
	 * 
	 * @hibernate.property column="equ_phone" type="java.lang.String"
	 */
	String equPhone;

	/**
	 * 其他联系方式
	 * 
	 * @hibernate.property column="other_phone" type="java.lang.String"
	 */
	String otherPhone;

	/**
	 * 购买日期
	 * 
	 * @hibernate.property column="buy_day" type="java.util.Date"
	 */
	Date buyDay;

	/**
	 * 过保日期
	 * 
	 * @hibernate.property column="unrepair_day" type="java.util.Date"
	 */
	Date unrepairDay;
	
	/**
	 * 设备系统配置文件
	 * 
	 * @hibernate.property column="equSysConfig" type="java.lang.String"
	 */
	String equSysConfig;
	
	/**
	 * 设备管理单位
	 * 
	 * @hibernate.property column="equManagerDepart" type="java.lang.String"
	 */
	String equManagerDepart;


    /**
     *
     * snmp服务端口号
     * @return
     */
    String equPort;

    /**
     *
     * snmp密码
     * @return
     */
    String equSnmpPwd;

    /**
     *
     * snmp版本
     * @return
     */
    
    String equSnmpver;


    String equOidName;

    
    //拓扑图位置
    String topologyStation;

    String equId;

    public String getEquId() {
        return equId;
    }

    public void setEquId(String equId) {
        this.equId = equId;
    }

    public String getEquOidName() {
        return equOidName;
    }

    public void setEquOidName(String equOidName) {
        this.equOidName = equOidName;
    }

    public Long getId() {
		return id;
	}

	public String getIsKeyDevice() {
		return isKeyDevice;
	}

	public void setIsKeyDevice(String isKeyDevice) {
		this.isKeyDevice = isKeyDevice;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEquName() {
		return equName;
	}

	public void setEquName(String equName) {
		this.equName = equName;
	}

	public String getEquTypeCode() {
		return equTypeCode;
	}

	public void setEquTypeCode(String equTypeCode) {
		this.equTypeCode = equTypeCode;
	}

	public EquipmentType getEquType() {
		return equType;
	}

	public void setEquType(EquipmentType equType) {
		this.equType = equType;
	}

	public String getEquIconCode() {
		return equIconCode;
	}

	public void setEquIconCode(String equIconCode) {
		this.equIconCode = equIconCode;
	}

	public String getNetStation() {
		return netStation;
	}

	public void setNetStation(String netStation) {
		this.netStation = netStation;
	}

	public String getMonitorUsed() {
		return monitorUsed;
	}

	public void setMonitorUsed(String monitorUsed) {
		this.monitorUsed = monitorUsed;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOtherIp() {
		return otherIp;
	}

	public void setOtherIp(String otherIp) {
		this.otherIp = otherIp;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getSubnetMask() {
		return subnetMask;
	}

	public void setSubnetMask(String subnetMask) {
		this.subnetMask = subnetMask;
	}

	public String getInrOrExt() {
		return inrOrExt;
	}

	public void setInrOrExt(String inrOrExt) {
		this.inrOrExt = inrOrExt;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getEquStation() {
		return equStation;
	}

	public void setEquStation(String equStation) {
		this.equStation = equStation;
	}

	public String getEquInfo() {
		return equInfo;
	}

	public void setEquInfo(String equInfo) {
		this.equInfo = equInfo;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getEquPhone() {
		return equPhone;
	}

	public void setEquPhone(String equPhone) {
		this.equPhone = equPhone;
	}

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	public Date getBuyDay() {
		return buyDay;
	}

	public void setBuyDay(Date buyDay) {
		this.buyDay = buyDay;
	}

	public Date getUnrepairDay() {
		return unrepairDay;
	}

	public void setUnrepairDay(Date unrepairDay) {
		this.unrepairDay = unrepairDay;
	}

	public Equipment() {

	}

	public String getEquSysConfig() {
		return equSysConfig;
	}

	public void setEquSysConfig(String equSysConfig) {
		this.equSysConfig = equSysConfig;
	}

	public String getEquManagerDepart() {
		return equManagerDepart;
	}

	public void setEquManagerDepart(String equManagerDepart) {
		this.equManagerDepart = equManagerDepart;
	}

    public String getKeyDevice() {
        return isKeyDevice;
    }

    public void setKeyDevice(String keyDevice) {
        isKeyDevice = keyDevice;
    }

    public String getEquPort() {
        return equPort;
    }

    public void setEquPort(String equPort) {
        this.equPort = equPort;
    }

    public String getEquSnmpPwd() {
        return equSnmpPwd;
    }

    public void setEquSnmpPwd(String equSnmpPwd) {
        this.equSnmpPwd = equSnmpPwd;
    }

    public String getEquSnmpver() {
        return equSnmpver;
    }

    public void setEquSnmpver(String equSnmpver) {
        this.equSnmpver = equSnmpver;
    }

    public String getTopologyStation() {
        return topologyStation;
    }

    public void setTopologyStation(String topologyStation) {
        this.topologyStation = topologyStation;
    }
}
