/*
 * ! Ext JS Library 3.2.0 Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com http://www.extjs.com/license
 */
url = '../log.do?m=businessLog';
title = '业务日志审计';
Ext.onReady(function() {

	Ext.QuickTips.init();

	var tb = new Ext.Toolbar({
		width : 720,
		height : 30,
		items : ['起始日期：', {
			id : 'startDate',
			xtype : 'datefield',
			name : 'startDate',
			emptyText : '点击输入日期',
			format : 'Y-m-d'
		}, {
			xtype : 'tbspacer',
			width : 10
		}, '结束日期：', // same as {xtype: 'tbtext', text: 'text1'} to create
				// Ext.Toolbar.TextItem
				{
					id : 'endDate',
					xtype : 'datefield',
					name : 'endDate',
					emptyText : '点击输入日期',
					format : 'Y-m-d'
				}, {
					xtype : 'tbspacer',
					width : 10
				}, '级别：', new Ext.form.ComboBox({
					id : "logLevel",
					store : new Ext.data.ArrayStore({
						autoDestroy : true,
						fields : ['value', 'text'],
						data : [['', 'ALL'], ['ERROR', 'ERROR'],
								['WARN', 'WARN'], ['INFO', 'INFO'],
								['DEBUG', 'DEBUG']]
					}),
					valueField : 'value',
					displayField : 'text',
					mode : 'local',
					forceSelection : true,
					triggerAction : 'all',
					value : '',
					selectOnFocus : true,
					width : 70
				}), {
					xtype : 'tbspacer',
					width : 10
				}, '业务名：', new Ext.form.ComboBox({
					id : "businessName",
					store : new Ext.data.JsonStore({
						url : '../bizConf.do?m=bizCombox',
						fields : ['businessName']
					}),
					valueField : 'businessName',
					displayField : 'businessName',
					mode : 'remote',
					value : '',
					emptyText : 'All',
					forceSelection : true,
					triggerAction : 'all',
					selectOnFocus : true,
					width : 80
				}), {
					xtype : 'tbspacer',
					width : 10
			}, {
					// xtype: 'button',
                text : '查询',
                listeners : {
                    "click" : function() {
                        var logLevel = Ext.fly("logLevel").dom.value == 'ALL'
                            ? ''
                            : Ext.fly("logLevel").dom.value;
                        var startDate = Ext.fly("startDate").dom.value == '点击输入日期'
                            ? ''
                            : Ext.fly("startDate").dom.value;
                        var endDate = Ext.fly("endDate").dom.value == '点击输入日期'
                            ? ''
                            : Ext.fly("endDate").dom.value;
                        var businessName = Ext.getCmp('businessName')
                            .getValue();
                        store.setBaseParam("startDate", startDate);
                        store.setBaseParam("endDate", endDate);
                        store.setBaseParam("logLevel", logLevel);
                        store.setBaseParam("businessName", businessName);
                        store.load({
                            params : {
                                start : 0,
                                limit : PAGESIZE
                            }
                        });
					}
			    }
            },{
                xtype : 'tbspacer',
                width : 10
            }, {
                id:'clear.info',
                text : '清空',
                listeners : {
                    'click' : function() {
                        Ext.MessageBox.show({
                            title:'信息',
                            width:250,
                            msg:'确定要清空业务日志记录?',
                            animEl:'clear.info',
                            buttons:Ext.MessageBox.YESNO,
                            buttons:{'ok':'确定','no':'取消'},
                            icon:Ext.MessageBox.QUESTION,
                            closable:false,
                            fn:function(e){
                                if(e=='ok'){
                                    Ext.Ajax.request({
                                        url : '../log.do?m=deleteBusinessLog',
                                        success : function(response, options) {
                                            var respText = Ext.util.JSON.decode(response.responseText);
                                            var msg = respText.msg;

                                            Ext.MessageBox.show({
                                                title:'信息',
                                                width:300,
                                                msg: msg,
                                                animEl:'clear.info',
                                                buttons:{'ok':'确定'},
                                                icon:Ext.MessageBox.INFO,
                                                closable:false,
                                                fn:function(e){
                                                    if(e=='ok'&&msg=='清空成功'){
                                                        grid.render();
                                                        store.reload();
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
			}]
	});

	// create the Data Store
	var store = new Ext.data.JsonStore({
		url : url,
		root : 'results',
		totalProperty : 'amount',
		idProperty : 'id',
		// remoteSort: true,

		fields : [{
			name : 'id',
			mapping : 'id'
		}, {
			name : 'logTime',
			mapping : 'logTime'
		}, {
			name : 'level',
			mapping : 'level'
		}, {
			name : 'businessName',
			mapping : 'businessName'
		}, {
			name : 'platformName',
			mapping : 'platformName'
		}, {
			name : 'auditInfo',
			mapping : 'auditInfo'
		}, {
			name : 'sourceIp',
			mapping : 'sourceIp'
		}, {
			name : 'sourcePort',
			mapping : 'sourcePort'
		}, {
			name : 'destIp',
			mapping : 'destIp'
		}, {
			name : 'destPort',
			mapping : 'destPort'
		}, {
			name : 'userName',
			mapping : 'userName'
		}, {
			name : 'operation',
			mapping : 'operation'
		}]

	});
	// store.setDefaultSort('lastpost', 'desc');

	var grid = new Ext.grid.GridPanel({
		title : title,
		store : store,
		loadMask : true,
		trackMouseOver : true,// 鼠标悬浮高亮显示
		// autoExpandColumn: 'auditInfo',// 自动扩展宽度适应空白部分
		columnLines : true,// 列分隔符

		// grid columns
		columns : [{
			id : 'topic', // id assigned so we can apply custom css (e.g.
			// .x-grid-col-topic b { color:#333 })
			header : "行为发生时间",
			dataIndex : 'logTime',
			width : 150,
			menuDisabled : true,
			sortable : true
		}, {
			header : "消息级别",
			dataIndex : 'level',
			width : 100,
			menuDisabled : true
		}, {
			header : "业务名",
			dataIndex : 'businessName',
			width : 100,
			menuDisabled : true
		}, {
			header : "平台名",
			dataIndex : 'platformName',
			width : 100,
			menuDisabled : true
		},{
			header : "源端IP",
			dataIndex : 'sourceIp',
			width : 100,
			menuDisabled : true
		}, {
			header : "源端端口",
			dataIndex : 'sourcePort',
			width : 100,
			menuDisabled : true
		}, {
			header : "目标端IP",
			dataIndex : 'destIp',
			width : 100,
			menuDisabled : true
		}, {
			header : "目标端端口",
			dataIndex : 'destPort',
			width : 100,
			menuDisabled : true
		}, {
			header : "用户名",
			dataIndex : 'userName',
			width : 100,
			menuDisabled : true
		}, {
			header : "操作行为",
			dataIndex : 'operation',
			width : 100,
			menuDisabled : true
		},  {
			header : "审计内容",
			dataIndex : 'auditInfo',
			width : 300,
			menuDisabled : true
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
