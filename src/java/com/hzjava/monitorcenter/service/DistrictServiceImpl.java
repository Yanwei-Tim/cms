package com.hzjava.monitorcenter.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.hzjava.monitorcenter.dao.DistrictDao;
import com.hzjava.monitorcenter.domain.District;

public class DistrictServiceImpl implements DistrictService {

	private final static Logger logger = Logger.getLogger(DistrictServiceImpl.class);
	private DistrictDao districtDao;

	@Override
	public List<District> findChildByParent(Long parentId) {
		return districtDao.findChildByParent(parentId);
	}

	public DistrictDao getDistrictDao() {
		return districtDao;
	}

	public void setDistrictDao(DistrictDao districtDao) {
		this.districtDao = districtDao;
	}

	public static Logger getLogger() {
		return logger;
	}

	@Override
	public List<District> findParents() {
		return districtDao.getAllParents();
	}

}
