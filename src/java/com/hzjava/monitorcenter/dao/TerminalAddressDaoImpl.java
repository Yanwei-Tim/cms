package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;
import com.hzjava.monitorcenter.domain.TerminalAddress;

public class TerminalAddressDaoImpl extends MyDaoSupport implements TerminalAddressDao{
	
	public void setEntityClass() {
		this.entityClass = TerminalAddress.class;
	}

}
