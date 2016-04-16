package com.hzjava.monitorcenter.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.DispatchActionSupport;

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.web.SessionUtils;

/**
 * @author collin.code@gmail.com
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/logout" validate="false"
 * @struts.action-forward name="success" path="/login.jsp" redirect="true"
 */
public class LogoutAction extends DispatchActionSupport {
	private LogService logService;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Account account = SessionUtils.getAccount(request); 
		if(account !=null){
			String userName = account.getUserName();
			SessionUtils.removeAccount(request);
			SessionUtils.invalidateSession(request);
			logService.newLog("INFO", userName, "用户登录", "用户退出成功");
		}
		return mapping.findForward("success");
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}
}
