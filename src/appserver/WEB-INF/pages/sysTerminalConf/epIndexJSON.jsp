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
			<json:property name="terminalName" value="${item.terminalName}" />
			<json:property name="userName" value="${item.userName }"/>
			<json:property name="userId" value="${item.userId }"/>
			<json:property name="policeId" value="${item.policeId }"/>
			<json:property name="regTime" value="${item.regTime }"/>
			<json:property name="ifCancel" value="${item.ifCancel }"/>
		</json:object>
	</json:array>
</json:object>
