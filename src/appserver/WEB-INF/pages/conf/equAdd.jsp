<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>
	<script >
            function validate()
            {
                if(document.editform.equName.value=="")
                {
                    Ext.MessageBox.show({
                        title:'信息',
                        width:200,
                        msg:'设备名不能为空!',
                        buttons:Ext.MessageBox.OK,
                        buttons:{'ok':'确定'},
                        icon:Ext.MessageBox.ERROR,
                        closable:false
                    });
                }
            }
    </script>
</head>
<body style="padding: 15px;">
<form id="editForm" name="editform"
	action="<c:url value="/equConf.do?m=add"/>" enctype="multipart/form-data" method="post"  >
<table width="100%" border="0" cellspacing="5" cellpadding="5"
	style="padding: 15px;">
	<%--<tr>--%>
		<%--<td align="right" style="width:130px;">设备名称：</td>--%>
		<%--<td><input type="text" name="equName" value=""--%>
			<%--style="width: 300px;" onclick="validate();" /></td>--%>
	<%--</tr>--%>

    <tr>
        <td align="right" style="width:130px;">名称：</td>
        <td id="equName.info" onmouseout="validate()">

        </td>
    </tr>
    <script>

        Ext.onReady(function() {

            new Ext.form.TextField({
                renderTo:'equName.info',
                name:'equName',
                width:300,
                allowBlank:false,
                blankText:'设备名称不能为空！',
                regex : /^.{1,30}$/
            });
        });

    </script>

	<tr>
		<td align="right" style="width:130px;" >是否核心设备：</td>
		<td><input type="radio" name="isKeyDevice" value="1" checked />是
		<input type="radio" name="isKeyDevice" value="0" />否</td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">设备类型：</td>
		<td><select name="equTypeCode">
			<c:forEach var="item" items="${model.equipmentTypeList}">
				<option value="${item.typeCode}">${item.typeName}</option>
			</c:forEach>
		</select></td>
	</tr>
 
	<tr>
		<td align="right" style="width:130px;">设备图标：</td>
		<td>
		<p>
			<input type="radio" name="equIconCode" value="1001" checked />
			<img src="<c:url value="/img/equ/1001S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="1002" />
			<img src="<c:url value="/img/equ/1002S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="1003" />
			<img src="<c:url value="/img/equ/1003S.PNG"/>" border=0 />&nbsp;&nbsp;		
			<input type="radio" name="equIconCode" value="1004" />
			<img src="<c:url value="/img/equ/1004S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="1005" />
			<img src="<c:url value="/img/equ/1005S.PNG"/>" border=0 />&nbsp;&nbsp;
			<br>
			<input type="radio" name="equIconCode" value="1006" />
			<img src="<c:url value="/img/equ/1006S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="1008" />
			<img src="<c:url value="/img/equ/1008S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="1009" />
			<img src="<c:url value="/img/equ/1009S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="1010" />
			<img src="<c:url value="/img/equ/1010S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="1011" />
			<img src="<c:url value="/img/equ/1011S.PNG"/>" border=0 />&nbsp;&nbsp;
			<br>
			<input type="radio" name="equIconCode" value="1012" />
			<img src="<c:url value="/img/equ/1012S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="1013" />
			<img src="<c:url value="/img/equ/1013S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="1014" />
			<img src="<c:url value="/img/equ/1014S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="2000" />
			<img src="<c:url value="/img/equ/2000S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="2001" />
			<img src="<c:url value="/img/equ/2001S.PNG"/>" border=0 />&nbsp;&nbsp;
			<br>
			<input type="radio" name="equIconCode" value="2002" />
			<img src="<c:url value="/img/equ/2002S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="2003" />
			<img src="<c:url value="/img/equ/2003S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="2004" />
			<img src="<c:url value="/img/equ/2004S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="3001" />
			<img src="<c:url value="/img/equ/3001S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="3002" />
			<img src="<c:url value="/img/equ/3002S.PNG"/>" border=0 />&nbsp;&nbsp;
			<br>
			<input type="radio" name="equIconCode" value="3003" />
			<img src="<c:url value="/img/equ/3003S.PNG"/>" border=0 />&nbsp;&nbsp;
			<input type="radio" name="equIconCode" value="3004" />
			<img src="<c:url value="/img/equ/3004S.PNG"/>" border=0 />&nbsp;&nbsp;
		</p>
		</td>
	</tr>

	<tr>
		<td align="right" style="width:130px;">网络位置：</td>
		<td><select name="netStation">
			<option value="e">外网</option>
			<option value="i">内网</option>
		</select></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">是否开启监控：</td>
		<td><input type="radio" name="monitorUsed" value="Y" checked />是
		<input type="radio" name="monitorUsed" value="N" />否</td>
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
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^((((((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))\,)*(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])))||(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])))$/,
                regexText:'不规范(例):0.0.0.0~255.255.255.255',
                emptyText:'(例):1.1.1.1'
            });
            new Ext.form.TextField({
				renderTo:'mac.info',
            	name:'mac',
            	width:300,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^(([0-9a-fA-F]{2}\-){5}[0-9a-fA-F]{2})$/,
				regexText:'不规范(例):0a-45-be-e6-00-AF,必须是[a-fA-F]之间的字母或者数字',
				emptyText:'(例):0a-45-bE-e6-00-AF'
            });
            new Ext.form.TextField({
				renderTo:'subnetMask.info',
            	name:'subnetMask',
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
		<td><select id="intOrExt" name="inrOrExt" onchange="changeLinkName(this.value,'linkName.info');">
			<option value="e">外部链路</option>
			<option value="i">内部链路</option>
		</select></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">链路：</td>
		<td id="linkName.info"><select name="linkName">		
			<c:forEach var="item" items="${model.extLinks}">
				<option value="${item.linkName}">${item.linkName}</option></c:forEach>	
		</select></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">设备位置：</td>
		<td><input type="text" name="equStation" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">设备硬件配置：</td>
		<td><input type="text" name="equInfo" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">设备系统配置文件：</td>
		<td><input type="file" name="equSysConfig" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">设备管理单位：</td>
		<td><input type="text" name="equManagerDepart" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">生产厂家：</td>
		<td><input type="text" name="manufacturer" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">产品型号：</td>
		<td><input type="text" name="model" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">供货商：</td>
		<td><input type="text" name="provider" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">联系电话：</td>
		<td><input type="text" name="equPhone" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">其他联系方式：</td>
		<td><input type="text" name="otherPhone" value=""
			style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">购买日期：</td>
		<td><input type="text" name="buyDay" value="" class="Wdate"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 300px;" /></td>
	</tr>
	<tr>
		<td align="right" style="width:130px;">过保日期：</td>
		<td><input type="text" name="unrepairDay" value="" class="Wdate"
			onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 300px;" /></td>
	</tr>
    <tr>
        <td align="right" style="width:130px;">设备SNMPOID名称：</td>
        <td><select name="equOidName">
            <c:forEach var="item" items="${model.snmpOidList}">
                <option value="${item.name}">${item.name}</option>
            </c:forEach>
        </select></td>
    </tr>
    <tr>
        <td align="right" style="width:130px;">设备SNMP服务端口：</td>
        <td><input type="text" name="equPort" value=""
                   style="width: 300px;" /></td>
    </tr>
    <tr>
        <td align="right" style="width:130px;">设备SNMP服务密码：</td>
        <td><input type="text" name="equSnmpPwd" value=""
                   style="width: 300px;" /></td>
    </tr>
    <tr>
        <td align="right" style="width:130px;">SNMP版本：</td>
        <td><select name="equSnmpver">
            <option value="v1">SNMP v1</option>
            <option value="v2">SNMP v2</option>
            <option value="v2">SNMP v3</option>
        </select></td>
    </tr>
    <tr>
        <td align="right" style="width:130px;">拓扑图位置：</td>
        <td><select name="topologyStation">
            <option value="1">外网接入区</option>
            <option value="2">安全接入区</option>
            <option value="3">隔离区</option>
            <option value="4">公安信息网</option>
        </select></td>
    </tr>

</table>
</form>
</body>
</html>
