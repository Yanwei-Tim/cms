package com.hzjava.monitorcenter.web.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.service.UserService;
import com.hzjava.monitorcenter.web.SessionUtils;

/**
 * 密码修改
 * 
 * @author collin.code@gmail.com
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/pwd" validate="false" parameter="m"
 */
public class PwdAction extends DispatchActionSupport {
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pwd = ServletRequestUtils.getRequiredStringParameter(request,
				"pwd");
		String newpwd = ServletRequestUtils.getRequiredStringParameter(request,
				"newpwd");
		Account account = SessionUtils.getAccount(request);
		if (pwd.equals(account.getPassword())) {
			userService.updatePwd(account.getId(), newpwd);
			account.setPassword(newpwd);
			SessionUtils.setAccount(request, account);
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true}");
		writer.close();

		return null;
	}
}
