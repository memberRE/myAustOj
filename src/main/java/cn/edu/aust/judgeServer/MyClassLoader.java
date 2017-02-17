package cn.edu.aust.judgeServer;

public class MyClassLoader extends ClassLoader {
	public Class<?> loadClass(byte[] classFile, String className) {
		Class<?> cla = super.defineClass(className, classFile, 0, classFile.length);
		return cla;
	}
}