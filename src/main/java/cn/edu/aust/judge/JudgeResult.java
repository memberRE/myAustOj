package cn.edu.aust.judge;

public class JudgeResult {

	/**
	 * 编译错误
	 */
	public static final int WRONG_COMPILER = 1;
	/**
	 * 非法操作处理
	 */
	public static final int RESTRICT_FUNCTION = 2;

	/**
	 * 运行超时
	 */
	public static final int TIME_LIMITE_EXCEED = 3;

	/**
	 * 输出超过限制
	 */
	public static final int OUTPUT_LIMIT_EXCEED = 4;

	/*
	 * 运行时错误,运行时程序报错
	 */
	public static final int RUNTIME_ERROR = 5;

	/*
	 * 答案错误
	 */
	public static final int WRONG_ANSWER = 6;

	/**
	 * 运行时堆溢出异常
	 */
	public static final int RUNTIME_STACK_OVERFLOW = 7;

	/*
	 * 除零异常
	 */
	public static final int RUNTIME_DIVIDE_BY_ZERO = 8;

	/**
	 * 内存溢出
	 */
	public static final int MEMORY_LIMIT_EXCEED = 9;

	/*
	 * 数组越界
	 */
	public static final int RUNTIME_ARRAY_BOUNDS_EXCEEDED = 10;

	/*
	 * 输出错误
	 */
	public static final int PRESENTING_ERROR = 11;

	/*
	 * 通过
	 */
	public static final int ACCEPTED = 12;

	/*
	 * 设置测评结果.
	 */
	public static String toString(int resultType) {
		String result;
		switch (resultType) {
		case 1:
			result = "编译错误";
			break;
		case 2:
			result = "非法操作处理";
			break;
		case 3:
			result = "运行超时";
			break;
		case 4:
			result = "输出异常";
			break;
		case 5:
			result = "运行时错误";
			break;
		case 6:
			result = "答案错误";
			break;
		case 7:
			result = "运行时堆溢出异常";
			break;
		case 8:
			result = "除零异常";
			break;
		case 9:
			result = "内存溢出";
			break;
		case 10:
			result = "数组越界";
			break;
		case 11:
			result = "输出错误";
			break;
		case 12:
			result = "通过";
			break;
		default:
			result = "未知结果";
			break;
		}
		return result;
	}

}
