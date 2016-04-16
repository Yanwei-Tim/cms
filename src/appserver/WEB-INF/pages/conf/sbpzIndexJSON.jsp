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
			<json:property name="level" value="${item.level}" />
			<json:property name="ip" value="${item.ip}" />
			<json:property name="port" value="${item.port}" />
			<json:property name="username" value="${item.username}" />
			<json:property name="password" value="${item.password}" />
			<json:property name="hour" value="${item.hour}" />
			<json:property name="minute" value="${item.minute}" />
			<json:property name="second" value="${item.second}" />
			<fmt:formatDate var="time" value="${item.createdTime}" pattern="yyyy-MM-dd HH:mm" />
			<json:property name="time" value="${time}" />
		</json:object>
	</json:array>
</json:object>
