var listGrid;
var editWindow;
var listStore;
var editAction = "";

Ext.onReady(function() {

	Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';

	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'qtip';

	var listRecord = Ext.data.Record.create(['id', 'equName', 'equIconCode',
			'netStation', 'monitorUsed', 'ip', 'runStatus','ipPing']);

	listStore = new Ext.data.Store({
		storeId : 'listStore',
		url : "../../runMonitor.do?m=equIndex",
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
			align : 'center'
		}), {
			header : '设备名称',
			dataIndex : 'equName',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 60
		}, {
			header : '网络位置',
			dataIndex : 'netStation',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 30,
			renderer : function(value, p, r) {
				if (value == 'i') {
					return '内网';
				} else {
					return '外网';
				}
			}
		}, {
			header : '是否开启监控',
			dataIndex : 'monitorUsed',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 40,
			renderer : function(value, p, r) {
				if (value == 'Y') {
					return '是';
				} else {
					return '否';
				}
			}
		}, {
			header : 'IP',
			dataIndex : 'ip',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 50
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
            header : '网络状态',
            dataIndex : 'ipPing',
            align : 'center',
            sortable : false,
            menuDisabled : true,
            width : 50,
            renderer : function(value) {
                if (value == true) {
                    return '<img src="../../img/icon/ok.png" alt="运行正常" title="运行正常" />';
                }  else {
                    return '<img src="../../img/icon/off.gif" alt="异常" title="异常"/>';
                }
            }
        },  {
			header : '操作',
			align : 'center',
			dataIndex : 'id',
			menuDisabled : true,
			width : 60,
			renderer : function(value, p, r) {
				return '<a href="javascript:void(0);" onclick="showRunDetail();return false;">运行信息</a>'
						+ '&nbsp;&nbsp;'
						+ '<a href="javascript:void(0);" onclick="showEquDetail();return false;">设备信息</a>';
			}
		}],
		viewConfig : {
			forceFit : true
		},
		stripeRows : true,
		sm : new Ext.grid.RowSelectionModel({
			singleSelect : true
		}),

		bbar : page_toolbar
	});



	editWindow = new Ext.Window({
		width : 670,
		height : 400,
		modal : true,
		autoScroll : true,
		closeAction : 'hide',
		buttons : [{
			xtype : "tbfill"
		}, {
			text : '关闭',
			handler : function() {
				editWindow.hide();
			}
		}],
		listeners : {
			'hide' : function() {
				// editForm.getForm().reset();
			}
		}
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
		interval : 30000
			// 30秒
	}
	Ext.TaskMgr.start(task);

}); // / Ext onReady end!

function showRunDetail() {
	var selectGridRow = listGrid.getSelectionModel().getSelected();
	editWindow.setTitle("设备运行信息", "win_add");
	editWindow.removeAll(true);
	editWindow.show();
	editWindow.load('../../runMonitor.do?m=equDetail&id='
			+ selectGridRow.data.id);
	var task = {
		run : function() {
			editWindow.load('../../runMonitor.do?m=equDetail&id='
					+ selectGridRow.data.id)
					+ "&time" + new Date().getTime();
		},
		interval : 20000
	}
	Ext.TaskMgr.start(task);
	editWindow.on("hide", function() {
		Ext.TaskMgr.stop(task);
	});
}

function showEquDetail() {
	var selectGridRow = listGrid.getSelectionModel().getSelected();
	editWindow.setTitle("详细信息", "win_add");
	editWindow.removeAll(true);
	editWindow.show();
	editWindow.load('../../equConf.do?m=detail&id=' + selectGridRow.data.id);
}

function download(fileName) {
    window.open('../../equConf.do?m=download&fileName=' + fileName,	'_blank');
}
