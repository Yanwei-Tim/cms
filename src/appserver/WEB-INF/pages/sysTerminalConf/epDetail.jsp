<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<table width="100%" border="0" cellspacing="5" cellpadding="5"
	style="padding: 15px;">
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">接入终端标识：</td>
		<td>${model.entry.id }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">接入终端名称：</td>
		<td>${model.entry.terminalName }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">接入终端类型：</td>
		<td>
			<c:forEach var="item" items="${model.terminalType}">
					<c:if test="${item.code==model.entry.terminalType}">${item.name}</c:if>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">接入终端外部链路：</td>
		<td>
			<c:forEach var="item" items="${model.terminalOutlink}">
					<c:if test="${item.code==model.entry.termianlOutlink}">${item.name}</c:if>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">接入终端操作系统：</td>
		<td>
			<c:forEach var="item" items="${model.terminalOS}">
					<c:if test="${item.code==model.entry.termianlOS}">${item.name}</c:if>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">接入终端品牌：</td>
		<td>
			<c:forEach var="item" items="${model.terminalBand}">
					<c:if test="${item.code==model.entry.termianlBand}">${item.name}</c:if>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">安全卡类型：</td>
		<td>
			<c:forEach var="item" items="${model.cardType}">
				<c:if test="${item.code==model.entry.cardType}">${item.name}</c:if>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">安全卡型号：</td>
		<td>${model.entry.cardName }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">安全卡版本：</td>
		<td>${model.entry.cardVersion }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">用户姓名：</td>
		<td>${model.entry.userName }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">身份证号：</td>
		<td>${model.entry.userId }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">警号：</td>
		<td>${model.entry.policeId }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">所属地区：</td>
		<td>
			<c:forEach var="item" items="${model.districtList}">
					<c:if test="${item.id == model.entry.userZone}">${item.districtName}</c:if>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">所属单位：</td>
		<td>
			<c:forEach var="item" items="${model.sysClass}">
					<c:if test="${item.id == model.entry.userDepart}">${item.sysClassName}</c:if>
			</c:forEach>
		</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">初次接入时间：</td>
		<td>${model.entry.regTime }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 160px;">是否停用：</td>
		<td>${model.entry.ifCancel=='1'?'是':'否' }</td>
	</tr>
</table>
</body>
</html>
