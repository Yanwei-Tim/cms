var listGrid;
var editWindow;
var listStore;

Ext.onReady(function() {

	Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';

	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'qtip';

	var listRecord = Ext.data.Record.create(['id', 'name', 'linkName',
			'exchModel', 'recordCount', 'dataVolume', 'alertCount',
			'linkCount', 'responseTime','runStatus']);

	listStore = new Ext.data.Store({
		storeId : 'listStore',
		url : "../../runMonitor.do?m=bizIndex",
		reader : new Ext.data.JsonReader({
			totalProperty : 'amount',
			root : "results",
			id : 'id'
		}, listRecord),
		baseParams : {
			p : 1
		}
	});
	
//	listStore.on('load', function() {
//		if (listStore.data.length == 0) {
//			Ext.Msg.alert('提示', '没有搜索到符合条件的数据！');
//		}
//	});

    var page_toolbar = new Ext.PagingToolbar({
        xtype : 'paging',
        id : 'pagingbar',
        pageSize : 15,
        store:listStore,
        displayInfo:true,
        displayMsg:"第 {0} 条到 {1} 条，共 {2} 条",
        emptyMsg:"没有搜索到符合条件的数据！",
        beforePageText:"第",
        afterPageText:"页,共{0}页"
    });

	listGrid = new Ext.grid.GridPanel({
		region : 'center',
		border : false,
		store : listStore,
		columns : [new Ext.grid.RowNumberer({
			header : '',
			dataIndex : 'id',
			width : 24,
			align : 'center',
			hidden : true
		}), {
			header : '业务名',
			dataIndex : 'name',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 60
		}, {
			header : '接入对象',
			dataIndex : 'linkName',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 60
		}, {
			header : '业务操作方式',
			dataIndex : 'exchModel',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 40
		}, {
			header : '总记录/请求数',
			dataIndex : 'recordCount',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 50
		}, {
			header : '总流量（M）',
			dataIndex : 'dataVolume',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 50
		}, {
			header : '总报警次数',
			dataIndex : 'alertCount',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 50
		}, {
			header : '连接数',
			dataIndex : 'linkCount',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 30
		}, {
			header : '响应时间（毫秒）',
			dataIndex : 'responseTime',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 40
        }, {
			header : '运行状态',
			dataIndex : 'runStatus',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 50,
			renderer : function(v, p, r) {
				if (v == 200) {
					return '<img src="../../img/icon/ok.png" alt="运行正常" title="运行正常" />';
				} else if (v == 501) {
					return '<img src="../../img/icon/warning.png" alt="告警" title="告警"/>';
				} else if (v == 503) {
					return '<img src="../../img/icon/off.gif" alt="服务不可用" title="服务不可用"/>';
				} else {
                    return '<img src="../../img/icon/off.gif" alt="异常" title="异常"/>';
                }
			}
		}, {
			header : '操作',
			align : 'center',
			dataIndex : 'id',
			menuDisabled : true,
			width : 60,
			renderer : function(value, p, r) {
				return String
						.format('<a href="javascript:void(0);" onclick="showDetail();return false;">详细</a>'
								+ '&nbsp;&nbsp;'
								+ '<a href="javascript:void(0);" onclick="showStat();return false;">统计</a>');
			}
		}],
		viewConfig : {
			forceFit : true
		},
		loadMask : {
			msg : '正在加载数据，请稍侯……'
		},
		stripeRows : true,
		sm : new Ext.grid.RowSelectionModel({
			singleSelect : true
		}),

		bbar : page_toolbar
//        {
//			xtype : 'paging',
//			id : 'pagingbar',
//			pageSize : 15,
//			store : listStore,
//			displayInfo : true,
//			displayMsg : '第 {0} 条到 {1} 条，共 {2} 条',
//			emptyMsg : '没有搜索到符合条件的数据！' ,
//            beforePageText:"第"
//		}
	});



	editWindow = new Ext.Window({
		width : 550,
		height : 400,
		modal : true,
		closeAction : 'hide',
		autoScroll : true,
		buttons : [{
			xtype : "tbfill"
		}, {
			text : '关闭',
			handler : function() {
				editWindow.hide();
			}
		}]
	});

	var viewport = new Ext.Viewport({
		layout : 'border',
		border : false,
		items : [listGrid]
	});

	listStore.load();

	var task = {
		run : function() {
			listStore.reload();
		},
		interval : 50000*60
			// 50秒
	}
	Ext.TaskMgr.start(task);

}); // / Ext onReady end!

function showDetail() {
	var selectGridRow = listGrid.getSelectionModel().getSelected();
	editWindow.setTitle("详细信息", "win_add");
	editWindow.removeAll(true);
	editWindow.show();
	editWindow.load('../../bizConf.do?m=detail&id=' + selectGridRow.data.id);
}

function showStat() {
	var selectGridRow = listGrid.getSelectionModel().getSelected();
	editWindow.setTitle("统计信息", "win_add");
	editWindow.removeAll(true);
	editWindow.show();
	editWindow.load('../../runMonitor.do?m=bizStat&name=' + selectGridRow.data.name);
}
