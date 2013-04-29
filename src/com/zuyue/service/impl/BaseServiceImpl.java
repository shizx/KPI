package com.zuyue.service.impl;

import java.io.Serializable;

import org.hibernate.criterion.DetachedCriteria;

import com.zuyue.dao.BaseDAO;
import com.zuyue.pager.Pager;
import com.zuyue.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDAO baseDAO;

	public BaseDAO getBaseDao() {
		return baseDAO;
	}

	public void setBaseDao(BaseDAO baseDao) {
		this.baseDAO = baseDao;
	}
	
	public void add(T entity) {
		baseDAO.save(entity);
	}

	public void edit(T entity) {
		baseDAO.update(entity);		
	}

	public void delete(T entity) {
		baseDAO.delete(entity);
	}

	public void delete(Serializable id) {
		baseDAO.delete(id);
	}
	
	public void flush() {
		baseDAO.flush();
	}

	public void clear() {
		baseDAO.clear();
	}

	public void evict(Object object) {
		baseDAO.evict(object);
	}
	

	public Pager<T> findByPager(Pager<T> pager) {
		return baseDAO.findByPager(pager);
	}

	public Pager<T> findByPager(Pager<T> pager,
			DetachedCriteria detachedCriteria) {
		return baseDAO.findByPager(pager, detachedCriteria);
	}
	
	public T get(Serializable id) {
		return (T) baseDAO.get(id);
	}

}
