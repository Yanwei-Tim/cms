package com.hzjava.monitorcenter.utils;

import cn.collin.commons.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-12-26
 * Time: 上午11:11
 * To change this template use File | Settings | File Templates.
 */
public class DateTimeUtil {
    private static String TIME_PATTERN="HH:mm:ss";//定义标准时间格式
	private static String DATE_PATTERN_1="yyyy/MM/dd";//定义标准日期格式1
	private static String DATE_PATTERN_2="yyyy-MM-dd";//定义标准日期格式2
	private static String DATE_PATTERN_3="yyyy/MM/dd HH:mm:ss";//定义标准日期格式3，带有时间
	private static String DATE_PATTERN_4="yyyy/MM/dd HH:mm:ss E";//定义标准日期格式4，带有时间和星期
	private static String DATE_PATTERN_5="yyyy年MM月dd日 HH:mm:ss E";//定义标准日期格式5，带有时间和星期
	/**
	 * 定义时间类型常量
	 */
	private final static int SECOND=1;
	private final static int MINUTE=2;
	private final static int HOUR=3;
	private final static int DAY=4;
   /* private Date now;
	public Date getNow() {
		return now;
	}
	public void setNow(Date now) {
		this.now = now;
	}*/
	/**
	 * 构造方法，初始化now时间
	 */
	/*public DateTimeUtil(){
		now=new Date();
	}	*/
    /**
	 * 把一个日期，按照某种格式 格式化输出
	 * @param date 日期对象
	 * @param pattern 格式模型
	 * @return 返回字符串类型
	 */
	public String formatDate(Date date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	    return sdf.format(date);
	}
	/**
	 * 将字符串类型的时间转换为Date类型
	 * @param str 时间字符串
	 * @param pattern 格式
	 * @return 返回Date类型
	 */
	public Date formatString(String str,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date time=null;
		//需要捕获ParseException异常，如不要捕获，可以直接抛出异常，或者抛出到上层
		try {
			time = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}
    	/**
	 * 将一个表示时间段的数转换为毫秒数
	 * @param num 时间差数值,支持小数
	 * @param type 时间类型：1->秒,2->分钟,3->小时,4->天
	 * @return long类型时间差毫秒数，当为-1时表示参数有错
	 */
	public long formatToTimeMillis(double num, int type) {
		if (num <= 0)
			return 0;
		switch (type) {
		case SECOND:
			return (long) (num * 1000);
		case MINUTE:
			return (long) (num * 60 * 1000);
		case HOUR:
			return (long) (num * 60 * 60 * 1000);
		case DAY:
			return (long) (num * 24 * 60 * 60  * 1000);
		default:
			return -1;
		}
	}
    /**
	 * 得到一个日期函数的格式化时间
	 * @param date 日期对象
	 * @return
	 */
	public String getTimeByDate(Date date){
		 return formatDate(date, TIME_PATTERN);
	}
	/**
	 * 获取当前的时间，new Date()获取当前的日期
	 * @return
	 */
	public String getNowTime(){
		return formatDate(new Date(), TIME_PATTERN);
	}
    	/**
	 * 获取某一指定时间的前一段时间
	 * @param num 时间差数值
	 * @param type 时间差类型：1->秒,2->分钟,3->小时,4->天
	 * @param date 参考时间
	 * @return 返回格式化时间字符串
	 */
	public String getPreTimeStr(double num,int type, Date date){
		long nowLong=date.getTime();//将参考日期转换为毫秒时间
	    Date time = new Date(nowLong-formatToTimeMillis(num, type));//减去时间差毫秒数
	    return getTimeByDate(time);
	}
	/**
	 * 获取某一指定时间的前一段时间
	 * @param num 时间差数值
	 * @param type 时间差类型：1->秒,2->分钟,3->小时,4->天
	 * @param date 参考时间
	 * @return 返回Date对象
	 */
	public Date getPreTime(double num,int type, Date date){
		long nowLong=date.getTime();//将参考日期转换为毫秒时间
	    Date time = new Date(nowLong-formatToTimeMillis(num, type));//减去时间差毫秒数
	    return time;
	}
	/**
	 * 获取某一指定时间的后一段时间
	 * @param num 时间差数值
	 * @param type 时间差类型：1->秒,2->分钟,3->小时,4->天
	 * @param date 参考时间
	 * @return 返回格式化时间字符串
	 */
	public String getNextTimeStr(double num,int type, Date date){
		long nowLong=date.getTime();//将参考日期转换为毫秒时间
	    Date time = new Date(nowLong+formatToTimeMillis(num, type));//加上时间差毫秒数
	    return getTimeByDate(time);
	}
	/**
	 * 获取某一指定时间的后一段时间
	 * @param num 时间差数值
	 * @param type 时间差类型：1->秒,2->分钟,3->小时,4->天
	 * @param date 参考时间
	 * @return 返回Date对象
	 */
	public Date getNextTime(double num,int type, Date date){
		long nowLong=date.getTime();//将参考日期转换为毫秒时间
	    Date time = new Date(nowLong+formatToTimeMillis(num, type));//加上时间差毫秒数
	    return time;
	}
    	/**
	 * 得到前几个月的现在
	 * 利用GregorianCalendar类的set方法来实现
	 * @param num
	 * @param date
	 * @return
	 */
	public Date getPreMonthTime(int num, Date date){
		GregorianCalendar gregorianCal = new GregorianCalendar();
		gregorianCal.set(Calendar.MONTH, gregorianCal.get(Calendar.MONTH) - num);
		return gregorianCal.getTime();
	}
	/**
	 * 得到后几个月的现在时间
	 * 利用GregorianCalendar类的set方法来实现
	 * @param num
	 * @param date
	 * @return
	 */
	public Date getNextMonthTime(int num, Date date){
		GregorianCalendar gregorianCal = new GregorianCalendar();
		gregorianCal.set(Calendar.MONTH, gregorianCal.get(Calendar.MONTH) + num);
		return gregorianCal.getTime();
	}


    //返回当前年月日
    public String getNowDate()
    {
        Date date = new Date();
        String nowDate = new SimpleDateFormat("yyyy年MM月dd日").format(date);
        return nowDate;
    }

    //返回当前年份
    public int getYear()
    {
        Date date = new Date();
        String year = new SimpleDateFormat("yyyy").format(date);
        return Integer.parseInt(year);
    }

    //返回当前月份
    public int getMonth()
    {
        Date date = new Date();
        String month = new SimpleDateFormat("MM").format(date);
        return Integer.parseInt(month);
    }

    //判断闰年
    public boolean isLeap(int year)
    {
        if (((year % 100 == 0) && year % 400 == 0) || ((year % 100 != 0) && year % 4 == 0))
            return true;
        else
            return false;
    }

    //返回当月天数
    public int getDays(int year, int month)
    {
        int days;
        int FebDay = 28;
        if (isLeap(year))
            FebDay = 29;
        switch (month)
        {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                days = FebDay;
                break;
            default:
                days = 0;
                break;
        }
        return days;
    }

    //返回当月星期天数
    public int getSundays(int year, int month)
    {
        int sundays = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Calendar setDate = Calendar.getInstance();
        //从第一天开始
        int day;
        for (day = 1; day <= getDays(year, month); day++)
        {
            setDate.set(Calendar.DATE, day);
            String str = sdf.format(setDate.getTime());
            if (str.equals("星期日"))
            {
                sundays++;
            }
        }
        return sundays;
    }
    public static void main(String[] arg) throws ParseException {
        Date datetime = StringUtils.isBlank("2012-07-29 18:19:45") ? null : DateUtils
            				.parse("2012-07-29 18:19:45", "yyyy-MM-dd hh:mm:ss");
        long nowLong=datetime.getTime();
        System.out.println(nowLong);
	    Date time = new Date(nowLong-300000);//加上时间差毫秒数
	    Date time2 = new Date(nowLong+300000);//加上时间差毫秒数
	    System.out.println(time);
	    System.out.println(time2);
    }
}
