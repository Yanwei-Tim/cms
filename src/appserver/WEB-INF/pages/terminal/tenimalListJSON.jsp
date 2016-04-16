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
			<json:property name="hour" value="${item.hour}" />
			<fmt:formatDate var="certcreateDate" value="${item.certcreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			<json:property name="certcreateDate" value="${certcreateDate}" />
			<fmt:formatDate var="loginTime" value="${item.loginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			<json:property name="loginTime" value="${loginTime}" />
			<fmt:formatDate var="cardCertidDate" value="${item.cardCertidDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			<json:property name="cardCertidDate" value="${cardCertidDate}" />
			<json:property name="cardId" value="${item.cardId}" />
			<c:forEach var="itemCard" items="${model.cardType}">
				<c:if test="${itemCard.id==item.cardType}"><json:property name="cardType" value="${itemCard.name}" /></c:if>
			</c:forEach>
			<json:property name="cardModel" value="${item.cardModel}" />
			<json:property name="cardVersion" value="${item.cardVersion}" />
			<json:property name="userDepart" value="${item.userDepart}" />
			<json:property name="userZone" value="${item.userZone}" />
			<json:property name="policeId" value="${item.policeId}" />
			<json:property name="policeName" value="${item.policeName}" />
			<json:property name="certId" value="${item.certId}" />
			<c:choose>
				<c:when test="${item.blockType=='A'}">
				<json:property name="blockType" value="无阻断" />
				</c:when>
				<c:when test="${item.blockType=='B'}">
				<json:property name="blockType" value="临时阻断" />
				</c:when>
				<c:when test="${item.blockType=='C'}">
				<json:property name="blockType" value="长久阻断" />
				</c:when>
			</c:choose>
			
					<c:choose>
				<c:when test="${item.status=='1'}">
				<json:property name="status" value="在线" />
				</c:when>
				<c:when test="${item.status=='0'}">
				<json:property name="status" value="离线" />
				</c:when>
			</c:choose>
		</json:object>
	</json:array>
</json:object>
