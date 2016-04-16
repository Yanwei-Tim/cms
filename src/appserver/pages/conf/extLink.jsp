<%@page contentType="text/html;charset=utf-8"%>
<%@include file="/taglib.jsp"%>

<html version="-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<title>外部链路配置</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<META http-equiv="x-ua-compatible" content="ie=EmulateIE7" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/js/ext/resources/css/ext-all.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/js/ext/resources/css/ext-all-notheme.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/js/ext/resources/css/xtheme-blue.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/ext-patch.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/index.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/icon.css"/>" />
<script type="text/javascript" language="Javascript"
	src="<c:url value="/js/ext/adapter/ext/ext-base.js"/>"></script>
<script type="text/javascript" language="Javascript"
	src="<c:url value="/js/ext/ext-all.js"/>"></script>
<script type="text/javascript" language="Javascript"
	src="<c:url value="/js/ext/ext-lang-zh_CN.js"/>"></script>
<script language="javascript" type="text/javascript">
// 交换平台配置
var listGrid;
var editWindow; var editWindow2;
var listStore;
var editAction = "";

Ext.onReady(function() {
	Ext.BLANK_IMAGE_URL = '../../js/ext/resources/images/default/s.gif';

	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'qtip';

	var listRecord = Ext.data.Record.create(['id', 'linkName',
			'linkType', 'linkCorp', 'linkSecurity', 'linkAmount','linkBandWidth','linkTypeCode']);

	listStore = new Ext.data.Store({
		storeId : 'listStore',
		url : "../../extLinkConf.do?m=index",
		reader : new Ext.data.JsonReader({
			totalProperty : 'amount',
			root : "results",
			id : 'id'
		}, listRecord),
		baseParams : {
			p : 1
		}
	});

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
				var selectedRows = listGrid.getSelectionModel().getSelections();
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
								url : '../../extLinkConf.do?m=delete',
								params : {
									ids : ids
								},
								success : function(response, options) {
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
		columns : [sm, {
			id : 'id',
			header : 'ID',
			dataIndex : 'id',
			width : 24,
			align : 'center',
			sortable : false,
			menuDisabled : true
		}, {
			header : '链路名称',
			dataIndex : 'linkName',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 60
		}, {
			header : '链路类型',
			dataIndex : 'linkTypeCode',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 60
		}, {
			header : '链路运营商',
			dataIndex : 'linkCorp',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 60,
            renderer : function(value, p, r) {
                if(value=='001'){
                    return "中国移动";
                } else if(value=='002'){
                    return "中国联通";
                } else if(value=='003'){
                    return "中国电信";
                } else {
                    return "其它";
                }
			}
		}, {
			header : '链路安全机制',
			dataIndex : 'linkSecurity',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 60
		}, {
			header : '链路数量',
			dataIndex : 'linkAmount',
			align : 'center',
			sortable : false,
			menuDisabled : true,
			width : 50
		},  {
			header : '带宽（M）',
			dataIndex : 'linkBandWidth',
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
		width : 530,
		height : 400,
		modal : true,
		autoScroll : true,
		closeAction : 'hide',
		buttons : [{
			xtype : "tbfill"
		}, {
			text : '保存',
			handler : function() {
                var linkName=Ext.getDom("linkName").value;//链路名称
                var linkPropertyCode=Ext.getDom("linkPropertyCode.info").value;//链路性质
                var linkTypeCode=Ext.getDom("linkTypeCode.info").value;//链路类型
                var linkCorp=Ext.getDom("linkCorp.info").value;//链路运营商
                var ip=Ext.getDom("ip").value;//接入网络地址
                var mask=Ext.getDom("mask").value;//接入网络子网掩码
                var gateway=Ext.getDom("gateway").value;//接入网关地址
                var linkSecurity=Ext.getDom("linkSecurity").value;//链路安全机制
                var linkAmount=Ext.getDom("linkAmount").value;//链路数量
                var linkBandWidth=Ext.getDom("linkBandWidth").value;//链路带宽
                if(linkName==""){
                    Ext.Msg.alert('提示', '链路名称不能为空');
                }else if(linkPropertyCode==""){
                    Ext.Msg.alert('提示', '链路性质不能为空');
                }else if(linkTypeCode==""){
                    Ext.Msg.alert('提示', '链路类型不能为空');
                }else if(linkCorp==""){
                    Ext.Msg.alert('提示', '链路运营商不能为空');
                }else if(ip==""){
                    Ext.Msg.alert('提示', '接入网络地址不能为空');
                }else if(mask==""){
                    Ext.Msg.alert('提示', '接入网络子网掩码不能为空');
                }else if(gateway==""){
                    Ext.Msg.alert('提示', '接入网关地址不能为空');
                }else if(linkSecurity==""){
                    Ext.Msg.alert('提示', '链路安全机制不能为空');
                }else if(linkAmount==""){
                    Ext.Msg.alert('提示', '链路数量不能为空');
                }else if(linkBandWidth==""){
                    Ext.Msg.alert('提示', '链路带宽不能为空');
                }else{
                    Ext.Ajax.request({
                        form : 'editForm',
                        success : function(reponse, options) {
                            var respText = Ext.util.JSON.decode(reponse.responseText);
                            var msg = respText.msg;
                            listStore.reload();
                            Ext.Msg.alert('提示', msg);
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
				editForm.getForm().reset();
			}
		}
	});
    editWindow2 = new Ext.Window({
        width : 530,
        height : 400,
        modal : true,
        autoScroll : true,
        closeAction : 'hide'
    });
	function showAddWindow() {
		editWindow.setTitle("新增信息", "win_add");
		editWindow.removeAll(true);
		editWindow.show();
		editWindow.load({
			url :'../../extLinkConf.do?m=showAdd',
			scripts : true
		});
	}

	var viewport = new Ext.Viewport({
		layout : 'border',
		border : false,
		loadMask:true,
		items : [listGrid]
	});

	listStore.load();

}); // / Ext onReady end!

function showDetail() {
	var selectGridRow = listGrid.getSelectionModel().getSelected();
	editWindow2.setTitle("详细信息", "win_add");
	editWindow2.removeAll(true);
	editWindow2.show();
	editWindow2.load('../../extLinkConf.do?m=detail&id=' + selectGridRow.data.id);
}

function showUpdate() {
	var selectGridRow = listGrid.getSelectionModel().getSelected();
	editWindow.setTitle("修改信息", "win_add");
	editWindow.removeAll(true);
	editWindow.show();
	editWindow.load({
		url : '../../extLinkConf.do?m=showUpdate&id=' + selectGridRow.data.id,
		callback : function() {
		//	init();
		},
		scripts : true
	});
}
</script>
</head>

<body>

</body>
</html>