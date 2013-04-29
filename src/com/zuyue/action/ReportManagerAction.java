package com.zuyue.action;

public class ReportManagerAction extends BaseAction {


	private static final long serialVersionUID = 831602667095669235L;

	/**
	 * 综合计划指标完成情况报表 
	 * @return
	 */
	public String completeStatus() {
		return REPORT;
	}
	
	/**
	 * 关键指标完成情况分析报表 
	 * @return
	 */
	public String statusAnalysis() {
		return REPORT;
	}
	
	/**
	 * 生成综合报告
	 * @return
	 */
	public String generateCompositeReport() {
		return REPORT;
	}
	
}
