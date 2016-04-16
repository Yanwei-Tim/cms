package com.hzjava.monitorcenter.service;

import java.util.List;

import com.hzjava.monitorcenter.dao.SysabnormalEventCodeDao;
import com.hzjava.monitorcenter.domain.SysabnormalEventCode;

public class SysabnormalEventCodeServiceImpl implements
		SysabnormalEventCodeService {
	private SysabnormalEventCodeDao sysabnormalEventCodeDao; 
	
	/**
	 * 查找所有
	 */
	public String findAll() {
		String json = "{success:true,rows:[";
		List<SysabnormalEventCode> list = sysabnormalEventCodeDao.findAll();
		int i = 1;
		for (SysabnormalEventCode sec : list) {
			if(i == list.size()){
				json += "{'code':'"+sec.getCode()+"','name':'"+sec.getName()+"'}";
			}else{
				json += "{'code':'"+sec.getCode()+"','name':'"+sec.getName()+"'},";
			}
			i++;
		}
		json += "]}";
		return json;
	}
	
	public SysabnormalEventCodeDao getSysabnormalEventCodeDao() {
		return sysabnormalEventCodeDao;
	}
	public void setSysabnormalEventCodeDao(
			SysabnormalEventCodeDao sysabnormalEventCodeDao) {
		this.sysabnormalEventCodeDao = sysabnormalEventCodeDao;
	}
	

}
