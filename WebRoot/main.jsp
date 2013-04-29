<%@page import="com.zuyue.system.Environment"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=utf-8"%>

<%
	String _c = request.getContextPath();
    //计算内存的状态
    Runtime rt = Runtime.getRuntime();
    long totalMemory = rt.totalMemory();
    long freeMemory = rt.freeMemory();
    long maxMemory = rt.maxMemory();

    long mbTotalMemory = totalMemory/1024/1024;
    long mbFreeMemory = freeMemory/1024/1024;
    long mbMaxMemory = maxMemory/1024/1024;
    long mbUseMemory =mbTotalMemory - mbFreeMemory;

    float intUse = Float.parseFloat(String.valueOf(mbUseMemory));
    float intTotal = Float.parseFloat(String.valueOf(mbTotalMemory));

    int usePre = Math.round((intUse / intTotal)*100);
    int freePre = 100-usePre;
%>
<%
    //获得系统的信息
    ServletContext sc = pageContext.getServletContext();
    Properties sysInfos = System.getProperties();
    String sysTime = "";
    SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    Date currentDate = new Date();
    sysTime = sf.format(currentDate);
    String startTime = sf.format(Environment.getApplicationStartTime());
    long useTime = currentDate.getTime() - Environment.getApplicationStartTime().getTime();
    long useTimeHour = useTime / (1000 * 60 * 60);
    long useTimeMins = (useTime % (1000 * 60 * 60))/(1000*60);

%>
<html>
<head>
<title>远程服务器</title>
<link href="<%=request.getContextPath()%>/css/base.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/jquery-1.6.min.js"></script>
</head>
<body>

<div>
<table width=100%>
<tr>
<td class=formTableContainer>

    <table name="resultLister"   border=1 width=100% style='border-collapse:collapse;' borderColor="#BBBBBB" cellpadding=3>

    <tr style='background-color:#E4E8F8'><td colspan=6 class=xtitle><img src=img/g1.gif align=absmiddle>系 统 信 息</td></tr>
    <tr><td>使用内存</td><td><%=mbUseMemory%> MB</td>
    <td>空闲内存</td><td><%=mbFreeMemory%> MB</td>
    <td>当前总数</td><td><%=mbTotalMemory%> MB</td>
    </tr>
    <tr><td>图形描述</td><td colspan=5><table cellspacing=0 cellpadding=0><tr><td><img src=img/r1.gif width=<%=usePre*2%> height=18 align=absmiddle><img src=img/g.gif width=<%=freePre*2%> height=18 align=absmiddle> <b><%=freePre%>%空闲</b> &nbsp;&nbsp<a href="####" onclick="doExecService('system/action/gc.jsp')"><img src="<%=request.getContextPath()%>/img/intra/trash.gif" align=absmiddle border=0>回收</a>(时间会较长)</td></tr></table></td></tr>

    <tr>
    <td>Java安装目录</td><td><%=sysInfos.get("java.home")%></td>
    <td>Java版本</td><td><%=sysInfos.get("java.vm.version")%></td>
    <td>操作系统</td><td><%=sysInfos.get("os.name")%> <%=sysInfos.get("os.version")%></td>
    </tr>


    <tr>
    <td>系统时间</td><td><%=sysTime%></td>
    <td>应用服务器</td><td><%=sc.getServerInfo()%></td>
    <td>当前工作目录</td><td><%=sysInfos.get("user.dir")%></td>
    </tr>
    <tr>
    <td>启动时间</td><td><%=startTime%></td>
    <td>至今运行时长</td><td><%=useTimeHour%>小时<%=useTimeMins%>分钟</td>
    </tr>
    </table>
</td>
</tr>

</table>
</div>

</body>
<script type="text/javascript">
    function doExecService($url) {
        if (!confirm("你确认要执行该操作吗？")) {
            return;
        }
        
		$.ajax({
				url : $url,
				success : function() {
					window.location = '<%=_c%>/main.jsp';
				}
			});
    }
</script>

</html>

