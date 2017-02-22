package cn.edu.aust.judgeServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import cn.edu.aust.util.Contants;

/**
 * 执行方法
 * 
 * @author lvbiao
 *
 */
public class ExecuteServer {

	public static void main(String[] args) throws IOException {
		ServerSocket server = null;
		try {
			server = new ServerSocket(Contants.JUDGE_PORT);
			Socket socket;
			System.out.println("执行进程已启动");
			Executor service = Executors.newCachedThreadPool();
			while (true) {
				socket = server.accept();
				//设置连接限制为10秒，防止程序总出现位置阻塞
				socket.setSoTimeout(100);
				service.execute(new MyExecuteThread(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(server != null){
				server.close();
			}
		}
	}
}


