package com.hzjava.monitorcenter.web.action;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.DispatchActionSupport;

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.service.LoginService;
import com.hzjava.monitorcenter.web.SessionUtils;

/**
 *  用户登录 
 * 
 * @author collin.code@gmail.com
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/login" validate="false"
 * @struts.action-forward name="login" path="/login.jsp" redirect="true"
 * @struts.action-forward name="index" path="/" redirect="true"
 */
public class LoginAction extends DispatchActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LoginAction.class);

	private LoginService loginService;
	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String vcode = request.getParameter("vcode");

		if (SessionUtils.getAccount(request) != null) {
			SessionUtils.removeAccount(request);
		}

		if (null == name || null == pwd || null == vcode) {
			logger.info(" 用户名、密码或验证码为空！ ");
			request.setAttribute("message", "用户名、密码或验证码为空！");
			return mapping.findForward("login");
		}

		if (!vcode.equalsIgnoreCase(SessionUtils.getVcode(request))) {
			logger.info(" 验证码出错！ ");
			request.setAttribute("message", "验证码出错！");
			return mapping.findForward("login");
		}

		Account account = loginService.getAccountByNameAndPwd(name, pwd);
		if (account != null ) {
			boolean isTime = checkTime(account);
//			boolean isIp = checkIp(account,request);
			if(isTime){
//				if(isIp){
					logger.info(" 用户名和密码验证通过!");
					SessionUtils.setAccount(request, account);
					logService.newLog("INFO", account.getUserName(), "用户登录", "用户登录成功");
					return mapping.findForward("index");
//				} else {
//					logger.info("非法登陆IP!");
//					request.setAttribute("message", "非法登陆IP!");
//				}
			} else {
				logger.info("非法登陆时间!");
				request.setAttribute("message", "非法登陆时间!");
			}
		} else {
			logger.info(" 用户名、密码错误!");
			request.setAttribute("message", "用户名、密码错误!");
		}
		return mapping.findForward("login");
	}

	private boolean checkTime(Account account) {
		int startHour = account.getStartHour();
		int endHour = account.getEndHour();
		int hour = Calendar.HOUR+1;
		if(hour >= startHour && hour <= endHour){
			return true;
		}
		return false;
	}

	private boolean checkIp(Account account,HttpServletRequest request) {
		String ip = this.getIpAddr(request);
		String startIp = account.getStartIp();
		String endIp = account.getEndIp();
		try {
			long ipLong = getIP(InetAddress.getByName(ip));
			long startIpLong = getIP(InetAddress.getByName(startIp));
			long endIpLong = getIP(InetAddress.getByName(endIp));
			if(ipLong >= startIpLong && ipLong <= endIpLong){
				return true;
			}
		} catch (UnknownHostException e) {			
			e.printStackTrace();
		}		
		return false;
	}
	/**
	 *  获取登陆IP
	 * @param request
	 * @return
	 */
	private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
	/**
	 * ip转成long类型
	 * @param ip
	 * @return
	 */
	public long getIP(InetAddress ip) {  
        byte[] b = ip.getAddress();  
        long l = b[0] << 24L & 0xff000000L | b[1] << 16L & 0xff0000L  
                | b[2] << 8L & 0xff00 | b[3] << 0L & 0xff;  
        return l;  
    } 
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}
