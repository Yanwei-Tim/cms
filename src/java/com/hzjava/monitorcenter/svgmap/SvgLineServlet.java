package com.hzjava.monitorcenter.svgmap;

import com.hzjava.monitorcenter.domain.EquipmentLine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: cx
 * Date: 12-12-31
 * Time: 下午5:08
 * To change this template use File | Settings | File Templates.
 */
public class SvgLineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lineatt=request.getParameter("lineatt");
        MyEquipmentDao equipmentDao = new MyEquipmentDao();
        int i = lineatt.indexOf("_");
        if(i==-1){
            System.out.println(lineatt);
            int id = Integer.parseInt(lineatt);
            equipmentDao.delLine( equipmentDao.findLineById(id) );
        } else {
            System.out.println(lineatt);
            String[] xy = lineatt.split("_");
            equipmentDao.addLine(new EquipmentLine(null,xy[0],xy[1],xy[2],xy[3]));
        }
    }
}
