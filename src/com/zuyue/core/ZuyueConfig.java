package com.zuyue.core;

import java.util.ArrayList;
import java.util.List;

import com.zuyue.formula.SymbolModel;

/**
 *<p>Title: 系统配置信息 </p>
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
 *<p>date:2013-5-5 下午8:38:59</p>
 */
public class ZuyueConfig {
	
	/**
	 * 公式中的符号模型集合
	 */
	private static List<SymbolModel> symbols = new ArrayList<SymbolModel>();
	public static void addSymbol(SymbolModel sm) {
		symbols.add(sm);
	}
	
	public static List<SymbolModel> getSymbols() {
		return symbols;
	}
}

