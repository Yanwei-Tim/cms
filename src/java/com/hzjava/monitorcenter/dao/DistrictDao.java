package com.hzjava.monitorcenter.dao;

import java.util.List;

import com.hzjava.monitorcenter.domain.District;

import cn.collin.commons.dao.BaseDao;

public interface DistrictDao extends BaseDao {
	
	List<District> getAllParents();
	
	List<District> findChildByParent(Long parentId);
	
	List<District> findChildFirst();
	
	District findById(Long id);
}
