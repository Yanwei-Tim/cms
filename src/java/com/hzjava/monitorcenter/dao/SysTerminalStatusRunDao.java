package com.hzjava.monitorcenter.dao;

import java.util.List;

import com.hzjava.monitorcenter.domain.SysTerminalStatusRun;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface SysTerminalStatusRunDao extends BaseDao{
	public List<SysTerminalStatusRun> findPage(int pageIndex, int pageLength);

	public SysTerminalStatusRun findByUserId(String userId);

}
