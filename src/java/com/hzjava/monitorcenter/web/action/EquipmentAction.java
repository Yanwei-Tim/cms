package com.hzjava.monitorcenter.web.action;

import com.hzjava.monitorcenter.domain.Equipment;
import com.hzjava.monitorcenter.service.RunMonitorService;
import com.hzjava.monitorcenter.web.WebUtil;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.DispatchActionSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-24
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
public class EquipmentAction extends DispatchActionSupport {
    private String deviceName;
    private RunMonitorService equipmentService;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public RunMonitorService getEquipmentService() {
        return equipmentService;
    }

    public void setEquipmentService(RunMonitorService equipmentService) {
        this.equipmentService = equipmentService;
    }

    public ActionForward getAllByFindName(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json=equipmentService.findName();
        WebUtil.sendHtml(request, response, json);
        return null;
    }

    public String getEquipmentByDeviceId(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response) throws IOException {
        String deviceid= request.getParameter("deviceId");
        if(deviceid==null||deviceid.equals("")){
            String msg = "查看失败";
            String json = "{success:true,msg:'"+msg+"'}";
            WebUtil.sendHtml(request, response, json);
        }else {
            Long Deviceid=Long.valueOf(deviceid);
            Equipment equipment=equipmentService.findEquipmentById(Deviceid);
            StringBuffer buffer=new StringBuffer();
            buffer.append("{");
            buffer.append("'equ_Icon_code':'"+equipment.getEquIconCode()+"',");
            buffer.append("'equ_info':'"+equipment.getEquInfo()+"',");
            buffer.append("'unrepair_day':'"+equipment.getUnrepairDay()+"',");
            buffer.append("'model':'"+equipment.getModel()+"',");
            buffer.append("'monitor_used':'"+equipment.getMonitorUsed()+"',");
            buffer.append("'equManagerDepart':'"+equipment.getEquManagerDepart()+"',");
            buffer.append("'buy_day':'"+equipment.getBuyDay()+"',");
            buffer.append("'link_name':'"+equipment.getLinkName()+"',");
            buffer.append("'other_ip':'"+equipment.getOtherIp()+"',");
            buffer.append("'provider':'"+equipment.getProvider()+"',");
            buffer.append("'is_key_device':'"+equipment.getIsKeyDevice()+"',");
            buffer.append("'ip':'"+equipment.getIp()+"',");
            buffer.append("'id':'"+equipment.getId()+"',");
            buffer.append("'subnet_mask':'"+equipment.getSubnetMask()+"',");
            buffer.append("'equ_name':'"+equipment.getEquName()+"',");
            buffer.append("'equ_phone':'"+equipment.getEquPhone()+"',");
            buffer.append("'equ_station':'"+equipment.getEquStation()+"',");
            buffer.append("'int_or_ext':'"+equipment.getInrOrExt()+"',");
            buffer.append("'net_station':'"+equipment.getNetStation()+"',");
            buffer.append("'equSysConfig':'"+equipment.getEquSysConfig()+"',");
            buffer.append("'manufacturer':'"+equipment.getManufacturer()+"',");
            buffer.append("'other_phone':'"+equipment.getOtherPhone()+"',");
            buffer.append("'topologyStation':'"+equipment.getTopologyStation()+"',");
            buffer.append("'MAC':'"+equipment.getMac()+"',");
            buffer.append("'equId':'"+equipment.getEquId()+"',");
            buffer.append("'equPort':'"+equipment.getEquPort()+"',");
            buffer.append("'equOidName':'"+equipment.getEquSnmpPwd()+"',");
            buffer.append("'snmpVer':'"+equipment.getEquSnmpver()+"',");
            buffer.append("'equSnmpPwd':'"+equipment.getEquSnmpPwd()+"',");
            buffer.append("'equ_type':'"+equipment.getEquType().getTypeName()+"'}");

//            net.sf.json.JSONObject jsonObject= net.sf.json.JSONObject.fromObject(equipment);
//            log.info(jsonObject.toString());
            WebUtil.sendHtml(request, response, buffer.toString());
        }
        return null;
    }


}
