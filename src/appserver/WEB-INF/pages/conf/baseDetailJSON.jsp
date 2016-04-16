<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<json:array>
	<json:object>
		<json:property name="id" value="${model.entry.id}" />
		<json:property name="platformName" value="${model.entry.platformName}" />
		<json:property name="platformId" value="${model.entry.platformId}" />
		<json:property name="systemClass" value="${model.entry.systemClass}" />
		<json:property name="address" value="${model.entry.address}" />
		<json:property name="fzrName" value="${model.entry.fzrName}" />
		<json:property name="fzrPhone" value="${model.entry.fzrPhone}" />
		<json:property name="fzrEmail" value="${model.entry.fzrEmail}" />
		<json:property name="fzrOhterPhone" value="${model.entry.fzrOhterPhone}" />
		<json:property name="jksysIp" value="${model.entry.jksysIp}" />
		<json:property name="jksysMac" value="${model.entry.jksysMac}" />
		<json:property name="jsdw" value="${model.entry.jsdw}" />
		<json:property name="zycjdw" value="${model.entry.zycjdw}" />
		<c:set var="jsDay"><fmt:formatDate value='${model.entry.jsDay}' pattern='yyyy-MM-dd HH:mm:ss'/></c:set>
		<json:property name="jsDay" value="${jsDay}" />
		<json:property name="bmxy" value="${model.entry.bmxy}" />
		<json:property name="spdw" value="${model.entry.spdw}" />
		<c:set var="spDay"><fmt:formatDate value='${model.entry.spDay}' pattern='yyyy-MM-dd HH:mm:ss'/></c:set>
		<json:property name="spDay" value="${spDay}" />
		<json:property name="spph" value="${model.entry.spph}" />
		<json:property name="mainComp" value="${model.entry.mainComp}" />
		<json:property name="maintainerName" value="${model.entry.maintainerName}" />
		<json:property name="maintainerPhone" value="${model.entry.maintainerPhone}" />
		<json:property name="maintainerEmail" value="${model.entry.maintainerEmail}" />
		<json:property name="maintainerOhterPhone" value="${model.entry.maintainerOhterPhone}" />
		<json:property name="jsdwChildName" value="${model.entry.districtJsdw.orgname}" />
		<json:property name="spdwChildName" value="${model.entry.districtSpdw.orgname}" />
		<json:property name="mainCompChildName" value="${model.entry.districtMainComp.orgname}" />
		<json:property name="parentJsdw" value="${model.entry.parentJsdw}" />
		<json:property name="parentSpdw" value="${model.entry.parentSpdw}" />
		<json:property name="parentMainComp" value="${model.entry.parentMainComp}" />		
	</json:object>
</json:array>