package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.BaseDao;

import com.hzjava.monitorcenter.domain.Jbqk;

public interface JbqkDao extends BaseDao {

	Jbqk getJbqk()throws Exception;

	Object[] getBlob(Long id, String type)throws Exception;

    Jbqk getJbqkByIdsystem(String idsystem)throws Exception;

}
