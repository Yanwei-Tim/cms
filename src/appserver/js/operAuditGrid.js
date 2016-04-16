/*
 * ! Ext JS Library 3.2.0 Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com http://www.extjs.com/license
 */
url = '../terminalOperAudit.do?m=index';
title = '用户操作审计';
Ext.onReady(function() {
	Ext.QuickTips.init();
	var tb = new Ext.Toolbar({
		width : 720,
		height : 30,
		items : ['操作日期晚于：', {
			id : 'startDate',
			xtype : 'datefield',
			name : 'startDate',
			emptyText : '点击输入日期',
			format : 'Y-m-d'
		}, {
			xtype : 'tbspacer',
			width : 10
		}, '早于：', // same as {xtype: 'tbtext', text: 'text1'} to create
					// Ext.Toolbar.TextItem
				{
					id : 'endDate',
					xtype : 'datefield',
					name : 'endDate',
					emptyText : '点击输入日期',
					format : 'Y-m-d'
				}, {xtype : 'tbspacer',width : 10}
				,'警号：'
				,{id : 'policeId',xtype:'textfield',name : 'policeId'}
				,'安全卡编号：'
				,{id : 'cardId',xtype:'textfield',name : 'cardId'}
				,'操作员：'
				,{id : 'operater',xtype:'textfield',name : 'operater'}
				, '操作类型：', new Ext.form.ComboBox({
					id : "operateTpye",
					store : new Ext.data.ArrayStore({
						autoDestroy : true,
						fields : ['value', 'text'],
						data : [['', '不限'], ['A', '截屏'],
								['B', '阻断'], ['C', '吊销证书'],
								['D', '恢复接入']]
					}),
					valueField : 'value',
					displayField : 'text',
					mode : 'local',
					forceSelection : true,
					triggerAction : 'all',
					value : '',
					selectOnFocus : true,
					width : 70
				}), {xtype : 'tbspacer',width : 10}
				, {
					// xtype: 'button',
					text : '查询',
					listeners : {
						"click" : function() {
							var startDate = Ext.fly("startDate").dom.value == '点击输入日期'
									? ''
									: Ext.fly("startDate").dom.value;
							var endDate = Ext.fly("endDate").dom.value == '点击输入日期'
									? ''
									: Ext.fly("endDate").dom.value;
							var operateTpye = Ext.fly("operateTpye").dom.value == '不限'
								? ''
								: Ext.getCmp("operateTpye").value;
							var policeId = Ext.fly("policeId").dom.value;
							var cardId = Ext.fly("cardId").dom.value;
							var operater = Ext.fly("operater").dom.value;
							store.setBaseParam("startDate", startDate);
							store.setBaseParam("endDate", endDate);
							store.setBaseParam("policeId", policeId);
							store.setBaseParam("cardId", cardId);
							store.setBaseParam("operater", operater);
							store.setBaseParam("operateTpye", operateTpye);
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
			name : 'operateTime',
			mapping : 'operateTime'
		}, {
			name : 'operater',
			mapping : 'operater'
		}, {
			name : 'cardId',
			mapping : 'cardId'
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
			name : 'certId',
			mapping : 'certId'
		}, {
			name : 'operateTpye',
			mapping : 'operateTpye'
		}]

	});
	// store.setDefaultSort('lastpost', 'desc');
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
			header : "操作时间",
			dataIndex : 'operateTime',
			width : 150,
			menuDisabled : true,
			sortable : true
		}, {
			header : "操作员",
			dataIndex : 'operater',
			width : 100,
			menuDisabled : true
		}, {
			header : "安全卡编号",
			dataIndex : 'cardId',
			width : 200,
			menuDisabled : true
		}, {
			header : "安全卡类型",
			dataIndex : 'cardType',
			width : 100,
			menuDisabled : true
		}, {
			header : "警号",
			dataIndex : 'policeId',
			width : 100,
			menuDisabled : true
		}, {
			header : "警员姓名",
			dataIndex : 'policeName',
			width : 100,
			menuDisabled : true
		}, {
			header : "操作类型",
			dataIndex : 'operateTpye',
			width : 100,
			menuDisabled : true
		}, {
			header : "证书编号",
			dataIndex : 'certId',
			width : 250,
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

	// trigger the data store load
	// store.load({params:{start:0, limit:PAGESIZE}});
	var viewport = new Ext.Viewport({
		layout : 'fit',
		border : false,
		items : [grid]
	});
});
