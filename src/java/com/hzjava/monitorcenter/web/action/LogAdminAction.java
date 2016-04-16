package com.hzjava.monitorcenter.web.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.Role;
import com.hzjava.monitorcenter.web.SessionUtils;
import com.hzjava.monitorcenter.web.WebUtil;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;

import cn.collin.commons.domain.PageResult;
import cn.collin.commons.utils.DateUtils;

import com.hzjava.monitorcenter.constant.AppConstant;
import com.hzjava.monitorcenter.constant.ServiceConstant;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.utils.StringUtils;

/**
 * 审计管理: 1.系统日志审计 2.用户日志审计 3.业务日志审计 4.设备日志审计
 * 
 * @author xiangqi.java@gmail.com
 * @struts.action path="/log" scope="request" parameter="m"
 * @struts.action-forward name="sysLogJSON"
 *                        path="/WEB-INF/pages/logs/sysLogJSON.jsp"
 * @struts.action-forward name="equLogJSON"
 *                        path="/WEB-INF/pages/logs/equLogJSON.jsp"
 * @struts.action-forward name="bizLogJSON"
 *                        path="/WEB-INF/pages/logs/bizLogJSON.jsp"
 * @struts.action-forward name="userLogJSON"
 *                        path="/WEB-INF/pages/logs/userLogJSON.jsp"
 */
public class LogAdminAction extends BaseAction {
	private static Logger logger = Logger.getLogger(LogAdminAction.class);

	/** 系统日志审计 * */
	public ActionForward sysLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LogService logService = (LogService) getWebApplicationContext()
				.getBean(ServiceConstant.LOG_SERVICE);

		int start = ServletRequestUtils.getIntParameter(request, "start", 0);// 从第几条记录开始而不是第几页
		int pageLength = ServletRequestUtils.getIntParameter(request, "limit",
				AppConstant.PAGERESULT_PAGE_LENGTH);
		int pageIndex = start / pageLength + 1;
		String startDateStr = ServletRequestUtils.getStringParameter(request,
				"startDate");
		String endDateStr = ServletRequestUtils.getStringParameter(request,
				"endDate");

		Date startDate = StringUtils.isBlank(startDateStr) ? null : DateUtils
				.parse(startDateStr, "yyyy-MM-dd");
		Date endDate = StringUtils.isBlank(endDateStr) ? null : DateUtils
				.parse(endDateStr, "yyyy-MM-dd");
		String logLevel = ServletRequestUtils.getStringParameter(request,
				"logLevel");

		PageResult ps = logService.listSysLogByPage(pageIndex, pageLength,
				startDate, endDate, logLevel);

		request.setAttribute("ps", ps);

		return mapping.findForward("sysLogJSON");
	}

	/** 用户日志审计 * */
	public ActionForward userOperLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LogService logService = (LogService) getWebApplicationContext()
				.getBean(ServiceConstant.LOG_SERVICE);

		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int pageLength = ServletRequestUtils.getIntParameter(request, "limit",
				AppConstant.PAGERESULT_PAGE_LENGTH);
		int pageIndex = start / pageLength + 1;
		String startDateStr = ServletRequestUtils.getStringParameter(request,
				"startDate");
		String endDateStr = ServletRequestUtils.getStringParameter(request,
				"endDate");

		Date startDate = StringUtils.isBlank(startDateStr) ? null : DateUtils
				.parse(startDateStr, "yyyy-MM-dd");
		Date endDate = StringUtils.isBlank(endDateStr) ? null : DateUtils
				.parse(endDateStr, "yyyy-MM-dd");
		String logLevel = ServletRequestUtils.getStringParameter(request,
				"logLevel");
		String userName = ServletRequestUtils.getStringParameter(request,
				"userName");

		PageResult ps = logService.listUserOperLogByPage(pageIndex, pageLength,
				startDate, endDate, logLevel, userName);

		request.setAttribute("ps", ps);

		return mapping.findForward("userLogJSON");
	}

	/** 业务日志审计 * */
	public ActionForward businessLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LogService logService = (LogService) getWebApplicationContext()
				.getBean(ServiceConstant.LOG_SERVICE);

		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int pageLength = ServletRequestUtils.getIntParameter(request, "limit",
				AppConstant.PAGERESULT_PAGE_LENGTH);
		int pageIndex = start / pageLength + 1;
		String startDateStr = ServletRequestUtils.getStringParameter(request,
				"startDate");
		String endDateStr = ServletRequestUtils.getStringParameter(request,
				"endDate");

		Date startDate = StringUtils.isBlank(startDateStr) ? null : DateUtils
				.parse(startDateStr, "yyyy-MM-dd");
		Date endDate = StringUtils.isBlank(endDateStr) ? null : DateUtils
				.parse(endDateStr, "yyyy-MM-dd");
		String logLevel = ServletRequestUtils.getStringParameter(request,
				"logLevel");
		String businessName = ServletRequestUtils.getStringParameter(request,
				"businessName");

		PageResult ps = logService.listBusinessLogByPage(pageIndex, pageLength,
				startDate, endDate, logLevel, businessName);

		request.setAttribute("ps", ps);

		return mapping.findForward("bizLogJSON");
	}

	/** 设备日志审计 * */
	public ActionForward equipmentLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LogService logService = (LogService) getWebApplicationContext()
				.getBean(ServiceConstant.LOG_SERVICE);

		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int pageLength = ServletRequestUtils.getIntParameter(request, "limit",
				AppConstant.PAGERESULT_PAGE_LENGTH);
		int pageIndex = start / pageLength + 1;
		String startDateStr = ServletRequestUtils.getStringParameter(request,
				"startDate");
		String endDateStr = ServletRequestUtils.getStringParameter(request,
				"endDate");

		Date startDate = StringUtils.isBlank(startDateStr) ? null : DateUtils
				.parse(startDateStr, "yyyy-MM-dd");
		Date endDate = StringUtils.isBlank(endDateStr) ? null : DateUtils
				.parse(endDateStr, "yyyy-MM-dd");
		String logLevel = ServletRequestUtils.getStringParameter(request,
				"logLevel");
		String equipmentName = ServletRequestUtils.getStringParameter(request,
				"equipmentName");

		PageResult ps = logService.listEquipmentLogByPage(pageIndex,
				pageLength, startDate, endDate, logLevel, equipmentName);

		request.setAttribute("ps", ps);

		return mapping.findForward("equLogJSON");
	}

     /**
     *  清空业务日志
     */
    public ActionForward deleteBusinessLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        LogService logService = (LogService) getWebApplicationContext()
				.getBean(ServiceConstant.LOG_SERVICE);
		String msg = null;
        try{
            Account account = SessionUtils.getAccount(request);
            logService.deleteBusinessLog();
            msg = "清空成功";
            logService.newLog("INFO",account.getUserName(),"业务日志审计","清空业务日志成功");
        }catch (Exception e) {
            logger.info("业务日志审计"+e.getMessage());
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(),
                    "业务日志审计", "清空业务日志失败");
            msg = "清空失败"+e.getMessage();
        }

        WebUtil.sendHtml(request, response, "{success:true,msg:'" + msg + "'}");
		return null;
	}

    /**
     *  清空设备日志
     */
    public ActionForward deleteEquipmentLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        LogService logService = (LogService) getWebApplicationContext()
				.getBean(ServiceConstant.LOG_SERVICE);
		String msg = null;
        try{
            Account account = SessionUtils.getAccount(request);
            logService.deleteEquipmentLog();
            msg = "清空成功";
            logService.newLog("INFO",account.getUserName(),"设备日志审计","清空设备日志成功");
        }catch (Exception e) {
            logger.info("设备日志审计"+e.getMessage());
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(),
                    "设备日志审计", "清空设备日志失败");
            msg = "清空失败"+e.getMessage();
        }
        WebUtil.sendHtml(request, response, "{success:true,msg:'" + msg + "'}");
		return null;
	}
}
