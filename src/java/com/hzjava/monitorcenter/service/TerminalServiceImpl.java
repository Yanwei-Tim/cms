package com.hzjava.monitorcenter.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.dao.CodeTableDao;
import com.hzjava.monitorcenter.dao.DistrictDao;
import com.hzjava.monitorcenter.dao.SysClassDao;
import com.hzjava.monitorcenter.dao.SysTerminalStatusDao;
import com.hzjava.monitorcenter.dao.SysTerminalStatusRunDao;
import com.hzjava.monitorcenter.dao.SysTerminalStatusRunReportDao;
import com.hzjava.monitorcenter.dao.TenimalDao;
import com.hzjava.monitorcenter.dao.TerminalAccessAuditDao;
import com.hzjava.monitorcenter.dao.TerminalAccessAuditReportDao;
import com.hzjava.monitorcenter.dao.TerminalOperAuditDao;
import com.hzjava.monitorcenter.domain.CardType;
import com.hzjava.monitorcenter.domain.District;
import com.hzjava.monitorcenter.domain.SysTerminalStatus;
import com.hzjava.monitorcenter.domain.SysTerminalStatusRun;
import com.hzjava.monitorcenter.domain.SysTerminalStatusRunReport;
import com.hzjava.monitorcenter.domain.TenimalList;
import com.hzjava.monitorcenter.domain.TenimalOperationAudit;

public class TerminalServiceImpl implements TerminalService {
	CodeTableDao codeTableDao;
	TerminalOperAuditDao terminalOperAuditDao;
	TerminalAccessAuditDao terminalAccessAuditDao;
	TenimalDao tenimalDao;
	DistrictDao districtDao;
	SysClassDao sysClassDao;
	TerminalAccessAuditReportDao terminalAccessAuditReportDao;
	SysTerminalStatusRunReportDao sysTerminalStatusRunReportDao;
	SysTerminalStatusRunDao sysTerminalStatusRunDao;
	SysTerminalStatusDao sysTerminalStatusDao;
	
	public void setSysTerminalStatusDao(
			SysTerminalStatusDao sysTerminalStatusDao) {
		this.sysTerminalStatusDao = sysTerminalStatusDao;
	}
	
	public void setSysTerminalStatusRunDao(
			SysTerminalStatusRunDao sysTerminalStatusRunDao) {
		this.sysTerminalStatusRunDao = sysTerminalStatusRunDao;
	}
	
	public void setSysTerminalStatusRunReportDao(
			SysTerminalStatusRunReportDao sysTerminalStatusRunReportDao) {
		this.sysTerminalStatusRunReportDao = sysTerminalStatusRunReportDao;
	}

	public void setTenimalDao(TenimalDao tenimalDao) {
		this.tenimalDao = tenimalDao;
	}

	public void setCodeTableDao(CodeTableDao codeTableDao) {
		this.codeTableDao = codeTableDao;
	}

	public void setTerminalOperAuditDao(
			TerminalOperAuditDao terminalOperAuditDao) {
		this.terminalOperAuditDao = terminalOperAuditDao;
	}

	public void setTerminalAccessAuditDao(
			TerminalAccessAuditDao terminalAccessAuditDao) {
		this.terminalAccessAuditDao = terminalAccessAuditDao;
	}
	
	public void setDistrictDao(DistrictDao districtDao) {
		this.districtDao = districtDao;
	}
	
	public void setSysClassDao(SysClassDao sysClassDao) {
		this.sysClassDao = sysClassDao;
	}

	public void setTerminalAccessAuditReportDao(
			TerminalAccessAuditReportDao terminalAccessAuditReportDao) {
		this.terminalAccessAuditReportDao = terminalAccessAuditReportDao;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getAccessAuditModel(int pageIndex, int pageLength,
			String policeId, String policeName, String messageLevel,
			Date startDate, Date endDate, String busName, String userDepart, String userZone) {
		Map model = new HashMap();
		PageResult ps = terminalAccessAuditDao.findPage(pageIndex, pageLength,
				policeId, policeName, messageLevel, startDate, endDate,busName,userDepart,userZone);
		model.put("ps", ps);

		List cardType = codeTableDao.listAllCardType();
		model.put("cardType", cardType);
		
		List district = districtDao.findAll();
		model.put("district", district);
		
		List sysClass = sysClassDao.findAll();
		model.put("sysClass", sysClass);
				
		return model;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getOperAuditModel(int pageIndex, int pageLength, String cardId,
			String cardType, String policeId, String operateTpye,
			String operater, Date startDate, Date endDate) {
		Map model = new HashMap();
		PageResult ps = terminalOperAuditDao.findPage(pageIndex, pageLength,
				cardId, cardType, policeId, operateTpye, operater, startDate,
				endDate);
		model.put("ps", ps);

		List cardTypes = codeTableDao.listAllCardType();
		model.put("cardType", cardTypes);

		return model;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	/**
	 * 终端用户访问统计
	 */
	public Map getAccessAuditReportModel(int pageIndex, int pageLength,
			String userDepart, Date reportDate) {
		Map model = new HashMap();
		PageResult ps = terminalAccessAuditReportDao.findPage(pageIndex, pageLength, userDepart, reportDate);
		model.put("ps", ps);
		List cardTypes = codeTableDao.listAllCardType();
		model.put("cardType", cardTypes);		
		List sysClass = sysClassDao.findAll();
		model.put("sysClass", sysClass);
		
		return model;
	}
	
	/**
	 * 终端用户运行监控日统计
	 */
	public Map getSysTerminalStatusReportModel(int pageIndex, int pageLength,
			Long idTerminal, Date auditDate) {
		Map model = new HashMap();
		PageResult ps = sysTerminalStatusRunReportDao.findPage(pageIndex, pageLength, idTerminal, auditDate);
		model.put("ps", ps);
		List cardTypes = codeTableDao.listAllCardType();
		model.put("cardType", cardTypes);		
		List sysClass = sysClassDao.findAll();
		model.put("sysClass", sysClass);
		List<SysTerminalStatusRunReport> list = ps.getResults();
		List<District> disLists = new ArrayList<District>();
		for (SysTerminalStatusRunReport stsr : list) {
			District district = districtDao.findById(Long.parseLong(stsr.getUserZone()));
			disLists.add(district);			
		}
		model.put("district", disLists);
		return model;
	}
	
	@Override
	public Map getIdTerminalModel() {
		Map model = new HashMap();
		List idTerminals = new ArrayList();
		List list = sysTerminalStatusRunDao.findAll();
		for (int i = 0; i < list.size(); i++) {
			SysTerminalStatusRun sysTerminalStatus =  (SysTerminalStatusRun) list.get(i);
			Long idTerminal = sysTerminalStatus.getIdTerminal();
			idTerminals.add(idTerminal);
		}
		idTerminals = removeDuplicateWithOrder(idTerminals);
		model.put("list", idTerminals);
		return model;
	}
	private List removeDuplicateWithOrder(List list){
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element)){
				newList.add(element);
			}
		}
		return newList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getTenimalModel(int pageIndex, int pageLength, String cardType,
			String state, String blockType, String RadioPolice, String police) {
		Map model = new HashMap();
		PageResult ps = tenimalDao.findPage(pageIndex, pageLength, cardType,
				state, blockType, RadioPolice, police);
		model.put("ps", ps);

		List cardTypes = codeTableDao.listAllCardType();
		model.put("cardType", cardTypes);
		return model;
	}

	/*
	 * date:2012-03-16 author:crj function:显示详细信息
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map loadById(Long id) {
		Map model = new HashMap();
		PageResult ps = tenimalDao.loadById(id);
		model.put("ps", ps);
		List cardTypes = codeTableDao.listAllCardType();
		model.put("cardType", cardTypes);
		return model;
	}

	/*
	 * date:2012-03-16 author:crj function:显示所有的安全卡类型信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CardType> showCardType() {

		return codeTableDao.listAllCardType();
	}

	/*
	 * date:2012-03-16 author:crj function:解除阻断操作和阻断操作
	 */
	@Override
	public void releaseBlock(TenimalList tenimal) {
		TenimalList older = (TenimalList) tenimalDao.getById(tenimal.getId());
		older.setBlockType(tenimal.getBlockType());// 状态 无阻断 A 、临时阻断 B、长久阻断 C
		older.setHour(tenimal.getHour());
		tenimalDao.update(older);
	}

	// date:2012-03-16 author:crj function:创建一条吊销证件记录
	@Override
	public void revokeCert(TenimalOperationAudit t) {
		terminalOperAuditDao.create(t);
	}
	
	// date:2012-03-20 author:qxp function:创建一条截图记录
	public void addCert(TenimalOperationAudit t) {
		terminalOperAuditDao.create(t);		
	}
	
	
	public void addSysTerminalStatus(SysTerminalStatus sysTerminalStatus) {
		SysTerminalStatusRun sysTerminalStatusRun = 
			sysTerminalStatusRunDao.findByUserId(sysTerminalStatus.getUserId());
		if(sysTerminalStatusRun!=null){
            sysTerminalStatus.setAccessUrl(sysTerminalStatusRun.getAccessUrl());
            sysTerminalStatus.setIdTerminal(sysTerminalStatusRun.getIdTerminal());
        }
		sysTerminalStatus.setIdSystem("");
		sysTerminalStatusDao.create(sysTerminalStatus);
		
	}
}
