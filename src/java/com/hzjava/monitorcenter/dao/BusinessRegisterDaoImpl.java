package com.hzjava.monitorcenter.dao;

import java.util.List;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.BusinessRegister;

public class BusinessRegisterDaoImpl extends MyDaoSupport implements
		BusinessRegisterDao {

	@Override
	public void setEntityClass() {
		this.entityClass = BusinessRegister.class;
	}

	public PageResult listByPage(int pageIndex, int pageLength) {
		String hql = "from BusinessRegister ";
		String countHql = " select count(*) " + hql;
		PageResult ps = this.findByPage(hql, countHql, pageIndex, pageLength);
		return ps;
	}

	public Object[] getBlob(Long id, String type) {
		String fieldName = null, fileName = null;
		if (type.equalsIgnoreCase("authMaterial")) {
			fieldName = "authMaterial";
			fileName = "authMaterialFileName";
		} else if (type.equalsIgnoreCase("tpGraph")) {
			fieldName = "tpGraph";
			fileName = "tpGraphFileName";
		}
		String hql = "select " + fieldName + "," + fileName
				+ " from BusinessRegister where id = ?";
		List list = this.findByMaxResults(hql, new Long[] { id }, 1);
		Object[] obj = (Object[]) list.get(0);

		return obj;
	}

}
