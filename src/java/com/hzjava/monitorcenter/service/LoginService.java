package com.hzjava.monitorcenter.service;

import com.hzjava.monitorcenter.domain.Account;

public interface LoginService {

	Account getAccountByNameAndPwd(String name, String pwd);

}
