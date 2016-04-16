package com.hzjava.monitorcenter.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface SysabnormalDao extends BaseDao {
	public PageResult findPage(int pageIndex, int pageLength,
                               Date startDate, Date endDate, String eventCode, String objectCode);
    public List list(String hql)throws  Exception;
    public List countAll(String queryString);
    public List countAllMap(String queryString);
    public int countAlls(String queryString);
}
