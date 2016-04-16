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
			<json:property name="equName" value="${item.equName}" />
			<json:property name="netStation" value="${item.netStation}" />
			<json:property name="isKeyDevice" value="${item.isKeyDevice}" />  <!--是否核心设备-->
			<json:property name="equTypeCode" value="${item.equTypeCode}" />  <!--设备类型-->
			<json:property name="equIconCode" value="${item.equIconCode}" />
			<json:property name="monitorUsed" value="${item.monitorUsed}" />
			<json:property name="ip" value="${item.ip}" />
			<json:property name="otherIp" value="${item.otherIp}" />
			<json:property name="mac" value="${item.mac}" />
			<json:property name="subnetMask" value="${item.subnetMask}" />
			<json:property name="intOrExt" value="${item.inrOrExt}" />
			<json:property name="equStation" value="${item.equStation}" />
			<json:property name="equInfo" value="${item.equInfo}" />
			<json:property name="equSysConfig" value="${item.equSysConfig}" />
			<json:property name="equManagerDepart" value="${item.equManagerDepart}" />
			<json:property name="manufacturer" value="${item.manufacturer}" />
			<json:property name="model" value="${item.model}" />
			<json:property name="provider" value="${item.provider}" />
			<json:property name="equPhone" value="${item.equPhone}" />
			<json:property name="otherPhone" value="${item.otherPhone}" />
            <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.buyDay}" var="buyDay" />
			<json:property name="buyDay" value="${buyDay}" />
            <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.unrepairDay}" var="unrepairDay" />
			<json:property name="unrepairDay" value="${unrepairDay}" />
			<json:property name="equOidName" value="${item.equOidName}" />
			<json:property name="equPort" value="${item.equPort}" />
			<json:property name="equSnmpPwd" value="${item.equSnmpPwd}" />
			<json:property name="equSnmpver" value="${item.equSnmpver}" />
			<json:property name="topologyStation" value="${item.topologyStation}" />
		</json:object>
	</json:array>
</json:object>
