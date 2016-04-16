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
			<json:property name="equName" value="${item.equName}" />
			<json:property name="equType" value="${item.equType.typeName}" />
			<json:property name="equIconCode" value="${item.equIconCode}" />
			<json:property name="netStation" value="${item.netStation}" />
			<json:property name="monitorUsed" value="${item.monitorUsed}" />
			<json:property name="ip" value="${item.ip}" />
			<json:property name="runStatus" value="${item.runStatus}" />
		</json:object>
	</json:array>
</json:object>
