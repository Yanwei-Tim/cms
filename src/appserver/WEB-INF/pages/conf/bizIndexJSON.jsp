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
			<json:property name="businessName" value="${item.businessName}" />
			<json:property name="businessDesc" value="${item.businessDesc}" />
			<json:property name="businessDepart" value="${item.businessDepartDistrict.orgname}" />
			<json:property name="businessDepartId" value="${item.businessDepartDistrict.orgcode}" />
			<json:property name="businessCode" value="${item.businessCode.description}" />
			<json:property name="businessCodeId" value="${item.businessCode.code}" />
			<json:property name="linkName" value="${item.linkName}" />
			<json:property name="businessAdmin" value="${item.businessAdmin}" />

			<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.registerDate}" var="registerDate" />
			<json:property name="registerDate" value="${registerDate}" />
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.authDate}" var="authDate" />
			<json:property name="authDate" value="${authDate}" />


            <json:property name="adminPhone" value="${item.adminPhone}" />
            <json:property name="adminEmail" value="${item.adminEmail}" />
            <json:property name="adminOtherPhone" value="${item.adminOtherPhone}" />
            <json:property name="businessExchModel" value="${item.businessExchModel.description}" />
            <json:property name="businessExchModelId" value="${item.businessExchModel.code}" />
            <json:property name="authDepart" value="${item.authDepartDistrict.orgname}" />
            <json:property name="authDepartId" value="${item.authDepartDistrict.orgcode}" />
            <json:property name="authSerial" value="${item.authSerial}" />
            <json:property name="auth_material" value="${item.authMaterialFileName}" />
            <json:property name="isRealtime" value="${item.isRealtime}" />
            <json:property name="dayDataVolume" value="${item.dayDataVolume}" />
            <json:property name="isApproved" value="${item.isApproved}" />
            <json:property name="approvedDepart" value="${item.approvedDepartDistrict.orgname}" />
            <json:property name="approvedDepartId" value="${item.approvedDepartDistrict.orgcode}" />
            <json:property name="enablereport" value="${item.enablereport}" />
            <json:property name="tp_graph" value="${item.tpGraphFileName}" />
            <json:property name="businessProtocol" value="${item.businessProtocol}" />
            <json:property name="protocolDesc" value="${item.protocolDesc}" />
            <json:property name="sourceIpRange" value="${item.sourceIpRange}" />
            <json:property name="destIpRange" value="${item.destIpRange}" />
            <json:property name="sourcePortRange" value="${item.sourcePortRange}" />
            <json:property name="destPortRange" value="${item.destPortRange}" />
            <json:property name="useDepartAddress" value="${item.useDepartAddress}" />
            <json:property name="useDepartType" value="${item.useDepartType.departTypeDesc}" />
            <json:property name="useDepartTypeId" value="${item.useDepartType.departCode}" />
            <json:property name="useDepartAddress" value="${item.useDepartAddress}" />
            <json:property name="useDepartAdminPhone" value="${item.useDepartAdminPhone}" />
            <json:property name="useDepartAdminName" value="${item.useDepartAdminName}" />
            <json:property name="useDepartAdminEmail" value="${item.useDepartAdminEmail}" />
            <json:property name="useDepartAdminOhterPhone" value="${item.useDepartAdminOhterPhone}" />
            <json:property name="terminalAmount" value="${item.terminalAmount}" />
            <json:property name="userAmount" value="${item.userAmount}" />
            <json:property name="useDepart" value="${item.useDepartDistrict.orgname}" />
            <json:property name="useDepartId" value="${item.useDepartDistrict.orgcode}" />
            <json:property name="exchProtocol" value="${item.exchProtocol.protocolName}" />
            <json:property name="exchProtocolCode" value="${item.exchProtocol.protocolCode}" />
            <json:property name="exchangeDirection" value="${item.exchangeDirection.codeDesc}" />
            <json:property name="exchangeDirectionCode" value="${item.exchangeDirection.code}" />
		</json:object>
	</json:array>
</json:object>
