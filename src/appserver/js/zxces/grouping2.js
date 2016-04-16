function show_flag4(value,p,r){
//    alert(r.get('createtime'));
          return String.format( '<a href="javascript:void(0);" onclick="viewDetailed4();"><font color="#fa8072">查看设备</font></a> &nbsp; &nbsp;'+
        '<a href="javascript:void(0);" onclick="selectLog(\''+r.get("createtime")+'\',\''+r.get("deviceName")+'\');"><font color="#fa8072">查看日志</font></a> &nbsp; &nbsp;');
}
Ext.onReady(function(){

    Ext.QuickTips.init();

    var xg = Ext.grid;

//===============第一种方式获取JSON数据================================//
   /*  var start = 0;
    var pageSize = 15;
		var reader=new Ext.data.JsonReader({
				root:'rows',
				totalProperty:'totalCount',
				fields:['instructions_no','unit_name','category_name','request_title',
						'request_context','require_completion_time','action_name','remark'
					]
			});

	var store=new Ext.data.GroupingStore({
			id:'GroupStore',
			reader: reader,
			remoteSort:true,
            sortInfo:{field: 'instructions_no', direction: 'ASC'},
            groupField:'instructions_no',
			proxy:new Ext.data.HttpProxy({
				url:'../../equStatus.do?m=selectByTime_1',
				autoAbort:true,
				disableCaching:true,
				timeout:180000,
				method:'POST'
			})
        });
		store.load({
            params:{
                start:start,limit:pageSize
            }
    });
*/
//========================================================================//
/*
// 字母排序
Ext.override(Ext.data.GroupingStore, {
            applySort: function () {
            if (this.sortInfo && !this.remoteSort) {
                var s = this.sortInfo;        var f = s.field;
                var st = this.fields.get(f).sortType;
                var fn = function (r1, r2) {
                    var v1 = st(r1.data[f]);
                    var v2 = st(r2.data[f]);
                    if (typeof(v1) == "string") {
                        return v1.localeCompare(v2);
                    }
                    return v1 > v2 ? 1 : (v1 < v2 ? -1 : 0);
                };             this.data.sort(s.direction, fn);
                if (this.snapshot && this.snapshot != this.data) {
                    this.snapshot.sort(s.direction, fn);
                }
            }
            }
    });*/
//===============第二种方式获取JSON数据================================//
    var start = 0;
    var pageSize = 15;
	var store=new Ext.data.GroupingStore({
			url:'../../equStatus.do?m=selectByTime',
			reader: new Ext.data.JsonReader({
				root:'rows',
				totalProperty:'total',
				remoteSort:true,
				fields:[
					{name:'id'},
                    {name:'deviceId'},
                    {name:'deviceName'},
                    {name:'ip'},
                    {name:'status'},
                    {name:'createtime'},
                    {name:'type'}
					]
			}),
//            sortInfo:{field: 'id', direction: 'ASC'},
            sortInfo:{field: 'id', direction: 'DESC'},
            groupField:'type'
        });
		store.load(
           {
        params:{
            start:start,limit:pageSize
        }
    }
        );
//====================================================================//
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
//                        editable : false,
//						style : 'padding-left:0px;',
//						anchor : '95%,95%',
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
//            value : 'value',
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
                }
            }
        }]
    });
    var grid = new xg.GridPanel({
        id:'grid.info',
        store: store,
        tbar:[tb] ,
        columns: [
				new Ext.grid.RowNumberer(),//获得行号
				{header:"id",		dataIndex:"id",width :50, align:'center',sortable:true,menuDisabled:true,hidden:true},
				{header:"设备名称",		dataIndex:"deviceName",width :50, align:'center',sortable:true,menuDisabled:true},
                {header:"ip",		dataIndex:"ip",	width : 50,  align:'center',sortable:true,menuDisabled:true},
                {header:'网络状态',		    dataIndex:'status',	width:30 , align:'center',sortable:true,menuDisabled:true,renderer : function(value) {
                    if (value == '1') {
                        return '<img src="../../img/icon/ok.png" alt="正常" title="正常" />';
                    }  else {
                        return '<img src="../../img/icon/off.gif" alt="异常" title="异常"/>';
                    }
                }},
                {header:'事件时间',dataIndex:'createtime',width:80 ,   align:'center',sortable:true,menuDisabled:true},
                {header:'类型',dataIndex:'type',width:80 ,   align:'center',sortable:true,menuDisabled:true,hidden:true,renderer : function(value) {
                    if (value == '0') {
                        return '外网接入区';
                    }
                    if (value == '1') {
                        return '安全接入区';
                    }
                    if (value == '2') {
                        return '隔离区';
                    }
                    if (value == '3') {
                        return '公安信息网';
                    }
                }},
                {header:'操作标记',dataIndex:'deviceId', align:'center',sortable:true,menuDisabled:true, renderer:show_flag4,	width:100}
        ],

        view: new Ext.grid.GroupingView({
            showGroupName : false,
            forceFit:true,
            groupTextTpl: '{text} ({[values.rs.length]} {[values.rs.length > 1 ? "条数据" : "条数据"]})'
        }),


        layout:'fit',
        height: 800 ,
        autoScroll:true,
        collapsible: true,
        animCollapse: false,
        title: '事件回查',
        iconCls: 'icon-grid',
        renderTo: Ext.getBody()
    });
     new Ext.Viewport({
        renderTo:Ext.getBody(),
        autoScroll:true,
        items:[{
            items:[grid]
        }]
    });
});
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
            {header:"设备名称",			dataIndex:"Equipment_name",  align:'center',sortable:true,menuDisabled:true,width:80},
            {header:"日志内容",			dataIndex:"Log_info",  align:'center',sortable:true,menuDisabled:true,width:600},
            {header:"链路名称",			dataIndex:"Link_name",  align:'center',sortable:true,menuDisabled:true,width:80},
//            {header:"Link_property",			dataIndex:"Link_property",  align:'center',sortable:true,menuDisabled:true,width:200},
//            {header:"链路安全",			dataIndex:"Link_security",  align:'center',sortable:true,menuDisabled:true,width:200},
//            {header:"Link_Corp",			dataIndex:"Link_Corp",  align:'center',sortable:true,menuDisabled:true,width:200},
            {header:"日志时间",			dataIndex:"Log_time",  align:'center',sortable:true,menuDisabled:true,width:160},
            {header:"日志等级",			dataIndex:"Level",  align:'center',sortable:true,menuDisabled:true,width:80}
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
//        id:'grid.info',
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
        ,
        bbar:page_toolbar
    });
    var win = new Ext.Window({
        title:"查看日志",
        width:1000,
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
function viewDetailed4(){
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
            {name:'equId',			mapping:'equId'},
            {name:'MAC',			mapping:'MAC'},
            {name:'equSysConfig',			mapping:'equSysConfig'},
            {name:'equPort',			mapping:'equPort'},
            {name:'equOidName',			mapping:'equOidName'},
            {name:'snmpVer',			mapping:'snmpVer'},
            {name:'equSnmpPwd',			mapping:'equSnmpPwd'},
            {name:'topologyStation',			mapping:'topologyStation'},
            {name:'equ_phone',			mapping:'equ_phone'},
            {name:'other_phone',			mapping:'other_phone'},
            {name:'provider',			mapping:'provider'},
            {name:'equ_type',			mapping:'equ_type'}
        ]);
   var ds=new Ext.data.Store(
     {     url:'../../equAction.do?m=getEquipmentByDeviceId&deviceId='+id,
           reader:new Ext.data.JsonReader({
           },record1)});

    ds.load();
    ds.on('load',function(){
        var equId =   ds.getAt(0).get('equId');
        var equ_Icon_code = ds.getAt(0).get('equ_Icon_code');
        var equ_info = ds.getAt(0).get('equ_info');
        var model = ds.getAt(0).get('model');
        var equ_phone = ds.getAt(0).get('equ_phone');
        var other_phone = ds.getAt(0).get('other_phone');
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
        var equSysConfig = ds.getAt(0).get('equSysConfig');
        var equSnmpPwd = ds.getAt(0).get('equSnmpPwd');
        var snmpVer = ds.getAt(0).get('snmpVer');
        var equOidName = ds.getAt(0).get('equOidName');
        var equPort = ds.getAt(0).get('equPort');
        var topologyStation = ds.getAt(0).get('topologyStation');
        var provider = ds.getAt(0).get('provider');



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
            Ext.getCmp('net_station').setValue('内网')
        }

        if(topologyStation=='1'){
            Ext.getCmp('topologyStation').setValue('外网接入区');
        } else if(topologyStation=='2'){
            Ext.getCmp('topologyStation').setValue('安全接入区');
        } else if(topologyStation=='3'){
            Ext.getCmp('topologyStation').setValue('隔离区');
        }else if(topologyStation=='4'){
            Ext.getCmp('topologyStation').setValue('公安信息网');
        }



        Ext.getCmp('equ_Icon_code').setValue("<img src=../../img/equ/"+equ_Icon_code+".PNG>");
        Ext.getCmp('equ_info').setValue(equ_info);
        Ext.getCmp('equSysConfig').setValue(equSysConfig=='null'||equSysConfig==''?'<font color="gray">下载</font>':'<a href="javascript:;" onclick="download(\''+equSysConfig+'\')">下载</a>'+equSysConfig);
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
        Ext.getCmp('equId').setValue(equId);
        Ext.getCmp('equSnmpPwd').setValue(equSnmpPwd);
        Ext.getCmp('snmpVer').setValue(snmpVer);
        Ext.getCmp('equOidName').setValue(equOidName);
        Ext.getCmp('equPort').setValue(equPort);
        Ext.getCmp('equ_phone').setValue(equ_phone);
        Ext.getCmp('other_phone').setValue(other_phone);
        Ext.getCmp('provider').setValue(provider);

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
        autoScroll:true,
        width:700,
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
                       name:'equId',
                       id:'equId',
                       fieldLabel:'设备Id'
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
                       name:'equSysConfig',
                       id:'equSysConfig',
                       fieldLabel:'设备系统配置文件'
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
                   }),
                   new Ext.form.DisplayField({
                       name:'equPort',
                       id:'equPort',
                       fieldLabel:'设备端口号'
                   }),
                   new Ext.form.DisplayField({
                       name:'equOidName',
                       id:'equOidName',
                       fieldLabel:'设备OID名称'
                   }),
                   new Ext.form.DisplayField({
                       name:'snmpVer',
                       id:'snmpVer',
                       fieldLabel:'设备SNMP类型'
                   }),
                   new Ext.form.DisplayField({
                       name:'equSnmpPwd',
                       id:'equSnmpPwd',
                       fieldLabel:'设备SNMP密码'
                   }),
                   new Ext.form.DisplayField({
                       name:'topologyStation',
                       id:'topologyStation',
                       fieldLabel:'拓扑图位置'
                   })
               ]
           });

           var select_Win = new Ext.Window({
               title:"查看详细",
               width:500,
               autoScroll:true,
               layout:'fit',
               height:350,
               modal:true,
               items:formPanel
           });
           select_Win.show();
}
function setHeight(){
    var h = document.body.clientHeight-20;
    return h;
}

function setWidth(){
    return document.body.clientWidth-8;
}

function download(fileName){
    window.open('../../equConf.do?m=download&fileName=' + fileName,	'_blank');
}