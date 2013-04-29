package com.zuyue.test.action;

import com.opensymphony.xwork2.ActionSupport;

public class DynamicResultAction extends ActionSupport {

	private static final long serialVersionUID = 2220123412341234124L;
	
	private String type;
	private String page;
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String  execute() {
		if ("1".equals(type)) {
			page = "/test/Test_register_success.jsp";
		}else {		
			page = "/test/Test_register_fail.jsp";
		}
		return SUCCESS;
	}

}
