package com.hzjava.monitorcenter.service;

import cn.collin.commons.domain.PageResult;
import cn.collin.commons.utils.DateUtils;
import com.hzjava.monitorcenter.dao.*;
import com.hzjava.monitorcenter.domain.*;
import com.hzjava.monitorcenter.utils.ServiceResponse;
import com.hzjava.monitorcenter.utils.ServiceUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.util.*;

public class RunMonitorServiceImpl implements RunMonitorService {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
            .getLogger(RunMonitorServiceImpl.class);

    private BusinessRegisterDao businessRegisterDao;
    private BusinessReportDao businessReportDao;
    private EquipmentDao equipmentDao;
    private BusinessHourReportDao businessHourReportDao;
    private SysTerminalStatusRunDao sysTerminalStatusRunDao;
    private DistrictDao districtDao;
    private SysClassDao sysClassDao;
    private CodeTableDao codeTableDao;


    public void setCodeTableDao(CodeTableDao codeTableDao) {
        this.codeTableDao = codeTableDao;
    }
    public void setDistrictDao(DistrictDao districtDao) {
        this.districtDao = districtDao;
    }

    public void setSysClassDao(SysClassDao sysClassDao) {
        this.sysClassDao = sysClassDao;
    }

    public void setBusinessHourReportDao(
            BusinessHourReportDao businessHourReportDao) {
        this.businessHourReportDao = businessHourReportDao;
    }

    public void setSysTerminalStatusRunDao(SysTerminalStatusRunDao sysTerminalStatusRunDao) {
        this.sysTerminalStatusRunDao = sysTerminalStatusRunDao;
    }

    public Map getBizIndexModel(int pageIndex) {
        Map model = new HashMap();
        PageResult ps = businessRegisterDao.listByPage(pageIndex, 15);

        List items = new ArrayList();
        for (Iterator iter = ps.getResults().iterator(); iter.hasNext();) {
            BusinessRegister register = (BusinessRegister) iter.next();
            Map item = new HashMap();
            item.put("id", register.getId());
            item.put("name", register.getBusinessName());
            item.put("linkName", register.getLinkName());
            item.put("code", register.getBusinessCode().getDescription());
            item.put("exchModel", register.getBusinessExchModel()
                    .getDescription());

            try {
                // 从接口获取实时信息
                String[][] params = new String[][] {
                        { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                        { "Command", "businessmonitor" },
                        { "businessname", register.getBusinessName() } };
                ServiceResponse response = ServiceUtil.callService(params);
                if (response != null && response.getData() != null) {
                    JSONObject obj = JSONObject.fromObject(""+response.getData());
                    logger.info("实时信息:"+response.getData());
                    item.put("recordCount", obj.getLong("record_count"));
                    item.put("dataVolume", Float.parseFloat(obj.getString("xt_dataflow")));
                    item.put("alertCount", obj.getInt("alert_count"));
                    item.put("linkCount", obj.getInt("connect_count"));
                    item.put("responseTime", obj.getInt("action_time"));
                } else {
                    item.put("recordCount", "无数据");
                    item.put("dataVolume", "无数据");
                    item.put("alertCount", "无数据");
                    item.put("linkCount", "无数据");
                    item.put("responseTime", "无数据");
                }
                item.put("runStatus", response.getCode());
            } catch (Exception e) {
                logger.error("从接口获取业务运行信息出错", e);
                item.put("recordCount", "无数据");
                item.put("dataVolume", "无数据");
                item.put("alertCount", "无数据");
                item.put("linkCount", "无数据");
                item.put("responseTime", "无数据");
                item.put("runStatus", 503);
            }

            items.add(item);
        }
        ps.setResults(items);
        model.put("ps", ps);

        return model;
    }
    public Map getVisMonitor(int pageIndex) {
        Map model = new HashMap();
        PageResult ps = businessRegisterDao.listByPage(pageIndex, 15);

        List items = new ArrayList();
        for (Iterator iter = ps.getResults().iterator(); iter.hasNext();) {
            BusinessRegister register = (BusinessRegister) iter.next();
            Map item = new HashMap();
            item.put("id", register.getId());
            item.put("name", register.getBusinessName());
            item.put("linkName", register.getLinkName());
            item.put("code", register.getBusinessCode().getDescription());
            item.put("exchModel", register.getBusinessExchModel()
                    .getDescription());

            try {
                // 从接口获取实时信息
                String[][] params = new String[][] {
                        { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                        { "Command", "video" },
                        { "businessname", register.getBusinessName() } };
                ServiceResponse response = ServiceUtil.callService(params);
                if (response != null && response.getData() != null) {
                    JSONObject obj = JSONObject.fromObject(""+response.getData());
                    logger.info("实时信息:"+response.getData());
                    item.put("recordCount", obj.getLong("record_count"));
                    item.put("dataVolume", Float.parseFloat(obj.getString("xt_dataflow")));
                    item.put("alertCount", obj.getInt("alert_count"));
                    item.put("linkCount", obj.getInt("connect_count"));
                    item.put("responseTime", obj.getInt("action_time"));
                } else {
                    item.put("recordCount", "无数据");
                    item.put("dataVolume", "无数据");
                    item.put("alertCount", "无数据");
                    item.put("linkCount", "无数据");
                    item.put("responseTime", "无数据");
                }
                item.put("runStatus", response.getCode());
            } catch (Exception e) {
                logger.error("从接口获取业务运行信息出错", e);
                item.put("recordCount", "无数据");
                item.put("dataVolume", "无数据");
                item.put("alertCount", "无数据");
                item.put("linkCount", "无数据");
                item.put("responseTime", "无数据");
                item.put("runStatus", 503);
            }

            items.add(item);
        }
        ps.setResults(items);
        model.put("ps", ps);

        return model;
    }
    public Map getEquIndexModel(int pageIndex) {
        Map model = new HashMap();
        PageResult ps = equipmentDao.listByPage(pageIndex, 15);
        List items = new ArrayList();
        for (Object obj : ps.getResults()) {
            Equipment equ = (Equipment) obj;

            Map item = new HashMap();
            item.put("id", equ.getId());
            item.put("equName", equ.getEquName());
            item.put("equIconCode", equ.getEquIconCode());
            item.put("netStation", equ.getNetStation());
            item.put("monitorUsed", equ.getMonitorUsed());
            item.put("ip", equ.getIp());
//            从接口获取设备运行状态出错
            try {
                String[][] params = new String[][] {
                        { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                        { "Command", "devicemonitor" },
                        { "deviceid", equ.getId().toString() },
                        { "deviceip", equ.getIp() } ,
                        {"netStation",equ.getNetStation()}};

                ServiceResponse response = ServiceUtil.callService(params);
                String result = response.getData();
                JSONObject resultobj = JSONObject.fromObject(result);
                logger.debug(response.getCode());
                item.put("runStatus", response.getCode());
                item.put("ipPing",resultobj.get("ipPing"));
            } catch (Exception e) {
                logger.error("从接口获取设备运行状态出错", e);
            }

            items.add(item);
        }
        ps.setResults(items);
        model.put("ps", ps);

        return model;
    }

    public void setBusinessRegisterDao(BusinessRegisterDao businessRegisterDao) {
        this.businessRegisterDao = businessRegisterDao;
    }

    public void setBusinessReportDao(BusinessReportDao businessReportDao) {
        this.businessReportDao = businessReportDao;
    }

    public void setEquipmentDao(EquipmentDao equipmentDao) {
        this.equipmentDao = equipmentDao;
    }

    public Map getEquDetailModel(Long id) {
        Map model = new HashMap();
        Equipment entry = (Equipment) equipmentDao.getById(id);
        model.put("id", entry.getId());
        model.put("equName", entry.getEquName());
        model.put("netStation", entry.getNetStation());

        try {
            String[][] params = new String[][] {
                    { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                    { "Command", "devicemonitor" },
                    { "deviceid", entry.getId().toString() },
                    { "deviceip", entry.getIp() } ,
                    {"netStation",entry.getNetStation()}};
            ServiceResponse response = ServiceUtil.callService(params);
            if (response != null && response.getCode() == 200
                    && response.getData() != null) {
                JSONObject obj = JSONObject.fromObject(response.getData());
                model.put("cpu", obj.get("cpu"));
                model.put("vpn", obj.get("vpn"));
                model.put("maxcon", obj.get("maxcon"));
                model.put("currentcon", obj.get("currentcon"));
                model.put("mem", obj.get("mem"));
                model.put("mem_total", obj.get("mem_total"));
                model.put("disk", obj.get("disk"));
                model.put("disk_total", obj.get("disk_total"));
            }
        } catch (Exception e) {
            logger.error("获取设备运行详细信息出错", e);
        }
        return model;
    }

    public Map getBizStatModel(String businessName) {
        Map model = new HashMap();
        model.put("businessName", businessName);

        Map entry = businessHourReportDao.countDayReport(businessName,DateUtils.getNow());
        model.put("entry", entry);

        model.put("model", model);

        return model;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map getTerminalIndexModel(int pageIndex,int pageLength) {
        Map model = new HashMap();
        List<SysTerminalStatusRun> list = sysTerminalStatusRunDao.findPage(pageIndex, pageLength);
        model.put("size",list.size());
        model.put("ps",list);
        List<District> districts = new ArrayList<District>();
        for (SysTerminalStatusRun status : list) {
            District district = districtDao.findById(Long.valueOf(status.getUserZone()));
            districts.add(district);
        }
        model.put("district", districts);
        List sysClass = sysClassDao.findAll();
        model.put("sysClass", sysClass);
        List cardTypes = codeTableDao.listAllCardType();
        model.put("cardType", cardTypes);
        return model;
    }


    public boolean isEquExist(String ip) throws Exception {
//        Equipment equ = null;
        String hql = "from Equipment where ip='" + ip + "'";
        System.out.println("hql:" + hql);
        List list = equipmentDao.list(hql);
//        equ = (Equipment) list.get(0);
        if(list.size()>0){
            return true;
        }
        return false;
    }

    public String findName() {
        String json = "{success:true,total:";
        List<Equipment> lists = equipmentDao.findAll();
        int total = lists.size();
        json += total + ",rows:[";
        for (Equipment t : lists) {
            json +="{key:'"+t.getId()+"',value:'"+ t.getEquName()+"'},";
        }
        json += "]}";
        json=json.replace("},]}","}]}");
        json=json.replace("\n"," ");
        System.out.println("json==" + json);
        return json;
    }

    public Equipment findEquipmentById(Long id) {
        Equipment equipment= (Equipment) equipmentDao.getById(id);
        if(equipment!=null){
            return equipment;  //To change body of implemented methods use File | Settings | File Templates.
        } else {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
