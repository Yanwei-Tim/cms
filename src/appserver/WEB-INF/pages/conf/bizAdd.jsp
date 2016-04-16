<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

<script>
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
    function verify(){
        alert('ces')
        var form = document.forms.editForm;
        if(form.businessName.value==null||form.businessName.value.length==0){
            alert("业务系统名称不能为空！");
            return false;
        }
    /*    var businessName = form.businessName.value;
        var re = /^[0-9a-zA-Z!$#%@^&*()~_+]{8,20}$/;
        if(!re.test(password)){
            alert("密码必须为大写字母、小写字母、数字和特殊字符的组合，且不少于8位！");
            return false;
        }
        //re = /([0-9].*([a-zA-Z].*[!$#%@^&*()~_+]|[!$#%@^&*()~_+].*[a-zA-Z])|[a-zA-Z].*([0-9].*[!$#%@^&*()~_+]|[!$#%@^&*()~_+].*[0-9])|[!$#%@^&*()~_+].*([0-9].*[a-zA-Z]|[a-zA-Z].*[0-9]))/;
        //if(!re.test(password)){
        //alert("密码必须为大写字母、小写字母、数字和特殊字符的组合，且不少于8位！");
        //return false;
        //}
        if(form.vcode.value==null||form.vcode.value.length==0){
            alert("验证码不能为空！");
            return false;
        }*/
        return true;
    }
</script>

</head>
<body style="padding: 15px;">
<form id="editForm" name="editForm"
	action="<c:url value="/bizConf.do?m=add"/>" method="post"  onsubmit="return verify()"
	enctype="multipart/form-data" >
<table width="100%" border="0" cellspacing="5" cellpadding="5"
	style="padding: 15px;">
	<tr>
		<td align="right">业务系统名称：</td>
		<td><input type="text" id="businessName" name="businessName" value=""
			style="width: 300px;"  /></td>
	</tr>
	<tr>
		<td align="right">业务系统描述：</td>
		<td><input type="text" id="businessDesc" name="businessDesc" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">业务管理部门：</td>
		<td><select onchange="getCity(this.value,'businessDepart')" style="width: 100px;" id="value1">
		<option value="">请选择</option>
			<c:forEach var="item" items="${model.districtList}" >
				<option value="${item.id }">${item.districtName}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right"></td>
		<td id="businessDepart"><select name="businessDepart"  style="width: 100px;">
		<option value="" >请选择</option>
			<c:forEach var="item" items="${model.districtListFirst}">
				<option value="${item.id}">${item.districtName}</option>
			</c:forEach>
			</select></td>
	</tr>
	<tr>
		<td align="right">业务类型：</td>
		<td><select name="businessCodeId"  style="width: 100px;" id="businessCode">
		<option value="" >请选择</option>
			<c:forEach var="item" items="${model.businessCodes}">
				<option value="${item.code }">${item.description}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">链路名：</td>
		<td><select name="linkName"  style="width: 100px;" id="linkName">
		<option value="" >请选择</option>
			<c:forEach var="item" items="${model.intLinkList}">
				<option value="${item.linkName }">${item.linkName}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">业务交换方式：</td>
		<td><select name="businessExchModelId"  style="width: 100px;" id="businessExchModel">
		<option value="" >请选择</option>
			<c:forEach var="item" items="${model.businessExchModels}">
				<option value="${item.code }">${item.description}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">业务主管人：</td>
		<td><input type="text" name="businessAdmin" value=""     id="businessAdmin"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">业务主管人电话：</td>
		<td><input type="text" name="adminPhone" value=""    id="adminPhone"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">主管人邮件：</td>
		<td><input type="text" name="adminEmail" value=""   id="adminEmail"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">主管人其他联系方式：</td>
		<td><input type="text" name="adminOtherPhone" value=""    id="adminOtherPhone"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">审批部门：</td>
		<td><select onchange="getCity(this.value,'authDepart')"  style="width: 100px;" id="value2">
		<option value="" >请选择</option>
			<c:forEach var="item" items="${model.districtList}">
				<option value="${item.id }">${item.districtName}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right"></td>
		<td id="authDepart"><select name="authDepart"  style="width: 100px;">
		<option value="" >请选择</option>
			<c:forEach var="item" items="${model.districtListFirst}">
				<option value="${item.id}">${item.districtName}</option>
			</c:forEach>
			</select></td>
	</tr>
	<tr>
		<td align="right">审批时间：</td>
		<td><input type="text" name="auth_date" class="Wdate"     id="auth_date"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">审批编号：</td>
		<td><input type="text" name="authSerial" value=""    id="authSerial"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">审批材料：</td>
		<td><input type="file" name="auth_material"  id="auth_material" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">注册时间：</td>
		<td><input type="text" name="register_date" class="Wdate"  id="register_date"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">交换方向：</td>
		<td><select name="exchangeDirectionCode"  style="width: 100px;" id="exchange">
		<option value="" >请选择</option>
			<c:forEach var="item" items="${model.exchangeDirections}">
				<option value="${item.code }">${item.codeDesc}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">业务交换协议：</td>
		<td><select name="exchProtocolCode"  style="width: 100px;" id="exchProtocol">
		<option value="" >请选择</option>
			<c:forEach var="item" items="${model.exchProtocols}">
				<option value="${item.protocolCode }">${item.protocolName}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">是否有实时性要求：</td>
		<td><input type="radio" name="isRealtime" value="Y" checked />是
		<input type="radio" name="isRealtime" value="N" />否</td>
	</tr>
	<tr>
		<td align="right">日数据交换量（M）：</td>
		<td><input type="text" name="dayDataVolume" value=""    id="dayDataVolume"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">是否备案：</td>
		<td><input type="radio" name="isApproved" value="Y" checked />是
		<input type="radio" name="isApproved" value="N" />否</td>
	</tr>
	<tr>
		<td align="right">备案单位：</td>
		<td>
			<select onchange="getCity(this.value,'approvedDepart')"  style="width: 100px;" id="value3">
				<option value="">请选择</option>
				<c:forEach var="item" items="${model.districtList}">
					<option value="${item.id }">${item.districtName}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td align="right"></td>
		<td id="approvedDepart">
			<select name="approvedDepart"  style="width: 100px;">
				<option value="">请选择</option>
				<c:forEach var="item" items="${model.districtListFirst}">
					<option value="${item.id}">${item.districtName}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td align="right">拓扑图：</td>
		<td><input type="file" name="tp_graph"     style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">业务协议名：</td>
		<td><input type="text" name="businessProtocol" value=""  id="businessProtocol"
                   style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">协议描述：</td>
		<td><input type="text" name="protocolDesc" value=""  id="protocolDesc"
                   style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">源IP地址范围：</td>
		<td id="sourceIpRange.info">
			
		</td>
	</tr>
	<script>
		Ext.onReady(function(){
			new Ext.form.TextField({
				renderTo:'sourceIpRange.info',
            	name:'sourceIpRange',
            	id:'sourceIpRange',
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
            	width:300,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])||((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])-(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])))$/,
                regexText:'不规范(例):1-65536或者100',
                emptyText:'(例):1-65536或者100'
            });
            new Ext.form.TextField({
				renderTo:'destPortRange.info',
            	name:'destPortRange',
            	id:'destPortRange',
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
	<tr>
		<td align="right">使用单位：</td>
		<td><select onchange="getCity(this.value,'useDepart')"  style="width: 100px;" id="value4">
		<option value="" >请选择</option>
			<c:forEach var="item" items="${model.districtList}">
				<option value="${item.id }">${item.districtName}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right"></td>
		<td id="useDepart"><select name="useDepart"  style="width: 100px;">
		<option value="" >请选择</option>
			<c:forEach var="item" items="${model.districtListFirst}">
				<option value="${item.id}">${item.districtName}</option>
			</c:forEach>
			</select></td>
	</tr>
	<tr>
		<td align="right">使用单位类型：</td>
		<td><select name="useDepartTypeId"  style="width: 100px;" id="value5">
		<option value="" >请选择</option>
			<c:forEach var="item" items="${model.useDepartTypes}">
				<option value="${item.departCode }">${item.departTypeDesc}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">使用单位物理地址：</td>
		<td><input type="text" name="useDepartAddress" value=""  id="useDepartAddress"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">使用单位主管人姓名：</td>
		<td><input type="text" name="useDepartAdminName" value=""  id="useDepartAdminName"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">使用单位主管人电话：</td>
		<td><input type="text" name="useDepartAdminPhone" value=""  id="useDepartAdminPhone"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">使用单位主管人邮件：</td>
		<td><input type="text" name="useDepartAdminEmail" value=""   id="useDepartAdminEmail"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">其他联系方式：</td>
		<td><input type="text" name="useDepartAdminOhterPhone" value=""  id="useDepartAdminOhterPhone"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">终端数量：</td>
		<td><input type="text" name="terminalAmount" value=""      id="terminalAmount"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">用户数量：</td>
		<td><input type="text" name="userAmount" value=""          id="userAmount"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">允许上报：</td>
		<td><input type="radio" name="enablereport" value="1" />是 <input
			type="radio" name="enablereport" value="0" checked />否</td>
	</tr>
</table>
</form>
</body>
</html>
