package com.hzjava.monitorcenter.dao;

import java.util.Date;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface SysTerminalStatusRunReportDao extends BaseDao{
	public PageResult findPage(int pageIndex, int pageLength,
                               long idTerminal, Date reportDate);
	
	public void buderDayReport(Date date);
}
