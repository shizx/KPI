package com.zuyue.test.action;

import com.zuyue.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class DomainModelAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute() {
		System.out.println(user.getUserName());
		
		return SUCCESS;
	}
}
