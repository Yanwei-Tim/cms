package com.hzjava.monitorcenter.svgmap;

import com.hzjava.monitorcenter.domain.EquipmentPosition;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: cx
 * Date: 12-12-28
 * Time: 下午6:12
 * To change this template use File | Settings | File Templates.
 */
public class SvgJspServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("/svgwork/vv.jsp").forward(request, response)
        String stt=request.getParameter("stt");
        System.out.println(stt);
        String[] sts = stt.split("_");
        int id = Integer.parseInt( sts[0] );
        MyEquipmentDao myEquipmentDao = new MyEquipmentDao();
        EquipmentPosition evo = myEquipmentDao.findEquipmentVoById(id);
        evo.setEx(sts[1]);
        evo.setEy(sts[2]);
        myEquipmentDao.updEquipmentPosition(evo);
    }
}
