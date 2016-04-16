<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json"%>
<json:array>
	<json:object>
		<json:property name="conf_type" value="${model.conf_type}" />
		<json:property name="conf_time" value="${model.conf_time}" />
		<json:property name="conf_day" value="${model.conf_day}" />
		<json:property name="conf_time2" value="${model.conf_time2}" />
		<json:property name="conf_month_day" value="${model.conf_month_day}" />
		<json:property name="conf_time3" value="${model.conf_time3}" />
		<json:property name="conf_file_path" value="${model.conf_file_path}" />
		<json:property name="conf_enabled" value="${model.conf_enabled}" />
		<json:property name="log_type" value="${model.log_type}" />
		<json:property name="log_time" value="${model.log_time}" />
		<json:property name="log_day" value="${model.log_day}" />
		<json:property name="log_time2" value="${model.log_time2}" />
		<json:property name="log_month_day" value="${model.log_month_day}" />
		<json:property name="log_time3" value="${model.log_time3}" />
	</json:object>
</json:array>