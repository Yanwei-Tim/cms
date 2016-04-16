<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<json:array var="item" items="${model.list}">
	<json:object>
		<json:property name="idTerminal" value="${item}" />
	</json:object>
</json:array>
