<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<form id="editForm" name="editForm"
	action="<c:url value="/provinceEpConf.do?m=add"/>" method="post" enctype="multipart/form-data">
<table width="100%" border="0" cellspacing="5" cellpadding="5"
	style="padding: 15px;">
	<tr>
		<td align="right">集控中心名称：</td>
		<td><input type="text" name="platformName" value=""   id="platformName"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">平台IP地址：</td>
		<td><input type="text" name="platformIp" value=""    id="platformIp"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">连接端口：</td>
		<td><input type="text" name="platformPort" value=""   id="platformPort"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">服务地址：</td>
		<td><input type="text" name="address" value=""        id="address"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">是否加密：</td>
		<td><input type="radio" name="isEncrypted" value="Y" checked />是&nbsp;&nbsp;<input
			type="radio" name="isEncrypted" value="N" />否</td>
	</tr>
	<tr>
		<td align="right">内部链路：</td>
		<td><select name="linkName" id="linkName.info">
			<c:forEach var="item" items="${model.intLinks}">
				<option value="${item.linkName }"
					<c:if test="${item.linkName==model.item.linkName}">selected</c:if>>${item.linkName}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">证书上传：</td>
		<td><input type="file" name="certificatePath" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">证书密码：</td>
		<td><input type="text" name="certificatePwd" value=""  id="certificatePwd"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">上报时间：</td>
		<td>
		<input type="text" name="hour" value="" size="4" /> 时
		<input type="text" name="minute" value="" size="4" /> 分
		<input type="text" name="second" value="" size="4" /> 秒</td>
	</tr> 
	<tr>
		<td align="right" valign=top>描述：</td>
		<td><textarea name="memo" rows="4"
			style="width: 300px; font-size: 13px;"></textarea></td>
	</tr>
	<tr>
		<td align="right">允许上报：</td>
		<td><input type="checkbox" name="enablereport" value="1" /></td>
	</tr>
</table>
</form>
</body>
</html>
