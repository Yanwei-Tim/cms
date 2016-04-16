package com.hzjava.monitorcenter.web.action;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzjava.monitorcenter.utils.JsonUtil;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.struts.DispatchActionSupport;

import com.hzjava.monitorcenter.constant.AppConstant;
import com.hzjava.monitorcenter.service.BackUpThread;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.web.SessionUtils;
import com.hzjava.monitorcenter.web.SiteContext;

/**
 * 审计备份策略
 * 
 * @author collin.code@gmail.com
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/backupConf" validate="false"
 *                parameter="m"
 * @struts.action-forward name="detail"
 *                        path="/WEB-INF/pages/conf/backupDetailJSON.jsp"
 */
public class BackupConfAction extends BaseAction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(BackupConfAction.class);

	private LogService logService;

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SAXReader reader = new SAXReader();
		String fileName = SiteContext.getInstance().contextRealPath
				+ AppConstant.BACKUP_CONFIG_PATH;
		Document doc = reader.read(new File(fileName));

		Map model = new HashMap();
		model.put("conf_type", doc.selectSingleNode("//backup/conf_type")
				.getText());
		model.put("conf_time", doc.selectSingleNode("//backup/conf_time")
				.getText());
		model.put("conf_day", doc.selectSingleNode("//backup/conf_day")
				.getText());
		model.put("conf_time2", doc.selectSingleNode("//backup/conf_time2")
				.getText());
		model.put("conf_month_day", doc.selectSingleNode(
				"//backup/conf_month_day").getText());
		model.put("conf_time3", doc.selectSingleNode("//backup/conf_time3")
				.getText());
		model.put("log_type", doc.selectSingleNode("//backup/log_type")
				.getText());
		model.put("log_time", doc.selectSingleNode("//backup/log_time")
				.getText());
		model.put("log_day", doc.selectSingleNode("//backup/log_day")
						.getText());
		model.put("log_time2", doc.selectSingleNode("//backup/log_time2")
				.getText());
		model.put("log_month_day", doc.selectSingleNode("//backup/log_month_day").getText());
		model.put("log_time3", doc.selectSingleNode("//backup/log_time3").getText());

		model.put("conf_file_path", doc.selectSingleNode(
				"//backup/conf_file_path").getText());
		model.put("conf_enabled", doc.selectSingleNode("//backup/conf_enabled")
				.getText());

		request.setAttribute("model", model);

		return mapping.findForward("detail");
	}
   /* public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SAXReader reader = new SAXReader();
		String fileName = SiteContext.getInstance().contextRealPath
				+ AppConstant.BACKUP_CONFIG_PATH;
		Document doc = reader.read(new File(fileName));

		Map model = new HashMap();
		model.put("conf_type", doc.selectSingleNode("//backup/conf_type")
				.getText());
		model.put("conf_time", doc.selectSingleNode("//backup/conf_time")
				.getText());
		model.put("conf_day", doc.selectSingleNode("//backup/conf_day")
                .getText());
		model.put("conf_time2", doc.selectSingleNode("//backup/conf_time2")
				.getText());
		model.put("conf_month_day", doc.selectSingleNode(
				"//backup/conf_month_day").getText());
		model.put("conf_time3", doc.selectSingleNode("//backup/conf_time3")
				.getText());
		model.put("log_type", doc.selectSingleNode("//backup/log_type")
                .getText());
		model.put("log_time", doc.selectSingleNode("//backup/log_time")
				.getText());
		model.put("log_day", doc.selectSingleNode("//backup/log_day")
						.getText());
		model.put("log_time2", doc.selectSingleNode("//backup/log_time2")
				.getText());
		model.put("log_month_day", doc.selectSingleNode("//backup/log_month_day").getText());
		model.put("log_time3", doc.selectSingleNode("//backup/log_time3").getText());

		model.put("conf_file_path", doc.selectSingleNode(
				"//backup/conf_file_path").getText());
		model.put("conf_enabled", doc.selectSingleNode("//backup/conf_enabled")
				.getText());

		request.setAttribute("model", model);
        JsonUtil jsonUtil = new JsonUtil();
        jsonUtil.addState(Boolean.TRUE);
        jsonUtil.addResult(model);

        String html = jsonUtil.toString();
        logger.debug(html);
        sendHTML(response, html);
        return null;
	}*/

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SAXReader reader = new SAXReader();
		String fileName = SiteContext.getInstance().contextRealPath
				+ AppConstant.BACKUP_CONFIG_PATH;
		Document doc = reader.read(new File(fileName));

		XMLWriter writer = null;
		try {
			Node tempNode = doc.selectSingleNode("//backup/conf_type");
			tempNode.setText(request.getParameter("conf_type"));
			tempNode = doc.selectSingleNode("//backup/conf_time");
			tempNode.setText(request.getParameter("conf_time"));
			tempNode = doc.selectSingleNode("//backup/conf_day");
			tempNode.setText(request.getParameter("conf_day"));
			tempNode = doc.selectSingleNode("//backup/conf_time2");
			tempNode.setText(request.getParameter("conf_time2"));
			tempNode = doc.selectSingleNode("//backup/conf_month_day");
			tempNode.setText(request.getParameter("conf_month_day"));
			tempNode = doc.selectSingleNode("//backup/conf_time3");
			tempNode.setText(request.getParameter("conf_time3"));
			tempNode = doc.selectSingleNode("//backup/conf_file_path");
			tempNode.setText(request.getParameter("conf_file_path"));
			tempNode = doc.selectSingleNode("//backup/conf_enabled");
			tempNode.setText(ServletRequestUtils.getStringParameter(request,
					"conf_enabled", "0"));

			tempNode = doc.selectSingleNode("//backup/log_type");
			tempNode.setText(request.getParameter("log_type"));
			tempNode = doc.selectSingleNode("//backup/log_time");
			tempNode.setText(request.getParameter("log_time"));
			tempNode = doc.selectSingleNode("//backup/log_day");
			tempNode.setText(request.getParameter("log_day"));
			tempNode = doc.selectSingleNode("//backup/log_time2");
			tempNode.setText(request.getParameter("log_time2"));
			tempNode = doc.selectSingleNode("//backup/log_month_day");
			tempNode.setText(request.getParameter("log_month_day"));
			tempNode = doc.selectSingleNode("//backup/log_time3");
			tempNode.setText(request.getParameter("log_time3"));

			writer = new XMLWriter(new FileWriter(fileName));// 构建输出流

			writer.write(doc);// 输出XML

			BackUpThread backupThread = (BackUpThread) WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext())
					.getBean("backupThread");
			backupThread.updateDoc(doc, null);

			logService.newLog("INFO", SessionUtils.getAccount(request)
					.getUserName(), "审计备份策略", "审计备份策略配置成功 ");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "审计备份策略", "审计备份策略配置不成功 ");
		} finally {
			writer.close();
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter pwriter = response.getWriter();
		pwriter.println("{success:true}");
		pwriter.close();

		return null;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

}
