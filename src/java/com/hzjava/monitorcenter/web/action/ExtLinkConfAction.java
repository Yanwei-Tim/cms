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
import com.hzjava.monitorcenter.domain.ExtLink;
import com.hzjava.monitorcenter.service.ConfService;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.web.SessionUtils;

/**
 * 外部链接配置
 * 
 * @author collin.code@gmail.com
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/extLinkConf" validate="false"
 *                parameter="m"
 * @struts.action-forward name="indexJSON"
 *                        path="/WEB-INF/pages/conf/extLinkIndexJSON.jsp"
 * @struts.action-forward name="add" path="/WEB-INF/pages/conf/extLinkAdd.jsp"
 * @struts.action-forward name="update"
 *                        path="/WEB-INF/pages/conf/extLinkUpdate.jsp"
 * @struts.action-forward name="detail"
 *                        path="/WEB-INF/pages/conf/extLinkDetail.jsp"
 * 
 */
public class ExtLinkConfAction extends DispatchActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(ExtLinkConfAction.class);

	private ConfService confService;

	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 15);
		int pageIndex = start / limit + 1;
		Map model = confService.getExtLinkConfIndexModel(pageIndex);
		request.setAttribute("model", model);
		return mapping.findForward("indexJSON");
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		Map model = confService.getExtLinkDetailModel(id);
		request.setAttribute("model", model);
		return mapping.findForward("detail");
	}

	public ActionForward showAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map model = confService.getExtLinkAddModel();
		request.setAttribute("model", model);
		return mapping.findForward("add");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Account account = SessionUtils.getAccount(request);
		String msg = "您还没有登录";
		if(account !=null){
			String linkName = null;
			try {
				ExtLink entry = new ExtLink();
				RequestUtils.populate(entry, request);
				linkName = entry.getLinkName();
				confService.newExtLink(entry);
				logService.newLog("INFO", account.getUserName(), "链路配置", "外部链路 " + linkName + " 新增成功");
				msg = "新增成功";
			} catch (Exception e) {
				logger.error("", e);
				logService.newLog("ERROR", account.getUserName(), "链路配置", "外部链路 " + linkName + " 新增不成功");
				msg = "新增失败";
			}
		}		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true,msg:'"+msg+"'}");
		writer.close();
		
		return null;
	}

	public ActionForward showUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		Map model = confService.getExtLinkUpdateModel(id);
		request.setAttribute("model", model);
		return mapping.findForward("update");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Account account = SessionUtils.getAccount(request);
		String msg = "您还没有登录";
		if(account !=null){
			String linkName = null;
			try {
				ExtLink entry = new ExtLink();
				RequestUtils.populate(entry, request);
				linkName = entry.getLinkName();
				confService.updateExtLink(entry);
				logService.newLog("INFO", account.getUserName(), "链路配置", "外部链路 " + linkName+ " 修改成功");
				msg = "修改成功";
			} catch (Exception e) {
				logger.error("", e);
				logService.newLog("ERROR", account.getUserName(), "链路配置", "外部链路 " + linkName + " 修改不成功");
				msg = "修改失败";
			}			
		}		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true,msg:'"+msg+"'}");
		writer.close();
		
		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Account account = SessionUtils.getAccount(request);
		String msg = "您还没有登录";
		if(account !=null){
			try{
				String ids = ServletRequestUtils.getRequiredStringParameter(request,"ids");
				String idsArr[] = ids.split(",");
				confService.deleteExtLinks(idsArr, account);
				msg = "删除成功";
			}catch (Exception e) {
				msg = "删除失败";
			}			
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true,msg:'"+msg+"'}");
		writer.close();
		
		return null;
	}

	public void setConfService(ConfService confService) {
		this.confService = confService;
	}

}
