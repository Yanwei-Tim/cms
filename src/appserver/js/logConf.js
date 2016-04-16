// 审计库管理
Ext.onReady(function() {

	Ext.QuickTips.init();

	Ext.form.Field.prototype.msgTarget = 'side';

	var listRecord = Ext.data.Record.create(['name']);

	var listStore = new Ext.data.Store({
		storeId : 'listStore',
		url : "../logConf.do?m=showSqlFiles",
		reader : new Ext.data.JsonReader({
			totalProperty : 'amount',
			root : "results",
			id : 'name'
		}, listRecord)
	});

	var fp = new Ext.FormPanel({
		frame : true,
		autoScroll : true,
//		title : '',
		border : false,
		layout : 'form',
		labelAlign : 'right',
        buttonAlign : 'left',
		labelWidth : 160,
		autoWidth : true,
		waitMsgTarget : true,
		renderTo : document.body,
		reader : new Ext.data.JsonReader({}, [{
			name : 'dbip'
		}, {
			name : 'dbport'
		}, {
			name : 'dbname'
		}, {
			name : 'username'
		}, {
			name : 'password'
        }, {
            name : 'dbUsed'
		}, {
			name : 'backup_dbip'
		}, {
			name : 'backup_dbport'
		}, {
			name : 'backup_dbname'
		}, {
			name : 'backup_username'
		}, {
			name : 'backup_password'
		}]),
		items : [ new Ext.form.FieldSet({
            title : '数据库配置',
            autoHeight : true,
            autoWidth : true,
            defaultType : 'textfield',
            items : [{
                fieldLabel : 'IP',
                name : 'dbip',
                id : 'dbip',
                width : 300,
                regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
                regexText:'这个不是IP(例:1.1.1.1)',
                emptyText:'请输入IP(例:1.1.1.1)'
            }, {
                fieldLabel : '数据库端口',
                name : 'dbport',
                id : 'dbport',
                width : 300,
                regex:/^(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9])$/,
                regexText:'这个不是端口类型1—65536',
                emptyText:'请输入端口1—65536'
            }, {
                fieldLabel : '数据库名称',
                name : 'dbname',
                id : 'dbname',
                width : 300
            }, {
                fieldLabel : '用户名',
                name : 'username',
                id : 'username',
                width : 300
            }, {
                fieldLabel : '口令',
                name : 'password',
                id : 'password',
                width : 300
            }]
        }),new Ext.form.FieldSet({
            title : '报警阀值',
            autoHeight : true,
            autoWidth : true,
            defaultType : 'textfield',
//                layout:'form',
                items:[{
                    width : 30,
                    fieldLabel:"审计存储容量报警阀值(%)",
                    xtype:'textfield',
                    id:'dbUsed.info',
                    name:'dbUsed',
                    regex:/^(100|[1-9][0-9]|[0-9])$/,
                    regexText : '大小为[0-100]',
                    emptyText : '大小为[0-100]',
                    listeners:{
                        specialkey : function(textfield,e){
                            if(e.getKey() == Ext.EventObject.ENTER){
                                var dbUsed = this.getValue();
                                if(dbUsed>=0&&dbUsed<=100){
                                    Ext.getCmp('dbUsed.slider.info').setValue(Number(dbUsed));
                                }else{
                                    Ext.Msg.alert('修改失败', '请输入0—100之间的整数!');
                                }
                            }
                        }
                    }
                },{
                    width:300,
                    fieldLabel:'滑动设置',
                    id:'dbUsed.slider.info',
                    xtype:'slider',
                    increment:1,
                    minValue:0,
                    maxValue:100,
                    plugins: new Ext.slider.Tip({
                        getText: function(thumb){
                            Ext.getCmp('dbUsed.info').setValue(thumb.value);
                            return String.format('<b>{0}% 开始报警</b>', thumb.value);
                        }
                    })
                }]
        }), new Ext.form.FieldSet({
            title : '备份数据库配置',
            autoHeight : true,
            autoWidth : true,
            hidden : true,
            defaultType : 'textfield',
            items : [{
                fieldLabel : 'IP',
                name : 'backup_dbip',
                id : 'backup_dbip',
                width : 300
            }, {
                fieldLabel : '数据库端口',
                name : 'backup_dbport',
                id : 'backup_dbport',
                width : 300
            }, {
                fieldLabel : '数据库名称',
                name : 'backup_dbname',
                id : 'backup_dbname',
                width : 300
            }, {
                fieldLabel : '用户名',
                name : 'backup_username',
                id : 'backup_username',
                width : 300
            }, {
                fieldLabel : '口令',
                name : 'backup_password',
                id : 'backup_password',
                width : 300
            }]
	    })],
		buttons : [{
            id:'init.info',
			text : '初始化数据库',
			listeners : {
				'click' : function() {
                    Ext.MessageBox.show({
                        title:'信息',
                        width:200,
                        msg:'确定要初始化数据库?',
                        animEl:'init.info',
                        buttons:Ext.MessageBox.YESNO,
                        buttons:{'ok':'确定','no':'取消'},
                        icon:Ext.MessageBox.QUESTION,
                        closable:false,
                        fn:function(e){
                            if(e=='ok'){
                                fp.getForm().submit({
                                    clientValidation : true,
                                    url : '../logConf.do?m=createBizTables',
                                    waitTitle :'系统提示',
		                            waitMsg :'正在初始化(成功后会自动重启)...',
                                    success : function(form, action) {
                                        Ext.Msg.alert('提示信息',
                                           '初始化数据库<font color="green">成功</font>！系统重启中..');
                                    },
                                    failure : function(form, action) {
                                        Ext.Msg.alert('提示信息',
                                           '初始化数据库<font color="red">失败</font>！');
                                    }
                               });
                           }
                        }
                    });
				}
			}
		}, {
			text : '备份数据信息导入',
			listeners : {
				'click' : function() {
					var window = new Ext.Window({
						width : 300,
						height : 400,
						modal : true,
						layout : 'border',
						title : '请选择要导入的备份数据信息备份文件',
						items : {
							id : 'grid',
							xtype : 'grid',
							region : 'center',
							border : false,
							store : listStore,
							columns : [new Ext.grid.RowNumberer({
								header : '',
								dataIndex : 'id',
								width : 24,
								align : 'center'
							}), {
								header : '名称',
								dataIndex : 'name',
								align : 'center',
								sortable : false,
								menuDisabled : true,
								width : 60
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
							})
						},
						closeAction : 'hide',
						buttons : [{
							xtype : "tbfill"
						}, {
							text : '导入',
							handler : function() {
								var selectGridRow = Ext.getCmp('grid')
										.getSelectionModel().getSelected();
								if (!selectGridRow) {
									Ext.Msg.alert('警告', '请选中你要导入的文件！');
									return;
								}
								Ext.Ajax.request({
									url : '../logConf.do?m=importConfTables',
									params : {
										file : selectGridRow.data.name
									},
									success : function(response, options) {
										Ext.Msg.alert('导入成功', '导入成功！');
										window.close();
									}
								});
							}
						}, {
							text : '关闭',
							handler : function() {
								window.hide();
							}
						}],
						listeners : {
							'hide' : function() {

							}
						}
					});
					window.show();
					listStore.load();
				}
			}
		}, {
			text : '保存',
			listeners : {
				'click' : function() {
                    if(fp.form.isValid()){
                        fp.getForm().submit({
                            clientValidation : true,
                            url : '../logConf.do?m=update',
                            success : function(form, action) {
                                Ext.Msg.alert('保存成功', '保存成功！');
                            },
                            failure : function(form, action) {
                                Ext.Msg.alert('保存失败', '系统错误，请联系管理员。');
                            }
                        });
                    }else{
                        Ext.Msg.alert('保存失败', '请正确填写!');
                    }
				}
			}
		}, {
			text : '取消',
			listeners : {
				'click' : function() {
					fp.getForm().reset();
				}
			}
		}]
	});

	// 加载配置数据
	if (fp) {
		fp.getForm().load({
			url : '../logConf.do?m=detail',
            success : function(form,action){
                var dbUsed = Ext.getCmp('dbUsed.info').getValue();
                Ext.getCmp('dbUsed.slider.info').setValue(Number(dbUsed));
            },
			failure : function(form, action) {
				Ext.Msg.alert('错误', '加载数据出错！');
			}
		});
	}
	var viewport = new Ext.Viewport({
		layout : 'fit',
		border : false,
		items : [fp]
	});

});
