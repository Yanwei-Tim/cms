<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>
</head>
<body style="padding: 15px;">
<form id="editForm" name="editform" action="<c:url value="/equConf.do?m=add"/>" method="post">
<table width="100%" border="0" cellspacing="5" cellpadding="5" style="padding:15px;">
	<tr>
		<td align="right" style="width:130px;"><strong>设备名称：</strong></td>
		<td>${model.equipment.equName}</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">是否核心设备：</td>
		<td>
	<c:if test="${model.equipment.isKeyDevice=='1'}">是</c:if>
		<c:if test="${model.equipment.isKeyDevice=='0'}">否</c:if>
	</tr></td>
	<tr>
		<td align="right" style="width:130px;">设备类型：</td>
		<td>${model.equipment.equType.typeName}</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">设备图标：</td>
		<td>
			<img src="<c:url value="/img/equ/${model.equipment.equIconCode}S.PNG"/>" border=0 />
		</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">网络位置：</td>
		<td>
		<c:if test="${model.equipment.netStation=='i'}">内网</c:if>
		<c:if test="${model.equipment.netStation=='e'}">外网</c:if>
		</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">是否开启监控：</td>
		<td>
		<c:if test="${model.equipment.monitorUsed=='Y'}">是</c:if>
		<c:if test="${model.equipment.monitorUsed=='N'}">否</c:if>
		</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">首选IP：</td>
		<td>${model.equipment.ip }</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">次选IP：</td>
		<td>${model.equipment.otherIp }</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">MAC地址：</td>
		<td>${model.equipment.mac }</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">子网掩码：</td>
		<td>${model.equipment.subnetMask }</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">链路位置：</td>
		<td>
		<c:if test="${model.equipment.inrOrExt=='i'}">内部链路</c:if>
		<c:if test="${model.equipment.inrOrExt=='e'}">外部链路</c:if>
		</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">链路：</td>
		<td>${model.equipment.linkName }</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">设备位置：</td>
		<td>${model.equipment.equStation }</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">设备硬件配置：</td>
		<td>${model.equipment.equInfo }</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">设备系统配置文件：</td>
		<td>${model.equipment.equSysConfig }</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;"></td>
		<td><a href="javascript:;" onclick="download('${model.equipment.equSysConfig }');" >下载</a></td> 
	</tr>
	<tr>
		<td align="right" style="width:130px;">设备管理单位：</td>
		<td>${model.equipment.equManagerDepart }</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">生产厂家：</td>
		<td>${model.equipment.manufacturer }</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">产品型号：</td>
		<td>${model.equipment.model }</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">供货商：</td>
		<td>${model.equipment.provider }</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">联系电话：</td>
		<td>${model.equipment.equPhone }</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">其他联系方式：</td>
		<td>${model.equipment.otherPhone }</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">购买日期：</td>
		<td><fmt:formatDate value="${model.equipment.buyDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">过保日期：</td>
		<td><fmt:formatDate value="${model.equipment.unrepairDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	</tr>
    <tr>
        <td align="right" style="width:130px;">设备SNMPOID名称：</td>
        <td>${model.equipment.equOidName}</td>
    </tr>
    <tr>
        <td align="right" style="width:130px;">设备SNMP服务端口：</td>
        <td>${model.equipment.equPort}</td>
    </tr>
    <tr>
        <td align="right" style="width:130px;">设备SNMP服务密码：</td>
        <td>${model.equipment.equSnmpPwd}</td>
    </tr>
    <tr>
        <td align="right" style="width:130px;">SNMP版本：</td>
        <td>
            <c:if test="${model.equipment.equSnmpver=='v1'}">SNMP v1</c:if>
            <c:if test="${model.equipment.equSnmpver=='v2'}">SNMP v2</c:if>
            <c:if test="${model.equipment.equSnmpver=='v3'}">SNMP v3</c:if>
        </td>
    </tr>
    <tr>
        <td align="right" style="width:130px;">拓扑图位置：</td>
        <td>
            <c:if test="${model.equipment.topologyStation=='1'}">外网接入区</c:if>
            <c:if test="${model.equipment.topologyStation=='2'}">安全接入区</c:if>
            <c:if test="${model.equipment.topologyStation=='3'}">隔离区</c:if>
            <c:if test="${model.equipment.topologyStation=='4'}">公安信息网</c:if>
        </td>
    </tr>

</table>
</form>
</body>
</html>
