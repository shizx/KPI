package com.zuyue.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zuyue.dao.FormulaValidtorDAO;
import com.zuyue.model.KPIValidateFormula;
import com.zuyue.service.FormulaValidtorService;

/**
 *<p>Title: </p>
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
 *<p>date:2013-5-5 上午11:11:11</p>
 */
@Service("formulaValidtorService")
public class FormulaValidtorServiceImpl  extends BaseServiceImpl<KPIValidateFormula> implements FormulaValidtorService {

	@Resource
	private FormulaValidtorDAO formulaValidtorDAO;

	@Resource
	public void setDao(FormulaValidtorDAO formulaValidtorDAO) {
		super.setBaseDao(formulaValidtorDAO);
	}
	
	

}

