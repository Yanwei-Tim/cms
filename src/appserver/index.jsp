<%@page contentType="text/html;charset=utf-8"%>
<%@include file="/include/common.jsp"%>
<c:if test="${account==null}">
	<%response.sendRedirect("login.jsp");%>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>集中监控平台</title>
<script type="text/javascript" language="Javascript" src="<c:url value="/js/extux.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<c:url value="/js/index.jsp"/>"></script>
</head>
<body>
<DIV id=top-div>
<DIV id=funmenu><A onclick="setFrontPage();return false;"
	href="javascript:void(0);"><IMG src="img/house.png">设为首页</A>| <A
	onclick=window.external.AddFavorite(location.href,document.title);
	href="javascript:void(0);"><IMG src="img/page_white_office.png">加入收藏</A>|
<A onclick="showUpdatePwd();return false;" href="javascript:void(0);"><IMG
	src="img/key.png">修改密码</A>| <A onclick="logout();return false;"
	href="javascript:void(0);"><IMG src="img/door_out.png">退出系统</A></DIV>
</DIV>
<DIV id=left-div></DIV>
<DIV id=right-div-listOutBounds></DIV>
<DIV id=footer-div></DIV>
</body>
</html>