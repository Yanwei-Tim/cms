<%@page contentType="text/html;charset=utf-8"%>
<%@include file="/taglib.jsp"%>

<html version="-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
    <title>设备扫描</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <META http-equiv="x-ua-compatible" content="ie=EmulateIE7" />
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/js/ext/resources/css/ext-all.css"/>" />
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/js/ext/resources/css/ext-all-notheme.css"/>" />
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/js/ext/resources/css/xtheme-blue.css"/>" />
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/css/ext-patch.css"/>" />
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/css/index.css"/>" />
    <link rel="stylesheet" type="text/css"
          href="<c:url value="/css/icon.css"/>" />
    <script type="text/javascript" language="Javascript"
            src="<c:url value="/js/ext/adapter/ext/ext-base.js"/>"></script>
    <script type="text/javascript" language="Javascript"
    src="<c:url value="/js/ext/ext-all.js"/>"></script>
    <script type="text/javascript" language="Javascript"
            src="<c:url value="/js/ext/ext-lang-zh_CN.js"/>"></script>
    <script type="text/javascript" language="Javascript"
    src="<c:url value="/js/manager_deviceScan.js"/>"></script>
</head>

<body>
<div id="ColumnLayout" style="width: 800px;"></div>
<div id="selectContent" style="width: 800px;"></div>
<div id="grid" style="height:300px;width:800px;"></div>
<div id="mainPanel"></div>
<div id="mbx"></div>
</body>
</html>