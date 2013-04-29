package com.zuyue.test.fomula;

import org.junit.Test;

import com.zuyue.formula.FormulaTestImpl;
import com.zuyue.formula.IFormula;

public class TestIFormulaTestImpl {

	@Test
	public void testComput() {
		IFormula formula = new FormulaTestImpl();
		
		Object valueObj = formula.comput(1.0,2.0);
		
		System.out.println(valueObj);
	}

}
