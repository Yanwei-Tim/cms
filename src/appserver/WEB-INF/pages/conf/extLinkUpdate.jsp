<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<form id="editForm" name="editForm"
	action="<c:url value="/extLinkConf.do?m=update"/>" method="post">
<input type="hidden" name="id" value="${model.entry.id }" />
<table width="100%" border="0" cellspacing="5" cellpadding="5"
	style="padding: 15px;">
	<tr>
		<td align="right">链路名称：</td>
		<td><input type="text" name="linkName" id="linkName" value="${model.entry.linkName }"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">链路性质：</td>
		<td>
		<select name="linkPropertyCode"  id="linkPropertyCode.info">
		<c:forEach var="item" items="${model.linkPropertyList}">
			<option value="${item.code}" <c:if test="${item.code==model.entry.linkPropertyCode }">selected</c:if>>${item.codeDesc}</option>
		</c:forEach>
		</select>
		</td>
	</tr>
	<tr>
		<td align="right">链路类型：</td>
		<td>
		<select name="linkTypeCode"  id="linkTypeCode.info">
		<c:forEach var="item" items="${model.linkTypeList}">
			<option value="${item.linkTypeCode}" <c:if test="${item.linkTypeCode==model.entry.linkTypeCode }">selected</c:if>>${item.linkTypeName}</option>
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
            	value :'${model.entry.ip}',
            	width:300,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
                regexText:'不规范(例):0.0.0.0~255.255.255.255'
            });
            new Ext.form.TextField({
				renderTo:'mask.info',
            	id:'mask',
            	name:'mask',
            	value :'${model.entry.mask}',
            	width:300,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
                regexText:'不规范(例):0.0.0.0~255.255.255.255'
            });
            
            new Ext.form.TextField({
				renderTo:'gateway.info',
            	id:'gateway',
            	name:'gateway',
            	value :'${model.entry.gateway}',
            	width:300,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
                regexText:'不规范(例):0.0.0.0~255.255.255.255'
            });
		});
		
	</script>
	<tr>
		<td align="right">链路运营商：</td>
		<td>
            <select name="linkCorp"  id="linkCorp.info">
            <c:forEach var="item" items="${model.linkCorpList}">
                <option value="${item.code}"<c:if test="${item.code==model.entry.linkCorp }">selected</c:if>>${item.name}</option>
            </c:forEach>
            </select>
        </td>
	</tr>
	<tr>
		<td align="right">链路安全机制：</td>
		<td><input type="text" name="linkSecurity"  id="linkSecurity" value="${model.entry.linkSecurity }"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">链路数量：</td>
		<td><input type="text" name="linkAmount"  id="linkAmount" value="${model.entry.linkAmount }"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">链路带宽（M）：</td>
		<td><input type="text" name="linkBandWidth"  id="linkBandWidth" value="${model.entry.linkBandWidth }"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" valign=top>其他安全设施：</td>
		<td><input type="text" name="otherSecurity" value="${model.entry.otherSecurity }"
			style="width: 300px;" /></td>
	</tr>
</table>
</form>
</body>
</html>
