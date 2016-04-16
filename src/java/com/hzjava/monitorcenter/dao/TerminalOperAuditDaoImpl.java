package com.hzjava.monitorcenter.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.TenimalOperationAudit;

public class TerminalOperAuditDaoImpl extends MyDaoSupport implements TerminalOperAuditDao {
	private final static Logger logger = Logger.getLogger(TerminalOperAuditDaoImpl.class);

	@Override
	public void setEntityClass() {
		this.entityClass = TenimalOperationAudit.class;
	}

	public PageResult findPage(
								int pageIndex, 
								int pageLength, 
								String cardId,
								String cardType,
								String policeId,
								String operateTpye,
								String operater,
								Date startDate,
								Date endDate) {
		
		StringBuffer sb = new StringBuffer(" from TenimalOperationAudit s where 1=1");
		List params = new ArrayList(10);// 手动指定容量，避免多次扩容
		if(!StringUtils.isBlank(cardId)){
			sb.append(" and cardId = ?");
			params.add(cardId);
		}
		if(!StringUtils.isBlank(cardType)){
			sb.append(" and cardType = ?");
			params.add(cardType);
		}
		if(!StringUtils.isBlank(policeId)){
			sb.append(" and policeId like ?");
			params.add("%"+policeId+"%");
		}
		if(!StringUtils.isBlank(operateTpye)){
			sb.append(" and operateTpye = ?");
			params.add(operateTpye);
		}
		if(!StringUtils.isBlank(operater)){
			sb.append(" and operater = ?");
			params.add(operater);
		}
		if(startDate!=null){
			sb.append(" and date_format(operateTime,'%Y-%m-%d')>= date_format(?,'%Y-%m-%d')");
			params.add(startDate);
		}
		if(endDate!=null){
			sb.append(" and date_format(operateTime,'%Y-%m-%d')<= date_format(?,'%Y-%m-%d')");
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
