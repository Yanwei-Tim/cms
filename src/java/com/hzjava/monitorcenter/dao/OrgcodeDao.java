package com.hzjava.monitorcenter.dao;

import cn.collin.commons.dao.BaseDao;
import com.hzjava.monitorcenter.domain.District;
import com.hzjava.monitorcenter.domain.Orgcode;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 13-1-17
 * Time: 下午1:31
 * To change this template use File | Settings | File Templates.
 */
public interface OrgcodeDao extends BaseDao{
    public List list(String hql)throws Exception;
    
    public List findOrgcode(String code);
    
    public List<Orgcode> findDepart(String userZone,String selectDepart);
    List<Orgcode> getAllParents(String code);



}
