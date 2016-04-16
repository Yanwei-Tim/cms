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
 * 用户操作审计
 * 
 * @author xiangqi.java@gmail.com
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/terminalOperAudit" validate="false"
 *                parameter="m"
 * @struts.action-forward name="index"
 *                        path="/WEB-INF/pages/terminal/operAuditJSON.jsp"
 */
public class TerminalOperAuditAction extends DispatchActionSupport {
	private static Logger logger = Logger
			.getLogger(TerminalOperAuditAction.class);
	private TerminalService terminalService;

	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
		String cardId = ServletRequestUtils.getStringParameter(request,
				"cardId");
		String cardType = ServletRequestUtils.getStringParameter(request,
				"cardType");
		String policeId = ServletRequestUtils.getStringParameter(request,
				"policeId");
		String operateTpye = ServletRequestUtils.getStringParameter(request,
				"operateTpye");
		String operater = ServletRequestUtils.getStringParameter(request,
				"operater");

		Map model = terminalService.getOperAuditModel(pageIndex, pageLength,
				cardId, cardType, policeId, operateTpye, operater, startDate,
				endDate);

		request.setAttribute("model", model);

		return mapping.findForward("index");
	}

	public void setTerminalService(TerminalService terminalService) {
		this.terminalService = terminalService;
	}

}
