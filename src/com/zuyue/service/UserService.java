package com.zuyue.service;

import com.zuyue.model.User;

public interface UserService extends BaseService<User> {
	public void add(User u);
	
	public void delete(User u);
	
	public boolean exsistWithUserName(String name);
	
	public User getUser(String emailAddress,String password);
	
}
