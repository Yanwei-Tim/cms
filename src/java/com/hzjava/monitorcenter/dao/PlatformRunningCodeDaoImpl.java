package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.PlatformRunningCode;

public class PlatformRunningCodeDaoImpl extends MyDaoSupport implements
		PlatformRunningCodeDao {

	@Override
	public void setEntityClass() {
		this.entityClass = PlatformRunningCode.class;
	}

}
