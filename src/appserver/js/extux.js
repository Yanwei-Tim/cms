/*!
 * ext 扩展组件
 * @author xiangqi.java@gmail.com
 */

// 全局常量
BASEURL='/mc/';
PAGESIZE=15;

Ext.namespace('Ext.ux');

Ext.ux.PagingToolbar=Ext.extend(Ext.PagingToolbar,{
		pageSize: PAGESIZE,
        displayInfo: true,
        beforePageText: '第',
        afterPageText: '页，总共 {0} 页',
        refreshText: '刷新',
        firstText: '第一页',
        lastText: '最后一页',
        prevText: '上一页',
        nextText: '下一页',
        displayMsg: '显示第 {0} 条到 {1} 条记录 - 总共 {2} 条',
        emptyMsg: "没有记录"
});
