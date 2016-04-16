package com.hzjava.monitorcenter.service;

import com.hzjava.monitorcenter.dao.JbqkDao;
import com.hzjava.monitorcenter.dao.SysabnormalDao;
import com.hzjava.monitorcenter.domain.Jbqk;
import com.hzjava.monitorcenter.domain.Sysabnormal;
import com.hzjava.monitorcenter.utils.DateTimeUtil;
import com.hzjava.monitorcenter.utils.LineChart;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-7
 * Time: 下午4:25
 * To change this template use File | Settings | File Templates.
 */
public class SysAbnormalServiceImpl implements SysAbnormalService {
    private SysabnormalDao sysabnormalDao;
    private JbqkDao jbqkDao;
    public SysabnormalDao getSysabnormalDao() {
        return sysabnormalDao;
    }

    public void setSysabnormalDao(SysabnormalDao sysabnormalDao) {
        this.sysabnormalDao = sysabnormalDao;
    }

    public JbqkDao getJbqkDao() {
        return jbqkDao;
    }

    public void setJbqkDao(JbqkDao jbqkDao) {
        this.jbqkDao = jbqkDao;
    }

    @Override
    public String findAllBySysAbnormal() throws Exception {
        StringBuilder str=new StringBuilder();

        String hql="from Sysabnormal group by idsystem";
        List<Sysabnormal> lists=sysabnormalDao.list(hql);        //根据idsystem分组
        for (Sysabnormal sysabnormal:lists){
            String hqls = "from Sysabnormal where idsystem = "+sysabnormal.getIdSystem()+" group by  username";
            Jbqk jbqk = jbqkDao.getJbqkByIdsystem(sysabnormal.getIdSystem());
            String idsystem=null;
            if(jbqk!=null){
                idsystem= jbqk.getPlatformName();      //获取平台
            }
            List<Sysabnormal> list=sysabnormalDao.list(hqls);   // 根据idsystem  username分组
            for (Sysabnormal sysabnormal2:list){
                str.append("{");
                str.append("'idsystemname':'"+idsystem+"','username':'"+sysabnormal2.getUsername()+"','sysabnormalobjecttype':'"+sysabnormal2.getSysabnormalObjectType().getName()+"',");
                str.append("'idsystem':'"+sysabnormal2.getIdSystem()+"',");
                String s1hql = "from Sysabnormal where idsystem = '"+sysabnormal2.getIdSystem()+"' and username = '"+sysabnormal2.getUsername() +"' group by abnormaltypecode";
                String s2hql = "select count(*) from Sysabnormal where idsystem = '"+sysabnormal2.getIdSystem()+"' and username = '"+sysabnormal2.getUsername() +"' group by abnormaltypecode";
                List<Sysabnormal>  listab= sysabnormalDao.list(s1hql);
                List<Sysabnormal>  listabnum= sysabnormalDao.countAll(s2hql);
                for (int i=0;i<listab.size();i++){
                    Sysabnormal sb=listab.get(i);
                    if(sb.getAbNormalTypeCode().equals("0000")) {
                        // 其他违规
                        str.append("'OtherViolations':'"+listabnum.get(i)+"',");
                    } else if(sb.getAbNormalTypeCode().equals("0001")){
                        // 网络违规
                        str.append("'NetworkViolations':'"+listabnum.get(i)+"',");
                    }else if(sb.getAbNormalTypeCode().equals("0002")){
                        // 进程违规
                        str.append("'ProcessViolations':'"+listabnum.get(i)+"',");
                    }else if(sb.getAbNormalTypeCode().equals("0003")){
                        // 外设违规
                        str.append("'PeripheralViolations':'"+listabnum.get(i)+"',");
                    }else if(sb.getAbNormalTypeCode().equals("0004")){
                        // URL违规
                        str.append("'URLViolations':'"+listabnum.get(i)+"',");
                    }else if(sb.getAbNormalTypeCode().equals("0005")){
                        // 流量违规
                        str.append("'FluxViolations':'"+listabnum.get(i)+"',");
                    } else if(sb.getAbNormalTypeCode().equals("0006")){
                        // 机卡分离违规
                        str.append("'SeparationViolations':'"+listabnum.get(i)+"',");
                    }

                }
                String st=str.toString().substring(str.toString().lastIndexOf("{"),str.toString().length());
                if(!st.contains("OtherViolations")){
                    str.append("'OtherViolations':'0',");
                }
                if(!st.contains("NetworkViolations")){
                    str.append("'NetworkViolations':'0',");
                }
                if(!st.contains("ProcessViolations")){
                    str.append("'ProcessViolations':'0',");
                }
                if(!st.contains("PeripheralViolations")){
                    str.append("'PeripheralViolations':'0',");
                }
                if(!st.contains("URLViolations")){
                    str.append("'URLViolations':'0',");
                }
                if(!st.contains("FluxViolations")){
                    str.append("'FluxViolations':'0',");
                }
                if(!st.contains("SeparationViolations")){
                    str.append("'SeparationViolations':'0'");
                }
                str.append("},");


            }
        };
        str.append("]}" );
        System.out.println(str.toString());
        String s[]=str.toString().split("idsystemname");
        int i=s.length-1;
        String st="{success:true,totalCount:"+i+",rows:[";
        String strs = str.toString();
        if(strs.contains(",},")){
             strs = strs.replace(",},","},");
        }
        if(strs.endsWith(",]}")){
            strs = strs.replace(",]}","]}");
        }
        System.out.println(str.toString());
        return st+strs;

    }

    @Override
    public String findAll(String idsystem,String username) throws Exception {
        StringBuilder str=new StringBuilder();

        String hql="from Sysabnormal where idsystem = '"+idsystem+"' and username = '"+username+"'" ;
        List<Sysabnormal> lists=sysabnormalDao.list(hql);
        str.append("{success:true,totalCount:"+lists.size()).append(",rows:[");
        for (Sysabnormal sysabnormal:lists){
            str.append("{");
            str.append("'id':'"+sysabnormal.getId()+"','Username':'"+sysabnormal.getUsername()+"','Sysabnormalobjecttype':'"+sysabnormal.getSysabnormalObjectType().getName()+"',");
            str.append("'AbNormalTypeCode':'"+sysabnormal.getSysabnormalEventCode().getName() +"','ExceptionDesc':'"+sysabnormal.getExceptionDesc()+"',");
            str.append("'Idsystem':'"+sysabnormal.getIdSystem()+"','TreadResult':'"+sysabnormal.getSysabnormalResult().getName()+"','WriteTime':'"+sysabnormal.getWriteTime()+"'");
            str.append("},");
        }
        str.append("]}" );
        String strs = str.toString();
        /*if(strs.contains(",},")){
            strs = strs.replace(",},","},");
        }*/
        if(strs.endsWith(",]}")){
            strs = strs.replace(",]}","]}");
        }
        System.out.println(str.toString());
        return strs;
    }

    @Override
    public String listReportCountDay(String reportDate)throws Exception{
        String yesterday =null;
        if(reportDate==null){
            Calendar   cal   =   Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            yesterday = new SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());
            System.out.println("yesterday"+yesterday);
        } else {
           yesterday=reportDate;
        }
        String hql="from Sysabnormal s where date_format(s.writeTime,'%Y-%m-%d')= '"+yesterday+"' group by abnormaltypecode" ;
        List<Sysabnormal> lists=sysabnormalDao.list(hql);
        StringBuilder str=new StringBuilder();
        str.append("{success:true,root:7,rows:[");
        if(lists==null){
            str.append("{");
            str.append("'abnormalName':'其它违规','abnormalCount':0,'abnormalCode':'0000'");
            str.append("},");
            str.append("{");
            str.append("'abnormalName':'网络违规','abnormalCount':0,'abnormalCode':'0001'");
            str.append("},");
            str.append("{");
            str.append("'abnormalName':'流量违规','abnormalCount':0,'abnormalCode':'0005'");
            str.append("},");
            str.append("{");
            str.append("'abnormalName':'进程违规','abnormalCount':0,'abnormalCode':'0002'");
            str.append("},");
            str.append("{");
            str.append("'abnormalName':'外设违规','abnormalCount':0,'abnormalCode':'0003'");
            str.append("},");
            str.append("{");
            str.append("'abnormalName':'URL违规','abnormalCount':0,'abnormalCode':'0004'");
            str.append("},");
            str.append("{");
            str.append("'abnormalName':'机卡分离','abnormalCount':0,'abnormalCode':'0006'");
            str.append("}");
        }else {
            for (Sysabnormal sysabnormal:lists){
                String hqls="select count(*) from Sysabnormal s where date_format(s.writeTime,'%Y-%m-%d')= '"+yesterday+"'and abnormaltypecode = '"+sysabnormal.getAbNormalTypeCode()+"'" ;
                int count=sysabnormalDao.countAlls(hqls);
                str.append("{");
                str.append("'abnormalName':'"+sysabnormal.getSysabnormalEventCode().getName()+"','abnormalCount':"+count+",'abnormalCode':'"+sysabnormal.getAbNormalTypeCode()+"'");
                str.append("},");
            }
            if(!str.toString().contains("0000")) {
                str.append("{");
                str.append("'abnormalName':'其它违规','abnormalCount':0,'abnormalCode':'0000'");
                str.append("},");
            }
            if(!str.toString().contains("0001")) {
                str.append("{");
                str.append("'abnormalName':'网络违规','abnormalCount':0,'abnormalCode':'0001'");
                str.append("},");
            }
            if(!str.toString().contains("0002")) {
                str.append("{");
                str.append("'abnormalName':'进程违规','abnormalCount':0,'abnormalCode':'0002'");
                str.append("},");
            }
            if(!str.toString().contains("0003")) {
                str.append("{");
                str.append("'abnormalName':'外设违规','abnormalCount':0,'abnormalCode':'0003'");
                str.append("},");
            }
            if(!str.toString().contains("0004")) {
                str.append("{");
                str.append("'abnormalName':'URL违规','abnormalCount':0,'abnormalCode':'0004'");
                str.append("},");
            }
            if(!str.toString().contains("0005")) {
                str.append("{");
                str.append("'abnormalName':'流量违规','abnormalCount':0,'abnormalCode':'0005'");
                str.append("},");
            }
            if(!str.toString().contains("0006")) {
                str.append("{");
                str.append("'abnormalName':'机卡分离','abnormalCount':0,'abnormalCode':'0006'");
                str.append("},");
            }
        }
        str.append("]}" );
        String strs = str.toString();
        if(strs.endsWith(",]}")){
            strs = strs.replace(",]}","]}");
        }
        System.out.println(str.toString());
        return strs;
    }

    public String listReportCountMonth(String reportDate)throws Exception{
        String m = null;
        if(reportDate==null){
            DateTimeUtil dateTimeUtil=new DateTimeUtil();
            int month=dateTimeUtil.getMonth();
            int year=dateTimeUtil.getYear();
            if(String.valueOf(month).length()==1){
                m=String.valueOf(year)+"-0"+String.valueOf(month) ;
            } else {
                m=String.valueOf(year)+"-"+String.valueOf(month) ;
            }
        } else {
            m=reportDate;
        }

        String hql="from Sysabnormal s where date_format(s.writeTime,'%Y-%m')= '"+m+"' group by abnormaltypecode" ;
        List<Sysabnormal> lists=sysabnormalDao.list(hql);
        StringBuilder str=new StringBuilder();
        str.append("{success:true,root:7,rows:[");
        if(lists==null){
            str.append("{");
            str.append("'abnormalName':'其它违规','abnormalCount':0,'abnormalCode':'0000'");
            str.append("},");
            str.append("{");
            str.append("'abnormalName':'网络违规','abnormalCount':0,'abnormalCode':'0001'");
            str.append("},");
            str.append("{");
            str.append("'abnormalName':'流量违规','abnormalCount':0,'abnormalCode':'0005'");
            str.append("},");
            str.append("{");
            str.append("'abnormalName':'进程违规','abnormalCount':0,'abnormalCode':'0002'");
            str.append("},");
            str.append("{");
            str.append("'abnormalName':'外设违规','abnormalCount':0,'abnormalCode':'0003'");
            str.append("},");
            str.append("{");
            str.append("'abnormalName':'URL违规','abnormalCount':0,'abnormalCode':'0004'");
            str.append("},");
            str.append("{");
            str.append("'abnormalName':'机卡分离','abnormalCount':0,'abnormalCode':'0006'");
            str.append("}");
        }else {
            for (Sysabnormal sysabnormal:lists){
                String hqls="select count(*) from Sysabnormal s where date_format(s.writeTime,'%Y-%m')= '"+m+"'and  abnormaltypecode = '"+sysabnormal.getAbNormalTypeCode()+"'" ;
                int count=sysabnormalDao.countAlls(hqls);
                str.append("{");
                str.append("'abnormalName':'"+sysabnormal.getSysabnormalEventCode().getName()+"','abnormalCount':"+count+",'abnormalCode':'"+sysabnormal.getAbNormalTypeCode()+"'");
                str.append("},");
            }
            if(!str.toString().contains("0000")) {
                str.append("{");
                str.append("'abnormalName':'其它违规','abnormalCount':0,'abnormalCode':'0000'");
                str.append("},");
            }
            if(!str.toString().contains("0001")) {
                str.append("{");
                str.append("'abnormalName':'网络违规','abnormalCount':0,'abnormalCode':'0001'");
                str.append("},");
            }
            if(!str.toString().contains("0002")) {
                str.append("{");
                str.append("'abnormalName':'进程违规','abnormalCount':0,'abnormalCode':'0002'");
                str.append("},");
            }
            if(!str.toString().contains("0003")) {
                str.append("{");
                str.append("'abnormalName':'外设违规','abnormalCount':0,'abnormalCode':'0003'");
                str.append("},");
            }
            if(!str.toString().contains("0004")) {
                str.append("{");
                str.append("'abnormalName':'URL违规','abnormalCount':0,'abnormalCode':'0004'");
                str.append("},");
            }
            if(!str.toString().contains("0005")) {
                str.append("{");
                str.append("'abnormalName':'流量违规','abnormalCount':0,'abnormalCode':'0005'");
                str.append("},");
            }
            if(!str.toString().contains("0006")) {
                str.append("{");
                str.append("'abnormalName':'机卡分离','abnormalCount':0,'abnormalCode':'0006'");
                str.append("},");
            }
        }
        str.append("]}" );
        String strs = str.toString();
        if(strs.endsWith(",]}")){
            strs = strs.replace(",]}","]}");
        }
        System.out.println(str.toString());
        return strs;
    }

    public String listReportCountYear(String reportDate)throws Exception{
        String y=null;
        if(reportDate==null){
            DateTimeUtil dateTimeUtil=new DateTimeUtil();
            int year=dateTimeUtil.getYear();
            y=String.valueOf(year) ;
        }else {
            y=reportDate;
        }

        String hql="from Sysabnormal s where date_format(s.writeTime,'%Y')= '"+y+"' group by abnormaltypecode" ;
        List<Sysabnormal> lists=sysabnormalDao.list(hql);
        StringBuilder str=new StringBuilder();
        str.append("{success:true,root:7,rows:[");
        if(lists==null){
            str.append("{");
            str.append("'abnormalName':'其它违规','abnormalCount':0,'abnormalCode':'0000'");
            str.append("},");
            str.append("{");
            str.append("'abnormalName':'网络违规','abnormalCount':0,'abnormalCode':'0001'");
            str.append("},");
            str.append("{");
            str.append("'abnormalName':'流量违规','abnormalCount':0,'abnormalCode':'0005'");
            str.append("},");
            str.append("{");
            str.append("'abnormalName':'进程违规','abnormalCount':0,'abnormalCode':'0002'");
            str.append("},");
            str.append("{");
            str.append("'abnormalName':'外设违规','abnormalCount':0,'abnormalCode':'0003'");
            str.append("},");
            str.append("{");
            str.append("'abnormalName':'URL违规','abnormalCount':0,'abnormalCode':'0004'");
            str.append("},");
            str.append("{");
            str.append("'abnormalName':'机卡分离','abnormalCount':0,'abnormalCode':'0006'");
            str.append("}");
        }else {
            for (Sysabnormal sysabnormal:lists){
                String hqls="select count(*) from Sysabnormal s where date_format(s.writeTime,'%Y')= '"+y+"' and abnormaltypecode = '"+sysabnormal.getAbNormalTypeCode()+"'" ;
                int count=sysabnormalDao.countAlls(hqls);
                str.append("{");
                str.append("'abnormalName':'"+sysabnormal.getSysabnormalEventCode().getName()+"','abnormalCount':"+count+",'abnormalCode':'"+sysabnormal.getAbNormalTypeCode()+"'");
                str.append("},");
            }
            if(!str.toString().contains("0000")) {
                str.append("{");
                str.append("'abnormalName':'其它违规','abnormalCount':0,'abnormalCode':'0000'");
                str.append("},");
            }
            if(!str.toString().contains("0001")) {
                str.append("{");
                str.append("'abnormalName':'网络违规','abnormalCount':0,'abnormalCode':'0001'");
                str.append("},");
            }
            if(!str.toString().contains("0002")) {
                str.append("{");
                str.append("'abnormalName':'进程违规','abnormalCount':0,'abnormalCode':'0002'");
                str.append("},");
            }
            if(!str.toString().contains("0003")) {
                str.append("{");
                str.append("'abnormalName':'外设违规','abnormalCount':0,'abnormalCode':'0003'");
                str.append("},");
            }
            if(!str.toString().contains("0004")) {
                str.append("{");
                str.append("'abnormalName':'URL违规','abnormalCount':0,'abnormalCode':'0004'");
                str.append("},");
            }
            if(!str.toString().contains("0005")) {
                str.append("{");
                str.append("'abnormalName':'流量违规','abnormalCount':0,'abnormalCode':'0005'");
                str.append("},");
            }
            if(!str.toString().contains("0006")) {
                str.append("{");
                str.append("'abnormalName':'机卡分离','abnormalCount':0,'abnormalCode':'0006'");
                str.append("},");
            }
        }
        str.append("]}" );
        String strs = str.toString();
        if(strs.endsWith(",]}")){
            strs = strs.replace(",]}","]}");
        }
        System.out.println(str.toString());
        return strs;
    }

    public String listReportDay(String reportDate)throws Exception{
        String yesterday =null;
        if(reportDate!=null) {
            yesterday= reportDate;
        }else {
            Calendar   cal   =   Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            yesterday = new SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());
            System.out.println("yesterday"+yesterday);
        }

        String s1hql="from Sysabnormal s where date_format(s.writeTime,'%Y-%m-%d')= '"+yesterday+"' group by abnormaltypecode" ;
        String s2hql="select count(*) from Sysabnormal s where date_format(s.writeTime,'%Y-%m-%d')= '"+yesterday+"' group by abnormaltypecode" ;
        StringBuilder str=new StringBuilder();
        str.append("{success:true,root:1,rows:[");
        List<Sysabnormal>  listab= sysabnormalDao.list(s1hql);
        List<Sysabnormal>  listabnum= sysabnormalDao.countAll(s2hql);
        str.append("{");
        if(listab==null){
            str.append("'OtherViolations':0,'NetworkViolations':0,'ProcessViolations':0,");
            str.append("'PeripheralViolations':0,'URLViolations':0,'FluxViolations':0,");
            str.append("'SeparationViolations':0");
        } else {
            for (int i=0;i<listab.size();i++){
                Sysabnormal sb=listab.get(i);
                if(sb.getAbNormalTypeCode().equals("0000")) {
                    // 其他违规
                    str.append("'OtherViolations':'"+listabnum.get(i)+"',");
                } else if(sb.getAbNormalTypeCode().equals("0001")){
                    // 网络违规
                    str.append("'NetworkViolations':'"+listabnum.get(i)+"',");
                }else if(sb.getAbNormalTypeCode().equals("0002")){
                    // 进程违规
                    str.append("'ProcessViolations':'"+listabnum.get(i)+"',");
                }else if(sb.getAbNormalTypeCode().equals("0003")){
                    // 外设违规
                    str.append("'PeripheralViolations':'"+listabnum.get(i)+"',");
                }else if(sb.getAbNormalTypeCode().equals("0004")){
                    // URL违规
                    str.append("'URLViolations':'"+listabnum.get(i)+"',");
                }else if(sb.getAbNormalTypeCode().equals("0005")){
                    // 流量违规
                    str.append("'FluxViolations':'"+listabnum.get(i)+"',");
                } else if(sb.getAbNormalTypeCode().equals("0006")){
                    // 机卡分离违规
                    str.append("'SeparationViolations':'"+listabnum.get(i)+"',");
                }
            }
            String st=str.toString().substring(str.toString().lastIndexOf("{"),str.toString().length());
            if(!st.contains("OtherViolations")){
                str.append("'OtherViolations':'0',");
            }
            if(!st.contains("NetworkViolations")){
                str.append("'NetworkViolations':'0',");
            }
            if(!st.contains("ProcessViolations")){
                str.append("'ProcessViolations':'0',");
            }
            if(!st.contains("PeripheralViolations")){
                str.append("'PeripheralViolations':'0',");
            }
            if(!st.contains("URLViolations")){
                str.append("'URLViolations':'0',");
            }
            if(!st.contains("FluxViolations")){
                str.append("'FluxViolations':'0',");
            }
            if(!st.contains("SeparationViolations")){
                str.append("'SeparationViolations':'0',");
            }
        }
        str.append("}]}" );
        String strs = str.toString();
        if(strs.contains(",}")){
            strs = strs.replace(",}","}");
        }
        System.out.println(strs);
        return strs;
    }

    public String listReportMonth(String reportDate)throws Exception{
        StringBuilder str=new StringBuilder();
        str.append("{success:true,root:1,rows:[");
        String m=null;
        if(reportDate!=null){
            m=reportDate;
        }else {
            DateTimeUtil dateTimeUtil=new DateTimeUtil();
            int month=dateTimeUtil.getMonth();
            int year=dateTimeUtil.getYear();
            if(String.valueOf(month).length()==1){
                m=String.valueOf(year)+"-0"+String.valueOf(month) ;
            } else {
                m=String.valueOf(year)+"-"+String.valueOf(month) ;
            }
        }


        String s1hql = "from Sysabnormal s where date_format(s.writeTime,'%Y-%m')= '"+m+"' group by abnormaltypecode" ;
        String s2hql = "select count(*) from Sysabnormal s where date_format(s.writeTime,'%Y-%m')= '"+m+"' group by abnormaltypecode";
        List<Sysabnormal>  listab= sysabnormalDao.list(s1hql);
        List<Sysabnormal>  listabnum= sysabnormalDao.countAll(s2hql);
        str.append("{");
        if(listab==null){
            str.append("'OtherViolations':0,'NetworkViolations':0,'ProcessViolations':0,");
            str.append("'PeripheralViolations':0,'URLViolations':0,'FluxViolations':0,");
            str.append("'SeparationViolations':0");
        }else {
            for (int i=0;i<listab.size();i++){
                Sysabnormal sb=listab.get(i);
                if(sb.getAbNormalTypeCode().equals("0000")) {
                    // 其他违规
                    str.append("'OtherViolations':'"+listabnum.get(i)+"',");
                } else if(sb.getAbNormalTypeCode().equals("0001")){
                    // 网络违规
                    str.append("'NetworkViolations':'"+listabnum.get(i)+"',");
                }else if(sb.getAbNormalTypeCode().equals("0002")){
                    // 进程违规
                    str.append("'ProcessViolations':'"+listabnum.get(i)+"',");
                }else if(sb.getAbNormalTypeCode().equals("0003")){
                    // 外设违规
                    str.append("'PeripheralViolations':'"+listabnum.get(i)+"',");
                }else if(sb.getAbNormalTypeCode().equals("0004")){
                    // URL违规
                    str.append("'URLViolations':'"+listabnum.get(i)+"',");
                }else if(sb.getAbNormalTypeCode().equals("0005")){
                    // 流量违规
                    str.append("'FluxViolations':'"+listabnum.get(i)+"',");
                } else if(sb.getAbNormalTypeCode().equals("0006")){
                    // 机卡分离违规
                    str.append("'SeparationViolations':'"+listabnum.get(i)+"',");
                }
            }
            String st=str.toString().substring(str.toString().lastIndexOf("{"),str.toString().length());
            if(!st.contains("OtherViolations")){
                str.append("'OtherViolations':'0',");
            }
            if(!st.contains("NetworkViolations")){
                str.append("'NetworkViolations':'0',");
            }
            if(!st.contains("ProcessViolations")){
                str.append("'ProcessViolations':'0',");
            }
            if(!st.contains("PeripheralViolations")){
                str.append("'PeripheralViolations':'0',");
            }
            if(!st.contains("URLViolations")){
                str.append("'URLViolations':'0',");
            }
            if(!st.contains("FluxViolations")){
                str.append("'FluxViolations':'0',");
            }
            if(!st.contains("SeparationViolations")){
                str.append("'SeparationViolations':'0',");
            }
        }
        str.append("}]}" );
        String strs = str.toString();
        if(strs.contains(",}")){
            strs = strs.replace(",}","}");
        }
        System.out.println(strs);
        return strs;
    }

    public String listReportYear(String reportDate)throws Exception{
        StringBuilder str=new StringBuilder();
        str.append("{success:true,root:1,rows:[");
        String y=null;
        if(reportDate==null){
            DateTimeUtil dateTimeUtil=new DateTimeUtil();
            int year=dateTimeUtil.getYear();
            y=String.valueOf(year) ;
        } else {
            y=reportDate;
        }
        String s1hql="from Sysabnormal s where date_format(s.writeTime,'%Y')= '"+y+"' group by abnormaltypecode" ;
        String s2hql = "select count(*) from Sysabnormal s where date_format(s.writeTime,'%Y')= '"+y+"' group by abnormaltypecode";
        List<Sysabnormal>  listab= sysabnormalDao.list(s1hql);
        List<Sysabnormal>  listabnum= sysabnormalDao.countAll(s2hql);
        str.append("{");
        if(listab==null){
            str.append("'OtherViolations':0,'NetworkViolations':0,'ProcessViolations':0,");
            str.append("'PeripheralViolations':0,'URLViolations':0,'FluxViolations':0,");
            str.append("'SeparationViolations':0");
        }else {
            for (int i=0;i<listab.size();i++){
                Sysabnormal sb=listab.get(i);
                if(sb.getAbNormalTypeCode().equals("0000")) {
                    // 其他违规
                    str.append("'OtherViolations':'"+listabnum.get(i)+"',");
                } else if(sb.getAbNormalTypeCode().equals("0001")){
                    // 网络违规
                    str.append("'NetworkViolations':'"+listabnum.get(i)+"',");
                }else if(sb.getAbNormalTypeCode().equals("0002")){
                    // 进程违规
                    str.append("'ProcessViolations':'"+listabnum.get(i)+"',");
                }else if(sb.getAbNormalTypeCode().equals("0003")){
                    // 外设违规
                    str.append("'PeripheralViolations':'"+listabnum.get(i)+"',");
                }else if(sb.getAbNormalTypeCode().equals("0004")){
                    // URL违规
                    str.append("'URLViolations':'"+listabnum.get(i)+"',");
                }else if(sb.getAbNormalTypeCode().equals("0005")){
                    // 流量违规
                    str.append("'FluxViolations':'"+listabnum.get(i)+"',");
                } else if(sb.getAbNormalTypeCode().equals("0006")){
                    // 机卡分离违规
                    str.append("'SeparationViolations':'"+listabnum.get(i)+"',");
                }
            }
            String st=str.toString().substring(str.toString().lastIndexOf("{"),str.toString().length());
            if(!st.contains("OtherViolations")){
                str.append("'OtherViolations':'0',");
            }
            if(!st.contains("NetworkViolations")){
                str.append("'NetworkViolations':'0',");
            }
            if(!st.contains("ProcessViolations")){
                str.append("'ProcessViolations':'0',");
            }
            if(!st.contains("PeripheralViolations")){
                str.append("'PeripheralViolations':'0',");
            }
            if(!st.contains("URLViolations")){
                str.append("'URLViolations':'0',");
            }
            if(!st.contains("FluxViolations")){
                str.append("'FluxViolations':'0',");
            }
            if(!st.contains("SeparationViolations")){
                str.append("'SeparationViolations':'0',");
            }
        }
        str.append("}]}" );
        String strs = str.toString();
        if(strs.contains(",}")){
            strs = strs.replace(",}","}");
        }
        System.out.println(strs);
        return strs;
    }

    @Override
    public String lineChartMonth(String reportDate) throws Exception {
        String str="{success:true,root:30,rows:[";
        String m=null;
        if(reportDate!=null){
            m=reportDate;
        }else {
            DateTimeUtil dateTimeUtil=new DateTimeUtil();
            int month=dateTimeUtil.getMonth();
            int year=dateTimeUtil.getYear();
            if(String.valueOf(month).length()==1){
                m=String.valueOf(year)+"-0"+String.valueOf(month) ;
            } else {
                m=String.valueOf(year)+"-"+String.valueOf(month) ;
            }
        }
        String s1hql =  "select DATE_FORMAT(writeTime,'%Y-%m-%d')as time ,count(*) as count from Sysabnormal" +
                " where DATE_FORMAT(writeTime,'%Y-%m')='"+m+"'  and abNormalTypeCode='0000' " +
                "group by DATE_FORMAT(writeTime,'%Y-%m-%d')  " +
                "order by DATE_FORMAT(writeTime,'%Y-%m-%d')  " ;
        String s2hql = "select DATE_FORMAT(writeTime,'%Y-%m-%d')as time ,count(*) as count from Sysabnormal" +
                " where DATE_FORMAT(writeTime,'%Y-%m')='"+m+"'  and abNormalTypeCode='0001' " +
                "group by DATE_FORMAT(writeTime,'%Y-%m-%d')  " +
                "order by DATE_FORMAT(writeTime,'%Y-%m-%d')  " ;
        String s3hql =  "select DATE_FORMAT(writeTime,'%Y-%m')as time ,count(*) as count from Sysabnormal" +
                " where DATE_FORMAT(writeTime,'%Y-%m')='"+m+"'  and abNormalTypeCode='0002' " +
                "group by DATE_FORMAT(writeTime,'%Y-%m-%d')  " +
                "order by DATE_FORMAT(writeTime,'%Y-%m-%d')  " ;
        String s4hql =  "select DATE_FORMAT(writeTime,'%Y-%m-%d')as time ,count(*) as count from Sysabnormal" +
                " where DATE_FORMAT(writeTime,'%Y-%m')='"+m+"'  and abNormalTypeCode='0003' " +
                "group by DATE_FORMAT(writeTime,'%Y-%m-%d')  " +
                "order by DATE_FORMAT(writeTime,'%Y-%m-%d')  " ;
        String s5hql = "select DATE_FORMAT(writeTime,'%Y-%m-%d')as time ,count(*) as count from Sysabnormal" +
                " where DATE_FORMAT(writeTime,'%Y-%m')='"+m+"'  and abNormalTypeCode='0004' " +
                "group by DATE_FORMAT(writeTime,'%Y-%m-%d')  " +
                "order by DATE_FORMAT(writeTime,'%Y-%m-%d')  " ;
        String s6hql =  "select DATE_FORMAT(writeTime,'%Y-%m-%d')as time ,count(*) as count from Sysabnormal" +
                " where DATE_FORMAT(writeTime,'%Y-%m')='"+m+"'  and abNormalTypeCode='0005' " +
                "group by DATE_FORMAT(writeTime,'%Y-%m-%d')  " +
                "order by DATE_FORMAT(writeTime,'%Y-%m-%d')  " ;
        String s7hql = "select DATE_FORMAT(writeTime,'%Y-%m-%d')as time ,count(*) as count from Sysabnormal" +
                " where DATE_FORMAT(writeTime,'%Y-%m')='"+m+"'  and abNormalTypeCode='0006' " +
                "group by DATE_FORMAT(writeTime,'%Y-%m-%d')  " +
                "order by DATE_FORMAT(writeTime,'%Y-%m-%d')  " ;
//       其他违规
        List list1= sysabnormalDao.countAllMap(s1hql);
        if(list1.size()==0){
            str+="{time:'"+m+"-01',type1:"+0+"},";
            str+="{time:'"+m+"-02',type1:"+0+"},";
            str+="{time:'"+m+"-03',type1:"+0+"},";
            str+="{time:'"+m+"-04',type1:"+0+"},";
            str+="{time:'"+m+"-05',type1:"+0+"},";
            str+="{time:'"+m+"-06',type1:"+0+"},";
            str+="{time:'"+m+"-07',type1:"+0+"},";
            str+="{time:'"+m+"-08',type1:"+0+"},";
            str+="{time:'"+m+"-09',type1:"+0+"},";
            str+="{time:'"+m+"-10',type1:"+0+"},";
            str+="{time:'"+m+"-11',type1:"+0+"},";
            str+="{time:'"+m+"-12',type1:"+0+"},";
            str+="{time:'"+m+"-13',type1:"+0+"},";
            str+="{time:'"+m+"-14',type1:"+0+"},";
            str+="{time:'"+m+"-15',type1:"+0+"},";
            str+="{time:'"+m+"-16',type1:"+0+"},";
            str+="{time:'"+m+"-17',type1:"+0+"},";
            str+="{time:'"+m+"-18',type1:"+0+"},";
            str+="{time:'"+m+"-19',type1:"+0+"},";
            str+="{time:'"+m+"-20',type1:"+0+"},";
            str+="{time:'"+m+"-21',type1:"+0+"},";
            str+="{time:'"+m+"-22',type1:"+0+"},";
            str+="{time:'"+m+"-23',type1:"+0+"},";
            str+="{time:'"+m+"-24',type1:"+0+"},";
            str+="{time:'"+m+"-25',type1:"+0+"},";
            str+="{time:'"+m+"-26',type1:"+0+"},";
            str+="{time:'"+m+"-27',type1:"+0+"},";
            str+="{time:'"+m+"-28',type1:"+0+"},";
            String month=m.substring(m.length()-2,m.length());
            String y=m.substring(0,4);
            int year=Integer.parseInt(y);
            if(month.equals("02")){
                if(year%4==0)   {
                    str+="{time:'"+m+"-29',type1:"+0+"},";
                }

            }else if(month.equals("04")||month.equals("06")||month.equals("09")||month.equals("11")) {
                str+="{time:'"+m+"-29',type1:"+0+"},";
                str+="{time:'"+m+"-30',type1:"+0+"},";
            } else {
                str+="{time:'"+m+"-29',type1:"+0+"},";
                str+="{time:'"+m+"-30',type1:"+0+"},";
                str+="{time:'"+m+"-31',type1:"+0+"},";
            }
        }
        for (int i=0;i<list1.size();i++){
            LineChart lineChart=new LineChart();
            Map map =(Map)list1.get(i);
            lineChart.setCount(Integer.valueOf(map.get("count").toString()));
            lineChart.setTime(map.get("time").toString());
            str+="{time:'"+lineChart.getTime()+"',type1:"+lineChart.getCount()+"}";
        }
//       网络违规
        List list2= sysabnormalDao.countAllMap(s2hql);
        if(list2.size()==0){
            str=Str(str,m,"2");
        }
        for (int i=0;i<list2.size();i++){
            LineChart lineChart=new LineChart();
            Map map =(Map)list2.get(i);
            lineChart.setCount(Integer.valueOf(map.get("count").toString()));
            lineChart.setTime(map.get("time").toString());
            if(str.toString().contains("time:'"+lineChart.getTime()+"',")){
                str =str.split("time:'"+lineChart.getTime()+"',")[0]+"time:'"+lineChart.getTime()+"',"+"type2:"+lineChart.getCount()+","+str.toString().split("time:'"+lineChart.getTime()+"',")[1];
                System.out.println(str);
            } else {
                str+=",{time:'"+lineChart.getTime()+"',type2:"+lineChart.getCount()+"}";
                System.out.println(str.toString());
            }

        }
//       进程违规
        List list3= sysabnormalDao.countAllMap(s3hql);
        if(list3.size()==0){
            str=Str(str,m,"3");
        }
        for (int i=0;i<list3.size();i++){
            LineChart lineChart=new LineChart();
            Map map =(Map)list3.get(i);
            lineChart.setCount(Integer.valueOf(map.get("count").toString()));
            lineChart.setTime(map.get("time").toString());
            if(str.toString().contains("time:'"+lineChart.getTime()+"',")){
                str=str.split("time:'"+lineChart.getTime()+"',")[0]+"time:'"+lineChart.getTime()+"',"+"type3:"+lineChart.getCount()+","+str.toString().split("time:'"+lineChart.getTime()+"',")[1];
                System.out.println(str);
            } else {
                str+=",{time:'"+lineChart.getTime()+"',type3:"+lineChart.getCount()+"}";
                System.out.println(str.toString());
            }

        }
//       外设违规
        List list4= sysabnormalDao.countAllMap(s4hql);
        if(list4.size()==0){
            str=Str(str,m,"4");
        }
        for (int i=0;i<list4.size();i++){
            LineChart lineChart=new LineChart();
            Map map =(Map)list4.get(i);
            lineChart.setCount(Integer.valueOf(map.get("count").toString()));
            lineChart.setTime(map.get("time").toString());
            if(str.toString().contains("time:'"+lineChart.getTime()+"',")){
                str=str.toString().split("time:'"+lineChart.getTime()+"',")[0]+"time:'"+lineChart.getTime()+"',"+"type4:"+lineChart.getCount()+","+str.toString().split("time:'"+lineChart.getTime()+"',")[1];
                System.out.println(str);
            } else {
                str+=",{time:'"+lineChart.getTime()+"',type4:"+lineChart.getCount()+"}";
                System.out.println(str.toString());
            }

        }
//       url违规
        List list5= sysabnormalDao.countAllMap(s5hql);
        if(list5.size()==0){
            str=Str(str,m,"5");
        }
        for (int i=0;i<list5.size();i++){
            LineChart lineChart=new LineChart();
            Map map =(Map)list5.get(i);
            lineChart.setCount(Integer.valueOf(map.get("count").toString()));
            lineChart.setTime(map.get("time").toString());
            if(str.toString().contains("time:'"+lineChart.getTime()+"',")){
                str=str.toString().split("time:'"+lineChart.getTime()+"',")[0]+"time:'"+lineChart.getTime()+"',"+"type5:"+lineChart.getCount()+","+str.toString().split("time:'"+lineChart.getTime()+"',")[1];
                System.out.println(str);
            } else {
                str+=(",{time:'"+lineChart.getTime()+"',type5:"+lineChart.getCount()+"}");
                System.out.println(str.toString());
            }

        }
//       流量违规
        List list6= sysabnormalDao.countAllMap(s6hql);
        if(list6.size()==0){
            str=Str(str,m,"6");
        }
        for (int i=0;i<list6.size();i++){
            LineChart lineChart=new LineChart();
            Map map =(Map)list6.get(i);
            lineChart.setCount(Integer.valueOf(map.get("count").toString()));
            lineChart.setTime(map.get("time").toString());
            if(str.toString().contains("time:'"+lineChart.getTime()+"',")){
                str=str.split("time:'"+lineChart.getTime()+"',")[0]+"time:'"+lineChart.getTime()+"',"+"type6:"+lineChart.getCount()+","+str.toString().split("time:'"+lineChart.getTime()+"',")[1];
                System.out.println(str);
            } else {
                str+=",{time:'"+lineChart.getTime()+"',type6:"+lineChart.getCount()+"}";
                System.out.println(str.toString());
            }

        }
//       机卡分离违规
        List list7= sysabnormalDao.countAllMap(s7hql);
        if(list7.size()==0){
            str=Str(str,m,"7");
        }
        for (int i=0;i<list7.size();i++){
            LineChart lineChart=new LineChart();
            Map map =(Map)list7.get(i);
            lineChart.setCount(Integer.valueOf(map.get("count").toString()));
            lineChart.setTime(map.get("time").toString());
            if(str.toString().contains("time:'"+lineChart.getTime()+"',")){
                str=str.split("time:'"+lineChart.getTime()+"',")[0]+"time:'"+lineChart.getTime()+"',"+"type7:"+lineChart.getCount()+","+str.toString().split("time:'"+lineChart.getTime()+"',")[1];
                System.out.println(str);
            } else {
                str+=",{time:'"+lineChart.getTime()+"',type7:"+lineChart.getCount()+"}";
                System.out.println(str.toString());
            }

        }
        str+="]}";
        System.out.println(str.toString());

//        String jsons=LineChart.addAll(str.toString());
        return str;
    }


    @Override
    public String lineChartYear(String reportDate) throws Exception {
//        StringBuilder str=new StringBuilder();
        String str="{success:true,root:7,rows:[";
        String y=null;
        if(reportDate==null){
            DateTimeUtil dateTimeUtil=new DateTimeUtil();
            int year=dateTimeUtil.getYear();
            y=String.valueOf(year) ;
        } else {
            y=reportDate;
        }
        str+="{time:'"+y+"-01',},";
        str+="{time:'"+y+"-02',},";
        str+="{time:'"+y+"-03',},";
        str+="{time:'"+y+"-04',},";
        str+="{time:'"+y+"-05',},";
        str+="{time:'"+y+"-06',},";
        str+="{time:'"+y+"-07',},";
        str+="{time:'"+y+"-08',},";
        str+="{time:'"+y+"-09',},";
        str+="{time:'"+y+"-10',},";
        str+="{time:'"+y+"-11',},";
        str+="{time:'"+y+"-12',},";
        String s1hql =  "select DATE_FORMAT(writeTime,'%Y-%m')as time ,count(*) as count from Sysabnormal" +
                " where DATE_FORMAT(writeTime,'%Y')='"+y+"'  and abNormalTypeCode='0000' " +
                "group by DATE_FORMAT(writeTime,'%Y-%m')  " +
                "order by DATE_FORMAT(writeTime,'%Y-%m')  " ;
        String s2hql = "select DATE_FORMAT(writeTime,'%Y-%m')as time ,count(*) as count from Sysabnormal" +
                " where DATE_FORMAT(writeTime,'%Y')='"+y+"'  and abNormalTypeCode='0001' " +
                "group by DATE_FORMAT(writeTime,'%Y-%m')  " +
                "order by DATE_FORMAT(writeTime,'%Y-%m')  " ;
        String s3hql =  "select DATE_FORMAT(writeTime,'%Y-%m')as time ,count(*) as count from Sysabnormal" +
                " where DATE_FORMAT(writeTime,'%Y')='"+y+"'  and abNormalTypeCode='0002' " +
                "group by DATE_FORMAT(writeTime,'%Y-%m')  " +
                "order by DATE_FORMAT(writeTime,'%Y-%m')  " ;
        String s4hql =  "select DATE_FORMAT(writeTime,'%Y-%m')as time ,count(*) as count from Sysabnormal" +
                " where DATE_FORMAT(writeTime,'%Y')='"+y+"'  and abNormalTypeCode='0003' " +
                "group by DATE_FORMAT(writeTime,'%Y-%m')  " +
                "order by DATE_FORMAT(writeTime,'%Y-%m')  " ;
        String s5hql = "select DATE_FORMAT(writeTime,'%Y-%m')as time ,count(*) as count from Sysabnormal" +
                " where DATE_FORMAT(writeTime,'%Y')='"+y+"'  and abNormalTypeCode='0004' " +
                "group by DATE_FORMAT(writeTime,'%Y-%m')  " +
                "order by DATE_FORMAT(writeTime,'%Y-%m')  " ;
        String s6hql =  "select DATE_FORMAT(writeTime,'%Y-%m')as time ,count(*) as count from Sysabnormal" +
                " where DATE_FORMAT(writeTime,'%Y')='"+y+"'  and abNormalTypeCode='0005' " +
                "group by DATE_FORMAT(writeTime,'%Y-%m')  " +
                "order by DATE_FORMAT(writeTime,'%Y-%m')  " ;
        String s7hql = "select DATE_FORMAT(writeTime,'%Y-%m')as time ,count(*) as count from Sysabnormal" +
                " where DATE_FORMAT(writeTime,'%Y')='"+y+"'  and abNormalTypeCode='0006' " +
                "group by DATE_FORMAT(writeTime,'%Y-%m')  " +
                "order by DATE_FORMAT(writeTime,'%Y-%m')  " ;
//       其他违规
        List list1= sysabnormalDao.countAllMap(s1hql);
//        if(list1.size()==0){
//         str=StrYear(str,y,"1") ;
//        }
        for (int i=0;i<list1.size();i++){
            LineChart lineChart=new LineChart();
            Map map =(Map)list1.get(i);
            lineChart.setCount(Integer.valueOf(map.get("count").toString()));
            lineChart.setTime(map.get("time").toString());
            if(str.toString().contains("time:'"+lineChart.getTime()+"',")){
                str =str.split("time:'"+lineChart.getTime()+"',")[0]+"time:'"+lineChart.getTime()+"',"+"type1:"+lineChart.getCount()+","+str.toString().split("time:'"+lineChart.getTime()+"',")[1];
//                String ss1=str.split("time:'"+lineChart.getTime()+"',")[1];
//                String ss5=ss1.split("type1:")[1];
//                String ss4=ss5.split(",")[1];
//                String ss6=ss1.substring(ss1.indexOf(","),ss1.length());
//                String ss2=str.split("time:'"+lineChart.getTime()+"',")[1].split("type1:")[0];
//                String ss3=str.split("time:'"+lineChart.getTime()+"',")[0]+"time:'"+lineChart.getTime()+"',"+"type1:"+lineChart.getCount()+ss6;
//                str=ss3;
                System.out.println(str);
            }
        }

//
//       网络违规
        List list2= sysabnormalDao.countAllMap(s2hql);
//        if(list2.size()==0){
//            str=StrYear(str,y,"2");
//        }
        for (int i=0;i<list2.size();i++){
            LineChart lineChart=new LineChart();
            Map map =(Map)list2.get(i);
            lineChart.setCount(Integer.valueOf(map.get("count").toString()));
            lineChart.setTime(map.get("time").toString());
            if(str.toString().contains("time:'"+lineChart.getTime()+"',")){
                str =str.split("time:'"+lineChart.getTime()+"',")[0]+"time:'"+lineChart.getTime()+"',"+"type2:"+lineChart.getCount()+","+str.toString().split("time:'"+lineChart.getTime()+"',")[1];
//                String ss1=str.split("time:'"+lineChart.getTime()+"',")[1];
//                String ss5=ss1.split("type2:")[1];
//                String ss4=ss5.split(",")[1];
//                String ss6=ss1.substring(ss1.indexOf(","),ss1.length());
//                String ss2=str.split("time:'"+lineChart.getTime()+"',")[1].split("type1:")[0];
//                String ss3=str.split("time:'"+lineChart.getTime()+"',")[0]+"time:'"+lineChart.getTime()+"',"+"type2:"+lineChart.getCount()+ss6;
//                str=ss3;
//                System.out.println(str);
            } else {
                str+=",{time:'"+lineChart.getTime()+"',type2:"+lineChart.getCount()+"}";
                System.out.println(str.toString());
            }

        }
//       进程违规
        List list3= sysabnormalDao.countAllMap(s3hql);
//        if(list3.size()==0){
//            str=StrYear(str,y,"3");
//        }
        for (int i=0;i<list3.size();i++){
            LineChart lineChart=new LineChart();
            Map map =(Map)list3.get(i);
            lineChart.setCount(Integer.valueOf(map.get("count").toString()));
            lineChart.setTime(map.get("time").toString());
            if(str.toString().contains("time:'"+lineChart.getTime()+"',")){
                str=str.split("time:'"+lineChart.getTime()+"',")[0]+"time:'"+lineChart.getTime()+"',"+"type3:"+lineChart.getCount()+","+str.toString().split("time:'"+lineChart.getTime()+"',")[1];
                System.out.println(str);
            } else {
                str+=",{time:'"+lineChart.getTime()+"',type3:"+lineChart.getCount()+"}";
                System.out.println(str.toString());
            }

        }
//       外设违规
        List list4= sysabnormalDao.countAllMap(s4hql);
//        if(list4.size()==0){
//            str=StrYear(str,y,"4");
//        }
        for (int i=0;i<list4.size();i++){
            LineChart lineChart=new LineChart();
            Map map =(Map)list4.get(i);
            lineChart.setCount(Integer.valueOf(map.get("count").toString()));
            lineChart.setTime(map.get("time").toString());
            if(str.toString().contains("time:'"+lineChart.getTime()+"',")){
                str=str.toString().split("time:'"+lineChart.getTime()+"',")[0]+"time:'"+lineChart.getTime()+"',"+"type4:"+lineChart.getCount()+","+str.toString().split("time:'"+lineChart.getTime()+"',")[1];
//                String ss1=str.split("time:'"+lineChart.getTime()+"',")[1];
//                String ss5=ss1.split("type3:")[1];
//                String ss4=ss5.split(",")[1];
//                String ss6=ss1.substring(ss1.indexOf(","),ss1.length());
//                String ss2=str.split("time:'"+lineChart.getTime()+"',")[1].split("type1:")[0];
//                String ss3=str.split("time:'"+lineChart.getTime()+"',")[0]+"time:'"+lineChart.getTime()+"',"+"type4:"+lineChart.getCount()+","+ss1;
//                str=ss3;
                System.out.println(str);
            } else {
                str+=",{time:'"+lineChart.getTime()+"',type4:"+lineChart.getCount()+"}";
                System.out.println(str.toString());
            }
        }
//       url违规
        List list5= sysabnormalDao.countAllMap(s5hql);
//        if(list5.size()==0){
//            str=StrYear(str,y,"5");
//        }
        for (int i=0;i<list5.size();i++){
            LineChart lineChart=new LineChart();
            Map map =(Map)list5.get(i);
            lineChart.setCount(Integer.valueOf(map.get("count").toString()));
            lineChart.setTime(map.get("time").toString());
            if(str.toString().contains("time:'"+lineChart.getTime()+"',")){
                str=str.toString().split("time:'"+lineChart.getTime()+"',")[0]+"time:'"+lineChart.getTime()+"',"+"type5:"+lineChart.getCount()+","+str.toString().split("time:'"+lineChart.getTime()+"',")[1];
                System.out.println(str);
            } else {
                str+=(",{time:'"+lineChart.getTime()+"',type5:"+lineChart.getCount()+"}");
                System.out.println(str.toString());
            }

        }
//       流量违规
        List list6= sysabnormalDao.countAllMap(s6hql);
//        if(list6.size()==0){
//            str=StrYear(str,y,"6");
//        }
        for (int i=0;i<list6.size();i++){
            LineChart lineChart=new LineChart();
            Map map =(Map)list6.get(i);
            lineChart.setCount(Integer.valueOf(map.get("count").toString()));
            lineChart.setTime(map.get("time").toString());
            if(str.toString().contains("time:'"+lineChart.getTime()+"',")){
                str=str.split("time:'"+lineChart.getTime()+"',")[0]+"time:'"+lineChart.getTime()+"',"+"type6:"+lineChart.getCount()+","+str.toString().split("time:'"+lineChart.getTime()+"',")[1];
                System.out.println(str);
            } else {
                str+=",{time:'"+lineChart.getTime()+"',type6:"+lineChart.getCount()+"}";
                System.out.println(str.toString());
            }

        }
//       机卡分离违规
        List list7= sysabnormalDao.countAllMap(s7hql);
//        if(list7.size()==0){
//            str=StrYear(str,y,"7");
//        }
        for (int i=0;i<list7.size();i++){
            LineChart lineChart=new LineChart();
            Map map =(Map)list7.get(i);
            lineChart.setCount(Integer.valueOf(map.get("count").toString()));
            lineChart.setTime(map.get("time").toString());
            if(str.toString().contains("time:'"+lineChart.getTime()+"',")){
                str=str.split("time:'"+lineChart.getTime()+"',")[0]+"time:'"+lineChart.getTime()+"',"+"type7:"+lineChart.getCount()+","+str.toString().split("time:'"+lineChart.getTime()+"',")[1];
                System.out.println(str);
            } else {
                str+=",{time:'"+lineChart.getTime()+"',type7:"+lineChart.getCount()+"}";
                System.out.println(str.toString());
            }

        }
        str+="]}";
        System.out.println(str.toString());
        if(str.contains("},]}")){
            return str.toString().replace("},]}","}]}");
        }else {
            return str.toString();
        }
//        String jsons=LineChart.addAll(str.toString());

    }
    private String Str(String str,String m,String num){
        if(str.toString().contains("time:'"+m+"-01',")){
            str =str.split("time:'"+m+"-01',")[0]+"time:'"+m+"-01',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-01',")[1];
        }if(str.toString().contains("time:'"+m+"-02',")){
            str =str.split("time:'"+m+"-02',")[0]+"time:'"+m+"-02',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-02',")[1];
        }if(str.toString().contains("time:'"+m+"-03',")){
            str =str.split("time:'"+m+"-03',")[0]+"time:'"+m+"-03',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-03',")[1];
        }if(str.toString().contains("time:'"+m+"-04',")){
            str =str.split("time:'"+m+"-04',")[0]+"time:'"+m+"-04',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-04',")[1];
        }if(str.toString().contains("time:'"+m+"-05',")){
            str =str.split("time:'"+m+"-05',")[0]+"time:'"+m+"-05',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-05',")[1];
        }if(str.toString().contains("time:'"+m+"-06',")){
            str =str.split("time:'"+m+"-06',")[0]+"time:'"+m+"-06',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-06',")[1];
        }if(str.toString().contains("time:'"+m+"-07',")){
            str =str.split("time:'"+m+"-07',")[0]+"time:'"+m+"-07',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-07',")[1];
        }if(str.toString().contains("time:'"+m+"-08',")){
            str =str.split("time:'"+m+"-08',")[0]+"time:'"+m+"-08',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-08',")[1];
        }if(str.toString().contains("time:'"+m+"-09',")){
            str =str.split("time:'"+m+"-09',")[0]+"time:'"+m+"-09',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-09',")[1];
        }if(str.toString().contains("time:'"+m+"-10',")){
            str =str.split("time:'"+m+"-10',")[0]+"time:'"+m+"-10',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-10',")[1];
        }if(str.toString().contains("time:'"+m+"-11',")){
            str =str.split("time:'"+m+"-11',")[0]+"time:'"+m+"-11',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-11',")[1];
        }if(str.toString().contains("time:'"+m+"-12',")){
            str =str.split("time:'"+m+"-12',")[0]+"time:'"+m+"-12',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-12',")[1];
        }if(str.toString().contains("time:'"+m+"-13',")){
            str =str.split("time:'"+m+"-13',")[0]+"time:'"+m+"-13',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-13',")[1];
        }if(str.toString().contains("time:'"+m+"-14',")){
            str =str.split("time:'"+m+"-14',")[0]+"time:'"+m+"-14',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-14',")[1];
        }if(str.toString().contains("time:'"+m+"-15',")){
            str =str.split("time:'"+m+"-15',")[0]+"time:'"+m+"-15',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-15',")[1];
        }if(str.toString().contains("time:'"+m+"-16',")){
            str =str.split("time:'"+m+"-16',")[0]+"time:'"+m+"-16',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-16',")[1];
        }if(str.toString().contains("time:'"+m+"-17',")){
            str =str.split("time:'"+m+"-17',")[0]+"time:'"+m+"-17',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-17',")[1];
        }
        if(str.toString().contains("time:'"+m+"-18',")){
            str =str.split("time:'"+m+"-18',")[0]+"time:'"+m+"-18',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-18',")[1];
        }
        if(str.toString().contains("time:'"+m+"-19',")){
            str =str.split("time:'"+m+"-19',")[0]+"time:'"+m+"-19',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-19',")[1];
        }
        if(str.toString().contains("time:'"+m+"-20',")){
            str =str.split("time:'"+m+"-20',")[0]+"time:'"+m+"-20',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-20',")[1];
        }
        if(str.toString().contains("time:'"+m+"-21',")){
            str =str.split("time:'"+m+"-21',")[0]+"time:'"+m+"-21',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-21',")[1];
        }
        if(str.toString().contains("time:'"+m+"-22',")){
            str =str.split("time:'"+m+"-22',")[0]+"time:'"+m+"-22',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-22',")[1];
        }
        if(str.toString().contains("time:'"+m+"-23',")){
            str =str.split("time:'"+m+"-23',")[0]+"time:'"+m+"-23',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-23',")[1];
        }
        if(str.toString().contains("time:'"+m+"-24',")){
            str =str.split("time:'"+m+"-24',")[0]+"time:'"+m+"-24',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-24',")[1];
        }
        if(str.toString().contains("time:'"+m+"-25',")){
            str =str.split("time:'"+m+"-25',")[0]+"time:'"+m+"-25',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-25',")[1];
        }
        if(str.toString().contains("time:'"+m+"-26',")){
            str =str.split("time:'"+m+"-26',")[0]+"time:'"+m+"-26',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-26',")[1];
        }
        if(str.toString().contains("time:'"+m+"-27',")){
            str =str.split("time:'"+m+"-27',")[0]+"time:'"+m+"-27',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-27',")[1];
        }
        if(str.toString().contains("time:'"+m+"-28',")){
            str =str.split("time:'"+m+"-28',")[0]+"time:'"+m+"-28',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-28',")[1];
        }
        String month=m.substring(m.length()-2,m.length());
        String y=m.substring(0,4);
        int year=Integer.parseInt(y);
        if(month.equals("02")){
            if(year%4==0)   {
                if(str.toString().contains("time:'"+m+"-29',")){
                    str =str.split("time:'"+m+"-29',")[0]+"time:'"+m+"-29',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-29',")[1];
                }
                return str;
            }else {
                return str;
            }

        }else if(month.equals("04")||month.equals("06")||month.equals("09")||month.equals("11")) {
            if(str.toString().contains("time:'"+m+"-29',")){
                str =str.split("time:'"+m+"-29',")[0]+"time:'"+m+"-29',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-29',")[1];
            }
            if(str.toString().contains("time:'"+m+"-30',")){
                str =str.split("time:'"+m+"-30',")[0]+"time:'"+m+"-30',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-30',")[1];
            }
            return str;
        }else {
            if(str.toString().contains("time:'"+m+"-29',")){
                str =str.split("time:'"+m+"-29',")[0]+"time:'"+m+"-29',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-29',")[1];
            }
            if(str.toString().contains("time:'"+m+"-30',")){
                str =str.split("time:'"+m+"-30',")[0]+"time:'"+m+"-30',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-30',")[1];
            }
            if(str.toString().contains("time:'"+m+"-31',")){
                str =str.split("time:'"+m+"-31',")[0]+"time:'"+m+"-31',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-31',")[1];
            }
            return str;
        }
    }
    private String StrYear(String str,String m,String num){
        if(str.toString().contains("time:'"+m+"-01',")){
            str =str.split("time:'"+m+"-01',")[0]+"time:'"+m+"-01',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-01',")[1];
        }if(str.toString().contains("time:'"+m+"-02',")){
            str =str.split("time:'"+m+"-02',")[0]+"time:'"+m+"-02',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-02',")[1];
        }if(str.toString().contains("time:'"+m+"-03',")){
            str =str.split("time:'"+m+"-03',")[0]+"time:'"+m+"-03',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-03',")[1];
        }if(str.toString().contains("time:'"+m+"-04',")){
            str =str.split("time:'"+m+"-04',")[0]+"time:'"+m+"-04',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-04',")[1];
        }if(str.toString().contains("time:'"+m+"-05',")){
            str =str.split("time:'"+m+"-05',")[0]+"time:'"+m+"-05',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-05',")[1];
        }if(str.toString().contains("time:'"+m+"-06',")){
            str =str.split("time:'"+m+"-06',")[0]+"time:'"+m+"-06',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-06',")[1];
        }if(str.toString().contains("time:'"+m+"-07',")){
            str =str.split("time:'"+m+"-07',")[0]+"time:'"+m+"-07',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-07',")[1];
        }if(str.toString().contains("time:'"+m+"-08',")){
            str =str.split("time:'"+m+"-08',")[0]+"time:'"+m+"-08',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-08',")[1];
        }if(str.toString().contains("time:'"+m+"-09',")){
            str =str.split("time:'"+m+"-09',")[0]+"time:'"+m+"-09',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-09',")[1];
        }if(str.toString().contains("time:'"+m+"-10',")){
            str =str.split("time:'"+m+"-10',")[0]+"time:'"+m+"-10',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-10',")[1];
        }if(str.toString().contains("time:'"+m+"-11',")){
            str =str.split("time:'"+m+"-11',")[0]+"time:'"+m+"-11',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-11',")[1];
        }if(str.toString().contains("time:'"+m+"-12',")){
            str =str.split("time:'"+m+"-12',")[0]+"time:'"+m+"-12',"+"type"+num+":"+0+","+str.toString().split("time:'"+m+"-12',")[1];
        }
        return str;
    }
}

