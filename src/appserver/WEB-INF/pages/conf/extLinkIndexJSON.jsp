<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<json:object>
	<json:property name="amount" value="${model.ps.allResultsAmount}" />
	<json:array var="item" name="results" items="${model.ps.results}">
		<json:object>
			<json:property name="id" value="${item.id}" />
			<json:property name="linkName" value="${item.linkName}" />
			<json:property name="linkType" value="${item.linkType}" />
			<json:property name="linkCorp" value="${item.linkCorp}" />
			<json:property name="linkSecurity" value="${item.linkSecurity}" />
			<json:property name="linkAmount" value="${item.linkAmount}" />
			<json:property name="linkBandWidth" value="${item.linkBandWidth}" />
			<json:property name="linkTypeCode" value="${item.linkType.linkTypeName}" />
		</json:object>
	</json:array>
</json:object>
