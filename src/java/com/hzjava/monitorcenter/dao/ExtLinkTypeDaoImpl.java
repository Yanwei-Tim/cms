package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.ExtLinkType;

public class ExtLinkTypeDaoImpl extends MyDaoSupport implements ExtLinkTypeDao {

	@Override
	public void setEntityClass() {
		this.entityClass = ExtLinkType.class;
	}

}
