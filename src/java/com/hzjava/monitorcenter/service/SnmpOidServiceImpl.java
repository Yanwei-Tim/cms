package com.hzjava.monitorcenter.service;

import cn.collin.commons.domain.PageResult;
import com.hzjava.monitorcenter.dao.SnmpOidDao;
import com.hzjava.monitorcenter.domain.SnmpOid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-27
 * Time: 下午1:11
 * To change this template use File | Settings | File Templates.
 */
public class SnmpOidServiceImpl implements SnmpOidService{
     private SnmpOidDao snmpOidDao;

    public void setSnmpOidDao(SnmpOidDao snmpOidDao) {
        this.snmpOidDao = snmpOidDao;
    }

    @Override
    public Map listByPage(int pageIndex) {
        Map model = new HashMap();
        PageResult ps = snmpOidDao.listByPage(pageIndex,15);
        List items = new ArrayList();
        for (Object obj : ps.getResults()) {
            SnmpOid snmpOid = (SnmpOid) obj;

            Map item = new HashMap();
            item.put("id",snmpOid.getId());
            item.put("name", snmpOid.getName());
            item.put("type", snmpOid.getType());
            item.put("company", snmpOid.getCompany());
            item.put("cpuuse", snmpOid.getCpuuse());
            item.put("memtotal", snmpOid.getMemtotal());
            item.put("memuse", snmpOid.getMemuse());
            item.put("disktotal", snmpOid.getDisktotal());
            item.put("diskuse", snmpOid.getDiskuse());
            item.put("curconn", snmpOid.getCurconn());
            item.put("snmpver", snmpOid.getSnmpver());
            items.add(item);
        }
        ps.setResults(items);
        model.put("ps", ps);
        return model;
    }

    @Override
    public void addSnmpOid(SnmpOid snmpOid) {
        snmpOidDao.create(snmpOid);
    }

    @Override
    public SnmpOid FindSnmpOidById(int id) {
        SnmpOid snmpOid = snmpOidDao.FindSnmpOidById(id);
        return snmpOid;
    }

    @Override
    public void UpdateSnmpOid(SnmpOid snmpOid) {
       snmpOidDao.Update(snmpOid);
    }

    @Override
    public void DeleteSnmpOid(SnmpOid snmpOid) {
       snmpOidDao.Delete(snmpOid);
    }


}
