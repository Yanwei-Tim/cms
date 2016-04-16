package com.hzjava.monitorcenter.web.action;

import java.text.SimpleDateFormat;
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
import com.hzjava.monitorcenter.service.EquipmentReportService;
import com.hzjava.monitorcenter.utils.JsonUtil;
import com.hzjava.monitorcenter.utils.StringUtils;

/**
 * 设备统计: 1.设备日统计 2.设备月统计 3.设备年统计
 * 
 * @author xiangqi.java@gmail.com
 * @struts.action path="/eqreport" scope="request" parameter="m"
 */
public class EquipmentReportAdminAction extends BaseAction {
	private static Logger logger = Logger
			.getLogger(EquipmentReportAdminAction.class);

	/** 设备日统计-列表 * */
	public ActionForward eqDayReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EquipmentReportService ers = (EquipmentReportService) getWebApplicationContext().getBean(ServiceConstant.EQUIPMENT_REPORT_SERVICE);

		int start = ServletRequestUtils.getIntParameter(request, "start", 0);// 从第几条记录开始而不是第几页
		int pageLength = ServletRequestUtils.getIntParameter(request, "limit",
				AppConstant.PAGERESULT_PAGE_LENGTH);
		int pageIndex = start / pageLength + 1;
		String endDateStr = ServletRequestUtils.getStringParameter(request,
				"endDate");

		Date endDate = StringUtils.isBlank(endDateStr) ? DateUtils.getNow() : DateUtils
				.parse(endDateStr, "yyyy-MM-dd");
		String equName = ServletRequestUtils.getStringParameter(request,
				"equName");

		PageResult ps = ers.listHourReportPage(pageIndex, pageLength,
				endDate, equName);

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

	/** 设备日统计-图表数据 * */
	public ActionForward eqDayReportCount(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		EquipmentReportService ers = (EquipmentReportService) getWebApplicationContext()
				.getBean(ServiceConstant.EQUIPMENT_REPORT_SERVICE);

		String endDateStr = ServletRequestUtils.getStringParameter(request,
				"endDate");

		Date endDate = StringUtils.isBlank(endDateStr) ?  DateUtils.getNow()  : DateUtils
				.parse(endDateStr, "yyyy-MM-dd");
		String equName = ServletRequestUtils.getStringParameter(request,
				"equName");
        List  countList = ers.listHourReportCount(endDate, equName);
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

	/** 设备月统计-列表数据 * */
	public ActionForward eqMonthReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EquipmentReportService ers = (EquipmentReportService) getWebApplicationContext()
				.getBean(ServiceConstant.EQUIPMENT_REPORT_SERVICE);

		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(System.currentTimeMillis());

		int year = ServletRequestUtils.getIntParameter(request, "year", now
				.get(Calendar.YEAR));

        int month = ServletRequestUtils.getIntParameter(request, "month", now.get(Calendar.MONTH)+ 1);

		String equName = ServletRequestUtils.getStringParameter(request,
				"equName");
//        if(year==0&&month>0)
        if(equName==null||equName.equals("All")||equName.equals("")){
            equName = "";
        }

		month = month < 0 ? now.get(Calendar.MONTH) + 1 : month;

//		List data = ers.buildListDayReportCount(year, month, equName);

        PageResult ps = ers.PageResultDayReportCount(year, month,equName);

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
    /** 设备月统计-图表数据 * */
    public ActionForward eqMonthReportCount(ActionMapping mapping,
                                          ActionForm form, HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        EquipmentReportService ers = (EquipmentReportService) getWebApplicationContext()
                .getBean(ServiceConstant.EQUIPMENT_REPORT_SERVICE);

        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());
        int year = ServletRequestUtils.getIntParameter(request, "year", now.get(Calendar.YEAR));

        int month = ServletRequestUtils.getIntParameter(request, "month", now.get(Calendar.MONTH)+ 1);
        String equName = ServletRequestUtils.getStringParameter(request,
                "equName");
        List countList = ers.buildListDayReportCount(year, month, equName);
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
	/** 设备年统计-列表数据 * */
	public ActionForward eqYearReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EquipmentReportService ers = (EquipmentReportService) getWebApplicationContext()
				.getBean(ServiceConstant.EQUIPMENT_REPORT_SERVICE);

		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(System.currentTimeMillis());

		int year = ServletRequestUtils.getIntParameter(request, "year", now
				.get(Calendar.YEAR));

		String equName = ServletRequestUtils.getStringParameter(request,"equName");
        if(equName==null||equName.equals("All")||equName.equals("")){
            equName = "";
        }
//		List data = ers.buildListMonthReportCount(year, equName);
        PageResult ps = ers.PageResultMonthReportCount(year,equName);

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
    /** 设备月统计-图表数据 * */
    public ActionForward eqYearReportCount(ActionMapping mapping,
                                            ActionForm form, HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {
        EquipmentReportService ers = (EquipmentReportService) getWebApplicationContext()
                .getBean(ServiceConstant.EQUIPMENT_REPORT_SERVICE);

        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis());
        int year = ServletRequestUtils.getIntParameter(request, "year", now.get(Calendar.YEAR));
        String equName = ServletRequestUtils.getStringParameter(request,
                "equName");
        if(equName==null||equName.equals("All")||equName.equals("")){
            equName = "";
        }
        List countList = ers.buildListMonthReportCount(year, equName);
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
}
