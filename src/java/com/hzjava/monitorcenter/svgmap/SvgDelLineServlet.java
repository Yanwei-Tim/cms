package com.hzjava.monitorcenter.svgmap;

import com.hzjava.monitorcenter.domain.EquipmentLine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: cx
 * Date: 13-1-2
 * Time: 下午5:15
 * To change this template use File | Settings | File Templates.
 */
public class SvgDelLineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lineatt=request.getParameter("lineatt");
        MyEquipmentDao equipmentDao = new MyEquipmentDao();
        String[] xy = lineatt.split("_");
        ArrayList<EquipmentLine> list = equipmentDao.findEquipmentLinesByXY(xy[0], xy[1], xy[2], xy[3]);
        for (EquipmentLine l : list) {
            equipmentDao.delLine(l);
        }
    }
}
