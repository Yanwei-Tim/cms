
function show_flag(value,p,r){
          return String.format( '<a href="javascript:void(0);" onclick="viewDetailed1();"><font color="#fa8072">查看设备</font></a> &nbsp; &nbsp;'+
        '<a href="javascript:void(0);" onclick="selectLog(\''+r.get("createtime")+'\',\''+r.get("deviceName")+'\');"><font color="#fa8072">查看日志</font></a> &nbsp; &nbsp;');
}
function show_flag2(value,p,r){
          return String.format( '<a href="javascript:void(0);" onclick="viewDetailed2();"><font color="#fa8072">查看设备</font></a> &nbsp; &nbsp;'+
        '<a href="javascript:void(0);" onclick="selectLog(\''+r.get("createtime")+'\',\''+r.get("deviceName")+'\');"><font color="#fa8072">查看日志</font></a> &nbsp; &nbsp;');
}
function show_flag3(value,p,r){
          return String.format( '<a href="javascript:void(0);" onclick="viewDetailed3();"><font color="#fa8072">查看设备</font></a> &nbsp; &nbsp;'+
        '<a href="javascript:void(0);" onclick="selectLog(\''+r.get("createtime")+'\',\''+r.get("deviceName")+'\');"><font color="#fa8072">查看日志</font></a> &nbsp; &nbsp;');
}
function show_flag4(value,p,r){
          return String.format( '<a href="javascript:void(0);" onclick="viewDetailed4();"><font color="#fa8072">查看设备</font></a> &nbsp; &nbsp;'+
        '<a href="javascript:void(0);" onclick="selectLog(\''+r.get("createtime")+'\',\''+r.get("deviceName")+'\');"><font color="#fa8072">查看日志</font></a> &nbsp; &nbsp;');
}
function selectLog(value,v2){
        Ext.QuickTips.init();
        Ext.form.Field.prototype.msgTarget = 'side';
        var start = 0;
        var pageSize = 15;
        var record = new Ext.data.Record.create([
            {name:'id',			mapping:'id'},
            {name:'Equipment_name',			mapping:'Equipment_name'},
            {name:'Level',			mapping:'Level'},
            {name:'Log_info',			mapping:'Log_info'},
            {name:'Link_name',			mapping:'Link_name'},
            {name:'Link_property',			mapping:'Link_property'},
            {name:'Link_security',			mapping:'Link_security'},
            {name:'Link_Corp',			mapping:'Link_Corp'},
            {name:'Log_time',			mapping:'Log_time'}
        ]);
        var proxy = new Ext.data.HttpProxy({
            url:"../../equLog.do?m=selectByTime&createtime="+value+"&deviceName="+v2
        });
        var reader = new Ext.data.JsonReader({
            totalProperty:'total',
            root:'rows'
        },record);
        var store = new Ext.data.GroupingStore({
            proxy : proxy,
            reader : reader
        });
        store.load({
            params:{
                start:start,limit:pageSize
            }
        });
        var rowNumber = new Ext.grid.RowNumberer();         //自动 编号
        var colM = new Ext.grid.ColumnModel([
            rowNumber,
//            {header:"id",			dataIndex:"id",  align:'center',sortable:true,menuDisabled:true,width:100},
            {header:"设备名称",			dataIndex:"Equipment_name",  align:'center',sortable:true,menuDisabled:true,width:200},
            {header:"日志内容",			dataIndex:"Log_info",  align:'center',sortable:true,menuDisabled:true,width:200},
            {header:"链路名称",			dataIndex:"Link_name",  align:'center',sortable:true,menuDisabled:true,width:200},
//            {header:"Link_property",			dataIndex:"Link_property",  align:'center',sortable:true,menuDisabled:true,width:200},
//            {header:"链路安全",			dataIndex:"Link_security",  align:'center',sortable:true,menuDisabled:true,width:200},
//            {header:"Link_Corp",			dataIndex:"Link_Corp",  align:'center',sortable:true,menuDisabled:true,width:200},
            {header:"日志时间",			dataIndex:"Log_time",  align:'center',sortable:true,menuDisabled:true,width:200},
            {header:"日志等级",			dataIndex:"Level",  align:'center',sortable:true,menuDisabled:true,width:200}
            /*,
             {header:'操作标记',		dataIndex:'id',		  align:'center',sortable:true,menuDisabled:true, renderer:show_flag,	width:100}*/

        ]);
    var page_toolbar = new Ext.PagingToolbar({
        pageSize : pageSize,
        store:store,
        displayInfo:true,
        displayMsg:"第{0}条到第{1}条，共{2}条",
        emptyMsg:"没有记录",
        beforePageText:"第",
        afterPageText:"页,共{0}页"
    });
    var grid_panel = new Ext.grid.GridPanel({
        id:'grid.info',
        plain:true,

        height:400,
        animCollapse:true,
        loadMask:{msg:'正在加载数据，请稍后...'},
        border:false,
        collapsible:false,
        cm:colM,
        store:store,
        stripeRows:true,
        enableDragDrop: true,
        selModel:new Ext.grid.RowSelectionModel({singleSelect:true}),
        view:new Ext.grid.GroupingView({
            forceFit:true,
            groupingTextTpl:'{text}({[values.rs.length]}条记录)'
        })
//        ,
//        bbar:page_toolbar
    });
    var win = new Ext.Window({
        title:"查看日志",
        width:800,
        modal:true,
        items:grid_panel,
        bbar:[
            '->',
            {
                text:'关闭',
                handler:function(){
                    win.close();
                }
            }
        ]
    }).show();
}

/**
 * 用户管理
 */
Ext.onReady(function() {

    Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';

    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';

    var start = 0;
    var pageSize = 15;
    var record = new Ext.data.Record.create([
         {name:'id',			mapping:'id'},
        {name:'deviceId',			mapping:'deviceId'},
        {name:'deviceName',			mapping:'deviceName'},
        {name:'ip',			mapping:'ip'},
        {name:'status',			mapping:'status'},
        {name:'createtime',			mapping:'createtime'},
        {name:'type',			mapping:'type'}
    ]);
    var proxy = new Ext.data.HttpProxy({
       url:"../../equStatus.do?m=selectByTime_1"
    });
    var reader = new Ext.data.JsonReader({
//        fields:["id", "name","url"],
        totalProperty:'total',
        root:'rows'
    },record);
    var store = new Ext.data.GroupingStore({
        proxy : proxy,
        reader : reader
    });
    store.load({
        params:{
            start:start,limit:pageSize
        }
    });
    var rowNumber = new Ext.grid.RowNumberer();         //自动 编号
    var colM = new Ext.grid.ColumnModel([
        rowNumber,
        {header:"设备名称",		dataIndex:"deviceName",width :50, align:'center',sortable:true,menuDisabled:true},
        {header:"ip",		dataIndex:"ip",	width : 50,  align:'center',sortable:true,menuDisabled:true},
        {header:'状态',		    dataIndex:'status',	width:30 , align:'center',sortable:true,menuDisabled:true,renderer : function(value) {
               if (value == '1') {
                    return '<img src="../../img/icon/ok.png" alt="正常" title="正常" />';
                }  else {
                    return '<img src="../../img/icon/off.gif" alt="异常" title="异常"/>';
                }
            }},
        {header:'事件时间',		    dataIndex:'createtime',width:80 ,   align:'center',sortable:true,menuDisabled:true},
        {header:'操作标记',		dataIndex:'deviceId',		  align:'center',sortable:true,menuDisabled:true, renderer:show_flag}

    ]);
     var task = {
        run: function(){
            store.reload();
            grid_panel.reload;
        },
        interval: 1000*300 //10 second
    }
    var runner = new Ext.util.TaskRunner();
    runner.start(task);
    var page_toolbar = new Ext.PagingToolbar({
        pageSize : pageSize,
        store:store,
        displayInfo:true,
        displayMsg:"第{0}条到第{1}条，共{2}条",
        emptyMsg:"没有记录",
        beforePageText:"第",
        afterPageText:"页,共{0}页"
    });
    var grid_panel = new Ext.grid.GridPanel({
        id:'grid.info',
        plain:true,
//        height:600,
        width:setWidth(),
        animCollapse:true,
        loadMask:{msg:'正在加载数据，请稍后...'},
        border:false,
        collapsible:false,
        cm:colM,
        store:store,
        stripeRows:true,
        autoExpandColumn:2,
        disableSelection:true,
        bodyStyle:'width:100%',
        enableDragDrop: true,
        selModel:new Ext.grid.RowSelectionModel({singleSelect:true}),
        viewConfig:{
            forceFit:true,
            enableRowBody:true,
            getRowClass:function(record,rowIndex,p,store){
                return 'x-grid3-row-collapsed';
            }
        },
        view:new Ext.grid.GroupingView({
            forceFit:true,
            groupingTextTpl:'{text}({[values.rs.length]}条记录)'
        })
//        ,
//        bbar:page_toolbar
    });

    var record2 = new Ext.data.Record.create([
         {name:'id',			mapping:'id'},
        {name:'deviceId',			mapping:'deviceId'},
        {name:'deviceName',			mapping:'deviceName'},
        {name:'ip',			mapping:'ip'},
        {name:'status',			mapping:'status'},
        {name:'createtime',			mapping:'createtime'},
        {name:'type',			mapping:'type'}
    ]);
    var proxy2 = new Ext.data.HttpProxy({
       url:"../../equStatus.do?m=selectByTime_2"
    });
    var reader2 = new Ext.data.JsonReader({
//        fields:["id", "name","url"],
        totalProperty:'total',
        root:'rows'
    },record2);
    var store2 = new Ext.data.GroupingStore({
        proxy : proxy2,
        reader : reader2
    });
    store2.load({
        params:{
            start:start,limit:pageSize
        }
    });

    var colM2 = new Ext.grid.ColumnModel([
        rowNumber,
        {header:"设备名称",		dataIndex:"deviceName",	      align:'center',sortable:true,menuDisabled:true},
        {header:"ip",		dataIndex:"ip",	      align:'center',sortable:true,menuDisabled:true},
        {header:"状态",		    dataIndex:'status',	  align:'center',sortable:true,menuDisabled:true,renderer : function(value) {
                if (value == '1') {
                    return '<img src="../../img/icon/ok.png" alt="正常" title="正常" />';
                }  else {
                    return '<img src="../../img/icon/off.gif" alt="异常" title="异常"/>';
                }
            }
},
        {header:"事件时间",		    dataIndex:'createtime',    align:'center',sortable:true,menuDisabled:true},
        {header:'操作标记',		dataIndex:'deviceId',		  align:'center',sortable:true,menuDisabled:true, renderer:show_flag2,	width:100}

    ]);
    var task2 = {
        run: function(){
            store2.reload();
            grid_panel2.reload;
        },
        interval: 1000*300 //10 second
    }
    var runner2 = new Ext.util.TaskRunner();
    runner2.start(task2);
    var page_toolbar2 = new Ext.PagingToolbar({
        pageSize : pageSize,
        store:store2,
        displayInfo:true,
        displayMsg:"第{0}条到第{1}条，共{2}条",
        emptyMsg:"没有记录",
        beforePageText:"第",
        afterPageText:"页,共{0}页"
    });
    var grid_panel2 = new Ext.grid.GridPanel({
        id:'grid.info2',
        plain:true,
//        height:600,
        width:setWidth(),
        animCollapse:true,
        loadMask:{msg:'正在加载数据，请稍后...'},
        border:false,
        collapsible:false,
        cm:colM2,
        store:store2,
        stripeRows:true,
        autoExpandColumn:2,
        disableSelection:true,
        bodyStyle:'width:100%',
        enableDragDrop: true,
        selModel:new Ext.grid.RowSelectionModel({singleSelect:true}),
        viewConfig:{
            forceFit:true,
            enableRowBody:true,
            getRowClass:function(record,rowIndex,p,store){
                return 'x-grid3-row-collapsed';
            }
        },
        view:new Ext.grid.GroupingView({
            forceFit:true,
            groupingTextTpl:'{text}({[values.rs.length]}条记录)'
        })
//        ,
//        bbar:page_toolbar2
    });



    var record3 = new Ext.data.Record.create([
         {name:'id',			mapping:'id'},
        {name:'deviceId',			mapping:'deviceId'},
        {name:'deviceName',			mapping:'deviceName'},
        {name:'ip',			mapping:'ip'},
        {name:'status',			mapping:'status'},
        {name:'createtime',			mapping:'createtime'},
        {name:'type',			mapping:'type'}
    ]);
    var proxy3 = new Ext.data.HttpProxy({
       url:"../../equStatus.do?m=selectByTime_3"
    });
    var reader3 = new Ext.data.JsonReader({
        totalProperty:'total',
        root:'rows'
    },record3);
    var store3 = new Ext.data.GroupingStore({
        proxy : proxy3,
        reader : reader3
    });
    store3.load({
        params:{
            start:start,limit:pageSize
        }
    });
     var colM3 = new Ext.grid.ColumnModel([
        rowNumber,
        {header:"设备名称",		dataIndex:"deviceName",	      align:'center',sortable:true,menuDisabled:true},
        {header:"ip",		dataIndex:"ip",	      align:'center',sortable:true,menuDisabled:true},
        {header:"状态",		    dataIndex:'status',	  align:'center',sortable:true,menuDisabled:true,renderer : function(value) {
                if (value == '1') {
                    return '<img src="../../img/icon/ok.png" alt="正常" title="正常" />';
                }  else {
                    return '<img src="../../img/icon/off.gif" alt="异常" title="异常"/>';
                }
            }
},
        {header:"事件时间",		    dataIndex:'createtime',    align:'center',sortable:true,menuDisabled:true},
        {header:'操作标记',		dataIndex:'deviceId',		  align:'center',sortable:true,menuDisabled:true, renderer:show_flag3,	width:100}

    ]);
      var task3 = {
        run: function(){
            store3.reload();
            grid_panel3.reload;
        },
        interval: 1000*300 //10 second
    }
    var runner3 = new Ext.util.TaskRunner();
    runner3.start(task3);
    var page_toolbar3 = new Ext.PagingToolbar({
        pageSize : pageSize,
        store:store3,
        displayInfo:true,
        displayMsg:"第{0}条到第{1}条，共{2}条",
        emptyMsg:"没有记录",
        beforePageText:"第",
        afterPageText:"页,共{0}页"
    });
    var grid_panel3 = new Ext.grid.GridPanel({
        id:'grid.info3',
        plain:true,
//        height:600,
        width:setWidth(),
        animCollapse:true,
        loadMask:{msg:'正在加载数据，请稍后...'},
        border:false,
        collapsible:false,
        cm:colM3,
        store:store3,
        stripeRows:true,
        autoExpandColumn:2,
        disableSelection:true,
        bodyStyle:'width:100%',
        enableDragDrop: true,
        selModel:new Ext.grid.RowSelectionModel({singleSelect:true}),
        viewConfig:{
            forceFit:true,
            enableRowBody:true,
            getRowClass:function(record,rowIndex,p,store){
                return 'x-grid3-row-collapsed';
            }
        },
        view:new Ext.grid.GroupingView({
            forceFit:true,
            groupingTextTpl:'{text}({[values.rs.length]}条记录)'
        })
//        ,
//        bbar:page_toolbar3
    });
     var record4 = new Ext.data.Record.create([
         {name:'id',			mapping:'id'},
        {name:'deviceId',			mapping:'deviceId'},
        {name:'deviceName',			mapping:'deviceName'},
        {name:'ip',			mapping:'ip'},
        {name:'status',			mapping:'status'},
        {name:'createtime',			mapping:'createtime'},
        {name:'type',			mapping:'type'}
    ]);
    var proxy4 = new Ext.data.HttpProxy({
       url:"../../equStatus.do?m=selectByTime_4"
    });
    var reader4 = new Ext.data.JsonReader({
        totalProperty:'total',
        root:'rows'
    },record4);
    var store4 = new Ext.data.GroupingStore({
        proxy : proxy4,
        reader : reader4
    });
    store4.load({
        params:{
            start:start,limit:pageSize
        }
    });
       var colM4 = new Ext.grid.ColumnModel([
        rowNumber,
        {header:"设备名称",		dataIndex:"deviceName",	      align:'center',sortable:true,menuDisabled:true},
        {header:"ip",		dataIndex:"ip",	      align:'center',sortable:true,menuDisabled:true},
        {header:"状态",		    dataIndex:'status',	  align:'center',sortable:true,menuDisabled:true,renderer : function(value) {
                if (value == '1') {
                    return '<img src="../../img/icon/ok.png" alt="正常" title="正常" />';
                }  else {
                    return '<img src="../../img/icon/off.gif" alt="异常" title="异常"/>';
                }
            }
},
        {header:"事件时间",		    dataIndex:'createtime',    align:'center',sortable:true,menuDisabled:true},
        {header:'操作标记',		dataIndex:'deviceId',		  align:'center',sortable:true,menuDisabled:true, renderer:show_flag4,	width:100}

    ]);

     var task4 = {
        run: function(){
            store4.reload();
            grid_panel4.reload;
        },
        interval: 1000*300 //10 second
    }
    var runner4 = new Ext.util.TaskRunner();
    runner4.start(task4);
    var page_toolbar4 = new Ext.PagingToolbar({
        pageSize : pageSize,
        store:store4,
        displayInfo:true,
        displayMsg:"第{0}条到第{1}条，共{2}条",
        emptyMsg:"没有记录",
        beforePageText:"第",
        afterPageText:"页,共{0}页"
    });
    var grid_panel4 = new Ext.grid.GridPanel({
        id:'grid.info4',
        plain:true,
//        height:600,
        width:setWidth(),
        animCollapse:true,
        loadMask:{msg:'正在加载数据，请稍后...'},
        border:false,
        collapsible:false,
        cm:colM4,
        store:store4,
        stripeRows:true,
        autoExpandColumn:2,
        disableSelection:true,
        bodyStyle:'width:100%',
        enableDragDrop: true,
        selModel:new Ext.grid.RowSelectionModel({singleSelect:true}),
        viewConfig:{
            forceFit:true,
            enableRowBody:true,
            getRowClass:function(record,rowIndex,p,store){
                return 'x-grid3-row-collapsed';
            }
        },
        view:new Ext.grid.GroupingView({
            forceFit:true,
            groupingTextTpl:'{text}({[values.rs.length]}条记录)'
        })
//        ,
//        bbar:page_toolbar4
    });
    var deviceNameRecord = new Ext.data.Record.create([
        {name:'value',mapping:'value'},
        {name:'key',mapping:'key'}
    ]);
    var deviceNameStore = new Ext.data.Store({
        url:'../../equAction.do?m=getAllByFindName',
        reader:new Ext.data.JsonReader({totalProperty:'total',root:"rows"},deviceNameRecord)
    });
    deviceNameStore.load();
     var startDateDf = new Cls.form.DateTimeField({
						fieldLabel : '日期',
                        id:'startDate.tb.info',
						name : 'startDate',
						format : 'Y-m-d H:i:s' ,
						width:200

					})
    var endDateDf = new Cls.form.DateTimeField({
						fieldLabel : '日期',
                        id:'endDate.tb.info',
						name : 'endDate',
						format : 'Y-m-d H:i:s' ,
                        editable : false,
						style : 'padding-left:0px;',
						anchor : '95%,95%',
                        width:200

					})
     var tb = new Ext.Toolbar({
        width : setWidth(),
        height : 30,
        layout : 'column',
        items : [
        '起始日期：', startDateDf,'结束日期：', endDateDf, {
            xtype : 'tbspacer',
            width : 10
        }, '设备名称',{
            id:'deviceName.tb.info',
            xtype:'combo',
            store:deviceNameStore,
            valueField : 'key',
            displayField : 'value',
            mode : 'local',
            forceSelection : true,
            triggerAction : 'all',
            emptyText : '--请选择--',
            value : '',
            editable : true,
            selectOnFocus : true,
            width : 100
        } ,{
            text : '查询',
            iconCls:'query' ,
           listeners : {
                click : function() {
//                    var logLevel = Ext.fly('logLevel.tb.info').dom.value == '--请选择--'
//                        ? null
//                        : Ext.fly('logLevel.tb.info').dom.value;
                    var startDate = Ext.fly("startDate.tb.info").dom.value == '点击输入日期'
                        ? null
                        : Ext.fly('startDate.tb.info').dom.value;
                    var endDate = Ext.fly('endDate.tb.info').dom.value == '点击输入日期'
                        ? null
                        : Ext.fly('endDate.tb.info').dom.value;
                    var deviceName = Ext.fly('deviceName.tb.info').dom.value == '--请选择--'
                        ? null
                        :Ext.getCmp('deviceName.tb.info').getValue();
                    store.setBaseParam('startDate', startDate);
                    store.setBaseParam('endDate', endDate);
                    store.setBaseParam('deviceName', deviceName);
                    store.load({
                        params : {
                            start : start,
                            limit : pageSize
                        }
                    });
                    store2.setBaseParam('startDate', startDate);
                    store2.setBaseParam('endDate', endDate);
                    store2.setBaseParam('deviceName', deviceName);
                    store2.load({
                        params : {
                            start : start,
                            limit : pageSize
                        }
                    });
                    store3.setBaseParam('startDate', startDate);
                    store3.setBaseParam('endDate', endDate);
                    store3.setBaseParam('deviceName', deviceName);
                    store3.load({
                        params : {
                            start : start,
                            limit : pageSize
                        }
                    });
                    store4.setBaseParam('startDate', startDate);
                    store4.setBaseParam('endDate', endDate);
                    store4.setBaseParam('deviceName', deviceName);
                    store4.load({
                        params : {
                            start : start,
                            limit : pageSize
                        }
                    });


                }
            }
        }]
    });

    var panel = new Ext.Panel({
        plain:true,
        width:setWidth ,
        height:setHeight(),
        border:false,
        layout:'column',
        tbar:[tb] ,
        items:[{
            columnWidth:0.25,
            xtype:'fieldset',
            title:'外网接入区',
            height:600,
            layout:'fit',
            items:[grid_panel4]
        },{
            columnWidth:0.25,
            id:'userCerts.info',
            xtype:'fieldset',
            title:'安全接入区',
            height:600,
            layout:'fit',
            items:[grid_panel]
        },{
            columnWidth:0.25,
            xtype:'fieldset',
            title:'隔离区',
            height:600,
            layout:'fit',
            items:[grid_panel2]
        },{
            columnWidth:0.25,
            xtype:'fieldset',
            title:'公安内网区',
            height:600,
            layout:'fit',
            items:[grid_panel3]
        }]
    });
    new Ext.Viewport({
        layout :'fit',
        renderTo:Ext.getBody(),
        autoScroll:true,
        items:[{
            frame:true,
            autoScroll:true,
            items:[panel]
        }]
    });

});
function setHeight(){
    var h = document.body.clientHeight-20;
    return h;
}

function setWidth(){
    return document.body.clientWidth-8;
}


function viewDetailed4(){
    var  grid_panel = Ext.getCmp("grid.info4");
    var recode = grid_panel.getSelectionModel().getSelected();
    var id=recode.get("deviceId");
     var record1 = new Ext.data.Record.create([
            {name:'equ_Icon_code',			mapping:'equ_Icon_code'},
            {name:'equ_info',			mapping:'equ_info'},
            {name:'model',			mapping:'model'},
            {name:'monitor_used',			mapping:'monitor_used'},
            {name:'link_name',			mapping:'link_name'},
            {name:'other_ip',			mapping:'other_ip'},
            {name:'is_key_device',			mapping:'is_key_device'},
            {name:'ip',			mapping:'ip'},
            {name:'id',			mapping:'id'},
            {name:'subnet_mask',			mapping:'subnet_mask'},
            {name:'equ_name',			mapping:'equ_name'},
            {name:'equ_station',			mapping:'equ_station'},
            {name:'int_or_ext',			mapping:'int_or_ext'},
            {name:'net_station',			mapping:'net_station'},
            {name:'manufacturer',			mapping:'manufacturer'},
            {name:'MAC',			mapping:'MAC'},
            {name:'equ_type',			mapping:'equ_type'}
        ]);
   var ds=new Ext.data.Store(
     {     url:'../../equAction.do?m=getEquipmentByDeviceId&deviceId='+id,
           reader:new Ext.data.JsonReader({
           },record1)});

    ds.load();
    ds.on('load',function(){
        var equ_Icon_code = ds.getAt(0).get('equ_Icon_code');
        var equ_info = ds.getAt(0).get('equ_info');
        var model = ds.getAt(0).get('model');
        var monitor_used = ds.getAt(0).get('monitor_used');
        var link_name = ds.getAt(0).get('link_name');
        var other_ip = ds.getAt(0).get('other_ip');
        var is_key_device = ds.getAt(0).get('is_key_device');
        var ip = ds.getAt(0).get('ip');
        var subnet_mask = ds.getAt(0).get('subnet_mask');
        var equ_name = ds.getAt(0).get('equ_name');
        var equ_station = ds.getAt(0).get('equ_station');
        var int_or_ext = ds.getAt(0).get('int_or_ext');
        var net_station = ds.getAt(0).get('net_station');
        var manufacturer = ds.getAt(0).get('manufacturer');
        var MAC = ds.getAt(0).get('MAC');
        var equ_type = ds.getAt(0).get('equ_type');


        if(is_key_device=='1'){
            Ext.getCmp('is_key_device').setValue('是');
        }else{
            Ext.getCmp('is_key_device').setValue('否');
        }
        if(monitor_used=='Y'){
            Ext.getCmp('monitor_used').setValue('启用');
        }else{
            Ext.getCmp('monitor_used').setValue('不启用');
        }
        if(int_or_ext=='i'){
            Ext.getCmp('int_or_ext').setValue('内部链路');
        }
        if(int_or_ext=='e'){
            Ext.getCmp('int_or_ext').setValue('外部链路');
        }
        if(net_station=='i'){
            Ext.getCmp('net_station').setValue('内网');
        }
        if(net_station=='e'){
            Ext.getCmp('net_station').setValue('内网');
        }



        Ext.getCmp('equ_Icon_code').setValue("<img src=../../img/equ/"+equ_Icon_code+".PNG>");
        Ext.getCmp('equ_info').setValue(equ_info);
        Ext.getCmp('model').setValue(model);
        Ext.getCmp('link_name').setValue(link_name);
        Ext.getCmp('other_ip').setValue(other_ip);
        Ext.getCmp('ip').setValue(ip);
//        Ext.getCmp('id').setValue(id);
        Ext.getCmp('subnet_mask').setValue(subnet_mask);
        Ext.getCmp('equ_name').setValue(equ_name);
        Ext.getCmp('equ_station').setValue(equ_station);
        Ext.getCmp('manufacturer').setValue(manufacturer);
        Ext.getCmp('MAC').setValue(MAC);
        Ext.getCmp('equ_type').setValue(equ_type);
       /* Ext.getCmp('keystore').setValue(keystore);
        Ext.getCmp('password').setValue(password);
        Ext.getCmp('xlsAddress').setValue(xlsAddress);
        Ext.getCmp('caAddress').setValue(caAddress);
        Ext.getCmp('crlAddress').setValue(crlAddress);
        Ext.getCmp('deviceurl').setValue(deviceurl);
        Ext.getCmp('serviceUrl').setValue(serviceUrl);
        Ext.getCmp('blockService').setValue(blockService);
        Ext.getCmp('recoverService').setValue(recoverService);
        Ext.getCmp('snapshotService').setValue(snapshotService);
        Ext.getCmp('syslog').setValue(syslog);*/

    });


   var formPanel = new Ext.form.FormPanel({
        frame:true,
        autoScroll:true,
        labelWidth:150,
        baseCls : 'x-plain',
        labelAlign:'right',
        defaultWidth:300,
        width:800,
               layout:'form',
               border:false,
               defaults:{
                   width:250
               },
               items:[
                   new Ext.form.DisplayField({
                       name:'equ_name',
                       id:'equ_name',
                       fieldLabel:'设备名称'
                   }),
                     new Ext.form.DisplayField({
                       name:'model',
                       id:'model',
                       fieldLabel:'是否核心设备'
                   }),
                     new Ext.form.DisplayField({
                       name:'equ_type',
                       id:'equ_type',
                       fieldLabel:'设备类型'
                   }),
                     new Ext.form.DisplayField({
                       name:'equ_Icon_code',
                       id:'equ_Icon_code',
                       fieldLabel:'设备图标'
                   }),
                     new Ext.form.DisplayField({
                       name:'net_station',
                       id:'net_station',
                       fieldLabel:'网络位置'
                   }),
                     new Ext.form.DisplayField({
                       name:'monitor_used',
                       id:'monitor_used',
                       fieldLabel:'是否开启监控'
                   }),
                     new Ext.form.DisplayField({
                       name:'ip',
                       id:'ip',
                       fieldLabel:'首选IP'
                   }),  new Ext.form.DisplayField({
                       name:'other_ip',
                       id:'other_ip',
                       fieldLabel:'次选IP'
                   }),
                     new Ext.form.DisplayField({
                       name:'MAC',
                       id:'MAC',
                       fieldLabel:'MAC地址'
                   }),
                   new Ext.form.DisplayField({
                       name:'subnet_mask',
                       id:'subnet_mask',
                       fieldLabel:'子网掩码'
                   }),
                   new Ext.form.DisplayField({
                       name:'int_or_ext',
                       id:'int_or_ext',
                       fieldLabel:'链路位置'
                   }),
                   new Ext.form.DisplayField({
                       name:'link_name',
                       id:'link_name',
                       fieldLabel:'链路'
                   }),
                   new Ext.form.DisplayField({
                       name:'equ_station',
                       id:'equ_station',
                       fieldLabel:'设备位置'
                   }),
                   new Ext.form.DisplayField({
                       name:'equ_info',
                       id:'equ_info',
                       fieldLabel:'设备硬件配置'
                   }),
                   new Ext.form.DisplayField({
                       name:'manufacturer',
                       id:'manufacturer',
                       fieldLabel:'制造商'
                   }),
                   new Ext.form.DisplayField({
                       name:'model',
                       id:'model',
                       fieldLabel:'产品型号'
                   }),
                    new Ext.form.DisplayField({
                       name:'provider',
                       id:'provider',
                       fieldLabel:'供应商'
                   }),
                    new Ext.form.DisplayField({
                       name:'equ_phone',
                       id:'equ_phone',
                       fieldLabel:'联系电话'
                   }),
                    new Ext.form.DisplayField({
                       name:'other_phone',
                       id:'other_phone',
                       fieldLabel:'其他联系方式'
                   }),
                    new Ext.form.DisplayField({
                       name:'is_key_device',
                       id:'is_key_device',
                       fieldLabel:'是否是关键设备'
                   })
               ]
           });

           var select_Win = new Ext.Window({
               title:"查看详细",
               width:700,
               autoScroll:true,
               layout:'fit',
               height:600,
               modal:true,
               items:formPanel
           });
           select_Win.show();


}
function viewDetailed3(){
    var  grid_panel = Ext.getCmp("grid.info3");
    var recode = grid_panel.getSelectionModel().getSelected();
    var id=recode.get("deviceId");
     var record1 = new Ext.data.Record.create([
            {name:'equ_Icon_code',			mapping:'equ_Icon_code'},
            {name:'equ_info',			mapping:'equ_info'},
            {name:'model',			mapping:'model'},
            {name:'monitor_used',			mapping:'monitor_used'},
            {name:'link_name',			mapping:'link_name'},
            {name:'other_ip',			mapping:'other_ip'},
            {name:'is_key_device',			mapping:'is_key_device'},
            {name:'ip',			mapping:'ip'},
            {name:'id',			mapping:'id'},
            {name:'subnet_mask',			mapping:'subnet_mask'},
            {name:'equ_name',			mapping:'equ_name'},
            {name:'equ_station',			mapping:'equ_station'},
            {name:'int_or_ext',			mapping:'int_or_ext'},
            {name:'net_station',			mapping:'net_station'},
            {name:'manufacturer',			mapping:'manufacturer'},
            {name:'MAC',			mapping:'MAC'},
            {name:'equ_type',			mapping:'equ_type'}
        ]);
   var ds=new Ext.data.Store(
     {     url:'../../equAction.do?m=getEquipmentByDeviceId&deviceId='+id,
           reader:new Ext.data.JsonReader({
           },record1)});

    ds.load();
    ds.on('load',function(){
        var equ_Icon_code = ds.getAt(0).get('equ_Icon_code');
        var equ_info = ds.getAt(0).get('equ_info');
        var model = ds.getAt(0).get('model');
        var monitor_used = ds.getAt(0).get('monitor_used');
        var link_name = ds.getAt(0).get('link_name');
        var other_ip = ds.getAt(0).get('other_ip');
        var is_key_device = ds.getAt(0).get('is_key_device');
        var ip = ds.getAt(0).get('ip');
        var subnet_mask = ds.getAt(0).get('subnet_mask');
        var equ_name = ds.getAt(0).get('equ_name');
        var equ_station = ds.getAt(0).get('equ_station');
        var int_or_ext = ds.getAt(0).get('int_or_ext');
        var net_station = ds.getAt(0).get('net_station');
        var manufacturer = ds.getAt(0).get('manufacturer');
        var MAC = ds.getAt(0).get('MAC');
        var equ_type = ds.getAt(0).get('equ_type');

        if(is_key_device=='1'){
            Ext.getCmp('is_key_device').setValue('是');
        }else{
            Ext.getCmp('is_key_device').setValue('否');
        }
        if(monitor_used=='Y'){
            Ext.getCmp('monitor_used').setValue('启用');
        }else{
            Ext.getCmp('monitor_used').setValue('不启用');
        }
        if(int_or_ext=='i'){
            Ext.getCmp('int_or_ext').setValue('内部链路');
        }
        if(int_or_ext=='e'){
            Ext.getCmp('int_or_ext').setValue('外部链路');
        }
        if(net_station=='i'){
            Ext.getCmp('net_station').setValue('内网');
        }
        if(net_station=='e'){
            Ext.getCmp('net_station').setValue('内网');
        }


        Ext.getCmp('equ_Icon_code').setValue("<img src=../../img/equ/"+equ_Icon_code+".PNG>");
        Ext.getCmp('equ_info').setValue(equ_info);
        Ext.getCmp('model').setValue(model);
        Ext.getCmp('link_name').setValue(link_name);
        Ext.getCmp('other_ip').setValue(other_ip);
        Ext.getCmp('ip').setValue(ip);
//        Ext.getCmp('id').setValue(id);
        Ext.getCmp('subnet_mask').setValue(subnet_mask);
        Ext.getCmp('equ_name').setValue(equ_name);
        Ext.getCmp('equ_station').setValue(equ_station);
        Ext.getCmp('manufacturer').setValue(manufacturer);
        Ext.getCmp('MAC').setValue(MAC);
        Ext.getCmp('equ_type').setValue(equ_type);
       /* Ext.getCmp('keystore').setValue(keystore);
        Ext.getCmp('password').setValue(password);
        Ext.getCmp('xlsAddress').setValue(xlsAddress);
        Ext.getCmp('caAddress').setValue(caAddress);
        Ext.getCmp('crlAddress').setValue(crlAddress);
        Ext.getCmp('deviceurl').setValue(deviceurl);
        Ext.getCmp('serviceUrl').setValue(serviceUrl);
        Ext.getCmp('blockService').setValue(blockService);
        Ext.getCmp('recoverService').setValue(recoverService);
        Ext.getCmp('snapshotService').setValue(snapshotService);
        Ext.getCmp('syslog').setValue(syslog);*/

    });


   var formPanel = new Ext.form.FormPanel({
        frame:true,
        autoScroll:true,
        labelWidth:150,
        baseCls : 'x-plain',
        labelAlign:'right',
        defaultWidth:300,
        width:800,
               layout:'form',
               border:false,
               defaults:{
                   width:250
               },
               items:[
                   new Ext.form.DisplayField({
                       name:'equ_name',
                       id:'equ_name',
                       fieldLabel:'设备名称'
                   }),
                     new Ext.form.DisplayField({
                       name:'model',
                       id:'model',
                       fieldLabel:'是否核心设备'
                   }),
                     new Ext.form.DisplayField({
                       name:'equ_type',
                       id:'equ_type',
                       fieldLabel:'设备类型'
                   }),
                     new Ext.form.DisplayField({
                       name:'equ_Icon_code',
                       id:'equ_Icon_code',
                       fieldLabel:'设备图标'
                   }),
                     new Ext.form.DisplayField({
                       name:'net_station',
                       id:'net_station',
                       fieldLabel:'网络位置'
                   }),
                     new Ext.form.DisplayField({
                       name:'monitor_used',
                       id:'monitor_used',
                       fieldLabel:'是否开启监控'
                   }),
                     new Ext.form.DisplayField({
                       name:'ip',
                       id:'ip',
                       fieldLabel:'首选IP'
                   }),  new Ext.form.DisplayField({
                       name:'other_ip',
                       id:'other_ip',
                       fieldLabel:'次选IP'
                   }),
                     new Ext.form.DisplayField({
                       name:'MAC',
                       id:'MAC',
                       fieldLabel:'MAC地址'
                   }),
                   new Ext.form.DisplayField({
                       name:'subnet_mask',
                       id:'subnet_mask',
                       fieldLabel:'子网掩码'
                   }),
                   new Ext.form.DisplayField({
                       name:'int_or_ext',
                       id:'int_or_ext',
                       fieldLabel:'链路位置'
                   }),
                   new Ext.form.DisplayField({
                       name:'link_name',
                       id:'link_name',
                       fieldLabel:'链路'
                   }),
                   new Ext.form.DisplayField({
                       name:'equ_station',
                       id:'equ_station',
                       fieldLabel:'设备位置'
                   }),
                   new Ext.form.DisplayField({
                       name:'equ_info',
                       id:'equ_info',
                       fieldLabel:'设备硬件配置'
                   }),
                   new Ext.form.DisplayField({
                       name:'manufacturer',
                       id:'manufacturer',
                       fieldLabel:'制造商'
                   }),
                   new Ext.form.DisplayField({
                       name:'model',
                       id:'model',
                       fieldLabel:'产品型号'
                   }),
                    new Ext.form.DisplayField({
                       name:'provider',
                       id:'provider',
                       fieldLabel:'供应商'
                   }),
                    new Ext.form.DisplayField({
                       name:'equ_phone',
                       id:'equ_phone',
                       fieldLabel:'联系电话'
                   }),
                    new Ext.form.DisplayField({
                       name:'other_phone',
                       id:'other_phone',
                       fieldLabel:'其他联系方式'
                   }),
                    new Ext.form.DisplayField({
                       name:'is_key_device',
                       id:'is_key_device',
                       fieldLabel:'是否是关键设备'
                   })
               ]
           });


           var select_Win = new Ext.Window({
               title:"查看详细",
               width:700,
               autoScroll:true,
               layout:'fit',
               height:600,
               modal:true,
               items:formPanel
           });
           select_Win.show();


}
function viewDetailed2(){
    var  grid_panel = Ext.getCmp("grid.info2");
    var recode = grid_panel.getSelectionModel().getSelected();
    var id=recode.get("deviceId");
     var record1 = new Ext.data.Record.create([
            {name:'equ_Icon_code',			mapping:'equ_Icon_code'},
            {name:'equ_info',			mapping:'equ_info'},
            {name:'model',			mapping:'model'},
            {name:'monitor_used',			mapping:'monitor_used'},
            {name:'link_name',			mapping:'link_name'},
            {name:'other_ip',			mapping:'other_ip'},
            {name:'is_key_device',			mapping:'is_key_device'},
            {name:'ip',			mapping:'ip'},
            {name:'id',			mapping:'id'},
            {name:'subnet_mask',			mapping:'subnet_mask'},
            {name:'equ_name',			mapping:'equ_name'},
            {name:'equ_station',			mapping:'equ_station'},
            {name:'int_or_ext',			mapping:'int_or_ext'},
            {name:'net_station',			mapping:'net_station'},
            {name:'manufacturer',			mapping:'manufacturer'},
            {name:'MAC',			mapping:'MAC'},
            {name:'equ_type',			mapping:'equ_type'}
        ]);
   var ds=new Ext.data.Store(
     {     url:'../../equAction.do?m=getEquipmentByDeviceId&deviceId='+id,
           reader:new Ext.data.JsonReader({
           },record1)});

    ds.load();
    ds.on('load',function(){
        var equ_Icon_code = ds.getAt(0).get('equ_Icon_code');
        var equ_info = ds.getAt(0).get('equ_info');
        var model = ds.getAt(0).get('model');
        var monitor_used = ds.getAt(0).get('monitor_used');
        var link_name = ds.getAt(0).get('link_name');
        var other_ip = ds.getAt(0).get('other_ip');
        var is_key_device = ds.getAt(0).get('is_key_device');
        var ip = ds.getAt(0).get('ip');
        var subnet_mask = ds.getAt(0).get('subnet_mask');
        var equ_name = ds.getAt(0).get('equ_name');
        var equ_station = ds.getAt(0).get('equ_station');
        var int_or_ext = ds.getAt(0).get('int_or_ext');
        var net_station = ds.getAt(0).get('net_station');
        var manufacturer = ds.getAt(0).get('manufacturer');
        var MAC = ds.getAt(0).get('MAC');
        var equ_type = ds.getAt(0).get('equ_type');


        if(is_key_device=='1'){
            Ext.getCmp('is_key_device').setValue('是');
        }else{
            Ext.getCmp('is_key_device').setValue('否');
        }
        if(monitor_used=='Y'){
            Ext.getCmp('monitor_used').setValue('启用');
        }else{
            Ext.getCmp('monitor_used').setValue('不启用');
        }
        if(int_or_ext=='i'){
            Ext.getCmp('int_or_ext').setValue('内部链路');
        }
        if(int_or_ext=='e'){
            Ext.getCmp('int_or_ext').setValue('外部链路');
        }
        if(net_station=='i'){
            Ext.getCmp('net_station').setValue('内网');
        }
        if(net_station=='e'){
            Ext.getCmp('net_station').setValue('内网');
        }


        Ext.getCmp('equ_Icon_code').setValue("<img src=../../img/equ/"+equ_Icon_code+".PNG>");
        Ext.getCmp('equ_info').setValue(equ_info);
        Ext.getCmp('model').setValue(model);
        Ext.getCmp('link_name').setValue(link_name);
        Ext.getCmp('other_ip').setValue(other_ip);
        Ext.getCmp('ip').setValue(ip);
//        Ext.getCmp('id').setValue(id);
        Ext.getCmp('subnet_mask').setValue(subnet_mask);
        Ext.getCmp('equ_name').setValue(equ_name);
        Ext.getCmp('equ_station').setValue(equ_station);
        Ext.getCmp('manufacturer').setValue(manufacturer);
        Ext.getCmp('MAC').setValue(MAC);
        Ext.getCmp('equ_type').setValue(equ_type);
       /* Ext.getCmp('keystore').setValue(keystore);
        Ext.getCmp('password').setValue(password);
        Ext.getCmp('xlsAddress').setValue(xlsAddress);
        Ext.getCmp('caAddress').setValue(caAddress);
        Ext.getCmp('crlAddress').setValue(crlAddress);
        Ext.getCmp('deviceurl').setValue(deviceurl);
        Ext.getCmp('serviceUrl').setValue(serviceUrl);
        Ext.getCmp('blockService').setValue(blockService);
        Ext.getCmp('recoverService').setValue(recoverService);
        Ext.getCmp('snapshotService').setValue(snapshotService);
        Ext.getCmp('syslog').setValue(syslog);*/

    });


   var formPanel = new Ext.form.FormPanel({
        frame:true,
        autoScroll:true,
        labelWidth:150,
        baseCls : 'x-plain',
        labelAlign:'right',
        defaultWidth:300,
        width:800,
               layout:'form',
               border:false,
               defaults:{
                   width:250
               },
               items:[
                   new Ext.form.DisplayField({
                       name:'equ_name',
                       id:'equ_name',
                       fieldLabel:'设备名称'
                   }),
                     new Ext.form.DisplayField({
                       name:'model',
                       id:'model',
                       fieldLabel:'是否核心设备'
                   }),
                     new Ext.form.DisplayField({
                       name:'equ_type',
                       id:'equ_type',
                       fieldLabel:'设备类型'
                   }),
                     new Ext.form.DisplayField({
                       name:'equ_Icon_code',
                       id:'equ_Icon_code',
                       fieldLabel:'设备图标'
                   }),
                     new Ext.form.DisplayField({
                       name:'net_station',
                       id:'net_station',
                       fieldLabel:'网络位置'
                   }),
                     new Ext.form.DisplayField({
                       name:'monitor_used',
                       id:'monitor_used',
                       fieldLabel:'是否开启监控'
                   }),
                     new Ext.form.DisplayField({
                       name:'ip',
                       id:'ip',
                       fieldLabel:'首选IP'
                   }),  new Ext.form.DisplayField({
                       name:'other_ip',
                       id:'other_ip',
                       fieldLabel:'次选IP'
                   }),
                     new Ext.form.DisplayField({
                       name:'MAC',
                       id:'MAC',
                       fieldLabel:'MAC地址'
                   }),
                   new Ext.form.DisplayField({
                       name:'subnet_mask',
                       id:'subnet_mask',
                       fieldLabel:'子网掩码'
                   }),
                   new Ext.form.DisplayField({
                       name:'int_or_ext',
                       id:'int_or_ext',
                       fieldLabel:'链路位置'
                   }),
                   new Ext.form.DisplayField({
                       name:'link_name',
                       id:'link_name',
                       fieldLabel:'链路'
                   }),
                   new Ext.form.DisplayField({
                       name:'equ_station',
                       id:'equ_station',
                       fieldLabel:'设备位置'
                   }),
                   new Ext.form.DisplayField({
                       name:'equ_info',
                       id:'equ_info',
                       fieldLabel:'设备硬件配置'
                   }),
                   new Ext.form.DisplayField({
                       name:'manufacturer',
                       id:'manufacturer',
                       fieldLabel:'制造商'
                   }),
                   new Ext.form.DisplayField({
                       name:'model',
                       id:'model',
                       fieldLabel:'产品型号'
                   }),
                    new Ext.form.DisplayField({
                       name:'provider',
                       id:'provider',
                       fieldLabel:'供应商'
                   }),
                    new Ext.form.DisplayField({
                       name:'equ_phone',
                       id:'equ_phone',
                       fieldLabel:'联系电话'
                   }),
                    new Ext.form.DisplayField({
                       name:'other_phone',
                       id:'other_phone',
                       fieldLabel:'其他联系方式'
                   }),
                    new Ext.form.DisplayField({
                       name:'is_key_device',
                       id:'is_key_device',
                       fieldLabel:'是否是关键设备'
                   })
               ]
           });

           var select_Win = new Ext.Window({
               title:"查看详细",
               width:700,
               autoScroll:true,
               layout:'fit',
               height:600,
               modal:true,
               items:formPanel
           });
           select_Win.show();


}
function viewDetailed1(){
    var  grid_panel = Ext.getCmp("grid.info");
    var recode = grid_panel.getSelectionModel().getSelected();
    var id=recode.get("deviceId");
     var record1 = new Ext.data.Record.create([
            {name:'equ_Icon_code',			mapping:'equ_Icon_code'},
            {name:'equ_info',			mapping:'equ_info'},
            {name:'model',			mapping:'model'},
            {name:'monitor_used',			mapping:'monitor_used'},
            {name:'link_name',			mapping:'link_name'},
            {name:'other_ip',			mapping:'other_ip'},
            {name:'is_key_device',			mapping:'is_key_device'},
            {name:'ip',			mapping:'ip'},
            {name:'id',			mapping:'id'},
            {name:'subnet_mask',			mapping:'subnet_mask'},
            {name:'equ_name',			mapping:'equ_name'},
            {name:'equ_station',			mapping:'equ_station'},
            {name:'int_or_ext',			mapping:'int_or_ext'},
            {name:'net_station',			mapping:'net_station'},
            {name:'manufacturer',			mapping:'manufacturer'},
            {name:'MAC',			mapping:'MAC'},
            {name:'equ_type',			mapping:'equ_type'}
        ]);
   var ds=new Ext.data.Store(
     {     url:'../../equAction.do?m=getEquipmentByDeviceId&deviceId='+id,
           reader:new Ext.data.JsonReader({
           },record1)});

    ds.load();
    ds.on('load',function(){
        var equ_Icon_code = ds.getAt(0).get('equ_Icon_code');
        var equ_info = ds.getAt(0).get('equ_info');
        var model = ds.getAt(0).get('model');
        var monitor_used = ds.getAt(0).get('monitor_used');
        var link_name = ds.getAt(0).get('link_name');
        var other_ip = ds.getAt(0).get('other_ip');
        var is_key_device = ds.getAt(0).get('is_key_device');
        var ip = ds.getAt(0).get('ip');
        var subnet_mask = ds.getAt(0).get('subnet_mask');
        var equ_name = ds.getAt(0).get('equ_name');
        var equ_station = ds.getAt(0).get('equ_station');
        var int_or_ext = ds.getAt(0).get('int_or_ext');
        var net_station = ds.getAt(0).get('net_station');
        var manufacturer = ds.getAt(0).get('manufacturer');
        var MAC = ds.getAt(0).get('MAC');
        var equ_type = ds.getAt(0).get('equ_type');

        if(is_key_device=='1'){
            Ext.getCmp('is_key_device').setValue('是');
        }else{
            Ext.getCmp('is_key_device').setValue('否');
        }
        if(monitor_used=='Y'){
            Ext.getCmp('monitor_used').setValue('启用');
        }else{
            Ext.getCmp('monitor_used').setValue('不启用');
        }
        if(int_or_ext=='i'){
            Ext.getCmp('int_or_ext').setValue('内部链路');
        }
        if(int_or_ext=='e'){
            Ext.getCmp('int_or_ext').setValue('外部链路');
        }
        if(net_station=='i'){
            Ext.getCmp('net_station').setValue('内网');
        }
        if(net_station=='e'){
            Ext.getCmp('net_station').setValue('内网');
        }

        Ext.getCmp('equ_Icon_code').setValue("<img src=../../img/equ/"+equ_Icon_code+".PNG>");
        Ext.getCmp('equ_info').setValue(equ_info);
        Ext.getCmp('model').setValue(model);


        Ext.getCmp('link_name').setValue(link_name);
        Ext.getCmp('other_ip').setValue(other_ip);
        Ext.getCmp('ip').setValue(ip);
//        Ext.getCmp('id').setValue(id);
        Ext.getCmp('subnet_mask').setValue(subnet_mask);
        Ext.getCmp('equ_name').setValue(equ_name);
        Ext.getCmp('equ_station').setValue(equ_station);

        Ext.getCmp('manufacturer').setValue(manufacturer);
        Ext.getCmp('MAC').setValue(MAC);
        Ext.getCmp('equ_type').setValue(equ_type);
       /* Ext.getCmp('keystore').setValue(keystore);
        Ext.getCmp('password').setValue(password);
        Ext.getCmp('xlsAddress').setValue(xlsAddress);
        Ext.getCmp('caAddress').setValue(caAddress);
        Ext.getCmp('crlAddress').setValue(crlAddress);
        Ext.getCmp('deviceurl').setValue(deviceurl);
        Ext.getCmp('serviceUrl').setValue(serviceUrl);
        Ext.getCmp('blockService').setValue(blockService);
        Ext.getCmp('recoverService').setValue(recoverService);
        Ext.getCmp('snapshotService').setValue(snapshotService);
        Ext.getCmp('syslog').setValue(syslog);*/

    });


   var formPanel = new Ext.form.FormPanel({
        frame:true,
        autoScroll:true,
        labelWidth:150,
        baseCls : 'x-plain',
        labelAlign:'right',
        defaultWidth:300,
        width:800,
               layout:'form',
               border:false,
               defaults:{
                   width:250
               },
               items:[
                   new Ext.form.DisplayField({
                       name:'equ_name',
                       id:'equ_name',
                       fieldLabel:'设备名称'
                   }),
                     new Ext.form.DisplayField({
                       name:'model',
                       id:'model',
                       fieldLabel:'是否核心设备'
                   }),
                     new Ext.form.DisplayField({
                       name:'equ_type',
                       id:'equ_type',
                       fieldLabel:'设备类型'
                   }),
                     new Ext.form.DisplayField({
                       name:'equ_Icon_code',
                       id:'equ_Icon_code',
                       fieldLabel:'设备图标'
                   }),
                     new Ext.form.DisplayField({
                       name:'net_station',
                       id:'net_station',
                       fieldLabel:'网络位置'
                   }),
                     new Ext.form.DisplayField({
                       name:'monitor_used',
                       id:'monitor_used',
                       fieldLabel:'是否开启监控'
                   }),
                     new Ext.form.DisplayField({
                       name:'ip',
                       id:'ip',
                       fieldLabel:'首选IP'
                   }),  new Ext.form.DisplayField({
                       name:'other_ip',
                       id:'other_ip',
                       fieldLabel:'次选IP'
                   }),
                     new Ext.form.DisplayField({
                       name:'MAC',
                       id:'MAC',
                       fieldLabel:'MAC地址'
                   }),
                   new Ext.form.DisplayField({
                       name:'subnet_mask',
                       id:'subnet_mask',
                       fieldLabel:'子网掩码'
                   }),
                   new Ext.form.DisplayField({
                       name:'int_or_ext',
                       id:'int_or_ext',
                       fieldLabel:'链路位置'
                   }),
                   new Ext.form.DisplayField({
                       name:'link_name',
                       id:'link_name',
                       fieldLabel:'链路'
                   }),
                   new Ext.form.DisplayField({
                       name:'equ_station',
                       id:'equ_station',
                       fieldLabel:'设备位置'
                   }),
                   new Ext.form.DisplayField({
                       name:'equ_info',
                       id:'equ_info',
                       fieldLabel:'设备硬件配置'
                   }),
                   new Ext.form.DisplayField({
                       name:'manufacturer',
                       id:'manufacturer',
                       fieldLabel:'制造商'
                   }),
                   new Ext.form.DisplayField({
                       name:'model',
                       id:'model',
                       fieldLabel:'产品型号'
                   }),
                    new Ext.form.DisplayField({
                       name:'provider',
                       id:'provider',
                       fieldLabel:'供应商'
                   }),
                    new Ext.form.DisplayField({
                       name:'equ_phone',
                       id:'equ_phone',
                       fieldLabel:'联系电话'
                   }),
                    new Ext.form.DisplayField({
                       name:'other_phone',
                       id:'other_phone',
                       fieldLabel:'其他联系方式'
                   }),
                    new Ext.form.DisplayField({
                       name:'is_key_device',
                       id:'is_key_device',
                       fieldLabel:'是否是关键设备'
                   })
               ]
           });

           var select_Win = new Ext.Window({
               title:"查看详细",
               width:700,
               autoScroll:true,
               layout:'fit',
               height:600,
               modal:true,
               items:formPanel
           });
           select_Win.show();


}





