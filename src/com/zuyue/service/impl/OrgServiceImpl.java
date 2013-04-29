package com.zuyue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zuyue.dao.BaseDAO;
import com.zuyue.dao.OrgDAO;
import com.zuyue.model.OrgInfo;
import com.zuyue.service.OrgService;

@Service
public class OrgServiceImpl extends BaseServiceImpl<OrgInfo> implements OrgService {
	
	@Resource
	private OrgDAO orgDAO;

	
	@Resource
	public void setBaseDAO(OrgDAO baseDAO) {
		super.setBaseDao(baseDAO);
	}


	public List<OrgInfo> findAll() {
		return orgDAO.findAll();
	}	
}
