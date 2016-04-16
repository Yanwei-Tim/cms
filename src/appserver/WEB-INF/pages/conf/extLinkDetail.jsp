<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<table width="100%" border="0" cellspacing="10" cellpadding="5"
	style="padding: 15px;">
	<tr>
		<td align="right" style="font-weight:bold;width:150px;">链路名称：</td>
		<td>${model.entry.linkName }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight:bold;width:150px;">链路性质：</td>
		<td>${model.entry.linkProperty.codeDesc }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight:bold;width:150px;">链路类型：</td>
		<td>${model.entry.linkType.linkTypeName }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight:bold;width:150px;">接入网络地址：</td>
		<td id="ip.info">${model.entry.ip }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight:bold;width:150px;">接入网络子网掩码：</td>
		<td id="mask.info">${model.entry.mask }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight:bold;width:150px;">接入网关地址：</td>
		<td id="gateway.info">${model.entry.gateway }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight:bold;width:150px;">链路运营商：</td>
		<td>
            <c:forEach var="item" items="${model.linkCorpList}">
                <c:if test="${item.code==model.entry.linkCorp }">${item.name}</c:if>
            </c:forEach>
        </td>
	</tr>
	<tr>
		<td align="right" style="font-weight:bold;width:150px;">链路安全机制：</td>
		<td>${model.entry.linkSecurity }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight:bold;width:150px;">链路数量：</td>
		<td>${model.entry.linkAmount }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight:bold;width:150px;">链路带宽(M)：</td>
		<td>${model.entry.linkBandWidth }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight:bold;width:150px;" valign=top>其他安全设施：</td>
		<td>${model.entry.otherSecurity }</td>
	</tr>
</table>
</body>
</html>
