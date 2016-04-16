<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<table width="100%" border="0" cellspacing="10" cellpadding="5"
	style="padding: 15px;">
	<tr>
		<td style="width:100px;font-weight:bold;" align="right">探针通道名称：</td>
		<td>${model.entry.agentName }</td>
	</tr>
	<tr>
		<td style="width:100px;font-weight:bold;" align="right">探针IP地址：</td>
		<td>${model.entry.agentIp }</td>
	</tr>
	<tr>
		<td style="width:100px;font-weight:bold;" align="right">连接端口：</td>
		<td>${model.entry.agentPort }</td>
	</tr>
	<tr>
		<td style="width:100px;font-weight:bold;" align="right">是否加密：</td>
		<td><c:if test="${model.entry.isEncrypted=='Y'}">是</c:if><c:if test="${model.entry.isEncrypted=='N'}">否</c:if></td>
	</tr>
	<tr>
		<td style="width:100px;font-weight:bold;" align="right">内部链路：</td>
		<td>${model.entry.linkName }</td>
	</tr>
	<tr>
		<td style="width:100px;font-weight:bold;" align="right">探针位置：</td>
		<td><c:if test="${model.entry.agentStation=='i'}">内网</c:if><c:if test="${model.entry.agentStation=='e'}">外网</c:if></td>
	</tr>
	<tr>
		<td style="width:100px;font-weight:bold;" align="right">证书路径：</td>
		<td>${model.entry.certificatePath }</td>
	</tr>
	<tr>
		<td style="width:100px;font-weight:bold;" align="right">证书密码：</td>
		<td>${model.entry.certificatePwd }</td>
	</tr>
	<tr>
		<td align="right" valign=top style="width:100px;font-weight:bold;">描述：</td>
		<td>${model.entry.memo }</td>
	</tr>
</table>
</body>
</html>
