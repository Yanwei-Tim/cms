// 设备管理配置
var listGrid;
var editWindow;
var listStore;
var editAction = "";

Ext.onReady(function() {

	Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';

	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'qtip';

	var listRecord = Ext.data.Record.create(['id', 'linkName', 'equName',
			'netStation', 'ip']);

	listStore = new Ext.data.Store({
		storeId : 'listStore',
		url : "../../equConf.do?m=index",
		reader : new Ext.data.JsonReader({
			totalProperty : 'amount',
			root : "results",
			id : 'id'
		}, listRecord),
		baseParams : {
			p : 1
		}
	});

	/*
	 * listStore.on('load', function() { if (listStore.data.length == 0) {
	 * Ext.Msg.alert('提示', '没有搜索到符合条件的数据！'); } });
	 */

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
								url : '../../equConf.do?m=delete',
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
		}

		],
		columns : [sm, {
			header : 'ID',
			dataIndex : 'id',
			width : 24,
			align : 'center',
			hidden : true
		}, {
            header : '设备名称',
            dataIndex : 'equName',
            align : 'center',
            sortable : false,
            menuDisabled : true,
            width : 60
        },{
			header : '链路',
			dataIndex : 'linkName',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 60
		},  {
			header : '区域',
			dataIndex : 'netStation',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 30,
			renderer : function(value, p, r) {
				return value == 'i' ? '内网' : '外网';
			}
		}, {
			header : '首选IP',
			dataIndex : 'ip',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 50
		}, {
			header : '操作',
			align : 'center',
			dataIndex : 'id',
			menuDisabled : true,
			width : 60,
			renderer : function(value, p, r) {
				return String
						.format('<a href="javascript:void(0);" onclick="showDetail();return false;">详细</a>'
								+ '&nbsp;&nbsp;'
								+ '<a href="javascript:void(0);" onclick="showUpdate();return false;">修改</a>');
			}
		}],
		viewConfig : {
			forceFit : true
		},
		loadMask : {
			msg : '正在加载数据，请稍侯……'
		},
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

	editWindow = new Ext.Window({
		width : 600,
		height : 400,
		modal : true,
		autoScroll : true,
		closeAction : 'hide',
		resizable : false,
		buttons : [{
			xtype : "tbfill"
		}, {
			text : '保存',
			handler : function() {
                    Ext.Ajax.request({
                    form : 'editForm',
                    success : function(reponse, options) {
                        var respText = Ext.util.JSON.decode(reponse.responseText);
                        var msg = respText.msg;
                        listStore.reload();
                        Ext.Msg.alert('温馨提示', msg);
                        editWindow.hide();
                    }
                });
			}
		}, {
			text : '关闭',
			handler : function() {
				editWindow.hide();
			}
		}],
		listeners : {
			'hide' : function() {

			}
		}
	});

	var viewport = new Ext.Viewport({
		layout : 'border',
		border : false,
		items : [listGrid]
	});

	function showAddWindow() {
		editWindow.setTitle("新增设备", "win_add");
		editWindow.removeAll(true);
		editWindow.show();
		editWindow.load({
                url:'../../equConf.do?m=showAdd',
                callback : function() {

                },
                scripts : true
		});
	}

	listStore.load();

}); // / Ext onReady end!

function showDetail() {
	var selectGridRow = listGrid.getSelectionModel().getSelected();
	editWindow.setTitle("详细信息", "win_add");
	editWindow.removeAll(true);
	editWindow.show();
	editWindow.load('../../equConf.do?m=detail&id=' + selectGridRow.data.id);
}

function showUpdate() {
	var selectGridRow = listGrid.getSelectionModel().getSelected();
	editWindow.setTitle("修改信息", "win_add");
	editWindow.removeAll(true);
	editWindow.show();
	editWindow.load({
		url : '../../equConf.do?m=showUpdate&id=' + selectGridRow.data.id,
		callback : function() {
			init();
		},
        nocache: true,
		scripts : true
	});
}

function download(fileName) {
	window.open('../../equConf.do?m=download&fileName=' + fileName,	'_blank');
}

