package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.BusinessReport;

public class BusinessReportDaoImpl extends MyDaoSupport implements
		BusinessReportDao {

	@Override
	public void setEntityClass() {
		this.entityClass = BusinessReport.class;
	}

}
