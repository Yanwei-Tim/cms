package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;
import com.hzjava.monitorcenter.domain.SnmpOid;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-27
 * Time: 上午11:58
 * To change this template use File | Settings | File Templates.
 */
public interface SnmpOidDao extends BaseDao {
    PageResult listByPage(int pageIndex, int pageLength);

    public void addSnmpOid(SnmpOid snmpOid);
    
    public SnmpOid FindSnmpOidById(int id);
    
    public void Update(SnmpOid snmpOid);

    public void Delete(SnmpOid snmpOid);
}
