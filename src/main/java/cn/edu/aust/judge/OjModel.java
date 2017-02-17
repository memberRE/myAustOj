package cn.edu.aust.judge;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 与判题服务进行通信是需要传送的数据
 * @author lvbiao
 *
 */
public class OjModel implements Serializable {
	private static final long serialVersionUID = 308138868350981432L;
	private String className;
	private byte[] classFile;
	private String methodName;// 执行方法名称
	private String[] normInput;// 标准输入
	private float normMemoryLimit;// 内存限制(kb)
	private long normTimeLimit;// 时间限制(毫秒)
	private String[] actualOutput;// 实际输出
	private float actualMemory;// 实际内存使用(kb)
	private long actualTime;// 实际花费时间(毫秒)
	private int result;//运行结果，主要用于记录产生的异常类型

	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public byte[] getClassFile() {
		return classFile;
	}

	public void setClassFile(byte[] classFile) {
		this.classFile = classFile;
	}

	public String[] getNormInput() {
		return normInput;
	}

	public void setNormInput(String[] normInput) {
		this.normInput = normInput;
	}

	public float getNormMemoryLimit() {
		return normMemoryLimit;
	}

	public void setNormMemoryLimit(float normMemoryLimit) {
		this.normMemoryLimit = normMemoryLimit;
	}

	public long getNormTimeLimit() {
		return normTimeLimit;
	}

	public void setNormTimeLimit(long normTimeLimit) {
		this.normTimeLimit = normTimeLimit;
	}

	public String[] getActualOutput() {
		return actualOutput;
	}

	public void setActualOutput(String[] actualOutput) {
		this.actualOutput = actualOutput;
	}

	public float getActualMemory() {
		return actualMemory;
	}

	public void setActualMemory(float actualMemory) {
		this.actualMemory = actualMemory;
	}

	public long getActualTime() {
		return actualTime;
	}

	public void setActualTime(long actualTime) {
		this.actualTime = actualTime;
	}

}
