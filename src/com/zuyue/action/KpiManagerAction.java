package com.zuyue.action;

import com.zuyue.model.KPIInfo;
import com.zuyue.pager.Pager;
import com.zuyue.service.KpiService;

import javax.annotation.Resource;

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
        setPager(kpiService.findByPager(null));
		return LIST;
	}
	
	public String kpiValueList() {
        setPager(kpiService.findByPager(null));
		return LIST;
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
    	setKpiInfo(kpiService.get(kpiInfo.getKpiId()));    	
    	return super.forwardEdit();
    }
    
    public String delete() {
    	kpiService.delete(kpiInfo.getKpiId());
    	return this.kpiList();
    }
}
