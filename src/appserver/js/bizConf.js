// 业务注册管理
var listGrid;
var editWindow;
var listStore;
var editAction = "";

Ext.onReady(function() {

	Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';

	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'qtip';

	var listRecord = Ext.data.Record.create(['id', 'businessName',
			'businessDesc', 'businessDepart', 'businessCode', 'linkName',
			'businessAdmin', 'registerDate', 'authDate']);

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

	/*
	 * listStore.on('load', function() { if (listStore.data.length == 0) {
	 * Ext.Msg.alert('提示', '没有搜索到符合条件的数据！'); } });
	 */

	var sm = new Ext.grid.CheckboxSelectionModel();
	listGrid = new Ext.grid.GridPanel({
		region : 'center',
		border : false,
		store : listStore,
		tbar : [{
					pressed : false,
					text : '新增',
					id : 'add_btn',
					iconCls : 'add',
					handler : function() {
						showAddWindow();
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
            }, {
                header : '操作',
                align : 'center',
                dataIndex : 'id',
                menuDisabled : true,
                width : 40,
                renderer : function(value, p, r) {
                    return String
                            .format('<a href="javascript:void(0);" onclick="showDetail();return false;">详细</a>'
                                    + '&nbsp;&nbsp;'
                                    + '<a href="javascript:void(0);" onclick="showUpdate();return false;">修改</a>');
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

	editWindow = new Ext.Window({
				width : 670,
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


                                var value1=Ext.getDom("value1").value;//业务管理部门
                                var businessCode=Ext.getDom("businessCode").value;//业务类型
                                var linkName=Ext.getDom("linkName").value;//链路名
                                var businessExchModel=Ext.getDom("businessExchModel").value;//业务交换方式
                                var value2=Ext.getDom("value2").value;//审批部门
                                var exchange=Ext.getDom("exchange").value;//交换方向
                                var exchProtocol=Ext.getDom("exchProtocol").value;//业务交换协议
                                var value3=Ext.getDom("value3").value;//备案单位
                                var value4=Ext.getDom("value4").value;//使用单位
                                var value5=Ext.getDom("value5").value;
                                var businessName=Ext.getDom("businessName").value;//业务系统名称
                                var businessDesc=Ext.getDom("businessDesc").value;//业务系统描述
                                var businessAdmin=Ext.getDom("businessAdmin").value;//业务主管人
                                var adminPhone=Ext.getDom("adminPhone").value;//业务主管人电话
                                var adminEmail=Ext.getDom("adminEmail").value;//业务主管人邮件
                                var adminOtherPhone=Ext.getDom("adminOtherPhone").value;//业务主管人其他联系方式
                                var auth_date=Ext.getDom("auth_date").value;//审批时间
                                var authSerial=Ext.getDom("authSerial").value;//审批编号
                                var register_date=Ext.getDom("register_date").value;//注册时间
                                var dayDataVolume=Ext.getDom("dayDataVolume").value;//日数据交换量
                                var businessProtocol=Ext.getDom("businessProtocol").value;//业务协议名
                                var protocolDesc=Ext.getDom("protocolDesc").value;//协议描述
                                var sourceIpRange=Ext.getDom("sourceIpRange.info").value;//源ip地址范围
                                var sourcePortRange=Ext.getDom("sourcePortRange.info").value;//源端口范围
                                var destIpRange=Ext.getDom("destIpRange.info").value;//目标ip地址范围
                                var destPortRange=Ext.getDom("destPortRange.info").value;//目标端口范围
                                var useDepartAddress=Ext.getDom("useDepartAddress").value;//使用单位物理地址
                                var useDepartAdminName=Ext.getDom("useDepartAdminName").value;//使用单位主管姓名
                                var useDepartAdminPhone=Ext.getDom("useDepartAdminPhone").value;//使用单位主管电话
                                var useDepartAdminEmail=Ext.getDom("useDepartAdminEmail").value;//使用单位主管邮件
                                var useDepartAdminOhterPhone=Ext.getDom("useDepartAdminOhterPhone").value;//使用单位主管其他联系方式
                                var terminalAmount=Ext.getDom("terminalAmount").value;//终端数量
                                var userAmount=Ext.getDom("userAmount").value;//用户数量


                                if(value1==""){
                                    Ext.Msg.alert('提示', '请选择业务管理部门');
                                }else if(businessCode==""){
                                    Ext.Msg.alert('提示', '请选择业务类型');
                                }else  if(linkName==""){
                                    Ext.Msg.alert('提示', '请选择链路名');
                                }else  if(businessExchModel==""){
                                    Ext.Msg.alert('提示', '请选择业务交换方式');
                                }else   if(value2==""){
                                    Ext.Msg.alert('提示', '请选择审批部门');
                                }else  if(exchange==""){
                                    Ext.Msg.alert('提示', '请选择交换方向');
                                }else if(exchProtocol==""){
                                    Ext.Msg.alert('提示', '请选择业务交换协议');
                                }else if(value3==""){
                                    Ext.Msg.alert('提示', '请选择备案单位');
                                }else if(value4==""){
                                    Ext.Msg.alert('提示', '请选择使用单位');
                                }else if(value5==""){
                                    Ext.Msg.alert('提示', '请选择使用单位类型');
                                }else if(businessName==""){
                                    Ext.Msg.alert('提示', '请输入业务系统名称');
                                }else if(businessDesc==""){
                                    Ext.Msg.alert('提示', '请输入业务系统描述');
                                }else if(businessAdmin==""){
                                    Ext.Msg.alert('提示', '请输入业务主管人姓名');
                                }else if(adminPhone==""){
                                    Ext.Msg.alert('提示', '请输入业务主管人电话');
                                }else if(adminEmail==""){
                                    Ext.Msg.alert('提示', '请输入业务主管人邮件');
                                }else if(adminOtherPhone==""){
                                    Ext.Msg.alert('提示', '请输入业务主管人其他联系方式');
                                }else if(auth_date==""){
                                    Ext.Msg.alert('提示', '请输入审批时间');
                                }else if(authSerial==""){
                                    Ext.Msg.alert('提示', '请输入审批编号');
                                }else if(register_date==""){
                                    Ext.Msg.alert('提示', '请输入注册时间');
                                }else if(dayDataVolume==""){
                                    Ext.Msg.alert('提示', '请输入日数据交换量');
                                }else if(businessProtocol==""){
                                    Ext.Msg.alert('提示', '请输入业务协议名');
                                }else if(protocolDesc==""){
                                    Ext.Msg.alert('提示', '请输入业务协议描述');
                                }else if(sourceIpRange==""){
                                    Ext.Msg.alert('提示', '请输入源Ip地址范围');
                                }else if(sourcePortRange==""){
                                    Ext.Msg.alert('提示', '请输入源端口范围');
                                }else if(destIpRange==""){
                                    Ext.Msg.alert('提示', '请输入目标Ip地址范围');
                                }else if(destPortRange==""){
                                    Ext.Msg.alert('提示', '请输入目标端口范围');
                                }else if(useDepartAddress==""){
                                    Ext.Msg.alert('提示', '请输入使用单位物理地址');
                                }else if(useDepartAdminName==""){
                                    Ext.Msg.alert('提示', '请输入使用单位主管姓名');
                                }else if(useDepartAdminPhone==""){
                                    Ext.Msg.alert('提示', '请输入使用单位主管电话');
                                }else if(useDepartAdminEmail==""){
                                    Ext.Msg.alert('提示', '请输入使用单位主管邮件');
                                }else if(useDepartAdminOhterPhone==""){
                                    Ext.Msg.alert('提示', '请输入使用单位主管其他联系方式');
                                }else if(terminalAmount==""){
                                    Ext.Msg.alert('提示', '请输入终端数量');
                                }else if(userAmount==""){
                                    Ext.Msg.alert('提示', '请输入用户数量');
                                }else{
                                        Ext.Ajax.request({
                                                form : 'editForm',
                                                success : function(reponse, options) {
                                                    var respText = Ext.util.JSON.decode(reponse.responseText);
                                                    var msg = respText.msg;
                                                    listStore.reload();
                                                    Ext.Msg.alert('温馨提示', msg);
                                                    editWindow.hide();
                                                }
                                            });
                                }

							}
						}, {
							text : '关闭',
							handler : function() {
								editWindow.hide();
							}
						}],
				listeners : {
					'hide' : function() {
						// editForm.getForm().reset();
					}
				}
			});

	function showAddWindow() {
		editWindow.setTitle("新业务注册", "win_add");
		editWindow.removeAll(true);
		editWindow.show();
		editWindow.load({
					url : '../../bizConf.do?m=showAdd',
					callback : function() {

					},
					scripts : true
				});
	}

	var viewport = new Ext.Viewport({
				layout : 'border',
				border : false,
				items : [listGrid]
			});

	listStore.load();

}); // / Ext onReady end!

function showDetail() {
	var selectGridRow = listGrid.getSelectionModel().getSelected();
	editWindow.setTitle("详细信息", "win_add");
	editWindow.removeAll(true);
	editWindow.show();
	editWindow.load('../../bizConf.do?m=detail&id=' + selectGridRow.data.id);
}

function showUpdate() {
	var selectGridRow = listGrid.getSelectionModel().getSelected();
	editWindow.setTitle("修改信息", "win_add");
	editWindow.removeAll(true);
	editWindow.show();
	editWindow.load({
				url : '../../bizConf.do?m=showUpdate&id='
						+ selectGridRow.data.id,
				callback : function() {
					// init();
				},
				scripts : true
			});
}

