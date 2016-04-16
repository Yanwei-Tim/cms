/**
 * Created by IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 11-11-8
 * Time: 上午10:24
 * To change this template use File | Settings | File Templates.
 */
//==================================== -- 非可信数据源 extjs 页面-- =============================================================
Ext.onReady(function(){
    Ext.BLANK_IMAGE_URL = '../Images/ext/s.gif';
    Ext.form.Field.prototype.msgTarget = 'side';

    var start = 0;			//分页--开始数
    var pageSize = 15;		//分页--每页数
    var record = new Ext.data.Record.create([
        {name:'id',						mapping:'id'},
        {name:'strategyDesc',			mapping:'strategyDesc'},
        {name:'strategyProtocalType',	mapping:'strategyProtocalType'},
        {name:'srcIp',					mapping:'srcIp'},
        {name:'descIp',					mapping:'descIp'},
        {name:'srcPort',				mapping:'srcPort'},
        {name:'descPort',				mapping:'descPort'},
        {name:'collectTime',			mapping:'collectTime'}
    ]);
    var proxy = new Ext.data.HttpProxy({
        url:"sysStrategyInf.do?m=read"
    });
    var reader = new Ext.data.JsonReader({
        totalProperty:"total",
        root:"rows"
    },record);
    var store = new Ext.data.GroupingStore({
        id:"store.info",
        proxy : proxy,
        reader : reader
    });
	store.load({
        params:{
            start:start,limit:pageSize
        }
    });
    var boxM = new Ext.grid.CheckboxSelectionModel();   //复选框
    var rowNumber = new Ext.grid.RowNumberer();         //自动 编号
    var colM = new Ext.grid.ColumnModel([
        boxM,
        rowNumber,
        {menuDisabled : true,				header:"安全策略描述",		dataIndex:"strategyDesc",			align:'center'},
        {menuDisabled : true,sortable:true,	header:'安全策略协议类型',	dataIndex:'strategyProtocalType',	align:'center',		renderer: showURL_type,width:50},
        {menuDisabled : true,				header:'源IP地址范围',		dataIndex:'srcIp',					align:'center'},
        {menuDisabled : true,				header:'目的IP地址范围',		dataIndex:'descIp',					align:'center'},
        {menuDisabled : true,				header:'源端口范围',			dataIndex:'srcPort',				align:'center',		width:50},
        {menuDisabled : true,				header:'目的端口范围',		dataIndex:'descPort',				align:'center',		width:50},
        {menuDisabled : true,sortable:true,	header:'统计时间',			dataIndex:'collectTime',			align:'center',		type:'datefield',width:50},
        {menuDisabled : true,				header:'操作标记',			dataIndex:'id',						align:'center',		renderer: showURL_flag,width:50}

    ]);
    for(var i=2;i<3;i++){
        //colM.setHidden(i,!colM.isHidden(i));                // 加载后 不显示 该项
    }
    colM.defaultSortable = true;
    var page_toolbar = new Ext.PagingToolbar({
        pageSize : pageSize,
        store:store,
        displayInfo:true,
        displayMsg:"显示第{0}条记录到第{1}条记录，一共{2}条",
        emptyMsg:"没有记录",
        beforePageText:"第",
        afterPageText:"页,共{0}页"
    });
    var grid_panel = new Ext.grid.GridPanel({
        id:'grid.info',
        plain:true,
        hieght:setHeight(),
        width:setWidth(),
        loadMask:{msg:'正在加载数据，请稍后...'},
        border:false,
        cm:colM,
        sm:boxM,
        store:store,
        stripeRows:true,
        autoExpandColumn:2,
        bodyStyle:'width:100%',
        viewConfig:{forceFit:true},
        tbar:[{
                id:'btnAdd.info',
                text:'新增',
                iconCls:'add',
                handler:function(){
                    insert_win(grid_panel,store);     //连接到 新增 面板
                }
            },'-',{
			    id : 'btnRemove.info',
                text : '删除',
				iconCls : 'delete',
				handler : function() {
					delete_row();    
				}
            }
        ],
        view:new Ext.grid.GroupingView({
            forceFit:true,
            groupingTextTpl:'{text}({[values.rs.length]}条记录)'
        }),
        bbar:page_toolbar
    });
    var port = new Ext.Viewport({
        layout:'fit',
        renderTo: Ext.getBody(),
        items:[grid_panel]
    });
});
function setHeight(){
	var h = document.body.clientHeight-8;
	return h;
}

function setWidth(){
    return document.body.clientWidth-8;
}
//============================================ -- javascript function -- =============================================================================

function showURL_flag(value){
	return "<a href='javascript:;' onclick='detail_win();'>详细</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:;' onclick='update_win();'>修改</a>";
}

function showURL_type(value){
	if(value=='1001'){
		return 'tcp';
	}else if(value=='1002'){
		return 'ftp';
	}else if(value=='1003'){
		return 'http';
	}else if(value=='1004'){
		return 'https';
	}else if(value=='2001'){
		return 'udp';
	}else if(value=='0000'){
		return '其它';
	}
	 
}
var dataTransport = [['1001','tcp'],['1002','ftp'],['1003','http'],['1004','https'],['2001','udp'],['0000','其它']];
var storeTransport = new Ext.data.SimpleStore({fields:['value','key'],data:dataTransport});

function insert_win(grid,store){
    var formPanel = new Ext.form.FormPanel({
        defaultType:'textfield',
        frame:true,
        labelAlign:'right',
        autoScroll:true,
        labelWidth:130,
        defaults:{
            width:220,
            allowBlank:false,
            blankText:'该项不能为空！'
        },
        items:[
            {fieldLabel:"安全策略描述",	name:'strategyDesc'},
            {
            	fieldLabel:"安全策略协议类型",name:'strategyProtocalType',
            	hiddenName: 'strategyProtocalType',
            	xtype:'combo',
            	mode:'local',
                emptyText :'--请选择--',
                editable : false,
                typeAhead:true,
                forceSelection: true,
                triggerAction:'all',
                displayField:"key",valueField:"value",
                store:storeTransport
            },{
            	fieldLabel:"源IP地址范围",	name:'srcIp',
            	regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])||(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])-((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])))$/,
                regexText:'不规范(例):1.1.1.1-1.1.1.9或者1.1.1.5',
                emptyText:'(例):1.1.1.1-1.1.1.9或者1.1.1.5'
            },{
            	fieldLabel:"目的IP地址范围",name:'descIp',
            	regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])||(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])-((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])))$/,
                regexText:'不规范(例):1.1.1.1-1.1.1.9或者1.1.1.5',
                emptyText:'(例):1.1.1.1-1.1.1.9或者1.1.1.5'
            },{
            	fieldLabel:'源端口范围',	name:'srcPort',
            	regex:/^((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])||((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])-(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])))$/,
                regexText:'不规范(例):1-65536或者100',
                emptyText:'(例):1-65536或者100'
            },{
            	fieldLabel:"目的端口范围",	name:'descPort',
            	regex:/^((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])||((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])-(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])))$/,
                regexText:'不规范(例):1-65536或者100',
                emptyText:'(例):1-65536或者100'
            },
            {fieldLabel:"统计时间",	name:'collectTime',xtype:'datetimefield',format: 'Y-m-d H:i:s'}
		]
    });
    var win = new Ext.Window({
        title:"新增信息",
        width:420,
        height:280,
        layout:'fit',
        modal:true,
        items:formPanel,
        bbar:[
        	'->',
        	{
        		id:'insert.win.info',
        		text:'保存',
        		handler:function(){
        			if (formPanel.form.isValid()) {
                    	formPanel.getForm().submit({
			            	url :'sysStrategyInf.do?m=insert',
			                method :'POST',
			                waitTitle :'系统提示',
			                waitMsg :'正在连接...',
			                success : function(form,action) {
			                	var msg = action.result.msg;
				                Ext.MessageBox.show({
				                	title:'信息',
				                    width:250,
				                    msg:msg+',点击返回页面!',
			                        animEl:'insert.win.info',
			                         
			                        buttons:{'ok':'确定'},
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
			                },
							failure : function() {
				            	Ext.MessageBox.show({
				                	title:'信息',
				                    width:250,
				                    msg:'新增失败,请与管理员联系!',
				                    animEl:'insert.win.info',
				                     
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
                            animEl:'insert.win.info',
                             
                            buttons:{'ok':'确定'},
                            icon:Ext.MessageBox.ERROR,
                            closable:false
                        });
                    }
        		}
        	},
        	{
        		text:'关闭',
        		handler:function(){
        			win.close();
        		}
        	}
        ]
    }).show();
}

function delete_row(){
	var grid = Ext.getCmp('grid.info');
    var store = Ext.getCmp('grid.info').getStore();
    var selModel = grid.getSelectionModel();
    var count = selModel.getCount();
    if(count==0){
        Ext.MessageBox.show({
            title:'信息',
            msg:'<font color="green">您没有勾选任何记录!</font>',
            animEl:'btnRemove.info',
            buttons:Ext.Msg.OK,
            buttons:{'ok':'确定'},
            icon:Ext.MessageBox.INFO,
            closable:false
        });
    }else if(count > 0){
        var ids = new Array();
        var record = grid.getSelectionModel().getSelections();
        for(var i = 0; i < record.length; i++){
            ids[i] = record[i].get('id');
        }
        Ext.MessageBox.show({
            title:'信息',
            msg:'<font color="green">确定要删除所选记录？</font>',
            animEl:'btnRemove.info',
            width:250,
            buttons:Ext.Msg.YESNO,
            buttons:{'ok':'确定','no':'取消'},
            icon:Ext.MessageBox.INFO,
            closable:false,
            fn:function(e){
                if(e == 'ok'){
                    Ext.Ajax.request({
                        url : 'sysStrategyInf.do',             // 删除 连接 到后台
                        params :{ids : ids,m : 'delete'},
                        success : function(action){
                            var json = Ext.decode(action.responseText);
                            var code = json.code;
                            Ext.MessageBox.show({
                                title:'信息',
                                width:code,
                                msg:json.msg,
                                animEl:'btnRemove.info',
                                 
                                buttons:{'ok':'确定'},
                                icon:Ext.MessageBox.INFO,
                                closable:false,
		                        fn:function(e){
		                        	if(e=='ok'){
		                            	grid.render();
		                            	store.reload();
		                        	}
		                        }
                            });
                        },
                        failure : function(){
                            Ext.MessageBox.show({
                                title:'信息',
                                msg:'请与后台服务人员联系!',
                                animEl:'btnRemove.info',
                                 
                                buttons:{'ok':'确定'},
                                icon:Ext.MessageBox.ERROR,
                                closable:false
                            });
                        }
                    });
                }
            }
        });
    }
}

function update_win(){
    var grid = Ext.getCmp('grid.info');
    var store = Ext.getCmp('grid.info').getStore();
    var selModel = grid.getSelectionModel();
    var formPanel;
    if(selModel.hasSelection()){
        var selections = selModel.getSelections();
        Ext.each(selections,function(item){
            formPanel = new Ext.form.FormPanel({
                defaultType:'textfield',
                frame:true,
                labelAlign:'right',
                autoScroll:true,
                labelWidth:130,
                defaults:{
                    width:220,
                    allowBlank:false,
                    blankText:'该项不能为空！'
                },
                items:[
                    {fieldLabel:"安全策略标识",	name:'id',	value: item.data.id,xtype:'displayfield'},
                    {xtype:"hidden",			name:'id',	value: item.data.id},
					{fieldLabel:"安全策略描述",	name:'strategyDesc',	value: item.data.strategyDesc},
					{
		            	fieldLabel:"安全策略协议类型",name:'strategyProtocalType',
		            	hiddenName: 'strategyProtocalType',
		            	value:item.data.strategyProtocalType,
		            	xtype:'combo',
		            	mode:'local',
		                emptyText :'--请选择--',
		                editable : false,
		                typeAhead:true,
		                forceSelection: true,
		                triggerAction:'all',
		                displayField:"key",valueField:"value",
		                store:storeTransport
		            },{
		            	fieldLabel:"源IP地址范围",	name:'srcIp',	value:item.data.srcIp,
		            	regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])||(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])-((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])))$/,
		                regexText:'不规范(例):1.1.1.1-1.1.1.9或者1.1.1.5',
		                emptyText:'(例):1.1.1.1-1.1.1.9或者1.1.1.5'
		            },{
		            	fieldLabel:"目的IP地址范围",name:'descIp',	value:item.data.descIp,
		            	regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])||(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])-((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])))$/,
		                regexText:'不规范(例):1.1.1.1-1.1.1.9或者1.1.1.5',
		                emptyText:'(例):1.1.1.1-1.1.1.9或者1.1.1.5'
		            },{
		            	fieldLabel:'源端口范围',	name:'srcPort',	value:item.data.srcPort,
		            	regex:/^((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])||((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])-(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])))$/,
		                regexText:'不规范(例):1-65536或者100',
		                emptyText:'(例):1-65536或者100'
		            },{
		                fieldLabel:"目的端口范围",	name:'descPort',	value:item.data.descPort,
		                regex:/^((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])||((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])-(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])))$/,
		                regexText:'不规范(例):1-65536或者100',
		                emptyText:'(例):1-65536或者100'
		            },
					{fieldLabel:"统计时间",		name:'collectTime',	value:item.data.collectTime,xtype:'datetimefield',format: 'Y-m-d H:i:s'}
                ]
            });
        });
    }
    var win = new Ext.Window({
        title:"修改信息",
        width:420,
		layout:'fit',
        height:300,
        modal:true,
        items:formPanel,
        bbar:['->', {
        	id:'update.win.info',
        	text:'修改',
        	handler:function(){
        		if (formPanel.form.isValid()) {        			
        			formPanel.getForm().submit({
        				url :'sysStrategyInf.do?m=update',
        				method :'POST',
        				waitTitle :'系统提示',
        				waitMsg :'正在连接...',
        				success : function(form,action) {
        					var msg = action.result.msg;
        					Ext.MessageBox.show({
        						title:'信息',
        						width:260,
        						msg:msg+',点击返回页面!',
        						animEl:'update.win.info',
        						 
        						buttons:{'ok':'确定'},
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
        				},
        				failure : function() {
        					Ext.MessageBox.show({
        						title:'信息',
        						width:260,
        						msg:'测试失败，请与管理员联系!',
        						animEl:'update.win.info',
        						 
        						buttons:{'ok':'确定'},
        						icon:Ext.MessageBox.ERROR,
        						closable:false
        					});
        				}
        			});
        		} else {
        			Ext.MessageBox.show({
        				title:'信息',
        				width:260,
        				msg:'请填写完成再提交!',
        				animEl:'update.win.info',
        				 
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

function detail_win(){
    var grid = Ext.getCmp('grid.info');
    var selModel = grid.getSelectionModel();
    var formPanel;
    if(selModel.hasSelection()){
        var selections = selModel.getSelections();
        Ext.each(selections,function(item){
            formPanel = new Ext.form.FormPanel({
                defaultType:'displayfield',
                frame:true,
                labelAlign:'right',
                autoScroll:true,
                labelWidth:130,
                defaults:{
                    width:200,
                    allowBlank:false,
                    blankText:'该项不能为空！'
                },
                items:[
                    {fieldLabel:"安全策略标识",	value: item.data.id},
                	{fieldLabel:"安全策略描述",	value: item.data.strategyDesc},
                	{fieldLabel:"安全策略协议类型",value:showURL_type(item.data.strategyProtocalType)},
                    {fieldLabel:"源IP地址范围",	value:item.data.srcIp},
                    {fieldLabel:"目的IP地址范围",value:item.data.descIp},
                    {fieldLabel:'源端口范围',	value:item.data.srcPort},
                    {fieldLabel:"目的端口范围",	value:item.data.descPort},
                    {fieldLabel:"统计时间",		value:item.data.collectTime}
                ]
            });
        });
    }
    var win = new Ext.Window({
        title:"详细信息",
        width:420,
		layout:'fit',
        height:300,
        modal:true,
        items:formPanel

    }).show();
}