package com.hzjava.monitorcenter.service;

import java.util.List;

import com.hzjava.monitorcenter.dao.SysabnormalObjectTypeDao;
import com.hzjava.monitorcenter.domain.SysabnormalEventCode;
import com.hzjava.monitorcenter.domain.SysabnormalObjectType;

public class SysabnormalObjectTypeServiceImpl implements
		SysabnormalObjectTypeService {
	private SysabnormalObjectTypeDao sysabnormalObjectTypeDao; 
	
	/**
	 * 查找所有
	 */
	public String findAll() {
		String json = "{success:true,rows:[";
		List<SysabnormalObjectType> list = sysabnormalObjectTypeDao.findAll();
		int i = 1;
		for (SysabnormalObjectType sec : list) {
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
	
	public SysabnormalObjectTypeDao getSysabnormalObjectTypeDao() {
		return sysabnormalObjectTypeDao;
	}
	public void setSysabnormalObjectTypeDao(
			SysabnormalObjectTypeDao sysabnormalObjectTypeDao) {
		this.sysabnormalObjectTypeDao = sysabnormalObjectTypeDao;
	}
	

}
