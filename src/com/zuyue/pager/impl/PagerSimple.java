package com.zuyue.pager.impl;

import com.zuyue.pager.AbsPager;
import com.zuyue.util.Validator;

public class PagerSimple<T> extends AbsPager<T> {
	
	
	@Override
	public String toHTML(String namespace) {
		
		//如果没有没有设置元素，或者元素的size为0
		if (Validator.isEmpty(getElements())) {
			return emptyHTML();
		}
		
		return buildHtml(namespace);
	}
	
	/**
	 * 为空的描述信息
	 * @return
	 */
	private String emptyHTML() {
		return "<TBODY class=data_list_body><TR><TD colSpan='111'>没有记录</TD></TR></TBODY>";
	}

	/**
	 * 构建分页详情
	 * @param nameSpace
	 * @return
	 */
	private String buildHtml(String nameSpace) {
		final int currentPageNum = getCurrentPageNumber();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<TFOOT class='data_list_foot'>");
		sb.append("<TR>");
		sb.append("<TD style='VISIBILITY: visible' id='tableJumpPage' colSpan='111' align='right'>");
		
		sb.append("第<SPAN style='COLOR: red'> ")
			.append(getCurrentPageNumber())
			.append(" </SPAN>页 共<SPAN style='COLOR: red'> "+getLastPageNumber()+" </SPAN> 页  ");
		sb.append("共 <SPAN style='COLOR: red'>"+getTotalElements()+"</SPAN> 条记录 ");
		
		if (currentPageNum != 1) {
			sb.append("<A style='CURSOR: hand' href='####'  onclick="+nameSpace+"go('1')>首页</A> ");		
			sb.append("<A style='CURSOR: hand' href='####' onclick="+nameSpace+"go('"+(currentPageNum-1)+"')>上一页</A> ");
		}
		
		if (hasNextPage()) {
			sb.append("<A style='CURSOR: hand' href='####'  onclick="+nameSpace+"go('"+(currentPageNum+1)+"')>下一页</A> ");
			sb.append("<A style='CURSOR: hand' href='####'  onclick="+nameSpace+"go('"+(getLastPageNumber())+"')>末页</A> ");
		}
		sb.append("<SPAN>到<INPUT style='TEXT-ALIGN: center; WIDTH: 30px' "+ restrictNumCode +" id='"+nameSpace+"pageNumber' class='ip_text'>页 ");
		
		final String javascript = "javascript:if(document.getElementById('"+nameSpace+"pageNumber').value>"+this.getLastPageNumber()+") {"
							+"	alert('超过最大页码数，请重新输入')"
							+"} else if (document.getElementById('"+nameSpace+"pageNumber').value < 1) {"
							+ " alert('页数不能小于1');"
							+"} else { "
							+"	"+nameSpace+"go(document.getElementById('"+nameSpace+"pageNumber').value)"
							+"}";
		
		sb.append("<A href='####' onclick=\""+javascript+"\">GO</A></SPAN>");
		
		sb.append("</TD>");
		sb.append("</TR>");
		sb.append("</TFOOT>");
		
		return sb.toString();		
	}
}
