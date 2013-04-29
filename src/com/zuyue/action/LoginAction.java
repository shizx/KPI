package com.zuyue.action;

import javax.annotation.Resource;

import com.zuyue.model.User;
import com.zuyue.service.UserService;

/**
 * 登录的action
 * @author Z
 *
 */
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = -1258072962310608059L;
	
	private String username;
	private String passwordShadow;
	
	

	@Resource
	private UserService userService;

	public String login() {
		
		User user = userService.getUser(username, passwordShadow);		
		if (user == null) {
			addFieldError("loginError", "邮箱账号或者密码错误！");
			return ERROR;
		}
		return SUCCESS;
	}
	
	

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	public String getPasswordShadow() {
		return passwordShadow;
	}

	public void setPasswordShadow(String passwordShadow) {
		this.passwordShadow = passwordShadow;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
