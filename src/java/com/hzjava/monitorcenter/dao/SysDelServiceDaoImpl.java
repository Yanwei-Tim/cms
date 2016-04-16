package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;
import com.hzjava.monitorcenter.domain.SysDelService;

/**
 * Created with IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 12-8-10
 * Time: 上午9:49
 * To change this template use File | Settings | File Templates.
 */
public class SysDelServiceDaoImpl extends MyDaoSupport implements SysDelServiceDao {
    @Override
    public void setEntityClass() {
        this.entityClass = SysDelService.class;
    }
}
