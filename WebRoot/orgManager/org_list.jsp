<%@page import="com.zuyue.pager.impl.PagerSimple"%>
<%@page import="com.zuyue.pager.Pager"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>公司管理</title>
	
	<script type="text/javascript">
		function doAdd() {
			//__open("org_add.jsp", "width=700;height=450;center=true;fresh=true");
			
			document.location = "forwardAdd!forwardAdd.action";
		}
		
		function orgListgo($reqNum) {
			alert($reqNum)
		}
	</script>
	</head>

	<body>
		<div tt.impl="WAMenu" tt.noback=true>
			<span name="添加公司" img="/img/xp/xls.gif" event="doAdd()"></span>
		</div>
		<table class="zuyue-table">
			<tr>
				<td>序号</td><td>名称</td><td>父级机构</td>
			</tr>
			
			<s:iterator value="pager.elements">
				<tr>
					<td><s:property value="seqNo" /></td>
					<td><s:property value="orgName" /></td>
					<td><s:property value="parentOrgInfo.orgName" /></td>
				</tr>
			</s:iterator>

			<z:pager pager="${pager }" nameSpace="orgList" />
		</table>
	</body>
</html>
