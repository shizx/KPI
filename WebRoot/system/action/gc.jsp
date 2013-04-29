<%@page import="com.zuyue.exception.BusinessException"%>
<%@ page contentType="text/xml;charset=UTF-8" %>
<%
    try {
        System.gc();
    } catch (Exception ex) {
        throw new BusinessException("垃圾回收失败", ex);
    } 
%>
<?xml version="1.0" encoding="UTF-8"?>
<xuice>
</xuice>