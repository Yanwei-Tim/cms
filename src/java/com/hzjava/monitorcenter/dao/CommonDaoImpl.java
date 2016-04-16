package com.hzjava.monitorcenter.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.hibernate3.HibernateAccessor;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.collin.commons.domain.PageResult;

/**
 * 用于通用的增删改查，class作为传入参数
 * @author xiangqi.java@gmail.com
 *
 * 2010-10-17
 */
public class CommonDaoImpl extends HibernateDaoSupport implements CommonDao{


	/**
	 * retrieve entity by id
	 *
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public Object retrieveById(Class entityClass, Serializable id) {
		return getHibernateTemplate().load(entityClass, id);
	}

	/**
	 * get entity by id
	 *
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public Object getById(Class entityClass, Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * 更新entity
	 *
	 * @param entity
	 */
	public void update(Object entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 * 创建entity
	 *
	 * @param entity
	 */
	public void create(Object entity) {
		getHibernateTemplate().save(entity);
	}

	public void delete(Object entity) {
		getHibernateTemplate().delete(entity);
	}
	
	public void delete(Class entityClass, Serializable id) {
		getHibernateTemplate().delete(
				getHibernateTemplate().load(entityClass, id));
	}


	/**
	 * 批量删除entity（只删除当前的entity，不删除关联的entity）
	 *
	 * @param ids
	 */
	public void deleteWithIndependent(String entityName, String[] ids) {
		if (ids.length <= 0)
			return;
		String condition = "";
		for (int i = 0; i < ids.length; i++) {
			condition += ids[i];
			if (i < ids.length - 1)
				condition += ",";
		}
		getHibernateTemplate().bulkUpdate(
				"delete from " + entityName + " where id in (" + condition
						+ ")");
	}


	/**
	 * 批量删除entity（同时删除entity关联的所有entity）
	 *
	 * @param ids
	 */
	public void deleteWithDependent(Class entityClass, String[] ids) {
		for (int i = 0; i < ids.length; i++) {
			getHibernateTemplate().delete(
					getHibernateTemplate().load(entityClass,
							Long.valueOf(ids[i])));
		}
	}


	/**
	 * 根据条件批量删除entity（同时删除entity关联的所有entity）
	 *
	 * @param ids
	 */
	public void deleteWithCondition(String entityName, String whereString,
			Object[] values) {
		String hql;
		if (whereString != null) {
			hql = whereString.toUpperCase();
			if (hql.indexOf("WHERE") < 0) {
				hql = "WHERE " + hql;
			}
		} else {
			hql = "";
		}
		hql = "FROM " + entityName + " " + hql;

		Session session = this.getSession(true);
		Query query = session.createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		for (Iterator iter = query.iterate(); iter.hasNext();) {
			getHibernateTemplate().delete(iter.next());
		}

	}


	private void checkWriteOperationAllowed(Session session)
			throws InvalidDataAccessApiUsageException {
		if (this.getHibernateTemplate().isCheckWriteOperations()
				&& this.getHibernateTemplate().getFlushMode() != HibernateAccessor.FLUSH_EAGER
				&& FlushMode.NEVER.equals(session.getFlushMode())) {
			throw new InvalidDataAccessApiUsageException(
					"Write operations are not allowed in read-only mode (FlushMode.NEVER) - turn your Session "
							+ "into FlushMode.AUTO or remove 'readOnly' marker from transaction definition");
		}
	}

	/**
	 * 用于批量更新数据;纠正缓存溢出问题
	 *
	 * @param entities
	 * @throws org.springframework.dao.DataAccessException
	 */
	public void saveOrUpdateAll(final Collection entities) {
		this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// session.setFlushMode(FlushMode.AUTO);
				checkWriteOperationAllowed(session);
				int i = 0;
				for (Iterator it = entities.iterator(); it.hasNext();) {
					session.save(it.next());
					i++;
					if (i % 20 == 0) { // 20, same as the JDBC batch size
						// flush a batch of inserts and release memory:
						session.flush();
						session.clear();
					}

				}
				session.flush();
				session.clear();
				return null;
			}
		}, true);
	}

	public List findAll(Class entityClass) {

		List result = getHibernateTemplate().loadAll(entityClass);
		return (result != null) ? result : Collections.EMPTY_LIST;
	}
	/**
	 * 分页查询
	 * @param params
	 * @param className
	 * @param orderCloumn
	 * @param pageIndex
	 * @param pageLength
	 * @return
	 */
	public PageResult pageList(Map<String,String> params,String className,String orderCloumn, int pageIndex,
			int pageLength) {

		StringBuffer queryString = new StringBuffer("from ").append(className).append(" where 1=1");

		List paramsList = new ArrayList();

		Set<String> keys=params.keySet();
		for (String key : keys) {
			String entry=params.get(key);
			if(entry!=null&&entry.length()>0){
				queryString.append(" and ").append(key).append(" = ?");
				paramsList.add(entry);
				logger.debug(key+" = "+entry);
			}
		}


		String countString = "select count(*) " + queryString;

		queryString.append(" order by ").append(orderCloumn).append(" desc");

		return this.findByPage(queryString.toString(), countString, paramsList.toArray(),
				pageIndex, pageLength);

	}

	/**
	 */
	private PageResult findByPage(String queryString, String countString,
			Object[] objects, int pageIndex, int pageLength) {
		int firstResult = (pageIndex - 1) * pageLength;
		Query query = getSession().createQuery(queryString);
		query.setFirstResult(firstResult);
		query.setMaxResults(pageLength);
		for (int i = 0; i < objects.length; i++)
			query.setParameter(i, objects[i]);
		int allResultsAmount = ((Long) getHibernateTemplate().find(countString,
				objects).get(0)).intValue();
		int pagesAmount = new Double(Math.ceil(new Integer(allResultsAmount)
				.doubleValue()
				/ new Integer(pageLength).doubleValue())).intValue();

		return new PageResult(pageIndex, pageLength, pagesAmount,
				allResultsAmount, query.list());
	}

}
