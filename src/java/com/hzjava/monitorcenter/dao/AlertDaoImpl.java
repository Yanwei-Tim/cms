package com.hzjava.monitorcenter.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.utils.StringUtils;

public class AlertDaoImpl extends MyDaoSupport implements AlertDao {
	@Override
	public void setEntityClass() {

	}

	/**
	 * 分页查询BusinessExceptAlert
	 * 
	 */
	public PageResult listBsExpAlert(int pageIndex, int pageLength,
			Date startDate, Date endDate, String businessName, String alertCode) {
		StringBuffer sb = new StringBuffer(
				" from BusinessExceptAlert s where 1=1");
		List params = new ArrayList(3);// 手动指定容量，避免多次扩容
		if(startDate!=null){
			sb.append(" and date_format(alertTime,'%Y-%m-%d')>= date_format(?,'%Y-%m-%d')");
			params.add(startDate);
		}
		if(endDate!=null){
			sb.append(" and date_format(alertTime,'%Y-%m-%d')<= date_format(?,'%Y-%m-%d')");
			params.add(endDate);
		}
		if (StringUtils.isNotBlank(businessName)) {
			sb.append(" and businessName = ?");
			params.add(businessName);
		}
		if (StringUtils.isNotBlank(alertCode)) {
			sb.append(" and exceptCode = ?");
			params.add(alertCode);
		}

		String countString = "select count(*) " + sb.toString();
		;
		sb
				.insert(
						0,
						"select new map(s.id as id,s.ip as ip,s.userName as userName,s.isRead as isRead,s.businessName as businessName,s.exceptCode as alertType,date_format(s.alertTime,'%Y-%m-%d %H:%i:%s') as alertTime,s.alertInfo as alertInfo)");

		String queryString = sb.toString();

		PageResult ps = this.findByPage(queryString+" order by s.id desc", countString, params
				.toArray(), pageIndex, pageLength);
		logger.debug(ps == null ? "ps=null" : "ps.results.size:"
				+ ps.getResults().size());
		return ps;
	}

	/**
	 * 分页查询SecurityEventAlert
	 * 
	 */
	public PageResult listScEventAlert(int pageIndex, int pageLength,
			Date startDate, Date endDate, String equName, String alertCode) {
		StringBuffer sb = new StringBuffer(
				" from SecurityEventAlert s where 1=1");
		List params = new ArrayList(4);// 手动指定容量，避免多次扩容
		if(startDate!=null){
			sb.append(" and date_format(alertTime,'%Y-%m-%d')>= date_format(?,'%Y-%m-%d')");
			params.add(startDate);
		}
		if(endDate!=null){
			sb.append(" and date_format(alertTime,'%Y-%m-%d')<= date_format(?,'%Y-%m-%d')");
			params.add(endDate);
		}
		if (StringUtils.isNotBlank(equName)) {
			sb.append(" and equName = ?");
			params.add(equName);
		}
		if (StringUtils.isNotBlank(alertCode)) {
			sb.append(" and eventCode = ?");
			params.add(alertCode);
		}

		String countString = "select count(*) " + sb.toString();
		;
		sb
				.insert(
						0,
						"select new map(s.id as id,s.ip as ip,s.isRead as isRead,s.equName as equName,s.eventCode as alertType,date_format(s.alertTime,'%Y-%m-%d %H:%i:%s') as alertTime,s.alertInfo as alertInfo)");

		String queryString = sb.toString();

		PageResult ps = this.findByPage(queryString+" order by s.id desc", countString, params
				.toArray(), pageIndex, pageLength);
		logger.debug(ps == null ? "ps=null" : "ps.results.size:"
				+ ps.getResults().size());
		return ps;
	}

	/**
	 * 分页查询EquipmentAlert
	 * 
	 */
	public PageResult listEquAlert(int pageIndex, int pageLength,
			Date startDate, Date endDate, String equName) {
		StringBuffer sb = new StringBuffer(" from EquipmentAlert s where 1=1");
		List params = new ArrayList(4);// 手动指定容量，避免多次扩容
		if(startDate!=null){
			sb.append(" and date_format(alertTime,'%Y-%m-%d')>= date_format(?,'%Y-%m-%d')");
			params.add(startDate);
		}
		if(endDate!=null){
			sb.append(" and date_format(alertTime,'%Y-%m-%d')<= date_format(?,'%Y-%m-%d')");
			params.add(endDate);
		}
		if (StringUtils.isNotBlank(equName)) {
			sb.append(" and equName = ?");
			params.add(equName);
		}

		String countString = "select count(*) " + sb.toString();
		;
		sb
				.insert(
						0,
						"select new map(s.id as id,s.ip as ip,s.isRead as isRead,s.equName as equName,date_format(s.alertTime,'%Y-%m-%d %H:%i:%s') as alertTime,s.alertInfo as alertInfo)");

		String queryString = sb.toString();

		PageResult ps = this.findByPage(queryString+" order by s.id desc", countString, params
				.toArray(), pageIndex, pageLength);
		logger.debug(ps == null ? "ps=null" : "ps.results.size:"
				+ ps.getResults().size());
		return ps;
	}

	public void execute(String hql) {
		this.getHibernateTemplate().bulkUpdate(hql);
	}

}
