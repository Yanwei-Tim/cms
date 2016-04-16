<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<json:array var="item" items="${users}">
	<json:object>
		<json:property name="userName" value="${item.userName}" />
		<json:property name="name" value="${item.name}" />
	</json:object>
</json:array>
