package com.hzjava.monitorcenter.domain;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-24
 * Time: 下午4:40
 * To change this template use File | Settings | File Templates.
 */
public class EquipmentLog {
    private Long Id ;
	private Date LogTime ;
	private String Level ;
	private String LinkName ;
	private String EquipmentName ;
	private String LogInfo ;
	private String LinkProperty ;
	private String LinkType ;
	private String LinkCorp ;
	private String LinkSecurity ;
	private Long LinkAmount ;
	private Long LinkBandwidth ;
	private String OtherSecurity ;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Date getLogTime() {
        return LogTime;
    }

    public void setLogTime(Date logTime) {
        LogTime = logTime;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getLinkName() {
        return LinkName;
    }

    public void setLinkName(String linkName) {
        LinkName = linkName;
    }

    public String getEquipmentName() {
        return EquipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        EquipmentName = equipmentName;
    }

    public String getLogInfo() {
        return LogInfo;
    }

    public void setLogInfo(String logInfo) {
        LogInfo = logInfo;
    }

    public String getLinkProperty() {
        return LinkProperty;
    }

    public void setLinkProperty(String linkProperty) {
        LinkProperty = linkProperty;
    }

    public String getLinkType() {
        return LinkType;
    }

    public void setLinkType(String linkType) {
        LinkType = linkType;
    }

    public String getLinkCorp() {
        return LinkCorp;
    }

    public void setLinkCorp(String linkCorp) {
        LinkCorp = linkCorp;
    }

    public String getLinkSecurity() {
        return LinkSecurity;
    }

    public void setLinkSecurity(String linkSecurity) {
        LinkSecurity = linkSecurity;
    }

    public Long getLinkAmount() {
        return LinkAmount;
    }

    public void setLinkAmount(Long linkAmount) {
        LinkAmount = linkAmount;
    }

    public Long getLinkBandwidth() {
        return LinkBandwidth;
    }

    public void setLinkBandwidth(Long linkBandwidth) {
        LinkBandwidth = linkBandwidth;
    }

    public String getOtherSecurity() {
        return OtherSecurity;
    }

    public void setOtherSecurity(String otherSecurity) {
        OtherSecurity = otherSecurity;
    }
}
