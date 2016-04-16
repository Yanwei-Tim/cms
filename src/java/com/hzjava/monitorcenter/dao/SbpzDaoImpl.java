package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.Sbpz;

public class SbpzDaoImpl extends MyDaoSupport implements SbpzDao {

	@Override
	public void setEntityClass() {
		this.entityClass = Sbpz.class;
	}

	public PageResult listByPage(int pageIndex, int pageLength) {
		String hql = "from Sbpz order by id desc";
		return findByPage(hql, "select count(*) " + hql, pageIndex, pageLength);
	}

}
