package com.hzjava.monitorcenter.web.action;

import cn.collin.commons.utils.DateUtils;
import com.hzjava.monitorcenter.constant.ServiceConstant;
import com.hzjava.monitorcenter.domain.DataBean;
import com.hzjava.monitorcenter.domain.Sysabnormal;
import com.hzjava.monitorcenter.service.EquipmentReportService;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.service.SysAbnormalService;
import com.hzjava.monitorcenter.utils.DateTimeUtil;
import com.hzjava.monitorcenter.utils.JsonUtil;
import com.hzjava.monitorcenter.utils.StringUtils;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author www
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/pages/sysinf/sysAbnormalInf" validate="false"
 *                parameter="m"
 * @struts.action-forward name="insert"
 * @struts.action-forward name="update"
 * @struts.action-forward name="delete"
 * @struts.action-forward name="read"
 *
 */
public class SysAbnormalAction extends  BaseAction {

    private static Logger logger = Logger.getLogger(SysAbnormalInfAction.class);
    private SysAbnormalService sysAbnormalService;
    private LogService logService;

    public LogService getLogService() {
        return logService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }


    public SysAbnormalService getSysAbnormalService() {
        return sysAbnormalService;
    }

    public void setSysAbnormalService(SysAbnormalService sysAbnormalService) {
        this.sysAbnormalService = sysAbnormalService;
    }

    public ActionForward select(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logger.info("违规处理查询");

//        int limit = ServletRequestUtils.getIntParameter(request, "limit");
//        int pageIndex = start / limit + 1;
        String json = sysAbnormalService.findAllBySysAbnormal();
        logger.info("json = "+json);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.close();
        logger.info("违规处理查询完成");
        return mapping.findForward("read");
    }
    //查看详细信息
    public ActionForward selectAll(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logger.info("违规处理查询");
        String idsystem = ServletRequestUtils.getStringParameter(request, "idsystem");
        String username = ServletRequestUtils.getStringParameter(request, "username");
        String json = sysAbnormalService.findAll(idsystem,username);
//        String json = sysAbnormalService.findAll("22010001","mmac");
        logger.info("json = "+json);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.print(json);
        writer.close();
        logger.info("违规处理查询完成");
        return mapping.findForward("read");
    }

    /** 违规月统计-图表数据 * */
    public ActionForward selectAbnormalMonth(ActionMapping mapping,
                                        ActionForm form, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        String year = ServletRequestUtils.getStringParameter(request, "year");
        String month = ServletRequestUtils.getStringParameter(request, "month");
        String datetime=null;
        if(year!=null&month!=null){
            if(month.length()==1){
                datetime=year+"-0"+month;
            }else {
                datetime=year+"-"+month;
            }

        }
        String json = sysAbnormalService.listReportCountMonth(datetime);
        sendHTML(response, json);
        return null;
    }
    /** 违规年统计-图表数据 * */
    public ActionForward selectAbnormalYear(ActionMapping mapping,
                                         ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {
        String year = ServletRequestUtils.getStringParameter(request, "year");
        String datetime=null;
        if(year!=null){

                datetime=year;


        }
        String json = sysAbnormalService.listReportCountYear(datetime);
        sendHTML(response, json);
        return null;
    }
    /** 违规日统计-图表数据 * */
    public ActionForward selectAbnormalDay(ActionMapping mapping,
                                         ActionForm form, HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {
        String reportDate = ServletRequestUtils.getStringParameter(request, "reportDate");
        if(reportDate!=null&&reportDate.length()>10){
            reportDate=reportDate.substring(0,10);
        }
        String json = sysAbnormalService.listReportCountDay(reportDate);
        sendHTML(response, json);
        return null;
    }
    /** 违规日统计-表格数据 * */
    public ActionForward selectDayForAbnormal(ActionMapping mapping,
                                              ActionForm form, HttpServletRequest request,
                                              HttpServletResponse response) throws Exception {
        String reportDate = ServletRequestUtils.getStringParameter(request, "reportDate");
        if(reportDate!=null&&reportDate.length()>10){
            reportDate=reportDate.substring(0,10);
        }
        String json = sysAbnormalService.listReportDay(reportDate);
        sendHTML(response, json);
        return null;
    }
    /** 违规月统计-表格数据 * */
    public ActionForward selectMonthForAbnormal(ActionMapping mapping,
                                              ActionForm form, HttpServletRequest request,
                                              HttpServletResponse response) throws Exception {
        String year = ServletRequestUtils.getStringParameter(request, "year");
        String month = ServletRequestUtils.getStringParameter(request, "month");
        String datetime=null;
        if(year!=null&month!=null){
            if(month.length()==1){
                datetime=year+"-0"+month;
            }else {
                datetime=year+"-"+month;
            }

        }
        String json = sysAbnormalService.listReportMonth(datetime);
        sendHTML(response, json);
        return null;
    }
    /** 违规年统计-表格数据 * */
    public ActionForward selectYearForAbnormal(ActionMapping mapping,
                                              ActionForm form, HttpServletRequest request,
                                              HttpServletResponse response) throws Exception {
        String year = ServletRequestUtils.getStringParameter(request, "year");
        String json = sysAbnormalService.listReportYear(year);
        sendHTML(response, json);
        return null;
    }
    public ActionForward selectLineChartMonth(ActionMapping mapping,
                                               ActionForm form, HttpServletRequest request,
                                               HttpServletResponse response) throws Exception {
        String year = ServletRequestUtils.getStringParameter(request, "year");
        String month = ServletRequestUtils.getStringParameter(request, "month");
        String datetime=null;
        if(year!=null&month!=null){
            if(month.length()==1){
                datetime=year+"-0"+month;
            }else {
                datetime=year+"-"+month;
            }
        }
        String json = sysAbnormalService.lineChartMonth(datetime);
//        sendHTML(response, json);
        if(datetime!=null){
            json=json.replace(datetime+"-"," ");
            System.out.println(json);
            sendHTML(response, json);
        }else {
            String m=null;
            DateTimeUtil dateTimeUtil=new DateTimeUtil();
            int months=dateTimeUtil.getMonth();
            int years=dateTimeUtil.getYear();
            if(String.valueOf(months).length()==1){
                m=String.valueOf(years)+"-0"+String.valueOf(months) ;
            } else {
                m=String.valueOf(years)+"-"+String.valueOf(months) ;
            }
            json=json.replace(m+"-"," ");
            System.out.println(json);
            sendHTML(response, json);
        }
        return null;
    }
    public ActionForward selectLineChartYear(ActionMapping mapping,
                                              ActionForm form, HttpServletRequest request,
                                              HttpServletResponse response) throws Exception {
        String reportDate = ServletRequestUtils.getStringParameter(request, "year");


        String json = sysAbnormalService.lineChartYear(reportDate);
        if(reportDate!=null){
            json=json.replace(reportDate+"-"," ");
            System.out.println(json);
            sendHTML(response, json);
        }else {
            DateTimeUtil dateTimeUtil=new DateTimeUtil();
            int year=dateTimeUtil.getYear();
            json=json.replace(String.valueOf(year)+"-"," ");
            System.out.println(json);
            sendHTML(response, json);
        }

        return null;
    }
   

}
