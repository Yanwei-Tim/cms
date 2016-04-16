package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.IntLink;

public class IntLinkDaoImpl extends MyDaoSupport implements IntLinkDao {

	@Override
	public void setEntityClass() {
		this.entityClass = IntLink.class;
	}

	public PageResult listByPage(int pageIndex, int pageLength) {
		String hql = "from IntLink";
		String countHql = "select count(*) " + hql;
		return this.findByPage(hql, countHql, pageIndex, pageLength);
	}

}
