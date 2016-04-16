package com.hzjava.monitorcenter.web.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.DispatcherServlet;

import com.hzjava.monitorcenter.constant.AppConstant;
import com.hzjava.monitorcenter.web.SiteContext;

public class SiteContextLoaderServlet extends DispatcherServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log log = LogFactory.getLog(SiteContextLoaderServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletContext = config.getServletContext();
		WebApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(servletContext);

		SiteContext.getInstance().contextRealPath = config.getServletContext()
				.getRealPath("/");

		// set constants value to app context
		servletContext.setAttribute("appConstant", new AppConstant());

		Properties pros = new Properties();
		try {
			pros.load(getClass().getResourceAsStream("/config.properties"));
			SiteContext.getInstance().uploadFileDir = pros.getProperty("uploadFileDir");
			SiteContext.getInstance().serviceUrl = pros.getProperty("serviceUrl");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ServletConfig getServletConfig() {
		// do nothing
		return null;
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// do nothing
	}

	@Override
	public String getServletInfo() {
		// do nothing
		return null;
	}

	@Override
	public void destroy() {

	}

}
