package com.hzjava.monitorcenter.dao;

import java.util.Date;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface UserOperLogDao extends BaseDao {
	/**
	 * 分页查询
	 * 
	 */
	public PageResult listLogsByParams(int pageIndex, int pageLength,
                                       Date startDate, Date endDate, String logLevel, String userName);
}
