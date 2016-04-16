package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface BusinessRegisterDao extends BaseDao {

	PageResult listByPage(int pageIndex, int pageLength);

	Object[] getBlob(Long id, String type);

}
