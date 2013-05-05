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
			
			function searchKpiInfo() {
				alert("查询指标")
			}
			
			function doClickKpi(id) {
				alert(id)
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
				<td><input type="text" class="ip_text" name="kpiInfo.name' value="${kpiInfo.name }"></td>
				<td><input type="button" value="搜索指标" onclick="searchKpiInfo()" ></td>
			</tr>
		</table>		
	</div>
	
	<div>
		<table style="BORDER: 1px solid;BORDER-COLOR: #cccccc;">
			<tr class="TBL_A">
				<td><input type="button" value="营业利润" onclick="doClickKpi('营业利润')" ></td>
				<td><input type="button" value="营业利润" onclick="doClickKpi('营业利润')" ></td>
				<td><input type="button" value="营业利润" onclick="doClickKpi('营业利润')" ></td>
				<td><input type="button" value="营业利润" onclick="doClickKpi('营业利润')" ></td>
				<td><input type="button" value="营业利润" onclick="doClickKpi('营业利润')" ></td>
			</tr>
			
			<tr class="TBL_B">
				<td><input type="button" value="营业利润" onclick="doClickKpi('营业利润')" ></td>
				<td><input type="button" value="营业利润" onclick="doClickKpi('营业利润')" ></td>
				<td><input type="button" value="营业利润" onclick="doClickKpi('营业利润')" ></td>
				<td><input type="button" value="营业利润" onclick="doClickKpi('营业利润')" ></td>
				<td><input type="button" value="营业利润" onclick="doClickKpi('营业利润')" ></td>
			</tr>
		</table>
	</div>
	
	<div>
		<textarea class="ip_textarea"></textarea>
	</div>
	
  	</body>
</html>