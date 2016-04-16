package com.hzjava.monitorcenter.service;

import java.util.Date;
import java.util.List;

import cn.collin.commons.domain.PageResult;

public interface BusinessReportService {

	/**
	 * 分页查询-业务日统计
	 * 
	 */
	public PageResult listHourReportPage(int pageIndex, int pageLength,
                                         Date endDate, String businessName);

	/**
	 * 分页查询-业务日统计
	 * 
	 */
	public List listHourReportCount(Date endDate, String businessName);

	/**
	 * 分页查询-业务月统计
	 * 
	 */
	public List buildListDayReportCount(int year, int month, String businessName);

	/**
	 * 分页查询-业务年统计
	 * 
	 */
	public List buildListMonthReportCount(int year, String businessName);

	/**
	 * 按小时报表生成统计日报表和月报表：放在一起保持数据一致性，先统计日报表然后根据日报表生成月报表
	 * 
	 */
	public void updBuildReport(Date date);

	public void updBuildReport2(Date date);
}
