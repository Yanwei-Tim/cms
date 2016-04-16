package com.hzjava.monitorcenter.web.action;

import cn.collin.commons.utils.DateUtils;
import com.hzjava.monitorcenter.service.EquipmentStatusService;
import com.hzjava.monitorcenter.utils.StringUtils;
import com.hzjava.monitorcenter.web.WebUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.DispatchActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-24
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
public class EquipmentStatusAction extends DispatchActionSupport {

    private EquipmentStatusService equipmentStatusService;

    public EquipmentStatusService getEquipmentStatusService() {
        return equipmentStatusService;
    }

    public void setEquipmentStatusService(EquipmentStatusService equipmentStatusService) {
        this.equipmentStatusService = equipmentStatusService;
    }
     public String selectByTime_1(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        int start = Integer.valueOf(request.getParameter("start"));
        int limit = Integer.valueOf(request.getParameter("limit"));
        String time1=request.getParameter("startDate");
        String time2=request.getParameter("endDate");
        String deviceName=request.getParameter("deviceName");
        Date startDate = StringUtils.isBlank(time1) ? null : DateUtils
            				.parse(time1, "yyyy-MM-dd hh:mm:ss");
        Date endDate = StringUtils.isBlank(time2) ? null : DateUtils
            				.parse(time2, "yyyy-MM-dd hh:mm:ss");
        System.out.println("startDate:"+startDate);
        System.out.println("endDate:"+endDate);
         try{
             String json=equipmentStatusService.selectStatusByTime_1(start/limit+1, limit,startDate,endDate,"",deviceName);
             WebUtil.sendHtml(request, response, json);
         }catch (Exception e)  {
             log.info(e);
         }


         return null;
    }
    public String selectByTime_2(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception, ParseException {
        int start = Integer.valueOf(request.getParameter("start"));
        int limit = Integer.valueOf(request.getParameter("limit"));
        String time1=request.getParameter("startDate");
        String time2=request.getParameter("endDate");
        String deviceName=request.getParameter("deviceName");
        Date startDate = StringUtils.isBlank(time1) ? null : DateUtils
            				.parse(time1, "yyyy-MM-dd hh:mm:ss");
        Date endDate = StringUtils.isBlank(time2) ? null : DateUtils
            				.parse(time2, "yyyy-MM-dd hh:mm:ss");
        System.out.println("startDate:"+startDate);
        System.out.println("endDate:"+endDate);
        String json=equipmentStatusService.selectStatusByTime_2(start/limit+1, limit,startDate,endDate,"",deviceName);
        WebUtil.sendHtml(request, response, json);
        return null;
    }
    public String selectByTime_3(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {
        int start = Integer.valueOf(request.getParameter("start"));
        int limit = Integer.valueOf(request.getParameter("limit"));
        String time1=request.getParameter("startDate");
        String time2=request.getParameter("endDate");
        String deviceName=request.getParameter("deviceName");
        Date startDate = StringUtils.isBlank(time1) ? null : DateUtils
            				.parse(time1, "yyyy-MM-dd hh:mm:ss");
        Date endDate = StringUtils.isBlank(time2) ? null : DateUtils
            				.parse(time2, "yyyy-MM-dd hh:mm:ss");
        System.out.println("startDate:"+startDate);
        System.out.println("endDate:"+endDate);
        String json=equipmentStatusService.selectStatusByTime_3(start/limit+1, limit,startDate,endDate,"",deviceName);
        WebUtil.sendHtml(request, response, json);
        return null;
    }
     public String selectByTime_4(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
         int start = Integer.valueOf(request.getParameter("start"));
         int limit = Integer.valueOf(request.getParameter("limit"));

        String time1=request.getParameter("startDate");
        String time2=request.getParameter("endDate");
        String deviceName=request.getParameter("deviceName");
        Date startDate = StringUtils.isBlank(time1) ? null : DateUtils
            				.parse(time1, "yyyy-MM-dd hh:mm:ss");
        Date endDate = StringUtils.isBlank(time2) ? null : DateUtils
            				.parse(time2, "yyyy-MM-dd hh:mm:ss");
        System.out.println("startDate:"+startDate);
        System.out.println("endDate:"+endDate);
        String json=equipmentStatusService.selectStatusByTime_4(start/limit+1, limit,startDate,endDate,"",deviceName);
        WebUtil.sendHtml(request, response, json);
        return null;
    }

    public String selectByTime(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception {
        int start = Integer.valueOf(request.getParameter("start"));
        int limit = Integer.valueOf(request.getParameter("limit"));

        String time1=request.getParameter("startDate");
        String time2=request.getParameter("endDate");
        String deviceName=request.getParameter("deviceName");
        Date startDate = StringUtils.isBlank(time1) ? null : DateUtils
                .parse(time1, "yyyy-MM-dd hh:mm:ss");
        Date endDate = StringUtils.isBlank(time2) ? null : DateUtils
                .parse(time2, "yyyy-MM-dd hh:mm:ss");
        System.out.println("startDate:"+startDate);
        System.out.println("endDate:"+endDate);
        String json=equipmentStatusService.selectStatusByTime(start/limit+1, limit,startDate,endDate,"",deviceName);
        WebUtil.sendHtml(request, response, json);
        return null;
    }

}
