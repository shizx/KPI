<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/common.jsp"  %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加子公司</title>
<script>
</script>
</head>
<body>
<div tt.impl="WAMenu">
<span name="保存" img="/img/save.gif" event='__post("addOrgForm")'></span>
<span name="返回" img="/img/save.gif" event='__back()'></span>
</div>

		<div tt.impl=WABody>
			<table width=100%>
				<tr>
					<td class=formTableContainer>

						<form name="addOrgForm" id="addOrgForm" method="post"
							action="orgAdd!add" enctype="multipart/form-data">

							<table width="100%">
								<col class=P_LB>
								<col class=P_NL>

								<tr>
									<td>
										所属公司：
									</td>
									<td>
										<select name="orgInfo.parentOrgInfo.orgId" tt.comment="所属公司" tt.required="true">
											<option></option>
											<s:iterator value="orgInfos">
												<option value="<s:property value="orgId"/>"><s:property value="orgName"/></option>
											</s:iterator>
										</select>
									</td>
								</tr>

								<tr>
									<td>
										公司名称：
									</td>
									<td>
										<input type=text name="orgInfo.orgName" class="ip_text notnull"
											tt.require=true maxlength=100 value="1" tt.comment="公司名称">
									</td>
								</tr>

								<tr>
									<td>
										公司图片
									</td>
									<td>
										<input type=file name="companyImage" class="ip_text"
											tt.require=false maxlength=30 value="2" tt.comment="公司图片">
									</td>
								</tr>

								<tr>
									<td>
										序号
									</td>
									<td>
										<input type=text name="orgInfo.seqNo" class="ip_text"
											maxlength=10 value="99" tt.checktype=int tt.comment="序号">
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

