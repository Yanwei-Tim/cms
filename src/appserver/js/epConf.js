// 交换平台配置
var listGrid;
var editWindow;
var editWindow2;
var listStore;
var editAction = "";

Ext.onReady(function() {

	Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';

	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'qtip';

	var listRecord = Ext.data.Record.create(['id', 'platformName',
			'platformIp', 'platformPort', 'isEncrypted', 'linkName']);

	listStore = new Ext.data.Store({
		storeId : 'listStore',
		url : "../../epConf.do?m=index",
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
								url : '../../epConf.do?m=delete',
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
			header : '平台名称',
			dataIndex : 'platformName',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 100
		}, {
			header : 'IP',
			dataIndex : 'platformIp',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 60
		}, {
			header : '端口',
			dataIndex : 'platformPort',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 30
		}, {
			header : '是否加密',
			dataIndex : 'isEncrypted',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 30,
			renderer : function(v, p, r) {
				return v == 'Y' ? '是' : '否';
			}
		}, {
			header : '链路名称',
			dataIndex : 'linkName',
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

	editWindow = new Ext.Window({
		width : 500,
		height : 370,
		modal : true,
		autoScroll : true,
		closeAction : 'hide',
		resizable : false,
		buttons : [{
			xtype : "tbfill"
		}, {
			text : '保存',
			handler : function() {
                var platformName=Ext.getDom("platformName").value;//数据交换平台名称
                var platformIp=Ext.getDom("platformIp").value;//平台IP地址
                var platformPort=Ext.getDom("platformPort").value;//连接端口
                var linkName=Ext.getDom("linkName.info").value;//内部链路
                var certificatePwd=Ext.getDom("certificatePwd").value;//证书密码
                if(platformName==""){
                    Ext.Msg.alert('提示', '数据交换平台名称不能为空');
                }else if(platformIp==""){
                    Ext.Msg.alert('提示', '平台IP地址不能为空');
                }else if(platformPort==""){
                    Ext.Msg.alert('提示', '连接端口不能为空');
                }else if(linkName==""){
                    Ext.Msg.alert('提示', '内部链路不能为空');
                }else if(certificatePwd==""){
                    Ext.Msg.alert('提示', '证书密码不能为空');
                }else{
                    Ext.Ajax.request({
                        form : 'editForm',
                        success : function(reponse, options) {
                            listStore.reload();
                            Ext.Msg.alert('提示', '保存信息成功！');
                            editWindow.hide();
                        }
                    });
                }
			}
		}, {
			text : '关闭',
			handler : function() {
				editWindow.hide();
			}
		}],
		listeners : {
			'hide' : function() {
				editForm.getForm().reset();
			}
		}
	});
    editWindow2 = new Ext.Window({
        width : 500,
        height : 370,
        modal : true,
        autoScroll : true,
        closeAction : 'hide',
        resizable : false

    });
	function showAddWindow() {
		editWindow.setTitle("新增信息", "win_add");
		editWindow.removeAll(true);
		editWindow.show();
		editWindow.load('../../epConf.do?m=showAdd');
	}

	var viewport = new Ext.Viewport({
		layout : 'border',
		border : false,
		items : [listGrid]
	});

	listStore.load();

}); // / Ext onReady end!

function showDetail() {
	var selectGridRow = listGrid.getSelectionModel().getSelected();
	editWindow2.setTitle("详细信息", "win_add");
	editWindow2.removeAll(true);
	editWindow2.show();
	editWindow2.load('../../epConf.do?m=detail&id=' + selectGridRow.data.id);
}

function showUpdate() {
	var selectGridRow = listGrid.getSelectionModel().getSelected();
	editWindow.setTitle("修改信息", "win_add");
	editWindow.removeAll(true);
	editWindow.show();
	editWindow.load({
		url : '../../epConf.do?m=showUpdate&id=' + selectGridRow.data.id,
		callback : function() {
			init();
		},
		scripts : true
	});
}

