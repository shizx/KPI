package com.zuyue.formula;

public class FormulaTestImpl implements IFormula {

	public Object comput(Double... objects) {
		
		
		return (objects[0] * objects[1] - objects[2]);
	}

}
