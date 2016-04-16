package com.hzjava.monitorcenter.service;
import java.util.List;

import com.hzjava.monitorcenter.domain.District;

public interface DistrictService {
	
	/**
	 * 查询所有省、直辖市级
	 */
	public List<District> findParents();
	
	/**
	 * 根据省、直辖市查询下属市、区、县
	 */
	public List<District> findChildByParent(Long parentId);

}
