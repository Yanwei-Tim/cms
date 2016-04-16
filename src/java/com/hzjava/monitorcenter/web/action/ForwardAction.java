package com.hzjava.monitorcenter.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.DispatchActionSupport;

/**
 * 专门用于跳转页面的action,没有业务逻辑。
 * 
 * @author xiangqi.java@gmail.com
 * @struts.action type="com.hzjava.monitorcenter.web.action.ForwardAction"
 *                path="/admin/forward" scope="request" parameter="m"
 *                validate="false"
 * @struts.action-forward name="sysLog"
 *                        path="/WEB-INF/pages/logs/sysLogGrid.jsp"
 * @struts.action-forward name="userLog"
 *                        path="/WEB-INF/pages/logs/userLogGrid.jsp"
 * @struts.action-forward name="businessLog"
 *                        path="/WEB-INF/pages/logs/businessLogGrid.jsp"
 * @struts.action-forward name="equipmentLog"
 *                        path="/WEB-INF/pages/logs/equipmentLogGrid.jsp"
 * @struts.action-forward name="bdDayReport"
 *                        path="/WEB-INF/pages/reports/bdDayReport.jsp"
 * @struts.action-forward name="bdMonthReport"
 *                        path="/WEB-INF/pages/reports/bdMonthReport.jsp"
 * @struts.action-forward name="bdYearReport"
 *                        path="/WEB-INF/pages/reports/bdYearReport.jsp"
 * @struts.action-forward name="eqDayReport"
 *                        path="/WEB-INF/pages/reports/eqDayReport.jsp"
 * @struts.action-forward name="eqMonthReport"
 *                        path="/WEB-INF/pages/reports/eqMonthReport.jsp"
 * @struts.action-forward name="eqYearReport"
 *                        path="/WEB-INF/pages/reports/eqYearReport.jsp"
 * @struts.action-forward name="bsalert" path="/WEB-INF/pages/alert/bsalert.jsp"
 * @struts.action-forward name="scalert" path="/WEB-INF/pages/alert/scalert.jsp"
 * @struts.action-forward name="eqalert" path="/WEB-INF/pages/alert/eqalert.jsp"
 * @struts.action-forward name="alertconfig"
 *                        path="/WEB-INF/pages/alert/alertconfig.jsp"
 * @struts.action-forward name="sysruntime"
 *                        path="/WEB-INF/pages/jlsb/sysruntimeGrid.jsp"
 * @struts.action-forward name="sysabnormal"
 *                        path="/WEB-INF/pages/jlsb/sysabnormalGrid.jsp"
 * @struts.action-forward name="sysstatus"
 *                        path="/WEB-INF/pages/jlsb/sysstatusGrid.jsp"
 * @struts.action-forward name="sysbizstatus"
 *                        path="/WEB-INF/pages/jlsb/sysbizstatusGrid.jsp"
 * @struts.action-forward name="lowerSysruntime"
 *                        path="/WEB-INF/pages/jlsb/lowerSysruntimeGrid.jsp"
 * @struts.action-forward name="lowerSysabnormal"
 *                        path="/WEB-INF/pages/jlsb/lowerSysabnormalGrid.jsp"
 * @struts.action-forward name="lowerSysstatus"
 *                        path="/WEB-INF/pages/jlsb/lowerSysstatusGrid.jsp"
 * @struts.action-forward name="lowerSysbizstatus"
 *                        path="/WEB-INF/pages/jlsb/lowerSysbizstatusGrid.jsp"
 * @struts.action-forward name="parentEp"
 *                        path="/WEB-INF/pages/jlsb/parentEp/ep.jsp"
 * @struts.action-forward name="provinceEp"
 *                        path="/WEB-INF/pages/jlsb/provinceEp/ep.jsp"
 * @struts.action-forward name="sysTerminalConf"
 *                        path="/WEB-INF/pages/sysTerminalConf/ep.jsp"
 * @struts.action-forward name="terminalOperAudit"
 *                        path="/WEB-INF/pages/terminal/operAuditGrid.jsp"
 * @struts.action-forward name="terminalAccessAudit"
 *                        path="/WEB-INF/pages/terminal/accessAuditGrid.jsp"*
 * @struts.action-forward name="tenimalList" 
 * 						  path="/WEB-INF/pages/terminal/tenimalListGrid.jsp"
 * @struts.action-forward name="terminalUserAccessReport" 
 *                        path="/WEB-INF/pages/terminal/terminalUserAccessReport.jsp"
 * @struts.action-forward name="sysTerminalStatusUserReport" 
 *                        path="/WEB-INF/pages/terminal/sysTerminalStatusUserReport.jsp"
 */
public class ForwardAction extends DispatchActionSupport {
//	private static Logger logger = Logger.getLogger(ForwardAction.class);

	/** 系统日志审计 * */
	public ActionForward sysLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("sysLog");
	}

	/** 用户日志审计 * */
	public ActionForward userLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("userLog");
	}

	/** 业务日志审计 * */
	public ActionForward businessLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("businessLog");
	}

	/** 设备日志审计 * */
	public ActionForward equipmentLog(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("equipmentLog");
	}

	/** 业务日统计 * */
	public ActionForward bdDayReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("bdDayReport");
	}

	/** 业务月统计 * */
	public ActionForward bdMonthReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("bdMonthReport");
	}

	/** 业务年统计 * */
	public ActionForward bdYearReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("bdYearReport");
	}

	/** 设备日统计 * */
	public ActionForward eqDayReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("eqDayReport");
	}

	/** 设备月统计 * */
	public ActionForward eqMonthReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("eqMonthReport");
	}

	/** 设备年统计 * */
	public ActionForward eqYearReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("eqYearReport");
	}

	/** 报警配置 * */
	public ActionForward alertconfig(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("alertconfig");
	}

	/** 业务异常报警查询 * */
	public ActionForward bsalert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("bsalert");
	}

	/** 安全事件报警查询 * */
	public ActionForward scalert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("scalert");
	}

	/** 设备故障报警查询 * */
	public ActionForward eqalert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("eqalert");
	}

    /**  * */
	public ActionForward logConf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("logConf");
	}



	/** 本级系统状态查询 * */
	public ActionForward sysruntime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("sysruntime");
	}

	/** 本级系统违规情况查询 * */
	public ActionForward sysabnormal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("sysabnormal");
	}

	/** 本级系统运行情况查询 * */
	public ActionForward sysstatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("sysstatus");
	}

	/** 本级接入应用运行情况查询 * */
	public ActionForward sysbizstatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("sysbizstatus");
	}

	/** 下级系统状态查询 * */
	public ActionForward lowerSysruntime(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("lowerSysruntime");
	}

	/** 下级系统违规情况查询 * */
	public ActionForward lowerSysabnormal(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("lowerSysabnormal");
	}

	/** 下级系统运行情况查询 * */
	public ActionForward lowerSysstatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("lowerSysstatus");
	}

	/** 下级接入应用运行情况查询 * */
	public ActionForward lowerSysbizstatus(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("lowerSysbizstatus");
	}

	/** 上级平台交换配置 * */
	public ActionForward parentEp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("parentEp");
	}

	/** 下级级平台交换配置 * */
	public ActionForward provinceEp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("provinceEp");
	}

	/** 终端设备管理 * */
	public ActionForward sysTerminalConf(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("sysTerminalConf");
	}

	/** 用户管理审计 * */
	public ActionForward terminalOperAudit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("terminalOperAudit");
	}

	/** 用户访问审计  * */
	public ActionForward terminalAccessAudit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		return mapping.findForward("terminalAccessAudit");
	}
	/**date:2012-03-20 author:qxp function:终端管理的'全部用户列表'**/
	public ActionForward tenimalList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		return mapping.findForward("tenimalList");
	}
	/**
	 * date 2012-03-20 author qxp function:终端管理的'在线用户管理'
	 */
	public ActionForward tenimalListOnline(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		return mapping.findForward("tenimalListOnline");
	}
	
	/**
	 * date 2012-03-20 author qxp function:终端管理的'终端用户访问URL'
	 */
	public ActionForward terminalAccessUrl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		return mapping.findForward("terminalAccessUrl");
	}
	
	/**
	 * date 2012-03-20 author qxp function:统计报表的'终端用户访问统计表'
	 */
	public ActionForward terminalUserAccessReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		return mapping.findForward("terminalUserAccessReport");
	}
	
	/**
	 * date 2012-03-20 author qxp function:统计报表的'终端用户运行日统计表'
	 */
	public ActionForward sysTerminalStatusReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		return mapping.findForward("sysTerminalStatusReport");
	}
    /**
     * date 2013-03-13 author gdw function:统计报表的'违规日统计表'
     */
    public ActionForward abnormalReportDay(ActionMapping mapping, ActionForm form,
                                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("abnormalReportDay");
    }
    /**
     * date 2013-03-13 author gdw function:统计报表的'违规月统计表'
     */
    public ActionForward abnormalReportMonth(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("abnormalReportMonth");
    }
    /**
     * date 2013-03-13 author gdw function:统计报表的'违规年统计表'
     */
    public ActionForward abnormalReportYear(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return mapping.findForward("abnormalReportYear");
    }
}
