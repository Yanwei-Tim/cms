package com.hzjava.monitorcenter.service;

import com.hzjava.monitorcenter.domain.SysAbnormalInf;

public interface SysAbnormalInfService {

	String getPages(int pageIndex, int limit);
	
	public String insert(SysAbnormalInf sysAbnormalInf);
	
	public String delete(long[] ids);
	
	public String update(SysAbnormalInf sysAbnormalInf);
}
