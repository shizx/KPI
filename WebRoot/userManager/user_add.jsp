<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp"  %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加用户</title>
<script>
</script>
</head>
<body>
<div tt.impl="WAMenu">
<span name="保存" img="/img/save.gif" event='__post()'></span>
<span name="返回" img="/img/save.gif" event='__back()'></span>
</div>

	<div tt.impl=WABody>
		<table width=100%>
			<tr>
				<td class=formTableContainer>

					<form name="addUserForm" id="addUserForm" method="post"	action="userManager!add">

						<table width="100%">
							<col class=P_LB>
							<col class=P_NL>
							<tr>
								<td>
									所属公司：
								</td>
								<td>
									<select name="user.userInfo.orgId" tt.comment="所属公司" tt.required="true">
										<option></option>
										<s:iterator value="orgInfos">
											<option value="<s:property value="orgId"/>"><s:property value="orgName"/></option>
										</s:iterator>
									</select>									
								</td>
							</tr>
							
							<tr>
								<td>
									用户名：
								</td>
								<td>
									<input type=text name="user.userName" class="ip_text notnull"
										tt.require=true maxlength=50 value="<s:property value="user.userName"/>" tt.comment="用户名">
								</td>
							</tr>

							<tr>
								<td>
									邮箱账号：
								</td>
								<td>
									<input type=text name="user.email" class="ip_text notnull"
										tt.require=true maxlength=100 value="<s:property value="user.email"/>" tt.comment="邮箱账号">
								</td>
							</tr>

							<tr>
								<td>
									密码
								</td>
								<td>
									<input type="password"" name="user.password" class="ip_text"
										tt.require=false maxlength=30 value="<s:property value="user.password"/>" tt.comment="密码">
								</td>
							</tr>
							
							<tr>
								<td>
									确认密码
								</td>
								<td>
									<input type="password"" name="configPassword" class="ip_text"
										tt.require=false maxlength=30 value="" tt.comment="确认密码">
								</td>
							</tr>

							<tr>
								<td>
									手机号码
								</td>
								<td>
									<input type=text name="user.mobile" class="ip_text"
										maxlength=12 value="99" tt.checktype=int tt.comment="手机号码">
								</td>
							</tr>
							
							<tr>
								<td>
									QQ号码
								</td>
								<td>
									<input type=text name="user.qq" class="ip_text"
										maxlength=12 value="99" tt.checktype=int tt.comment="QQ号码">
								</td>
							</tr>

						</table>

					</form>

				</td>
			</tr>
		</table>
	</div>

	</body>
</html>

