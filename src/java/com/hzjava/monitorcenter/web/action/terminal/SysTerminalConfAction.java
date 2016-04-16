package com.hzjava.monitorcenter.web.action.terminal;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzjava.monitorcenter.domain.Role;
import com.hzjava.monitorcenter.domain.TerminalAddress;
import com.hzjava.monitorcenter.entity.FileForm;
import com.hzjava.monitorcenter.utils.FileUtils;
import com.hzjava.monitorcenter.web.SiteContext;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import com.hzjava.monitorcenter.constant.AppConstant;
import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.SysTerminalInfo;
import com.hzjava.monitorcenter.service.LogService;
import com.hzjava.monitorcenter.service.SysTerminalService;
import com.hzjava.monitorcenter.web.SessionUtils;
import com.hzjava.monitorcenter.web.WebUtil;

/**
 * 终端设备管理
 * 
 * @author collin.code@gmail.com
 * @struts.action type="org.springframework.web.struts.DelegatingActionProxy"
 *                scope="request" path="/sysTerminalConf" validate="false"
 *                parameter="m"
 * @struts.action-forward name="indexJSON"
 *                        path="/WEB-INF/pages/sysTerminalConf/epIndexJSON.jsp"
 * @struts.action-forward name="add"
 *                        path="/WEB-INF/pages/sysTerminalConf/epAdd.jsp"
 * @struts.action-forward name="update"
 *                        path="/WEB-INF/pages/sysTerminalConf/epUpdate.jsp"
 * @struts.action-forward name="detail"
 *                        path="/WEB-INF/pages/sysTerminalConf/epDetail.jsp"
 * 
 */
public class SysTerminalConfAction extends DispatchActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(SysTerminalConfAction.class);

	private SysTerminalService sysTerminalService;

	private LogService logService;

	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	public void setSysTerminalService(SysTerminalService sysTerminalService) {
		this.sysTerminalService = sysTerminalService;
	}

	@SuppressWarnings("rawtypes")
	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int start = ServletRequestUtils.getIntParameter(request, "start", 0);
		int pageLength = ServletRequestUtils.getIntParameter(request, "limit",
				AppConstant.PAGERESULT_PAGE_LENGTH);
		int pageIndex = start / pageLength + 1;
		String userName= ServletRequestUtils.getStringParameter(request, "userName");
		String policeId= ServletRequestUtils.getStringParameter(request, "policeId");
		String userDepart= ServletRequestUtils.getStringParameter(request, "userDepart");
		String userZone= ServletRequestUtils.getStringParameter(request, "userZone");
		String ifCancel= ServletRequestUtils.getStringParameter(request, "ifCancel");
		Map model = sysTerminalService.getSysTerminalConfIndexModel(pageIndex,
				pageLength, userName, policeId, userDepart, userZone, ifCancel);
		request.setAttribute("model", model);
		return mapping.findForward("indexJSON");
	}
	
	@SuppressWarnings("rawtypes")
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		Map model = sysTerminalService.getSysTerminalDetailModel(id);
		request.setAttribute("model", model);
		return mapping.findForward("detail");
	}

	@SuppressWarnings("rawtypes")
	public ActionForward showAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map model = sysTerminalService.getSysTerminalAddModel();
		request.setAttribute("model", model);
		return mapping.findForward("add");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String terminalName = null;
		try {
			SysTerminalInfo entry = new SysTerminalInfo();
			String userZone = ServletRequestUtils.getStringParameter(request, "userZone");
			String userDepart = ServletRequestUtils.getStringParameter(request, "userDepart");
			terminalName = ServletRequestUtils.getRequiredStringParameter(request, "terminalName");
			String terminalType = ServletRequestUtils.getRequiredStringParameter(request, "terminalType");
			String termianlOutlink = ServletRequestUtils.getRequiredStringParameter(request, "termianlOutlink");
			String termianlOS = ServletRequestUtils.getRequiredStringParameter(request, "termianlOS");
			String termianlBand = ServletRequestUtils.getRequiredStringParameter(request, "termianlBand");
			String cardType = ServletRequestUtils.getRequiredStringParameter(request, "cardType");
			String cardName = ServletRequestUtils.getRequiredStringParameter(request, "cardName");
			String cardVersion = ServletRequestUtils.getRequiredStringParameter(request, "cardVersion");
			String userId = ServletRequestUtils.getRequiredStringParameter(request, "userId");
			String userName = ServletRequestUtils.getRequiredStringParameter(request, "userName");
			String policeId = ServletRequestUtils.getRequiredStringParameter(request, "policeId");
			String ifCancel = ServletRequestUtils.getRequiredStringParameter(request, "ifCancel");
			String time = ServletRequestUtils.getRequiredStringParameter(request, "regTime");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date regTime = sdf.parse(time);		
			entry.setUserZone(userZone);
			entry.setUserDepart(userDepart);
			entry.setCardName(cardName);
			entry.setCardType(cardType);
			entry.setCardVersion(cardVersion);
			entry.setIfCancel(ifCancel);
			entry.setPoliceId(policeId);
			entry.setRegTime(regTime);
			entry.setTermianlBand(termianlBand);
			entry.setTermianlOS(termianlOS);
			entry.setTermianlOutlink(termianlOutlink);
			entry.setTerminalName(terminalName);
			entry.setTerminalType(terminalType);
			entry.setUserId(userId);
			entry.setUserName(userName);

			sysTerminalService.newSysTerminal(entry);
			logService.newLog("INFO", SessionUtils.getAccount(request)
					.getUserName(), "接入终端配置", "接入终端 "
					+ entry.getTerminalName() + " 新增成功");
		} catch (Exception e) {
			logger.error("", e);
			logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "接入终端配置", "接入终端 " + terminalName
					+ " 新增不成功");
		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		writer.println("{success:true}");
		writer.close();
		return null;
	}

	@SuppressWarnings("rawtypes")
	public ActionForward showUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Long id = ServletRequestUtils.getLongParameter(request, "id");
		Map model = sysTerminalService.getSysTerminalUpdateModel(id);
		request.setAttribute("model", model);
		return mapping.findForward("update");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String terminalName = null;
		try {
			SysTerminalInfo entry = new SysTerminalInfo();
			Long id = ServletRequestUtils.getLongParameter(request, "id");
			String userZone = ServletRequestUtils.getStringParameter(request, "userZone");
			String userDepart = ServletRequestUtils.getStringParameter(request, "userDepart");

			terminalName = ServletRequestUtils.getRequiredStringParameter(request, "terminalName");
			String terminalType = ServletRequestUtils.getRequiredStringParameter(request, "terminalType");
			String termianlOutlink = ServletRequestUtils.getRequiredStringParameter(request, "termianlOutlink");
			String termianlOS = ServletRequestUtils.getRequiredStringParameter(request, "termianlOS");
			String termianlBand = ServletRequestUtils.getRequiredStringParameter(request, "termianlBand");
			String cardType = ServletRequestUtils.getRequiredStringParameter(request, "cardType");
			String cardName = ServletRequestUtils.getRequiredStringParameter(request, "cardName");
			String cardVersion = ServletRequestUtils.getRequiredStringParameter(request, "cardVersion");
			String userId = ServletRequestUtils.getRequiredStringParameter(request, "userId");
			String userName = ServletRequestUtils.getRequiredStringParameter(request, "userName");
			String policeId = ServletRequestUtils.getRequiredStringParameter(request, "policeId");
			String ifCancel = ServletRequestUtils.getRequiredStringParameter(request, "ifCancel");
			String time = ServletRequestUtils.getRequiredStringParameter(request, "regTime");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date regTime = sdf.parse(time);	
			entry.setId(id);
			entry.setUserZone(userZone);
			entry.setUserDepart(userDepart);
			entry.setCardName(cardName);
			entry.setCardType(cardType);
			entry.setCardVersion(cardVersion);
			entry.setIfCancel(ifCancel);
			entry.setPoliceId(policeId);
			entry.setRegTime(regTime);
			entry.setTermianlBand(termianlBand);
			entry.setTermianlOS(termianlOS);
			entry.setTermianlOutlink(termianlOutlink);
			entry.setTerminalName(terminalName);
			entry.setTerminalType(terminalType);
			entry.setUserId(userId);
			entry.setUserName(userName);
			sysTerminalService.updateSysTerminal(entry);
			logService.newLog("INFO", SessionUtils.getAccount(request)
					.getUserName(), "接入终端配置", "接入终端 "
					+ entry.getTerminalName() + " 修改成功");
		} catch (Exception e) {
			logger.error("", e);
			logService.newLog("ERROR", SessionUtils.getAccount(request)
					.getUserName(), "接入终端配置", "接入终端 " + terminalName
					+ " 修改不成功");
		}

		WebUtil.sendHtml(request, response, "{success:true}");

		return null;
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = ServletRequestUtils.getRequiredStringParameter(request,"ids");
		String idsArr[] = ids.split(",");
		Account account = SessionUtils.getAccount(request);
		sysTerminalService.deleteSysTerminal(idsArr, account);

		WebUtil.sendHtml(request, response, "{success:true}");

		return null;
	}
	public ActionForward download(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
            String fileName = request.getRealPath("excel")+"/moldboard.xls";
            File file = new File(fileName);
			FileInputStream is = new FileInputStream(file);
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String((fileName == null ? "file" : fileName)
							.getBytes("utf-8"), "utf-8"));
			OutputStream out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			out.flush();
			out.close();
		} catch (Exception e){
			PrintWriter writer = response.getWriter();
			writer.write("模板不存在，请确认是否被删除。");
			writer.close();
		}
		return null;
	}

	public ActionForward addMany(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
		throws Exception {
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
                        String fileName = ""+System.currentTimeMillis()+"_"+sa[sa.length - 1];
                        if(fileName.endsWith(".xls")||fileName.endsWith(".et")){
                            // 保存到文件夹中
                            String uploadFileDir = SiteContext.getInstance().uploadFileDir;
                            filePath = uploadFileDir + fileName;
                            File file = new File(filePath);

                            try {
                                item.write(file);
                            } catch (Exception e) {
                                logger.error("", e);
                            }
                        } else {
                            msg = "导入的文件不是[.xls]或者[.et]文件";
                        }
                    }
                    item.delete();
                }
            }
            /***/
            if(msg == null){
                HSSFWorkbook workbook = new HSSFWorkbook(
                    new POIFSFileSystem(
                            new FileInputStream(filePath)));
                HSSFSheet sheet = workbook.getSheetAt(0);
                int lastRowNum = sheet.getLastRowNum();
                if(lastRowNum > 5000){
                    msg = "Excel文件实际数据内容大于5000行,导入失败!<br/>请选中5002行到"+(lastRowNum+1)+"行数据,执行一次删除操作";
                } else {
                    boolean isNeedToAddMany = true;
                    List<SysTerminalInfo> list = new ArrayList<SysTerminalInfo>();
                    for ( int i = 1;i <= lastRowNum;i++){
                        HSSFRow row = sheet.getRow(i);
                        int cellNum = 0;
                        HSSFCell cell = row.getCell((short) cellNum ++);
                        if(cell != null){
                            String terminalName = cell.getStringCellValue();
                            if (terminalName.equals("") || terminalName == null){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列terminalName 不存在,导入失败";
                                isNeedToAddMany = false;break;
                            } else if (terminalName.length() > 30){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列terminalName 长度大于30,导入失败";
                                isNeedToAddMany = false;break;
                            }
                            cell = row.getCell((short )cellNum ++);
                            String terminalType = cell.getStringCellValue();
                            if(terminalType.equals("") || terminalType == null){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 terminalType 不存在,导入失败";
                                isNeedToAddMany = false;break;
                            }  else if (terminalType.length()>3){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 terminalType 长度大于3,导入失败";
                                isNeedToAddMany = false;break;
                            }
                            cell = row.getCell((short )cellNum ++);
                            String terminalOutLink = cell.getStringCellValue();
                            if(terminalOutLink.equals("") || terminalOutLink == null){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 terminalOutLink 不存在,导入失败";
                                isNeedToAddMany = false;break;
                            } else if (terminalOutLink.length()>3){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 terminalOutLink 长度大于3,导入失败";
                                isNeedToAddMany = false;break;
                            }
                            cell = row.getCell((short )cellNum ++);
                            String terminalOs = cell.getStringCellValue();
                            if(terminalOs.equals("") || terminalOs == null){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 terminalOs 不存在,导入失败";
                                isNeedToAddMany = false;break;
                            } else if (terminalOs.length()>3){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 terminalOs 长度大于3,导入失败";
                                isNeedToAddMany = false;break;
                            }
                            cell = row.getCell((short )cellNum ++);
                            String terminalBand = cell.getStringCellValue();
                            if(terminalBand.equals("") || terminalBand == null){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 terminalBand 不存在,导入失败";
                                isNeedToAddMany = false;break;
                            } else if (terminalBand.length()>3){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 terminalBand 长度大于3,导入失败";
                                isNeedToAddMany = false;break;
                            }
                            cell = row.getCell((short )cellNum ++);
                            String cardType = cell.getStringCellValue();
                            if(cardType.equals("") || cardType == null){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 cardType 不存在,导入失败";
                                isNeedToAddMany = false;break;
                            } else if (cardType.length()>3){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 cardType 长度大于3,导入失败";
                                isNeedToAddMany = false;break;
                            }
                            cell = row.getCell((short )cellNum ++);
                            String cardName = cell.getStringCellValue();
                            if(cardName.equals("") || cardName == null){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 cardName 不存在,导入失败";
                                isNeedToAddMany = false;break;
                            } else if (cardName.length()>30){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 cardName 长度大于30,导入失败";
                                isNeedToAddMany = false;break;
                            }
                            cell = row.getCell((short )cellNum ++);
                            String cardVersion = cell.getStringCellValue();
                            if(cardVersion.equals("") || cardVersion == null){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 cardVersion 不存在,导入失败";
                                isNeedToAddMany = false;break;
                            } else if (cardVersion.length()>30){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 cardVersion 长度大于30,导入失败";
                                isNeedToAddMany = false;break;
                            }
                            cell = row.getCell((short )cellNum ++);
                            String userId = cell.getStringCellValue();
                            if(userId.equals("") || userId == null){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 userId 不存在,导入失败";
                                isNeedToAddMany = false;break;
                            } else if (userId.length()>18){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 userId 长度大于18,导入失败";
                                isNeedToAddMany = false;break;
                            }
                            cell = row.getCell((short )cellNum ++);
                            String userName = cell.getStringCellValue();
                            if(userName.equals("") || userName == null){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 userName 不存在,导入失败";
                                isNeedToAddMany = false;break;
                            } else if (userName.length()>30){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 userName 长度大于30,导入失败";
                                isNeedToAddMany = false;break;
                            }
                            cell = row.getCell((short )cellNum ++);
                            String userDepart = cell.getStringCellValue();
                            if(userDepart.equals("") || userDepart == null){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 userDepart 不存在,导入失败";
                                isNeedToAddMany = false;break;
                            } else if (userDepart.length()>5){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 userDepart 长度大于5,导入失败";
                                isNeedToAddMany = false;break;
                            }
                            cell = row.getCell((short )cellNum ++);
                            String userZone = cell.getStringCellValue();
                            if(userZone.equals("") || userZone == null){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 userZone 不存在,导入失败";
                                isNeedToAddMany = false;break;
                            } else if (userZone.length()>6){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 userZone 长度大于5,导入失败";
                                isNeedToAddMany = false;break;
                            }
                            cell = row.getCell((short )cellNum ++);
                            String policeId = cell.getStringCellValue();
                            if(policeId.equals("") || policeId == null){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 policeId 不存在,导入失败";
                                isNeedToAddMany = false;break;
                            } else if (policeId.length()>50){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 policeId 长度大于50,导入失败";
                                isNeedToAddMany = false;break;
                            }
                            cell = row.getCell((short )cellNum ++);
                            String regTime = cell.getStringCellValue();
                            Date regTimeDate = null;
                            if(regTime.equals("") || regTime == null){
                                logger.warn("第"+(i+1)+"行,第" + cellNum + "列 regTime 不存在,导入时间更改为当前处理时间!");
                                regTimeDate = new Date();
                            } else if(isFix(regTime) == true){
                                regTimeDate = changeToDate(regTime);
                            } else if(isFix(regTime) == false){
                                logger.warn("第"+(i+1)+"行,第" + cellNum + "列 regTime 格式不对,导入时间更改为当前处理时间!");
                                regTimeDate = new Date();
                            }
                            cell = row.getCell((short )cellNum ++);
                            String ifCancel = cell.getStringCellValue();
                            if(ifCancel.equals("") || ifCancel == null){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 ifCancel 不存在,导入失败";
                                isNeedToAddMany = false;break;
                            } else if (isIfCancel(ifCancel)==false){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 ifCancel 不是1(是)或者0(否),导入失败";
                                isNeedToAddMany = false;
                                break;
                            }
                            cell = row.getCell((short )cellNum ++);
                            String flag = cell.getStringCellValue();
                            if(flag.equals("") || flag == null){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 flag 不存在,导入失败";
                                isNeedToAddMany = false;
                                break;
                            } else if (isFlage(flag) == false){
                                msg = "第"+(i+1)+"行,第"+cellNum+"列 flag 不是新增标记1或者修改标记2,导入失败";
                                isNeedToAddMany = false;
                                break;
                            }
                            SysTerminalInfo s = new SysTerminalInfo();
                            s.setTerminalName(terminalName);
                            s.setTerminalType(terminalType);
                            s.setTermianlOutlink(terminalOutLink);
                            s.setTermianlOS(terminalOs);
                            s.setTermianlBand(terminalBand);
                            s.setCardType(cardType);
                            s.setCardName(cardName);
                            s.setCardVersion(cardVersion);
                            s.setUserId(userId);
                            s.setUserName(userName);
                            s.setUserDepart(userDepart);
                            s.setUserZone(userZone);
                            s.setPoliceId(policeId);
                            s.setRegTime(regTimeDate);
                            s.setIfCancel(ifCancel);
                            s.setFlag(flag);
                            list.add(s);
                        }
                    }
                    if(isNeedToAddMany){
                        Account account = SessionUtils.getAccount(request);
                        msg = sysTerminalService.saveMany(list, account);
                        logService.newLog("INFO", SessionUtils.getAccount(request).getUserName(), "批量导入", "批量导入终端设备信息成功");
                    }
                }
            }
            File file = new File(filePath);
            file.delete();
            /**/
        } catch (Exception e){
            logger.error("批量导入",e);
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(), "批量导入", "批量导入终端设备信息失败");
            msg = "导入失败";
        }
		WebUtil.sendHtml(request, response, "{success:true,msg:'"+msg+"'}");
		return null;
	}

    private boolean isFlage(String flag) {
        int idx = Integer.parseInt(flag);
        if(idx == 1 || idx == 2){
            return true;
        }
        return false;
    }

    private boolean isIfCancel(String ifCancel) {
        int idx = Integer.parseInt(ifCancel);
        if(idx == 1 || idx == 0){
            return true;
        }
        return false;
    }

    private boolean isFix(String regTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            sdf.parse(regTime);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private Date changeToDate(String regTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return sdf.parse(regTime);
    }

    /**
     * 终端加固
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateTerminalAddress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        String msg = null;
		try{
            String ip = ServletRequestUtils.getStringParameter(request, "ip");
            Long id = ServletRequestUtils.getLongParameter(request, "id");
            int port = Integer.parseInt(ServletRequestUtils.getStringParameter(request, "port"));
            Account account = SessionUtils.getAccount(request);
            msg = sysTerminalService.updateTerminalAddress(id,ip,port);
            logService.newLog("INFO",SessionUtils.getAccount(request).getUserName(),"终端管理","修改终端加固成功");
        } catch (Exception e){
            logger.error("终端管理",e);
            logService.newLog("ERROR",SessionUtils.getAccount(request).getUserName(),"终端管理","修改终端加固失败");
            msg = "加固失败";
        }
		WebUtil.sendHtml(request, response, "{success:true,msg:'"+msg+"'}");

		return null;
	}

    public ActionForward showTerminalAddress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        String json = null;
		try{
            Account account = SessionUtils.getAccount(request);
            TerminalAddress t = sysTerminalService.getTerminalAddress();
            json = "{success:true,total:1,rows:[{id:'"+t.getId()+"',ip:'"+t.getIp()+"',port:'"+t.getPort()+"'}]}";
            logService.newLog("INFO",SessionUtils.getAccount(request).getUserName(),"终端管理","读取终端加固成功");
        } catch (Exception e){
            logger.error(e.getMessage());
            logService.newLog("ERROR",SessionUtils.getAccount(request).getUserName(),"终端管理","读取终端加固失败");
            json = "{success:true,total:1,rows:[{id:'',ip:'',port:''}]}";
        }
		WebUtil.sendHtml(request, response, json);

		return null;
	}

    /**
     * 清空表
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteSysTerminalInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        String msg = null;
        try{
            Account account = SessionUtils.getAccount(request);
            sysTerminalService.truncateSysTerminal();
            msg = "清空成功,点击[确定]返回列表!";
            logService.newLog("INFO",account.getUserName(),"终端设备管理","清空终端设备信息成功");

        }catch (Exception e) {
            logger.info("终端设备管理"+e.getMessage());
            logService.newLog("ERROR", SessionUtils.getAccount(request).getUserName(),
                    "终端设备管理", "清空终端设备信息失败");
            msg = "清空失败"+e.getMessage();
        }

		WebUtil.sendHtml(request, response, "{success:true,msg:'"+msg+"'}");

		return null;
	}


	/**
	 * 操作系统查看
	 * @author 钱晓盼
	 * @2012-04-04
	 */
	public ActionForward osQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String osPath = "/TSRS/asset/asset_QueryCompSelect.asp";
        TerminalAddress t = sysTerminalService.getTerminalAddress();
		String url = "http://" + t.getIp() + ":" + t.getPort() + osPath;
		WebUtil.sendHtml(request, response, "{success:true,url:'"+url+"'}");
		return null;
	}
	
	/**
	 * 网络查看
	 * @author 钱晓盼
	 * @2012-04-04
	 */
	public ActionForward netQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String netPath = "/TSRS/desktop/compNetworkSettingsList.asp";
        TerminalAddress t = sysTerminalService.getTerminalAddress();
		String url = "http://" + t.getIp() + ":" + t.getPort() + netPath;
		WebUtil.sendHtml(request, response, "{success:true,url:'"+url+"'}");
		return null;
	}
	
	/**
	 * 进程查看
	 * @author 钱晓盼
	 * @2012-04-04
	 */
	public ActionForward processQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String processPath = "/TSRS/process/processQueryCompSelect.asp";
        TerminalAddress t = sysTerminalService.getTerminalAddress();
		String url = "http://" + t.getIp() + ":" + t.getPort() + processPath;
		WebUtil.sendHtml(request, response, "{success:true,url:'"+url+"'}");
		return null;
	}
	
}
