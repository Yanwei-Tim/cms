package com.hzjava.monitorcenter.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface BusinessHourReportDao extends BaseDao {
	/**
	 * 分页查询BusinessHourReport
	 * 
	 */
	public PageResult listHourReportByParams(int pageIndex, int pageLength,
                                             Date endDate);

	public PageResult listHourReportByParams(int pageIndex, int pageLength,
                                             Date endDate, String businessName);

	/**
	 * 按业务名为分组统计各项数据
	 * @param endDate
	 * 
	 * @return
	 */
	public List countHourReport(Date endDate);

	/**
	 * 按时段统计各项数据
	 * @param endDate TODO
	 * 
	 * @return
	 */
	public List countHourReport(Date endDate, String businessName);

	/**
	 * 按月查询日报表
	 * 
	 * @param date
	 * @return
	 */
	public List countDayReport(int reportMonth, int reportYear,
                               String businessName);

	/**
	 * 按年查询月报表
	 * 
	 * @param date
	 * @return
	 */
	public List countMonthReport(int reportYear, String businessName);

	/**
	 * 按小时统计生成日报表
	 */
	public void buildDayReport(Date date);

	/**
	 * 按日报表统计生成月报表
	 */
	public void buildMonthReport(Date date);

	public Map countDayReport(String businessName, Date now);

	public void buildDayReport2(Date date);

}
