package com.hzjava.monitorcenter.web.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.FileOutputStream;

import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzjava.monitorcenter.domain.Role;
import com.inetec.common.util.OSInfo;
import com.inetec.common.util.Proc;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.web.bind.ServletRequestUtils;

import cn.collin.commons.domain.PageResult;
import cn.collin.commons.utils.DateUtils;

import com.hzjava.monitorcenter.constant.AppConstant;
import com.hzjava.monitorcenter.constant.ServiceConstant;
import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.service.AlertService;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.utils.JavaMailUtil;
import com.hzjava.monitorcenter.utils.JsonUtil;
import com.hzjava.monitorcenter.utils.StringUtils;
import com.hzjava.monitorcenter.web.SessionUtils;
import com.hzjava.monitorcenter.web.SiteContext;

/**
 * @author xiangqi.java@gmail.com
 * @struts.action path="/alert" scope="request" parameter="m"
 */
public class AlertAdminAction extends BaseAction {
	private static Logger logger = Logger.getLogger(AlertAdminAction.class);

	public ActionForm checkLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		Account account = SessionUtils.getAccount(request);
		String msg = "logout";
		if(account != null){
			msg = "login";
		}
		String path = request.getContextPath();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true,msg:'"+msg+"',path:'"+path+"'}");
		writer.close();
		return null;
	}
	
	public ActionForward bscode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AlertService as = (AlertService) getWebApplicationContext().getBean(
				ServiceConstant.ALERT_SERVICE);

		List list = as.listBsAlertType();

		Map<Object, Object> model = new HashMap<Object, Object>();

		model.put("data", list);

		JsonUtil jsonUtil = new JsonUtil();
		jsonUtil.addState(Boolean.TRUE);
		jsonUtil.addResult(model);

		String html = jsonUtil.toString();
		logger.debug(html);
		sendHTML(response, html);

		return null;
	}

	public ActionForward bsalert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AlertService as = (AlertService) getWebApplicationContext().getBean(
				ServiceConstant.ALERT_SERVICE);

		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int pageLength = ServletRequestUtils.getIntParameter(request, "limit",
				AppConstant.PAGERESULT_PAGE_LENGTH);
		int pageIndex = start / pageLength + 1;
		String startDateStr = ServletRequestUtils.getStringParameter(request,
				"startDate");
		String endDateStr = ServletRequestUtils.getStringParameter(request,
				"endDate");

		Date startDate = StringUtils.isBlank(startDateStr) ? null : DateUtils
				.parse(startDateStr, "yyyy-MM-dd");
		Date endDate = StringUtils.isBlank(endDateStr) ? null : DateUtils
				.parse(endDateStr, "yyyy-MM-dd");
		String businessName = ServletRequestUtils.getStringParameter(request,
				"businessName");
		String alertCode = ServletRequestUtils.getStringParameter(request,
				"alertCode");

		PageResult ps = as.listBsExpAlert(pageIndex, pageLength, startDate,
				endDate, businessName, alertCode);

		Map<Object, Object> model = new HashMap<Object, Object>();
		model.put("total", ps.getAllResultsAmount());

		model.put("data", ps.getResults());

		JsonUtil jsonUtil = new JsonUtil();
		jsonUtil.addState(Boolean.TRUE);
		jsonUtil.addResult(model);

		String html = jsonUtil.toString();
		logger.debug(html);
		sendHTML(response, html);

		return null;
	}

	public ActionForward sccode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AlertService as = (AlertService) getWebApplicationContext().getBean(
				ServiceConstant.ALERT_SERVICE);

		List list = as.listScAlertType();

		Map<Object, Object> model = new HashMap<Object, Object>();

		model.put("data", list);

		JsonUtil jsonUtil = new JsonUtil();
		jsonUtil.addState(Boolean.TRUE);
		jsonUtil.addResult(model);

		String html = jsonUtil.toString();
		logger.debug(html);
		sendHTML(response, html);

		return null;
	}

	public ActionForward scalert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AlertService as = (AlertService) getWebApplicationContext().getBean(
				ServiceConstant.ALERT_SERVICE);

		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int pageLength = ServletRequestUtils.getIntParameter(request, "limit",
				AppConstant.PAGERESULT_PAGE_LENGTH);
		int pageIndex = start / pageLength + 1;
		String startDateStr = ServletRequestUtils.getStringParameter(request,
				"startDate");
		String endDateStr = ServletRequestUtils.getStringParameter(request,
				"endDate");

		Date startDate = StringUtils.isBlank(startDateStr) ? null : DateUtils
				.parse(startDateStr, "yyyy-MM-dd");
		Date endDate = StringUtils.isBlank(endDateStr) ? null : DateUtils
				.parse(endDateStr, "yyyy-MM-dd");
		String equName = ServletRequestUtils.getStringParameter(request,
				"equName");
		String alertCode = ServletRequestUtils.getStringParameter(request,
				"alertCode");

		PageResult ps = as.listScEventAlert(pageIndex, pageLength, startDate,
				endDate, equName, alertCode);

		Map<Object, Object> model = new HashMap<Object, Object>();
		model.put("total", ps.getAllResultsAmount());

		model.put("data", ps.getResults());

		JsonUtil jsonUtil = new JsonUtil();
		jsonUtil.addState(Boolean.TRUE);
		jsonUtil.addResult(model);

		String html = jsonUtil.toString();
		logger.debug(html);
		sendHTML(response, html);

		return null;
	}

	public ActionForward eqalert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AlertService as = (AlertService) getWebApplicationContext().getBean(
				ServiceConstant.ALERT_SERVICE);

		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int pageLength = ServletRequestUtils.getIntParameter(request, "limit",
				AppConstant.PAGERESULT_PAGE_LENGTH);
		int pageIndex = start / pageLength + 1;
		String startDateStr = ServletRequestUtils.getStringParameter(request,
				"startDate");
		String endDateStr = ServletRequestUtils.getStringParameter(request,
				"endDate");

		Date startDate = StringUtils.isBlank(startDateStr) ? null : DateUtils
				.parse(startDateStr, "yyyy-MM-dd");
		Date endDate = StringUtils.isBlank(endDateStr) ? null : DateUtils
				.parse(endDateStr, "yyyy-MM-dd");
		String equName = ServletRequestUtils.getStringParameter(request,
				"equName");

		PageResult ps = as.listEquAlert(pageIndex, pageLength, startDate,
				endDate, equName);

		Map<Object, Object> model = new HashMap<Object, Object>();
		model.put("total", ps.getAllResultsAmount());

		model.put("data", ps.getResults());

		JsonUtil jsonUtil = new JsonUtil();
		jsonUtil.addState(Boolean.TRUE);
		jsonUtil.addResult(model);

		String html = jsonUtil.toString();
		logger.debug(html);
		sendHTML(response, html);

		return null;
	}

	public ActionForward loadconfig(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SAXReader reader = new SAXReader();
		String fileName = SiteContext.getInstance().contextRealPath
				+ AppConstant.XML_ALERT_CONFIG_PATH;
		Document document = reader.read(new File(fileName));

		Map<Object, Object> model = new HashMap<Object, Object>();
		Node tempNode = document.selectSingleNode("//config/mailconfig/server");
		model.put(tempNode.getName(), tempNode.getText());
		tempNode = document.selectSingleNode("//config/mailconfig/port");
		model.put(tempNode.getName(), tempNode.getText());
		tempNode = document.selectSingleNode("//config/mailconfig/email");
		model.put(tempNode.getName(), tempNode.getText());
		tempNode = document.selectSingleNode("//config/mailconfig/account");
		model.put(tempNode.getName(), tempNode.getText());
		tempNode = document.selectSingleNode("//config/mailconfig/password");
		model.put(tempNode.getName(), tempNode.getText());
		tempNode = document.selectSingleNode("//config/mailconfig/title");
		model.put(tempNode.getName(), tempNode.getText());
		tempNode = document
				.selectSingleNode("//config/mailconfig/mailfrequency");
		model.put(tempNode.getName(), tempNode.getText());
		tempNode = document.selectSingleNode("//config/smsconfig/smsnumber");
		model.put(tempNode.getName(), tempNode.getText());
		tempNode = document.selectSingleNode("//config/smsconfig/smstitle");
		model.put(tempNode.getName(), tempNode.getText());
		tempNode = document.selectSingleNode("//config/smsconfig/smsfrequency");
		model.put(tempNode.getName(), tempNode.getText());

		JsonUtil jsonUtil = new JsonUtil();
		jsonUtil.addState(Boolean.TRUE);
		jsonUtil.addResult(model);

		String html = jsonUtil.toString();
		logger.debug(html);
		sendHTML(response, html);
		return null;
	}

	public ActionForward saveconfig(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SAXReader reader = new SAXReader();
		String fileName = SiteContext.getInstance().contextRealPath
				+ AppConstant.XML_ALERT_CONFIG_PATH;
		Document document = reader.read(new File(fileName));
		document.setXMLEncoding("UTF-8");
		Element root = document.getRootElement();
		/*author:crj
		 * date:2012-03-18
		 * function:编码处理，原来不可以保存中文的
		 * 
		 */
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		FileOutputStream fos = new FileOutputStream(new File(fileName));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos,
				"utf-8"));
		XMLWriter writer = new XMLWriter(bw, format);

		// XMLWriter writer = null;
		try {
			Node tempNode = document
					.selectSingleNode("//config/mailconfig/server");
			tempNode.setText(request.getParameter("server"));
			tempNode = document.selectSingleNode("//config/mailconfig/port");
			tempNode.setText(request.getParameter("port"));
			tempNode = document.selectSingleNode("//config/mailconfig/email");
			tempNode.setText(request.getParameter("email"));
			tempNode = document.selectSingleNode("//config/mailconfig/account");
			tempNode.setText(request.getParameter("account"));
			tempNode = document
					.selectSingleNode("//config/mailconfig/password");
			tempNode.setText(request.getParameter("password"));
			tempNode = document.selectSingleNode("//config/mailconfig/title");
			tempNode.setText(request.getParameter("title"));
			tempNode = document
					.selectSingleNode("//config/mailconfig/mailfrequency");
			tempNode.setText(request.getParameter("mailfrequency"));
			tempNode = document
					.selectSingleNode("//config/smsconfig/smsnumber");
			tempNode.setText(request.getParameter("smsnumber"));
			tempNode = document.selectSingleNode("//config/smsconfig/smstitle");
			tempNode.setText(request.getParameter("smstitle"));
			tempNode = document
					.selectSingleNode("//config/smsconfig/smsfrequency");
			tempNode.setText(request.getParameter("smsfrequency"));

			// writer = new XMLWriter(new FileWriter(fileName));
			writer.write(document);
			writer.flush();
			logger.info("write file success:" + fileName);
			writer.close();

			LogService logService = (LogService) getWebApplicationContext()
					.getBean(ServiceConstant.LOG_SERVICE);
			logService.newLog("INFO", SessionUtils.getAccount(request)
					.getUserName(), " 报警配置 ", " 保存配置成功 ");
		} catch (Exception e) {
			logger.error(e.getMessage());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", Boolean.FALSE);

			String html = jsonObject.toString();
			logger.debug(html);
			sendHTML(response, html);

			LogService logService = (LogService) getWebApplicationContext()
					.getBean(ServiceConstant.LOG_SERVICE);
			logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), " 报警配置 ", " 保存配置不成功 ");
			return null;
		} finally {
			writer.close();
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", Boolean.TRUE);

		String html = jsonObject.toString();
		logger.debug(html);
		sendHTML(response, html);
		return null;
	}

	public ActionForward validatemail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String subject = " 测试邮件 ";
		String text = " 测试邮件 ";
		String contact = request.getParameter("contact");

		SAXReader reader = new SAXReader();
		String fileName = SiteContext.getInstance().contextRealPath
				+ AppConstant.XML_ALERT_CONFIG_PATH;
		Document document = reader.read(new File(fileName));

		Map<String, String> model = new HashMap<String, String>();
		Node tempNode = document.selectSingleNode("//config/mailconfig/server");
		model.put(tempNode.getName(), tempNode.getText());
		tempNode = document.selectSingleNode("//config/mailconfig/port");
		model.put(tempNode.getName(), tempNode.getText());
		tempNode = document.selectSingleNode("//config/mailconfig/email");
		model.put(tempNode.getName(), tempNode.getText());
		tempNode = document.selectSingleNode("//config/mailconfig/account");
		model.put(tempNode.getName(), tempNode.getText());
		tempNode = document.selectSingleNode("//config/mailconfig/password");
		model.put(tempNode.getName(), tempNode.getText());
		tempNode = document.selectSingleNode("//config/mailconfig/title");
		model.put(tempNode.getName(), tempNode.getText());

		tempNode = document.selectSingleNode("//config/smsconfig/smsnumber");
		model.put(tempNode.getName(), tempNode.getText());
		tempNode = document.selectSingleNode("//config/smsconfig/smstitle");
		model.put(tempNode.getName(), tempNode.getText());

		logger.info(" 发送测试邮件...To: " + contact);
		try {
			JavaMailUtil.sendMail(model, subject, text, contact);
		} catch (Exception e) {
			logger.error(e.getMessage());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("success", Boolean.FALSE);

			String html = jsonObject.toString();
			logger.debug(html);
			sendHTML(response, html);
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", Boolean.TRUE);

		String html = jsonObject.toString();
		logger.debug(html);
		sendHTML(response, html);
		return null;
	}

	/*
	 * 定时刷新接口，看是否有报警信息
	 */
	public ActionForward refreshAlerts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AlertService as = (AlertService) getWebApplicationContext().getBean(
				ServiceConstant.ALERT_SERVICE);

        String html = null;
        try{
            JSONObject jsonObject = as.getAlerts();
            jsonObject.put("time", DateUtils.format(new Date(),
                    "yyyy年MM月dd日 HH:mm:ss"));
            html = jsonObject.toString();
        } catch (Exception e){
            logger.error("定时刷新接口:"+e.getMessage());
            html = "{success:true,device:0,business:0,security:0}";
        }
        sendHTML(response, html);
        // logger.debug(html);
		return null;
	}

	/*
	 * 定时刷新接口，看是否有审计库报警信息
	 */
	public ActionForward refreshDiskUseAlerts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        AlertService as = (AlertService) getWebApplicationContext().getBean(
        				ServiceConstant.ALERT_SERVICE);
        String diskMsg = "审计库所在磁盘空间充足";
        int alert = 0;
        int dbUsed = 0;
        Date date = new Date();
        String  time = DateUtils.format(date, "yyyy年MM月dd日 HH:mm:ss");
        try{
            Account account = SessionUtils.getAccount(request);
            Set<Role> roles = account.getRoles();
            Iterator<Role> iterator = roles.iterator();
            Role role = null;
            while (iterator.hasNext()){
                role = iterator.next();
                break;
            }
            if(role.getName().equals("审计管理员")||role.getName().indexOf("审计")>-1){
                SAXReader reader = new SAXReader();
                String fileName = SiteContext.getInstance().contextRealPath
                        + AppConstant.XML_DB_CONFIG_PATH;
                Document doc = reader.read(new File(fileName));
                dbUsed = Integer.parseInt(doc.selectSingleNode("//config/maindb/dbused").getText()); //审计库容量告警%值
                int diskUsed = getDataBaseAtDisk(doc);            //审计库所在磁盘使用容量(%)
                if(diskUsed >= dbUsed){
                    diskMsg = "审计库总容量达到警戒容量";
                    alert = 1;
                    as.newLog(account,"审计库管理",diskMsg,date);
                }
            }else{
                alert = 0;
            }
        } catch (Exception e){
            logger.error("定时刷新接口:"+e.getMessage());
            alert = 0;
        }
        String html = "{success:true,dbUsed:"+dbUsed+",time:'"+time+"',diskMsg:'"+diskMsg+"',alert:"+alert+"}";
        sendHTML(response, html);
        // logger.debug(html);
		return null;
	}

    private int getDataBaseAtDisk(Document doc) {
        String ip = doc.selectSingleNode("//config/maindb/dbip").getText();
        String port = doc.selectSingleNode("//config/maindb/dbport").getText();
        String dbName = "information_schema";
        String userName = doc.selectSingleNode("//config/maindb/username").getText();
        String password = doc.selectSingleNode("//config/maindb/password").getText();
        String dataDir = null;
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + ip
                    + ":" + port + "/"
                    + dbName
                    + "?useUnicode=true&characterEncoding=utf-8";
            conn = DriverManager.getConnection(url, userName, password);
            stat = conn.createStatement();
            rs = stat.executeQuery("select VARIABLE_VALUE from `GLOBAL_VARIABLES` where VARIABLE_NAME = 'datadir';");
            if (rs != null && rs.next()) {
                dataDir = rs.getString(1);
            }
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            try {
                if (null != rs) {
                    rs.close();
                    rs = null;
                }
                if (null != stat) {
                    stat.close();
                    stat = null;
                }
                if (null != conn) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {

            }
        }
        int used = 100;
        Proc proc;
        OSInfo osinfo = OSInfo.getOSInfo();
        if (osinfo.isWin()) {
        }
        if (osinfo.isLinux()) {
            proc = new Proc();
            proc.exec("df "+dataDir);
            String result = proc.getOutput();
            String[] lines = result.split("\n");
            String[] str = lines[1].split("\\s");
            for (int i = 0;i<str.length;i++){
                logger.info("审计磁盘开始1-----3"+str[i]);
                if(str[i].endsWith("%") ){
                    used = Integer.parseInt(str[i].split("%")[0]);
                }
            }
        }
        return used;
    }

    /*
      * 设置报警信息为已读
      */
	public ActionForward setReaded(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AlertService as = (AlertService) getWebApplicationContext().getBean(
				ServiceConstant.ALERT_SERVICE);
		String ids = ServletRequestUtils.getRequiredStringParameter(request,
				"ids");
		String domainName = ServletRequestUtils.getRequiredStringParameter(
				request, "domain");

		as.updateReadStatus(ids, domainName);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true}");
		writer.close();

		return null;
	}


}
