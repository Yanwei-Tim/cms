package com.hzjava.monitorcenter.dao;

import java.util.Date;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface AlertDao extends BaseDao {
	/**
	 * 分页查询BusinessExceptAlert
	 * 
	 * @param pageIndex
	 * @param pageLength
	 * @param startDate
	 * @param endDate
	 * @param businessName
	 * @return
	 */
	public PageResult listBsExpAlert(int pageIndex, int pageLength,
                                     Date startDate, Date endDate, String businessName, String alertCode);

	/**
	 * 分页查询SecurityEventAlert
	 * 
	 * @param pageIndex
	 * @param pageLength
	 * @param startDate
	 * @param endDate
	 * @param equName
	 * @return
	 */
	public PageResult listScEventAlert(int pageIndex, int pageLength,
                                       Date startDate, Date endDate, String equName, String alertCode);

	/**
	 * 分页查询EquipmentAlert
	 * 
	 * @param pageIndex
	 * @param pageLength
	 * @param startDate
	 * @param endDate
	 * @param equName
	 * @return
	 */
	public PageResult listEquAlert(int pageIndex, int pageLength,
                                   Date startDate, Date endDate, String equName);

	public void execute(String hql);

}
