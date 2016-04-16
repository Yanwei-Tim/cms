package com.hzjava.monitorcenter.web.action;

import com.hzjava.monitorcenter.service.OrgcodeService;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.DispatchActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author www
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/Orgcode" validate="false"
 *                parameter="m"
 * @struts.action-forward name="listJSON"
 *                        path="/WEB-INF/pages/Orgcode/listJSON.jsp"
 * @struts.action-forward name="cityList"
 *                        path="/WEB-INF/pages/Orgcode/cityList.jsp" *
 * @struts.action-forward name="cityListShow"
 *                        path="/WEB-INF/pages/Orgcode/cityListShow.jsp"
 */
public class OrgcodeAction extends DispatchActionSupport {

	private static Logger logger = Logger.getLogger(OrgcodeAction.class);
	private OrgcodeService OrgcodeService;

	public ActionForward comboParent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String json = OrgcodeService.findParents();
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.close();
		return null;
	}
    //业务交换下拉框数据
    public ActionForward showBusinessExchModel(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String json = OrgcodeService.showBusinessExchModel();
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.close();
        return null;
    }
    public ActionForward showExchangeDirection(ActionMapping mapping, ActionForm form,
                                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String json = OrgcodeService.showExchangeDirection();
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.close();
        return null;
    }
    public ActionForward showUseDepartType(ActionMapping mapping, ActionForm form,
                                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String json = OrgcodeService.showUseDepartType();
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.close();
        return null;
    }
    public ActionForward showProtocolType(ActionMapping mapping, ActionForm form,
                                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String json = OrgcodeService.showProtocolType();
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.close();
        return null;
    }


	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		OrgcodeAction.logger = logger;
	}

	public OrgcodeService getOrgcodeService() {
		return OrgcodeService;
	}

	public void setOrgcodeService(OrgcodeService OrgcodeService) {
		this.OrgcodeService = OrgcodeService;
	}

}
