url = '../sysTerminalStatusRunReport.do?m=index';
title = '终端用户运行日统计表';
Ext.onReady(function() {
	Ext.QuickTips.init();
	var comboIdTerminal = new Ext.data.JsonStore({
		autoLoad : true,
		url : '../sysTerminalStatusRunReport.do?m=comboIdTerminal',
		fields : ['idTerminal']
	});
	var tb = new Ext.Toolbar({
		width : 720,
		height : 30,
		items : ['日期：', {
			id : 'auditDate.info',
			xtype : 'datefield',
			name : 'auditDate',
			emptyText : '点击输入日期',
			format : 'Y-m-d',
			allowBlank : false,
			value : new Date()
		}, {
			xtype : 'tbspacer',
			width : 10
		}, '终端ID：', new Ext.form.ComboBox({
			id : "idTerminal.info",
			store : comboIdTerminal,
			valueField : 'idTerminal',
			displayField : 'idTerminal',
			mode : 'remote',
			editable : true,
			allowBlank : false,
			emptyText : '请选择',
			forceSelection : true,
			triggerAction : 'all',
			selectOnFocus : true,
			listWidth  : 100,
			width : 100
		}), {
			xtype : 'tbspacer',
			width : 10
		}, {
			text : '统计',
			listeners : {
				"click" : function() {
					var idTerminal = Ext.getCmp("idTerminal.info").getValue();
					if(idTerminal == 0){
						store.setBaseParam("idTerminal", 0);
					}else if (idTerminal == '') {
						Ext.Msg.alert("提示", "请选择终端ID！");
						return;
					}
					var auditDate = Ext.fly("auditDate.info").dom.value;
					if (auditDate == '点击输入日期' || auditDate == '') {
						Ext.Msg.alert("提示", "请输入日期！");
						return;
					}
					store.setBaseParam("auditDate", auditDate);
					store.setBaseParam("idTerminal", idTerminal);
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
		fields : [{
				name : 'id',
				mapping : 'id'
			}, {
				name : 'cardType',
				mapping : 'cardType'
			}, {
				name : 'userId',
				mapping : 'userId'
			}, {
				name : 'userDepart',
				mapping : 'userDepart'
			}, {
				name : 'flux',
				mapping : 'flux'
			}, {
				name : 'busName',
				mapping : 'busName'
			}, {
				name : 'count',
				mapping : 'count'
			}, {
				name : 'auditDate',
				mapping : 'auditDate'
			}, {
				name : 'userZone',
				mapping : 'userZone'
			}, {
				name : 'userZone',
				mapping : 'userZone'
			}, {
				name : 'policeNumber',
				mapping : 'policeNumber'
			}, {
				name : 'idTerminal',
				mapping : 'idTerminal'
			}, {
				name : 'accessUrl',
				mapping : 'accessUrl'
			}
		]

	});
	var rowNumber = new Ext.grid.RowNumberer();         //自动 编号
	// store.setDefaultSort('lastpost', 'desc');
	var grid = new Ext.grid.GridPanel({
		width : "100%",
		height : "555",
		renderTo : 'topic-grid',
		// autoHeight:true,
		title : title,
		store : store,
		loadMask : true,
		trackMouseOver : true,// 鼠标悬浮高亮显示
		// autoExpandColumn: 'auditInfo',// 自动扩展宽度适应空白部分
		columnLines : true,// 列分隔符

		// grid columns
		columns : [rowNumber,{
			header : '终端ID',
			dataIndex : 'idTerminal',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 50
		}, {
			header : '身份证号',
			dataIndex : 'userId',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 150
		}, {
			header : '警号',
			dataIndex : 'policeNumber',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 100
		}, {
			header : '业务名称',
			dataIndex : 'busName',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 100
		}, {
			header : '部门',
			dataIndex : 'userDepart',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 150
		}, {
			header : '所属地区',
			dataIndex : 'userZone',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 150
		}, {
			header : '终端安全卡类型',
			dataIndex : 'cardType',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 100
		}, {
			header : '访问URL',
			dataIndex : 'accessUrl',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 300,
			renderer : function(v, p, r) {
				return new String(v).substring(0, 20);
			}
		}, {
			header : '请求数',
			dataIndex : 'count',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 50
		}, {
			header : '流量',
			dataIndex : 'flux',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 50
		}],
		sm : new Ext.grid.RowSelectionModel({
			singleSelect : true
		}),

		// paging bar on the bottom
		bbar : new Ext.ux.PagingToolbar({
			id : 'mypagebar',
			store : store
		}),
		tbar : tb,
		viewConfig : {}
	});

	// trigger the data store load

	var chart1 = new Ext.Panel({
		iconCls : 'chart',
		title : '日统计-流量',
		frame : true,
		renderTo : 'report-chart1',
		width : "100%",
		height : 300,
		layout : 'fit',

		items : {
			xtype : 'columnchart',
			store : store,
			url : '../js/ext/resources/charts.swf',
			xField : 'userId',
			yField : 'flux',
			yAxis : new Ext.chart.NumericAxis({
				displayName : '流量大小',
				labelRenderer : Ext.util.Format.numberRenderer('0,0')
			}),
			tipRenderer : function(chart, record) {
				return record.data.auditDate
						+ '终端'+
						+record.data.idTerminal
						+' 的流量大小： '
						+ Ext.util.Format.number(record.data.flux,
								'0,0');
			}
		}
	});
	var chart2 = new Ext.Panel({
		iconCls : 'chart',
		title : '日统计-请求数',
		frame : true,
		renderTo : 'report-chart2',
		width : "100%",
		height : 300,
		layout : 'fit',

		items : {
			xtype : 'columnchart',
			store : store,
			url : '../js/ext/resources/charts.swf',
			xField : 'userId',
			yField : 'count',
			yAxis : new Ext.chart.NumericAxis({
				displayName : '请求数',
				labelRenderer : Ext.util.Format.numberRenderer('0,0')
			}),
			tipRenderer : function(chart, record) {
				return record.data.auditDate
						+ '终端'+
						+record.data.idTerminal
						+ ' 的请求数： '
						+ Ext.util.Format.number(record.data.count,
								'0,0');
			}
		}
	});
	store.load({
		params : {
			start : 0,
			limit : PAGESIZE
		}
	});

});
