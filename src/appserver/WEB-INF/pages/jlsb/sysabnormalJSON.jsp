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
			<fmt:formatDate var="happenTime" value="${item.happenTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			<json:property name="happenTime" value="${happenTime}" />
			<fmt:formatDate var="treatTime" value="${item.treatTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			<json:property name="treatTime" value="${treatTime}" />
			<fmt:formatDate var="writeTime" value="${item.writeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			<json:property name="writeTime" value="${writeTime}" />
			<json:property name="idSystem" value="${item.idSystem}" />
			<json:property name="idAlertMatter" value="${item.idAlertMatter}" />
			<json:property name="abNormalTypeCode" value="${item.abNormalTypeCode}" />
			<json:property name="connectObjectCode" value="${item.connectObjectCode}" />
			<json:property name="exceptionDesc" value="${item.exceptionDesc}" />
			<json:property name="treadResult" value="${item.treadResult}" />
		</json:object>
	</json:array>
</json:object>
