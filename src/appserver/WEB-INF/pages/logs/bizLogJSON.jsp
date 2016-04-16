<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<json:object>
	<json:property name="amount" value="${ps.allResultsAmount}" />
	<json:array var="item" name="results" items="${ps.results}">
		<json:object>
			<json:property name="id" value="${item.id}" />
			<fmt:formatDate var="logTime" value="${item.logTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			<json:property name="logTime" value="${logTime}" />
			<json:property name="level" value="${item.level}" />
			<json:property name="businessName" value="${item.businessName}" />
			<json:property name="platformName" value="${item.platformName}" />
			<json:property name="auditInfo" value="${item.auditInfo}" />
			<json:property name="sourceIp" value="${item.sourceIp}" />
			<json:property name="sourcePort" value="${item.sourcePort}" />
			<json:property name="destIp" value="${item.destIp}" />
			<json:property name="destPort" value="${item.destPort}" />
			<json:property name="userName" value="${item.userName}" />
			<json:property name="operation" value="${item.operation}" />
		</json:object>
	</json:array>
</json:object>
