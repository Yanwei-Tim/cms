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
			<json:property name="platformName" value="${item.platformName}" />
			<json:property name="platformIp" value="${item.platformIp}" />
			<json:property name="platformPort" value="${item.platformPort}" />
			<json:property name="isEncrypted" value="${item.isEncrypted}" />
			<json:property name="linkName" value="${item.linkName}" />
		</json:object>
	</json:array>
</json:object>
