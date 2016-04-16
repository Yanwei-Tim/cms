<%@page contentType="text/javascript;charset=UTF-8"%>
<%@include file="/taglib.jsp"%>
var centerPanel;
var idx;
var idx2;
Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
	var imagePath = 'js/ext/resources/images';
	Ext.BLANK_IMAGE_URL = imagePath + '/default/tree/s.gif';
	var menu_root_node_1 = new Ext.tree.TreeNode({
		text : '运行监控',
		expanded : true
	});
	var menu_root_node_2 = new Ext.tree.TreeNode({
		text : '审计管理',
		expanded : false
	});
	var menu_root_node_3 = new Ext.tree.TreeNode({
		text : '报警管理',
		expanded : false
	});
	var menu_root_node_4 = new Ext.tree.TreeNode({
		text : '统计报表',
		expanded : false
	});
	var menu_root_node_5 = new Ext.tree.TreeNode({
		text : '级联上报',
		expanded : false
	});
	var menu_root_node_6 = new Ext.tree.TreeNode({
		text : '权限管理',
		expanded : false
	});
	var menu_root_node_7 = new Ext.tree.TreeNode({
		text : '配置管理',
		expanded : false
	});
	var menu_root_node_8 = new Ext.tree.TreeNode({
		text : '终端管理',
		expanded : false
	});
    var menu_root_node_9 = new Ext.tree.TreeNode({
		text : '网络管理',
		expanded : false
	});
	var menu_root_node_10 = new Ext.tree.TreeNode({
		text : '系统管理',
		expanded : false
	});
	var mrn_1_1 = new Ext.tree.TreeNode({
		id : 'mrn_1_1',
		text : '业务运行监控',
		leaf : true,
		url : 'pages/monitor/bizIndex.jsp'
	});
	var mrn_1_2 = new Ext.tree.TreeNode({
		id : 'mrn_1_2',
		text : '设备运行监控',
		leaf : true,
		url : 'pages/monitor/equIndex.jsp'
	});

	var mrn_1_3 = new Ext.tree.TreeNode({
		id : 'mrn_1_3',
		text : '终端用户运行监控',
		leaf : true,
		url : 'pages/monitor/terminalIndex.jsp'
	});
    var mrn_1_4 = new Ext.tree.TreeNode({
        id : 'mrn_1_4',
        text : 'Vpn运行监控',
        leaf : true,
        url : 'pages/monitor/vpnMonitor.jsp'
    });

    var mrn_1_5 = new Ext.tree.TreeNode({
        id : 'mrn_1_5',
        text : 'Ca证书管理',
        leaf : true,
        url : 'pages/monitor/caManager.jsp'
    });

    var mrn_1_6 = new Ext.tree.TreeNode({
        id : 'mrn_1_6',
        text : '事件回查',
        leaf : true,
        url : 'js/zxces/grouping.jsp'
    });
    var mrn_1_7 = new Ext.tree.TreeNode({
        id : 'mrn_1_7',
        text : '自动拓扑',
        leaf : true,
        <%--url : 'svgworkv.jsp'--%>
        url : 'pages/monitor/svgTopology.jsp'
    });
    var mrn_1_8 = new Ext.tree.TreeNode({
        id : 'mrn_1_8',
        text : '视频运行监控',
        leaf : true,
        <%--url : 'svgworkv.jsp'--%>
        url : 'pages/monitor/visMonitor.jsp'
    });




	<lbs:access code="SECOND_YWYXJK" >
	menu_root_node_1.appendChild(mrn_1_1) ;
	</lbs:access>
	<lbs:access code="SECOND_SBYXJK" >
	menu_root_node_1.appendChild(mrn_1_2) ;
	</lbs:access>
	<lbs:access code="SECOND_ZDYHYXJK" >
	menu_root_node_1.appendChild(mrn_1_3) ;
	</lbs:access>
    <lbs:access code="SECOND_ZDYHYXJK" >
    menu_root_node_1.appendChild(mrn_1_4) ;
    </lbs:access>
    <lbs:access code="SECOND_ZDYHYXJK" >
    menu_root_node_1.appendChild(mrn_1_5) ;
    </lbs:access>
    <lbs:access code="SECOND_ZDYHYXJK" >
    menu_root_node_1.appendChild(mrn_1_6) ;
    </lbs:access>
    <lbs:access code="SECOND_ZDYHYXJK" >
    menu_root_node_1.appendChild(mrn_1_7) ;
    </lbs:access>
    <lbs:access code="SECOND_ZDYHYXJK" >
    menu_root_node_1.appendChild(mrn_1_8) ;
    </lbs:access>
	
	var mrn_2_1 = new Ext.tree.TreeNode({
		id: 'mrn_2_1',
		text: '系统日志审计',
		leaf: true ,
		url: 'admin/forward.do?m=sysLog' 
	}) ;
	var mrn_2_2 = new Ext.tree.TreeNode({
		id: 'mrn_2_2',
		text: '用户日志审计',
		leaf: true ,
		url: 'admin/forward.do?m=userLog' 
	}) ;
	var mrn_2_3 = new Ext.tree.TreeNode({
		id: 'mrn_2_3',
		text: '业务日志审计',
		leaf: true ,
		url: 'admin/forward.do?m=businessLog' 
	}) ;
	var mrn_2_4 = new Ext.tree.TreeNode({
		id: 'mrn_2_4',
		text: '设备日志审计',
		leaf: true ,
		url: 'admin/forward.do?m=equipmentLog' 
	}) ;
	<lbs:access code="SECOND_XTRZSJ" >
	menu_root_node_2.appendChild(mrn_2_1) ;
	</lbs:access>
	<lbs:access code="SECOND_YHRZSJ">
	menu_root_node_2.appendChild(mrn_2_2) ;
	</lbs:access>
	<lbs:access code="SECOND_YWRZSJ">
	menu_root_node_2.appendChild(mrn_2_3) ;
	</lbs:access>
	<lbs:access code="SECOND_SBRZSJ">
	menu_root_node_2.appendChild(mrn_2_4) ;
	</lbs:access>
	
	var mrn_3_1 = new Ext.tree.TreeNode({
		id: 'mrn_3_1',
		text: '邮件报警配置',
		leaf: true ,
		url: 'admin/forward.do?m=alertconfig' 
	}) ;
    var mrn_3_2 = new Ext.tree.TreeNode({
        id: 'mrn_3_2',
        text: '短信报警配置',
        leaf: true ,
        url: 'http://localhost:8000/mobile'
    }) ;

	var mrn_3_3 = new Ext.tree.TreeNode({
		id: 'mrn_3_3',
		text: '业务异常报警',
		leaf: true ,
		url: 'admin/forward.do?m=bsalert' 
	}) ;
	var mrn_3_4 = new Ext.tree.TreeNode({
		id: 'mrn_4_4',
		text: '安全事件报警',
		leaf: true ,
		url: 'admin/forward.do?m=scalert' 
	}) ;
	var mrn_3_5 = new Ext.tree.TreeNode({
		id: 'mrn_3_5',
		text: '设备故障报警',
		leaf: true ,
		url: 'admin/forward.do?m=eqalert' 
	}) ;
	<lbs:access code="SECOND_BJSZ">
	menu_root_node_3.appendChild(mrn_3_1) ;
	</lbs:access>
	<lbs:access code="SECOND_YWYCBJ">
	menu_root_node_3.appendChild(mrn_3_2) ;
	</lbs:access>
	<lbs:access code="SECOND_AQSJBJ">
	menu_root_node_3.appendChild(mrn_3_3) ;
	</lbs:access>
	<lbs:access code="SECOND_SBGZBJ">
	menu_root_node_3.appendChild(mrn_3_4) ;
	</lbs:access>
    <lbs:access code="SECOND_SBGZBJ">
    menu_root_node_3.appendChild(mrn_3_5) ;
    </lbs:access>
	
	var mrn_4_1 = new Ext.tree.TreeNode({
		id: 'mrn_4_1',
		text: '业务日统计表',
		leaf: true ,
		url: 'admin/forward.do?m=bdDayReport'
	}) ;
	var mrn_4_2 = new Ext.tree.TreeNode({
		id: 'mrn_4_2',
		text: '业务月统计表',
		leaf: true ,
		url: 'admin/forward.do?m=bdMonthReport'
	}) ;
	var mrn_4_3 = new Ext.tree.TreeNode({
		id: 'mrn_4_3',
		text: '业务年统计表',
		leaf: true ,
		url: 'admin/forward.do?m=bdYearReport'
	}) ;
	var mrn_4_4 = new Ext.tree.TreeNode({
		id: 'mrn_4_4',
		text: '设备日统计表',
		leaf: true ,
		url: 'admin/forward.do?m=eqDayReport'
	}) ;
	var mrn_4_5 = new Ext.tree.TreeNode({
		id: 'mrn_4_5',
		text: '设备月统计表',
		leaf: true ,
		url: 'admin/forward.do?m=eqMonthReport'
	}) ;
	var mrn_4_6 = new Ext.tree.TreeNode({
		id: 'mrn_4_6',
		text: '设备年统计表',
		leaf: true ,
		url: 'admin/forward.do?m=eqYearReport'
	}) ;
	var mrn_4_7 = new Ext.tree.TreeNode({
		id: 'mrn_4_7',
		text: '终端用户访问日统计表',
		leaf: true ,
		url: 'admin/forward.do?m=terminalUserAccessReport'
	}) ;
	var mrn_4_8 = new Ext.tree.TreeNode({
		id: 'mrn_4_8',
		text: '终端用户运行日统计表',
		leaf: true ,
		url: 'admin/forward.do?m=sysTerminalStatusReport'
	}) ;
    var mrn_4_9 = new Ext.tree.TreeNode({
        id: 'mrn_4_9',
        text: '违规日统计表',
        leaf: true ,
        url: 'admin/forward.do?m=abnormalReportDay'
    }) ;
    var mrn_4_10 = new Ext.tree.TreeNode({
        id: 'mrn_4_10',
        text: '违规月统计表',
        leaf: true ,
        url: 'admin/forward.do?m=abnormalReportMonth'
    }) ;
    var mrn_4_11 = new Ext.tree.TreeNode({
        id: 'mrn_4_11',
        text: '违规年统计表',
        leaf: true ,
        url: 'admin/forward.do?m=abnormalReportYear'
    }) ;
	<lbs:access code="SECOND_YWRTJB">
	menu_root_node_4.appendChild(mrn_4_1) ;
	</lbs:access>
	<lbs:access code="SECOND_YWYTJB">
	menu_root_node_4.appendChild(mrn_4_2) ;
	</lbs:access>
	<lbs:access code="SECOND_YWNTJB">
	menu_root_node_4.appendChild(mrn_4_3) ;
	</lbs:access>
	<lbs:access code="SECOND_SBRTJB">
	menu_root_node_4.appendChild(mrn_4_4) ;
	</lbs:access>
	<lbs:access code="SECOND_SBYTJB">
	menu_root_node_4.appendChild(mrn_4_5) ;
	</lbs:access>
	<lbs:access code="SECOND_SBNTJB">
	menu_root_node_4.appendChild(mrn_4_6) ;
	</lbs:access>
    <lbs:access code="SECOND_ZDYHFWRTJB">
    menu_root_node_4.appendChild(mrn_4_7) ;
    </lbs:access>
    <lbs:access code="SECOND_ZDYHYXRTJB">
    menu_root_node_4.appendChild(mrn_4_8) ;
    </lbs:access>
    <lbs:access code="SECOND_WGRTJB">
    menu_root_node_4.appendChild(mrn_4_9) ;
    </lbs:access>
    <lbs:access code="SECOND_WGYTJB">
    menu_root_node_4.appendChild(mrn_4_10) ;
    </lbs:access>
    <lbs:access code="SECOND_WGNTJB">
    menu_root_node_4.appendChild(mrn_4_11) ;
    </lbs:access>

	
	var mrn_5_1 = new Ext.tree.TreeNode({
		id: 'mrn_5_1',
		text: '上报配置',
		leaf: true ,
		url: 'pages/conf/sbpz.jsp' 
	}) ;
	var mrn_5_2 = new Ext.tree.TreeNode({
		id: 'mrn_5_2',
		text: '上级平台配置',
		leaf: true ,
		url: 'admin/forward.do?m=parentEp' 
	}) ;
	var mrn_5_3 = new Ext.tree.TreeNode({
		id: 'mrn_5_3',
		text: '本级系统状态',
		leaf: true ,
		url: 'admin/forward.do?m=sysruntime' 
	}) ;
	var mrn_5_4 = new Ext.tree.TreeNode({
		id: 'mrn_5_4',
		text: '本级系统违规情况',
		leaf: true ,
		url: 'admin/forward.do?m=sysabnormal' 
	}) ;
	var mrn_5_5 = new Ext.tree.TreeNode({
		id: 'mrn_5_5',
		text: '本级系统运行情况',
		leaf: true ,
		url: 'admin/forward.do?m=sysstatus' 
	}) ;
	var mrn_5_6 = new Ext.tree.TreeNode({
		id: 'mrn_5_6',
		text: '本级接入应用运行情况',
		leaf: true ,
		url: 'admin/forward.do?m=sysbizstatus' 
	}) ;
	var mrn_5_7 = new Ext.tree.TreeNode({
		id: 'mrn_5_7',
		text: '下级平台配置',
		leaf: true ,
		url: 'admin/forward.do?m=provinceEp' 
	}) ;
	var mrn_5_8 = new Ext.tree.TreeNode({
		id: 'mrn_5_8',
		text: '下级平台系统状态',
		leaf: true ,
		url: 'admin/forward.do?m=lowerSysruntime' 
	}) ;
	var mrn_5_9 = new Ext.tree.TreeNode({
		id: 'mrn_5_9',
		text: '下级平台违规情况',
		leaf: true ,
		url: 'admin/forward.do?m=lowerSysabnormal' 
	}) ;
	var mrn_5_10 = new Ext.tree.TreeNode({
		id: 'mrn_5_10',
		text: '下级平台系统运行情况',
		leaf: true ,
		url: 'admin/forward.do?m=lowerSysstatus' 
	}) ;
	var mrn_5_11 = new Ext.tree.TreeNode({
		id: 'mrn_5_11',
		text: '下级平台接入应用运行情况',
		leaf: true ,
		url: 'admin/forward.do?m=lowerSysbizstatus' 
	}) ;
	var mrn_5_12 = new Ext.tree.TreeNode({
		id: 'mrn_5_12',
		text: '平台注册信息手动上报',
		leaf: true ,
		url: 'pages/conf/uplink.jsp' 
	}) ;
    var mrn_5_13 = new Ext.tree.TreeNode({
		id: 'mrn_5_13',
		text: '已上报项管理',
		leaf: true ,
		url: 'pages/conf/uplinkManager.jsp'
	}) ;
	
	<%--menu_root_node_5.appendChild(mrn_5_1) ;--%>
	menu_root_node_5.appendChild(mrn_5_2) ;
	menu_root_node_5.appendChild(mrn_5_3) ;
	menu_root_node_5.appendChild(mrn_5_4) ;
	menu_root_node_5.appendChild(mrn_5_5) ;
	menu_root_node_5.appendChild(mrn_5_6) ;
	menu_root_node_5.appendChild(mrn_5_7) ;
	menu_root_node_5.appendChild(mrn_5_8) ;
	menu_root_node_5.appendChild(mrn_5_9) ;
	menu_root_node_5.appendChild(mrn_5_10) ;
	menu_root_node_5.appendChild(mrn_5_11) ;
	menu_root_node_5.appendChild(mrn_5_12) ;
	menu_root_node_5.appendChild(mrn_5_13) ;

	var mrn_6_1 = new Ext.tree.TreeNode({
		id: 'mrn_6_1',
		text: '用户管理',
		leaf: true ,
		url: 'pages/user/userIndex.jsp' 
	}) ;
	var mrn_6_2 = new Ext.tree.TreeNode({
		id: 'mrn_6_2',
		text: '角色管理',
		leaf: true ,
		url: 'pages/user/roleIndex.jsp'
	}) ;
	<lbs:access code="SECOND_YHGL">
	menu_root_node_6.appendChild(mrn_6_1) ;
	</lbs:access>
	<lbs:access code="SECOND_JSGL">
	menu_root_node_6.appendChild(mrn_6_2) ;
	</lbs:access>
	
	var mrn_7_1 = new Ext.tree.TreeNode({
		id: 'mrn_7_1',
		text: '审计库管理',
		leaf: true ,
		url: 'admin/forward.do?m=logConf'
	}) ;
	var mrn_7_2 = new Ext.tree.TreeNode({
		id: 'mrn_7_2',
		text: '基本配置',
		leaf: true ,
		url: 'pages/conf/base.jsp' 
	}) ;
	var mrn_7_3 = new Ext.tree.TreeNode({
		id: 'mrn_7_3',
		text: '链路配置',
		leaf: true ,
		url: 'pages/conf/link.jsp' 
	}) ;
	var mrn_7_4 = new Ext.tree.TreeNode({
		id: 'mrn_7_4',
		text: '交换平台配置',
		leaf: true ,
		url: 'pages/conf/ep.jsp' 
	}) ;
	var mrn_7_5 = new Ext.tree.TreeNode({
		id: 'mrn_7_5',
		text: '探针通道',
		leaf: true ,
		url: 'pages/conf/ma.jsp' 
	}) ;
	var mrn_7_6 = new Ext.tree.TreeNode({
		id: 'mrn_7_6',
		text: '审计备份策略',
		leaf: true ,
		url: 'pages/conf/backup.jsp' 
	}) ;
	var mrn_7_7 = new Ext.tree.TreeNode({
		id: 'mrn_7_7',
		text: '设备管理配置',
		leaf: true ,
		url: 'pages/conf/manager_equipment.jsp'
	}) ;
	var mrn_7_8 = new Ext.tree.TreeNode({
		id: 'mrn_7_8',
		text: '业务注册管理',
		leaf: true ,
		<%--url: 'pages/conf/biz.jsp' --%>
		url: 'pages/conf/bizConf.jsp'
	}) ;
	var mrn_7_9 = new Ext.tree.TreeNode({
		id: 'mrn_7_9',
		text: '控制策略注册管理',
		leaf: true ,
		url: 'pages/sysinf/sysControlRulesInf.jsp' 
	}) ;
	var mrn_7_10 = new Ext.tree.TreeNode({
		id: 'mrn_7_10',
		text: '安全策略注册管理',
		leaf: true ,
		url: 'pages/sysinf/sysStrategyInf.jsp' 
	}) ;
	var mrn_7_11 = new Ext.tree.TreeNode({
		id: 'mrn_7_11',
		text: '违规处理策略注册管理',
		leaf: true ,
		url: 'pages/sysinf/sysAbnormalInf.jsp' 
	}) ;
    var mrn_7_12 = new Ext.tree.TreeNode({
        id: 'mrn_7_12',
        text: 'SNMPOID管理',
        leaf: true ,
        url: 'pages/sysinf/snmpOid.jsp'
    }) ;

    <%--var mrn_7_13 = new Ext.tree.TreeNode({--%>
        <%--id: 'mrn_7_13',--%>
        <%--text: 'Ca ldap 导入配置',--%>
        <%--leaf: true ,--%>
        <%--url: 'pages/conf/sysConfig.jsp'--%>
    <%--}) ;--%>


	<lbs:access code="SECOND_SJKGL">
	menu_root_node_7.appendChild(mrn_7_1) ;
	</lbs:access>
	<lbs:access code="SECOND_JBPZ">
	menu_root_node_7.appendChild(mrn_7_2) ;
	</lbs:access>
	<lbs:access code="SECOND_LLPZ">
	menu_root_node_7.appendChild(mrn_7_3) ;
	</lbs:access>
	<lbs:access code="SECOND_JHPTPZ">
	menu_root_node_7.appendChild(mrn_7_4) ;
	</lbs:access>
	<lbs:access code="SECOND_TZTD">
	menu_root_node_7.appendChild(mrn_7_5) ;
	</lbs:access>
	<lbs:access code="SECOND_SJBFCL">
	menu_root_node_7.appendChild(mrn_7_6) ;
	</lbs:access>
	<lbs:access code="SECOND_SBGLPZ">
	menu_root_node_7.appendChild(mrn_7_7) ;
	</lbs:access>
	<lbs:access code="SECOND_YWZCGL">
	menu_root_node_7.appendChild(mrn_7_8) ;
	</lbs:access>
	<lbs:access code="SECOND_KZCEZCGL">
	menu_root_node_7.appendChild(mrn_7_9) ;
	</lbs:access>
	<lbs:access code="SECOND_AQCLZCGL">
	menu_root_node_7.appendChild(mrn_7_10) ;
	</lbs:access>
	<lbs:access code="SECOND_WGCLCLZC">
	menu_root_node_7.appendChild(mrn_7_11) ;
	</lbs:access>
    <lbs:access code="SECOND_WGCLCLZC">
    menu_root_node_7.appendChild(mrn_7_12) ;
    </lbs:access>

    var mrn_9_1 = new Ext.tree.TreeNode({
        id: 'mrn_9_1',
        text: '接口管理',
        leaf: true ,
        url: 'pages/system/manager_interface.jsp'
    }) ;
    var mrn_9_2 = new Ext.tree.TreeNode({
        id: 'mrn_9_2',
        text: '连通测试',
        leaf: true ,
        url: 'pages/system/manager_ping.jsp'
    }) ;
    var mrn_9_3 = new Ext.tree.TreeNode({
        id: 'mrn_9_3',
        text: '端口测试',
        leaf: true ,
        url: 'pages/system/manager_telnet.jsp'
    }) ;
    var mrn_9_4 = new Ext.tree.TreeNode({
        id: 'mrn_9_4',
        text: '路由管理',
        leaf: true ,
        url: 'pages/system/manager_router.jsp'
    }) ;

    var mrn_9_5 = new Ext.tree.TreeNode({
        id: 'mrn_9_5',
        text: '设备扫描',
        leaf: true ,
        url: 'pages/system/manager_deviceScan.jsp'
    }) ;

    <lbs:access code="SECOND_JKGL">
    menu_root_node_9.appendChild(mrn_9_1) ;
    </lbs:access>
    <lbs:access code="SECOND_LTCS">
    menu_root_node_9.appendChild(mrn_9_2) ;
    </lbs:access>
    <lbs:access code="SECOND_DKCS">
    menu_root_node_9.appendChild(mrn_9_3) ;
    </lbs:access>
    <lbs:access code="SECOND_LYGL">
    menu_root_node_9.appendChild(mrn_9_4) ;
    </lbs:access>
    <lbs:access code="SECOND_LYGL">
    menu_root_node_9.appendChild(mrn_9_5) ;
    </lbs:access>
    var mrn_10_1 = new Ext.tree.TreeNode({
        id: 'mrn_10_1',
        text: '平台管理',
        leaf: true ,
        url: 'pages/system/manager_platform.jsp'
    }) ;
    var mrn_10_2 = new Ext.tree.TreeNode({
        id: 'mrn_10_2',
        text: '安全配置',
        leaf: true ,
        url: 'pages/system/manager_config.jsp'
    }) ;
    var mrn_10_3 = new Ext.tree.TreeNode({
        id: 'mrn_10_3',
        text: '版本升级',
        leaf: true ,
        url: 'pages/system/manager_upgrade_version.jsp'
    }) ;
    var mrn_10_4 = new Ext.tree.TreeNode({
        id: 'mrn_10_4',
        text: '日志下载',
        leaf: true ,
        url: 'pages/system/manager_downloadLog.jsp'
    }) ;
    <lbs:access code="SECOND_PTGL">
    menu_root_node_10.appendChild(mrn_10_1) ;
    </lbs:access>
    <lbs:access code="SECOND_PZGL">
    menu_root_node_10.appendChild(mrn_10_2) ;
    </lbs:access>
    <lbs:access code="SECOND_BBSJ">
    menu_root_node_10.appendChild(mrn_10_3) ;
    </lbs:access>
    <lbs:access code="SECOND_RZXZ">
    menu_root_node_10.appendChild(mrn_10_4) ;
    </lbs:access>
	var mrn_8_1 = new Ext.tree.TreeNode({
		id: 'mrn_8_1',
		text: '在线用户管理',
		leaf: true ,
		url: 'admin/forward.do?m=tenimalListOnline' 
	}) ;
	var mrn_8_2 = new Ext.tree.TreeNode({
		id: 'mrn_8_2',
		text: '全部用户列表',
		leaf: true ,
		url: 'admin/forward.do?m=tenimalList' 
	}) ;
	var mrn_8_3 = new Ext.tree.TreeNode({
		id: 'mrn_8_3',
		text: '终端设备管理',
		leaf: true ,
		url: 'admin/forward.do?m=sysTerminalConf' 
	}) ;
	var mrn_8_4 = new Ext.tree.TreeNode({
		id: 'mrn_8_4',
		text: '用户管理审计',
		leaf: true ,
		url: 'admin/forward.do?m=terminalOperAudit' 
	}) ;
	var mrn_8_5 = new Ext.tree.TreeNode({
		id: 'mrn_8_5',
		text: '用户访问审计',
		leaf: true ,
		url: 'admin/forward.do?m=terminalAccessAudit' 
	}) ;
    var mrn_8_6 = new Ext.tree.TreeNode({
        id: 'mrn_8_6',
        text: '终端用户访问URL',
        leaf: true ,
        url: 'admin/forward.do?m=terminalAccessUrl'
    }) ;
    var mrn_8_7 = new Ext.tree.TreeNode({
        id: 'mrn_8_7',
        text: '违规查询',
        leaf: true ,
        url: 'pages/abnormal/abnormal.jsp'
    }) ;
	menu_root_node_8.appendChild(mrn_8_1) ;
	menu_root_node_8.appendChild(mrn_8_2) ;
	menu_root_node_8.appendChild(mrn_8_3) ;
	menu_root_node_8.appendChild(mrn_8_4) ;
	menu_root_node_8.appendChild(mrn_8_5) ;
	menu_root_node_8.appendChild(mrn_8_6) ;
	menu_root_node_8.appendChild(mrn_8_7) ;


	var tree_menu_1 = new Ext.tree.TreePanel({
		border: false,
		root: menu_root_node_1,
		rootVisible: false,
		listeners: {
			click: nodeClick
		}
	});
	var tree_menu_2 = new Ext.tree.TreePanel({
		border: false,
		root: menu_root_node_2,
		rootVisible: false,
		listeners: {
			click: nodeClick
		}
	});
	var tree_menu_3 = new Ext.tree.TreePanel({
		border: false,
		root: menu_root_node_3,
		rootVisible: false,
		listeners: {
			click: nodeClick
		}
	});
	var tree_menu_4 = new Ext.tree.TreePanel({
		border: false,
		root: menu_root_node_4,
		rootVisible: false,
		listeners: {
			click: nodeClick
		}
	});
	var tree_menu_5 = new Ext.tree.TreePanel({
		border: false,
		root: menu_root_node_5,
		rootVisible: false,
		listeners: {
			click: nodeClick
		}
	});
	var tree_menu_6 = new Ext.tree.TreePanel({
		border: false,
		root: menu_root_node_6,
		rootVisible: false,
		listeners: {
			click: nodeClick
		}
	});
	var tree_menu_7= new Ext.tree.TreePanel({
		border: false,
		root: menu_root_node_7,
		rootVisible: false,
		listeners: {
			click: nodeClick
		}
	});
	var tree_menu_8= new Ext.tree.TreePanel({
		border: false,
		root: menu_root_node_8,
		rootVisible: false,
		listeners: {
			click: nodeClick
		}
	});
    var tree_menu_9= new Ext.tree.TreePanel({
		border: false,
		root: menu_root_node_9,
		rootVisible: false,
		listeners: {
			click: nodeClick
		}
	});
    var tree_menu_10= new Ext.tree.TreePanel({
		border: false,
		root: menu_root_node_10,
		rootVisible: false,
		listeners: {
			click: nodeClick
		}
	});
	
	function nodeClick(node, e){
		if (node.isLeaf()){
			var _url = node.attributes.url ;
			if (_url != ''){
				if(_url.indexOf('?')>0)
					_url += '&time=' + new Date().getTime();
				else
					_url += '?time=' + new Date().getTime();
			}
			var _tab = centerPanel.getComponent(node.id) ;
			if(!_tab){
				centerPanel.add({
					id: node.id ,
					title: node.text ,
					closable: true ,
					iconCls: node.attributes.iconCls, 
					html: '<iframe id="frame_'+node.id+'" width="100%" height="100%" frameborder="0" src="'+_url+'"></iframe>'
				}) ;
			}
			centerPanel.setActiveTab(node.id) ;
		}
	}
	
	var northBar = new Ext.Toolbar({
		id: 'northBar',
		items: [{xtype: 'tbtext',
				id: 'msg_title', 
				text: ''
			},{
				xtype: "tbfill"
			},{	
				id:'warningMsg',
				iconCls: 'warning',
				hidden:true,
				handler:function(){
					eastPanel.expand(true);
				}
			},{
			 	xtype: 'tbseparator'
			},{
				pressed:false,
			 	text:'刷新',
			 	iconCls: 'refresh', 
			 	handler: function(){
			 		var mID = centerPanel.getActiveTab().getId();
                    if(centerPanel.getActiveTab().getStateId()==null){
                        window.frames[0].location.reload();
                    }else{
                    	window.parent.document.getElementById('frame_'+mID).contentWindow.location.reload();
                    }
			 	}
			},{
			 	xtype: 'tbseparator'
			},{
			 	pressed:false,
			 	text:'帮助',
			 	iconCls: 'help', 
			 	handler: function(){
			 		window.open('help.doc');
			 	}
			 /*},{
			 	xtype: 'tbseparator'
			},{
			 	xtype:"combo",
			 	width: 120*/
			}
		]
	});
	
	//页面的上部分
	var northPanel=new Ext.Panel({
		region : 'north',			//北部，即顶部，上面
    	contentEl : 'top-div',		//面板包含的内容
		split : false,
	    titlebar: false,
		border: false, 				//是否显示边框
		collapsible: false, 		//是否可以收缩,默认不可以收缩，即不显示收缩箭头
		height : 86,
		bbar: northBar   
	});
	
	//左边菜单
	var  westPanel=new Ext.Panel({
		title : '系统功能',			//面板名称
		region : 'west',			//该面板的位置，此处是西部 也就是左边
		split : true,				//为true时，布局边框变粗 ,默认为false
	    titlebar: true,
	    collapsible: true,
	    animate: true,
		border : true,
		bodyStyle: 'border-bottom: 0px solid;',
		bodyborder: true,
		width : 200,
		minSize : 100,				//最小宽度
		maxSize : 300,
		layout : 'accordion',
		iconCls : 'title-1',
		layoutConfig : { 			//布局
			titleCollapse : true,
			animate : true
		},
		items : [
			<lbs:access code="TOP_YXJK" >
			{
				title: '运行监控',
				border: false,
				bodyStyle: 'border-bottom: 1px solid;padding-top: 5px;padding-left: 15px;',
				iconCls:'yxjg',
				items: [tree_menu_1]
			},
			</lbs:access>
			<lbs:access code="TOP_SJGL" >
			{
				title: '审计管理',
				border: false,
				iconCls:'sjgl',
				bodyStyle: 'border-bottom: 1px solid;padding-top: 5px;padding-left: 15px;',
				items: [tree_menu_2]
			},
			</lbs:access>
			<lbs:access code="TOP_BJGL" >
			{
				title: '报警管理',
				border: false,
				iconCls:'bjgl',
				bodyStyle: 'border-bottom: 1px solid;padding-top: 5px;padding-left: 15px;',
				items: [tree_menu_3]
			},
			</lbs:access>
			<lbs:access code="TOP_TJBB" >
			{
				title: '统计报表',
				border: false,
				iconCls:'tjbb',
				bodyStyle: 'border-bottom: 1px solid;padding-top: 5px;padding-left: 15px;',
				items: [tree_menu_4]
			},
			</lbs:access>
			<lbs:access code="TOP_JLSB" >
			{
				title: '级联上报',
				border: false,
				iconCls:'jlsb',
				bodyStyle: 'border-bottom: 1px solid;padding-top: 5px;padding-left: 15px;',
				items: [tree_menu_5]
			},
			</lbs:access>
			<lbs:access code="TOP_ZDGL" >
			{
				title: '终端管理',
				border: false,
				iconCls:'jlsb',
				bodyStyle: 'border-bottom: 1px solid;padding-top: 5px;padding-left: 15px;',
				items: [tree_menu_8]
			},
			</lbs:access>
			<lbs:access code="TOP_QXGL" >
			{
				title: '权限管理',
				border: false,
				iconCls:'user',
				bodyStyle: 'border-bottom: 1px solid;padding-top: 5px;padding-left: 15px;',
				items: [tree_menu_6]
			},
			</lbs:access>
			<lbs:access code="TOP_PZGL" >
			{
				title: '配置管理',
				border: false,
				iconCls:'pzgl',
				bodyStyle: 'border-bottom: 1px solid;padding-top: 5px;padding-left: 15px;',
				items: [tree_menu_7]
			},
			</lbs:access>
            <lbs:access code="TOP_WLGL" >
			{
				title: '网络管理',
				border: false,
				iconCls:'wlgl',
				bodyStyle: 'border-bottom: 1px solid;padding-top: 5px;padding-left: 15px;',
				items: [tree_menu_9]
			},
			</lbs:access>
            <lbs:access code="TOP_XTGL" >
			{
				title: '系统管理',
				border: false,
				iconCls:'xtgl',
				bodyStyle: 'border-bottom: 1px solid;padding-top: 5px;padding-left: 15px;',
				items: [tree_menu_10]
			},
			</lbs:access>
 			{}
		]
	});
	
	//页面的底部
	var southPanel=new Ext.Panel({
		region: "south",
		split: true,
		height: 0,
		border: false,
		collapsible: false,
		collapsed: false,
		contentEl: "footer-div",
		margins: "0 0 0 0"    
	});
	
	//页面的中间面板
	centerPanel = new Ext.TabPanel({
		id: 'mainContent',
        region: 'center',
        deferredRender: false,
        enableTabScroll: true,
        activeTab: 0,
        items: [{
        	id: 'mrn_1_1',
			title: '业务运行监控',
			border: false,
			closable: true,
			html: '<iframe id="frame_mrn_1_1" width="100%" height="100%" frameborder="0" src="pages/monitor/bizIndex.jsp"></iframe>'
			//autoScroll: true
		}]
	});
    centerPanel.activate(0);
    
    var viewport = new Ext.Viewport({
		layout: 'border',
		loadMask: true,
		items: [northPanel, 		//上
				westPanel,  		//左
				centerPanel,		//中
				southPanel  		//下
		]
	});
	
	northBar.get(0).setText("您好！${account.name}");
	
	soundManager = new SoundManager();
	soundManager.debugMode = false;
	soundManager.url = '<c:url value="/sound/swf"/>';
	soundManager.beginDelayedInit();
	soundManager.onload = function() {
		soundManager.createSound({
			id: 'msgSound',
			url: '<c:url value="/sound/mp3/msg.mp3"/>'
		});
	}
	idx = 0;
	var task = {
		run : function() {
			Ext.Ajax.request({
				url : '<c:url value="/alert.do?m=checkLogin"/>&time='+new Date().getTime(),
			    success : function(r,o) {
			    	var respText = Ext.util.JSON.decode(r.responseText);
			        var msg = respText.msg;
			        if(msg == 'logout'){		        	
			        	var path = respText.path;
			        	window.location = "<c:url value="/logout.do"/>";
			        }
			    }
			});
            if(idx==0){
                Ext.Ajax.request({
                    url: '<c:url value="/alert.do?m=refreshAlerts"/>&time='+new Date().getTime(),
                    success: function(response){
                        var result = Ext.util.JSON.decode(response.responseText);
                        if(result.device>0||result.business>0||result.security>0){
                            var qq = new Ext.ux.ToastWindow({
                                title: '报警提示',
                                html: result.time+' 收到'+result.device+'条设备报警信息，<a href="javascript:void(0);" onclick="nodeClick2(\'admin/forward.do?m=eqalert\',\'mrn_3_4\',\'设备故障报警\');return false;">详细内容</a><br/>'+result.time+' 收到'+result.business+'条业务报警信息，<a href="javascript:void(0);" onclick="nodeClick2(\'admin/forward.do?m=bsalert\',\'mrn_3_2\',\'业务异常报警\');return false;">详细内容</a><br/>'+result.time+' 收到'+result.security+'条安全报警信息，<a href="javascript:void(0);" onclick="nodeClick2(\'admin/forward.do?m=scalert\',\'mrn_3_3\',\'安全事件报警\');return false;">详细内容</a><br><a href="javascript:void(0);" onclick="cacheFresh();">暂时不刷新</a>',
                                iconCls: 'bjgl',
                                autoShow:true
                            });
                            qq.animShow();
                            soundManager.play('msgSound');
                        }
                    }
                });
            } else if (idx>0&&idx<20){
                idx ++;
            } else if(idx>=20){
                idx = 0;
            }
            if(idx2 = 0){
                Ext.Ajax.request({
                    url: '<c:url value="/alert.do?m=refreshDiskUseAlerts"/>&time='+new Date().getTime(),
                    success: function(response){
                        var result = Ext.util.JSON.decode(response.responseText);
                        if(result.alert>0){
                            var qq = new Ext.ux.ToastWindow({
                                title: '报警提示',
                                html: result.time+' 收到'+result.alert+'条报警信息:审计库容量达到警戒值'+result.dbUsed+'，请看[报警阀值设置]<br/>'+result.diskMsg+'<br><a href="javascript:void(0);" onclick="cacheFresh2();">暂时不刷新</a>',
                                iconCls: 'bjgl',
                                autoShow:true
                            });
                            qq.animShow();
                            soundManager.play('msgSound');
                        }
                    }
                });
            } else if (idx2>0&&idx2<20){
                idx2 ++;
            } else if(idx2>=20){
                idx2 = 0;
            }
		},
		interval : 10000*600
	}
	Ext.TaskMgr.start(task);
});

function cacheFresh(){
    idx ++;
    return idx;
}
function cacheFresh2(){
    idx2 ++;
    return idx2;
}

function showWindow(flag){
	if(flag==1){
		window.open("admin/forward.do?m=eqalert");
	}else if(flag==2){
		window.open("admin/forward.do?m=bsalert");
	}else if(flag==3){
		window.open("admin/forward.do?m=scalert");
	}
}

function logout(){
	Ext.Msg.confirm("确认","确认退出系统吗？",function(btn){
		if (btn == 'yes') {
			window.location = "<c:url value="/logout.do"/>";
		}else{
			return false;
		}
	});
}

 Ext.apply(Ext.form.VTypes,{  
  //验证方法    
  password:function(val,field){//val指这里的文本框值，field指这个文本框组件  
   if(field.password.password_id){  
    //password是自定义的配置参数，一般用来保存另外的组件的id值  
    var pwd=Ext.get(field.password.password_id);//取得user_password的那个id的值  
    return (val==pwd.getValue());//验证  
   }  
   return true;  
 },   
  //验证提示错误提示信息(注意：方法名+Text)   
  passwordText: "两次密码输入不一致!"     
                 
 });   

var pwdForm = new Ext.FormPanel({
        	region:'center',
            deferredRender:true,
        	border:false,
			labelAlign : 'right',
        	defaults:{xtype:"textfield",inputType:"password"},
        	items:[{
				fieldLabel : '当前密码',
				name : 'pwd',
				id : 'pwd',
				width : 150
			}, {
				fieldLabel : '输入新密码',
				name : 'newpwd',
				id : 'newpwd',
				width : 150
			}, {
				fieldLabel : '再次输入新密码',
				name : 'rnewpwd',
				id : 'rnewpwd',
				width : 150,
				password:{password_id:'newpwd'}, 
				vtype:'password'
			}]
        });
var pwdWin;
function showUpdatePwd(){
if(!pwdWin){
	var pwdWin = new Ext.Window({
        layout:'border',
        width:300,
        height:145,
        closeAction:'hide',
        plain: true,
        modal:true,
        title:'修改密码',
        resizable:false,
        
        items: pwdForm,
        
        buttons : [{
			text : '保存',
			listeners : {
				'click' : function() {
					pwdForm.getForm().submit({
						clientValidation : true,
						url : 'pwd.do?m=update',
						success : function(form, action) {
							Ext.Msg.alert('保存成功', '保存成功！');
							pwdWin.close();
						},
						failure : function(form, action) {
							Ext.Msg.alert('保存失败', '系统错误，请联系管理员。');
							pwdWin.close();
						}
					});
				}
			}
		}, {
			text : '取消',
			listeners : {
				'click' : function() {
					pwdWin.close();
				}
			}
		}]

	});
	
	pwdWin.show();
}	
	
}

function nodeClick2(_url,id,text){
			if (_url != ''){
				if(_url.indexOf('?')>0)
					_url += '&time=' + new Date().getTime();
				else
					_url += '?time=' + new Date().getTime();
			}
			var _tab = centerPanel.getComponent(id) ;
			if(!_tab){
				centerPanel.add({
					id: id ,
					title: text ,
					closable: true ,
					iconCls: '', 
					html: '<iframe id="frame_'+id+'" width="100%" height="100%" frameborder="0" src="'+_url+'"></iframe>'
				}) ;
			}
			centerPanel.setActiveTab(id) ;
	}

