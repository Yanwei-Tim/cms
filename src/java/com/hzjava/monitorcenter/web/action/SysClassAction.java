package com.hzjava.monitorcenter.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.DispatchActionSupport;

import com.hzjava.monitorcenter.domain.SysClass;
import com.hzjava.monitorcenter.service.SysClassService;

/**
 * @author www
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/sysClass" validate="false"
 *                parameter="m"
 * @struts.action-forward name="listJSON"
 *                        path="/WEB-INF/pages/sysClass/listJSON.jsp"
 */
public class SysClassAction extends DispatchActionSupport {

	private static Logger logger = Logger.getLogger(SysClassAction.class);
	private SysClassService sysClassService;
	

	public SysClassService getSysClassService() {
		return sysClassService;
	}



	public void setSysClassService(SysClassService sysClassService) {
		this.sysClassService = sysClassService;
	}

	public ActionForward comboSysClass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map model = new HashMap();
		List<SysClass> list = sysClassService.findAll();
		model.put("list", list);
		request.setAttribute("model", model);
		return mapping.findForward("listJSON");
	}

}
