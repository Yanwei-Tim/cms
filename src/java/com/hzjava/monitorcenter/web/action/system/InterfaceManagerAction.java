package com.hzjava.monitorcenter.web.action.system;

import com.hzjava.monitorcenter.entity.NetInfo;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.service.RunMonitorService;
import com.hzjava.monitorcenter.utils.NetworkUtil;
import com.hzjava.monitorcenter.utils.StringTrim;
import com.hzjava.monitorcenter.web.SessionUtils;
import com.hzjava.monitorcenter.web.action.thread.PingIp;
import com.inetec.common.net.Ping;
import com.inetec.common.net.Telnet;
import com.inetec.common.util.OSInfo;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 网络管理
 *
 */
public class InterfaceManagerAction extends DispatchActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(InterfaceManagerAction.class);
    private static Map ipmap = new HashMap();
    private static Map threadmap = new HashMap();
    private static int  threadnum;
	private LogService logService;
    private RunMonitorService runMonitorService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

    public static Map getThreadmap() {
        return threadmap;
    }

    public static Map getIpmap() {
        return ipmap;
    }

    public void setRunMonitorService(RunMonitorService runMonitorService) {
        this.runMonitorService = runMonitorService;
    }

    /**
     * 连通测试
     *
     */
	public ActionForward ping(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String json = null;
        String msg = null;
        try {
            String ip = ServletRequestUtils.getStringParameter(request,"ip");
            int count = ServletRequestUtils.getIntParameter(request,"count");
            String pingStr = Ping.exec(ip, count);
            msg = getResult(pingStr);
            logService.newLog("INFO", SessionUtils.getAccount(request)
						.getUserName(), "连通测试", "用户ping成功 ");
        } catch (Exception e) {
            logger.error("连通测试", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "连通测试", "用户ping不成功 ");
            msg = "ping失败";
        }
        json = "{success:true,msg:'"+msg+"'}";
        response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println(json);
		writer.close();
		return null;
	}

	private String getResult(String pingStr) {
		String result = "";
		OSInfo osInfo = OSInfo.getOSInfo();
		if(osInfo.isLinux()){
			String[] pings = pingStr.split("\n");
			for (int i = 0; i < pings.length; i++) {
				if(i<pings.length - 1){
					result += pings[i].trim()+"<br>";
				}else{
					result += pings[i].trim();
				}
			}
		}else if(osInfo.isWin()){
			String[] pings = pingStr.split("\r\n");
			for (int i = 0; i < pings.length; i++) {
				if(i<pings.length - 1){
					result += pings[i].trim()+"<br>";
				}else{
					result += pings[i].trim();
				}
			}
		}
		return result;
	}

     /**
     * 端口测试
     *
     */
	public ActionForward telnet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String json = null;
        String msg = null;
		try {
            String ip = ServletRequestUtils.getStringParameter(request,"ip");
            int port = ServletRequestUtils.getIntParameter(request,"port");
            boolean isTelnet = Telnet.exec(ip, port);
            if(isTelnet){
                log.info("IP"+ip+"上的端口"+port+"是打开的!");
                msg = "<font color=\"green\">端口是打开的!</font>";
            }else{
                log.info("IP"+ip+"上的端口"+port+"是关闭的!");
                msg = "<font color=\"red\">端口是关闭的!</font>";
            }
            logService.newLog("INFO", SessionUtils.getAccount(request)
						.getUserName(), "端口测试", "用户telnet成功 ");
        } catch (Exception e) {
            logger.error("端口测试", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "端口测试", "用户telnet不成功 ");
            msg = "telnet失败";
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
    * 读取路由信息
    *
    */
    public ActionForward readRouter(ActionMapping mapping, ActionForm form,
               HttpServletRequest request, HttpServletResponse response)
               throws IOException {
        String json = "{success:true,total:0,rows:[]}";
        try {
            NetworkUtil networkUtil = new NetworkUtil();
            json = networkUtil.readListRouter();
            json = new StringTrim().trim(json);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "路由管理", "用户读取路由信息成功 ");
        } catch (Exception e) {
            logger.error("路由管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "路由管理", "用户读取路由信息不成功 ");
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.close();
        return null;
    }

    /**
    * 读取所有接口名
    *
    */
    public ActionForward readInterfaceName(ActionMapping mapping, ActionForm form,
               HttpServletRequest request, HttpServletResponse response)
               throws IOException {
        String json =  "{'success':true,'total':0,'rows':[]}";
        String model = "路由管理/接口管理";
        try {
            String t = ServletRequestUtils.getStringParameter(request,"t");
            if(t.equals("router")){
                model = "路由管理";
            } else if (t.endsWith("interface")){
                model = "接口管理";
            }

            NetworkUtil networkUtil = new NetworkUtil();
            json = networkUtil.readListNetInfoName();
            json = new StringTrim().trim(json);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), model, "用户读取所有接口名成功 ");
        } catch (Exception e) {
            logger.error(model, e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), model, "用户读取所有接口名不成功 ");
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.close();
        return null;
    }
    /**
    * 新增一个路由信息
    *
    */
    public ActionForward saveRouter(ActionMapping mapping, ActionForm form,
               HttpServletRequest request, HttpServletResponse response)
               throws IOException {
        String json = null;
        String msg = null;
        try {
            String interfaceName = ServletRequestUtils.getStringParameter(request,"interfaceName");
            String subnetMask = ServletRequestUtils.getStringParameter(request,"subnetMask");
            String gateway = ServletRequestUtils.getStringParameter(request,"gateway");
            String destination  = ServletRequestUtils.getStringParameter(request,"destination");
            NetInfo netInfo = new NetInfo(interfaceName,subnetMask,gateway,destination);
            NetworkUtil networkUtil = new NetworkUtil();
            msg = networkUtil.saveRouter(netInfo);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "路由管理", "用户新增一个路由信息成功 ");

        } catch (Exception e) {
            logger.error("路由管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "路由管理", "用户新增一个路由信息不成功 ");
            msg = "新增一个路由信息失败";
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
    * 删除一个路由信息
    *
    */
    public ActionForward deleteRouter(ActionMapping mapping, ActionForm form,
               HttpServletRequest request, HttpServletResponse response)
               throws IOException {
        String json = null;
        String msg = null;
        try {
            String[] destinationArray = ServletRequestUtils.getStringParameters(request,"destinationArray");
	        String[] gatewayArray = ServletRequestUtils.getStringParameters(request,"gatewayArray");
	        String[] subnetMaskArray = ServletRequestUtils.getStringParameters(request,"subnetMaskArray");
	        String[] interfaceNameArray = ServletRequestUtils.getStringParameters(request,"interfaceNameArray");
            NetworkUtil networkUtil = new NetworkUtil();
            List<NetInfo> netInfos = new ArrayList<NetInfo>();
		    for (int i = 0; i < destinationArray.length; i++) {
                NetInfo netInfo = new NetInfo();
                netInfo.setDestination(destinationArray[i]);
                netInfo.setInterfaceName(interfaceNameArray[i]);
                netInfo.setSubnetMask(subnetMaskArray[i]);
                netInfo.setGateway(gatewayArray[i]);
                netInfos.add(netInfo);
            }
		    msg = networkUtil.deleteRouter(netInfos);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "路由管理", "用户删除路由信息成功 ");
        } catch (Exception e) {
            logger.error("路由管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "路由管理", "用户删除路由信息不成功 ");
            msg = "删除路由信息失败";
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
    * 读取接口信息
    *
    */
    public ActionForward readInterface(ActionMapping mapping, ActionForm form,
               HttpServletRequest request, HttpServletResponse response)
               throws IOException {
        String json = "{success:true,total:0,rows:[]}";
        try {
            String startStr = ServletRequestUtils.getStringParameter(request,"start");
            String limitStr = ServletRequestUtils.getStringParameter(request,"limit");
            Integer start = Integer.decode(startStr);
            Integer limit = Integer.decode(limitStr);
            NetworkUtil networkUtil = new NetworkUtil();
            json = networkUtil.readListNetInfo(start,limit);
            json = new StringTrim().trim(json);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "接口管理", "用户读取接口信息成功 ");
        } catch (Exception e) {
            logger.error("接口管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "接口管理", "用户读取接口信息不成功 ");
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.close();
        return null;
    }

    /**
     *  激活接口
     *
     */
    public ActionForward ifInterfaceUp(ActionMapping mapping, ActionForm form,
               HttpServletRequest request, HttpServletResponse response)
               throws IOException {
        String json = null;
        String msg = null;
        try {
            String interfaceName = ServletRequestUtils.getStringParameter(request,"interfaceName");
            NetworkUtil networkUtil = new NetworkUtil();
		    msg = networkUtil.ifUp(interfaceName);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "接口管理", "用户激活接口成功 ");
        } catch (Exception e) {
            logger.error("接口管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "接口管理", "用户激活接口不成功 ");
            msg = "激活接口失败";
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
     *  注销接口
     *
     */
    public ActionForward ifInterfaceDown(ActionMapping mapping, ActionForm form,
               HttpServletRequest request, HttpServletResponse response)
               throws IOException {
        String json = null;
        String msg = null;
        try {
            String interfaceName = ServletRequestUtils.getStringParameter(request,"interfaceName");
            NetworkUtil networkUtil = new NetworkUtil();
		    msg = networkUtil.ifDown(interfaceName);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "接口管理", "用户注销接口成功 ");
        } catch (Exception e) {
            logger.error("接口管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "接口管理", "用户注销接口不成功 ");
            msg = "注销接口失败";
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
    * 新增虚拟接口
    *
    */
    public ActionForward saveInterface(ActionMapping mapping, ActionForm form,
               HttpServletRequest request, HttpServletResponse response)
               throws IOException {
        String json = null;
        String msg = null;
        try {
            String interfaceName = ServletRequestUtils.getStringParameter(request,"interfaceName");
            String ip = ServletRequestUtils.getStringParameter(request,"ip");
            Boolean up = ServletRequestUtils.getBooleanParameter(request,"isUp");
            String subnetMask = ServletRequestUtils.getStringParameter(request,"subnetMask");
            String broadCast = ServletRequestUtils.getStringParameter(request,"broadCast");
            NetInfo netInfo = new NetInfo(interfaceName,ip,up, subnetMask,broadCast);
            NetworkUtil networkUtil = new NetworkUtil();
            msg = networkUtil.saveInterface(netInfo);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "接口管理", "用户新增虚拟接口信息成功 ");
        } catch (Exception e) {
            logger.error("接口管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "接口管理", "用户新增虚拟接口信息不成功 ");
            msg = "新增虚拟接口信息失败";
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
     *  删除虚拟接口
     *
     */
    public ActionForward deleteInterface(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String json = null;
        String msg = null;
        try {
            String interfaceName = ServletRequestUtils.getStringParameter(request,"interfaceName");
            NetworkUtil networkUtil = new NetworkUtil();
            msg = networkUtil.deleteInterface(interfaceName);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "接口管理", "用户删除虚拟接口成功 ");
        } catch (Exception e) {
            logger.error("接口管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "接口管理", "用户删除虚拟接口不成功 ");
            msg = "删除虚拟接口失败";
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
     *  修改接口信息
     *
     */
    public ActionForward updateInterface(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String json = null;
        String msg = null;
        try {
            String interfaceName = ServletRequestUtils.getStringParameter(request,"interfaceName");
            String encap = ServletRequestUtils.getStringParameter(request,"encap");
            String ip = ServletRequestUtils.getStringParameter(request,"ip");
            Boolean isUp = ServletRequestUtils.getBooleanParameter(request,"isUp");
            Boolean isUpOlder = ServletRequestUtils.getBooleanParameter(request,"isUpOlder");
            String gateway = ServletRequestUtils.getStringParameter(request,"gateway");
            String subnetMask = ServletRequestUtils.getStringParameter(request,"subnetMask");
            String broadCast = ServletRequestUtils.getStringParameter(request,"broadCast");
            NetInfo netInfo = new NetInfo(interfaceName,encap,ip,isUp,gateway,subnetMask,broadCast);
            NetworkUtil networkUtil = new NetworkUtil();
            msg = networkUtil.updateInterface(netInfo,isUpOlder);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "接口管理", "用户修改接口信息成功 ");
        } catch (Exception e) {
            logger.error("接口管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "接口管理", "用户修改接口信息不成功 ");
            msg = "修改接口信息失败";
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
     *  修改虚拟接口信息
     *
     */
    public ActionForward updateXNInterface(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String json = null;
        String msg = null;
        try {
            String interfaceName = ServletRequestUtils.getStringParameter(request,"interfaceName");
            String encap = ServletRequestUtils.getStringParameter(request,"encap");
            String ip = ServletRequestUtils.getStringParameter(request,"ip");
            Boolean isUp = ServletRequestUtils.getBooleanParameter(request,"isUp");
            Boolean isUpOlder = ServletRequestUtils.getBooleanParameter(request,"isUpOlder");
            String gateway = ServletRequestUtils.getStringParameter(request,"gateway");
            String subnetMask = ServletRequestUtils.getStringParameter(request,"subnetMask");
            String broadCast = ServletRequestUtils.getStringParameter(request,"broadCast");
            NetInfo netInfo = new NetInfo(interfaceName,encap,ip,isUp,gateway,subnetMask,broadCast);
            NetworkUtil networkUtil = new NetworkUtil();
            msg = networkUtil.updateInterface(netInfo,isUpOlder);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "接口管理", "用户修改虚拟接口信息成功 ");
        } catch (Exception e) {
            logger.error("接口管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "接口管理", "用户修改虚拟接口信息不成功 ");
            msg = "修改虚拟接口信息失败";
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
     *  修改DNS信息
     *
     */
    public ActionForward updateDNS(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String json = null;
        String msg = null;
        try {
            String interfaceName = ServletRequestUtils.getStringParameter(request,"interfaceName");
            String dns_1 = ServletRequestUtils.getStringParameter(request,"dns_1");
            String dns_2 = ServletRequestUtils.getStringParameter(request,"dns_2");
            NetInfo netInfo = new NetInfo(interfaceName,dns_1,dns_2);
            NetworkUtil networkUtil = new NetworkUtil();
            msg = networkUtil.updateDNS(netInfo);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "接口管理", "用户修改DNS信息成功 ");
        } catch (Exception e) {
            logger.error("接口管理", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "接口管理", "用户修改DNS信息不成功 ");
            msg = "修改DNS信息失败";
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
     * ping地址段测试
     *
     */

    public ActionForward addressPing(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        threadmap = new HashMap();
        ipmap = new HashMap();
        String json = null;
        String msg = null;
        String[] end;
        String[] start;
        String ipstart;
        String ipend;
        String relativeip;
        try {
            logger.info("进入方法--------------------------------------------");
            ipstart = ServletRequestUtils.getStringParameter(request, "ipstart");
            ipend = ServletRequestUtils.getStringParameter(request, "ipend");
            logger.info("Ip分别为" + ipstart + ipend + "-------------------------------");
            start = ipstart.split("\\.");
            end = ipend.split("\\.");
            relativeip = start[0] + "." + start[1] + "." + start[2] + ".";
            int packagenum = 0;
            threadnum=0;
            //设置一个线程队列       大小为10
            BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);
            //创建线程池 初试线程为 3个 最大运行线程数为 10(9+1) 5S过后池内线程不执行则关闭线   等待队列为10个
            ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 9, 5, TimeUnit.SECONDS, queue, new ThreadPoolExecutor.CallerRunsPolicy());
            for (int i = Integer.parseInt(start[3]); i <= Integer.parseInt(end[3]); i++) {
                packagenum++;
                if (packagenum == 5) {
                    threadnum++;
                    PingIp pingIp = new PingIp();
                    pingIp.init(relativeip +i,5, relativeip);
                    Thread pingthread = new Thread(pingIp,threadnum+"线程");
                    executor.execute(pingthread);
                    packagenum = 0;
                } else if (i == Integer.parseInt(end[3])) {
                    threadnum++;
                    logger.info("进入最后一批ping的地址");
                    PingIp pingIp = new PingIp();
                    pingIp.init(relativeip +i, packagenum,relativeip);
                    Thread pingthread = new Thread(pingIp,threadnum+"线程");
                    executor.execute(pingthread);
                }
            }
            logger.info("联通成功");
            //关闭线程池
            executor.shutdown();
        } catch (Exception e) {
            logger.error("连通测试", e);
            logger.error("连通测试, 用户ping不成功 ");
            return null;
        }
        json = "{success:true,msg:'"+msg+"'}";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.close();
        return null;
    }

    //判断搜寻的设备是否已经存在 标明后返回界面
    public ActionForward findPingSuccessIp(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response) throws IOException {
        String str ="";
        String json = "";
        try {
            int start =Integer.parseInt(ServletRequestUtils.getStringParameter(request,"start")) ;
            int limit =Integer.parseInt(ServletRequestUtils.getStringParameter(request,"limit")) ;
            logger.info(start);
            int num =0;
            str = "{totalProperty:" +ipmap.size() + ",root:[";
            Set<Map.Entry> set = ipmap.entrySet();
            for (Iterator<Map.Entry> it = set.iterator(); it.hasNext();) {
                Map.Entry entry =  it.next();
                if(entry.getValue().equals("true")){
                    num++;
                    if (start < num && num <= (start + limit)) {
                        boolean flag = runMonitorService.isEquExist((String)entry.getKey());
                        str+= "{ip:'"+entry.getKey()+"',flag:'"+flag+"'},";
                    }
                }
            }
            str +="]}";
            logger.info(str);
            json = str.toString();

        } catch (ServletRequestBindingException e) {
            logger.info(e);
            json = "";
        } catch (Exception e) {
            logger.info(e);
        }
                 logger.info("查找设备返回Json为"+json);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.close();
        return null;
    }

     //判断线程是否结束 结束则停止界面自动刷新
    public ActionForward isMapFlag(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String json;
        String msg ="";
        logger.info(threadnum+"   线程数量-------------------------------------------------");
        logger.info(threadmap.size()+"   Map数量--------------------------------------------");
        if(threadnum==threadmap.size()){
            msg="true";
        }   else{
            msg="false";
        }
        json = msg;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.close();
        return null ;
    }


}
