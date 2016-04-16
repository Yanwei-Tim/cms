<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>
	<script>
		function init(){
			
			getCurrectLink('${model.equipment.netStation}');
		}
		function getCurrectLink(inrOrExt){
			var link = document.getElementById("linkName.info");
			if(inrOrExt=='i'){
				link.innerHTML = '<select name="linkName"><c:forEach var="item" items="${model.intLinks}">'+
				'<option value="${item.linkName}"<c:if test="${model.equipment.linkName==item.linkName}">selected</c:if>>${item.linkName}</option></c:forEach></select>';
			}else if(inrOrExt=='e'){
				link.innerHTML = '<select name="linkName"><c:forEach var="item" items="${model.extLinks}">'+
				'<option value="${item.linkName}"<c:if test="${model.equipment.linkName==item.linkName}">selected</c:if>>${item.linkName}</option></c:forEach></select>';
			}			
		}
	</script>
</head>
<body style="padding: 15px;">
<form id="editForm" name="editForm"
	action="<c:url value="/equConf.do?m=update"/>" enctype="multipart/form-data" method="post"><input
	type="hidden" name="id" value="${model.equipment.id}" />
<table width="100%" border="0" cellspacing="5" cellpadding="5"
	style="padding: 15px;">
	<tr>
		<td align="right" style="width:130px;">设备名称：</td>
		<td><input type="text" name="equName"
			value="${model.equipment.equName }" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">是否核心设备：</td>
		<td><input type="radio" name="isKeyDevice" value="1"
			<c:if test="${model.equipment.isKeyDevice=='1'}">checked</c:if> />是
		<input type="radio" name="isKeyDevice" value="0"
			<c:if test="${model.equipment.isKeyDevice=='0'}">checked</c:if> />否</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">设备类型：</td>
		<td><select name="equTypeCode">
			<c:forEach var="item" items="${model.equipmentTypeList}">
				<option value="${item.typeCode}"
					<c:if test="${model.equipment.equTypeCode==item.typeCode }">selected</c:if>>${item.typeName}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">设备图标：</td>
		<td>
		<p>
			<input type="radio" name="equIconCode" value="1001" <c:if test="${ model.equipment.equIconCode == 1001 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/1001S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="1002" <c:if test="${ model.equipment.equIconCode == 1002 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/1002S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="1003" <c:if test="${ model.equipment.equIconCode == 1003 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/1003S.PNG"/>" border=0 />&nbsp;&nbsp;		
			<input type="radio" name="equIconCode" value="1004" <c:if test="${ model.equipment.equIconCode == 1004 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/1004S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="1005" <c:if test="${ model.equipment.equIconCode == 1005 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/1005S.PNG"/>" border=0 />&nbsp;&nbsp;
			<br>
			<input type="radio" name="equIconCode" value="1006" <c:if test="${ model.equipment.equIconCode == 1006 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/1006S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="1008" <c:if test="${ model.equipment.equIconCode == 1008 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/1008S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="1009" <c:if test="${ model.equipment.equIconCode == 1009 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/1009S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="1010" <c:if test="${ model.equipment.equIconCode == 1010 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/1010S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="1011" <c:if test="${ model.equipment.equIconCode == 1011 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/1011S.PNG"/>" border=0 />&nbsp;&nbsp;
			<br>
			<input type="radio" name="equIconCode" value="1012" <c:if test="${ model.equipment.equIconCode == 1012 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/1012S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="1013" <c:if test="${ model.equipment.equIconCode == 1013 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/1013S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="1014" <c:if test="${ model.equipment.equIconCode == 1014 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/1014S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="2000" <c:if test="${ model.equipment.equIconCode == 2000 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/2000S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="2001" <c:if test="${ model.equipment.equIconCode == 2001 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/2001S.PNG"/>" border=0 />&nbsp;&nbsp;
			<br>
			<input type="radio" name="equIconCode" value="2002" <c:if test="${ model.equipment.equIconCode == 2002 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/2002S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="2003" <c:if test="${ model.equipment.equIconCode == 2003 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/2003S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="2004" <c:if test="${ model.equipment.equIconCode == 2004 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/2004S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="3001" <c:if test="${ model.equipment.equIconCode == 3001 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/3001S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="3002" <c:if test="${ model.equipment.equIconCode == 3002 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/3002S.PNG"/>" border=0 />&nbsp;&nbsp;
			<br>
			<input type="radio" name="equIconCode" value="3003" <c:if test="${ model.equipment.equIconCode == 3003 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/3003S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="3004" <c:if test="${ model.equipment.equIconCode == 3004 }">checked</c:if>/>
			<img src="<c:url value="/img/equ/3004S.PNG"/>" border=0 />&nbsp;&nbsp;
		</p>
		</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">网络位置：</td>
		<td><select name="netStation">
			<option value="e"
				<c:if test="${model.equipment.netStation=='e' }">selected</c:if>>外网</option>
			<option value="i"
				<c:if test="${model.equipment.netStation=='i' }">selected</c:if>>内网</option>
		</select></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">是否开启监控：</td>
		<td><input type="radio" name="monitorUsed" value="Y"
			<c:if test="${model.equipment.monitorUsed=='Y' }">checked</c:if> />是
		<input type="radio" name="monitorUsed" value="N"
			<c:if test="${model.equipment.monitorUsed=='N' }">checked</c:if> />否</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">首选IP：</td>
		<td id="ip.info">
		
		</td>
	</tr>
	<script>
		
		Ext.onReady(function() {
			new Ext.form.TextField({
				renderTo:'ip.info',
            	name:'ip',
            	value:'${model.equipment.ip}',
            	width:300,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
                regexText:'不规范(例):0.0.0.0~255.255.255.255',
                emptyText:'(例):1.1.1.1'
            });
            new Ext.form.TextField({
				renderTo:'otherIp.info',
            	name:'otherIp',
            	width:300,
            	value:'${model.equipment.otherIp}',
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^((((((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))\,)*(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])))||(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])))$/,
                regexText:'不规范(例):0.0.0.0,255.255.255.255',
                emptyText:'(例):1.1.1.1'
            });
            new Ext.form.TextField({
				renderTo:'mac.info',
            	name:'mac',
            	width:300,
            	value:'${model.equipment.mac}',
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^(([0-9a-fA-F]{2}\-){5}[0-9a-fA-F]{2})$/,
				regexText:'不规范(例):0a-45-be-e6-00-AF,必须是[a-fA-F]之间的字母或者数字',
				emptyText:'(例):0a-45-bE-e6-00-AF'
            });
            new Ext.form.TextField({
				renderTo:'subnetMask.info',
            	name:'subnetMask',
            	value:'${model.equipment.subnetMask}',
            	width:300,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
                regexText:'不规范(例):0.0.0.0~255.255.255.255',
                emptyText:'(例):1.1.1.1'
            });
		});
            function changeLinkName(value,id){
				var link = document.getElementById(id);
				if(value=='i'){
					link.innerHTML = '<select name="linkName"><c:forEach var="item" items="${model.intLinks}">'+
					'<option value="${item.linkName}">${item.linkName}</option></c:forEach></select>';
				}else if(value=='e'){
					link.innerHTML = '<select name="linkName"><c:forEach var="item" items="${model.extLinks}">'+
					'<option value="${item.linkName}">${item.linkName}</option></c:forEach></select>';
				}
			}
	</script>
	<tr>
		<td align="right" style="width:130px;">次选IP：</td>
		<td id="otherIp.info">
		
		</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">MAC地址：</td>
		<td id="mac.info">
	
		</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">子网掩码：</td>
		<td id="subnetMask.info">
		
		</td>
	</tr>
	
	<tr>
		<td align="right" style="width:130px;">链路位置：</td>
		<td><select name="inrOrExt" onchange="changeLinkName(this.value,'linkName.info');">
			<option value="e"
				<c:if test="${model.equipment.netStation=='e' }">selected</c:if>>外部链路</option>
			<option value="i"
				<c:if test="${model.equipment.netStation=='i' }">selected</c:if>>内部链路</option>
		</select></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">链路：</td>
		<td id="linkName.info"><select name="linkName"></select></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">设备位置：</td>
		<td><input type="text" name="equStation"
			value="${model.equipment.equStation }" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">设备硬件配置：</td>
		<td><input type="text" name="equInfo"
			value="${model.equipment.equInfo }" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">设备系统配置文件：</td>
		<td><input type="file" name="equSysConfig" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">设备管理单位：</td>
		<td><input type="text" name="equManagerDepart"
			value="${model.equipment.equManagerDepart }"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">生产厂家：</td>
		<td><input type="text" name="manufacturer"
			value="${model.equipment.manufacturer }" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">产品型号：</td>
		<td><input type="text" name="model"
			value="${model.equipment.model }" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">供货商：</td>
		<td><input type="text" name="provider"
			value="${model.equipment.provider }" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">联系电话：</td>
		<td><input type="text" name="equPhone"
			value="${model.equipment.equPhone }" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">其他联系方式：</td>
		<td><input type="text" name="otherPhone"
			value="${model.equipment.otherPhone }" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">购买日期：</td>
		<td><input type="text" name="buy_day"
			value="<fmt:formatDate value="${model.equipment.buyDay }" pattern="yyyy-MM-dd HH:mm:ss" />"
			class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">过保日期：</td>
		<td><input type="text" name="unrepair_day"
			value="<fmt:formatDate value="${model.equipment.unrepairDay }" pattern="yyyy-MM-dd HH:mm:ss" />"
			class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
			style="width: 300px;" /></td>
	</tr>
</table>
</form>
</body>
</html>
