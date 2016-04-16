package com.hzjava.monitorcenter.service;

import com.hzjava.monitorcenter.domain.SysControlRulesInf;

public interface SysControlRulesInfService {

	String getPages(int pageIndex, int limit);

	String insert(SysControlRulesInf sysControlRulesInf);

	String delete(long[] ids);

	String update(SysControlRulesInf sysControlRulesInf);

}
