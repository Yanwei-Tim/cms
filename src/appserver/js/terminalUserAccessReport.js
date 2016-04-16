url = '../terminalAccessAuditReport.do?m=index';
title = '终端用户访问日统计表';
Ext.onReady(function() {
	Ext.QuickTips.init();
	var comboSysClass = new Ext.data.JsonStore({
		autoLoad : true,
		url : '../sysClass.do?m=comboSysClass',
		sotreId : 'comboSysClass',
		root : 'results',
		idProperty : 'id',
		fields : ['id', 'name']
	});
	var tb = new Ext.Toolbar({
		width : 720,
		height : 30,
		items : ['日期：', {
			id : 'reportDate.info',
			xtype : 'datefield',
			name : 'reportDate',
			emptyText : '点击输入日期',
			format : 'Y-m-d',
			allowBlank : false,
			value : new Date()
		}, {
			xtype : 'tbspacer',
			width : 10
		}, '部门：', new Ext.form.ComboBox({
			id : "userDepart.info",
			store : comboSysClass,
			valueField : 'id',
			displayField : 'name',
			mode : 'local',
			editable : true,
			allowBlank : false,
			emptyText : '请选择',
			forceSelection : true,
			triggerAction : 'all',
			selectOnFocus : true,
			listWidth  : 210,
			width : 200
		}), {
			xtype : 'tbspacer',
			width : 10
		}, {
			// xtype: 'button',
			text : '统计',
			listeners : {
				"click" : function() {
					var userDepart = Ext.getCmp("userDepart.info").getValue();
					if (userDepart == '') {
						Ext.Msg.alert("提示", "请选择部门！");
						return;
					}
					var reportDate = Ext.fly("reportDate.info").dom.value;
					if (reportDate == '点击输入日期' || reportDate == '') {
						Ext.Msg.alert("提示", "请输入日期！");
						return;
					}
					store.setBaseParam("reportDate", reportDate);
					store.setBaseParam("userDepart", userDepart);
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
//		fields : ['policeType', 'busName', 'reportDate', 'cardType', 'userId',
//				'userDepart','flux','count']
		fields : [{
				name : 'id',
				mapping : 'id'
			}, {
				name : 'reportDate',
				mapping : 'reportDate'
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
		columns : [
		           rowNumber,
		{
			header : "ID",
			dataIndex : 'id',
			width : 100,
			menuDisabled : true,
			sortable : true,
			hidden : true
		}, {
			header : "所属单位",
			dataIndex : 'userDepart',
			width : 200,
			menuDisabled : true
		}, {
			header : "业务名称",
			dataIndex : 'busName',
			width : 100,
			menuDisabled : true
		}, {
			header : "身份证号",
			dataIndex : 'userId',
			width : 150,
			menuDisabled : true
		}, {
			header : "终端安全卡类型",
			dataIndex : 'cardType',
			width : 100,
			menuDisabled : true
		}, {
			header : "流量",
			dataIndex : 'flux',
			width : 100,
			menuDisabled : true
		}, {
			header : "请求数",
			dataIndex : 'count',
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
				return record.data.reportDate
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
				return record.data.reportDate
						+ ' 的请求数： '
						+ Ext.util.Format.number(record.data.count,	'0,0');
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
