package com.hzjava.monitorcenter.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.EquipmentDayReport;
import com.hzjava.monitorcenter.domain.EquipmentHourReport;
import com.hzjava.monitorcenter.domain.EquipmentMonthReport;
import com.hzjava.monitorcenter.utils.StringUtils;

public class EquipmentHourReportDaoImpl extends MyDaoSupport implements
		EquipmentHourReportDao {

	@Override
	public void setEntityClass() {
		this.entityClass = EquipmentHourReport.class;
	}

	/**
	 * 分页查询EquipmentHourReport
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageResult listHourReportByParams(int pageIndex, int pageLength,
			Date endDate) {
		StringBuffer sb = new StringBuffer(	" from EquipmentHourReport s where 1=1");
		List params = new ArrayList(3);// 手动指定容量，避免多次扩容
		if (endDate != null) {
			sb.append(" and date_format(reportDate,'%Y-%m-%d')= date_format(?,'%Y-%m-%d')");
			params.add(endDate);
		}
		String countString = "select count(distinct equName) " + sb.toString();
		sb.append(" group by equName ");
		sb.insert(0,"select new map(s.id as id,s.equName as equName,(select ip from Equipment where equName=s.equName) as equIp,sum(s.breakdownCount) as breakdownCount,sum(s.alertCount) as alertCount)");

		String queryString = sb.toString();

		PageResult ps = this.findByPage(queryString, countString, params
				.toArray(), pageIndex, pageLength);
		logger.debug(ps == null ? "ps=null" : "ps.results.size:"
				+ ps.getResults().size());
		return ps;
	}

	/**
	 * 分页查询EquipmentHourReport
	 * 
	 */
	public PageResult listHourReportByParams(int pageIndex, int pageLength,
			Date endDate, String equName) {
		StringBuffer sb = new StringBuffer(
				" from EquipmentHourReport s where 1=1");
		List params = new ArrayList(3);// 手动指定容量，避免多次扩容
		if (endDate != null) {
			sb.append(" and date_format(s.reportDate,'%Y-%m-%d') = date_format(?,'%Y-%m-%d')");
			params.add(endDate);
		}
		sb.append(" and equName = ?");
		params.add(equName);
        String countString = "select count(*) " + sb.toString();
		sb.insert(0,"select new map(s.id as id,s.equName as equName,(select ip from Equipment where equName=s.equName) as equIp,s.reportTime as reportTime,s.breakdownCount as breakdownCount,s.alertCount as alertCount)");
		sb.append(" order by s.reportTime");
		String queryString = sb.toString();

		PageResult ps = this.findByPage(queryString, countString, params
				.toArray(), pageIndex, pageLength);
		logger.debug(ps == null ? "ps=null" : "ps.results.size:"
				+ ps.getResults().size());
		return ps;
	}
    public PageResult listDayReportByParams(int year, int month,
                                             String equName) {
        StringBuffer sb = new StringBuffer(
                " from EquipmentDayReport s where 1=1");
        List params = new ArrayList(3);// 手动指定容量，避免多次扩容
        sb.append(" and reportYear = ?");
        params.add(year);
        sb.append(" and reportMonth = ?");
        params.add(month);
        sb.append(" and equName = ?");
        params.add(equName);
        String countString = "select count(*) " + sb.toString();
        sb.insert(0,"select new map(s.equName as equName,s.reportYear as reportYear,s.reportMonth as reportMonth," +
                "date_format(s.reportDate,'%Y-%m-%d') as reportDate,s.reportDay as reportDay,s.breakdownCount as breakdownCount,s.alertCount as alertCount)");
        sb.append(" order by s.reportDay ");
        String queryString = sb.toString();

        PageResult ps = this.findByPage(queryString, countString, params
                .toArray(), 1, 100);
        logger.debug(ps == null ? "ps=null" : "ps.results.size:"
                + ps.getResults().size());
        return ps;
    }
    public PageResult listMonthReportByParams(int year,String equName){
        StringBuffer sb = new StringBuffer(
                " from EquipmentMonthReport s where 1=1");
        List params = new ArrayList(2);// 手动指定容量，避免多次扩容
        sb.append(" and reportYear = ?");
        params.add(year);
        sb.append(" and equName = ?");
        params.add(equName);
        String countString = "select count(*) " + sb.toString();
//        sb.insert(0,"select new map(s.equName as equName,s.reportYear as reportYear,s.reportMonth as reportMonth," +
//                "date_format(s.reportDate,'%Y-%m-%d') as reportDate,s.reportDay as reportDay,s.breakdownCount as breakdownCount,s.alertCount as alertCount)");
        sb.insert(0,"select new map(s.equName as equName,s.reportMonth as reportMonth,s.breakdownCount as breakdownCount,s.alertCount as alertCount)");
        sb.append(" order by s.reportMonth ");
        String queryString = sb.toString();

        PageResult ps = this.findByPage(queryString, countString, params
                .toArray(), 1, 100);
        logger.debug(ps == null ? "ps=null" : "ps.results.size:"
                + ps.getResults().size());
        return ps;
    }
    public PageResult listMonthReportByParams(int year){
        StringBuffer sb = new StringBuffer(
                " from EquipmentMonthReport s where 1=1");
        List params = new ArrayList(2);// 手动指定容量，避免多次扩容
        sb.append(" and reportYear = ?");
        params.add(year);
        String countString = "select count(distinct equName) " + sb.toString();
        sb.append(" group by equName ");
        sb.insert(0,"select new map(s.equName as equName,s.reportMonth as reportMonth,sum(s.breakdownCount) as breakdownCount,sum(s.alertCount)  as alertCount)");

//        sb.insert(0,"select new map(s.equName as equName,s.reportYear as reportYear,s.reportMonth as reportMonth," +
//                "date_format(s.reportDate,'%Y-%m-%d') as reportDate,s.reportDay as reportDay,sum(s.breakdownCount) as breakdownCount,sum(s.alertCount) as alertCount)");
        sb.append(" order by s.reportMonth ");
        String queryString = sb.toString();

        PageResult ps = this.findByPage(queryString, countString, params
                .toArray(), 1, 100);
        logger.debug(ps == null ? "ps=null" : "ps.results.size:"
                + ps.getResults().size());
        return ps;
    }
    public PageResult listDayReportByParams(int year, int month){
        StringBuffer sb = new StringBuffer(
                " from EquipmentDayReport s where 1=1");
        List params = new ArrayList(2);// 手动指定容量，避免多次扩容
        sb.append(" and reportYear = ?");
        params.add(year);
        sb.append(" and reportMonth = ?");
        params.add(month);
        String countString = "select count(distinct equName) " + sb.toString();
        sb.append(" group by equName ");
//        sb.insert(0,"select new map(s.id as id,s.equName as equName,(select ip from Equipment where equName=s.equName) as equIp,s.reportTime as reportTime,s.breakdownCount as breakdownCount,s.alertCount as alertCount)");
//        sb.append(" order by s.reportTime");
        sb.insert(0,"select new map(s.equName as equName,s.reportYear as reportYear,s.reportMonth as reportMonth," +
                "date_format(s.reportDate,'%Y-%m-%d') as reportDate,s.reportDay as reportDay,sum(s.breakdownCount) as breakdownCount,sum(s.alertCount) as alertCount)");
        sb.append(" order by s.reportDay ");
        String queryString = sb.toString();

        PageResult ps = this.findByPage(queryString, countString, params
                .toArray(), 1, 100);
        logger.debug(ps == null ? "ps=null" : "ps.results.size:"
                + ps.getResults().size());
        return ps;
    }

	/**
	 * 按业务名为分组统计各项数据
	 * 
	 */
	public List countHourReport(Date endDate) {
		StringBuffer sb = new StringBuffer(
				" from EquipmentHourReport s where 1=1");
		List params = new ArrayList(3);// 手动指定容量，避免多次扩容
		if (endDate != null) {
			sb
					.append(" and date_format(reportDate,'%Y-%m-%d') = date_format(?,'%Y-%m-%d')");
			params.add(endDate);
		}
		sb.append(" group by equName ");
		sb
				.insert(
						0,
						"select new map(s.equName as equName,sum(s.breakdownCount) as breakdownCount,sum(s.alertCount) as alertCount)");
		String queryString = sb.toString();
		List results = getHibernateTemplate().find(queryString,
				params.toArray());

		logger.debug(results == null ? "results=null" : ".results.size:"
				+ results.size());
		return results;
	}

	/**
	 * 按日查询小时报表
	 * 
	 * @param date
	 * @return
	 */
	public List countHourReport(Date date, String equName) {
		StringBuffer sb = new StringBuffer(
				" from EquipmentHourReport s where 1=1");
		List params = new ArrayList(3);// 手动指定容量，避免多次扩容
		if (date != null) {
			sb.append(" and date_format(reportDate,'%Y-%m-%d') = date_format(?,'%Y-%m-%d')");
			params.add(date);
		}
		sb.append(" and equName = ?");
		params.add(equName);
		sb.insert(0,"select new map(s.equName as equName,s.reportTime as reportTime,s.breakdownCount as breakdownCount,s.alertCount as alertCount)");
		sb.append(" order by s.reportTime ");
		String queryString = sb.toString();
		List results = getHibernateTemplate().find(queryString,
				params.toArray());

		logger.debug(results == null ? "results=null" : ".results.size:"
				+ results.size());
		return results;
	}

    /**
     *  按月查询日报表
     * @param reportMonth
     * @param reportYear
     * @param equName
     * @return
     */
	public List countDayReport(int reportMonth, int reportYear, String equName) {
		StringBuffer sb = new StringBuffer(" from EquipmentDayReport s where 1=1");
		List params = new ArrayList(3);// 手动指定容量，避免多次扩容
		sb.append(" and reportMonth = ?");
		params.add(reportMonth);
		sb.append(" and reportYear = ?");
		params.add(reportYear);
        sb.append(" and equName = ?");
        params.add(equName);
        sb.insert(0,"select new map(s.equName as equName,s.reportYear as reportYear,s.reportMonth as reportMonth," +
				"date_format(s.reportDate,'%Y-%m-%d') as reportDate,s.reportDay as reportDay,s.breakdownCount as breakdownCount,s.alertCount as alertCount)");
		sb.append(" order by s.reportDay ");
		String queryString = sb.toString();
		List results = getHibernateTemplate().find(queryString,
				params.toArray());
		logger.debug(results == null ? "results=null" : ".results.size:"
				+ results.size());
		return results;
	}
    public List countDayReport(int reportMonth, int reportYear) {
        StringBuffer sb = new StringBuffer(
                " from EquipmentDayReport s where 1=1");
        List params = new ArrayList(2);// 手动指定容量，避免多次扩容
        sb.append(" and reportMonth = ?");
        params.add(reportMonth);
        sb.append(" and reportYear = ?");
        params.add(reportYear);
        sb.insert(0,"select new map(s.equName as equName,s.reportYear as reportYear,s.reportMonth as reportMonth," +
                "date_format(s.reportDate,'%Y-%m-%d') as reportDate,s.reportDay as reportDay,s.breakdownCount as breakdownCount,s.alertCount as alertCount)");
        sb.append(" order by s.reportDay ");
        String queryString = sb.toString();
        String countString = "select count(*) " + sb.toString();
        List results = getHibernateTemplate().find(queryString,params.toArray());
        logger.debug(results == null ? "results=null" : ".results.size:"
                + results.size());
        return results;
    }



    /**
     *  按年查询月报表
     * @param reportYear
     * @param equName
     * @return
     */
	public List countMonthReport(int reportYear, String equName) {
		StringBuffer sb = new StringBuffer(
				" from EquipmentMonthReport s where 1=1");
		List params = new ArrayList(2);// 手动指定容量，避免多次扩容
		sb.append(" and reportYear = ?");
		params.add(reportYear);
        if (StringUtils.isNotBlank(equName)) {
            sb.append(" and equName = ?");
            params.add(equName);
        }

		sb.insert(0,"select new map(s.equName as equName,s.reportMonth as reportMonth,s.breakdownCount as breakdownCount,s.alertCount as alertCount)");
		String queryString = sb.toString();
		List results = getHibernateTemplate().find(queryString,
				params.toArray());
		logger.debug(results == null ? "results=null" : ".results.size:"
				+ results.size());
		return results;
	}

	/**
	 * 按小时统计生成日报表
	 */
	public void buildDayReport(Date date) {
		StringBuffer sb = new StringBuffer(
				" from EquipmentHourReport s where 1=1 and state=0");
		List params = new ArrayList(3);

		Calendar c = Calendar.getInstance();
		c.setTime(date);

		sb.append(" and reportDate = date_format(?,'%Y-%m-%d')");
		params.add(date);

		sb.append(" group by equName");
		sb
				.insert(
						0,
						"select new map(s.equName as equName,s.reportDate as reportDate,sum(s.breakdownCount) as breakdownCount,sum(s.alertCount) as alertCount)");
		String queryString = sb.toString();
		List results = getHibernateTemplate().find(queryString,
				params.toArray());
		logger.debug(results == null ? "results=null" : ".results.size:"
				+ results.size());
		for (int i = 0; i < results.size(); i++) {
			Map map = (Map) results.get(i);
			EquipmentDayReport dayReport = new EquipmentDayReport();
			dayReport.setEquName(map.get("equName").toString());
			dayReport.setReportDay(c.get(Calendar.DAY_OF_MONTH));
			dayReport.setReportMonth(c.get(Calendar.MONTH) + 1);
			dayReport.setReportYear(c.get(Calendar.YEAR));
			Date reportDate = (Date) map.get("reportDate");

			/** 查询是否已有重复记录，没有则新增，有则更新 * */
			sb = new StringBuffer(" from EquipmentDayReport s where 1=1");
			sb.append(" and equName = ?");
			sb.append(" and reportDay = ?");
			sb.append(" and reportMonth = ?");
			sb.append(" and reportYear = ?");
			Object[] objects = new Object[] { dayReport.getEquName(),
					dayReport.getReportDay(), dayReport.getReportMonth(),
					dayReport.getReportYear() };
			List list = getHibernateTemplate().find(sb.toString(), objects);
			if (list == null || list.size() == 0) {
				logger.debug("reportDate: " + reportDate + " equName: "
						+ dayReport.getEquName() + " 没有日统计记录，新建记录。");
			} else {
				logger.debug("reportDate: " + reportDate + " equName: "
						+ dayReport.getEquName() + " 已有日统计记录，更新记录。");
				dayReport = (EquipmentDayReport) list.get(0);
			}

			dayReport.setReportDate(reportDate);
			dayReport.setAlertCount((Long) map.get("alertCount"));
			dayReport.setBreakdownCount((Long) map.get("breakdownCount"));

			getHibernateTemplate().saveOrUpdate(
					EquipmentDayReport.class.getName(), dayReport);
		}
		
		String updateSql = "update EquipmentHourReport set state=1 where reportDate = date_format(?,'%Y-%m-%d')";
		getHibernateTemplate().bulkUpdate(updateSql, date);

	}

	/**
	 * 按日报表统计生成月报表
	 */
	public void buildMonthReport(Date date) {
		StringBuffer sb = new StringBuffer(
				" from EquipmentDayReport s where 1=1");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		logger.debug(c.getTime());
		List params = new ArrayList(3);

		sb.append(" and reportMonth = ?");
		params.add(c.get(Calendar.MONTH) + 1);

		sb.append(" group by equName");
		sb
				.insert(
						0,
						"select new map(s.equName as equName,s.reportDate as reportDate,sum(s.breakdownCount) as breakdownCount,sum(s.alertCount) as alertCount)");
		String queryString = sb.toString();
		List results = getHibernateTemplate().find(queryString,
				params.toArray());
		logger.debug(results == null ? "results=null" : ".results.size:"
				+ results.size());
		for (int i = 0; i < results.size(); i++) {
			Map map = (Map) results.get(i);
			EquipmentMonthReport monthReport = new EquipmentMonthReport();
			monthReport.setEquName(map.get("equName").toString());

			Date reportDate = (Date) map.get("reportDate");
			monthReport.setReportDate(reportDate);

			monthReport.setReportMonth(c.get(Calendar.MONTH) + 1);
			monthReport.setReportYear(c.get(Calendar.YEAR));

			/** 查询是否已有重复记录，没有则新增，有则更新 * */
			sb = new StringBuffer(" from EquipmentMonthReport s where 1=1");
			sb.append(" and equName = ?");
			sb.append(" and reportMonth = ?");
			sb.append(" and reportYear = ?");
			Object[] objects = new Object[] { monthReport.getEquName(),
					monthReport.getReportMonth(), monthReport.getReportYear() };
			List list = getHibernateTemplate().find(sb.toString(), objects);
			if (list == null || list.size() == 0) {
				logger.debug("reportDate: " + reportDate + " equName: "
						+ monthReport.getEquName() + " 没有月统计记录，新建记录。");
			} else {
				logger.debug("reportDate: " + reportDate + " equName: "
						+ monthReport.getEquName() + " 已有月统计记录，更新记录。");
				monthReport = (EquipmentMonthReport) list.get(0);
			}

			monthReport.setAlertCount((Long) map.get("alertCount"));
			monthReport.setBreakdownCount((Long) map.get("breakdownCount"));

			getHibernateTemplate().saveOrUpdate(
					EquipmentMonthReport.class.getName(), monthReport);

		}

	}

	public void buildDayReport2(Date date) {
		String hql = "select reportDate from EquipmentHourReport where state=0 and reportDate<=? group by reportDate";
		List list = getHibernateTemplate().find(hql, date);
		logger.debug(list.size());
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Date rDate = (Date) iter.next();
			buildDayReport(rDate);
			buildMonthReport(rDate);
		}
	}
}
