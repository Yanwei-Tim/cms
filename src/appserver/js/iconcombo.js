Ext.BLANK_IMAGE_URL = '/js/ext/resources/images/default/s.gif';
iconcombo = function(){

	return {
		init:function(){
			var icnCombo = new Ext.ux.IconCombo({
		//		renderTo:'equIconCode.info',
				store:new Ext.data.SimpleStore({
					fields:['code','name','flag'],
					data:[
					      ['1001','1001','x-flag-1001'],
					      ['1002','1002','x-flag-1002'],
					      ['1003','1003','x-flag-1003']
					      ]
				}),		
				valueField:'code',
				displayField:'name',
				iconClsField:'flag',
				triggerAction:'all',
				mode:'local',
				name:'equIconCode',
				width:160			
			});
			icnCombo.render('equIconCode.info');
			icnCombo.setValue('1002');
		}
	};
}();