package com.zuyue.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import com.zuyue.core.ZuyueConfig;
import com.zuyue.formula.SymbolModel;

/**
 *<p>Title:输出公式的符号 </p>
 *
 *<p>Description: </p>
 *
 *<p>Copyright: Copyright (c) 2010</p>
 *
 *<p>Company:昆明逐月科技有限公司</p>
 *
 *<p>Author shizx</p>
 *
 *<p>Email:shizhuxiong@gmail.com </p>
 *
 *<p>Version :1.0</p>
 *
 *<p>date:2013-5-5 下午11:21:57</p>
 */
public class SymbolsTag extends TagSupport {

	private static final long serialVersionUID = 1465565266682090101L;

	/**
	 * onclick方法的名称
	 */
	private String clickFunction = "";
	
	/**
	 * 样式的名称
	 */
	private String className;
	
	
	/**
	 * @return 
	 */
	public String getClickFunction() {
		return clickFunction;
	}


	/**
	 * @param clickFunction the clickFunction to set
	 */
	public void setClickFunction(String clickFunction) {
		this.clickFunction = clickFunction;
	}


	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}


	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}


	@Override
	public int doStartTag() throws JspException {
		
		try {
			StringBuilder sb = new StringBuilder();
			
			List<SymbolModel> symbols = ZuyueConfig.getSymbols();
			for (SymbolModel sm : symbols) {
				sb.append("<td>");
				sb.append("<input type='button' value='"+sm.getSymbol()+"' class='"+className+"' onclick='"+clickFunction+"(\""+sm.getSymbol()+"\")' />");
				sb.append("</td>");
			}
			pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Tag.SKIP_PAGE;
	}

}

