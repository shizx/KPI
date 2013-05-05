package com.zuyue.action;

import java.util.Set;

import javax.annotation.Resource;

import com.zuyue.model.KPIInfo;
import com.zuyue.model.KPIValidateFormula;
import com.zuyue.service.FormulaValidtorService;
import com.zuyue.service.KpiService;

/**
 *<p>Title: 每个指标验证公式的action</p>
 *
 *<p>Description: </p>
 *
 *<p>Copyright: Copyright (c) 2010</p>
 *
 *<p>Company:昆明逐月科技有限公司</p>
 *
 *<p>Author shizx</p>
 *
 *<p>Email:shizhuxiong@gmail.com </p>
 *
 *<p>Version :1.0</p>
 *
 *<p>date:2013-5-5 上午11:48:56</p>
 */
public class FormulaValidtorAction extends BaseAction {

	private static final long serialVersionUID = 4207654150300362665L;

	@Resource
	private FormulaValidtorService formulaValidtorService;
	
	@Resource
    private KpiService kpiService;
	
	/**
	 *存放每个指标对应的所有验证公式
	 */
	private Set<KPIValidateFormula> formulaValidatorList;
	
	/**
	 * 当前的指标信息
	 */
	private KPIInfo kpiInfo;
	
	/**
	 * 验证公式
	 */
	private KPIValidateFormula kpiValidateFormula;
		
	

	/**
     * 跳转到指标验证的列表
     * @return
     */
    public String list() {    	
    	
    	KPIInfo kpiInfo = kpiService.get(getKpiInfo().getKpiId());
    	setKpiInfo(kpiInfo);
    	
    	Set<KPIValidateFormula> formulaValidatorList = kpiInfo.getKPIValidateFormulas();
    	setFormulaValidatorList(formulaValidatorList);
    	
    	return super.list();
    }
    
    /**
     * 跳转到验证公式添加页面
     */
    public String forwardAdd() {
    	
    	KPIInfo kpiInfo = kpiService.get(getKpiInfo().getKpiId());
    	setKpiInfo(kpiInfo);
    	
    	return super.forwardAdd();
    }
    
    /**
     * 跳转到修改页面
     */
    public String forwardEdit() {
    	
    	KPIInfo kpiInfo = kpiService.get(getKpiInfo().getKpiId());
    	setKpiInfo(kpiInfo);
    	
    	KPIValidateFormula fk = formulaValidtorService.get(kpiValidateFormula.getFormulaId());
    	setKpiValidateFormula(fk);
    	
    	return super.forwardEdit();    	
    }
    
    
    /**
     * 执行添加或者修改操作
     */
    public String add() {
    	KPIInfo kpiInfo = kpiService.get(getKpiInfo().getKpiId());    	
    	kpiValidateFormula.setKPIInfo(kpiInfo);
    	
    	if (getIsNew()) {	    	
	    	formulaValidtorService.add(kpiValidateFormula);
    	} else {    		
    		formulaValidtorService.edit(kpiValidateFormula);
    	}
    	
    	return this.list();
    }
	
    /**
     * 执行删除操作
     */
    public String delete() {
    	
    	formulaValidtorService.delete(kpiValidateFormula.getFormulaId());
    	
    	return this.list();
    }
    
	public Set<KPIValidateFormula> getFormulaValidatorList() {
		return formulaValidatorList;
	}
	public void setFormulaValidatorList(Set<KPIValidateFormula> formulaValidatorList) {
		this.formulaValidatorList = formulaValidatorList;
	}
	public KPIInfo getKpiInfo() {
		return kpiInfo;
	}
	public void setKpiInfo(KPIInfo kpiInfo) {
		this.kpiInfo = kpiInfo;
	}
	public KPIValidateFormula getKpiValidateFormula() {
		return kpiValidateFormula;
	}
	public void setKpiValidateFormula(KPIValidateFormula kpiValidateFormula) {
		this.kpiValidateFormula = kpiValidateFormula;
	}
}

