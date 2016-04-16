package com.hzjava.monitorcenter.web.action.system;

import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.web.SessionUtils;
import com.hzjava.monitorcenter.web.WebUtil;
import com.inetec.common.util.*;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.DispatchActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 平台管理
 *
 */
public class BootAction extends DispatchActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BootAction.class);

	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

    /**
     * 设备关闭
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
	public ActionForward equipShutdown(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
        String msg = null;
		try {
            logService.newLog("INFO", SessionUtils.getAccount(request)
                    .getUserName(), "平台管理", "用户关闭设备成功 ");
            OSShutDown.exec();
            msg = "关闭成功,稍后重登录";
        } catch (Exception e) {
            logger.error("平台管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "平台管理", "用户关闭设备不成功 ");
            msg = "关闭失败";
        }
        String json = "{success:true,msg:'"+msg+"'}";
        WebUtil.sendHtml(request, response, json);
		return null;
	}

    /**
     * 重启设备
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ActionForward equipRestart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        String msg = null;
		try {
            logService.newLog("INFO", SessionUtils.getAccount(request)
                    .getUserName(), "平台管理", "用户重启设备成功 ");
            OSReBoot.exec();
            msg = "重启成功,稍后重登录";
        } catch (Exception e) {
            logger.error("平台管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "平台管理", "用户重启设备不成功 ");
            msg = "重启失败";
        }
        String json = "{success:true,msg:'"+msg+"'}";
        WebUtil.sendHtml(request, response, json);
		return null;
	}

    /**
     * 重启系统
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward sysRestart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        String msg = null;
		try {
            logService.newLog("INFO", SessionUtils.getAccount(request)
                    .getUserName(), "平台管理", "用户重启系统成功 ");
            Proc proc;
            OSInfo osinfo = OSInfo.getOSInfo();
            if (osinfo.isWin()) {
                proc = new Proc();
                proc.exec("nircmd service restart cms");
            }
            if (osinfo.isLinux()) {
                proc = new Proc();
                proc.exec("service cms restart");
            }

            msg = "重启成功,稍后重登录";
        } catch (Exception e) {
            logger.error("平台管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "平台管理", "用户重启系统不成功 ");
            msg = "重启失败";
        }
        String json = "{success:true,msg:'"+msg+"'}";
        WebUtil.sendHtml(request, response, json);
		return null;
	}

}
