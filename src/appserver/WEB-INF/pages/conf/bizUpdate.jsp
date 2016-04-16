<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>
<script>
	
	Ext.onReady(function (){
		getCurrentCity('${model.item.businessDepartParent}','${model.item.authDepartParent}','${model.item.approvedDepartParent}','${model.item.useDepartParent}','${model.item.businessDepart}','${model.item.authDepart}','${model.item.approvedDepart}','${model.item.useDepart}');
	});
	

	function getCity(province,selname){
		Ext.Ajax.request({
			url:'../../district.do?m=comboChildByParent',
			success:function(response){
				Ext.getDom(selname).innerHTML = response.responseText;
			},
			params:{
				parentId:province,
				name:selname
			}
		});
	}
	
	function getCurrentCity(businessDepartParent,authDepartParent,approvedDepartParent,useDepartParent,
	businessDepart,authDepart,approvedDepart,useDepart){
		Ext.Ajax.request({
			url:'../../district.do?m=comboChildByParentShow',
			success:function(response){
				Ext.getDom("businessDepart").innerHTML = response.responseText;
			},
			params:{
				parentId:businessDepartParent,
				name:"businessDepart",
				id:businessDepart
			}
		});
		
		Ext.Ajax.request({
			url:'../../district.do?m=comboChildByParentShow',
			success:function(response){
				Ext.getDom("authDepart").innerHTML = response.responseText;
			},
			params:{
				parentId:authDepartParent,
				name:"authDepart",
				id:authDepart
			}
		});
		
		Ext.Ajax.request({
			url:'../../district.do?m=comboChildByParentShow',
			success:function(response){
				Ext.getDom("approvedDepart").innerHTML = response.responseText;
			},
			params:{
				parentId:approvedDepartParent,
				name:"approvedDepart",
				id:approvedDepart
			}
		});
		
		Ext.Ajax.request({
			url:'../../district.do?m=comboChildByParentShow',
			success:function(response){
				Ext.getDom("useDepart").innerHTML = response.responseText;
			},
			params:{
				parentId:useDepartParent,
				name:"useDepart",
				id:useDepart
			}
		});
		
	}
	

</script>
</head>
<body style="padding: 15px;">
<form id="editForm" name="editForm"
	action="<c:url value="/bizConf.do?m=update"/>" method="post"
	enctype="multipart/form-data"><input type="hidden" name="id"
	value="${model.item.id }" />
<table width="100%" border="0" cellspacing="5" cellpadding="5"
	style="padding: 15px;">
	<tr>
		<td align="right">业务系统名称：</td>
		<td><input type="text" name="businessName"  id="businessName"
			value="${model.item.businessName}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">业务系统描述：</td>
		<td><input type="text" name="businessDesc"   id="businessDesc"
			value="${model.item.businessDesc}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">业务管理部门：</td>
		<td><select onchange="getCity(this.value,'businessDepart')" id="value1">
			<c:forEach var="item" items="${model.districtList}">
				<option value="${item.id }"
					<c:if test="${item.id==model.item.businessDepartParent}">selected</c:if>>${item.districtName}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right"></td>
		<td id="businessDepart"><select name="businessDepart">
			<select name="businessCodeId">
			</select></td>
	</tr>
	<tr>
		<td align="right">业务类型：</td>
		<td><select name="businessCodeId" id="businessCode">
			<c:forEach var="item" items="${model.businessCodes}">
				<option value="${item.code }"
					<c:if test="${item.code==model.item.businessCodeId}">selected</c:if>>${item.description}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">链路名：</td>
		<td><select name="linkName" id="linkName">
			<c:forEach var="item" items="${model.intLinkList}">
				<option value="${item.linkName }"
					<c:if test="${item.linkName==model.item.linkName}">selected</c:if>>${item.linkName}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">业务交换方式：</td>
		<td><select name="businessExchModelId" id="businessExchModel">
			<c:forEach var="item" items="${model.businessExchModels}">
				<option value="${item.code }"
					<c:if test="${item.code==model.item.businessExchModelId}">selected</c:if>>${item.description}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">业务主管人：</td>
		<td><input type="text" name="businessAdmin"   id="businessAdmin"
			value="${model.item.businessAdmin}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">业务主管人电话：</td>
		<td><input type="text" name="adminPhone"     id="adminPhone"
			value="${model.item.adminPhone}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">主管人邮件：</td>
		<td><input type="text" name="adminEmail"     id="adminEmail"
			value="${model.item.adminEmail}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">主管人其他联系方式：</td>
		<td><input type="text" name="adminOtherPhone"   id="adminOtherPhone"
			value="${model.item.adminOtherPhone}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">审批部门：</td>
		<td><select onchange="getCity(this.value,'authDepart')" id="value2">
			<c:forEach var="item" items="${model.districtList}">
				<option value="${item.id }"
					<c:if test="${item.id==model.item.authDepartParent}">selected</c:if>>${item.districtName}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right"></td>
		<td id="authDepart"><select name="authDepart">
		</select></td>
	</tr>
	<tr>
		<td align="right">审批时间：</td>
		<td><input type="text" name="auth_date" class="Wdate"    id="auth_date"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
			value="${model.item.authDate}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">审批编号：</td>
		<td><input type="text" name="authSerial"              id="authSerial"
			value="${model.item.authSerial}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">审批材料：</td>
		<td><input type="file" name="auth_material" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">注册时间：</td>
		<td><input type="text" name="register_date" class="Wdate"     id="register_date"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
			value="${model.item.registerDate}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">交换方向：</td>
		<td><select name="exchangeDirectionCode" id="exchange">
			<c:forEach var="item" items="${model.exchangeDirections}">
				<option value="${item.code }"
					<c:if test="${item.code==model.item.exchangeDirectionCode}">selected</c:if>>${item.codeDesc}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">业务交换协议：</td>
		<td><select name="exchProtocolCode" id="exchProtocol">
			<c:forEach var="item" items="${model.exchProtocols}">
				<option value="${item.protocolCode}"
					<c:if test="${item.protocolCode==model.item.exchProtocolCode}">selected</c:if>>${item.protocolName}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">是否有实时性要求：</td>
		<td><input type="radio" name="isRealtime" value="Y" checked />是
		<input type="radio" name="isRealtime" value="N" />否</td>
	</tr>
	<tr>
		<td align="right">日数据交换量(M)：</td>
		<td><input type="text" name="dayDataVolume"                      id="dayDataVolume"
			value="${model.item.dayDataVolume}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">是否备案：</td>
		<td><input type="radio" name="isApproved" value="Y" checked />是
		<input type="radio" name="isApproved" value="N" />否</td>
	</tr>
	<tr>
		<td align="right">备案单位：</td>
		<td><select onchange="getCity(this.value,'approvedDepart')" id="value3">
			<c:forEach var="item" items="${model.districtList}">
				<option value="${item.id }"
					<c:if test="${item.id==model.item.approvedDepartParent}">selected</c:if>>${item.districtName}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right"></td>
		<td id="approvedDepart"><select name="approvedDepart">
		</select></td>
	</tr>
	<tr>
		<td align="right">拓扑图：</td>
		<td><input type="file" name="tp_graph" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">业务协议名：</td>
		<td><input type="text" name="businessProtocol"  id="businessProtocol"
			value="${model.item.businessProtocol}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">协议描述：</td>
		<td><input type="text" name="protocolDesc"      id="protocolDesc"
			value="${model.item.protocolDesc}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">源IP地址范围：</td>
		<td id="sourceIpRange.info"></td>
	</tr>
	<tr>
		<td align="right">源端口范围：</td>
		<td id="sourcePortRange.info"></td>
	</tr>
	<tr>
		<td align="right">目标IP地址范围：</td>
		<td id="destIpRange.info"></td>
	</tr>
	<tr>
		<td align="right">目标端口范围：</td>
		<td id="destPortRange.info"></td>
	</tr>
		<script>
		Ext.onReady(function(){
			new Ext.form.TextField({
				renderTo:'sourceIpRange.info',
            	name:'sourceIpRange',
                id:'sourceIpRange',
            	value:'${model.item.sourceIpRange}',
            	width:300,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])||(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])-((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])))$/,
                regexText:'不规范(例):1.1.1.1-1.1.1.9或者1.1.1.5',
                emptyText:'(例):1.1.1.1-1.1.1.9或者1.1.1.5'
            });
            new Ext.form.TextField({
				renderTo:'destIpRange.info',
            	name:'destIpRange',
            	id:'destIpRange',
            	value:'${model.item.destIpRange}',
            	width:300,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])||(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])-((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])))$/,
                regexText:'不规范(例):1.1.1.1-1.1.1.9或者1.1.1.5',
                emptyText:'(例):1.1.1.1-1.1.1.9或者1.1.1.5'
            });
            new Ext.form.TextField({
				renderTo:'sourcePortRange.info',
            	name:'sourcePortRange',
            	id:'sourcePortRange',
            	value:'${model.item.sourcePortRange}',
            	width:300,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])||((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])-(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])))$/,
                regexText:'不规范(例):1-65536或者100',
                emptyText:'(例):1-65536或者100'
            });
            new Ext.form.TextField({
				renderTo:'destPortRange.info',
            	id:'destPortRange',
            	name:'destPortRange',
            	value:'${model.item.destPortRange}',
            	width:300,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])||((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])-(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])))$/,
                regexText:'不规范(例):1-65536或者100',
                emptyText:'(例):1-65536或者100'
            });
		});
	</script>
	<tr>
		<td align="right">使用单位：</td>
		<td><select onchange="getCity(this.value,'useDepart')" id="value4">
			<c:forEach var="item" items="${model.districtList}">
				<option value="${item.id }"
					<c:if test="${item.id==model.item.useDepartParent}">selected</c:if>>${item.districtName}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right"></td>
		<td id="useDepart"><select name="useDepart">
		</select></td>
	</tr>
	<tr>
		<td align="right">使用单位类型：</td>
		<td><select name="useDepartTypeId" id="value5">
			<c:forEach var="item" items="${model.useDepartTypes}">
				<option value="${item.departCode }"
					<c:if test="${item.departCode==model.item.useDepartTypeId}">selected</c:if>>${item.departTypeDesc}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">使用单位物理地址：</td>
		<td><input type="text" name="useDepartAddress"    id="useDepartAddress"
			value="${model.item.useDepartAddress}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">使用单位主管人姓名：</td>
		<td><input type="text" name="useDepartAdminName"  id="useDepartAdminName"
			value="${model.item.useDepartAdminName}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">使用单位主管人电话：</td>
		<td><input type="text" name="useDepartAdminPhone"  id="useDepartAdminPhone"
			value="${model.item.useDepartAdminPhone}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">使用单位主管人邮件：</td>
		<td><input type="text" name="useDepartAdminEmail"   id="useDepartAdminEmail"
			value="${model.item.useDepartAdminEmail}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">其他联系方式：</td>
		<td><input type="text" name="useDepartAdminOhterPhone"    id="useDepartAdminOhterPhone"
			value="${model.item.useDepartAdminOhterPhone}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">终端数量：</td>
		<td><input type="text" name="terminalAmount"      id="terminalAmount"
			value="${model.item.terminalAmount}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">用户数量：</td>
		<td><input type="text" name="userAmount"       id="userAmount"
			value="${model.item.userAmount}" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">允许上报：</td>
		<td><input type="radio" name="enablereport" value="1"
			<c:if test="${model.item.enablereport }">checked</c:if> />是 <input
			type="radio" name="enablereport" value="0"
			<c:if test="${!model.item.enablereport }">checked</c:if> />否</td>
	</tr>
</table>
</form>
</body>
</html>
