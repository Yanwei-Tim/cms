package com.hzjava.monitorcenter.dao;

import java.util.ArrayList;
import java.util.List;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.SecurityEventCode;

public class SecurityEventCodeDaoImpl extends MyDaoSupport implements
		SecurityEventCodeDao {

	@Override
	public void setEntityClass() {
		this.entityClass = SecurityEventCode.class;
	}

	public List listScAlertType() {
		StringBuffer sb = new StringBuffer(
				" from SecurityEventCode s where 1=1");

		String countString = "select count(*) " + sb.toString();
		;
		sb.insert(0, "select new map(s.code as value,s.codeDesc as name)");

		String queryString = sb.toString();

		List list = getHibernateTemplate().find(queryString);
		return list;
	}
/*
 * date:2012-03-17
 * author:crj
 * function:根据传进来的报警编码得到报警的名称
 */
	@Override
	public String findAlertTypeByCode(String code) {
		StringBuffer sb = new StringBuffer(
				" from SecurityEventCode s where 1=1");
		List params = new ArrayList();
		if (code != null && code != "") {
			sb.append(" and code=? ");
			params.add(code);
		}
		String queryString = sb.toString();
		List<SecurityEventCode> list = getHibernateTemplate().find(queryString,
				params.toArray());
		if(list!=null&&list.size()>0){
			return list.get(0).getCodeDesc();
		}else{
			return code;
		}
		
	}
}
