package com.hzjava.monitorcenter.dao;

import java.util.List;

import cn.collin.commons.dao.MyDaoSupport;

import com.hzjava.monitorcenter.domain.Jbqk;

public class JbqkDaoImpl extends MyDaoSupport implements JbqkDao {

	@Override
	public void setEntityClass() {
		this.entityClass = Jbqk.class;
	}

	public Jbqk getJbqk()throws Exception  {
		String hql = "from Jbqk";
		List list = this.findByMaxResults(hql, null, 1);
		if (list.size() > 0)
			return (Jbqk) list.get(0);
		else
			return null;
	}

	public Object[] getBlob(Long id, String type) throws Exception {
		String fieldName = null, fileName = null;
		if (type.equalsIgnoreCase("spcl")) {
			fieldName = "spcl";
			fileName = "spclFileName";
		} else if (type.equalsIgnoreCase("ts")) {
			fieldName = "technologySolution";
			fileName = "technologySolutionFileName";
		} else if (type.equalsIgnoreCase("ca")) {
			fieldName = "confidentialAgreement";
			fileName = "confidentialAgreementFileName";
		} else if (type.equalsIgnoreCase("tp")) {
			fieldName = "platformTp";
			fileName = "platformTpFileName";
		}
		String hql = "select " + fieldName + "," + fileName
				+ " from Jbqk where id=?";
		List list = this.findByMaxResults(hql, new Long[] { id }, 1);
		return (Object[]) (list.get(0));
	}

    @Override
    public Jbqk getJbqkByIdsystem(String idsystem) throws Exception {
        String hql = "from Jbqk where platformId = "+idsystem;
        List list = this.findByMaxResults(hql, null, 1);
        if (list.size() > 0)
            return (Jbqk) list.get(0);
        else
            return null;
    }

}
