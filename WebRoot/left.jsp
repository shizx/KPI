<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/common.jsp" %>
<html>
<head>
	<title>左边的菜单</title>
    <style type="text/css">
        #TopMenu {
            font-size: 14px;
            background-image: url( 'img/skin/inner_bg.gif' );
            width: 165;
            height: 33;
            color: #165eb4;
            font-weight: bold;
            padding-left: 32px;
            padding-top: 9px;
            cursor: pointer;
        }

        #div1 {
            font-size: 14px;
            padding-left: 32px;
            padding-top: 9px;
            cursor: pointer;
        }

        A:visited {
            COLOR: white;
            TEXT-DECORATION: none
        }

        A:hover {
            COLOR: yellow;
            TEXT-DECORATION: none
        }

        A:active {
            COLOR: red;
            TEXT-DECORATION: none
        }

        A:link {
            color:white;
        }

		.hidden {
			display: none;		
		}
    </style>
    
    <script type="text/javascript">
    
        function renderMenu() {
            var ts = __getObjs("TopMenu");
            for (var i = 0; i < ts.length; i++) {
                ts[i].attachEvent("onclick", doHandleMenu);
            }
        }

        function doHandleMenu() {
            var d = event.srcElement;
            d = __getReal(d, "id", "TopMenu");
            var dParent = d.parentElement;
            var dChildContainer = dParent.children[1];
            dChildContainer.style.display = dChildContainer.style.display == "none" ? "" : "none";
        }
        window.attachEvent("onload", renderMenu);
    </script>    
</head>
<body style="background-image:url('img/skin/inner_02b.gif');background-repeat:repeat-y;" style="padding:0px;margin:0px">
<div style="background-image:url('img/lmb.gif');background-repeat:no-repeat;width:100%;height:100%">
<div style="background-position:bottom left;background-image:url('img/skin/inner_02.gif');background-repeat:no-repeat;height:100%;padding-left:16px;">
    <div>
        <span id="TopMenu">系统管理</span>

        <div style="display:;" id="div1">
            <a href="main.jsp" target="right"><img src="img/menu/home.gif" border=0 align="absmiddle">&nbsp;系统首页</a>
            <br><br>
            
            <a href="orgManager/orgList!list.action" target="right" class="menu">
            	<img src="img/menu/department.gif" border=0 align="absmiddle">&nbsp;公司管理
            </a>
            <br><br>
            
            <a href="userManager/userList!list.action" target="right" class="menu">
            	<img src="img/menu/ann.gif" border=0 align="absmiddle">&nbsp;用户管理
            </a>
            <br><br>
            
            <!--
            <a href="schedule/schedule_list.jsp" target="right"><img src="img/menu/7.gif" border=0 align="absmiddle">&nbsp;权限管理</a>
            <br><br>            
             
            <a href="config/tbrokerLog_list.jsp" target="right"><img src="img/menu/ann.gif" border=0 align="absmiddle">&nbsp;操作日志</a>
            <br><br>
             -->
        </div>

    </div>
    <div>
        <span id="TopMenu">指标信息管理</span>

        <div style="display:none" id="div1">
            <a href="kpiManager/kpiManager!kpiList.action" target="right"><img src="img/menu/oo1.gif" border=0 align="absmiddle">&nbsp;指标管理</a>
            <br><br>
            
            
            <a href="kpiValueManager/kpiValueManager!kpiValueList.action" target="right"><img src="img/menu/oo2.gif" border=0 align="absmiddle">&nbsp;指标值管理</a>
            <br><br>
            
            <!-- 
            <a href="config/itsm_server_list.jsp" target="right"><img src="img/menu/oo3.gif" border=0 align="absmiddle">&nbsp;运维服务器</a>
            <br><br>
             -->
        </div>
    </div>
    <div>
    	<span id="TopMenu" class="">报表管理</span>
        <div style="display:none" id="div1">
        	<a href="reportManager/completeStatus!completeStatus.action" target="right"><img src="img/menu/oo1.gif" border=0 align="absmiddle">&nbsp;综合计划指标完成情况报表</a>
            <br><br>
            
            <a href="reportManager/statusAnalysis!statusAnalysis.action" target="right"><img src="img/menu/oo2.gif" border=0 align="absmiddle">&nbsp;关键指标完成情况分析报表</a>
            <br><br>
            
            <a href="reportManager/generateCompositeReport!generateCompositeReport.action" target="right"><img src="img/menu/oo2.gif" border=0 align="absmiddle">&nbsp;生成综合报告</a>
            <br><br>
        </div>
    </div>
    <div class="hidden">
        <span id="TopMenu">日志管理</span>
        <div style="display:none" id="div1">
        </div>
    </div>

</div>
</div>
</body>

</html>