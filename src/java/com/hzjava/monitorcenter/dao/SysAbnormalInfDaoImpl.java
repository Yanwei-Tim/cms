package com.hzjava.monitorcenter.dao;

import java.util.ArrayList;
import java.util.List;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.SysAbnormalInf;

public class SysAbnormalInfDaoImpl extends MyDaoSupport implements SysAbnormalInfDao{
		
	public void setEntityClass() {
		this.entityClass = SysAbnormalInf.class;		
	}
	
	public int getCount() {
		String hql = "select count(*) from SysAbnormalInf";
		List list = getHibernateTemplate().find(hql);
		int count = 0;
		for (Object object : list) {
			count = Integer.parseInt(""+object);
		}
		return count;
	}
	
	public PageResult listByPage(int pageIndex,int limit) {
		String hql = "from SysAbnormalInf where 1=1 ";
		List paramsList = new ArrayList();
		String countHql = "select count(*) " + hql;
		PageResult ps = this.findByPage(hql, countHql, paramsList.toArray(),
				pageIndex, limit);
		return ps;
	}
	
}
