package com.hzjava.monitorcenter.ca;

import com.hzjava.monitorcenter.service.VpnServiceResponse;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.struts.DispatchActionSupport;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: hhm
 * Date: 12-11-23
 * Time: 上午9:01
 * To change this template use File | Settings | File Templates.
 */
public class EndUser extends DispatchActionSupport {
    private static final Logger logger = Logger.getLogger(EndUser.class);
    
    private static final String revokeCaUrl = "http://192.168.1.232:8000/ca/CmsCommandAction_revokeUserCa.action";
    private static final String releaseCaUrl = "http://192.168.1.232:8000/ca/CmsCommandAction_releaseUserCa.action";
    private String caip ="";
    private String caport="";
    private String ldapbasedn;
    private static Properties prop= new Properties();

    public EndUser() throws IOException {
        prop.load(EndUser.class.getResourceAsStream("/config.properties"));
        caip = prop.getProperty("caip");
        caport = prop.getProperty("caport");
        ldapbasedn= prop.getProperty("ldapbasedn");
    }

    public static String getUserData(String DN, Attributes attrs) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        StringBuffer json=new StringBuffer();
        json.append("{");
        json.append("cn:'"+ LdapUtils.getAttrValue(attrs, "cn")+"',");
        json.append("hzihdatadn:'"+DN+"',") ;
        json.append("hzihpassword:'"+ LdapUtils.getAttrValue(attrs, "hzihpassword") +"',");
        json.append("hzihprovince:'"+ LdapUtils.getAttrValue(attrs, "hzihprovince") +"',");
        json.append("hzihcity:'"+      LdapUtils.getAttrValue(attrs, "hzihcity")+"',");
        json.append("hzihorganization:'"+ LdapUtils.getAttrValue(attrs, "hzihorganization")+"',");
        json.append("hzihinstitutions:'"+ LdapUtils.getAttrValue(attrs, "hzihinstitutions")+"',");
        json.append("hzihid:'"+ LdapUtils.getAttrValue(attrs, "hzihid")+"',");
        json.append("hzihphone:'"+ LdapUtils.getAttrValue(attrs, "hzihphone")+"',");
        json.append("hzihaddress:'"+ LdapUtils.getAttrValue(attrs, "hzihaddress")+"',");
        json.append("hzihemail:'"+ LdapUtils.getAttrValue(attrs, "hzihemail")+"',");
        json.append("hzihjobnumber:'"+ LdapUtils.getAttrValue(attrs, "hzihjobnumber")+"',");
        String validate =  LdapUtils.getAttrValue(attrs, "hzihcavalidity");
        String certificatetype =  LdapUtils.getAttrValue(attrs, "hzihcertificatetype");
        if(certificatetype!=null){
            json.append("certificatetype:'"+certificatetype+"',");
        }
        if(validate!=null) {
            json.append("hzihcavalidity:'"+validate+"',");
        }
        String hzihcacreatedate = LdapUtils.getAttrValue(attrs, "hzihcacreatedate");
        if(hzihcacreatedate!=null){
            json.append("hzihcacreatedate:'"+simpleDateFormat.format(new Date(Long.parseLong(hzihcacreatedate)))+"',");
            json.append("hzihcaenddate:'"+simpleDateFormat.format(new Date(Long.parseLong(LdapUtils.getAttrValue(attrs, "hzihcaenddate"))))+"',");
        }
        json.append("hzihcaentries:'"+ LdapUtils.getAttrValue(attrs, "hzihcaentries")+"',");
        json.append("hzihparentcadn:'"+ LdapUtils.getAttrValue(attrs, "hzihparentca")+"',");
        json.append("hzihcastatus:'"+ LdapUtils.getAttrValue(attrs, "hzihcastatus")+"'");
        json.append("}");
        return json.toString();
    }




    public static String callService(String[][] params,String methodurl) {
        String data="";
        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().getParams().setConnectionTimeout(
                5 * 1000);
        client.getHttpConnectionManager().getParams().setSoTimeout(5 * 1000);
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
                data = post.getResponseBodyAsString();
                logger.info("date"+data);
                responseVpn.setData(data);
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return data;
    }




    public void revokeCa(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException, IOException {
        String DN = ServletRequestUtils.getStringParameter(request, "DN");
        String CN = ServletRequestUtils.getStringParameter(request,"CN");
        String[][] params = new String[][] {
                { "DN", DN },
                { "CN", CN }
        };
        String json =  callService(params,"http://"+caip+":"+caport+"/ca/CmsCommandAction_revokeUserCa.action");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.close();
    }

    public void release(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException, IOException {
        String DN = ServletRequestUtils.getStringParameter(request, "DN");
        String CN = ServletRequestUtils.getStringParameter(request,"CN");
        String validate = ServletRequestUtils.getStringParameter(request, "validate");
        String hzihprovince = ServletRequestUtils.getStringParameter(request,"hzihprovince");
        String hzihcity = ServletRequestUtils.getStringParameter(request, "hzihcity");
        String hzihid = ServletRequestUtils.getStringParameter(request,"hzihid");
        String hzihorganization = ServletRequestUtils.getStringParameter(request, "hzihorganization");
        String hzihinstitutions = ServletRequestUtils.getStringParameter(request,"hzihinstitutions");
        String password = ServletRequestUtils.getStringParameter(request,"password");
        String keyLength = ServletRequestUtils.getStringParameter(request,"keyLength");
        String type = ServletRequestUtils.getStringParameter(request,"type");
        String[][] params = new String[][] {
                { "DN", DN },
                { "CN", CN },
                { "validate", validate },
                { "hzihprovince", hzihprovince },
                { "hzihcity", hzihcity },
                { "hzihid", hzihid },
                { "hzihorganization", hzihorganization },
                { "hzihinstitutions", hzihinstitutions },
                { "password", password },
                { "keyLength", keyLength },
                { "type", type }
        };

        String json =  callService(params,"http://"+caip+":"+caport+"/ca/CmsCommandAction_releaseUserCa.action");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.close();
    }



    public void getEndUser(ActionMapping mapping, ActionForm form,HttpServletRequest request,
                           HttpServletResponse response) throws ServletRequestBindingException, IOException {
        String start = ServletRequestUtils.getStringParameter(request, "start");
        String limit = ServletRequestUtils.getStringParameter(request,"limit");
        LdapUtils ldapUtil = new LdapUtils();
        DirContext ctx = ldapUtil.getLdapDirContext();
        SearchControls sc = new SearchControls();
        sc.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration results = null;
        try {
            //baseDn配置
            results = ctx.search(ldapbasedn, "(&(objectclass=hzihuser)(|(hzihcastatus=2)(hzihcastatus=3)))",sc);
        } catch (NamingException e) {
            logger.info("end User 线程查找ldap数据出错!"+e.getMessage());
        }
        List<String> list = new ArrayList<>();
        if(results!=null){
            try {
                while (results.hasMore()) {
                    SearchResult sr = (SearchResult) results.next();
                    Attributes atts = sr.getAttributes();
                    String DN=sr.getNameInNamespace();
                    String entryJsonData = getUserData(DN,atts);
                    list.add(entryJsonData);
                }
            } catch (NamingException e) {
                logger.info("endUser 线程遍历ldap数据出错!"+e.getMessage());
            }
        }
       String json = "{totalCount:"+list.size()+",root:["+ getReturnData(Integer.parseInt(start),Integer.parseInt(limit),list)+ "]}";

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println(json);
        writer.close();
        LdapUtils.closeLdap(ctx);
    }


    private String getReturnData(Integer first, Integer limitInt, List<String> list) {
        StringBuffer showData=new StringBuffer();
        int end=first+limitInt;
        int index = end>list.size()?list.size():end;
        for(int i=first;i<index;i++){
            showData.append(list.get(i));
            if(i != index-1){
                showData.append(",");
            }
        }
        return showData.toString();
    }
}
