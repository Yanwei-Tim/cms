/*
 * ! Ext JS Library 3.2.0 Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com http://www.extjs.com/license
 */
url = '../sysabnormal.do?m=index';
title = '本级系统违规情况查询';
Ext.onReady(function() {
	Ext.QuickTips.init();
	var tb = new Ext.Toolbar({
		width : 720,
		height : 30,
		items : ['违规事件发生时间晚于：', {
			id : 'startDate',
			xtype : 'datefield',
			name : 'startDate',
			emptyText : '点击输入日期',
			format : 'Y-m-d' ,
            editable:false
		}, {
			xtype : 'tbspacer',
			width : 10
		}, '早于：', // same as {xtype: 'tbtext', text: 'text1'} to create
					// Ext.Toolbar.TextItem
				{
					id : 'endDate',
					xtype : 'datefield',
					name : 'endDate',
					emptyText : '点击输入日期',
					format : 'Y-m-d',
                    editable:false
				}, {
					xtype : 'tbspacer',
					width : 10
				},"违规类型",{
					id : "eventCode.info",
					xtype:'combo',
					store : new Ext.data.JsonStore({
						url : '../sysabnormal.do?m=comboEvent',
						root : 'rows',
//						idProperty : 'id',
						fields : [ 'code', 'name' ]
					}),
					valueField : 'code',
					displayField : 'name',
					hiddenName : 'eventCode',
					mode : 'remote',
					forceSelection : true,
					triggerAction : 'all',
					selectOnFocus : true,
					width : 100
				},{
					xtype : 'tbspacer',
					width : 10
				},"违规对象",{
					id : "objectType.info",
					xtype:'combo',
					store : new Ext.data.JsonStore({
						url : '../sysabnormal.do?m=comboObject',
						root : 'rows',
//						idProperty : 'id',
						fields : [ 'code', 'name' ]
					}),
					valueField : 'code',
					displayField : 'name',
					hiddenName : 'objectType',
					mode : 'remote',
					forceSelection : true,
					triggerAction : 'all',
					selectOnFocus : true,
					width : 100
				},{
					xtype : 'tbspacer',
					width : 10
				},{
					// xtype: 'button',
					text : '查询',
					listeners : {
						"click" : function() {
							var startDate = Ext.fly("startDate").dom.value == '点击输入日期'
									? ''
									: Ext.fly("startDate").dom.value;
							var endDate = Ext.fly("endDate").dom.value == '点击输入日期'
									? ''
									: Ext.fly("endDate").dom.value;
							var objectType = Ext.getCmp('objectType.info').getValue();
							var eventCode = Ext.getCmp('eventCode.info').getValue();
							store.setBaseParam("startDate", startDate);
							store.setBaseParam("endDate", endDate);
							store.setBaseParam("eventCode", eventCode);
							store.setBaseParam("objectType", objectType);
							store.load({
								params : {
									start : 0,
									limit : PAGESIZE
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
			name : 'happenTime',
			mapping : 'happenTime'
		}, {
			name : 'treatTime',
			mapping : 'treatTime'
		}, {
			name : 'writeTime',
			mapping : 'writeTime'
		}, {
			name : 'idSystem',
			mapping : 'idSystem'
		}, {
			name : 'idAlertMatter',
			mapping : 'idAlertMatter'
		}, {
			name : 'abNormalTypeCode',
			mapping : 'abNormalTypeCode'
		}, {
			name : 'connectObjectCode',
			mapping : 'connectObjectCode'
		}, {
			name : 'exceptionDesc',
			mapping : 'exceptionDesc'
		}, {
			name : 'treadResult',
			mapping : 'treadResult'
		}]

	});
    store.setBaseParam("startDate", '');
    store.setBaseParam("endDate", '');
    store.setBaseParam("eventCode", '');
    store.setBaseParam("objectType", '');
    store.load({
        params : {
            start : 0,
            limit : PAGESIZE
        }
    });
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
			header : "发生时间",
			dataIndex : 'happenTime',
			width : 150,
			menuDisabled : true,
			sortable : true
		},{
			id : 'topic', 
			header : "处理时间",
			dataIndex : 'treatTime',
			width : 150,
			menuDisabled : true,
			sortable : true
		},{
			id : 'topic',
			header : "记录时间",
			dataIndex : 'writeTime',
			width : 150,
			menuDisabled : true,
			sortable : true
		}, {
			header : "系统标识",
			dataIndex : 'idSystem',
			width : 100,
			menuDisabled : true
		}, {
			header : "事件标识",
			dataIndex : 'idAlertMatter',
			width : 100,
			menuDisabled : true
		}, {
			header : "事件类型",
			dataIndex : 'abNormalTypeCode',
			width : 100,
			menuDisabled : true,
			renderer : showURL_abNormalTypeCode
		}, {
			header : "违规对象",
			dataIndex : 'connectObjectCode',
			width : 100,
			menuDisabled : true,
			renderer : showURL_connectObjectCode
		}, {
			header : "事件描述",
			dataIndex : 'exceptionDesc',
			width : 300,
			menuDisabled : true
		}, {
			header : "处理结果",
			dataIndex : 'treadResult',
			width : 100,
			menuDisabled : true,
			renderer : showURL_treadResult
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

function showURL_treadResult(value){
	if(value =='001'){
		return '已处理';
	}else if(value=='002'){
		return '待处理';
	}else if(value=='003'){
		return '未处理';
	}else if(value=='004'){
		return '处理中';
	}else if(value=='005'){
		return '其它结果';
	}
}
