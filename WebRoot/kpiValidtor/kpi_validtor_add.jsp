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


					<form name="addValidotrForm" id="addValidotrForm" method="post"	action="add!add.action">
						<input type="hidden" name="isNew" value="${isNew }" />
						<input type="hidden" name="kpiInfo.kpiId" value="${kpiInfo.kpiId }" />
						<input type="hidden" name="kpiValidateFormula.formulaId" value="${kpiValidateFormula.formulaId }" />
						
						<table width="100%">
							<col class=P_LB>
							<col class=P_NL>
							<tr>
								<td>
                                   	验证公式：
								</td>
								<td>
                                    <textarea name="kpiValidateFormula.formula" 
                                    	class="ip_textarea notnull"
                                        tt.require=true maxlength=200 
                                        tt.comment="验证公式"
                                        ><s:property value="kpiValidateFormula.formula"/></textarea>
								</td>
							</tr>
							
							<tr>
								<td>
									提示语：
								</td>
								<td>
									<input type=text name="kpiValidateFormula.tips" class="ip_text"
										maxlength=100 value="<s:property value="kpiValidateFormula.tips"/>" tt.comment="提示语">
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

