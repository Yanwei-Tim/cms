package com.hzjava.monitorcenter.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 *
 * @author xiangqi.java@gmail.com
 *
 * 2011-7-6 下午04:30:51
 */
public class WebUtil {
	private static Logger logger = Logger.getLogger(WebUtil.class);

	/**
	 * 输出数据
	 *
	 * @param request
	 * @param response
	 * @param html
	 *            数据内容
	 */
	public static void sendHtml(HttpServletRequest request,
			HttpServletResponse response, String html) {
		logger.debug(html);
		try {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			out.write(html);
			out.close();
		} catch (IOException e) {
			logger.error(e);
		}
	}
}
