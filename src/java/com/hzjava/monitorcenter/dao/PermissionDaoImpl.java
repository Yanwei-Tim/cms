package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.Permission;

public class PermissionDaoImpl extends MyDaoSupport implements PermissionDao {

	@Override
	public void setEntityClass() {
		this.entityClass = Permission.class;
	}

}
