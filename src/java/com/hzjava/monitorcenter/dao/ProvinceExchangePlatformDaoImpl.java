package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.ProvinceExchangePlatform;

public class ProvinceExchangePlatformDaoImpl extends MyDaoSupport implements
		ProvinceExchangePlatformDao {

	@Override
	public void setEntityClass() {
		this.entityClass = ProvinceExchangePlatform.class;
	}

	public PageResult listByPage(int pageIndex, int pageLength) {
		String hql = "from ProvinceExchangePlatform";
		String countHql = "select count(*) " + hql;
		return this.findByPage(hql, countHql, pageIndex, pageLength);
	}

}
