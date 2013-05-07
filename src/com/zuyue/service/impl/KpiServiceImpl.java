package com.zuyue.service.impl;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.zuyue.dao.KpiDAO;
import com.zuyue.model.KPIInfo;
import com.zuyue.pager.Pager;
import com.zuyue.pager.Pager.OrderType;
import com.zuyue.pager.impl.PagerSimple;
import com.zuyue.service.KpiService;
import com.zuyue.util.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: S
 * Date: 13-4-14
 * Time: 下午5:22
 * To change this template use File | Settings | File Templates.
 */
@Service
public class KpiServiceImpl extends BaseServiceImpl<KPIInfo> implements KpiService {
    @Resource
    private KpiDAO kpiDAO;

    @Resource
    public void setKpiDAO(KpiDAO kpiDAO) {
        super.setBaseDao(kpiDAO);
    }
    
    
    public Pager<KPIInfo> findPagerByKpiName(String kpiName) {
    	Pager<KPIInfo> p = new PagerSimple<KPIInfo>();
		p.setOrderBy("kpiOrder");
		p.setOrderType(OrderType.asc);
		p.setPageSize(20);
		
		DetachedCriteria kpiInfoCriteria = DetachedCriteria.forClass(KPIInfo.class, "kpiInfo");;
		
		if (Validator.isNotNull(kpiName)) {
			kpiInfoCriteria.add(Restrictions.like("kpiInfo.name", "%"+kpiName+"%"));
		}
		
		return kpiDAO.findByPager(p, kpiInfoCriteria);
    }
}
