<%@page import="com.zuyue.consts.SelectConst"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>指标管理</title>

    <script type="text/javascript">
        function doAdd() {
            //__open("org_add.jsp", "width=700;height=450;center=true;fresh=true");

            document.location = "forwardAdd!forwardAdd.action";
        }

        function go($reqNum) {
            alert($reqNum)
        }
        
        function doEdit(id) {
        	document.location = "forwardEdit!forwardEdit.action?kpiInfo.kpiId=" + id;
        }
        function doDelete(id) {
        	if (confirm("确定要删除该记录吗？")) {
        		document.location = "delete!delete.action?kpiInfo.kpiId=" + id;
        	}        	
        }
        
        function doAddValidatorFormula(id) {
        	document.location = "<%=contextPath%>/kpiValidtor/list!list.action?kpiInfo.kpiId=" + id;
        }
    </script>
</head>

<body>
<div tt.impl="WAMenu" tt.noback=true>
    <span name="添加指标" img="/img/xp/xls.gif" event="doAdd()"></span>
</div>
<table class="zuyue-table">
    <tr>
        <td>序号</td>
        <td>指标名称</td>
        <td>指标维度</td>
        <td>指标类别</td>
        <td>计量单位</td>
        <td>公式</td>
        <td>定义</td>
        <td>目的</td>
        <td align="center">操作</td>
    </tr>

    <s:iterator value="pager.elements">
        <tr>
            <td><s:property value="kpiOrder" /></td>
            <td><s:property value="name" /></td>
            <td>
            	<z:displayText 
            		selectionMap="<%=SelectConst.KPIINFO_DIMENSION %>"  
            		selectedValue="${dimension}" />
            </td>
            <td>
				<z:displayText 
            		selectionMap="<%=SelectConst.KPIINFO_TYPE %>"  
            		selectedValue="${type}" />
			</td>
            <td><s:property value="unit" /></td>
            <td>
            	<span title="${formulaDes }">
            		<z:shortenText text="${formulaDes }" length="18"/>
            	</span>
            </td>
            <td>
            	<span title="${definition }">
            		<z:shortenText text="${definition }" length="18"/>
            	</span>
            </td>
            <td>${aim }</td>

            <td align="center">
            	<a href="####" onclick="doEdit('${kpiId}')">修改</a>
            	<a href="####" onclick="doDelete('${kpiId}')">删除</a>
            	<a href="####" onclick="doAddValidatorFormula('${kpiId}')">查看验证公式</a>
            </td>

        </tr>
    </s:iterator>

    <z:pager pager="${pager }"/>
</table>
</body>
</html>