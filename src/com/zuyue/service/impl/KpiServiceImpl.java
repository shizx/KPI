package com.zuyue.service.impl;

import com.zuyue.dao.KpiDAO;
import com.zuyue.model.KPIInfo;
import com.zuyue.service.KpiService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
