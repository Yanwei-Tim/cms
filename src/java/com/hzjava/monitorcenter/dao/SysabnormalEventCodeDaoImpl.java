package com.hzjava.monitorcenter.dao;

import com.hzjava.monitorcenter.domain.SysabnormalEventCode;

import cn.collin.commons.dao.MyDaoSupport;

public class SysabnormalEventCodeDaoImpl extends MyDaoSupport implements
		SysabnormalEventCodeDao {

	@Override
	public void setEntityClass() {
		this.entityClass = SysabnormalEventCode.class;
	}

}
