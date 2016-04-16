package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

/**
 * Created with IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 12-8-10
 * Time: 上午9:48
 * To change this template use File | Settings | File Templates.
 */
public interface SysQueryServiceDao extends BaseDao {
    PageResult listByPage(String objectName, int pageIndex, int limit);
}
