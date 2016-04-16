package com.hzjava.monitorcenter.service;

import java.util.Date;
import java.util.List;

import com.hzjava.monitorcenter.domain.Account;
import net.sf.json.JSONObject;
import cn.collin.commons.domain.PageResult;

public interface AlertService {
	/**
	 * 分页查询-业务异常报警
	 * 
	 */
	public PageResult listBsExpAlert(int pageIndex, int pageLength,
                                     Date startDate, Date endDate, String businessName, String alertCode);

	/**
	 * 分页查询-安全事件报警
	 * 
	 */
	public PageResult listScEventAlert(int pageIndex, int pageLength,
                                       Date startDate, Date endDate, String equName, String alertCode);

	/**
	 * 分页查询-设备故障报警
	 * 
	 */
	public PageResult listEquAlert(int pageIndex, int pageLength,
                                   Date startDate, Date endDate, String equName);

	/**
	 * 返回所有业务异常code
	 * 
	 * @return
	 */
	public List listBsAlertType();

	/**
	 * 返回所有安全事件异常code
	 * 
	 * @return
	 */
	public List listScAlertType();

	/**
	 * 获取接口中报警信息数量
	 */
	public JSONObject getAlerts();

	/**
	 * 更新报警信息状态
	 */
	public void updateReadStatus(String ids, String domainName);

    public void newLog(Account account, String model, String diskMsg, Date date) throws Exception;
}
