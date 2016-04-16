package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.ExchangePlatform;

public class ExchangePlatformDaoImpl extends MyDaoSupport implements
		ExchangePlatformDao {

	@Override
	public void setEntityClass() {
		this.entityClass = ExchangePlatform.class;
	}

	public PageResult listByPage(int pageIndex, int pageLength) {
		String hql = "from ExchangePlatform";
		String countHql = "select count(*) " + hql;
		return this.findByPage(hql, countHql, pageIndex, pageLength);
	}

}
