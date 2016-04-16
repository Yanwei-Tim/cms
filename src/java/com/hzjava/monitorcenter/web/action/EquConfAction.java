package com.hzjava.monitorcenter.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzjava.monitorcenter.web.WebUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import cn.collin.commons.utils.DateUtils;

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.Equipment;
import com.hzjava.monitorcenter.service.ConfService;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.utils.FileUtils;
import com.hzjava.monitorcenter.web.SessionUtils;

/**
 * 设备管理配置
 * 
 * @author collin.code@gmail.com
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/equConf" validate="false" parameter="m"
 * @struts.action-forward name="indexJSON"
 *                        path="/WEB-INF/pages/conf/equIndexJSON.jsp"
 * @struts.action-forward name="add" path="/WEB-INF/pages/conf/equAdd.jsp"
 * @struts.action-forward name="update" path="/WEB-INF/pages/conf/equUpdate.jsp"
 * @struts.action-forward name="detail" path="/WEB-INF/pages/conf/equDetail.jsp"
 * @struts.action-forward name="equComboxJSON" path="/WEB-INF/pages/conf/equComboxJSON.jsp"
 */
public class EquConfAction extends DispatchActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EquConfAction.class);

	private ConfService confService;

	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setConfService(ConfService confService) {
		this.confService = confService;
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		Map model = confService.getEquDetailModel(id);
		request.setAttribute("model", model);
		return mapping.findForward("detail");
	}
	
	public ActionForward equCombox(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List list = confService.findEquAll();
        if(!list.contains("All")){
            Equipment equ = new Equipment();
			equ.setEquName("All");
			list.add(0,equ);
        }
//		if(ServletRequestUtils.getIntParameter(request,"all",0)==1){
//			Equipment equ = new Equipment();
//			equ.setEquName("All");
//			list.add(0,equ);
//		}
		
		request.setAttribute("list", list);
		return mapping.findForward("equComboxJSON");
	}

	public ActionForward showAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map model = confService.getEquAddModel();
		request.setAttribute("model", model);
		return mapping.findForward("add");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
		String equName = ServletRequestUtils.getStringParameter(request,"");
		String msg = null;
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			try {
				Equipment entry = new Equipment();
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(1024 * 1024 * 10);
				upload.setHeaderEncoding("UTF-8");
				List items = upload.parseRequest(request);
//				List fileItems = FileUtils.populate(entry, items, "UTF-8");
                List<FileItem> fileItems = new ArrayList<FileItem>();
                for (Iterator iter = items.iterator(); iter.hasNext();) {
                    FileItem item = (FileItem) iter.next();
                    if (!item.isFormField()) {
                        fileItems.add(item);
                    } else {
                        String fieldName = item.getFieldName();
                        if(fieldName.equals("buyDay")||fieldName.equals("unrepairDay")) {

                        }else {
                            BeanUtils.setProperty(entry, fieldName, item.getString("UTF-8"));
                        }

                    }
                }
				equName = entry.getEquName();
				String buy_day = ServletRequestUtils.getStringParameter(request,
				"buy_day");
              /*  Date buyDay =  FileUtils.getFieldValueWithTimestamp(items,
                        "buy_day", "yyyy-MM-dd HH:mm:ss");
                entry.setBuyDay(buyDay);
                Date unrepair_day =  FileUtils.getFieldValueWithTimestamp(items,
                        "unrepair_day", "yyyy-MM-dd HH:mm:ss");
                entry.setBuyDay(unrepair_day);*/
				String unrepair_day = ServletRequestUtils.getStringParameter(
						request, "unrepair_day");
				if (buy_day != null && buy_day.length() > 0) {
					Date buyDay = DateUtils.parse(buy_day, "yyyy-MM-dd HH:mm:ss");
					entry.setBuyDay(buyDay);
				}
				if (unrepair_day != null && unrepair_day.length() > 0) {
					Date unrepairDay = DateUtils.parse(unrepair_day, "yyyy-MM-dd HH:mm:ss");
					entry.setUnrepairDay(unrepairDay);
				}
				confService.newEquipment(entry,fileItems);
				
				logService.newLog("INFO", SessionUtils.getAccount(request)
						.getUserName(), "设备配置管理", "设备 " + entry.getEquName() + " 新增成功");
				msg = "新增成功";
			} catch (Exception e) {
                e.printStackTrace();
				logger.error("", e);
				logService.newLog("ERROR", SessionUtils.getAccount(request)
						.getUserName(), "设备配置管理", "设备 " + equName + " 新增不成功");
				msg = "新增失败";
			}
		}else{
			msg = "您还没有登录";
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true,msg:'"+msg+"'}");
		writer.close();
		
		return null;
	}

	public ActionForward showUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		Map model = confService.getEquUpdateModel(id);
		request.setAttribute("model", model);
		return mapping.findForward("update");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String equName = null;
		String msg = null;
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			try {
				Equipment entry = new Equipment();
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(1024 * 1024 * 10);
				upload.setHeaderEncoding("UTF-8");
				List items = upload.parseRequest(request);
				List fileItems = FileUtils.populate(entry, items, "UTF-8");
				equName = entry.getEquName();
				String buy_day = ServletRequestUtils.getStringParameter(request,
				"buy_day");
				String unrepair_day = ServletRequestUtils.getStringParameter(
						request, "unrepair_day");
				if (buy_day != null && buy_day.length() > 0) {
					Date buyDay = DateUtils.parse(buy_day, "yyyy-MM-dd HH:mm:ss");
					entry.setBuyDay(buyDay);
				}
				if (unrepair_day != null && unrepair_day.length() > 0) {
					Date unrepairDay = DateUtils.parse(unrepair_day, "yyyy-MM-dd HH:mm:ss");
					entry.setUnrepairDay(unrepairDay);
				}
				confService.updateEquipment(entry,fileItems);
				
				logService.newLog("INFO", SessionUtils.getAccount(request)
						.getUserName(), "设备配置管理", "设备 " + entry.getEquName()
						+ " 修改成功");
				msg = "修改成功";
			} catch (Exception e) {
				logger.error("", e);
				logService.newLog("ERROR", SessionUtils.getAccount(request)
						.getUserName(), "设备配置管理", "设备 " + equName + " 修改不成功");
				msg = "修改失败";
			}			
		}else{
			msg = "您还没有登录";
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true,msg:'"+msg+"'}");
		writer.close();
		
		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String msg = null;
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			try{
				String ids = ServletRequestUtils.getRequiredStringParameter(request,
				"ids");
				String idsArr[] = ids.split(",");
				confService.deleteEquipments(idsArr, account);
				msg = "删除成功";
			}catch (Exception e) {
				msg = "删除失败";
			}
		}else{
			msg = "您还没有登录";
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true,msg:'"+msg+"'}");
		writer.close();
		
		return null;
	}

	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 15);
		int pageIndex = start / limit + 1;
		Map model = confService.getEquConfIndexModel(pageIndex);
		request.setAttribute("model", model);
		return mapping.findForward("indexJSON");
	}
	
	public ActionForward download(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String fileName = ServletRequestUtils.getRequiredStringParameter(request,"fileName");
		
		if (fileName != null) {
			File file = new File(fileName);
			FileInputStream is = new FileInputStream(file);
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("gb2312"), "ISO8859_1"));
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
			writer.write("设备系统配置文件不存在，请确认是否已经上传。");
			writer.close();
		}
		return null;
	}
    public ActionForward showEquType(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //移动警务安全接入系统
        String json = confService.showEquType();
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.close();
        return null;
    }
    public ActionForward showEquTypes(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //边界安全接入平台
        String json = confService.showEquTypes();
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.close();
        return null;
    }
    public ActionForward selectLinkNameKeyValue(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //边界安全接入平台
        String intOrExt = ServletRequestUtils.getRequiredStringParameter(request,"intOrExt");
        String json = null;
        if(intOrExt.equals("i")){
            json = confService.showLinkInt();
        }else {
            json = confService.showLinkExt();
        }
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.close();
        return null;
    }
    public ActionForward findByIp(ActionMapping mapping, ActionForm form,
                                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //边界安全接入平台
        String ip = ServletRequestUtils.getRequiredStringParameter(request,"ip");
        if(confService.findByIp(ip)){
//            WebUtil.sendHtml(request, response, "{success:true,msg:'ip不存在'}");
        }else {
            WebUtil.sendHtml(request, response, "{success:true,msg:'ip存在;请更改ip'}");
        }
        return null;
    }
}
