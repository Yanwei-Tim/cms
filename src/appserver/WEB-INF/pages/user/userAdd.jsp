<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<form id="userForm" name="userForm" action="<c:url value="/userAdmin.do?m=addUser"/>" method="post">
<table width="100%" border="0" cellspacing="5" cellpadding="5" style="padding:15px;">
	<tr>
		<td>用户名</td>
		<td><input type="text" name="userName" id="userName" value="" /></td>
		<td>密码</td>
		<td><input type="password" name="password" id="password" value=""/></td>
	</tr>
	<tr>
		<td>真实姓名</td>
		<td><input type="text" name="name" id="name" value="" /></td>
		<td>性别</td>
		<td>
		<select name="sex">
			<option value="男">男</option>
			<option value="女">女</option>
		</select>
		</td>
	</tr>
	<tr>
		<td>电话</td>
		<td><input type="text" name="phone" value="" /></td>
		<td>邮箱</td>
		<td><input type="text" name="email" value=""/></td>
	</tr>
	<tr>
		<td>部门</td>
		<td><input type="text" name="depart" value="" /></td>
		<td>职务</td>
		<td><input type="text" name="title" value=""/></td>
	</tr>
	<tr>
		<td>开始IP</td>
		<td id="startIp.info"></td>
		<td>结束IP</td>
		<td id="endIp.info"></td>
	</tr>
	<script>
		Ext.onReady(function(){
			new Ext.form.TextField({
				renderTo:'startIp.info',
            	name:'startIp',
                id:'startIp',
            	width:153,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])||(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])-((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])))$/
//                regexText:'不规范(例):1.1.1.1-1.1.1.9或者1.1.1.5',
//                emptyText:'(例):1.1.1.1-1.1.1.9或者1.1.1.5'
            });
            new Ext.form.TextField({
				renderTo:'endIp.info',
            	name:'endIp',
                id:'endIp',
            	width:153,
            	allowBlank:false,
            	blankText:'该项不能为空！',
            	regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])||(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])-((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])))$/
//                regexText:'不规范(例):1.1.1.1-1.1.1.9或者1.1.1.5',
//                emptyText:'(例):1.1.1.1-1.1.1.9或者1.1.1.5'
            });
         });
    </script>
	<tr>
		<td>开始时间</td>
		<td><input type="text" name="startHour" value="" /></td>
		<td>结束时间</td>
		<td><input type="text" name="endHour" value=""/></td>
	</tr>
	<tr>
		<td valign=top>描述</td>
		<td><textarea name="description" style="width:200px;"></textarea></td>
		<td valign=top>状态</td>
		<td valign=top>
		<select name="status">
			<option value="有效">有效</option>
			<option value="无效">无效</option>
		</select>
		</td>
	</tr>
	<tr>
		<td>角色</td>
		<td colspan=3>
		<c:forEach var="item" items="${model.roles}">
			<%--<input type="checkbox" name="rid" id="rid" value="${item.id}"   /><label>${item.name}&nbsp;</label>--%>
			<input type="radio" name="rid" id="rid" value="${item.id}"  checked="true" /><label>${item.name}&nbsp;</label>
		</c:forEach>
		</td>
	</tr>
</table>
</form>
</body>
</html>
