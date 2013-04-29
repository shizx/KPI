package com.zuyue.tag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 构建下拉框选项框，不支持有重复值的value。比如有两个选项的值都是“0”
 */
public class DisplayTextTag extends TagSupport {

	private static final long serialVersionUID = -7205821673435612531L;


	/**
     *所有选择项的map对象
     */
	private Map<String,String> selectionMap = new HashMap<String, String>();

    
    /**
     * 已经选择的下拉框
     */
    private String selectedValue;

    	
	@Override
	public int doStartTag() throws JspException {
		
		try {
            if (selectionMap != null) {
                
            	Iterator<String> it = selectionMap.keySet().iterator();
            	while (it.hasNext()) {
            		final String value = it.next();
            		if (selectedValue.equals(value)) {
            			pageContext.getOut().write(selectionMap.get(value));
            		}
            	}
            	
			    
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Tag.SKIP_PAGE;
	}


    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }
    
    /**
	 * @return the selectionMap
	 */
	public Map<String, String> getSelectionMap() {
		return selectionMap;
	}

	/**
	 * @param selectionMap the selectionMap to set
	 */
	public void setSelectionMap(Map<String, String> selectionMap) {
		this.selectionMap = selectionMap;
	}
}
