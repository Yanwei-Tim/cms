package com.hzjava.monitorcenter.service;

import cn.collin.commons.domain.PageResult;
import cn.collin.commons.utils.DateUtils;
import com.hzjava.monitorcenter.dao.BusinessLogDao;
import com.hzjava.monitorcenter.dao.EquipmentLogDao;
import com.hzjava.monitorcenter.dao.SysLogDao;
import com.hzjava.monitorcenter.dao.UserOperLogDao;
import com.hzjava.monitorcenter.domain.EquipmentLog;
import com.hzjava.monitorcenter.domain.UserOperLog;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 审计管理: 1.系统日志审计 2.用户日志审计 3.业务日志审计 4.设备日志审计
 * 
 * @author xiangqi
 * 
 */
public class LogServiceImpl implements LogService {
	private final static Logger logger = Logger.getLogger(LogServiceImpl.class);
	private SysLogDao sysLogDao;
	private UserOperLogDao userOperLogDao;
	private BusinessLogDao businessLogDao;
	private EquipmentLogDao equipmentLogDao;

	public void setSysLogDao(SysLogDao sysLogDao) {
		this.sysLogDao = sysLogDao;
	}

	public void setUserOperLogDao(UserOperLogDao userOperLogDao) {
		this.userOperLogDao = userOperLogDao;
	}

	public void setBusinessLogDao(BusinessLogDao businessLogDao) {
		this.businessLogDao = businessLogDao;
	}

	public void setEquipmentLogDao(EquipmentLogDao equipmentLogDao) {
		this.equipmentLogDao = equipmentLogDao;
	}

	/**
	 * 分页查询-系统日志审计
	 * 
	 * @param pageIndex
	 * @param pageLength
	 * @param startDate
	 * @param endDate
	 * @param logLevel
	 * @return
	 */
	public PageResult listSysLogByPage(int pageIndex, int pageLength,
			Date startDate, Date endDate, String logLevel) {
		logger.debug("pageIndex:" + pageIndex + "pageLength:" + pageLength
				+ "startDate:" + startDate + " endDate:" + endDate);
		PageResult ps = this.sysLogDao.listLogsByParams(pageIndex, pageLength,
				startDate, endDate, logLevel);
		return ps;
	}

	/**
	 * 分页查询-用户日志审计
	 * 
	 * @param pageIndex
	 * @param pageLength
	 * @param startDate
	 * @param endDate
	 * @param logLevel
	 * @param userName
	 * @return
	 */
	public PageResult listUserOperLogByPage(int pageIndex, int pageLength,
			Date startDate, Date endDate, String logLevel, String userName) {
		logger.debug("startDate:" + startDate + " endDate:" + endDate);
		PageResult ps = this.userOperLogDao.listLogsByParams(pageIndex,
				pageLength, startDate, endDate, logLevel, userName);
		return ps;
	}

	/**
	 * 分页查询-业务日志审计
	 * 
	 * @return
	 */
	public PageResult listBusinessLogByPage(int pageIndex, int pageLength,
			Date startDate, Date endDate, String logLevel, String businessName) {
		logger.debug("startDate:" + startDate + " endDate:" + endDate);
		PageResult ps = this.businessLogDao.listLogsByParams(pageIndex,
				pageLength, startDate, endDate, logLevel, businessName);
		return ps;
	}

	/**
	 * 分页查询-设备日志审计
	 * 
	 * @param pageIndex
	 * @param pageLength
	 * @param startDate
	 * @param endDate
	 * @param logLevel
	 * @param equipmentName
	 * @return
	 */
	public PageResult listEquipmentLogByPage(int pageIndex, int pageLength,
			Date startDate, Date endDate, String logLevel, String equipmentName) {
		logger.debug("startDate:" + startDate + " endDate:" + endDate);
		PageResult ps = this.equipmentLogDao.listLogsByParams(pageIndex,
				pageLength, startDate, endDate, logLevel, equipmentName);
		return ps;
	}

	public void newLog(String level, String userName, String auditModule,
			String auditInfo) {
		UserOperLog entry = new UserOperLog();
		entry.setAuditInfo(auditInfo);
		entry.setAuditModule(auditModule);
		entry.setLevel(level);
		entry.setUserName(userName);
		entry.setLogTime(DateUtils.getNow());
		userOperLogDao.create(entry);
	}

    @Override
    public void deleteBusinessLog() throws Exception {
         businessLogDao.truncate();
    }

    @Override
    public void deleteEquipmentLog() throws Exception {
        equipmentLogDao.truncate();
    }


    public String getAll(int start, int limit) {
        String json = "{success:true,total:";
        List<EquipmentLog> lists = equipmentLogDao.findAll();
        String pat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pat);
        int total = lists.size();
        json += total + ",rows:[";
        int index = 0;
        int count = 0;
        for (EquipmentLog t : lists) {
            if(index == start && count < limit){
                json +="{id:'"+t.getId()+"',Equipment_name:'"+t.getEquipmentName()+
                        "',Level:'"+t.getLevel()+
                        "',Link_amount:'"+t.getLinkAmount()+
                        "',Link_bandwidth:'"+t.getLinkBandwidth()+
                        "',Link_Corp:'"+t.getLinkCorp()+
                        "',Log_info:'"+t.getLogInfo()+
                        "',Link_name:'"+t.getLinkName()+
                        "',Link_property:'"+t.getLinkProperty()+
                        "',Link_security:'"+t.getLinkSecurity();
                        Date d = t.getLogTime();
                        if(d!=null){
                            String day = sdf.format(d);
                            json+= "',Log_time:'"+day+"'},";
                        }else{
                            json+= "',Log_time:'"+t.getLogTime()+"'},";
                        }
                count ++;
                start ++;
            }
            index ++;
        }
        json += "]}";
        json=json.replace("},]}","}]}");
        json=json.replace("\n"," ");
        System.out.println("json=="+json);
        return json;
    }
    /**
     * 分页读取用户日志--并以json形式返回
     */
    public String selectLogByTime(int pageIndex, int limit, Date startDate, Date endDate,
                                  String logLevel, String deviceName) throws Exception {
        PageResult pageResult = equipmentLogDao.listLogsByParams(pageIndex,limit,startDate, endDate, logLevel, deviceName);
        List<EquipmentLog> equipmentLogs = pageResult.getResults();
        int total = pageResult.getAllResultsAmount();
        String pat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(pat);
        String json = "{success:true,total:"+ total + ",rows:[";
        for (EquipmentLog t : equipmentLogs) {
            
            json +="{id:'"+t.getId()+"',Equipment_name:'"+t.getEquipmentName()+
                    "',Level:'"+t.getLevel()+
                    "',Link_amount:'"+t.getLinkAmount()+
                    "',Link_bandwidth:'"+t.getLinkBandwidth()+
                    "',Link_Corp:'"+t.getLinkCorp()+
                    "',Log_info:'"+t.getLogInfo()+
                    "',Link_name:'"+t.getLinkName()+
                    "',Link_property:'"+t.getLinkProperty()+
                    "',Link_security:'"+t.getLinkSecurity();
            Date d = t.getLogTime();
            if(d!=null){
                String day = sdf.format(d);
                json+= "',Log_time:'"+day+"'},";
            }else{
                json+= "',Log_time:'"+t.getLogTime()+"'},";
            }
        }
        json += "]}";
        json=json.replace("},]}","}]}");
        json=json.replace("\n"," ");
        System.out.println("json=="+json);
        return json;
    }
}
