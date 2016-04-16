<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<form id="editForm" name="editForm"
	action="<c:url value="/sysTerminalConf.do?m=add"/>" method="post">
<table width="100%" border="0" cellspacing="5" cellpadding="5"
	style="padding: 15px;">
	
	<tr>
		<td align="right">接入终端名称：</td>
		<td><input type="text" name="terminalName" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">接入终端类型：</td>
		<td><select name="terminalType">
			<c:forEach var="item" items="${model.terminalType}">
				<option value="${item.code }" >${item.name}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">接入终端外部链路：</td>
		<td><select name="termianlOutlink">
			<c:forEach var="item" items="${model.terminalOutlink}">
				<option value="${item.code }" >${item.name}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">接入终端操作系统：</td>
		<td><select name="termianlOS">
			<c:forEach var="item" items="${model.terminalOS}">
				<option value="${item.code }">${item.name}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">接入终端品牌：</td>
		<td><select name="termianlBand">
			<c:forEach var="item" items="${model.terminalBand}">
				<option value="${item.code }">${item.name}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">安全卡类型：</td>
		<td><select name="cardType">
			<c:forEach var="item" items="${model.cardType}">
				<option value="${item.code }">${item.name}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">安全卡型号：</td>
		<td><input type="text" name="cardName" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">安全卡版本：</td>
		<td><input type="text" name="cardVersion" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">用户姓名：</td>
		<td><input type="text" name="userName" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">身份证号：</td>
		<td><input type="text" name="userId" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">警号：</td>
		<td><input type="text" name="policeId" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">所属地区：</td>
		<td>
			<select onchange="getCity(this.value,'userZone');" style="width: 100px;" id="userParentZone">
				<option value="">请选择</option>
					<c:forEach var="item" items="${model.districtList}" >
						<option value="${item.id }">${item.districtName}</option>
					</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td align="right"></td>
		<td id="userZone">
			<select name="userZone"  style="width: 100px;">
				<option value="" >请选择</option>
				<c:forEach var="item" items="${model.districtListFirst}">
					<option value="${item.id}">${item.districtName}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td align="right">所属单位：</td>
		<td><select name="userDepart">
			<option value="" >请选择</option>
			<c:forEach var="item" items="${model.sysClass}">
				<option value="${item.id }">${item.sysClassName}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">初次接入时间：</td>
		<td><input type="text" name="regTime" class="Wdate"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">终端是否已停用：</td>
		<td><input type="radio" name="ifCancel" value="1" />是 <input
			type="radio" name="ifCancel" value="0" checked />否</td>
	</tr>
	
</table>
</form>
</body>
</html>
