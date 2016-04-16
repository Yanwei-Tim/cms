// 基本配置
Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';

	var comboSysClass = new Ext.data.JsonStore({
		autoLoad : true,
		url : '../../sysClass.do?m=comboSysClass',
		sotreId : 'comboSysClass',
		root : 'results',
		idProperty : 'id',
		fields : ['id', 'name']
	});
	comboSysClass.load();
	var comboParent = new Ext.data.JsonStore({
				autoLoad : true,
				url : '../../district.do?m=comboParent',
				sotreId : 'comboParent',
				root : 'results',
				idProperty : 'id',
				fields : ['id', 'name']
			});
	comboParent.load();
    var recordRole = new Ext.data.Record.create([{name:'id',mapping:'id'}, {name:'name',mapping:'name'}]);
    var readerRole = new Ext.data.JsonReader({ totalProperty:'total',root:"results"},recordRole);
    var cbxBusinessDepart = new Ext.data.Store({
        url : '../../orgcode.do?m=comboParent',
       reader:readerRole
    });
    cbxBusinessDepart.load();
	var comboChildJsdw = new Ext.data.JsonStore({
				autoLoad : true,
				url : '',
				storeId : 'comboChildJsdw',
				root : 'results',
				idProperty : 'id',
				fields : ['id', 'name']
			});
	var comboChildSpdw = new Ext.data.JsonStore({
				autoLoad : true,
				url : '',
				storeId : 'comboChildSpdw',
				root : 'results',
				idProperty : 'id',
				fields : ['id', 'name']
			});
	var comboChildMainComp = new Ext.data.JsonStore({
				autoLoad : true,
				url : '',
				storeId : 'comboChildMainComp',
				root : 'results',
				idProperty : 'id',
				fields : ['id', 'name']
			});

	var reader = new Ext.data.JsonReader({}, [{
						name : 'id'
					}, {
						name : 'platformName'
					}, {
						name : 'platformId'
					}, {
						name : 'systemClass'
					}, {
						name : 'address'
					}, {
						name : 'fzrName'
					}, {
						name : 'fzrPhone'
					}, {
						name : 'fzrEmail'
					}, {
						name : 'fzrOhterPhone'
					}, {
						name : 'jksysIp'
					}, {
						name : 'jksysMac'
					}, {
						name : 'jsdw'
					}, {
						name : 'zycjdw'
					}, {
						name : 'js_day',
						mapping : 'jsDay'
					}, {
						name : 'bmxy'
					}, {
						name : 'spdw'
					}, {
						name : 'sp_day',
						mapping : 'spDay'
					}, {
						name : 'spph'
					}, {
						name : 'spcl'
					}, {
						name : 'technologySolution'
					}, {
						name : 'confidentialAgreement'
					}, {
						name : 'mainComp'
					}, {
						name : 'maintainerName'
					}, {
						name : 'maintainerPhone'
					}, {
						name : 'maintainerOhterPhone'
					}, {
						name : 'maintainerEmail'
					}, {
						name : 'parentJsdw'
					}, {
						name : 'jsdwChildName'
					}, {
						name : 'parentSpdw'
					}, {
						name : 'spdwChildName'
					}, {
						name : 'parentMainComp'
					}, {
						name : 'mainCompChildName'
					}]);
    var comb=[["S","移动警务安全接入系统"],["P","边界安全接入平台"]];
	var fp = new Ext.FormPanel({
		id : 'fp',
		frame : true,
		autoScroll : true,
		title : '',
		border : false,
		layout : 'fit',
		labelAlign : 'right',
		labelWidth : 140,
		autoWidth : true,
		waitMsgTarget : true,
		renderTo : document.body,
		reader : reader,
		fileUpload : true,
		items : [
            new Ext.form.FieldSet({
                title : '平台注册',
                autoHeight : true,
                autoWidth : true,
                defaultType : 'textfield',
                items : [
                    {
                        xtype : 'hidden',
                        name : 'id',
                        id : 'id',
                        value : '1',
                        width : 300
                    }, {
                        fieldLabel : '平台名称',
                        name : 'platformName',
                        id : 'platformName',
                        allowBlank:false,
                        regex:/^.{2,100}$/,
                        regexText:'请输入任意2--100个字符',
                        emptyText:'请输入任意2--100个字符',
                        width : 300
                    }, {
                        name : 'sc',
                        id : 'sc',
                        fieldLabel : '平台类型',
                        xtype : 'combo',
                        width : 300,
//                            hiddenName : 'sc',
                        valueField : 'id',
                        displayField : 'name',
                        mode : 'remote',
                        emptyText : '请选择',
                        allowBlank : false,
                        store : comb,
                        selectOnFocus : true,
                        editable : false,
                        triggerAction : 'all',
                        listeners: {
                            select : function(combo, record, index) {
                                var pid = Ext.getCmp("platformId").value;
                                if(Ext.getCmp("sc").getValue()=="S"){
                                    if(pid==null||pid==undefined){
                                         Ext.getCmp("platformId").setValue("S")
                                    }else{
                                       var first= pid.substring(0,1);
                                        if(first!="S") {
                                            if(first =="P"){
                                                Ext.getCmp("platformId").setValue("S"+pid.substring(1))
                                            }else{
                                                Ext.getCmp("platformId").setValue("S"+pid)
                                            }
                                        }

                                    }
                                }
                                if(Ext.getCmp("sc").getValue()=="P"){
                                    if(pid==null||pid==undefined){
                                        Ext.getCmp("platformId").setValue("P")
                                    }else{
                                        var first= pid.substring(0,1);
                                        if(first!="P") {
                                            if(first =="S"){
                                                Ext.getCmp("platformId").setValue("P"+pid.substring(1))
                                            }else{
                                                Ext.getCmp("platformId").setValue("P"+pid)
                                            }
                                        }

                                    }
                                }
                            }
                        },
                        loadingText : '加载中...'
                    }, {
                        fieldLabel : '平台标识（机构代码）',
                        name : 'platformId',
                        id : 'platformId',
                        allowBlank:false,
                        regex: /^[P|S]\d{8}$/,
                        regexText:'P+八位数或者S+八位数',
                        emptyText:'例:"P22002200",或者"S22002200"',
                        listeners: {
                            blur : function() {
                                var sc = Ext.getCmp("sc").value;
                                var pid = this.getValue();
                                var first=pid.substring(0,1);
                                if(first!=sc){
                                    Ext.getCmp("platformId").setValue(sc);
                                    Ext.MessageBox.show({
                                        title:"消息",
                                        msg:"请输入对应平台值,\"如:边界安全接入平台=P22000001\",\"移动警务安全接入系统=S22000001\""
                                    }) ;

                                }

                            }
                        },
                        width : 300
                    },{
                        name : 'systemClass',
                        id : 'sysClass',
                        fieldLabel : '平台所属部门',
                        xtype : 'combo',
                        width : 300,
                        hiddenName : 'systemClass',
                        valueField : 'id',
                        displayField : 'name',
                        mode : 'remote',
                        emptyText : '请选择',
                        allowBlank : false,
                        store : comboSysClass,
                        selectOnFocus : true,
                        editable : false,
                        triggerAction : 'all',
                        loadingText : '加载中...'
                    }, {
                        fieldLabel : '物理地址',
                        name : 'address',
                        id : 'address',
                        allowBlank:false,
                        regex:/^.{2,100}$/,
                        regexText:'请输入任意2--100个字符',
                        emptyText:'请输入任意2--100个字符',
                        width : 300
                    }, {
                        fieldLabel : '负责人姓名',
                        name : 'fzrName',
                        id : 'fzrName',
                        allowBlank:false,
                        regex:/^.{2,100}$/,
                        regexText:'请输入任意2--100个字符',
                        emptyText:'请输入任意2--100个字符',
                        width : 300
                    }, {
                        fieldLabel : '负责人电话',
                        name : 'fzrPhone',
                        id : 'fzrPhone',
                        allowBlank:false,
                        regex:/^.{2,100}$/,
                        regexText:'请输入任意2--100个字符',
                        emptyText:'请输入任意2--100个字符',
                        width : 300
                    }, {
                        fieldLabel : '负责人邮件',
                        name : 'fzrEmail',
                        id : 'fzrEmail',
                        allowBlank:false,
                        regex:/^.{2,100}$/,
                        regexText:'请输入任意2--100个字符',
                        emptyText:'请输入任意2--100个字符',
                        width : 300
                    }, {
                        fieldLabel : '负责人其他联系方式',
                        name : 'fzrOhterPhone',
                        id : 'fzrOhterPhone',
                        allowBlank:false,
                        regex:/^.{2,100}$/,
                        regexText:'请输入任意2--100个字符',
                        emptyText:'请输入任意2--100个字符',
                        width : 300
                    }, {
                        fieldLabel : '本监控系统IP',
                        name : 'jksysIp',
                        id : 'jksysIp',
                        width : 300,
                        allowBlank:false,
                        regex:/^((((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9]))(\:(6553[0-6]|655[0-2][0-9]|65[0-4][0-9]{2}|6[0-4][0-9]{3}|[1-5][0-9]{4}|[1-9][0-9]{3}|[1-9][0-9]{2}|[1-9][0-9]{1}|[1-9])){1,2})$/,										regexText:'不规范(例):0.0.0.0~255.255.255.255',
                        regexText:'不规范(例):1.1.1.1:8080',
                        emptyText:'(例):1.1.1.1:8080'
                    }, {
                        fieldLabel : '本监控系统MAC',
                        name : 'jksysMac',
                        id : 'jksysMac',
                        width : 300,
                        allowBlank:false,
                        regex:/^(([0-9a-fA-F]{2}\:){5}[0-9a-fA-F]{2})$/,
                        regexText:'不规范(例):0a:45:be:e6:00:AF,必须是[a-fA-F]之间的字母或者数字',
                        emptyText:'(例):0a:45:bE:e6:00:AF'
                            }]
                    }), new Ext.form.FieldSet({
					title : '平台建设信息注册',
					autoHeight : true,
					autoWidth : true,
					defaultType : 'textfield',
					items : [{
                        fieldLabel:'建设单位',
                        name:'ad',
                        id:'ad',
                        xtype : 'combo',
                        width : 300,
                        hiddenName : 'jsdw',
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
						name : '_zycjdw',
						id : '_zycjdw',
						fieldLabel : '主要承建单位',
						xtype : 'combo',
						width : 300,
						hiddenName : 'zycjdw',
						valueField : 'id',
						displayField : 'name',
						mode : 'local',
						emptyText : '请选择',
						allowBlank : false,
						store : new Ext.data.SimpleStore({
									fields : ['name', 'id'],
									data : [['一所', 'YS'],
                                             ['信大', 'XD'],
                                             ['富星', 'FX'],
                                             ['国宝金泰', 'JT'],
                                             ['合众','HZ'],
                                             ['辰锐', 'CR'],
                                             ['天行', 'TX']
									]
								}),
						selectOnFocus : true,
						editable : false,
						triggerAction : 'all',
						loadingText : '加载中...'
					}, {
						fieldLabel : '建设日期',
						name : 'js_day',
						id : 'js_day',
						xtype : 'datetimefield',
						format : 'Y-m-d H:i:s',
                        allowBlank : false,
						width : 300
					}, {
						fieldLabel : '是否签署保密协议',
						name : 'bmxy',
						id : 'bmxy',
						xtype : 'radiogroup',
						width : 100,
						items : [{
									boxLabel : '是',
									name : 'bmxy',
									inputValue : 'Y',
									checked : true
								}, {
									boxLabel : '否',
									name : 'bmxy',
									inputValue : 'N'
								}]
					}, {
                        fieldLabel:'审批单位',
                        name:'sdw',
                        id:'sdw',
                        xtype : 'combo',
                        width : 300,
                        hiddenName : 'spdw',
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

					}, /*{
						name : 'spdwChildName',
						id : 'spdwChildName',
						fieldLabel : '',
						xtype : 'combo',
						width : 300,
						hiddenName : 'spdw',
						valueField : 'id',
						displayField : 'name',
						mode : 'remote',
						emptyText : '请选择',
						allowBlank : false,
						store : comboChildSpdw,
						selectOnFocus : true,
						editable : true,
						triggerAction : 'all',
						loadingText : '加载中...'
					},*/ {
						fieldLabel : '审批日期',
						name : 'sp_day',
						id : 'sp_day',
						xtype : 'datetimefield',
						format : 'Y-m-d H:i:s',
                        allowBlank : false,
						width : 300
					}, {
						fieldLabel : '审批批号',
						name : 'spph',
						id : 'spph',
                        allowBlank : false,
						width : 300
					}, {
						fieldLabel : '审批材料',
						name : 'spcl',
						id : 'spcl',
						inputType : 'file',
						width : 300
					}, {
						id : 'spcldownload',
						xtype : 'displayfield',
						value : '<a href="javascript:download(\'spcl\');">下载</a>'
					}, {
						fieldLabel : '技术方案',
						name : 'technologySolution',
						id : 'technologySolution',
						inputType : 'file',
						width : 300
					}, {
						id : 'tsdownload',
						xtype : 'displayfield',
						value : '<a href="javascript:download(\'ts\');">下载</a>'
					}, {
						fieldLabel : '保密协议',
						name : 'confidentialAgreement',
						id : 'confidentialAgreement',
						inputType : 'file',
						width : 300
					}, {
						id : 'cadownload',
						xtype : 'displayfield',
						value : '<a href="javascript:download(\'ca\');">下载</a>'
					}]
				}), new Ext.form.FieldSet({
					title : '平台运维信息注册',
					autoHeight : true,
					autoWidth : true,
					defaultType : 'textfield',
					items : [
                        {
                            fieldLabel:'运维单位',
                            name:'ywdw',
                            id:'ywdw',
                            xtype : 'combo',
                            width : 300,
                            hiddenName : 'mainComp',
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
                        },/*{
						name : '_parentMainCompId',
						id : '_parentMainCompId',
						fieldLabel : '运维单位',
						xtype : 'combo',
						width : 300,
						hiddenName : 'parentMainComp',
						valueField : 'id',
						displayField : 'name',
						mode : 'remote',
						emptyText : '请选择',
						allowBlank : false,
						store : comboParent,
						selectOnFocus : true,
						editable : true,
						triggerAction : 'all',
						loadingText : '加载中...',
						listeners : {
							select : function(combo, record, index) {
								Ext.getCmp('mainCompChildName').clearValue();
								comboChildMainComp.proxy = new Ext.data.HttpProxy({
									url : '../../district.do?m=comboChild&parentId='
											+ Ext.getCmp('_parentMainCompId').value
								});
								comboChildMainComp.load();
							}
						}
					}, {
						name : 'mainCompChildName',
						id : 'mainCompChildName',
						fieldLabel : '',
						xtype : 'combo',
						width : 300,
						hiddenName : 'mainComp',
						valueField : 'id',
						displayField : 'name',
						mode : 'remote',
						emptyText : '请选择',
						allowBlank : false,
						store : comboChildMainComp,
						selectOnFocus : true,
						editable : true,
						triggerAction : 'all',
						loadingText : '加载中...'
					},*/ {
						fieldLabel : '管理员姓名',
						name : 'maintainerName',
						id : 'maintainerName',
                        allowBlank : false,
						width : 300
					}, {
						fieldLabel : '管理员电话',
						name : 'maintainerPhone',
						id : 'maintainerPhone',
                        allowBlank : false,
						width : 300
					}, {
						fieldLabel : '管理员邮箱',
						name : 'maintainerEmail',
						id : 'maintainerEmail',
                        allowBlank : false,
						width : 300
					}, {
						fieldLabel : '管理员其他联系方式',
						name : 'maintainerOhterPhone',
						id : 'maintainerOhterPhone',
                        allowBlank : false,
						width : 300
					}, {
						fieldLabel : '平台拓扑图',
						name : 'platformTp',
						id : 'platformTp',
						inputType : 'file',
						width : 300
					}, {
						id : 'tpdownload',
						xtype : 'displayfield',
						value : '<a href="javascript:download(\'tp\');">下载</a>'
					}]
				})],
		buttons : [{
					text : '保存',
					listeners : {
						'click' : function() {
                            if (fp.form.isValid()) {
							    fp.getForm().submit({
                                    clientValidation : true,
                                    url : '../../baseConf.do?m=update',
                                    success : function(form, action) {
                                        var msg = action.result.msg;
                                        Ext.Msg.alert('温馨提示', msg);
                                    },
                                    failure : function(form, action) {
                                        Ext.Msg.alert('保存失败',
                                                '系统错误，请联系管理员。');
                                    }
									});
                            }
                            else {
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
					}
				}, {
					text : '取消',
					listeners : {
						'click' : function() {
							fp.getForm().reset();
						}
					}
				}]
	});

	// 加载配置数据
	if (fp) {
		fp.getForm().load({
					url : '../../baseConf.do?m=detail',
                    success: function(form, action){
                       var pid= Ext.getCmp("platformId").value;
                        Ext.getCmp("sc").setValue(pid.substring(0,1))
                    },
					failure : function(form, action) {
						Ext.Msg.alert('错误', '加载数据出错！');
					}
				});
	}
	var viewport = new Ext.Viewport({
				layout : 'fit',
				border : false,
				items : [fp]
			});

});

function download(type) {
	var id = Ext.getCmp('fp').reader.jsonData[0].id;
	window.open('../../baseConf.do?m=download&id=' + id + '&type=' + type,
			'_blank');
}
