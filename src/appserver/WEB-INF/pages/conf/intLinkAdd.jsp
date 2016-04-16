<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<form id="editForm" name="editForm"
	action="<c:url value="/intLinkConf.do?m=add"/>" method="post">
<table width="100%" border="0" cellspacing="5" cellpadding="5"
	style="padding: 15px;">
	<tr>
		<td align="right">链路名称：</td>
		<td><input type="text" name="linkName" value=""     id="linkName"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">接入对象：</td>
		<td><input type="text" name="jrdx" value="" id="jrdx" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">业务交换方式：</td>
		<td><select name="exchangeMode" id="exchangeMode.info">
			<c:forEach var="item" items="${model.businessExchModels}">
				<option value="${item.description }">${item.description}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">链路带宽（M）：</td>
		<td><input type="text" name="linkBandWidth" value=""    id="linkBandWidth"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">是否使用防火墙：</td>
		<td><input type="radio" name="fwUsed" value="Y" checked />是&nbsp;&nbsp;<input
			type="radio" name="fwUsed" value="N" />否</td>
	</tr>
	<tr>
		<td align="right">是否使用安全网关：</td>
		<td><input type="radio" name="secGatewayUsed" value="Y" checked />是&nbsp;&nbsp;<input
			type="radio" name="secGatewayUsed" value="N" />否</td>
	</tr>
	<tr>
		<td align="right">是否使用网闸：</td>
		<td><input type="radio" name="gapUsed" value="Y" checked />是&nbsp;&nbsp;<input
			type="radio" name="gapUsed" value="N" />否</td>
	</tr>
	<tr>
		<td align="right">是否使用VPN：</td>
		<td><input type="radio" name="vpnUsed" value="Y" checked />是&nbsp;&nbsp;<input
			type="radio" name="vpnUsed" value="N" />否</td>
	</tr>
	<tr>
		<td align="right" valign=top>其他安全设施：</td>
		<td><input type="text" name="otherSecurity" value=""     id="otherSecurity"
                   style="width: 300px;" /></td>
	</tr>
</table>
</form>
</body>
</html>
