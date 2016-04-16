package com.hzjava.monitorcenter.web.action;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;

import cn.collin.commons.domain.PageResult;
import cn.collin.commons.utils.DateUtils;

import com.hzjava.monitorcenter.constant.AppConstant;
import com.hzjava.monitorcenter.constant.ServiceConstant;
import com.hzjava.monitorcenter.service.BusinessReportService;
import com.hzjava.monitorcenter.utils.JsonUtil;
import com.hzjava.monitorcenter.utils.StringUtils;

/**
 * 业务统计: 1.业务日统计 2.业务月统计 3.业务年统计
 * 
 * @author xiangqi.java@gmail.com
 * @struts.action path="/report" scope="request" parameter="m"
 */
public class BusinessReportAdminAction extends BaseAction {
	private static Logger logger = Logger
			.getLogger(BusinessReportAdminAction.class);

	/** 业务日统计-列表 * */
	public ActionForward bdDayReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BusinessReportService brs = (BusinessReportService) getWebApplicationContext()
				.getBean(ServiceConstant.BUSINESS_REPORT_SERVICE);

		int start = ServletRequestUtils.getIntParameter(request, "start", 0);// 从第几条记录开始而不是第几页
		int pageLength = ServletRequestUtils.getIntParameter(request, "limit",
				AppConstant.PAGERESULT_PAGE_LENGTH);
		int pageIndex = start / pageLength + 1;
		String endDateStr = ServletRequestUtils.getStringParameter(request,
				"endDate");

		Date endDate = StringUtils.isBlank(endDateStr) ? DateUtils.getNow()
				: DateUtils.parse(endDateStr, "yyyy-MM-dd");
		String businessName = ServletRequestUtils.getStringParameter(request,
				"businessName");

		PageResult ps = brs.listHourReportPage(pageIndex, pageLength, endDate,
				businessName);

		Map<Object, Object> model = new HashMap<Object, Object>();
		model.put("total", ps.getAllResultsAmount());

		model.put("data", ps.getResults());

		JsonUtil jsonUtil = new JsonUtil();
		jsonUtil.addState(Boolean.TRUE);
		jsonUtil.addResult(model);

		String html = jsonUtil.toString();
		logger.debug(html);
		sendHTML(response, html);

		return null;
	}

	/** 业务日统计-图表数据 * */
	public ActionForward bdDayReportCount(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BusinessReportService brs = (BusinessReportService) getWebApplicationContext()
				.getBean(ServiceConstant.BUSINESS_REPORT_SERVICE);

		String endDateStr = ServletRequestUtils.getStringParameter(request,
				"endDate");

		Date endDate = StringUtils.isBlank(endDateStr) ? DateUtils.getNow()
				: DateUtils.parse(endDateStr, "yyyy-MM-dd");
		String businessName = ServletRequestUtils.getStringParameter(request,
				"businessName");

		List countList = brs.listHourReportCount(endDate, businessName);

		Map<Object, Object> model = new HashMap<Object, Object>();
		model.put("countList", countList);

		JsonUtil jsonUtil = new JsonUtil();
		jsonUtil.addState(Boolean.TRUE);
		jsonUtil.addResult(model);

		String html = jsonUtil.toString();
		logger.debug(html);
		sendHTML(response, html);

		return null;
	}

	/** 业务月统计-列表数据 * */
	public ActionForward bdMonthReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BusinessReportService brs = (BusinessReportService) getWebApplicationContext()
				.getBean(ServiceConstant.BUSINESS_REPORT_SERVICE);

		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(System.currentTimeMillis());

		int year = ServletRequestUtils.getIntParameter(request, "year", now
				.get(Calendar.YEAR));
		int month = ServletRequestUtils.getIntParameter(request, "month", -1);

		String businessName = ServletRequestUtils.getStringParameter(request,
				"businessName");

		if (StringUtils.isBlank(businessName))
			return null;
		month = month < 0 ? now.get(Calendar.MONTH) + 1 : month;

		List data = brs.buildListDayReportCount(year, month, businessName);

		Map<Object, Object> model = new HashMap<Object, Object>();
		model.put("data", data);

		JsonUtil jsonUtil = new JsonUtil();
		jsonUtil.addState(Boolean.TRUE);
		jsonUtil.addResult(model);

		String html = jsonUtil.toString();
		logger.debug(html);
		sendHTML(response, html);

		return null;
	}

	/** 业务年统计-列表数据 * */
	public ActionForward bdYearReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BusinessReportService brs = (BusinessReportService) getWebApplicationContext()
				.getBean(ServiceConstant.BUSINESS_REPORT_SERVICE);

		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(System.currentTimeMillis());

		int year = ServletRequestUtils.getIntParameter(request, "year", now
				.get(Calendar.YEAR));

		String businessName = ServletRequestUtils.getStringParameter(request,
				"businessName");

		if (StringUtils.isBlank(businessName))
			return null;

		List data = brs.buildListMonthReportCount(year, businessName);

		Map<Object, Object> model = new HashMap<Object, Object>();
		model.put("data", data);

		JsonUtil jsonUtil = new JsonUtil();
		jsonUtil.addState(Boolean.TRUE);
		jsonUtil.addResult(model);

		String html = jsonUtil.toString();
		logger.debug(html);
		sendHTML(response, html);

		return null;
	}

}
