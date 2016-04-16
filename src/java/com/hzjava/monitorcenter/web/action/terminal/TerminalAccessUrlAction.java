package com.hzjava.monitorcenter.web.action.terminal;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.SysStrategyInf;
import com.hzjava.monitorcenter.domain.TerminalAccessUrl;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.service.SysStrategyService;
import com.hzjava.monitorcenter.service.TerminalAccessUrlService;
import com.hzjava.monitorcenter.web.SessionUtils;

/**
 * @author www
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/pages/sysinf/sysStrategyInf" validate="false"
 *                parameter="m"
 * @struts.action-forward name="read"
 *                        path=""
 * @struts.action-forward name="insert"
 *                        path=""
 * @struts.action-forward name="update"
 *                        path=""
 * @struts.action-forward name="delete"
 *                        path=""
 */
public class TerminalAccessUrlAction extends DispatchActionSupport {

	private static Logger logger = Logger.getLogger(TerminalAccessUrlAction.class);
	private TerminalAccessUrlService terminalAccessUrlService;
	private LogService logService;
	
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	
	public void setTerminalAccessUrlService(
			TerminalAccessUrlService terminalAccessUrlService) {
		this.terminalAccessUrlService = terminalAccessUrlService;
	}

	public TerminalAccessUrlService getTerminalAccessUrlService() {
		return terminalAccessUrlService;
	}

	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("终端访问Url查看、查看功能开始");
		int start = ServletRequestUtils.getIntParameter(request, "start");
		int limit = ServletRequestUtils.getIntParameter(request, "limit");
		int pageIndex = start / limit + 1;
		
		String json = terminalAccessUrlService.findPages(pageIndex,limit);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print(json);
		writer.close();		
		logger.info("终端访问Url查看、查看功能完成");
		return mapping.findForward("index");
	}
	
	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("终端访问Url新增功能开始");
		String json = null;
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			String busName = ServletRequestUtils.getRequiredStringParameter(request, "busName");
			String accessUrl = ServletRequestUtils.getRequiredStringParameter(request, "accessUrl");
			String resourceRegx = ServletRequestUtils.getRequiredStringParameter(request, "resourceRegx");
			String action = ServletRequestUtils.getRequiredStringParameter(request, "action");
			Long idTerminal = ServletRequestUtils.getLongParameter(request, "idTerminal");
			TerminalAccessUrl terminalAccessUrl = new TerminalAccessUrl();
			terminalAccessUrl.setAccessUrl(accessUrl);
			terminalAccessUrl.setAction(action);
			terminalAccessUrl.setBusName(busName);
			terminalAccessUrl.setResourceRegx(resourceRegx);
			terminalAccessUrl.setIdTerminal(idTerminal);
			try{
				json = terminalAccessUrlService.create(terminalAccessUrl);
				logService.newLog("INFO", account.getUserName(), 
						"终端访问Url管理", "用户 " + account.getName()+ " 新增成功");
			}catch (Exception e) {
				logger.info(e.getMessage());
				logService.newLog("INFO", account.getUserName(), 
						"终端访问Url管理", "用户 " + account.getName()+ " 新增失败");
				json = "{success:true,msg:'新增失败'}";
			}
		}else{
			json = "{success:true,msg:'您还没有登录'}";
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print(json);
		writer.close();		
		logger.info("终端访问Url新增功能完成");
		return mapping.findForward("index");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("终端访问Url删除功能开始");
		String json = null;
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			long[] ids = ServletRequestUtils.getLongParameters(request, "ids");
			try{
				json = terminalAccessUrlService.delete(ids);
				logService.newLog("INFO", account.getUserName(), 
						"终端访问Url管理", "用户 " + account.getName()+ " 删除成功");
			}catch (Exception e) {
				logger.info(e.getMessage());
				logService.newLog("INFO", account.getUserName(), 
						"终端访问Url管理", "用户 " + account.getName()+ " 删除失败");
				json = "{success:true,msg:'删除失败'}";
			}
		}else{
			json = "{success:true,msg:'您还没有登录'}";
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print(json);
		writer.close();		
		logger.info("终端访问Url删除功能完成");
		return mapping.findForward("index");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("终端访问Url修改功能开始");
		String json = null;
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			String busName = ServletRequestUtils.getRequiredStringParameter(request, "busName");
			String accessUrl = ServletRequestUtils.getRequiredStringParameter(request, "accessUrl");
			String resourceRegx = ServletRequestUtils.getRequiredStringParameter(request, "resourceRegx");
			String action = ServletRequestUtils.getRequiredStringParameter(request, "action");
			Long id = ServletRequestUtils.getLongParameter(request, "id");
			Long idTerminal = ServletRequestUtils.getLongParameter(request, "idTerminal");
			TerminalAccessUrl terminalAccessUrl = new TerminalAccessUrl();
			terminalAccessUrl.setAccessUrl(accessUrl);
			terminalAccessUrl.setAction(action);
			terminalAccessUrl.setBusName(busName);
			terminalAccessUrl.setResourceRegx(resourceRegx);
			terminalAccessUrl.setId(id);
			terminalAccessUrl.setIdTerminal(idTerminal);
			try{
				json = terminalAccessUrlService.update(terminalAccessUrl);
				logService.newLog("INFO", account.getUserName(), 
						"终端访问Url管理", "用户 " + account.getName()+ " 修改成功");
			}catch (Exception e) {
				logger.info(e.getMessage());
				logService.newLog("INFO", account.getUserName(), 
						"终端访问Url管理", "用户 " + account.getName()+ " 修改失败");
				json = "{success:true,msg:'修改失败'}";
			}
		}else{
			json = "{success:true,msg:'您还没有登录'}";
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print(json);
		writer.close();		
		logger.info("终端访问Url修改功能完成");
		return mapping.findForward("index");
	}

    public ActionForward byName(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        logger.info("终端访问Url校验名单ID不能相同功能开始");
        Long idTerminal = ServletRequestUtils.getLongParameter(request, "idTerminal");
        TerminalAccessUrl terminalAccessUrl=terminalAccessUrlService.findByIdTerminal(idTerminal);
        if(terminalAccessUrl!=null){
            String json = "{success:true,msg:'名单ID已存在'}";
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.print(json);
            writer.close();
        }
        return null;
    }
}
