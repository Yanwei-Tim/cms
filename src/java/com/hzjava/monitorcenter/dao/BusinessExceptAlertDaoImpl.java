package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.BusinessExceptAlert;

public class BusinessExceptAlertDaoImpl extends MyDaoSupport implements
		BusinessExceptAlertDao {

	@Override
	public void setEntityClass() {
		this.entityClass = BusinessExceptAlert.class;
	}

}
