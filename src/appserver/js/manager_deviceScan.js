var editWindow;

Ext.onReady(function() {
    var myMask = new Ext.LoadMask(Ext.getBody(), {
        msg: '正在搜寻地址段,请稍后...',
        removeMask: true //完成后移除
    });

    //定时器 在扫描线程没结束前定时刷新datastore
    var task = {
        run : function() {
            ds.load({
                params : {
                    start : 0,
                    limit : 15
                }
            });
            isMapSelect();
            myMask.hide();
        },
        interval : 5000 // 5秒
    }



    var tbar = new Ext.Toolbar({
        autoWidth :true,
        autoHeight:true,
        items: [
             'ip开始地址'
        ,{
            id:'internal.ip.start',
            emptyText : "ip开始地址",
            xtype:'textfield',
            name:'ipstart',
            regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
            regexText:'这个不是Ip'
        },{
            xtype : 'tbseparator',
            width : 10
        },
            'ip结束地址'
       ,{
            id:'internal.ip.stop',
            emptyText : "ip结束地址",
            xtype:'textfield',
            name:'ipstart',
            regex:/^(((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))$/,
            regexText:'这个不是Ip'
        } ,{
            pressed : false,
            text : '<font color="blue">开始搜索</font>',
            handler : function() {
                var ipstart = Ext.getCmp('internal.ip.start').getValue();
                var ipstop = Ext.getCmp('internal.ip.stop').getValue();
                if(ipstart==""){
                    Ext.Msg.alert('提示', "请填写开始ip！");
                }else if(ipstart==""){
                    Ext.Msg.alert('提示', "请填写结束ip！");
                }else{
                    select();
                }
            }
        }
        ]
    });



    // 表头
    var sm = new Ext.grid.CheckboxSelectionModel();
    var cm = new Ext.grid.ColumnModel([
        sm,
        {header : 'IP地址', dataIndex : 'ip', align : 'center',sortable:true},
        { header : '操作标记',dataIndex :'flag', align : 'center',renderer : function(value) {
            if(value=='false'){
                return "<a  style='text-decoration: underline;color:blue;'onclick='insert()'>增加设备</a>&nbsp<a style='text-decoration: underline;color:gray;'>查看设备</a>";
            } else{
                return "<a  style='text-decoration: underline;color:gray;'>增加设备</a>&nbsp<a style='text-decoration: underline;color:blue;'onclick='lookup()'>查看设备</a>";
            }
        }
        }
    ]);



    //数据存储
    var ds = new Ext.data.Store({
        proxy : new Ext.data.HttpProxy({
            url :'interfaceManager.do?m=findPingSuccessIp'
        }),
        reader : new Ext.data.JsonReader({
            totalProperty : 'totalProperty',
            root : 'root'
        }, [{
            name : 'ip'
        },{
            name :'flag'
        }
        ])
    });

    //表格
    var grid = new Ext.grid.GridPanel({
        renderTo : "grid", // 渲染到哪里
        stripeRows : true, // 斑马线效果
        columnLines : true, // 控制中间是否有线相隔
        store : ds,
        height : 400,
        width : 1000,
        cm : cm, // 表头
        selModel : sm, // 为Grid提供选区模型
        viewConfig : {
            forceFit : true
        },
        tbar:tbar,
        bbar : new Ext.PagingToolbar({
            pageSize : 15,
            store : ds,
            displayInfo : true,
            displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
            emptyMsg : "没有记录"
        })
    });
    grid.render();
    ds.load({
        params : {
            start : 0,
            limit : 15
        }
    });

    var port = new Ext.Viewport({
        layout:'fit',
        renderTo:Ext.getBody(),
        items:[grid]
    });



    // 点击获取行
    var row;
    sm.on("rowselect", function(sm, rowIndex) {
        row = rowIndex;
    });

    //Ping的IP数量
    var num = 0;

    //搜寻地址段方法
    var select = function (){

        myMask.show();
        var sum = 0;     //pingIP数量的大小
//       alert(ips.length);
        num = 0;     //存放IP数组的大小
        var ipstart = Ext.getCmp('internal.ip.start').getValue();
        var ipstop = Ext.getCmp('internal.ip.stop').getValue();
        var ipkaishi = ipstart.split(".");
        var ipjieshu = ipstop.split(".");
        if(ipkaishi[0]==ipjieshu[0]&&ipkaishi[1]==ipjieshu[1]&&ipkaishi[2]==ipjieshu[2]){
            var kaishi = parseInt(ipkaishi[3]);
            var jieshu = parseInt(ipjieshu[3]);
//                    var myMask = new Ext.LoadMask(Ext.getBody(),{
//                        msg:'正在扫描地址段'+ipkaishi+"到"+ipjieshu+',请稍后...',
//                        removeMask:true
//                    });
            Ext.Ajax.request({
                url : 'interfaceManager.do?m=addressPing',
                params:{
                    ipstart:ipstart,
                    ipend:ipstop
                },

                method : 'POST',

                success:function(result,request) {

                    grid.render();

                    Ext.TaskMgr.start(task);
//                    myMask.hide();
                },
                failure:function(){
                    grid.render();

                    Ext.TaskMgr.start(task);
//                   myMask.hide();
                }
            });
        }else{
            Ext.Msg.alert('提示', "请确保开始IP与结束IP在一个地址段内！");
        }
    }

    var isMapSelect =function(){
        Ext.Ajax.request({
            url : 'interfaceManager.do?m=isMapFlag',
            method : 'POST',
            success:function(result,request) {

                if(result.responseText=="true"){
                    ds.load({
                        params : {
                            start : 0,
                            limit : 15
                        }
                    });
                    Ext.TaskMgr.stop(task);
                    myMask.hide();
                }

            },
            failure:function(){
                ds.load({
                    params : {
                        start : 0,
                        limit : 15
                    }
                });
                Ext.TaskMgr.stop(task);
                myMask.hide();
            }
        });
    }

    editWindow = new Ext.Window({
        width : 600,
        height : 400,
        modal : true,
        autoScroll : true,
        closeAction : 'hide',
        resizable : false,
        buttons : [{
            xtype : "tbfill"
        }, {
            text : '保存',
            handler : function() {
                Ext.Ajax.request({
                    form : 'editForm',
                    success : function(reponse, options) {
                        var respText = Ext.util.JSON.decode(reponse.responseText);
                        var msg = respText.msg;
                        Ext.Msg.alert('温馨提示', msg);
                        editWindow.hide();
                    }
                });
            }
        }, {
            text : '关闭',
            handler : function() {
                editWindow.hide();
            }
        }],
        listeners : {
            'hide' : function() {

            }
        }
    });



    var insert = function(){
        editWindow.setTitle("新增设备", "win_add");
        editWindow.removeAll(true);
        editWindow.show();
        editWindow.load({
            url:'../../equConf.do?m=showAdd',
            callback : function() {

            },
            scripts : true
        });
    }

    var lookup = function(){
        var selectGridRow = grid.getSelectionModel().getSelected();
        editWindow.setTitle("详细信息", "win_add");
        editWindow.removeAll(true);
        editWindow.show();
        editWindow.load('../../equConf.do?m=detailip&ip=' + selectGridRow.data.ip);
    }






    Method.insert = function() {
        var record = grid.getSelectionModel().getSelected();// 点击选择行
        var ipValue = record.get("ip");
        insert(ipValue);
    };
    Method.lookup = function() {
        var record = grid.getSelectionModel().getSelected();// 点击选择行
        var ipValue = record.get("ip");
        lookup(ipValue);
    };

});


var Method= new Object;

function insert() {
    Method.insert();
}

function lookup() {
    Method.lookup();
}


