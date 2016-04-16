package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;
import com.hzjava.monitorcenter.domain.EquipmentTypes;

public class EquipmentTypesDaoImpl extends MyDaoSupport implements
		EquipmentTypesDao {

	@Override
	public void setEntityClass() {
		this.entityClass = EquipmentTypes.class;
	}

}
