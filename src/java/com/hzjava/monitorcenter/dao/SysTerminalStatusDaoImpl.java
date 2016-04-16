package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;
import com.hzjava.monitorcenter.domain.SysTerminalStatus;

/**
 * Created by IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 12-6-20
 * Time: 下午3:02
 * To change this template use File | Settings | File Templates.
 */
public class SysTerminalStatusDaoImpl extends MyDaoSupport implements SysTerminalStatusDao {
    @Override
    public void setEntityClass() {
        this.entityClass = SysTerminalStatus.class;
    }
}
