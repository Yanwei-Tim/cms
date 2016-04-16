<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<json:array>
	<json:object>
        <json:property name="dbip" value="${model.dbip}" />
        <json:property name="dbport" value="${model.dbport}" />
        <json:property name="dbname" value="${model.dbname}" />
        <json:property name="username" value="${model.username}" />
        <json:property name="password" value="${model.password}" />
        <json:property name="dbUsed" value="${model.dbUsed}" />
		<json:property name="backup_dbip" value="${model.backup_dbip}" />
		<json:property name="backup_dbport" value="${model.backup_dbport}" />
		<json:property name="backup_dbname" value="${model.backup_dbname}" />
		<json:property name="backup_username" value="${model.backup_username}" />
		<json:property name="backup_password" value="${model.backup_password}" />
	</json:object>
</json:array>