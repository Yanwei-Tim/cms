package com.hzjava.monitorcenter.service;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-24
 * Time: 下午4:54
 * To change this template use File | Settings | File Templates.
 */
public interface EquipmentStatusService {
    String getAll(int start, int limit);
    public String selectStatusByTime_1(int pageIndex, int limit, Date startDate, Date endDate,
                                       String logLevel, String deviceName)throws Exception;
    public String selectStatusByTime_2(int pageIndex, int limit, Date startDate, Date endDate,
                                       String logLevel, String deviceName)throws Exception;
    public String selectStatusByTime_3(int pageIndex, int limit, Date startDate, Date endDate,
                                       String logLevel, String deviceName)throws Exception;
    public String selectStatusByTime_4(int pageIndex, int limit, Date startDate, Date endDate,
                                       String logLevel, String deviceName)throws Exception;
    public String selectStatusByTime(int pageIndex, int limit, Date startDate, Date endDate,
                                       String logLevel, String deviceName)throws Exception;
}
