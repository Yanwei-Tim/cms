<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<form id="editForm" name="editForm"
	action="<c:url value="/epConf.do?m=add"/>" method="post" enctype="multipart/form-data">
<table width="100%" border="0" cellspacing="5" cellpadding="5"
	style="padding: 15px;">
	<tr>
		<td align="right">数据交换平台名称：</td>
		<td><input type="text" name="platformName" id="platformName" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">平台IP地址：</td>
		<td><input type="text" name="platformIp"  id="platformIp" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">连接端口：</td>
		<td><input type="text" name="platformPort"   id="platformPort" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">是否加密：</td>
		<td><input type="radio" name="isEncrypted"  value="Y" checked/>是&nbsp;&nbsp;<input
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
		<td><input type="text" name="certificatePwd" id="certificatePwd" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" valign=top>描述：</td>
		<td><textarea name="memo" id="memo" rows="4" style="width: 300px;font-size:13px;"></textarea></td>
	</tr>
</table>
</form>
</body>
</html>
