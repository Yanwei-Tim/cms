Ext.onReady(function () {
    Ext.BLANK_IMAGE_URL = '../../../js/ext/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';

    var start = 0;
    var pageSize = 15;
    var record = new Ext.data.Record.create([
        {name:'idsystemname', mapping:'idsystemname'} ,
        {name:'idsystem', mapping:'idsystem'} ,
        {name:'username', mapping:'username'} ,
        {name:'sysabnormalobjecttype', mapping:'sysabnormalobjecttype'} ,
        {name:'SeparationViolations', mapping:'SeparationViolations'} ,
        {name:'OtherViolations', mapping:'OtherViolations'},
        {name:'NetworkViolations', mapping:'NetworkViolations'},
        {name:'ProcessViolations', mapping:'ProcessViolations'},
        {name:'PeripheralViolations', mapping:'PeripheralViolations'},
        {name:'URLViolations', mapping:'URLViolations'},
        {name:'FluxViolations', mapping:'FluxViolations'}

    ]);

    var proxy = new Ext.data.HttpProxy({
        url:"../../sysAbnormalService.do?m=select",
        timeout: 20*60*1000
    });

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

    store.load({
        params:{
            start:start, limit:pageSize
        }
    });

//    var boxM = new Ext.grid.CheckboxSelectionModel();   //复选框单选

    var rowNumber = new Ext.grid.RowNumberer();         //自动编号
    var colM = new Ext.grid.ColumnModel([
//        boxM,
        rowNumber,
        {header:"系统名称", dataIndex:"idsystemname", sortable:true, menuDisabled:true} ,
        {header:"终端用户", dataIndex:"username", sortable:true, menuDisabled:true} ,
        {header:"连接对象", dataIndex:"sysabnormalobjecttype", sortable:true, menuDisabled:true} ,
        {header:"网络违规", dataIndex:"NetworkViolations", sortable:true, menuDisabled:true} ,
        {header:"进程违规", dataIndex:"ProcessViolations", sortable:true, menuDisabled:true} ,
        {header:"外设违规", dataIndex:"PeripheralViolations", sortable:true, menuDisabled:true} ,
        {header:"URL违规", dataIndex:"URLViolations", sortable:true, menuDisabled:true} ,
        {header:"流量违规", dataIndex:"FluxViolations", sortable:true, menuDisabled:true} ,
        {header:"机卡分离违规", dataIndex:"SeparationViolations", sortable:true, menuDisabled:true} ,
        {header:"其他违规", dataIndex:"OtherViolations", sortable:true, menuDisabled:true} ,
        {header:"违规总数", dataIndex:"idsystem",sortable:true, menuDisabled:true,renderer:showUrl_sum} ,
        {header:'操作标记', dataIndex:'username', sortable:true, menuDisabled:true, renderer:show_flag}
    ]);


    var page_toolbar = new Ext.PagingToolbar({
        pageSize:pageSize,
        store:store,
        displayInfo:true,
        displayMsg:"显示第{0}条记录到第{1}条记录，一共{2}条",
        emptyMsg:"没有记录",
        beforePageText:"第",
        afterPageText:"页,共{0}页"
    });

    function setHeight() {
        var h = document.body.clientHeight - 8;
        return h;
    } ;


    var grid_panel = new Ext.grid.GridPanel({
        id:'grid.info',
        plain:true,
        height:setHeight(),
        viewConfig:{
            forceFit:true //让grid的列自动填满grid的整个宽度，不用一列一列的设定宽度。
        },
        bodyStyle:'width:100%',
        loadMask:{msg:'正在加载数据，请稍后...'},
        border:true,
        cm:colM,
//        sm:boxM,
//        tbar:tbar,
        store:store,
        bbar:page_toolbar
    });

    var port = new Ext.Viewport({
        layout:'fit',
        renderTo:Ext.getBody(),
        items:[grid_panel]
    });
});
function showUrl_sum(value, p, r) {
    return parseInt(r.get("SeparationViolations")) + parseInt(r.get("OtherViolations")) + parseInt(r.get("NetworkViolations"))
        + parseInt(r.get("ProcessViolations")) + parseInt(r.get("URLViolations")) + parseInt(r.get("FluxViolations")) + parseInt(r.get("PeripheralViolations"))

}
function show_flag(value,p,r){
    return String.format( '<a href="javascript:void(0);" onclick="selectAll(\''+r.get("idsystem")+'\',\''+r.get("username")+'\');"><font color="#fa8072">查看详细</font></a> &nbsp; &nbsp;');
}
function selectAll(value,v2){
//    Ext.QuickTips.init();
//    Ext.form.Field.prototype.msgTarget = 'side';
    var start = 0;
    var pageSize = 15;
    var record = new Ext.data.Record.create([
        {name:'id',			mapping:'id'},
        {name:'Username',			mapping:'Username'},
        {name:'Idsystem',			mapping:'Idsystem'},
        {name:'Sysabnormalobjecttype',			mapping:'Sysabnormalobjecttype'},
        {name:'AbNormalTypeCode',			mapping:'AbNormalTypeCode'},
        {name:'ExceptionDesc',			mapping:'ExceptionDesc'},
        {name:'TreadResult',			mapping:'TreadResult'},
        {name:'WriteTime',			mapping:'WriteTime'}
    ]);
    var proxy = new Ext.data.HttpProxy({
        url:"../../sysAbnormalService.do?m=selectAll&idsystem="+value+"&username="+v2
    });
    var reader = new Ext.data.JsonReader({
        totalProperty:'totalCount',
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
        {header:"终端用户",			dataIndex:"Username",  align:'center',sortable:true,menuDisabled:true,width:200},
        {header:"连接类型",			dataIndex:"Sysabnormalobjecttype",  align:'center',sortable:true,menuDisabled:true,width:200},
        {header:"违规类型",			dataIndex:"AbNormalTypeCode",  align:'center',sortable:true,menuDisabled:true,width:200},
        {header:"详细内容",			dataIndex:"ExceptionDesc",  align:'center',sortable:true,menuDisabled:true,width:200},
        {header:"处理状态",			dataIndex:"TreadResult",  align:'center',sortable:true,menuDisabled:true,width:200},
        {header:"上报时间",			dataIndex:"WriteTime",  align:'center',sortable:true,menuDisabled:true,width:200}
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
        title:"查看违规详细",
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



