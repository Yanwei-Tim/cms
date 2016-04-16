<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<json:object>
	<json:property name="amount" value="${model.ps.allResultsAmount}" />
	<json:array var="item" name="results" items="${model.ps.results}">
		<json:object>
			<c:forEach var="itemDistrict" items="${model.district}">
				<c:if test="${itemDistrict.id==item.userZone}">
					<json:property name="userZone" value="${itemDistrict.districtName}" />
				</c:if>
			</c:forEach>
			<json:property name="id" value="${item.id}" />
			<fmt:formatDate var="auditDate" value="${item.auditDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			<json:property name="auditDate" value="${auditDate}"/>
			<json:property name="accessUrl" value="${item.accessUrl}" />
			<json:property name="busName" value="${item.busName}" />
			<c:forEach var="itemCard" items="${model.cardType}">
				<c:if test="${itemCard.code==item.cardType}"><json:property name="cardType" value="${itemCard.name}" /></c:if>
			</c:forEach>
			<json:property name="count" value="${item.count}" />
			<json:property name="flux" value="${item.flux}" />
			<json:property name="policeNumber" value="${item.policeNumber}" />
			<json:property name="idTerminal" value="${item.idTerminal}" />
			<c:forEach var="itemSysClass" items="${model.sysClass}">
				<c:if test="${itemSysClass.id==item.userDepart}">
					<json:property name="userDepart" value="${itemSysClass.sysClassName}" />
				</c:if>
			</c:forEach>
			<json:property name="userId" value="${item.userId}" />
		</json:object>
	</json:array>
</json:object>