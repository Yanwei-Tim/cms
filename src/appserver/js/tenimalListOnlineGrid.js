/*
 * ! Ext JS Library 3.2.0 Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com http://www.extjs.com/license
 */
url = '../tenimalListAction.do?m=indexOnline';
var cardurl = '../tenimalListAction.do?m=showCardType';
//title = '在线用户列表';
var grid ;
Ext.onReady(function() {
	Ext.QuickTips.init();
		var radioGroup=new Ext.form.RadioGroup({
					xtype : 'radiogroup',
					vertical : false,
					id: "RadioPoliceId",
					width : 150,
					height:25,
					items : [{
						boxLabel: "警员编号",
						inputValue:'policeno',
						name:'option',
						checked:true
					}, {
						boxLabel: "警员名",
						inputValue:'policename',
						name:'option'
					}],
					name:'option'
				});
		var cardTypeSelect = new Ext.form.ComboBox({
			id : "cardType",
			store : new Ext.data.JsonStore({
				url : cardurl,
				root : 'list',
				idProperty : 'id',
				fields : [ 'code', 'name' ]
			}),
			valueField : 'code',
			displayField : 'name',
			hiddenName : 'cardTyp',
			mode : 'remote',
			forceSelection : true,
			triggerAction : 'all',
			selectOnFocus : true,
			width : 100
		});
		var tb = new Ext.Toolbar({
			width : 720,
			height : 30,
			items : [ 
			    {xtype : 'tbspacer',width : 20},
			    '卡类型：',
			    cardTypeSelect ,
			    '-', 
			    radioGroup, 
			    { 
			    	xtype:'textfield',
			    	id : 'police',
			    	name : 'police'
			    }, 
			    "-" , 
			    {
			    	text : '查询',
			    	listeners : {
			    		"click" : function() {
			    			var cardType = Ext.getCmp("cardType").value;
			    			
			    			var RadioPolice = radioGroup.getValue().inputValue;
			    			var police = Ext.fly("police").dom.value;
			    			store.setBaseParam("cardType", cardType);
			    			store.setBaseParam("RadioPolice", RadioPolice);
			    			store.setBaseParam("police", police);
			    			store.load({
			    				params : {start : 0,limit : PAGESIZE }
			    			});
			    		}
			    	}
			    }
			 ]
		});

	// create the Data Store
	var store = new Ext.data.JsonStore({
		url : url,
		root : 'results',
		totalProperty : 'amount',
		idProperty : 'idno',
		// remoteSort: true,

		fields : [{
			name : 'idno',
			mapping : 'idno'
		}, {
			name : 'cardmodel',
			mapping : 'cardmodel'
		}, {
			name : 'cardtype',
			mapping : 'cardtype'
		}, {
			name : 'cardver',
			mapping : 'cardver'
		}, {
			name : 'region',
			mapping : 'region'
		}, {
			name : 'depart',
			mapping : 'depart'
		}, {
			name : 'policeno',
			mapping : 'policeno'
		}, {
			name : 'policename',
			mapping : 'policename'
		}, {
			name : 'createdate',
			mapping : 'createdate'
		}, {
			name : 'ifblock',
			mapping : 'ifblock'
		},{
			name : 'ip',
			mapping : 'ip'
		},{
			name : 'logindate',
			mapping : 'logindate'
		},{
			name : 'onlinetime',
			mapping : 'onlinetime'
		},{
			name : 'ifcancel',
			mapping : 'ifcancel'
		}]
	});
	store.load({
		params : {start : 0,limit : PAGESIZE }
	});
	var rowNumber = new Ext.grid.RowNumberer();         //自动 编号
	// store.setDefaultSort('lastpost', 'desc');
	 grid = new Ext.grid.GridPanel({
		 id : 'tenimalListOnlineGrid.info',
//		title : title,
		store : store,
		loadMask : true,
		trackMouseOver : true,// 鼠标悬浮高亮显示
//		autoExpandColumn: 'auditInfo',// 自动扩展宽度适应空白部分
		columnLines : true,// 列分隔符

		// grid columns
		stripeRows:true,
		columns : [
			rowNumber,{
				id : 'topic', // id assigned so we can apply custom css (e.g.
								// .x-grid-col-topic b { color:#333 })
				header : "卡编号",
				dataIndex : 'cardmodel',
				width : 100,
				menuDisabled : true,
				sortable : true
			}, {
				header : "卡类型",
				dataIndex : 'cardtype',
				width : 100,
				menuDisabled : true,
				renderer:showURL_cardType
			} ,{
				header : "地区",
				dataIndex : 'region',
				width : 100,
				menuDisabled : true
			}, {
				header : "部门",
				dataIndex : 'depart',
				width : 100,
				menuDisabled : true
			}, {
				header : "警员编号",
				dataIndex : 'policeno',
				width : 100,
				menuDisabled : true
			}, {
				header : "警员姓名",
				dataIndex : 'policename',
				width : 100,
				menuDisabled : true
			}, {
				header : "身份证号",
				dataIndex : 'idno',
				width : 100,
				menuDisabled : true
			},{
				header : "登录IP",
				dataIndex : 'ip',
				width : 100,
				menuDisabled : true
			},{
				header : "登录时间",
				dataIndex : 'logindate',
				width : 100,
				menuDisabled : true
			},{
				header : "在线时长",
				dataIndex : 'onlinetime',
				width : 100,
				menuDisabled : true
			},{
				header : "阻断状态",
				dataIndex : 'ifblock',
				width : 100,
				menuDisabled : true,
				renderer:showURL_ifblock
			},{
				header : '操作',
				dataIndex : '0',
				align : 'center',
				sortable : false,
				menuDisabled : true,
				width : 220,
				renderer : function(value, metaData, record, rowIndex, colIndex, store) {
					var block =null; 
					if(record.get("ifblock")==false){
						block="<a onclick='javascript:showBlock("
							+ value
							+ ");' style='cursor:hand;cursor:pointer;color:blue' >阻断</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					}else if(record.get("ifblock")==true){
						block="<a onclick='javascript:releaseBlock("
							+ value
							+ ");' style='cursor:hand;cursor:pointer;color:blue' >恢复接入</a>&nbsp;&nbsp;&nbsp;&nbsp;"
							
					}
					var cert = null;
					if(record.get("ifcancel")==false){
						cert = "<a onclick='javascript:revokeCert("
							+ value
							+ ");' style='cursor:hand;cursor:pointer;color:blue' >吊销证件</a>&nbsp;&nbsp;&nbsp;&nbsp;";
					}else if(record.get("ifcancel")==true){
						cert = "<font color='gray'>证件无效</font>&nbsp;&nbsp;&nbsp;&nbsp;";
					}
					var photo = "<a onclick='javascript:getPhoto("+value+");'style='cursor:hand;cursor:pointer;color:blue'>截屏</a>&nbsp;&nbsp;&nbsp;&nbsp;";
					var str = "<a onclick='javascript:showDetail("
						+ value
						+ ");' style='cursor:hand;cursor:pointer;color:blue' >详细</a>&nbsp;&nbsp;&nbsp;&nbsp;"
						+ photo												
						+ cert
						+ block;
				return str;
			}
		}],
		sm : new Ext.grid.RowSelectionModel({
			singleSelect : true
		}),
		// paging bar on the bottom
		bbar : new Ext.ux.PagingToolbar({
			store : store
		}),
		tbar : tb,
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

function showURL_ifblock(value){
	if(value==true){
		return '阻断';
	}else if(value==false){
		return '无阻断';
	}
	
}

function showURL_cardType(value){
	if(value=='001'){
		return 'USBKEY';
	}else if(value=='002'){
		return 'TFCard';
	}else if(value=='003'){
		return '其它类型';
	}
}

/**记录详细信息**/
function showDetail(id){
	var grid = Ext.getCmp('tenimalListOnlineGrid.info');
	var selModel = grid.getSelectionModel();
    var formPanel;
    if(selModel.hasSelection()){
        var selections = selModel.getSelections();
        Ext.each(selections,function(item){
            formPanel = new Ext.form.FormPanel({
                defaultType : 'displayfield',
                labelWidth:150,
                autoScroll:true,
                frame:true,
                border:false,
                labelAlign:'right',
				items : [
				    {fieldLabel : "卡编号",value: item.data.cardmodel},
				    {fieldLabel : "卡类型",value: showURL_cardType(item.data.cardtype)},
				    {fieldLabel : "地区",value: item.data.region},
				    {fieldLabel : "部门",value: item.data.depart},
				    {fieldLabel : "警员编号",value: item.data.policeno},
				    {fieldLabel : "警员姓名",value: item.data.policename},
				    {fieldLabel : "身份证号",value: item.data.idno},
				    {fieldLabel : "制证日期",value: item.data.createdate},
				    {fieldLabel : "证书是否有效",value: item.data.ifcancel?'无效':'有效'},
				    {fieldLabel : "登录IP",value: item.data.ip},
				    {fieldLabel : "登录时间",value: item.data.logindate},
				    {fieldLabel : "在线时长",value: item.data.onlinetime},
				    {fieldLabel : "阻断状态",value: showURL_ifblock(item.data.ifblock)}
				]
            });
        });
    }
	var win = new Ext.Window({
		title:"详细信息",
        width:450,
        height:430,
        modal:true,
        layout:'fit',
    	items:[formPanel]


    }).show(); 
}

	/**阻断函数**/
function showBlock(value){
	var grid = Ext.getCmp('tenimalListOnlineGrid.info');
	var selModel = grid.getSelectionModel();
    var policeno;
    var policename;
    var cardtype;
	var cardmodel;
	var idno;
	var ip;
    if(selModel.hasSelection()){
        var selections = selModel.getSelections();
        Ext.each(selections,function(item){
           policeno = item.data.policeno;
           policename = item.data.policename;
           cardtype = item.data.cardtype;
           cardmodel = item.data.cardmodel;
           idno = item.data.idno;
           ip = item.data.ip;
        });
    }
	var frm = new Ext.FormPanel({
		id : 'form_add',
		labelWidth : 70,
		labelAlign : 'right',		
		border : false,
		baseCls : 'x-plain',
		bodyStyle : 'padding:5px 5px 0',
		anchor : '100%',
		defaults : {
			width : 120,
			msgTarget : 'side' // 验证信息显示右边
		},
		defaultType : 'textfield',
		items : [{
			xtype : 'compositefield',
			hideLabel : true,
			width : 120,
			items : [{
				name : 'block',
				xtype : 'radio',
				inputValue : 'value1',
				boxLabel : '长久阻断'
			}]
		}, {
			xtype : 'compositefield',
			hideLabel : true,
			items : [{
				name : 'block',
				xtype : 'radio',
				inputValue : 'value2',
				boxLabel : '阻断'
			}, {
				xtype : 'textfield',
				name : 'hour',
				width : 40
			}, {
				xtype : 'displayfield',
				value : '小时'
			}]
		}]			
	});
    var detailWin2 = new Ext.Window({
		id : 'detailWin2',
		title : '请填写阻断信息',
		width : 200,
		height : 130,
		resizable : true,
		modal : true,
		closeAction : 'hide',
		bbar : ['->',{
			id : 'add',
			text : '确定',
			handler : function(btn) {
				if (frm.form.isValid()) {
					frm.form.submit({
						url : '../tenimalListAction.do?',
						method:'POST',
						params:{m:'showBlockOnline',policeno:policeno,policename:policename,cardtype:cardtype,cardmodel:cardmodel,idno:idno,ip:ip},
						waitTitle : '请稍候',
						waitMsg : '正在提交表单数据,请稍候...',
						success : function(form,action) {
							var msg = action.result.msg;
							var data = action.result.data;
							var store = grid.getStore();
							grid.render();
							store.reload();
							Ext.Msg.alert("温馨提示", msg+","+data);							
							detailWin2.close();
						},
						failure : function() {
							Ext.Msg.show({
								title : '错误提示',
								msg : '添加失败，请检查后输入!',
								buttons : Ext.Msg.OK,
								icon : Ext.Msg.ERROR
							});
							btn.enable();
						}
					});
				}
			}
		}, {
			text : '取消',
			handler : function() {
				detailWin2.close();
			}
		}],
		items : [frm]
	}).show();
}
	/**恢复接入函数**/
function releaseBlock(id){
	var grid = Ext.getCmp('tenimalListOnlineGrid.info');
	var selModel = grid.getSelectionModel();
    var policeno;
    var policename;
    var cardtype;
	var cardmodel;
	var idno;
	var ip;
    if(selModel.hasSelection()){
        var selections = selModel.getSelections();
        Ext.each(selections,function(item){
           policeno = item.data.policeno;
           policename = item.data.policename;
           cardtype = item.data.cardtype;
           cardmodel = item.data.cardmodel;
           idno = item.data.idno;
           ip = item.data.ip;
        });
    }
	Ext.Msg.show({
		title : '确认',
		msg : '恢复接入？',
		buttons : Ext.Msg.YESNO,
		fn : function(btnId) {
			if(btnId=="yes"){
				Ext.Ajax.request({
					url : '../tenimalListAction.do?',
					 params:{id:id,m:'releaseBlockOnline',policeno:policeno,policename:policename,cardtype:cardtype,cardmodel:cardmodel,idno:idno,ip:ip},
					 method:'POST',
					success : function(r,o) {
						var respText = Ext.util.JSON.decode(r.responseText);
						var msg = respText.msg;
						var data = respText.data;
						var store = grid.getStore();
						grid.render();
						store.reload();
						Ext.Msg.alert('温馨提示', msg+','+data);
						
					},
					failure : function(form, action) {
						Ext.Msg.alert("提示信息", "解除失败!请联系管理员！");
						return;
					}
				});
			}else if(btnId=="no"){
			}
		},
	icon : Ext.Msg.QUESTION
	});
}
	/**吊销证件函数**/
function revokeCert(id){
	var grid = Ext.getCmp('tenimalListOnlineGrid.info');
	var selModel = grid.getSelectionModel();
    var policeno;
    var policename;
    var cardtype;
	var cardmodel;
	var idno;
	var ip;
    if(selModel.hasSelection()){
        var selections = selModel.getSelections();
        Ext.each(selections,function(item){
           policeno = item.data.policeno;
           policename = item.data.policename;
           cardtype = item.data.cardtype;
           cardmodel = item.data.cardmodel;
           idno = item.data.idno;
           ip = item.data.ip;
        });
    }
	Ext.Msg.show({
		title : '确认',
		msg : '吊销证书？',
		buttons : Ext.Msg.YESNO,
		fn : function(btnId) {
			if(btnId=="yes"){
				Ext.Ajax.request({
					url : '../tenimalListAction.do',
					 params:{id:id,m:'revokeCertOnline',policeno:policeno,policename:policename,cardtype:cardtype,cardmodel:cardmodel,idno:idno,ip:ip},
					 method:'POST',
					success : function(r,o) {
						var respText = Ext.util.JSON.decode(r.responseText);
						var msg = respText.msg;
						var data = respText.data;
						var store = grid.getStore();
						store.reload();
						
							Ext.Msg.alert('温馨提示', msg +','+ data);
						
					},
					failure : function(form, action) {
						Ext.Msg.alert("提示信息", "吊销失败!请联系管理员！");
						return;
					}
				});
			}else if(btnId=="no"){
			}
		},
		icon : Ext.Msg.QUESTION
	});
}
	/**截屏**/
function getPhoto(id){
	var grid = Ext.getCmp('tenimalListOnlineGrid.info');
	var selModel = grid.getSelectionModel();
    var policeno;
    var policename;
    var cardtype;
	var cardmodel;
	var idno;
	var ip;
	var status;
    if(selModel.hasSelection()){
        var selections = selModel.getSelections();
        Ext.each(selections,function(item){
           policeno = item.data.policeno;
           policename = item.data.policename;
           cardtype = item.data.cardtype;
           cardmodel = item.data.cardmodel;
           idno = item.data.idno;
           ip = item.data.ip;
           status = item.data.status;
        });
    }
	Ext.Msg.show({
		title : '确认',
		msg : '确定要截屏？',
		buttons : Ext.Msg.YESNO,
		fn : function(btnId) {
			if(btnId=="yes"){
				Ext.Ajax.request({
					url : '../tenimalListAction.do',
					params:{policeno:policeno,m:'getPhoto',status:status,policename:policename,cardtype:cardtype,cardmodel:cardmodel,idno:idno,ip:ip},
					method:'post',
					success : function(r,o) {
						var respText = Ext.util.JSON.decode(r.responseText);
						var msg = respText.msg;
						var data = respText.data;
						var screenName = respText.screenName;
						var screenwidth = respText.width;
						var screenHeight = respText.height;
						var store = grid.getStore();
						store.reload();
						if(data == '截屏失败'){
							Ext.Msg.alert('温馨提示', msg +','+ data);
						}else{
							
							var win = new Ext.Window({
						        title:"截屏信息",
						        width:screenwidth/2,
						        height:screenHeight/2,
						        modal:true,
						        maximizable:true,
						        layout:'fit',
						        items:[{
						        	border:false,
						        	autoScroll:true,
						        	html:'<img src="../upload/'+screenName+'">'
						        }],
						        bbar:['->',{
//						        	text:'保存',
//						        	handler:function(){
//						        		Ext.Ajax.request({
//						        			url:'../screenAction.do',
//						        			params:{m:'saveScreen',path:data},
//						        			method:'POST',
//						        			success:function(r,o) {
//												var respText = Ext.util.JSON.decode(r.responseText);
//												var msg = respText.msg;
//						        				Ext.MessageBox.show({
//				                                    title:'信息',
//				                                    msg:msg,
//				                                    animEl:'init.save.info',
//				                                    width:230,
//				                                    buttons:Ext.Msg.OK,
//				                                    buttons:{'ok':'确定'},
//				                                    icon:Ext.MessageBox.ERROR,
//				                                    closable:false
//				                                });
//						        			}
//						        		});						        		
//						        		win.close();
//						        	}
//						        },{
						        	text:'关闭',
						        	handler:function(){
						        		win.close();
						        	}
						        }]
						    }).show();
						}	
					},
					failure : function(form, action) {
						Ext.Msg.alert("提示信息", "截屏失败!请联系管理员！");
						return;
					}
				});
			}else if(btnId=="no"){
			}
		},
		icon : Ext.Msg.QUESTION
	});
}





















