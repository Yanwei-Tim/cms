package com.hzjava.monitorcenter.svgmap;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: cx
 * Date: 13-1-5
 * Time: 下午1:57
 * To change this template use File | Settings | File Templates.
 */
public class DownloadFileServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DownloadFileServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OutputStream o = response.getOutputStream();
        byte b[] = new byte[1024];
//        File fileLoad = new File(System.getProperty("monitor.home"), "SVGView.exe");
        String  downsrc= System.getProperty("monitor.home")+"/download/";
        File fileLoad = new File( downsrc , "SVGView.exe");
        logger.debug(System.getProperty("monitor.home") + "caixiang123");
        response.setHeader("Content-disposition", "attachment;filename=SVGView.exe");
        response.setContentType("application/x-tar");
        long fileLength = fileLoad.length();
        String length = String.valueOf(fileLength);
        response.setHeader("Content_Length", length);
        FileInputStream in = new FileInputStream(fileLoad);
        int n = 0;
        while ((n = in.read(b)) != -1) {
            o.write(b, 0, n);
        }
    }
}
