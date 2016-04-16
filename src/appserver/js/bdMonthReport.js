/*
 * ! Ext JS Library 3.2.0 Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com http://www.extjs.com/license
 */
url = '../report.do?m=bdMonthReport&_dc=' + new Date().getTime();
title = '业务日志月审计';
Ext.onReady(function() {

	Ext.QuickTips.init();
	var tb = new Ext.Toolbar({
		width : 720,
		height : 30,
		items : ['年：', {
			id : 'bdyear',
			xtype : 'textfield',
			name : 'year',
			emptyText : '点击输入年'
		}, {
			xtype : 'tbspacer',
			width : 40
		}, '月：', {
			id : 'bdmonth',
			xtype : 'textfield',
			name : 'month',
			emptyText : '点击输入月份'
		}, {
			xtype : 'tbspacer',
			width : 40
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
			emptyText : '',
			forceSelection : true,
			triggerAction : 'all',
			selectOnFocus : true,
			width : 80
		}), {
			xtype : 'tbspacer',
			width : 40
		}, {
			text : '统计',
			listeners : {
				"click" : function() {
					var businessName = Ext.getCmp("businessName").getValue();
					if (businessName == '') {
						Ext.Msg.alert("提示", "请选择业务名！");
						return;
					}
					var bdyear = Ext.fly("bdyear").dom.value == '点击输入年'
							? ''
							: Ext.fly("bdyear").dom.value;
					var bdmonth = Ext.fly("bdmonth").dom.value == '点击输入月份'
							? ''
							: Ext.fly("bdmonth").dom.value;
					store.setBaseParam("year", bdyear);
					store.setBaseParam("month", bdmonth);
					store.setBaseParam("businessName", businessName);
					store.load();

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
			name : 'reportDate',
			mapping : 'reportDate'
		}, {
			name : 'reportDay',
			mapping : 'reportDay'
		}, {
			name : 'reportMonth',
			mapping : 'reportMonth'
		}, {
			name : 'reportYear',
			mapping : 'reportYear'
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
		height : "740",
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
			header : "日期",
			dataIndex : 'reportDay',
			width : 100,
			menuDisabled : true,
			sortable : true,
			renderer : function(value, cellmeta,record) {
				return record.data['reportYear']+'-'+record.data['reportMonth']+'-'+value;
			}
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

		tbar : tb,
		viewConfig : {}
	});

	// trigger the data store load

	var chart1 = new Ext.Panel({
		iconCls : 'chart',
		title : '业务月统计-内网流量',
		frame : true,
		renderTo : 'report-chart1',
		width : "100%",
		height : 300,
		layout : 'fit',

		items : {
			xtype : 'columnchart',
			store : store,
			url : '../js/ext/resources/charts.swf',
			xField : 'reportDay',
			yField : 'intDataFlow',
			yAxis : new Ext.chart.NumericAxis({
				displayName : '内网流量',
				labelRenderer : Ext.util.Format.numberRenderer('0,0')
			}),
			tipRenderer : function(chart, record) {
				return record.data.reportDay
						+ ' 的内网流量： '
						+ Ext.util.Format
								.number(record.data.intDataFlow, '0,0');
			}
		}
	});
	var chart2 = new Ext.Panel({
		iconCls : 'chart',
		title : '业务月统计-外网流量',
		frame : true,
		renderTo : 'report-chart2',
		width : "100%",
		height : 300,
		layout : 'fit',

		items : {
			xtype : 'columnchart',
			store : store,
			url : '../js/ext/resources/charts.swf',
			xField : 'reportDay',
			yField : 'extDataFlow',
			yAxis : new Ext.chart.NumericAxis({
				displayName : '外网流量',
				labelRenderer : Ext.util.Format.numberRenderer('0,0')
			}),
			tipRenderer : function(chart, record) {
				return record.data.reportDay
						+ ' 的外网流量： '
						+ Ext.util.Format
								.number(record.data.extDataFlow, '0,0');
			}
		}
	});
	var chart3 = new Ext.Panel({
		iconCls : 'chart',
		title : '业务月统计-内网请求',
		frame : true,
		renderTo : 'report-chart3',
		width : "100%",
		height : 300,
		layout : 'fit',

		items : {
			xtype : 'columnchart',
			store : store,
			url : '../js/ext/resources/charts.swf',
			xField : 'reportDay',
			yField : 'intRecordCount',
			yAxis : new Ext.chart.NumericAxis({
				displayName : '内网请求',
				labelRenderer : Ext.util.Format.numberRenderer('0,0')
			}),
			tipRenderer : function(chart, record) {
				return record.data.reportDay
						+ ' 的内网请求： '
						+ Ext.util.Format.number(record.data.intRecordCount,
								'0,0');
			}
		}
	});
	var chart4 = new Ext.Panel({
		iconCls : 'chart',
		title : '业务月统计-外网请求',
		frame : true,
		renderTo : 'report-chart4',
		width : "100%",
		height : 300,
		layout : 'fit',

		items : {
			xtype : 'columnchart',
			store : store,
			url : '../js/ext/resources/charts.swf',
			xField : 'reportDay',
			yField : 'extRecordCount',
			yAxis : new Ext.chart.NumericAxis({
				displayName : '外网请求',
				labelRenderer : Ext.util.Format.numberRenderer('0,0')
			}),
			tipRenderer : function(chart, record) {
				return record.data.reportDay
						+ ' 的外网请求： '
						+ Ext.util.Format.number(record.data.extRecordCount,
								'0,0');
			}
		}
	});
	store.load();

});
