package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.Account;

public interface AccountDao extends BaseDao {

	PageResult listByPage(String userName, String status, int pageIndex);

	Account findByNameAndPwd(String name, String pwd);

}
