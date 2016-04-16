/**
 * 日志下载
 */
Ext.onReady(function(){
    Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget ='side';

//    var pageStart = 0;
//    var pageSize = 15;
    var tb = new Ext.Toolbar({
        width : 720,
        height : 30,
        items : [{
                xtype : 'tbspacer',
                width : 10
            }, {
                id:'clear.info',
                text : '清空所有日志' ,
                listeners : {
                'click' : function() {
                    Ext.MessageBox.show({
                        title:'信息',
                        width:250,
                        msg:'确定要清空日志?',
                        animEl:'clear.info',
                        buttons:{'ok':'确定','no':'取消'},
                        icon:Ext.MessageBox.QUESTION,
                        closable:false,
                        fn:function(e){
                            if(e=='ok'){
                                Ext.Ajax.request({
                                    url:"../../download.do?m=deleteLog",
                                    success : function(response, options) {
                                        var respText = Ext.util.JSON.decode(response.responseText);
                                        var msg = respText.msg;

                                        Ext.MessageBox.show({
                                            title:'信息',
                                            width:300,
                                            msg: msg,
                                            animEl:'clear.info',
//                                            buttons:Ext.MessageBox.OK,
                                            buttons:{'ok':'确定'},
                                            icon:Ext.MessageBox.INFO,
                                            closable:false,
                                            fn:function(e){
                                                if(e=='ok'){
                                                    internal_logGrid.render();
                                                    internal_store.reload();
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    });
                }
            }
            }]
    });
	var internal_record = new Ext.data.Record.create([
        {name:'fileName',mapping:'fileName'},
        {name:'Desc',mapping:'Desc'}
    ]);
    var internal_proxy = new Ext.data.HttpProxy({
        url:"../../download.do?m=readLocalLogName"
    });
    var internal_reader = new Ext.data.JsonReader({
        totalProperty:"total",
        root:"rows"
    },internal_record);
    var internal_store = new Ext.data.Store({
        proxy : internal_proxy,
        reader : internal_reader
    });
    internal_store.load();
	
//	var internal_logBoxM = new Ext.grid.CheckboxSelectionModel();   //复选框
    var internal_logRowNumber = new Ext.grid.RowNumberer();         //自动 编号
    var internal_logColM = new Ext.grid.ColumnModel([
//        internal_logBoxM,
        internal_logRowNumber,
        {header:"下载本地日志文件名",dataIndex:"fileName",align:'center',menuDisabled:true/*,renderer : internal_logDownloadShowUrl*/},
        {header:"说明",dataIndex:"Desc",align:'center',menuDisabled:true},
        {header:"操作标记",dataIndex:"fileName",align:'center',menuDisabled:true,renderer : show_falg}
    ]);

    var internal_logGrid = new Ext.grid.GridPanel({
        id:'grid',
        plain:true,
        animCollapse:true,
        height:300,
        loadMask:{msg:'正在加载数据，请稍后...'},
        border:false,
        collapsible:false,
        cm:internal_logColM,
//        sm:internal_logBoxM,
        store:internal_store,
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
        tbar:tb
    });
    
    /*var external_record = new Ext.data.Record.create([
        {name:'externalLog',mapping:'externalLog'}
    ]);
    var external_proxy = new Ext.data.HttpProxy({
    	url:"../../DownLoadAction_readRemoteLogName.action"
    });
    var external_reader = new Ext.data.JsonReader({
    	totalProperty:"total",
    	root:"rows"
    },external_record);
    var external_store = new Ext.data.Store({
    	proxy : external_proxy,
    	reader : external_reader
    });
    external_store.load();
                                              	
    var external_logBoxM = new Ext.grid.CheckboxSelectionModel();   //复选框
    var external_logRowNumber = new Ext.grid.RowNumberer();         //自动 编号
    var external_logColM = new Ext.grid.ColumnModel([
    	external_logBoxM,
    	external_logRowNumber,
        {header:"下载远程日志文件名",dataIndex:"fileName",align:'center',renderer : external_logDownloadShowUrl}
    ]);

    var external_logGrid = new Ext.grid.GridPanel({
    	plain:true,
    	animCollapse:true,
    	height:300,
    	loadMask:{msg:'正在加载数据，请稍后...'},
    	border:false,
    	collapsible:false,
    	cm:external_logColM,
    	sm:external_logBoxM,
    	store:external_store,
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
    	}
    });*/
    new Ext.Viewport({
    	border:false,
    	renderTo:Ext.getBody(),
        layout:'fit',
        items:[internal_logGrid]
        /*[{
        	layout:'column',
        	items:[
        		{height:setHeight(),border:false,items:[internal_logGrid],columnWidth:.5},
        		{height:setHeight(),border:false,items:[external_logGrid],columnWidth:.5}
        	]
    	}]*/
    });
});

function setHeight(){
	var h = document.body.clientHeight-8;
	return h;
}

function internal_logDownloadShowUrl(value){
	var type = 'internal_log';
	return "<a href='javascript:;' onclick='download_log(\""+value+"\",\""+type+"\");'>"+value+"</a>";
}
function show_falg(value){
	var type = 'internal_log';
    return "<a href='javascript:;' onclick='download_log(\""+value+"\",\""+type+"\");'>"+"日志下载"+"</a>" +"&nbsp;&nbsp;&nbsp;"
        +"<a href='javascript:;' onclick='deleteLog(\""+value+"\");'>"+"日志清空"+"</a>";
}
function external_logDownloadShowUrl(value){
    var type = 'external_log';
    return "<a href='javascript:;' onclick='download_log(\""+value+"\",\""+type+"\");'>"+value+"</a>";
}
function download_log(logName,type){
    if (!Ext.fly('test')) {
        var frm = document.createElement('form');
        frm.id = 'test';
        frm.name = id;
        frm.style.display = 'none';
        document.body.appendChild(frm);
    }
    Ext.Ajax.request({
        url: '../../download.do?m=download&type='+type+'&fileName='+logName,
        form: Ext.fly('test'),
        isUpload: true
    });
}function deleteLog(logName){
    Ext.Ajax.request({
        url:"../../download.do?m=deleteLog&filename="+logName,
        success : function(response, options) {
            var respText = Ext.util.JSON.decode(response.responseText);
            var msg = respText.msg;

            Ext.MessageBox.show({
                title:'信息',
                width:300,
                msg: msg,
//                animEl:'clear.info',
                buttons:{'ok':'确定'},
                icon:Ext.MessageBox.INFO,
                closable:false,
                fn:function(e){
                    if(e=='ok'){
                        Ext.getCmp("grid").render();
                        Ext.getCmp("grid").getStore().reload();
                    }
                }
            });
        }
    });


}

