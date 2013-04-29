package com.zuyue.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.zuyue.dao.UserDAO;
import com.zuyue.model.User;

@Repository
public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO {

	public void save(User u) {
		super.save(u);
	}

	public boolean exsistWithUserName(String userName) {
		String hql = "from User u where u.userName := userName";
		
		Session session = getSession();
		
		Query query = session.createQuery(hql)
							.setString("userName", userName);
		if (query==null || query.list().size()<=0) {
			return false;
		}
		return true;
	}

	public User getUser(String emailAddress,String password) {
		String hql = "from User user where lower(user.email) = lower(?) and lower(user.password) = lower(?)";
		return (User) getSession().createQuery(hql)
								.setParameter(0, emailAddress)
								.setParameter(1, password)
								.uniqueResult();
	}

}
