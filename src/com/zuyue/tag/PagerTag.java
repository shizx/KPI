package com.zuyue.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import com.zuyue.pager.Pager;

public class PagerTag extends TagSupport {

	private Pager pager;
	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
	private String nameSpace;

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}


	@Override
	public int doStartTag() throws JspException {
		
		try {
            if (pager != null) {
			    pageContext.getOut().write(pager.toHTML(nameSpace));
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Tag.SKIP_PAGE;
	}

	private static final long serialVersionUID = -925251914287095924L;

}
