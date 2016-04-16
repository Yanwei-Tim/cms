package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;
import com.hzjava.monitorcenter.domain.TerminalAccessUrl;

public interface TerminalAccessUrlDao extends BaseDao{
	public PageResult findPage(int pageIndex, int pageLength);

	public int getCount();
	public TerminalAccessUrl findByidTerminal(Long idTerminal);
}
