/*
 * ! Ext JS Library 3.2.0 Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com http://www.extjs.com/license
 */
url = '../alert.do?m=bsalert';
codeurl = '../alert.do?m=bscode';
title = '业务异常报警';
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
		}, '结束日期：', {
			id : 'endDate',
			xtype : 'datefield',
			name : 'endDate',
			emptyText : '点击输入日期',
			format : 'Y-m-d',
			value : new Date()
		}, {
			xtype : 'tbspacer',
			width : 10
		}, '业务名：', new Ext.form.ComboBox({
			id : "businessName",
			store : new Ext.data.JsonStore({
				url : '../bizConf.do?m=bizCombox',
				fields : ['businessName']
			}),
			valueField : 'businessName',
			displayField : 'businessName',
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
		}, '报警类型：', new Ext.form.ComboBox({
			id : "alertType",
			store : new Ext.data.JsonStore({
				url : codeurl,
				root : 'root.result.data',
				totalProperty : 'root.result.total',
				fields : ['name', 'value']
			}),
			valueField : 'value',
			displayField : 'name',
			mode : 'remote',
			value : '',
			emptyText : 'All',
			forceSelection : true,
			triggerAction : 'all',
			selectOnFocus : true,
			width : 'auto'
		}), {
			xtype : 'tbspacer',
			width : 10
		}, {
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
					var businessName = Ext.getCmp('businessName').getValue();
					var alertType = Ext.getCmp("alertType").getValue();

					store.setBaseParam("startDate", startDate);
					store.setBaseParam("endDate", endDate);
					store.setBaseParam("businessName", businessName);
					store.setBaseParam("alertCode", alertType);
					store.load({
						params : {
							start : 0,
							limit : PAGESIZE
						}
					});

				}
			}
		}, '-', {
			pressed : false,
			text : '标记为已读',
			id : 'delete_btn',
			handler : function() {
				var selectedRows = grid.getSelectionModel().getSelections();
				if (selectedRows.length == 0) {
					Ext.Msg.alert('警告', '请选中你要标记的行！');
				} else {
					var ids = "";
					for (var i = 0; i < selectedRows.length; i++) {
						ids += selectedRows[i].data.id + ",";
					}
					ids = ids.substring(0, ids.length - 1);
					Ext.Ajax.request({
						url : '../alert.do?m=setReaded',
						params : {
							ids : ids,
							domain : 'BusinessExceptAlert'
						},
						success : function(response, options) {
							store.reload();
						}
					});
				}
			}
		}]
	});

	// create the Data Store
	var store = new Ext.data.JsonStore({
		url : url,
		root : 'root.result.data',
		totalProperty : 'root.result.total',
		idProperty : 'id',

		fields : ['id', 'alertTime', 'businessName', 'alertType', 'alertInfo',
				'ip', 'isRead', 'userName']

	});
	// store.setDefaultSort('lastpost', 'desc');
	var sm = new Ext.grid.CheckboxSelectionModel();
	var grid = new Ext.grid.GridPanel({
		title : title,
		store : store,
		loadMask : true,
		trackMouseOver : true,// 鼠标悬浮高亮显示
		columnLines : true,// 列分隔符

		// grid columns
		columns : [sm, {
			id : 'id',
			dataIndex : 'id',
			hidden : true
		}, {
			header : "报警时间",
			dataIndex : 'alertTime',
			width : 150,
			menuDisabled : true,
			sortable : true
		}, {
			header : "业务名",
			dataIndex : 'businessName',
			width : 100,
			menuDisabled : true
		}, {
			header : "报警类型",
			dataIndex : 'alertType',
			width : 150,
			menuDisabled : true
		}, {
			header : "IP地址",
			dataIndex : 'ip',
			width : 150,
			menuDisabled : true
		}, {
			header : "用户名",
			dataIndex : 'userName',
			width : 150,
			menuDisabled : true
		}, {
			header : "状态",
			dataIndex : 'isRead',
			width : 100,
			menuDisabled : true,
			renderer : function(v, p, r) {
				return v == 'Y' ? '已读' : '未读';
			}
		}, {
			header : "报警内容",
			dataIndex : 'alertInfo',
			width : 300,
			menuDisabled : true
		}],
		sm : sm,

		bbar : new Ext.ux.PagingToolbar({
			store : store
		}),
		tbar : tb,
		viewConfig : {}
	});

	grid.render(document.body);

	var viewport = new Ext.Viewport({
		layout : 'fit',
		border : false,
		items : [grid]
	});
	
	store.load();
});
