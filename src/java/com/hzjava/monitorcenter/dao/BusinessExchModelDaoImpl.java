package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.BusinessExchModel;

public class BusinessExchModelDaoImpl extends MyDaoSupport implements
		BusinessExchModelDao {

	@Override
	public void setEntityClass() {
		this.entityClass = BusinessExchModel.class;
	}

}
