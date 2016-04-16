package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.MonitorAgent;

public class MonitorAgentDaoImpl extends MyDaoSupport implements
		MonitorAgentDao {

	@Override
	public void setEntityClass() {
		this.entityClass = MonitorAgent.class;
	}

	public PageResult listByPage(int pageIndex, int pageLength) {
		String hql = "from MonitorAgent";
		String countHql = "select count(*) " + hql;
		return this.findByPage(hql, countHql, pageIndex, pageLength);
	}

}
