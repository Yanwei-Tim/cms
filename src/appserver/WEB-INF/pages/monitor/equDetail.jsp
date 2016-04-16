<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>
</head>
<body style="padding: 10px;">
<table width="100%" border="0" cellspacing="10" cellpadding="5"
	style="padding: 15px 15px 0 15px;">
	<tr>
		<td align="right" style="font-weight: bold; width: 110px;">设备名称：</td>
		<td>${model.equName }</td>
		<td align="right" style="font-weight: bold; width: 110px;">VPN隧道数：</td>
		<td><span style="color:red;font-weight:bold;">${model.vpn }</span></td>
		<td align="right" style="font-weight: bold; width: 110px;">允许最大连接数：</td>
		<td><span style="color:red;font-weight:bold;">${model.maxcon }</span></td>
		<td align="right" style="font-weight: bold; width: 110px;">当前连接数：</td>
		<td><span style="color:red;font-weight:bold;">${model.currentcon }</span></td>
	</tr>
	<tr>
		<td colspan=8>
		<hr />
		</td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	style="padding: 0 15px 15px 15px;text-align:center">
	<tr>
		<td valign=top><img
			src="<c:url value="/runMonitor.do?m=cpuChart&cpu=${model.cpu}"/>"
			border=0 /></td>
		<td valign=top><img
			src="<c:url value="/runMonitor.do?m=memChart&mem=${model.mem}&mem_total=${model.mem_total}"/>"
			border=0 /><br/><span style="color:red;font-weight:bold;">共${model.mem_total }GB</span></td>
		<td valign=top><img
			src="<c:url value="/runMonitor.do?m=diskChart&disk=${model.disk}&disk_total=${model.disk_total}"/>"
			border=0 /><br/><span style="color:red;font-weight:bold;">共${model.disk_total }GB</span></td>
	</tr>
</table>
</body>
</html>
