package com.zuyue.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zuyue.dao.UserDAO;
import com.zuyue.model.User;
import com.zuyue.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	@Resource
	private UserDAO userDAO;
	
	@Resource
	public void setUserDAO(UserDAO userDAO) {
		super.setBaseDao(userDAO);
	}
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public boolean exsistWithUserName(String name) {
		return userDAO.exsistWithUserName(name);
	}	
	
	public User getUser(String emailAddress,String password) {
		return userDAO.getUser(emailAddress,password);
	}
}
