/*
 * ! Ext JS Library 3.2.0 Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com http://www.extjs.com/license
 */
url = '../eqreport.do?m=eqYearReport&_dc=' + new Date().getTime();
title = '设备年统计报表';
Ext.onReady(function() {

	Ext.QuickTips.init();
	var tb = new Ext.Toolbar({
		width : 720,
		height : 30,
		items : ['年：', {
			id : 'reportYear',
			xtype : 'textfield',
			name : 'year',
			emptyText : '点击输入年'
		}, {
			xtype : 'tbspacer',
			width : 10
		}, '设备名：', new Ext.form.ComboBox({
			id : "equName",
			store : new Ext.data.JsonStore({
				url : '../equConf.do?m=equCombox',
				fields : ['equipmentName']
			}),
			valueField : 'equipmentName',
			displayField : 'equipmentName',
			mode : 'remote',
			value : '',
			emptyText : '',
			forceSelection : true,
			triggerAction : 'all',
			selectOnFocus : true,
			width : 80
		}), {
			xtype : 'tbspacer',
			width : 10
		}, {
			// xtype: 'button',
			text : '统计',
			listeners : {
				"click" : function() {
					var equName = Ext.getCmp("equName").getValue();
					if (equName == '') {
						Ext.Msg.alert("提示", "请选择设备！");
						return;
					}
					var reportYear = Ext.fly("reportYear").dom.value;
					if (reportYear == '点击输入年' || reportYear == '') {
						Ext.Msg.alert("提示", "请输入年份！");
						return;
					}
					store.setBaseParam("year", reportYear);
					store.setBaseParam("equName", equName);
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
		root : 'root.result.data',
		totalProperty : 'root.result.total',
		idProperty : 'id',
		// remoteSort: true,
		fields : ['equName', 'reportDate', 'reportMonth', 'breakdownCount',
				'alertCount']

	});
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
		columns : [{
			header : "ID",
			dataIndex : 'id',
			width : 100,
			menuDisabled : true,
			sortable : true,
			hidden : true
		}, {
			header : "月份",
			dataIndex : 'reportMonth',
			width : 100,
			menuDisabled : true

		}, {
			header : "设备名称",
			dataIndex : 'equName',
			width : 150,
			menuDisabled : true
		}, {
			header : "故障次数",
			dataIndex : 'breakdownCount',
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

	var chart1 = new Ext.Panel({
		iconCls : 'chart',
		title : '设备年统计-故障次数',
		frame : true,
		renderTo : 'report-chart1',
		width : "100%",
		height : 300,
		layout : 'fit',

		items : {
			xtype : 'columnchart',
			store : store,
			url : '../js/ext/resources/charts.swf',
//			xField : 'reportMonth',
            xField : 'equName',
			yField : 'breakdownCount',
			yAxis : new Ext.chart.NumericAxis({
				displayName : '故障次数',
				labelRenderer : Ext.util.Format.numberRenderer('0,0')
			}),
			tipRenderer : function(chart, record) {
				return record.data.reportMonth
						+ ' 月的故障次数： '
						+ Ext.util.Format.number(record.data.breakdownCount,
								'0,0');
			}
		}
	});
	var chart2 = new Ext.Panel({
		iconCls : 'chart',
		title : '设备年统计-报警次数',
		frame : true,
		renderTo : 'report-chart2',
		width : "100%",
		height : 300,
		layout : 'fit',

		items : {
			xtype : 'columnchart',
			store : store,
			url : '../js/ext/resources/charts.swf',
//			xField : 'reportMonth',
			xField : 'equName',
			yField : 'alertCount',
			yAxis : new Ext.chart.NumericAxis({
				displayName : '报警次数',
				labelRenderer : Ext.util.Format.numberRenderer('0,0')
			}),
			tipRenderer : function(chart, record) {
				return record.data.reportMonth + ' 月的报警次数： '
						+ Ext.util.Format.number(record.data.alertCount, '0,0');
			}
		}
	});
	store.load({
		params : {
			start : 0,
			limit : PAGESIZE,
			equName : ''
		}
	});

});
