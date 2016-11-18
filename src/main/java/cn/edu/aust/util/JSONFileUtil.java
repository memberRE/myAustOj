package cn.edu.aust.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


/**
 * 此类用于从数据库更新JSON文件
 * @author lvbiao
 *
 */
public class JSONFileUtil {
	
	public static void  refreshTags(String path,String text){
		//将字符串text写入到path文件中
		try {
			File file = new File(path);
			FileUtils.writeStringToFile(file, text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//测试
	public static void main(String[] args) {
		String path = "F:\\Test\\1.txt";
		String text = "[{\"name\":biao,\"age\":12}]";
		refreshTags(path, text);
		System.out.println("写入完成");
	}
}
