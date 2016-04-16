package com.hzjava.monitorcenter.dao;

import java.util.ArrayList;
import java.util.List;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.Account;

public class AccountDaoImpl extends MyDaoSupport implements AccountDao {

	@Override
	public void setEntityClass() {
		this.entityClass = Account.class;
	}

	public PageResult listByPage(String userName, String status, int pageIndex) {
		String hql = "from Account where 1=1 ";
		List paramsList = new ArrayList();
		if (userName != null && userName.length() > 0) {
			hql += " and userName like ?";
			paramsList.add("%" + userName + "%");
		}
		if (status != null && status.length() > 0
				&& !status.equalsIgnoreCase("全部")) {
			hql += " and status=?";
			paramsList.add(status);
		}
		String countHql = "select count(*) " + hql;

		PageResult ps = this.findByPage(hql, countHql, paramsList.toArray(),
				pageIndex, 15);
		return ps;
	}

	public Account findByNameAndPwd(String name, String pwd) {
		String hql = "from Account where userName=? and password=? and status=?";
		List list = getHibernateTemplate().find(hql,
				new String[] { name, pwd, "有效" });
		if (list != null && list.size() > 0) {
			return (Account) list.get(0);
		} else {
			return null;
		}
	}

}
