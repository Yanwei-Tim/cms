// 业务注册管理
var listGrid;
var listStore ;
Ext.onReady(function() {
    Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';

    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'qtip';

    var listRecord = Ext.data.Record.create(['id', 'businessName','authSerial','authMaterialFileName','isRealtime','isApproved',
        'businessDesc', 'businessDepart', 'businessCode','businessCodeId', 'linkName','authDepart','enablereport', 'dayDataVolume','approvedDepart',
        'businessAdmin', 'registerDate', 'authDate','adminPhone','adminEmail','adminOtherPhone','businessExchModel','businessExchModelId',
        'tp_graph','businessProtocol','protocolDesc','sourceIpRange','destIpRange','sourcePortRange','destPortRange','useDepart',
        'useDepartAddress','useDepartType','useDepartTypeId','useDepartAdminName','useDepartAdminPhone','useDepartAdminEmail','useDepartAdminOhterPhone',
        'terminalAmount','userAmount','exchangeDirection','exchangeDirectionCode','exchProtocol','exchProtocolCode','businessDepartId', 'authDepartId','approvedDepartId','useDepartId'
      ]);

     listStore = new Ext.data.Store({
        storeId : 'listStore',
        url : "../../bizConf.do?m=index",
        reader : new Ext.data.JsonReader({
            totalProperty : 'amount',
            root : "results",
            id : 'id'
        }, listRecord),
        baseParams : {
            p : 1
        }
    });
    listStore.load();
    /*
     * listStore.on('load', function() { if (listStore.data.length == 0) {
     * Ext.Msg.alert('提示', '没有搜索到符合条件的数据！'); } });
     */

    var sm = new Ext.grid.CheckboxSelectionModel();
     listGrid = new Ext.grid.GridPanel({
        id:'listGrid',
        region : 'center',
        border : false,
        store : listStore,
        tbar : [{
            pressed : false,
            text : '新增',
            id : 'add_btn',
            iconCls : 'add',
            handler : function() {
                showAddWindow(listStore,listGrid);
            }
        }, {
            pressed : false,
            text : '删除',
            id : 'delete_btn',
            iconCls : 'delete',
            handler : function() {
                var selectedRows = listGrid.getSelectionModel()
                    .getSelections();
                if (selectedRows.length == 0) {
                    Ext.Msg.alert('警告', '请选中你要删除的行！');
                } else {
                    Ext.Msg.confirm('警告', '确定要删除这些记录吗？', function(btn) {
                        if (btn == 'yes') {
                            var ids = "";
                            for (var i = 0; i < selectedRows.length; i++) {
                                ids += selectedRows[i].data.id + ",";
                            }
                            ids = ids.substring(0, ids.length - 1);
                            Ext.Ajax.request({
                                url : '../../bizConf.do?m=delete',
                                params : {
                                    ids : ids
                                },
                                success : function(response,
                                                   options) {
                                    listStore.reload();
                                }
                            });
                        } else {
                            return false;
                        }
                    });
                }

            }
        }],
        columns : [
            sm, {
                id : 'id',
                header : 'ID',
                dataIndex : 'id',
                width : 24,
                align : 'center',
                sortable : false,
                menuDisabled : true,
                hidden : true
            }, {
                header : '业务系统名称',
                dataIndex : 'businessName',
                align : 'center',
                allowBlank : false,
                regex:/^.{2,100}$/,
                regexText:'请输入任意2--100个字符',
                emptyText:'请输入任意2--100个字符',
                sortable : false,
                menuDisabled : true,
                width : 60
            }, {
                header : '业务系统描述',
                dataIndex : 'businessDesc',
                align : 'center',
                sortable : false,
                allowBlank : false,
                menuDisabled : true,
                width : 60
            }, {
                header : '业务管理部门',
                dataIndex : 'businessDepart',
                align : 'center',
                sortable : false,
                allowBlank : false,
                menuDisabled : true,
                width : 60
            }, {
                header : '业务类型',
                dataIndex : 'businessCode',
                align : 'center',
                sortable : false,
                menuDisabled : true,
                width : 60
            }, {
                header : '链路名',
                dataIndex : 'linkName',
                align : 'center',
                sortable : false,
                menuDisabled : true,
                width : 60
            }, {
                header : '业务主管人',
                dataIndex : 'businessAdmin',
                align : 'center',
                sortable : false,
                menuDisabled : true,
                width : 50
            }, {
                header : '注册时间',
                dataIndex : 'registerDate',
                align : 'center',
                sortable : false,
                menuDisabled : true,

                width : 50
            }, {
                header : '审批时间',
                dataIndex : 'authDate',
                align : 'center',
                sortable : false,
                menuDisabled : true,
                width : 50
            } ,{
                header : '操作',
                align : 'center',
                dataIndex : 'id',
                menuDisabled : true,
                width : 40,
                renderer : function(value, p, r) {
                    return String
                        .format('<a href="javascript:void(0);" onclick="showDetail(listStore,listGrid);return false;">详细</a>'
                        + '&nbsp;&nbsp;'
                        + '<a href="javascript:void(0);" onclick="showupdateWindow(listStore,listGrid);return false;">修改</a>');
                }
            }],
        viewConfig : {
            forceFit : true
        },
        loadMask : {
            msg : '正在加载数据，请稍侯……'
        },
        columnLines : true,
        stripeRows : true,
        sm : sm,
        bbar : {
            xtype : 'paging',
            id : 'pagingbar',
            pageSize : 15,
            store : listStore,
            displayInfo : true,
            displayMsg : '第 {0} 条到 {1} 条，共 {2} 条',
            emptyMsg : '没有搜索到符合条件的数据！'
        }
    });
    var port = new Ext.Viewport({
        layout:'fit',
        width:500,
        renderTo: Ext.getBody(),
        items:[listGrid]
    });

});
var recordRole = new Ext.data.Record.create([{name:'id',mapping:'id'}, {name:'name',mapping:'name'}]);
var readerRole = new Ext.data.JsonReader({ totalProperty:'total',root:"results"},recordRole);
var cbxBusinessCode = new Ext.data.Store({
    url : '../../bizConf.do?m=showBusinessCode',
    reader:readerRole
});
cbxBusinessCode.load();
var cbxBusinessDepart = new Ext.data.Store({
    url : '../../orgcode.do?m=comboParent',
    reader:readerRole
});
cbxBusinessDepart.load();
var cbxlinkName = new Ext.data.Store({
    url : '../../intLinkConf.do?m=showLink',
    reader:readerRole
});
cbxlinkName.load();

var cbxexchangeDirection = new Ext.data.Store({
    url : '../../orgcode.do?m=showExchangeDirection',
    reader:readerRole
});
cbxexchangeDirection.load();

var cbxbusinessExchModel = new Ext.data.Store({
    url : '../../orgcode.do?m=showBusinessExchModel',
    reader:readerRole
});
cbxbusinessExchModel.load();
var cbxexchProtocol = new Ext.data.Store({
    url : '../../orgcode.do?m=showProtocolType',
    reader:readerRole
});
cbxexchProtocol.load();
var cbxuseDepartType = new Ext.data.Store({
    url : '../../orgcode.do?m=showUseDepartType',
    reader:readerRole
});
cbxuseDepartType.load();


function showAddWindow(store,grid) {
    var addform = new Ext.form.FormPanel({
        defaultType:'textfield',
        frame:true,
        labelAlign:'right',
        autoScroll:true,
        labelWidth:150,
        fileUpload:true,
        defaults:{

            width:300,
            allowBlank:false,
            blankText:'该项不能为空！'
        },
        items:[{
            fieldLabel:'业务系统名称',
            name:'businessName',
            id:'businessName',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'
        },{
            fieldLabel:'业务系统描述',
            name:'businessDesc',
            id:'businessDesc',
            allowBlank:false,
            regex: /[^~!`#$%^&*()_+-=?><.,;:'"]/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'
        },{
            name : 'bdt',
            id : 'bdt',
            fieldLabel : '业务管理部门',
            hiddenName:'businessDepart',
            xtype:'combo',
            mode:'local',
            emptyText :'--请选择--',
            editable : false,
            typeAhead:true,
            forceSelection: true,
            triggerAction:'all',
            displayField:"name",valueField:"id",
            store:cbxBusinessDepart

        }, {
            fieldLabel:'业务类型',
            name:'bc',
            id:'bc',
            xtype : 'combo',
            width : 300,
            hiddenName : 'businessCode',
            valueField : 'id',
            displayField : 'name',
            mode : 'local',
            emptyText : '请选择',
            allowBlank : false,
            store : cbxBusinessCode,
            selectOnFocus : true,
            editable : false,
            triggerAction : 'all',
            loadingText : '加载中...'

        },{
            fieldLabel:'链路名',
            name:'ln',
            id:'ln',
            xtype : 'combo',
            width : 300,
            hiddenName : 'linkName',
            valueField : 'name',
            displayField : 'name',
            mode : 'local',
            emptyText : '请选择',
            allowBlank : false,
            store : cbxlinkName,
            selectOnFocus : true,
            editable : false,
            triggerAction : 'all',
            loadingText : '加载中...'

        },{
            fieldLabel:'业务交换方式',
            name:'bem',
            id:'bem',
            xtype : 'combo',
            width : 300,
            hiddenName : 'businessExchModel',
            valueField : 'id',
            displayField : 'name',
            mode : 'local',
            emptyText : '请选择',
            allowBlank : false,
            store : cbxbusinessExchModel,
            selectOnFocus : true,
            editable : false,
            triggerAction : 'all',
            loadingText : '加载中...'
        },{
            fieldLabel:'业务主管人',
            name:'businessAdmin',
            id:'businessAdmin',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'
        },{
            fieldLabel:'业务主管人电话',
            name:'adminPhone',
            id:'adminPhone',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'
        },{
            fieldLabel:'主管人邮件',
            name:'adminEmail',
            id:'adminEmail',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'
        },{
            fieldLabel:'主管人其他联系方式',
            name:'adminOtherPhone',
            id:'adminOtherPhone',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'
        },{
            fieldLabel:'审批部门',
            name:'adpt',
            id:'adpt',
            xtype : 'combo',
            width : 300,
            hiddenName : 'authDepart',
            valueField : 'id',
            displayField : 'name',
            mode : 'local',
            emptyText : '请选择',
            allowBlank : false,
            store : cbxBusinessDepart,
            selectOnFocus : true,
            editable : false,
            triggerAction : 'all',
            loadingText : '加载中...'
        }, {
            fieldLabel:'审批时间',
            name:'auth_date',
            id:'auth_date',
            hiddenName:'auth_date',
            xtype : 'datetimefield',
            format : 'Y-m-d H:i:s',
            width : 200,
            editable : false,
            regex:false

        },{
            fieldLabel:'审批编号',
            name:'authSerial',
            id:'authSerial',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'
        },{
            fieldLabel:'审批材料',
//            name:'auth_material',
            id:'auth_material',
            allowBlank:false,
            inputType: 'file',//文件类型
            blankText : 'File can\'t not empty.'
//            anchor : '100%' // anchor width by percentage
        },{
            fieldLabel:'注册时间',
            name:'register_date',
//            id:'register_date',
            hiddenName:'register_date',
            xtype : 'datetimefield',
            format : 'Y-m-d H:i:s',
            width : 200,
            editable : false,
            regex:false
        },{
            fieldLabel:'交换方向',
            name:'ed',
            id:'ed',
            xtype : 'combo',
            width : 300,
            hiddenName : 'exchangeDirection',
            valueField : 'id',
            displayField : 'name',
            mode : 'local',
            emptyText : '请选择',
            allowBlank : false,
            store : cbxexchangeDirection,
            selectOnFocus : true,
            editable : false,
            triggerAction : 'all',
            loadingText : '加载中...'
        },{
            fieldLabel:'业务交换协议',
            name:'ep',
            id:'ep',
            xtype : 'combo',
            width : 300,
            hiddenName : 'exchProtocol',
            valueField : 'id',
            displayField : 'name',
            mode : 'local',
            emptyText : '请选择',
            allowBlank : false,
            store : cbxexchProtocol,
            selectOnFocus : true,
            editable : false,
            triggerAction : 'all',
            loadingText : '加载中...'
        },{
            fieldLabel:'是否有实时性要求',
            xtype: 'fieldset',
            defaultType: 'radio',
            layout:'column',
            items:[{
                checked:true,
                name:'isRealtime',
                boxLabel:'是',
                inputValue:'true'
            },{
                name:'isRealtime',
                boxLabel:'否',
                inputValue:'flase'
            }]
        },{
            fieldLabel:'日数据交换量（M）',
            name:'dayDataVolume',
            id:'dayDataVolume',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'
        },{
            fieldLabel:'是否备案',
            xtype: 'fieldset',
            defaultType: 'radio',
            layout:'column',
            items:[{
                checked:true,
                name:'isApproved',
                boxLabel:'是',
                inputValue:'true'
            },{
                name:'isApproved',
                boxLabel:'否',
                inputValue:'flase'
            }]
        },{
            fieldLabel:'备案单位',
            name:'ad',
            id:'ad',
            xtype : 'combo',
            width : 300,
            hiddenName : 'approvedDepart',
            valueField : 'id',
            displayField : 'name',
            mode : 'local',
            emptyText : '请选择',
            allowBlank : false,
            store : cbxBusinessDepart,
            selectOnFocus : true,
            editable : false,
            triggerAction : 'all',
            loadingText : '加载中...'
        },{
            fieldLabel:'拓扑图',
            name:'tp_graph',
            id:'tp_graph',
            allowBlank:false,
            xtype:'textfield',
            inputType: 'file',//文件类型
            blankText : 'File can\'t not empty.'
//            anchor : '100%' // anchor width by percentage
        },{
            fieldLabel:'业务协议名',
            name:'businessProtocol',
            id:'businessProtocol',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'
        },{
            fieldLabel:'协议描述',
            name:'protocolDesc',
            id:'protocolDesc',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'
        },{
            fieldLabel:'源IP地址范围',
            name:'sourceIpRange',
            id:'sourceIpRange',
            width:300,
            allowBlank:false,
            blankText:'该项不能为空！',
            regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])||(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])-((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])))$/,
            regexText:'不规范(例):1.1.1.1-1.1.1.9或者1.1.1.5',
            emptyText:'(例):1.1.1.1-1.1.1.9或者1.1.1.5'
        },{
            fieldLabel:'目标IP地址范围',
            name:'destIpRange',
            id:'destIpRange',
            width:300,
            allowBlank:false,
            blankText:'该项不能为空！',
            regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])||(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])-((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])))$/,
            regexText:'不规范(例):1.1.1.1-1.1.1.9或者1.1.1.5',
            emptyText:'(例):1.1.1.1-1.1.1.9或者1.1.1.5'
        },{
            fieldLabel:'源端口范围',
            name:'sourcePortRange',
            id:'sourcePortRange',
            width:300,
            allowBlank:false,
            blankText:'该项不能为空！',
            regex:/^((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])||((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])-(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])))$/,
            regexText:'不规范(例):1-65536或者100',
            emptyText:'(例):1-65536或者100'
        },{
            fieldLabel:'目标端口范围',
            name:'destPortRange',
            id:'destPortRange',
            width:300,
            allowBlank:false,
            blankText:'该项不能为空！',
            regex:/^((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])||((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])-(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])))$/,
            regexText:'不规范(例):1-65536或者100',
            emptyText:'(例):1-65536或者100'
        },{
            fieldLabel:'使用单位',
            name:'ud',
            id:'ud',
            xtype : 'combo',
            width : 300,
            hiddenName : 'useDepart',
            valueField : 'id',
            displayField : 'name',
            mode : 'local',
            emptyText : '请选择',
            allowBlank : false,
            store : cbxBusinessDepart,
            selectOnFocus : true,
            editable : false,
            triggerAction : 'all',
            loadingText : '加载中...'
        },{
            fieldLabel:'使用单位类型',
            name:'udt',
            id:'udt',
            xtype : 'combo',
            width : 300,
            hiddenName : 'useDepartType',
            valueField : 'id',
            displayField : 'name',
            mode : 'local',
            emptyText : '请选择',
            allowBlank : false,
            store : cbxuseDepartType,
            selectOnFocus : true,
            editable : false,
            triggerAction : 'all',
            loadingText : '加载中...'
        },{
            fieldLabel:'使用单位物理地址',
            name:'useDepartAddress',
            id:'useDepartAddress',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'
        },{
            fieldLabel:'使用单位主管人姓名',
            name:'useDepartAdminName',
            id:'useDepartAdminName',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'
        },{
            fieldLabel:'使用单位主管人电话',
            name:'useDepartAdminPhone',
            id:'useDepartAdminPhone',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'
        },{
            fieldLabel:'使用单位主管人邮件',
            name:'useDepartAdminEmail',
            id:'useDepartAdminEmail',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'
        },{
            fieldLabel:'其他联系方式',
            name:'useDepartAdminOhterPhone',
            id:'useDepartAdminOhterPhone',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'
        },{
            fieldLabel:'终端数量',
            name:'terminalAmount',
            id:'terminalAmount',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'
        },{
            fieldLabel:'用户数量',
            name:'userAmount',
            id:'userAmount',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'
        },{
            fieldLabel:'允许上报',
            xtype: 'fieldset',
            defaultType: 'radio',
            layout:'column',
            items:[{
                checked:true,
                name:'enablereport',
                boxLabel:'是',
                inputValue:'true'
            },{
                name:'enablereport',
                boxLabel:'否',
                inputValue:'flase'
            }]

        }


    ]
    });
    var win = new Ext.Window({
        title:"新增信息",
        width:600,
        layout:'fit',
        height:500,
        modal:true,
        items:addform,
//        titleAlign:"center" ,
        bbar:[
          '->',
            {
                id:'insert.win.info',
                text:'保存',
//                align:'centen',
                handler:function(){
                    if (addform.form.isValid()) {
                        addform.getForm().submit({
                            url :'../../bizConf.do?m=addss',
                            method :'POST',
                            waitTitle :'系统提示',
                            waitMsg :'正在连接...',
                            success : function(form,action) {
                                var msg = action.result.msg;
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:250,
                                    msg:msg+',点击返回页面!',
                                    animEl:'insert.win.info',
                                    buttons:{'ok':'确定'},
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
                            },
                            failure : function() {
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:250,
                                    msg:'新增失败,请与管理员联系!',
                                    animEl:'insert.win.info',
                                    buttons:{'ok':'确定'},
                                    icon:Ext.MessageBox.ERROR,
                                    closable:false
                                });
                            }
                        });
                    } else {
                        Ext.MessageBox.show({
                            title:'信息',
                            width:200,
                            msg:'请填写完成再提交!',
                            animEl:'insert.win.info',
                            buttons:{'ok':'确定'},
                            icon:Ext.MessageBox.ERROR,
                            closable:false
                        });
                    }
                }
            },'-',
            {
                text:'关闭',
                handler:function(){
                    win.close();
                }
            }
        ]

    }).show();
};
function showupdateWindow(store,grid) {
    var record=Ext.getCmp("listGrid").getSelectionModel().getSelected();
//    alert(record.get("businessDepart"))
    var updateform = new Ext.form.FormPanel({
        defaultType:'textfield',
        frame:true,
        labelAlign:'right',
        autoScroll:true,
        labelWidth:150,
        fileUpload:true,
        defaults:{
            width:300,
            allowBlank:false,
            blankText:'该项不能为空！'
        },
        items:[
        {
//            fieldLabel:'id',
            name:'id',
            id:'id',
            hidden:true,
//            hiddenField:true,
            hiddenName:'id',
            value:record.get("id")
        },{
            fieldLabel:'业务系统名称',
            name:'businessName',
            id:'businessName',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"',
            value:record.get("businessName")
        },{
            fieldLabel:'业务系统描述',
            name:'businessDesc',
            id:'businessDesc',
            allowBlank:false,
            regex: /[^~!`#$%^&*()_+-=?><.,;:'"]/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'  ,
            value:record.get("businessDesc")
        },{
            name : 'bdt',
            id : 'bdt',
            fieldLabel : '业务管理部门',
            hiddenName:'businessDepart',
            xtype:'combo',
            mode:'local',
            emptyText :'--请选择--',
            editable : false,
            typeAhead:true,
            forceSelection: true,
            triggerAction:'all',
            displayField:"name",valueField:"id",
            store:cbxBusinessDepart,
            value:record.get("businessDepart")

        }, {
            fieldLabel:'业务类型',
            name:'bc',
            id:'bc',
            xtype : 'combo',
            width : 300,
            hiddenName : 'businessCode',
            valueField : 'id',
            displayField : 'name',
            mode : 'local',
            emptyText : '请选择',
            allowBlank : false,
            store : cbxBusinessCode,
            selectOnFocus : true,
            editable : false,
            triggerAction : 'all',
            loadingText : '加载中...'  ,
            value:record.get("businessCode")

        },{
            fieldLabel:'链路名',
            name:'ln',
            id:'ln',
            xtype : 'combo',
            width : 300,
            hiddenName : 'linkName',
            valueField : 'name',
            displayField : 'name',
            mode : 'local',
            emptyText : '请选择',
            allowBlank : false,
            store : cbxlinkName,
            selectOnFocus : true,
            editable : false,
            triggerAction : 'all',
            loadingText : '加载中...'  ,
            value:record.get("linkName")

        },{
            fieldLabel:'业务交换方式',
            name:'bem',
            id:'bem',
            xtype : 'combo',
            width : 300,
            hiddenName : 'businessExchModel',
            valueField : 'id',
            displayField : 'name',
            mode : 'local',
            emptyText : '请选择',
            allowBlank : false,
            store : cbxbusinessExchModel,
            selectOnFocus : true,
            editable : false,
            triggerAction : 'all',
            loadingText : '加载中...'   ,
            value:record.get("businessExchModel")
        },{
            fieldLabel:'业务主管人',
            name:'businessAdmin',
            id:'businessAdmin',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'     ,
            value:record.get("businessAdmin")
        },{
            fieldLabel:'业务主管人电话',
            name:'adminPhone',
            id:'adminPhone',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'    ,
            value:record.get("adminPhone")
        },{
            fieldLabel:'主管人邮件',
            name:'adminEmail',
            id:'adminEmail',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'     ,
            value:record.get("adminEmail")
        },{
            fieldLabel:'主管人其他联系方式',
            name:'adminOtherPhone',
            id:'adminOtherPhone',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'             ,
            value:record.get("adminOtherPhone")
        },{
            fieldLabel:'审批部门',
            name:'adpt',
            id:'adpt',
            xtype : 'combo',
            width : 300,
            hiddenName : 'authDepart',
            valueField : 'id',
            displayField : 'name',
            mode : 'local',
            emptyText : '请选择',
            allowBlank : false,
            store : cbxBusinessDepart,
            selectOnFocus : true,
            editable : false,
            triggerAction : 'all',
            loadingText : '加载中...'          ,
            value:record.get("authDepart")
        }, {
            fieldLabel:'审批时间',
            name:'auth_date',
            id:'auth_date',
            hiddenName:'auth_date',
            xtype : 'datetimefield',
            format : 'Y-m-d H:i:s',
            width : 200,
            editable : false,
            regex:false                        ,
            value:record.get("authDate")

        },{
            fieldLabel:'审批编号',
            name:'authSerial',
            id:'authSerial',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'   ,
            value:record.get("authSerial")
        },{
            fieldLabel:'审批材料',
//            name:'auth_material',
            id:'auth_material',
            allowBlank:false,
            inputType: 'file',//文件类型
            blankText : 'File can\'t not empty.'
//            anchor : '100%' // anchor width by percentage
        },{
            fieldLabel:'注册时间',
            name:'register_date',
//            id:'register_date',
            hiddenName:'register_date',
            xtype : 'datetimefield',
            format : 'Y-m-d H:i:s',
            width : 200,
            editable : false,
            regex:false               ,
            value:record.get("registerDate")
        },{
            fieldLabel:'交换方向',
            name:'ed',
            id:'ed',
            xtype : 'combo',
            width : 300,
            hiddenName : 'exchangeDirection',
            valueField : 'id',
            displayField : 'name',
            mode : 'local',
            emptyText : '请选择',
            allowBlank : false,
            store : cbxexchangeDirection,
            selectOnFocus : true,
            editable : false,
            triggerAction : 'all',
            loadingText : '加载中...'   ,
            value:record.get("exchangeDirection")
        },{
            fieldLabel:'业务交换协议',
            name:'ep',
            id:'ep',
            xtype : 'combo',
            width : 300,
            hiddenName : 'exchProtocol',
            valueField : 'id',
            displayField : 'name',
            mode : 'local',
            emptyText : '请选择',
            allowBlank : false,
            store : cbxexchProtocol,
            selectOnFocus : true,
            editable : false,
            triggerAction : 'all',
            loadingText : '加载中...'    ,
            value:record.get("exchProtocol")
        },{
            fieldLabel:'是否有实时性要求',
            xtype: 'fieldset',
            defaultType: 'radio',
            layout:'column',
            items:[{
                checked:true,
                name:'isRealtime',
                boxLabel:'是',
                inputValue:'true'
            },{
                name:'isRealtime',
                boxLabel:'否',
                inputValue:'flase'
            }]
            ,
            value:record.get("isRealtime")
        },{
            fieldLabel:'日数据交换量（M）',
            name:'dayDataVolume',
            id:'dayDataVolume',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'    ,
            value:record.get("dayDataVolume")
        },{
            fieldLabel:'是否备案',
            xtype: 'fieldset',
            defaultType: 'radio',
            layout:'column',
            value:record.get("dayDataVolume"),
            items:[{
                checked:true,
                name:'isApproved',
                boxLabel:'是',
                inputValue:'true'
            },{
                name:'isApproved',
                boxLabel:'否',
                inputValue:'flase'
            }]
        },{
            fieldLabel:'备案单位',
            name:'ad',
            id:'ad',
            xtype : 'combo',
            width : 300,
            hiddenName : 'approvedDepart',
            valueField : 'id',
            displayField : 'name',
            mode : 'local',
            emptyText : '请选择',
            allowBlank : false,
            store : cbxBusinessDepart,
            selectOnFocus : true,
            editable : false,
            triggerAction : 'all',
            loadingText : '加载中...' ,
            value:record.get("approvedDepart")
        },{
            fieldLabel:'拓扑图',
            id:'tp_graph',
            allowBlank:false,
            xtype:'textfield',
            inputType: 'file',//文件类型
            blankText : 'File can\'t not empty.'
//            anchor : '100%' // anchor width by percentage
        },{
            fieldLabel:'业务协议名',
            name:'businessProtocol',
            id:'businessProtocol',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"' ,
            value:record.get("businessProtocol")
        },{
            fieldLabel:'协议描述',
            name:'protocolDesc',
            id:'protocolDesc',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'  ,
            value:record.get("protocolDesc")
        },{
            fieldLabel:'源IP地址范围',
            name:'sourceIpRange',
            id:'sourceIpRange',
            width:300,
            allowBlank:false,
            blankText:'该项不能为空！',
            regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])||(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])-((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])))$/,
            regexText:'不规范(例):1.1.1.1-1.1.1.9或者1.1.1.5',
            emptyText:'(例):1.1.1.1-1.1.1.9或者1.1.1.5'     ,
            value:record.get("sourceIpRange")
        },{
            fieldLabel:'目标IP地址范围',
            name:'destIpRange',
            id:'destIpRange',
            width:300,
            allowBlank:false,
            blankText:'该项不能为空！',
            regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])||(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])-((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])))$/,
            regexText:'不规范(例):1.1.1.1-1.1.1.9或者1.1.1.5',
            emptyText:'(例):1.1.1.1-1.1.1.9或者1.1.1.5'     ,
            value:record.get("destIpRange")
        },{
            fieldLabel:'源端口范围',
            name:'sourcePortRange',
            id:'sourcePortRange',
            width:300,
            allowBlank:false,
            blankText:'该项不能为空！',
            regex:/^((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])||((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])-(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])))$/,
            regexText:'不规范(例):1-65536或者100',
            emptyText:'(例):1-65536或者100'      ,
            value:record.get("sourcePortRange")
        },{
            fieldLabel:'目标端口范围',
            name:'destPortRange',
            id:'destPortRange',
            width:300,
            allowBlank:false,
            blankText:'该项不能为空！',
            regex:/^((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])||((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])-(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])))$/,
            regexText:'不规范(例):1-65536或者100',
            emptyText:'(例):1-65536或者100'       ,
            value:record.get("destPortRange")
        },{
            fieldLabel:'使用单位',
            name:'ud',
            id:'ud',
            xtype : 'combo',
            width : 300,
            hiddenName : 'useDepart',
            valueField : 'id',
            displayField : 'name',
            mode : 'local',
            emptyText : '请选择',
            allowBlank : false,
            store : cbxBusinessDepart,
            selectOnFocus : true,
            editable : false,
            triggerAction : 'all',
            loadingText : '加载中...'    ,
            value:record.get("useDepart")
        },{
            fieldLabel:'使用单位类型',
            name:'udt',
            id:'udt',
            xtype : 'combo',
            width : 300,
            hiddenName : 'useDepartType',
            valueField : 'id',
            displayField : 'name',
            mode : 'local',
            emptyText : '请选择',
            allowBlank : false,
            store : cbxuseDepartType,
            selectOnFocus : true,
            editable : false,
            triggerAction : 'all',
            loadingText : '加载中...'       ,
            value:record.get("useDepartType")
        },{
            fieldLabel:'使用单位物理地址',
            name:'useDepartAddress',
            id:'useDepartAddress',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'  ,
            value:record.get("useDepartAddress")
        },{
            fieldLabel:'使用单位主管人姓名',
            name:'useDepartAdminName',
            id:'useDepartAdminName',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'  ,
            value:record.get("useDepartAdminName")
        },{
            fieldLabel:'使用单位主管人电话',
            name:'useDepartAdminPhone',
            id:'useDepartAdminPhone',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'   ,
            value:record.get("useDepartAdminPhone")
        },{
            fieldLabel:'使用单位主管人邮件',
            name:'useDepartAdminEmail',
            id:'useDepartAdminEmail',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'  ,
            value:record.get("useDepartAdminEmail")
        },{
            fieldLabel:'其他联系方式',
            name:'useDepartAdminOhterPhone',
            id:'useDepartAdminOhterPhone',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'   ,
            value:record.get("useDepartAdminOhterPhone")
        },{
            fieldLabel:'终端数量',
            name:'terminalAmount',
            id:'terminalAmount',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'    ,
            value:record.get("terminalAmount")
        },{
            fieldLabel:'用户数量',
            name:'userAmount',
            id:'userAmount',
            allowBlank:false,
            regex: /^[^~!@#$%^&*()_+\x22]+/,
            regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
            emptyText:'例:">=200",或者">200"'   ,
            value:record.get("userAmount")
        },{
            fieldLabel:'允许上报',
            xtype: 'fieldset',
            defaultType: 'radio',
            layout:'column',
            items:[{
                checked:true,
                name:'enablereport',
                boxLabel:'是',
                inputValue:'true'
            },{
                name:'enablereport',
                boxLabel:'否',
                inputValue:'flase'
            }]    ,
            value:record.get("enablereport")

        }


        ]
    });
    var win = new Ext.Window({
        title:"修改信息",
        width:600,
        layout:'fit',
        height:500,
        modal:true,
        items:updateform,
        bbar:[
            '->',
            {
                id:'insert.win.info',
                text:'保存',
//                align:'centen',
                handler:function(){
                    if (updateform.form.isValid()) {
                        updateform.getForm().submit({
                            url :'../../bizConf.do?m=updatess',
                            method :'POST',
                            waitTitle :'系统提示',
                            waitMsg :'正在连接...',
                            success : function(form,action) {
                                var msg = action.result.msg;
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:250,
                                    msg:msg+',点击返回页面!',
                                    animEl:'insert.win.info',
                                    buttons:{'ok':'确定'},
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
                            },
                            failure : function() {
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:250,
                                    msg:'新增失败,请与管理员联系!',
                                    animEl:'insert.win.info',
                                    buttons:{'ok':'确定'},
                                    icon:Ext.MessageBox.ERROR,
                                    closable:false
                                });
                            }
                        });
                    } else {
                        Ext.MessageBox.show({
                            title:'信息',
                            width:200,
                            msg:'请填写完成再提交!',
                            animEl:'insert.win.info',
                            buttons:{'ok':'确定'},
                            icon:Ext.MessageBox.ERROR,
                            closable:false
                        });
                    }
                }
            },'-',
            {
                text:'关闭',
                handler:function(){
                    win.close();
                }
            }
        ]

    }).show();
};
function showDetail(store,grid) {
    var record=Ext.getCmp("listGrid").getSelectionModel().getSelected();
    var flag = record.get("isRealtime");
    var bb;
    if(flag){
        bb='是'
    } else{
        bb='否'
    }
    var flag2 = record.get("dayDataVolume") ;
    var cc;
    if(flag2){
        cc='是'
    } else{
        cc='否'
    }
    var flag3 =record.get("enablereport")
    var aa;
    if(flag3){
        aa='是'
    } else{
        aa='否'
    }
    var showform = new Ext.form.FormPanel({
        defaultType:'displayfield',
        frame:true,
        labelAlign:'right',
        autoScroll:true,
        labelWidth:150,
        fileUpload:true,
        defaults:{
            width:300,
            allowBlank:false,
            blankText:'该项不能为空！'
        },
        items:[
            {
//            fieldLabel:'id',
                name:'id',
                id:'id',
                hidden:true,
//            hiddenField:true,
                hiddenName:'id',
                value:record.get("id")
            },{
                fieldLabel:'业务系统名称',
                name:'businessName',
                id:'businessName',
                allowBlank:false,
                regex: /^[^~!@#$%^&*()_+\x22]+/,
                regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
                emptyText:'例:">=200",或者">200"',
                value:record.get("businessName")
            },{
                fieldLabel:'业务系统描述',
                name:'businessDesc',
                id:'businessDesc',
                allowBlank:false,
                regex: /[^~!`#$%^&*()_+-=?><.,;:'"]/,
                regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
                emptyText:'例:">=200",或者">200"'  ,
                value:record.get("businessDesc")
            },{
                name : 'bdt',
                id : 'bdt',
                fieldLabel : '业务管理部门',
                hiddenName:'businessDepart',
                /*xtype:'combo',
                mode:'local',
                emptyText :'--请选择--',
                editable : false,
                typeAhead:true,
                forceSelection: true,
                triggerAction:'all',
                displayField:"name",valueField:"id",
                store:cbxBusinessDepart      ,*/
                value:record.get("businessDepart")

            }, {
                fieldLabel:'业务类型',
                name:'bc',
                id:'bc',
               /* xtype : 'combo',
                width : 300,
                hiddenName : 'businessCode',
                valueField : 'id',
                displayField : 'name',
                mode : 'local',
                emptyText : '请选择',
                allowBlank : false,
                store : cbxBusinessCode,
                selectOnFocus : true,
                editable : false,
                triggerAction : 'all',
                loadingText : '加载中...'  ,*/
                value:record.get("businessCode")

            },{
                fieldLabel:'链路名',
                name:'ln',
                id:'ln',
              /*  xtype : 'combo',
                width : 300,
                hiddenName : 'linkName',
                valueField : 'name',
                displayField : 'name',
                mode : 'local',
                emptyText : '请选择',
                allowBlank : false,
                store : cbxlinkName,
                selectOnFocus : true,
                editable : false,
                triggerAction : 'all',
                loadingText : '加载中...'  ,*/
                value:record.get("linkName")

            },{
                fieldLabel:'业务交换方式',
                name:'bem',
                id:'bem',
                value:record.get("businessExchModel")
            },{
                fieldLabel:'业务主管人',
                name:'businessAdmin',
                id:'businessAdmin',
                allowBlank:false,
                regex: /^[^~!@#$%^&*()_+\x22]+/,
                regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
                emptyText:'例:">=200",或者">200"'     ,
                value:record.get("businessAdmin")
            },{
                fieldLabel:'业务主管人电话',
                name:'adminPhone',
                id:'adminPhone',
                allowBlank:false,
                regex: /^[^~!@#$%^&*()_+\x22]+/,
                regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
                emptyText:'例:">=200",或者">200"'    ,
                value:record.get("adminPhone")
            },{
                fieldLabel:'主管人邮件',
                name:'adminEmail',
                id:'adminEmail',
                allowBlank:false,
                regex: /^[^~!@#$%^&*()_+\x22]+/,
                regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
                emptyText:'例:">=200",或者">200"'     ,
                value:record.get("adminEmail")
            },{
                fieldLabel:'主管人其他联系方式',
                name:'adminOtherPhone',
                id:'adminOtherPhone',
                allowBlank:false,
                regex: /^[^~!@#$%^&*()_+\x22]+/,
                regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
                emptyText:'例:">=200",或者">200"'             ,
                value:record.get("adminOtherPhone")
            },{
                fieldLabel:'审批部门',
                name:'adpt',
                id:'adpt',
                value:record.get("authDepart")
            }, {
                fieldLabel:'审批时间',
                name:'auth_date',
                id:'auth_date',
//                hiddenName:'auth_date',
//                xtype : 'datetimefield',
//                format : 'Y-m-d H:i:s',
//                width : 200,
//                editable : false,
//                regex:false                        ,
                value:record.get("authDate")

            },{
                fieldLabel:'审批编号',
                name:'authSerial',
                id:'authSerial',
                allowBlank:false,
                regex: /^[^~!@#$%^&*()_+\x22]+/,
                regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
                emptyText:'例:">=200",或者">200"'   ,
                value:record.get("authSerial")
            },{
                fieldLabel:'审批材料',
                id:'auth_material',
               value:record.get("auth_material")
            },{
                fieldLabel:'注册时间',
                name:'register_date',
                value:record.get("registerDate")
            },{
                fieldLabel:'交换方向',
                name:'ed',
                id:'ed',
                value:record.get("exchangeDirection")
            },{
                fieldLabel:'业务交换协议',
                name:'ep',
                id:'ep',

                value:record.get("exchProtocol")
            },{
                fieldLabel:'是否有实时性要求',

                value:bb

            },{
                fieldLabel:'日数据交换量（M）',
                name:'dayDataVolume',
                id:'dayDataVolume',
                allowBlank:false,
                regex: /^[^~!@#$%^&*()_+\x22]+/,
                regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
                emptyText:'例:">=200",或者">200"'    ,
                value:record.get("dayDataVolume")
            },{
                fieldLabel:'是否备案',
                value:cc
            },{
                fieldLabel:'备案单位',
                name:'ad',
                id:'ad',
                value:record.get("approvedDepart")
            },{
                fieldLabel:'拓扑图',
                id:'tp_graph',
                name:'tp_graph',
                value:record.get("tp_graph")
            },{
                fieldLabel:'业务协议名',
                name:'businessProtocol',
                id:'businessProtocol',
                allowBlank:false,
                regex: /^[^~!@#$%^&*()_+\x22]+/,
                regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
                emptyText:'例:">=200",或者">200"' ,
                value:record.get("businessProtocol")
            },{
                fieldLabel:'协议描述',
                name:'protocolDesc',
                id:'protocolDesc',
                allowBlank:false,
                regex: /^[^~!@#$%^&*()_+\x22]+/,
                regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
                emptyText:'例:">=200",或者">200"'  ,
                value:record.get("protocolDesc")
            },{
                fieldLabel:'源IP地址范围',
                name:'sourceIpRange',
                id:'sourceIpRange',
                width:300,
                allowBlank:false,
                blankText:'该项不能为空！',
                regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])||(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])-((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])))$/,
                regexText:'不规范(例):1.1.1.1-1.1.1.9或者1.1.1.5',
                emptyText:'(例):1.1.1.1-1.1.1.9或者1.1.1.5'     ,
                value:record.get("sourceIpRange")
            },{
                fieldLabel:'目标IP地址范围',
                name:'destIpRange',
                id:'destIpRange',
                width:300,
                allowBlank:false,
                blankText:'该项不能为空！',
                regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])||(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])-((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])))$/,
                regexText:'不规范(例):1.1.1.1-1.1.1.9或者1.1.1.5',
                emptyText:'(例):1.1.1.1-1.1.1.9或者1.1.1.5'     ,
                value:record.get("destIpRange")
            },{
                fieldLabel:'源端口范围',
                name:'sourcePortRange',
                id:'sourcePortRange',
                width:300,
                allowBlank:false,
                blankText:'该项不能为空！',
                regex:/^((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])||((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])-(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])))$/,
                regexText:'不规范(例):1-65536或者100',
                emptyText:'(例):1-65536或者100'      ,
                value:record.get("sourcePortRange")
            },{
                fieldLabel:'目标端口范围',
                name:'destPortRange',
                id:'destPortRange',
                width:300,
                allowBlank:false,
                blankText:'该项不能为空！',
                regex:/^((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])||((6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])-(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])))$/,
                regexText:'不规范(例):1-65536或者100',
                emptyText:'(例):1-65536或者100'       ,
                value:record.get("destPortRange")
            },{
                fieldLabel:'使用单位',
                name:'ud',
                id:'ud',
                value:record.get("useDepart")
            },{
                fieldLabel:'使用单位类型',
                name:'udt',
                id:'udt',
                value:record.get("useDepartType")
            },{
                fieldLabel:'使用单位物理地址',
                name:'useDepartAddress',
                id:'useDepartAddress',
                allowBlank:false,
                regex: /^[^~!@#$%^&*()_+\x22]+/,
                regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
                emptyText:'例:">=200",或者">200"'  ,
                value:record.get("useDepartAddress")
            },{
                fieldLabel:'使用单位主管人姓名',
                name:'useDepartAdminName',
                id:'useDepartAdminName',
                allowBlank:false,
                regex: /^[^~!@#$%^&*()_+\x22]+/,
                regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
                emptyText:'例:">=200",或者">200"'  ,
                value:record.get("useDepartAdminName")
            },{
                fieldLabel:'使用单位主管人电话',
                name:'useDepartAdminPhone',
                id:'useDepartAdminPhone',
                allowBlank:false,
                regex: /^[^~!@#$%^&*()_+\x22]+/,
                regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
                emptyText:'例:">=200",或者">200"'   ,
                value:record.get("useDepartAdminPhone")
            },{
                fieldLabel:'使用单位主管人邮件',
                name:'useDepartAdminEmail',
                id:'useDepartAdminEmail',
                allowBlank:false,
                regex: /^[^~!@#$%^&*()_+\x22]+/,
                regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
                emptyText:'例:">=200",或者">200"'  ,
                value:record.get("useDepartAdminEmail")
            },{
                fieldLabel:'其他联系方式',
                name:'useDepartAdminOhterPhone',
                id:'useDepartAdminOhterPhone',
                allowBlank:false,
                regex: /^[^~!@#$%^&*()_+\x22]+/,
                regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
                emptyText:'例:">=200",或者">200"'   ,
                value:record.get("useDepartAdminOhterPhone")
            },{
                fieldLabel:'终端数量',
                name:'terminalAmount',
                id:'terminalAmount',
                allowBlank:false,
                regex: /^[^~!@#$%^&*()_+\x22]+/,
                regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
                emptyText:'例:">=200",或者">200"'    ,
                value:record.get("terminalAmount")
            },{
                fieldLabel:'用户数量',
                name:'userAmount',
                id:'userAmount',
                allowBlank:false,
                regex: /^[^~!@#$%^&*()_+\x22]+/,
                regexText:'>=N(当天违规次数),或者>N(当天违规次数)',
                emptyText:'例:">=200",或者">200"'   ,
                value:record.get("userAmount")
            },{
                fieldLabel:'允许上报',

                value:aa

            }


        ]
    });
    var win = new Ext.Window({
        title:" 查看详细信息",
        width:600,
        layout:'fit',
        height:500,
        modal:true,
        items:showform,
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
};
