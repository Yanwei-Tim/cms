<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<table width="100%" border="0" cellspacing="10" cellpadding="5"
	style="padding: 15px;">
	<tr>
		<td align="right" style="font-weight: bold; width: 150px;">业务名：</td>
		<td>${model.businessName }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 150px;">外网流量：</td>
		<td>${model.entry.extDataFlow }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 150px;">内网流量：</td>
		<td>${model.entry.intDataFlow }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 150px;">外网记录/请求数：</td>
		<td>${model.entry.extRecordCount }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 150px;">内网记录/请求数：</td>
		<td>${model.entry.intRecordCount }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 150px;">报警数：</td>
		<td>${model.entry.alertCount }</td>
	</tr>
</table>
</body>
</html>
