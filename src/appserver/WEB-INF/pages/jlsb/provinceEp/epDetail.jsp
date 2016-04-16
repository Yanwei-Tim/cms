<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<table width="100%" border="0" cellspacing="10" cellpadding="5"
	style="padding: 15px;">
	<tr>
		<td align="right" style="font-weight: bold; width: 110px;">集控中心名称：</td>
		<td>${model.entry.platformName }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 120px;">平台IP地址：</td>
		<td>${model.entry.platformIp }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 120px;">连接端口：</td>
		<td>${model.entry.platformPort }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 120px;">服务地址：</td>
		<td>${model.entry.address }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 120px;">是否加密：</td>
		<td><c:if test="${model.entry.isEncrypted=='Y'}">是</c:if><c:if
			test="${model.entry.isEncrypted=='N'}">否</c:if></td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 120px;">内部链路：</td>
		<td>${model.entry.linkName }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 120px;">证书路径：</td>
		<td>${model.entry.certificatePath }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 120px;">证书密码：</td>
		<td>${model.entry.certificatePwd }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 120px;">上报时间：</td>
		<td>${model.entry.hour }时${model.entry.minute
		}分${model.entry.second }秒</td>
	</tr>
	<tr>
		<td align="right" valign=top style="font-weight: bold; width: 120px;">描述：</td>
		<td>${model.entry.memo }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 120px;">允许上报：</td>
		<td><c:if test="${model.entry.enablereport}">是</c:if><c:if
			test="${!model.entry.enablereport}">否</c:if></td>
	</tr>
</table>
</body>
</html>
