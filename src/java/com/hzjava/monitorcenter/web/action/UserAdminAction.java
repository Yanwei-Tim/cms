package com.hzjava.monitorcenter.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.RequestUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import cn.collin.commons.utils.DateUtils;

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.service.UserService;
import com.hzjava.monitorcenter.web.SessionUtils;

/**
 * 用户管理
 * 
 * @author collin.code@gmail.com
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/userAdmin" validate="false"
 *                parameter="m"
 * @struts.action-forward name="index" path="/WEB-INF/pages/user/userIndex.jsp"
 * @struts.action-forward name="detail"
 *                        path="/WEB-INF/pages/user/userDetail.jsp"
 * @struts.action-forward name="addUser" path="/WEB-INF/pages/user/userAdd.jsp"
 * @struts.action-forward name="updateUser"
 *                        path="/WEB-INF/pages/user/userUpdate.jsp"
 * @struts.action-forward name="userComboxJSON"
 *                        path="/WEB-INF/pages/user/userComboxJSON.jsp"
 */
public class UserAdminAction extends DispatchActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(UserAdminAction.class);

	private UserService userService;

	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userName = ServletRequestUtils.getStringParameter(request,
				"name");
		String status = ServletRequestUtils.getStringParameter(request,
				"status");
		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 15);
		int pageIndex = start / limit + 1;
		Map model = userService.getUserIndexModel(userName, status, pageIndex);
		request.setAttribute("model", model);
		return mapping.findForward("index");
	}

	public ActionForward showAddUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map model = userService.getAddUserModel();
		request.setAttribute("model", model);
		return mapping.findForward("addUser");
	}

	public ActionForward addUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userName = null;
		try {
			Account account = new Account();
			RequestUtils.populate(account, request);
			userName = account.getName();
			long rIds[] = ServletRequestUtils.getLongParameters(request, "rid");
			Date now = DateUtils.getNow();
			account.setCreatedTime(now);
			account.setModifiedTime(now);
			userService.newUser(account, rIds);
			logService
					.newLog("INFO", SessionUtils.getAccount(request)
							.getUserName(), "用户管理", "用户 " + account.getName()
							+ " 新增成功");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println("{success:true,msg:'新增成功'}");
            writer.close();
		} catch (Exception e) {
			logger.error("", e);
			logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "用户管理", "用户 " + userName + " 新增不成功");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println("{success:false,msg:'新增不成功'}");
            writer.close();
		}
		

		
		return null;
	}

	public ActionForward showUpdateUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		Map model = userService.getUpdateUserModel(id);
		request.setAttribute("model", model);
		return mapping.findForward("updateUser");
	}

	public ActionForward updateUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = null;
		try {
			Account account = new Account();
			RequestUtils.populate(account, request);
			name = account.getName();
			long rIds[] = ServletRequestUtils.getLongParameters(request, "rid");
			Date now = DateUtils.getNow();
			account.setModifiedTime(now);
			userService.updateUser(account, rIds);
			logService
					.newLog("INFO", SessionUtils.getAccount(request)
							.getUserName(), "用户管理", "用户 " + account.getName()
							+ " 修改成功");
		} catch (Exception e) {
			logger.error("", e);
			logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "用户管理", "用户 " + name + " 修改不成功");
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true}");
		writer.close();
		
		return null;
	}

	public ActionForward getUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		Map model = userService.getUserDetailModel(id);
		request.setAttribute("model", model);

		return mapping.findForward("detail");
	}

	public ActionForward deleteUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		Account account = SessionUtils.getAccount(request);
		userService.deleteUser(id, account);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true}");
		writer.close();
		
		return null;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ActionForward userCombox(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List users = userService.listAllUsers();
		request.setAttribute("users", users);

		return mapping.findForward("userComboxJSON");
	}

}
