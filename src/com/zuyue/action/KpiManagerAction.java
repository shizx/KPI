package com.zuyue.action;


import javax.annotation.Resource;

import com.zuyue.model.KPIInfo;
import com.zuyue.pager.Pager;
import com.zuyue.pager.Pager.OrderType;
import com.zuyue.pager.impl.PagerSimple;
import com.zuyue.service.KpiService;

public class KpiManagerAction extends BaseAction {

	private static final long serialVersionUID = -7244520412836081407L;

    @Resource
    private KpiService kpiService;

    private Pager<KPIInfo> pager;

    private KPIInfo kpiInfo;  

	/**
     * 指标集合
     * @return
     */
	public String kpiList() {
		
		setPagerData();

		return LIST;
	}
	
	public String kpiValueList() {
		
		setPagerData();
		
		return LIST;
	}    

    @Override
    public String add() {
    	if (this.getIsNew()) {
    		kpiService.add(this.getKpiInfo());
    	} else {
    		kpiService.edit(this.getKpiInfo());
    	}
        return this.kpiValueList();
    }
    
    public String forwardEdit() {
    	   	
    	KPIInfo kpiInfo = getKiInfo();
    	
    	setKpiInfo(kpiInfo);
    	
    	return super.forwardEdit();
    }
    
    public String delete() {
    	kpiService.delete(kpiInfo.getKpiId());
    	return this.kpiList();
    }
    
    
    /**
     * 获得指标信息对象
     * @return
     */
    private KPIInfo getKiInfo() {
    	return kpiService.get(kpiInfo.getKpiId());
    }
    
    
    /**
     * 设置pager对象，以便前台能获取数据
     */
    private void setPagerData() {
    	Pager<KPIInfo> p = new PagerSimple<KPIInfo>();
		p.setOrderBy("kpiOrder");
		p.setOrderType(OrderType.asc);
        setPager(kpiService.findByPager(p));
    }    
    
    public KpiService getKpiService() {
        return kpiService;
    }

    public void setKpiService(KpiService kpiService) {
        this.kpiService = kpiService;
    }

    public Pager<KPIInfo> getPager() {
        return pager;
    }

    public void setPager(Pager<KPIInfo> pager) {
        this.pager = pager;
    }

    public KPIInfo getKpiInfo() {
        return kpiInfo;
    }

    public void setKpiInfo(KPIInfo kpiInfo) {
        this.kpiInfo = kpiInfo;
    }
    
    
}
