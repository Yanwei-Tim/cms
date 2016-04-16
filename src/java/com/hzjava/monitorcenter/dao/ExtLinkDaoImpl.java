package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.ExtLink;

public class ExtLinkDaoImpl extends MyDaoSupport implements ExtLinkDao {

	@Override
	public void setEntityClass() {
		this.entityClass = ExtLink.class;
	}

	public PageResult listByPage(int pageIndex, int pageLength) {
		String hql = "from ExtLink";
		String countHql = "select count(*) " + hql;
		return this.findByPage(hql, countHql, pageIndex, pageLength);
	}

}
