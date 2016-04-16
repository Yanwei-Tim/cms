package com.hzjava.monitorcenter.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.BusinessDayReport;
import com.hzjava.monitorcenter.domain.BusinessHourReport;
import com.hzjava.monitorcenter.domain.BusinessMonthReport;

public class BusinessHourReportDaoImpl extends MyDaoSupport implements
		BusinessHourReportDao {

	@Override
	public void setEntityClass() {
		this.entityClass = BusinessHourReport.class;
	}

	/**
	 * 分页查询BusinessHourReport
	 * 
	 */
	public PageResult listHourReportByParams(int pageIndex, int pageLength,
			Date endDate) {
		String hql = "from BusinessRegister ";
		String countHql = "select count(*)" + hql;
		PageResult ps = this.findByPage("select businessName " + hql, countHql,
				pageIndex, pageLength);
		String query = "select new map(s.businessName as businessName,sum(s.extDataFlow) as extDataFlow,sum(s.intDataFlow) as intDataFlow,sum(s.extRecordCount) as extRecordCount,sum(s.intRecordCount) as intRecordCount,sum(s.alertCount) as alertCount) from BusinessHourReport s where 1=1 ";
		List params = new ArrayList(1);
		if (endDate != null) {
			query += " and date_format(s.reportDate,'%Y-%m-%d') = date_format(?,'%Y-%m-%d') ";
			params.add(endDate);
		}

		query += " and s.businessName=? group by s.businessName";
		List results = new ArrayList(pageLength);
		for (Iterator iter = ps.getResults().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			List _params = new ArrayList(params);
			_params.add(name);
			List list = getHibernateTemplate().find(query, _params.toArray());
			if (list.size() > 0) {
				results.add(list.get(0));
			} else {
				Map result = new HashMap();
				result.put("businessName", name);
				result.put("extDataFlow", 0);
				result.put("intDataFlow", 0);
				result.put("extRecordCount", 0);
				result.put("intRecordCount", 0);
				result.put("alertCount", 0);

				results.add(result);
			}
		}
		ps.setResults(results);
		logger.debug(ps == null ? "ps=null" : "ps.results.size:"
				+ ps.getResults().size());
		return ps;
	}

	/**
	 * 分页查询BusinessHourReport
	 * 
	 */
	public PageResult listHourReportByParams(int pageIndex, int pageLength,
			Date endDate, String businessName) {
		StringBuffer sb = new StringBuffer(
				" from BusinessHourReport s where 1=1");
		List params = new ArrayList(3);// 手动指定容量，避免多次扩容

		if (endDate != null) {
			sb
					.append(" and date_format(s.reportDate,'%Y-%m-%d') = date_format(?,'%Y-%m-%d')");
			params.add(endDate);
		}
		sb.append(" and s.businessName = ?");
		params.add(businessName);

		String countString = "select count(*) " + sb.toString();
		sb
				.insert(
						0,
						"select new map(s.id as id,s.businessName as businessName,s.reportHour as reportHour,s.extDataFlow as extDataFlow,s.intDataFlow as intDataFlow,s.extRecordCount as extRecordCount,s.intRecordCount as intRecordCount,s.alertCount as alertCount)");
		sb.append(" order by s.reportHour ");
		String queryString = sb.toString();

		PageResult ps = this.findByPage(queryString, countString, params
				.toArray(), pageIndex, pageLength);
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
				" from BusinessHourReport s where 1=1");
		List params = new ArrayList(3);// 手动指定容量，避免多次扩容
		if (endDate != null) {
			sb
					.append(" and date_format(reportDate,'%Y-%m-%d')= date_format(?,'%Y-%m-%d')");
			params.add(endDate);
		}
		sb.append(" group by businessName ");
		sb
				.insert(
						0,
						"select new map(s.businessName as businessName,sum(s.extDataFlow) as extDataFlow,sum(s.intDataFlow) as intDataFlow,sum(s.extRecordCount) as extRecordCount,sum(s.intRecordCount) as intRecordCount,sum(s.alertCount) as alertCount)");
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
	 * @return
	 */
	public List countHourReport(Date endDate, String businessName) {
		StringBuffer sb = new StringBuffer(
				" from BusinessHourReport s where 1=1");
		List params = new ArrayList(3);// 手动指定容量，避免多次扩容
		sb
				.append(" and date_format(reportDate,'%Y-%m-%d') = date_format(?,'%Y-%m-%d')");
		params.add(endDate);
		sb.append(" and businessName = ?");
		params.add(businessName);

		sb
				.insert(
						0,
						"select new map(s.businessName as businessName,s.reportHour as reportHour,s.extDataFlow as extDataFlow,s.intDataFlow as intDataFlow,s.extRecordCount as extRecordCount,s.intRecordCount as intRecordCount,s.alertCount as alertCount)");
		sb.append(" order by s.reportHour ");
		String queryString = sb.toString();
		List results = getHibernateTemplate().find(queryString,
				params.toArray());

		logger.debug(results == null ? "results=null" : ".results.size:"
				+ results.size());
		return results;
	}

	/**
	 * 按月查询日报表
	 * 
	 */
	public List countDayReport(int reportMonth, int reportYear,
			String businessName) {
		StringBuffer sb = new StringBuffer(
				" from BusinessDayReport s where 1=1");
		List params = new ArrayList(3);// 手动指定容量，避免多次扩容

		sb.append(" and reportMonth = ?");
		params.add(reportMonth);
		sb.append(" and reportYear = ?");
		params.add(reportYear);

		if (com.hzjava.monitorcenter.utils.StringUtils.isNotBlank(businessName)) {
			sb.append(" and businessName = ?");
			params.add(businessName);
		}

		sb
				.insert(
						0,
						"select new map(s.businessName as businessName,s.reportMonth as reportMonth,s.reportYear as reportYear,date_format(s.reportDate,'%Y-%m-%d') as reportDate,s.reportDay as reportDay,s.extDataFlow as extDataFlow,s.intDataFlow as intDataFlow,s.extRecordCount as extRecordCount,s.intRecordCount as intRecordCount,s.alertCount as alertCount)");
		String queryString = sb.toString() + " order by reportDay ";
		List results = getHibernateTemplate().find(queryString,
				params.toArray());
		// // 如果查询结果为空，则遍历当月天数，执行日报表生成
		// if(results==null||results.size()<=0){
		// logger.info("月统计-检测到日报表为空，执行遍历统计生成日报表...开始时间："+startDate.getTime()+"
		// 结束时间："+endDate.getTime());
		// while(startDate.compareTo(endDate)<=0){
		// this.buildDayReport(startDate.getTime());
		//				
		// startDate.add(Calendar.DATE, 1);
		// }
		// logger.info("月统计-检测到日报表为空，执行遍历统计生成日报表完成。");
		// }
		// results=getHibernateTemplate().find(queryString, params.toArray());
		logger.debug(results == null ? "results=null" : ".results.size:"
				+ results.size());
		return results;
	}

	/**
	 * 按年查询月报表
	 * 
	 */
	public List countMonthReport(int reportYear, String businessName) {
		StringBuffer sb = new StringBuffer(
				" from BusinessMonthReport s where 1=1");
		List params = new ArrayList(3);// 手动指定容量，避免多次扩容
		sb.append(" and reportYear = ?");
		params.add(reportYear);
		sb.append(" and businessName = ?");
		params.add(businessName);

		sb
				.insert(
						0,
						"select new map(s.businessName as businessName,s.reportMonth as reportMonth,s.extDataFlow as extDataFlow,s.intDataFlow as intDataFlow,s.extRecordCount as extRecordCount,s.intRecordCount as intRecordCount,s.alertCount as alertCount)");
		String queryString = sb.toString();
		List results = getHibernateTemplate().find(queryString,
				params.toArray());
		// // 如果查询结果为空，则遍历当年月份数，执行月报表生成
		// if(results==null||results.size()<=0){
		// logger.info("年统计-检测到月报表为空，执行遍历统计生成月报表...开始时间："+startDate+"
		// 结束时间："+endDate);
		// while(startDate.compareTo(endDate)<=0){
		// Calendar temp=Calendar.getInstance();
		// temp.setTime(startDate.getTime());
		// temp.set(Calendar.MONTH, temp.get(Calendar.MONTH)+1);
		// temp.set(Calendar.DAY_OF_MONTH, -1);
		// this.countDayReport(startDate, temp, businessName);
		// this.buildMonthReport(startDate.getTime());
		//				
		// startDate.add(Calendar.MONTH, 1);
		// }
		// logger.info("年统计-检测到月报表为空，执行遍历统计生成月报表完成。");
		// }
		// results=getHibernateTemplate().find(queryString, params.toArray());
		logger.debug(results == null ? "results=null" : ".results.size:"
				+ results.size());
		return results;
	}

	/**
	 * 按小时统计生成日报表
	 */
	public void buildDayReport(Date date) {
		StringBuffer sb = new StringBuffer(
				" from BusinessHourReport s where 1=1 and state=0");
		List params = new ArrayList(3);

		Calendar c = Calendar.getInstance();
		c.setTime(date);

		sb
				.append(" and date_format(reportDate,'%Y-%m-%d') = date_format(?,'%Y-%m-%d')");
		params.add(date);

		sb.append(" group by businessName");
		sb
				.insert(
						0,
						"select new map(s.businessName as businessName,s.reportDate as reportDate,sum(s.extDataFlow) as extDataFlow,sum(s.intDataFlow) as intDataFlow,sum(s.extRecordCount) as extRecordCount,sum(s.intRecordCount) as intRecordCount,sum(s.alertCount) as alertCount)");
		String queryString = sb.toString();
		List results = getHibernateTemplate().find(queryString,
				params.toArray());
		logger.debug(results == null ? "results=null" : ".results.size:"
				+ results.size());
		for (int i = 0; i < results.size(); i++) {
			Map map = (Map) results.get(i);
			BusinessDayReport dayReport = new BusinessDayReport();
			dayReport.setBusinessName(map.get("businessName").toString());

			Date reportDate = (Date) map.get("reportDate");
			dayReport.setReportDate(reportDate);

			dayReport.setReportDay(c.get(Calendar.DAY_OF_MONTH));
			dayReport.setReportMonth(c.get(Calendar.MONTH) + 1);
			dayReport.setReportYear(c.get(Calendar.YEAR));

			/** 查询是否已有重复记录，没有则新增，有则更新 * */
			sb = new StringBuffer(" from BusinessDayReport s where 1=1");
			sb.append(" and businessName = ?");
			sb.append(" and reportDay = ?");
			sb.append(" and reportMonth = ?");
			sb.append(" and reportYear = ?");
			Object[] objects = new Object[] { dayReport.getBusinessName(),
					dayReport.getReportDay(), dayReport.getReportMonth(),
					dayReport.getReportYear() };
			List list = getHibernateTemplate().find(sb.toString(), objects);
			if (list == null || list.size() == 0) {
				logger.debug("reportDate: " + reportDate + " businessName: "
						+ dayReport.getBusinessName() + " 没有日统计记录，新建记录。");
			} else {
				logger.debug("reportDate: " + reportDate + " businessName: "
						+ dayReport.getBusinessName() + " 已有日统计记录，更新记录。");
				dayReport = (BusinessDayReport) list.get(0);
			}
			dayReport.setAlertCount((Long) map.get("alertCount"));
			dayReport.setExtDataFlow((Double) map.get("extDataFlow"));
			dayReport.setExtRecordCount((Long) map.get("extRecordCount"));
			dayReport.setIntDataFlow((Double) map.get("intDataFlow"));
			dayReport.setIntRecordCount((Long) map.get("intRecordCount"));

			getHibernateTemplate().saveOrUpdate(
					BusinessDayReport.class.getName(), dayReport);
		}

		String updateSql = "update BusinessHourReport set state=1 where reportDate = date_format(?,'%Y-%m-%d')";
		getHibernateTemplate().bulkUpdate(updateSql, date);

	}

	/**
	 * 按日报表统计生成月报表
	 */
	public void buildMonthReport(Date date) {
		StringBuffer sb = new StringBuffer(
				" from BusinessDayReport s where 1=1");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		logger.debug(c.getTime());
		// Calendar start=Calendar.getInstance();
		// start.set(Calendar.YEAR, c.get(Calendar.YEAR));
		// start.set(Calendar.MONTH, c.get(Calendar.MONTH));
		// logger.debug(start.getTime());
		// Calendar end=Calendar.getInstance();
		// end.set(Calendar.YEAR, c.get(Calendar.YEAR));
		// end.set(Calendar.MONTH, c.get(Calendar.MONTH)+1);
		// end.set(Calendar.DATE, -1);
		// logger.debug(end.getTime());
		List params = new ArrayList(3);

		sb.append(" and reportMonth = ?");
		params.add(c.get(Calendar.MONTH) + 1);

		sb.append(" group by businessName");
		sb
				.insert(
						0,
						"select new map(s.businessName as businessName,s.reportDate as reportDate,sum(s.extDataFlow) as extDataFlow,sum(s.intDataFlow) as intDataFlow,sum(s.extRecordCount) as extRecordCount,sum(s.intRecordCount) as intRecordCount,sum(s.alertCount) as alertCount)");
		String queryString = sb.toString();
		List results = getHibernateTemplate().find(queryString,
				params.toArray());
		logger.debug(results == null ? "results=null" : ".results.size:"
				+ results.size());
		for (int i = 0; i < results.size(); i++) {
			Map map = (Map) results.get(i);
			BusinessMonthReport monthReport = new BusinessMonthReport();
			monthReport.setBusinessName(map.get("businessName").toString());

			Date reportDate = (Date) map.get("reportDate");
			monthReport.setReportDate(reportDate);
			monthReport.setReportMonth(c.get(Calendar.MONTH) + 1);
			monthReport.setReportYear(c.get(Calendar.YEAR));

			/** 查询是否已有重复记录，没有则新增，有则更新 * */
			sb = new StringBuffer(" from BusinessMonthReport s where 1=1");
			sb.append(" and businessName = ?");
			sb.append(" and reportMonth = ?");
			sb.append(" and reportYear = ?");
			Object[] objects = new Object[] { monthReport.getBusinessName(),
					monthReport.getReportMonth(), monthReport.getReportYear() };
			List list = getHibernateTemplate().find(sb.toString(), objects);
			if (list == null || list.size() == 0) {
				logger.debug("reportDate: " + reportDate + " businessName: "
						+ monthReport.getBusinessName() + " 没有月统计记录，新建记录。");
			} else {
				logger.debug("reportDate: " + reportDate + " businessName: "
						+ monthReport.getBusinessName() + " 已有月统计记录，更新记录。");
				monthReport = (BusinessMonthReport) list.get(0);
			}

			monthReport.setAlertCount((Long) map.get("alertCount"));
			monthReport.setExtDataFlow((Double) map.get("extDataFlow"));
			monthReport.setExtRecordCount((Long) map.get("extRecordCount"));
			monthReport.setIntDataFlow((Double) map.get("intDataFlow"));
			monthReport.setIntRecordCount((Long) map.get("intRecordCount"));

			getHibernateTemplate().saveOrUpdate(
					BusinessMonthReport.class.getName(), monthReport);
		}

	}

	public Map countDayReport(String businessName, Date now) {
		String hql = "select new map(sum(extDataFlow) as extDataFlow,sum(intDataFlow) as intDataFlow,sum(extRecordCount) as extRecordCount,sum(intRecordCount) as intRecordCount,sum(alertCount) as alertCount) from BusinessHourReport where businessName=? and reportDate = date_format(?,'%Y-%m-%d') group by businessName";
		List list = this.findByMaxResults(hql,
				new Object[] { businessName, now }, 1);
		if (list.size() > 0)
			return (Map) list.get(0);
		else
			return null;
	}

	public void buildDayReport2(Date date) {
		String hql = "select reportDate from BusinessHourReport where state=0 and reportDate<=? group by reportDate";
		List list = getHibernateTemplate().find(hql, date);
		logger.debug(list.size());
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Date rDate = (Date) iter.next();
			buildDayReport(rDate);
			buildMonthReport(rDate);
		}
	}
}
