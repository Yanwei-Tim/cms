package com.hzjava.monitorcenter.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.RequestUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.ExchangePlatform;
import com.hzjava.monitorcenter.service.ConfService;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.utils.FileUtils;
import com.hzjava.monitorcenter.web.SessionUtils;

/**
 * 交换平台配置
 * 
 * @author collin.code@gmail.com
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/epConf" validate="false" parameter="m"
 * @struts.action-forward name="indexJSON"
 *                        path="/WEB-INF/pages/conf/epIndexJSON.jsp"
 * @struts.action-forward name="add" path="/WEB-INF/pages/conf/epAdd.jsp"
 * @struts.action-forward name="update" path="/WEB-INF/pages/conf/epUpdate.jsp"
 * @struts.action-forward name="detail" path="/WEB-INF/pages/conf/epDetail.jsp"
 * 
 */
public class EpConfAction extends DispatchActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EpConfAction.class);

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
		Map model = confService.getEpConfIndexModel(pageIndex);
		request.setAttribute("model", model);
		return mapping.findForward("indexJSON");
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		Map model = confService.getEpDetailModel(id);
		request.setAttribute("model", model);
		return mapping.findForward("detail");
	}

	public ActionForward showAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map model = confService.getEpAddModel();
		request.setAttribute("model", model);
		return mapping.findForward("add");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String platformName = null;
		try {
			ExchangePlatform entry = new ExchangePlatform();
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(1024 * 1024 * 10);
			upload.setHeaderEncoding("UTF-8");
			List items = upload.parseRequest(request);
			List fileItems = FileUtils.populate(entry, items, "UTF-8");
			// RequestUtils.populate(entry, request);
			platformName = entry.getPlatformName();
			confService.newExchangePlatform(entry, fileItems);
			logService.newLog("INFO", SessionUtils.getAccount(request)
					.getUserName(), "交换平台配置", "交换平台 " + entry.getPlatformName()
					+ " 新增成功");
		} catch (Exception e) {
			logger.error("", e);
			logService
					.newLog("ERROR", SessionUtils.getAccount(request)
							.getUserName(), "交换平台配置", "交换平台 " + platformName
							+ " 新增不成功");
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true}");
		writer.close();

		return null;
	}

	public ActionForward showUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		Map model = confService.getEpUpdateModel(id);
		request.setAttribute("model", model);
		return mapping.findForward("update");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String platformName = null;
		try {
			ExchangePlatform entry = new ExchangePlatform();
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(1024 * 1024 * 10);
			upload.setHeaderEncoding("UTF-8");
			List items = upload.parseRequest(request);
			List fileItems = FileUtils.populate(entry, items, "UTF-8");
			// RequestUtils.populate(entry, request);
			platformName = entry.getPlatformName();
			confService.updateExchangePlatform(entry, fileItems);
			logService.newLog("INFO", SessionUtils.getAccount(request)
					.getUserName(), "交换平台配置", "交换平台 " + entry.getPlatformName()
					+ " 修改成功");
		} catch (Exception e) {
			logger.error("", e);
			logService
					.newLog("ERROR", SessionUtils.getAccount(request)
							.getUserName(), "交换平台配置", "交换平台 " + platformName
							+ " 修改不成功");
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true}");
		writer.close();

		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = ServletRequestUtils.getRequiredStringParameter(request,
				"ids");
		String idsArr[] = ids.split(",");
		Account account = SessionUtils.getAccount(request);
		confService.deleteExchangePlatform(idsArr, account);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true}");
		writer.close();

		return null;
	}

	public void setConfService(ConfService confService) {
		this.confService = confService;
	}

}
