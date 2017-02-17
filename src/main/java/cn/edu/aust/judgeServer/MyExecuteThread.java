package cn.edu.aust.judgeServer;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import cn.edu.aust.judge.JudgeResult;
import cn.edu.aust.judge.OjModel;


public class MyExecuteThread implements Runnable {
	private Socket socket = null;
	private static Thread thread = null;
	// 运行结果（主要用于记录非正常运行时，抛出的错误类型,如果运行过程中没有抛出异常，则结果为空）
	private static int result = 0;

	public MyExecuteThread(Socket socket) {
		this.socket = socket;
	}

	// 处理通信细节的静态方法，这里主要是方便线程池服务器的调用
	private static void execute(Socket mysocket) throws IOException {
		ObjectOutputStream os = null;
		ObjectInputStream is = null;
		try {

			// 通过输入流获取对象
			is = new ObjectInputStream(mysocket.getInputStream());
			OjModel om = (OjModel) is.readObject();
			// 进行系列操作
			// System.out.println("设置的输入:" + normInputBuffer.toString());
			// 获取类及方法
			MyClassLoader mcl = new MyClassLoader();
			Class<?> cla = mcl.loadClass(om.getClassFile(), om.getClassName());
			Method mainMethod = cla.getMethod(om.getMethodName(), String[].class);
			mainMethod.setAccessible(true);
			if (!"".equals(om.getNormInput())) {
				// 设置输入
				StringBuffer normInputBuffer = new StringBuffer();
				for (int i = 0; i < om.getNormInput().length; i++) {
					normInputBuffer.append(om.getNormInput()[i] + " ");
				}
				// 输入重定向
				System.setIn(new BufferedInputStream(new ByteArrayInputStream(normInputBuffer.toString().getBytes())));
			}
			
			SecurityManager security = null;
			try {
				//设置安全管理器
				security = System.getSecurityManager();
				if (security == null) {
					System.setSecurityManager(new MySecurityManager());
				}
				
				// 准备执行核心函数
				process(om, mainMethod);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}finally{
				if (security != null) {
					System.setSecurityManager(null);
				}
			}

			// 如果结果不为0，向OjMedel对象中设置结果
			if (result != 0) {
				om.setResult(result);
			}

			os = new ObjectOutputStream(mysocket.getOutputStream());
			os.writeObject(om);
			os.flush();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
			// 重置结果
			result = 0;
			thread = null;
		}
	}


	private static void setResult(int newresult) {
		if (result == 0) {
			result = newresult;
		}
	}

	/**
	 * 将字节表示的内存消耗量以kb表示，并保留两位小数
	 * 
	 * @param usedMemory
	 * @return
	 */
	private static float switchMemory(int usedMemory) {
		float tem = (float) usedMemory / 1024.0f;
		// 保留两位小数,四舍五入
		float usedMemoryKB = new BigDecimal(tem).setScale(2, 4).floatValue();
		// float usedMemoryKB = (float)(Math.round(tem*100))/100;
		return usedMemoryKB;
	}

	/**
	 * 执行核心函数，单独再分一个线程任务，主要是防止用户程序运行超时，如果超时就终止这个核心函数执行线程
	 * 
	 * @return
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	private static OjModel process(final OjModel ojModel, final Method mainMethod)
			throws  ExecutionException, InterruptedException {
		FutureTask<OjModel> task = null;
		try {
			task = new FutureTask<OjModel>(new Callable<OjModel>() {
				public OjModel call() throws Exception {
					long startTime = 0;
					int startMemory = 0;
					MemoryMXBean mmb = ManagementFactory.getMemoryMXBean();
					// 输出重定向 重新定向输出流
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					System.setOut(new PrintStream(baos));
					try {
						// 获取消耗的内存 必须在执行前对垃圾回收,否则不准确.
						System.gc();
						startTime = System.currentTimeMillis();
						startMemory = (int) mmb.getHeapMemoryUsage().getUsed();
						// 执行main方法
						mainMethod.invoke(null, new Object[] { new String[0] });
					} catch (InvocationTargetException e) {
						Throwable targetException = e.getTargetException();
						e.printStackTrace();
						// 超过内存限制
						if (targetException instanceof OutOfMemoryError){
							setResult(JudgeResult.MEMORY_LIMIT_EXCEED);
						}else if (targetException instanceof SecurityException
								|| targetException instanceof ExceptionInInitializerError) {
							// 非法操作处理
							setResult(JudgeResult.RESTRICT_FUNCTION);
						} else {
							// 运行时错误处理
							String stage = e.getCause().toString();
							if (stage.contains("Output Limit Exceed")) {
								setResult(JudgeResult.OUTPUT_LIMIT_EXCEED);
							} else {
								// setResult(JudgeResult.RUNTIME_ERROR);
								if (stage.endsWith("StackOverflowError")) {
									setResult(JudgeResult.RUNTIME_STACK_OVERFLOW);
								} else if (stage.endsWith("/ by zero")) {
									setResult(JudgeResult.RUNTIME_DIVIDE_BY_ZERO);
								} else if (stage.contains("ArrayIndexOutOfBoundsException")) {
									setResult(JudgeResult.RUNTIME_ARRAY_BOUNDS_EXCEEDED);
								} else {
									setResult(JudgeResult.RUNTIME_ERROR);
								}
							}
						}
						//throw new RuntimeException("Runtime Exception");
					} 
					int usedMemory = (int) (mmb.getHeapMemoryUsage().getUsed() - startMemory);
					long usedTime = System.currentTimeMillis() - startTime;
					// 将使用内存的字节表示法转换为kb表示，保留两位小数
					float usedMemoryKB = switchMemory(usedMemory);

					// 获取输出
					byte[] b = new byte[4096];
					baos.write(b);
					String output = baos.toString().trim();

					// 设置输出
					if (!"".equals(output)) {
						
						String[] actualOutput = output.split("[\\s,]");
						ojModel.setActualOutput(actualOutput);
					}
					// 设置执行方法花费时间
					ojModel.setActualTime(usedTime);
					// 设置内存消耗
					ojModel.setActualMemory(usedMemoryKB);
					// 设置运行结果
					if (result != 0) {
						ojModel.setResult(result);
					}
					return ojModel;
				}
			});
		} catch (Exception e) {
			throw new RuntimeException();
		}

		thread = new Thread(task);
		thread.start();
		OjModel newoj = null;
		try {
			//设置超时，如果程序在超时时间加上50毫秒之后仍然没有执行完毕，强行结束任务
			newoj = task.get(ojModel.getNormTimeLimit() + 50,TimeUnit.MILLISECONDS);
		} catch (TimeoutException e) {
			//任务超时,这里防止用户提交任务重发生死循环
			setResult(JudgeResult.TIME_LIMITE_EXCEED);
			//取消任务
			task.cancel(true);
			e.printStackTrace();
			return ojModel;
		}
		return newoj;
	}

	public void run() {
		try {
			execute(this.socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
