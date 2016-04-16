package com.hzjava.monitorcenter.dao;

import java.util.Date;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface EquipmentLogDao extends BaseDao {
	/**
	 * 分页查询EquipmentLog
	 * 
	 */
	public PageResult listLogsByParams(int pageIndex, int pageLength,
                                       Date startDate, Date endDate, String logLevel, String equipmentName);

    void truncate() throws Exception;

    public boolean findByDate(Date date, String diskMsg);
}
