package com.hzjava.monitorcenter.web.action.terminal;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import cn.collin.commons.utils.DateUtils;

import com.hzjava.monitorcenter.constant.AppConstant;
import com.hzjava.monitorcenter.service.TerminalService;
import com.hzjava.monitorcenter.utils.StringUtils;

/**
 * 用户访问统计
 * 
 * @author qxp
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/terminalAccessAuditReport" validate="false"
 *                parameter="m"
 * @struts.action-forward name="index"
 *                        path="/WEB-INF/pages/terminal/accessAuditReportJSON.jsp"
 */
public class TerminalAccessrAuditReportAction extends DispatchActionSupport {
	private static Logger logger = Logger
			.getLogger(TerminalAccessrAuditReportAction.class);
	private TerminalService terminalService;

	@SuppressWarnings("rawtypes")
	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int pageLength = ServletRequestUtils.getIntParameter(request, "limit",
				AppConstant.PAGERESULT_PAGE_LENGTH);
		int pageIndex = start / pageLength + 1;
		String userDepart = ServletRequestUtils.getStringParameter(request,"userDepart");
		if(userDepart !=null){
			String reportDateStr = ServletRequestUtils.getStringParameter(request,"reportDate");
//			String userDepart = ServletRequestUtils.getRequiredStringParameter(request, "userDepart");
			Date reportDate = StringUtils.isBlank(reportDateStr) ? null : DateUtils
					.parse(reportDateStr, "yyyy-MM-dd");
			
			Map model = terminalService.getAccessAuditReportModel(pageIndex, pageLength, userDepart, reportDate);
			request.setAttribute("model", model);
		}

		return mapping.findForward("index");
	}
	
	public ActionForward count(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int pageLength = ServletRequestUtils.getIntParameter(request, "limit",
				AppConstant.PAGERESULT_PAGE_LENGTH);
		int pageIndex = start / pageLength + 1;
		String userDepart = ServletRequestUtils.getStringParameter(request,"userDepart");
		if(userDepart !=null){
			String reportDateStr = ServletRequestUtils.getStringParameter(request,"reportDate");
//			String userDepart = ServletRequestUtils.getRequiredStringParameter(request, "userDepart");
			Date reportDate = StringUtils.isBlank(reportDateStr) ? null : DateUtils
					.parse(reportDateStr, "yyyy-MM-dd");
			
			Map model = terminalService.getAccessAuditReportModel(pageIndex, pageLength, userDepart, reportDate);
			request.setAttribute("model", model);
		}

		return mapping.findForward("index");
	}

	public void setTerminalService(TerminalService terminalService) {
		this.terminalService = terminalService;
	}

}
