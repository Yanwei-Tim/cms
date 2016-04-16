package com.hzjava.monitorcenter.dao;

import java.util.ArrayList;
import java.util.List;

import com.hzjava.monitorcenter.domain.SysControlRulesInf;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

public class SysControlRulesInfDaoImpl extends MyDaoSupport implements SysControlRulesInfDao{

	
	public void setEntityClass() {
		this.entityClass = SysControlRulesInf.class;
	}

	public int getCount() {
		String hql = "select count(*) from SysControlRulesInf";
		List list = getHibernateTemplate().find(hql);
		int count = 0;
		for (Object object : list) {
			count = Integer.parseInt(""+object);
		}
		return count;
	}
	
	public PageResult listByPage(int pageIndex,int limit) {
		String hql = "from SysControlRulesInf where 1=1 ";
		List paramsList = new ArrayList();
		String countHql = "select count(*) " + hql;
		PageResult ps = this.findByPage(hql, countHql, paramsList.toArray(),
				pageIndex, limit);
		return ps;
	}

}
