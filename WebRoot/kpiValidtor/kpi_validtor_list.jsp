<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>添加指标验证信息</title>
	<script type="text/javascript">
		function doAdd() {
			document.location = "forwardAdd!forwardAdd.action?kpiInfo.kpiId=${kpiInfo.kpiId}";
		}
		
		function doEdit(formulaId) {
			document.location = "forwardEdit!forwardEdit.action?kpiInfo.kpiId=${kpiInfo.kpiId}&kpiValidateFormula.formulaId=" + formulaId;
		}
		
		function doDelete(formulaId) {
			if (confirm("确定要删除该记录吗?")) {
				document.location = "delete!delete.action?kpiInfo.kpiId=${kpiInfo.kpiId}&kpiValidateFormula.formulaId=" + formulaId;
			}			
		}
	</script>
  </head>
  
  <body>
  	<div tt.impl="WAMenu" tt.noback=true>
	    <span name="添加验证公式" img="/img/xp/xls.gif" event="doAdd()"></span>
	    <span name="返回" img="/img/save.gif" event='__back()'></span>
	</div>
	
	<!-- 
    <table width="100%" borderColor="#bbbbbb" style="border-collapse: collapse;" border="1" cellPadding="3" name="resultLister">
		<TR style="BACKGROUND-COLOR: #e4e8f8" align="center">
			<TD class=xtitle colSpan=6><font style="font-weight: bold;">${kpiInfo.name }</font>指标-验证公式</TD>
		</TR>
	</table>
	 -->
	
	<div style="width: 100%;text-align: center;color:blue;font-size: 14px;margin-bottom: 5px;">${kpiInfo.name }指标-验证公式</div>
	
	<table class="zuyue-table">
	    <tr>
	        <td>
		        <input type="checkbox" id="checkAll"/>
		        <label for="checkAll">全选</label>
	       </td>
	        <td>指标验证公式</td>
	        <td>提示语</td>
	        <td align="center">操作</td>
	    </tr>
	
	    <s:iterator value="formulaValidatorList">
	        <tr>	           
	            <td>
	            	${formulaId }
	            </td>
	            <td>
					${formula }
				</td>
	            <td>
	            	${tips }
	            </td>	
	            <td align="center">
	            	<a href="####" onclick="doEdit('${formulaId }')">修改</a>
	            	<a href="####" onclick="doDelete('${formulaId }')">删除</a>
	            </td>	
	        </tr>
	    </s:iterator>
	</table>
  </body>
</html>
