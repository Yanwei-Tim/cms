package com.hzjava.monitorcenter.svgmap;

/**
 * Created by IntelliJ IDEA.
 * User: cx
 * Date: 12-12-26
 * Time: 上午10:58
 * To change this template use File | Settings | File Templates.
 */

import com.hzjava.monitorcenter.domain.Equipment;

public class TestSvgDom {
    public static void main(String args[]){
        MyEquipmentDao equipmentDao = new MyEquipmentDao();
//        equipmentDao.findAllEquipmentVos();
//        Session session = DBUtil.createSession();
//        session.beginTransaction();
//        String hql = "from com.hzjava.monitorcenter.domain.EquipmentPosition";
//        ArrayList<EquipmentPosition> list = (ArrayList<EquipmentPosition>) session.createQuery(hql).list();
//        session.getTransaction().commit();
//        session.close();
//        for(EquipmentPosition e: list){
//           System.out.println(e.getEquipmentid());
//        }
        Equipment equipment= equipmentDao.findEquipmentsByIp("(例):1.1.1.1").get(0);
        int a = 0;
    }
}
