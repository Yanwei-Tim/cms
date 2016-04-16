package com.hzjava.monitorcenter.web.action;

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

import cn.collin.commons.utils.DateUtils;

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.Sbpz;
import com.hzjava.monitorcenter.service.ConfService;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.web.SessionUtils;

/**
 * 级联上报：上报配置
 * 
 * @author collin.code@gmail.com
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/sbpzConf" validate="false"
 *                parameter="m"
 * @struts.action-forward name="indexJSON"
 *                        path="/WEB-INF/pages/conf/sbpzIndexJSON.jsp"
 * @struts.action-forward name="detailJSON"
 *                        path="/WEB-INF/pages/conf/sbpzDetailJSON.jsp"
 * 
 */
public class SbpzConfAction extends DispatchActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SbpzConfAction.class);

	private ConfService confService;

	private LogService logService;

	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 15);
		int pageIndex = start / limit + 1;
		Map model = confService.getSbpzIndexModel(pageIndex);
		request.setAttribute("model", model);
		return mapping.findForward("indexJSON");
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		Map model = confService.getSbpzDetailModel(id);
		request.setAttribute("model", model);
		return mapping.findForward("detailJSON");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Sbpz entry = new Sbpz();
		RequestUtils.populate(entry, request);
		entry.setCreatedTime(DateUtils.getNow());
		entry.setModifiedTime(DateUtils.getNow());
		confService.newSbpz(entry);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true}");
		writer.close();

		return null;
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Sbpz entry = new Sbpz();
		RequestUtils.populate(entry, request);
		entry.setModifiedTime(DateUtils.getNow());
		confService.updateSbpz(entry);

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
		confService.deleteSbpz(idsArr, account);

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

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

}
