package com.hzjava.monitorcenter.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.hzjava.monitorcenter.dao.AccountDao;
import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.Role;

public class LoginServiceImpl implements LoginService {
	private AccountDao accountDao;

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public Account getAccountByNameAndPwd(String name, String pwd) {
		Account account = accountDao.findByNameAndPwd(name, pwd);
		Set permissions = new HashSet();
		if (account != null) {
			Set roles = account.getRoles();
			for (Iterator iter = roles.iterator(); iter.hasNext();) {
				Role role = (Role) iter.next();
				permissions.addAll(role.getPermissions());
			}
			account.setPermissions(permissions);
		}

		return account;
	}

}
