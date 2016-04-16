package com.hzjava.monitorcenter.svgmap;

/**
 * Created by IntelliJ IDEA.
 * User: cx
 * Date: 12-12-26
 * Time: 下午5:44
 * To change this template use File | Settings | File Templates.
 */
public class EquipmentVo {
    private int id;
    private String name;
    private String imag;
    private String ip;
    private String ex;
    private String ey;
    private String netStation;

    public EquipmentVo() {
    }

    public EquipmentVo(int id, String name, String imag, String ip, String ex, String ey,String netStation) {
        this.id = id;
        this.name = name;
        this.imag = imag;
        this.ip = ip;
        this.ex = ex;
        this.ey = ey;
        this.netStation = netStation;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImag() {
        return imag;
    }

    public void setImag(String imag) {
        this.imag = imag;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public String getNetStation() {
        return netStation;
    }

    public void setNetStation(String netStation) {
        this.netStation = netStation;
    }
}
