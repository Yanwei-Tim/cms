package com.hzjava.monitorcenter.dao;

import java.util.Date;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface LowerSysbizstatusDao extends BaseDao {
	public PageResult findPage(int pageIndex, int pageLength,
                               Date startDate, Date endDate, String idSystem);
}
