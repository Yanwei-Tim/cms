<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<form id="editForm" name="editForm" action="<c:url value="/bizConf.do?m=add"/>" method="post" enctype="multipart/form-data">
<table width="100%" border="0" cellspacing="5" cellpadding="5" style="padding:15px;">
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">业务系统名称：</td>
		<td>${model.item.businessName }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">业务系统描述：</td>
		<td>${model.item.businessDesc }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">业务管理部门：</td>
		<td>${model.item.businessDepartDistrict.orgname }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">业务类型：</td>
		<td>${model.item.businessCode.description }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">链路名：</td>
		<td>${model.item.linkName }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">业务交换方式：</td>
		<td>${model.item.businessExchModel.description }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">业务主管人：</td>
		<td>${model.item.businessAdmin }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">业务主管人电话：</td>
		<td>${model.item.adminPhone }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">主管人邮件：</td>
		<td>${model.item.adminEmail }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">主管人其他联系方式：</td>
		<td>${model.item.adminOtherPhone }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">审批部门：</td>
		<td>${model.item.authDepartDistrict.orgname }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">审批时间：</td>
		<td><fmt:formatDate value="${model.item.authDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">审批编号：</td>
		<td>${model.item.authSerial }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">审批材料：</td>
		<td><a href="<c:url value="/bizConf.do?m=download&id=${model.item.id}&type=authMaterial"/>"  target=_blank>下载</a></td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">注册时间：</td>
		<td><fmt:formatDate value="${model.item.registerDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">交换方向：</td>
		<td>${model.item.exchangeDirection.codeDesc }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">业务交换协议：</td>
		<td>${model.item.exchProtocol.protocolName }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">是否有实时性要求：</td>
		<td><c:if test="${model.item.isRealtime}">是</c:if><c:if test="${!model.item.isRealtime }">否</c:if></td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">日数据交换量（M）：</td>
		<td>${model.item.dayDataVolume}</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">是否备案：</td>
		<td><c:if test="${model.item.isApproved }">是</c:if><c:if test="${!model.item.isApproved }">否</c:if></td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">备案单位名称：</td>
		<td>${model.item.approvedDepartDistrict.orgname }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">拓扑图：</td>
		<td><a href="<c:url value="/bizConf.do?m=download&id=${model.item.id}&type=tpGraph"/>" target=_blank>下载</a></td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">业务协议名：</td>
		<td>${model.item.businessProtocol }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">协议描述：</td>
		<td>${model.item.protocolDesc }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">源IP地址范围：</td>
		<td>${model.item.sourceIpRange }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">源端口范围：</td>
		<td>${model.item.sourcePortRange }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">目标IP地址范围：</td>
		<td>${model.item.destIpRange }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">目标端口范围：</td>
		<td>${model.item.destPortRange }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">使用单位名称：</td>
		<td>${model.item.useDepartDistrict.orgname }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">使用单位类型：</td>
		<td>${model.item.useDepartType.departTypeDesc }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">使用单位物理地址：</td>
		<td>${model.item.useDepartAddress }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">使用单位主管人姓名：</td>
		<td>${model.item.useDepartAdminName }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">使用单位主管人电话：</td>
		<td>${model.item.useDepartAdminPhone }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">使用单位主管人邮件：</td>
		<td>${model.item.useDepartAdminEmail }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">其他联系方式：</td>
		<td>${model.item.useDepartAdminOhterPhone }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">终端数量：</td>
		<td>${model.item.terminalAmount }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">用户数量：</td>
		<td>${model.item.userAmount }</td>
	</tr>
	<tr>
		<td align="right" style="font-weight: bold; width: 200px;">允许上报：</td>
		<td><c:if test="${model.item.enablereport}">是</c:if><c:if test="${!model.item.enablereport }">否</c:if></td>
	</tr>
</table>
</form>
</body>
</html>
