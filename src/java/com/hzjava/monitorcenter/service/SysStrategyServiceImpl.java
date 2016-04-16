package com.hzjava.monitorcenter.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.dao.SysStrategyInfDao;
import com.hzjava.monitorcenter.domain.SysStrategyInf;

public class SysStrategyServiceImpl implements SysStrategyService{
	private SysStrategyInfDao sysStrategyInfDao;

	public SysStrategyInfDao getSysStrategyInfDao() {
		return sysStrategyInfDao;
	}

	public void setSysStrategyInfDao(SysStrategyInfDao sysStrategyInfDao) {
		this.sysStrategyInfDao = sysStrategyInfDao;
	}
	
	@SuppressWarnings("unchecked")
	public String getPages(int pageIndex,int limit) {
		String json = null;
		PageResult pr = sysStrategyInfDao.listByPage(pageIndex,limit);
		int total = pr.getAllResultsAmount();
		if(total==0){
			json = "{success:true,total:0,rows:[]}";
		}else{
			json = "{success:true,total:"+total+",rows:[";
			List<SysStrategyInf> list = pr.getResults();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (SysStrategyInf s : list) {
				json += "{'id':"+s.getId()+",'srcIp':'"+s.getSrcIp()+"','descIp':'"+s.getDescIp()+"','srcPort':'"+s.getSrcPort()+"','descPort':'"+s.getDescPort()
						+"','strategyDesc':'"+s.getStrategyDesc()+"','strategyProtocalType':'"+s.getStrategyProtocalType()+"','collectTime':'"+sdf.format(s.getCollectTime())+"'},";
			}
			json += "]}";
			json = trim(json);
		}
		return json;
	}
	
	private String trim(String str){
        String[] ary = str.split(",]");
        if(ary.length > 1 ){
        	str = ary[0]+"]"+ary[1];
        }
        return str;
    }
	public String insert(SysStrategyInf sysStrategyInf) {
		int before = sysStrategyInfDao.getCount();
		sysStrategyInfDao.create(sysStrategyInf);
		int after = sysStrategyInfDao.getCount();
		String msg = "新增失败";
		if( before < after){
			msg = "新增成功";
		}
		String json = "{success:true,msg:'"+msg+"'}";
		return json;
	}
	
	public String delete(long[] ids) {
		String msg = "删除失败";
		int code = 0;
		List<Long> list = new ArrayList<Long>(); 
		for (int i = 0; i < ids.length; i++) {
			sysStrategyInfDao.getById(Long.parseLong(""+ids[i]));
			sysStrategyInfDao.delete(Long.parseLong(""+ids[i]));
		}
		for (int i = 0; i < ids.length; i++) {
			SysStrategyInf inf = (SysStrategyInf) sysStrategyInfDao.getById(Long.parseLong(""+ids[i]));
			if(inf == null){
				msg = "删除成功";
				code = 200;
			}else{
				list.add(ids[i]); 
			}
		}
		if(list.size()>0){
			msg += ",[";
			for (Long l : list) {
				msg += l+",";
			}				
			msg += "]没有成功删除";
			code = 300;
		}
		msg = trim(msg);
		String json = "{success:true,msg:'"+msg+"',code:"+code+"}";
		return json;
	}
	
	public String update(SysStrategyInf sysStrategyInf) {
		SysStrategyInf older = (SysStrategyInf) sysStrategyInfDao.getById(sysStrategyInf.getId());
		older.setCollectTime(sysStrategyInf.getCollectTime());
		older.setDescIp(sysStrategyInf.getDescIp());
		older.setDescPort(sysStrategyInf.getDescPort());
		older.setSrcIp(sysStrategyInf.getSrcIp());
		older.setSrcPort(sysStrategyInf.getSrcPort());
		older.setStrategyDesc(sysStrategyInf.getStrategyDesc());
		older.setStrategyProtocalType(sysStrategyInf.getStrategyProtocalType());
		sysStrategyInfDao.update(older);
		
		String msg = "修改成功";
		String json = "{success:true,msg:'"+msg+"'}";
		return json;
	}

}
