package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.BusinessCode;

public class BusinessCodeDaoImpl extends MyDaoSupport implements
		BusinessCodeDao {

	@Override
	public void setEntityClass() {
		this.entityClass = BusinessCode.class;
	}

}
