package com.zuyue.consts;

import java.util.HashMap;
import java.util.Map;

/**
 *<p>Title: 定义INPUT的一些常量</p>
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
 *<p>date:2013-5-1 下午3:26:46</p>
 */
public class InputConst {
	
	public static final String RADIO = "radio";
	
	public static final String CHECKBOX = "checkbox";
	
	public static final String CHECKED = "checked='checked'";
	
	public static final String YES = "Y";
	
	public static final String NO = "N";
	
	/**
     *指标信息中的是否只能输入字母
     */
	public static Map<String,String> KPI_IS_REQUIRED_NUM = new HashMap<String,String>();
	static {
		KPI_IS_REQUIRED_NUM.put(YES, "数字");
		KPI_IS_REQUIRED_NUM.put(NO, "文字");
	}

}

