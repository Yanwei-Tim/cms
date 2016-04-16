package com.hzjava.monitorcenter.domain;

/**
 * Created by IntelliJ IDEA.
 * User: cx
 * Date: 12-12-27
 * Time: 上午10:04
 * To change this template use File | Settings | File Templates.
 */
public class EquipmentLine {
    private int id;
    private String name;
    private String x1;
    private String y1;
    private String x2;
    private String y2;

    public EquipmentLine() {
    }

    public EquipmentLine(String name, String x1, String y1, String x2, String y2) {
        this.name = name;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
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

    public String getX1() {
        return x1;
    }

    public void setX1(String x1) {
        this.x1 = x1;
    }

    public String getY1() {
        return y1;
    }

    public void setY1(String y1) {
        this.y1 = y1;
    }

    public String getX2() {
        return x2;
    }

    public void setX2(String x2) {
        this.x2 = x2;
    }

    public String getY2() {
        return y2;
    }

    public void setY2(String y2) {
        this.y2 = y2;
    }
}
