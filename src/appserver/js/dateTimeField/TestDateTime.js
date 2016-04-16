

Ext.onReady(function() {
			function substr(str, len) {
				if (!str || !len) {
					return '';
				}
				// 预期计数：中文2字节，英文1字节
				var a = 0;
				// 循环计数
				var i = 0;
				// 临时字串
				var temp = '';
				for (i = 0; i < str.length; i++) {
					if (str.charCodeAt(i) > 255) {
						// 按照预期计数增加2
						a += 2;
					} else {
						a++;
					}
					// 如果增加计数后长度大于限定长度，就直接返回临时字符串
					if (a > len) {
						return temp;
					}
					// 将当前内容加到临时字符串
					temp += str.charAt(i);
				}
				// 如果全部是单字节字符，就直接返回源字符串
				return str;
			}

			/** *********************************************请假Form******************************************************* */
			// 红星
			redStar = '<span style="color:red">*</span>';
			// 申请单号
			var noTxt = new Ext.form.TextField({
						fieldLabel : redStar + '申请单号',
						name : 'attend.no',
						anchor : '95%',
						value : 'AA123001',
						readOnly : true
					})
			// 申请人
			var attendNameTxt = new Ext.form.TextField({
						fieldLabel : redStar + '申请人',
						name : 'attendName',
						anchor : '95%',
						value : 'admin',
						readOnly : true
					})
			// 申请日期
			var applyDateTxt = new Ext.form.TextField({
						fieldLabel : redStar + '申请日期',
						name : 'attend.applyDate',
						anchor : '95%',
						value : new Date().dateFormat('Y-m-d H:m:s'),
						readOnly : true
					})
			// 请假始于
			var beginDateDf = new Cls.form.DateTimeField({
						fieldLabel : redStar + '请假始于',
						name : 'attend.beginDate',
						format : 'Y-m-d H:i:s',
						editable : false,
						style : 'padding-left:0px;',
						anchor : '95%,95%',
						readOnly : true,
						listeners : {
							'select' : function(beginDateDf, date) {
							}
						}
					})

			// 请假至于
			var endDateDf = new Cls.form.DateTimeField({
						fieldLabel : redStar + '请假至于',
						name : 'attend.endDate',
						format : 'Y-m-d H:i:s',
						editable : false,
						style : 'padding-left:0px;',
						anchor : '95%,95%',
						readOnly : true,
						listeners : {
							'select' : function(endDateDf, date) {
							}
						}
					})

			// 请假类型
			var leaveTypeStores = new Ext.data.ArrayStore({
						fields : ['leaveType', 'leaveType_d'],
						data : [[1, '事假'], [2, '病假'], [3, '婚假'], [4, '产假'],
								[5, '丧家'], [6, '探亲假'], [7, '小产假'], [8, '年假']]
					});

			var leaveTypeCmb = new Ext.form.ComboBox({
						editable : false,
						emptyText : '请选择',
						triggerAction : 'all',
						fieldLabel : redStar + '请假类型',
						hiddenName : 'attend.leaveType',
						mode : 'local',
						valueField : 'leaveType',
						displayField : 'leaveType_d',
						store : leaveTypeStores,
						anchor : '95%',
						allowBlank : false
					})
			// 请假具体原因
			var noteArea = new Ext.form.HtmlEditor({
						fieldLabel : redStar + '请假原因',
						name : 'attend.note',
						anchor : '95%',
						maxLength : 2000,
						height : 200,
						enableKeyEvents : true,
						allowBlank : false
					})
			noteArea.on("keyup", function(noteArea, e) {
						if (noteArea.getValue().length > 2000)
							noteArea.setValue(noteArea.getValue().substring(0,
									2000));
					})
			var reader = new Ext.data.JsonReader({
						root : 'root'
					}, [{
								name : 'attend.no',
								mapping : 'no'
							}, {
								name : 'attend.attendEmpId',
								mapping : 'attendEmpId'
							}, {
								name : 'attend.applyDate',
								mapping : 'applyDate'
							}, {
								name : 'attend.beginDate',
								mapping : 'beginDate'
							}, {
								name : 'attend.endDate',
								mapping : 'endDate'
							}, {
								name : 'attend.leaveType',
								mapping : 'leaveType'
							}, {
								name : 'attend.note',
								mapping : 'note'
							}])
			var mainForm = new Ext.form.FormPanel({
						labelAlign : 'right',
						labelWidth : 62,
						region : "center",
						frame : true,
//						disabledClass : "x-item-disabled",
						reader : reader,
						items : [{
									xtype : 'fieldset',
									autoHeight : true,
									margins : '10 5 5 5',
									items : [{// 第1行
										layout : 'column',
										items : [{
													columnWidth : .50,
													layout : 'form',
													items : noTxt
												}, {
													columnWidth : .50,
													layout : 'form',
													items : attendNameTxt
												}]
									}, {	// 第2行
												layout : 'column',
												items : [{
															columnWidth : .50,
															layout : 'form',
															items : applyDateTxt
														},{
															columnWidth : .50,
															layout : 'form',
															items : leaveTypeCmb
														}]
											}, {// 第3行
												layout : 'column',
												items : [{
															columnWidth : .60,
															layout : 'form',
															items : beginDateDf
														}]
											},{//第4行
												layout : 'column',
												items : [ {
															columnWidth : .60,
															layout : 'form',
															items : endDateDf
														}]
											
											}, {// 第5行
												layout : 'column',
												items : [{
															columnWidth : 1,
															layout : 'form',
															items : noteArea
														}]
											}]
								}]
					})
			/** *****************************************************toolbar工具条**************************************************** */
			var toolbar = new Ext.Toolbar([ "-",{
						id : 'save',
						icon : 'js/images/save.gif',
						text : '保存',
						handler : function() {
						}
					}, "-", {
						id : 'tally',
						icon : 'js/images/btn-save.png',
						text : '记账',
						handler : function() {
						}
					}, "-", {
						id : 'cancel',
						text : '取消',
						icon : 'js/images/btn-cancel.png',
						handler : function() {
							update_Win.close();
						}
					} ,"-"]);
			/** *******************************************************显示窗口********************************************** */
			var update_Win = new Ext.Window({
						plain : true,
						layout : 'form',
						resizable : true, // 改变大小
						draggable : true, // 不允许拖动
						closeAction : 'close',// 可被关闭 close or hide
						modal : true, // 模态窗口
						width : 600,
						height : 400,
						title : '请假申请',
						items : [toolbar, mainForm],// 嵌入数据
						buttonAlign : 'center',
						loadMask : true
					});
			update_Win.show();

		})