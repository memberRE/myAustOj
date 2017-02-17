package cn.edu.aust.judge;


public class MyOjClient {
	public static void main(String[] args) {
		//必须保证在同一时刻不能传入的相同的类文件名
		MyOjCore moc = new MyOjCore("F:\\mavenProject\\MyOjCore\\Test", "Test2", "main");
		String[] normInput = { "1", "1"};//标准输入
		String[] normOutput = {"2"};//标准输出
		long timeLimit = 5000L;//设置时间限制
		float memoryLimit = 500.0f;//设置内存限制
		moc.init();
		OjModel om = moc.run(normInput, normOutput,timeLimit,memoryLimit);
		System.out.println("执行结果：" + JudgeResult.toString(om.getResult()));
	}
}
