package com.hzjava.monitorcenter.service;

import cn.collin.commons.domain.PageResult;

import java.util.Date;

public interface LogService {
	/**
	 * 分页查询SysLog
	 * 
	 * @param pageIndex
	 * @param pageLength
	 * @param startDate
	 * @param endDate
	 * @param logLevel
	 * @return
	 */
	public PageResult listSysLogByPage(int pageIndex, int pageLength,
                                       Date startDate, Date endDate, String logLevel);

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
                                            Date startDate, Date endDate, String logLevel, String userName);

	/**
	 * 分页查询-业务日志审计
	 * 
	 */
	public PageResult listBusinessLogByPage(int pageIndex, int pageLength,
                                            Date startDate, Date endDate, String logLevel, String businessName);

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
                                             Date startDate, Date endDate, String logLevel, String equipmentName);

	public void newLog(String level, String userName, String auditModule,
                       String auditInfo);

    void deleteBusinessLog() throws Exception;

    void deleteEquipmentLog() throws Exception;

    public String getAll(int start, int limit);

    public String selectLogByTime(int pageIndex, int limit, Date startDate, Date endDate,
                                  String logLevel, String deviceName) throws Exception;

}
