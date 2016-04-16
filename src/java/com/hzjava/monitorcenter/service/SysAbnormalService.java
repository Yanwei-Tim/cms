package com.hzjava.monitorcenter.service;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-7
 * Time: 下午4:24
 * To change this template use File | Settings | File Templates.
 */
public interface SysAbnormalService {
    public String findAllBySysAbnormal()throws Exception;
    public String findAll(String idsystem,String username)throws Exception;
    /**
     * 违规日统计图
     */
    public String listReportCountDay(String reportDate)throws Exception;
    /**
     * 违规月统计图
     */
    public String listReportCountMonth(String reportDate)throws Exception;
    /**
     * 违规年统计图
     */
    public String listReportCountYear(String reportDate)throws Exception;
    /**
     * 违规日统计
     */
    public String listReportDay(String reportDate)throws Exception;
    /**
     * 违规月统计
     */
    public String listReportMonth(String reportDate)throws Exception;
    /**
     * 违规年统计
     */
    public String listReportYear(String reportDate)throws Exception;
    /**
     * 违规月统计线形图
     */
    public String lineChartMonth(String reportDate)throws Exception;
    /**
     * 违规年统计线形图
     */
    public String lineChartYear(String reportDate)throws Exception;
}
