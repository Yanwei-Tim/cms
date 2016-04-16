package com.hzjava.monitorcenter.service;

import cn.collin.commons.domain.PageResult;
import com.hzjava.monitorcenter.dao.EquipmentDao;
import com.hzjava.monitorcenter.dao.EquipmentStatusDao;
import com.hzjava.monitorcenter.domain.Equipment;
import com.hzjava.monitorcenter.domain.EquipmentStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class EquipmentStatusServiceImpl implements EquipmentStatusService {
    private EquipmentStatusDao equipmentStatusDao;
    private EquipmentDao equipmentDao;

    public EquipmentStatusDao getEquipmentStatusDao() {
        return equipmentStatusDao;
    }

    public void setEquipmentStatusDao(EquipmentStatusDao equipmentStatusDao) {
        this.equipmentStatusDao = equipmentStatusDao;
    }

    public EquipmentDao getEquipmentDao() {
        return equipmentDao;
    }

    public void setEquipmentDao(EquipmentDao equipmentDao) {
        this.equipmentDao = equipmentDao;
    }


    @Override
    public String getAll(int start, int limit) {
       String json = "{success:true,total:";
        List<EquipmentStatus> lists = equipmentStatusDao.findAll();
        int total = lists.size();
        json += total + ",rows:[";
        int index = 0;
        int count = 0;
        for (EquipmentStatus t : lists) {
            Equipment equipment= (Equipment) equipmentDao.getById(t.getDeviceId());
            if(index == start && count < limit){
               json +="{id:'"+t.getId()+"',deviceName:'"+equipment.getEquName()+"',ip:'"+equipment.getIp()+"',deviceId:'"+equipment.getId()+
                    "',status:'"+t.getStatus()+"',type:'"+t.getType()+
                        "',createtime:'"+t.getCreatetime()+"'},";
                count ++;
                start ++;
            }
            index ++;
        }
        json += "]}";
        json=json.replace("},]}","}]}");
        System.out.println("json=="+json);
        return json;
    }

    @Override
    public  String selectStatusByTime_1(int pageIndex, int limit, Date startDate, Date endDate, String logLevel, String deviceName) throws Exception {
       List list= equipmentDao.findAll();
        PageResult pageResult = equipmentStatusDao.listByParams_1(pageIndex, list.size(), startDate, endDate, deviceName);
        String json = null;
        StringBuilder json2=new StringBuilder();
        StringBuilder json1=new StringBuilder();
        json1.append( "{success:true,total:");
         List<EquipmentStatus> EquipmentStatuss = pageResult.getResults();
        int a=0;
        for (EquipmentStatus t : EquipmentStatuss) {
            Equipment equipment= (Equipment) equipmentDao.getById(t.getDeviceId());
            if(t.getType().equals("1")){
                if(json2.toString().contains("deviceId:'"+equipment.getId()+"'")) {
                        System.out.println("json2存在此数据");
                }else {
                    json2.append("{id:'"+t.getId()+"',deviceId:'"+equipment.getId()+"',ip:'"+equipment.getIp());
                    json2.append("',deviceName:'"+equipment.getEquName());
                    json2.append("',status:'"+t.getStatus());
                    json2.append("',type:'"+t.getType());
                    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time = df1.format(t.getCreatetime());
                    json2.append( "',createtime:'"+time+"'},");
                    a++;
                 }
            }
        }
        json1.append(a+ ",rows:[");
        json=json1.toString()+json2.toString();
        json += "]}";
        json=json.replace("},]}","}]}");
        System.out.println("json=="+json);
        return json;
    }
     public String selectStatusByTime_2(int pageIndex, int limit, Date startDate, Date endDate, String logLevel, String deviceName) throws Exception {
          List list= equipmentDao.findAll();
        PageResult pageResult = equipmentStatusDao.listByParams_2(pageIndex, list.size(), startDate, endDate, deviceName);
        String json = null;
        StringBuilder json2=new StringBuilder();
        StringBuilder json1=new StringBuilder();
        json1.append( "{success:true,total:");
        List<EquipmentStatus> EquipmentStatuss = pageResult.getResults();
        int a=0;
        for (EquipmentStatus t : EquipmentStatuss) {
            Equipment equipment= (Equipment) equipmentDao.getById(t.getDeviceId());
            if(t.getType().equals("2")){
                if(json2.toString().contains("deviceId:'"+equipment.getId()+"'")) {
                        System.out.println("json2存在此数据");
                }else {
                    json2.append("{id:'"+t.getId()+"',deviceId:'"+equipment.getId()+"',ip:'"+equipment.getIp());
                    json2.append("',deviceName:'"+equipment.getEquName());
                    json2.append("',status:'"+t.getStatus());
                    json2.append("',type:'"+t.getType());
                    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time = df1.format(t.getCreatetime());
                    json2.append( "',createtime:'"+time+"'},");
                    a++;
                 }
            }
        }
        json1.append(a+ ",rows:[");
        json=json1.toString()+json2.toString();
        json += "]}";
        json=json.replace("},]}","}]}");
        System.out.println("json=="+json);
        return json;
    }
     public String selectStatusByTime_3(int pageIndex, int limit, Date startDate, Date endDate, String logLevel, String deviceName) throws Exception {
        List list= equipmentDao.findAll();
        PageResult pageResult = equipmentStatusDao.listByParams_3(pageIndex, list.size(), startDate, endDate, deviceName);
        String json = null;
        StringBuilder json2=new StringBuilder();
        StringBuilder json1=new StringBuilder();
        json1.append( "{success:true,total:");
         List<EquipmentStatus> EquipmentStatuss = pageResult.getResults();
          int a=0;
        for (EquipmentStatus t : EquipmentStatuss) {
            Equipment equipment= (Equipment) equipmentDao.getById(t.getDeviceId());
            if(t.getType().equals("3")){
                if(json2.toString().contains("deviceId:'"+equipment.getId()+"'")) {
                        System.out.println("json2存在此数据");
                }else {
                    json2.append("{id:'"+t.getId()+"',deviceId:'"+equipment.getId()+"',ip:'"+equipment.getIp());
                    json2.append("',deviceName:'"+equipment.getEquName());
                    json2.append("',status:'"+t.getStatus());
                    json2.append("',type:'"+t.getType());
                    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time = df1.format(t.getCreatetime());
                    json2.append( "',createtime:'"+time+"'},");
                   a++;
                }
            }
        }
        json1.append(a+ ",rows:[");
        json=json1.toString()+json2.toString();
        json += "]}";
        json=json.replace("},]}","}]}");
        System.out.println("json=="+json);
        return json;
    }

    @Override
    public String selectStatusByTime_4(int pageIndex, int limit, Date startDate, Date endDate, String logLevel, String deviceName) throws Exception {
        List list= equipmentDao.findAll();
        PageResult pageResult = equipmentStatusDao.listByParams_4(pageIndex, list.size(), startDate, endDate, deviceName);
        String json = null;
        StringBuilder json2=new StringBuilder();
        StringBuilder json1=new StringBuilder();
        json1.append( "{success:true,total:");
         List<EquipmentStatus> EquipmentStatuss = pageResult.getResults();
          int a=0;
        for (EquipmentStatus t : EquipmentStatuss) {
            Equipment equipment= (Equipment) equipmentDao.getById(t.getDeviceId());
            if(t.getType().equals("0")){
                if(json2.toString().contains("deviceId:'"+equipment.getId()+"'")) {
                        System.out.println("json2存在此数据");
                }else {
                    json2.append("{id:'"+t.getId()+"',deviceId:'"+equipment.getId()+"',ip:'"+equipment.getIp());
                    json2.append("',deviceName:'"+equipment.getEquName());
                    json2.append("',status:'"+t.getStatus());
                    json2.append("',type:'"+t.getType());
                    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time = df1.format(t.getCreatetime());
                    json2.append( "',createtime:'"+time+"'},");
                     a++;
                 }
            }
        }
        json1.append(a+ ",rows:[");
        json=json1.toString()+json2.toString();
        json += "]}";
        json=json.replace("},]}","}]}");
        System.out.println("json=="+json);
        return json;
    }

    @Override
    public String selectStatusByTime(int pageIndex, int limit, Date startDate, Date endDate, String logLevel, String deviceName) throws Exception {
        List list= equipmentDao.findAll();
        PageResult pageResult = equipmentStatusDao.listByParams(pageIndex, list.size(), startDate, endDate, deviceName);
        String json = null;
        StringBuilder json2=new StringBuilder();
        StringBuilder json1=new StringBuilder();
        json1.append( "{success:true,total:");
        List<EquipmentStatus> EquipmentStatuss = pageResult.getResults();
        int a=0;
        for (EquipmentStatus t : EquipmentStatuss) {
            Equipment equipment= (Equipment) equipmentDao.getById(t.getDeviceId());
            if(equipment!=null){
                if(json2.toString().contains("deviceId:'"+equipment.getId()+"'")) {
                    System.out.println("json2存在此数据");
                }else {
                    json2.append("{id:'"+t.getId()+"',deviceId:'"+equipment.getId()+"',ip:'"+equipment.getIp());
                    json2.append("',deviceName:'"+equipment.getEquName());
                    json2.append("',status:'"+t.getStatus());
                 /*   if(t.getType().equals("0")){
                        json2.append("',type:'外网接入区");
                    }
                    if(t.getType().equals("1")){
                        json2.append("',type:'安全接入区");
                    }
                    if(t.getType().equals("2")){
                        json2.append("',type:'隔离区");
                    }
                    if(t.getType().equals("3")){
                        json2.append("',type:'公安信息网");
                    }*/
                    json2.append("',type:'"+t.getType());
                    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time = df1.format(t.getCreatetime());
                    json2.append( "',createtime:'"+time+"'},");
                    a++;
                }
            }
//            if(t.getType().equals("0")){
//                if(json2.toString().contains("deviceId:'"+equipment.getId()+"'")) {
//                    System.out.println("json2存在此数据");
//                }else {
//                    json2.append("{id:'"+t.getId()+"',deviceId:'"+equipment.getId()+"',ip:'"+equipment.getIp());
//                    json2.append("',deviceName:'"+equipment.getEquName());
//                    json2.append("',status:'"+t.getStatus());
//                    json2.append("',type:'"+t.getType());
//                    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    String time = df1.format(t.getCreatetime());
//                    json2.append( "',createtime:'"+time+"'},");
//                    a++;
//                }
//            }
        }
        json1.append(a+ ",rows:[");
        json=json1.toString()+json2.toString();
        json += "]}";
        json=json.replace("},]}","}]}");
        System.out.println("json=="+json);
        return json;
    }

}
