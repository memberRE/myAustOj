package cn.edu.aust.judge;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import cn.edu.aust.util.Contants;


/**
 * 负责和判题服务进行通信并获取结果
 * @author lvbiao
 *
 */
public class MyTask{
	private OjModel om;
	
	public MyTask(OjModel om){
		this.om = om;
	}
	
	public OjModel execute() throws IOException{
		Socket socket = null;
		ObjectInputStream is = null;
		ObjectOutputStream os = null;
		OjModel omNew = null;
		try {
			System.out.println("\n客户端发起连接...");
			socket = new Socket(Contants.JUDGE_LOCATION, Contants.JUDGE_PORT);
			os = new ObjectOutputStream(socket.getOutputStream());
			os.writeObject(om);
			os.flush();
			System.out.println("等待接收数据...");
			
			is = new ObjectInputStream(socket.getInputStream());
			omNew = (OjModel) is.readObject();
			
			System.out.println("已经接收到数据...");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(os != null){
				os.close();
			}
			if(is != null){
				
				is.close();
			}
			if(socket != null){
				socket.close();
			}
		}
		return omNew;
	}
}
