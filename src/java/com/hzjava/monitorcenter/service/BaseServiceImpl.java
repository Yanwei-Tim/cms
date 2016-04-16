package com.hzjava.monitorcenter.service;

import java.io.Serializable;

import cn.collin.commons.dao.BaseDao;

/**
 * service基类，通用方法通过继承此类即可 子类需要设置dao变量为对应的dao
 * 
 * @author xiangqi
 * 
 */
public class BaseServiceImpl implements BaseService {
	protected BaseDao dao;

	protected BaseDao getDao() {
		return dao;
	}

	protected void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public void saveObject(Object obj) {
		getDao().create(obj);
	}

	public void delete(Serializable[] ids) {
		getDao().delete(ids);
	}

	public void update(Object obj) {
		getDao().update(obj);
	}

}
