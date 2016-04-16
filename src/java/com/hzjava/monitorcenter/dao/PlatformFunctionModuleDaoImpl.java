package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.PlatformFunctionModule;

public class PlatformFunctionModuleDaoImpl extends MyDaoSupport implements
		PlatformFunctionModuleDao {

	@Override
	public void setEntityClass() {
		this.entityClass = PlatformFunctionModule.class;
	}

}
