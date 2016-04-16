package com.hzjava.monitorcenter.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.hzjava.monitorcenter.domain.TerminalAccessUrl;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

public class TerminalAccessUrlDaoImpl extends MyDaoSupport implements TerminalAccessUrlDao{

	@Override
	public PageResult findPage(int pageIndex, int pageLength) {
		StringBuffer sb = new StringBuffer(" from TerminalAccessUrl s where 1=1");
		List params = new ArrayList(2);// 手动指定容量，避免多次扩容
		String countString = "select count(*) " + sb.toString();
		sb.append(" order by s.id asc"); 
		String queryString = sb.toString();

		PageResult ps = this.findByPage(queryString, countString, params
				.toArray(), pageIndex, pageLength);
		logger.info(ps == null ? "ps=null" : "ps.results.size:"
				+ ps.getResults().size());
		return ps;
	}
	

	public int getCount() {		
		return super.findAll().size();
	}

    @Override
    public TerminalAccessUrl findByidTerminal(Long idTerminal) {
        String hql = "from TerminalAccessUrl where idTerminal ='"+idTerminal+"'";
        List list=getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0) {
            return (TerminalAccessUrl) list.get(0);
        } else {
            return null;
        }
    }

    public void setEntityClass() {
		this.entityClass = TerminalAccessUrl.class;
	}

}
