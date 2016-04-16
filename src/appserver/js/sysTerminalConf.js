// 终端设备管理
var listGrid;
var editWindow;
var editWindow2;
var listStore;
var editAction = "";

Ext.onReady(function() {

	Ext.BLANK_IMAGE_URL = '../js/ext/resources/images/default/s.gif';

	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'qtip';

	var listRecord = Ext.data.Record.create(['id', 'terminalName','userId']);

	listStore = new Ext.data.Store({
		storeId : 'listStore',
		url : "../sysTerminalConf.do?m=index",
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
		id:'grid.info',
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
			text : '批量导入',
			id : 'add_many_btn',
			iconCls : 'add',
			handler : function() {
				showAddManyWindow(listGrid,listStore);
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
                            var myMask = new Ext.LoadMask(Ext.getBody(),{
                                msg : '正在处理,请稍后...',
                                removeMask : true
                            });
                            myMask.show();
                            Ext.Ajax.request({
								url : '../sysTerminalConf.do?m=delete',
								params : {
									ids : ids
								},
								success : function(response, options) {
                                    myMask.hide();
									listStore.reload();
								}
							});
						} else {
							return false;
						}
					});
				}

			}
		},{
            pressed : false,
			text : '终端加固',
			id : 'terminal_btn',
			iconCls : 'terminal',
			handler : function() {
                showTerminalWindow();
            }
        },{
            pressed : false,
            text : '清空',
            id:'clear.info',
            iconCls : 'delete',
            handler : function() {
                Ext.MessageBox.show({
                    title:'信息',
                    width:250,
                    msg:'确定要清空设备日志记录?',
                    animEl:'clear.info',
                    buttons:{'ok':'确定','no':'取消'},
                    icon:Ext.MessageBox.WARNING,
                    closable:false,
                    fn:function(e){
                        if(e=='ok'){
                            var myMask = new Ext.LoadMask(Ext.getBody(),{
                                msg : '正在处理,请稍后...',
                                removeMask : true
                            });
                            myMask.show();
                            Ext.Ajax.request({
                                url : '../sysTerminalConf.do?m=deleteSysTerminalInfo',
                                success : function(response, options) {
                                    myMask.hide();
                                    var respText = Ext.util.JSON.decode(response.responseText);
                                    var msg = respText.msg;
                                    Ext.MessageBox.show({
                                        title:'信息',
                                        width:300,
                                        msg: msg,
                                        animEl:'clear.info',
                                        buttons:{'ok':'确定'},
                                        icon:Ext.MessageBox.INFO,
                                        closable:false,
                                        fn:function(e){
                                            if(e=='ok'){
                                                listStore.reload();
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    }
                });
            }
        }],
		columns : [sm, {
			id : 'id',
			header : '接入终端标识',
			dataIndex : 'id',
			width : 24,
			align : 'center',
			sortable : true,
			menuDisabled : true
		}, {
			header : '接入终端名称',
			dataIndex : 'terminalName',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 30
		}, {
			header : '身份证号',
			dataIndex : 'userId',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 30
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
								+ '<a href="javascript:void(0);" onclick="showUpdate();return false;">修改</a>'
								+ '&nbsp;&nbsp;'
								+ '<a href="javascript:void(0);" onclick="showProcess();return false;">进程查看</a>'
								+ '&nbsp;&nbsp;'
								+ '<a href="javascript:void(0);" onclick="showNet();return false;">网络查看</a>'
								+ '&nbsp;&nbsp;'
								+ '<a href="javascript:void(0);" onclick="showOS();return false;">操作系统查看</a>'
						);
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
				Ext.Ajax.request({
					form : 'editForm',
					success : function(reponse, options) {
						listStore.reload();
						Ext.Msg.alert('提示', '保存信息成功！');
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
        resizable : false,

        listeners : {
            'hide' : function() {
                editForm.getForm().reset();
            }
        }
    });
	function showAddWindow() {
		editWindow.setTitle("新增信息", "win_add");
		editWindow.removeAll(true);
		editWindow.show();
		editWindow.load('../sysTerminalConf.do?m=showAdd');
	}

	
	var viewport = new Ext.Viewport({
		layout : 'border',
		border : false,
		items : [listGrid]
	});

	listStore.load();

}); // / Ext onReady end!

function showAddManyWindow(grid,store){
	var sysTerminalAddManyForm = new Ext.form.FormPanel({
        frame:true,
        labelWidth:60,
        labelAlign:'right',
        fileUpload:true,
        border:false,
        defaults : {
            width : 300,
            allowBlank : false,
            blankText : '该项不能为空！'
        },
        items:[{
            xtype:'displayfield',
            value:'一次导入数据最多5,000条!导入文件为[*.xls]或者[*.et]'
        },{
            fieldLabel:"模板下载",
            xtype:'displayfield',
            value : '<a href="javascript:download();">下载</a>'
        },{
            id:'formFile',
            fieldLabel:"导入文件",
            name:'formFile',
            xtype:'textfield',
            inputType: 'file'
        }]
    });
	var win = new Ext.Window({
		title:'批量导入',
		width:400,
		height:150,
		layout:'fit',
        modal:true,
		items:[sysTerminalAddManyForm],
		bbar:['->',{
    		id:'insert.win.info',
    		text:'导入',
    		handler:function(){
    			if (sysTerminalAddManyForm.form.isValid()) {
                	sysTerminalAddManyForm.getForm().submit({
		            	url :'../sysTerminalConf.do?m=addMany',
		                method :'POST',
		                waitTitle :'系统提示',
		                waitMsg :'正在导入...',
		                success : function(form,action) {
		                	var msg = action.result.msg;
			                Ext.MessageBox.show({
			                	title:'信息',
			                    width:300,
			                    msg:msg,
		                        animEl:'insert.win.info',
		                        buttons:Ext.MessageBox.YESNO,
		                        buttons:{'ok':'确定','no':'取消'},
		                        icon:Ext.MessageBox.INFO,
		                        closable:false,
		                        fn:function(e){
		                        	if(e=='ok'){
		                            	grid.render();
		                            	store.reload();
		                            	win.close();
		                        	}
		                        }
		                	});
		                }
		        	});
                } else {
                    Ext.MessageBox.show({
                        title:'信息',
                        width:200,
                        msg:'请填写完成再提交!',
                        animEl:'insert.win.info',
                        buttons:{'ok':'确定'},
                        icon:Ext.MessageBox.ERROR,
                        closable:false
                    });
                }
    		}
    	},{
    		text:'关闭',
    		handler:function(){
    			win.close();
    		}
    	}]
	}).show();
}
function download() {
	window.open('../sysTerminalConf.do?m=download','_blank');
}
function showTerminalWindow(){
    var record = new Ext.data.Record.create([
        {name:'id',	mapping:'id'},
        {name:'ip',   mapping:'ip'},
        {name:'port', mapping:'port'}
    ]);
    var proxy = new Ext.data.HttpProxy({
        url:"../sysTerminalConf.do?m=showTerminalAddress"
    });
    var reader = new Ext.data.JsonReader({
        totalProperty:"total",
        root:"rows"
    },record);
    var store = new Ext.data.GroupingStore({
        id:"store.run.info",
        proxy : proxy,
        reader : reader
    });
	var formPanel = new Ext.form.FormPanel({
        frame:true,
        labelWidth:100,
        labelAlign:'right',
        border:false,
        defaults : {
            width : 200,
            allowBlank : false,
            blankText : '该项不能为空！'
        },
        items:[{
            id:'id.terminal.info',
            xtype:'hidden',
            name:'id'
        },{
            id:'ip.terminal.info',
            fieldLabel:"终端加固IP地址",
            name:'ip',
            xtype:'textfield',
            regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
            regexText:'这个不是IP(例:1.1.1.1)',
            emptyText:'请输入IP(例:1.1.1.1)'
        },{
            id:'port.terminal.info',
            fieldLabel:"终端加固端口",
            name:'port',
            xtype:'textfield',
            regex:/^(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9])$/,
            regexText:'这个不是端口类型1—65536',
            emptyText:'请输入端口1—65536'
        }]
    });
	var win = new Ext.Window({
		title:'终端加固设置',
		width:400,
		height:150,
		layout:'fit',
        modal:true,
		items:[formPanel],
		bbar:['->',{
    		id:'terminal.win.info',
    		text:'保存',
    		handler:function(){
    			if (formPanel.form.isValid()) {
                	formPanel.getForm().submit({
		            	url : '../sysTerminalConf.do?m=updateTerminalAddress',
		                method :'POST',
		                waitTitle :'系统提示',
		                waitMsg :'正在处理...',
		                success : function(form,action) {
		                	var msg = action.result.msg;
			                Ext.MessageBox.show({
			                	title:'信息',
			                    width:300,
			                    msg:msg,
		                        animEl:'terminal.win.info',
		                        buttons:{'ok':'确定'},
		                        icon:Ext.MessageBox.INFO,
		                        closable:false,
		                        fn:function(e){
                                    if(e='ok'){
                                        win.close();
                                    }
                                }
		                	});
		                }
		        	});
                } else {
                    Ext.MessageBox.show({
                        title:'信息',
                        width:200,
                        msg:'请填写完成再提交!',
                        animEl:'terminal.win.info',
                        buttons:{'ok':'确定'},
                        icon:Ext.MessageBox.ERROR,
                        closable:false
                    });
                }
    		}
    	},{
    		text:'关闭',
    		handler:function(){
    			win.close();
    		}
    	}]
	}).show();
    store.load();
    store.addListener('load',function(){
        var record = store.getAt(0);
        Ext.getCmp('id.terminal.info').setValue(record.get('id'));
        Ext.getCmp('ip.terminal.info').setValue(record.get('ip'));
        Ext.getCmp('port.terminal.info').setValue(record.get('port'));
    });
}

function showProcess(){
    Ext.Ajax.request({
		url:'../sysTerminalConf.do?m=processQuery',
		success:function(r,o) {
			var respText = Ext.util.JSON.decode(r.responseText);
			var _url = respText.url;
			window.open(_url,"_blank");
		}
	});
}

function showNet(){
    Ext.Ajax.request({
		url:'../sysTerminalConf.do?m=netQuery',
		success:function(r,o) {
			var respText = Ext.util.JSON.decode(r.responseText);
			var _url = respText.url;
			window.open(_url,"_blank");
		}
	});
}

function showOS(){
    Ext.Ajax.request({
		url:'../sysTerminalConf.do?m=osQuery',
		success:function(r,o) {
			var respText = Ext.util.JSON.decode(r.responseText);
			var _url = respText.url;
			window.open(_url,"_blank");
        }
	});
}

function showDetail() {
	var selectGridRow = listGrid.getSelectionModel().getSelected();
	editWindow2.setTitle("详细信息", "win_add");
	editWindow2.removeAll(true);
	editWindow2.show();
	editWindow2.load('../sysTerminalConf.do?m=detail&id=' + selectGridRow.data.id);
}

function showUpdate() {
	var selectGridRow = listGrid.getSelectionModel().getSelected();
	editWindow.setTitle("修改信息", "win_add");
	editWindow.removeAll(true);
	editWindow.show();
	editWindow.load({
		url : '../sysTerminalConf.do?m=showUpdate&id=' + selectGridRow.data.id,
		callback : function() {
			init();
		},
		scripts : true
	});
}
function getCity(province,selname){
	Ext.Ajax.request({
		url:'../district.do?m=comboChildByParent',
		success:function(response){
			Ext.getDom(selname).innerHTML = response.responseText;
		},
		params:{
			parentId:province,
			name:selname
		}
	});
}
function getCurrentCity(userParentZone,userZone){
	Ext.Ajax.request({
		url:'../district.do?m=comboChildByParentShow',
		success:function(response){
			Ext.getDom("userZone").innerHTML = response.responseText;
		},
		params:{
			parentId:userZone,
			name:"userZone",
			id:userZone
		}
	});	
}
