<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>    
    	<title>选择指标公式</title>
		<script type="text/javascript">
			function doClick(symobol) {
				alert(symobol)
			}
			
			function searchKpiInfo(pageNum) {
				
				pageNum = pageNum||1;
				var kpiName = document.getElementById("kpiInfo.name").value;
				if ($.trim(kpiName)=="") {
					//alert("指标名称不能为空");
					//return;
				}
				
				doLoadKpiInfos(kpiName,pageNum);
			}
			
			function doClickKpi(id) {
				alert(id)
			}
			
			$(function(){
				searchKpiInfo();
			});
			
			function doLoadKpiInfos(kpiName,pageNum) {
				
				kpiName = kpiName || "";
				$.post("<%=contextPath%>/json/KpiData!kpiData.action", { "kpiName": kpiName ,"pageNum":pageNum,"t":new Date().getTime()},
					function(data){	
						$("#trHtml").html(data.trHtml);
						$("#pagerHtml").html(data.pagerHtml);
					}, "json");
			}
			
			function go(pageNum) {
				searchKpiInfo(pageNum)
			}
		</script>
		<style type="text/css">
			.symbolInput {
				width: 30px;
				height: 25px;
				cursor: pointer;
			}
		</style>
  	</head>
  	<body>

	<div>
		<table>
			<tr>
				<z:symbol className="symbolInput" clickFunction="doClick"></z:symbol>
			</tr>
		</table>		
	</div>
	
	<div>
		<table>
			<tr>
				<td><input type="text" class="ip_text" name="kpiInfo.name" id="kpiInfo.name" value="${kpiInfo.name }"></td>
				<td><input type="button" value="搜索指标" onclick="searchKpiInfo()" ></td>
			</tr>
		</table>		
	</div>
	
	<div style="min-height: 50p0x;">
		<table style="BORDER: 1px solid;BORDER-COLOR: #cccccc;width: 440px;">
			
			<tfoot id="trHtml">
			</tfoot>
			<tfoot id="pagerHtml">			
			</tfoot>
		</table>
	</div>
	
	<div>
		<textarea class="ip_textarea"></textarea>
	</div>
	
  	</body>
</html>