package com.hzjava.monitorcenter.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.EquipmentDayReport;
import com.hzjava.monitorcenter.domain.TernimalAccessAudit;
import com.hzjava.monitorcenter.domain.TernimalAccessAuditReport;

public class TerminalAccessAuditReportDaoImpl extends MyDaoSupport implements TerminalAccessAuditReportDao {
	private final static Logger logger = Logger.getLogger(TerminalAccessAuditReportDaoImpl.class);

	public void setEntityClass() {
		this.entityClass = TernimalAccessAudit.class;
	}
	
	public PageResult findPage(int pageIndex, int pageLength,
			String userDepart, Date reportDate) {
		buildDayReport(reportDate);
		StringBuffer sb = new StringBuffer(" from TernimalAccessAuditReport s where 1=1");
		List params = new ArrayList(2);// 手动指定容量，避免多次扩容
		if(!StringUtils.isBlank(userDepart)){
			sb.append(" and s.userDepart = ?");
			params.add(userDepart);
		}
		if(reportDate!=null){
			sb.append(" and date_format(s.reportDate,'%Y-%m-%d') = date_format(?,'%Y-%m-%d')");
			params.add(reportDate);
		}
		sb.append(" group by s.userId");
		String countString = "select count(*) " + sb.toString();
		sb.append(" order by s.id asc"); 
		String queryString = sb.toString();

		PageResult ps = this.findByPage(queryString, countString, params
				.toArray(), pageIndex, pageLength);
		logger.info(ps == null ? "ps=null" : "ps.results.size:"
				+ ps.getResults().size());
		return ps;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void buildDayReport(Date date){
		StringBuffer sb = new StringBuffer(
			" from TernimalAccessAudit s where 1=1");
		List params = new ArrayList(3);
		
		sb.append(" and date_format(s.accessTime,'%Y-%m-%d') = date_format(?,'%Y-%m-%d')");
		params.add(date);
		
		sb.append(" group by s.userId");
		sb.insert(0,
			"select new map(s.busName as busName,s.accessTime as reportDate,sum(s.flux) as flux,sum(s.count) as count," +
			"s.userId as userId,s.userDepart as userDepart, s.cardType as cardType)");
		String queryString = sb.toString();
		List results = getHibernateTemplate().find(queryString,	params.toArray());
		logger.debug(results == null ? "results=null" : ".results.size:"
				+ results.size());
		for (int i = 0; i < results.size(); i++) {
			Map map = (Map) results.get(i);
			TernimalAccessAuditReport dayReport = new TernimalAccessAuditReport();
			dayReport.setBusName(map.get("busName").toString());
			dayReport.setCardType(map.get("cardType").toString());

			dayReport.setUserDepart(map.get("userDepart").toString());
			dayReport.setUserId(map.get("userId").toString());
			Date reportDate = (Date) map.get("reportDate");
			dayReport.setReportDate(reportDate);
			dayReport.setCount(Integer.parseInt(map.get("count").toString()));
			dayReport.setFlux(Integer.parseInt(map.get("flux").toString()));
			/** 查询是否已有重复记录，没有则新增，有则更新 * */
			sb = new StringBuffer(" from TernimalAccessAuditReport s where 1=1");
			sb.append(" and s.userDepart = ?");
			sb.append(" and s.userId = ?");
			sb.append(" and date_format(s.reportDate,'%Y-%m-%d') = date_format(?,'%Y-%m-%d')");
			Object[] objects = new Object[] { 
					dayReport.getUserDepart(),dayReport.getUserId(),dayReport.getReportDate() };
			
			List list = getHibernateTemplate().find(sb.toString(), objects);
			if (list == null || list.size() == 0) {
				logger.debug("reportDate: " + reportDate + " userDepart: "
						+ dayReport.getUserDepart() + " 没有日统计记录，新建记录。");
				getHibernateTemplate().save(dayReport);
			} else {
				logger.debug("reportDate: " + reportDate + " userDepart: "
						+ dayReport.getUserDepart() + " 已有日统计记录，更新记录。");
				dayReport = (TernimalAccessAuditReport) list.get(0);
				getHibernateTemplate().update(dayReport);
			}
		
//			getHibernateTemplate().saveOrUpdate(TernimalAccessAuditReport.class.getName(), dayReport);
		}
		
//		String updateSql = "update TernimalAccessAuditReport set state=1 where reportDate = ?";
//		getHibernateTemplate().bulkUpdate(updateSql, date);
		
	}

}
