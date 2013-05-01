package com.zuyue.tag;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import com.zuyue.consts.InputConst;
import com.zuyue.consts.StringPool;
import com.zuyue.util.Validator;

/**
 *<p>Title:构建checkbox或者radio的HTML </p>
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
 *<p>date:2013-5-1 下午3:37:34</p>
 */
public class ChoiceTag extends TagSupport {
	
	private static final long serialVersionUID = -4151583106754596071L;

	/**
    *所有选择项的map对象
    */
	private Map<String,String> choiceMap = new HashMap<String, String>();

   /**
    * 选项的名称
    */
   private String choiceName;

   /**
    * 已经选择的值，如果是checkbox，则先回用,分离出所有的值
    */
   private String choicedValue;
   
   /**
    * 默认值
    */
   private String defalutValue;
	

	/**
    * 类型，默认为radio
    */
	private String type = InputConst.RADIO;
	
	
	/**
	 * 获得需要选中的值
	 * @return
	 */
	private String getNeedChoicedValue() {
		if (Validator.isNotNull(choicedValue)) {
			return choicedValue;
		}
		
		if (Validator.isNotNull(defalutValue)) {
			return defalutValue;
		}
		
		return "";
	}
	
	@Override
	public int doStartTag() throws JspException {
		
		try {
            if (choiceMap != null) {
                StringBuilder sb = new StringBuilder();
               
                Iterator<String> it = choiceMap.keySet().iterator();
                while (it.hasNext()) {
                	final String id = UUID.randomUUID().toString();
                	
                    final String value = it.next();
                    final String text = choiceMap.get(value);
                    
                    boolean checked = false;
                    
                    //如果是单选框，且选择的值与当前的一样。则选中它
                    if (InputConst.RADIO.equals(getType())
                    		&& value.equals(getNeedChoicedValue())) {
                    	checked = true;
                    } else {
                    	
                    	String[] selectedValues = getNeedChoicedValue().split(StringPool.COMMA);
                    	
                    	for (String selectedValue : selectedValues) {
                    		if (value.equals(selectedValue)) {
                    			checked = true;
                    		}
                    	}
                    }
                    
                    sb.append("<input type='"+getType()+"' value='"+value+"' id='"+id+"' name='"+getChoiceName()+"'");
                    
                    if (checked) {
                    	sb.append(InputConst.CHECKED);
                    }
                    
                    sb.append("><label for="+id+">"+text+"</label>");
                }

                sb.append("</SELECT>");
			    pageContext.getOut().write(sb.toString());
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Tag.EVAL_PAGE;
	}

	/**
	 * @return 所有选择项的map对象
	 */
	public Map<String, String> getChoiceMap() {
		return choiceMap;
	}

	/**
	 * @param 所有选择项的map对象
	 */
	public void setChoiceMap(Map<String, String> choiceMap) {
		this.choiceMap = choiceMap;
	}

	/**
	 * @return 选项的名称
	 */
	public String getChoiceName() {
		return choiceName;
	}

	/**
	 * @param 选项的名称
	 */
	public void setChoiceName(String choiceName) {
		this.choiceName = choiceName;
	}

	/**
	 * @return 已经选择的值，如果是checkbox，则先回用,分离出所有的值
	 */
	public String getChoicedValue() {
		return choicedValue;
	}

	/**
	 * @param 已经选择的值，如果是checkbox，则先回用,分离出所有的值
	 */
	public void setChoicedValue(String choicedValue) {
		this.choicedValue = choicedValue;
	}

	/**
	 * @return 类型，默认为radio
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param 类型，默认为radio
	 */
	public void setType(String type) {
		this.type = type;
	}

	
	/**
	 * @return the defalutValue
	 */
	public String getDefalutValue() {
		return defalutValue;
	}

	/**
	 * @param defalutValue the defalutValue to set
	 */
	public void setDefalutValue(String defalutValue) {
		this.defalutValue = defalutValue;
	}
	

}

