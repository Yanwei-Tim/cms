package com.hzjava.monitorcenter.dao;

import java.util.List;

import com.hzjava.monitorcenter.domain.SysClass;

import cn.collin.commons.dao.BaseDao;

public interface SysClassDao extends BaseDao {
	
	List<SysClass> findAll();
	
	
}
