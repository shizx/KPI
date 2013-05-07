package com.zuyue.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 *<p>Title: </p>
 *
 *<p>Description: </p>
 *
 *<p>Copyright: Copyright (c) 2010</p>
 *
 *<p>Company:昆明逐月科技有限公司</p>
 *
 *<p>Author shizx</p>
 *
 *<p>Email:shizhuxiong@gmail.com </p>
 *
 *<p>Version :1.0</p>
 *
 *<p>date:2013-5-6 下午11:29:52</p>
 */
public class AjaxUtil {
	
	/**
	 * 往客户端输出HTML
	 * @param response
	 * @param html
	 */
	public static void writHtmlToResponse(HttpServletResponse response,String html) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(html);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

