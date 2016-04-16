package com.hzjava.monitorcenter.web.action.system;

import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.utils.Configuration;
import com.hzjava.monitorcenter.utils.StringTrim;
import com.hzjava.monitorcenter.web.SessionUtils;
import com.inetec.common.exception.Ex;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 12-5-18
 * Time: 下午3:54
 * 配置管理
 */
public class ConfigManagerAction  extends DispatchActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConfigManagerAction.class);
    private static final String path = System.getProperty("monitor.home")+"/tomcat/conf/server.xml";
//    private static final String path = "E:/server.xml";
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

    /**
     * 获取管理服务、集控采集数据接口设定IP地址所需json1
     *
     */
	public ActionForward readIps(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String json = "{'success':true,'total':0,'rows':[]}";
        try {
            json = read(path);
            logService.newLog("INFO", SessionUtils.getAccount(request)
						.getUserName(), "配置管理", "用户获取管理服务、集控采集数据接口设定IP地址成功 ");
        } catch (Exception e) {
            logger.error("配置管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "配置管理", "用户获取管理服务、集控采集数据接口设定IP地址不成功 ");
        }
        json = new StringTrim().trim(json);
        response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println(json);
		writer.close();
		return null;
	}

    /**
     * 获取管理客户机地址所需json
     *
     */
	public ActionForward read(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String json = "{'success':true,'total':0,'ip1':'','ip2':'','rows':[]}";
        try {
            String startStr = ServletRequestUtils.getStringParameter(request,"start");
            String limitStr = ServletRequestUtils.getStringParameter(request,"limit");
            Integer start = Integer.decode(startStr);
            Integer limit = Integer.decode(limitStr);
            json = read(path,start,limit);
            logService.newLog("INFO", SessionUtils.getAccount(request)
						.getUserName(), "配置管理", "用户获取管理客户机地址成功 ");
        } catch (Exception e) {
            logger.error("配置管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "配置管理", "用户获取管理客户机地址不成功 ");
        }
        json = new StringTrim().trim(json);
        response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println(json);
		writer.close();
		return null;
	}



    /**
     * 删除 管理客户机地址（注：可以多个为客户机地址）
     *
     */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String json = null;
        String msg = null;
        try {
            String[] ip = ServletRequestUtils.getStringParameters(request, "ipArray");
            msg = delete(path, ip);
            logService.newLog("INFO", SessionUtils.getAccount(request)
						.getUserName(), "配置管理", "用户删除管理客户机地址成功 ");
        } catch (Exception e) {
            logger.error("配置管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "配置管理", "用户删除管理客户机地址不成功 ");
            msg = "删除管理客户机地址失败";
        }
        json = "{success:true,msg:'"+msg+"'}";
        response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println(json);
		writer.close();
		return null;
	}
    /**
     * 新增 管理客户机地址（注：可以多个为客户机地址）
     *
     */
	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String json = null;
        String msg = null;
        try {
            String[] ip = ServletRequestUtils.getStringParameters(request, "ipArray");
            msg = insert(path, ip);
            logService.newLog("INFO", SessionUtils.getAccount(request)
						.getUserName(), "配置管理", "用户新增管理客户机地址成功 ");
        } catch (Exception e) {
            logger.error("配置管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "配置管理", "用户新增管理客户机地址不成功 ");
            msg = "新增管理客户机地址失败";
        }
        json = "{success:true,msg:'"+msg+"'}";
        response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println(json);
		writer.close();
		return null;
	}

    /**
     * 更新端口是8443的服务ip--管理服务接口设定IP地址
     *
     */
	public ActionForward update8443(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String json = null;
        String msg = null;
        try {
            String ip = ServletRequestUtils.getStringParameter(request, "ip");
            msg = update(path, ip,8443);
            logService.newLog("INFO", SessionUtils.getAccount(request)
						.getUserName(), "配置管理", "用户更新管理服务接口设定IP地址成功 ");
        } catch (Exception e) {
            logger.error("配置管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
                    .getUserName(), "配置管理", "用户更新管理服务接口设定IP地址不成功 ");
            msg = "更新管理服务接口设定IP地址失败";
        }
        json = "{success:true,msg:'"+msg+"'}";
        response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println(json);
		writer.close();
		return null;
	}

    /**
     * 更新端口是8000的服务ip--集控采集数据接口设定IP地址
     *
     */
	public ActionForward update8000(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String json = null;
        String msg = null;
        try {
            String ip = ServletRequestUtils.getStringParameter(request, "ip");
            msg = update(path,ip, 8000);
            logService.newLog("INFO", SessionUtils.getAccount(request)
						.getUserName(), "配置管理", "用户更新集控采集数据接口设定IP地址成功 ");
        } catch (Exception e) {
            logger.error("配置管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "配置管理", "用户更新集控采集数据接口设定IP地址不成功 ");
            msg = "更新集控采集数据接口设定IP地址失败";
        }
        json = "{success:true,msg:'"+msg+"'}";
        response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println(json);
		writer.close();
		return null;
	}

    private String update(String path, String ip, int port) {
        String result = null;
        try {
            Configuration config = new Configuration(path);
//            Configuration config = new Configuration("F:/tomcat/tomcat/conf/server.xml");
            result = config.editConnectorIp(ip,""+port);
        } catch (Ex ex) {
            result = ex.getMessage();
        }
        return result;
    }

    private String insert(String path, String[] ips) {
        String result = null;
        try {
            Configuration config = new Configuration(path);
            List<String> list = config.getAllowIPs();
            String ip = "|";
            for (int i = 0 ; i < ips.length ; i ++){
                boolean isExist = check(ips[i],list);
                if(!isExist){
                    ip += ips[i];
                }
            }
            result = config.editAllowIp( ip);
        } catch (Ex ex) {
            result = ex.getMessage();
        }
        return result;
    }

    private String delete(String path, String[] ips) {
        String result = null;
        try {
            Configuration config = new Configuration(path);
            List<String> temp = new ArrayList<String>();
            List<String> list = config.getAllowIPs();
            for (int i = 0 ; i < ips.length ; i ++){
                boolean isExist = check(ips[i],list);
                if(isExist){
                    temp.add(ips[i]);
                }else {
                    logger.warn(ips[i] + "已经删除或不存在");
                }
            }
            list.removeAll(temp);
            String ip = list.get(0);
            for (int i = 1 ; i < list.size() ; i ++){
                ip += "|"+list.get(i);
            }
            result = config.deleteAllowIp( ip);
        } catch (Ex ex) {
            result = ex.getMessage();
        }
        return result;
    }

    private boolean check(String ip, List<String> list) {
        for (String str : list){
            if(str.equals(ip)){
                return true;
            }
        }
        return false;
    }

    private String read(String path, Integer start, Integer limit) {
        String json = "{success:true,total:0,rows:[]}";
        try {
            Configuration config = new Configuration(path);
            List<String> list = config.getAllowIPs();
            String[] array = (String[]) list.toArray(new String[list.size()]);
            Arrays.sort(array);
            json = "{success:true,total:"+(list.size())+",rows:[";
            int count = 0;
            for (int i= 0;i<array.length;i++){
                if(i == start && count < limit){
                    json +="{ip:'"+array[i]+"'},";
                    count ++;
                    start ++;
                }
            }
            json += "]}";
        } catch (Ex ex) {
            logger.error(ex.getMessage());
        }
        return json;
    }

    private String read(String path) {
        String json = "{'success':true,'total':0,'rows':[]}";
        try {
            Configuration config = new Configuration(path);
            String ip1 = config.getConnectorIp(""+8443);
            String ip2 = config.getConnectorIp(""+8000);
            json = "{'success':true,'total':1,'rows':[{'ip1':'"+ip1+"','ip2':'"+ip2+"'}]}";
        } catch (Ex ex) {
            logger.error(ex.getMessage());
        }
        return json;
    }
}
