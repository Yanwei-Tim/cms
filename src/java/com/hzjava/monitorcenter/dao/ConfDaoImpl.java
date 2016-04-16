package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.Conf;

public class ConfDaoImpl extends MyDaoSupport implements ConfDao {

	@Override
	public void setEntityClass() {
		this.entityClass = Conf.class;
	}

}
