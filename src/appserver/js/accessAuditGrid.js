/*
 * ! Ext JS Library 3.2.0 Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com http://www.extjs.com/license
 */
url = '../terminalAccessAudit.do?m=index';
title = '用户访问审计';
Ext.onReady(function() {
	Ext.QuickTips.init();
	var comboSysClass = new Ext.data.JsonStore({
		autoLoad : true,
		url : '../sysClass.do?m=comboSysClass',
		sotreId : 'comboSysClass',
		root : 'results',
		idProperty : 'id',
		fields : ['id', 'name']
	});
	comboSysClass.load();
	var comboParent = new Ext.data.JsonStore({
				autoLoad : true,
				url : '../district.do?m=comboParent',
				sotreId : 'comboParent',
				root : 'results',
				idProperty : 'id',
				fields : ['id', 'name']
			});
	comboParent.load();
	var comboChildUserDepart = new Ext.data.JsonStore({
				autoLoad : true,
				url : '',
				root : 'results',
				idProperty : 'id',
				fields : ['id', 'name']
			});
	var tbar = new Ext.Toolbar({
		width : 1500,
		height : 30,
		items :[ '消息级别：', {
			id : "messageLevel",
			store : new Ext.data.ArrayStore({
				autoDestroy : true,
				fields : ['value', 'text'],
				data : [['ERROR', 	'ERROR'],
				        ['WARN', 	'WARN'], 
				        ['INFO', 	'INFO'],
				        ['DEBUG', 	'DEBUG']]
			}),
			valueField : 'value',
			displayField : 'text',
			mode : 'local',
			emptyText : '请选择',
			forceSelection : true,
			triggerAction : 'all',
			xtype : 'combo',
			width : 70,
			selectOnFocus : true
		}
		,{xtype : 'tbspacer',width : 10}
		,'所属地区：'
		,{
			id : "_parentId.userZone.info",
			xtype : 'combo',
			width : 130,
			valueField : 'id',
			displayField : 'name',
			mode : 'remote',
			emptyText : '请选择',
			allowBlank : true,
			store : comboParent,
			selectOnFocus : true,
			editable : true,
			triggerAction : 'all',
			loadingText : '加载中...',
			listeners : {
				select : function(combo, record, index) {
					Ext.getCmp('userZone.info').clearValue();
					comboChildUserDepart.proxy = new Ext.data.HttpProxy({
						url : '../district.do?m=comboChild&parentId='
							+ Ext.getCmp('_parentId.userZone.info').value
					});
					comboChildUserDepart.load();
				}
			}
		}, {
			id : 'userZone.info',
			xtype : 'combo',
			width : 320,
			listWidth  : 330,
			valueField : 'id',
			displayField : 'name',
			hiddenName : 'userZone',
			mode : 'remote',
			emptyText : '请选择',
			allowBlank : true,
			store : comboChildUserDepart,
			selectOnFocus : true,
			editable : true,
			triggerAction : 'all',
			loadingText : '加载中...'
		}
		,{xtype : 'tbspacer',width : 10}
		,'所属部门：'
		,{
			id : "userDepart.info",
			xtype : 'combo',
			listWidth  : 200,
			store : comboSysClass,
			emptyText : '请选择',
			valueField : 'id',
			displayField : 'name',
			mode : 'remote',
			forceSelection : true,
			triggerAction : 'all',
			selectOnFocus : true
		}
		, {xtype : 'tbspacer',width : 10}
		, {
			text : '查询',
			listeners : {
				"click" : function() {
					var startDate = Ext.fly("startDate").dom.value == '点击输入日期'
						? '' : Ext.fly("startDate").dom.value;
					var endDate = Ext.fly("endDate").dom.value == '点击输入日期'
						? '' : Ext.fly("endDate").dom.value;
					var messageLevel = Ext.fly("messageLevel").dom.value == '请选择'
						? '' : Ext.getCmp("messageLevel").value;
					var policeId = Ext.fly("policeId").dom.value;
					var policeName = Ext.fly("policeName").dom.value;
					var userDepart = Ext.fly("userDepart.info").dom.value == '请选择'
						? '' : Ext.getCmp("userDepart.info").value;
					var userZone = Ext.fly("userZone.info").dom.value == '请选择'
						? '' : Ext.getCmp("userZone.info").value;
					var busName = Ext.fly("busName.info").dom.value=='请选择'
						? '' : Ext.getCmp("busName.info").value;
					
					store.setBaseParam("startDate", startDate);
					store.setBaseParam("endDate", endDate);
					store.setBaseParam("policeId", policeId);
					store.setBaseParam("policeName", policeName);
					store.setBaseParam("messageLevel", messageLevel);
					store.setBaseParam("userDepart", userDepart);
					store.setBaseParam("userZone", userZone);
					store.setBaseParam("busName", busName);
					store.load({
						params : {
							start : 0,
							limit : PAGESIZE
						}
					});
					
				}
			}
		}]
	});
	var tb = new Ext.Toolbar({
		width : 720,
		height : 30,
		items:['访问日期晚于：', {
				id : 'startDate',
				xtype : 'datefield',
				name : 'startDate',
				emptyText : '点击输入日期',
				format : 'Y-m-d'
			}, {
				xtype : 'tbspacer',
				width : 10
			}, '早于：', 
			{
				id : 'endDate',
				xtype : 'datefield',
				name : 'endDate',
				emptyText : '点击输入日期',
				format : 'Y-m-d'
			}, {xtype : 'tbspacer',width : 10}
			,'警号：'
			,{id : 'policeId',xtype:'textfield',name : 'policeId',width:113}
			,{xtype : 'tbspacer',width : 10}
			,'警员姓名：'
			,{id : 'policeName',xtype:'textfield',name : 'policeName',width:100}
			,{xtype : 'tbspacer',width : 10}
			,'业务名称：'
			,{
				id : "busName.info",
				xtype : 'combo',
				store : new Ext.data.JsonStore({
					url : '../bizConf.do?m=bizCombox',
					fields : ['businessName']
				}),
				valueField : 'businessName',
				displayField : 'businessName',
				mode : 'remote',
				emptyText : '请选择',
				value : '',
				forceSelection : true,
				triggerAction : 'all',
				selectOnFocus : true,
				width : 150
			}
		]
	});

	// create the Data Store
	var store = new Ext.data.JsonStore({
		url : url,
		root : 'results',
		totalProperty : 'amount',
		idProperty : 'id',
		// remoteSort: true,

		fields : [{
			name : 'id',
			mapping : 'id'
		}, {
			name : 'accessTime',
			mapping : 'accessTime'
		}, {
			name : 'messageLevel',
			mapping : 'messageLevel'
		}, {
			name : 'cardType',
			mapping : 'cardType'
		}, {
			name : 'policeId',
			mapping : 'policeId'
		}, {
			name : 'policeName',
			mapping : 'policeName'
		}, {
			name : 'desc',
			mapping : 'desc'
		}, {
			name : 'userId',
			mapping : 'userId'
		}, {
			name : 'userDepart',
			mapping : 'userDepart'
		}, {
			name : 'userZone',
			mapping : 'userZone'
		}, {
			name : 'flux',
			mapping : 'flux'
		}, {
			name : 'busName',
			mapping : 'busName'
		}, {
			name : 'count',
			mapping : 'count'
		}]

	});
    store.setBaseParam("startDate", "");
    store.setBaseParam("endDate", "");
    store.setBaseParam("policeId", "");
    store.setBaseParam("policeName", "");
    store.setBaseParam("messageLevel", "");
    store.setBaseParam("userDepart", "");
    store.setBaseParam("userZone", "");
    store.setBaseParam("busName", "");
    store.load({
        params : {
            start : 0,
            limit : PAGESIZE
        }
    });
	var grid = new Ext.grid.GridPanel({
		title : title,
		store : store,
		loadMask : true,
		trackMouseOver : true,// 鼠标悬浮高亮显示
		// autoExpandColumn: 'auditInfo',// 自动扩展宽度适应空白部分
		columnLines : true,// 列分隔符

		// grid columns
		columns : [{
			id : 'topic', // id assigned so we can apply custom css (e.g.
							// .x-grid-col-topic b { color:#333 })
			header : "访问时间",
			dataIndex : 'accessTime',
			width : 150,
			menuDisabled : true,
			sortable : true
		}, {
			header : "消息级别",
			dataIndex : 'messageLevel',
			width : 60,
			menuDisabled : true
		}, {
			header : "安全卡类型",
			dataIndex : 'cardType',
			width : 80,
			menuDisabled : true
		}, {
			header : "警号",
			dataIndex : 'policeId',
			width : 80,
			menuDisabled : true
		}, {
			header : "警员姓名",
			dataIndex : 'policeName',
			width : 60,
			menuDisabled : true
		}, {
			header : "身份证号",
			dataIndex : 'userId',
			width : 150,
			menuDisabled : true
		}, {
			header : "所属地区",
			dataIndex : 'userZone',
			width : 150,
			menuDisabled : true
		}, {
			header : "所属单位",
			dataIndex : 'userDepart',
			width : 150,
			menuDisabled : true
		}, {
			header : "流量",
			dataIndex : 'flux',
			width : 50,
			menuDisabled : true
		}, {
			header : "请求数",
			dataIndex : 'count',
			width : 50,
			menuDisabled : true
		}, {
			header : "业务名称",
			dataIndex : 'busName',
			width : 100,
			menuDisabled : true
		}, {
			header : "描述",
			dataIndex : 'desc',
			width : 300,
			menuDisabled : true
		}],
		sm : new Ext.grid.RowSelectionModel({
			singleSelect : true
		}),

		// paging bar on the bottom
		bbar : new Ext.ux.PagingToolbar({
			store : store
		}),
		tbar : tb,
		viewConfig : {},
		listeners:{
		    render:function(){
		       tbar.render(this.tbar);
		    }
		}
	});
	// render it
//	grid.render('topic-grid');

	// trigger the data store load
	// store.load({params:{start:0, limit:PAGESIZE}});
	var viewport = new Ext.Viewport({
		layout : 'fit',
		border : false,
		items : [grid]
	});
});
