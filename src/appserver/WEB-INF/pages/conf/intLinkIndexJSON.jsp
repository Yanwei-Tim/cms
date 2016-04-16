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
			<json:property name="linkName" value="${item.linkName}" />
			<json:property name="jrdx" value="${item.jrdx}" />
			<json:property name="exchangeMode" value="${item.exchangeMode}" />
			<json:property name="linkBandWidth" value="${item.linkBandWidth}" />
			<json:property name="fwUsed" value="${item.fwUsed}" />
			<json:property name="secGatewayUsed" value="${item.secGatewayUsed}" />
			<json:property name="gapUsed" value="${item.gapUsed}" />
			<json:property name="vpnUsed" value="${item.vpnUsed}" />
			<json:property name="otherSecurity" value="${item.otherSecurity}" />
		</json:object>
	</json:array>
</json:object>
