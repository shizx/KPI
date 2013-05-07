package com.zuyue.service;

import com.zuyue.model.KPIInfo;
import com.zuyue.pager.Pager;

/**
 * Created with IntelliJ IDEA.
 * User: S
 * Date: 13-4-14
 * Time: 下午5:21
 * To change this template use File | Settings | File Templates.
 */
public interface KpiService extends BaseService<KPIInfo> {
	
	/**
	 * 根据KPI名称查找kpi的pager信息
	 * @param kpiName
	 * @return
	 */
	public Pager<KPIInfo> findPagerByKpiName(String kpiName);
}
