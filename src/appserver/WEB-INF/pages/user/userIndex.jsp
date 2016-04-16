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
			<json:property name="userName" value="${item.userName}" />
			<json:property name="name" value="${item.name}" />
			<json:property name="sex" value="${item.sex}" />
			<json:property name="phone" value="${item.phone}" />
			<json:property name="status" value="${item.status}" />
			<json:property name="depart" value="${item.depart}" />
			<json:property name="title" value="${item.title}" />
			<json:property name="email" value="${item.email}" />
			<fmt:formatDate var="time" value="${item.createdTime}" pattern="yyyy-MM-dd HH:mm" />
			<json:property name="time" value="${time}" />
		</json:object>
	</json:array>
</json:object>
