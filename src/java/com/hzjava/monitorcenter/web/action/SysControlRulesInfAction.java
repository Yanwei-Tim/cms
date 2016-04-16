package com.hzjava.monitorcenter.web.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.collin.commons.utils.DateUtils;
import com.hzjava.monitorcenter.entity.FileForm;
import com.hzjava.monitorcenter.utils.FileUtils;
import com.hzjava.monitorcenter.web.SiteContext;
import com.hzjava.monitorcenter.web.WebUtil;
import org.apache.commons.fileupload.FileItem;
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
import com.hzjava.monitorcenter.domain.SysControlRulesInf;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.service.SysControlRulesInfService;
import com.hzjava.monitorcenter.web.SessionUtils;

/**
 * @author www
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/pages/sysinf/sysControlRulesInf" validate="false"
 *                parameter="m"
 * @struts.action-forward name="insert"
 * @struts.action-forward name="update"
 * @struts.action-forward name="delete"
 * @struts.action-forward name="read"
 *                        
 */
public class SysControlRulesInfAction extends DispatchActionSupport {

	private static Logger logger = Logger.getLogger(SysControlRulesInfAction.class);
	private SysControlRulesInfService sysControlRulesInfService;
	private LogService logService;
	
	public LogService getLogService() {
		return logService;
	}

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public SysControlRulesInfService getSysControlRulesInfService() {
		return sysControlRulesInfService;
	}

	public void setSysControlRulesInfService(
			SysControlRulesInfService sysControlRulesInfService) {
		this.sysControlRulesInfService = sysControlRulesInfService;
	}

	public ActionForward read(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("控制策略注册查看、查询功能开始");
		int start = ServletRequestUtils.getIntParameter(request, "start");
		int limit = ServletRequestUtils.getIntParameter(request, "limit");
		int pageIndex = start / limit + 1;
		String json = sysControlRulesInfService.getPages(pageIndex,limit);
		WebUtil.sendHtml(request, response, json);
		return null;
	}
	
	public ActionForward insert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("控制策略注册新增功能开始");
		request.getCharacterEncoding();
		String json = null;
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			SysControlRulesInf sysControlRulesInf = new SysControlRulesInf();
			String fileName = ServletRequestUtils.getStringParameter(request, "fileName");
			String desc = ServletRequestUtils.getStringParameter(request, "desc");
			String time = ServletRequestUtils.getStringParameter(request, "collectTime");
			Date collectTime = DateUtils.parse(time, "yyyy-MM-dd HH:mm:ss");
			sysControlRulesInf.setFileName(fileName);
			sysControlRulesInf.setDesc(desc);
			sysControlRulesInf.setCollectTime(collectTime);
			try {

				json = sysControlRulesInfService.insert(sysControlRulesInf);
				logService
				.newLog("INFO", account.getUserName(), "控制策略注册管理", "用户 " + account.getName()
						+ " 新增成功");
			}catch (Exception e) {
				logger.error("", e);
				logService.newLog("ERROR", SessionUtils.getAccount(request)
						.getUserName(), "控制策略注册管理", "用户 " + account.getName()
						+ " 新增失败");
				json = "{success:true,msg:'新增失败'}";
			}
		}else{
			json = "{success:true,msg:'您还没有登录'}";
		}
		WebUtil.sendHtml(request, response, json);
		return null;
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("控制策略注册删除功能开始");
		String json = null;
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			long[] ids = ServletRequestUtils.getLongParameters(request, "ids");
			try{
				json = sysControlRulesInfService.delete(ids);
				logService.newLog("INFO", account.getUserName(), 
						"控制策略注册管理", "用户 " + account.getName()+ " 删除成功");
			}catch (Exception e) {
				logger.info(e.getMessage());
				logService.newLog("INFO", account.getUserName(), 
						"控制策略注册管理", "用户 " + account.getName()+ " 删除失败");
				json = "{success:true,msg:'删除失败'}";
			}
		}else{
			json = "{success:true,msg:'您还没有登录'}";
		}
		WebUtil.sendHtml(request, response, json);
		return null;
	}

    public ActionForward upload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
        String msg = null;
        try{
            FileForm entry = (FileForm) form;
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(1024 * 1024 * 10);
            upload.setHeaderEncoding("UTF-8");
            List items = upload.parseRequest(request);
            List fileItems = FileUtils.populate(entry, items, "UTF-8");
            String filePath = null;
            for (Iterator iter = fileItems.iterator(); iter.hasNext();) {
                FileItem item = (FileItem) iter.next();
                if (item.getSize() > 0) {
                    String n = item.getFieldName();
                    if (item.getFieldName().equalsIgnoreCase("formFile")) {
                        String name = item.getName();
                        String[] sa = name.split("\\\\");
                        String fileName = sa[sa.length - 1];
                        String uploadFileDir = System.getProperty("monitor.home")+"/data";
                        filePath = uploadFileDir +"/"+ fileName;
                        File file = new File(filePath);
                        try {
                            item.write(file);
                            msg = "上传成功!";
                        } catch (Exception e) {
                            logger.error("", e);
                        }
                    }
                    item.delete();
                } else {
                    msg = "上传失败,文件内容为空!";
                }
            }
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(),
                        "控制策略注册管理", "用户 " + SessionUtils.getAccount(request).getUserName()+ msg);
        } catch (Exception e){
            logger.error("控制策略注册管理",e);
            logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(),
						"控制策略注册管理", "用户 " + SessionUtils.getAccount(request).getUserName()+ "上传失败");
            msg = "上传失败!";
        }
        String json = "{success:true,msg:'"+msg+"'}";
        WebUtil.sendHtml(request, response, json);
		return null;
    }

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("控制策略注册修改功能开始");
		String json = null;
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			SysControlRulesInf sysControlRulesInf = new SysControlRulesInf();
			Long id = ServletRequestUtils.getLongParameter(request, "id");
			String fileName = ServletRequestUtils.getStringParameter(request, "fileName");
			String desc = ServletRequestUtils.getStringParameter(request, "desc");
			String time = ServletRequestUtils.getStringParameter(request, "collectTime");
			Date collectTime = DateUtils.parse(time, "yyyy-MM-dd HH:mm:ss");
			sysControlRulesInf.setId(id);
			sysControlRulesInf.setFileName(fileName);
			sysControlRulesInf.setDesc(desc);
			sysControlRulesInf.setCollectTime(collectTime);
			try{

				json = sysControlRulesInfService.update(sysControlRulesInf);
				logService.newLog("INFO", account.getUserName(), 
						"控制策略注册管理", "用户 " + account.getName()+ " 修改成功");
			}catch (Exception e) {
				logger.info(e.getMessage());
				logService.newLog("INFO", account.getUserName(), 
						"控制策略注册管理", "用户 " + account.getName()+ " 修改失败");
				json = "{success:true,msg:'修改失败'}";
			}
		}else{
			json = "{success:true,msg:'您还没有登录'}";
		}
		WebUtil.sendHtml(request, response, json);
		return null;
	}

}
