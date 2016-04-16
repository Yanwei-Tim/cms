Ext.onReady(function () {
    Ext.BLANK_IMAGE_URL = '../../../js/ext/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';

    var start = 0;
    var pageSize = 15;
    var record = new Ext.data.Record.create([
        {name:'cn', mapping:'cn'} ,
        {name:'hzihpassword', mapping:'hzihpassword'} ,
        {name:'hzihdatadn', mapping:'hzihdatadn'} ,
        {name:'hzihuserpassword', mapping:'hzihuserpassword'} ,
        {name:'hzihprovince', mapping:'hzihprovince'},
        {name:'hzihcity', mapping:'hzihcity'},
        {name:'hzihorganization', mapping:'hzihorganization'},
        {name:'hzihinstitutions', mapping:'hzihinstitutions'},
        {name:'hzihid', mapping:'hzihid'},
        {name:'hzihusername', mapping:'hzihusername'},
        {name:'certificatetype', mapping:'certificatetype'},
        {name:'hzihphone', mapping:'hzihphone'},
        {name:'hzihaddress', mapping:'hzihaddress'},
        {name:'hzihemail', mapping:'hzihemail'},
        {name:'hzihcavalidity', mapping:'hzihcavalidity'},
        {name:'hzihcacreatedate', mapping:'hzihcacreatedate'},
        {name:'hzihcaenddate', mapping:'hzihcaenddate'},
        {name:'hzihjobnumber', mapping:'hzihjobnumber'},
        {name:'hzihcaentries', mapping:'hzihcaentries'},
        {name:'hzihparentcadn', mapping:'hzihparentcadn'},
        {name:'hzihcastatus', mapping:'hzihcastatus'}

    ]);

    var proxy = new Ext.data.HttpProxy({
        url:"../../ca.do?m=getEndUser",
        timeout: 20*60*1000
    });

    var reader = new Ext.data.JsonReader({
        totalProperty:"totalCount",
        root:"root"//,
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

    var boxM = new Ext.grid.CheckboxSelectionModel();   //复选框单选

    var rowNumber = new Ext.grid.RowNumberer();         //自动编号

    var colM = new Ext.grid.ColumnModel([
        boxM,
        rowNumber,
        {header:"用户名称", dataIndex:"cn", sortable:true, menuDisabled:true} ,
        {header:"身份证", dataIndex:"hzihid", sortable:true, menuDisabled:true} ,
        //{header:"联系电话", dataIndex:"hzihphone", sortable:true, menuDisabled:true} ,
        //{header:"联系地址", dataIndex:"hzihaddress", sortable:true, menuDisabled:true} ,
        // {header:"电子邮件", dataIndex:"hzihemail", sortable:true, menuDisabled:true} ,
        //  {header:"工号", dataIndex:"hzihjobnumber", sortable:true, menuDisabled:true} ,
        {header:"省", dataIndex:"hzihprovince", sortable:true, menuDisabled:true} ,
        {header:"市", dataIndex:"hzihcity", sortable:true, menuDisabled:true} ,
        {header:"组织", dataIndex:"hzihorganization", sortable:true, menuDisabled:true} ,
        {header:"机构", dataIndex:"hzihinstitutions", sortable:true, menuDisabled:true} ,
        // {header:"有效期", dataIndex:"hzihcavalidity", sortable:true, menuDisabled:true} ,
        // {header:"创建日期", dataIndex:"hzihcacreatedate", sortable:true, menuDisabled:true} ,
        // {header:"结束日期", dataIndex:"hzihcaenddate", sortable:true, menuDisabled:true} ,
        // {header:"状态", dataIndex:"hzihcastatus", sortable:true, menuDisabled:true} ,
        {header:'操作标记', dataIndex:'flag', sortable:true, menuDisabled:true, renderer:show_flag, width:300}
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

    /*   var tbar = new Ext.Toolbar({
     autoWidth :true,
     autoHeight:true,
     items:[ *//* '用户创建日期',
     new Ext.form.DateField({
     id:'hzih.tbar.model.createDate',
     name:'hzih.model.createDate'
     }),*//* '用户名', new Ext.form.TextField({
     name : 'username',
     id:'hzih.tbar.model.username'
     }), '身份证号码',
     new Ext.form.TextField({
     //minLength:15,
     id:'hzih.tbar.model.id',
     regex:/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})([0-9xX])$/,
     regexText:'请输入有效的身份证号',
     //minLength:18,
     name : 'id'
     }), *//* '联系电话',
     new Ext.form.TextField({
     id:'hzih.tbar.model.phone',
     name : 'phone'
     }),   '联系地址',
     new Ext.form.TextField({
     name : 'address',
     id:'hzih.tbar.model.address'
     }),    '电子邮件',
     new Ext.form.TextField({
     id:'hzih.tbar.model.email' ,
     name : 'email'
     }), *//*    '工号',
     new Ext.form.TextField({
     name : 'jobNumber',
     regex:/^[0-9]*$/,
     id:'hzih.tbar.model.jobNumber'
     }) , {
     id:'tbar.tbar.seniorSelect.info',
     xtype:'button',
     iconCls:'select',
     text:'查询',
     handler:function () {
     var username = Ext.getCmp('hzih.tbar.model.username').getValue();
     var id = Ext.getCmp('hzih.tbar.model.id').getValue();
     // var phone = Ext.getCmp('hzih.tbar.model.phone').getValue();
     // var address = Ext.getCmp('hzih.tbar.model.address').getValue();
     //var email = Ext.getCmp('hzih.tbar.model.email').getValue();
     var jobNumber = Ext.getCmp('hzih.tbar.model.jobNumber').getValue();
     //                    var relation = "1";
     store.setBaseParam('username', username);
     store.setBaseParam('id', id);
     //store.setBaseParam('phone', phone);
     //                    store.setBaseParam('relation', relation);
     //store.setBaseParam('address', address);
     //store.setBaseParam('email', email);
     store.setBaseParam('jobNumber', jobNumber);
     store.setBaseParam('phone', null);
     //                      store.setBaseParam('relation', relation);
     store.setBaseParam('address', null);
     store.setBaseParam('email', null);
     store.setBaseParam('province', null);
     store.setBaseParam('city', null);
     store.setBaseParam('organization', null);
     store.setBaseParam('institutions', null);
     store.load({
     params : {
     start : start,
     limit : pageSize
     }
     });
     Ext.getCmp('hzih.tbar.model.username').reset();
     Ext.getCmp('hzih.tbar.model.id').reset();
     Ext.getCmp('hzih.tbar.model.jobNumber').reset();
     }
     }, {
     id:'seniorSelect.info',
     xtype:'button',
     iconCls:'select',
     text:'高级查询',
     handler:function(){
     selectUserCa(grid_panel,store);
     }
     }]
     });*/
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
        sm:boxM,
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

function show_flag(value, p, r){
    if(parseInt(r.get("hzihcastatus"))==2){
        return String.format(
            '<a id="certificateIssued.info" href="javascript:void(0);" onclick="certificateIssued();return false;">发布证书</a>&nbsp;&nbsp;&nbsp;'
                +'<a id="viewDetailed.info" href="javascript:void(0);" onclick="viewDetailed();return false;">查看详细</a> &nbsp;&nbsp;&nbsp;'
        );
    }else if(parseInt(r.get("hzihcastatus"))<=3&&parseInt(r.get("hzihcastatus"))>2)  {
        return String.format(
//            '<a id="downloadCA.info" href="javascript:void(0);" onclick="downloadCA();return false;">下载证书</a>&nbsp;&nbsp;&nbsp;'
//                + '<a id="downloadCAList.info" href="javascript:void(0);" onclick="downloadCAList();return false;">下载证书链</a>&nbsp;&nbsp;&nbsp;'
            /*+ */'<a id="revokeCa.info" href="javascript:void(0);" onclick="revokeCa();return false;">吊销</a>&nbsp;&nbsp;&nbsp;'
                + '<a id="viewDetailed.info" href="javascript:void(0);" onclick="viewDetailed();return false;">查看详细</a> &nbsp;&nbsp;&nbsp;'
        );
    }
} ;         //操作标记



var revokeCa = function(){
    var grid = Ext.getCmp('grid.info');
    var recode = grid.getSelectionModel().getSelected();
    var CN =recode.get("cn");
    var DN  = recode.get("hzihdatadn");    Ext.MessageBox.show({
        title:'信息',
        width:250,
        msg:'确定要吊销?',
        animEl:'update_win.info',
        buttons:{'ok':'继续','no':'取消'},
        icon:Ext.MessageBox.WARNING,
        closable:false,
        fn:function(e){
            if(e == 'ok'){
                Ext.Ajax.request({
                    url: '../../ca.do?m=revokeCa',
                    timeout: 20*60*1000,
                    params:{DN:DN,CN:CN},
                    method: 'POST',
                    success : function(form, action) {
                        Ext.Msg.alert("提示", "吊销证书成功!");
                        grid.getStore().reload();
                    },
                    failure : function(result) {
                        Ext.Msg.alert("提示", "吊销证书失败!");
                        grid.getStore().reload();
                    }
                });
            }
        }
    });


};  //吊销证书



var viewDetailed = function(){
    var grid_panel = Ext.getCmp("grid.info");
    var recode = grid_panel.getSelectionModel().getSelected();
    var status =recode.get("hzihcastatus")
    var statusValue;
    if(status=="0"){
        statusValue= "新增"
    } else if(status=="1"){
        statusValue= "待批准"
    } else if(status=="2"){
        statusValue= "已批准"
    } else if(status=="3"){
        statusValue= "已发证"
    }  else if(status=="4"){
        statusValue= "已吊销"
    } else if(status=="5"){
        statusValue= "已废除"
    }
    var formPanel = new Ext.form.FormPanel({
        frame:true,
        width:800,
        autoScroll:true,
        baseCls : 'x-plain',
        labelWidth:150,
        labelAlign:'right',
        defaultWidth:300,
        layout:'form',
        border:false,
        defaults:{
            width:250
        },
        items:[
            new Ext.form.DisplayField({
                fieldLabel:'用户名称',
                value:recode.get("cn")
            }),
            /*  new Ext.form.DisplayField({
             fieldLabel:'DN',
             value:recode.get("hzihdatadn")
             }),*/
            new Ext.form.DisplayField({
                fieldLabel:'身份证',
                value:recode.get("hzihid")
            }),
            new Ext.form.DisplayField({
                fieldLabel:'联系电话',
                value:recode.get("hzihphone")
            }),
            new Ext.form.DisplayField({
                fieldLabel:'联系地址',
                value:recode.get("hzihaddress")
            }),
            new Ext.form.DisplayField({
                fieldLabel:'电子邮件',
                value:recode.get("hzihemail")
            }),
            new Ext.form.DisplayField({
                fieldLabel:'工号',
                value:recode.get("hzihjobnumber")
            }),
            new Ext.form.DisplayField({
                fieldLabel:'省',
                value:recode.get("hzihprovince")
            }),
            new Ext.form.DisplayField({
                fieldLabel:'市',
                value:recode.get("hzihcity")
            }),
            new Ext.form.DisplayField({
                fieldLabel:'组织',
                value:recode.get("hzihorganization")
            }),
            new Ext.form.DisplayField({
                fieldLabel:'机构',
                value:recode.get("hzihinstitutions")
            })  ,
            new Ext.form.DisplayField({
                fieldLabel:'状态',
                value:statusValue
            }),
            /*    new Ext.form.DisplayField({
             fieldLabel:'父CA',
             value:recode.get("hzihparentcadn")
             }),*/
            new Ext.form.DisplayField({
                fieldLabel:'截止日期',
                value:recode.get("hzihcavalidity")
            })  ,
            new Ext.form.DisplayField({
                fieldLabel:'创建日期',
                value:recode.get("hzihcacreatedate")
            })   ,
            new Ext.form.DisplayField({
                fieldLabel:'结束日期',
                value:recode.get("hzihcaenddate")
            })
        ]
    });

    var select_Win = new Ext.Window({
        title:"用户证书详细",
        width:800,
        layout:'fit',
        height:380,
        modal:true,
        items:formPanel
    });
    select_Win.show();
};         //查看详细

var certificateIssued = function(){
    var typeData = [
        ['1024','1024 bits'],['2048','2048 bits'],['4096','4096 bits']
    ];
    var typeDataStore = new Ext.data.SimpleStore({
        fields:['id','name'],
        data:typeData
    });
    var grid = Ext.getCmp('grid.info');
    var recode = grid.getSelectionModel().getSelected();
    var CN =recode.get("cn");
    var DN =   recode.get("hzihdatadn");
    var validate = recode.get("hzihcavalidity");
    var hzihprovince =recode.get("hzihprovince");
    var hzihcity =recode.get("hzihcity");
    var hzihid =recode.get("hzihid");
    var password = recode.get("hzihpassword");
    var hzihorganization =recode.get("hzihorganization");
    var hzihinstitutions =recode.get("hzihinstitutions");
    var type = recode.get("certificatetype");
    var formPanel = new Ext.form.FormPanel({
        border:false,
        plain:true,
        frame:true,
        autoScroll:true,
        labelWidth:150,
        labelAlign:'right',
        defaultWidth:300,
        autoWidth:true,
        layout:'form',
        padding:0,
        border:false,
        defaults : {
            width : 250,
            allowBlank : false,
            blankText : '该项不能为空！'
        },
        items:[
            new Ext.form.ComboBox({
                fieldLabel : '密钥长度',
                typeAhead: true,
                triggerAction: 'all',
                forceSelection: true,
                mode: 'local',
                hiddenName:"keyLength",
                store: typeDataStore,
                valueField: 'id',   //下拉框具体的值（例如值为SM，则显示的内容即为‘短信’）
                displayField: 'name'//下拉框显示内容
            })]
    });
    var win = new Ext.Window({
        title:"发证",
        width:500,
        layout:'fit',
        height:120,
        modal:true,
        items: formPanel,
        bbar:[
            '->',
            {
                id:'certificateIssued.win',
                text:'发布证书',
                handler:function(){
                    if (formPanel.form.isValid()) {
                        formPanel.getForm().submit({
                            url : '../../ca.do?m=release',
                            timeout: 20*60*1000,
                            params :{type:type,hzihid:hzihid,DN:DN,CN:CN,validate:validate,password:password,
                                hzihprovince:hzihprovince,hzihcity:hzihcity,hzihorganization:hzihorganization,
                                hzihinstitutions:hzihinstitutions},
                            method :'POST',
                            waitTitle :'系统提示',
                            waitMsg :'正在连接...',
                            success : function() {
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:250,
                                    msg:'请求成功,点击返回页面!',
                                    
                                    buttons:{'ok':'确定'},
                                    icon:Ext.MessageBox.INFO,
                                    closable:false,
                                    fn:function(e){
                                        grid.getStore().reload();
                                        win.close();
                                    }
                                });
                            },
                            failure : function() {
                                Ext.MessageBox.show({
                                    title:'信息',
                                    width:250,
                                    msg:'请求失败，请与管理员联系!',
                                    
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
                            
                            buttons:{'ok':'确定'},
                            icon:Ext.MessageBox.ERROR,
                            closable:false
                        });
                    }
                }
            },{
                text:'重置',
                handler:function(){
                    formPanel.getForm().reset();
                }
            }
        ]
    }).show();
};   //发布证书





