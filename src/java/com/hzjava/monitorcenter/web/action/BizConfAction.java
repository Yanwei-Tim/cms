package com.hzjava.monitorcenter.web.action;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.collin.commons.utils.DateUtils;
import com.hzjava.monitorcenter.domain.Role;
import com.hzjava.monitorcenter.entity.FileForm;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.BusinessRegister;
import com.hzjava.monitorcenter.service.ConfService;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.utils.FileUtils;
import com.hzjava.monitorcenter.web.SessionUtils;

/**
 * 业务注册管理
 * 
 * @author collin.code@gmail.com
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/bizConf" validate="false" parameter="m"
 * @struts.action-forward name="indexJSON"
 *                        path="/WEB-INF/pages/conf/bizIndexJSON.jsp"
 * @struts.action-forward name="add" path="/WEB-INF/pages/conf/bizAdd.jsp"
 * @struts.action-forward name="update" path="/WEB-INF/pages/conf/bizUpdate.jsp"
 * @struts.action-forward name="detail" path="/WEB-INF/pages/conf/bizDetail.jsp"
 * @struts.action-forward name="bizComboxJSON"
 *                        path="/WEB-INF/pages/conf/bizComboxJSON.jsp"
 */
public class BizConfAction extends DispatchActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BizConfAction.class);

	private ConfService confService;

	private LogService logService;

    public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 15);
		int pageIndex = start / limit + 1;
		Map model = confService.getBizConfIndexModel(pageIndex);
		request.setAttribute("model", model);
		return mapping.findForward("indexJSON");
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		Map model = confService.getBizDetailModel(id);
		request.setAttribute("model", model);
		return mapping.findForward("detail");
	}

	public ActionForward showAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map model = confService.getBizAddModel();
		request.setAttribute("model", model);
		return mapping.findForward("add");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
		String businessName = null;
		String msg = null;
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			try {
				BusinessRegister entry = new BusinessRegister();
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(1024 * 1024 * 20);
				upload.setHeaderEncoding("UTF-8");
				List items = upload.parseRequest(request);
				List fileItems = FileUtils.populate(entry, items, "UTF-8");
				businessName = entry.getBusinessName();
				entry.setRegisterDate(FileUtils.getFieldValueWithTimestamp(items,
						"register_date", "yyyy-MM-dd HH:mm:ss"));
				entry.setAuthDate(FileUtils.getFieldValueWithTimestamp(items,
						"auth_date", "yyyy-MM-dd HH:mm:ss"));
//			if (entry.getUseDepart() == 0) {
//				entry.setUseDepart(entry.getUseDepartParent());
//			}
				
				confService.newBusinessRegister(entry, fileItems);
				logService.newLog("INFO", SessionUtils.getAccount(request)
						.getUserName(), "业务注册管理", "业务 " + entry.getBusinessName()
						+ " 新增成功");
				msg = "新增成功";
			} catch (Exception e) {
				logger.error("", e);
				logService.newLog("ERROR", SessionUtils.getAccount(request)
						.getUserName(), "业务注册管理", "业务 " + businessName + " 新增不成功");
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
    public ActionForward addss(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        String businessName = null;
        String msg = null;
        Account account = SessionUtils.getAccount(request);
        if(account!=null){
            try {
                BusinessRegister entry = new BusinessRegister();
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(1024 * 1024 * 20);
                upload.setHeaderEncoding("UTF-8");
                List items = upload.parseRequest(request);
                String auth_material=null;
                String tp_graph=null;
                for (Iterator iter = items.iterator(); iter.hasNext();) {
                    FileItem item = (FileItem) iter.next();
                    if (!item.isFormField()) {
                       if(item.getFieldName().equals("auth_material")){
                           auth_material=item.getName() ;
                           entry.setAuthMaterialFileName(auth_material);
                       }else {
                           tp_graph=item.getName() ;
                           entry.setTpGraphFileName(tp_graph);
                       }
                    }else {
                        String fieldName = item.getFieldName();
                        if(fieldName.equals("businessCode")){
                            BeanUtils.setProperty(entry, "businessCodeId", item.getString("UTF-8"));
                        }else if(fieldName.equals("businessExchModel")){
                            BeanUtils.setProperty(entry, "businessExchModelId", item.getString("UTF-8"));
                        }else if(fieldName.equals("useDepartType")){
                            BeanUtils.setProperty(entry, "useDepartTypeId", item.getString("UTF-8"));
                        }else if(fieldName.equals("businessExchModel")){
                            BeanUtils.setProperty(entry, "businessExchModelId", item.getString("UTF-8"));
                        }else if(fieldName.equals("exchangeDirection")){
                            BeanUtils.setProperty(entry, "exchangeDirectionCode", item.getString("UTF-8"));
                        }else if(fieldName.equals("exchProtocol")){
                            BeanUtils.setProperty(entry, "exchProtocolCode", item.getString("UTF-8"));
                        }else {
                            BeanUtils.setProperty(entry, fieldName, item.getString("UTF-8"));
                        }
                    }
                }
                entry.setRegisterDate(FileUtils.getFieldValueWithTimestamp(items,
                        "register_date", "yyyy-MM-dd HH:mm:ss"));
                entry.setAuthDate(FileUtils.getFieldValueWithTimestamp(items,
                        "auth_date", "yyyy-MM-dd HH:mm:ss"));
                confService.addBusinessRegister(entry);
                logService.newLog("INFO", SessionUtils.getAccount(request)
                        .getUserName(), "业务注册管理", "业务 " + entry.getBusinessName()
                        + " 新增成功");
                msg = "新增成功";
            } catch (Exception e) {
                logger.error("", e);
                logService.newLog("ERROR", SessionUtils.getAccount(request)
                        .getUserName(), "业务注册管理", "业务 " + businessName + " 新增不成功");
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
		Map model = confService.getBizUpdateModel(id);
		request.setAttribute("model", model);
		return mapping.findForward("update");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		String businessName = null;
		String msg = null;
		Account account = SessionUtils.getAccount(request);
		if(account!=null){
			try {
				BusinessRegister entry = new BusinessRegister();
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(1024 * 1024 * 20);
				upload.setHeaderEncoding("UTF-8");
				List items = upload.parseRequest(request);
				List fileItems = FileUtils.populate(entry, items, "UTF-8");
				businessName = entry.getBusinessName();
				
				entry.setRegisterDate(FileUtils.getFieldValueWithTimestamp(items,
						"register_date", "yyyy-MM-dd HH:mm:ss"));
				entry.setAuthDate(FileUtils.getFieldValueWithTimestamp(items,
						"auth_date", "yyyy-MM-dd HH:mm:ss"));
//			Map model = confService.getBizUpdateModel(entry.getId());
//			BusinessRegister br = (BusinessRegister) model.get("item");
//			if(br.getUseDepart()==0){
//				entry.setUseDepart(entry.getUseDepartParent());
//			}
				confService.updateBusinessRegister(entry, fileItems);
				logService.newLog("INFO", SessionUtils.getAccount(request)
						.getUserName(), "业务注册管理", "业务 " + entry.getBusinessName()
						+ " 修改成功");
				msg = "修改成功";
			} catch (Exception e) {
				logger.error("", e);
				logService.newLog("ERROR", SessionUtils.getAccount(request)
						.getUserName(), "业务注册管理", "业务 " + businessName + " 修改不成功");
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
    public ActionForward updatess(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws IOException {
        String businessName = null;
        String msg = null;
        Account account = SessionUtils.getAccount(request);
        if(account!=null){
            try {
                BusinessRegister entry = new BusinessRegister();
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(1024 * 1024 * 20);
                upload.setHeaderEncoding("UTF-8");
                List items = upload.parseRequest(request);
                String auth_material=null;
                String tp_graph=null;
                for (Iterator iter = items.iterator(); iter.hasNext();) {
                    FileItem item = (FileItem) iter.next();
                    if (!item.isFormField()) {
                        if(item.getFieldName().equals("auth_material")){
                            auth_material=item.getName() ;
                            entry.setAuthMaterialFileName(auth_material);
                        }else {
                            tp_graph=item.getName() ;
                            entry.setTpGraphFileName(tp_graph);
                        }
                    }else {
                        String fieldName = item.getFieldName();
                        if(fieldName.equals("businessCode")){
                            BeanUtils.setProperty(entry, "businessCodeId", item.getString("UTF-8"));
                        }else if(fieldName.equals("businessExchModel")){
                            BeanUtils.setProperty(entry, "businessExchModelId", item.getString("UTF-8"));
                        }else if(fieldName.equals("useDepartType")){
                            BeanUtils.setProperty(entry, "useDepartTypeId", item.getString("UTF-8"));
                        }else if(fieldName.equals("businessExchModel")){
                            BeanUtils.setProperty(entry, "businessExchModelId", item.getString("UTF-8"));
                        }else if(fieldName.equals("exchangeDirection")){
                            BeanUtils.setProperty(entry, "exchangeDirectionCode", item.getString("UTF-8"));
                        }else if(fieldName.equals("exchProtocol")){
                            BeanUtils.setProperty(entry, "exchProtocolCode", item.getString("UTF-8"));
                        }else {
                            BeanUtils.setProperty(entry, fieldName, item.getString("UTF-8"));
                        }
                    }
                }
                entry.setRegisterDate(FileUtils.getFieldValueWithTimestamp(items,
                        "register_date", "yyyy-MM-dd HH:mm:ss"));
                entry.setAuthDate(FileUtils.getFieldValueWithTimestamp(items,
                        "auth_date", "yyyy-MM-dd HH:mm:ss"));
                confService.updateBusinessRegister(entry);
                logService.newLog("INFO", SessionUtils.getAccount(request)
                        .getUserName(), "业务注册管理", "业务 " + entry.getBusinessName()
                        + " 修改成功");
                msg = "修改成功";
            } catch (Exception e) {
                logger.error("", e);
                logService.newLog("ERROR", SessionUtils.getAccount(request)
                        .getUserName(), "业务注册管理", "业务 " + businessName + " 修改不成功");
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
			try{String ids = ServletRequestUtils.getRequiredStringParameter(request,
				"ids");
				String idsArr[] = ids.split(",");
				confService.deleteBusinessRegisters(idsArr, account);
				msg = "删除成功";
                logService.newLog("INFO",SessionUtils.getAccount(request).getUserName(),"业务注册管理","删除业务成功");
			}catch (Exception e) {
				logger.info(e.getMessage());
                logService.newLog("ERROR",SessionUtils.getAccount(request).getUserName(),"业务注册管理","删除业务失败");
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

	public void setConfService(ConfService confService) {
		this.confService = confService;
	}

	public ActionForward bizCombox(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List list = confService.findBizAll();
		if(ServletRequestUtils.getIntParameter(request,"all",0)==1){
			BusinessRegister biz = new BusinessRegister();
			biz.setBusinessName("All");
			list.add(0, biz);
		}
		
		request.setAttribute("list", list);
		return mapping.findForward("bizComboxJSON");
	}

	public ActionForward download(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Long id = ServletRequestUtils.getRequiredLongParameter(request, "id");
		String type = ServletRequestUtils.getRequiredStringParameter(request,
				"type");
		Object[] obj = confService.getBlobFromBiz(id, type);
		if (obj[0] != null) {
			InputStream is = ((Blob) obj[0]).getBinaryStream();
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(((String) obj[1]).getBytes("gb2312"),
							"ISO8859_1"));
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
    public ActionForward showBusinessCode(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String json=confService.showBusinessCode();
        PrintWriter writer = response.getWriter();
        writer.write(json);
        writer.close();
        return null;
    }

}
