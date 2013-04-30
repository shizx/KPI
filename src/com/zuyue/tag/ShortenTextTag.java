package com.zuyue.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import com.zuyue.util.StringUtil;
import com.zuyue.util.Validator;

/**
 *<p>Title: </p>
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
 *<p>date:2013-4-30 下午1:44:00</p>
 */
public class ShortenTextTag extends TagSupport {

	private static final long serialVersionUID = 8595588885796823344L;
	
	
	@Override
	public int doStartTag() throws JspException {
		
		try {
            if (Validator.isNotNull(text)) {
			    pageContext.getOut().write(StringUtil.shorten(text, length, suffix));
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Tag.SKIP_PAGE;
	}
	
	
	private String text;
	/**
	 * @return 获得传入的字符串
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param 设置脂肪层
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return 获取取字符串的长度
	 */
	public int getLength() {
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}
	/**
	 * @return the suffix
	 */
	public String getSuffix() {
		return suffix;
	}
	/**
	 * @param suffix the suffix to set
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	private int length;
	private String suffix = "...";

}

