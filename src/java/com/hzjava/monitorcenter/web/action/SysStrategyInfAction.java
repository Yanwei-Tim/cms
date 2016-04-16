package com.hzjava.monitorcenter.web.action;

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
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.service.SysStrategyService;
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
public class SysStrategyInfAction extends DispatchActionSupport {

	private static Logger logger = Logger.getLogger(SysStrategyInfAction.class);
	private SysStrategyService sysStrategyService;
	private LogService logService;
	
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public SysStrategyService getSysStrategyService() {
		return sysStrategyService;
	}

	public void setSysStrategyService(SysStrategyService sysStrategyService) {
		this.sysStrategyService = sysStrategyService;
	}

	public ActionForward read(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("安全策略注册查看、查看功能开始");
		int start = ServletRequestUtils.getIntParameter(request, "start");
		int limit = ServletRequestUtils.getIntParameter(request, "limit");
		int pageIndex = start / limit + 1;
		
		String json = sysStrategyService.getPages(pageIndex,limit);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print(json);
		writer.close();		
		logger.info("安全策略注册查看、查看功能完成");
		return mapping.findForward("read");
	}
	
	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("安全策略注册新增功能开始");
		String json = null;
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			String descIp = ServletRequestUtils.getRequiredStringParameter(request, "descIp");
			String descPort = ServletRequestUtils.getRequiredStringParameter(request, "descPort");
			String srcIp = ServletRequestUtils.getRequiredStringParameter(request, "srcIp");
			String srcPort = ServletRequestUtils.getRequiredStringParameter(request, "srcPort");
			String strategyDesc = ServletRequestUtils.getRequiredStringParameter(request, "strategyDesc");
			String strategyProtocalType = ServletRequestUtils.getRequiredStringParameter(request, "strategyProtocalType");
			String time = ServletRequestUtils.getStringParameter(request, "collectTime");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date collectTime = sdf.parse(time);		
			SysStrategyInf sysStrategyInf = new SysStrategyInf();
			sysStrategyInf.setCollectTime(collectTime);
			sysStrategyInf.setDescIp(descIp);
			sysStrategyInf.setDescPort(descPort);
			sysStrategyInf.setSrcIp(srcIp);
			sysStrategyInf.setSrcPort(srcPort);
			sysStrategyInf.setStrategyDesc(strategyDesc);
			sysStrategyInf.setStrategyProtocalType(strategyProtocalType);
			try{
				json = sysStrategyService.insert(sysStrategyInf);
				logService.newLog("INFO", account.getUserName(), 
						"安全策略注册管理", "用户 " + account.getName()+ " 新增成功");
			}catch (Exception e) {
				logger.info(e.getMessage());
				logService.newLog("INFO", account.getUserName(), 
						"安全策略注册管理", "用户 " + account.getName()+ " 新增失败");
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
		logger.info("安全策略注册新增功能完成");
		return mapping.findForward("read");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("安全策略注册删除功能开始");
		String json = null;
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			long[] ids = ServletRequestUtils.getLongParameters(request, "ids");
			try{
				json = sysStrategyService.delete(ids);
				logService.newLog("INFO", account.getUserName(), 
						"安全策略注册管理", "用户 " + account.getName()+ " 删除成功");
			}catch (Exception e) {
				logger.info(e.getMessage());
				logService.newLog("INFO", account.getUserName(), 
						"安全策略注册管理", "用户 " + account.getName()+ " 删除失败");
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
		logger.info("安全策略注册删除功能完成");
		return mapping.findForward("delete");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("安全策略注册修改功能开始");
		String json = null;
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			Long id = ServletRequestUtils.getLongParameter(request, "id");
			String descIp = ServletRequestUtils.getRequiredStringParameter(request, "descIp");
			String descPort = ServletRequestUtils.getRequiredStringParameter(request, "descPort");
			String srcIp = ServletRequestUtils.getRequiredStringParameter(request, "srcIp");
			String srcPort = ServletRequestUtils.getRequiredStringParameter(request, "srcPort");
			String strategyDesc = ServletRequestUtils.getRequiredStringParameter(request, "strategyDesc");
			String strategyProtocalType = ServletRequestUtils.getRequiredStringParameter(request, "strategyProtocalType");
			String time = ServletRequestUtils.getStringParameter(request, "collectTime");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date collectTime = sdf.parse(time);		
			SysStrategyInf sysStrategyInf = new SysStrategyInf();
			sysStrategyInf.setId(id);
			sysStrategyInf.setCollectTime(collectTime);
			sysStrategyInf.setDescIp(descIp);
			sysStrategyInf.setDescPort(descPort);
			sysStrategyInf.setSrcIp(srcIp);
			sysStrategyInf.setSrcPort(srcPort);
			sysStrategyInf.setStrategyDesc(strategyDesc);
			sysStrategyInf.setStrategyProtocalType(strategyProtocalType);
			try{
				json = sysStrategyService.update(sysStrategyInf);
				logService.newLog("INFO", account.getUserName(), 
						"安全策略注册管理", "用户 " + account.getName()+ " 修改成功");
			}catch (Exception e) {
				logger.info(e.getMessage());
				logService.newLog("INFO", account.getUserName(), 
						"安全策略注册管理", "用户 " + account.getName()+ " 修改失败");
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
		logger.info("安全策略注册修改功能完成");
		return mapping.findForward("read");
	}
}
