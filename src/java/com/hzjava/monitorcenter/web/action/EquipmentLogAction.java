package com.hzjava.monitorcenter.web.action;

import cn.collin.commons.utils.DateUtils;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.utils.DateTimeUtil;
import com.hzjava.monitorcenter.utils.StringUtils;
import com.hzjava.monitorcenter.web.WebUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-24
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
public class EquipmentLogAction extends DispatchActionSupport {

    private LogService equipmentLogService;



    public LogService getEquipmentLogService() {
        return equipmentLogService;
    }

    public void setEquipmentLogService(LogService equipmentLogService) {
        this.equipmentLogService = equipmentLogService;
    }

    public String getAll(ActionMapping mapping, ActionForm form,
                         HttpServletRequest request, HttpServletResponse response) throws IOException {
        int start = Integer.valueOf(request.getParameter("start"));
        int limit = Integer.valueOf(request.getParameter("limit"));
        String json=equipmentLogService.getAll(start,limit);
        WebUtil.sendHtml(request, response, json);
        return null;
    }
    public String selectByTime(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception, ParseException {
        int start = Integer.valueOf(request.getParameter("start"));
        int limit = Integer.valueOf(request.getParameter("limit"));
        String time= ServletRequestUtils.getStringParameter(request,"createtime");
        String deviceName=ServletRequestUtils.getStringParameter(request,"deviceName");
        Date datetime = StringUtils.isBlank(time) ? null : DateUtils
            				.parse(time, "yyyy-MM-dd hh:mm:ss");
        System.out.println("time:"+time);
        System.out.println("datetime:"+datetime);
        DateTimeUtil dateTimeUtil=new DateTimeUtil();
        //获取time的前5分钟      type 时间差类型：1->秒,2->分钟,3->小时,4->天
//        Date startDate=dateTimeUtil.getPreTime(300,1,datetime);
        long nowLong=datetime.getTime();
        System.out.println(nowLong);
	    Date startDate = new Date(nowLong-600000);//加上时间差毫秒数
	    Date endDate = new Date(nowLong+600000);//加上时间差毫秒数
        System.out.println("startDate:"+startDate);
        //获取time的后5分钟    type 时间差类型：1->秒,2->分钟,3->小时,4->天
//        Date endDate=dateTimeUtil.getNextTime(3000,1,datetime);
        System.out.println("endDate:"+endDate);
        String json=equipmentLogService.selectLogByTime(start/limit+1, limit,startDate,endDate,"",deviceName);
        WebUtil.sendHtml(request, response, json);
        return null;
    }



}
