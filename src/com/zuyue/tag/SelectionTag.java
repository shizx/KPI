package com.zuyue.tag;


import org.apache.commons.lang3.StringUtils;

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
public class SelectionTag extends TagSupport {

    /**
     *所有选择项的map对象
     */
	private Map<String,String> selectionMap = new HashMap<String, String>();

    /**
     * 选择框的名称
     */
    private String selectionName;

    /**
     * 已经选择的下拉框
     */
    private String selectedValue;

    
	/**
     * 是否构建空选项框,默认为true
     */
	private boolean booBuildEmptyOption = true;




	@Override
	public int doStartTag() throws JspException {
		
		try {
            if (selectionMap != null) {
                StringBuilder sb = new StringBuilder();

                sb.append("<SELECT name='").append(getSelectionName()+"'>");
                if (booBuildEmptyOption) {
                    sb.append(buildOption("",""));
                }

                Iterator<String> it = selectionMap.keySet().iterator();
                while (it.hasNext()) {
                    String value = it.next();
                    String text = selectionMap.get(value);
                    sb.append(buildOption(text,value));
                }

                sb.append("</SELECT>");
			    pageContext.getOut().write(sb.toString());
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Tag.SKIP_PAGE;
	}

    /**
     * 构建select的选项
     * @param text
     * @param value
     * @return
     */
    private String buildOption(String text,String value) {
        if (StringUtils.isNotBlank(value) && value.equals(this.selectedValue)) {
            return "<OPTION value='"+value+"' selected='selected'>" + text + "</OPTION>";
        }
        return "<OPTION value='"+value+"'>" + text + "</OPTION>";
    }

	private static final long serialVersionUID = -925251914287095924L;

    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }

    public String getSelectionName() {
        return selectionName;
    }

    public void setSelectionName(String selectionName) {
        this.selectionName = selectionName;
    }

    public boolean isBooBuildEmptyOption() {
        return booBuildEmptyOption;
    }

    public void setBooBuildEmptyOption(boolean booBuildEmptyOption) {
        this.booBuildEmptyOption = booBuildEmptyOption;
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
