package com.hzjava.monitorcenter.service;

import java.util.Date;
import java.util.List;

import cn.collin.commons.domain.PageResult;

public interface EquipmentReportService {

	/**
	 * 分页查询-设备日统计
	 * 
	 * @param pageIndex
	 * @param pageLength
	 * @param endDate
	 * @param equName
	 * @return
	 */
	public PageResult listHourReportPage(int pageIndex, int pageLength,
                                         Date endDate, String equName);

	/**
	 * 分页查询-设备日统计
	 */
	public List listHourReportCount(Date endDate, String equName);

	/**
	 * 分页查询-设备月统计
	 *
     *
     */
	public List buildListDayReportCount(int year, int month, String equName);

    public PageResult PageResultDayReportCount(int year, int month, String equName);
	/**
	 * 分页查询-设备年统计
	 * 
	 */
	public List buildListMonthReportCount(int year, String equName);

    public PageResult PageResultMonthReportCount(int year,String equName);
	/**
	 * 按小时报表生成统计日报表和月报表：放在一起保持数据一致性，先统计日报表然后根据日报表生成月报表
	 * 
	 */
	public void updBuildReport(Date date);

	public void updBuildReport2(Date date);
}
