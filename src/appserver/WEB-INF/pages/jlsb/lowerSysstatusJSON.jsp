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
			<fmt:formatDate var="startTime" value="${item.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			<json:property name="startTime" value="${startTime}" />
			<fmt:formatDate var="endTime" value="${item.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			<json:property name="endTime" value="${endTime}" />
			<fmt:formatDate var="writeTime" value="${item.writeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			<json:property name="writeTime" value="${writeTime}" />
			<json:property name="idSystem" value="${item.idSystem}" />
			<json:property name="access" value="${item.access}" />
			<json:property name="terminalNum" value="${item.terminalNum}" />
			<json:property name="inFlux" value="${item.inFlux}" />
			<json:property name="outFlux" value="${item.outFlux}" />
			<json:property name="accessSum" value="${item.accessSum}" />
			<json:property name="bizSum" value="${item.bizSum}" />
		</json:object>
	</json:array>
</json:object>