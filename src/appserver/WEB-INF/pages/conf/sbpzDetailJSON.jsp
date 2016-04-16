<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<json:array>
	<json:object>
		<json:property name="id" value="${model.entry.id}" />
		<json:property name="ip" value="${model.entry.ip}" />
		<json:property name="port" value="${model.entry.port}" />
		<json:property name="level" value="${model.entry.level}" />
		<json:property name="username" value="${model.entry.username}" />
		<json:property name="password" value="${model.entry.password}" />
		<json:property name="hour" value="${model.entry.hour}" />
		<json:property name="minute" value="${model.entry.minute}" />
		<json:property name="second" value="${model.entry.second}" />
	</json:object>
</json:array>