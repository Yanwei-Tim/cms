package com.hzjava.monitorcenter.dao;

import java.util.List;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.SysClass;

public class SysClassDaoImpl extends MyDaoSupport implements SysClassDao {

	@Override
	public void setEntityClass() {
		this.entityClass=SysClass.class;
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<SysClass> findAll() {
		
		return super.findAll();
	}

}
