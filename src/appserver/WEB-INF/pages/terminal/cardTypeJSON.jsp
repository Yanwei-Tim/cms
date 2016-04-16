<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<json:object>
	<json:array var="item" name="list" items="${list}">
		<json:object>
			<json:property name="id" value="${item.id}" />
			<json:property name="name" value="${item.name}" />
			<json:property name="code" value="${item.code}" />
		</json:object>
	</json:array>
</json:object>
