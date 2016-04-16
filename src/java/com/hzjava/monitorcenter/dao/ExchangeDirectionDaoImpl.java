package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.ExchangeDirection;

public class ExchangeDirectionDaoImpl extends MyDaoSupport implements
		ExchangeDirectionDao {

	@Override
	public void setEntityClass() {
		this.entityClass = ExchangeDirection.class;
	}

}
