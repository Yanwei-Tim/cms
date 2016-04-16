package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.ParentExchangePlatform;

public class ParentExchangePlatformDaoImpl extends MyDaoSupport implements
		ParentExchangePlatformDao {

	@Override
	public void setEntityClass() {
		this.entityClass = ParentExchangePlatform.class;
	}

	public PageResult listByPage(int pageIndex, int pageLength) {
		String hql = "from ParentExchangePlatform";
		String countHql = "select count(*) " + hql;
		return this.findByPage(hql, countHql, pageIndex, pageLength);
	}

}
