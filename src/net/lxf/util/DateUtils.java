package net.lxf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {
	
	public static final String FORMAT1 = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒";
	
	public static final String FORMAT2 = "yyyy-MM-dd HH:mm:ss.SSS";
	
	public static final String FORMAT3 = "yyyy-MM-dd";
	
	public DateUtils(){
		
	}
	/**
	 * 获取系统当前时间
	 * @return String
	 * @description 
	 * @author linxiaofan
	 * @time 2014-8-29 下午3:34:34
	 * @return
	 */
	public static String getSysDate(){
		String sysdate = "";
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT2);
		sysdate = sdf.format(new Date());
		return sysdate;
	}
	
	/**
	 * 将字符串转换为指定的时间格式
	 * @return String
	 * @description 
	 * @author linxiaofan
	 * @time 2014-8-29 下午3:39:22
	 * @param datestr
	 * @return
	 */
	public static String getDate(String datestr){
		String str= "20140303";
		String str2 = "";
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat(FORMAT3);
		try {
			str2 = sdf2.format((sdf1.parse(str)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return str2;
	}
	
	public static void main(String args []){
		System.out.println(DateUtils.getDate("1"));
	}
}










