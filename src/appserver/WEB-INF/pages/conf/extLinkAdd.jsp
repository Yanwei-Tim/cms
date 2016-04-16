<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<form id="editForm" name="editForm"
	action="<c:url value="/extLinkConf.do?m=add"/>" method="post">
<table width="100%" border="0" cellspacing="5" cellpadding="5"
	style="padding: 15px;">
	<tr>
		<td align="right">链路名称：</td>
		<td><input type="text" name="linkName" value=""  id="linkName"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">链路性质：</td>
		<td>
		<select name="linkPropertyCode" id="linkPropertyCode.info">
		<c:forEach var="item" items="${model.linkPropertyList}">
			<option value="${item.code}">${item.codeDesc}</option>
		</c:forEach>
		</select>
		</td>
	</tr>
	<tr>
		<td align="right">链路类型：</td>
		<td>
		<select name="linkTypeCode" id="linkTypeCode.info">
		<c:forEach var="item" items="${model.linkTypeList}">
			<option value="${item.linkTypeCode}">${item.linkTypeName}</option>
		</c:forEach>
		</select>
		</td>
	</tr>
	<tr>
		<td align="right" style="width:150px;">接入网络地址：</td>
		<td id="ip.info"></td>
	</tr>
	<tr>
		<td align="right" style="width:150px;">接入网络子网掩码：</td>
		<td id="mask.info"></td>
	</tr>
	<tr>
		<td align="right" style="width:150px;">接入网关地址：</td>
		<td id="gateway.info"></td>
	</tr>
	<script>
		
		Ext.onReady(function() {
		 	
			new Ext.form.TextField({
				renderTo:'ip.info',
            	name:'ip',
            	id:'ip',
            	width:300,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
                regexText:'不规范(例):0.0.0.0~255.255.255.255',
                emptyText:'(例):1.1.1.1'
            });
            new Ext.form.TextField({
				renderTo:'mask.info',
            	name:'mask',
            	id:'mask',
            	width:300,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
                regexText:'不规范(例):0.0.0.0~255.255.255.255',
                emptyText:'(例):1.1.1.1'
            });
            
            new Ext.form.TextField({
				renderTo:'gateway.info',
            	name:'gateway',
            	id:'gateway',
            	width:300,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
                regexText:'不规范(例):0.0.0.0~255.255.255.255',
                emptyText:'(例):1.1.1.1'
            });
		});
		
	</script>
	<tr>
		<td align="right">链路运营商：</td>
		<td>
        <select name="linkCorp" id="linkCorp.info">
		<c:forEach var="item" items="${model.linkCorpList}">
			<option value="${item.code}">${item.name}</option>
		</c:forEach>
		</select>
        </td>
	</tr>
	<tr>
		<td align="right">链路安全机制：</td>
		<td><input type="text" name="linkSecurity" value=""   id="linkSecurity"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">链路数量：</td>
		<td><input type="text" name="linkAmount" value=""   id="linkAmount"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">链路带宽（M）：</td>
		<td><input type="text" name="linkBandWidth" value=""  id="linkBandWidth"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" valign=top>其他安全设施：</td>
		<td><input type="text" name="otherSecurity" value=""   id="otherSecurity"
			style="width: 300px;" /></td>
	</tr>
</table>
</form>
</body>
</html>
