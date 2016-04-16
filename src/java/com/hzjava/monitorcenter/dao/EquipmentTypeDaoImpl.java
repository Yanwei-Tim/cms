package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.EquipmentType;

public class EquipmentTypeDaoImpl extends MyDaoSupport implements
		EquipmentTypeDao {

	@Override
	public void setEntityClass() {
		this.entityClass = EquipmentType.class;
	}

}
