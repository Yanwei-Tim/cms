package com.hzjava.monitorcenter.service;

import com.hzjava.monitorcenter.domain.SnmpOid;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-27
 * Time: 下午12:08
 * To change this template use File | Settings | File Templates.
 */
public interface SnmpOidService {
    public  Map listByPage(int pageIndex);

    public void addSnmpOid(SnmpOid snmpOid);

    public SnmpOid FindSnmpOidById(int id);

    public void  UpdateSnmpOid(SnmpOid snmpOid);

    public void DeleteSnmpOid(SnmpOid snmpOid);
}
