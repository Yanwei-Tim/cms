var task = {
		run : function() {
			Ext.Ajax.request({
			   url: '<c:url value="/alert.do?m=refreshAlerts"/>&time='+new Date().getTime(),
			   success: function(response){
			   	var result = Ext.util.JSON.decode(response.responseText);
			   	if(result.device>0||result.business>0||result.security>0){
			   		var qq = new Ext.ux.ToastWindow({
					  title: '报警提示',
					  html: result.time+' 收到'+result.device+'条设备报警信息，<a href="javascript:void(0);" onclick="nodeClick2("mrn_3_4",\\"admin/forward.do?m=eqalert\\",\"设备故障报警\");return false;">详细内容</a><br/>'+result.time+' 收到'+result.business+'条业务报警信息，<a href="javascript:void(0);" onclick="nodeClick2(\"admin/forward.do?m=bsalert\",\"mrn_3_2\",\"业务异常报警\");return false;">详细内容</a><br/>'+result.time+' 收到'+result.security+'条安全报警信息，<a href="javascript:void(0);" onclick="nodeClick2(\"admin/forward.do?m=scalert\",\"mrn_3_3\",\"安全事件报警\");return false;">详细内容</a>',
					  iconCls: 'bjgl',
					  autoShow:true
					});
					qq.animShow();
			   	}
			   	
			    soundManager.play('msgSound');
			   }
			});
		},
		interval : 10000
	}

Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side'; 	
	
	var imagePath = 'js/ext/resources/images';
	Ext.BLANK_IMAGE_URL = imagePath+'/default/tree/s.gif';
	
	var menu_root_node_1 = new Ext.tree.TreeNode({
		text: '运行监控',
		expanded: true
	});
	var menu_root_node_2 = new Ext.tree.TreeNode({
		text: '审计管理',
		expanded: false
	});
	var menu_root_node_3 = new Ext.tree.TreeNode({
		text: '报警管理',
		expanded: false
	});
	var menu_root_node_4 = new Ext.tree.TreeNode({
		text: '统计报表',
		expanded: false
	});
	var menu_root_node_6 = new Ext.tree.TreeNode({
		text: '权限管理',
		expanded: false
	});
	var menu_root_node_7 = new Ext.tree.TreeNode({
		text: '配置管理',
		expanded: false
	});
	
	
	var mrn_1_1 = new Ext.tree.TreeNode({
		id: 'mrn_1_1',
		text: '业务运行监控',
		leaf: true ,
		iconCls: 'object_user',
		url: 'pages/monitor/bizIndex.jsp' 
	}) ;
	var mrn_1_2 = new Ext.tree.TreeNode({
		id: 'mrn_1_2',
		text: '设备运行监控',
		leaf: true ,
		iconCls: 'object_archive',
		url: 'pages/monitor/equIndex.jsp' 
	}) ;
	menu_root_node_1.appendChild(mrn_1_1) ;
	menu_root_node_1.appendChild(mrn_1_2) ;
	
	var mrn_2_1 = new Ext.tree.TreeNode({
		id: 'mrn_2_1',
		text: '系统日志审计',
		leaf: true ,
		iconCls: 'object_user',
		url: BASEURL+'admin/forward.do?m=sysLog' 
	}) ;
	var mrn_2_2 = new Ext.tree.TreeNode({
		id: 'mrn_2_2',
		text: '用户日志审计',
		leaf: true ,
		iconCls: 'object_archive',
		url: BASEURL+'admin/forward.do?m=userLog' 
	}) ;
	var mrn_2_3 = new Ext.tree.TreeNode({
		id: 'mrn_2_3',
		text: '业务日志审计',
		leaf: true ,
		iconCls: 'object_user',
		url: BASEURL+'admin/forward.do?m=businessLog' 
	}) ;
	var mrn_2_4 = new Ext.tree.TreeNode({
		id: 'mrn_2_4',
		text: '设备日志审计',
		leaf: true ,
		iconCls: 'object_archive',
		url: BASEURL+'admin/forward.do?m=equipmentLog' 
	}) ;
	menu_root_node_2.appendChild(mrn_2_1) ;
	menu_root_node_2.appendChild(mrn_2_2) ;
	menu_root_node_2.appendChild(mrn_2_3) ;
	menu_root_node_2.appendChild(mrn_2_4) ;
	
	var mrn_3_1 = new Ext.tree.TreeNode({
		id: 'mrn_3_1',
		text: '报警配置',
		leaf: true ,
		iconCls: 'object_user',
		url: BASEURL+'admin/forward.do?m=alertconfig' 
	}) ;
	var mrn_3_2 = new Ext.tree.TreeNode({
		id: 'mrn_3_2',
		text: '业务异常报警',
		leaf: true ,
		iconCls: 'object_archive',
		url: BASEURL+'admin/forward.do?m=bsalert' 
	}) ;
	var mrn_3_3 = new Ext.tree.TreeNode({
		id: 'mrn_3_3',
		text: '安全事件报警',
		leaf: true ,
		iconCls: 'object_user',
		url: BASEURL+'admin/forward.do?m=scalert' 
	}) ;
	var mrn_3_4 = new Ext.tree.TreeNode({
		id: 'mrn_3_4',
		text: '设备故障报警',
		leaf: true ,
		iconCls: 'object_archive',
		url: BASEURL+'admin/forward.do?m=eqalert' 
	}) ;
	menu_root_node_3.appendChild(mrn_3_1) ;
	menu_root_node_3.appendChild(mrn_3_2) ;
	menu_root_node_3.appendChild(mrn_3_3) ;
	menu_root_node_3.appendChild(mrn_3_4) ;
	
	var mrn_4_1 = new Ext.tree.TreeNode({
		id: 'mrn_4_1',
		text: '业务日统计表',
		leaf: true ,
		iconCls: 'object_user',
		url: BASEURL+'admin/forward.do?m=bdDayReport'
	}) ;
	var mrn_4_2 = new Ext.tree.TreeNode({
		id: 'mrn_4_2',
		text: '业务月统计表',
		leaf: true ,
		iconCls: 'object_archive',
		url: BASEURL+'admin/forward.do?m=bdMonthReport'
	}) ;
	var mrn_4_3 = new Ext.tree.TreeNode({
		id: 'mrn_4_3',
		text: '业务年统计表',
		leaf: true ,
		iconCls: 'object_archive',
		url: BASEURL+'admin/forward.do?m=bdYearReport'
	}) ;
	var mrn_4_4 = new Ext.tree.TreeNode({
		id: 'mrn_4_4',
		text: '设备日统计表',
		leaf: true ,
		iconCls: 'object_archive',
		url: BASEURL+'admin/forward.do?m=eqDayReport'
	}) ;
	var mrn_4_5 = new Ext.tree.TreeNode({
		id: 'mrn_4_5',
		text: '设备月统计表',
		leaf: true ,
		iconCls: 'object_archive',
		url: BASEURL+'admin/forward.do?m=eqMonthReport'
	}) ;
	var mrn_4_6 = new Ext.tree.TreeNode({
		id: 'mrn_4_6',
		text: '设备年统计表',
		leaf: true ,
		iconCls: 'object_archive',
		url: BASEURL+'admin/forward.do?m=eqYearReport'
	}) ;
	menu_root_node_4.appendChild(mrn_4_1) ;
	menu_root_node_4.appendChild(mrn_4_2) ;
	menu_root_node_4.appendChild(mrn_4_3) ;
	menu_root_node_4.appendChild(mrn_4_4) ;
	menu_root_node_4.appendChild(mrn_4_5) ;
	menu_root_node_4.appendChild(mrn_4_6) ;
	
	var mrn_6_1 = new Ext.tree.TreeNode({
		id: 'mrn_6_1',
		text: '用户管理',
		leaf: true ,
		iconCls: 'object_user',
		url: 'pages/user/userIndex.jsp' 
	}) ;
	var mrn_6_2 = new Ext.tree.TreeNode({
		id: 'mrn_6_2',
		text: '角色管理',
		leaf: true ,
		iconCls: 'object_archive',
		url: 'pages/user/roleIndex.jsp'
	}) ;
	menu_root_node_6.appendChild(mrn_6_1) ;
	menu_root_node_6.appendChild(mrn_6_2) ;
	
	var mrn_7_1 = new Ext.tree.TreeNode({
		id: 'mrn_7_1',
		text: '审计库管理',
		leaf: true ,
		iconCls: '',
		url: '' 
	}) ;
	var mrn_7_2 = new Ext.tree.TreeNode({
		id: 'mrn_7_2',
		text: '基本配置',
		leaf: true ,
		iconCls: '',
		url: '' 
	}) ;
	var mrn_7_3 = new Ext.tree.TreeNode({
		id: 'mrn_7_3',
		text: '链路配置',
		leaf: true ,
		iconCls: '',
		url: '' 
	}) ;
	var mrn_7_4 = new Ext.tree.TreeNode({
		id: 'mrn_7_4',
		text: '交换平台配置',
		leaf: true ,
		iconCls: '',
		url: '' 
	}) ;
	var mrn_7_5 = new Ext.tree.TreeNode({
		id: 'mrn_7_5',
		text: '探针通道',
		leaf: true ,
		iconCls: '',
		url: '' 
	}) ;
	var mrn_7_6 = new Ext.tree.TreeNode({
		id: 'mrn_7_6',
		text: '审计备份策略',
		leaf: true ,
		iconCls: '',
		url: 'pages/' 
	}) ;
	var mrn_7_7 = new Ext.tree.TreeNode({
		id: 'mrn_7_7',
		text: '设备管理配置',
		leaf: true ,
		iconCls: '',
		url: 'pages/conf/equ.jsp' 
	}) ;
	var mrn_7_8 = new Ext.tree.TreeNode({
		id: 'mrn_7_8',
		text: '业务注册管理',
		leaf: true ,
		iconCls: '',
		url: 'pages/conf/biz.jsp' 
	}) ;
	menu_root_node_7.appendChild(mrn_7_1) ;
	menu_root_node_7.appendChild(mrn_7_2) ;
	menu_root_node_7.appendChild(mrn_7_3) ;
	menu_root_node_7.appendChild(mrn_7_4) ;
	menu_root_node_7.appendChild(mrn_7_5) ;
	menu_root_node_7.appendChild(mrn_7_6) ;
	menu_root_node_7.appendChild(mrn_7_7) ;
	menu_root_node_7.appendChild(mrn_7_8) ;
	
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
			 		NodeClick(mrn_1_5,mrn_1_5.attributes.id);
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
			{
				title: '运行监控',
				border: false,
				bodyStyle: 'border-bottom: 1px solid;padding-top: 5px;padding-left: 15px;',
				iconCls: 'object_archiveManage',	//字面板样式
				items: [tree_menu_1]
			},
			{
				title: '审计管理',
				border: false,
				bodyStyle: 'border-bottom: 1px solid;padding-top: 5px;padding-left: 15px;',
				iconCls: 'object_archiveManage',	//字面板样式
				items: [tree_menu_2]
			},
			{
				title: '报警管理',
				border: false,
				bodyStyle: 'border-bottom: 1px solid;padding-top: 5px;padding-left: 15px;',
				iconCls: 'object_archiveManage',	//字面板样式
				items: [tree_menu_3]
			},
			{
				title: '统计报表',
				border: false,
				bodyStyle: 'border-bottom: 1px solid;padding-top: 5px;padding-left: 15px;',
				iconCls: 'object_archiveManage',	//字面板样式
				items: [tree_menu_4]
			},
			{
				title: '级联上报',
				border: false,
				bodyStyle: 'border-bottom: 1px solid;padding-top: 5px;padding-left: 15px;',
				iconCls: 'object_archiveManage',	//字面板样式
				items: []
			},
			{
				title: '权限管理',
				border: false,
				bodyStyle: 'border-bottom: 1px solid;padding-top: 5px;padding-left: 15px;',
				iconCls: 'object_archiveManage',	//字面板样式
				items: [tree_menu_6]
			},
			{
				title: '配置管理',
				border: false,
				bodyStyle: 'border-bottom: 1px solid;padding-top: 5px;padding-left: 15px;',
				iconCls: 'object_archiveManage',	//字面板样式
				items: [tree_menu_7]
			}
 
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
	var centerPanel = new Ext.TabPanel({
		id: 'mainContent',
        region: 'center',
        deferredRender: false,
        enableTabScroll: true,
        activeTab: 0,
        items: [{
        	id: 'mrn_1_1',
			title: '业务运行监控',
			border: false,
			closable: false,
			iconCls : 'object_first',
			html: '<iframe width="100%" height="100%" frameborder="0" src="pages/monitor/bizIndex.jsp"></iframe>'
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
	
	northBar.get(0).setText("您好");
});


