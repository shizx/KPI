package com.zuyue.test.action;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

//比较常用的获取request的方法
public class Test2Action extends ActionSupport implements RequestAware,SessionAware,ApplicationAware{
		
	private static final long serialVersionUID = 1L;
	
	private Map<String,Object> request;
	private Map<String,Object> session;
	private Map<String,Object> application;
	
	public void setApplication(Map<String, Object> application) {
		this.application = application;		
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public String register() { 
		System.out.println("要重新点击一下？");
		return ERROR;
	}
	

}
