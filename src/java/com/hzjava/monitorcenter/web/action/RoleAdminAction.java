package com.hzjava.monitorcenter.web.action;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.Role;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.service.UserService;
import com.hzjava.monitorcenter.web.SessionUtils;

/**
 * 角色管理
 * 
 * @author collin.code@gmail.com
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/roleAdmin" validate="false"
 *                parameter="m"
 * @struts.action-forward name="index" path="/WEB-INF/pages/user/manager_platform.jsp"
 * @struts.action-forward name="detail"
 *                        path="/WEB-INF/pages/user/roleDetail.jsp"
 * @struts.action-forward name="addRole" path="/WEB-INF/pages/user/roleAdd.jsp"
 * @struts.action-forward name="updateRole"
 *                        path="/WEB-INF/pages/user/roleUpdate.jsp"
 */
public class RoleAdminAction extends DispatchActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(RoleAdminAction.class);

	private UserService userService;
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map model = userService.getRoleIndexModel();
		request.setAttribute("model", model);
		return mapping.findForward("index");
	}

	public ActionForward addRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = null;
		try {
			Role role = new Role();
			RequestUtils.populate(role, request);
			name = role.getName();
			long permissionIds[] = ServletRequestUtils.getLongParameters(
					request, "pid");
            if(!name.equals("")&&permissionIds.length>0){
                userService.newRole(role, permissionIds);
                logService.newLog("INFO", SessionUtils.getAccount(request)
                        .getUserName(), "角色管理", "角色名 " + name + " 新增成功");
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html");
                PrintWriter writer = response.getWriter();
                writer.println("{success:true,msg:'角色名新增成功'}");
                writer.close();
            } else {
                logService.newLog("ERROR", SessionUtils.getAccount(request)
                        .getUserName(), "角色管理", "角色名 " + name + " 新增失败");
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html");
                PrintWriter writer = response.getWriter();
                writer.println("{success:true,msg:'角色名新增失败,请输入新增加内容'}");
                writer.close();
            }

		} catch (Exception e) {
			logger.error("", e);
			logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "角色管理", "角色名 " + name + " 新增不成功");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println("{success:true,msg:'角色名新增失败'}");
            writer.close();
		}
		return null;
	}

	public ActionForward showAddRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map model = userService.getAddRoleModel();
		request.setAttribute("model", model);
		return mapping.findForward("addRole");
	}

	public ActionForward showUpdateRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		Map model = userService.getUpdateRoleModel(id);
		request.setAttribute("model", model);
		return mapping.findForward("updateRole");
	}

	public ActionForward updateRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = null;
		try {
			Role role = new Role();
			RequestUtils.populate(role, request);
			name = role.getName();
			long permissionIds[] = ServletRequestUtils.getLongParameters(
					request, "pid");

            if(!name.equals("")&&permissionIds.length>0){
                userService.updateRole(role, permissionIds);
                logService.newLog("INFO", SessionUtils.getAccount(request)
                        .getUserName(), "角色管理", "角色名 " + name + " 修改成功");
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html");
                PrintWriter writer = response.getWriter();
                writer.println("{success:true,msg:'角色名修改成功'}");
                writer.close();
            } else {
                logService.newLog("ERROR", SessionUtils.getAccount(request)
                        .getUserName(), "角色管理", "角色名 " + name + " 修改不成功");
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html");
                PrintWriter writer = response.getWriter();
                writer.println("{success:true,msg:'角色名修改失败,请输入新修改内容'}");
                writer.close();
            }
		} catch (Exception e) {
			logger.error("", e);
			logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "角色管理", "角色名 " + name + " 修改不成功");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println("{success:true,msg:'角色名修改失败'}");
            writer.close();
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true}");
		writer.close();
		
		return null;
	}

	public ActionForward getRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long roleId = ServletRequestUtils.getLongParameter(request, "id");
		Map model = userService.getRoleDetailModel(roleId);
		request.setAttribute("model", model);
		return mapping.findForward("detail");
	}

	public ActionForward deleteRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long roleId = ServletRequestUtils.getLongParameter(request, "id");
		Account account = SessionUtils.getAccount(request);
		userService.deleteRole(roleId, account);
		
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
}
