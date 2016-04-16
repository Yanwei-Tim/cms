package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;
import com.hzjava.monitorcenter.domain.Equipment;

import java.util.List;

public class EquipmentDaoImpl extends MyDaoSupport implements EquipmentDao {

	@Override
	public void setEntityClass() {
		this.entityClass = Equipment.class;
	}

	public PageResult listByPage(int pageIndex, int pageLength) {
		String hql = "from Equipment";
		String countHql = "select count(*) " + hql;
		PageResult ps = this.findByPage(hql, countHql, pageIndex, pageLength);
		return ps;
	}

    public List list(String hql) throws Exception {
        List list = null;
        try {
            list = getHibernateTemplate().find(hql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
