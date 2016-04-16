package com.hzjava.monitorcenter.service;

import com.hzjava.monitorcenter.domain.SysStrategyInf;

public interface SysStrategyService {

	String getPages(int pageIndex, int limit);
	
	public String insert(SysStrategyInf sysStrategyInf);
	
	public String delete(long[] ids);
	
	public String update(SysStrategyInf sysStrategyInf);
}
