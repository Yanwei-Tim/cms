Ext.chart.Chart.CHART_URL = '../ext/resources/charts.swf';

Ext.onReady(function(){

  /*  var store = new Ext.data.JsonStore({
        data: [
            {name:'Jul 07', visits: 245000, views: 3000000},
            {name:'Aug 07', visits: 240000, views: 3500000},
            {name:'Sep 07', visits: 355000, views: 4000000},
            {name:'Oct 07', visits: 375000, views: 4200000},
            {name:'Nov 07', visits: 490000, views: 4500000},
            {name:'Dec 07', visits: 495000, views: 5800000},
            {name:'Jan 08', visits: 520000, views: 6000000},
            {name:'Feb 08', visits: 620000, views: 7500000}
        ] ,
        fields:['name', 'visits', 'views']
    });*/
    var store = new Ext.data.JsonStore({
        url:"../../pages/abnormal/text.jsp",
        root : 'root',
        fields:["name", "visits", "views"]
    });
    store.load();
// extra extra simple
    new Ext.Panel({
        title: 'container',
        applyTo: 'report-chart3',
        width:500,
        height:300,
        layout:'fit',
        items:{
            xtype: 'linechart',
            store: store,
            xField: 'name',
            listeners: {
                itemclick: function(o){
                    var rec = store.getAt(o.index);
                    Ext.example.msg('Item Selected', 'You chose {0}.', rec.get('name'));
                }
            },

            series: [{//列
                type: 'line', //类型可以改变（线）
                displayName: 'Good',
                yField: 'views',
                style: {
                    color:0x00BB00
                }
            },{
                type:'line',
                displayName: 'Visits',
                yField: 'visits',
                style: {
                    color: 0xE1E100
                }
            }]

        }
    });
    var chart2 = new Ext.Panel({
        iconCls : 'chart',
        title : '违规次数/日',
        frame : true,
        renderTo : 'report-chart2',
        width : "100%",
        height : 300,
        layout : 'fit',

        items : {
            xtype : 'columnchart',
            store : store,
            url : '../ext/resources/charts.swf',
            xField : 'name',
            yField : 'visits',
            yAxis : new Ext.chart.NumericAxis({
                displayName : '违规次数',
                labelRenderer : Ext.util.Format.numberRenderer('0,0')
            }),
            tipRenderer : function(chart, record) {
                return record.data.name + ' 违规次数： '
                    + Ext.util.Format.number(record.data.visits, '0,0');
            }
        }
    });

    // 简单例子
    var chart1= new Ext.Panel({
        title: 'ExtJS.com Visits Trend, 2007/2008 (No styling)',
        renderTo: 'report-chart1',
        width:500,
        height:300,
        layout:'fit',

        items: {
            xtype: 'linechart',    //线型图
            store: store,
            xField: 'name',
            yField: 'visits',
            listeners: {
                itemclick: function(o){
                    var rec = store.getAt(o.index);
                    Ext.example.msg('Item Selected', 'You chose {0}.', rec.get('name'));
                }
            }
        }
    });
//    store.load();

    // 简单例子（有样式的）
//    var chart2 = new Ext.Panel({
//        iconCls:'chart',    //在头部工具栏里（headerTool）加上chart图标。
//        title: 'ExtJS.com Visits Trend, 2007/2008 (Simple styling)',
//        frame:true,
//        renderTo: 'report-chart2',
//        width:500,
//        height:300,
//        layout:'fit',
//
//        items: {
//            xtype: 'linechart',
//            store: store,
//            url: '../../resources/charts.swf',
//            xField: 'name',
//            yField: 'visits',
//            yAxis: new Ext.chart.NumericAxis({    //对y轴显示进行设置。
//                displayName: 'Visits',
//                labelRenderer : Ext.util.Format.numberRenderer('0,0')
//            }),
//            tipRenderer : function(chart, record){
//                return Ext.util.Format.number(record.data.visits, '0,0') + ' visits in ' + record.data.name;
//            }
//        }
//    });
    var chart8 = new Ext.Panel({
        id : 'chart8',
        iconCls : 'chart',
        title : '业务日统计-外网请求',
        frame : true,
        renderTo : 'report-chart8',
//        width : "100%",
        height : 300,
        layout : 'fit',

        items : {
            id : 'chart8item',
            xtype : 'linechart',
            store : store,
            url : '../js/ext/resources/charts.swf',
            xField : 'name',
            yAxis : new Ext.chart.NumericAxis({
                displayName : '外网请求',
                labelRenderer : Ext.util.Format
                    .numberRenderer('0,0')
            }),
            series : [{
                type : 'line',
                displayName : '外网请求',
                yField : 'visits',
                style : {
                    color : 0x1E90FF
                }
            }]
        }
    });
});