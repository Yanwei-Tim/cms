package com.hzjava.monitorcenter.dao;

import java.util.List;

import cn.collin.commons.dao.BaseDao;

public interface SecurityEventCodeDao extends BaseDao {

	/**
	 * 返回所有安全事件报警类型
	 * 
	 * @return
	 */
	public List listScAlertType();
	/*
	 * date:2012-03-17
	 * author:crj
	 * function:根据传进来的报警编码得到报警的名称
	 */
	public String findAlertTypeByCode(String code);
}
