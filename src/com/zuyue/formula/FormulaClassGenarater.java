package com.zuyue.formula;

import java.io.File;
import java.io.IOException;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import org.apache.commons.io.FileUtils;

import com.zuyue.exception.BusinessException;

/**
 * 公式类生成器
 * @author Z
 *
 */
public class FormulaClassGenarater {
	
	/**
	 * 生成公式时，参数的类型
	 */
	private static final String FORMULA_PARAM_TYPE = "Double";
	
	/**
	 * 生成公式时，参数的名称
	 */
	private static final String FORMULA_PARAM_NAME = "objects";
	
	private String className;
	private String formula;
	
	/**
	 * 公式生成类
	 * @param className 类名称
	 * @param formula 公式
	 */
	public FormulaClassGenarater(String className,String formula) {
		this.className = className;
		this.formula = formula;
	}
	
	public void genarateFormulaClass() {
		try {			
			genarateJavaSource();			
			compiler();				
		} catch (Exception e) {
			throw new BusinessException(e);
		}		
	}
	
	
	/**
	 * 生成java源文件
	 * @throws IOException
	 */
	private void genarateJavaSource() throws IOException {
		String rt = "\r\n";
		String javaSource = "package " + FormulaUtil.SRC_PACKAGE_PATH + ";" + rt
							+ "import com.zuyue.formula.IFormula;" + rt
						  	+ "public class " + className + " implements IFormula {" + rt
							+ "    public Object comput("+ FORMULA_PARAM_TYPE +"... "+ FORMULA_PARAM_NAME +") {" + rt 
							+ "        return "	+ getJavaSourceFormula() + ";" + rt 
							+ "    }" + rt 
							+ "}";

		//System.out.println(javaSource);

		File f = FormulaUtil.getFormulaJavaFile(className);	
		FileUtils.write(f, javaSource, "utf-8");
	}
	
	/**
	 * 编译成class
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void compiler() {
		// compile
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null,
				null, null);
		Iterable units = fileMgr.getJavaFileObjects(FormulaUtil.getFormulaJavaFilePhyPath(className));
		CompilationTask t = compiler.getTask(null, fileMgr, null, null, null,units);
		t.call();
	}
	
	/**
	 * 根据公式类名称，获得公式实例
	 * @return
	 * @throws BusinessException
	 */
	public IFormula getFormulaClassInstance() throws BusinessException {
		return FormulaUtil.getFormulaClassInstance(this.className);
	}
	
	/**
	 * 根据公式参数获得java源码中的参数表达形式
	 * @return
	 */
	public String getJavaSourceFormula() {
		String[] brackets = formula.split("\\"+FormulaUtil.FORMULA_SPECIAL_SIGN);
		
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<brackets.length;i++) {
			//把($*$)-$ 改成 (objects[0]*objects[1]-objects[2])
			String bracketStr = brackets[i];
			String paramStr = FORMULA_PARAM_NAME + "[" + i + "]";
			sb.append(bracketStr).append(paramStr);  
		}
		return sb.toString();
	}		
}
