#java操作Cmd命令

标签（空格分隔）：java进阶

---

##一、简单命令操作
```
public class Cmd {
	public static void main(String[] args) {
		Runtime run = Runtime.getRuntime();
		try {
			Process process = run.exec("cmd.exe /c  chdir");
			InputStream in = process.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
			String s;
			while ((s = bufferedReader.readLine()) != null) {
				System.out.println(s);
			}
			in.close();
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```


##二、批量命令操作
```
public class Cmd {
	public static void main(String[] args) {
		String path = "F:\\Test\\my.bat";// 批处理文件，多个cmd命令组合的文件
		Runtime run = Runtime.getRuntime();
		try {
			Process process = run.exec("cmd.exe /c " + path);
			InputStream in = process.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
			String s;
			while ((s = bufferedReader.readLine()) != null) {
				System.out.println(s);
			}
			in.close();
			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
```

my.bat文件
```
F:
cd Test
javac Test.java
java  Test 123
```

Test.java
```
public class Test {
	public static void main(String[] args) {
		for(String s:args)
		System.out.print(s + "  ");
	}
}
```

##三、批量处理，并获取错误信息
```
public class Cmd2 {
	public static void main(String[] args) {
		String path = "F:\\Test\\my.bat";// 批处理文件，多个cmd命令组合的文件
		Runtime run = Runtime.getRuntime();
		try {
			Process process = run.exec("cmd.exe /c  " + path);

			InputStream in = process.getInputStream();
			InputStream inError = process.getErrorStream();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
			BufferedReader brError = new BufferedReader(new InputStreamReader(inError, "GBK"));

			String s;
			while ((s = bufferedReader.readLine()) != null) {
				System.out.println(s);
			}
			
			String error;
			while ((error = brError.readLine()) != null) {
				System.out.println(error);
			}
			in.close();
			bufferedReader.close();
			System.out.println("返回值：" + process.waitFor());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```

