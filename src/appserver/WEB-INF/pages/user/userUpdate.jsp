<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>
<script>
function init(){
	<c:forEach var="item" items="${model.account.roles}">
		document.getElementById("r-${item.id}").checked=true;
	</c:forEach>
}
</script>
</head>
<body style="padding: 15px;" >
<form name="userForm" id="userForm" action="<c:url value="/userAdmin.do?m=updateUser"/>" method="post">
<input type="hidden" name="id" value="${model.account.id}" />
<table width="100%" border="0" cellspacing="5" cellpadding="5" style="padding:15px;">
	<tr>
		<td>用户名</td>
		<td><input type="text" name="userName" id="userName.inf" value="${model.account.userName }" /></td>
		<td>密码</td>
		<td><input type="password" name="password" value="${model.account.password }"/></td>
	</tr>
	<tr>
		<td>真实姓名</td>
		<td><input type="text" name="name" value="${model.account.name }" /></td>
		<td>性别</td>
		<td>
		<select name="sex">
			<option value="男" <c:if test="${model.account.sex=='男'}">selected</c:if>>男</option>
			<option value="女" <c:if test="${model.account.sex=='女'}">selected</c:if>>女</option>
		</select>
		</td>
	</tr>
	<tr>
		<td>电话</td>
		<td><input type="text" name="phone" value="${model.account.phone }" /></td>
		<td>邮箱</td>
		<td><input type="text" name="email" value="${model.account.email }"/></td>
	</tr>
	<tr>
		<td>部门</td>
		<td><input type="text" name="depart" value="${model.account.depart }" /></td>
		<td>职务</td>
		<td><input type="text" name="title" value="${model.account.title }"/></td>
	</tr>
	<tr>
		<td>开始IP</td>
		<td id="startHour.info"><input type="text" name="startIp" value="${model.account.startIp }" /></td>
		<td>结束IP</td>
		<td id="endHour.info"><input type="text" name="endIp" value="${model.account.endIp }"/></td>
	</tr>
	<tr>
		<td>开始时间</td>
		<td><input type="text" name="startHour" value="${model.account.startHour }" /></td>
		<td>结束时间</td>
		<td><input type="text" name="endHour" value="${model.account.endHour }"/></td>
	</tr>
	
	<tr>
		<td valign=top>描述</td>
		<td><textarea name="description" style="width:200px;">${model.account.description }</textarea></td>
		<td valign=top>状态</td>
		<td valign=top>
		<select name="status">
			<option value="有效" <c:if test="${model.account.status=='有效'}">selected</c:if>>有效</option>
			<option value="无效" <c:if test="${model.account.status=='无效'}">selected</c:if>>无效</option>
		</select>
		</td>
	</tr>
	<tr>
		<td>角色</td>
		<td colspan=3>
		<c:forEach var="item" items="${model.roles}">
			<input type="checkbox" id="r-${item.id}" name="rid" value="${item.id}" /><label>${item.name}&nbsp;</label>
		</c:forEach>
		</td>
	</tr>
</table>
</form>
</body>
</html>
