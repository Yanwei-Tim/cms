package com.hzjava.monitorcenter.dao;

import com.hzjava.monitorcenter.domain.SysabnormalObjectType;

import cn.collin.commons.dao.MyDaoSupport;

public class SysabnormalObjectTypeDaoImpl extends MyDaoSupport implements
		SysabnormalObjectTypeDao {

	@Override
	public void setEntityClass() {
		this.entityClass = SysabnormalObjectType.class;
	}

}
