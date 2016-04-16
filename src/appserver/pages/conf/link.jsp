<%@page contentType="text/html;charset=utf-8"%>
<%@include file="/taglib.jsp"%>

<html version="-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<title>链路配置</title>
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
<link href="<c:url value="/js/My97DatePicker/skin/default/datepicker.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="Javascript"
	src="<c:url value="/js/ext/adapter/ext/ext-base.js"/>"></script>
<script type="text/javascript" language="Javascript"
	src="<c:url value="/js/ext/ext-all.js"/>"></script>
<script type="text/javascript" language="Javascript"
	src="<c:url value="/js/ext/ext-lang-zh_CN.js"/>"></script>
<script language="javascript" type="text/javascript" src="<c:url value="/js/My97DatePicker/WdatePicker.js"/>"></script>
<script language="javascript" type="text/javascript">
Ext.onReady(function(){
	var tabsPanel = new Ext.TabPanel({
        region: 'center',
        activeTab: 0,
        plain:false,
        defaults:{autoScroll: true},
        tabPosition:'top',
        border:false,
        deferredRender: false,
        items:[{
                title: '内部链路设置',
                html: '<iframe width="100%" height="100%" frameborder="0" src="intLink.jsp"></iframe>'
            },{
                title: '外部链路设置',
               	html: '<iframe width="100%" height="100%" frameborder="0" src="extLink.jsp"></iframe>'
            }
        ]
    });

    var viewport = new Ext.Viewport({
		layout: 'border',
		loadMask: true,
		items: [tabsPanel]
	});
});
</script>
</head>

<body>

</body>
</html>