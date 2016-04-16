package com.hzjava.monitorcenter.dao;

import java.util.Date;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface SysLogDao extends BaseDao {
	/**
	 * 分页查询SysLog
	 * 
	 */
	public PageResult listLogsByParams(int pageIndex, int pageLength,
                                       Date startDate, Date endDate, String logLevel);

}
