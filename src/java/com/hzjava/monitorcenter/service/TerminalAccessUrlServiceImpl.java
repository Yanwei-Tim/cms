package com.hzjava.monitorcenter.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.dao.TerminalAccessUrlDao;
import com.hzjava.monitorcenter.domain.TerminalAccessUrl;

public class TerminalAccessUrlServiceImpl implements TerminalAccessUrlService{
	private TerminalAccessUrlDao terminalAccessUrlDao;
	
	public void setTerminalAccessUrlDao(TerminalAccessUrlDao terminalAccessUrlDao) {
		this.terminalAccessUrlDao = terminalAccessUrlDao;
	}

	@Override
	public String findPages(int pageIndex, int pageLength) {
		String json = null;
		PageResult ps = terminalAccessUrlDao.findPage(pageIndex, pageLength);
		int total = ps.getAllResultsAmount();
		if(total==0){
			json = "{success:true,total:0,rows:[]}";
		}else{
			json = "{success:true,total:"+total+",rows:[";
			List<TerminalAccessUrl> list = ps.getResults();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (TerminalAccessUrl t : list) {
				json += "{'id':"+t.getId()+",'accessUrl':'"+t.getAccessUrl()+"','idTerminal':'"+t.getIdTerminal()
					+"','action':'"+t.getAction()+"','busName':'"+t.getBusName()
						+"','resourceRegx':'"+t.getResourceRegx()+"'},";
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
	@Override
	public String create(TerminalAccessUrl entry) {
		int before = terminalAccessUrlDao.getCount();
		terminalAccessUrlDao.create(entry);
		int after = terminalAccessUrlDao.getCount();
		String msg = "新增失败";
		if( before < after){
			msg = "新增成功";
		}
		String json = "{success:true,msg:'"+msg+"'}";
		return json;
	}

	@Override
	public String delete(long[] ids) {
		String msg = "删除失败";
		int code = 0;
		List<Long> list = new ArrayList<Long>(); 
		for (int i = 0; i < ids.length; i++) {
			terminalAccessUrlDao.getById(Long.parseLong(""+ids[i]));
			terminalAccessUrlDao.delete(Long.parseLong(""+ids[i]));
		}
		for (int i = 0; i < ids.length; i++) {
			TerminalAccessUrl inf = (TerminalAccessUrl) terminalAccessUrlDao.getById(Long.parseLong(""+ids[i]));
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

	@Override
	public String update(TerminalAccessUrl entry) {
		TerminalAccessUrl older = (TerminalAccessUrl) terminalAccessUrlDao.getById(entry.getId());
		older.setAccessUrl(entry.getAccessUrl());
		older.setAction(entry.getAction());
		older.setBusName(entry.getBusName());
		older.setIdTerminal(entry.getIdTerminal());
		older.setResourceRegx(entry.getResourceRegx());
		older.setIdTerminal(entry.getIdTerminal());
		terminalAccessUrlDao.update(older);
		String msg = "修改成功";
		String json = "{success:true,msg:'"+msg+"'}";
		return json;
	}
    public TerminalAccessUrl findByIdTerminal(Long idTerminal){
        TerminalAccessUrl older = (TerminalAccessUrl) terminalAccessUrlDao.findByidTerminal(idTerminal);
        return  older;
    }
}
