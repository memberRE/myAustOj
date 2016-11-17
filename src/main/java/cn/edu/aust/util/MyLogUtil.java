package cn.edu.aust.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyLogUtil {
	private static final Logger log ;
	
	static{
		log = LoggerFactory.getLogger(MyLogUtil.class);
	}
	
	public static Logger getLogger(){
		return log;
	}
}
