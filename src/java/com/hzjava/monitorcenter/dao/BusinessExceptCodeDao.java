package com.hzjava.monitorcenter.dao;

import java.util.List;

import cn.collin.commons.dao.BaseDao;

public interface BusinessExceptCodeDao extends BaseDao {

	/**
	 * 列出所有报警类型
	 * 
	 * @return
	 */
	public List listBsAlertType();

	public String findAlertTypeByCode(String code);
}
