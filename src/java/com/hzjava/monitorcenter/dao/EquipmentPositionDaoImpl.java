package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;
import com.hzjava.monitorcenter.domain.EquipmentPosition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-4
 * Time: 下午2:35
 * To change this template use File | Settings | File Templates.
 */
public class EquipmentPositionDaoImpl extends MyDaoSupport implements EquipmentPositionDao {

    @Override
    public void setEntityClass() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object retrieveById(Serializable serializable) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object retrieveById(Class aClass, Serializable serializable) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getById(Serializable serializable) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getById(Class aClass, Serializable serializable) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Object o) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void create(Object o) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Serializable serializable) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(Class aClass, Serializable serializable) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteWithIndependent(String[] strings) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteWithIndependent(String s, String[] strings) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteWithDependent(String[] strings) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteWithDependent(Class aClass, String[] strings) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List findAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addEquipmentPositionDao(EquipmentPosition equipmentPositionDao) {
        try {
            getHibernateTemplate().save(equipmentPositionDao);
        } catch (Exception e) {
            logger.info(e);
            throw e;
        }
    }

    @Override
    public void updEquipmentPositionDao(EquipmentPosition equipmentPosition) {
        try {
            getHibernateTemplate().update(equipmentPosition);
        } catch (Exception e) {
            logger.info(e);
            throw e;
        }
    }

    @Override
    public void Delete(EquipmentPosition equipmentPositionDao) {
        try {
            getHibernateTemplate().delete(equipmentPositionDao);
        } catch (Exception e) {
            logger.info(e);
            throw e;
        }
    }

    @Override
    public EquipmentPosition selectById(int id) {
        String hql = "select e from com.hzjava.monitorcenter.domain.EquipmentPosition e where e.equipmentid="+id;
        List list = null;
        try {
            list = getHibernateTemplate().find(hql);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        EquipmentPosition equipmentPosition = null;
        if(list.size()>0){
            equipmentPosition   = (EquipmentPosition) list.get(0);
        }

        return equipmentPosition;
    }

    @Override
    public ArrayList<EquipmentPosition> selectEquipmentPositionById(int id) {
        String hql = "select e from com.hzjava.monitorcenter.domain.EquipmentPosition e where e.equipmentid="+id;
        ArrayList<EquipmentPosition> list = null;
        try {
            list = (ArrayList<EquipmentPosition>) getHibernateTemplate().find(hql);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return list;
    }
}
