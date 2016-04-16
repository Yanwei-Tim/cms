package com.hzjava.monitorcenter.dao;

import java.util.Date;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface TerminalAccessAuditDao extends BaseDao {
	public PageResult findPage(
            int pageIndex,
            int pageLength,
            String policeId,
            String policeName,
            String messageLevel,
            Date startDate,
            Date endDate, String busName, String userDepart, String userZone);
}
