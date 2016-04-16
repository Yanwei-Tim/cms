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
//    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';

    var start = 0;			//分页--开始数
    var pageSize = 15;		//分页--每页数
    var record = new Ext.data.Record.create([
        {name:'id',			mapping:'id'},
        {name:'fileName',		mapping:'fileName'},
        {name:'desc',			mapping:'desc'},
        {name:'collectTime',	mapping:'collectTime'}
    ]);
    var proxy = new Ext.data.HttpProxy({
        url:"../../sysControlRulesInf.do?m=read"
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
//    var boxM = new Ext.grid.CheckboxSelectionModel();   //复选框
    var rowNumber = new Ext.grid.RowNumberer();         //自动 编号
    var colM = new Ext.grid.ColumnModel([
//        boxM,
        rowNumber,
        {menuDisabled : true,sortable:true,header:"系统控制策略文件名称",	dataIndex:"fileName",		align:'center'},
        {menuDisabled : true,header:'系统控制策略描述',	dataIndex:'desc',				align:'center'},
        {menuDisabled : true,sortable:true,header:'更新时间',			dataIndex:'collectTime',		align:'center',		type:'datetimefield'},
        {menuDisabled : true,header:'操作标记',			dataIndex:'id',					align:'center',		renderer: showURL_flag}

    ]);
//    for(var i=6;i<14;i++){
//        colM.setHidden(i,!colM.isHidden(i));                // 加载后 不显示 该项
//    }
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
        animCollapse:true,
        loadMask:{msg:'正在加载数据，请稍后...'},
        border:false,
        collapsible:false,
        columnLines : true,
        cm:colM,
//        sm:boxM,
        store:store,
        stripeRows:true,
        disableSelection:true,
        bodyStyle:'width:100%',
        enableDragDrop: true,
        selModel:new Ext.grid.RowSelectionModel({singleSelect:true}),
        viewConfig:{
            forceFit:true,
            enableRowBody:true,
            getRowClass:function(record,rowIndex,p,store){
                return 'x-grid3-row-collapsed';
            }
        },
        tbar:[
            new Ext.Button({
                id:'btnAdd.info',
                text:'新增',
                iconCls:'add',
                handler:function(){
                    insert_win(grid_panel,store);     //连接到 新增 面板
                }
            }),
            {xtype:"tbseparator"},
			new Ext.Button ({
			    id : 'btnRemove.info',
                text : '删除',
				iconCls : 'delete',
				handler : function() {
					delete_row(grid_panel,store);    //删除 表格 的 一 行 或多行
				}
            })
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
	return "<a href='javascript:;' onclick='detail_win();'>详细</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:;' onclick='update_win();'>修改</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:;' onclick='upload_win();'>上传</a>";
}

function insert_win(grid,store){
    var controlFormPanel = new Ext.form.FormPanel({
        defaultType:'textfield',
        frame:true,
        labelAlign:'right',
        autoScroll:true,
        labelWidth:150,
        defaults:{
            width:200,
            allowBlank:false,
            blankText:'该项不能为空！'
        },
        items:[
            {fieldLabel:"系统控制策略文件名称",	name:'fileName'},
            {fieldLabel:"系统控制策略描述",name:'desc'},
            {fieldLabel:"更新时间",		name:'collectTime',id:'collectTime',xtype:'datetimefield',format: 'Y-m-d H:i:s',listeners:{
                change : function(){
                    var value1 = this.getValue();
                    var dt1 = new Date(value1);//获取选择的时期对象
                    var dt2 = new Date();
                    var value2 = dt2.format('m/d/Y');//①
                    dt2 = new Date(value2);//获取当前日期的时间对象
                    value1 = Date.parse(dt1); //Date.parse的处理很关键
                    value2 = Date.parse(dt2);
                    if (value1 > value2) {
                        Ext.Msg.alert('信息', '选择日期必须小于今天');
                        Ext.getCmp("collectTime").setValue("");
                    }

                }
            }}
		]
    });
    var win = new Ext.Window({
        title:"新增信息",
        width:420,
		layout:'fit',
        height:200,
        modal:true,
        items:controlFormPanel,
        bbar:[
        	'->',
        	{
        		id:'insert.win.info',
        		text:'保存',
        		handler:function(){
        			if (controlFormPanel.form.isValid()) {
                    	controlFormPanel.getForm().submit({
			            	url :'../../sysControlRulesInf.do?m=insert',
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

function delete_row(grid,store){
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
                        url : '../../sysControlRulesInf.do',             // 删除 连接 到后台
                        params :{ids : ids,m : 'delete'},
                        success : function(action){
                            var json = Ext.decode(action.responseText)
                            Ext.MessageBox.show({
                                title:'信息',
                                width:json.code,
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
                labelWidth:150,
                defaults:{
                    width:200,
                    allowBlank:false,
                    blankText:'该项不能为空！'
                },
                items:[
                    {fieldLabel:"系统控制策略标识",	value: item.data.id,xtype:'displayfield'},
                    {id:'id.update.info',name:'id',			value: item.data.id,xtype:'hidden'},
					{id:'fileName.update.info',fieldLabel:"系统控制策略文件名称",	name:'fileName',	value: item.data.fileName},
					{id:'desc.update.info',fieldLabel:"系统控制策略描述",name:'desc',value:item.data.desc},
					{id:'time.update.info',fieldLabel:"更新时间",		name:'collectTime',	value:item.data.collectTime,xtype:'datetimefield',format: 'Y-m-d H:i:s',
                        listeners:{
                            change : function(){
                                var value1 = this.getValue();
                                var dt1 = new Date(value1);//获取选择的时期对象
                                var dt2 = new Date();
                                var value2 = dt2.format('m/d/Y');//①
                                dt2 = new Date(value2);//获取当前日期的时间对象
                                value1 = Date.parse(dt1); //Date.parse的处理很关键
                                value2 = Date.parse(dt2);
                                if (value1 > value2) {
                                    Ext.Msg.alert('信息', '选择日期必须小于今天');
                                    Ext.getCmp("time.update.info").setValue("");
                                }
                            }
                        }
                    }
                ]
            });
        });
    }

    var win = new Ext.Window({
        title:"修改信息",
        width:420,
		layout:'fit',
        height:220,
        modal:true,
        items:[formPanel],
        bbar:['->', {
        	id:'update.win.info',
        	text:'修改',
        	handler:function(){
        		if (formPanel.form.isValid) {
        			formPanel.getForm().submit({
        				url :'../../sysControlRulesInf.do?m=update',
        				method :'POST',
        				waitTitle :'系统提示',
        				waitMsg :'正在连接...',
        				success : function(form,action) {
							var msg = action.result.msg;
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

function upload_win(){
    var controlFormPanel = new Ext.form.FormPanel({
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
            id:'formFile',
            fieldLabel:"文件上传",
            name:'formFile',
            xtype:'textfield',
            inputType: 'file'
        }]
    });
    var win = new Ext.Window({
        title:"上传文件",
        width:420,
		layout:'fit',
        height:100,
        modal:true,
        items:[controlFormPanel],
        bbar:['->', {
        	id:'update.win.info',
        	text:'上传',
        	handler:function(){
        		if (controlFormPanel.form.isValid) {
        			controlFormPanel.getForm().submit({
        				url :'../../sysControlRulesInf.do?m=upload',
        				method :'POST',
        				waitTitle :'系统提示',
        				waitMsg :'正在上传...',
        				success : function(form,action) {
							var msg = action.result.msg;
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
        								win.close();
        							}
        						}
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
                labelWidth:150,
                defaults:{
                    width:200,
                    allowBlank:false,
                    blankText:'该项不能为空！'
                },
                items:[
                    {fieldLabel:"系统控制策略标识",	value: item.data.id},
                	{fieldLabel:"系统控制策略文件名称",	value: item.data.fileName},
                	{fieldLabel:"系统控制策略描述",	value:item.data.desc},
                    {fieldLabel:"更新时间",			value:item.data.collectTime}
                ]
            });
        });
    }
    var win = new Ext.Window({
        title:"详细信息",
        width:420,
		layout:'fit',
        height:200,
        modal:true,
        items:formPanel,
        bbar:[

        	{
        		text:'关闭',
        		handler:function(){
        			win.close();
        		}
        	}
        ]
    }).show();
}