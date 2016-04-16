package com.hzjava.monitorcenter.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzjava.monitorcenter.web.SessionUtils;

/**
 * 检测用户是否已经登录
 * 
 * @author collin.code@gmail.com
 * 
 */
public class CheckLoginFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;

		HttpServletResponse response = (HttpServletResponse) res;

		String requestURL = request.getRequestURL().toString();

		if ((SessionUtils.getAccount((HttpServletRequest) request) == null)
				&& (requestURL.indexOf("login") < 0)) {
			response.sendRedirect("http://" + request.getHeader("Host")
					+ request.getContextPath());
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
