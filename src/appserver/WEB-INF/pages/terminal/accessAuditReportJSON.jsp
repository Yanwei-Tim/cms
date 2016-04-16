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
			<fmt:formatDate var="reportDate" value="${item.reportDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			<json:property name="reportDate" value="${reportDate}" />
			<c:forEach var="itemCard" items="${model.cardType}">
				<c:if test="${itemCard.code==item.cardType}"><json:property name="cardType" value="${itemCard.name}" /></c:if>
			</c:forEach>
			<json:property name="busName" value="${item.busName}" />
			<json:property name="userId" value="${item.userId}" />
			<json:property name="flux" value="${item.flux}" />
			<json:property name="count" value="${item.count}" />
			<c:forEach var="itemSysClass" items="${model.sysClass}">
				<c:if test="${itemSysClass.id==item.userDepart}"><json:property name="userDepart" value="${itemSysClass.sysClassName}" /></c:if>
			</c:forEach>
		</json:object>
	</json:array>
</json:object>
