package com.hzjava.monitorcenter.utils;

import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-3-20
 * Time: 下午3:02
 * To change this template use File | Settings | File Templates.
 */
public class LineChart {
    public String time;
    public int  count;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public static String addAll(String json ){

        String js="{success:true,root:12,rows:[";
        String[] str=json.split("time:") ;
        String s1=null;
        String s2=null;
        String s3=null;
        String s4=null;
        String s5=null;
        String s6=null;
        String s7=null;
        String s8=null;
        String s9=null;
        String s10=null;
        String s11=null;
        String s12=null;
        String time=null;
        for(String st :str) {
            if(st.contains("{success:true,root:")) {
                continue;
            }else {
                String strs=st.split("}")[0];
                time=strs.substring(1,5);
                if(strs.substring(6,8).equals("01")){
                    s1=strs.split("',")[1];
                }if(strs.substring(6,8).equals("02")){
                    s2=strs.split("',")[1];
                }if(strs.substring(6,8).equals("03")){
                    s3=strs.split("',")[1];
                }if(strs.substring(6,8).equals("04")){
                    s4=strs.split("',")[1];
                }if(strs.substring(6,8).equals("05")){
                    s5=strs.split("',")[1];
                }if(strs.substring(6,8).equals("06")){
                    s6=strs.split("',")[1];
                }if(strs.substring(6,8).equals("07")){
                    s7=strs.split("',")[1];
                }if(strs.substring(6,8).equals("08")){
                    s8=strs.split("',")[1];
                }if(strs.substring(6,8).equals("09")){
                    s9=strs.split("',")[1];
                }if(strs.substring(6,8).equals("10")){
                    s10=strs.split("',")[1];
                }if(strs.substring(6,8).equals("11")){
                    s11=strs.split("',")[1];
                }if(strs.substring(6,8).equals("12")){
                    s12=strs.split("',")[1];
                }
            }
        }
        //System.out.println(s1);

        int s1_type1=0 ,s2_type1=0,s3_type1=0,s4_type1=0,s5_type1=0,s6_type1=0,s7_type1=0,s8_type1=0,s9_type1=0,s10_type1=0,s11_type1=0,s12_type1=0;
        int s1_type2=0 ,s2_type2=0,s3_type2=0,s4_type2=0,s5_type2=0,s6_type2=0,s7_type2=0,s8_type2=0,s9_type2=0,s10_type2=0,s11_type2=0,s12_type2=0;
        int s1_type3=0 ,s2_type3=0,s3_type3=0,s4_type3=0,s5_type3=0,s6_type3=0,s7_type3=0,s8_type3=0,s9_type3=0,s10_type3=0,s11_type3=0,s12_type3=0;
        int s1_type4=0,s2_type4=0,s3_type4=0,s4_type4=0,s5_type4=0,s6_type4=0,s7_type4=0,s8_type4=0,s9_type4=0,s10_type4=0,s11_type4=0,s12_type4=0;
        int s1_type5=0,s2_type5=0,s3_type5=0,s4_type5=0,s5_type5=0,s6_type5=0,s7_type5=0,s8_type5=0,s9_type5=0,s10_type5=0,s11_type5=0,s12_type5=0;
        int s1_type6=0,s2_type6=0,s3_type6=0,s4_type6=0,s5_type6=0,s6_type6=0,s7_type6=0,s8_type6=0,s9_type6=0,s10_type6=0,s11_type6=0,s12_type6=0;
        int s1_type7=0 ,s2_type7=0,s3_type7=0,s4_type7=0,s5_type7=0,s6_type7=0,s7_type7=0,s8_type7=0,s9_type7=0,s10_type7=0,s11_type7=0,s12_type7=0;
        if(s1==null){
            s1_type1=0;
            s1_type2=0;
            s1_type3=0;
            s1_type4=0;
            s1_type5=0;
            s1_type6=0;
            s1_type7=0;
        } else {
            String[] strings=s1.split(","); {}
            for (String sts :strings){
                if(sts.contains("type1")){
                    s1_type1= Integer.parseInt(sts.split(":")[1]) ;
                    //System.out.println("s1_type1=="+s1_type1);
                }
                if(sts.contains("type2")){
                    s1_type2= Integer.parseInt(sts.split(":")[1]) ;
                    //System.out.println("s1_type2=="+s1_type2);
                }
                if(sts.contains("type3")){
                    s1_type3= Integer.parseInt(sts.split(":")[1]) ;
                    //System.out.println("s1_type3=="+s1_type3);
                }
                if(sts.contains("type4")){
                    s1_type4= Integer.parseInt(sts.split(":")[1]) ;
                    //System.out.println("s1_type4=="+s1_type4);
                }
                if(sts.contains("type5")){
                    s1_type5= Integer.parseInt(sts.split(":")[1]) ;
                    //System.out.println("s1_type5=="+s1_type5);
                }
                if(sts.contains("type6")){
                    s1_type6= Integer.parseInt(sts.split(":")[1]) ;
                    //System.out.println("s1_type6=="+s1_type6);
                }
                if(sts.contains("type7")){
                    s1_type7= Integer.parseInt(sts.split(":")[1]) ;
                    //System.out.println("s1_type7=="+s1_type7);
                }
            }
        }
        js+="{time:'"+time+"-01',type1:" + s1_type1 +",type2:"+s1_type2+",type3:"+s1_type3+",type4:"+s1_type4+",type5:"+s1_type5+",type6:"+s1_type6+",type7:"+s1_type7+"}," ;
        //System.out.println(js);
        if(s2==null){
            s2_type1=s1_type1;
            s2_type2=s1_type2;
            s2_type3=s1_type3;
            s2_type4=s1_type4;
            s2_type5=s1_type5;
            s2_type6=s1_type6;
            s2_type7=s1_type7;
        } else {
            String[] strings2=s2.split(",");
            for (String sts :strings2){
                if(sts.contains("type1")){
                    s2_type1= Integer.parseInt(sts.split(":")[1])+s1_type1 ;
                    //System.out.println("s2_type1=="+s2_type1);
                }
                if(sts.contains("type2")){
                    s2_type2= Integer.parseInt(sts.split(":")[1])+s1_type2 ;
                    //System.out.println("s2_type2=="+s2_type2);
                }
                if(sts.contains("type3")){
                    s2_type3= Integer.parseInt(sts.split(":")[1])+s1_type3 ;
                    //System.out.println("s2_type3=="+s2_type3);
                }
                if(sts.contains("type4")){
                    s2_type4= Integer.parseInt(sts.split(":")[1])+s1_type4 ;
                    //System.out.println("s2_type4=="+s2_type4);
                }
                if(sts.contains("type5")){
                    s2_type5= Integer.parseInt(sts.split(":")[1])+s1_type5 ;
                    //System.out.println("s2_type5=="+s2_type5);
                }
                if(sts.contains("type6")){
                    s2_type6= Integer.parseInt(sts.split(":")[1])+s1_type6 ;
                    //System.out.println("s2_type6=="+s2_type6);
                }
                if(sts.contains("type7")){
                    s2_type7= Integer.parseInt(sts.split(":")[1])+s1_type7 ;
                    //System.out.println("s2_type7=="+s2_type7);
                }
            }
        }
        if(s2_type1==0){
            s2_type1=s1_type1;
        }
        if(s2_type2==0){
            s2_type2=s1_type2;
        }
        if(s2_type3==0){
            s2_type3=s1_type3;
        }
        if(s2_type4==0){
            s2_type4=s1_type4;
        }
        if(s2_type5==0){
            s2_type5=s1_type5;
        }
        if(s2_type6==0){
            s2_type6=s1_type6;
        }
        if(s2_type7==0){
            s2_type7=s1_type7;
        }

        js+="{time:'"+time+"-02',type1:" + s2_type1 +",type2:"+s2_type2+
                ",type3:"+s2_type3+",type4:"+s2_type4+",type5:"+s2_type5+",type6:"+s2_type6+",type7:"+s2_type7+"}," ;
        //System.out.println(js);
        if(s3==null){
            s3_type1=s2_type1;
            s3_type2=s2_type2;
            s3_type3=s2_type3;
            s3_type4=s2_type4;
            s3_type5=s2_type5;
            s3_type6=s2_type6;
            s3_type7=s2_type7;
        } else {
            String[] strings3=s3.split(",");
            for (String sts :strings3){
                if(sts.contains("type1")){
                    s3_type1= Integer.parseInt(sts.split(":")[1])+s2_type1 ;
                    //System.out.println("s3_type1=="+s3_type1);
                }
                if(sts.contains("type2")){
                    s3_type2= Integer.parseInt(sts.split(":")[1])+s2_type2 ;
                    //System.out.println("s3_type2=="+s3_type2);
                }
                if(sts.contains("type3")){
                    s3_type3= Integer.parseInt(sts.split(":")[1])+s2_type3 ;
                    //System.out.println("s3_type3=="+s3_type3);
                }
                if(sts.contains("type4")){
                    s3_type4= Integer.parseInt(sts.split(":")[1])+s2_type4 ;
                    //System.out.println("s3_type4=="+s3_type4);
                }
                if(sts.contains("type5")){
                    s3_type5= Integer.parseInt(sts.split(":")[1]) +s2_type5;
                    //System.out.println("s3_type5=="+s3_type5);
                }
                if(sts.contains("type6")){
                    s3_type6= Integer.parseInt(sts.split(":")[1]) +s2_type6;
                    //System.out.println("s3_type6=="+s3_type6);
                }
                if(sts.contains("type7")){
                    s3_type7= Integer.parseInt(sts.split(":")[1]) +s2_type7;
                    //System.out.println("s3_type7=="+s3_type7);
                }
            }
        }
        if(s3_type1==0){
            s3_type1=s2_type1;
        }
        if(s3_type2==0){
            s3_type2=s2_type2;
        }
        if(s3_type3==0){
            s3_type3=s2_type3;
        }
        if(s3_type4==0){
            s3_type4=s2_type4;
        }
        if(s3_type5==0){
            s3_type5=s2_type5;
        }
        if(s3_type6==0){
            s3_type6=s2_type6;
        }
        if(s3_type7==0){
            s3_type7=s2_type7;
        }
        js+="{time:'"+time+"-03',type1:" + s3_type1 +",type2:"+s3_type2+",type3:"+s3_type3+
                ",type4:"+s3_type4+",type5:"+s3_type5+",type6:"+s3_type6+",type7:"+s3_type7+"}," ;
        //System.out.println(js);
        if(s4==null){
            s4_type1=s3_type1;
            s4_type2=s3_type2;
            s4_type3=s3_type3;
            s4_type4=s3_type4;
            s4_type5=s3_type5;
            s4_type6=s3_type6;
            s4_type7=s3_type7;
        } else {
            String[] strings4=s4.split(",");
            for (String sts :strings4){
                if(sts.contains("type1")){
                    s4_type1= Integer.parseInt(sts.split(":")[1])+s3_type1 ;
                    //System.out.println("s4_type1=="+s4_type1);
                }
                if(sts.contains("type2")){
                    s4_type2= Integer.parseInt(sts.split(":")[1])+s3_type2  ;
                    //System.out.println("s4_type2=="+s4_type2);
                }
                if(sts.contains("type3")){
                    s4_type3= Integer.parseInt(sts.split(":")[1])+s3_type3  ;
                    //System.out.println("s4_type3=="+s4_type3);
                }
                if(sts.contains("type4")){
                    s4_type4= Integer.parseInt(sts.split(":")[1])+s3_type4  ;
                    //System.out.println("s4_type4=="+s4_type4);
                }
                if(sts.contains("type5")){
                    s4_type5= Integer.parseInt(sts.split(":")[1])+s3_type5  ;
                    //System.out.println("s4_type5=="+s4_type5);
                }
                if(sts.contains("type6")){
                    s4_type6= Integer.parseInt(sts.split(":")[1])+s3_type6  ;
                    //System.out.println("s4_type6=="+s4_type6);
                }
                if(sts.contains("type7")){
                    s4_type7= Integer.parseInt(sts.split(":")[1])+s3_type7  ;
                    //System.out.println("s4_type7=="+s4_type7);
                }
            }
        }
        if(s4_type1==0){
            s4_type1=s3_type1;
        }
        if(s4_type2==0){
            s4_type2=s3_type2;
        }
        if(s4_type3==0){
            s4_type3=s3_type3;
        }
        if(s4_type4==0){
            s4_type4=s3_type4;
        }
        if(s4_type5==0){
            s4_type5=s3_type5;
        }
        if(s4_type6==0){
            s4_type6=s3_type6;
        }
        if(s4_type7==0){
            s4_type7=s3_type7;
        }
        js+="{time:'"+time+"-04',type1:" + s4_type1 +",type2:"+s4_type2+",type3:"+s4_type3+
                ",type4:"+s4_type4+",type5:"+s4_type5+",type6:"+s4_type6+",type7:"+s4_type7+"}," ;
        //System.out.println(js);
        if(s5==null){
            s5_type1=s4_type1;
            s5_type2=s4_type2;
            s5_type3=s4_type3;
            s5_type4=s4_type4;
            s5_type5=s4_type5;
            s5_type6=s4_type6;
            s5_type7=s4_type7;
        } else {
            String[] strings5=s5.split(",");
            for (String sts :strings5){
                if(sts.contains("type1")){
                    s5_type1= Integer.parseInt(sts.split(":")[1])+s4_type1 ;
                    //System.out.println("s5_type1=="+s5_type1);
                }
                if(sts.contains("type2")){
                    s5_type2= Integer.parseInt(sts.split(":")[1])+s4_type2 ;
                    //System.out.println("s5_type2=="+s5_type2);
                }
                if(sts.contains("type3")){
                    s5_type3= Integer.parseInt(sts.split(":")[1])+s4_type3 ;
                    //System.out.println("s5_type3=="+s5_type3);
                }
                if(sts.contains("type4")){
                    s5_type4= Integer.parseInt(sts.split(":")[1])+s4_type4 ;
                    //System.out.println("s5_type4=="+s5_type4);
                }
                if(sts.contains("type5")){
                    s5_type5= Integer.parseInt(sts.split(":")[1])+s4_type5 ;
                    //System.out.println("s5_type5=="+s5_type5);
                }
                if(sts.contains("type6")){
                    s5_type6= Integer.parseInt(sts.split(":")[1])+s4_type6 ;
                    //System.out.println("s5_type6=="+s5_type6);
                }
                if(sts.contains("type7")){
                    s5_type7= Integer.parseInt(sts.split(":")[1])+s4_type7 ;
                    //System.out.println("s5_type7=="+s5_type7);
                }
            }
        }
        if(s5_type1==0){
            s5_type1=s4_type1;
        }
        if(s5_type2==0){
            s5_type2=s4_type2;
        }
        if(s5_type3==0){
            s5_type3=s4_type3;
        }
        if(s5_type4==0){
            s5_type4=s4_type4;
        }
        if(s5_type5==0){
            s5_type5=s4_type5;
        }
        if(s5_type6==0){
            s5_type6=s4_type6;
        }
        if(s5_type7==0){
            s5_type7=s4_type7;
        }
        js+="{time:'"+time+"-05',type1:" + s5_type1 +",type2:"+s5_type2+",type3:"+s5_type3+
                ",type4:"+s5_type4+",type5:"+s5_type5+",type6:"+s5_type6+",type7:"+s5_type7+"}," ;
        //System.out.println(js);
        //System.out.println(s6);
        if(s6==null){
            s6_type1=s5_type1;
            s6_type2=s5_type2;
            s6_type3=s5_type3;
            s6_type4=s5_type4;
            s6_type5=s5_type5;
            s6_type6=s5_type6;
            s6_type7=s5_type7;
        } else {
            String[] strings6=s6.split(",");
            for (String sts :strings6){
                if(sts.contains("type1")){
                    s6_type1= Integer.parseInt(sts.split(":")[1])+s5_type1 ;
                    //System.out.println("s6_type1=="+s6_type1);
                }
                if(sts.contains("type2")){
                    s6_type2= Integer.parseInt(sts.split(":")[1])+s5_type2 ;
                    //System.out.println("s6_type2=="+s6_type2);
                }
                if(sts.contains("type3")){
                    s6_type3= Integer.parseInt(sts.split(":")[1])+s5_type3 ;
                    //System.out.println("s6_type3=="+s6_type3);
                }
                if(sts.contains("type4")){
                    s6_type4= Integer.parseInt(sts.split(":")[1])+s5_type4 ;
                    //System.out.println("s6_type4=="+s6_type4);
                }
                if(sts.contains("type5")){
                    s6_type5= Integer.parseInt(sts.split(":")[1]) +s5_type5;
                    //System.out.println("s6_type5=="+s6_type5);
                }
                if(sts.contains("type6")){
                    s6_type6= Integer.parseInt(sts.split(":")[1]) +s5_type6;
                    //System.out.println("s6_type6=="+s6_type6);
                }
                if(sts.contains("type7")){
                    s6_type7= Integer.parseInt(sts.split(":")[1]) +s5_type7;
                    //System.out.println("s6_type7=="+s6_type7);
                }
            }
        }
        if(s6_type1==0){
            s6_type1=s5_type1;
        }
        if(s6_type2==0){
            s6_type2=s5_type2;
        }
        if(s6_type3==0){
            s6_type3=s5_type3;
        }
        if(s6_type4==0){
            s6_type4=s5_type4;
        }
        if(s6_type5==0){
            s6_type5=s5_type5;
        }
        if(s6_type6==0){
            s6_type6=s5_type6;
        }
        if(s6_type7==0){
            s6_type7=s5_type7;
        }
        js+="{time:'"+time+"-06',type1:" + s6_type1 +",type2:"+s6_type2+",type3:"+s6_type3+
                ",type4:"+s6_type4+",type5:"+s6_type5+",type6:"+s6_type6+",type7:"+s6_type7+"}," ;
        //System.out.println(js);
        //System.out.println(s7);
        if(s7==null){
            s7_type1=s6_type1;
            s7_type2=s6_type2;
            s7_type3=s6_type3;
            s7_type4=s6_type4;
            s7_type5=s6_type5;
            s7_type6=s6_type6;
            s7_type7=s6_type7;
        } else {
            String[] strings7=s7.split(",");
            for (String sts :strings7){
                if(sts.contains("type1")){
                    s7_type1= Integer.parseInt(sts.split(":")[1])+s6_type1 ;
                    //System.out.println("s7_type1=="+s7_type1);
                }
                if(sts.contains("type2")){
                    s7_type2= Integer.parseInt(sts.split(":")[1])+s6_type2 ;
                    //System.out.println("s7_type2=="+s7_type2);
                }
                if(sts.contains("type3")){
                    s7_type3= Integer.parseInt(sts.split(":")[1])+s6_type3 ;
                    //System.out.println("s7_type3=="+s7_type3);
                }
                if(sts.contains("type4")){
                    s7_type4= Integer.parseInt(sts.split(":")[1]) +s6_type4;
                    //System.out.println("s7_type4=="+s7_type4);
                }
                if(sts.contains("type5")){
                    s7_type5= Integer.parseInt(sts.split(":")[1])+s6_type5 ;
                    //System.out.println("s7_type5=="+s7_type5);
                }
                if(sts.contains("type6")){
                    s7_type6= Integer.parseInt(sts.split(":")[1])+s6_type6 ;
                    //System.out.println("s7_type6=="+s7_type6);
                }
                if(sts.contains("type7")){
                    s7_type7= Integer.parseInt(sts.split(":")[1])+s6_type7 ;
                    //System.out.println("s7_type7=="+s7_type7);
                }
            }
        }
        if(s7_type1==0){
            s7_type1=s6_type1;
        }
        if(s7_type2==0){
            s7_type2=s6_type2;
        }
        if(s7_type3==0){
            s7_type3=s6_type3;
        }
        if(s7_type4==0){
            s7_type4=s6_type4;
        }
        if(s7_type5==0){
            s7_type5=s6_type5;
        }
        if(s7_type6==0){
            s7_type6=s6_type6;
        }
        if(s7_type7==0){
            s7_type7=s6_type7;
        }
        js+="{time:'"+time+"-07',type1:" + s7_type1 +",type2:"+s7_type2+",type3:"+s7_type3+
                ",type4:"+s7_type4+",type5:"+s7_type5+",type6:"+s7_type6+",type7:"+s7_type7+"}," ;
        //System.out.println(js);
        //System.out.println(s8);
        if(s8==null){
            s8_type1=s7_type1;
            s8_type2=s7_type2;
            s8_type3=s7_type3;
            s8_type4=s7_type4;
            s8_type5=s7_type5;
            s8_type6=s7_type6;
            s8_type7=s7_type7;
        } else {
            String[] strings8=s8.split(",");
            for (String sts :strings8){
                if(sts.contains("type1")){
                    s8_type1= Integer.parseInt(sts.split(":")[1])+s7_type1 ;
                    //System.out.println("s8_type1=="+s8_type1);
                }
                if(sts.contains("type2")){
                    s8_type2= Integer.parseInt(sts.split(":")[1])+s7_type2 ;
                    //System.out.println("s8_type2=="+s8_type2);
                }
                if(sts.contains("type3")){
                    s8_type3= Integer.parseInt(sts.split(":")[1])+s7_type3 ;
                    //System.out.println("s8_type3=="+s8_type3);
                }
                if(sts.contains("type4")){
                    s8_type4= Integer.parseInt(sts.split(":")[1])+s7_type4 ;
                    //System.out.println("s8_type4=="+s8_type4);
                }
                if(sts.contains("type5")){
                    s8_type5= Integer.parseInt(sts.split(":")[1])+s7_type5 ;
                    //System.out.println("s8_type5=="+s8_type5);
                }
                if(sts.contains("type6")){
                    s8_type6= Integer.parseInt(sts.split(":")[1])+s7_type6 ;
                    //System.out.println("s8_type6=="+s8_type6);
                }
                if(sts.contains("type7")){
                    s8_type7= Integer.parseInt(sts.split(":")[1]) +s7_type7;
                    //System.out.println("s8_type7=="+s8_type7);
                }
            }
        }
        if( s8_type1==0){
            s8_type1=s7_type1;
        }
        if( s8_type2==0){
            s8_type2=s7_type2;
        }
        if( s8_type3==0){
            s8_type3=s7_type3;
        }
        if( s8_type4==0){
            s8_type4=s7_type4;
        }
        if( s8_type5==0){
            s8_type5=s7_type5;
        }
        if( s8_type6==0){
            s8_type6=s7_type6;
        }
        if( s8_type7==0){
            s8_type7=s7_type7;
        }
        js+="{time:'"+time+"-08',type1:" + s8_type1 +",type2:"+s8_type2+",type3:"+s8_type3+
                ",type4:"+s8_type4+",type5:"+s8_type5+",type6:"+s8_type6+",type7:"+s8_type7+"}," ;
        //System.out.println(s9);
        if(s9==null){
            s9_type1=s8_type1;
            s9_type2=s8_type2;
            s9_type3=s8_type3;
            s9_type4=s8_type4;
            s9_type5=s8_type5;
            s9_type6=s8_type6;
            s9_type7=s8_type7;
        } else {
            String[] strings9=s9.split(",");
            for (String sts :strings9){
                if(sts.contains("type1")){
                    s9_type1= Integer.parseInt(sts.split(":")[1])+s8_type1 ;
                    //System.out.println("s9_type1=="+s9_type1);
                }
                if(sts.contains("type2")){
                    s9_type2= Integer.parseInt(sts.split(":")[1])+s8_type2 ;
                    //System.out.println("s9_type2=="+s9_type2);
                }
                if(sts.contains("type3")){
                    s9_type3= Integer.parseInt(sts.split(":")[1])+s8_type3 ;
                    //System.out.println("s9_type3=="+s9_type3);
                }
                if(sts.contains("type4")){
                    s9_type4= Integer.parseInt(sts.split(":")[1])+s8_type4 ;
                    //System.out.println("s9_type4=="+s9_type4);
                }
                if(sts.contains("type5")){
                    s9_type5= Integer.parseInt(sts.split(":")[1]) +s8_type5;
                    //System.out.println("s9_type5=="+s9_type5);
                }
                if(sts.contains("type6")){
                    s9_type6= Integer.parseInt(sts.split(":")[1]) +s8_type6;
                    //System.out.println("s9_type6=="+s9_type6);
                }
                if(sts.contains("type7")){
                    s9_type7= Integer.parseInt(sts.split(":")[1]) +s8_type7;
                    //System.out.println("s9_type7=="+s9_type7);
                }
            }
        }
        if( s9_type1==0){
            s9_type1=s8_type1;
        }
        if( s9_type2==0){
            s9_type2=s8_type2;
        }
        if( s9_type3==0){
            s9_type3=s8_type3;
        }
        if( s9_type4==0){
            s9_type4=s8_type4;
        }
        if( s9_type5==0){
            s9_type5=s8_type5;
        }
        if( s9_type6==0){
            s9_type6=s8_type6;
        }
        if( s9_type7==0){
            s9_type7=s8_type7;
        }
        js+="{time:'"+time+"-09',type1:" + s9_type1 +",type2:"+s9_type2+",type3:"+s9_type3+
                ",type4:"+s9_type4+",type5:"+s9_type5+",type6:"+s9_type6+",type7:"+s9_type7+"}," ;
        //System.out.println(js);
        if(s10==null){
            s10_type1=s9_type1;
            s10_type2=s9_type2;
            s10_type3=s9_type3;
            s10_type4=s9_type4;
            s10_type5=s9_type5;
            s10_type6=s9_type6;
            s10_type7=s9_type7;
        } else {
            String[] strings10=s10.split(",");
            for (String sts :strings10){
                if(sts.contains("type1")){
                    s10_type1= Integer.parseInt(sts.split(":")[1])+s9_type1;
                    //System.out.println("s10_type1=="+s10_type1);
                }
                if(sts.contains("type2")){
                    s10_type2= Integer.parseInt(sts.split(":")[1])+s9_type2 ;
                    //System.out.println("s10_type2=="+s10_type2);
                }
                if(sts.contains("type3")){
                    s10_type3= Integer.parseInt(sts.split(":")[1])+s9_type3 ;
                    //System.out.println("s10_type3=="+s10_type3);
                }
                if(sts.contains("type4")){
                    s10_type4= Integer.parseInt(sts.split(":")[1])+s9_type4 ;
                    //System.out.println("s10_type4=="+s10_type4);
                }
                if(sts.contains("type5")){
                    s10_type5= Integer.parseInt(sts.split(":")[1])+s9_type5 ;
                    //System.out.println("s10_type5=="+s10_type5);
                }
                if(sts.contains("type6")){
                    s10_type6= Integer.parseInt(sts.split(":")[1]) +s9_type6;
                    //System.out.println("s10_type6=="+s10_type6);
                }
                if(sts.contains("type7")){
                    s10_type7= Integer.parseInt(sts.split(":")[1]) +s9_type7;
                    //System.out.println("s1_type7=="+s10_type7);
                }
            }
        }
        if( s10_type1==0){
            s10_type1=s9_type1;
        }
        if( s10_type2==0){
            s10_type2=s9_type2;
        }
        if( s10_type3==0){
            s10_type3=s9_type3;
        }
        if( s10_type4==0){
            s10_type4=s9_type4;
        }
        if( s10_type5==0){
            s10_type5=s9_type5;
        }
        if( s10_type6==0){
            s10_type6=s9_type6;
        }
        if( s10_type7==0){
            s10_type7=s9_type7;
        }
        js+="{time:'"+time+"-10',type1:" + s10_type1 +",type2:"+s10_type2+",type3:"+s10_type3+
                ",type4:"+s10_type4+",type5:"+s10_type5+",type6:"+s10_type6+",type7:"+s10_type7+"}," ;
        //System.out.println(js);
        if(s11==null){
            s11_type1=s10_type1;
            s11_type2=s10_type2;
            s11_type3=s10_type3;
            s11_type4=s10_type4;
            s11_type5=s10_type5;
            s11_type6=s10_type6;
            s11_type7=s10_type7;
        } else {
            String[] strings11=s11.split(",");
            for (String sts :strings11){
                if(sts.contains("type1")){
                    s11_type1= Integer.parseInt(sts.split(":")[1]) +s10_type1;
                    //System.out.println("s11_type1=="+s11_type1);
                }
                if(sts.contains("type2")){
                    s11_type2= Integer.parseInt(sts.split(":")[1]) +s10_type2 ;
                    //System.out.println("s1_type2=="+s11_type2);
                }
                if(sts.contains("type3")){
                    s11_type3= Integer.parseInt(sts.split(":")[1]) +s10_type3 ;
                    //System.out.println("s1_type3=="+s11_type3);
                }
                if(sts.contains("type4")){
                    s11_type4= Integer.parseInt(sts.split(":")[1]) +s10_type4 ;
                    //System.out.println("s1_type4=="+s11_type4);
                }
                if(sts.contains("type5")){
                    s11_type5= Integer.parseInt(sts.split(":")[1]) +s10_type5 ;
                    //System.out.println("s1_type5=="+s11_type5);
                }
                if(sts.contains("type6")){
                    s11_type6= Integer.parseInt(sts.split(":")[1])  +s10_type6;
                    //System.out.println("s11_type6=="+s11_type6);
                }
                if(sts.contains("type7")){
                    s11_type7= Integer.parseInt(sts.split(":")[1])  +s10_type7;
                    //System.out.println("s11_type7=="+s11_type7);
                }
            }
        }
        if( s11_type1==0){
            s11_type1=s10_type1;
        }
        if( s11_type2==0){
            s11_type2=s10_type2;
        }
        if( s11_type3==0){
            s11_type3=s10_type3;
        }
        if( s11_type4==0){
            s11_type4=s10_type4;
        }
        if( s11_type5==0){
            s11_type5=s10_type5;
        }
        if( s11_type6==0){
            s11_type6=s10_type6;
        }
        if( s11_type7==0){
            s11_type7=s10_type7;
        }
        js+="{time:'"+time+"-11',type1:" + s11_type1 +",type2:"+s11_type2+",type3:"+s11_type3+
                ",type4:"+s11_type4+",type5:"+s11_type5+",type6:"+s11_type6+",type7:"+s11_type7+"}," ;
        //System.out.println(js);
        if(s12==null){
            s12_type1=s11_type1;
            s12_type2=s11_type2;
            s12_type3=s11_type3;
            s12_type4=s11_type4;
            s12_type5=s11_type5;
            s12_type6=s11_type6;
            s12_type7=s11_type7;
        } else {
            String[] strings12=s12.split(",");
            for (String sts :strings12){
                if(sts.contains("type1")){
                    s12_type1= Integer.parseInt(sts.split(":")[1])+s11_type1 ;
                    //System.out.println("s12_type1=="+s12_type1);
                }
                if(sts.contains("type2")){
                    s12_type2= Integer.parseInt(sts.split(":")[1])+s11_type2 ;
                    //System.out.println("s12_type2=="+s12_type2);
                }
                if(sts.contains("type3")){
                    s12_type3= Integer.parseInt(sts.split(":")[1])+s11_type3 ;
                    //System.out.println("s12_type3=="+s12_type3);
                }
                if(sts.contains("type4")){
                    s12_type4= Integer.parseInt(sts.split(":")[1])+s11_type4 ;
                }
                if(sts.contains("type5")){
                    s12_type5= Integer.parseInt(sts.split(":")[1]) +s11_type5;
                }
                if(sts.contains("type6")){
                    s12_type6= Integer.parseInt(sts.split(":")[1])+s11_type6 ;
                }
                if(sts.contains("type7")){
                    s12_type7= Integer.parseInt(sts.split(":")[1])+s11_type7 ;
                }
            }
        }
        if( s12_type1==0){
            s12_type1=s11_type1;
        }
        if( s12_type2==0){
            s12_type2=s11_type2;
        }
        if( s12_type3==0){
            s12_type3=s11_type3;
        }
        if( s12_type4==0){
            s12_type4=s11_type4;
        }
        if( s12_type5==0){
            s12_type5=s11_type5;
        }
        if( s12_type6==0){
            s12_type6=s11_type6;
        }
        if( s12_type7==0){
            s12_type7=s11_type7;
        }
        js+="{time:'"+time+"-12',type1:" + s12_type1 +",type2:"+s12_type2+",type3:"+s12_type3+
                ",type4:"+s12_type4+",type5:"+s12_type5+",type6:"+s12_type6+",type7:"+s12_type7+"}," ;

        js+="]}";

        if(js.endsWith("},]}")){
            js=js.replace("},]}","}]}");
        }
//        System.out.println(js);
        return js;
    }
}
