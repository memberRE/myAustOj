package cn.edu.aust.util;

import java.io.File;
import java.io.IOException;

public class FilePathUtil {
	/**
	 * 获取去当前项目路径
	 * @return 路径
	 * @throws IOException 
	 */
	public static String getCourseFilePath(){
		return "F:/mavenProject/myAustOj";
	}
	
	public static void main(String[] args){
		System.out.println(getCourseFilePath());
	}
	
}
