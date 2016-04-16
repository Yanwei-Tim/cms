package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.Role;

public class RoleDaoImpl extends MyDaoSupport implements RoleDao {

	@Override
	public void setEntityClass() {
		this.entityClass = Role.class;
	}

}
