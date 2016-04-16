var resourceDS;
var currentRow;
Ext.onReady(function(){

    var tbar = new Ext.Toolbar({
        autoWidth :true,
        autoHeight:true,
        items: [{
//            pressed: false,
//            enableToggle:true,
            text: '新增',
            handler : function() {
                insert();
            }
        },{
//            pressed: false,
//            enableToggle:true,
            text: '编辑',
            handler : function() {
                update();
            }
        },{
//            pressed: false,
//            enableToggle:true,
            text: '删除',
            handler : function() {
                deleteSnmpOid();
            }
        }
        ]
    });

    // 表头
    var sm = new Ext.grid.CheckboxSelectionModel();
//    var sm = new Ext.grid.boxSelectionModel();
    var cm = new Ext.grid.ColumnModel([
        sm,
        {header: "设备名称", width: 100, dataIndex: 'name'},
        {header: "设备厂商", width: 100, dataIndex: 'company'},
        {header: "设备类型", width: 100, dataIndex: 'type'},
        {header: "CPU使用率", width: 100, dataIndex: 'cpuuse'},
        {header: "磁盘总量", width: 100, dataIndex: 'disktotal'},
        {header: "磁盘用量", width: 100, dataIndex: 'diskuse'},
        {header: "内存总量", width: 100, dataIndex: 'memtotal'},
        {header: "内存用量", width: 100, dataIndex: 'memuse'},
        {header: "当前连接数", width: 100, dataIndex: 'curconn'},
        {header: "SNMP版本", width: 150, dataIndex: 'snmpver'}
    ]);

    var resourceRecord = Ext.data.Record.create(
        [   {name:'id'},
            {name: 'cpuuse'},
            {name: 'name'},
            {name: 'disktotal'},
            {name: 'type'},
            {name: 'company'},
            {name: 'diskuse'},
            {name: 'memtotal'},
            {name: 'curconn'},
            {name: 'memuse'},
            {name: 'snmpver'}
        ]);


    resourceDS = new Ext.data.Store({
        storeId : 'resourceDS',
        proxy: new Ext.data.HttpProxy({url:'../../snmpOid.do?m=index',method:'POST'}),
        reader: new Ext.data.JsonReader({
            totalProperty : 'amount',
            root : "results"
        }, resourceRecord)
    });



    //表格
    var grid = new Ext.grid.GridPanel({
        renderTo : "grid", // 渲染到哪里
        animCollapse:true,
        border:false,
        stripeRows : true, // 斑马线效果
        columnLines : true, // 控制中间是否有线相隔
        store : resourceDS,
        height : setHeight(),
        width : setWidth(),
        cm : cm, // 表头
        selModel : sm, // 为Grid提供选区模型
        viewConfig : {
            forceFit : true
        },
        tbar:tbar,
        bbar : new Ext.PagingToolbar({
            pageSize : 15,
            store : resourceDS,
            displayInfo : true,
            displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
            emptyMsg : "没有记录"
        })
    });

    resourceDS.load({
        params : {
            start : 0,
            limit : 15
        }
    });

    grid.addListener('rowclick', rowClick);
    grid.render();

    var port = new Ext.Viewport({
        layout:'fit',
        renderTo:Ext.getBody(),
        items:[grid]
    });


    //增加SnmpOID方法
    var insert = function(){
        Ext.QuickTips.init();// 用来提示信息
        Ext.form.Field.prototype.msgTarget = 'side';// 统一指定错误提示方式

        var charsetDs = [
            ['Cisco','思科'],
            ['huawei','华为'],
            ['3com','3com'],
            ['h3c','华三'],
            ['bdcom','博达'],
            ['topsec','天融信'],
            ['leadsec','联想网御'],
            ['venustech','启明星辰'],
            ['pcserver','服务器'],
            ['radware','radware负载均衡'],
            ['koal','格尔'],
            ['zhyu','中宇万通'],
            ['hawksec','云鹰合创'],
            ['netchina','中网'],
            ['legendsec','网御神州']
        ];

        var proxytypeDs = [
            ['v1','v1'],
            ['v2','v2'],
            ['v3','v3'],
            ['trapv1','trapv1'],
            ['trapv2','trapv2'],
            ['trapv3','trapv3']
        ];

        //选择框 输入框
        var nameField = new Ext.form.TextField({
            fieldLabel: '名称',
            name: 'name',
            width:190,
            allowBlank : false,
            blankText : "不能为空，请正确填写",
            regex : /^.{1,30}$/,
            regexText : '请输入设备名称',
            emptyText : '请输入设备名称',
            anchor : '90%'
        });

        var tyepField = new Ext.form.TextField({
            fieldLabel: '设备类型',
            name: 'type',
            width:190,
            allowBlank : false,
            blankText : "不能为空，请正确填写",
            regex : /^.{1,30}$/,
            regexText : '请输入设备类型',
            emptyText : '请输入设备类型',
            anchor : '90%'
        });

        var companyField = new Ext.form.ComboBox({
            fieldLabel: '设备厂商',
            hiddenName:'company',
            store: new Ext.data.SimpleStore({
                fields: ['value', 'text'],
                data : charsetDs
            }),
            displayField:'text',
            valueField:'value',
            typeAhead: true,
            editable:true,
            allowBlank:false,
            mode: 'local',
            triggerAction: 'all',
            emptyText:'请选择厂商',
            selectOnFocus:true,
            width:190
        });

        var cpuuseField =  new Ext.form.TextField({
            fieldLabel: 'CPU使用率',
            name: 'cpuuse',
            width:190,
            allowBlank:true
        })


        var disktotalField =  new Ext.form.TextField({
            fieldLabel: '磁盘总量',
            name: 'disktotal',
            width:190,
            allowBlank:false
        })

        var diskuseField =  new Ext.form.TextField({
            fieldLabel: '磁盘用量',
            name: 'diskuse',
            width:190,
            grow: false
        })

        var memtotalField = new Ext.form.TextField({
            fieldLabel: '内存总量',
            name: 'memtotal',
            width:190,
            allowBlank:false
        })

        var memuseField =   new Ext.form.TextField({
            fieldLabel: '内存用量',
            name: 'memuse',
            width:190,
            grow: false
        })

        var curconnField = new Ext.form.TextField({
            fieldLabel: '当前连接数',
            name: 'curconn',
            width:190,
            grow: false
        })

        var snmpverField = new Ext.form.ComboBox({
            fieldLabel: 'SNMP版本',
            hiddenName:'snmpver',
            store: new Ext.data.SimpleStore({
                fields: ['value', 'text'],
                data : proxytypeDs
            }),
            displayField:'text',
            valueField:'value',
            typeAhead: true,
            editable:true,
            mode: 'local',
            triggerAction: 'all',
            emptyText:'请选择SNMP版本',
            selectOnFocus:true,
            width:190
        });


        //表单
        var myForm = new Ext.form.FormPanel({
            labelAlign : 'right',
            labelWidth : 150,
            frame : true,
            items : [{
                xtype : 'fieldset',
                title : '设备信息',
                autoHeight : true,
                items : [{// 第1行
                    layout : 'column',
                    items : [{
                        columnWidth : .8,
                        layout : 'form',
                        items : nameField
                    },{
                        columnWidth : .8,
                        layout : 'form',
                        items : tyepField
                    },{
                        columnWidth : .8,
                        layout : 'form',
                        items : companyField
                    },{
                        columnWidth : .8,
                        layout : 'form',
                        items : cpuuseField
                    },{
                        columnWidth : .8,
                        layout : 'form',
                        items : disktotalField
                    },{
                        columnWidth : .8,
                        layout : 'form',
                        items : diskuseField
                    },{
                        columnWidth : .8,
                        layout : 'form',
                        items : memtotalField
                    },{
                        columnWidth : .8,
                        layout : 'form',
                        items : memuseField
                    },{
                        columnWidth : .8,
                        layout : 'form',
                        items : curconnField
                    } ,{
                        columnWidth : .8,
                        layout : 'form',
                        items : snmpverField
                    }
                    ]
                }]
            }]
        });

        //增加消息框
        var insert_Win = new Ext.Window({
            plain : true,
            layout : 'form',
            resizable : true, // 改变大小
            draggable : true, // 不允许拖动
            closeAction : 'close',// 可被关闭 close or hide
            modal : true, // 模态窗口
            width : 600,
            autoHeight : true,
            title : '增加设备信息',
            items : [myForm],// 嵌入数据
            buttonAlign : 'center',
            loadMask : true,
            bbar:[
                new Ext.Toolbar.Fill(),
                new Ext.Button ({
                    id:'snmpOid.update.win.info',
                    text : '保存',
                    allowDepress : false,
                    handler : function() {
                        if (myForm.form.isValid()) {
                            Ext.MessageBox.show({
                                title:'信息',
                                width:200,
                                msg:'是否确定要新增设备么?',
                                buttons:{'ok':'确定','no':'取消'},
                                icon:Ext.MessageBox.QUESTION,
                                closable:false,
                                fn:function(e){
                                    if(e=='ok'){
                                        myForm.getForm().submit({
                                            url :'../../snmpOid.do?m=Add',
                                            method :'POST',
                                            waitTitle :'系统提示',
                                            waitMsg :'正在保存,请稍后...',
                                            success : function(form,action) {
                                                Ext.MessageBox.alert("恭喜", "提交成功!");
                                                resourceDS.reload();
                                                insert_Win.close();
                                            },
                                            failure : function() {
                                                Ext.MessageBox.show({
                                                    title:'系统提示',
                                                    width:200,
                                                    msg:'新增设备失败，请与管理员联系!',
                                                    animEl:'snmpOid.update.win.info',
                                                    buttons:{'ok':'确定'},
                                                    icon:Ext.MessageBox.ERROR,
                                                    closable:false
                                                });
                                            }
                                        });
                                    }
                                }
                            });

                        } else {
                            Ext.MessageBox.show({
                                title:'信息',
                                width:200,
                                msg:'请填写完成再提交!',
                                animEl:'snmpOid.update.win.info',
                                buttons:{'ok':'确定'},
                                icon:Ext.MessageBox.ERROR,
                                closable:false
                            });
                        }
                    }
                }),
                new Ext.Button ({
                    allowDepress : false,
                    text : '重置',
                    handler : function() {
                        myForm.form.reset();
                    }
                })
            ]
        }).show();
    }

    function rowClick(grid, rowIndex, columnIndex, e) {
        currentRow = rowIndex;
    }
    //修改方法
    var update = function(){
        if (currentRow == null) {
            Ext.MessageBox.alert('提示', '请先选择一行数据.');
        } else {
            var row = resourceDS.data.items[currentRow];
            var name = row.data['name'];
            var company = row.data['company'];
            var type = row.data['type'];
            var cpuuse = row.data['cpuuse'];
            var disktotal = row.data['disktotal'];
            var diskuse = row.data['diskuse'];
            var memtotal = row.data['memtotal'];
            var memuse = row.data['memuse'];
            var curconn = row.data['curconn'];
            var snmpver = row.data['snmpver'];
            var id = row.data['id'];

            var charsetDs = [
                ['Cisco','思科'],
                ['huawei','华为'],
                ['3com','3com'],
                ['h3c','华三'],
                ['bdcom','博达'],
                ['topsec','天融信'],
                ['leadsec','联想网御'],
                ['venustech','启明星辰'],
                ['pcserver','服务器'],
                ['radware','radware负载均衡'],
                ['koal','格尔'],
                ['zhyu','中宇万通'],
                ['hawksec','云鹰合创'],
                ['netchina','中网'],
                ['legendsec','网御神州']
            ];

            var proxytypeDs = [
                ['v1','v1'],
                ['v2','v2'],
                ['v3','v3'],
                ['trapv1','trapv1'],
                ['trapv2','trapv2'],
                ['trapv3','trapv3']
            ];

            //选择框 输入框
            var nameField = new Ext.form.TextField({
                fieldLabel: '名称',
                name: 'name',
                width:190,
                allowBlank : false,
                blankText : "不能为空，请正确填写",
                regex : /^.{1,30}$/,
                regexText : '请输入设备名称',
                emptyText : '请输入设备名称',
                anchor : '90%'
            });

            var tyepField = new Ext.form.TextField({
                fieldLabel: '设备类型',
                name: 'type',
                width:190,
                allowBlank : false,
                blankText : "不能为空，请正确填写",
                regex : /^.{1,30}$/,
                regexText : '请输入设备类型',
                emptyText : '请输入设备类型',
                anchor : '90%'
            });

            var companyField = new Ext.form.ComboBox({
                fieldLabel: '设备厂商',
                hiddenName:'company',
                store: new Ext.data.SimpleStore({
                    fields: ['value', 'text'],
                    data : charsetDs
                }),
                displayField:'text',
                valueField:'value',
                typeAhead: true,
                editable:true,
                allowBlank:false,
                mode: 'local',
                triggerAction: 'all',
                emptyText:'请选择厂商',
                selectOnFocus:true,
                width:190
            });

            var cpuuseField =  new Ext.form.TextField({
                fieldLabel: 'CPU使用率',
                name: 'cpuuse',
                width:190,
                allowBlank:true
            })


            var disktotalField =  new Ext.form.TextField({
                fieldLabel: '磁盘总量',
                name: 'disktotal',
                width:190,
                allowBlank:false
            })

            var diskuseField =  new Ext.form.TextField({
                fieldLabel: '磁盘用量',
                name: 'diskuse',
                width:190,
                grow: false
            })

            var memtotalField = new Ext.form.TextField({
                fieldLabel: '内存总量',
                name: 'memtotal',
                width:190,
                allowBlank:false
            })

            var memuseField =   new Ext.form.TextField({
                fieldLabel: '内存用量',
                name: 'memuse',
                width:190,
                grow: false
            })

            var curconnField = new Ext.form.TextField({
                fieldLabel: '当前连接数',
                name: 'curconn',
                width:190,
                grow: false
            })

            var snmpverField = new Ext.form.ComboBox({
                fieldLabel: 'SNMP版本',
                hiddenName:'snmpver',
                store: new Ext.data.SimpleStore({
                    fields: ['value', 'text'],
                    data : proxytypeDs
                }),
                displayField:'text',
                valueField:'value',
                typeAhead: true,
                editable:true,
                mode: 'local',
                triggerAction: 'all',
                emptyText:'请选择SNMP版本',
                selectOnFocus:true,
                width:190
            });
            nameField.setValue(name);
            tyepField.setValue(type);
            companyField.setValue(company);
            cpuuseField.setValue(cpuuse);
            disktotalField.setValue(cpuuse);
            diskuseField.setValue(diskuse);
            memtotalField.setValue(memtotal);
            memuseField.setValue(memuse);
            curconnField.setValue(curconn);
            snmpverField.setValue(snmpver);


            //表单
            var myForm = new Ext.form.FormPanel({
                labelAlign : 'right',
                labelWidth : 150,
                frame : true,
                items : [{
                    xtype : 'fieldset',
                    title : '设备信息',
                    autoHeight : true,
                    items : [{// 第1行
                        layout : 'column',
                        items : [{
                            columnWidth : .8,
                            layout : 'form',
                            items : nameField
                        },{
                            columnWidth : .8,
                            layout : 'form',
                            items : tyepField
                        },{
                            columnWidth : .8,
                            layout : 'form',
                            items : companyField
                        },{
                            columnWidth : .8,
                            layout : 'form',
                            items : cpuuseField
                        },{
                            columnWidth : .8,
                            layout : 'form',
                            items : disktotalField
                        },{
                            columnWidth : .8,
                            layout : 'form',
                            items : diskuseField
                        },{
                            columnWidth : .8,
                            layout : 'form',
                            items : memtotalField
                        },{
                            columnWidth : .8,
                            layout : 'form',
                            items : memuseField
                        },{
                            columnWidth : .8,
                            layout : 'form',
                            items : curconnField
                        } ,{
                            columnWidth : .8,
                            layout : 'form',
                            items : snmpverField
                        }
                        ]
                    }]
                }]
            });

            //增加消息框
            var update_Win = new Ext.Window({
                plain : true,
                layout : 'form',
                resizable : true, // 改变大小
                draggable : true, // 不允许拖动
                closeAction : 'close',// 可被关闭 close or hide
                modal : true, // 模态窗口
                width : 600,
                autoHeight : true,
                title : '修改设备信息',
                items : [myForm],// 嵌入数据
                buttonAlign : 'center',
                loadMask : true,
                bbar:[
                    new Ext.Toolbar.Fill(),
                    new Ext.Button ({
                        id:'snmpOid.update.win.info',
                        text : '保存',
                        allowDepress : false,
                        handler : function() {
                            if (myForm.form.isValid()) {
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:200,
                                    msg:'是否确定要修改设备么?',
                                    buttons:{'ok':'确定','no':'取消'},
                                    icon:Ext.MessageBox.QUESTION,
                                    closable:false,
                                    fn:function(e){
                                        if(e=='ok'){
                                            myForm.getForm().submit({
                                                url :'../../snmpOid.do?m=Update',
                                                method :'POST',
                                                params:{
                                                 equOidId: row.data['id']
                                                },
                                                waitTitle :'系统提示',
                                                waitMsg :'正在保存,请稍后...',
                                                success : function(form,action) {
                                                    update_Win.close();
                                                    Ext.MessageBox.alert("信息提示", "提交成功!");
                                                    resourceDS.reload();
                                                },
                                                failure : function() {
                                                    Ext.MessageBox.show({
                                                        title:'系统提示',
                                                        width:200,
                                                        msg:'修改设备失败，请与管理员联系!',
                                                        animEl:'snmpOid.update.win.info',
                                                        buttons:{'ok':'确定'},
                                                        icon:Ext.MessageBox.ERROR,
                                                        closable:false
                                                    });
                                                }
                                            });
                                        }
                                    }
                                });

                            } else {
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:200,
                                    msg:'请填写完成再提交!',
                                    animEl:'snmpOid.update.win.info',
                                    buttons:{'ok':'确定'},
                                    icon:Ext.MessageBox.ERROR,
                                    closable:false
                                });
                            }
                        }
                    }),
                    new Ext.Button ({
                        allowDepress : false,
                        text : '重置',
                        handler : function() {
                            myForm.form.reset();
                        }
                    })
                ]
            }).show();
            currentRow=null;
            resourceDS.reload();


        }
    };

    //删除方法
    var  deleteSnmpOid = function(){
        if (currentRow == null) {
            Ext.MessageBox.alert('提示', '请先选择一行数据.');
        }else{
            Ext.Msg.confirm('警告', '确定要删除这些记录吗？', function(btn) {
                if (btn == 'yes') {
                    var row = resourceDS.data.items[currentRow];
                    Ext.Ajax.request({
                        url : '../../snmpOid.do?m=Delete',
                        params:{
                            equOidId: row.data['id']
                        },
                        method : 'POST',
                        success:function(result,request) {
                            Ext.MessageBox.alert("信息提示", "删除成功!");
                            resourceDS.reload();
                        },
                        failure:function(){
                            Ext.MessageBox.show({
                                title:'系统提示',
                                width:200,
                                msg:'删除设备失败，请与管理员联系!',
                                animEl:'snmpOid.update.win.info',
                                buttons:{'ok':'确定'},
                                icon:Ext.MessageBox.ERROR,
                                closable:false
                            });
                            resourceDS.reload();
                        }
                    });
                } else {
                    return false;
                }
            });
            currentRow=null;
        }
    }



    });

function setHeight(){
    var h = document.body.clientHeight-8;
    return h;
}

function setWidth(){
    return document.body.clientWidth-8;
}