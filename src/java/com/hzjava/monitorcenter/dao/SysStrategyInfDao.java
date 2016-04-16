package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface SysStrategyInfDao extends BaseDao {
	
	PageResult listByPage(int pageIndex, int limit);

	int getCount();
	
	
}
