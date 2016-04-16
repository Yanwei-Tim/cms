package com.hzjava.monitorcenter.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inetec.common.util.OSInfo;
import com.inetec.common.util.Proc;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.struts.DispatchActionSupport;

import com.hzjava.monitorcenter.constant.AppConstant;
import com.hzjava.monitorcenter.service.BackUpThread;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.web.SessionUtils;
import com.hzjava.monitorcenter.web.SiteContext;

/**
 * 审计库管理
 * 
 * @author collin.code@gmail.com
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/logConf" validate="false" parameter="m"
 * @struts.action-forward name="detail"
 *                        path="/WEB-INF/pages/conf/logDetailJSON.jsp"
 * @struts.action-forward name="backFiles"
 *                        path="/WEB-INF/pages/conf/backFilesJSON.jsp"
 */
public class LogConfAction extends DispatchActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LogConfAction.class);
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SAXReader reader = new SAXReader();
		String fileName = SiteContext.getInstance().contextRealPath
				+ AppConstant.XML_DB_CONFIG_PATH;
		Document doc = reader.read(new File(fileName));

		Map model = new HashMap();
		model.put("dbip", doc.selectSingleNode("//config/maindb/dbip")
				.getText());
		model.put("dbport", doc.selectSingleNode("//config/maindb/dbport")
				.getText());
		model.put("dbname", doc.selectSingleNode("//config/maindb/dbname")
				.getText());
		model.put("username", doc.selectSingleNode("//config/maindb/username")
				.getText());
		model.put("password", doc.selectSingleNode("//config/maindb/password")
				.getText());
        model.put("dbUsed", Integer.parseInt(doc.selectSingleNode("//config/maindb/dbused")
                .getText()));
		model.put("backup_dbip", doc.selectSingleNode("//config/backupdb/dbip")
				.getText());
		model.put("backup_dbport", doc.selectSingleNode(
				"//config/backupdb/dbport").getText());
		model.put("backup_dbname", doc.selectSingleNode(
				"//config/backupdb/dbname").getText());
		model.put("backup_username", doc.selectSingleNode(
				"//config/backupdb/username").getText());
		model.put("backup_password", doc.selectSingleNode(
				"//config/backupdb/password").getText());

		request.setAttribute("model", model);

		return mapping.findForward("detail");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SAXReader reader = new SAXReader();
		String fileName = SiteContext.getInstance().contextRealPath
				+ AppConstant.XML_DB_CONFIG_PATH;
		Document doc = reader.read(new File(fileName));

		XMLWriter writer = null;
		try {
			Node tempNode = doc.selectSingleNode("//config/maindb/dbip");
			tempNode.setText(request.getParameter("dbip"));
			tempNode = doc.selectSingleNode("//config/maindb/dbport");
			tempNode.setText(request.getParameter("dbport"));
			tempNode = doc.selectSingleNode("//config/maindb/dbname");
			tempNode.setText(request.getParameter("dbname"));
			tempNode = doc.selectSingleNode("//config/maindb/username");
			tempNode.setText(request.getParameter("username"));
			tempNode = doc.selectSingleNode("//config/maindb/password");
			tempNode.setText(request.getParameter("password"));
            tempNode = doc.selectSingleNode("//config/maindb/dbused");
			tempNode.setText(request.getParameter("dbUsed"));

			tempNode = doc.selectSingleNode("//config/backupdb/dbip");
			tempNode.setText(request.getParameter("backup_dbip"));
			tempNode = doc.selectSingleNode("//config/backupdb/dbport");
			tempNode.setText(request.getParameter("backup_dbport"));
			tempNode = doc.selectSingleNode("//config/backupdb/dbname");
			tempNode.setText(request.getParameter("backup_dbname"));
			tempNode = doc.selectSingleNode("//config/backupdb/username");
			tempNode.setText(request.getParameter("backup_username"));
			tempNode = doc.selectSingleNode("//config/backupdb/password");
			tempNode.setText(request.getParameter("backup_password"));

			writer = new XMLWriter(new FileWriter(fileName));// 构建输出流

			writer.write(doc);// 输出XML

			BackUpThread backupThread = (BackUpThread) WebApplicationContextUtils
					.getWebApplicationContext(this.getServletContext())
					.getBean("backupThread");
			backupThread.updateDoc(null, doc);

			logService.newLog("INFO", SessionUtils.getAccount(request)
					.getUserName(), "审计库管理", "用户保存审计库信息成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "审计库管理", "用户保存配置不成功");
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

	/**
	 * 测试备份数据库
	 * 
	 */
	public ActionForward testBackupDB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String canConnect = "true";
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://" + request.getParameter("backup_dbip")
					+ ":" + request.getParameter("backup_dbport") + "/"
					+ request.getParameter("backup_dbname")
					+ "?useUnicode=true&characterEncoding=utf-8";
			String user = request.getParameter("backup_username");
			String password = request.getParameter("backup_password");

			conn = DriverManager.getConnection(url, user, password);
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from business_log limit 1");
			if (rs != null && rs.next()) {

			}
		} catch (Exception e) {
			canConnect = "false";
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
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:" + canConnect + "}");
        writer.close();
		return null;
	}

	/**
	 * 初始化数据库
	 * 
	 */
	public ActionForward createBizTables(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
        boolean result = true;

        try{
            SQLExec sqlExec = new SQLExec();
            sqlExec.setEncoding("UTF-8");
            //设置数据库参数
            sqlExec.setDriver("com.mysql.jdbc.Driver");
            sqlExec.setUrl("jdbc:mysql://" + request.getParameter("dbip") + ":"
                    + request.getParameter("dbport") + "/"
					+ request.getParameter("dbname") + "?useUnicode=true&characterEncoding=utf-8");
            sqlExec.setUserid(request.getParameter("username"));
            sqlExec.setPassword(request.getParameter("password"));
            String sqlPath = request.getRealPath("WEB-INF")+ "/createtables.sql";
            //要执行的脚本
            sqlExec.setSrc(new File(sqlPath));
            //有出错的语句该如何处理
            sqlExec.setOnerror((SQLExec.OnError)(EnumeratedAttribute.getInstance(
                    SQLExec.OnError.class, "abort")));

            sqlExec.setPrint(true); //设置是否输出

            //输出到文件 sql.out 中；不设置该属性，默认输出到控制台
            sqlExec.setOutput(new File(System.getProperty("monitor.home")+"/logs/sql_out.log"));
            sqlExec.setProject(new Project()); // 要指定这个属性，不然会出错
            sqlExec.execute();
            logger.info("初始化数据库成功!");
            logService.newLog("INFO", SessionUtils.getAccount(request)
					.getUserName(), "审计库管理", "用户初始化审计库信息成功");
        } catch (Exception e) {
            result = false;
            logger.error("审计库管理--初始化数据库,", e);
            logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "审计库管理", "用户初始化审计库信息失败");
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter pwriter = response.getWriter();
        pwriter.println("{success:" + result + "}");
        pwriter.close();
        Proc proc;
        OSInfo osinfo = OSInfo.getOSInfo();
        if (osinfo.isWin()) {
            proc = new Proc();
            proc.exec("nircmd service restart cms");
        }
        if (osinfo.isLinux()) {
            proc = new Proc();
            proc.exec("service cms restart");
        }
        return null;
	}

	/**
	 * 备份数据信息表导入
	 * 
	 */
	public ActionForward importConfTables(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			ServletRequestBindingException, DocumentException {
		String file = ServletRequestUtils.getRequiredStringParameter(request,"file");

		SAXReader reader = new SAXReader();
		String fileName = SiteContext.getInstance().contextRealPath
				+ AppConstant.BACKUP_CONFIG_PATH;
		String dbFile = SiteContext.getInstance().contextRealPath
				+ AppConstant.XML_DB_CONFIG_PATH;
		Document doc = reader.read(new File(fileName));
		Document dbDoc = reader.read(new File(dbFile));

		String filePath = doc.selectSingleNode("//backup/conf_file_path").getText();
		filePath += System.getProperty("file.separator") + file;
		logger.debug("backup file path:" + filePath);
		String os = System.getProperty("os.name").toUpperCase();
		logger.debug(os);
		String user = dbDoc.selectSingleNode("//config/maindb/username").getText();
		String pass = dbDoc.selectSingleNode("//config/maindb/password").getText();
		String dbname = dbDoc.selectSingleNode("//config/maindb/dbname").getText();
		String host = dbDoc.selectSingleNode("//config/maindb/dbip").getText();
		
		String param = "mysql -h" + host + " --default-character-set=utf8 -u"
				+ user + " -p" + pass + " " + dbname + " < " + filePath;
		String cmd[] = null;
		if (os.indexOf("WINDOWS") > -1) {
			cmd = new String[] { "cmd", "/c", param };
		} else {
			cmd = new String[] { "sh", "-c", param };
		}
		logger.debug("开始导入");
		// logger.debug(cmd);
		try {
            Process child = Runtime.getRuntime().exec(cmd);
            child.waitFor();
		} catch (InterruptedException e) {
			logger.error("导入失败", e);
		}
		logger.debug("导入结束");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter pwriter = response.getWriter();
		pwriter.println("{success:true}");
		pwriter.close();
		return null;
	}

	/**
	 * 显示备份的sql文件
	 */
	public ActionForward showSqlFiles(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, DocumentException {
		SAXReader reader = new SAXReader();
		String fileName = SiteContext.getInstance().contextRealPath
				+ AppConstant.BACKUP_CONFIG_PATH;
		Document doc = reader.read(new File(fileName));
		File dir = new File(doc.selectSingleNode("//backup/conf_file_path")	.getText());
		logger.debug(dir);
		File[] files = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				logger.debug(name);
				if (name.indexOf(".sql") > -1) {
					return true;
				} else {
					return false;
				}
			}
		});

		Map model = new HashMap();
		model.put("files", files);
		model.put("amount", files.length);
		request.setAttribute("model", model);

		return mapping.findForward("backFiles");
	}
}
