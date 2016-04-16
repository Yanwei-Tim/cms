var listGrid;
var listStore;

Ext.onReady(function() {

	Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';

	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'qtip';

	var listRecord = Ext.data.Record.create(['id', 'accessTime', 'idTerminal',
			'userId', 'userDepart', 'userZone', 'policeNumber','accessUrl','cardType','count','flux','busName']);

	listStore = new Ext.data.Store({
		storeId : 'listStore',
		url : "../../runMonitor.do?m=terminalIndex",
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
		id : 'grid.info',
		border : false,
		store : listStore,
		columns : [{
			header : '警号',
			dataIndex : 'policeNumber',
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
			width : 100
		}, {
			header : '业务名称',
			dataIndex : 'busName',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 50
		}, {
			header : '部门',
			dataIndex : 'userDepart',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 100
		}, {
			header : '所属地区',
			dataIndex : 'userZone',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 100
		}, {
			header : '终端ID',
			dataIndex : 'idTerminal',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 50
		}, {
			header : '终端安全卡类型',
			dataIndex : 'cardType',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 50
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
		}, {
			header : '访问时间',
			dataIndex : 'accessTime',
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
			width : 100,
			renderer : function(v, p, r) {
				return new String(v).substring(0, 20);
			}
		}, {
			header : '操作',
			align : 'center',
			dataIndex : 'id',
			menuDisabled : true,
			width : 20,
			renderer : oprate_showUrl
		}],
		viewConfig : {
			forceFit : true
		},
		stripeRows : true,
		sm : new Ext.grid.RowSelectionModel({
			singleSelect : true
		}),

		bbar :page_toolbar
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

});

function oprate_showUrl(value){
	return "<a href='javascript:;' onclick='detail()'>详细</a>";
}

function detail(){
	var grid = Ext.getCmp('grid.info');
	var selModel = grid.getSelectionModel();
    var formPanel;
    if(selModel.hasSelection()){
        var selections = selModel.getSelections();
        Ext.each(selections,function(item){
            formPanel = new Ext.form.FormPanel({
                defaultType : 'displayfield',
                labelWidth:100,
                autoScroll:true,
                frame:true,
                border:false,
                labelAlign:'right',
				items : [
				    {fieldLabel : "警号",value: item.data.policeNumber},
				    {fieldLabel : "业务名称",value: item.data.busName},
				    {fieldLabel : "访问URL",value: item.data.accessUrl},
				    {fieldLabel : "访问时间",value: item.data.accessTime},
				    {fieldLabel : "所属部门",value: item.data.userDepart},
				    {fieldLabel : "所属地区",value: item.data.userZone},
				    {fieldLabel : "身份证号",value: item.data.userId},
				    {fieldLabel : "终端ID",value: item.data.idTerminal},
				    {fieldLabel : "流量",value: item.data.flux},
				    {fieldLabel : "请求数",value: item.data.count},
				    {fieldLabel : "安全卡类型",value: item.data.cardType}
				]
            });
        });
    }
	var win = new Ext.Window({
		title:"详细信息",
        width:500,
        height:350,
        modal:true,
        layout:'fit',
    	items:[formPanel],
    	bbar:[
              new Ext.Toolbar.Fill(),
              new Ext.Button ({
                  allowDepress : false,
                  handler : function() {win.close();},
                  text : '返回'
              }),
              new Ext.Button ({
                  allowDepress : false,
                  handler : function() {win.close();},
                  text : '关闭'
              })
          ]
    }).show(); 
}