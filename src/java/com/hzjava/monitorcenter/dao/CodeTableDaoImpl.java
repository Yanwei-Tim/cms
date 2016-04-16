package com.hzjava.monitorcenter.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CodeTableDaoImpl extends HibernateDaoSupport implements CodeTableDao {
	private final static Logger logger = Logger.getLogger(CodeTableDaoImpl.class);

	public List listAllTermianlBand() {
		String hql = " from TermianlBand";
		return getHibernateTemplate().find(hql);
	}

	public List listAllTermianlOS() {
		String hql = " from TermianlOS";
		return getHibernateTemplate().find(hql);
	}

	public List listAllTermianlOutlink() {
		String hql = " from TermianlOutlink";
		return getHibernateTemplate().find(hql);
	}

	public List listAllTerminalType() {
		String hql = " from TerminalType";
		return getHibernateTemplate().find(hql);
	}

	public List listAllCardType() {
		String hql = " from CardType";
		return getHibernateTemplate().find(hql);
	}

}
