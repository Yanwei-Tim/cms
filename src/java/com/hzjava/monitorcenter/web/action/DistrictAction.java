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
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import com.hzjava.monitorcenter.domain.District;
import com.hzjava.monitorcenter.service.DistrictService;

/**
 * @author www
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/district" validate="false"
 *                parameter="m"
 * @struts.action-forward name="listJSON"
 *                        path="/WEB-INF/pages/district/listJSON.jsp"
 * @struts.action-forward name="cityList"
 *                        path="/WEB-INF/pages/district/cityList.jsp" *
 * @struts.action-forward name="cityListShow"
 *                        path="/WEB-INF/pages/district/cityListShow.jsp"
 */
public class DistrictAction extends DispatchActionSupport {

	private static Logger logger = Logger.getLogger(DistrictAction.class);
	private DistrictService districtService;

	public ActionForward comboParent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map model = new HashMap();
		List<District> list = districtService.findParents();
		model.put("list", list);
		request.setAttribute("model", model);
		return mapping.findForward("listJSON");
	}

	public ActionForward comboChild(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map model = new HashMap();
		Long parentId = ServletRequestUtils.getLongParameter(request,
				"parentId");
		List<District> list = districtService.findChildByParent(parentId);
		model.put("list", list);
		request.setAttribute("model", model);
		return mapping.findForward("listJSON");

	}

	public ActionForward comboChildByParent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map model = new HashMap();
		Long parentId = ServletRequestUtils.getLongParameter(request,
				"parentId");
		List<District> list = districtService.findChildByParent(parentId);
		model.put("list", list);
		request.setAttribute("CityList", model);
		request.setAttribute("name", ServletRequestUtils.getStringParameter(
				request, "name"));
		return mapping.findForward("cityList");

	}

	public ActionForward comboChildByParentShow(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map model = new HashMap();
		Long parentId = ServletRequestUtils.getLongParameter(request,
				"parentId");
		Long id = ServletRequestUtils.getLongParameter(request,
				"id");
		List<District> list = districtService.findChildByParent(parentId/10000*10000);
		model.put("list", list);
		request.setAttribute("CityList", model);
		request.setAttribute("name", ServletRequestUtils.getStringParameter(
				request, "name"));
		request.setAttribute("id", id);
		return mapping.findForward("cityListShow");

	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		DistrictAction.logger = logger;
	}

	public DistrictService getDistrictService() {
		return districtService;
	}

	public void setDistrictService(DistrictService districtService) {
		this.districtService = districtService;
	}

}
