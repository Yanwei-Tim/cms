package com.hzjava.monitorcenter.web.action.jlsb;

import com.hzjava.monitorcenter.service.JlsbService;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.web.SessionUtils;
import com.hzjava.monitorcenter.web.WebUtil;
import com.hzjava.monitorcenter.web.action.BaseAction;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 12-8-10
 * Time: 上午10:19
 * 已上报项管理
 */
public class SysQueryServiceAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(SysQueryServiceAction.class);
    private JlsbService jlsbService;
    private LogService logService;

    public void setJlsbService(JlsbService jlsbService) {
        this.jlsbService = jlsbService;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        String json = null;
        try{
            String objectName = ServletRequestUtils.getStringParameter(request,"objectName");
            int start = ServletRequestUtils.getIntParameter(request, "start");
            int limit = ServletRequestUtils.getIntParameter(request,"limit");
            String platformId = ServletRequestUtils.getStringParameter(request,"platformId");
            if(platformId!=null&&platformId.length()>0){
                jlsbService.monitorSysQueryService(objectName,platformId);
            }
            json = jlsbService.selectSysQueryService(objectName,start,limit);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(),"已上报项管理","查询已上报项内容成功");
        } catch (Exception e){
            logger.error("已上报项管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(),"已上报项管理","查询已上报项内容失败");
            json = "{success:true,total:0,rows:[]}";
        }
        WebUtil.sendHtml(request,response,json);
        return null;
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        String msg = null;
        try{
            String platformId = ServletRequestUtils.getStringParameter(request,"platformId");
            long[] idArray = ServletRequestUtils.getLongParameters(request, "idArray");
            msg = jlsbService.deleteSysQueryService(idArray,platformId);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(),"已上报项管理","删除已上报项内容成功");
        } catch (Exception e){
            logger.error("已上报项管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "已上报项管理", "删除已上报项内容失败");
            msg = "删除失败";
        }
        String json = "{success:true,msg:'"+msg+"'}";
        WebUtil.sendHtml(request,response,json);
        return null;
    }
}
