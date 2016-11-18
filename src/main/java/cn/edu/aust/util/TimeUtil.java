package cn.edu.aust.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	public static String getDate(Date date){
		// 获取当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateText = sdf.format(date);
		return dateText;
	}
}
