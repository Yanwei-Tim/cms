package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface ProvinceExchangePlatformDao extends BaseDao {

	PageResult listByPage(int pageIndex, int pageLength);

}
