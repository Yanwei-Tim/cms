package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.ExtLinkProperty;

public class ExtLinkPropertyDaoImpl extends MyDaoSupport implements
		ExtLinkPropertyDao {

	@Override
	public void setEntityClass() {
		this.entityClass = ExtLinkProperty.class;
	}

}
