<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<form id="editForm" name="editForm"
	action="<c:url value="/provinceEpConf.do?m=update"/>" method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value="${model.entry.id }" />
<table width="100%" border="0" cellspacing="5" cellpadding="5"
	style="padding: 15px;">
	<tr>
		<td align="right">集控中心名称：</td>
		<td><input type="text" name="platformName"
			value="${model.entry.platformName }" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">平台IP地址：</td>
		<td><input type="text" name="platformIp"
			value="${model.entry.platformIp }" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">连接端口：</td>
		<td><input type="text" name="platformPort"
			value="${model.entry.platformPort }" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">服务地址：</td>
		<td><input type="text" name="address"
			value="${model.entry.address }" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">是否加密：</td>
		<td><input type="radio" name="isEncrypted" value="Y"
			<c:if test="${model.entry.isEncrypted=='Y' }">checked</c:if> />是&nbsp;&nbsp;<input
			type="radio" name="isEncrypted" value="N"
			<c:if test="${model.entry.isEncrypted=='N' }">checked</c:if> />否</td>
	</tr>
	<tr>
		<td align="right">内部链路：</td>
		<td><select name="linkName">
			<c:forEach var="item" items="${model.intLinks}">
				<option value="${item.linkName }"
					<c:if test="${item.linkName==model.entry.linkName}">selected</c:if>>${item.linkName}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">证书上传：</td>
		<td><input type="file" name="certificatePath"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">证书密码：</td>
		<td><input type="text" name="certificatePwd"
			value="${model.entry.certificatePwd }" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">上报时间：</td>
		<td><input type="text" name="hour" value="${model.entry.hour }"
			size="4" /> 时 <input type="text" name="minute"
			value="${model.entry.minute }" size="4" /> 分 <input type="text"
			name="second" value="${model.entry.second }" size="4" /> 秒</td>
	</tr>
	<tr>
		<td align="right" valign=top>描述：</td>
		<td><textarea name="memo" rows="4"
			style="width: 300px; font-size: 13px;">${model.entry.memo }</textarea></td>
	</tr>
	<tr>
		<td align="right">允许上报：</td>
		<td><input type="checkbox" name="enablereport" value="1"
			<c:if test="${model.entry.enablereport }">checked</c:if> /></td>
	</tr>
</table>
</form>
</body>
</html>
