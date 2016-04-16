package com.hzjava.monitorcenter.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.TernimalAccessAudit;

public class TerminalAccessAuditDaoImpl extends MyDaoSupport implements TerminalAccessAuditDao {
	private final static Logger logger = Logger.getLogger(TerminalAccessAuditDaoImpl.class);

	@Override
	public void setEntityClass() {
		this.entityClass = TernimalAccessAudit.class;
	}

	public PageResult findPage(
								int pageIndex, 
								int pageLength, 
								String policeId,
								String policeName,
								String messageLevel,
								Date startDate,
								Date endDate, String busName, String userDepart, String userZone) {
		
		StringBuffer sb = new StringBuffer(" from TernimalAccessAudit s where 1=1");
		List params = new ArrayList(10);// 手动指定容量，避免多次扩容
		if(!StringUtils.isBlank(policeId)){
			sb.append(" and policeId like ?");
			params.add("%"+policeId+"%");
		}
		if(!StringUtils.isBlank(policeName)){
			sb.append(" and policeName like ?");
			params.add("%"+policeName+"%");
		}
		if(!StringUtils.isBlank(messageLevel)){
			sb.append(" and messageLevel = ?");
			params.add(messageLevel);
		}
		boolean si = StringUtils.isBlank(busName);
		if(!StringUtils.isBlank(busName)){
			sb.append(" and busName like ?");
			params.add("%"+busName+"%");
		}
		if(!StringUtils.isBlank(userDepart)){
			sb.append(" and userDepart = ?");
			params.add(userDepart);
		}
		if(!StringUtils.isBlank(userZone)){
			sb.append(" and userZone = ?");
			params.add(userZone);
		}
		if(startDate!=null){
			sb.append(" and date_format(accessTime,'%Y-%m-%d')>= date_format(?,'%Y-%m-%d')");
			params.add(startDate);
		}
		if(endDate!=null){
			sb.append(" and date_format(accessTime,'%Y-%m-%d')<= date_format(?,'%Y-%m-%d')");
			params.add(endDate);
		}

		String countString = "select count(*) " + sb.toString();
		
		sb.append(" order by id desc");
		String queryString = sb.toString();

		PageResult ps = this.findByPage(queryString, countString, params
				.toArray(), pageIndex, pageLength);
		logger.debug(ps == null ? "ps=null" : "ps.results.size:"
				+ ps.getResults().size());
		return ps;
	}

}
