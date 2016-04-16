/*
 * ! Ext JS Library 3.2.0 Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com http://www.extjs.com/license
 */
url = '../alert.do?m=saveconfig';
loadurl = '../alert.do?m=loadconfig';
Ext.onReady(function() {

	Ext.QuickTips.init();

	// turn on validation errors beside the field globally
	Ext.form.Field.prototype.msgTarget = 'side';
	var fp = new Ext.FormPanel({
		frame : true,
		title : '',
		border : false,
		labelAlign : 'right',
		labelWidth : 100,

		width : '100%',
		waitMsgTarget : true,
		renderTo : document.body,
		items : [new Ext.form.FieldSet({
			title : '邮件报警设置',
			autoHeight : true,
			defaultType : 'textfield',
			items : [{
				fieldLabel : '邮件服务器地址',
				name : 'server',
				id : 'server',
				width : 190
			}, {
				fieldLabel : '端口',
				name : 'port',
				id : 'port',
				width : 190
			}, {
				fieldLabel : '邮箱地址',
				name : 'email',
				id : 'email',
				vtype : 'email',
				width : 190
			}, {
				fieldLabel : '用户名',
				name : 'account',
				id : 'account',
				width : 190
			}, {
				fieldLabel : '密码',
				name : 'password',
				id : 'password',
				inputType : 'password',
				width : 190
			}, {
				fieldLabel : '报警标题',
				name : 'title',
				id : 'title',
				width : 190
			}, {
				fieldLabel : '报警频率（分钟）',
				name : 'mailfrequency',
				id : 'mailfrequency',
				width : 190
			}]

		})
        ],
        buttonAlign:'left',
        buttons : [{
            text : '短信测试',
            align : 'center',
            listeners : {
                'click' : function() {
                    Ext.Msg.alert('温馨提示', '该功能暂时未开放...');
                }
            }
        },{
            text : '邮件测试',
            align : 'center',
            listeners : {
                'click' : function() {
                    var addform = new Ext.form.FormPanel({
                        defaultType:'textfield',
                        frame:true,
                        labelAlign:'right',
                        autoScroll:true,
                        labelWidth:150,
                        fileUpload:true,
                        defaults:{
                            width:200,
                            allowBlank:false,
                            blankText:'该项不能为空！'
                        },
                        items:[{
                            fieldLabel:'请输入邮件地址',
                            name:'contact',
                            id:'contact',
                            allowBlank:false,
                            regex: /^[^~!@#$%^&*()_+\x22]+/,
                            regexText:'请输入邮件地址格式',
                            emptyText:'请输入邮件地址格式'
                        }]
                    });
                    var win = new Ext.Window({
                        title:"新增信息",
                        width:400,
                        layout:'fit',
                        height:100,
                        modal:true,
                        items:[addform],
                        bbar:[
                            '->',
                            {
                                id:'insert.win.info',
                                text:'保存',
                                handler:function(){
                                    if (addform.form.isValid()) {
                                        addform.getForm().submit({
                                            url : "../alert.do?m=validatemail&contact="+Ext.getCmp("contact").getValue(),
                                            method :'POST',
                                            waitTitle :'系统提示',
                                            waitMsg :'正在连接...',
                                            success : function(form,action) {
                                                Ext.Msg.alert('发送成功', '测试邮件已发出，请注意查收！');
                                            },
                                            failure : function() {
                                                Ext.Msg.alert('发送失败','测试邮件发送失败，请稍后再试！');
                                            }
                                        });
                                    } else {
                                        Ext.MessageBox.show({
                                            title:'信息',
                                            width:200,
                                            msg:'请输入邮件地址!',
                                            animEl:'insert.win.info',
                                            buttons:{'ok':'确定'},
                                            icon:Ext.MessageBox.ERROR,
                                            closable:false
                                        });
                                    }
                                }
                            },'-',
                            {
                                text:'关闭',
                                handler:function(){
                                    win.close();
                                }
                            }
                        ]

                    }).show();
                }
            }
        }, {
            text : '保存',
            align : 'center',
            listeners : {
                'click' : function() {
                    Ext.Msg.alert('', '正在处理，请稍后...');
                    fp.getForm().submit({
                        clientValidation : true,
                        url : url,
                        params : {
                            newStatus : 'delivered'
                        },
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
		Ext.Ajax.request({
			url : loadurl,
			params : {
				dc : new Date()
			},
			success : function(response, opts) {
				var data = Ext.util.JSON.decode(response.responseText);
				Ext.fly("server").dom.value = data.root.result.server;
				Ext.fly("port").dom.value = data.root.result.port;
				Ext.fly("email").dom.value = data.root.result.email;
				Ext.fly("account").dom.value = data.root.result.account;
				Ext.fly("password").dom.value = data.root.result.password;
				Ext.fly("title").dom.value = data.root.result.title;
				Ext.fly("mailfrequency").dom.value = data.root.result.mailfrequency;

				Ext.fly("smsnumber").dom.value = data.root.result.smsnumber;
				Ext.fly("smstitle").dom.value = data.root.result.smstitle;
				Ext.fly("smsfrequency").dom.value = data.root.result.smsfrequency;
			},
			failure : function(response, opts) {
				Ext.Msg.alert('', "加载配置数据失败，请重试！");
			}
		});
	}
	var viewport = new Ext.Viewport({
		layout : 'fit',
		border : false,
		items : [fp]
	});

});
