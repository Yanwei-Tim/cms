/**
 * 已上报项管理
 */

Ext.onReady(function() {
	Ext.QuickTips.init();
    var pageSize = 15;
    var data = Ext.data.Record.create(["id","name"]);
    var parentStore = new Ext.data.Store({
        url : '../../parentEpConf.do?m=findAll',
        reader:new Ext.data.JsonReader({totalProperty:"total",root:"rows",id:"id"},data)
    });
    parentStore.load();
	var tb = new Ext.Toolbar({
		width : 720,
		height : 30,
		items : [{
            id:'btnRemove.info',
    		text : '删除',
            iconCls:'delete',
			listeners : {
				click : function() {
					delete_rows();
				}
			}
        },{
			xtype : 'tbspacer',
			width : 10
        },'对象名称',new Ext.form.ComboBox({
                id : "objectName.info",
                store : new Ext.data.ArrayStore({
				    autoDestroy : true,
					fields : ['value', 'text'],
					data : [['sysbizinf', '业务注册'],
                        ['sysdeviceinf', '设备注册'],
                        ['sysoutlinkinf', '外部链路'],
                        ['sysstrategyinf', '安全策略'],
                        ['systerminalinfo', '终端信息'],
						['sysabnormal', '违规信息']]
			    }),
                valueField : 'value',
                displayField : 'text',
                mode : 'local',
                emptyText : '--请选择--',
                forceSelection : true,
                triggerAction : 'all',
                selectOnFocus : true,
                width : 100
        }),{
			xtype : 'tbspacer',
			width : 10
        },'平台名称',new Ext.form.ComboBox({
                id : "parentExchangePlatform",
                store : parentStore,
                valueField : 'id',
                displayField : 'name',
                mode : 'local',
                emptyText : '--请选择--',
                forceSelection : true,
                triggerAction : 'all',
                selectOnFocus : true,
                width : 100
        }),{
			xtype : 'tbspacer',
			width : 10
		},{
    		text : '查询',
			listeners : {
				click : function() {
                    var objectName = Ext.fly("objectName.info").dom.value == '--请选择--'
                                    ?'':Ext.getCmp('objectName.info').getValue();
                    var platformId = Ext.fly("parentExchangePlatform").dom.value == '--请选择--'
                                    ?'':Ext.getCmp('parentExchangePlatform').getValue();
                    if(platformId==''){
                        Ext.MessageBox.show({
                            title:'信息',
                            msg:'请先选择平台!',
                            buttons:{'ok':'确定'},
                            icon:Ext.MessageBox.WARNING,
                            closable:false
                        });
                    } else {
                        store.setBaseParam("objectName", objectName);
                        store.setBaseParam("platformId", platformId);
                        store.load({ params : { start : 0, limit : pageSize } });
                    }
				}
			}
        }]
	});

	// create the Data Store
	var store = new Ext.data.JsonStore({
		url:'../../sysQueryService.do?m=index',
		root : 'rows',
		totalProperty : 'total',
		idProperty : 'id',
		fields : [{
			name : 'id',
			mapping : 'id'
		}, {
			name : 'idSystem',
			mapping : 'idSystem'
		}, {
			name : 'objectName',
			mapping : 'objectName'
		}, {
			name : 'ids',
			mapping : 'ids'
		}]
	});
    var boxM = new Ext.grid.CheckboxSelectionModel();   //复选框
	var grid = new Ext.grid.GridPanel({
        id:'grid.info',
		store : store,
		loadMask : {msg:'正在加载,请稍后...'},
		trackMouseOver : true,// 鼠标悬浮高亮显示
		columnLines : true,// 列分隔符
		columns : [boxM,{
			header : "ID",
			dataIndex : 'id',
			width : 100,
			menuDisabled : true
		}, {
			header : "系统标识",
			dataIndex : 'idSystem',
			width : 100,
			menuDisabled : true
		}, {
			header : "表示对象",
			dataIndex : 'objectName',
			width : 100,
			menuDisabled : true
		}, {
			header : "对象标识集合",
			dataIndex : 'ids',
			menuDisabled : true
		}],
		sm : new Ext.grid.RowSelectionModel({
			singleSelect : true
		}),
		bbar : new Ext.PagingToolbar({
            pageSize : pageSize,
            store:store,
            displayInfo:true,
            displayMsg:"显示第{0}条记录到第{1}条记录，一共{2}条",
            emptyMsg:"没有记录",
            beforePageText:"第",
            afterPageText:"页,共{0}页"
        }),
		tbar : tb,
		viewConfig : {}
	});
	var viewport = new Ext.Viewport({
		layout : 'fit',
		border : false,
		items : [grid]
	});
    store.load({ params : { start : 0, limit : pageSize } });
});

function delete_rows(){
    var grid = Ext.getCmp('grid.info');
    var store = grid.getStore();
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
        var idArray = new Array();
        var record = grid.getSelectionModel().getSelections();
        for(var i = 0; i < record.length; i++){
            idArray[i] = record[i].get('id');
        }
        var data = Ext.data.Record.create(["id","name"]);
        var parentStore = new Ext.data.Store({
            url : '../../parentEpConf.do?m=findAll',
            reader:new Ext.data.JsonReader({totalProperty:"total",root:"rows",id:"id"},data)
        });
        parentStore.load();
        var formPanel = new Ext.form.FormPanel({
             frame:true,
             labelAlign:'right',
             autoScroll:true,
             labelWidth:100,
             defaults:{
                 width:200,
                 allowBlank:false,
                 blankText:'该项不能为空！'
             },
             items:[{
                 fieldLabel:"平台名称",
                 xtype:'combo',
                 hiddenName:'platformId',
                 mode:'local',
                 emptyText :'--请选择--',
                 editable : false,
                 typeAhead:true,
                 forceSelection: true,
                 triggerAction:'all',
                 displayField:"name",valueField:"id",
                 store:parentStore
             }]
        });
        var win = new Ext.Window({
            title:"按业务导出",
            width:400,
            height:100,
            layout:'fit',
            modal:true,
            items: [formPanel],
            bbar:[
                new Ext.Toolbar.Fill(),
                new Ext.Button ({
                    id:'delete.info',
                    text : '确定',
                    allowDepress : false,
                    handler : function() {
                        if(formPanel.form.isValid()){
                            Ext.MessageBox.show({
                                title:'信息',
                                msg:'<font color="green">确定要删除所选记录？</font>',
                                animEl:'delete.info',
                                width:250,
                                buttons:Ext.Msg.YESNO,
                                buttons:{'ok':'确定','no':'取消'},
                                icon:Ext.MessageBox.WARNING,
                                closable:false,
                                fn:function(e){
                                    if(e == 'ok'){
                                        formPanel.getForm().submit({
                                            url : '../../sysQueryService.do',
                                            params :{idArray : idArray,m : 'delete'},
                                            method:'POST',
                                            waitTitle :'系统提示',
                                            waitMsg:'正在删除,请稍后...',
                                            success:function(form,action){
                                                Ext.MessageBox.show({
                                                    title:'信息',
                                                    width:300,
                                                    msg:action.result.msg,
                                                    animEl:'delete.info',
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
                                    }
                                }
                            });
                        }
                    }
                }),
                new Ext.Button ({
                    allowDepress : false,
                    text : '关闭',
                    handler : function() {win.close();}
                })
            ]
        }).show();
    }
}