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
//					id : 'conf_type',
					name : 'conf_type',
					xtype : 'radio',
					inputValue : 'day',
					boxLabel : '按日备份',
                    listeners:{
                        focus:function (){
                            Ext.getCmp("conf_day").hide();
                            Ext.getCmp("conf_time2").hide();
                        }
                    }
				}, {
					xtype : 'textfield',
//					id : 'conf_time',
					name : 'conf_time',
					width : 60,
					value : '23:00'
				}]
			}, {
				xtype : 'compositefield',
				hideLabel : true,
				items : [{
//					id : 'conf_type',
					name : 'conf_type',
					xtype : 'radio',
					inputValue : 'week',
					boxLabel : '按周备份',
                    listeners:{
                        focus:function (){
                               Ext.getCmp("conf_day").show();
                               Ext.getCmp("conf_time2").show();
                        }
                    }

				    }]
            },{
                xtype : 'radiogroup',
                hidden:true,
                id:'conf_day',
                name:'conf_day',
                columns : 8,
                vertical : false,
                width : 500,
                items : [{
                    boxLabel : '周一',
                    inputValue : 1,
//                    id : 'conf_day',
                    name : 'conf_day'
                    }, {
                        boxLabel : '周二',
                        inputValue : 2,
//                        id : 'conf_day',
                        name : 'conf_day'
                    }, {
                        boxLabel : '周三',
                        inputValue : 3,
//                        id : 'conf_day',
                        name : 'conf_day'
                    }, {
                        boxLabel : '周四',
                        inputValue : 4,
//                        id : 'conf_day',
                        name : 'conf_day'
                    }, {
                        boxLabel : '周五',
                        inputValue : 5,
//                        id : 'conf_day',
                        name : 'conf_day'
                    }, {
                        boxLabel : '周六',
                        inputValue : 6,
//                        id : 'conf_day' ,
                        name : 'conf_day'
                    }, {
                        boxLabel : '周日',
                        inputValue : 7,
//                        id : 'conf_day',
                        name : 'conf_day'
                    },{
                        xtype : 'textfield',
                        name : 'conf_time2',
                        id : 'conf_time2',
                        width : 60,
                        value : '23:00'
                }]

			}, {
				xtype : 'compositefield',
				hideLabel : true,
				items : [{
//					id : 'conf_type',
					name : 'conf_type',
					xtype : 'radio',
					inputValue : 'month',
					boxLabel : '按月备份' ,
                    listeners:{
                        focus:function (){
                            Ext.getCmp("conf_day").hide();
                            Ext.getCmp("conf_time2").hide();
                        }
                    }
				}, {
					xtype : 'textfield',
//					id : 'conf_month_day',
					name : 'conf_month_day',
					width : 40
				}, {
					xtype : 'displayfield',
					value : '日'
				}, {
					xtype : 'textfield',
//					id : 'conf_time3',
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
//				id : 'conf_enabled',
				name : 'conf_enabled'
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
	// 加载配置数据
	if (fp) {
      /* Ext.Ajax.request({
            url : '../../backupConf.do?m=detail',
            success : function(response, opts) {
                var data = Ext.util.JSON.decode(response.responseText);
                Ext.fly("conf_type").dom.value = data.root.result.conf_type;
                Ext.fly("conf_time").dom.value = data.root.result.conf_time;
                Ext.fly("conf_day").dom.value = data.root.result.conf_day;
                Ext.fly("conf_time2").dom.value = data.root.result.conf_time2;
                Ext.fly("conf_month_day").dom.value = data.root.result.conf_month_day;
                Ext.fly("conf_time3").dom.value = data.root.result.conf_time3;
                Ext.fly("conf_enabled").dom.value = data.root.result.conf_enabled;
            },
            failure : function(response, opts) {
                Ext.Msg.alert('', "加载配置数据失败，请重试！");
            }
        });*/
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
