package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface TenimalDao extends BaseDao {
	public PageResult findPage(int pageIndex, int pageLength, String cardType,
                               String state, String blockType, String RadioPolice, String police);
	public PageResult loadById(Long id);
}
