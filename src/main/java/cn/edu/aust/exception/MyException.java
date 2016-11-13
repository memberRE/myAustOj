package cn.edu.aust.exception;

/**
 * 自定义异常类
 */
public class MyException extends Exception {

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }
}
