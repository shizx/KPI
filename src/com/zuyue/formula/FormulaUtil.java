package com.zuyue.formula;

import java.io.File;
import java.io.FileNotFoundException;

import com.zuyue.classLoader.DynamicClassLoader;
import com.zuyue.exception.BusinessException;

/**
 * 公式工具类
 * @author Z
 *
 */
public class FormulaUtil {
	
	/**
	 * 生成公式类的包明
	 */
	public static final String SRC_PACKAGE_PATH = "com.zuyue.formula.tmp";
	
	/**
	 * 公式类生产java文件所在的包的物理路径
	 */
	public static final String PACKAGE_HPY_PATH = FormulaClassGenarater.class.getResource("").getPath() + "tmp" + "/";
	
	/**
	 * 当前classes目录所在的物理路径
	 */
	public static final String CLASSES_PHY_PATH = PACKAGE_HPY_PATH.substring(0,PACKAGE_HPY_PATH.indexOf("classes") + 7);
	
	/**
	 * 公式中用来替换参数的符号,比如公式格式($*$)-$ 对应的正常公式为(5*5)-5
	 */
	public static final String FORMULA_SPECIAL_SIGN = "$";
	
	/**
	 * 获得公式类java源文件的物理地址
	 * @param className
	 * @return
	 */
	public static String getFormulaJavaFilePhyPath(String className) {
		return FormulaUtil.PACKAGE_HPY_PATH + className + ".java";
	}
	
	/**
	 * 获得公式类java源文件的File对象
	 * @param className
	 * @return
	 */
	public static File getFormulaJavaFile(String className) {
		return new File(getFormulaJavaFilePhyPath(className));
	}
	
	/**
	 * 获得公式类class的物理地址
	 * @param className
	 * @return
	 */
	public static String getFormulaClassFilePhyPath(String className) {
		return FormulaUtil.PACKAGE_HPY_PATH + className + ".class";
	}
	
	/**
	 * 获得公式类class的File对象
	 * @param className
	 * @return
	 */
	public static File getFormulaClassFile(String className) {
		return new File(getFormulaClassFilePhyPath(className));
	}
	
	/**
	 * 获得class包名所在路径，比如：com.xx.TestAction
	 * @param className
	 * @return
	 */
	public static String getClassPath(String className) {
		return SRC_PACKAGE_PATH + "." + className + "." + "class";
	}
	
	/**
	 * 根据公式类名称，获得公式实例
	 * @return
	 * @throws BusinessException
	 */
	public static IFormula getFormulaClassInstance(String className) throws BusinessException {		
		try {
			//class文件的全路径
			final File classPhyFile = FormulaUtil.getFormulaClassFile(className);
			if (!classPhyFile.exists()) {
				throw new FileNotFoundException("文件不存在："+classPhyFile.getPath());
			}
			
			//获得动态的classLoader
			DynamicClassLoader loader = new DynamicClassLoader(DynamicClassLoader.class.getClassLoader());
			
			return (IFormula)loader.loadClass(FormulaUtil.CLASSES_PHY_PATH, FormulaUtil.getClassPath(className)).newInstance();	
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
}
