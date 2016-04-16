package com.hzjava.monitorcenter.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import cn.collin.commons.domain.PageResult;

/**
 * 用于通用的增删改查，class作为传入参数
 * @author xiangqi.java@gmail.com
 *
 * 2010-10-17
 */
public interface CommonDao{


	/**
	 * retrieve entity by id
	 *
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public Object retrieveById(Class entityClass, Serializable id) ;

	/**
	 * get entity by id
	 *
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public Object getById(Class entityClass, Serializable id) ;

	/**
	 * 更新entity
	 *
	 * @param entity
	 */
	public void update(Object entity) ;

	/**
	 * 创建entity
	 *
	 * @param entity
	 */
	public void create(Object entity) ;

	public void delete(Object entity);
	public void delete(Class entityClass, Serializable id) ;


	/**
	 * 批量删除entity（只删除当前的entity，不删除关联的entity）
	 *
	 * @param ids
	 */
	public void deleteWithIndependent(String entityName, String[] ids) ;


	/**
	 * 批量删除entity（同时删除entity关联的所有entity）
	 *
	 * @param ids
	 */
	public void deleteWithDependent(Class entityClass, String[] ids) ;


	/**
	 * 根据条件批量删除entity（同时删除entity关联的所有entity）
	 *
	 * @param ids
	 */
	public void deleteWithCondition(String entityName, String whereString,
                                    Object[] values) ;


	/**
	 * 用于批量更新数据;纠正缓存溢出问题
	 *
	 * @param entities
	 * @throws org.springframework.dao.DataAccessException
	 */
	public void saveOrUpdateAll(final Collection entities) ;

	public List findAll(Class entityClass) ;

	/**
	 * 分页查询
	 * @param params
	 * @param className
	 * @param orderCloumn
	 * @param pageIndex
	 * @param pageLength
	 * @return
	 */
	public PageResult pageList(Map<String, String> params, String className, String orderCloumn, int pageIndex,
                               int pageLength) ;

}
