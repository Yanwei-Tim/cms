package com.hzjava.monitorcenter.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hzjava.monitorcenter.domain.SysTerminalStatusRun;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;

public class SysTerminalStatusRunDaoImpl extends MyDaoSupport implements SysTerminalStatusRunDao {

	@Override
	public void setEntityClass() {
		this.entityClass = SysTerminalStatusRun.class;
		
	}
	
	public List<SysTerminalStatusRun> findPage(int pageIndex, int pageLength) {
		
		Date date = new Date();		
		StringBuffer sb = new StringBuffer(
			" from SysTerminalStatusRun s where 1=1");
		List params = new ArrayList(3);
		
		sb.append(" and date_format(s.accessTime,'%Y-%m-%d') = date_format(?,'%Y-%m-%d')");
		params.add(date);
		
		sb.append(" group by s.userId");
		sb.insert(0,
			"select new map(s.userId as userId,max(accessTime) as accessTime)");
		String queryString = sb.toString();
		List results = getHibernateTemplate().find(queryString,	params.toArray());
		List<SysTerminalStatusRun> sysTerminalStatusRuns = new ArrayList<SysTerminalStatusRun>();
		for (int i = 0; i < results.size(); i++) {
			Map map = (Map) results.get(i);
			SysTerminalStatusRun stsr = new SysTerminalStatusRun();
			stsr.setUserId(map.get("userId").toString());
			Date accessTime = (Date) map.get("accessTime");
			stsr.setAccessTime(accessTime);
			/** 查询是否已有重复记录，没有则新增，有则更新 * */
			sb = new StringBuffer(" from SysTerminalStatusRun s where 1=1");
			sb.append(" and s.userId = ?");
			sb.append(" and s.accessTime = ?");
			Object[] objects = new Object[] {stsr.getUserId(),stsr.getAccessTime() };
			List list = getHibernateTemplate().find(sb.toString(), objects);
			sysTerminalStatusRuns.add((SysTerminalStatusRun) list.get(0));
		}
		int size = sysTerminalStatusRuns.size();
		List<SysTerminalStatusRun> returns = new ArrayList<SysTerminalStatusRun>();
		int start = ( pageIndex - 1 )* pageLength;
		int index = 0;
		for (int i = start; i < size; i++) {
			if(index < pageLength){
				returns.add(sysTerminalStatusRuns.get(i));
			}
			index ++;
		}
		return returns;
	}	
	
	public SysTerminalStatusRun findByUserId(String userId) {
        String hql = new String("from SysTerminalStatusRun where userId = " + userId + " order by id asc");
        SysTerminalStatusRun s = null;
        try{
            s = (SysTerminalStatusRun) getHibernateTemplate().find(hql).get(0);
        } catch (Exception e){

        }
		return s;
	}

}
