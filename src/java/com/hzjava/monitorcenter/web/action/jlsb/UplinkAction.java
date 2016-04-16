package com.hzjava.monitorcenter.web.action.jlsb;

import com.hzjava.monitorcenter.utils.ServiceResponse;
import com.hzjava.monitorcenter.utils.ServiceUtil;
import com.hzjava.monitorcenter.web.action.BaseAction;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * @author qxp
 * @struts.action path="/uplink" scope="request" parameter="m"
 */
public class UplinkAction extends BaseAction {
	private static Logger logger = Logger.getLogger(UplinkAction.class);
	private static final String reportType_sysbizinf = "sysbizinf";
    private static final String reportType_syscontrolrulesinf = "syscontrolrulesinf";
    private static final String reportType_sysdeviceinf = "sysdeviceinf";
    private static final String reportType_sysoutlinkinf = "sysoutlinkinf";
    private static final String reportType_sysreginf = "sysreginf";
    private static final String reportType_sysstrategyinf = "sysstrategyinf";
    private static final String reportType_systerminalinf = "systerminalinf";

	/*
	 * 
	 */
	public ActionForward uplink(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("手动级联上报注册信息开始");

        String sysid = ServletRequestUtils.getStringParameter(request,"sysid");
		boolean sysbizinf = ServletRequestUtils.getBooleanParameter(request,"sysbizinf");
		boolean syscontrolrulesinf = ServletRequestUtils.getBooleanParameter(request,"syscontrolrulesinf");
		boolean sysdeviceinf = ServletRequestUtils.getBooleanParameter(request,"sysdeviceinf");
		boolean sysoutlinkinf = ServletRequestUtils.getBooleanParameter(request,"sysoutlinkinf");
		boolean sysreginf = ServletRequestUtils.getBooleanParameter(request,"sysreginf");
		boolean sysstrategyinf = ServletRequestUtils.getBooleanParameter(request,"sysstrategyinf");
		boolean systerminalinfo = ServletRequestUtils.getBooleanParameter(request,"systerminalinfo");
		String uplinkTypes = "";
		String msg = "";
		if(sysbizinf){
			uplinkTypes += reportType_sysbizinf + ":";
		}
		if(syscontrolrulesinf){
			uplinkTypes += reportType_syscontrolrulesinf + ":";
		}
		if(sysdeviceinf){
			uplinkTypes += reportType_sysdeviceinf + ":";
		}
		if(sysoutlinkinf){
			uplinkTypes += reportType_sysoutlinkinf + ":";
		}
		if(sysreginf){
			uplinkTypes += reportType_sysreginf + ":";
		}
		if(sysstrategyinf){
			uplinkTypes += reportType_sysstrategyinf + ":";
		}
		if(systerminalinfo){
			uplinkTypes += reportType_systerminalinf + ":";
		}
		if(uplinkTypes.equals("")){
			msg = "没有选择上报服务";
		}else if(uplinkTypes.split(":").length > 0){
			uplinkTypes = changeString(uplinkTypes);
			String[][] params = new String[][] {
					{ "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
					{ "Command", "uplinkprocess" },
					{ "uplinkid", sysid },
					{ "uplinktypes", uplinkTypes } };
			ServiceResponse sResponse = ServiceUtil.callService(params);
			logger.info(sResponse.getCode());
			if(sResponse.getCode() == 404){
				msg = "上报服务没有开通";
			}else if(sResponse.getCode()==200){
				msg = "上报服务成功";
			}else if(sResponse.getCode()==500){
				msg = "上报服务出错";
			}else {
                msg = "上报异常,错误代码:" + sResponse.getCode();
            }
		}
		logger.info(msg);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true,msg:'"+msg+"'}");
		writer.close();
		
		return mapping.findForward("uplink");
	}	
	
		
	/**
	 * 	去掉字符串最后的":"
	 * @param str aaa:bbb:
	 * @return result aaa:bbb
	 */
	private String changeString(String str){
		String[] strs = str.split(":");
		String result = "";
		for (int i = 0; i < strs.length; i++) {
			if(i != strs.length-1){
				result += strs[i]+ ":";
			}else{
				result += strs[i];
			}
		}
		return result;
	}
}
