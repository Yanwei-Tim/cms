<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<form id="editForm" name="editForm"
	action="<c:url value="/parentEpConf.do?m=add"/>" method="post" enctype="multipart/form-data">
<table width="100%" border="0" cellspacing="5" cellpadding="5"
	style="padding: 15px;">
	<tr>
		<td align="right">集控中心名称：</td>
		<td><input type="text" name="platformName" id="platformName" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">平台IP地址：</td>
		<td id="ip.info" ></td>
	</tr>
	<tr>
		<td align="right">连接端口：</td>
		<td id="port.info"></td>
	</tr>
	<tr>
		<td align="right">平台Mac地址：</td>
		<td id="mac.info"></td>
	</tr>
	<script>
		Ext.onReady(function() {		 	
			new Ext.form.TextField({
				renderTo:'ip.info',
            	name:'platformIp',
            	id:'platformIp',
            	width:300,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
                regexText:'不规范(例):0.0.0.0~255.255.255.255'
//                emptyText:'(例):1.1.1.1'
            });
            new Ext.form.TextField({
				renderTo:'port.info',
            	name:'platformPort',
            	id:'platformPort',
            	width:300,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])||((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])-(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])))$/,
                regexText:'不规范(例):1-65536或者100'/*,
                emptyText:'(例):1-65536或者100'*/
            });
            new Ext.form.TextField({
				renderTo:'mac.info',
            	id:'platformMac',
            	name:'platformMac',
            	width:300,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^(([0-9a-fA-F]{2}\:){5}[0-9a-fA-F]{2})$/,
				regexText:'不规范(例):0a:45:be:e6:00:AF,必须是[a-fA-F]之间的字母或者数字'/*,
				emptyText:'(例):0a:45:bE:e6:00:AF'*/
            });
            new Ext.form.ComboBox({
				renderTo:'timeType.info',
            	width:300,
            	hiddenName : 'timeType',
                id:'timeType',
                name:'timeType',
				valueField : 'value',
				displayField : 'key',
				store : new Ext.data.SimpleStore({
					fields : ['key', 'value'],
					data : [['2小时', '2'], ['4小时', '4'],['12小时', '12'], ['24小时', '24']]
					}),
				selectOnFocus : true,
				editable : true,
				triggerAction : 'all',
				loadingText : '加载中...',
				mode : 'local',
				emptyText : '请选择',
            	allowBlank:false,
            	blankText:'该项不能为空！'
            });
    	});
        function check(obj){
            if(document.getElementById("type2").checked){
                document.getElementById("pass").disabled = true;
                document.getElementById("name").disabled = true;
            }else if(document.getElementById("type1").checked){
                document.getElementById("pass").disabled = false;
                document.getElementById("name").disabled = false;
            }
        }
	</script>
    <tr>
        <td align="right">服务类型：</td>
        <td ><input type="radio" name="type" id="type1" value="Ftp" onclick="check('c')" checked />Ftp&nbsp;&nbsp;<input
                type="radio" name="type" id="type2" value="Webservice" onclick="check('cc')" />Webservice</td>
    </tr>
	<tr>
		<td align="right">服务地址：</td>
		<td><input type="text" name="address" value=""    id="address"
			style="width: 300px;" /></td>
	</tr>
    <tr>
        <td align="right">用户名：</td>
        <td><input type="text" name="name" id="name" value=""
                   style="width: 300px;" /></td>
    </tr>
    <tr>
        <td align="right">密码：</td>
        <td><input type="text" name="pass" id="pass" value=""
                   style="width: 300px;" /></td>
    </tr>
	<tr>
		<td align="right">是否加密：</td>
		<td id="isEncrypted"><input type="radio" name="isEncrypted" value="Y" checked />是&nbsp;&nbsp;<input
			type="radio" name="isEncrypted" value="N" />否</td>
	</tr>
	<tr>
		<td align="right">内部链路：</td>
		<td><select name="linkName" id="linkName.info">
			<c:forEach var="item" items="${model.intLinks}">
				<option value="${item.linkName }"
					<c:if test="${item.linkName==model.item.linkName}">selected</c:if>>${item.linkName}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right">证书上传：</td>
		<td><input type="file" name="certificatePath" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">证书密码：</td>
		<td><input type="text" name="certificatePwd" value=""  id="certificatePwd"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right">上报时间间隔：</td>
		<td id="timeType.info"></td>
	</tr>
	<tr>
		<td align="right">上报时间：</td>
		<td>
		<input type="text" name="hour" id='hour' value="" size="4" /> 时
		<input type="text" name="minute" id="minute" value="" size="4" /> 分
		<input type="text" name="second" id="second" value="" size="4" /> 秒</td>
	</tr> 
	<tr>
		<td align="right" valign=top>描述：</td>
		<td><textarea name="memo" rows="4" id="memo"
			style="width: 300px; font-size: 13px;"></textarea></td>
	</tr>
	<tr>
		<td align="right">允许上报：</td>
		<td><input type="checkbox" name="enablereport" value="1" id="enablereport" /></td>
	</tr>
	
</table>
</form>
</body>
</html>
