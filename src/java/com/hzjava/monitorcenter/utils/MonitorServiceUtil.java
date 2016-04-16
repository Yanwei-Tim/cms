package com.hzjava.monitorcenter.utils;

import com.hzjava.monitorcenter.dao.BaseDao;
import com.hzjava.monitorcenter.domain.Equipment;
import com.hzjava.monitorcenter.domain.EquipmentStatus;
import com.hzjava.monitorcenter.web.SiteContext;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-26
 * Time: 下午4:29
 * To change this template use File | Settings | File Templates.
 */
public class MonitorServiceUtil extends TimerTask {
    private static final Logger logger = Logger.getLogger(MonitorServiceUtil.class);


    @Override
    public void run() {
         logger.info("进入MonitorServiceUtil");
//        MyEquipmentDao equipmentDao = new MyEquipmentDao();
         BaseDao baseDao=new BaseDao();
        logger.info("baseDao初始化");
        try {
            baseDao.getConnection();
            logger.info("baseDao连接");
            List<Equipment> list=baseDao.selectEquipmentAll();
//            List<Equipment> list=equipmentDao.findAllEquipments();
            logger.info("LIST大小"+list.size());
            for (Equipment equipment:list) {
                System.out.println(equipment.getEquName());
                System.out.println(equipment.getId());
//                Equipmentposition equipmentposition=baseDao.selectById(equipment.getId()) ;
//                System.out.println("equipmentposition="+equipmentposition.getEx());
//                if(equipmentposition.getEx()!=null){
//                    int x=Integer.valueOf(equipmentposition.getEx());
                    String topologyStation = equipment.getTopologyStation();
                String[][] params = new String[][] {
                        { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                        {"Command","ping"},
                        { "deviceip", equipment.getIp() },
                        {"netStation",equipment.getNetStation()}
                };
                    ServiceResponse response= MonitorServiceUtil.callService(params);
                    EquipmentStatus equipmentStatus=new EquipmentStatus();
                    equipmentStatus.setDeviceId(equipment.getId());
                    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new Date();
                    String time = df1.format(date);
                    Timestamp ts = Timestamp.valueOf(time);
                    equipmentStatus.setCreatetime(ts);
                    logger.info(response.getData()+"ping的结果为");
                    if(response.getData()==null||response.getData().equals("")){
                        equipmentStatus.setStatus("0");
                    }else {
                        if(response.getData().equals("true")){
                            equipmentStatus.setStatus("1");
                        }else {
                            equipmentStatus.setStatus("0");
                        }
                    }
                    /*if((response.getData()!=null&&response.getData().equals("true"))||(response.getData().equals("")&&response.getData().equals("true"))){
                        equipmentStatus.setStatus("1");
                    }else {
                        equipmentStatus.setStatus("0");
                    }*/
                    if(topologyStation.equalsIgnoreCase("1")){
                        equipmentStatus.setType("0");    //   外网接入区
                    }else if(topologyStation.equalsIgnoreCase("2")){
                        equipmentStatus.setType("1");       //安全接入区
                    }else if(topologyStation.equalsIgnoreCase("3")){
                        equipmentStatus.setType("2");       //隔离区
                    }else if(topologyStation.equalsIgnoreCase("4")){
                        equipmentStatus.setType("3");     //公安内网区
                    }
                    baseDao.insertByEquipmentStatus(equipmentStatus);
//                }
            }
            baseDao.close();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    public static ServiceResponse callService(String[][] params) {
		HttpClient client = new HttpClient();

        client.getHttpConnectionManager().getParams().setConnectionTimeout(
				5 * 1000);
		client.getHttpConnectionManager().getParams().setSoTimeout(5 * 1000);

		PostMethod post = new PostMethod(SiteContext.getInstance().serviceUrl);
		post.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5 * 1000);
		post.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=UTF-8");

		for (String[] param : params) {
			post.addParameter(param[0], param[1]);
		}

		ServiceResponse response = new ServiceResponse();

		int statusCode = 0;
		try {
			statusCode = client.executeMethod(post);
            System.out.println("statusCode=="+statusCode);
			response.setCode(statusCode);
			if (statusCode == 200) {
				String data = post.getResponseBodyAsString();
				response.setData(data);
			}
		} catch (Exception e) {
			System.err.println(e);
		}

		return response;
	}

}
