package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface SysControlRulesInfDao extends BaseDao {
	
	public PageResult listByPage(int pageIndex, int limit);

	int getCount();
	
	
}
