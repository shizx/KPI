package com.zuyue.service;

import java.io.Serializable;
import java.util.List;

import com.zuyue.model.OrgInfo;

public interface OrgService extends BaseService<OrgInfo>{
	public List<OrgInfo> findAll();
}
