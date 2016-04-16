package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;
import com.hzjava.monitorcenter.domain.SnmpOid;

import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-27
 * Time: 下午12:01
 * To change this template use File | Settings | File Templates.
 */
public class SnmpOidDaoImpl extends MyDaoSupport implements SnmpOidDao{
    @Override
    public void setEntityClass() {
        this.entityClass = SnmpOid.class;
    }


    @Override

    public PageResult listByPage(int pageIndex, int pageLength) {
        String hql = "from SnmpOid";
        String countHql = "select count(*) " + hql;
        PageResult ps = this.findByPage(hql, countHql, pageIndex, pageLength);
        return ps;
    }

    @Override
    public void addSnmpOid(SnmpOid snmpOid) {
        try {
            getHibernateTemplate().save(snmpOid);
        } catch (Exception e) {
         logger.info(e);
            throw e;
        }
    }

    @Override
    public SnmpOid FindSnmpOidById(int id) {
        SnmpOid snmpOid = null;
        List list = null;
        String hql = "from SnmpOid where id = '"+id+"'";
        try {
            list = getHibernateTemplate().find(hql);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        snmpOid = (SnmpOid) list.get(0);
        return snmpOid;
    }

    @Override
    public void Update(SnmpOid snmpOid) {
        try {
            getHibernateTemplate().update(snmpOid);
        } catch (Exception e) {
            logger.info(e);
            throw e;
        }
    }

    @Override
    public void Delete(SnmpOid snmpOid) {
        try {
            getHibernateTemplate().delete(snmpOid);
        } catch (Exception e) {
            logger.info(e);
            throw e;
        }
    }

}
