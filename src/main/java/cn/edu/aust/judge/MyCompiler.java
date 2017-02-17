package cn.edu.aust.judge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 编译java文件,获取class文件
 * 
 * @author lvbiao
 *
 */
public class MyCompiler {
	private String path;
	private String name;
	
	public MyCompiler(String path,  String name){
		this.path = path;
		this.name = name;
	}
	
	/**
	 * 编译java文件    如果传进来了ClassName有可能出现相同的情况,这一方法必须要保证线程安全
	 * 
	 * @param path
	 * @param className
	 * @throws IOException
	 */
	//这一段要保证线程安全
	public int command() {
		// 设置批处理文件内容
		try {
			String orderPath = createBatFile();
			Runtime run = Runtime.getRuntime();
			Process process = run.exec("cmd.exe /c  " + orderPath);
			int result = process.waitFor();
			//执行完之后删除批处理文件
			File file = new File(orderPath);
			if(file.exists()){
				file.delete();
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 
	 * @return 批处理文件路径
	 */
	private  String createBatFile() {
		String filePath = path + "\\" + name + ".bat";

		String drive = new String(path.toCharArray(), 0, 1) + ":" + "\r\n";
		String order1 = "cd " + path + "\r\n";
		//设置编码格式为utf-8
		String order2 = "javac " + " -encoding utf-8 " + name + ".java";
		//编译命令
		String allOrder = drive + order1 + order2;
		try {
			// 创建文件
			File file = new File(filePath);
			// 写入文件
			FileOutputStream outFile = new FileOutputStream(file, false);
			outFile.write(allOrder.getBytes());
			outFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}
	
	public byte[] getClassFile(){
		File classFile = new File(path + "\\" + name + ".class");
		byte[] mainClass = new byte[(int) classFile.length()];
		try {
			FileInputStream in = new FileInputStream(classFile);
			in.read(mainClass);
			in.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mainClass;
	}
}
