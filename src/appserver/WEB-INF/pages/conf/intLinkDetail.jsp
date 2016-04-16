<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<form id="editForm" name="editForm"
	action="<c:url value="/intLinkConf.do?m=add"/>" method="post">
<table width="100%" border="0" cellspacing="8" cellpadding="5"
	style="padding: 15px;">
	<tr>
		<td align="right" style="font-weight:bold;width:160px;">链路名称：</td>
		<td>${model.entry.linkName }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight:bold;width:160px;">接入对象：</td>
		<td>${model.entry.jrdx }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight:bold;width:160px;">业务交换方式：</td>
		<td>${model.entry.exchangeMode }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight:bold;width:160px;">链路带宽(M)：</td>
		<td>${model.entry.linkBandWidth }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight:bold;width:160px;">是否使用防火墙：</td>
		<td>${model.entry.fwUsed }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight:bold;width:160px;">是否使用安全网关：</td>
		<td>${model.entry.secGatewayUsed }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight:bold;width:160px;">是否使用网闸：</td>
		<td>${model.entry.gapUsed }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight:bold;width:160px;">是否使用VPN：</td>
		<td>${model.entry.vpnUsed }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight:bold;width:160px;" valign=top>其他安全设施：</td>
		<td>${model.entry.otherSecurity }</td>
	</tr>
</table>
</form>
</body>
</html>
