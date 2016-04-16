package com.hzjava.monitorcenter.service;

import java.util.ArrayList;
import java.util.List;

import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.dao.SysAbnormalInfDao;
import com.hzjava.monitorcenter.domain.SysAbnormalInf;

public class SysAbnormalInfServiceImpl implements SysAbnormalInfService{
	private SysAbnormalInfDao sysAbnormalInfDao;

	public SysAbnormalInfDao getSysAbnormalInfDao() {
		return sysAbnormalInfDao;
	}

	public void setSysAbnormalInfDao(SysAbnormalInfDao sysAbnormalInfDao) {
		this.sysAbnormalInfDao = sysAbnormalInfDao;
	}
	
	@SuppressWarnings("unchecked")
	public String getPages(int pageIndex,int limit) {
		String json = null;
		PageResult pr = sysAbnormalInfDao.listByPage(pageIndex,limit);
		int total = pr.getAllResultsAmount();
		if(total==0){
			json = "{success:true,total:0,rows:[]}";
		}else{
			json = "{success:true,total:"+total+",rows:[";
			List<SysAbnormalInf> list = pr.getResults();
			for (SysAbnormalInf s : list) {
				json += "{'id':"+s.getId()+",'abnormalTypeCode':'"+s.getAbnormalTypeCode()+"','connectObjectType':'"+s.getConnectObjectType()
						+"','exceptionDesc':'"+s.getExceptionDesc()+"','exceptionIf':'"+s.getExceptionIf()
						+"','action':'"+s.getAction()+"','status':'"+s.getStatus()+"'},";
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
	public String insert(SysAbnormalInf sysAbnormalInf) {
		int before = sysAbnormalInfDao.getCount();
		sysAbnormalInfDao.create(sysAbnormalInf);
		int after = sysAbnormalInfDao.getCount();
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
			sysAbnormalInfDao.getById(Long.parseLong(""+ids[i]));
			sysAbnormalInfDao.delete(Long.parseLong(""+ids[i]));
		}
		for (int i = 0; i < ids.length; i++) {
			SysAbnormalInf inf = (SysAbnormalInf) sysAbnormalInfDao.getById(Long.parseLong(""+ids[i]));
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
	
	public String update(SysAbnormalInf sysAbnormalInf) {
		SysAbnormalInf older = (SysAbnormalInf) sysAbnormalInfDao.getById(sysAbnormalInf.getId());
		older.setAbnormalTypeCode(sysAbnormalInf.getAbnormalTypeCode());
		older.setConnectObjectType(sysAbnormalInf.getConnectObjectType());
		older.setExceptionIf(sysAbnormalInf.getExceptionIf());
		older.setExceptionDesc(sysAbnormalInf.getExceptionDesc());
		older.setAction(sysAbnormalInf.getAction());
		older.setStatus(sysAbnormalInf.getStatus());
		sysAbnormalInfDao.update(older);
		
		String msg = "修改成功";
		String json = "{success:true,msg:'"+msg+"'}";
		return json;
	}

}
