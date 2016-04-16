var roleGrid;
var editWindow;
var roleStore;

Ext.onReady(function() {

	Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';

	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'qtip';

	var roleRecord = Ext.data.Record.create(['id', 'name', 'description','time']);

	roleStore = new Ext.data.Store({
		storeId : 'roleStore',
		url : "../../roleAdmin.do?m=index",
		reader : new Ext.data.JsonReader({
			root : "results",
			id : 'id'
		}, roleRecord),
		baseParams : {
			p : 1
		}
	});

	roleStore.on('load', function() {
		if (roleStore.data.length == 0) {
			Ext.Msg.alert('提示', '没有搜索到符合条件的数据！');
		}
	});

	roleGrid = new Ext.grid.GridPanel({
		region : 'center',
		border : false,
		store : roleStore,
		tbar : [{
			pressed : false,
			text : '增加',
			id : 'add_btn',
			iconCls : 'add',
			handler : function() {
				addRoleWindow();
			}
		}

		],
		columns : [new Ext.grid.RowNumberer({
			header : '',
			dataIndex : 'id',
			width : 24,
			align : 'center'
		}), {
			header : '角色名',
			dataIndex : 'name',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 100
		}, {
			header : '描述',
			dataIndex : 'description',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 260
		}, {
			header : '创建时间',
			dataIndex : 'time',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 80
		}, {
			header : '操作',
			align : 'center',
			dataIndex : 'id',
			menuDisabled : true,
			width : 60,
			renderer : function(value, p, r) {
                if ( r.get('name')=='超级管理员'||r.get('name')=='授权管理员'
                    ||r.get('name')=='审计管理员'||r.get('name')=='配置管理员'){
                    return String
                        .format('<a href="javascript:void(0);" onclick="showUpdateRole();return false;">修改</a>'
                                + '&nbsp;&nbsp;<font color="gray">删除</font>');
                } else {
                    return String
                        .format('<a href="javascript:void(0);" onclick="showUpdateRole();return false;">修改</a>'
                                + '&nbsp;&nbsp;'
                                + '<a href="javascript:void(0);" onclick="deleteRole();return false;">删除</a>');
                }
			}
		}],
		viewConfig : {
			forceFit : true
		},
		loadMask : {
			msg : '正在加载数据，请稍侯……'
		},
		stripeRows : true,
		sm : new Ext.grid.RowSelectionModel({
			singleSelect : true
		}),

		bbar : {
			xtype : 'paging',
			id : 'pagingbar',
			pageSize : 15,
			store : roleStore,
			displayInfo : true,
			displayMsg : '第 {0} 条到 {1} 条，共 {2} 条',
			emptyMsg : '没有搜索到符合条件的数据！'
		}
	});

	editWindow = new Ext.Window({
		width : 570,
		height : 300,
		modal : true,
		autoScroll : true,
		closeAction : 'hide',
		buttons : [{
			xtype : "tbfill"
		}, {
			text : '保存',
			handler : saveRole
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

	function addRoleWindow() {
		editWindow.setTitle("新增角色", "win_add");
		editWindow.removeAll(true);
		editWindow.show();
		editWindow.load('../../roleAdmin.do?m=showAddRole');
	}

	function saveRole() {
		Ext.Ajax.request({
			form : 'roleForm',
			success : function(reponse, options) {
                var respText = Ext.util.JSON.decode(reponse.responseText);
                var msg = respText.msg;
				roleStore.reload();
				Ext.Msg.alert('提示', msg);
				editWindow.hide();
			}
		});
	}

	roleStore.load();

	var viewport = new Ext.Viewport({
		layout : 'border',
		border : false,
		items : [roleGrid]
	});

}); // / Ext onReady end!

function showUpdateRole() {
	var selectGridRow = roleGrid.getSelectionModel().getSelected();
	editWindow.setTitle("修改角色信息", "win_add");
	editWindow.removeAll(true);
	editWindow.show();
	editWindow.load({
		url : "../../roleAdmin.do?m=showUpdateRole&id=" + selectGridRow.data.id
		/*callback : function() {
			init();
		},
		scripts : true ,
        nocache: true*/
	});
}

function deleteRole() {
	var selectGridRow = roleGrid.getSelectionModel().getSelected();
	if (!selectGridRow) {
		Ext.Msg.alert('警告', '请选中你要删除的行！');
	} else {
		Ext.Msg.confirm('警告', '确定要删除此记录吗？', function(btn) {
			if (btn == 'yes') {
				Ext.Ajax.request({
					url : '../../roleAdmin.do?m=deleteRole',
					params : {
						id : selectGridRow.data.id
					},
					success : function(response, options) {
						roleStore.reload();
					}
				});
			} else {
				return false;
			}
		});
	}
}
