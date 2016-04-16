package com.hzjava.monitorcenter.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hzjava.monitorcenter.dao.EquipmentLogDao;
import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.EquipmentLog;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import cn.collin.commons.domain.PageResult;

import com.hzjava.monitorcenter.dao.AlertDao;
import com.hzjava.monitorcenter.dao.BusinessExceptCodeDao;
import com.hzjava.monitorcenter.dao.SecurityEventCodeDao;
import com.hzjava.monitorcenter.utils.ServiceResponse;
import com.hzjava.monitorcenter.utils.ServiceUtil;

/**
 * 报警管理: 1.业务异常报警 2.安全事件报警 3.设备故障报警
 * 
 * @author xiangqi.java@gmail.com
 * 
 */
public class AlertServiceImpl implements AlertService {
	private final static Logger logger = Logger
			.getLogger(AlertServiceImpl.class);

	private AlertDao alertDao;
	private BusinessExceptCodeDao businessExceptCodeDao;
	private SecurityEventCodeDao securityEventCodeDao;
    private EquipmentLogDao equipmentLogDao;

	public void setAlertDao(AlertDao alertDao) {
		this.alertDao = alertDao;
	}

	public void setBusinessExceptCodeDao(
			BusinessExceptCodeDao businessExceptCodeDao) {
		this.businessExceptCodeDao = businessExceptCodeDao;
	}

	public void setSecurityEventCodeDao(
			SecurityEventCodeDao securityEventCodeDao) {
		this.securityEventCodeDao = securityEventCodeDao;
	}

    public void setEquipmentLogDao(EquipmentLogDao equipmentLogDao) {
        this.equipmentLogDao = equipmentLogDao;
    }

    /**
	 * 分页查询-业务异常报警
	 * 
	 */
	public PageResult listBsExpAlert(int pageIndex, int pageLength,
			Date startDate, Date endDate, String businessName, String alertCode) {
		logger.debug("pageIndex:" + pageIndex + " pageLength:" + pageLength
				+ " startDate:" + startDate + " endDate:" + endDate
				+ " businessName:" + businessName);
		PageResult ps = null;
		ps = alertDao.listBsExpAlert(pageIndex, pageLength, startDate, endDate,
				businessName, alertCode);
		/*
		 * date:2012-03-20
		 * author:crj
		 * function:把业务异常报警的编码转换成业务异常报警的名称，对应表business_except_code
		 */
		for (int i = 0; i < ps.getResults().size(); i++) {
			Map map=(Map)(ps.getResults().get(i));
			String name = changeBsAlertType((String) map.get("alertType"));
			map.remove("alertType");
			map.put("alertType", name);
		}
		return ps;
	}

	/**
	 * 分页查询-安全事件报警
	 * 
	 */
	public PageResult listScEventAlert(int pageIndex, int pageLength,
			Date startDate, Date endDate, String equName, String alertCode) {
		logger.debug("pageIndex:" + pageIndex + " pageLength:" + pageLength
				+ " startDate:" + startDate + " endDate:" + endDate
				+ " equName:" + equName);
		PageResult ps = null;
		ps = alertDao.listScEventAlert(pageIndex, pageLength, startDate,
				endDate, equName, alertCode);
		/*
		 * date:2012-03-17
		 * author:crj
		 * function:把报警的编码转换成报警的名称
		 */
		for (int i = 0; i < ps.getResults().size(); i++) {
			Map map=(Map)(ps.getResults().get(i));
			String name = changeAlertType((String) map.get("alertType"));
			map.remove("alertType");
			map.put("alertType", name);
		}
		return ps;
	}
	/*
	 * date:2012-03-17
	 * author:crj
	 * function:根据传进来的报警编码得到报警的名称
	 */
	public String changeAlertType(String code) {
		return securityEventCodeDao.findAlertTypeByCode(code);
	}
	/*
	 * date:2012-03-20
	 * author:crj
	 * function:根据传进来的业务异常报警的编码得到业务异常报警的名称
	 */
	public String changeBsAlertType(String code) {
		return businessExceptCodeDao.findAlertTypeByCode(code);
	}
	/**
	 * 分页查询-设备故障报警
	 * 
	 */
	public PageResult listEquAlert(int pageIndex, int pageLength,
			Date startDate, Date endDate, String equName) {
		logger.debug("pageIndex:" + pageIndex + " pageLength:" + pageLength
				+ " startDate:" + startDate + " endDate:" + endDate
				+ " equName:" + equName);
		PageResult ps = null;
		ps = alertDao.listEquAlert(pageIndex, pageLength, startDate, endDate,
				equName);
		return ps;
	}

	/**
	 * 返回所有业务异常code
	 * 
	 * @return
	 */
	public List listBsAlertType() {
		List list = businessExceptCodeDao.listBsAlertType();
		return list;
	}

	/**
	 * 返回所有安全事件异常code
	 * 
	 * @return
	 */
	public List listScAlertType() {
		List list = securityEventCodeDao.listScAlertType();
		return list;
	}

	public JSONObject getAlerts() {
		try {
			String[][] params = new String[][] {
					{ "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
					{ "Command", "haveAlert" } };
			ServiceResponse response = ServiceUtil.callService(params);
			if (response != null && response.getCode() == 200
					&& response.getData() != null) {
				JSONObject obj = JSONObject.fromObject(response.getData());
				return obj;
			}
		} catch (Exception e) {
			logger.error("获取报警信息出错", e);
		}

		return null;
	}

	public void updateReadStatus(String ids, String domainName) {
		String hql = "update " + domainName + " set isRead='Y' where id in("
				+ ids + ")";
		alertDao.execute(hql);
	}

    @Override
    public void newLog(Account account, String model, String diskMsg, Date date) throws Exception {
        boolean isExist = equipmentLogDao.findByDate(date,diskMsg);//当天已经审计true
        if(!isExist){
            EquipmentLog equipmentLog = new EquipmentLog();
            equipmentLog.setLevel("WARN");
            equipmentLog.setLogInfo(diskMsg);
            equipmentLog.setEquipmentName("cms");
            equipmentLog.setLogTime(date);
            equipmentLog.setLinkName("B/SAccess");
            equipmentLogDao.create(equipmentLog);
        }
    }
}
