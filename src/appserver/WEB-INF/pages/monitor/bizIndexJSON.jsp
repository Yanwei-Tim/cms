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
			<json:property name="name" value="${item.name}" />
			<json:property name="linkName" value="${item.linkName}" />
			<json:property name="code" value="${item.code}" />
			<json:property name="exchModel" value="${item.exchModel}" />
			<json:property name="recordCount" value="${item.recordCount}" />
			<json:property name="dataVolume" value="${item.dataVolume}" />
			<json:property name="alertCount" value="${item.alertCount}" />
			<json:property name="linkCount" value="${item.linkCount}" />
			<json:property name="responseTime" value="${item.responseTime}" />
            <json:property name="runStatus" value="${item.runStatus}" />
		</json:object>
	</json:array>
</json:object>
