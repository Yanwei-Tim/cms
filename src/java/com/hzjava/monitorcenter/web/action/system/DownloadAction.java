package com.hzjava.monitorcenter.web.action.system;

import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.utils.FileUtils;
import com.hzjava.monitorcenter.utils.StringTrim;
import com.hzjava.monitorcenter.web.SessionUtils;
import com.inetec.common.client.ECommonUtilFactory;
import com.inetec.common.client.IECommonUtil;
import com.inetec.common.client.util.LogBean;
import com.inetec.common.client.util.XChange;
import com.inetec.common.util.OSInfo;
import com.inetec.common.util.OSReBoot;
import com.inetec.common.util.OSShutDown;
import com.inetec.common.util.Proc;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.StringTokenizer;

/**
 * 日志下载
 *
 */
public class DownloadAction extends DispatchActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DownloadAction.class);

	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

    /**
     * 读取cms的日志列表
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws java.io.IOException
     */
	public ActionForward readLocalLogName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
        String json = null;
        try {
            json = FileUtils.readFileNames(System.getProperty("monitor.home")+"/logs");
            logService.newLog("INFO", SessionUtils.getAccount(request)
                    .getUserName(), "日志下载", " 用户获取所有本地日志名称、大小信息成功");
        } catch (Exception e) {
            logger.error("日志下载", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
                    .getUserName(), "日志下载", " 用户获取所有本地日志名称、大小信息失败");
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
     * 下载
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ActionForward download(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        String json = "{success:false}";
        try {
            String logName = ServletRequestUtils.getStringParameter(request, "fileName");
            String type = ServletRequestUtils.getStringParameter(request,"type");
            if(logName!=null){
			    logger.info("下载:" + logName+"开始");
                String Agent = request.getHeader("User-Agent");
                StringTokenizer st = new StringTokenizer(Agent,";");
                st.nextToken();
                //得到用户的浏览器名  MSIE  Firefox
                String userBrowser = st.nextToken();
                String path = null;
                if ("internal_log".equals(type)) {
                    path = System.getProperty("monitor.home")+"/logs/" + logName.split("\\(")[0];
                    File source = new File(path);
                    String name = source.getName();
//                FileUtils.downType(response,name,userBrowser);
//                response = FileUtils.copy(source, response);
                    FileInputStream is = new FileInputStream(source);
                    response.setContentType("application/octet-stream");
                    response.addHeader("Content-Disposition", "attachment;filename="
                            + new String((path == null ? "file" : (String) path)
                            .getBytes("gb2312"), "ISO8859_1"));
                    OutputStream out = response.getOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = is.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    out.flush();
                    out.close();
                    logger.info("下载" + logName.split("\\(")[0] + "成功!");
                    logService.newLog("INFO", SessionUtils.getAccount(request)
                            .getUserName(), "日志下载"," 用户下载日志成功");
                    json = "{success:true}";
                }
            }
        } catch (Exception e) {
            logger.error("日志下载", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
                    .getUserName(), "日志下载", "用户下载日志失败");
        }

//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html");
//		PrintWriter writer = response.getWriter();
//		writer.println(json);
//		writer.close();
		return null;
	}
    public ActionForward deleteLog(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       String filename = ServletRequestUtils.getStringParameter(request, "filename");
       String s= System.getProperty("monitor.home")+"/logs";
        if(filename==null){
            if(emptyFile(s)){
                PrintWriter writer = response.getWriter();
                writer.println("{success:true,msg:'清空文件成功'}");
                writer.close();

            }else {
                PrintWriter writer = response.getWriter();
                writer.println("{success:true,msg:'清空文件失败'}");
                writer.close();
            }
        }else {
            String fn= filename.substring(0,filename.indexOf(".log"))+".log";
            File temp = new File(s+File.separator+fn);
            if (temp.isFile()) {
                FileWriter fw =  new FileWriter(temp);
                fw.write("");
                fw.close();
            }
            PrintWriter writer = response.getWriter();
            writer.println("{success:true,msg:'清空文件成功'}");
            writer.close();
        }

        return null;
    }

    // 清空文件
// param path 文件夹完整绝对路径
    public static boolean emptyFile(String path) throws IOException {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                FileWriter fw =  new FileWriter(temp);
                fw.write("");
                fw.close();
                flag=true;
            }

        }
        return flag;
    }

}
