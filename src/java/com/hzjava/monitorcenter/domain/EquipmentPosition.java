package com.hzjava.monitorcenter.domain;

/**
 * Created by IntelliJ IDEA.
 * User: cx
 * Date: 12-12-26
 * Time: 下午3:11
 * To change this template use File | Settings | File Templates.
 */
public class EquipmentPosition {
    private int id;
    private int equipmentid;
    private String ex;
    private String ey;

    public EquipmentPosition() {
    }

    public EquipmentPosition(int equipmentid, String ex, String ey) {
        this.equipmentid = equipmentid;
        this.ex = ex;
        this.ey = ey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipmentid() {
        return equipmentid;
    }

    public void setEquipmentid(int equipmentid) {
        this.equipmentid = equipmentid;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    public String getEy() {
        return ey;
    }

    public void setEy(String ey) {
        this.ey = ey;
    }
}
