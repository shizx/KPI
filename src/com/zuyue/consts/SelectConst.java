package com.zuyue.consts;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义下拉框的一些常量
 * User: Z
 * Date: 13-4-29
 * Time: 下午4:20
 */
public class SelectConst {

    /**
     *指标信息中的维度
     */
    public static Map<String,String> KPIINFO_DIMENSION = new HashMap<String, String>();
    static {
        KPIINFO_DIMENSION.put("1","价值创造");
        KPIINFO_DIMENSION.put("2","客户服务");
        KPIINFO_DIMENSION.put("3","内部运营");
        KPIINFO_DIMENSION.put("4","学习成长");
    }

    /**
     * 指标类型中的类型
     */
    public static Map<String,String> KPIINFO_TYPE = new HashMap<String, String>();
    static {
        KPIINFO_TYPE.put("1","一级评价指标");
        KPIINFO_TYPE.put("2","二级评价指标");
        KPIINFO_TYPE.put("3","需关注评价指标");
    }
}
