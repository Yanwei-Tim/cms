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
			<fmt:formatDate var="operateTime" value="${item.operateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			<json:property name="operateTime" value="${operateTime}" />
			<json:property name="operater" value="${item.operater}" />
			<json:property name="cardId" value="${item.cardId}" />
			<c:forEach var="itemCard" items="${model.cardType}">
				<c:if test="${itemCard.code==item.cardType}"><json:property name="cardType" value="${itemCard.name}" /></c:if>
			</c:forEach>
			<json:property name="policeId" value="${item.policeId}" />
			<json:property name="policeName" value="${item.policeName}" />
			<json:property name="certId" value="${item.certId}" />
			<c:choose>
				<c:when test="${item.operateTpye=='A'}">
					<json:property name="operateTpye" value="截屏" />
				</c:when>
				<c:when test="${item.operateTpye=='B'}">
					<json:property name="operateTpye" value="阻断" />
				</c:when>
				<c:when test="${item.operateTpye=='C'}">
					<json:property name="operateTpye" value="吊销证书" />
				</c:when>
				<c:when test="${item.operateTpye=='D'}">
					<json:property name="operateTpye" value="恢复接入" />
				</c:when>
			</c:choose>
		</json:object>
	</json:array>
</json:object>
