/*
 * ! Ext JS Library 3.2.0 Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com http://www.extjs.com/license
 */
url = '../eqreport.do?m=eqDayReport&_dc=' + new Date().getTime();
charturl = '../eqreport.do?m=eqDayReportCount&_dc=' + new Date().getTime();
title = '设备日统计报表';
Ext.onReady(function() {

	Ext.QuickTips.init();

	var tb = new Ext.Toolbar({
		width : 720,
		height : 30,
		items : ['日期：', {
			id : 'endDate',
			xtype : 'datefield',
			name : 'endDate',
			emptyText : '点击输入日期',
			format : 'Y-m-d',
			value : new Date()
		}, {
			xtype : 'tbspacer',
			width : 10
		}, '设备名：', new Ext.form.ComboBox({
			id : "equName",
			store : new Ext.data.JsonStore({
				url : '../equConf.do?m=equCombox&all=1',
				fields : ['equipmentName']
			}),
			valueField : 'equipmentName',
			displayField : 'equipmentName',
			mode : 'remote',
			value : '',
			emptyText : '请选择设备',
			forceSelection : true,
			triggerAction : 'all',
			selectOnFocus : true,
			width : 100
		}), {
			xtype : 'tbspacer',
			width : 10
		}, {
			// xtype: 'button',
			text : '查询',
			listeners : {
				"click" : function() {
					var equName = Ext.getCmp("equName").getValue();
					var endDate = Ext.fly("endDate").dom.value == '点击输入日期'
							? ''
							: Ext.fly("endDate").dom.value;
					if (equName == '' || equName == 'All') {
						// grid.getColumnModel().setHidden(1, true);
						grid.getColumnModel().setHidden(2, true);
						store.setBaseParam("endDate", endDate);
						store.setBaseParam("equName", '');
						store.load({
							params : {
								start : 0,
								limit : PAGESIZE
							}
						});

						if (chart1) {
							chart1.show();
							chart2.show();
                            chartStore.setBaseParam("endDate", endDate);
                            chartStore.setBaseParam("equName", '');
							chartStore.load();//如果需要重新获取数据则需要重新绑定store到chart，参考chart5
						}
						if (chart5) {
							chart5.hide();
							chart6.hide();
						}
					} else {
						// grid.getColumnModel().setHidden(1, false);
						grid.getColumnModel().setHidden(2, false);
						store.setBaseParam("equName", equName);
						store.setBaseParam("endDate", endDate);
						store.load({
							params : {
								start : 0,
								limit : PAGESIZE
							}
						});// equName不能直接放这里，否则点下一页会出问题

						var chart5Store = new Ext.data.JsonStore({
							url : charturl,
							root : 'root.result.countList',
							fields : ['reportTime', 'equName',
									'breakdownCount', 'alertCount']

						});
						chart5Store.setBaseParam("equName", equName);
						chart5Store.setBaseParam("endDate", endDate);
						if (chart1) {
							chart1.hide();
							chart2.hide();
						}
						if (chart5) {
							chart5.show();
							chart5Store.load();
							Ext.getCmp("chart5item").bindStore(chart5Store);

							chart6.show();
//							chart6Store.load();
							Ext.getCmp("chart6item").bindStore(chart5Store);
							return;
						}

						chart5 = new Ext.Panel({
							id : 'chart5',
							iconCls : 'chart',
							title : '设备日统计-故障次数',
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
								xField : 'reportTime',
								yAxis : new Ext.chart.NumericAxis({
									displayName : '外网请求',
									labelRenderer : Ext.util.Format
											.numberRenderer('0,0')
								}),
								series : [{
									type : 'line',
									displayName : '故障次数',
									yField : 'breakdownCount',
									style : {
										color : 0x4169E1
									}
								}]
							}
						});
						chart5Store.load();

						chart6 = new Ext.Panel({
							id : 'chart6',
							iconCls : 'chart',
							title : '设备日统计-安全报警次数',
							frame : true,
							renderTo : 'report-chart6',
							width : "100%",
							height : 300,
							layout : 'fit',

							items : {
								id : 'chart6item',
								xtype : 'linechart',
								store : chart5Store,
								url : '../js/ext/resources/charts.swf',
								xField : 'reportTime',
								yAxis : new Ext.chart.NumericAxis({
									displayName : '外网请求',
									labelRenderer : Ext.util.Format
											.numberRenderer('0,0')
								}),
								series : [{
									type : 'line',
									displayName : '安全报警次数',
									yField : 'alertCount',
									style : {
										color : 0x000080
									}
								}]
							}
						});
//						chart6Store.load();

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

		fields : ['equName', 'equIp', 'reportDate', 'reportTime',
				'breakdownCount', 'alertCount']

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
			header : "日期",
			dataIndex : 'reportDate',
			width : 100,
			menuDisabled : true,
			sortable : true,
			hidden : true
		}, {
			header : "时间点",
			dataIndex : 'reportTime',
			width : 100,
			menuDisabled : true,
			sortable : true,
			hidden : true

		}, {
			header : "设备名称",
			dataIndex : 'equName',
			width : 150,
			menuDisabled : true,
			sortable : true
		}, {
			header : "设备IP地址",
			dataIndex : 'equIp',
			width : 150,
			menuDisabled : true,
			sortable : true
		}, {
			header : "故障次数",
			dataIndex : 'breakdownCount',
			width : 100,
			menuDisabled : true
		}, {
			header : "安全报警次数",
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
			limit : PAGESIZE
		}
	});

	// create the Data Store
	var chartStore = new Ext.data.JsonStore({
		url : charturl,
		root : 'root.result.countList',
		fields : ['reportTime', 'equName', 'breakdownCount', 'alertCount']

	});
	var chart1 = new Ext.Panel({
		iconCls : 'chart',
		title : '设备日统计-故障次数',
		frame : true,
		renderTo : 'report-chart1',
		width : "100%",
		height : 300,
		layout : 'fit',

		items : {
			xtype : 'columnchart',
			store : chartStore,
			url : '../js/ext/resources/charts.swf',
			xField : 'equName',
			yField : 'breakdownCount',
			yAxis : new Ext.chart.NumericAxis({
				displayName : '故障次数',
				labelRenderer : Ext.util.Format.numberRenderer('0,0')
			}),
			tipRenderer : function(chart, record) {
				return record.data.equName
						+ ' 的故障次数： '
						+ Ext.util.Format.number(record.data.breakdownCount,
								'0,0');
			}
		}
	});
	var chart2 = new Ext.Panel({
		iconCls : 'chart',
		title : '设备日统计-报警次数',
		frame : true,
		renderTo : 'report-chart2',
		width : "100%",
		height : 300,
		layout : 'fit',

		items : {
			xtype : 'columnchart',
			store : chartStore,
			url : '../js/ext/resources/charts.swf',
			xField : 'equName',
			yField : 'alertCount',
			yAxis : new Ext.chart.NumericAxis({
				displayName : '报警次数',
				labelRenderer : Ext.util.Format.numberRenderer('0,0')
			}),
			tipRenderer : function(chart, record) {
				return record.data.equName + ' 的报警次数： '
						+ Ext.util.Format.number(record.data.alertCount, '0,0');
			}
		}
	});

	chartStore.load();

	var chart5;
	var chart6;
});
