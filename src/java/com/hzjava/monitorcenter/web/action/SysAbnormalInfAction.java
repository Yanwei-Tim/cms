package com.hzjava.monitorcenter.web.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.SysAbnormalInf;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.service.SysAbnormalInfService;
import com.hzjava.monitorcenter.web.SessionUtils;

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
public class SysAbnormalInfAction extends DispatchActionSupport {

	private static Logger logger = Logger.getLogger(SysAbnormalInfAction.class);
	private SysAbnormalInfService sysAbnormalInfService;
	private LogService logService;
	
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public SysAbnormalInfService getSysAbnormalInfService() {
		return sysAbnormalInfService;
	}

	public void setSysAbnormalInfService(
			SysAbnormalInfService sysAbnormalInfService) {
		this.sysAbnormalInfService = sysAbnormalInfService;
	}

	public ActionForward read(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("违规处理策略注册查看、查询功能开始");
		int start = ServletRequestUtils.getIntParameter(request, "start");
		int limit = ServletRequestUtils.getIntParameter(request, "limit");
		int pageIndex = start / limit + 1;
		String json = sysAbnormalInfService.getPages(pageIndex,limit);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.print(json);
		writer.close();		
		logger.info("违规处理策略注册查看、查询功能完成");
		return mapping.findForward("read");
	}
	
	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("违规处理策略注册新增功能开始");
		request.getCharacterEncoding();
		String json = null;
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			SysAbnormalInf sysAbnormalInf = new SysAbnormalInf();
			String abnormalTypeCode = ServletRequestUtils.getRequiredStringParameter(request, "abnormalTypeCode");
			String connectObjectType = ServletRequestUtils.getRequiredStringParameter(request, "connectObjectType");
			String exceptionDesc = ServletRequestUtils.getStringParameter(request, "exceptionDesc");
			String exceptionIf = ServletRequestUtils.getRequiredStringParameter(request, "exceptionIf");
			String action = ServletRequestUtils.getRequiredStringParameter(request, "action");
			String status = ServletRequestUtils.getStringParameter(request, "status");
			
			sysAbnormalInf.setAbnormalTypeCode(abnormalTypeCode);
			sysAbnormalInf.setConnectObjectType(connectObjectType);
			sysAbnormalInf.setExceptionIf(exceptionIf);
			sysAbnormalInf.setExceptionDesc(exceptionDesc);
			sysAbnormalInf.setAction(action);
			sysAbnormalInf.setStatus(status);
			try {
				json = sysAbnormalInfService.insert(sysAbnormalInf);
				logService
				.newLog("INFO", account.getUserName(), "违规处理策略注册管理", "用户 " + account.getName()
						+ " 新增成功");
			}catch (Exception e) {
				logger.error("", e);
				logService.newLog("ERROR", SessionUtils.getAccount(request)
						.getUserName(), "违规处理策略注册管理", "用户 " + account.getName()
						+ " 新增失败");
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
		logger.info("违规处理策略注册新增功能完成");
		return mapping.findForward("insert");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("违规处理策略注册删除功能开始");
		String json = null;
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			long[] ids = ServletRequestUtils.getLongParameters(request, "ids");
			try{
				json = sysAbnormalInfService.delete(ids);
				logService.newLog("INFO", account.getUserName(), 
						"违规处理策略注册管理", "用户 " + account.getName()+ " 删除成功");
			}catch (Exception e) {
				logger.info(e.getMessage());
				logService.newLog("INFO", account.getUserName(), 
						"违规处理策略注册管理", "用户 " + account.getName()+ " 删除失败");
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
		logger.info("违规处理策略注册删除功能完成");
		return mapping.findForward("delete");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("违规处理策略注册修改功能开始");
		String json = null;
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			SysAbnormalInf sysAbnormalInf = new SysAbnormalInf();
			Long id = ServletRequestUtils.getLongParameter(request, "id");
			String abnormalTypeCode = ServletRequestUtils.getRequiredStringParameter(request, "abnormalTypeCode");
			String connectObjectType = ServletRequestUtils.getRequiredStringParameter(request, "connectObjectType");
			String exceptionDesc = ServletRequestUtils.getStringParameter(request, "exceptionDesc");
			String exceptionIf = ServletRequestUtils.getRequiredStringParameter(request, "exceptionIf");
			String action = ServletRequestUtils.getRequiredStringParameter(request, "action");
			String status = ServletRequestUtils.getStringParameter(request, "status");
			sysAbnormalInf.setId(id);
			sysAbnormalInf.setAbnormalTypeCode(abnormalTypeCode);
			sysAbnormalInf.setConnectObjectType(connectObjectType);
			sysAbnormalInf.setExceptionIf(exceptionIf);
			sysAbnormalInf.setExceptionDesc(exceptionDesc);
			sysAbnormalInf.setAction(action);
			sysAbnormalInf.setStatus(status);
			try{
				json = sysAbnormalInfService.update(sysAbnormalInf);
				logService.newLog("INFO", account.getUserName(), 
						"违规处理策略注册管理", "用户 " + account.getName()+ " 修改成功");
			}catch (Exception e) {
				logger.info(e.getMessage());
				logService.newLog("INFO", account.getUserName(), 
						"违规处理策略注册管理", "用户 " + account.getName()+ " 修改失败");
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
		logger.info("违规处理策略注册修改功能完成");
		return mapping.findForward("insert");
	}

}
