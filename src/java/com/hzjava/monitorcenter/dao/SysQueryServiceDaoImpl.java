package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.MyDaoSupport;
import cn.collin.commons.domain.PageResult;
import com.hzjava.monitorcenter.domain.SysQueryService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 钱晓盼
 * Date: 12-8-10
 * Time: 上午9:50
 * To change this template use File | Settings | File Templates.
 */
public class SysQueryServiceDaoImpl extends MyDaoSupport implements SysQueryServiceDao {
    @Override
    public void setEntityClass() {
        this.entityClass = SysQueryService.class;
    }


    @Override
    public PageResult listByPage(String objectName, int pageIndex, int limit) {
        String hql = "from SysQueryService where 1=1 ";
		List paramsList = new ArrayList();
        if(objectName!=null&&objectName.length()>0){
            hql += " and objectName = ? ";
            paramsList.add(objectName);
        }
        hql += " order by id desc ";
		String countHql = "select count(*) " + hql;

		PageResult ps = this.findByPage(hql, countHql, paramsList.toArray(),
				pageIndex, limit);
		return ps;
    }


}
