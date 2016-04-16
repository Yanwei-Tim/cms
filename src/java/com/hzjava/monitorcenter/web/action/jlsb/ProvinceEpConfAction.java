package com.hzjava.monitorcenter.web.action.jlsb;

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

import com.hzjava.monitorcenter.constant.AppConstant;
import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.ProvinceExchangePlatform;
import com.hzjava.monitorcenter.service.JlsbService;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.utils.FileUtils;
import com.hzjava.monitorcenter.web.SessionUtils;
import com.hzjava.monitorcenter.web.WebUtil;

/**
 * 下级交换平台配置
 * 
 * @author collin.code@gmail.com
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/provinceEpConf" validate="false" parameter="m"
 * @struts.action-forward name="indexJSON"
 *                        path="/WEB-INF/pages/jlsb/provinceEp/epIndexJSON.jsp"
 * @struts.action-forward name="add" path="/WEB-INF/pages/jlsb/provinceEp/epAdd.jsp"
 * @struts.action-forward name="update" path="/WEB-INF/pages/jlsb/provinceEp/epUpdate.jsp"
 * @struts.action-forward name="detail" path="/WEB-INF/pages/jlsb/provinceEp/epDetail.jsp"
 * 
 */
public class ProvinceEpConfAction extends DispatchActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProvinceEpConfAction.class);

	private JlsbService jlsbService;

	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setJlsbService(JlsbService jlsbService) {
		this.jlsbService = jlsbService;
	}

	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int pageLength = ServletRequestUtils.getIntParameter(request, "limit", AppConstant.PAGERESULT_PAGE_LENGTH);
		int pageIndex = start / pageLength + 1;
		Map model = jlsbService.getProvinceEpConfIndexModel(pageIndex, pageLength);
		request.setAttribute("model", model);
		return mapping.findForward("indexJSON");
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		Map model = jlsbService.getProvinceEpDetailModel(id);
		request.setAttribute("model", model);
		return mapping.findForward("detail");
	}

	public ActionForward showAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map model = jlsbService.getProvinceEpAddModel();
		request.setAttribute("model", model);
		return mapping.findForward("add");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String platformName = null;
		try {
			ProvinceExchangePlatform entry = new ProvinceExchangePlatform();
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(1024 * 1024 * 10);
			upload.setHeaderEncoding("UTF-8");
			List items = upload.parseRequest(request);
			List fileItems = FileUtils.populate(entry, items, "UTF-8");
//			RequestUtils.populate(entry, request);
			platformName = entry.getPlatformName();
			jlsbService.newProvinceExchangePlatform(entry,fileItems);
			logService.newLog("INFO", SessionUtils.getAccount(request)
					.getUserName(), "下级交换平台配置", "交换平台 " + entry.getPlatformName()
					+ " 新增成功");
		} catch (Exception e) {
			logger.error("", e);
			logService
					.newLog("ERROR", SessionUtils.getAccount(request)
							.getUserName(), "下级交换平台配置", "交换平台 " + platformName
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
		Map model = jlsbService.getProvinceEpUpdateModel(id);
		request.setAttribute("model", model);
		return mapping.findForward("update");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String platformName = null;
		try {
			ProvinceExchangePlatform entry = new ProvinceExchangePlatform();
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(1024 * 1024 * 10);
			upload.setHeaderEncoding("UTF-8");
			List items = upload.parseRequest(request);
			List fileItems = FileUtils.populate(entry, items, "UTF-8");
//			RequestUtils.populate(entry, request);
			platformName = entry.getPlatformName();
			jlsbService.updateProvinceExchangePlatform(entry,fileItems);
			logService.newLog("INFO", SessionUtils.getAccount(request)
					.getUserName(), "下级交换平台配置", "交换平台 " + entry.getPlatformName()
					+ " 修改成功");
		} catch (Exception e) {
			logger.error("", e);
			logService
					.newLog("ERROR", SessionUtils.getAccount(request)
							.getUserName(), "下级交换平台配置", "交换平台 " + platformName
							+ " 修改不成功");
		}
		
		WebUtil.sendHtml(request, response, "{success:true}");
		
		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = ServletRequestUtils.getRequiredStringParameter(request,
				"ids");
		String idsArr[] = ids.split(",");
		Account account = SessionUtils.getAccount(request);
		jlsbService.deleteProvinceExchangePlatform(idsArr, account);
		
		WebUtil.sendHtml(request, response, "{success:true}");
		
		return null;
	}

}
