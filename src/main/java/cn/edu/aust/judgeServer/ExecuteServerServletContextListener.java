package cn.edu.aust.judgeServer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ExecuteServerServletContextListener implements ServletContextListener{
	ExecuteServer server = null;
	ServerThread servetThread = new ServerThread();
	Thread thread = new Thread(servetThread);
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		thread.start();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		server.destroyServer();
		thread.interrupt();
	}

	class ServerThread implements Runnable{
		@Override
		public void run() {
			server = new ExecuteServer();
			//启动判题服务程序
			server.starServer();
		}
	}
}

