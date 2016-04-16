<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<table width="100%" border="0" cellspacing="10" cellpadding="5"
	style="padding: 15px;">
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">数据交换平台名称：</td>
		<td>${model.entry.platformName }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">平台IP地址：</td>
		<td>${model.entry.platformIp }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">连接端口：</td>
		<td>${model.entry.platformPort }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">是否加密：</td>
		<td><c:if test="${model.entry.isEncrypted=='Y'}">是</c:if><c:if
			test="${model.entry.isEncrypted=='N'}">否</c:if></td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">内部链路：</td>
		<td>${model.entry.linkName }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">证书路径：</td>
		<td>${model.entry.certificatePath }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">证书密码：</td>
		<td>${model.entry.certificatePwd }</td>
	</tr>
	<tr>
		<td align="right" valign=top style="font-weight: bold; width: 160px;">描述：</td>
		<td>${model.entry.memo }</td>
	</tr>
</table>
</body>
</html>
