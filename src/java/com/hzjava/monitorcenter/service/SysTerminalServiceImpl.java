package com.hzjava.monitorcenter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.collin.commons.domain.PageResult;
import cn.collin.commons.utils.DateUtils;

import com.hzjava.monitorcenter.dao.*;
import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.SysTerminalInfo;
import com.hzjava.monitorcenter.domain.TerminalAddress;
import com.hzjava.monitorcenter.domain.UserOperLog;

public class SysTerminalServiceImpl implements SysTerminalService {
	SysTerminalInfoDao sysTerminalInfoDao;
	CommonDao commonDao;
	UserOperLogDao userOperLogDao;
	CodeTableDao codeTableDao;
	DistrictDao districtDao;
	SysClassDao sysClassDao;
    TerminalAddressDao terminalAddressDao;

    public void setTerminalAddressDao(TerminalAddressDao terminalAddressDao) {
        this.terminalAddressDao = terminalAddressDao;
    }

    public void setSysClassDao(SysClassDao sysClassDao) {
		this.sysClassDao = sysClassDao;
	}

	public void setDistrictDao(DistrictDao districtDao) {
		this.districtDao = districtDao;
	}

	public void setSysTerminalInfoDao(SysTerminalInfoDao sysTerminalInfoDao) {
		this.sysTerminalInfoDao = sysTerminalInfoDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void setUserOperLogDao(UserOperLogDao userOperLogDao) {
		this.userOperLogDao = userOperLogDao;
	}

	public void setCodeTableDao(CodeTableDao codeTableDao) {
		this.codeTableDao = codeTableDao;
	}

	public void deleteSysTerminal(String[] idsArr, Account account) {
		for (String id : idsArr) {
			SysTerminalInfo item = (SysTerminalInfo) sysTerminalInfoDao
					.getById(Long.valueOf(id));
			UserOperLog entry = new UserOperLog();
			entry.setAuditInfo("接入终端 " + item.getTerminalName() + " 删除成功");
			entry.setAuditModule("接入终端配置");
			entry.setLevel("INFO");
			entry.setUserName(account.getUserName());
			entry.setLogTime(DateUtils.getNow());
			userOperLogDao.create(entry);
		}
		sysTerminalInfoDao.deleteWithDependent(idsArr);
	}

	public Map getSysTerminalUpdateModel(Long id) {
		Map model = new HashMap();
		SysTerminalInfo entry = (SysTerminalInfo) sysTerminalInfoDao.getById(id);
		model.put("entry", entry);

		List cardType = codeTableDao.listAllCardType();
		model.put("cardType", cardType);
		List terminalBand = codeTableDao.listAllTermianlBand();
		model.put("terminalBand", terminalBand);
		List terminalOS = codeTableDao.listAllTermianlOS();
		model.put("terminalOS", terminalOS);
		List terminalOutlink = codeTableDao.listAllTermianlOutlink();
		model.put("terminalOutlink", terminalOutlink);
		List terminalType = codeTableDao.listAllTerminalType();
		model.put("terminalType", terminalType);
		List sysClass = sysClassDao.findAll();
		model.put("sysClass", sysClass);
		
		List districtList = districtDao.getAllParents();
		model.put("districtList", districtList);
		
		List districtListFirst = districtDao.findChildFirst();
		model.put("districtListFirst", districtListFirst);

		return model;
	}

    @Override
    public String updateTerminalAddress(Long id, String ip, int port) throws Exception{
        String msg = null;
        if(id!=null){
            TerminalAddress older = (TerminalAddress) terminalAddressDao.getById(id);
            older.setIp(ip);
            older.setPort(port);
            terminalAddressDao.update(older);
            msg = "加固修改成功,点击[确定]返回列表!";
        } else {
            TerminalAddress t = new TerminalAddress();
            t.setIp(ip);
            t.setPort(port);
            terminalAddressDao.create(t);
            msg = "加固新增成功,点击[确定]返回列表!";
        }
        return msg;
    }

    @Override
    public TerminalAddress getTerminalAddress() throws Exception{
        TerminalAddress terminalAddress = (TerminalAddress) terminalAddressDao.findAll().get(0);
        return terminalAddress;
    }

    @Override
    public String saveMany(List<SysTerminalInfo> list, Account account) throws Exception{
        String msg = null;
        try{
            sysTerminalInfoDao.saveMany(list);
            msg = "导入成功,点击[确定]返回列表!";
        } catch ( Exception e){
            e.printStackTrace();
            msg = "导入失败,"+e.getMessage();
        }
        UserOperLog entry = new UserOperLog();
        entry.setAuditInfo("批量导入,"+msg);
        entry.setAuditModule("终端管理");
        entry.setLevel("INFO");
        entry.setUserName(account.getUserName());
        entry.setLogTime(DateUtils.getNow());
        userOperLogDao.create(entry);
        return msg;
    }

    @Override
    public void truncateSysTerminal() throws Exception {
        sysTerminalInfoDao.truncate();
    }


    public Map getSysTerminalAddModel() {
		Map model = new HashMap();

		List cardType = codeTableDao.listAllCardType();
		model.put("cardType", cardType);
		List terminalBand = codeTableDao.listAllTermianlBand();
		model.put("terminalBand", terminalBand);
		List terminalOS = codeTableDao.listAllTermianlOS();
		model.put("terminalOS", terminalOS);
		List terminalOutlink = codeTableDao.listAllTermianlOutlink();
		model.put("terminalOutlink", terminalOutlink);
		List terminalType = codeTableDao.listAllTerminalType();
		model.put("terminalType", terminalType);
		List sysClass = sysClassDao.findAll();
		model.put("sysClass", sysClass);
		List districtList = districtDao.getAllParents();
		model.put("districtList", districtList);
		
		List districtListFirst = districtDao.findChildFirst();
		model.put("districtListFirst", districtListFirst);

		return model;
	}

	public Map getSysTerminalConfIndexModel(int pageIndex, int pageLength,
			String userName, String policeId, String userDepart,
			String userZone, String ifCancel) {
		Map model = new HashMap();
		PageResult ps = sysTerminalInfoDao.findPage(pageIndex, pageLength,
				userName, policeId, userDepart, userZone, ifCancel);
		model.put("ps", ps);
		return model;
	}

	public Map getSysTerminalDetailModel(Long id) {
		Map model = new HashMap();
		SysTerminalInfo entry = (SysTerminalInfo) sysTerminalInfoDao
				.getById(id);
		model.put("entry", entry);
		
		List cardType = codeTableDao.listAllCardType();
		model.put("cardType", cardType);
		List terminalBand = codeTableDao.listAllTermianlBand();
		model.put("terminalBand", terminalBand);
		List terminalOS = codeTableDao.listAllTermianlOS();
		model.put("terminalOS", terminalOS);
		List terminalOutlink = codeTableDao.listAllTermianlOutlink();
		model.put("terminalOutlink", terminalOutlink);
		List terminalType = codeTableDao.listAllTerminalType();
		model.put("terminalType", terminalType);
		List sysClass = sysClassDao.findAll();
		model.put("sysClass", sysClass);
		
		List districtList = districtDao.findAll();
		model.put("districtList", districtList);
		return model;
	}

	public void newSysTerminal(SysTerminalInfo entry) {
		entry.setFlag("1");// 新增

		sysTerminalInfoDao.create(entry);
	}

	public void updateSysTerminal(SysTerminalInfo entry) {
		
		SysTerminalInfo newEntry = (SysTerminalInfo) sysTerminalInfoDao.getById(entry.getId());
		
		newEntry.setId(entry.getId());
		newEntry.setTermianlBand(entry.getTermianlBand());
		newEntry.setTermianlOS(entry.getTermianlOS());
		newEntry.setTermianlOutlink(entry.getTermianlOutlink());
		newEntry.setTerminalName(entry.getTerminalName());
		newEntry.setTerminalType(entry.getTerminalType());		
			
		newEntry.setCardName(entry.getCardName());
		newEntry.setCardType(entry.getCardType());
		newEntry.setCardVersion(entry.getCardVersion());
		newEntry.setUserId(entry.getUserId());
		newEntry.setUserName(entry.getUserName());
		newEntry.setUserDepart(entry.getUserDepart());
		newEntry.setUserZone(entry.getUserZone());
		
		newEntry.setPoliceId(entry.getPoliceId());
		newEntry.setRegTime(entry.getRegTime());

		newEntry.setIfCancel(entry.getIfCancel());
		newEntry.setFlag("2");// 修改
		
		sysTerminalInfoDao.update(newEntry);
	}

}
