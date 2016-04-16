package com.hzjava.monitorcenter.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import org.springframework.web.struts.DispatchActionSupport;

/**
 * @author xiangqi.java@gmail.com
 */
public class BaseAction extends DispatchActionSupport {
	private static Logger logger = Logger.getLogger(BaseAction.class);

	/**
	 * 输出HTML
	 * 
	 * @param response
	 * @param html
	 * @return
	 * @throws java.io.IOException
	 */
	protected ActionForward sendHTML(HttpServletResponse response, String html)
			throws IOException {
		PrintWriter out = response.getWriter();
		out.print(html);
		out.close();
		return null;
	}

}
