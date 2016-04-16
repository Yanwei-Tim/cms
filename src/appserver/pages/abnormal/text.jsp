<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 13-3-18
  Time: 下午2:41
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
    String sb="";
    sb+="{";
    sb+="success:true,";
    sb+="root:6,rows:[{";
    sb+="name:'Jul07',type:245000,type:100000,type:100000,type:100000,type:100000,type:100000,type:100000";
    sb+="},{";
    sb+="name:'Aug07',type:205000,type:200000";
    sb+="},{";
    sb+="name:'Sep07',type:115000,type:300000";
    sb+="},{";
    sb+="name:'Oct07',type:255000,type:400000";
    sb+="},{";
    sb+="name:'Nov07',type:345000,type:200000";
    sb+="},{";
    sb+="name:'Dec07',type:315000,type:100000";
    sb+="}]";
    sb+="}";
    out.print(sb);
%>