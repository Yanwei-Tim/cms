package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;
import com.hzjava.monitorcenter.domain.Orgcode;
import org.hibernate.Query;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-17
 * Time: 下午1:31
 * To change this template use File | Settings | File Templates.
 */
public class OrgcodeDaoImpl extends MyDaoSupport implements OrgcodeDao{
    @Override
    public void setEntityClass() {
            this.entityClass=Orgcode.class;
    }


    @Override
    public List list(String hql)throws Exception {
        List list = null;
        try {
            list = getHibernateTemplate().find(hql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List findOrgcode(String code) {
        String hql = new String("from Orgcode where orgcode ='"+code+"'");
        List list = null;
        Orgcode orgcode = null;
        try {
            list = getHibernateTemplate().find(hql);
        } catch (Exception e) {
            logger.error("findOrgcode查找错误",e);
        }
        if(list.size()>0){
            orgcode= (Orgcode) list.get(0);
        }
        return list;
    }

    @Override
    public List<Orgcode> findDepart(String userZone, String selectDepart) {
        String hql="from Orgcode where orgcode like'"+userZone+"00%' and orgname like'%"+selectDepart+"%'";
        List<Orgcode> list=super.getHibernateTemplate().find(hql);
        return list;
    }


    public List<Orgcode> findChildFirst() {
        String hql="from Orgcode where id=010101";
        List<Orgcode> list=super.getHibernateTemplate().find(hql);
        return list;
    }


    @Override
    public List<Orgcode> getAllParents(String code) {
        String hql="from Orgcode as o where o.orgcode like '"+code+"%'";
//        List<Orgcode> list=super.getHibernateTemplate().find(hql);
        List list = getHibernateTemplate().find(hql);
        return list;
    }


}
