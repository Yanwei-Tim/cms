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
        {name:'id',				mapping:'id'},
        {name:'abnormalTypeCode',		mapping:'abnormalTypeCode'},
        {name:'connectObjectType',		mapping:'connectObjectType'},
        {name:'exceptionDesc',			mapping:'exceptionDesc'},
        {name:'exceptionIf',	mapping:'exceptionIf'},
        {name:'action',			mapping:'action'},
        {name:'status',			mapping:'status'}
        
    ]);
    var proxy = new Ext.data.HttpProxy({
        url:"sysAbnormalInf.do?m=read"
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
        {menuDisabled : true,sortable:true,	header:"违规事件类型",	dataIndex:"abnormalTypeCode",	align:'center',		renderer : showURL_abNormalTypeCode},
        {menuDisabled : true,				header:'违规对象类型',	dataIndex:'connectObjectType',	align:'center',		renderer : showURL_connectObjectCode},
        {menuDisabled : true,sortable:true,	header:'违规界定条件',	dataIndex:'exceptionIf',		align:'center',		type:'datetimefield'},
        {menuDisabled : true,				header:'违规处理策略描述',	dataIndex:'exceptionDesc',	align:'center'},
        {menuDisabled : true,				header:'违规处理动作',	dataIndex:'action',				align:'center',		renderer : showURL_action},
        {menuDisabled : true,sortable:true,	header:'状态',			dataIndex:'status',				align:'center',		renderer : showURL_status},
        {menuDisabled : true,				header:'操作标记',		dataIndex:'id',					align:'center',		renderer : showURL_flag}

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
        sm:boxM,
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
function showURL_abNormalTypeCode(value){
	if(value=='0001'){
		return '网络违规';
	}else if(value=='0002'){
		return '进程违规';
	}else if(value=='0003'){
		return '外设违规';
	}else if(value=='0004'){
		return 'URL违规';
	}else if(value=='0005'){
        return '流量违规';
    }else if(value=='0006'){
        return '机卡分离违规';
    }else if(value=='0000'){
		return '其它违规';
	}
}

function showURL_connectObjectCode(value){
	var _value;
	if(value == '001'){
		_value = '终端';
	}else if(value == '002'){
		_value = '用户';
	}else if(value == '003'){
		_value = '设备';
	}else if(value == '004'){
		_value = '策略';
	}else if(value == '005'){
		_value = '其它对象';
	}	
	return _value;
}

function showURL_action(value){
	if(value=='0'){
		return '不处理';
	}else if(value=='1'){
		return '停用';
	}else if(value=='2'){
		return '阻断';
	}
}

function showURL_status(value){
	if(value=='0'){
		return '无效';
	}else if(value=='1'){
		return '有效';
	}
}
function showURL_flag(value){
	return "<a href='javascript:;' onclick='detail_win();'>详细</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:;' onclick='update_win();'>修改</a>";
}
var dataTypeCode = [['0001','网络违规'],['0002','进程违规'],['0003','外设违规'],['0004','URL违规'],['0005','流量违规'],['0006','机卡分离违规'],['0000','其它违规']];
var storeTypeCode = new Ext.data.SimpleStore({fields:['value','key'],data:dataTypeCode});
var dataObjectType = [['001','终端'],['002','用户'],['003','设备'],['004','策略'],['005','其它对象']];
var storeObjectType = new Ext.data.SimpleStore({fields:['value','key'],data:dataObjectType});
var dataAction = [['1','停用'],['2','阻断'],['0','不处理']];
var storeAction = new Ext.data.SimpleStore({fields:['value','key'],data:dataAction });
var dataStatus = [['1','有效'],['0','无效']];
var storeStatus = new Ext.data.SimpleStore({fields:['value','key'],data:dataStatus });
function insert_win(grid,store){
    var formPanel = new Ext.form.FormPanel({
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
        items:[{
            fieldLabel:"违规事件类型",
            xtype:'combo',
            hiddenName:'abnormalTypeCode',
            mode:'local',
            emptyText :'--请选择--',
            editable : false,
            typeAhead:true,
            forceSelection: true,
            triggerAction:'all',
            displayField:"key",valueField:"value",
            store:storeTypeCode
        },{
            fieldLabel:'违规对象类型',
            xtype:'combo',
            hiddenName:'connectObjectType',
            mode:'local',
            emptyText :'--请选择--',
            editable : false,
            typeAhead:true,
            forceSelection: true,
            triggerAction:'all',
            displayField:"key",valueField:"value",
            store:storeObjectType
        },{
            fieldLabel:'违规界定条件',
            name:'exceptionIf',
            id:'exceptionIf',
            allowBlank:false,
            regex: /^[>][=]*[\d]+$/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'

        },{
            fieldLabel:'违规处理策略描述',	name:'exceptionDesc'
        },{
            fieldLabel:'违规处理动作',
            xtype:'combo',
            hiddenName:'action',
            mode:'local',
            emptyText :'--请选择--',
            editable : false,
            typeAhead:true,
            forceSelection: true,
            triggerAction:'all',
            displayField:"key",valueField:"value",
            store:storeAction
        },{
            fieldLabel:'状态',
            xtype:'combo',
            hiddenName:'status',
            mode:'local',
            emptyText :'--请选择--',
            editable : false,
            typeAhead:true,
            forceSelection: true,
            triggerAction:'all',
            displayField:"key",valueField:"value",
            store:storeStatus
        }]
    });
    var win = new Ext.Window({
        title:"新增信息",
        width:420,
        layout:'fit',
        height:250,
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
                            url :'sysAbnormalInf.do?m=insert',
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
                        url : 'sysAbnormalInf.do',             // 删除 连接 到后台
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
                    { fieldLabel:"违规处理策略标识",	value: item.data.id ,xtype:'displayfield'},{name:'id',value:item.data.id,xtype:'hidden'},{
		            	fieldLabel:"违规事件类型",
		            	xtype:'combo',
		            	hiddenName:'abnormalTypeCode',
		            	value:item.data.abnormalTypeCode,
		            	mode:'local',
		                emptyText :'--请选择--',
		                editable : false,
		                typeAhead:true,
		                forceSelection: true,
		                triggerAction:'all',
		                displayField:"key",valueField:"value",
		                store:storeTypeCode
		            },{
		            	fieldLabel:'违规对象类型',
		            	xtype:'combo',
		            	hiddenName:'connectObjectType',
		            	value:item.data.connectObjectType,
		            	mode:'local',
		                emptyText :'--请选择--',
		                editable : false,
		                typeAhead:true,
		                forceSelection: true,
		                triggerAction:'all',
		                displayField:"key",valueField:"value",
		                store:storeObjectType
		             },{
		            	 fieldLabel:'违规界定条件',
                        name:'exceptionIf',
                        allowBlank:false,
                        regex: /^[>][=]*[\d]+$/,
                        regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
                        emptyText:'例:">=200",或者">200"',
                        value:item.data.exceptionIf
		             },{
		            	 fieldLabel:'违规处理策略描述',	name:'exceptionDesc',value:item.data.exceptionIf
		             },{
		            	fieldLabel:'违规处理动作',
		            	xtype:'combo',
		            	hiddenName:'action',
		            	value:item.data.action,
		            	mode:'local',
		                emptyText :'--请选择--',
		                editable : false,
		                typeAhead:true,
		                forceSelection: true,
		                triggerAction:'all',
		                displayField:"key",valueField:"value",
		                store:storeAction
		            },{
		            	fieldLabel:'状态',
		            	xtype:'combo',
		            	hiddenName:'status',
		            	value:item.data.status,
		            	mode:'local',
		            	emptyText :'--请选择--',
		            	editable : false,
		            	typeAhead:true,
		            	forceSelection: true,
		            	triggerAction:'all',
		            	displayField:"key",valueField:"value",
		            	store:storeStatus
		         }]
            });
        });
    }
    var win = new Ext.Window({
        title:"修改信息",
        width:420,
		layout:'fit',
        height:270,
        modal:true,
        items:formPanel,
        bbar:['->', {
        	id:'update.win.info',
        	text:'修改',
        	handler:function(){
        		if (formPanel.form.isValid()) {        			
        			formPanel.getForm().submit({
        				url :'sysAbnormalInf.do?m=update',
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
                	{ fieldLabel:"违规处理策略标识",	value: item.data.id },
                	{ fieldLabel:"违规事件类型", value:showURL_abNormalTypeCode(item.data.abnormalTypeCode) },
                	{ fieldLabel:'违规对象类型', value:showURL_connectObjectCode(item.data.connectObjectType) },
                	{ fieldLabel:'违规界定条件',	value:item.data.exceptionIf },
                	{ fieldLabel:'违规处理策略描述',	value:item.data.exceptionDesc },
                	{ fieldLabel:'违规处理动作', value:showURL_action(item.data.action) },
                	{ fieldLabel:'状态', value:showURL_status(item.data.status) }
                ]
            });
        });
    }
    var win = new Ext.Window({
        title:"详细信息",
        width:420,
		layout:'fit',
        height:270,
        modal:true,
        items:formPanel

    }).show();
}