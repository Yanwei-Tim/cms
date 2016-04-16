package com.hzjava.monitorcenter.domain;

import java.util.Date;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-24
 * Time: 下午4:40
 * To change this template use File | Settings | File Templates.
 */
public class EquipmentStatus {
    private Long id;
    private Long deviceId;
    private String status;
    private String type;
    private Date createtime;
//    Set<EquipmentLog> equipmentLogs;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

//    public Set<EquipmentLog> getEquipmentLogs() {
//        return equipmentLogs;
//    }
//
//    public void setEquipmentLogs(Set<EquipmentLog> equipmentLogs) {
//        this.equipmentLogs = equipmentLogs;
//    }
}
