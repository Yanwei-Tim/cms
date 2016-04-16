package com.hzjava.monitorcenter.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.domain.TenimalList;

public class TenimalDaoImpl extends MyDaoSupport implements TenimalDao {
	private final static Logger logger = Logger.getLogger(TenimalDaoImpl.class);

	@Override
	public void setEntityClass() {
		this.entityClass = TenimalList.class;
	}

	@SuppressWarnings("unchecked")
	public PageResult findPage(int pageIndex, int pageLength, String cardType,
			String state, String blockType, String RadioPolice, String police) {
		StringBuffer sb = new StringBuffer(" from TenimalList s where 1=1");
		List params = new ArrayList();
		if (cardType != null && cardType != "") {
			sb.append(" and cardType=? ");
			params.add(cardType);
		}
		if (state != null && state != "") {
			sb.append(" and status=? ");
			params.add(state);
		}
		if (blockType != null && blockType != "") {
			sb.append(" and blockType=? ");
			params.add(blockType.trim());
		}
		if (RadioPolice != null && RadioPolice != "") {
			if (RadioPolice.equals("code") && police != null && police != "") {
				sb.append(" and policeId=? ");
				params.add(police.trim());
			}
			if (RadioPolice.equals("name") && police != null && police != "") {
				sb.append(" and policeName=? ");
				params.add(police.trim());
			}
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

	@Override
	public PageResult loadById(Long id) {
		StringBuffer sb = new StringBuffer(" from TenimalList s where 1=1");
		List params = new ArrayList(2);
		if (id != null) {
			sb.append(" and id=? ");
			params.add(id);
		}

		String countString = "select count(*) " + sb.toString();

		sb.append(" order by id desc");
		String queryString = sb.toString();

		PageResult ps = this.findByPage(queryString, countString, params
				.toArray(), 1, 1);
		logger.debug(ps == null ? "ps=null" : "ps.results.size:"
				+ ps.getResults().size());
		return ps;
	}

}
