package com.zuyue.test.fomula;

import org.junit.Test;

import com.zuyue.exception.BusinessException;
import com.zuyue.formula.FormulaClassGenarater;
import com.zuyue.formula.IFormula;

public class TestFormulaClassGenarater {

	@Test
	public void testGenarateFormulaClass() {
		//(5*0)-5 该表成 (doubles[0] * doubles[1] - doubles[2])
		//输入公式的格式为 ($ * $ 
		FormulaClassGenarater genarater = new FormulaClassGenarater("C_9999xx", "($*$)-$*$");
		
		try {
			genarater.genarateFormulaClass();
			
			IFormula formula = genarater.getFormulaClassInstance();
			
			double d1 = 2.0;
			double d2 = 2.0;
			double d3 = 3.0;
			double d4 = 4.0;
			
			System.out.println(formula.comput(d1,d2,d3,d4));
			
			
		} catch (BusinessException e) {
			e.printStackTrace();
		} 
	}

}
