package com.hzjava.monitorcenter.web.action;

import com.hzjava.monitorcenter.service.VpnServiceResponse;
import com.hzjava.monitorcenter.utils.StringTrim;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-11-15
 * Time: ????10:44
 * To change this template use File | Settings | File Templates.
 */
public class VpnAction  extends DispatchActionSupport {

    private static final Logger logger = Logger.getLogger(VpnAction.class);
    private String vpnip ="";
    private String vpnport="";
    private static  Properties prop= new Properties();
    private static StringTrim st = new StringTrim();

    public VpnAction() throws IOException {
        prop.load(VpnAction.class.getResourceAsStream("/config.properties"));
        vpnip = prop.getProperty("vpnip");
        vpnport = prop.getProperty("vpnport");
    }


    public ActionForward selectInfo (ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response){
        logger.info("????Vpn???????????????");
        String json ="";

        try {
            String[][] a = {};
            json =callService(a,"http://"+vpnip+":"+vpnport+"/sslvpn/McReturn.action");
            json =st.trim(json);
            logger.info("??????????Json?"+json);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println(json);
            writer.close();

        } catch (Exception e) {
           logger.info("selectInfo" + e);
        }
        return mapping.findForward("index");
    }

    public void  block(ActionMapping mapping, ActionForm form,
                        HttpServletRequest request, HttpServletResponse response){
        logger.info("????Vpn???????????????");
        String json = "";

        try {
            String ip = ServletRequestUtils.getStringParameter(request, "ip");
            String name = ServletRequestUtils.getStringParameter(request,"name");
            String[][] params = new String[][] {
                    { "ip", ip },
                    { "name", name }
            };
            json = callService(params,"http://"+vpnip+":"+vpnport+"/sslvpn/UserRecoverOrBlockAction_Block.action");
            json =st.trim(json);
            logger.info("??????????Json?"+json);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println(json);
            writer.close();
        } catch (Exception e) {
            logger.info("block" + e);
        }
    }

    public void  recover(ActionMapping mapping, ActionForm form,
                       HttpServletRequest request, HttpServletResponse response){
        logger.info("????Vpn???????????????");
        String json = "";

        try {
            String ip = ServletRequestUtils.getStringParameter(request,"ip");
            String name = ServletRequestUtils.getStringParameter(request,"name");
            String[][] params = new String[][] {
                    { "ip", ip },
                    { "name", name }
            };

            json = callService(params,"http://"+vpnip+":"+vpnport+"/sslvpn/UserRecoverOrBlockAction_Recover.action");
            json =st.trim(json);
            logger.info("??????????Json?"+json);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println(json);
            writer.close();
        } catch (Exception e) {
            logger.info("recover" + e);
        }
    }

    public void  cutScreen(ActionMapping mapping, ActionForm form,
                         HttpServletRequest request, HttpServletResponse response){
        logger.info("????Vpn???????????????");
        String json = "";

        try {
            String ip = ServletRequestUtils.getStringParameter(request,"ip");
            String name = ServletRequestUtils.getStringParameter(request,"name");
            String commond = "viewvpn";
            String[][] params = new String[][] {
                    { "ip", ip },
                    { "name", name },
                    {"commond",commond}
            };

            json = callService(params,"http://"+vpnip+":"+vpnport+"/sslvpn/UserSnapshotOrLocationAction_Snapshot.action");
            json =st.trim(json);
            logger.info("??????????Json?"+json);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println(json);
            writer.close();
        } catch (Exception e) {
            logger.info("cutScreen" + e);
        }
    }

    public void  uploadLocation(ActionMapping mapping, ActionForm form,
                           HttpServletRequest request, HttpServletResponse response){
        logger.info("????Vpn???????????????");
        String json = "";

        try {
            String ip = ServletRequestUtils.getStringParameter(request,"ip");
            String name = ServletRequestUtils.getStringParameter(request,"name");
            String commond = "location";
            String[][] params = new String[][] {
                    { "ip", ip },
                    { "name", name },
                    {"commond",commond}
            };

            json = callService(params,"http://"+vpnip+":"+vpnport+"/sslvpn/UserSnapshotOrLocationAction_Snapshot.action");
            json =st.trim(json);
            logger.info("??????????Json?"+json);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println(json);
            writer.close();
        } catch (Exception e) {
            logger.info("uploadLocation" + e);
        }
    }




    public static String callService(String[][] params,String methodurl) {
        logger.info("????callService????");
        String data="";
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(
                5 * 1000);
        client.getHttpConnectionManager().getParams().setSoTimeout(5 * 1000);
//        GetMethod get =new GetMethod(methodurl);
//        get.addRequestHeader("","");
        PostMethod post = new PostMethod(methodurl);

        post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5 * 1000);
        post.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=UTF-8");
        for (String[] param : params) {
            post.addParameter(param[0], param[1]);
        }
        VpnServiceResponse responseVpn = new VpnServiceResponse();

        int statusCode = 0;
        try {
            statusCode = client.executeMethod(post);
            logger.info("statusCode:" + statusCode);
            System.out.println("statusCode=="+statusCode);
            responseVpn.setCode(statusCode);
            if (statusCode == 200) {
//                 data = post.getResponseBodyAsString();
                 data=new String(post.getResponseBodyAsString().getBytes("ISO-8859-1"),"UTF-8");
                 logger.info("DATA:"+data);
                 responseVpn.setData(data);
            }
        } catch (Exception e) {
           logger.info("callService"+ e);
        }
        return data;
    }

    public static String callServiceSelect(String[][] params,String methodurl) {
        logger.info("????callService????");
        String data="";
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(
                5 * 1000);
        client.getHttpConnectionManager().getParams().setSoTimeout(5 * 1000);
//        GetMethod get =new GetMethod(methodurl);
//        get.addRequestHeader("","");
        PostMethod post = new PostMethod(methodurl);

        post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5 * 1000);
        post.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=UTF-8");
        for (String[] param : params) {
            post.addParameter(param[0], param[1]);
        }
        VpnServiceResponse responseVpn = new VpnServiceResponse();

        int statusCode = 0;
        try {
            statusCode = client.executeMethod(post);
            logger.info("statusCode:" + statusCode);
            System.out.println("statusCode=="+statusCode);
            responseVpn.setCode(statusCode);
            if (statusCode == 200) {
//                data = post.getResponseBodyAsString();
                data=new String(post.getResponseBodyAsString().getBytes("ISO-8859-1"),"UTF-8");
                logger.info("DATA????:"+data);
                responseVpn.setData(data);
            }
        } catch (Exception e) {
            logger.info("?????????"+ e);
        }
        return data;
    }


}
