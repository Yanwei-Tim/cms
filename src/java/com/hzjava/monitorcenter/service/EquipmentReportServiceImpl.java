package com.hzjava.monitorcenter.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cn.collin.commons.domain.PageResult;
import cn.collin.commons.utils.DateUtils;

import com.hzjava.monitorcenter.dao.EquipmentHourReportDao;
import com.hzjava.monitorcenter.utils.StringUtils;

/**
 * 设备统计报表
 * 
 * @author xiangqi.java@gmail.com
 * 
 */
public class EquipmentReportServiceImpl implements EquipmentReportService {
	private final static Logger logger = Logger
			.getLogger(EquipmentReportServiceImpl.class);

	private EquipmentHourReportDao equipmentHourReportDao;

	public void setEquipmentHourReportDao(
			EquipmentHourReportDao equipmentHourReportDao) {
		this.equipmentHourReportDao = equipmentHourReportDao;
	}

	/**
	 * 分页查询-设备日统计
	 * 
	 */
	public PageResult listHourReportPage(int pageIndex, int pageLength,
			Date endDate, String equName) {
		logger.debug("pageIndex:" + pageIndex + " pageLength:" + pageLength
				+ " endDate:" + endDate + " equName:" + equName);
		PageResult ps = null;
		if (StringUtils.isBlank(equName)) {
			ps = this.equipmentHourReportDao.listHourReportByParams(pageIndex,
					pageLength, endDate);
		} else {
			ps = this.equipmentHourReportDao.listHourReportByParams(pageIndex,
					pageLength, endDate, equName);
		}
		return ps;
	}

	/**
	 * 分页查询-设备日统计
	 * 
	 */
	public List listHourReportCount(Date endDate, String equName) {

		List countList = null;
		if (StringUtils.isBlank(equName)) {
			countList = equipmentHourReportDao.countHourReport(endDate);
		} else {
			countList = equipmentHourReportDao
					.countHourReport(endDate, equName);
		}
		return countList;
	}

	/**
	 * 分页查询-设备月统计
	 * 
	 * @param equName
	 * @return
	 */
	public List buildListDayReportCount(int year, int month, String equName) {
		List countList = null;
        if (StringUtils.isBlank(equName)) {
		    countList = equipmentHourReportDao.countDayReport(month, year);
        } else {
            countList = equipmentHourReportDao.countDayReport(month, year, equName);
        }
		return countList;
	}
    public PageResult PageResultDayReportCount(int year, int month, String equName) {
        logger.debug("year:" + year + " month:" + month  +" equName:" + equName);
        PageResult ps = null;
        if (StringUtils.isBlank(equName)) {
            ps = this.equipmentHourReportDao.listDayReportByParams(year,
                    month);
        } else {
            ps = this.equipmentHourReportDao.listDayReportByParams(year,
                    month, equName);
        }
        return ps;
    }
	/**
	 * 分页查询-设备年统计
	 * 
	 * @param equName
	 * @return
	 */
	public List buildListMonthReportCount(int year, String equName) {
		List countList = null;

		countList = equipmentHourReportDao.countMonthReport(year, equName);
		return countList;
	}
    public PageResult PageResultMonthReportCount(int year,String equName){
        logger.debug("year:" + year  +" equName:" + equName);
        PageResult ps = null;
        if (StringUtils.isBlank(equName)) {
            ps = this.equipmentHourReportDao.listMonthReportByParams(year);
        } else {
            ps = this.equipmentHourReportDao.listMonthReportByParams(year,equName);
        }
        return ps;
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
		equipmentHourReportDao.buildDayReport(date);
		equipmentHourReportDao.buildMonthReport(date);
	}

	public void updBuildReport2(Date date) {
		equipmentHourReportDao.buildDayReport2(date);
	}
}
