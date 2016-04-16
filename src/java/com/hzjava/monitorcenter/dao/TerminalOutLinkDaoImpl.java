package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;
import com.hzjava.monitorcenter.domain.TermianlOutlink;

/**
 * Created with IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 12-8-9
 * Time: 下午5:47
 * To change this template use File | Settings | File Templates.
 */
public class TerminalOutLinkDaoImpl extends MyDaoSupport implements TerminalOutLinkDao {
    @Override
    public void setEntityClass() {
        this.entityClass = TermianlOutlink.class;
    }
}
