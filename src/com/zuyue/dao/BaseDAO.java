package com.zuyue.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.zuyue.pager.Pager;


public interface BaseDAO<T> {
	public void save(T t);
	
	public void delete(T t);
	
	public void delete(Serializable id);
	
	public void update(T t);
	
	public T get(Serializable id);
	
	public T load(Serializable id);
	
	public Pager<T> findByPager(Pager<T> pager, DetachedCriteria detachedCriteria);
	
	public Pager<T> findByPager(Pager<T> pager);
	
	public void flush();

	public void clear();

	public void evict(Object object);
	
}
