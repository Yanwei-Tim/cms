Ext.onReady(function(){
    Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';

    var data = Ext.data.Record.create(["id","name"]);
    var store = new Ext.data.Store({
        url : '../../parentEpConf.do?m=findAll',
        reader:new Ext.data.JsonReader({totalProperty:"total",root:"rows",id:"id"},data)
    });
    store.load();

	var formPanel = new Ext.form.FormPanel({
        frame:true,
        labelWidth:150,
        border:false,
        loadMask : { msg : '正在加载数据，请稍后.....' },
        labelAlign:'right',
        buttonAlign :'left',
        defaults : {
            width : 180,
            allowBlank : false,
            blankText : '该项不能为空！'
        },
		items:[{
			width:400,
            title:'平台注册信息手动上报 -- 使用说明',
            xtype:'fieldset',
            html:"<font color='green'>1.不上报的请去掉勾!<br></font>"
		},{
            id:'sysname.info',
            fieldLabel:'平台名称',
            xtype:'combo',
            name:'sysname',
            emptyText:'请选择平台名称',
            editable : true,
            mode:'local',                   //数据来本地local 来之远程服务器用remote
            store:store,
            displayField:'name',
            valueField:'id',
            allowBlank : false,
            forceSelection:true,
            triggerAction:"all"
        },{
			id:'sysbizinf.info',
			fieldLabel:'接入应用',
			xtype:'checkbox',
			name:'sysbizinf',
			checked:true
		},{
			id:'syscontrolrulesinf.info',
			fieldLabel:'系统控制策略',
			xtype:'checkbox',
			name:'syscontrolrulesinf',
			checked:true
		},{
			id:'sysdeviceinf.info',
			fieldLabel:'系统核心设备',
			xtype:'checkbox',
			name:'sysdeviceinf',
			checked:true
		},{
			id:'sysoutlinkinf.info',
			fieldLabel:'系统外部链路',
			xtype:'checkbox',
			name:'sysoutlinkinf',
			checked:true
		},{
			id:'sysreginf.info',
			fieldLabel:'系统注册信息',
			xtype:'checkbox',
			name:'sysreginf',
			checked:true
		},{
			id:'sysstrategyinf.info',
			fieldLabel:'系统安全策略',
			xtype:'checkbox',
			name:'sysstrategyinf',
			checked:true
		},{
			id:'systerminalinfo.info',
			fieldLabel:'接入终端信息',
			xtype:'checkbox',
			name:'systerminalinfo',
			checked:true
		}],
        buttons:[
        	new Ext.Toolbar.Spacer({width:150}),
            {
                text:"上报",
                id:'uplink.info',
                handler:function(){
                    if (formPanel.form.isValid()) {
                        var sysname = Ext.getCmp('sysname.info').getValue();
                    	var systerminalinfo = Ext.getCmp('systerminalinfo.info').getValue();
                    	var sysbizinf = Ext.getCmp('sysbizinf.info').getValue();
                    	var syscontrolrulesinf = Ext.getCmp('syscontrolrulesinf.info').getValue();
                    	var sysdeviceinf = Ext.getCmp('sysdeviceinf.info').getValue();
                    	var sysoutlinkinf = Ext.getCmp('sysoutlinkinf.info').getValue();
                    	var sysreginf = Ext.getCmp('sysreginf.info').getValue();
                    	var sysstrategyinf = Ext.getCmp('sysstrategyinf.info').getValue();
                        formPanel.getForm().submit({
                            url :'uplink.do',
                            params :{
                            	m:'uplink',sysid:sysname,sysbizinf:sysbizinf,syscontrolrulesinf:syscontrolrulesinf,sysdeviceinf:sysdeviceinf,
                            	sysoutlinkinf:sysoutlinkinf,sysreginf:sysreginf,sysstrategyinf:sysstrategyinf,systerminalinfo:systerminalinfo
                            },
                            method :'POST',
                            waitTitle :'系统提示',
                            waitMsg :'正在上报...',
                            success : function(form,action) {
                                var flag = action.result.msg;
                                Ext.MessageBox.show({
                                	title:'信息',
                                    msg:flag+'，点击返回页面!',
                                    animEl:'uplink.info',
                                    width:300,
                                    buttons:Ext.Msg.OK,
                                    buttons:{'ok':'确定'},
                                    icon:Ext.MessageBox.INFO,
                                    closable:false
                                });
                            },
                            failure : function() {
                                Ext.MessageBox.show({
                                	title:'信息',
                                    msg:'上报失败，请与管理员联系!',
                                    animEl:'uplink.info',
                                    width:300,
                                    buttons:Ext.Msg.OK,
                                    buttons:{'ok':'确定'},
                                    icon:Ext.MessageBox.ERROR,
                                    closable:false
                                });
                            }
                        });
                    } else {
                        Ext.MessageBox.show({
                        	title:'信息',
                            msg:'上报失败，请填写完成再保存!',
                            animEl:'uplink.info',
                            width:300,
                            buttons:Ext.Msg.OK,
                            buttons:{'ok':'确定'},
                            icon:Ext.MessageBox.ERROR,
                            closable:false
                        });
                    }
                }
            }
        ]
    });

    var panel = new Ext.Panel({
        layout:'fit',
        frame:true,
        border : false,
        autoScroll:true,
        items:[formPanel]
    });
    new Ext.Viewport({
    	layout:'fit',
    	renderTo:Ext.getBody(),
        border:false,
    	items:[panel]
    });
});
