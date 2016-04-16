Ext.chart.Chart.CHART_URL = '../js/ext/resources/charts.swf'; // 模板flash
title = '平台违规日统计表';
Ext.onReady(function() {
    Ext.QuickTips.init();

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
        } ,{
            // xtype: 'button',
            text : '统计',
            listeners : {
                "click" : function() {
//                    var userDepart = Ext.getCmp("userDepart.info").getValue();
//                    if (userDepart == '') {
//                        Ext.Msg.alert("提示", "请选择部门！");
//                        return;
//                    }
                    var reportDate = Ext.getCmp("reportDate.info").getValue();
                    if (reportDate == '点击输入日期' || reportDate == '') {
                        Ext.Msg.alert("提示", "请输入日期！");
                        return;
                    }
                    store.setBaseParam("reportDate", reportDate);
//                    store.setBaseParam("userDepart", userDepart);
                    chartStore1.setBaseParam("reportDate", reportDate);
                    store.load();
                    chartStore1.load();

                }
            }
        }]
    });

    var proxy = new Ext.data.HttpProxy({
        url:"../sysAbnormalService.do?m=selectDayForAbnormal",
        timeout: 20*60*1000
    });
    var record = new Ext.data.Record.create([
//        {name:'idsystemname', mapping:'idsystemname'} ,
//        {name:'idsystem', mapping:'idsystem'} ,
//        {name:'username', mapping:'username'} ,
//        {name:'sysabnormalobjecttype', mapping:'sysabnormalobjecttype'} ,
        {name:'SeparationViolations', mapping:'SeparationViolations'} ,
        {name:'OtherViolations', mapping:'OtherViolations'},
        {name:'NetworkViolations', mapping:'NetworkViolations'},
        {name:'ProcessViolations', mapping:'ProcessViolations'},
        {name:'PeripheralViolations', mapping:'PeripheralViolations'},
        {name:'URLViolations', mapping:'URLViolations'},
        {name:'FluxViolations', mapping:'FluxViolations'}

    ]);
    var reader = new Ext.data.JsonReader({
        totalProperty:"totalCount",
        root:"rows"//,
//        id:'id'
    }, record);
    var store = new Ext.data.GroupingStore({
        id:"store.info",
        proxy:proxy,
        reader:reader
    });
    store.load();
    // create the Data Store

    var rowNumber = new Ext.grid.RowNumberer();         //自动 编号

    var colM = new Ext.grid.ColumnModel([
//        boxM,
        rowNumber,
        {header:"网络违规", dataIndex:"NetworkViolations", sortable:true, menuDisabled:true} ,
        {header:"进程违规", dataIndex:"ProcessViolations", sortable:true, menuDisabled:true} ,
        {header:"外设违规", dataIndex:"PeripheralViolations", sortable:true, menuDisabled:true} ,
        {header:"URL违规", dataIndex:"URLViolations", sortable:true, menuDisabled:true} ,
        {header:"流量违规", dataIndex:"FluxViolations", sortable:true, menuDisabled:true} ,
        {header:"机卡分离违规", dataIndex:"SeparationViolations", sortable:true, menuDisabled:true} ,
        {header:"其他违规", dataIndex:"OtherViolations", sortable:true, menuDisabled:true}
    ]);
    // store.setDefaultSort('lastpost', 'desc');
    var grid = new Ext.grid.GridPanel({
        width : "100%",
        height : "300",
        renderTo : 'topic-grid',
        layout : 'fit',
        // autoHeight:true,
        title : title,
        store : store,
        loadMask : true,
        trackMouseOver : true,// 鼠标悬浮高亮显示
        // autoExpandColumn: 'auditInfo',// 自动扩展宽度适应空白部分
        columnLines : true,// 列分隔符
        cm:colM,

        sm : new Ext.grid.RowSelectionModel({
            singleSelect : true
        }),

        // paging bar on the bottom
//        bbar : new Ext.ux.PagingToolbar({
//            id : 'mypagebar',
//            store : store
//        })   ,
        tbar : tb
    });


    var chartStore1 = new Ext.data.JsonStore({
        url : '../sysAbnormalService.do?m=selectAbnormalDay',
        root : 'rows',
        fields : [ 'abnormalName',  'abnormalCount','abnormalCode']
    });

    var chart1 = new Ext.Panel({
        iconCls : 'chart',
        title : '违规次数/日',
        frame : true,
        renderTo : 'report-chart1',
        width : "100%",
        height : 300,
        layout : 'fit',

        items : {
            xtype : 'columnchart',
            store : chartStore1,
//            url : '../js/ext/resources/charts.swf',
            xField : 'abnormalName',
            yField : 'abnormalCount',
            yAxis : new Ext.chart.NumericAxis({
                displayName : '违规次数',
                labelRenderer : Ext.util.Format.numberRenderer('0,0')
            }),
            tipRenderer : function(chart, record) {
                return record.data.abnormalName + ' 违规次数： '
                    + Ext.util.Format.number(record.data.abnormalCount, '0,0');
            }
        }
    });
    chartStore1.load();


});
