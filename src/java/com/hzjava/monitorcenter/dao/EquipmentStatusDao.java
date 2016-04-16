package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-24
 * Time: 下午4:50
 * To change this template use File | Settings | File Templates.
 */
public interface EquipmentStatusDao extends BaseDao{
    PageResult listByPage(String userName, String status, int pageIndex, int limit);
    PageResult listByParams(int pageIndex, int pageLength,
                            Date startDate, Date endDate , String deviceName);
    PageResult listByParams_1(int pageIndex, int pageLength,
                              Date startDate, Date endDate,String deviceName);
    PageResult listByParams_2(int pageIndex, int pageLength,
                              Date startDate, Date endDate,String deviceName);
    PageResult listByParams_3(int pageIndex, int pageLength,
                              Date startDate, Date endDate,String deviceName);
    PageResult listByParams_4(int pageIndex, int pageLength,
                              Date startDate, Date endDate,String deviceName);
}

