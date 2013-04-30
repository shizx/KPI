<%@ page import="com.zuyue.consts.SelectConst" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp"  %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加指标</title>
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


					<form name="addPkiForm" id="addUserForm" method="post"	action="kpiManager!add">
						<input type="hidden" name="isNew" value="${isNew }" />
						<input type="hidden" name="kpiInfo.kpiId" value="${kpiInfo.kpiId }" />
						<table width="100%">
							<col class=P_LB>
							<col class=P_NL>
							<tr>
								<td>
                                   	 排序号：
								</td>
								<td>
                                    <input type=text name="kpiInfo.kpiOrder" class="ip_text notnull"
                                           tt.require=true tt.checktype=int maxlength=5 value="<s:property value="kpiInfo.kpiOrder"/>" tt.comment="排序号">
								</td>
							</tr>
							
							<tr>
								<td>
									指标名称：
								</td>
								<td>
									<input type=text name="kpiInfo.name" class="ip_text notnull"
										tt.require=true maxlength=50 value="<s:property value="kpiInfo.name"/>" tt.comment="指标名称">
								</td>
							</tr>

							<tr>
								<td>
									计量单位：
								</td>
								<td>
									<input type=text name="kpiInfo.unit" class="ip_text notnull"
										tt.require=true maxlength=100 value="<s:property value="kpiInfo.unit"/>" tt.comment="计量单位">
								</td>
							</tr>

                            <tr>
                                <td>
                                                                                                  目的：
                                </td>
                                <td>
                                    <textarea name="kpiInfo.aim" class="ip_textarea"
                                              tt.require=true maxlength=200 tt.comment="目的" style=""><s:property value="kpiInfo.aim"/></textarea>
                                </td>
                            </tr>
							<tr>
								<td>
									定义：
								</td>
								<td>
                                    <textarea name="kpiInfo.definition" class="ip_textarea"
                                              tt.require=true maxlength=200 tt.comment="定义" style=""><s:property value="kpiInfo.definition"/></textarea>
								</td>
							</tr>
							
							<tr>
								<td>
									公式：
								</td>
								<td>
                                    <textarea name="kpiInfo.formulaDes" class="ip_textarea"
                                              tt.require=true maxlength=200 tt.comment="公式" style=""><s:property value="kpiInfo.formulaDes"/></textarea>
								</td>
							</tr>

                            <tr>
                                <td>
                                   	 维度：
                                </td>
                                <td>
                                    <z:selection selectionMap="<%=SelectConst.KPIINFO_DIMENSION %>"
                                                 selectionName="kpiInfo.dimension"
                                                 selectedValue="${kpiInfo.dimension}" />
                                </td>
                            </tr>

                            <tr>
                                <td>
                                                                                                类型：
                                </td>
                                <td>
                                	<z:selection selectionMap="<%=SelectConst.KPIINFO_TYPE %>"
                                                 selectionName="kpiInfo.type"
                                                 selectedValue="${kpiInfo.type}" />
                                </td>
                            </tr>

                            <tr>
                                <td>
                                                                                                 指标录入方式：
                                </td>
                                <td>
                                    <%--<input type="radio" value="N" id="char" name="kpiInfo.isRequiredNum"><label for="char">文字</label>--%>
                                    <%--<input type="radio" value="Y" id="number" name="kpiInfo.isRequiredNum"><label for="number">数字</label>--%>
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

