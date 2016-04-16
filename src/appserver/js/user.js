var userGrid;
var editWindow;
var editForm;
var userStore;
var userEditStore;
var editAction = "";
var editPk_id = ""

Ext.onReady(function() {

	Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';

	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'qtip';

	var _name = '';
	var _status = '';
	var userRecord = Ext.data.Record.create(['id', 'userName', 'name', 'phone',
			'status', 'depart', 'email', 'time']);

	userStore = new Ext.data.Store({
		storeId : 'userStore',
		url : "../../userAdmin.do?m=index",
		reader : new Ext.data.JsonReader({
			totalProperty : 'amount',
			root : "results",
			id : 'id'
		}, userRecord),
		baseParams : {
			name : _name,
			status : _status
		}
	});

	userStore.on('beforeload', function() {
		this.setBaseParam('name', _name);
		this.setBaseParam('status', _status);
	});

	userStore.on('load', function() {
		if (userStore.data.length == 0) {
			Ext.Msg.alert('提示', '没有搜索到符合条件的数据！');
		}
	});

	userGrid = new Ext.grid.GridPanel({
		region : 'center',
		border : false,
		store : userStore,
		tbar : [{
			pressed : false,
			text : '增加',
			id : 'add_btn',
			iconCls : 'add',
			handler : function() {
				addUserWindow();
			}
		}

		],
		columns : [new Ext.grid.RowNumberer({
			header : '',
			dataIndex : 'id',
			width : 24,
			align : 'center'
		}), {
			header : '用户名',
			dataIndex : 'userName',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 60
		}, {
			header : '姓名',
			dataIndex : 'name',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 60
		}, {
			header : '电话',
			dataIndex : 'phone',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 100
		}, {
			header : '状态',
			dataIndex : 'status',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 50,
			renderer : function(v, p, r) {
				if (v == '无效') {
					return '<span style="color:red;">无效</span>';
				} else {
					return v;
				}
			}
		}, {
			header : '部门',
			dataIndex : 'depart',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 50
		}, {
			header : '邮箱',
			dataIndex : 'email',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 120
		}, {
			header : '创建时间',
			dataIndex : 'time',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 120
		}, {
			header : '操作',
			align : 'center',
			dataIndex : 'id',
			menuDisabled : true,
			width : 60,
			renderer : function(value, p, r) {
				if(  r.get('userName')=='admin'||r.get('userName')=='auditadmin'
                    ||r.get('userName')=='authadmin'||r.get('userName')=='configadmin'){
					return String
							.format('<a href="javascript:void(0);" onclick="showUpdateUser();return false;">修改</a>'
									+ '&nbsp;&nbsp;'
									+ '<font color="gray">删除</font>');
				}else{
					return String
							.format('<a href="javascript:void(0);" onclick="showUpdateUser();return false;">修改</a>'
									+ '&nbsp;&nbsp;'
									+ '<a href="javascript:void(0);" onclick="deleteUser();return false;">删除</a>');
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
			store : userStore,
			displayInfo : true,
			displayMsg : '第 {0} 条到 {1} 条，共 {2} 条',
			emptyMsg : '没有搜索到符合条件的数据！'
		}
	});

	editWindow = new Ext.Window({
		width : 670,
		height : 400,
		modal : true,
		closeAction : 'hide',
		buttons : [{
			xtype : "tbfill"
		}, {
			text : '保存',
			handler : function() {
				editForm = document.getElementById('userForm');
//                alert(editForm.get("userName"));
                var userName=Ext.getDom("userName").value;
                var name=Ext.getDom("name").value;
                var password=Ext.getDom("password").value;
                var rid=Ext.getDom("rid").value;
                var startIp=Ext.getDom("startIp").value;

                var endIp=Ext.getDom("endIp").value;
                if(userName==""){
                    Ext.Msg.alert('提示', '请输入用户名');
                }else if(name==""){
                    Ext.Msg.alert('提示', '请输入真实名称');
                }else if(password==""){
                    Ext.Msg.alert('提示', '请输入密码');
                }else if(startIp==""){
                    Ext.Msg.alert('提示', '请输入开始IP,如:1.1.1.1或者1.1.1.1-255.255.255.255');
                }else if(endIp==""){
                    Ext.Msg.alert('提示', '请输入结束IP,如:1.1.1.1或者1.1.1.1-255.255.255.255');
                }else if(rid==""){
                    Ext.Msg.alert('提示', '请选择角色');
                }else{
                    Ext.Ajax.request({
                        form : 'userForm',
                        success : function(reponse, action) {
                            var respText = Ext.util.JSON.decode(reponse.responseText);
                            var msg = respText.msg;
                            Ext.MessageBox.show({
                                title:'信息',
                                width:260,
                                msg: msg + ',点击返回页面!',
                                animEl:'update.win.info',
                                buttons:{'ok':'确定'},
                                icon:Ext.MessageBox.INFO,
                                closable:false,
                                fn:function(e){
                                    if(e=='ok'){
                                        userGrid.render();
                                        userStore.reload();
                                        editWindow.hide();
                                    }
                                }
                            });

                        }/*,
                         failure: function(reponse, options) {
                         userGrid.render();
                         userStore.reload();
                         Ext.Msg.alert('提示', '保存信息失败！');
                         editWindow.hide();
                         }*/
                    });
                }


			}
		}, {
			text : '关闭',
			handler : function() {
				editForm = document.getElementById('userForm');
				editWindow.hide();				
			}
		}],
		listeners : {
			'hide' : function() {				
				editForm.reset();
//				editForm = document.getElementById('userForm');
			}
		}
	});


	// 查询面板
	var queryPanel = new Ext.Panel({
		id : "queryPanel",
		region : "north",
		title : "用户列表",
		collapsible : false,
		collapsed : false,
		split : false,
		height : 60,
		margins : "0 0 0 0",
		iconCls : "title",
		border : false,
		layout : 'form',
		labelWidth : 30,
		labelAlign : "right",
		bodyStyle : 'padding:5px;',
		items : [{
			xtype : "container",
			autoEl : "div",
			layout : "column",
			items : [{
				xtype : "container",
				autoEl : "div",
				layout : "form",
				height : 34,
				items : [{
					xtype : "textfield",
					fieldLabel : "用户名",
					name : '_name',
					id : '_name',
					allowBlank : false,
					width : 150
				}]
			}, {
				xtype : "container",
				autoEl : "div",
				layout : "form",
				height : 34,
				items : [{
					xtype : "combo",
					fieldLabel : "状态",
					id : '_status',
					name : '_status',
					value : "全部",
					mode : 'local',
					forceSelection : true,
					triggerAction : 'all',
					typeAhead : true,
					displayField : 'disp_txt',
					store : new Ext.data.SimpleStore({
						fields : [{
							name : 'status',
							mapping : 0
						}, {
							name : 'disp_txt',
							mapping : 1
						}],
						data : [['全部', '全部'], ['有效', '有效'], ['无效', '无效']]
					}),
					width : 150
				}]
			}, {
				xtype : "container",
				autoEl : "div",
				layout : "form",
				height : 34,
				items : [{
					xtype : "button",
					type : "submit",
					anchor : "100%",
					text : "查询",
					width : 50,
					handler : function() {
						_name = Ext.get('_name').dom.value;
						_status = Ext.get('_status').dom.value;

						userStore.load({
							params : {
								name : _name,
								status : _status,
								p : 1
							}
						});
					}
				}]
			}]
		}]
	});

	var viewport = new Ext.Viewport({
		layout : 'border',
		border : false,
		items : [userGrid]
	});

	userStore.load();

	function addUserWindow() {
		editWindow.setTitle("新增用户", "win_add");
		editWindow.removeAll(true);
		editWindow.show();
		editWindow.load({
			url : '../../userAdmin.do?m=showAddUser',
			callback : function() {
				//	init();
			},
			scripts : true
		});
	}
}); // / Ext onReady end!

function showUpdateUser() {
	var selectGridRow = userGrid.getSelectionModel().getSelected();
	editWindow.setTitle("修改用户信息", "win_update");
	editWindow.removeAll(true);
	editWindow.show();
	editWindow.load({
		url : "../../userAdmin.do?m=showUpdateUser&id=" + selectGridRow.data.id,
		callback : function() {
			init();
		},
		nocache: true,
		scripts : true
	});
}

function deleteUser() {
	var selectGridRow = userGrid.getSelectionModel().getSelected();
	if (!selectGridRow) {
		Ext.Msg.alert('警告', '请选中你要删除的行！');
	} else {
		Ext.Msg.confirm('警告', '确定要删除此记录吗？', function(btn) {
			if (btn == 'yes') {
				Ext.Ajax.request({
					url : '../../userAdmin.do?m=deleteUser',
					params : {
						id : selectGridRow.data.id
					},
					success : function(response, options) {
						userStore.reload();
					}
				});
			} else {
				return false;
			}
		});
	}
}

// 修改操作 开始
function updateCorrectionWait() {
	var editform = Ext.getCmp('userForm');
	var selectGridRow = correctionWaitGrid.getSelectionModel().getSelected();
	if (!selectGridRow) {
		Ext.Msg.alert('警告', '请选中你要修改的行！');
	} else {
		var correctionWaitTextfield = editform.findByType('textfield');
		editAction = "update";
		editPk_id = selectGridRow.data.pk_id;
		editWindow.setTitle('修改对象接收', 'edit');
		editWindow.show();
		correctionWaitTextfield[0].setValue({
			text : selectGridRow.data.dept_name,
			id : selectGridRow.data.dept_pk
		});
		correctionWaitTextfield[0].disable();
		correctionWaitTextfield[1].setValue(selectGridRow.data.cname);
		correctionWaitTextfield[2].setValue(selectGridRow.data.identity_no);
		correctionWaitTextfield[3].setValue(selectGridRow.data.sex);
		correctionWaitTextfield[4].setValue(selectGridRow.data.executive);
		correctionWaitTextfield[5].setValue(selectGridRow.data.accusal);
		correctionWaitTextfield[6].setValue(selectGridRow.data.etype);
		correctionWaitTextfield[7].setValue(selectGridRow.data.adjudicator);
		correctionWaitTextfield[8].setValue(selectGridRow.data.judgment_no);
		correctionWaitTextfield[9].setValue(selectGridRow.data.vdate);
	}
}
// 修改操作 结束

// 删除操作 开始
function deleteCorrectionWait() {
	var selectGridRow = correctionWaitGrid.getSelectionModel().getSelected();
	if (!selectGridRow) {
		Ext.Msg.alert('警告', '请选中你要修改的行！');
	} else {
		Ext.Msg.confirm('警告', '确定要删除此记录吗？', function(btn) {
			if (btn == 'yes') {
				correctionWaitEditStore.load({
					params : {
						action : 'delete',
						pk_id : selectGridRow.data.pk_id
					}
				});
			} else {
				return false;
			}
		});
	}
}
// 删除操作 结束
