package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.TerminalAuthCode;

public class TerminalAuthCodeDaoImpl extends MyDaoSupport implements
		TerminalAuthCodeDao {

	@Override
	public void setEntityClass() {
		this.entityClass = TerminalAuthCode.class;
	}

}
