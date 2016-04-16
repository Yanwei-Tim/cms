package com.hzjava.monitorcenter.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.hzjava.monitorcenter.constant.AppConstant;
import com.hzjava.monitorcenter.web.SiteContext;

public class BackUpThread extends Thread {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BackUpThread.class);

	private boolean running = true;
	private Document doc = null;
	private Document dbDoc = null;
	private int lastConfBackUpExecutedDay;// 上一次配置备份任务执行的day number
	private int lastLogBackUpExecutedDay;// 上一次日志备份任务执行的day number

	public void setRunning(boolean running) {
		this.running = running;
	}

	// 读取配置信息，当配置被更新以后，需要调用该方法
	private void readDoc() {
		SAXReader reader = new SAXReader();
		String fileName = SiteContext.getInstance().contextRealPath
				+ AppConstant.BACKUP_CONFIG_PATH;
		String dbFile = SiteContext.getInstance().contextRealPath
				+ AppConstant.XML_DB_CONFIG_PATH;
		try {
			doc = reader.read(new File(fileName));
			dbDoc = reader.read(new File(dbFile));
		} catch (DocumentException e) {
			logger.error("读取配置信息出错", e);
		}
	}

	public void updateDoc(Document doc, Document dbDoc) {
		if (doc != null)
			this.doc = doc;
		if (dbDoc != null)
			this.dbDoc = dbDoc;
	}

	public void init() {
		this.start();
	}

	@Override
	public void run() {
		logger.debug("线程开始");
		try {
			this.sleep(600000);
		} catch (InterruptedException e1) {
		}
		logger.debug("读取配置文件");
		readDoc();
		logger.debug("进入循环");
		while (running) {
			String enabled = doc.selectSingleNode("//backup/conf_enabled")
					.getText();
			if (enabled.equalsIgnoreCase("1")) {
				int dayNumber = Calendar.getInstance()
						.get(Calendar.DAY_OF_YEAR);

				// 备份配置信息
				String confTypeStr = doc.selectSingleNode("//backup/conf_type")
						.getText();
				if (dayNumber != lastConfBackUpExecutedDay) {
					boolean runOnce = false;
					if (confTypeStr.equalsIgnoreCase("day")) {
						String strConfTime = doc.selectSingleNode(
								"//backup/conf_time").getText();
						String arr[] = strConfTime.split("[:]");
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arr[0]));
						cal.set(Calendar.MINUTE, Integer.parseInt(arr[1]));
						if (System.currentTimeMillis() > cal.getTimeInMillis()) {
							runOnce = true;
						}
					} else if (confTypeStr.equalsIgnoreCase("week")) {
						String strConfDay = doc.selectSingleNode(
								"//backup/conf_day").getText();
						String strConfTime = doc.selectSingleNode(
								"//backup/conf_time2").getText();
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.DAY_OF_WEEK, Integer
								.parseInt(strConfDay));
						String arr[] = strConfTime.split("[:]");
						cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arr[0]));
						cal.set(Calendar.MINUTE, Integer.parseInt(arr[1]));
						if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == cal
								.get(Calendar.DAY_OF_WEEK)
								&& System.currentTimeMillis() > cal
										.getTimeInMillis()) {
							runOnce = true;
						}
					} else if (confTypeStr.equalsIgnoreCase("month")) {
						String strConfDay = doc.selectSingleNode(
								"//backup/conf_month_day").getText();
						String strConfTime = doc.selectSingleNode(
								"//backup/conf_time3").getText();
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.DAY_OF_MONTH, Integer
								.parseInt(strConfDay));
						String arr[] = strConfTime.split("[:]");
						cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arr[0]));
						cal.set(Calendar.MINUTE, Integer.parseInt(arr[1]));
						if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == cal
								.get(Calendar.DAY_OF_MONTH)
								&& System.currentTimeMillis() > cal
										.getTimeInMillis()) {
							runOnce = true;
						}
					}
					if (runOnce) {
						logger.debug("调用备份逻辑开始");
						confBackUp();
						logger.debug("调用备份逻辑结束");
						lastConfBackUpExecutedDay = dayNumber;
					}
				}

				// 备份日志信息
				// TODO

			}

			try {
				this.sleep(600000);
			} catch (InterruptedException e) {

			}
		}
	}

	/**
	 * 配置信息备份
	 */
	private void confBackUp() {
		try {
			Calendar cal = Calendar.getInstance();
			String filePath = doc.selectSingleNode("//backup/conf_file_path")
					.getText();
			File dir = new File(filePath);
			if (!dir.exists()) {
				dir.mkdir();
			}
			File[] files = dir.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					if (name.indexOf(".sql") > 0) {
						return true;
					} else {
						return false;
					}
				}
			});
			if (files.length >= 20) {
				Arrays.sort(files, new Comparator<File>() {

					public int compare(File o1, File o2) {
						if (o1.lastModified() > o2.lastModified()) {
							return 1;
						} else {
							return -1;
						}
					}

				});
				files[0].deleteOnExit();
			}
			if (filePath == null || filePath.trim().length() == 0) {
				filePath = System.getProperty("user.dir");
			}
			filePath += System.getProperty("file.separator")
					+ cal.get(Calendar.YEAR) + "-"
					+ (cal.get(Calendar.MONTH) + 1) + "-"
					+ cal.get(Calendar.DAY_OF_MONTH) + ".sql";
			logger.debug("backup file path:" + filePath);
			String os = System.getProperty("os.name").toUpperCase();
			String[] cmd;
			logger.debug(os);
			String user = dbDoc.selectSingleNode("//config/maindb/username")
					.getText();
			String pass = dbDoc.selectSingleNode("//config/maindb/password")
					.getText();
			String dbname = dbDoc.selectSingleNode("//config/maindb/dbname")
					.getText();
			String host = dbDoc.selectSingleNode("//config/maindb/dbip")
					.getText();
			String param = "mysqldump -h"
					+ host
					+ " -u"
					+ user
					+ " -p"
					+ pass
					+ " --default-character-set=utf8 --set-charset "
					+ dbname
					+ " account account_role role permission role_permission business_code business_except_code business_exch_model business_register equipment equipment_type exchange_direction exchange_platform ext_link ext_link_property ext_link_type int_link jbqk monitor_agent platform_running_code protocol_type security_event_code terminal_auth_code use_depart_type sbpz business_hour_report business_month_report business_day_report equipment_hour_report equipment_day_report equipment_month_report";
			if (os.indexOf("WINDOWS") > -1) {
				cmd = new String[] { "cmd", "/c", param };
			} else {
				cmd = new String[] { "sh", "-c", param };
			}
			Process child = Runtime.getRuntime().exec(cmd);
			InputStream in = child.getInputStream();
			InputStreamReader xx = new InputStreamReader(in, "utf8");
			String inStr;
			StringBuffer sb = new StringBuffer("");
			//String outStr = "set names 'utf8';\r\n";
			BufferedReader br = new BufferedReader(xx);
			while ((inStr = br.readLine()) != null) {
				// logger.debug(inStr);
				sb.append(inStr + "\r\n");
			}
			child.waitFor();
			String outStr = sb.toString();
			FileOutputStream fout = new FileOutputStream(filePath);
			OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
			writer.write(outStr);
			writer.flush();
			in.close();
			xx.close();
			br.close();
			writer.close();
			fout.close();
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public static void main(String args[]) {
		new BackUpThread().confBackUp();
	}

}
