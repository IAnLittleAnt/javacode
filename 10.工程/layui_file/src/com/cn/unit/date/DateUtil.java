package com.cn.unit.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.cn.comm.UseTool;

/**
 * 时间工具类
 * @author 10011037@qq.com
 * 2015-07-25
 */
public class DateUtil {
	
	/**
     * 获取系统时间
     */
    public static Date getSystemDate() {
		return new Date();
	}
	
	/**
	 * 把时间类型转为字符串类型
	 * @param date 时间类型
	 * @param formatType 时间格式
	 * @return 时间字符串
	 */
	public static String getStrByDate(Date date, String formatType) {
		if(date == null){
			date = new Date();
		}
		if(UseTool.isEmpty(formatType)){
			formatType = DateConst.YYYY_MM_DD_HH_MM_SS;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		String dateString = formatter.format(date);
		return dateString;
	}
    
    /**
	 * 把字符串类型转换为日期类型
	 * @param dateString 字符串类型
	 * @param formatType 时间格式
	 * @return 日期类型
	 */
	public static Date getDateByStr(String dateString, String formatType) {
		if(UseTool.isEmpty(dateString)){
			dateString = getStrByDate(null, null);
		}
        dateString = dateString.replaceAll("/", "-");
		if(UseTool.isEmpty(formatType)){
			formatType = DateConst.YYYY_MM_DD;
			if(dateString.indexOf(":")>=0 && dateString.indexOf("-")>=0){
	    		formatType = DateConst.YYYY_MM_DD_HH_MM_SS;
	    	}else if(dateString.indexOf(":")>=0){
	    		formatType = DateConst.H_M_S;
	    	}
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			date = new Date();
		}
		return date;
	}
	
	/**
	 * 把字符串类型转换为字符串类型
	 * @param dateString 字符串类型
	 * @param formatType 时间格式
	 * @return 字符串类型
	 */
	public static String getStrByStr(String dateString, String formatType) {
		dateString = dateString.length() < 12 ? dateString + DateConst.TIME_FLASH_START : dateString;
		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		String date = formatter.format(getDateByStr(dateString, null));
		return date;
	}
	
	/**
	 * 把Long类型转换为时间字符串类型
	 * @param currentTime 时间戳
	 * @param formatType 时间格式
	 * @return 字符串类型
	 */
	public static String getStrByLong(Long currentTime, String formatType) {
		Date date = new Date(currentTime);
 		return getStrByDate(date, formatType);
 	}
	
	/**
	 * 获取季度第几个月份(不是几月)
	 * @param month 月份
	 * @param isQuarterStart 是否获取该月份的季度 开始的第几个月份
	 * @return 季度一年四季， 第一季度：2月-4月， 第二季度：5月-7月， 第三季度：8月-10月， 第四季度：11月-1月
	 */
	public static int getQuarterInMonth(int month, boolean isQuarterStart) {
        int months[] = { 1, 4, 7, 10 };
        if (!isQuarterStart) {
            months = new int[] { 3, 6, 9, 12 };
        }
        if (month >= 2 && month <= 4)
            return months[0];
        else if (month >= 5 && month <= 7)
            return months[1];
        else if (month >= 8 && month <= 10)
            return months[2];
        else
            return months[3];
    }
	
	/**
	 * 获取中文格式星期几
	 * @param dateString 时间字符串
	 * @return 中文格式星期几
	 */
    public static String getWeekName(String dateString){
		// 1是星期日、2是星期一、3是星期二、4是星期三、5是星期四、6是星期五、7是星期六 
    	int goal = getGoalFormat(dateString, "week");
    	String[] weekNames = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    	return weekNames[goal-1];
    }
	
	/**
     * 获取当前时间的不同格式
     * @param mark 类型(YEAR年份,MONTH月份,CYCLE周期,WEEK星期,DATE日期,HOUR小时,MINUTE分钟,SECOND秒钟)
     * @return 当前时间的不同格式
     */
    public static int getGoalFormat(String dateString, String mark){
    	Calendar cal = Calendar.getInstance();
    	if(dateString != null){
        	cal.setTime(getDateByStr(dateString, DateConst.YYYY_MM_DD));
    	}
    	int goal = 0;
    	if(DateConst.YEAR.equals(mark)){
    		goal = cal.get(Calendar.YEAR);
    	}else if(DateConst.MONTH.equals(mark)){
    		goal = cal.get(Calendar.MONTH)+1;
    	}else if(DateConst.CYCLE.equals(mark)){
    		goal = cal.get(Calendar.WEEK_OF_YEAR);
    	}else if(DateConst.WEEK.equals(mark)){
    		// 1是星期日、2是星期一、3是星期二、4是星期三、5是星期四、6是星期五、7是星期六 
    		goal = cal.get(Calendar.DAY_OF_WEEK);
    	}else if(DateConst.DATE.equals(mark)){
    		goal = cal.get(Calendar.DATE);
    	}else if(DateConst.HOUR.equals(mark)){
    		goal = cal.get(Calendar.HOUR);
    	}else if(DateConst.MINUTE.equals(mark)){
    		goal = cal.get(Calendar.MINUTE);
    	}else if(DateConst.SECOND.equals(mark)){
    		goal = cal.get(Calendar.SECOND);
    	}
		return goal;
    }
	
    /**
     * 获取固定距离的时间
     * @param distance 距离大小
     * @param formatType 时间格式(字符串)
     * @param mark 类型(YEAR年份,MONTH月份,WEEK星期,DATE日期,HOUR小时,MINUTE分钟,SECOND秒钟)
     * @return 获取不同月份
     */
    public static String getFixedDiverseTime(String dateString, Integer distance, String formatType, String mark){
    	Calendar c = Calendar.getInstance();
    	if(!UseTool.isEmpty(dateString)){
    		c.setTime(DateUtil.getDateByStr(dateString, formatType));
    	}
    	if(DateConst.YEAR.equals(mark)){
        	c.add(Calendar.YEAR, distance);
    	}else if(DateConst.MONTH.equals(mark)){
    		c.add(Calendar.MONTH, distance);
    	}else if(DateConst.WEEK.equals(mark)){
    		c.add(Calendar.DAY_OF_WEEK, distance);
    	}else if(DateConst.DATE.equals(mark)){
    		c.add(Calendar.DATE, distance);
    	}else if(DateConst.HOUR.equals(mark)){
    		c.add(Calendar.HOUR, distance);
    	}else if(DateConst.MINUTE.equals(mark)){
    		c.add(Calendar.MINUTE, distance);
    	}else if(DateConst.SECOND.equals(mark)){
    		c.add(Calendar.SECOND, distance);
    	}
    	String time = getStrByDate(c.getTime(), formatType);
    	return time;
    }
	
    /**
     * 获取特殊距离的时间
     * @param distance 类型(1第一天,2最后一天)
     * @param mark 类型(WEEK本周,PREVMONTH上月,MONTH本月,NEXTMONTH下月,SEASON本季度,YEAR本年)
     * @return 日期字符串
     */
    public static String getSpecialDiverseTime(int distance, String mark){
    	Calendar calendar = new GregorianCalendar();
    	if(DateConst.WEEK.equals(mark)){
    		switch (distance) {
    		case 1:
    			calendar.set(Calendar.DAY_OF_WEEK, 2);
    			break;
    		case 2:
    			calendar.set(Calendar.DAY_OF_WEEK, 7);
    			break;
    		}
    	} else if(DateConst.PREVMONTH.equals(mark)){
        	switch (distance) {
    		case 1:
    			calendar.add(Calendar.MONTH, -1); 
    			calendar.set(Calendar.DAY_OF_MONTH, 1); 
    			break;
    		case 2:
    			calendar.set(Calendar.DAY_OF_MONTH, 0); 
    			break;
    		}
    	} else if(DateConst.MONTH.equals(mark)){
        	switch (distance) {
    		case 1:
    			calendar.set(Calendar.DAY_OF_MONTH, 1);
    			break;
    		case 2:
    	    	calendar.add(Calendar.MONTH, 1);
    	        calendar.set(Calendar.DAY_OF_MONTH, 0);
    			break;
    		}
    	} else if(DateConst.NEXTMONTH.equals(mark)){
        	switch (distance) {
    		case 1:
    			calendar.add(Calendar.MONTH, 1); 
    			calendar.set(Calendar.DAY_OF_MONTH, 1); 
    			break;
    		case 2:
    			calendar.set(Calendar.YEAR, Integer.parseInt(DateUtil.getStrByDate(null, DateConst.YYYY)));
    		    calendar.set(Calendar.MONTH, Integer.parseInt(DateUtil.getStrByDate(null, DateConst.MM)));
    		    calendar.set(Calendar.DAY_OF_MONTH, 1); 
    		    calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE)); 
    			break;
    		}
    	} else if(DateConst.SEASON.equals(mark)){
        	switch (distance) {
    		case 1:
    	    	calendar.setTime(new Date());  
    	        int month1 = getQuarterInMonth(calendar.get(Calendar.MONTH), true);  
    	        calendar.set(Calendar.MONTH, month1);  
    	        calendar.set(Calendar.DAY_OF_MONTH, 1); 
    			break;
    		case 2:
    	    	calendar.setTime(new Date());  
    	        int month2 = getQuarterInMonth(calendar.get(Calendar.MONTH), false);  
    	        calendar.set(Calendar.MONTH, month2 + 1);  
    	        calendar.set(Calendar.DAY_OF_MONTH, 0);  
    			break;
    		}
    	} else if(DateConst.YEAR.equals(mark)){
        	switch (distance) {
    		case 1:
    	        calendar.setTime(new Date());  
    	        int year1 = calendar.get(Calendar.YEAR);
    	        calendar.clear();
    	        calendar.set(Calendar.YEAR, year1);  
    			break;
    		case 2:
    	        calendar.setTime(new Date());  
    	        int year2 = calendar.get(Calendar.YEAR);
    	        calendar.clear();
    	        calendar.set(Calendar.YEAR, year2);  
    	        calendar.roll(Calendar.DAY_OF_YEAR, -1);  
    			break;
    		}
    	}
    	return DateUtil.getStrByDate(calendar.getTime(), DateConst.YYYY_MM_DD);
    }
	
    /**
     * 获取两时间差
     * @param dateString1 时间1
     * @param dateString2 时间2
     * @param mark 单位类型(DATE天,HOUR小时,MINUTE分钟,SECOND秒钟)
     * @return 两时间差
     */
    public static int getTimeRange(String dateString1, String dateString2, String mark){
    	if(UseTool.isEmpty(dateString1) || UseTool.isEmpty(dateString2)){
            return 0;
    	}
    	Date timeString1 = null;
    	Date timeString2 = null;
    	if(dateString1.indexOf(":") >= 0 && (dateString1.indexOf("-") >= 0 || dateString1.indexOf("/") >= 0)){
    		timeString1 = getDateByStr(dateString1, DateConst.YYYY_MM_DD_HH_MM_SS);
    		timeString2 = getDateByStr(dateString2, DateConst.YYYY_MM_DD_HH_MM_SS);
    	}else if(dateString1.indexOf(":") >= 0){
    		timeString1 = getDateByStr(dateString1, DateConst.H_M_S);
    		timeString2 = getDateByStr(dateString2, DateConst.H_M_S);
    	}else{
    		timeString1 = getDateByStr(dateString1, DateConst.YYYY_MM_DD);
    		timeString2 = getDateByStr(dateString2, DateConst.YYYY_MM_DD);
    	}
		long time = timeString2.getTime() - timeString1.getTime();
		 
    	int total = 0;
    	if(DateConst.SECOND.equals(mark)){
    		total = new Long(time / 1000).intValue();
    	}else if(DateConst.MINUTE.equals(mark)){
    		total = new Long(time / (1000 * 60)).intValue();
    	}else if(DateConst.HOUR.equals(mark)){
    		total = new Long(time / (1000 * 60 * 60)).intValue();
    	}else if(DateConst.DATE.equals(mark)){
    		total = new Long(time / (1000 * 60 * 60 * 24)).intValue();
    	}
        return total;
    }
    
    /**
     * 获取第几周
     */
    public static int getWeekByYear(String dateString) {
    	Date date = null;
    	if(UseTool.isEmpty(dateString)){
    		date = new Date();
    	}else{
    		date = getDateByStr(dateString, DateConst.YYYY_MM_DD);
    	}
    	Calendar calendar = Calendar.getInstance();
    	calendar.setFirstDayOfWeek(Calendar.MONDAY);
    	calendar.setTime(date);
    	return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    
    /**
     * 获取最近一个星期一
     */
    public static String getLatelyMon(String dateString, String formatType, Integer mark){
    	Calendar cal = Calendar.getInstance();
    	if (UseTool.isEmpty(dateString)) {
    		cal.setTime(new Date(System.currentTimeMillis()));
    	} else {
    		cal.setTime(new Date(getDateByStr(dateString, DateConst.YYYY_MM_DD).getTime()));
    	}
    	int c = cal.get(Calendar.DAY_OF_WEEK);
    	int distance = mark > 0 ? (c==1 ? (c-7) : (2-c)) : (c==1 ? -13 : (-c-5));
    	String dateTime = getFixedDiverseTime(null, distance, formatType, "date");
    	return dateTime;
    }
    
	
    /**
     * 测试方法
     */
	public static void main(String[] args){
		System.out.println(getQuarterInMonth(2, true));
		System.out.println(getQuarterInMonth(2, false));
	}
    
}
