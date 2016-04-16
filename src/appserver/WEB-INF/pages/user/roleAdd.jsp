<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>

</head>
<body style="padding: 15px;">
<form id="roleForm" name="roleForm" action="<c:url value="/roleAdmin.do?m=addRole"/>" method="post">
<table width="100%" border="0" cellspacing="5" cellpadding="5" style="padding:15px;">
	<tr>
		<td>角色名</td>
		<td><input type="text" name="name" value="" /></td>
		<td>描述</td>
		<td><input type="text" name="description" value=""/></td>
	</tr>
	<tr>
		<td valign=top>权限</td>
		<td colspan=3>
		<c:forEach var="item" items="${model.permissions}">
			<div <c:if test="${item.parentId!=0}">style="margin-left:20px;"</c:if>><input type="checkbox" name="pid" value="${item.id}" /><label>${item.name}&nbsp;</label></div>
		</c:forEach>
		</td>
	</tr>
</table>
</form>
</body>
</html>
