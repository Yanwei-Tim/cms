package com.hzjava.monitorcenter.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.District;
import com.hzjava.monitorcenter.domain.Jbqk;
import com.hzjava.monitorcenter.service.ConfService;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.utils.FileUtils;
import com.hzjava.monitorcenter.web.SessionUtils;

/**
 * 基本配置
 * 
 * @author collin.code@gmail.com
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/baseConf" validate="false"
 *                parameter="m"
 * @struts.action-forward name="detail"
 *                        path="/WEB-INF/pages/conf/baseDetailJSON.jsp"
 */
public class BaseConfAction extends DispatchActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaseConfAction.class);

	private ConfService confService;

	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map model = confService.getBaseConfDetailModel();
		request.setAttribute("model", model);
		return mapping.findForward("detail");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		boolean isLogin = setIsLogin(request);
		String json = null;
		if(isLogin){
			try {
				Jbqk entry = new Jbqk();
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(1024 * 1024 * 10);
				upload.setHeaderEncoding("UTF-8");
				List items = upload.parseRequest(request);
				List fileItems = FileUtils.populate(entry, items, "UTF-8");
				entry.setJsDay(FileUtils.getFieldValueWithTimestamp(items,
						"js_day", "yyyy-MM-dd HH:mm:ss"));
				entry.setSpDay(FileUtils.getFieldValueWithTimestamp(items,
						"sp_day", "yyyy-MM-dd HH:mm:ss"));
				Map model = confService.getBaseConfDetailModel();
				Jbqk jbqk = (Jbqk) model.get("entry");
				if (entry.getJsdw() == 0) {
					entry.setJsdw(jbqk.getJsdw());
				}
				if (entry.getSpdw() == 0) {
					entry.setSpdw(jbqk.getSpdw());
				}
				if (entry.getMainComp() == 0) {
					entry.setMainComp(jbqk.getMainComp());
				}
				confService.updateJbqk(entry, fileItems);
				logService.newLog("INFO", SessionUtils.getAccount(request)
						.getUserName(), "基本配置", "用户保存配置成功 ");
			} catch (Exception e) {
				logger.error("", e);
				logService.newLog("ERROR", SessionUtils.getAccount(request)
						.getUserName(), "基本配置", "用户保存配置不成功 ");
			}
			json = "{success:true,msg:'保存成功!'}";
		}else{
			json = "{success:true,msg:'您还没有登录!'}";
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println(json);
		writer.close();

		return null;
	}

	private boolean setIsLogin(HttpServletRequest request) {
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			return true;
		}else{
			return false;
		}
	}

	public void setConfService(ConfService confService) {
		this.confService = confService;
	}

	public ActionForward download(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
		String type = ServletRequestUtils.getRequiredStringParameter(request,
				"type");
		Object[] obj = confService.getBlobFromJbqk(id, type);
		if (obj[1] != null) {
			File file = new File((String) obj[1]);
			FileInputStream is = new FileInputStream(file);
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String((obj[1] == null ? "file" : (String) obj[1])
							.getBytes("gb2312"), "ISO8859_1"));
			OutputStream out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			out.flush();
			out.close();
		} else {
			PrintWriter writer = response.getWriter();
			writer.write("附件不存在，请确认是否已经上传。");
			writer.close();
		}
		return null;
	}

}
