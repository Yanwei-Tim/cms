<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html;charset=utf-8"%>
<%@include file="/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集中监控系统</title>

<script language="JavaScript">
	function reloadVerifyCode() {
		document.getElementById('verifyCodeImg').src = "<c:url value="/RandomCodeCtrl"/>"+"?tmp="+ new Date().getTime();
	}
</script>
</head>
<body style="background-color: #0161B7">

<div
	style="width: 793px; height: 449px; border: 0px solid #000; margin: 0 auto; margin-top: 50px; background: url(img/login.jpg) no-repeat;">
<form action="<c:url value="/login.do"/>" method="post"
	style="margin: 0; padding: 0;border:0;">
<div style="padding: 250px 0 0 450px">
<table cellspacing=3 cellpadding=0 border=0>
	<tr>
		<td align=right>用户名：</td>
		<td><input type="text" name="name" style="width:150px;" /></td>
	</tr>
	<tr>
		<td align=right>密&nbsp;&nbsp;码：</td>
		<td><input type="password" name="pwd" style="width:150px;" /></td>
	</tr>
	<tr>
		<td align=right>验证码：</td>
		<td><INPUT TYPE="text" NAME="vcode" style="width: 80px;"><img
			src="<c:url value="/RandomCodeCtrl"/>" height="20" width="60"
			align="absmiddle" id="verifyCodeImg" onclick="reloadVerifyCode();"
			alt="单击更换验证码" style="cursor: hand;" /></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="登&nbsp;&nbsp;录" />&nbsp;&nbsp;<input
			type="reset" value="取&nbsp;&nbsp;消" /></td>
	</tr>
</table>
</div>
</form>
</div>
</body>
</html>

