package com.zuyue.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.xwork.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.zuyue.dao.BaseDAO;
import com.zuyue.pager.Pager;
import com.zuyue.pager.Pager.OrderType;
import com.zuyue.pager.impl.PagerSimple;

@Repository
public class BaseDAOImpl<T> implements BaseDAO<T> {
	
	private Class<T> entityClass = null;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseDAOImpl() {
		this.entityClass = null;
		Class c = getClass();
        Type type = c.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            this.entityClass = (Class<T>) parameterizedType[0];
        }
	}
	private SessionFactory sessionFactory;

	protected Session getSession() {	
		
		return sessionFactory.getCurrentSession();
	}
	
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(T t) {
		getSession().save(t);	
	}

	public void delete(T t) {
		getSession().delete(t);
	}

	public void update(T t) {
		getSession().update(t);
	}
	
	public void delete(Serializable id) {
		T t = load(id);
		getSession().delete(t);
	}
	
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		return (T)getSession().get(entityClass, id);
	}
	
	@SuppressWarnings("unchecked")
	public T load(Serializable id) {
		return (T)getSession().load(entityClass, id);
	}
	
	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

	public void evict(Object object) {
		Assert.notNull(object, "object is required");
		getSession().evict(object);
	}
	
	public Pager<T> findByPager(Pager<T> pager) {
		if (pager == null) {
			pager = new PagerSimple<T>();
		}
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
		return findByPager(pager, detachedCriteria);
	}
	
	@SuppressWarnings("unchecked")
	public Pager<T> findByPager(Pager<T> pager, DetachedCriteria detachedCriteria) {
		if (pager == null) {
			pager = new PagerSimple<T>();
		}
		Integer pageNumber = pager.getCurrentPageNumber();
		Integer pageSize = pager.getPageSize();
		String property = pager.getProperty();
		String keyword = pager.getKeyword();
		String orderBy = pager.getOrderBy();
		OrderType orderType = pager.getOrderType();
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());

		if (StringUtils.isNotEmpty(property) && StringUtils.isNotEmpty(keyword)) {
			String propertyString = "";
			if (property.contains(".")) {
				String propertyPrefix = StringUtils.substringBefore(property, ".");
				String propertySuffix = StringUtils.substringAfter(property, ".");
				criteria.createAlias(propertyPrefix, "model");
				propertyString = "model." + propertySuffix;
			} else {
				propertyString = property;
			}
			criteria.add(Restrictions.like(propertyString, "%" + keyword + "%"));
		}
		
		//解决detachedCriteria rowCount前不加能orderby的问题
		CriteriaImpl impl = (CriteriaImpl) criteria;
        List<T> orderEntrys = new ArrayList<T>();
        try{
            Field field = CriteriaImpl.class.getDeclaredField("orderEntries");
            //field is private, so we must set it accessible when we access it. 
            field.setAccessible(true); 
            //Get orders
            orderEntrys = (List<T>) field.get(impl);
            //Remove orders
            field.set(criteria,new ArrayList<T>());
        }catch(Exception ex){
            ex.printStackTrace();
        }

		Integer totalCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);
		
		//把order by 设置回来
		try{
            Field field = CriteriaImpl.class.getDeclaredField("orderEntries");
            field.setAccessible(true); 
            //Add orders return
            List<T> innerOrderEntries = (List<T>) field.get(criteria); 
            innerOrderEntries.addAll(orderEntrys); 
            
            //如果criteria排序中包含了orderBy，把orderBy设置为空
            if(orderEntrys!=null && orderBy !=null && orderEntrys.toString().contains(orderBy))
            	orderBy="";
        }catch(Exception ex){
            ex.printStackTrace();
        }

		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		criteria.setFirstResult((pageNumber - 1) * pageSize);
		criteria.setMaxResults(pageSize);
		if (StringUtils.isNotEmpty(orderBy) && orderType != null) {
			if (orderType == OrderType.asc) {
				criteria.addOrder(Order.asc(orderBy));
			} else {
				criteria.addOrder(Order.desc(orderBy));
			}
		}
		pager.setTotalElements(totalCount);
		pager.setElements(criteria.list());
		return pager;
	}
	
}
