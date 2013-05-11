package com.zuyue.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.zuyue.model.KPIInfo;
import com.zuyue.pager.Pager;
import com.zuyue.service.KpiService;
import com.zuyue.util.AjaxUtil;
import com.zuyue.util.ParamUtil;

/**
 *<p>Title:返回json数据 </p>
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
 *<p>date:2013-5-6 下午10:35:59</p>
 */
public class JsonAction extends BaseAction {

	private static final long serialVersionUID = -3410717372250760027L;
	
	@Resource
    private KpiService kpiService;

  
	public void kpiData() {
		
		HttpServletRequest request = getRequest();
		
		String kpiName = ParamUtil.get(request, "kpiName","");
		
		int pageNum = ParamUtil.get(request, "pageNum", 1);
		
        Pager<KPIInfo> pager = kpiService.findPagerByKpiName(kpiName,pageNum);
        
        List<KPIInfo> infos = pager.getElements();
        
        final int size = infos.size();
        final int TR_SIZE = 5;
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<size;i++) {
        	
        	if (i%TR_SIZE==0) {
        		sb.append("<tr>");
        	}
        	
        	KPIInfo info = infos.get(i);
        	sb.append("<td><input type='button' value='"+info.getName()+"' onclick=\"doClickKpi('"+info.getKpiId()+"','"+info.getName()+"')\" ></td>");
        	
        	if (i!=0&&(i+1)%TR_SIZE==0) {
        		sb.append("</tr>");
        	}        	
        }
        
        for (int index=0;index<(TR_SIZE-size%TR_SIZE);index++) {
        	sb.append("<td></td>");        	
        }
        
        if (size%TR_SIZE!=0) {
        	sb.append("</tr>");
        }
        
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("pagerHtml", pager.toHTML(""));
        jsonObj.put("trHtml", sb.toString());
        
        AjaxUtil.writHtmlToResponse(ServletActionContext.getResponse(),jsonObj.toString());
    }
    
  
}

