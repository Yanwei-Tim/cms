Ext.onReady(function () {
    Ext.BLANK_IMAGE_URL = '../../../js/ext/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    var record = new Ext.data.Record.create([
        {name:'ldapIp', mapping:'ldapIp'} ,
        {name:'ldapPort',mapping:'ldapPort'},
        {name:'ldapBaseDN', mapping:'ldapBaseDN'},
        {name:'ldapAdmin', mapping:'ldapAdmin'},
        {name:'ldapAdminPass', mapping:'ldapAdminPass'}
    ]);

    var proxy = new Ext.data.HttpProxy({
        url:"../../../SysConfig_selectCaConfig.action"
    });

    var reader = new Ext.data.JsonReader({
        totalProperty:"totalCount",
        root:"root"
    }, record);

    var store = new Ext.data.GroupingStore({
        id:"store.info",
        proxy:proxy,
        reader:reader
    });

    store.load();
    store.on('load',function(){
        var ldapIp = store.getAt(0).get('ldapIp');
        var ldapPort = store.getAt(0).get('ldapPort');
        var ldapBaseDN = store.getAt(0).get('ldapBaseDN');
        var ldapAdmin = store.getAt(0).get('ldapAdmin');
        var ldapAdminPass = store.getAt(0).get('ldapAdminPass');
        Ext.getCmp('ca.ldap.ip').setValue(ldapIp);
        Ext.getCmp('ca.ldap.port').setValue(ldapPort);
        Ext.getCmp('ca.ldap.ldapBaseDN').setValue(ldapBaseDN);
        Ext.getCmp('ca.ldap.ldapAdmin').setValue(ldapAdmin);
        Ext.getCmp('ca.ldap.ldapAdminPass').setValue(ldapAdminPass);
    });

    var formPanel = new Ext.form.FormPanel({
        plain:true,
        width:500,
        labelAlign:'right',
        labelWidth:200,
        defaultType:'textfield',
        defaults:{
            width:250,
            allowBlank:false,
            blankText:'该项不能为空!'
        },
        items:[
            new Ext.form.TextField({
                fieldLabel : 'ldap连接ip',
                name : 'ldapIp',
                regex:/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/,
                regexText:'请输入正确的ip地址',
                id:"ca.ldap.ip",
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),
            new Ext.form.TextField({
                fieldLabel : 'ldap连接端口',
                id:"ca.ldap.port",
                regex:/^[1-9]$|(^[1-9][0-9]$)|(^[1-9][0-9][0-9]$)|(^[1-9][0-9][0-9][0-9]$)|(^[1-6][0-5][0-5][0-3][0-5]$)/,
                regexText:'请输入正确的端口',
                name : 'ldapPort',
                allowBlank : false,
                blankText : "不能为空，请正确填写"
            }),
            new Ext.form.TextField({
                fieldLabel:'ldap BaseDN',
                name:'ldapBaseDN',
                id:"ca.ldap.ldapBaseDN",
                allowBlank:false,
                blankText:"登陆DN"
            }),
            new Ext.form.TextField({
                fieldLabel:'ldap 管理用户',
                name:'ldapAdmin',
                allowBlank:false,
                id:"ca.ldap.ldapAdmin",
                blankText:"ldap 管理用户"
            }) ,
            new Ext.form.TextField({
                fieldLabel:'ldap 管理用户密码',
                name:'ldapAdminPass',
                id:"ca.ldap.ldapAdminPass",
                inputType:'password',
                allowBlank:false,
                blankText:"ldap 管理用户密码"
            })
        ],
        buttons:[
            {
                id:'insert_win.info',
                text:'保存配置',
                handler:function () {
                    if (formPanel.form.isValid()) {
                        formPanel.getForm().submit({
                            url:"../../../SysConfig_saveConfig.action",
                            method:'POST',
                            waitTitle:'系统提示',
                            waitMsg:'正在连接...',
                            success:function () {
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:250,
                                    msg:'保存成功,点击返回页面!',
                                    
                                    buttons:{'ok':'确定'},
                                    icon:Ext.MessageBox.INFO,
                                    closable:false
                                });
                            },
                            failure:function () {
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:250,
                                    msg:'保存失败，请与管理员联系!',
                                    
                                    buttons:{'ok':'确定'},
                                    icon:Ext.MessageBox.ERROR,
                                    closable:false
                                });
                            }
                        });
                    } else {
                        Ext.MessageBox.show({
                            title:'信息',
                            width:200,
                            msg:'请填写完成再提交!',
                            
                            buttons:{'ok':'确定'},
                            icon:Ext.MessageBox.ERROR,
                            closable:false
                        });
                    }
                }
            },
            {
                text:'测试ldap连接',
                handler:function () {
                    formPanel.getForm().submit({
                        url:"../../../SysConfig_ldapConnections.action",
                        method:'POST',
                        waitTitle:'系统提示',
                        waitMsg:'正在连接...',
                        success:function () {
                            Ext.MessageBox.show({
                                title:'信息',
                                width:250,
                                msg:'Ldap服务器已连通!',
                                
                                buttons:{'ok':'确定'},
                                icon:Ext.MessageBox.INFO
                            });
                        },
                        failure:function () {
                            Ext.MessageBox.show({
                                title:'信息',
                                width:250,
                                msg:'ldap服务器连通失败!',
                                
                                buttons:{'ok':'确定'},
                                icon:Ext.MessageBox.ERROR,
                                closable:false
                            });
                        }
                    });
                }
            }
        ]
    });

    var panel = new Ext.Panel({
        plain:true,
        width:600,
        border:false,
        items:[{
            id:'panel.info',
            xtype:'fieldset',
            title:'ldap 连接配置',
            width:530,
            items:[formPanel]
        }]
    });
    new Ext.Viewport({
        layout :'fit',
        renderTo:Ext.getBody(),
        autoScroll:true,
        items:[{
            frame:true,
            autoScroll:true,
            items:[panel]
        }]
    });

});


