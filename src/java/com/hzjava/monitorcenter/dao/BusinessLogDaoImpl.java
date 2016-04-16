package com.hzjava.monitorcenter.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.BusinessLog;
import com.hzjava.monitorcenter.utils.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class BusinessLogDaoImpl extends MyDaoSupport implements BusinessLogDao {

	@Override
	public void setEntityClass() {
		this.entityClass = BusinessLog.class;
	}

	/**
	 * 分页查询BusinessLog
	 * 
	 * @return
	 */
	public PageResult listLogsByParams(int pageIndex, int pageLength,
			Date startDate, Date endDate, String logLevel, String businessName) {
		StringBuffer sb = new StringBuffer(" from BusinessLog s where 1=1");
		List params = new ArrayList(4);// 手动指定容量，避免多次扩容
		if(startDate!=null){
			sb.append(" and date_format(logTime,'%Y-%m-%d')>= date_format(?,'%Y-%m-%d')");
			params.add(startDate);
		}
		if(endDate!=null){
			sb.append(" and date_format(logTime,'%Y-%m-%d')<= date_format(?,'%Y-%m-%d')");
			params.add(endDate);
		}
		
		if (StringUtils.isNotBlank(logLevel)) {
			sb.append(" and level = ?");
			params.add(logLevel);
		}
		if (StringUtils.isNotBlank(businessName)) {
			sb.append(" and businessName = ?");
			params.add(businessName);
		}
		String countString = "select count(*) " + sb.toString();
		String queryString = sb.toString();

		PageResult ps = this.findByPage(queryString, countString, params
				.toArray(), pageIndex, pageLength);
		logger.debug(ps == null ? "ps=null" : "ps.results.size:"
				+ ps.getResults().size());
		return ps;
	}

    @Override
    public void truncate() throws Exception {
        String sql = "truncate business_log";
        Session session = getSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.executeUpdate();
    }

}
