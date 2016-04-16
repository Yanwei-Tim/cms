var listGrid;
var editWindow;
var listStore;
var editAction = "";
var levelData = [['区县', '区县'], ['地市', '地市'], ['省级', '省级'], ['部级', '部级']];
Ext.onReady(function() {

	Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';

	Ext.QuickTips.init();

	var listRecord = Ext.data.Record.create(['id', 'level', 'ip', 'port',
			'username', 'password', 'hour', 'minute', 'second', 'time']);

	listStore = new Ext.data.Store({
		storeId : 'listStore',
		url : "../../sbpzConf.do?m=index",
		reader : new Ext.data.JsonReader({
			totalProperty : 'amount',
			root : "results",
			id : 'id'
		}, listRecord),
		baseParams : {
			p : 1
		}
	});

	var sm = new Ext.grid.CheckboxSelectionModel();
	listGrid = new Ext.grid.GridPanel({
		region : 'center',
		border : false,
		store : listStore,
		tbar : [{
			pressed : false,
			text : '新增',
			id : 'add_btn',
			iconCls : 'add',
			handler : function() {
				showAddWindow();
			}
		}, {
			pressed : false,
			text : '删除',
			id : 'delete_btn',
			iconCls : 'delete',
			handler : function() {
				var selectedRows = listGrid.getSelectionModel().getSelections();
				if (selectedRows.length == 0) {
					Ext.Msg.alert('警告', '请选中你要删除的行！');
				} else {
					Ext.Msg.confirm('警告', '确定要删除这些记录吗？', function(btn) {
						if (btn == 'yes') {
							var ids = "";
							for (var i = 0; i < selectedRows.length; i++) {
								ids += selectedRows[i].data.id + ",";
							}
							ids = ids.substring(0, ids.length - 1);
							Ext.Ajax.request({
								url : '../../sbpzConf.do?m=delete',
								params : {
									ids : ids
								},
								success : function(response, options) {
									listStore.reload();
								}
							});
						} else {
							return false;
						}
					});
				}

			}
		}],
		columns : [sm, {
			id : 'id',
			header : 'ID',
			dataIndex : 'id',
			width : 24,
			align : 'center',
			sortable : false,
			menuDisabled : true,
			hidden : true
		}, {
			header : '平台级别',
			dataIndex : 'level',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 60
		}, {
			header : 'IP',
			dataIndex : 'ip',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 60
		}, {
			header : '端口',
			dataIndex : 'port',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 60
		}, {
			header : '用户名',
			dataIndex : 'username',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 60
		}, {
			header : '密码',
			dataIndex : 'password',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 50
		}, {
			header : '运行时间',
			dataIndex : 'hour',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 50,
			renderer : function(value, cellmeta, record) {
				return value + "时" + record.data['minute'] + "分"
						+ record.data['second'] + "秒";
			}
		}, {
			header : '创建时间',
			dataIndex : 'time',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 50
		}, {
			header : '操作',
			align : 'center',
			dataIndex : 'id',
			menuDisabled : true,
			width : 40,
			renderer : function(value, p, r) {
				return String
						.format('<a href="javascript:void(0);" onclick="showUpdate();return false;">修改</a>');
			}
		}],
		viewConfig : {
			forceFit : true
		},
		loadMask : {
			msg : '正在加载数据，请稍侯……'
		},
		columnLines : true,
		stripeRows : true,
		sm : sm,
		bbar : {
			xtype : 'paging',
			id : 'pagingbar',
			pageSize : 15,
			store : listStore,
			displayInfo : true,
			displayMsg : '第 {0} 条到 {1} 条，共 {2} 条',
			emptyMsg : '没有搜索到符合条件的数据！'
		}
	});

	function showAddWindow() {
		editWindow = new Ext.Window({
			width : 300,
			modal : true,
			closeAction : 'close',
			items : {
				id : 'fp',
				xtype : 'form',
				frame : true,
				autoScroll : true,
				title : '',
				border : false,
				layout : 'form',
				labelAlign : 'right',
				labelWidth : 60,
				autoWidth : true,
				autoHeight : true,
				waitMsgTarget : true,
				defaultType : 'textfield',
				items : [{
					fieldLabel : '平台级别',
					valueField : 'value',
					displayField : 'text',
					width : 200,
					xtype : 'combo',
					triggerAction : 'all',
					mode : 'local',
					hiddenName : 'level',
					store : new Ext.data.SimpleStore({
						fields : ['value', 'text'],
						data : levelData
					})
				}, {
					fieldLabel : 'IP',
					width : 200,
					name : 'ip'
				}, {
					fieldLabel : '端口',
					width : 200,
					name : 'port'
				}, {
					fieldLabel : '用户名',
					width : 200,
					name : 'username'
				}, {
					fieldLabel : '密码',
					width : 200,
					name : 'password'
				}, {
					xtype : 'compositefield',
					fieldLabel : '运行时间',
					items : [{
						name : 'hour',
						width : 40,
						maxValue : 23,
						maxLength : 2,
						xtype : 'numberfield'
					}, {
						xtype : 'displayfield',
						value : '时'
					}, {
						name : 'minute',
						maxValue : 59,
						maxLength : 2,
						width : 40,
						xtype : 'numberfield'
					}, {
						xtype : 'displayfield',
						value : '分'
					}, {
						name : 'second',
						maxLength : 2,
						maxValue : 59,
						width : 40,
						xtype : 'numberfield'
					}, {
						xtype : 'displayfield',
						value : '秒'
					}]
				}]
			},
			buttons : [{
				xtype : "tbfill"
			}, {
				text : '保存',
				handler : function() {
					Ext.getCmp('fp').getForm().submit({
						clientValidation : true,
						url : '../../sbpzConf.do?m=add',
						success : function(form, action) {
							listStore.reload();
							Ext.Msg.alert('保存成功', '保存成功！');
							editWindow.close();
						},
						failure : function(form, action) {
							Ext.Msg.alert('保存失败', '系统错误，请联系管理员。');
						}
					});
				}
			}, {
				text : '关闭',
				handler : function() {
					editWindow.close();
				}
			}],
			listeners : {
				'hide' : function() {

				}
			}
		});

		editWindow.setTitle("新增", "win_add");
		editWindow.show();
	}

	var viewport = new Ext.Viewport({
		layout : 'border',
		border : false,
		items : [listGrid]
	});

	listStore.load();

}); // / Ext onReady end!

function showUpdate() {

	var selectGridRow = listGrid.getSelectionModel().getSelected();
	var id = selectGridRow.data.id;

	var reader = new Ext.data.JsonReader({}, [{
		name : 'id'
	}, {
		name : 'level'
	}, {
		name : 'ip'
	}, {
		name : 'port'
	}, {
		name : 'hour'
	}, {
		name : 'minute'
	}, {
		name : 'second'
	}, {
		name : 'username'
	}, {
		name : 'password'
	}]);

	editWindow = new Ext.Window({
		width : 350,
		modal : true,
		closeAction : 'close',
		items : {
			id : 'fp',
			xtype : 'form',
			frame : true,
			autoScroll : true,
			title : '',
			border : false,
			layout : 'form',
			labelAlign : 'right',
			labelWidth : 80,
			waitMsgTarget : true,
			reader : reader,
			defaultType : 'textfield',
			items : [{
				name : 'id',
				id : 'id',
				width : 0,
				hidden : true
			}, {
				fieldLabel : '平台级别',
				dataIndex:'level',
				valueField : 'value',
				displayField : 'text',
				width : 200,
				xtype : 'combo',
				triggerAction : 'all',
				mode : 'local',
				hiddenName : 'level',
				store : new Ext.data.SimpleStore({
					fields : ['value', 'text'],
					data : levelData
				})
			}, {
				fieldLabel : 'IP',
				width : 200,
				name : 'ip'
			}, {
				fieldLabel : '端口',
				width : 200,
				name : 'port'
			}, {
				fieldLabel : '用户名',
				width : 200,
				name : 'username'
			}, {
				fieldLabel : '密码',
				width : 200,
				name : 'password'
			}, {
				xtype : 'compositefield',
				fieldLabel : '运行时间',
				items : [{
					name : 'hour',
					width : 40,
					maxValue : 23,
					maxLength : 2,
					xtype : 'numberfield'
				}, {
					xtype : 'displayfield',
					value : '时'
				}, {
					name : 'minute',
					maxValue : 59,
					maxLength : 2,
					width : 40,
					xtype : 'numberfield'
				}, {
					xtype : 'displayfield',
					value : '分'
				}, {
					name : 'second',
					maxLength : 2,
					maxValue : 59,
					width : 40,
					xtype : 'numberfield'
				}, {
					xtype : 'displayfield',
					value : '秒'
				}]
			}]
		},
		buttons : [{
			xtype : "tbfill"
		}, {
			text : '保存',
			handler : function() {
				Ext.getCmp('fp').getForm().submit({
					clientValidation : true,
					url : '../../sbpzConf.do?m=update',
					success : function(form, action) {
						listStore.reload();
						Ext.Msg.alert('保存成功', '保存成功！');
						editWindow.close();
					},
					failure : function(form, action) {
						Ext.Msg.alert('保存失败', '系统错误，请联系管理员。');
					}
				});
			}
		}, {
			text : '关闭',
			handler : function() {
				editWindow.close();
			}
		}],
		listeners : {
			'hide' : function() {

			}
		}
	});

	editWindow.setTitle("修改信息", "win_add");
	editWindow.show();

	Ext.getCmp('fp').getForm().load({
		url : '../../sbpzConf.do?m=detail&id=' + id,
		failure : function(form, action) {
			Ext.Msg.alert('错误', '加载数据出错！');
		}
	});
}
