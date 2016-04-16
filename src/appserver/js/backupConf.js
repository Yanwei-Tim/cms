// 审计备份策略
Ext.onReady(function() {

	Ext.QuickTips.init();

	Ext.form.Field.prototype.msgTarget = 'side';
	var fp = new Ext.FormPanel({
		frame : true,
		title : '',
		border : false,
		labelAlign : 'right',
		buttonAlign : 'left',
		labelWidth : 100,
		width : '100%',
		waitMsgTarget : true,
		renderTo : document.body,
		reader : new Ext.data.JsonReader({}, [{
			name : 'conf_type'
		}, {
			name : 'conf_time'
		}, {
			name : 'conf_day'
		}, {
			name : 'conf_time2'
		}, {
			name : 'conf_month_day'
		}, {
			name : 'conf_time3'
		}, {
			name : 'log_type'
		}, {
			name : 'log_time'
		}, {
			name : 'log_day'
		}, {
			name : 'log_time2'
		}, {
			name : 'log_month_day'
		}, {
			name : 'log_time3'
		}, {
			name : 'conf_file_path'
		}, {
			name : 'conf_enabled'
		}]),
		items : [new Ext.form.FieldSet({
			title : '配置信息备份设置',
			autoHeight : true,
			defaultType : 'textfield',
            buttonAlign:'left',
			items : [{
				xtype : 'compositefield',
				hideLabel : true,
				width : 190,
				items : [{
					name : 'conf_type',
					xtype : 'radio',
					inputValue : 'day',
					boxLabel : '按日备份'
				}, {
					xtype : 'textfield',
					name : 'conf_time',
					width : 60,
					value : '23:00'
				}]
			}, {
				xtype : 'compositefield',
				hideLabel : true,
				items : [{
					name : 'conf_type',
					xtype : 'radio',
					inputValue : 'week',
					boxLabel : '按周备份',
                    listeners:{
                        focus:function (){
//                            return String.format( '<a href="javascript:void(0);" onclick="selectAll(\''+r.get("idsystem")+'\',\''+r.get("username")+'\');"><font color="#fa8072">查看详细</font></a> &nbsp; &nbsp;');
//                             alert(1)
//                                       Ext.getCmp("ssss").show();
                        }
                    }
//                    renderer:show_flag

				    }, {
					xtype : 'radiogroup',
					columns : 4,
					vertical : false,
					width : 300,
					items : [{
						boxLabel : '周一',
						inputValue : 1,
						name : 'conf_day'
					}, {
						boxLabel : '周二',
						inputValue : 2,
						name : 'conf_day'
					}, {
						boxLabel : '周三',
						inputValue : 3,
						name : 'conf_day'
					}, {
						boxLabel : '周四',
						inputValue : 4,
						name : 'conf_day'
					}, {
						boxLabel : '周五',
						inputValue : 5,
						name : 'conf_day'
					}, {
						boxLabel : '周六',
						inputValue : 6,
						name : 'conf_day'
					}, {
						boxLabel : '周日',
						inputValue : 7,
						name : 'conf_day'
					}],
					name : 'conf_day'
				}, {
					xtype : 'textfield',
					name : 'conf_time2',
					width : 60,
					value : '23:00'
				}]
			}, {
				xtype : 'compositefield',
				hideLabel : true,
				items : [{
					name : 'conf_type',
					xtype : 'radio',
					inputValue : 'month',
					boxLabel : '按月备份'
				}, {
					xtype : 'textfield',
					name : 'conf_month_day',
					width : 40
				}, {
					xtype : 'displayfield',
					value : '日'
				}, {
					xtype : 'textfield',
					name : 'conf_time3',
					width : 60,
					value : '23:00'
				}]
			}, {
				xtype : 'compositefield',
				hideLabel : true,
				items : [{
					xtype : 'displayfield',
					value : '备份目录：'
				}, {
					xtype : 'textfield',
					name : 'conf_file_path',
					width : 200
				}]
			},{
				xtype : 'checkbox',
				boxLabel :'启用备份',
				hideLabel : true,
				inputValue : '1',
				id : 'conf_enabled',
				name : 'conf_enabled'
			}]
		}), new Ext.form.FieldSet({
			title : '日志备份设置',
			autoHeight : true,
			defaultType : 'textfield',
			hidden : true,
			items : [{
				xtype : 'compositefield',
				hideLabel : true,
				width : 190,
				items : [{
					name : 'log_type',
					xtype : 'radio',
					inputValue : 'day',
					boxLabel : '按日备份'
				}, {
					xtype : 'textfield',
					name : 'log_time',
					width : 60,
					value : '23:00'
				}]
			}, {
				xtype : 'compositefield',
				hideLabel : true,
				items : [{
					name : 'log_type',
					xtype : 'radio',
					inputValue : 'week',
					boxLabel : '按周备份'
				}, {
					xtype : 'radiogroup',
					columns : 4,
					vertical : false,
					width : 300,
					items : [{
						boxLabel : '周一',
						inputValue : 1,
						value : 1,
						name : 'log_day'
					}, {
						boxLabel : '周二',
						inputValue : 2,
						name : 'log_day'
					}, {
						boxLabel : '周三',
						inputValue : 3,
						name : 'log_day'
					}, {
						boxLabel : '周四',
						inputValue : 4,
						name : 'log_day'
					}, {
						boxLabel : '周五',
						inputValue : 5,
						name : 'log_day'
					}, {
						boxLabel : '周六',
						inputValue : 6,
						name : 'log_day'
					}, {
						boxLabel : '周日',
						inputValue : 7,
						name : 'log_day'
					}],
					name : 'log_day'
				}, {
					xtype : 'textfield',
					name : 'log_time2',
					width : 60,
					value : '23:00'
				}]
			}, {
				xtype : 'compositefield',
				hideLabel : true,
				items : [{
					name : 'log_type',
					xtype : 'radio',
					inputValue : 'month',
					boxLabel : '按月备份'
				}, {
					xtype : 'textfield',
					name : 'log_month_day',
					width : 40
				}, {
					xtype : 'displayfield',
					value : '日'
				}, {
					xtype : 'textfield',
					name : 'log_time3',
					width : 60,
					value : '23:00'
				}]
			}]
		})],
        buttons : [{
            text : '保存',
            listeners : {
                'click' : function() {
                    Ext.Msg.alert('', '正在处理，请稍后...');
                    fp.getForm().submit({
                        clientValidation : true,
                        url : '../../backupConf.do?m=update',
                        success : function(form, action) {
                            Ext.Msg.alert('保存成功', '保存成功！');
                        },
                        failure : function(form, action) {
                            Ext.Msg.alert('保存失败', '系统错误，请联系管理员。');
                        }
                    });

                }
            }
        }]
	});
    function show_flag(){
        return String.format( '<a href="javascript:void(0);" onclick="selectAll(\''+r.get("idsystem")+'\',\''+r.get("username")+'\');"><font color="#fa8072">查看详细</font></a> &nbsp; &nbsp;');
    }
	// 加载配置数据
	if (fp) {
		fp.getForm().load({
			url : '../../backupConf.do?m=detail',
			success : function(form, action) {

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
