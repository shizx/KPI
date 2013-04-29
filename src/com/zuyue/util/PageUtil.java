package com.zuyue.util;


public class PageUtil {
	/**
	 * 获取总页数
	 * @param totalElements :总记录数
	 * @param pageSize		:每页显示的条数
	 * @return
	 */
	public static int getCountPage(int totalElements,int pageSize) {		
		return (totalElements-1)/pageSize+1;
	}

}
