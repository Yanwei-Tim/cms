package com.hzjava.monitorcenter.service;

import com.hzjava.monitorcenter.domain.District;
import com.hzjava.monitorcenter.domain.Orgcode;

import java.util.List;

public interface OrgcodeService {
	
	/**
	 * 查询所有省、直辖市级
	 */
	public String findParents();
	
	/**
	 * 根据省、直辖市查询下属市、区、县
	 */
	public List<Orgcode> findChildByParent(Long parentId);
	public String showBusinessExchModel();

    public String showExchangeDirection();

    public String showUseDepartType();

    public String showProtocolType();
}
