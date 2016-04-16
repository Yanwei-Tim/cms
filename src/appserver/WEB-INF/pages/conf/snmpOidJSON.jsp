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
            <json:property name="name" value="${item.name}" />
            <json:property name="type" value="${item.type}" />
            <json:property name="company" value="${item.company}" />
            <json:property name="cpuuse" value="${item.cpuuse}" />
            <json:property name="disktotal" value="${item.disktotal}" />
            <json:property name="diskuse" value="${item.diskuse}" />
            <json:property name="memtotal" value="${item.memtotal}" />
            <json:property name="memuse" value="${item.memuse}" />
            <json:property name="curconn" value="${item.curconn}" />
            <json:property name="snmpver" value="${item.snmpver}" />
        </json:object>
    </json:array>
</json:object>
