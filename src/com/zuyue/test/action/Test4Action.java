package com.zuyue.test.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class Test4Action extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
		this.application = this.session.getServletContext();
		
	}

}
