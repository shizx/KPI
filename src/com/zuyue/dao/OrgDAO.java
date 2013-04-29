package com.zuyue.dao;

import java.util.List;

import com.zuyue.model.OrgInfo;

public interface OrgDAO extends BaseDAO<OrgInfo> {

	List<OrgInfo> findAll();
}
