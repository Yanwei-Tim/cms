package com.hzjava.monitorcenter.dao;

import java.util.Date;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface TerminalAccessAuditReportDao extends BaseDao {
	public PageResult findPage(
            int pageIndex,
            int pageLength,
            String userDepart,
            Date reportDate);
	public void buildDayReport(Date date);
}
