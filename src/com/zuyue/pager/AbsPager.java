package com.zuyue.pager;

import java.util.List;

import com.zuyue.pager.Pager;
import com.zuyue.util.PageUtil;

public class AbsPager<T> implements Pager<T> {
	// 当前页记录聚集
	protected List<T> elements;

	// 每页显示条目数,默认是20条目每页
	protected int pageSize = 20;

	// 当前页编码，从1开始
	protected int currentPageNumber = 1;

	// 记录总数
	protected int totalElements = 0;
	//总页数
	protected int totalSize = 0;	
	
	private String property;// 查找属性名称	

	private String keyword;// 查找关键字
	private String orderBy = "createDate";// 排序字段
	private OrderType orderType = OrderType.desc;// 排序方式
	
	protected final static String restrictNumCode = "onKeypress='if(event.keyCode <45||event.keyCode> 57)return false;' onpaste='return false;'";   //限制只能输入数字的js脚本

	/**
	 * 页面显示页数的条数: 只显示7条页数
	 */
	protected static final int PAGE_NUM_SHOW_COUNT = 7;
	
	/* (non-Javadoc)
	 * @see com.zuyue.pager.impl.Pager#getElements()
	 */
	public List<T> getElements() {
		return elements;
	}

	/* (non-Javadoc)
	 * @see com.zuyue.pager.impl.Pager#setElements(java.util.List)
	 */
	public void setElements(List<T> elements) {
		this.elements = elements;
	}

	
	/* (non-Javadoc)
	 * @see com.zuyue.pager.impl.Pager#getCurrentPageNumber()
	 */
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	/* (non-Javadoc)
	 * @see com.zuyue.pager.impl.Pager#setCurrentPageNumber(int)
	 */
	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	/* (non-Javadoc)
	 * @see com.zuyue.pager.impl.Pager#getPageSize()
	 */
	public int getPageSize() {
		return pageSize;
	}

	/* (non-Javadoc)
	 * @see com.zuyue.pager.impl.Pager#setPageSize(int)
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/* (non-Javadoc)
	 * @see com.zuyue.pager.impl.Pager#getTotalElements()
	 */
	public int getTotalElements() {
		return totalElements;
	}

	/* (non-Javadoc)
	 * @see com.zuyue.pager.impl.Pager#setTotalElements(int)
	 */
	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}
	
	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	
	/* (non-Javadoc)
	 * @see com.zuyue.pager.impl.Pager#isFirstPage()
	 */
	public boolean isFirstPage() {
		return getCurrentPageNumber() == 1;
	}

	/* (non-Javadoc)
	 * @see com.zuyue.pager.impl.Pager#isLastPage()
	 */
	public boolean isLastPage() {
		return getCurrentPageNumber() >= getLastPageNumber();
	}
	
	/* (non-Javadoc)
	 * @see com.zuyue.pager.impl.Pager#hasNextPage()
	 */
	public boolean hasNextPage() {
		return getLastPageNumber() > getCurrentPageNumber();
	}

	/* (non-Javadoc)
	 * @see com.zuyue.pager.impl.Pager#hasPreviousPage()
	 */
	public boolean hasPreviousPage() {
		return getCurrentPageNumber() > 1;
	}

	/* (non-Javadoc)
	 * @see com.zuyue.pager.impl.Pager#getLastPageNumber()
	 */
	public int getLastPageNumber() {
		return PageUtil.getCountPage(totalElements, pageSize); 
	}
	
	/* (non-Javadoc)
	 * @see com.zuyue.pager.impl.Pager#getHTML(java.lang.String)
	 */
	public String getHTML(String nameSpace){
		int pageNumber = this.getCurrentPageNumber();
		int maxPageNumber = this.getLastPageNumber();
		int total = this.getTotalElements();
		int pageSize = this.getPageSize();
		StringBuffer sb = new StringBuffer();
		sb.append("\u5171 "+ maxPageNumber +" \u9875  "+ total+"\u6761\u8bb0\u5f55  ");
		sb.append(pageSize+"\u6761/\u9875  ");
		sb.append("<span style=\"cursor:pointer\" onclick=\"return "+nameSpace+"go('0');\">\u7b2c\u4e00\u9875</span>  ");
		if(pageNumber > 1){
			sb.append("<span style=\"cursor:pointer\" onclick=\""+nameSpace+"go('"+(pageNumber-1)+"')\">\u4e0a\u4e00\u9875</span>  ");
		}	
		if(pageNumber < maxPageNumber){
			sb.append("<span style=\"cursor:pointer\" onclick=\""+nameSpace+"go('"+(pageNumber+1)+"')\">\u4e0b\u4e00\u9875</span>  ");
		} 
		sb.append("<span style=\"cursor:pointer\" onclick=\"return "+nameSpace+"go('"+maxPageNumber+"');\">\u672b\u9875</span>  ");
		sb.append("\u8df3\u8f6c\u5230");
		//如果页码小于10则用下拉框，否则使用输入框
		if(maxPageNumber<11){
			sb.append("<select name=\"pageNumber\" style=\"width:50px\">");
			for(int i=1;i<=maxPageNumber;i++){
				if(i == pageNumber){
					sb.append("<option value='"+i+"' selected>"+i+"</option>");
				}else{
					sb.append("<option value='"+i+"'>"+i+"</option>");
				}
			}
			sb.append("</select>");
		}else{
			sb.append("<input type=\"text\" name=\"pageNumber\" value=\""+pageNumber+"\" size=\"5\" class=\"textbox\">");
		}
		sb.append("\u9875");
		sb.append("<span style=\"cursor:pointer\" onclick=\"return "+nameSpace+"go();\">go</span>");
		return sb.toString();
	}
	
	/* (non-Javadoc)
	 * @see com.zuyue.pager.impl.Pager#toHTML(java.lang.String)
	 */
	public String toHTML(String nameSpace) {		
		
		StringBuffer sb = new StringBuffer();
		
		try {
			sb.append(bulidForemostPageHtml(nameSpace));  	//首页
			sb.append(bulidPreviousPageHtml(nameSpace));	//上一页			
			sb.append(bulidMiddleHtml(nameSpace));			//构建中间部分按钮的HTML
			sb.append(buildDynamicHtml(nameSpace));
			sb.append(bulidNextPageHtml(nameSpace));  		//下一页
			sb.append(bulidFinalPageHtml(nameSpace));     	//末页
			sb.append(buildNumInfoHtml());                
		} catch(Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
	
	
	/* (non-Javadoc)
	 * @see com.zuyue.pager.impl.Pager#printOrderHTML(java.lang.String)
	 */
	public String printOrderHTML(String judgeColumn) {		
		
		StringBuffer sb = new StringBuffer();
		if(judgeColumn != null && judgeColumn.trim().length()> 0){
			if(judgeColumn.equals(this.getOrderType())){ 
	     		if(OrderType.asc == this.getOrderType()){
	     			sb.append("<img alt=\"升序\" src=\"/html/icons/upOrder.png\">");
	     		}else{
	     			sb.append("<img alt=\"降序\" src=\"/html/icons/downOrder.png\">");
	     		}
			}
		
		}
		return sb.toString();
	}
	
	
	
	/**
	 * 构建中间部分的页码显示的HTML
	 * @param nameSpace
	 * @return
	 */
	private String bulidMiddleHtml(String nameSpace) {
		StringBuffer sb = new StringBuffer();		
	
		if (isFirstPage()) {  //如果是第一页,则显示前7页
			final int loopMax = PAGE_NUM_SHOW_COUNT>= getLastPageNumber()? getLastPageNumber(): PAGE_NUM_SHOW_COUNT;
			for (int i=1;i<=loopMax;i++) {
				sb.append(bulidSpanByPageNum(nameSpace,i,String.valueOf(i)));
			}
		} else if (isLastPage()) {  //如果是最后一页,则显示前6页
			final int stratPageNum = getLastPageNumber()-6>0?getLastPageNumber()-6:1;
			for (int i=stratPageNum;i<=getLastPageNumber();i++) {
				sb.append(bulidSpanByPageNum(nameSpace,i,String.valueOf(i)));
			}
		} else {			
			if (getLastPageNumber()<=PAGE_NUM_SHOW_COUNT) {  //如果最大页数小于或等于需要展示的最大页总数,就全部显示
				for (int i=1;i<=getLastPageNumber();i++) {
					sb.append(bulidSpanByPageNum(nameSpace,i,String.valueOf(i)));
				}				
			} else {
				//----确保页面总共显示7页,所以当前页前面没有显示够3页就补到后面去
				final int needAddLastNumCount = 3-(currentPageNumber-1)<=0
											?0:3-(currentPageNumber-1);		  	
				final int needAddStartNumCount = 3-(getLastPageNumber()-currentPageNumber)<=0
											?0:3-(getLastPageNumber()-currentPageNumber);
				//---------------end
				
				final int startPageNum= currentPageNumber-needAddStartNumCount-3>0
										?currentPageNumber-needAddStartNumCount-3 : 1;  		//开始页数
				final int lastPageNum = getLastPageNumber()>currentPageNumber+3+needAddLastNumCount
											?currentPageNumber+3+needAddLastNumCount:getLastPageNumber();  	//结束页数
				
				for (int i=startPageNum;i<=currentPageNumber;i++) {//获取前面3页的按钮HTML
					sb.append(bulidSpanByPageNum(nameSpace,i,String.valueOf(i)));
				}
				for (int i=currentPageNumber+1;i<=lastPageNum;i++) {  //获取后面3页的按钮HTML
					sb.append(bulidSpanByPageNum(nameSpace,i,String.valueOf(i)));
				}
			}
			
		}
		return sb.toString();
	}
	
	/**
	 * 构建动态的HTML代码. 如果总页数大于10,就显示文本框,否则不显示
	 * @param nameSpace
	 * @return
	 */
	private String buildDynamicHtml(String nameSpace) {
		StringBuffer sb = new StringBuffer();
		
		//只有页数大于7页才显示下拉框
		if (getLastPageNumber()>PAGE_NUM_SHOW_COUNT) {
			sb.append(buildInputPageNumHtml(nameSpace));
			String javascript = "javascript:if(document.getElementById('"+nameSpace+"pageNumber').value>"+this.getLastPageNumber()+") {"
											+"	alert('超过最大页码数，请重新输入')"
											+"} else { "
											+"	"+nameSpace+"go(document.getElementById('"+nameSpace+"pageNumber').value)"
											+"}";
			sb.append("<a class='pageForward' onclick=\""+javascript+"\">&nbsp;\u8df3\u8f6c</a>");//跳转
		}
		return sb.toString();
	}
	/**
	 * 构建下拉框式的页码HTML
	 * @param nameSpace
	 * @return
	 */
	@SuppressWarnings("unused")
	private String buildSelectPageNumHtml(String nameSpace) {
		StringBuffer sb = new StringBuffer();
		
		sb.append("&nbsp;<select class='pageNumSelect' id=\""+nameSpace+"pageNumber\">");
		for(int i=1;i<=getLastPageNumber();i++){
			if(i == currentPageNumber){
				sb.append("<option class='font_style' value='"+i+"' style='background:#CFEBA5' selected><span class='font_style' >"+i+"</span></option>");
			}else{
				sb.append("<option value='"+i+"'><span class='font_style' >"+i+"</span></option>");
			}
		}
		sb.append("</select>&nbsp;");
		
		return sb.toString();
		
	}
	/**
	 * 构建input文本框的页码HTML
	 * @param nameSpace
	 * @return
	 */
	private String buildInputPageNumHtml(String nameSpace) {		
		
		return "&nbsp;<input class='pager_input_style' style='width:25px' type=\"text\" maxlength='4' "+restrictNumCode+" id=\""+nameSpace+"pageNumber\" value=\""+currentPageNumber+"\" />";
	}
	
	/**
	 * 获得 首页 的页面显示代码,如果是第一页就不显示
	 * @return 首页
	 */
	private String bulidForemostPageHtml(String nameSpace) {
		if (isFirstPage()) {
			return bulidDisableSpanHtml("\u7b2c\u4e00\u9875");
		}
		
		return bulidSpanByPageNum(nameSpace,1,"\u7b2c\u4e00\u9875","span_unselected span_size_long font_style");
	}

	
	/**
	 * 获得末页的描述,如果已经是最后一页了,则不显示
	 * @param nameSpace
	 * @return 末页
	 */
	private String bulidFinalPageHtml(String nameSpace) {		
		if (isLastPage()) {
			return bulidDisableSpanHtml("&nbsp;&nbsp;\u672b\u9875");
		}
		
		return bulidSpanByPageNum(nameSpace,getLastPageNumber(),"\u672b\u9875","span_unselected span_size_normal font_style");
	}
	
	/**
	 * 获得下一页按钮的HTML
	 * @param nameSpace
	 * @return
	 */
	private String bulidNextPageHtml(String nameSpace) {
		if (isLastPage()) {
			return "";
		}
		int reqPageNum = currentPageNumber+1;
		return bulidSpanByPageNum(nameSpace,reqPageNum,"\u4e0b\u4e00\u9875","span_unselected span_size_long font_style");
	}
	
	/**
	 * 获得上一页按钮的HTML
	 * @param nameSpace
	 * @return
	 */
	private String bulidPreviousPageHtml(String nameSpace) {
		if (isFirstPage()) {
			return "";
		}
		int reqPageNum = currentPageNumber-1;

		return bulidSpanByPageNum(nameSpace,reqPageNum,"\u4e0a\u4e00\u9875","span_unselected span_size_long font_style");
	}
	/**
	 * 获得页数不能点击的HTML代码
	 * @param pageNum
	 * @return <span>display</span>
	 */
	private String bulidDisableSpanHtml(String displayName) {
		return "<span class='font_style'>" + displayName + "</span>&nbsp;";
	}
	
	/**
	 * 获得页面页数显示的HTML代码
	 * @param nameSpace
	 * @param pageNum 
	 * @param displayName 显示名称
	 * @return
	 */
	private String bulidSpanByPageNum(String nameSpace,Integer pageNum,String displayName) {	
		if (pageNum == currentPageNumber) {
			return bulidSpanByPageNum(nameSpace,pageNum,pageNum.toString(),"span_selected span_size_short font_style");
		}
		return bulidSpanByPageNum(nameSpace,pageNum,pageNum.toString(),"span_unselected span_size_short font_style");
	}
	/**
	 * 获得页面页数显示的HTML代码
	 * @param nameSpace
	 * @param pageNum
	 * @param displayName
	 * @param classStyleNames 样式的名称
	 * @return
	 */
	private String bulidSpanByPageNum(String nameSpace,Integer pageNum,String displayName,String classStyleNames) {
		if (pageNum == currentPageNumber) {
			return "<SPAN id='span_grid' class='"+classStyleNames+"'>"+displayName+"</SPAN>";
		}
		return "<SPAN id='span_grid' class='"+classStyleNames+"' onclick="+nameSpace+"go('"+pageNum+"')>"+displayName+"</SPAN>";
	}
	
	private String buildNumInfoHtml() {
		return "<span class='font_style'>&nbsp;&nbsp;\u5171 <span class='span_page_num'> "+getLastPageNumber()+" </span>\u9875 <span class='span_page_num'> "+getTotalElements()+" </span>\u6761\u8bb0\u5f55</span>";  //共  页  条记录
	}
}
