package com.zuyue.service;

import java.io.Serializable;

import org.hibernate.criterion.DetachedCriteria;

import com.zuyue.pager.Pager;

public interface BaseService<T> {
	public void add(T entity);
	
	public void edit(T entity);
	
	public void delete(T entity);
	
	public void delete(Serializable id);
	
	public void flush();

	public void clear();

	public void evict(Object object);
	
	public T get(Serializable id);
	
	public Pager<T> findByPager(Pager<T> pager);
	
	public Pager<T> findByPager(Pager<T> pager, DetachedCriteria detachedCriteria);
}
