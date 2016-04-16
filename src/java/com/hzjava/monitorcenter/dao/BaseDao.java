package com.hzjava.monitorcenter.dao;

import com.hzjava.monitorcenter.domain.Equipment;
import com.hzjava.monitorcenter.domain.EquipmentStatus;
import com.hzjava.monitorcenter.domain.EquipmentType;
import com.hzjava.monitorcenter.domain.EquipmentPosition;
import com.hzjava.monitorcenter.svgmap.MyEquipmentDao;
import com.hzjava.monitorcenter.utils.MonitorServiceUtil;
import com.hzjava.monitorcenter.utils.ServiceResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-26
 * Time: 下午6:41
 * To change this template use File | Settings | File Templates.
 */
public class BaseDao {
     private static Properties prop= new Properties();
    private static Connection con;
    private static Statement sql;
    private static ResultSet re;
    private static final Logger logger = Logger.getLogger(BaseDao.class);

    public  Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
           prop.load(BaseDao.class.getResourceAsStream("/config.properties"));
             String   url = prop.getProperty("jdbc.url");
             String  userName = prop.getProperty("jdbc.user");
             String  password = prop.getProperty("jdbc.password");
             String  driver = prop.getProperty("jdbc.driverClass");
             Class.forName(driver.trim());
            con = DriverManager.getConnection(url, userName, password);
        return con;
    }
     public List<Equipment> selectEquipmentAll() throws SQLException {
         MyEquipmentDao equipmentDao = new MyEquipmentDao();
         logger.info("进入查询方法");
         try {
             List<Equipment> list =new ArrayList<Equipment>();
             logger.info("开始创建Statement");
             sql = con.createStatement();

            String query =
                    "(select * from equipment order by id desc)";
             logger.info("开始执行Query");
             re = sql.executeQuery(query);
             logger.info("查询完成");
            while(re.next()){
                Equipment equipment=new Equipment();
                equipment.setBuyDay(re.getDate("buy_day"));
                equipment.setEquIconCode(re.getString("equ_Icon_code"));
                equipment.setEquInfo(re.getString("equ_info"));
                equipment.setEquName(re.getString("equ_name"));
                equipment.setEquPhone(re.getString("equ_phone"));
                equipment.setEquStation(re.getString("equ_station"));
                EquipmentType equipmentType = new EquipmentType();
                equipmentType.setTypeName(re.getString("equ_type"));
                equipment.setEquType(equipmentType);
                equipment.setEquManagerDepart(re.getString("equManagerDepart"));
                equipment.setEquSysConfig(re.getString("equSysConfig"));
                equipment.setId(re.getLong("id"));
                equipment.setInrOrExt(re.getString("int_or_ext"));
                equipment.setIp(re.getString("ip"));
                equipment.setIsKeyDevice(re.getString("is_key_device"));
                equipment.setLinkName(re.getString("link_name"));
                equipment.setMac(re.getString("MAC"));
                equipment.setManufacturer(re.getString("manufacturer"));
                equipment.setModel(re.getString("model"));
                equipment.setMonitorUsed(re.getString("monitor_used"));
                equipment.setNetStation(re.getString("net_station"));
                equipment.setOtherIp(re.getString("other_ip"));
                equipment.setOtherPhone(re.getString("other_phone"));
                equipment.setProvider(re.getString("provider"));
                equipment.setSubnetMask(re.getString("subnet_mask"));
                equipment.setUnrepairDay(re.getDate("unrepair_day"));
                equipment.setTopologyStation(re.getString("topology_station"));
                list.add(equipment);
            }
            logger.info("List添加完成");
            return list;
        }catch(SQLException ex){
             logger.error("SQLException:" + ex.getMessage());
        }
          return null;
     }
     public EquipmentPosition selectById(Long id) throws SQLException {
         EquipmentPosition equipmentposition=new EquipmentPosition();
         sql = con.createStatement();
         String query =
                    "(select * from Equipmentposition where equipmentid="+id+")";
         re = sql.executeQuery(query);
         while(re.next()){
             equipmentposition.setId(Integer.parseInt(String.valueOf(re.getLong("id"))));
             equipmentposition.setEquipmentid(Integer.parseInt(String.valueOf(re.getString("equipmentid"))));
             equipmentposition.setEx(re.getString("ex"));
             equipmentposition.setEy(re.getString("ey"));
         }
         return equipmentposition;
     }
    public int insertByEquipmentStatus(EquipmentStatus equipmentStatus) throws SQLException {
        sql = con.createStatement();
        String query=
               " insert into equipment_status(`deviceid`, `status`, `type`, `createtime`)values	("+equipmentStatus.getDeviceId()+
                       ", '"+equipmentStatus.getStatus()+"', '"+equipmentStatus.getType()+"', '"+equipmentStatus.getCreatetime()+"')";
        logger.info(  " insert into equipment_status(`deviceid`, `status`, `type`, `createtime`)values	("+equipmentStatus.getDeviceId()+
                ", '"+equipmentStatus.getStatus()+"', '"+equipmentStatus.getType()+"', '"+equipmentStatus.getCreatetime()+"')");
        int result= sql.executeUpdate(query);
        return result;

    }
    /**	 * 关闭数据库连接
     *  * 	 * @throws SQLException	 *
     *  数据库异常	 *
     *  */
    public void close() {
        try {
            if (re != null) {
                re.close();
            }
            if (sql != null) {
                sql.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String arg[]){
        BaseDao baseDao=new BaseDao();
        try {
            con=baseDao.getConnection();
            List<Equipment> list=baseDao.selectEquipmentAll();
            for (Equipment equipment:list) {
                System.out.println(equipment.getEquName());
                System.out.println(equipment.getId());
                EquipmentPosition equipmentposition=baseDao.selectById(equipment.getId()) ;
                System.out.println("equipmentposition="+equipmentposition.getEx());
                if(equipmentposition.getEx()!=null){
                    int x=Integer.valueOf(equipmentposition.getEx());
                    String[][] params = new String[][] {
                    { "SERVICEREQUESTTYPE", "SERVICECONTROLPOST" },
                    {"Command","ping"},
                    { "deviceip", equipment.getIp() }
                    };
                    ServiceResponse response= MonitorServiceUtil.callService(params);
                    EquipmentStatus equipmentStatus=new EquipmentStatus();
                    equipmentStatus.setDeviceId(equipment.getId());
                    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    java.util.Date date = new java.util.Date();
                    String time = df1.format(date);
                    Timestamp ts = Timestamp.valueOf(time);
                    equipmentStatus.setCreatetime(ts);
                    if((response.getData()!=null&&response.getData().equals("true"))||(response.getData().equals("")&&response.getData().equals("true"))){
                        equipmentStatus.setStatus("运行中");
                    }else {
                        equipmentStatus.setStatus("已关闭");
                    }
                    if(x<=208&&x>0){
                        equipmentStatus.setType("外网接入区");
                    }else if(x>208&x<=700){
                        equipmentStatus.setType("安全接入区");
                    }else if(x>700&x<=855){
                        equipmentStatus.setType("隔离区");
                    }else if(x>855){
                        equipmentStatus.setType("公安内网区");
                    }
                    baseDao.insertByEquipmentStatus(equipmentStatus);
                }
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
}
