package com.hzjava.monitorcenter.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import cn.collin.commons.web.servlet.ImageVerifyCodeServlet;

import com.hzjava.monitorcenter.domain.Account;

public class SessionUtils {
	private static final String ACCOUNT_ATTRIBUTE = "account";

	public static Account getAccount(HttpServletRequest request) {
		return (Account) request.getSession().getAttribute(ACCOUNT_ATTRIBUTE);
	}

	public static void removeAccount(HttpServletRequest request) {
		request.getSession().removeAttribute(ACCOUNT_ATTRIBUTE);
	}

	public static void setAccount(HttpServletRequest request, Account account) {
		request.getSession().setAttribute(ACCOUNT_ATTRIBUTE, account);
	}

	public static String getVcode(HttpServletRequest request) {
		return request.getSession().getAttribute(
				ImageVerifyCodeServlet.SESSION_ATT_NAME).toString();

	}

	public static Account getAccount(PageContext pageContext) {
		HttpSession session = pageContext.getSession();
		if (session != null) {
			Object obj = session.getAttribute(ACCOUNT_ATTRIBUTE);
			if (obj != null)
				return (Account) obj;
		}
		return null;
	}

	public static void invalidateSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session != null)
			session.invalidate();
	}

}
