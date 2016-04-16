<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<form id="editForm" name="editForm"
	action="<c:url value="/intLinkConf.do?m=update"/>" method="post">
<input type="hidden" name="id" value="${model.entry.id }" />
<table width="100%" border="0" cellspacing="5" cellpadding="5"
	style="padding: 15px;">
	<tr>
		<td align="right">链路名称：</td>
		<td><input type="text" name="linkName"     id="linkName"
			value="${model.entry.linkName }" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">接入对象：</td>
		<td><input type="text" name="jrdx" id="jrdx" value="${model.entry.jrdx }"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">业务交换方式：</td>
		<td><select name="exchangeMode" id="exchangeMode.info">
			<c:forEach var="item" items="${model.businessExchModels}">
				<option value="${item.description }"
					<c:if test="${item.description==model.item.exchangeMode}">selected</c:if>>${item.description}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">链路带宽（M）：</td>
		<td><input type="text" name="linkBandWidth"   id="linkBandWidth"
			value="${model.entry.linkBandWidth }" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">是否使用防火墙：</td>
		<td><input type="radio" name="fwUsed" value="Y"
			<c:if test="${model.entry.fwUsed=='Y' }">checked</c:if> />是&nbsp;&nbsp;<input
			type="radio" name="fwUsed" value="N"
			<c:if test="${model.entry.fwUsed=='N' }">checked</c:if> />否</td>
	</tr>
	<tr>
		<td align="right">是否使用安全网关：</td>
		<td><input type="radio" name="secGatewayUsed" value="Y"
			<c:if test="${model.entry.secGatewayUsed=='Y' }">checked</c:if> />是&nbsp;&nbsp;<input
			type="radio" name="secGatewayUsed" value="N"
			<c:if test="${model.entry.secGatewayUsed=='N' }">checked</c:if> />否</td>
	</tr>
	<tr>
		<td align="right">是否使用网闸：</td>
		<td><input type="radio" name="gapUsed" value="Y"
			<c:if test="${model.entry.gapUsed=='Y' }">checked</c:if> />是&nbsp;&nbsp;<input
			type="radio" name="gapUsed" value="N"
			<c:if test="${model.entry.gapUsed=='N' }">checked</c:if> />否</td>
	</tr>
	<tr>
		<td align="right">是否使用VPN：</td>
		<td><input type="radio" name="vpnUsed" value="Y"
			<c:if test="${model.entry.vpnUsed=='Y' }">checked</c:if> />是&nbsp;&nbsp;<input
			type="radio" name="vpnUsed" value="N"
			<c:if test="${model.entry.vpnUsed=='N' }">checked</c:if> />否</td>
	</tr>
	<tr>
		<td align="right" valign=top>其他安全设施：</td>
		<td><input type="text" name="otherSecurity"
			value="${model.entry.otherSecurity }" style="width: 300px;" /></td>
	</tr>
</table>
</form>
</body>
</html>
