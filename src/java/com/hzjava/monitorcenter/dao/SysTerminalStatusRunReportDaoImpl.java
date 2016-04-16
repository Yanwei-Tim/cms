package com.hzjava.monitorcenter.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hzjava.monitorcenter.domain.SysTerminalStatusRunReport;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

public class SysTerminalStatusRunReportDaoImpl extends MyDaoSupport implements SysTerminalStatusRunReportDao{

	@Override
	public PageResult findPage(int pageIndex, int pageLength, long idTerminal,
			Date auditDate) {
		buderDayReport(auditDate);
		StringBuffer sb = new StringBuffer(" from SysTerminalStatusRunReport s where 1=1");
		List params = new ArrayList(2);// 手动指定容量，避免多次扩容
		if(!StringUtils.isBlank(""+idTerminal)){
			sb.append(" and s.idTerminal = ?");
			params.add(idTerminal);
		}
		if(auditDate!=null){
			sb.append(" and date_format(s.auditDate,'%Y-%m-%d') = date_format(?,'%Y-%m-%d')");
			params.add(auditDate);
		}
		sb.append(" group by s.userId");
		sb.append(" order by s.id asc"); 
		String countString = "select count(*) " + sb.toString();
		String queryString = sb.toString();

		PageResult ps = this.findByPage(queryString, countString, params
				.toArray(), pageIndex, pageLength);
		logger.info(ps == null ? "ps=null" : "ps.results.size:"
				+ ps.getResults().size());
		return ps;
	}

	@Override
	public void buderDayReport(Date date) {
		StringBuffer sb = new StringBuffer(
			" from SysTerminalStatusRun s where 1=1");
		List params = new ArrayList(3);
		
		sb.append(" and date_format(s.accessTime,'%Y-%m-%d') = date_format(?,'%Y-%m-%d')");
		params.add(date);
		
		sb.append(" group by s.userId");
		sb.insert(0,
			"select new map(s.idTerminal as idTerminal,s.policeNumber as policeNumber," +
			"s.busName as busName,s.accessTime as auditDate,sum(s.flux) as flux,sum(s.count) as count," +
			"s.userId as userId,s.userDepart as userDepart,s.userZone as userZone, s.cardType as cardType,s.accessUrl as accessUrl)");
		String queryString = sb.toString();
		List results = getHibernateTemplate().find(queryString,	params.toArray());
		logger.debug(results == null ? "results=null" : ".results.size:"
				+ results.size());
		for (int i = 0; i < results.size(); i++) {
			Map map = (Map) results.get(i);
			SysTerminalStatusRunReport dayReport = new SysTerminalStatusRunReport();
			dayReport.setBusName(map.get("busName").toString());
			dayReport.setCardType(map.get("cardType").toString());
			dayReport.setIdTerminal(Long.parseLong(map.get("idTerminal").toString()));
			dayReport.setAccessUrl(map.get("accessUrl").toString());
			dayReport.setPoliceNumber(map.get("policeNumber").toString());
			dayReport.setUserZone(map.get("userZone").toString());
			dayReport.setUserDepart(map.get("userDepart").toString());
			dayReport.setUserId(map.get("userId").toString());
			Date auditDate = (Date) map.get("auditDate");
			dayReport.setAuditDate(auditDate);
			dayReport.setCount(Long.parseLong(map.get("count").toString()));
			dayReport.setFlux(Long.parseLong(map.get("flux").toString()));
			/** 查询是否已有重复记录，没有则新增，有则更新 * */
			sb = new StringBuffer(" from SysTerminalStatusRunReport s where 1=1");
			sb.append(" and s.idTerminal = ?");
			sb.append(" and s.userId = ?");
			sb.append(" and date_format(s.auditDate,'%Y-%m-%d') = date_format(?,'%Y-%m-%d')");
			Object[] objects = new Object[] {dayReport.getIdTerminal(),dayReport.getUserId(),dayReport.getAuditDate() };
			
			List list = getHibernateTemplate().find(sb.toString(), objects);
			if (list == null || list.size() == 0) {
				logger.debug("auditDate: " + auditDate + " idTerminal: "
						+ dayReport.getIdTerminal() + " 没有日统计记录，新建记录。");
				getHibernateTemplate().save(dayReport);
			} else {
				logger.debug("auditDate: " + auditDate + " idTerminal: "
						+ dayReport.getIdTerminal() + " 已有日统计记录，更新记录。");
				dayReport = (SysTerminalStatusRunReport) list.get(0);
				getHibernateTemplate().update(dayReport);
			}
		
		}
		
	}

	@Override
	public void setEntityClass() {
		this.entityClass = SysTerminalStatusRunReport.class;
		
	}

}
