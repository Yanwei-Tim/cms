package com.hzjava.monitorcenter.service;

import java.util.List;

import com.hzjava.monitorcenter.dao.SysClassDao;
import com.hzjava.monitorcenter.domain.SysClass;

public class SysClassServiceImpl implements SysClassService{
	private SysClassDao sysClassDao;
	
	public SysClassDao getSysClassDao() {
		return sysClassDao;
	}


	public void setSysClassDao(SysClassDao sysClassDao) {
		this.sysClassDao = sysClassDao;
	}


	public List<SysClass> findAll() {
		
		return sysClassDao.findAll();
	}

}
