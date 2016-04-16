<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>
	<script type="text/javascript">
	function init(){
		getCurrentCity('${model.entry.userZone-model.entry.userZone%10000}','${model.entry.userZone}');
	}
	</script>
</head>
<body style="padding: 15px;" onload="init(${model.entry.userZone});" >
<form id="editForm" name="editForm"
	action="<c:url value="/sysTerminalConf.do?m=update"/>" method="post" >
<input type="hidden" name="id" value="${model.entry.id }" />
<table width="100%" border="0" cellspacing="5" cellpadding="5"
	style="padding: 15px;" >
	<tr>
		<td align="right">接入终端标识：</td>
		<td><input type="text" readonly="readonly" name="idTerminal" value="${model.entry.id }"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">接入终端名称：</td>
		<td><input type="text" name="terminalName" value="${model.entry.terminalName }"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">接入终端类型：</td>
		<td><select name="terminalType">
			<c:forEach var="item" items="${model.terminalType}">
				<option value="${item.code }"
					<c:if test="${item.code==model.entry.terminalType}">selected</c:if>>${item.name}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">接入终端外部链路：</td>
		<td><select name="termianlOutlink">
			<c:forEach var="item" items="${model.terminalOutlink}">
				<option value="${item.code }"
					<c:if test="${item.code==model.entry.termianlOutlink}">selected</c:if>>${item.name}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">接入终端操作系统：</td>
		<td><select name="termianlOS">
			<c:forEach var="item" items="${model.terminalOS}">
				<option value="${item.code }"
					<c:if test="${item.code==model.entry.termianlOS}">selected</c:if>>${item.name}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">接入终端品牌：</td>
		<td><select name="termianlBand">
			<c:forEach var="item" items="${model.terminalBand}">
				<option value="${item.code }"
					<c:if test="${item.code==model.entry.termianlBand}">selected</c:if>>${item.name}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">安全卡类型：</td>
		<td><select name="cardType">
			<c:forEach var="item" items="${model.cardType}">
				<option value="${item.code }"
					<c:if test="${item.code==model.entry.cardType}">selected</c:if>>${item.name}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">安全卡型号：</td>
		<td><input type="text" name="cardName" value="${model.entry.cardName }"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">安全卡版本：</td>
		<td><input type="text" name="cardVersion" value="${model.entry.cardVersion }"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">用户姓名：</td>
		<td><input type="text" name="userName" value="${model.entry.userName }"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">身份证号：</td>
		<td><input type="text" name="userId" value="${model.entry.userId }"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">警号：</td>
		<td><input type="text" name="policeId" value="${model.entry.policeId }"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">所属地区：</td>
		<td><select onchange="getCity(this.value,'userZone')" id="userParentZone">
			<c:forEach var="item" items="${model.districtList}">
				<option value="${item.id }"
					<c:if test="${item.id== model.entry.userZone-model.entry.userZone%10000}">selected</c:if>>${item.districtName}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right"></td>
		<td id="userZone">
			<select name="userZone">
			</select>
		</td>
	</tr>
	<tr>
		<td align="right">所属单位：</td>
		<td><select name="userDepart">
			<c:forEach var="item" items="${model.sysClass}">
				<option value="${item.id }"
					<c:if test="${item.id==model.entry.userDepart}">selected</c:if>>${item.sysClassName}
				</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">初次接入时间：</td>
		<td><input type="text" name="regTime" class="Wdate"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${model.entry.regTime }"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">是否停用：</td>
		<td><input type="radio" name="ifCancel" value="1"
			<c:if test="${model.entry.ifCancel=='1' }">checked</c:if>/>是&nbsp;&nbsp;<input
			type="radio" name="ifCancel" value="0" 
			<c:if test="${model.entry.ifCancel=='0' }">checked</c:if>/>否
			</td>
	</tr>
</table>
</form>
</body>
</html>
