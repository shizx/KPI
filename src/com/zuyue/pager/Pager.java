package com.zuyue.pager;

import java.util.List;

public interface Pager<T> {
	
	// 排序方式
	public enum OrderType{
		asc, desc
	}

	List<T> getElements();

	void setElements(List<T> elements);

	int getCurrentPageNumber();

	void setCurrentPageNumber(int currentPageNumber);

	int getPageSize();

	void setPageSize(int pageSize);

	int getTotalElements();

	void setTotalElements(int totalElements);

	boolean isFirstPage();

	boolean isLastPage();

	boolean hasNextPage();

	boolean hasPreviousPage();

	int getLastPageNumber();

	String getHTML(String nameSpace);

	String toHTML(String nameSpace);

	String printOrderHTML(String judgeColumn);
	
	public String getProperty();

	public void setProperty(String property);

	public String getKeyword();

	public void setKeyword(String keyword);

	public String getOrderBy();

	public void setOrderBy(String orderBy);
	
	public OrderType getOrderType();

	public void setOrderType(OrderType orderType);

}