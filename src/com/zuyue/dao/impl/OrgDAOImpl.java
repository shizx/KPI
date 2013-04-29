package com.zuyue.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.zuyue.dao.OrgDAO;
import com.zuyue.model.OrgInfo;
import com.zuyue.pager.Pager;
import com.zuyue.pager.Pager.OrderType;
import com.zuyue.pager.impl.PagerSimple;

@Repository("orgDAO")
public class OrgDAOImpl extends BaseDAOImpl<OrgInfo> implements OrgDAO {

	/**
	 * 根据seqNo排序
	 */
	public Pager<OrgInfo> finPager(Pager<OrgInfo> pager) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(OrgInfo.class);
		
		return this.finPager(pager, detachedCriteria);
	}

	/**
	 * 根据seqNo排序
	 */
	public Pager<OrgInfo> finPager(Pager<OrgInfo> pager,DetachedCriteria detachedCriteria) {
		if (pager == null) {
			pager = new PagerSimple<OrgInfo>();
			pager.setOrderBy("seqNo");
			pager.setOrderType(OrderType.asc);
		}
		return super.findByPager(pager, detachedCriteria); 
	}

	/**
	 * 获取所有的机构信息
	 */
	@SuppressWarnings("unchecked")
	public List<OrgInfo> findAll() {
		return (List<OrgInfo>) getSession().createQuery("from OrgInfo orgInfo ").list();
	}

}
