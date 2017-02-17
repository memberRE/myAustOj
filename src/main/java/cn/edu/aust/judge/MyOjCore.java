package cn.edu.aust.judge;

import java.io.IOException;
import java.util.Arrays;

public class MyOjCore {
	/**类路径**/
	private String classPath;
	
	/**类名称**/
	private String className ;
	
	/**调用方法名称**/
	private String methodName;
	
	/**评测结果**/
	private int result;
	
	/**类文件**/
	private byte[] classFile;
	
	/**
	 * 
	 * @param classPath  类文件路径
	 * @param className  类文件名称
	 * @param methodName	要执行的方法名称
	 */
	public MyOjCore(String classPath, String className, String methodName){
		this.classPath = classPath;
		this.className = className;
		this.methodName = methodName;
	}
	
	//初始化方法返回class
	public void init(){
		//编译java文件
		MyCompiler mc = new MyCompiler(classPath, className);
		int resultCompiler = mc.command();
//		int resultCompiler = MyCompiler.command(classPath,className);
		if(resultCompiler != 0){
			//编译错误
			setResult(JudgeResult.WRONG_COMPILER);
		}else{
			//编译成功,获取类文件
			this.classFile = mc.getClassFile();
			/*MyClassLoader mcl = new MyClassLoader();
			try {
				this.cla = mcl.loadClass(classPath, className);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}*/
		}
	}
	
	/**
	 * 设置评测结果
	 * @param result
	 */
	private void setResult(int result){
		this.result = result;
	}
	
	
	
	/**
	 * 运行方法，并与标准输入输出对比，返回运行结果
	 * @param normInput
	 * @param normOutput
	 * @return 运行结果
	 */
	public OjModel run(String[] normInput,String [] normOutput,long timeLimit, float memoryLimit){
		if(this.classFile == null){
			//编译错误
			System.out.println("编译错误");
			//return JudgeResult.toString(result);
			OjModel om = new OjModel();
			om.setResult(result);
			return om;
			
		}
		
		try {
			
			System.out.println("设置模型");
			OjModel om = new OjModel();
			om.setNormInput(normInput);
			om.setClassName(className);
			om.setClassFile(classFile);
			om.setMethodName(this.methodName);
			//设置超时
			om.setNormTimeLimit(timeLimit);
			//设置内存大小
			om.setNormMemoryLimit(memoryLimit);
			
			if (!"".equals(om.getNormInput())) {
				System.out.println("++++++++++++++标准输入+++++++++++++");
				for (int i = 0; i < om.getNormInput().length; i++) {
					System.out.print(om.getNormInput()[i] + "  ");
				} 
			}
			if (!"".equals(normOutput)) {
			System.out.println("\n++++++++++++++标准输出+++++++++++++");
			for(int i = 0 ; i < normOutput.length; i++){
				System.out.print(normOutput[i] + "  ");
			}
			}
			
			MyTask mt = new MyTask(om);
			OjModel newOm = mt.execute();
			
			//比对标准输出与实际输出
			if(newOm.getResult() == 0){
				
				if (!"".equals(newOm.getActualOutput()) && newOm.getActualOutput().length > 0) {
					System.out.println("\n++++++++++++++执行获取的输出+++++++++++++");
					for(int i = 0 ; i < newOm.getActualOutput().length; i++){
						System.out.print(newOm.getActualOutput()[i] + "  ");
					}
				}
				System.out.println("\n花费时长：" + newOm.getActualTime() + 
						"  消耗内存：" + newOm.getActualMemory() + " 执行过程是否发生异常：" + newOm.getResult());
				
				//判断消耗时间与消耗的内存
				if(newOm.getActualTime() > newOm.getNormTimeLimit()){
					//超时
					setResult(JudgeResult.TIME_LIMITE_EXCEED);
				}else if(newOm.getActualMemory() > newOm.getNormMemoryLimit()){
					//消耗超出指定内存
					setResult(JudgeResult.MEMORY_LIMIT_EXCEED);
				}else{
					//说明没有发生异常，结果仍然为初始值0，然后在进行比对
					compare(normOutput,newOm.getActualOutput());
				}
				//return JudgeResult.toString(result);
				newOm.setResult(result);
				return newOm;
			}else{
				//发生了异常，直接返回结果
				//return JudgeResult.toString(newOm.getResult());
				return newOm;
			}
			
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 比较标准输出与实际输出
	 * @param normOutput 标准输出
	 * @param actualOutput 实际输出
	 * @return 比较结果
	 */
	private void compare(String[] normOutput, String[] actualOutput) {
		if(Arrays.equals(normOutput, actualOutput)){
			//相等，答案正确
			setResult(JudgeResult.ACCEPTED);
		}else{
			//不相等，答案错误
			setResult(JudgeResult.WRONG_ANSWER);
		}
	}
}
