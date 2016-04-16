package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.ProtocolType;

public class ProtocolTypeDaoImpl extends MyDaoSupport implements
		ProtocolTypeDao {

	@Override
	public void setEntityClass() {
		this.entityClass = ProtocolType.class;
	}

}
