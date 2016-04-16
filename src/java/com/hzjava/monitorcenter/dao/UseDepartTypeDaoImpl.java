package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.UseDepartType;

public class UseDepartTypeDaoImpl extends MyDaoSupport implements
		UseDepartTypeDao {

	@Override
	public void setEntityClass() {
		this.entityClass = UseDepartType.class;
	}

}
