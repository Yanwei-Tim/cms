/**
 * 设备管理
 */
Ext.onReady(function() {

	Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';

	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';

    var start = 0;
    var pageSize = 15;
    var record = new Ext.data.Record.create([
        {name:'id',			    mapping:'id'},
        {name:'equName',	mapping:'equName'},
        {name:'monitorUsed',     mapping:'monitorUsed'},
        {name:'ip',             	mapping:'ip'},
        {name:'equPort',       	    mapping:'equPort'},
        {name:'model',       	    mapping:'model'},
        {name:'otherIp',          mapping:'otherIp'},
        {name:'isKeyDevice',     mapping:'isKeyDevice'},
        {name:'mac',              mapping:'mac'},
        {name:'subNetMask',      mapping:'subNetMask'},
        {name:'equTypeCode',       mapping:'equTypeCode'},
        {name:'equTypeCodeName',  mapping:'equTypeCodeName'},
        {name:'equTypeName',       mapping:'equTypeName'},
        {name:'equSysConfig',      mapping:'equSysConfig'},
        {name:'equManagerDepart', mapping:'equManagerDepart'},
        {name:'equManagerDepartName', mapping:'equManagerDepartName'},
        {name:'intOrExt',       	mapping:'intOrExt'},
        {name:'manufacturer',       	mapping:'manufacturer'},
        {name:'provider',       	mapping:'provider'},
        {name:'linkName',		    mapping:'linkName'},
        {name:'equOidName',		    mapping:'equOidName'},
        {name:'snmpVer',		    mapping:'snmpVer'},
        {name:'otherPhone',		    mapping:'otherPhone'},
        {name:'equPhone',		    mapping:'equPhone'},
        {name:'auth',		        mapping:'auth'},
        {name:'common',		    mapping:'common'},
        {name:'equInfo',		    mapping:'equInfo'},
        {name:'buyDay',		    mapping:'buyDay',dateFmt:'yyyy-MM-dd HH:mm:ss'},
        {name:'unrepairDay',		    mapping:'unrepairDay',dateFmt:'yyyy-MM-dd HH:mm:ss'},
        {name:'equStation',		    mapping:'equStation'},
        {name:'equSnmpPwd',		    mapping:'equSnmpPwd'},
        {name:'equId',		    mapping:'equId'},
        {name:'topologyStation',		    mapping:'topologyStation'},
        {name:'netStation',		    mapping:'netStation'}
    ]);
    var reader = new Ext.data.JsonReader({
        totalProperty:"amount",
        root:"results",
        id:'id'
    },record);
    var proxy = new Ext.data.HttpProxy({
        url:"../../equConf.do?m=index"
//        reader:reader
    });

    var store = new Ext.data.GroupingStore({
        id:"store.info",
        proxy : proxy,
        reader : reader
    });

//    var boxM = new Ext.grid.CheckboxSelectionModel();   //复选框
    var rowNumber = new Ext.grid.RowNumberer();         //自动 编号
    var colM = new Ext.grid.ColumnModel([
//        boxM,
        rowNumber,
        {header:"设备名",		dataIndex:"equName",  align:'center',sortable:true,menuDisabled:true},
//        {header:"设备类型",	dataIndex:"equipmentTypeName",  align:'center',sortable:true,menuDisabled:true,renderer:show_type},
//        {header:"链路类型",	dataIndex:"linkType",	    align:'center',sortable:true,menuDisabled:true,renderer:show_linkType},
        {header:"链路名",		dataIndex:"linkName",	    align:'center',sortable:true,menuDisabled:true},
        {header : '区域',dataIndex : 'netStation',align : 'center',sortable : false, menuDisabled : true, width : 30, renderer :
            function(value, p, r) {
                return value == 'i' ? '内网' : '外网';
            }
        }, {
            header : '首选IP',
            dataIndex : 'ip',
            align : 'center',
            sortable : false,
            menuDisabled : true,
            width : 50
        },
        {header:'操作标记',	dataIndex:'id',		        align:'center',sortable:true,menuDisabled:true, renderer:show_flag,	width:100}

    ]);

    var page_toolbar = new Ext.PagingToolbar({
        pageSize : pageSize,
        store:store,
        displayInfo:true,
        displayMsg:"显示第{0}条记录到第{1}条记录，一共{2}条",
        emptyMsg:"没有记录",
        beforePageText:"第",
        afterPageText:"页,共{0}页"
    });
    var grid_panel = new Ext.grid.GridPanel({
        id:'grid.info',
        plain:true,
        height:setHeight(),
        width:setWidth(),
        animCollapse:true,
        loadMask:{msg:'正在加载数据，请稍后...'},
        border:false,
        collapsible:false,
        cm:colM,
//        sm:boxM,
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
        tbar:[
            new Ext.Button({
                id:'btnAdd.info',
                text:'新增',
                iconCls:'add',
                handler:function(){
                    insert_win(grid_panel,store);     //连接到 新增 面板
                }
            })
        ],
        view:new Ext.grid.GroupingView({
            forceFit:true,
            groupingTextTpl:'{text}({[values.rs.length]}条记录)'
        }),
        bbar:page_toolbar
    });
    var port = new Ext.Viewport({
        layout:'fit',
        renderTo: Ext.getBody(),
        items:[grid_panel]
    });
    store.load({
        params:{
            start:start,limit:pageSize
        }
    });
});
function setHeight(){
	var h = document.body.clientHeight-8;
	return h;
}

function setWidth(){
    return document.body.clientWidth-8;
}

function show_linkType(value){
    if(value=='e'){
        return '外部链路';
    } else if(value=='i') {
        return '内部链路';
    }
}
function show_netStation(value){
    if(value=='e'){
        return '外网';
    } else if(value=='i') {
        return '内网';
    }
}

function show_type(value,p,r){
    var code = r.get('equipmentTypeCode');
    return  value;   //'<img src="../../img/equ/'+code+'S.PNG" alt="'+value+'" title="'+value+'"/>' +

}

function show_flag(value, p, r){
    var equipmentName = r.get('equipmentName');
    if( equipmentName=='stp'){
        return String
            .format('<a href="javascript:void(0);" onclick="equipmentDetailInfo();return false;" style="color: blue;">详细</a>'
            + '&nbsp;&nbsp;'
            + '<a href="javascript:void(0);" onclick="equipmentUpdatelInfo();return false;" style="color: blue;">修改</a>'
            + '&nbsp;&nbsp;'
            + '<font color="blue">删除</font>');
    }else{
        return String
            .format('<a href="javascript:void(0);" onclick="equipmentDetailInfo();return false;" style="color: blue;">详细</a>'
            + '&nbsp;&nbsp;'
            + '<a href="javascript:void(0);" onclick="equipmentUpdatelInfo();return false;" style="color: blue;">修改</a>'
            + '&nbsp;&nbsp;'
            + '<a id="\''+value+'\'.delete.info" href="javascript:void(0);" onclick="deleteEquipment(\''+value+'\');return false;" style="color: blue;">删除</a>');
    }
}

var netStationStore = new Ext.data.SimpleStore({
    fields:['value','key'],
    data:[['i','内网'],['e','外网']]
});
var topologyStationStore = new Ext.data.SimpleStore({
    fields:['value','key'],
    data:[['1','外网接入区'],['2','安全接入区'],['3','隔离区'],['4','公安信息网']]
});
var intOrExtStore = new Ext.data.SimpleStore({
    fields:['value','key'],
    data:[['i','内部链路'],['e','外部链路']]
});

 var monitorUsedStore = new Ext.data.SimpleStore({
    fields:['value','key'],
    data:[['Y','启用'],['N','不启用']]
});
var snmpVerStore = new Ext.data.SimpleStore({
    fields:['value','key'],
    data:[['v2','v2'],['v3','v3']]
});
  var isKeyDeviceStore = new Ext.data.SimpleStore({
    fields:['value','key'],
    data:[['1','是'],['0','否']]
});
var oidNameStore = new Ext.data.SimpleStore({
    fields:['value','key'],
    data:[
        ['lenovofirewall','lenovofirewall'],
        ['linuxos','linuxos'],
        ['netchinafirewall','netchinafirewall'],
        ['rdaps','rdaps'],
        ['secworld','secworld'],
        ['windowsos','windowsos']
    ]
});


var linkRecord = new Ext.data.Record.create([{name:'value',mapping:'value'}, {name:'key',mapping:'key'}]);
var linkStore = new Ext.data.Store({
    url:"../../equConf.do?m=selectLinkNameKeyValue",
    method:'POST',
    reader:new Ext.data.JsonReader({ totalProperty:'total',root:"results"},linkRecord)
});

var equManagerDepartRecord = new Ext.data.Record.create([{name:'value',mapping:'value'}, {name:'key',mapping:'key'}]);

var comb=[["S","移动警务安全接入系统"],["P","边界安全接入平台"]];
var comboChildJsdw = new Ext.data.JsonStore({
    autoLoad : true,
    url : '',
    storeId : 'comboChildJsdw',
    root : 'results',
    idProperty : 'id',
    fields : ['id', 'name','icon']
});
/**
 * 新增设备
 * @param grid
 * @param store
 */
function insert_win(grid,store){
  /*  var equManagerDepartStore = new Ext.data.Store({
        url:"../../EquipmentAction_selectDepartmentNameKeyValue.action",
        method:'POST',
        reader:new Ext.data.JsonReader({ totalProperty:'total',root:"rows"},equManagerDepartRecord)
    });
    equManagerDepartStore.load();*/
    var equipmentFormPanel = new Ext.form.FormPanel({
        frame:true,
        autoScroll:true,
        fileUpload : true,
        layout:'column',
        border:false,
        items:[{
            plain:true,
            defaultType:'textfield',
            columnWidth :.5,
            labelAlign:'right',
            labelWidth:90,
            border:false,
            layout: 'form',
            defaults:{
                width:200,
                allowBlank:false,
                blankText:'该项不能为空！'
            },
            items:[{
                id:'equName.insert.info',
                fieldLabel:"设备名",
                name:'equName',
                regex:/^.{2,30}$/,
                regexText:'请输入任意2--30个字符',
                emptyText:'请输入任意2--30个字符'
//                listeners:{
//                    blur:function(){
//                        var equipmentName = this.getValue();
//                        var myMask = new Ext.LoadMask(Ext.getBody(),{
//                            msg:'正在校验,请稍后...',
//                            removeMask:true
//                        });
//                        myMask.show();
//                        Ext.Ajax.request({
//                            url : '../../EquipmentAction_checkEquipmentName.action',
//                            params :{equipmentName:equipmentName},
//                            method:'POST',
//                            success : function(r,o) {
//                                var respText = Ext.util.JSON.decode(r.responseText);
//                                var msg = respText.msg;
//                                myMask.hide();
//                                if(msg != 'true'){
//                                    Ext.MessageBox.show({
//                                        title:'信息',
//                                        width:250,
//                                        msg:msg,
//                                        animEl:'equipmentName.insert.info',
//                                        buttons:{'ok':'确定'},
//                                        icon:Ext.MessageBox.INFO,
//                                        closable:false,
//                                        fn:function(e){
//                                            if(e=='ok'){
//                                                Ext.getCmp('equipmentName.insert.info').setValue('');
//                                            }
//                                        }
//                                    });
//                                }
//                            }
//                        });
//                    }
//                }
            },{
                id:'equId.insert.info',
                fieldLabel:"设备Id",
                name:'equId',
                regex:/^[A-Za-z0-9]+$/,
                regexText:'请输入设备Id 仅限英文或数字',
                emptyText:'请输入设备Id 仅限英文或数字'
            },{
                name : '_parentId',
                id : '_parentId',
                fieldLabel : '平台类型',
                xtype : 'combo',
//                width : 300,
                hiddenName : 'parentJsdw',
                valueField : 'id',
                displayField : 'name',
                mode : 'local',
                emptyText : '请选择',
                allowBlank : false,
                store : comb,
                selectOnFocus : true,
                editable : false,
                triggerAction : 'all',
                loadingText : '加载中...',
                listeners : {
                    select : function(combo, record, index) {
                        Ext.getCmp('jsdwChildName').clearValue();
                        if(Ext.getCmp('_parentId').value=="S"){
                            comboChildJsdw.proxy = new Ext.data.HttpProxy({
                                url :'../../equConf.do?m=showEquTypes'
                            });
                            comboChildJsdw.load();
                        }
                        if(Ext.getCmp('_parentId').value=="P"){
                            comboChildJsdw.proxy = new Ext.data.HttpProxy({
                                url :'../../equConf.do?m=showEquType'
                            });
                            comboChildJsdw.load();
                        }
                    }
                }

            }, new Ext.ux.IconCombo({
                fieldLabel:'设备类型',
                name : 'jsdwChildName',
                id : 'jsdwChildName',
                hiddenName : 'equTypeCode',
                valueField : 'id',
                displayField : 'name',
                iconClsField:'icon',
                mode : 'local',
                emptyText : '请选择',
                store : comboChildJsdw,
                triggerAction : 'all',
                loadingText : '加载中...'
            }),{
                id:'isKeyDevice.insert.info',
                fieldLabel:"核心设备",hiddenName:'isKeyDevice',
                xtype:'combo',
                mode:'local',
                emptyText :'--请选择--',
                editable : false,
                typeAhead:true,
                forceSelection: true,
                triggerAction:'all',
                displayField:"key",valueField:"value",
                store:isKeyDeviceStore
            },{
                fieldLabel:"网络位置",
                hiddenName:'netStation',
                xtype:'combo',
                mode:'local',
                emptyText :'--请选择--',
                editable : false,
                typeAhead:true,
                forceSelection: true,
                triggerAction:'all',
                displayField:"key",
                valueField:"value",
                store:netStationStore
            },{
                fieldLabel:'IP地址',
                name:'ip',
                regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
                regexText:'这个不是Ip(例:1.1.1.1)',
                emptyText:'请输入Ip(例:1.1.1.1)'
            },{
                fieldLabel:'端口',
                name:'equPort',
                value:161,
                regex:/^(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]|[1-9])$/,
                regexText:'这个不是端口类型1~65536',
                emptyText:'请输入端口1~65536'
            },{
                fieldLabel:'次选IP地址',
                name:'otherIp',
                allowBlank:true,
//                regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
//                regexText:'这个不是Ip(例:1.1.1.1)',
                emptyText:'请输入Ip(例:1.1.1.1)'
            },{
                fieldLabel:'MAC地址',
                name:'mac',
                regex:/^((([0-9a-fA-F]{2}\-){5}[0-9a-fA-F]{2})|(([0-9a-fA-F]{2}:){5}[0-9a-fA-F]{2}))$/,
                regexText:'这个不是mac地址:0a-45-be-e6-00-aa或者0a:45:be:e6:00:aa',
                emptyText:'请输入MAC地址:0a-45-be-e6-00-aa'
            },{
                fieldLabel:'子网掩码',
                name:'subnetMask',
                regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
                regexText:'这个不是Ip(例:1.1.1.1)',
                emptyText:'请输入Ip(例:1.1.1.1)'
            },{
                id:'equSysConfig',
                fieldLabel:'设备系统配置文件',
                allowBlank:true,
                xtype:'textfield',
                name:'equSysConfig',
                inputType: 'file'
            },{
                id:'intOrExt.insert.info',
                fieldLabel:"链路类型",hiddenName:'inrOrExt',
                xtype:'combo',
                mode:'local',
                emptyText :'--请选择--',
                editable : false,
                typeAhead:true,
                forceSelection: true,
                triggerAction:'all',
                displayField:"key",valueField:"value",
                store:intOrExtStore,
                listeners:{
                    select:function(){
                        var intOrExt = this.getValue();
                        linkStore.load({params:{intOrExt:intOrExt}});
                        linkStore.on('load',function(){
                            var record = linkStore.getAt(0);
                            Ext.getCmp('linkName.insert.info').setValue(record.data.value);
                        });
                    }
                }
            },{
                id :'linkName.insert.info',
                fieldLabel:"链路名",hiddenName:'linkName',
                xtype:'combo',
                mode : 'local',
                emptyText :'--请选择--',
                editable : false,
                typeAhead:true,
                forceSelection: true,
                triggerAction:'all',
                displayField:"key",valueField:"value",
                store : linkStore
            },{
                id:'equStation.insert.info',
                fieldLabel:"设备位置",
                name:'equStation',
                regexText:'请输入任意2--30个字符',
                emptyText:'请输入设备位置'
            },{
                id:'monitorUsed.insert.info',
                fieldLabel:"是否启用监控",hiddenName:'monitorUsed',
                xtype:'combo',
                mode:'local',
                emptyText :'--请选择--',
                editable : false,
                typeAhead:true,
                forceSelection: true,
                triggerAction:'all',
                displayField:"key",valueField:"value",
                store:monitorUsedStore
            }]
        },{
            plain:true,
            border:false,
            columnWidth :.5,
            layout:'form',
            items:[{
                plain:true,
                defaultType:'textfield',
                labelAlign:'right',
                labelWidth:100,
                border:false,
                layout: 'form',
                defaults:{
                    width:200,
                    allowBlank:false,
                    blankText:'该项不能为空！'
                },
                items:[{
                    id:'equInfo.insert.info',
                    fieldLabel:"设备硬件配置",
                    name:'equInfo',
                    regexText:'请输入任意2--30个字符',
                    emptyText:'请输入设备硬件配置'
                },{
                    fieldLabel:"设备管理单位",
                    hiddenName:'equManagerDepart',
                    name:'equManagerDepart',
                    regexText:'请输入任意2--30个字符',
                    emptyText:'请输入设备管理单位'
                },{
                    fieldLabel:"生产厂家",
                    hiddenName:'manufacturer',
                    name:'manufacturer',
                    regex:/^.{2,30}$/,
                    regexText:'请输入任意2--30个字符',
                    emptyText:'请输入设备管理单位'
                },{
                    fieldLabel:"产品型号",
                    hiddenName:'model',
                    name:'model',
                    regex:/^.{2,30}$/,
                    regexText:'请输入任意2--30个字符',
                    emptyText:'请输入产品型号'
                },{
                    fieldLabel:"供货商",
                    hiddenName:'provider',
                    name:'provider',
                    regex:/^.{2,30}$/,
                    regexText:'请输入任意2--30个字符',
                    emptyText:'请输入供货商'
                },{
                    fieldLabel:"联系电话",
                    hiddenName:'equPhone',
                    name:'equPhone',
                    allowBlank:true,
//                    regex:/^.{6,30}$/,
                    regexText:'请输入任意6--30个字符',
                    emptyText:'请输入联系电话'
                },{
                    fieldLabel:"其他联系方式",
                    hiddenName:'otherPhone',
                    name:'otherPhone',
                    allowBlank:true,
                    regexText:'请输入任意2--30个字符',
                    emptyText:'请输入其他联系方式'
                },{
                    fieldLabel : '购买日期',
                    hiddenName:'buyDay',
                    name:'buyDay',
                    allowBlank:true,
                    xtype : 'datetimefield',
                    format : 'Y-m-d H:i:s',
                    width : 200,
                    regex:false
                },{
                    fieldLabel : '过保日期',
                    hiddenName:'unrepairDay',
                    name:'unrepairDay',
                    allowBlank:true,
                    xtype : 'datetimefield',
                    format : 'Y-m-d H:i:s',
                    width : 200
                },{
                    id:'topologyStation.insert.info',
                    fieldLabel:"拓扑图位置",
                    hiddenName:'topologyStation',
                    xtype:'combo',
                    mode:'local',
                    emptyText :'--请选择--',
                    editable : false,
                    typeAhead:true,
                    forceSelection: true,
                    triggerAction:'all',
                    displayField:"key",
                    valueField:"value",
                    store:topologyStationStore
                },{
                    fieldLabel:"OID名称",
                    hiddenName:'equOidName',
                    xtype:'combo',
                    mode:'local',
                    emptyText :'--请选择--',
                    editable : false,
                    typeAhead:true,
                    forceSelection: true,
                    triggerAction:'all',
                    displayField:"key",
                    valueField:"value",
                    store:oidNameStore
                },{
                    fieldLabel:"设备SNMP服务密码(内网设备必填)",
                    hiddenName:'equSnmpPwd',
                    name:'equSnmpPwd',
                    allowBlank:true,
//                    regex:/^.{2,30}$/,
//                    regexText:'请输入任意2--30个字符',
                    emptyText:'请输入设备SNMP服务密码'
                },{
                    fieldLabel:"SNMP类型",hiddenName:'equSnmpver',
                    xtype:'combo',
                    mode:'local',
                    emptyText :'--请选择--',
                    editable : false,
                    typeAhead:true,
                    forceSelection: true,
                    triggerAction:'all',
                    displayField:"key",valueField:"value",
                    store:snmpVerStore,
                    value:'v2',
                    listeners:{
                        select:function(){
                            var value = this.getValue();
                            if(value=='v2'){
//                                Ext.getCmp('password_2.panel.info').hide();
//                                Ext.getCmp('password_2.panel.info').disable();
//                                Ext.getCmp('password.panel.info').enable();
//                                Ext.getCmp('password.panel.info').show();
                            } else if (value=='v3'){
//                                Ext.getCmp('password.panel.info').hide();
//                                Ext.getCmp('password.panel.info').disable();
//                                Ext.getCmp('password_2.panel.info').enable();
//                                Ext.getCmp('password_2.panel.info').show();
                            }
                        }
                    }
                }]
            },{
                id:'password.panel.info',
                plain:true,
                defaultType:'textfield',
                labelAlign:'right',
                labelWidth:100,
                border:false,
                layout: 'form',
                defaults:{
                    width:200,
                    allowBlank:false,
                    blankText:'该项不能为空！'
                },items:[
// {
//                    id:'password.info',
//                    fieldLabel:'只读访问密码',
//                    inputType:'password',
//                    name:'password',
//                    value:'public',
//                    allowBlank:true
//                }
]
            },{
                id:'password_2.panel.info',
                plain:true,
                hidden:true,
                defaultType:'textfield',
                labelAlign:'right',
                labelWidth:100,
                border:false,
                layout: 'form',
                defaults:{
                    width:200,
                    allowBlank:false,
                    blankText:'该项不能为空！'
                },
                items:[{
                    fieldLabel:'授权账号',
                    name:'auth',
                    allowBlank:true
                },{
                    fieldLabel:'授权密码',
                    inputType:'password',
                    name:'authPassword',
                    allowBlank:true
                },{
                    fieldLabel:'通讯账号',
                    name:'common',
                    allowBlank:true
                },{
                    fieldLabel:'通讯密码',
                    inputType:'password',
                    name:'commonPassword',
                    allowBlank:true
                }]
            }]
        }]
    });
    var win = new Ext.Window({
        title:"新增信息",
        width:800,
        layout:'fit',
        height:350,
        modal:true,
        items:equipmentFormPanel,
        bbar:[
            '->',
            {
                id:'insert_win.info',
                text:'保存',
                handler:function(){
                    if (equipmentFormPanel.form.isValid()) {
                        equipmentFormPanel.getForm().submit({
                            clientValidation : true,
                            url :'../../equConf.do?m=add',
                            method:'post',
                            waitTitle :'系统提示',
                            waitMsg :'正在保存,请稍后...',
                            success : function(form,action) {
                                var msg = action.result.msg;
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:300,
                                    msg:msg,
                                    animEl:'insert_win.info',
                                    buttons:{'ok':'确定','no':'取消'},
                                    icon:Ext.MessageBox.INFO,
                                    closable:false,
                                    fn:function(e){
                                        if(e=='ok'){
                                            grid.render();
                                            store.reload();
                                            win.close();
                                        }
                                    }
                                });
                            }
                        });
                    } else {
                        Ext.MessageBox.show({
                            title:'信息',
                            width:200,
                            msg:'请填写完成再提交!',
                            animEl:'insert_win.info',
                            buttons:{'ok':'确定'},
                            icon:Ext.MessageBox.ERROR,
                            closable:false
                        });
                    }
                }
            },{
                text:'关闭',
                handler:function(){
                    win.close();
                }
            }
        ]
    }).show();
}

/**
 * 修改设备
 */
function equipmentUpdatelInfo(){

    var grid = Ext.getCmp('grid.info');
    var store = grid.getStore();
    var selModel = grid.getSelectionModel();
    var formPanel;

    if(selModel.hasSelection()){
        var selections = selModel.getSelections();
        Ext.each(selections,function(item){
            formPanel = new Ext.form.FormPanel({
                frame:true,
                autoScroll:true,
                layout:'column',
                fileUpload : true,
                border:false,
                items:[{
                    plain:true,
                    defaultType:'textfield',
                    columnWidth :.5,
                    labelAlign:'right',
                    labelWidth:100,
                    border:false,
                    layout: 'form',
                    defaults:{
                        width:200,
                        allowBlank:false,
                        blankText:'该项不能为空！'
                    },
                    items:[{
                        xtype:'hidden',
                        name:'id',
                        value:item.data.id
                    },{
                        id:'equName.insert.info',
                        fieldLabel:"设备名",
                        name:'equName',
                        xtype:'textfield',
                        value:item.data.equName,
                        regex:/^.{2,30}$/,
                        regexText:'请输入任意2--30个字符',
                        emptyText:'请输入设备Id 仅限英文或数字'
                    },{
                        id:'equId.insert.info',
                        fieldLabel:"设备Id",
                        name:'equId',
                        xtype:'textfield',
                        value:item.data.equId,
                        regex:/^[A-Za-z0-9]+$/,
                        regexText:'请输入设备Id 仅限英文或数字',
                        emptyText:'请输入设备Id 仅限英文或数字'
                    },new Ext.ux.IconCombo({
                        fieldLabel:'设备类型',
                        hiddenName:'equTypeCode',
                        value:item.data.equTypeCode,
                        emptyText :'--请选择--',
                        store:new Ext.data.SimpleStore({
                            fields:['value','icon','key'],
                            data:[
                                ['1001','x-flag-1001S','防火墙'],
                                ['1002','x-flag-1002S','可信安全网关'],
                                ['1003','x-flag-1003S','入侵检测系统'],
                                ['1004','x-flag-1004S','网络防毒设备'],
                                ['1005','x-flag-1005S','安全隔离设备'],
                                ['1008','x-flag-1008S','AAA服务器'],
                                ['1009','x-flag-1009S','漏洞扫描系统'],
                                ['1011','x-flag-1011S','应用代理服务器'],
                                ['1012','x-flag-1012S','路由器'],
                                ['1013','x-flag-1013S','交换机'],
                                ['1014','x-flag-1014S','认证服务器'],
                                ['1015','x-flag-1015S','串口线'],
                                ['1030','x-flag-1030S','其他安全及网络设备'],
                                ['2000','x-flag-2000S','应用服务器'],
                                ['2001','x-flag-2001S','WEB服务器'],
                                ['2002','x-flag-2002S','FTP服务器'],
                                ['2003','x-flag-2003S','邮件服务器'],
                                ['2004','x-flag-2004S','数据库服务器'],
                                ['2020','x-flag-2020S','其他类型服务器'],
                                ['3000','x-flag-3000S','终端'],
                                ['3001','x-flag-3001S','台式计算机'],
                                ['3002','x-flag-3002S','笔记本电脑'],
                                ['3003','x-flag-3003S','IP音视频终端'],
                                ['3004','x-flag-3004S','手持终端设备'],
                                ['3005','x-flag-3005S','其他终端设备'],
                                ['4000','x-flag-4000S','IPSec VPN网关'],
                                ['4001','x-flag-4001S','SSL VPN网关'] ,
                                ['4002','x-flag-4002S','信接入网关'] ,
                                ['4003','x-flag-4003S','B/S应用代理服务器'] ,
                                ['4004','x-flag-4004S','B/S应用管理服务器'] ,
                                ['4005','x-flag-4005S','网络隔离设备'] ,
                                ['4007','x-flag-4007S','设备证书管理中心'] ,
                                ['4008','x-flag-4008S','鉴别评估管理服务器'] ,
                                ['4009','x-flag-1003S','监控管理探针'] ,
                                ['4010','x-flag-1003S','监控管理与级联服务器']
                            ]
                        }),
                        valueField:'value',
                        iconClsField:'icon',
                        displayField:'key',
                        triggerAction:'all',
                        mode:'local'
                    }),{
                        id:'isKeyDevice.insert.info',
                        fieldLabel:"核心设备",hiddenName:'isKeyDevice',
                        xtype:'combo',
                        mode:'local',
                        emptyText :'--请选择--',
                        value:item.data.isKeyDevice,
                        editable : false,
                        typeAhead:true,
                        forceSelection: true,
                        triggerAction:'all',
                        displayField:"key",valueField:"value",
                        store:isKeyDeviceStore
                    },{
                        fieldLabel:"网络位置",
                        hiddenName:'netStation',
                        xtype:'combo',
                        mode:'local',
                        emptyText :'--请选择--',
                        value:item.data.netStation,
                        editable : false,
                        typeAhead:true,
                        forceSelection: true,
                        triggerAction:'all',
                        displayField:"key",
                        valueField:"value",
                        store:netStationStore
                    },{
                        fieldLabel:'IP地址',
                        name:'ip',
                        value:item.data.ip,
                        regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
                        regexText:'这个不是Ip(例:1.1.1.1)',
                        emptyText:'请输入Ip(例:1.1.1.1)'
                    },{
                        fieldLabel:'端口',
                        name:'equPort',
                        value:item.data.equPort
                    },{
                        fieldLabel:'次选IP地址',
                        name:'otherIp',
                        value:item.data.otherIp,
                        allowBlank:true,
//                        regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
//                        regexText:'这个不是Ip(例:1.1.1.1)',
                        emptyText:'请输入Ip(例:1.1.1.1)'
                    },{
                        fieldLabel:'MAC地址',
                        name:'mac',
                        value:item.data.mac,
                        regex:/^((([0-9a-fA-F]{2}\-){5}[0-9a-fA-F]{2})|(([0-9a-fA-F]{2}:){5}[0-9a-fA-F]{2}))$/,
                        regexText:'这个不是mac地址:0a-45-be-e6-00-aa或者0a:45:be:e6:00:aa',
                        emptyText:'请输入MAC地址:0a-45-be-e6-00-aa'
                    },{
                        fieldLabel:'子网掩码',
                        name:'subnetMask',
                        value:item.data.subNetMask ,
                        regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
                        regexText:'这个不是Ip(例:1.1.1.1)',
                        emptyText:'请输入Ip(例:1.1.1.1)'
                    },{
                        fieldLabel:'设备系统配置文件地址',
                        xtype:'displayfield',
                        value:item.data.equSysConfig=='null'?null:item.data.equSysConfig
                    },{
                        xtype:'hidden',
                        name:'oldEquipmentSysConfig',
                        value:item.data.equtSysConfig=='null'?null:item.data.equSysConfig
                    },{
                        id:'equSysConfig',
                        fieldLabel:'设备系统配置文件',
                        allowBlank:true,
                        xtype:'textfield',
                        name:'equSysConfig',
                        inputType: 'file'
                    },{
                        id:'intOrExt.insert.info',
                        fieldLabel:"链路类型",
                        hiddenName:'inrOrExt',
                        name:'inrOrExt',
                        xtype:'combo',
                        mode:'local',
                        emptyText :'--请选择--',
                        value:item.data.intOrExt,
                        editable : false,
                        typeAhead:true,
                        forceSelection: true,
                        triggerAction:'all',
                        displayField:"key",
                        valueField:"value",
                        store:intOrExtStore,
                        listeners:{
                            select:function(){
                                var intOrExt = this.getValue();
                                linkStore.load({params:{intOrExt:intOrExt}});
                                linkStore.on('load',function(){
                                    var record = linkStore.getAt(0);
                                    Ext.getCmp('linkName.insert.info').setValue(record.data.value);
                                });
                            }
                        }
                    },{
                        id :'linkName.insert.info',
                        fieldLabel:"链路名",
                        hiddenName:'linkName',
                        name:'linkName',
                        xtype:'combo',
                        mode:'remote',
                        emptyText :'--请选择--',
                        value:item.data.linkName,
                        editable : false,
                        typeAhead:true,
                        forceSelection: true,
                        triggerAction:'all',
                        displayField:"key",valueField:"value",
                        store : linkStore
                    },{
                        id:'equStation.insert.info',
                        fieldLabel:"设备位置",
                        name:'equStation',
                        value:item.data.equStation,
                        regexText:'请输入任意2--30个字符',
                        emptyText:'请输入设备位置'
                    },{
                        id:'monitorUsed.insert.info',
                        fieldLabel:"是否启用监控",
                        hiddenName:'monitorUsed',
                        name:'monitorUsed',
                        xtype:'combo',
                        mode:'local',
                        emptyText :'--请选择--',
                        value:item.data.monitorUsed,
                        editable : false,
                        typeAhead:true,
                        forceSelection: true,
                        triggerAction:'all',
                        displayField:"key",valueField:"value",
                        store:monitorUsedStore
                    }]
                },{
                    plain:true,
                    border:false,
                    columnWidth :.5,
                    layout:'form',
                    items:[{
                        plain:true,
                        defaultType:'textfield',
                        labelAlign:'right',
                        labelWidth:100,
                        border:false,
                        layout: 'form',
                        defaults:{
                            width:200,
                            allowBlank:false,
                            blankText:'该项不能为空！'
                        },
                        items:[{
                            id:'equInfo.insert.info',
                            fieldLabel:"设备硬件配置",
                            name:'equInfo',
                            value:item.data.equInfo,
                            regexText:'请输入任意2--30个字符',
                            emptyText:'请输入设备硬件配置'
                        },{
                            fieldLabel:"设备管理单位",
                            hiddenName:'equManagerDepart',
                            name:'equManagerDepart',
                            value:item.data.equManagerDepart,
                            regexText:'请输入任意2--30个字符',
                            emptyText:'请输入设备管理单位'
                        },{
                            fieldLabel:"生产厂家",
                            hiddenName:'manufacturer',
                            name:'manufacturer',
                            value:item.data.manufacturer,
                            regex:/^.{2,30}$/,
                            regexText:'请输入任意2--30个字符',
                            emptyText:'请输入设备管理单位'
                        },{
                            fieldLabel:"产品型号",
                            hiddenName:'model',
                            value:item.data.model,
                            name:'model',
                            regex:/^.{2,30}$/,
                            regexText:'请输入任意2--30个字符',
                            emptyText:'请输入产品型号'
                        },{
                            fieldLabel:"供货商",
                            hiddenName:'provider',
                            value:item.data.provider,
                            name:'provider',
                            regex:/^.{2,30}$/,
                            regexText:'请输入任意2--30个字符',
                            emptyText:'请输入供货商'
                        },{
                            fieldLabel:"联系电话",
                            hiddenName:'equPhone',
                            value:item.data.equPhone,
                            name:'equPhone',
//                            regex:/^.{6,30}$/,
                            allowBlank:true,
                            regexText:'请输入任意6--30个字符',
                            emptyText:'请输入联系电话'
                        },{
                            fieldLabel:"其他联系方式",
                            hiddenName:'otherPhone',
                            value:item.data.otherPhone,
                            allowBlank:true,
                            name:'otherPhone',
                            regexText:'请输入任意2--30个字符',
                            emptyText:'请输入其他联系方式'
                        },{
                            fieldLabel : '购买日期',
                            hiddenName:'buyDay',
                            name:'buyDay',
                            xtype : 'datetimefield',
                            allowBlank:true,
                            value:item.data.buyDay,
                            width : 200
                        },{
                            fieldLabel : '过保日期',
                            hiddenName:'unrepairDay',
                            name:'unrepairDay',
                            xtype : 'datetimefield',
                            allowBlank:true,
                            value:item.data.unrepairDay,
                            width : 200
                        },{
                            id:'topologyStation.insert.info',
                            fieldLabel:"拓扑图位置",hiddenName:'topologyStation',
                            xtype:'combo',
                            mode:'local',
                            emptyText :'--请选择--',
                            value:item.data.topologyStation,
                            editable : false,
                            typeAhead:true,
                            forceSelection: true,
                            triggerAction:'all',
                            displayField:"key",
                            valueField:"value",
                            store:topologyStationStore
                        },{
                            fieldLabel:"OID名称",
                            hiddenName:'equOidName',
                            value:item.data.equOidName,
                            xtype:'combo',
                            mode:'local',
                            emptyText :'--请选择--',
                            editable : false,
                            typeAhead:true,
                            forceSelection: true,
                            triggerAction:'all',
                            displayField:"key",
                            valueField:"value",
                            store:oidNameStore
                        },{
                            fieldLabel:"设备SNMP服务密码(内网设备必填)",
                            hiddenName:'equSnmpPwd',
                            value:item.data.equSnmpPwd,
                            name:'equSnmpPwd',
//                            regex:/^.{2,30}$/,
                            allowBlank:true,
                            regexText:'请输入任意2--30个字符',
                            emptyText:'请输入设备SNMP服务密码'
                        },{
                            fieldLabel:"SNMP类型",
                            hiddenName:'equSnmpver',
                            value:item.data.snmpVer,
                            xtype:'combo',
                            mode:'local',
                            emptyText :'--请选择--',
                            editable : false,
                            typeAhead:true,
                            forceSelection: true,
                            triggerAction:'all',
                            displayField:"key",
                            valueField:"value",
                            store:snmpVerStore,
                            listeners:{
                                select:function(){
                                    var value = this.getValue();
                                    if(value=='v2'){
//                                        Ext.getCmp('password_2.panel.info').hide();
//                                        Ext.getCmp('password.panel.info').show();
                                    } else if (value=='v3'){
//                                        Ext.getCmp('password.panel.info').hide();
//                                        Ext.getCmp('password_2.panel.info').show();
                                    }
                                }
                            }
                        }]
                    },{
                        id:'password.panel.info',
                        plain:true,
                        defaultType:'textfield',
                        labelAlign:'right',
                        labelWidth:100,
                        border:false,
                        layout: 'form',
                        defaults:{
                            width:200,
                            allowBlank:false,
                            blankText:'该项不能为空！'
                        },
                        items:[
//                            {
//                            id:'password.info',
//                            fieldLabel:'只读访问密码',
//                            inputType:'password',
//                            name:'equipment.password',
//                            value:'public',
//                            allowBlank:true
//                        }
                        ]
                    },{
                        id:'password_2.panel.info',
                        plain:true,
                        hidden:true,
                        defaultType:'textfield',
                        labelAlign:'right',
                        labelWidth:100,
                        border:false,
                        layout: 'form',
                        defaults:{
                            width:200,
                            allowBlank:false,
                            blankText:'该项不能为空！'
                        },
                        items:[{
                            id:'auth.update.info',
                            fieldLabel:'授权账号',
                            name:'equipment.auth',
                            value:item.data.auth,
                            allowBlank:true
                        },{
                            fieldLabel:'授权密码',
                            inputType:'password',
                            name:'equipment.authPassword',
                            allowBlank:true
                        },{
                            fieldLabel:'通讯账号',
                            name:'equipment.common',
                            value:item.data.common ,
                            allowBlank:true
                        },{
                            fieldLabel:'通讯密码',
                            inputType:'password',
                            name:'equipment.commonPassword',
                            allowBlank:true
                        }]
                    }]
                }]
            });
        });
    }

    var win = new Ext.Window({
        title:"修改信息",
        width:690,
        layout:'fit',
        height:350,
        modal:true,
        items:formPanel,
        bbar:[
            '->',
            {
                id:'update_win.info',
                text:'修改',
                handler:function(){
                    Ext.MessageBox.show({
                        title:'信息',
                        width:250,
                        msg:'确定要修改?',
                        animEl:'update_win.info',
                        buttons:{'ok':'继续','no':'取消'},
                        icon:Ext.MessageBox.WARNING,
                        closable:false,
                        fn:function(e){
                            if(e == 'ok'){
                                if (formPanel.form.isValid()) {
                                    formPanel.getForm().submit({
                                        url :'../../equConf.do?m=update',
                                        method :'POST',
                                        waitTitle :'系统提示',
                                        waitMsg :'正在修改,请稍后...',
                                        success : function(form,action) {
                                            Ext.MessageBox.show({
                                                title:'信息',
                                                width:300,
                                                msg:action.result.msg,
                                                animEl:'update_win.info',
                                                buttons:{'ok':'确定','no':'取消'},
                                                icon:Ext.MessageBox.INFO,
                                                closable:false,
                                                fn:function(e){
                                                    if(e=='ok'){
                                                        grid.render();
                                                        store.reload();
                                                        win.close();
                                                    }
                                                }
                                            });
                                        }
                                    });
                                } else {
                                    Ext.MessageBox.show({
                                        title:'信息',
                                        width:200,
                                        msg:'请填写完成再提交!',
                                        animEl:'update_win.info',
                                        buttons:{'ok':'确定'},
                                        icon:Ext.MessageBox.ERROR,
                                        closable:false
                                    });
                                }
                            }
                        }
                    });

                }
            },{
                text:'关闭',
                handler:function(){
                    win.close();
                }
            }
        ]
    }).show();
}

/**
 * 删除设备
 */
function deleteEquipment(value){
    var grid = Ext.getCmp('grid.info');
    var store = grid.getStore();
    var selModel = grid.getSelectionModel();
    var equipmentName;
    var linkName;
    if(selModel.hasSelection()){
        var selections = selModel.getSelections();

    }
    Ext.MessageBox.show({
        title:'信息',
        msg:'<font color="green">确定要删除所选记录？</font>',
        animEl:value+'.delete.info',
        width:260,
        buttons:{'ok':'确定','no':'取消'},
        icon:Ext.MessageBox.INFO,
        closable:false,
        fn:function(e){
            if(e == 'ok'){
                var myMask = new Ext.LoadMask(Ext.getBody(),{
                    msg:'正在删除,请稍后...',
                    removeMask:true
                });
                myMask.show();
                Ext.Ajax.request({
                    url : '../../equConf.do?m=delete',             // 删除 连接 到后台
                    params :{ids:value},
                    method:'POST',
                    success : function(r,o){
                        var respText = Ext.util.JSON.decode(r.responseText);
                        var msg = respText.msg;
                        myMask.hide();
                        Ext.MessageBox.show({
                            title:'信息',
                            width:300,
                            msg:msg,
                            buttons:{'ok':'确定'},
                            animEl:value+'.delete.info',
                            icon:Ext.MessageBox.INFO,
                            closable:false,
                            fn:function(e){
                                if(e=='ok'){
                                    grid.render();
                                    store.reload();
                                }
                            }
                        });
                    }
                });
            }
        }
    });
}

function equipmentDetailInfo() {
	var grid = Ext.getCmp('grid.info');
    var store = grid.getStore();
    var selModel = grid.getSelectionModel();
    var formPanel;
    if(selModel.hasSelection()){
        var selections = selModel.getSelections();
        Ext.each(selections,function(item){
            formPanel = new Ext.form.FormPanel({
                frame:true,
                border:false,
                autoScroll:true,
                layout:'column',
                items:[{
                    plain:true,
                    columnWidth :.5,
                    labelAlign:'right',
                    labelWidth:80,
                    border:false,
                    layout: 'form',
                    autoScroll:true,
                    defaultType:'displayfield',
                    defaults : {
                        width : 200,
                        allowBlank : false,
                        blankText : '该项不能为空！'
                    },
                    items:[{
                        fieldLabel:'设备名',
                        value:item.data.equName
                    },{
                        fieldLabel:'设备Id',
                        value:item.data.equId
                    },{
                        fieldLabel:'设备类型',
                        value:'<img src="../../img/equ/'+item.data.equTypeCode+'S.PNG" alt="'+item.data.equTypeCodeName+'" title="'+item.data.equTypeCodeName+'"/>' + item.data.equTypeCodeName
                    },{
                        fieldLabel:'是否核心设备',
                        value:(item.data.isKeyDevice=='1')?'<font color="green">是</font>':'<font color="red">否</font>'
                    },{
                        fieldLabel:'网络位置',
                        value:show_netStation(item.data.netStation)
                    },{
                        fieldLabel:"IP地址",
                        value:item.data.ip
                    },{
                        fieldLabel:"端口",
                        value:item.data.equPort
                    },{
                        fieldLabel:"次选IP",
                        value:item.data.otherIp
                    },{
                        fieldLabel:"MAC地址",
                        value:item.data.mac
                    },{
                        fieldLabel:"子网掩码",
                        value:item.data.subNetMask
                    },{
                        fieldLabel:"设备系统配置文件",
                        value:item.data.equSysConfig=='null'||item.data.equSysConfig==''?'<font color="gray">下载</font>':'<a href="javascript:;" onclick="download(\''+item.data.equSysConfig+'\')">下载</a>'+item.data.equSysConfig
                    },{
                        fieldLabel:'链路类型',
                        value:show_linkType(item.data.intOrExt)
                    },{
                        fieldLabel:'链路名',
                        value:item.data.linkName
                    },{
                        fieldLabel:'设备位置',
                        value:item.data.equStation
                    },{
                        fieldLabel:'是否开启监控',
                        value:(item.data.monitorUsed=='Y')?'<font color="green">是</font>':'<font color="red">否</font>'
                    }]
                },{
                    plain:true,
                    columnWidth :.5,
                    labelAlign:'right',
                    labelWidth:100,
                    border:false,
                    layout: 'form',
                    autoScroll:true,
                    defaultType:'displayfield',
                    defaults : {
                        width : 150,
                        allowBlank : false,
                        blankText : '该项不能为空！'
                    },
                    items:[{
                        fieldLabel:'设备硬件配置',
                        value:item.data.equInfo
                    },{
                        fieldLabel:'设备管理单位',
                        value:item.data.equManagerDepart
                    },{
                        fieldLabel:'生产厂家',
                        value:item.data.manufacturer
                    },{
                        fieldLabel:'产品型号',
                        value:item.data.model
                    },{
                        fieldLabel:'供货商',
                        value:item.data.provider
                    },{
                        fieldLabel:'联系电话',
                        value:item.data.equPhone
                    },{
                        fieldLabel:'其他联系方式',
                        value:item.data.otherPhone
                    },{
                        fieldLabel:'购买日期',
                        value:item.data.buyDay
                    },{
                        fieldLabel:'过保日期',
                        value:item.data.unrepairDay
                    },{
                        fieldLabel:'拓扑图位置',
                        value:topologyStation(item.data.topologyStation)
                    },{
                        fieldLabel:'OID名称',
                        value:item.data.equOidName
                    },{
                        fieldLabel:'snmp服务密码',
                        value:item.data.equSnmpPwd
                    },{
                        fieldLabel:'SNMP类型',
                        value:item.data.snmpVer
                    }]
                }]
            });
        });
    }
    var win = new Ext.Window({
        title:"设备详细信息",
        width:700,
        layout:'fit',
        height:350,
        modal:true,
        items:formPanel,
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

function download(fileName){
//    if (!Ext.fly('test')) {
//        var frm = document.createElement('form');
//        frm.id = 'test';
//        frm.name = id;
//        frm.style.display = 'none';
//        document.body.appendChild(frm);
//    }
//    Ext.Ajax.request({
//        url: '../../EquipmentAction_download.action',
//        params:{fileName:fileName},
//        form: Ext.fly('test'),
//        method: 'POST',
//        isUpload: true
//    });
        window.open('../../equConf.do?m=download&fileName=' + fileName,	'_blank');
}

function topologyStation(value){
    if(value=='1'){
       return '外网接入区';
    } else if(value=='2'){
        return '安全接入区';
    } else if(value=='3'){
        return '隔离区';
    }else if(value=='4'){
        return '公安信息网';
    }
}