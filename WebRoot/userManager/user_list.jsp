<%@ page language="java" pageEncoding="UTF-8"%>
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
		
		function go($reqNum) {
			alert($reqNum)
		}
	</script>
	</head>

	<body>
		<div tt.impl="WAMenu" tt.noback=true>
			<span name="添加用户" img="/img/xp/xls.gif" event="doAdd()"></span>
		</div>
		<table class="zuyue-table">
			<tr>
				<td>邮箱账号</td>
				<td>用户名称</td>
				<td>手机号</td>
				<td>QQ</td>
			</tr>
			
			<s:iterator value="pager.elements">
				<tr>
					<td><s:property value="email" /></td>
					<td><s:property value="userName" /></td>
					<td><s:property value="mobile" /></td>
					<td><s:property value="qq" /></td>
				</tr>
			</s:iterator>

			<z:pager pager="${pager }"/>			
		</table>
	</body>
</html>