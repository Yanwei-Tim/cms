package com.hzjava.monitorcenter.svgmap;

import com.hzjava.monitorcenter.domain.Equipment;
import com.hzjava.monitorcenter.domain.EquipmentLine;
import com.hzjava.monitorcenter.domain.EquipmentPosition;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cx
 * Date: 12-12-25
 * Time: 下午6:11
 * To change this template use File | Settings | File Templates.
 */
public class MyEquipmentDao extends BaseHibernateDAO {
    private static SessionFactory sessionFactory;

    public ArrayList<Equipment> findAllEquipments() {
        Session session = getSession();
//		String sql = "select * from user";
        String hql = "from com.hzjava.monitorcenter.domain.Equipment";
        ArrayList<Equipment> list = (ArrayList<Equipment>) session.createQuery(hql).list();
        session.close();
        return list;
    }

    public ArrayList<EquipmentVo> findAllEquipmentVos() {
        Session session = getSession();
//		String sql = "select * from user";
        session.beginTransaction();
        String hql = "select new com.hzjava.monitorcenter.svgmap.EquipmentVo(p.id, e.equName,e.equIconCode, e.ip,p.ex,p.ey,e.netStation) " +
                "from com.hzjava.monitorcenter.domain.Equipment e,com.hzjava.monitorcenter.domain.EquipmentPosition p " +
                "where e.id=p.equipmentid and e.monitorUsed='Y'";
        ArrayList<EquipmentVo> list = (ArrayList<EquipmentVo>) session.createQuery(hql).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public ArrayList<EquipmentLine> findAllEquipmentLines() {
        Session session = getSession();
//		String sql = "select * from user";
        String hql = "from com.hzjava.monitorcenter.domain.EquipmentLine";
        ArrayList<EquipmentLine> list = (ArrayList<EquipmentLine>) session.createQuery(hql).list();
        session.close();
        return list;
    }
    
    public void addLine(EquipmentLine equipmentLine) {
        Session session = getSession();
        session.beginTransaction();
        session.save(equipmentLine);
        session.getTransaction().commit();
        session.close();
    }

    public EquipmentLine findLineById(int id) {
        Session session = getSession();
        EquipmentLine line = (EquipmentLine) session.get("com.hzjava.monitorcenter.domain.EquipmentLine", id);
        session.close();
        return line;
    }

    public ArrayList<EquipmentLine> findEquipmentLinesByXY(String x1, String y1, String x2, String y2) {
        Session session = getSession();
//		String sql = "select * from user";
        String hql = "select e from com.hzjava.monitorcenter.domain.EquipmentLine e where e.x1=? and e.y1=? and e.x2=? and e.y2=?";
        Query query = session.createQuery(hql);
        query.setString(0, x1).setString(1,y1).setString(2, x2).setString(3, y2);
        ArrayList<EquipmentLine> list = (ArrayList<EquipmentLine>) query.list();
        session.close();
        return list;
    }

    public void delLine(EquipmentLine equipmentLine) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(equipmentLine);
        session.getTransaction().commit();
        session.close();
    }

    public EquipmentPosition findEquipmentVoById(int id) {
        Session session = getSession();
        EquipmentPosition line = (EquipmentPosition) session.get("com.hzjava.monitorcenter.domain.EquipmentPosition", id);
        session.close();
        return line;
    }

    public void updEquipmentPosition(EquipmentPosition equipmentPosition) {
        Session session = getSession();
        session.beginTransaction();
        session.update(equipmentPosition);
        session.getTransaction().commit();
        session.close();
    }

    public void addEquipmentPosition(EquipmentPosition equipmentPosition) {
        Session session = getSession();
        session.beginTransaction();
        session.save(equipmentPosition);
        session.getTransaction().commit();
        session.close();
    }

    public ArrayList<Equipment> findEquipmentsByIp(String ip) {
        Session session = getSession();
        String hql = "select e from com.hzjava.monitorcenter.domain.Equipment e where e.ip=?";
        Query query = session.createQuery(hql);
        query.setString(0, ip);
        ArrayList<Equipment> list = (ArrayList<Equipment>) query.list();
        session.close();
        return list;
    }

//    public void addEquipmentPositionByEquipmentsIp(String ip) {
//        Equipment equipment = findEquipmentsByIp(ip).get(0);
//        EquipmentPosition equipmentPosition = new EquipmentPosition();
//        equipmentPosition.setEquipmentid(Integer.parseInt(equipment.getId().toString()));
//        equipmentPosition.setEy("198");
//        String st = equipment.getTopologyStation();
//        if("1".equals(st)){
//            equipmentPosition.setEx("55");
//        }else if("2".equals(st)){
//            equipmentPosition.setEx("375");
//        }else if("3".equals(st)){
//            equipmentPosition.setEx("745");
//        }else if("4".equals(st)){
//            equipmentPosition.setEx("982");
//        }
//        addEquipmentPosition(equipmentPosition);
//    }

    public void addEquipmentPositionByEquipmentsIp(Equipment equipment) {
        EquipmentPosition equipmentPosition = new EquipmentPosition();
        equipmentPosition.setEquipmentid(Integer.parseInt(equipment.getId().toString()));
        equipmentPosition.setEy("198");
        String st = equipment.getTopologyStation();
        if("1".equals(st)){
            equipmentPosition.setEx("55");
        }else if("2".equals(st)){
            equipmentPosition.setEx("375");
        }else if("3".equals(st)){
            equipmentPosition.setEx("745");
        }else if("4".equals(st)){
            equipmentPosition.setEx("982");
        }
        addEquipmentPosition(equipmentPosition);
    }

    public ArrayList<EquipmentPosition> findEquipmentPositionByEquipmentId(int id) {
        Session session = getSession();
//		String sql = "select * from user";
        String hql = "select e from com.hzjava.monitorcenter.domain.EquipmentPosition e where e.equipmentid=?";
        Query query = session.createQuery(hql);
        query.setInteger(0, id);
        ArrayList<EquipmentPosition> list = (ArrayList<EquipmentPosition>) query.list();
        session.close();
        return list;
    }

    public void delEquipmentPositionByEquipmentId(int id) {
        EquipmentPosition equipmentPositio = findEquipmentPositionByEquipmentId(id).get(0);
        delEquipmentPosition(equipmentPositio);
    }

    public void delEquipmentPosition(EquipmentPosition equipmentPosition) {
        Session session = getSession();
        session.beginTransaction();
        session.delete(equipmentPosition);
        session.getTransaction().commit();
        session.close();
    }


}
