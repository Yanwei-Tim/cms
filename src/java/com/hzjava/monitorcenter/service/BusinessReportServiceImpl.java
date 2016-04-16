package com.hzjava.monitorcenter.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.dao.BusinessHourReportDao;
import com.hzjava.monitorcenter.utils.StringUtils;

/**
 * 审计管理: 1.系统日志审计 2.用户日志审计 3.业务日志审计 4.设备日志审计
 * 
 * @author xiangqi.java@gmail.com
 * 
 */
public class BusinessReportServiceImpl implements BusinessReportService {
	private final static Logger logger = Logger
			.getLogger(BusinessReportServiceImpl.class);

	private BusinessHourReportDao businessHourReportDao;

	public void setBusinessHourReportDao(
			BusinessHourReportDao businessHourReportDao) {
		this.businessHourReportDao = businessHourReportDao;
	}

	/**
	 * 分页查询-业务日统计
	 * 
	 */
	public PageResult listHourReportPage(int pageIndex, int pageLength,
			Date endDate, String businessName) {
		logger.debug("pageIndex:" + pageIndex + " pageLength:" + pageLength
				+ " endDate:" + endDate + " businessName:" + businessName);
		PageResult ps = null;
		if (StringUtils.isBlank(businessName)) {
			ps = this.businessHourReportDao.listHourReportByParams(pageIndex,
					pageLength, endDate);
		} else {
			ps = this.businessHourReportDao.listHourReportByParams(pageIndex,
					pageLength, endDate, businessName);
		}
		return ps;
	}

	/**
	 * 分页查询-业务日统计
	 * 
	 */
	public List listHourReportCount(Date endDate, String businessName) {

		List countList = null;
		if (StringUtils.isBlank(businessName)) {
			countList = businessHourReportDao.countHourReport(endDate);
		} else {
			countList = businessHourReportDao.countHourReport(endDate,
					businessName);
		}
		return countList;
	}

	/**
	 * 分页查询-业务月统计
	 * 
	 */
	public List buildListDayReportCount(int year, int month, String businessName) {
		List countList = null;
		// Calendar c=Calendar.getInstance();
		// c.set(Calendar.YEAR, year);
		// c.set(Calendar.MONTH, month);
		// logger.debug(c.getTime());
		// Calendar startDate=Calendar.getInstance();
		// startDate.set(Calendar.YEAR, c.get(Calendar.YEAR));
		// startDate.set(Calendar.MONTH, c.get(Calendar.MONTH));
		// logger.debug(startDate.getTime());
		// Calendar endDate=Calendar.getInstance();
		// endDate.set(Calendar.YEAR, c.get(Calendar.YEAR));
		// endDate.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);
		// endDate.set(Calendar.DATE, -1);
		// logger.debug(endDate.getTime());

		countList = businessHourReportDao.countDayReport(month, year,
				businessName);
		return countList;
	}

	/**
	 * 分页查询-业务年统计
	 * 
	 */
	public List buildListMonthReportCount(int year, String businessName) {
		List countList = null;
		// Calendar c=Calendar.getInstance();
		// c.set(Calendar.YEAR, year);
		// logger.debug(c.getTime());
		// Calendar startDate=Calendar.getInstance();
		// startDate.set(Calendar.YEAR, c.get(Calendar.YEAR));
		// startDate.set(Calendar.MONTH, c.get(Calendar.MONTH));
		// logger.debug(startDate.getTime());
		// Calendar endDate=Calendar.getInstance();
		// endDate.set(Calendar.YEAR, c.get(Calendar.YEAR));
		// endDate.set(Calendar.YEAR, c.get(Calendar.YEAR)+1);
		// endDate.set(Calendar.DATE, -1);
		// logger.debug(endDate.getTime());

		countList = businessHourReportDao.countMonthReport(year, businessName);
		return countList;
	}

	/**
	 * 按小时报表生成统计日报表和月报表：放在一起保持数据一致性，先统计日报表然后根据日报表生成月报表
	 * 
	 * @param date
	 */
	public void updBuildReport(Date date) {
		if (date == null) {
			date = new Date(System.currentTimeMillis());
		}
		businessHourReportDao.buildDayReport(date);
		businessHourReportDao.buildMonthReport(date);
	}

	public void updBuildReport2(Date date) {
		businessHourReportDao.buildDayReport2(date);
	}
}
