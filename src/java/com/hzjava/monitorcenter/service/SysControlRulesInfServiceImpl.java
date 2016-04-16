package com.hzjava.monitorcenter.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.dao.SysControlRulesInfDao;
import com.hzjava.monitorcenter.domain.SysControlRulesInf;

public class SysControlRulesInfServiceImpl implements SysControlRulesInfService{
	private SysControlRulesInfDao sysControlRulesInfDao;
	
	public SysControlRulesInfDao getSysControlRulesInfDao() {
		return sysControlRulesInfDao;
	}

	public void setSysControlRulesInfDao(SysControlRulesInfDao sysControlRulesInfDao) {
		this.sysControlRulesInfDao = sysControlRulesInfDao;
	}
	
	@SuppressWarnings("unchecked")
	public String getPages(int pageIndex,int limit) {
		String json = null;
		PageResult pr = sysControlRulesInfDao.listByPage(pageIndex,limit);
		int total = pr.getAllResultsAmount();
		if(total==0){
			json = "{success:true,total:0,rows:[]}";
		}else{
			json = "{success:true,total:"+total+",rows:[";
			List<SysControlRulesInf> list = pr.getResults();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (SysControlRulesInf s : list) {
				json += "{'id':"+s.getId()+",'fileName':'"+s.getFileName()+"','desc':'"+s.getDesc()+"','collectTime':'"+sdf.format(s.getCollectTime())+"'},";
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
	public String insert(SysControlRulesInf sysControlRulesInf) {
		int before = sysControlRulesInfDao.getCount();
		sysControlRulesInfDao.create(sysControlRulesInf);
		int after = sysControlRulesInfDao.getCount();
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
			Long id = Long.parseLong(""+ids[i]);
			sysControlRulesInfDao.getById(id);
			sysControlRulesInfDao.delete(id);
		}
		
		for (int i = 0; i < ids.length; i++) {
			Long id = Long.parseLong(""+ids[i]);
			SysControlRulesInf inf = (SysControlRulesInf) sysControlRulesInfDao.getById(id);
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
	
	public String update(SysControlRulesInf sysControlRulesInf) {
		SysControlRulesInf older = (SysControlRulesInf) sysControlRulesInfDao.getById(sysControlRulesInf.getId());
		older.setCollectTime(sysControlRulesInf.getCollectTime());
		older.setDesc(sysControlRulesInf.getDesc());
		older.setFileName(sysControlRulesInf.getFileName());
		sysControlRulesInfDao.update(older);
		
		String msg = "修改成功";
		String json = "{success:true,msg:'"+msg+"'}";
		return json;
	}
}
