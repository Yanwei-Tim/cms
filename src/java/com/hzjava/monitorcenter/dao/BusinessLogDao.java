package com.hzjava.monitorcenter.dao;

import java.util.Date;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface BusinessLogDao extends BaseDao {

	/**
	 * 分页查询BusinessLog
	 * 
	 * @return
	 */
	public PageResult listLogsByParams(int pageIndex, int pageLength,
                                       Date startDate, Date endDate, String logLevel, String businessName);

    void truncate() throws Exception;
}
