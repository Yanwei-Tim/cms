<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>
<script type="text/javascript">
function init(){
	<c:forEach var="item" items="${model.role.permissions}">
		document.getElementById("p-${item.id}").checked=true;
	</c:forEach>
}
</script>
</head>
<body style="padding: 15px;">
<form id="roleForm" name="roleForm" action="<c:url value="/roleAdmin.do?m=updateRole"/>" method="post">
<input type="hidden" name="id" value="${model.role.id }"/>
<table width="100%" border="0" cellspacing="5" cellpadding="5" style="padding:15px;">
	<tr>
		<td>角色名</td>
		<td><input type="text" name="name" value="${model.role.name}" /></td>
		<td>描述</td>
		<td><input type="text" name="description" value="${model.role.description}"/></td>
	</tr>
	<tr>
		<td valign=top>权限</td>
		<td colspan=3>
		<c:forEach var="item" items="${model.permissions}">
			<div <c:if test="${item.parentId!=0}">style="margin-left:20px;"</c:if>><input type="checkbox" id="p-${item.id}" name="pid" value="${item.id}"  /><label>${item.name}&nbsp;</label></div>
		</c:forEach>
		</td>
	</tr>
</table>
</form>
</body>
</html>
