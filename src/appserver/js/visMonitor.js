var listGrid;var editWindow;var listStore;
Ext.onReady(function(){
    Ext.BLANK_IMAGE_URL="js/ext/resources/images/default/s.gif";
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget="qtip";
    var c=Ext.data.Record.create(["id","name","linkName","exchModel","dqspbfls","dqttl","recordCount","dataVolume","alertCount","linkCount","responseTime","runStatus"]);
    listStore=new Ext.data.Store({
        storeId:"listStore",
        url:"../../runMonitor.do?m=visMonitor",
        reader:new Ext.data.JsonReader({
            totalProperty:"amount",
            root:"results",
            id:"id"},c),
        baseParams:{p:1}
    });
//    listStore.on("load",function(){if(listStore.data.length==0){Ext.Msg.alert("提示","没有搜索到符合条件的数据！")}});
    listGrid=new Ext.grid.GridPanel({
        region:"center",border:false,
        store:listStore,
        columns:[new Ext.grid.RowNumberer({header:"",dataIndex:"id",width:24,align:"center",hidden:true}),
            {header:"业务名",dataIndex:"name",align:"center",sortable:false,menuDisabled:true,width:60},
//            {header:"接入对象",dataIndex:"linkName",align:"center",sortable:false,menuDisabled:true,width:60},
            {header:"业务操作方式",dataIndex:"exchModel",align:"center",sortable:false,menuDisabled:true,width:40},
//            {header:"当前视频并发路数",dataIndex:"dqspbfls",align:"center",sortable:false,menuDisabled:true,width:40},
//            {header:"当前吞吐量",dataIndex:"dqttl",align:"center",sortable:false,menuDisabled:true,width:30},
            {header:"总记录/请求数",dataIndex:"recordCount",align:"center",sortable:false,menuDisabled:true,width:50},
            {header:"当前吞吐量（byte）",dataIndex:"dataVolume",align:"center",sortable:false,menuDisabled:true,width:50},
            {header:"总报警次数",dataIndex:"alertCount",align:"center",sortable:false,menuDisabled:true,width:50},
            {header:"当前视频并发路数",dataIndex:"linkCount",align:"center",sortable:false,menuDisabled:true,width:50},
            {header:"响应时间（毫秒）",dataIndex:"responseTime",align:"center",sortable:false,menuDisabled:true,width:40},
            {header : '运行状态',	dataIndex : 'runStatus',	align : 'center',	sortable : false,	menuDisabled : true,	width : 50,
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
            },
            {header:"操作",align:"center",dataIndex:"id",menuDisabled:true,width:60,
                renderer:function(e,f,d){
                    return String.format('<a href="javascript:void(0);" onclick="showDetail();return false;">详细</a>&nbsp;&nbsp;<a href="javascript:void(0);"onclick="showStat();return false;">统计</a>     <a href="javascript:void(0);"onclick="lookmodel();return false;">XX</a>')}
            }
        ],
        viewConfig:{forceFit:true},loadMask:{msg:"正在加载数据，请稍侯……"},stripeRows:true,sm:new Ext.grid.RowSelectionModel({singleSelect:true}),bbar:{xtype:"paging",id:"pagingbar",pageSize:5,store:listStore,displayInfo:true,displayMsg:"第 {0} 条到 {1} 条，共 {2} 条",emptyMsg:"没有搜索到符合条件的数据！"}
    });
    editWindow=new Ext.Window({
        width:450,height:400,modal:true,
        closeAction:"hide",autoScroll:true,
        buttons:[{xtype:"tbfill"},{text:"关闭",handler:function(){editWindow.hide()}}]
    });
    var a=new Ext.Viewport({layout:"border",border:false,items:[listGrid]});
    listStore.load({params:{start:0,limit:5}});
    var b={run:function(){listStore.reload()},interval:30000};Ext.TaskMgr.start(b)


    var myform;
    function createLookform() {
        myform = new Ext.form.FormPanel({
            labelWidth:150,
            //renderTo : "formt",
            height:400,
            frame : true ,
            defaultType : 'textfield' ,
            buttonAlign : 'right' ,
            labelAlign : 'right' ,
            baseParams : {create : true },
            //  labelWidth : 70 ,
            items : [
                {
                    fieldLabel : '业务名' ,
                    name : 'name',
                    id: 'name',
                    width:163,
                    xtype: 'displayfield'
                },{
                    fieldLabel : '业务操作方式' ,
                    name : 'exchModel',
                    id: 'exchModel',
                    width:163,
                    xtype: 'displayfield'
                },{
                    fieldLabel : '当前视频并发路数' ,
                    name : 'dqspbfls',
                    id: 'dqspbfls',
                    width:163,
                    xtype: 'displayfield'
                },{
                    fieldLabel : '当前吞吐量' ,
                    name : 'dqttl',
                    id: 'dqttl',
                    width:163,
                    xtype: 'displayfield'
                },{
                    fieldLabel : '总记录/请求数' ,
                    name : 'recordCount',
                    id: 'recordCount',
                    width:163,
                    xtype: 'displayfield'
                },{
                    fieldLabel : '总流量(M)' ,
                    name : 'dataVolume',
                    id: 'dataVolume',
                    width:163,
                    xtype: 'displayfield'
                },{
                    fieldLabel : '总报警次数' ,
                    name : 'alertCount',
                    id: 'alertCount',
                    width:163,
                    xtype: 'displayfield'
                },{
                    fieldLabel : '连接数' ,
                    name : 'linkCount',
                    id: 'linkCount',
                    width:163,
                    xtype: 'displayfield'
                },{
                    fieldLabel : '响应时间(毫秒)' ,
                    name : 'responseTime',
                    id: 'responseTime',
                    width:163,
                    xtype: 'displayfield'
                },{
                    fieldLabel : '运行状态' ,
                    name : 'runStatus',
                    id: 'runStatus',
                    width:163,
                    xtype: 'displayfield'
                }
            ]
        });
    }
    Model.lookxx = function lookxx(){
        createLookform();
        Ext.getCmp("name").setValue(listGrid.getSelectionModel().getSelections()[0].get("name"));
        Ext.getCmp("exchModel").setValue(listGrid.getSelectionModel().getSelections()[0].get("exchModel"));
        Ext.getCmp("dqspbfls").setValue(listGrid.getSelectionModel().getSelections()[0].get("dqspbfls"));
        Ext.getCmp("dqttl").setValue(listGrid.getSelectionModel().getSelections()[0].get("dqttl"));
        Ext.getCmp("recordCount").setValue(listGrid.getSelectionModel().getSelections()[0].get("recordCount"));
        Ext.getCmp("alertCount").setValue(listGrid.getSelectionModel().getSelections()[0].get("alertCount"));
        Ext.getCmp("linkCount").setValue(listGrid.getSelectionModel().getSelections()[0].get("linkCount"));
        Ext.getCmp("responseTime").setValue(listGrid.getSelectionModel().getSelections()[0].get("responseTime"));
        Ext.getCmp("runStatus").setValue(listGrid.getSelectionModel().getSelections()[0].get("runStatus"));
        winlookxx(myform);
    }
    function winlookxx(form) {
        var myform = form;
        win = new Ext.Window({
            title : '查看消息',
            width : 450,
            height :400,
            items: [myform],
            bbar : ["->",
                {
                    text:'关闭',
                    handler:function() {
                        win.close();
                    }
                }
            ]
        });
        win.show();
    }
});
function showDetail(){
    var a=listGrid.getSelectionModel().getSelected();
    editWindow.setTitle("详细信息","win_add");
    editWindow.removeAll(true);editWindow.show();
    editWindow.load("../../bizConf.do?m=detail&id="+a.data.id+"&time="+new Date().getTime())
}
function showStat(){
    var a=listGrid.getSelectionModel().getSelected();
    editWindow.setTitle("统计信息","win_add");
    editWindow.removeAll(true);
    editWindow.show();
    editWindow.load("../../runMonitor.do?m=bizStat&name="+a.data.name+"&time="+new Date().getTime())
};

var Model = new Object;
function lookmodel(){
    Model.lookxx();
}