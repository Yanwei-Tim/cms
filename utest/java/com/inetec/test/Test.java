package com.inetec.test;

import com.hzjava.monitorcenter.domain.SysTerminalInfo;
import com.hzjava.monitorcenter.utils.ServiceResponse;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-26
 * Time: 下午5:48
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static ServiceResponse callService(String[][] params) {
//        String serviceUrl = "http://127.0.0.1:8080/sslservice/Service";
        HttpClient client = new HttpClient();

        client.getHttpConnectionManager().getParams().setConnectionTimeout(
                5 * 1000);
        client.getHttpConnectionManager().getParams().setSoTimeout(5 * 1000);

        PostMethod post = new PostMethod("http://172.16.2.2:8080/monitorservice/monitorService");
        post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5 * 1000);
        post.addRequestHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=UTF-8");

        for (String[] param : params) {
            post.addParameter(param[0], param[1]);
        }

        ServiceResponse response = new ServiceResponse();

        int statusCode = 0;
        try {
            statusCode = client.executeMethod(post);
            //logger.info("statusCode:" + statusCode);
            System.out.println("statusCode=="+statusCode);
            response.setCode(statusCode);
            if (statusCode == 200) {
                String data = post.getResponseBodyAsString();
                //logger.info("data:" + data);
                response.setData(data);
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        return response;
    }
    public static void main(String [] arg){
//        String[][] params = new String[][] {
//                { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
//                {"Command","ping"},
//                { "deviceip", "172.16.2.6" }
//        };
//        ServiceResponse response= callService(params);
//        System.out.println(response.getCode());
//        System.out.println(response.getData());

        SysTerminalInfo s = new SysTerminalInfo();
        s.setTerminalName("323");
        JSONObject jsonObject = JSONObject.fromObject(s);
        System.out.println(jsonObject.toString());
    }
}