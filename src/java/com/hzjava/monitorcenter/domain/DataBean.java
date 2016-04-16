package com.hzjava.monitorcenter.domain;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-13
 * Time: 下午5:36
 * To change this template use File | Settings | File Templates.
 */


import java.io.Serializable;

public class DataBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String            name             = "";
    private int               visits           = 0;
    private int               views            = 0;

    public DataBean (String name, int visits, int views) {
        this.name = name;
        this.views = views;
        this.visits = visits;
    }

    public DataBean(){}

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public int getVisits () {
        return visits;
    }

    public void setVisits ( int visits ) {
        this.visits = visits;
    }

    public int getViews () {
        return views;
    }

    public void setViews ( int views ) {
        this.views = views;
    }
}
