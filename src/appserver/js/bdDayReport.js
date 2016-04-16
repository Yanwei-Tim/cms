/*
 * ! Ext JS Library 3.2.0 Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com http://www.extjs.com/license
 */
url = '../report.do?m=bdDayReport&_dc=' + new Date().getTime();
charturl = '../report.do?m=bdDayReportCount&_dc=' + new Date().getTime();
title = '业务日志日审计';
Ext.onReady(function() {

	Ext.QuickTips.init();

	var tb = new Ext.Toolbar({
		width : 720,
		height : 30,
		items : ['日期：', {
			id : 'bdEndDate',
			xtype : 'datefield',
			name : 'field1',
			emptyText : '点击输入日期',
			format : 'Y-m-d',
			value : new Date()
		}, {
			xtype : 'tbspacer',
			width : 10
		}, '业务名：', new Ext.form.ComboBox({
			id : "businessName",
			store : new Ext.data.JsonStore({
				url : '../bizConf.do?m=bizCombox&all=1',
				fields : ['businessName']
			}),
			valueField : 'businessName',
			displayField : 'businessName',
			mode : 'remote',
			value : '',
			emptyText : '请选择业务',
			forceSelection : true,
			triggerAction : 'all',
			selectOnFocus : true,
			width : 100
		}), {
			xtype : 'tbspacer',
			width : 10
		}, {
			// xtype: 'button',
			text : '统计',
			listeners : {
				"click" : function() {

					var businessName = Ext.getCmp("businessName").getValue();

					var endDate = Ext.fly("bdEndDate").dom.value == '点击输入日期'
							? ''
							: Ext.fly("bdEndDate").dom.value;
					if (businessName == '' || businessName == 'All') {
						grid.getColumnModel().setHidden(1, true);
						store.setBaseParam("endDate", endDate);
						store.setBaseParam("businessName", '');
						store.load({
							params : {
								start : 0,
								limit : PAGESIZE
							}
						});

						if (chart1) {
							chart1.show();
							chart2.show();
							chart3.show();
							chart4.show();
							// chartStore.load();如果需要重新获取数据则需要重新绑定store到chart，参考chart5
						}
						if (chart5) {
							chart5.hide();
							chart6.hide();
							chart7.hide();
							chart8.hide();
						}
					} else {
						grid.getColumnModel().setHidden(1, false);
						store.setBaseParam("businessName", businessName);
						store.setBaseParam("endDate", endDate);
						store.load({
							params : {
								start : 0,
								limit : PAGESIZE
							}
						});// businessName不能直接放这里，否则点下一页会出问题
						
						var chart5Store = new Ext.data.JsonStore({
							url : charturl,
							root : 'root.result.countList',
							fields : ['reportHour', 'businessName',
									'extDataFlow', 'intDataFlow',
									'extRecordCount', 'intRecordCount']

						});
						chart5Store.setBaseParam("businessName", businessName);
						chart5Store.setBaseParam("endDate", endDate);

                        var chart6Store = new Ext.data.JsonStore({
							url : charturl,
							root : 'root.result.countList',
							fields : ['reportHour', 'businessName',
									'extDataFlow', 'intDataFlow',
									'extRecordCount', 'intRecordCount']

						});
						chart6Store.setBaseParam("businessName", businessName);
						chart6Store.setBaseParam("endDate", endDate);
						
						chart7Store = new Ext.data.JsonStore({
							url : charturl,
							root : 'root.result.countList',
							fields : ['reportHour', 'businessName',
									'extDataFlow', 'intDataFlow',
									'extRecordCount', 'intRecordCount']

						});
						chart7Store.setBaseParam("businessName", businessName);
						chart7Store.setBaseParam("endDate", endDate);
						
						chart8Store = new Ext.data.JsonStore({
							url : charturl,
							root : 'root.result.countList',
							fields : ['reportHour', 'businessName',
									'extDataFlow', 'intDataFlow',
									'extRecordCount', 'intRecordCount']

						});
						chart8Store.setBaseParam("businessName", businessName);
						chart8Store.setBaseParam("endDate", endDate);
						if (chart1) {
							chart1.hide();
							chart2.hide();
							chart3.hide();
							chart4.hide();
						}
						if (chart5) {
							chart5.show();
							chart5Store.load();
							Ext.getCmp("chart5item").bindStore(chart5Store);
							
							chart6.show();
							chart6Store.load();
							Ext.getCmp("chart6item").bindStore(chart6Store);
							
							chart7.show();
							chart7Store.load();
							Ext.getCmp("chart7item").bindStore(chart7Store);
							
							chart8.show();
							chart8Store.load();
							Ext.getCmp("chart8item").bindStore(chart8Store);
							return;
						}

						chart5 = new Ext.Panel({
							id : 'chart5',
							iconCls : 'chart',
							title : '业务日统计-内网流量',
							frame : true,
							renderTo : 'report-chart5',
							width : "100%",
							height : 300,
							layout : 'fit',

							items : {
								id : 'chart5item',
								xtype : 'linechart',
								store : chart5Store,
								url : '../js/ext/resources/charts.swf',
								xField : 'reportHour',
								yAxis : new Ext.chart.NumericAxis({
									displayName : '外网请求',
									labelRenderer : Ext.util.Format
											.numberRenderer('0,0')
								}),
								series : [{
									type : 'line',
									displayName : '内网流量',
									yField : 'intDataFlow',
									style : {
										color : 0x000080
									}
								}]
							}
						});
						chart5Store.load();
						
						chart6 = new Ext.Panel({
							id : 'chart6',
							iconCls : 'chart',
							title : '业务日统计-外网流量',
							frame : true,
							renderTo : 'report-chart6',
							width : "100%",
							height : 300,
							layout : 'fit',

							items : {
								id : 'chart6item',
								xtype : 'linechart',
								store : chart6Store,
								url : '../js/ext/resources/charts.swf',
								xField : 'reportHour',
								yAxis : new Ext.chart.NumericAxis({
									displayName : '外网请求',
									labelRenderer : Ext.util.Format
											.numberRenderer('0,0')
								}),
								series : [{
									type : 'line',
									displayName : '外网流量',
									yField : 'extDataFlow',
									style : {
										color : 0x4169E1
									}
								}]
							}
						});
						chart6Store.load();
						
						chart7 = new Ext.Panel({
							id : 'chart7',
							iconCls : 'chart',
							title : '业务日统计-内网请求',
							frame : true,
							renderTo : 'report-chart7',
							width : "100%",
							height : 300,
							layout : 'fit',

							items : {
								id : 'chart7item',
								xtype : 'linechart',
								store : chart7Store,
								url : '../js/ext/resources/charts.swf',
								xField : 'reportHour',
								yAxis : new Ext.chart.NumericAxis({
									displayName : '外网请求',
									labelRenderer : Ext.util.Format
											.numberRenderer('0,0')
								}),
								series : [{
									type : 'line',
									displayName : '内网请求',
									yField : 'intRecordCount',
									style : {
										color : 0x4682B4
									}
								}]
							}
						});
						chart7Store.load();
						
						chart8 = new Ext.Panel({
							id : 'chart8',
							iconCls : 'chart',
							title : '业务日统计-外网请求',
							frame : true,
							renderTo : 'report-chart8',
							width : "100%",
							height : 300,
							layout : 'fit',

							items : {
								id : 'chart8item',
								xtype : 'linechart',
								store : chart8Store,
								url : '../js/ext/resources/charts.swf',
								xField : 'reportHour',
								yAxis : new Ext.chart.NumericAxis({
									displayName : '外网请求',
									labelRenderer : Ext.util.Format
											.numberRenderer('0,0')
								}),
								series : [{
									type : 'line',
									displayName : '外网请求',
									yField : 'extRecordCount',
									style : {
										color : 0x1E90FF
									}
								}]
							}
						});
						chart8Store.load();

					}
				}
			}
		}]
	});

	// create the Data Store
	var store = new Ext.data.JsonStore({
		url : url,
		root : 'root.result.data',
		totalProperty : 'root.result.total',
		idProperty : 'id',
		// remoteSort: true,

		fields : [{
			name : 'id',
			mapping : 'id'
		}, {
			name : 'reportHour',
			mapping : 'reportHour'
		}, {
			name : 'businessName',
			mapping : 'businessName'
		}, {
			name : 'extDataFlow',
			mapping : 'extDataFlow'
		}, {
			name : 'intDataFlow',
			mapping : 'intDataFlow'
		}, {
			name : 'extRecordCount',
			mapping : 'extRecordCount'
		}, {
			name : 'intRecordCount',
			mapping : 'intRecordCount'
		}, {
			name : 'alertCount',
			mapping : 'alertCount'
		}]

	});
	// store.setDefaultSort('lastpost', 'desc');

	var grid = new Ext.grid.GridPanel({
		width : "100%",
		height : "500",
		renderTo : 'topic-grid',
		// autoHeight:true,
		title : title,
		store : store,
		loadMask : true,
		trackMouseOver : true,// 鼠标悬浮高亮显示
		// autoExpandColumn: 'auditInfo',// 自动扩展宽度适应空白部分
		columnLines : true,// 列分隔符

		// grid columns
		columns : [{
			header : "ID",
			dataIndex : 'id',
			width : 100,
			menuDisabled : true,
			sortable : true,
			hidden : true
		}, {
			id : 'col_reportHour',
			header : "时间点",
			dataIndex : 'reportHour',
			width : 100,
			menuDisabled : true,
			sortable : true,
			hidden : true

		}, {
			header : "业务名称",
			dataIndex : 'businessName',
			width : 150,
			menuDisabled : true,
			sortable : true
		}, {
			header : "内网流量（M）",
			dataIndex : 'intDataFlow',
			width : 100,
			menuDisabled : true
		}, {
			header : "外网流量（M）",
			dataIndex : 'extDataFlow',
			width : 100,
			menuDisabled : true
		}, {
			header : "内网记录/请求数",
			dataIndex : 'intRecordCount',
			width : 100,
			menuDisabled : true
		}, {
			header : "外网记录/请求数",
			dataIndex : 'extRecordCount',
			width : 100,
			menuDisabled : true
		}, {
			header : "报警数",
			dataIndex : 'alertCount',
			width : 100,
			menuDisabled : true
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
	store.load({
		params : {
			start : 0,
			limit : PAGESIZE,
			businessName : ''
		}
	});

	// create the Data Store
	var chartStore = new Ext.data.JsonStore({
		url : charturl,
		root : 'root.result.countList',
		fields : ['reportHour', 'businessName', 'extDataFlow', 'intDataFlow',
				'extRecordCount', 'intRecordCount']

	});
	var chart1 = new Ext.Panel({
		iconCls : 'chart',
		title : '业务日统计-内网流量',
		frame : true,
		renderTo : 'report-chart1',
		width : "100%",
		height : 300,
		layout : 'fit',

		items : {
			xtype : 'columnchart',
			store : chartStore,
			url : '../js/ext/resources/charts.swf',
			xField : 'businessName',
			yField : 'intDataFlow',
			yAxis : new Ext.chart.NumericAxis({
				displayName : '内网流量',
				labelRenderer : Ext.util.Format.numberRenderer('0,0')
			}),
			tipRenderer : function(chart, record) {
				return record.data.businessName
						+ ' 的内网流量： '
						+ Ext.util.Format
								.number(record.data.intDataFlow, '0,0');
			}
		}
	});
	var chart2 = new Ext.Panel({
		iconCls : 'chart',
		title : '业务日统计-外网流量',
		frame : true,
		renderTo : 'report-chart2',
		width : "100%",
		height : 300,
		layout : 'fit',

		items : {
			xtype : 'columnchart',
			store : chartStore,
			url : '../js/ext/resources/charts.swf',
			xField : 'businessName',
			yField : 'extDataFlow',
			yAxis : new Ext.chart.NumericAxis({
				displayName : '外网流量',
				labelRenderer : Ext.util.Format.numberRenderer('0,0')
			}),
			tipRenderer : function(chart, record) {
				return record.data.businessName
						+ ' 的外网流量： '
						+ Ext.util.Format
								.number(record.data.extDataFlow, '0,0');
			}
		}
	});
	var chart3 = new Ext.Panel({
		iconCls : 'chart',
		title : '业务日统计-内网请求',
		frame : true,
		renderTo : 'report-chart3',
		width : "100%",
		height : 300,
		layout : 'fit',

		items : {
			xtype : 'columnchart',
			store : chartStore,
			url : '../js/ext/resources/charts.swf',
			xField : 'businessName',
			yField : 'intRecordCount',
			yAxis : new Ext.chart.NumericAxis({
				displayName : '内网请求',
				labelRenderer : Ext.util.Format.numberRenderer('0,0')
			}),
			tipRenderer : function(chart, record) {
				return record.data.businessName
						+ ' 的内网请求： '
						+ Ext.util.Format.number(record.data.intRecordCount,
								'0,0');
			}
		}
	});
	var chart4 = new Ext.Panel({
		iconCls : 'chart',
		title : '业务日统计-外网请求',
		frame : true,
		renderTo : 'report-chart4',
		width : "100%",
		height : 300,
		layout : 'fit',

		items : {
			xtype : 'columnchart',
			store : chartStore,
			url : '../js/ext/resources/charts.swf',
			xField : 'businessName',
			yField : 'extRecordCount',
			yAxis : new Ext.chart.NumericAxis({
				displayName : '外网请求',
				labelRenderer : Ext.util.Format.numberRenderer('0,0')
			}),
			tipRenderer : function(chart, record) {
				return record.data.businessName
						+ ' 的外网请求： '
						+ Ext.util.Format.number(record.data.extRecordCount,
								'0,0');
			}
		}
	});
	chartStore.load();

	var chart5;
	var chart6;
	var chart7;
	var chart8;
});
