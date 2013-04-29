package com.zuyue.classLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DynamicClassLoader extends ClassLoader {
	public DynamicClassLoader(ClassLoader parent) {
		super(parent);
	}

	/**
	 * 获得动态的class
	 * @param classPath 获得classes的物理路径，比如：D:/workspace/study/Spring3.1.1_Struts2.3.3_Hibernate4.1.2/WebRoot/WEB-INF/classes
	 * @param classPath 获得类的路径，比如：com.zuyue.formula.tmp.C_9999xx.class 
	 * @return
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("rawtypes")
	public Class loadClass(String classesPhyPath, String classPath)
			throws ClassNotFoundException {
		try {
			String url = classPathParser(classesPhyPath) + classNameParser(classPath);
			URL myUrl = new URL(url);
			URLConnection connection = myUrl.openConnection();
			InputStream input = connection.getInputStream();
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int data = input.read();
			while (data != -1) {
				buffer.write(data);
				data = input.read();
			}
			input.close();
			byte[] classData = buffer.toByteArray();
			return defineClass(noSuffix(classPath), classData, 0,
					classData.length);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String pathParser(String path) {
		return path.replaceAll("\\\\", "/");
	}

	private String classPathParser(String path) {
		String classPath = pathParser(path);
		if (!classPath.startsWith("file:")) {
			classPath = "file:" + classPath;
		}
		if (!classPath.endsWith("/")) {
			classPath = classPath + "/";
		}
		return classPath;
	}

	private String classNameParser(String className) {
		return className.substring(0, className.lastIndexOf(".")).replaceAll("\\.", "/")
		+ className.substring(className.lastIndexOf("."));
	}

	private String noSuffix(String className) {
		return className.substring(0, className.lastIndexOf("."));

	}

	public static void main(String[] arguments) throws Exception {

		String classPath = "C:\\Documents and Settings\\Administrator\\Workspaces\\MyEclipse7.5\\lhsp\\web\\WEB-INF\\classes";

		String className = "libra.law.util.Test.class";

		new DynamicClassLoader(DynamicClassLoader.class.getClassLoader())

		.loadClass(classPath, className).newInstance();

	}

}
