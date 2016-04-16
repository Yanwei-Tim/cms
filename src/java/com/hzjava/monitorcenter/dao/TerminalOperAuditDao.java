package com.hzjava.monitorcenter.dao;

import java.util.Date;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface TerminalOperAuditDao extends BaseDao {
	public PageResult findPage(
            int pageIndex,
            int pageLength,
            String cardId,
            String cardType,
            String policeId,
            String operateTpye,
            String operater,
            Date startDate,
            Date endDate);
}
