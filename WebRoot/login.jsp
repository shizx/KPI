<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="common/common.jsp"%>
<html>
<head>
<title>指标统计管理系统</title>
<meta http-equiv="Content-Type" content="text/html;">
<!--Fireworks CS3 Dreamweaver CS3 target.  Created Wed Feb 04 16:55:03 GMT+0800 2009-->
<script src="<%=request.getContextPath()%>/js/login/JuiceLogin.js" ></script>
<script language="JavaScript">
<!--
function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}
function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
function doLogin() {
    var username = __get("username").value;
    var passwordShadow =__get("passwordShadow").value;
    if ((username==null || ""==username) && (passwordShadow==null || ""==passwordShadow)) {
        alert("邮箱账号和密码是必须的");
        return;
    }
    if (username==null || ""==username) {
        alert("邮箱账号是必须的");
        return;
    }
    if (passwordShadow==null || ""==passwordShadow) {
        alert("用户密码是必须的");
        return;
    }
    document.forms[0].submit();
}

function doGoLogin() {
    if (event.keyCode==13) {
        doLogin();
    }
}

function myInit() {
    MM_findObj("passwordShadow").focus();
}
function doLoginReset() {
   __get("username").value = "";
   __get("passwordShadow").value = "";
   __get("username").focus();
}

$(function(){
	if ("${errors.loginError}") {
		alert("${errors.loginError}");
	}
	
	myInit();
});

//-->
</script>
</head>

<body bgcolor="#999999" scroll="no" onLoad="MM_preloadImages('img/login/login_r6_c3_f2.jpg','img/login/login_r6_c5_f2.jpg')" style="margin:0px; padding:0px;">

<form name=loginForm method=post action="login.action">
<table border="0" cellpadding="0" cellspacing="0" height="100%" width="100%"><tr><td valign="middle">
<table border="0" cellpadding="0" cellspacing="0" width="1007" align="center">
<!-- fwtable fwsrc="login.png" fwpage="Page 1" fwbase="login.jpg" fwstyle="Dreamweaver" fwdocid = "742308039" fwnested="0" -->
  <tr>
   <td><img src="img/login/spacer.gif" width="772" height="1" border="0" alt=""></td>
   <td><img src="img/login/spacer.gif" width="11" height="1" border="0" alt=""></td>
   <td><img src="img/login/spacer.gif" width="52" height="1" border="0" alt=""></td>
   <td><img src="img/login/spacer.gif" width="6" height="1" border="0" alt=""></td>
   <td><img src="img/login/spacer.gif" width="52" height="1" border="0" alt=""></td>
   <td><img src="img/login/spacer.gif" width="8" height="1" border="0" alt=""></td>
   <td><img src="img/login/spacer.gif" width="1" height="1" border="0" alt=""></td>
   <td><img src="img/login/spacer.gif" width="105" height="1" border="0" alt=""></td>
   <td><img src="img/login/spacer.gif" width="1" height="1" border="0" alt=""></td>
  </tr>

  <tr>
   <td colspan="8"><img name="login_r1_c1" src="img/login/login_r1_c1.jpg" width="1007" height="381" border="0" alt=""></td>
   <td><img src="img/login/spacer.gif" width="1" height="381" border="0" alt=""></td>
  </tr>
  <tr>
   <td rowspan="6"><img name="login_r2_c1" src="img/login/login_r2_c1.jpg" width="772" height="219" border="0" alt=""></td>
   <td colspan="6" style="background-color:#ffffff"><input type="text" name="username" size="12" id="src" tt.comment=用户名 tt.require=true class=notnull value="admin"  style="width:128px;border:1px solid black;height:18px;"></td>
   <td rowspan="6"><img name="login_r2_c8" src="img/login/login_r2_c8.jpg" width="105" height="219" border="0" alt=""></td>
   <td><img src="img/login/spacer.gif" width="1" height="20" border="0" alt=""></td>
  </tr>
  <tr>
   <td colspan="6"><img name="login_r3_c2" src="img/login/login_r3_c2.jpg" width="130" height="11" border="0" alt=""></td>
   <td><img src="img/login/spacer.gif" width="1" height="11" border="0" alt=""></td>
  </tr>
  <tr>
   <td colspan="5" style="background-color:#ffffff"><input onkeypress=doGoLogin() type="password" name="passwordShadow" tt.require=true class=notnull id="pwd" size="12" style="width:128px;border:1px solid black;height:18px" tt.comment="密码" value=""></td>
   <td rowspan="4"><img name="login_r4_c7" src="img/login/login_r4_c7.jpg" width="1" height="188" border="0" alt=""></td>
   <td><img src="img/login/spacer.gif" width="1" height="20" border="0" alt=""></td>
  </tr>
  <tr>
   <td colspan="5"><img name="login_r5_c2" src="img/login/login_r5_c2.jpg" width="129" height="8" border="0" alt=""></td>
   <td><img src="img/login/spacer.gif" width="1" height="8" border="0" alt=""></td>
  </tr>
  <tr>
   <td rowspan="2"><img name="login_r6_c2" src="img/login/login_r6_c2.jpg" width="11" height="160" border="0" alt=""></td>
   <td><a href="#" onMouseOut="MM_swapImgRestore();" onMouseOver="MM_swapImage('login_r6_c3','','img/login/login_r6_c3_f2.jpg',1);" onclick='doLogin()'><img name="login_r6_c3" src="img/login/login_r6_c3.jpg" width="52" height="23" border="0" alt=""></a></td>
   <td rowspan="2"><img name="login_r6_c4" src="img/login/login_r6_c4.jpg" width="6" height="160" border="0" alt=""></td>
   <td><a href="#" onMouseOut="MM_swapImgRestore();" onclick="doLoginReset()" onMouseOver="MM_swapImage('login_r6_c5','','img/login/login_r6_c5_f2.jpg',1);"><img name="login_r6_c5" src="img/login/login_r6_c5.jpg" width="52" height="23" border="0" alt=""></a></td>
   <td rowspan="2"><img name="login_r6_c6" src="img/login/login_r6_c6.jpg" width="8" height="160" border="0" alt=""></td>
   <td><img src="img/login/spacer.gif" width="1" height="23" border="0" alt=""></td>
  </tr>
  <tr>
   <td><img name="login_r7_c3" src="img/login/login_r7_c3.jpg" width="52" height="137" border="0" alt=""></td>
   <td><img name="login_r7_c5" src="img/login/login_r7_c5.jpg" width="52" height="137" border="0" alt=""></td>
   <td><img src="img/login/spacer.gif" width="1" height="137" border="0" alt=""></td>
  </tr>
</table>
    </td></tr></table>
</form>
</body>
</html>
