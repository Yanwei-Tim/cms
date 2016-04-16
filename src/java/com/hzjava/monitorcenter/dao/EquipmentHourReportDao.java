package com.hzjava.monitorcenter.dao;

import java.util.Date;
import java.util.List;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;

public interface EquipmentHourReportDao extends BaseDao {
	/**
	 * 分页查询EquipmentHourReport
	 * 
	 */
	public PageResult listHourReportByParams(int pageIndex, int pageLength,
                                             Date endDate);

	/**
	 * 分页查询EquipmentHourReport
	 * 
	 */
	public PageResult listHourReportByParams(int pageIndex, int pageLength,
                                             Date endDate, String equName);
    public PageResult listDayReportByParams(int year, int month, String equName);
    public PageResult listMonthReportByParams(int year, String equName);
    public PageResult listDayReportByParams(int year, int month);
    public PageResult listMonthReportByParams(int year);
	/**
	 * 按业务名为分组统计各项数据
	 * 
	 */
	public List countHourReport(Date endDate);

	/**
	 * 按日查询小时报表
	 * 
	 */
	public List countHourReport(Date date, String equName);

	/**
	 * 按月查询日报表
	 * 

	 * @return
	 */
	public List countDayReport(int reportMonth, int reportYear, String equName);
    public List countDayReport(int reportMonth, int reportYear);
	/**
	 * 按年查询月报表
	 * 
	 * @param date
	 * @return
	 */
	public List countMonthReport(int reportYear, String equName);

	/**
	 * 按小时统计生成日报表
	 */
	public void buildDayReport(Date date);

	/**
	 * 按日报表统计生成月报表
	 */
	public void buildMonthReport(Date date);

	public void buildDayReport2(Date date);
}
