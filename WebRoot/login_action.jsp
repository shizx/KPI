<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.zuyue.model.TUser"%>
<%@page import="com.zuyue.exception.BusinessException"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<script type="text/javascript"></script>
<%
    String userName = request.getParameter("username");
    String password = request.getParameter("passwordShadow");


    try {
        if (StringUtils.isBlank(password) || StringUtils.isBlank(userName)) {
                throw new BusinessException("登录数据不合法");
        }
        
        TUser user = null;
        
        //BrokerUser user = null;
        //if ("admin".equals(userName) && "pass".equals(password)) {
        //    user = new BrokerUser();
        //    user.setIp(request.getRemoteAddr());
        //    user.setName("admin");
        //    user.setPrimaryKey("admin");
        //}
        //if (user == null) {
        //    throw new Exception("用户名或密码错误");
        //}

        //session.setAttribute(BrokerConst.SESSION_USER_KEY, user);       
        response.sendRedirect("index.jsp");
    }catch (BusinessException e) {
        out.println("<script>alert('登陆数据不合法')</script>");    
    }catch (Exception ex){
        out.println("<script>alert('用户名或密码错误')</script>");
        out.println("<script>window.location='login.jsp'</script>");
    }
%>