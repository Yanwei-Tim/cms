/*
 * ! Ext JS Library 3.2.0 Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com http://www.extjs.com/license
 */
url = '../log.do?m=userOperLog';
title = '用户日志审计';
Ext.onReady(function() {

	Ext.QuickTips.init();

	var tb = new Ext.Toolbar({
		width : 720,
		height : 30,
		items : ['起始日期：', {
			id : 'startDate',
			xtype : 'datefield',
			name : 'startDate',
			emptyText : '点击输入日期',
			format : 'Y-m-d'
		}, {
			xtype : 'tbspacer',
			width : 10
		}, '结束日期：', // same as {xtype: 'tbtext', text: 'text1'} to create
				// Ext.Toolbar.TextItem
				{
					id : 'endDate',
					xtype : 'datefield',
					name : 'endDate',
					emptyText : '点击输入日期',
					format : 'Y-m-d',
					value : new Date()
				}, {
					xtype : 'tbspacer',
					width : 10
				}, '级别：', new Ext.form.ComboBox({
					id : "logLevel",
					store : new Ext.data.ArrayStore({
						autoDestroy : true,
						fields : ['value', 'text'],
						data : [['', 'ALL'], ['ERROR', 'ERROR'],
								['INFO', 'INFO']]
					}),
					valueField : 'value',
					displayField : 'text',
					mode : 'local',
					forceSelection : true,
					triggerAction : 'all',
					value : '',
					selectOnFocus : true,
					width : 70
				}), {
					xtype : 'tbspacer',
					width : 10
				}, '用户：', new Ext.form.ComboBox({
					id : "userName",
					store : new Ext.data.JsonStore({
						url : '../userAdmin.do?m=userCombox',
						fields : ['userName', 'name']
					}),
					valueField : 'userName',
					displayField : 'name',
					mode : 'remote',
					value : '',
					emptyText : 'All',
					forceSelection : true,
					triggerAction : 'all',
					selectOnFocus : true,
					width : 80
				}), {
					xtype : 'tbspacer',
					width : 10
				}, {
					// xtype: 'button',
					xtype : 'button',
					text : '查询',
					listeners : {
						"click" : function() {
							var logLevel = Ext.fly("logLevel").dom.value == 'ALL'
									? ''
									: Ext.fly("logLevel").dom.value;
							var startDate = Ext.fly("startDate").dom.value == '点击输入日期'
									? ''
									: Ext.fly("startDate").dom.value;
							var endDate = Ext.fly("endDate").dom.value == '点击输入日期'
									? ''
									: Ext.fly("endDate").dom.value;
							var userName = Ext.getCmp('userName').getValue();
                            if(endDate<startDate){
                                Ext.Msg.alert("提示","请确保结束日期大于开始日期");
                            };
							store.setBaseParam("startDate", startDate);
							store.setBaseParam("endDate", endDate);
							store.setBaseParam("logLevel", logLevel);
							store.setBaseParam("userName", userName);
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
			name : 'logTime',
			mapping : 'logTime'
		}, {
			name : 'level',
			mapping : 'level'
		}, {
			name : 'userName',
			mapping : 'userName'
		}, {
			name : 'auditModule',
			mapping : 'auditModule'
		}, {
			name : 'auditInfo',
			mapping : 'auditInfo'
		}]

	});
	// store.setDefaultSort('lastpost', 'desc');

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
			header : "行为发生时间",
			dataIndex : 'logTime',
			width : 150,
			menuDisabled : true,
			sortable : true
		}, {
			header : "消息级别",
			dataIndex : 'level',
			width : 100,
			menuDisabled : true
		}, {
			header : "用户名",
			dataIndex : 'userName',
			width : 100,
			menuDisabled : true
		}, {
			header : "审计模块",
			dataIndex : 'auditModule',
			width : 100,
			menuDisabled : true
		}, {
			header : "详细描述",
			dataIndex : 'auditInfo',
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
		viewConfig : {}
	});

	// render it
	grid.render('topic-grid');
    store.load({
        params : {
            start : 0,
            limit : PAGESIZE
        }
    });

	// trigger the data store load
	// store.load({params:{start:0, limit:PAGESIZE}});
	var viewport = new Ext.Viewport({
		layout : 'fit',
		border : false,
		items : [grid]
	});
});
