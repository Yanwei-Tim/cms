package com.hzjava.monitorcenter.web.action;

import com.hzjava.monitorcenter.domain.Account;
import com.hzjava.monitorcenter.domain.SnmpOid;
import com.hzjava.monitorcenter.service.SnmpOidService;
import com.hzjava.monitorcenter.web.SessionUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-27
 * Time: 下午1:50
 * To change this template use File | Settings | File Templates.
 */
public class SnmpOidAction extends DispatchActionSupport {
    private static final Logger logger = Logger.getLogger(SnmpOidAction.class);
    private SnmpOidService snmpOidService;


    public void setSnmpOidService(SnmpOidService snmpOidService) {
        this.snmpOidService = snmpOidService;
    }

    public ActionForward index(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int start = ServletRequestUtils.getIntParameter(request, "start", 0);
        int limit = ServletRequestUtils.getIntParameter(request, "limit", 15);
        int pageIndex = start / limit + 1;
        Map model = snmpOidService.listByPage(pageIndex);
        request.setAttribute("model", model);
        return mapping.findForward("index");
    }

    public ActionForward Add(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String name = ServletRequestUtils.getStringParameter(request,"name");
        String company = ServletRequestUtils.getStringParameter(request,"company");
        String type = ServletRequestUtils.getStringParameter(request,"type");
        String cpuuse = ServletRequestUtils.getStringParameter(request,"cpuuse");
        String disktotal = ServletRequestUtils.getStringParameter(request,"disktotal");
        String diskuse = ServletRequestUtils.getStringParameter(request,"diskuse");
        String memtotal = ServletRequestUtils.getStringParameter(request,"memtotal");
        String memuse = ServletRequestUtils.getStringParameter(request,"memuse");
        String curconn = ServletRequestUtils.getStringParameter(request,"curconn");
        String snmpver = ServletRequestUtils.getStringParameter(request,"snmpver");
        String msg = null;
        Account account = SessionUtils.getAccount(request);
        if(account!=null){
            try {
                SnmpOid snmpOid = new SnmpOid();
                snmpOid.setCompany(company);
                snmpOid.setCpuuse(cpuuse);
                snmpOid.setCurconn(curconn);
                snmpOid.setDisktotal(disktotal);
                snmpOid.setDiskuse(diskuse);
                snmpOid.setMemtotal(memtotal);
                snmpOid.setMemuse(memuse);
                snmpOid.setName(name);
                snmpOid.setType(type);
                snmpOid.setSnmpver(snmpver);
                snmpOidService.addSnmpOid(snmpOid);
                msg = "新增成功";
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("", e);

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

    public ActionForward Update(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = ServletRequestUtils.getStringParameter(request,"equOidId");
        String name = ServletRequestUtils.getStringParameter(request,"name");
        String company = ServletRequestUtils.getStringParameter(request,"company");
        String type = ServletRequestUtils.getStringParameter(request,"type");
        String cpuuse = ServletRequestUtils.getStringParameter(request,"cpuuse");
        String disktotal = ServletRequestUtils.getStringParameter(request,"disktotal");
        String diskuse = ServletRequestUtils.getStringParameter(request,"diskuse");
        String memtotal = ServletRequestUtils.getStringParameter(request,"memtotal");
        String memuse = ServletRequestUtils.getStringParameter(request,"memuse");
        String curconn = ServletRequestUtils.getStringParameter(request,"curconn");
        String snmpver = ServletRequestUtils.getStringParameter(request,"snmpver");
        String msg = null;
        Account account = SessionUtils.getAccount(request);
        if(account!=null){
            try {
                int snmpOidId = Integer.parseInt(id);
                SnmpOid snmpOid = snmpOidService.FindSnmpOidById(snmpOidId);
                snmpOid.setCompany(company);
                snmpOid.setCpuuse(cpuuse);
                snmpOid.setCurconn(curconn);
                snmpOid.setDisktotal(disktotal);
                snmpOid.setDiskuse(diskuse);
                snmpOid.setMemtotal(memtotal);
                snmpOid.setMemuse(memuse);
                snmpOid.setName(name);
                snmpOid.setType(type);
                snmpOid.setSnmpver(snmpver);
                snmpOidService.UpdateSnmpOid(snmpOid);
                msg = "编辑成功";
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("", e);

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

    public ActionForward Delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = ServletRequestUtils.getStringParameter(request,"equOidId");
        String msg = null;
        Account account = SessionUtils.getAccount(request);
        if(account!=null){
            try {
                int snmpOidId = Integer.parseInt(id);
                SnmpOid snmpOid = snmpOidService.FindSnmpOidById(snmpOidId);
               snmpOidService.DeleteSnmpOid(snmpOid);
                msg = "删除成功";
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("", e);

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


}
