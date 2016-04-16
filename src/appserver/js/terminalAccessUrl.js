/*
 * ! Ext JS Library 3.2.0 Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com http://www.extjs.com/license
 */
url = '../terminalAccessUrl.do?m=index';
title = '终端用户访问URL';
var grid ;
Ext.onReady(function() {
	Ext.QuickTips.init();
		
	// create the Data Store
	var store = new Ext.data.JsonStore({
		url : url,
		root : 'rows',
		totalProperty : 'total',
		idProperty : 'id',
		// remoteSort: true,

		fields : [{
			name : 'id',
			mapping : 'id'
		}, {
			name : 'idTerminal',
			mapping : 'idTerminal'
		}, {
			name : 'busName',
			mapping : 'busName'
		}, {
			name : 'accessUrl',
			mapping : 'accessUrl'
		}, {
			name : 'resourceRegx',
			mapping : 'resourceRegx'
		}, {
			name : 'action',
			mapping : 'action'
		}]

	});
	store.load({
		params : {
			start : 0,
			limit : PAGESIZE
		}
	});
	// store.setDefaultSort('lastpost', 'desc');
	var boxM = new Ext.grid.CheckboxSelectionModel();   //复选框
	var rowNumber = new Ext.grid.RowNumberer();         //自动 编号
	 grid = new Ext.grid.GridPanel({
//		title : title,
		id:'grid.info',
		store : store,
		loadMask : true,
		trackMouseOver : true,// 鼠标悬浮高亮显示
		// autoExpandColumn: 'auditInfo',// 自动扩展宽度适应空白部分
		columnLines : true,// 列分隔符

		// grid columns
		stripeRows:true,
		columns : [
		    boxM,
			rowNumber,{
				id : 'topic', // id assigned so we can apply custom css (e.g.
				// .x-grid-col-topic b { color:#333 })
				header : "名单ID",
				dataIndex : 'idTerminal',
				width : 100,
				menuDisabled : true,
				sortable : true
			}, {
				header : "业务名称",
				dataIndex : 'busName',
				width : 100,
				menuDisabled : true
			} ,{
				header : "访问URL",
				dataIndex : 'accessUrl',
				width : 200,
				menuDisabled : true
			}, {
				header : "资源通配符",
				dataIndex : 'resourceRegx',
				width : 200,
				menuDisabled : true
			}, {
				header : "动作",
				dataIndex : 'action',
				width : 100,
				menuDisabled : true,
				renderer : action_showUrl 
			},{
				header : '操作',
				dataIndex : 'id',
				align : 'center',
				sortable : false,
				menuDisabled : true,
				width : 200,
				renderer : operate_showUrl
			}
		],
			
		sm : new Ext.grid.RowSelectionModel({
			singleSelect : true
		}),

		// paging bar on the bottom
		bbar : new Ext.ux.PagingToolbar({
			store : store
		}),
		tbar : [{
			pressed : false,
			text : '新增',
			id : 'add_btn',
			iconCls : 'add',
			handler : function() {
				showAddWindow(grid,store);
			}
		}, {
			pressed : false,
			text : '删除',
			id : 'delete_btn',
			iconCls : 'delete',
			handler : function() {
				var selectedRows = grid.getSelectionModel().getSelections();
				if (selectedRows.length == 0) {
					Ext.Msg.alert('警告', '请选中你要删除的行！');
				} else {
					Ext.Msg.confirm('警告', '确定要删除这些记录吗？', function(btn) {
						if (btn == 'yes') {
							var ids = "";
							for (var i = 0; i < selectedRows.length; i++) {
								ids += selectedRows[i].data.id + ",";
							}
							ids = ids.substring(0, ids.length - 1);
							Ext.Ajax.request({
								url : '../terminalAccessUrl.do?m=delete',
								params : {
									ids : ids
								},
								success : function(response, options) {
									var respText = Ext.util.JSON.decode(response.responseText);
							        var msg = respText.msg;
									Ext.Msg.alert('提示',msg );
									grid.render();
									store.reload();
								}
							});
						} else {
							return false;
						}
					});
				}

			}
		}],
		viewConfig : {}
	});
	// render it
	grid.render('topic-grid');

	// trigger the data store load
	// store.load({params:{start:0, limit:PAGESIZE}});
	var viewport = new Ext.Viewport({
		layout : 'fit',
		border : false,
		items : [grid]
	});
});

function action_showUrl(value){
    return value=='0'?'允许访问':'禁止访问';
}
function action_Url(value){
    return value=='0'?'允许访问':'禁止访问';
}

function operate_showUrl(value){
	return "<a href='javascript:;' onclick='showDetail();'>详细</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:;' onclick='update_win();'>修改</a>";
}


/**记录详细信息**/
function showDetail(){
	var grid = Ext.getCmp('grid.info');
	var selModel = grid.getSelectionModel();
    var formPanel;
    if(selModel.hasSelection()){
        var selections = selModel.getSelections();
        Ext.each(selections,function(item){
            formPanel = new Ext.form.FormPanel({
                defaultType : 'displayfield',
                labelWidth:100,
                autoScroll:true,
                frame:true,
                border:false,
                labelAlign:'right',
				items : [
				    {fieldLabel : "ID",value: item.data.id},
				    {fieldLabel : "名单ID",value: item.data.idTerminal},
				    {fieldLabel : "业务名称",value: item.data.busName},
				    {fieldLabel : "访问URL",value: item.data.accessUrl},
				    {fieldLabel : "资源通配符",value: item.data.resourceRegx},
				    {fieldLabel : "动作",value: action_showUrl(item.data.action)}
				]
            });
        });
    }
	var win = new Ext.Window({
		title:"详细信息",
        width:500,
        height:230,
        modal:true,
        layout:'fit',
    	items:[formPanel],
    	bbar:[
              new Ext.Toolbar.Fill(),
              new Ext.Button ({
                  allowDepress : false,
                  handler : function() {win.close();},
                  text : '返回'
              }),
              new Ext.Button ({
                  allowDepress : false,
                  handler : function() {win.close();},
                  text : '关闭'
              })
          ]
    }).show(); 
}

function showAddWindow(grid,store){
	var formPanel = new Ext.form.FormPanel({
        defaultType : 'textfield',
        labelWidth:100,
        autoScroll:true,
        frame:true,
        border:false,
        labelAlign:'right',
        defaults:{
            width:300,
            allowBlank:false,
            blankText:'该项不能为空！'
        },
		items : [
		    {
		    	fieldLabel : "名单ID",
                name:'idTerminal',
                id:'idTerminal',
		    	regex:/^([1-9][0-9]{1,12})$/,
	            regexText:'不规范(例):121212,请输入0~12为数字!',
	            emptyText:'(例):121212',
                listeners : {
                blur:function() {
                    Ext.Ajax.request({
                        url : '../terminalAccessUrl.do?m=byName&idTerminal='+this.getValue(),
                        success : function(reponse, options) {
                            var respText = Ext.util.JSON.decode(reponse.responseText);
                            var msg = respText.msg;
                            Ext.Msg.alert('温馨提示', msg);
                            Ext.getCmp("idTerminal").setValue("");

                        }
                    });
}

}
		    },
		    {fieldLabel : "业务名称",name:'busName'},
		    {fieldLabel : "访问URL",name:'accessUrl'},
		    {fieldLabel : "资源通配符",name:'resourceRegx'},
		    {	
		    	fieldLabel : "动作",
            	xtype : 'combo',
            	hiddenName : 'action',
				valueField : 'value',
				displayField : 'key',
				store : new Ext.data.SimpleStore({
					fields : ['key', 'value'],
					data : [['允许访问', '0'], ['禁止访问', '1']]
					}),
				selectOnFocus : true,
				editable : true,
				triggerAction : 'all',
				loadingText : '加载中...',
				mode : 'local',
				emptyText : '请选择',
            	allowBlank:false,
            	blankText:'该项不能为空！'
            }
		]
    });
	var win = new Ext.Window({
		title:"新增信息",
        width:500,
        height:210,
        modal:true,
        layout:'fit',
    	items:[formPanel],
    	bbar:[
              new Ext.Toolbar.Fill(),
              new Ext.Button ({
                  allowDepress : false,
                  id:'insert.win.info',
                  handler : function() {
                	  if (formPanel.form.isValid()) {
                      	formPanel.getForm().submit({
  			            	url :'../terminalAccessUrl.do?m=insert',
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
                  },
                  text : '保存'
              }),
              new Ext.Button ({
                  allowDepress : false,
                  handler : function() {win.close();},
                  text : '关闭'
              })
          ]
    }).show(); 
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
                labelWidth:100,
                defaults:{
                    width:300,
                    allowBlank:false,
                    blankText:'该项不能为空！'
                },
                items:[
                    {fieldLabel : "ID",xtype:'displayfield',value: item.data.id},
                    {xtype:'hidden',name:'id',value: item.data.id},
                    {
                    	fieldLabel : "名单ID",name:'idTerminal',value:item.data.idTerminal,
        		    	regex:/^([1-9][0-9]{1,12})$/,
        	            regexText:'不规范(例):121212,请输入0~12为数字!',
        	            emptyText:'(例):121212'
        	        },
           		    {fieldLabel : "业务名称",name:'busName',value:item.data.busName},
        		    {fieldLabel : "访问URL",name:'accessUrl',value:item.data.accessUrl},
        		    {fieldLabel : "资源通配符",name:'resourceRegx',value:item.data.resourceRegx},
        		    {	
        		    	fieldLabel : "动作",
                    	hiddenName : 'action',
                    	xtype : 'combo',
                    	value : item.data.action,
        				valueField : 'value',
        				displayField : 'key',
        				store : new Ext.data.SimpleStore({
        					fields : ['key', 'value'],
        					data : [['允许访问', '0'], ['禁止访问', '1']]
        					}),
        				selectOnFocus : true,
        				editable : true,
        				triggerAction : 'all',
        				loadingText : '加载中...',
        				mode : 'local',
        				emptyText : '请选择',
                    	allowBlank:false,
                    	blankText:'该项不能为空！'
                    }
                ]
            });
        });
    }
    var win = new Ext.Window({
        title:"修改信息",
        width:500,
		layout:'fit',
        height:230,
        modal:true,
        items:formPanel,
        bbar:['->', {
        	id:'update.win.info',
        	text:'修改',
        	handler:function(){
        		if (formPanel.form.isValid()) {        			
        			formPanel.getForm().submit({
        				url :'../terminalAccessUrl.do?m=update',
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


















