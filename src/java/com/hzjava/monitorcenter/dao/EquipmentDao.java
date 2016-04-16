package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

import java.util.List;

public interface EquipmentDao extends BaseDao {

	PageResult listByPage(int pageIndex, int pageLength);

    List list(String hql) throws Exception;
}
