package com.hzjava.monitorcenter.svgmap;

import com.hzjava.monitorcenter.utils.MonitorServiceUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: cx
 * Date: 13-1-6
 * Time: 下午4:27
 * To change this template use File | Settings | File Templates.
 */
public class AjaxPingIpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String str=request.getParameter("pingip");
        String[] ss = str.split("_");
        String[][] params = new String[][] {
                { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                {"Command","ping"},
                { "deviceip", ss[0] },
                {"netStation",ss[1]}
        };
        String ipflag = MonitorServiceUtil.callService(params).getData();

        String im = "off.gif";
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter out=response.getWriter();
        if("true".equals(ipflag)){
            im = "ok.png";
        }
        out.println(im);
        out.flush();
    }
}
