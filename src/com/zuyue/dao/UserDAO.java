package com.zuyue.dao;

import com.zuyue.model.User;

public interface UserDAO extends BaseDAO<User>{
	public void save(User u);
	
	public boolean exsistWithUserName(String userName);
	
	public void delete(User u);
	
	public User getUser(String emailAddress,String password);
}
